package br.com.marciorafael.filewatcher.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${amqp.file-received.queue}")
    private String fileReceivedQueue;

    @Value("${amqp.file-received.exchange}")
    private String fileReceivedExchange;

    @Bean
    public Queue fileReceivedQueue() {
        return new Queue(fileReceivedQueue);
    }

    @Bean
    public Exchange fileReceivedExchange() {
        return new TopicExchange(fileReceivedExchange);
    }
}


