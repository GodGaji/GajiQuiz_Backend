#!/bin/sh

dockerize -wait tcp://db:3306 -timeout 10s

# Start server
echo "Start Application"
java -jar gaji-quiz.jar