package br.com.marciorafael.filewatcher.service;

import br.com.marciorafael.filewatcher.dto.*;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class FileProcessService {

    private final ParseService parseService;
    private final FileAnalysisService fileAnalysisService;
    private final String pathOut;
    private final String extensionFileOut;
    private final String extensionFileIn;

    private final Logger logger = LoggerFactory.getLogger(FileProcessService.class);

    public FileProcessService(ParseService parseService, FileAnalysisService fileAnalysisService,
                              @Value("${app.files.directory.out}") String pathOut, @Value("${app.files.extension.done}") String extensionFileOut,
                              @Value("${app.files.extension.start}") String extensionFileIn) {
        this.parseService = parseService;
        this.fileAnalysisService = fileAnalysisService;
        this.pathOut = pathOut;
        this.extensionFileOut = extensionFileOut;
        this.extensionFileIn = extensionFileIn;
    }

    public void process(String file) throws IOException {
        Path filePath = Path.of(file);
        List<String> fileLines = FileUtils.readLines(filePath.toFile(), "UTF-8");

        List<Model> modelList = parseService.parseModels(fileLines);

        List<Customer> customerList = new Customer().getCustomers(modelList);
        List<Salesman> salesmanList = new Salesman().getSalesmans(modelList);
        List<Sale> saleList = new Sale().getSales(modelList);

        FileDataAnalysis fileDataAnalysis = fileAnalysisService.analyseMetricsFileContent(customerList, saleList, salesmanList);

        writeDataAnalysisFile(fileDataAnalysis, filePath.getFileName().toString());
    }

    private void writeDataAnalysisFile(FileDataAnalysis fileDataAnalysis, String fileName) {
        fileName = fileName.replace(extensionFileIn, "");
        Path outPath = Paths.get(System.getProperty("user.home").concat(pathOut), fileName.concat(extensionFileOut));
        try {
            Files.write(outPath, fileDataAnalysis.getDataAnlysis());
            logger.info("Arquivo: {} gerado com sucesso", outPath);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}