#!/bin/sh
set -e

pg='psql -U postgres -h localhost l22'

${pg} -c "update users set sid='201A4064.2022' where sid='201A4064';"

for u in 231A2132 231A2094 231A2148 231A2091 231A2145 231A2111; do
    ${pg} -c "update users set uhour='wed2' where sid='${u}';"
done

for u in 231A2114 231A2156 231A2159; do
    ${pg} -c "delete from users where sid='${u}';"
done
