import org.apache.kafka.clients.producer.Producer;

import java.util.concurrent.ExecutionException;

/**
 * Kafka Producer with Java
 */
public class Application {

    public static void main(String[] args) {
        Application kafkaApplication = new Application();
//        Producer<Long, String> kafkaProducer = kafkaApplication.createKafkaProducer(...);
    }

    public void produceMessages(int numberOfMessages, Producer<Long, String> kafkaProducer) throws ExecutionException, InterruptedException {

    }

//    public Producer<Long, String> createKafkaProducer(String bootstrapServers) {
//
//    }

}
