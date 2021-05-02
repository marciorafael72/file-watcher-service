package br.com.marciorafael.filewatcher.watcher;

import br.com.marciorafael.filewatcher.producer.FileProducer;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.nio.file.Path;

@Profile("!WORKER")
@Component
public class FileWatcher {

    private final FileProducer fileProducer;
    private final String pathToListen;
    private final String datFileExtension;

    private final Logger logger = LoggerFactory.getLogger(FileWatcher.class);

    public FileWatcher(FileProducer fileProducer, @Value("${app.files.directory.in}") String pathToListen,
                       @Value("${app.files.extension.start}") String datFileExtension) {
        this.fileProducer = fileProducer;
        this.pathToListen = pathToListen;
        this.datFileExtension = datFileExtension;
    }

    @PostConstruct
    public void watch() throws Exception {
        String directoryToWatch = System.getProperty("user.home").concat(pathToListen);

        logger.info("Iniciando WATCH no diretorio: {}", directoryToWatch);

        FileAlterationObserver observer = new FileAlterationObserver(directoryToWatch);
        FileAlterationMonitor monitor = new FileAlterationMonitor(5);

        FileAlterationListener listener = new FileAlterationListenerAdaptor() {
            @Override
            public void onFileCreate(File file) {
                sendFilePathToRabbitAccordingExtension(file.toPath(), datFileExtension);
            }

            @Override
            public void onFileChange(File file) {
                sendFilePathToRabbitAccordingExtension(file.toPath(), datFileExtension);
            }
        };

        observer.addListener(listener);
        monitor.addObserver(observer);
        monitor.start();
    }

    private void sendFilePathToRabbitAccordingExtension(Path path, String fileExtension) {
        String filePath = path.toString();
        if (filePath.endsWith(fileExtension)) {
            fileProducer.send(filePath);
        }
    }
}





