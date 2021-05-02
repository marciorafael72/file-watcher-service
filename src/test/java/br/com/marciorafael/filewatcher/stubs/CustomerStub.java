package br.com.marciorafael.filewatcher.stubs;

import br.com.marciorafael.filewatcher.dto.Customer;

import java.util.Arrays;
import java.util.List;

public class CustomerStub {

    public static List<Customer> many() {
        return Arrays.asList(create(), create());
    }

    public static Customer create() {
        return Customer.of()
                .id("002")
                .businessArea("businessArea")
                .cnpj("2345675434544345")
                .name("teste")
                .build();
    }
}
