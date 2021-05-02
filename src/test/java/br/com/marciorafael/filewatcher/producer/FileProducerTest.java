package br.com.marciorafael.filewatcher.producer;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

public class FileProducerTest {

    private final RabbitTemplate rabbitTemplate;
    private final FileProducer fileProducer;

    public FileProducerTest() {
        this.rabbitTemplate = mock(RabbitTemplate.class);
        this.fileProducer = new FileProducer(rabbitTemplate);
    }

    @Test
    public void should_Send_Message_To_Rabbit() {
        doNothing().when(rabbitTemplate).convertAndSend(any(), any(), eq("testFile"));

        fileProducer.send("testFile");

        then(rabbitTemplate).should().convertAndSend(any(), any(), eq("testFile"));
    }
}
