import org.apache.kafka.clients.producer.MockProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ApplicationTest {

    private static final String TOPIC = "events";

    @Test
    public void testProducesMessages() throws ExecutionException, InterruptedException {
        MockProducer mockProducer = new MockProducer<>(true, new LongSerializer(), new StringSerializer());
        Application testApp = new Application();

        testApp.produceMessages(10, mockProducer);

        assertTrue(mockProducer.history().size() == 10);
    }

    @Test
    public void testKeyValue() throws ExecutionException, InterruptedException {
        MockProducer mockProducer = new MockProducer<>(true, new LongSerializer(), new StringSerializer());
        Application testApp = new Application();

        testApp.produceMessages(10, mockProducer);

        ProducerRecord<Long, String> record  = (ProducerRecord<Long, String>) mockProducer.history().get(9);
        assertEquals(9, record.key());
        assertEquals("event 9", record.value());
    }

    @Test
    public void testTopic() throws ExecutionException, InterruptedException {
        MockProducer mockProducer = new MockProducer<>(true, new LongSerializer(), new StringSerializer());
        Application testApp = new Application();

        testApp.produceMessages(10, mockProducer);

        ProducerRecord<Long, String> record  = (ProducerRecord<Long, String>) mockProducer.history().get(9);
        assertEquals(TOPIC, record.topic());
    }

}