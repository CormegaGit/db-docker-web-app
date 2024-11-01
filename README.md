#
    Name: Deom app
    Description: This is a repo to dest Dockerfile
#
    Commands:
        mvn clean install
        java -jar target/db-docker-web-app-0.0.1-SNAPSHOT.jar --data.file.path=database/file.json
#
