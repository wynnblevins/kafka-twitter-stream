# Kafka Twitter Stream
This project takes tweets from twitter and puts them into a kafka topic.

## Running Locally
Follow these steps in order to run the project locally.

### Getting Started
This project needs both a running zookeeper and kafka server.  You should first start zookeeper by running
```
~/path/to/kafka-dir/bin/zookeeper-server-start.sh ~/path/to/kafka-dir/config/zookeeper.properties
```
...then, in a new terminal, start the kafka server
```
~/path/to/kafka-dir/bin/kafka-server-start.sh ~/path/to/kafurka-dir/config/server.properties
```

### Run With Gradle
Having completed the above steps, start the project by running:
```
gradle bootRun 
```
Your local server will now be live on localhost:8080

### Running in a Docker Container
You may want to have the docker container run in the background on a port of your choosing.  To do this run the following command but be sure to replace "internal" and "external" with the internal and external ports for your container (for example 8080:8080).  Also, you should replace "tagname" with an appropriate tag name from dockerhub.
```
sudo docker run --rm -d -p external:internal wynnblevins/twitterstream:tagname
```
The above command will launch the twitter stream application within a docker container running in detached mode (that is to say, in the background).  You can view the docker container which should now be running, by executing the following:
```
sudo docker container ls
```
To remove the docker container the previous command listed, replace "ContainerID" in the following command with the ID of the docker container you want to remove.  
```
sudo docker container rm -f ContainerID
```

## Initial Database Setup
The database should be run in a docker postgres container.  To start the postgresql database in docker container run the following.
```
sudo docker run --rm --name pg-docker -e POSTGRES_PASSWORD=postgres -d -p 5432:5432 postgres
```

### Create/Scaffold Database With Liquibase
Run the following command to get the container id of the postgres database you stood up in the previous step.
```
docker container ls
```

Use id of the postgres container (see previous step) to open a shell inside the docker container:
```
docker exec -it {container_id} bash
``` 

Log into the postgresql database by running:
```
psql -U postgres 
```

Create the database schema with the following command:
```
CREATE DATABASE kafkatwitterstream;
```

To scaffold the database schema, run the following command:
```
liquibase --driver=org.postgresql.Driver --classpath=postgresql-42.2.8.jar --changeLogFile="src/main/resources/db/db.changelog-master.xml" --url=jdbc:postgresql://localhost:5432/kafkatwitterstream --username=postgres --password=postgres update
```