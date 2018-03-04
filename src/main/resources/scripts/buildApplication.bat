@echo off

Color 0b
cd ../../../..

call mvn package appassembler:assemble
pause