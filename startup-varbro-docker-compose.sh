#!/bin/sh
mvn package
docker build -t varbro .
docker-compose up
