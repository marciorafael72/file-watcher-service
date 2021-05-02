package br.com.marciorafael.filewatcher.service;

import br.com.marciorafael.filewatcher.dto.Customer;
import br.com.marciorafael.filewatcher.dto.FileDataAnalysis;
import br.com.marciorafael.filewatcher.dto.Sale;
import br.com.marciorafael.filewatcher.dto.Salesman;
import br.com.marciorafael.filewatcher.stubs.CustomerStub;
import br.com.marciorafael.filewatcher.stubs.SaleStub;
import br.com.marciorafael.filewatcher.stubs.SalesmanStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

public class FileAnalysisServiceTest {

    private final FileAnalysisService fileAnalysisService;

    public FileAnalysisServiceTest() {
        this.fileAnalysisService = new FileAnalysisService();
    }

    @Nested
    class analyseFileData {

        @Test
        public void should_Return_FileAnalyse_From_Extracted_File_Content() {
            List<Customer> customerList = CustomerStub.many();
            List<Sale> saleList = SaleStub.many();
            List<Salesman> salesmanList = SalesmanStub.many();

            FileDataAnalysis fileDataAnalysis = fileAnalysisService.analyseMetricsFileContent(customerList, saleList, salesmanList);

            Assertions.assertAll(
                    () -> Assertions.assertEquals(Integer.valueOf(2), fileDataAnalysis.getCustomersAmounts()),
                    () -> Assertions.assertEquals(Integer.valueOf(2), fileDataAnalysis.getSalesmanAmounts()),
                    () -> Assertions.assertEquals(Integer.valueOf(10), fileDataAnalysis.getMostExpansiveSaleId()),
                    () -> Assertions.assertEquals("Ernesto", fileDataAnalysis.getWorstSalesman())
            );
        }

        @Test
        public void should_Return_FileAnalyse_With_0_Values_When_File_Content_Is_Empty() {
            List<Customer> customerList = Collections.emptyList();
            List<Sale> saleList = Collections.emptyList();
            List<Salesman> salesmanList = Collections.emptyList();

            FileDataAnalysis fileDataAnalysis = fileAnalysisService.analyseMetricsFileContent(customerList, saleList, salesmanList);

            Assertions.assertAll(
                    () -> Assertions.assertEquals(Integer.valueOf(0), fileDataAnalysis.getCustomersAmounts()),
                    () -> Assertions.assertEquals(Integer.valueOf(0), fileDataAnalysis.getSalesmanAmounts()),
                    () -> Assertions.assertEquals(Integer.valueOf(0), fileDataAnalysis.getMostExpansiveSaleId()),
                    () -> Assertions.assertEquals("", fileDataAnalysis.getWorstSalesman())
            );
        }
    }
}
