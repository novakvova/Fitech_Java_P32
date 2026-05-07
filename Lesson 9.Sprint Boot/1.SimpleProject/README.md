# Run Spring Boot console
```
mvn spring-boot:run

mvn clean install -U

mvn clean
mvn package
java -jar target/p32.jar
```

## Run Docker java
```
docker build -t p32-java .

docker run -it --rm -p 6892:8087 --name p32-java_container p32-java

docker run -d --restart=always -p 6892:8087 --name p32-java_container p32-java

docker run -d --restart=always -v d:/volumes/p32-java/mp3Songs:/app/mp3Songs -p 6892:8087 --name p32-java_container p32-java

docker run -d --restart=always -v /volumes/p32-java/mp3Songs:/app/mp3Songs -p 6892:8087 --name p32-java_container p32-java

docker ps -a

docker stop p32-java_container
docker rm p32-java_container
docker rmi p32-java

docker login
docker tag p32-java:latest novakvova/p32-java:latest
docker push novakvova/p32-java:latest

```

# Run app
```
dos2unix java-p32.sh
chmod +x java-p32.sh
./java-p32.sh
```