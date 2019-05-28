#!/usr/bin/env bash

set -e

export DATA_MAKER_HOME="$(cd "`dirname "$0"`"/..; pwd)"

# Find the java binary
if [ -n "${JAVA_HOME}" ]; then
  JAVA_RUN="${JAVA_HOME}/bin/java"
else
  if [ `command -v java` ]; then
    JAVA_RUN="java"
  else
    echo "JAVA_HOME is not set" >&2
    exit 1
  fi
fi

JAR_DIR=$DATA_MAKER_HOME/lib/*
CLASS_NAME=org.yemoge.bigdata.MakerMain

echo "data-maker starting ..."
$JAVA_RUN -cp $JAR_DIR $CLASS_NAME $@

