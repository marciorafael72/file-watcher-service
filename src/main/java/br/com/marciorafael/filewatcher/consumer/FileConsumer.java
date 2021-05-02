package br.com.marciorafael.filewatcher.consumer;

import br.com.marciorafael.filewatcher.service.FileProcessService;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FileConsumer {

    private final Logger logger = LoggerFactory.getLogger(FileConsumer.class);
    private final FileProcessService fileProcessService;

    public FileConsumer(FileProcessService fileProcessService) {
        this.fileProcessService = fileProcessService;
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${amqp.file-received.queue}", durable = "true"), exchange = @Exchange(value = "${amqp.file-received.exchange}",
            type = "topic", ignoreDeclarationExceptions = "true"), key = "#"), concurrency = "${amqp.file-received.concurrency}")
    public void receive(Message message, Channel channel) throws IOException {
        String filePath = new String(message.getBody());
        logger.info("Mensagem recebida referente ao arquivo: {}", filePath);
        fileProcessService.process(filePath);
    }

}
