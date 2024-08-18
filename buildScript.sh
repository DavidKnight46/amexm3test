#! /bin/bash

git checkout docker
git pull

cd ../../../

cp ./build/libs/*.jar ./src/main/docker

cd ./src/main/docker

docker image build -t app .

docker-compose up