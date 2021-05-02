package br.com.marciorafael.filewatcher.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileProducer {

    private final Logger logger = LoggerFactory.getLogger(FileProducer.class);

    @Value("${amqp.file-received.routing-key}")
    private String fileReceivedRoutingKey;

    @Value("${amqp.file-received.exchange}")
    private String fileReceivedExchange;

    private final RabbitTemplate rabbitTemplate;

    public FileProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String filePath) {
        try {
            rabbitTemplate.convertAndSend(fileReceivedExchange, fileReceivedRoutingKey, filePath);
            logger.info("O arquivo {} foi enviado para o rabbit", filePath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
