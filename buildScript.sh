#! /bin/bash

cd ./src/main/resources

pwd

#Run script for DB configuration
psql -U postgres -f ./buildDB.sql

cd ../../../

pwd

#Run SpringBoot application
./gradlew build

./gradlew bootRun