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