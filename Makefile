#!/usr/bin/make -f

all: build binary

binary:

clean:
	mvn clean

build: pom.xml
	mvn -e verify

.PHONY: build clean binary all
