package br.com.marciorafael.filewatcher.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class Salesman implements Model {

    private String id;
    private String taxId;
    private String name;
    private BigDecimal salary;

    public Salesman() {
    }

    public List<Salesman> getSalesmans(List<Model> models) {
        return models.stream().filter(model -> model.getClass().equals(Salesman.class)).map(salesman -> (Salesman) salesman).collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public static Builder of() {
        return new Builder();
    }

    public static final class Builder {
        private Salesman salesman;

        private Builder() {
            salesman = new Salesman();
        }

        public Builder id(String id) {
            salesman.setId(id);
            return this;
        }

        public Builder taxId(String taxId) {
            salesman.setTaxId(taxId);
            return this;
        }

        public Builder name(String name) {
            salesman.setName(name);
            return this;
        }

        public Builder salary(BigDecimal salary) {
            salesman.setSalary(salary);
            return this;
        }

        public Salesman build() {
            return salesman;
        }
    }
}
