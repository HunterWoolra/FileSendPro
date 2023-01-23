package KafkaPubSub;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class Consumer {

    public static void main(String[] args) {

        Properties configs = new Properties();
        configs.put("bootstrap.servers", "localhost:9092"); // server host 설정
        configs.put("session.timeout", "10000"); // session 설정
        configs.put("group.id", "woolra"); // topic 설정
        configs.put("enable.auto.commit","true"); // offset을 자동으로 commit하도록 함.
        configs.put("auto.offset.reset","latest"); // offset이 없는경우는 가장 마지막의 오프셋값으로 설정
        configs.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); // key deserializer
        configs.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); // value deserializer

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(configs); // consumer 생성
        consumer.subscribe(Arrays.asList("woolra")); // topic 설정

        System.out.println("======== 1. consumer start ========");

        while (true) {
            System.out.println("======== 2. ConsumerRecord start ========");
            // poll(long)은 2.0버전이후 Deprecated, Duration을 사용해야한다. 이것때문에 한참 찾음...
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> record : records) {
                String input = record.topic();
                if ("woolra".equals(input)) {
                    System.out.println(record.value());
                } else {
                    throw new IllegalStateException("get message on topic " + record.topic());
                }
            }
        }


    }// end main

}// end class

