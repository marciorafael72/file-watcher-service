package br.com.marciorafael.filewatcher.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class Sale implements Model {

    private String id;
    private Integer saleId;
    private List<SaleItem> saleItems;
    private String salesmanName;

    public Sale() {
    }

    public List<Sale> getSales(List<Model> models) {
        return models.stream().filter(model -> model.getClass().equals(Sale.class)).map(sale -> (Sale) sale).collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public List<SaleItem> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(List<SaleItem> saleItems) {
        this.saleItems = saleItems;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public BigDecimal getTotalSalePrice() {
        return saleItems.stream().map(SaleItem::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static Builder of() {
        return new Builder();
    }

    public static final class Builder {
        private Sale sale;

        private Builder() {
            sale = new Sale();
        }

        public Builder id(String id) {
            sale.setId(id);
            return this;
        }

        public Builder saleId(Integer saleId) {
            sale.setSaleId(saleId);
            return this;
        }

        public Builder saleItems(List<SaleItem> saleItems) {
            sale.setSaleItems(saleItems);
            return this;
        }

        public Builder salesmanName(String salesmanName) {
            sale.setSalesmanName(salesmanName);
            return this;
        }

        public Sale build() {
            return sale;
        }
    }
}
