#!/bin/sh
# 2023-06-03
# 2023年受講の subj を literacy とする。
set -e

pg='psql -h db -U postgres l22'

${pg} -c "update users set subj='literacy', updated_at=now()
            where date(created_at) > '2023-04-01'"
