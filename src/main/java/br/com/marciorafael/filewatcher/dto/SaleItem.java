package br.com.marciorafael.filewatcher.dto;

import java.math.BigDecimal;

public class SaleItem {

    private String id;
    private Integer quantity;
    private BigDecimal price;

    public SaleItem() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotal() {
        return this.price.multiply(new BigDecimal(quantity));
    }

    public static Builder of() {
        return new Builder();
    }

    public static final class Builder {
        private SaleItem saleItem;

        private Builder() {
            saleItem = new SaleItem();
        }

        public Builder id(String id) {
            saleItem.setId(id);
            return this;
        }

        public Builder quantity(Integer quantity) {
            saleItem.setQuantity(quantity);
            return this;
        }

        public Builder price(BigDecimal price) {
            saleItem.setPrice(price);
            return this;
        }

        public SaleItem build() {
            return saleItem;
        }
    }
}
