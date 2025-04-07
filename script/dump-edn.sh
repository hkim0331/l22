#!/bin/sh
set -o errexit -o nounset -o pipefail
http :3022/api/users/2024/python Accept:application/edn > users-2024.edn
