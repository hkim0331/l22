#!/bin/sh
set -e

pg='psql -h db -U postgres l22'
${pg} -c "update users set uhour='wed2' where uhour='none' and created_at > '2023-04-12 11:00:00';"
${pg} -c "update users set uhour='wed1' where uhour='none'"
