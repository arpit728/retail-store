#!/usr/bin/env sh
java -jar app.jar db migrate config/docker.yml
java -jar app.jar server config/docker.yml