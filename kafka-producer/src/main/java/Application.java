import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * Kafka Producer with Java
 */
public class Application {

    private static final String TOPIC ="events";
    private static final String BOOTSTRAP_SERVER ="localhost:9092,localhost:9093,localhost:9094";

    public static void main(String[] args) {
        Application kafkaApplication = new Application();
        Producer<Long, String> kafkaProducer = kafkaApplication.createKafkaProducer(BOOTSTRAP_SERVER);

        //Ctrl + Alt + T
        try {
            produceMessages(10, kafkaProducer);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        } finally{
            kafkaProducer.flush();
            kafkaProducer.close();
        }
    }

    public static void produceMessages(int numberOfMessages, Producer<Long, String> kafkaProducer) throws ExecutionException, InterruptedException {
        //int partition = 0;  //specify a partition number

        //to generate messages using different keys and values
        for(int i = 0; i < numberOfMessages; i++){
            //Record contains key,value and timestamp
            long key = i;
            String value = String.format("event %d", i);
            long timestamp = System.currentTimeMillis();

            //send directly to a particular partition
            //--ProducerRecord<Long, String> record = new ProducerRecord(TOPIC, partition, timestamp, key, value);

            //Use key, let kafka client library choose partition based on hash of key
            //--ProducerRecord<Long, String> record = new ProducerRecord(TOPIC, key, value);

            //left out key, messages sent using round-robin strategy
            ProducerRecord<Long, String> record = new ProducerRecord(TOPIC, value);

            //Send record to kafka and get result
            RecordMetadata recordMetadata = kafkaProducer.send(record).get();

            System.out.println(String.format("Record with (key: %d, value: %s) was sent to (partition: %d , offset: %d",
                    record.key(), record.value(), recordMetadata.partition(), recordMetadata.offset()));
        }
    }

                   //The key in our message is going to be long, and the values are going to be String
    public Producer<Long, String> createKafkaProducer(String bootstrapServers) {
        //--------To set up the basic configuration that I need for the kafka producer--------

        //Create Properties object
        Properties properties = new Properties();

        //The location of the bootstrap server
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        //To give the produce a name
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "events-producer");
        //This will tell Kafka that key should be serialized
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        //This will tell Kafka that value should be serialized
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());


        return new KafkaProducer<Long, String>(properties);
    }
}
