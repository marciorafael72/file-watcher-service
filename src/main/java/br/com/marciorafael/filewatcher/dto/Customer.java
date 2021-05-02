package br.com.marciorafael.filewatcher.dto;

import java.util.List;
import java.util.stream.Collectors;

public class Customer implements Model {

    private String id;
    private String cnpj;
    private String name;
    private String businessArea;

    public Customer() {
    }

    public List<Customer> getCustomers(List<Model> models) {
        return models.stream().filter(model -> model.getClass().equals(Customer.class)).map(customer -> (Customer) customer).collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

    public static Builder of() {
        return new Builder();
    }

    public static final class Builder {
        private Customer customer;

        private Builder() {
            customer = new Customer();
        }

        public Builder id(String id) {
            customer.setId(id);
            return this;
        }

        public Builder cnpj(String cnpj) {
            customer.setCnpj(cnpj);
            return this;
        }

        public Builder name(String name) {
            customer.setName(name);
            return this;
        }

        public Builder businessArea(String businessArea) {
            customer.setBusinessArea(businessArea);
            return this;
        }

        public Customer build() {
            return customer;
        }
    }
}
