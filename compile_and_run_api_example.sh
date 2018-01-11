#!/bin/bash

PATHSEP=":"
if [[ $OS == "Windows_NT" ]] || [[ $OSTYPE == "cygwin" ]]
then
    PATHSEP=";"
fi

CP="webroot/WEB-INF/lib/*${PATHSEP}systemet-api"

javac -cp "$CP" systemet-api/examples/ApiExample.java && java -cp "$CP" examples.ApiExample
