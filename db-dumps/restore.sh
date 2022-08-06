#!/bin/sh
# 2022-05-07 --last option
# 2022-07-02 --latest is same with --last

DB=l22

if [ -z "$1" ]; then
    echo "usage: $0 db-yyyy-mm-dd.sql"
    echo "       $0 --last|--latest"
    echo restore postgresql database from ${DB}-yyyy-mm-dd.sql
    echo mind: this script drops database first.
    exit 1
elif [ "$1" = "--last" -o "$1" = "--latest" ]; then
    DUMP=`ls -t ${DB}* | head -1`
else
    DUMP=$1
fi

PSQL="psql -h localhost -U postgres"
${PSQL} -c "drop database ${DB}"
${PSQL} -c "create database ${DB} owner='postgres'"
${PSQL} ${DB} < ${DUMP}

