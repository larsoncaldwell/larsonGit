#!/bin/sh

# clean up on ^C to terminate script
trap 'pkill java; rm -f file.*; exit' 0 1 2 3 15

java -cp . Racer &

while true
do
   sleep 1
   find . -name "file.*" | sort
   echo "----------------------"
done