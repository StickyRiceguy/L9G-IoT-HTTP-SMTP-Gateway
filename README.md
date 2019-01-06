# L9G-IoT-HTTP-SMTP-Gateway

## Configuration

- copy `config/mail.properties.sample` to `config/mail.properties`
- edit `config/mail.properties`

## Run Gateway

Simply start the JAR file `./l9g-iot-http-smtp-gateway.jar`

## Use different Port than 8080

Start JAR file with parameter `./l9g-iot-http-smtp-gateway.jar --server.port=8100`

## Send Mail

HTTP Get `http://<your host>:<port>/sm?fr=<from>?to=<to>?su=<subject>?me=<message>`

** No parameter is required **
