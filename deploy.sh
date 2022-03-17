#!/bin/sh
lein uberjar
scp target/uberjar/l22.jar app.melt:l22/
ssh app.melt 'sudo systemctl restart l22'

