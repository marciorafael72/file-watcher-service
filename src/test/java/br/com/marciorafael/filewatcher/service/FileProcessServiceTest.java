package br.com.marciorafael.filewatcher.service;

import br.com.marciorafael.filewatcher.dto.FileDataAnalysis;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

public class FileProcessServiceTest {

    private final FileProcessService fileProcessService;
    private final ParseService parseService;
    private final FileAnalysisService fileAnalysisService;

    public FileProcessServiceTest() {
        this.parseService = mock(ParseService.class);
        this.fileAnalysisService = mock(FileAnalysisService.class);
        this.fileProcessService = new FileProcessService(parseService, fileAnalysisService,
                "/out", ".out.dat", ".dat");
    }

    @Nested
    class ProcessFile {

        @Test
        public void should_ProcessFile() throws IOException {
            File tempFile = File.createTempFile("teste", ".dat");
            given(parseService.parseModels(anyList())).willReturn(Collections.emptyList());
            given(fileAnalysisService.analyseMetricsFileContent(anyList(), anyList(), anyList())).willReturn(new FileDataAnalysis());

            fileProcessService.process(tempFile.getAbsolutePath());

            then(parseService).should().parseModels(anyList());
            then(fileAnalysisService).should().analyseMetricsFileContent(anyList(), anyList(), anyList());
        }
    }
}
