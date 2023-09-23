package com.example.ewallet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

import java.util.Properties;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class TransactionConfig {
    Properties getProducerConfig(){
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return properties;
    }

    ProducerFactory<String, String> getProducerFactory(){
        return new DefaultKafkaProducerFactory(getProducerConfig());
    }

    @Bean
    KafkaTemplate<String, String> getKafkaTemplate(){
        return new KafkaTemplate<>(getProducerFactory());
    }

    @Bean
    ObjectMapper getMapper(){
        return new ObjectMapper();
    }

    Properties getConsumerConfig(){
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return properties;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/transact").authenticated()
                        .requestMatchers("/**").permitAll()
                )
                .formLogin(withDefaults())
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    ConsumerFactory<String, String> getConsumerFactory(){
        return new DefaultKafkaConsumerFactory(getConsumerConfig());
    }

    @Bean
    RestTemplate getTemplate(){
        return new RestTemplate();
    }
}
