@echo off

echo Docker login...
docker login

echo Building Docker image api...
docker build -t p32-java .

echo Tagging Docker image api...
docker tag p32-java:latest novakvova/p32-java:latest

echo Pushing Docker image api to repository...
docker push novakvova/p32-java:latest

echo Done ---api---!
pause