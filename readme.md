This is a small example showing how to produce specific Avro classes to Kafka
Using Confluent's Schema Repository and Avro Serializers.

Specific Avro classes mean that we use Avro's code generation to generate the LogLine class, then populate it and produce to Kafka.

To build this producer:

    $ cd sabat-account-generation-poc
    $ mvn clean package
    
Quickstart
-----------

Before running the examples, make sure that Zookeeper, Kafka and Schema Registry are
running. In what follows, we assume that Zookeeper, Kafka and Schema Registry are
started with the default settings.

    # Start Zookeeper
    $ bin/zookeeper-server-start config/zookeeper.properties

    # Start Kafka
    $ bin/kafka-server-start config/server.properties

    # Start Schema Registry
    $ bin/schema-registry-start config/schema-registry.properties
    
or

    # Start Full Confluent Platform
    $ bin/confluent start
    
    
Then create a topic called portal-beneficiaries (if not it will be created automatically with default parameters):

    # Create portal-beneficiaries topic
    $ bin/kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 \
      --partitions 1 --topic portal-beneficiaries
      

Then run the producer to produce 10 beneficiaries

    $ java -cp target/uber-sabat-account-generation-poc-1.0-SNAPSHOT.jar com.sab2i.portal.beneficiaries.Main 10 http://localhost:8081
    
You can validate the result by using the avro console consumer (part of the schema repository):

    $ bin/kafka-avro-console-consumer --bootstrap-server localhost:9092 --topic portal-beneficiaries --from-beginning