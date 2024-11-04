#
    Name: Deom app
    Description: This is a repo to dest Dockerfile
#
    Commands:
        mvn clean install
        java -jar target/db-docker-web-app-0.0.1-SNAPSHOT.jar --data.file.path=database/file.json
        docker build -t cormegarepo/my-app-image .
        docker run -it -d -p 8089:8089 --name container-web-app cormegarepo/my-app-image:latest
#
