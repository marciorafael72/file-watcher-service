package br.com.marciorafael.filewatcher.stubs;

import br.com.marciorafael.filewatcher.dto.Salesman;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class SalesmanStub {

    public static List<Salesman> many(){
        return Arrays.asList(create(), create());
    }

    public static Salesman create(){
        return Salesman
                .of()
                .id("001")
                .taxId("1234567891234")
                .name("Pedro")
                .salary(BigDecimal.valueOf(50000))
                .build();
    }
}
