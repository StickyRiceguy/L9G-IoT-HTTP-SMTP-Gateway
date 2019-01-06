# L9G-IoT-HTTP-SMTP-Gateway

This is a very very simple Gateway for sending an eMail via a HTTP GET request.
At this time the gateway allways returns immediate 'OK', non blocking your embedded device. Errors can only be detected in the LOG output of the gateway.

## Configuration

- copy `config/mail.properties.sample` to `config/mail.properties`
- edit `config/mail.properties`

## Run Gateway

Simply start the JAR file `./l9g-iot-http-smtp-gateway.jar`

## Use different Port than 8080

Start JAR file with parameter `./l9g-iot-http-smtp-gateway.jar --server.port=8100`

## Send Mail

HTTP GET `http://<your host>:<port>/sm?fr=<from>?to=<to>?su=<subject>?me=<message>`

** No parameter is required **

## Java

This gateway is developed and testet with Java JDK 8u191 and the [NetBeans IDE 8.2](https://netbeans.org/).

## Java on RaspberryPi

For Java on the RaspberryPi i like [BellSofts Liberica JDK](https://bell-sw.com/java.html).
