#!/bin/bash

mvn clean compile assembly:single
cp target/*.jar java-ssmaparm.jar

echo "build completed, the jar file is java-ssmparam.jar"
