#!/bin/bash

mvn clean install
cp target/l9g-iot-http-smtp-gateway.jar .
mvn clean
