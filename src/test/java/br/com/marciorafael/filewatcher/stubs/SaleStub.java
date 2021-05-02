package br.com.marciorafael.filewatcher.stubs;

import br.com.marciorafael.filewatcher.dto.Sale;

import java.util.Arrays;
import java.util.List;

public class SaleStub {

    public static List<Sale> many() {
        return Arrays.asList(create(), createLowerSale());
    }

    public static Sale create() {
        return Sale
                .of()
                .id("003")
                .saleId(10)
                .saleItems(SaleItemStub.many())
                .salesmanName("Pedro")
                .build();
    }

    public static Sale createLowerSale() {
        return Sale
                .of()
                .id("003")
                .saleId(11)
                .saleItems(SaleItemStub.many(1, 10))
                .salesmanName("Ernesto")
                .build();
    }
}
