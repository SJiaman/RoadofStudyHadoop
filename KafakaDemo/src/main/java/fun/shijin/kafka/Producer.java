package fun.shijin.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

/**
 * @Author Jiaman
 * @Date 2021/1/15 21:02
 * @Desc 创建生产者对象，发送数据
 */

public class Producer {
    public static void main(String[] args) {
        //加载配置文件
        Properties props = new Properties();
        props.put("bootstrap.servers", "hadoop01:9092");//kafka集群，broker-list
        props.put("acks", "all");
        props.put("retries", 1);//重试次数
        props.put("batch.size", 16384);//批次大小
        props.put("linger.ms", 1);//等待时间
        props.put("buffer.memory", 33554432);//RecordAccumulator缓冲区大小
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        //创建producer对象
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);

        //发送数据
        for (int i = 0; i < 10; i++) {
            producer.send(
                    new ProducerRecord<String, String>("first", Integer.toString(i), Integer.toString(i)),
                    new Callback() {
                        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                            if (e == null) {
                                System.out.println(recordMetadata);
                            }
                        }
                    });
            System.out.println("发了" + i + "条数据");
        }


        //关闭
        producer.close();

    }
}
