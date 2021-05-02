package br.com.marciorafael.filewatcher.dto;

public class FileDataAnalysis {

    private Integer customersAmounts;
    private Integer salesmanAmounts;
    private Integer mostExpansiveSaleId;
    private String worstSalesman;

    public Integer getCustomersAmounts() {
        return customersAmounts;
    }

    public void setCustomersAmounts(Integer customersAmounts) {
        this.customersAmounts = customersAmounts;
    }

    public Integer getSalesmanAmounts() {
        return salesmanAmounts;
    }

    public void setSalesmanAmounts(Integer salesmanAmounts) {
        this.salesmanAmounts = salesmanAmounts;
    }

    public Integer getMostExpansiveSaleId() {
        return mostExpansiveSaleId;
    }

    public void setMostExpansiveSaleId(Integer mostExpansiveSaleId) {
        this.mostExpansiveSaleId = mostExpansiveSaleId;
    }

    public String getWorstSalesman() {
        return worstSalesman;
    }

    public void setWorstSalesman(String worstSalesman) {
        this.worstSalesman = worstSalesman;
    }

    public byte[] getDataAnlysis() {
        return this.toString().getBytes();
    }

    @Override
    public String toString() {
        return "customersAmounts= " + customersAmounts +
                "\nsalesmanAmounts= " + salesmanAmounts +
                "\nmostExpansiveSaleId= " + mostExpansiveSaleId +
                "\nworstSalesman= " + worstSalesman;
    }
    
    public static Builder of() {
        return new Builder();
    }

    public static final class Builder {
        private FileDataAnalysis fileDataAnalysis;

        private Builder() {
            fileDataAnalysis = new FileDataAnalysis();
        }

        public Builder customersAmounts(Integer customersAmounts) {
            fileDataAnalysis.setCustomersAmounts(customersAmounts);
            return this;
        }

        public Builder salesmanAmounts(Integer salesmanAmounts) {
            fileDataAnalysis.setSalesmanAmounts(salesmanAmounts);
            return this;
        }

        public Builder mostExpansiveSaleId(Integer mostExpansiveSaleId) {
            fileDataAnalysis.setMostExpansiveSaleId(mostExpansiveSaleId);
            return this;
        }

        public Builder worstSalesman(String worstSalesman) {
            fileDataAnalysis.setWorstSalesman(worstSalesman);
            return this;
        }

        public FileDataAnalysis build() {
            return fileDataAnalysis;
        }
    }
}
