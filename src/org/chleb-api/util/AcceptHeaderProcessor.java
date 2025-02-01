import java.util.*;
import java.util.stream.Collectors;

public class AcceptHeaderProcessor {

    public static void main(String[] args) {
        // Example usage
        String acceptHeader = "text/html,application/xhtml+xml,application/xml;q=0.9,/;q=0.8";
        List<String> supportedTypes = Arrays.asList("application/xml", "text/html", "application/json");

        Optional<String> bestMatch = getBestMatch(acceptHeader, supportedTypes);
        bestMatch.ifPresentOrElse(
            type -> System.out.println("Best match: " + type),
            () -> System.out.println("No match found.")
        );
    }

    /**
     * Processes the Accept header and returns the best match from the supported types.
     *
     * @param acceptHeader   The Accept header from the HTTP request.
     * @param supportedTypes A list of supported media types.
     * @return The best matching media type, or an empty Optional if no match is found.
     */
    public static Optional<String> getBestMatch(String acceptHeader, List<String> supportedTypes) {
        if (acceptHeader == null || acceptHeader.isEmpty()) {
            return Optional.empty();
        }

        // Parse the Accept header into media types with quality values
        List<MediaType> mediaTypes = Arrays.stream(acceptHeader.split(","))
            .map(MediaType::parse)
            .sorted() // Sort by quality value (highest first)
            .collect(Collectors.toList());

        // Find the best match
        for (MediaType mediaType : mediaTypes) {
            for (String supportedType : supportedTypes) {
                if (mediaType.matches(supportedType)) {
                    return Optional.of(supportedType);
                }
            }
        }
        return Optional.empty();
    }
}

/**
 * Represents a media type with an optional quality value.
 */
class MediaType implements Comparable<MediaType> {
    private final String type;
    private final double quality;

    private MediaType(String type, double quality) {
        this.type = type;
        this.quality = quality;
    }

    /**
     * Parses a media type string with an optional quality value.
     *
     * @param input The media type string.
     * @return A MediaType instance.
     */
    public static MediaType parse(String input) {
        String[] parts = input.trim().split(";");
        String type = parts[0].trim();
        double quality = 1.0; // Default quality value

        for (int i = 1; i < parts.length; i++) {
            String[] param = parts[i].trim().split("=");
            if (param.length == 2 && "q".equals(param[0].trim())) {
                try {
                    quality = Double.parseDouble(param[1].trim());
                } catch (NumberFormatException ignored) {
                    // Invalid quality value, use default
                }
            }
        }

        return new MediaType(type, quality);
    }

    /**
     * Checks if this media type matches another type.
     *
     * @param other The other media type to check.
     * @return True if the types match or this type is a wildcard (* or /).
     */
    public boolean matches(String other) {
        return "/".equals(type) || type.equalsIgnoreCase(other) || (type.endsWith("/*") && other.startsWith(type.substring(0, type.indexOf('/'))));
    }

    @Override
    public int compareTo(MediaType other) {
        return Double.compare(other.quality, this.quality); // Descending order
    }

    @Override
    public String toString() {
        return type + ";q=" + quality;
    }
}
