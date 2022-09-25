#!/bin/sh
lein uberjar && \
scp target/default+uberjar/l22.jar app.melt:l22/ && \
ssh app.melt 'sudo systemctl restart l22' && \
ssh app.melt 'systemctl status l22'

