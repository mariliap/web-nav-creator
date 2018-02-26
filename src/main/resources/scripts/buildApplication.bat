@echo off

Color 0b
cd ../../../..
call mvn clean
call mvn package appassembler:assemble
pause