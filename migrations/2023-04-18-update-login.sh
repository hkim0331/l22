#!/bin/sh
set -e

pg='psql -h db -U postgres l22'

${pg} -c "update users set login='kengo' where login=' kengo';"
${pg} -c "update users set login='Lemon_1108' where login='Lemon*1108';"
${pg} -c "update users set login='shima_528' where login='shima.528';"
${pg} -c "update users set login='shoei_567' where login='shoei.567';"
${pg} -c "update users set login='tak_m1022' where login='tak.m1022';"
${pg} -c "update users set login='tana' where login='たな';"
${pg} -c "update users set login='_s2chll04' where login='＿ｓ2chll04';"
