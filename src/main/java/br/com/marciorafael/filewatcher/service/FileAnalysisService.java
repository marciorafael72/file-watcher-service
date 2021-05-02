package br.com.marciorafael.filewatcher.service;

import br.com.marciorafael.filewatcher.dto.Customer;
import br.com.marciorafael.filewatcher.dto.FileDataAnalysis;
import br.com.marciorafael.filewatcher.dto.Sale;
import br.com.marciorafael.filewatcher.dto.Salesman;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class FileAnalysisService {

    public FileDataAnalysis analyseMetricsFileContent(List<Customer> customerList, List<Sale> saleList, List<Salesman> salesmanList) {
        return FileDataAnalysis
                .of()
                .customersAmounts(customerList.size())
                .salesmanAmounts(salesmanList.size())
                .mostExpansiveSaleId(getMostExpansiveSaleIdFromSaleList(saleList))
                .worstSalesman(getWorstSalesmanNameFromSaleList(saleList))
                .build();
    }

    private Integer getMostExpansiveSaleIdFromSaleList(List<Sale> saleList) {
        if (saleList.isEmpty()) return 0;
        return saleList.stream()
                .max(Comparator.comparing(Sale::getTotalSalePrice))
                .orElseThrow(() -> new RuntimeException("Erro nos dados do arquivo de venda"))
                .getSaleId();
    }

    private String getWorstSalesmanNameFromSaleList(List<Sale> saleList) {
        if (saleList.isEmpty()) return "";

        return saleList.stream()
                .min(Comparator.comparing(Sale::getTotalSalePrice))
                .orElseThrow(() -> new RuntimeException("Erro nos dados do arquivo de venda"))
                .getSalesmanName();
    }
}
