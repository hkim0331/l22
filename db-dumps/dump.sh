#!/bin/sh
pg_dump -h localhost -U postgres l22 > `date +l22-%F.sql`

