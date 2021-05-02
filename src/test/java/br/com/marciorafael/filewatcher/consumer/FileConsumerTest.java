package br.com.marciorafael.filewatcher.consumer;

import br.com.marciorafael.filewatcher.service.FileProcessService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;

import java.io.IOException;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

public class FileConsumerTest {

    private final FileConsumer fileConsumer;
    private final FileProcessService fileProcessService;

    public FileConsumerTest() {
        this.fileProcessService = mock(FileProcessService.class);
        this.fileConsumer = new FileConsumer(fileProcessService);
    }

    @Test
    public void should_Receive_FilePath_And_Call_Service_To_Process_File() throws IOException {
        String file = "testeFile";

        doNothing().when(fileProcessService).process(file);
        Message message = MessageBuilder.withBody(file.getBytes()).build();

        fileConsumer.receive(message, null);

        then(fileProcessService).should().process(file);
    }
}
