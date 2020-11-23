import org.apache.kafka.clients.consumer.Consumer;

/**
 * Kafka Consumer Application
 */
public class Application {
    private static final String TOPIC = "events";
    private static final String BOOTSTRAP_SERVERS = "localhost:9092,localhost:9093,localhost:9094";

    public static void main(String[] args) {
        Application kafkaConsumerApp = new Application();

        String consumerGroup = "defaultConsumerGroup";
        if (args.length == 1) {
            consumerGroup = args[0];
        }

        System.out.println("Consumer is part of consumer group " + consumerGroup);

        Consumer<Long, String> kafkaConsumer = kafkaConsumerApp.createKafkaConsumer(BOOTSTRAP_SERVERS, consumerGroup);

        kafkaConsumerApp.consumeMessages(TOPIC, kafkaConsumer);
    }

    public static void consumeMessages(String topic, Consumer<Long, String> kafkaConsumer) {
        // COMPLETE THIS METHOD

    }

    public Consumer<Long, String> createKafkaConsumer(String bootstrapServers, String consumerGroup) {
        // COMPLETE THIS METHOD
    }

}
