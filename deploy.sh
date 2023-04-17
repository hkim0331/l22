#!/bin/sh
lein uberjar && \
scp target/uberjar/l22.jar p.melt:l22/ && \
ssh p.melt 'sudo systemctl restart l22' && \
ssh p.melt 'systemctl status l22'
