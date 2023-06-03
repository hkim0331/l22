#!/bin/sh
ssh tiger.melt 'cd l22/db-dumps && ./dump.sh'
scp tiger.melt:l22/db-dumps/l22-`date +%F`.sql .
