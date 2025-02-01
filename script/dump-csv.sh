#!/bin/sh
set -o errexit -o nounset -o pipefail
PG="psql -h localhost -U postgres"
