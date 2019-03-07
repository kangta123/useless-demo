#!/bin/sh
temporary=0
if [[ ! -d "./docker" ]]; then
    temporary=1
    mkdir docker
fi
#docker build -t 9fbank_jdk8:latest -f Dockerfile.jdk8 .

echo $temporary
if [[ ${temporary} = "1" ]]; then
    rm -fr docker
fi

