#!/bin/sh
PORT=3022 \
DATABASE_URL=jdbc:postgresql://db/l22?user=postgres \
lein run

