package fun.shijin.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

/**
 * @Author Jiaman
 * @Date 2021/1/15 21:46
 * @Desc 建一个消费者对象，用来消费数据
 */

public class Consumer {
    public static void main(String[] args) throws IOException {
        Properties props = new Properties();
        props.load(Consumer.class.getClassLoader().getResourceAsStream("consumer.properties"));

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

        consumer.subscribe(Arrays.asList("first"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
            }

        }
    }
}
