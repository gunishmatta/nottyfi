package com.gunish.nottyfi.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfiguration {
    private final Environment environment;

    public KafkaProducerConfiguration(Environment environment)
    {
        this.environment=environment;
    }

    @Bean
    public Map<String,Object> producerConfigs()
    {
        Map<String,Object> properties=new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,environment.getProperty("spring.kakfa.bootstrap-servers"));
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG,101680096);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 5000);


        return properties;
    }

    @Bean
    public ProducerFactory<Integer,String> producerFactory()
    {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<Integer,String> kafkaTemplate()
    {
        return new KafkaTemplate<>(producerFactory());
    }



}
