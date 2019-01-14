package com.sab2i.portal.beneficiaries;

import com.sab2i.sabhd.portal.beneficiary.avro.PortalBeneficiary;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class PortalBeneficiaryProducer {
    private BeneficiaryGenerator beneficiaryGenerator = new BeneficiaryGenerator();
    private String schemaUrl;
    private long count;

    public PortalBeneficiaryProducer(String schemaUrl, long count) {
        this.schemaUrl = schemaUrl;
        this.count = count;
    }

    public void process(String topic) throws ExecutionException, InterruptedException {
        Producer<String, PortalBeneficiary> producer = new KafkaProducer<>(getKafkaProperties());

        for (long nEvents = 0; nEvents < count; nEvents++) {
            PortalBeneficiary beneficiary = beneficiaryGenerator.getNextBeneficiary();

            ProducerRecord<String, PortalBeneficiary> record = new ProducerRecord<>(topic, beneficiary.getIban().toString(), beneficiary);
            producer.send(record).get();
        }
    }

    public Properties getKafkaProperties() {
        Properties props = new Properties();
        // hardcoding the Kafka server URI for this example
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("key.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
        props.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
        props.put("schema.registry.url", schemaUrl);
        return props;
    }

}
