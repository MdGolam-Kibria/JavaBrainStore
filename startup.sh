#!/bin/bash
kill $(cat /app/sms-service/pid.file)
nohup /usr/bin/java -Dlogging.config=/app/sms-service/config/logback-spring.xml -jar /app/sms-service/sms-service-1.0.jar --spring.config.location=/app/sms-service/config/application.properties > /app/sms-service/consoleLog.txt 2>&1 &
echo $! > /app/sms-service/pid.file
