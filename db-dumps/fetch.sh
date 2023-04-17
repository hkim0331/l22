#!/bin/sh
ssh tiger 'cd l22/db-dumps && ./dump.sh'
scp tiger:l22/db-dumps/l22-`date +%F`.sql .

