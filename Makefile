.DEFAULT_GOAL := build-run


clean:
	./gradlew clean

build:
	./gradlew clean build

install:
	./gradlew clean installDist

run-dist:
	./build/install/app/bin/app

run:
	./gradlew run

test:
	./gradlew test


build-run: build run

.PHONY: build
