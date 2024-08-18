#! /bin/bash

git checkout docker
git pull

docker image build -t app .

cd ./src/main/resources

docker-compose up