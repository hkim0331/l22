#!/bin/sh
ssh app.melt 'cd l22/db-dumps && ./dump.sh'
scp app.melt:l22/db-dumps/l22-`date +%F`.sql .
