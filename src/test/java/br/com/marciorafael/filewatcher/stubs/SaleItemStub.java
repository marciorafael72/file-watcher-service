package br.com.marciorafael.filewatcher.stubs;

import br.com.marciorafael.filewatcher.dto.SaleItem;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class SaleItemStub {

    public static List<SaleItem> many(){
        return Arrays.asList(create("1", 10, 100), create("2", 30, 2.50));
    }

    public static List<SaleItem> many(int quantity, double price){
        return Arrays.asList(create("1", quantity, price), create("2", quantity, price));
    }

    public static SaleItem create(String id, int quantity, double price){
        return SaleItem
                .of()
                .id(id)
                .price(BigDecimal.valueOf(price))
                .quantity(quantity)
                .build();
    }
}
