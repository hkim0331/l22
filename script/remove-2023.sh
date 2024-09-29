#!/bin/sh
set -o errexit -o nounset -o pipefail
PG="psql -h localhost -U postgres"
${PG} l22 -c "update users set subj='python' where login='hkimura'"
${PG} l22 -c "delete from users where subj is null"
${PG} l22 -c "delete from users where created_at < '2024-04-01'"
${PG} l22 -c "update users set updated_at=CURRENT_TIMESTAMP where login='hkimura'"
echo done
