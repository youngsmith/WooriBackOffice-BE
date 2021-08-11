#!/bin/sh

currentTime=$(date "+%Y_%m_%d")
fileName=db_back_up_${currentTime}.sql

# echo ${fileName}
# docker exec -t -u postgres {containerID} pg_dumpall -c > dump_postgresql.sql

docker ps
resultCode=$(echo $?)
echo $resultCode

if [ $resultCode != 0 ]; then
	echo $resultCode
	echo "docker not work!"
	exit 0
fi

containerId=$(docker ps | grep "postgres" | awk '{ printf "%s", $1}')
echo $containerId

if [ -z $containerId ]; then
	echo "container not work!"
	exit 0
fi

cd /desktop/sql_dump/
docker exec -t -u postgres ${containerId} pg_dumpall -c > ./dump_postgresql_${currentTime}.sql
