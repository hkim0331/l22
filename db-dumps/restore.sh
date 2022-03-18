#!/bin/sh
if [ -z "$1" ]; then
    echo usage: $0 yyyy-mm-dd.dumo
    exit 1
fi

PSQL="psql -h localhost -U postgres"
${PSQL} -c "drop database l22"
${PSQL} -c "create database l22 owner='postgres'"
${PSQL} l22 < $1

