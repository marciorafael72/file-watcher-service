package br.com.marciorafael.filewatcher.factory.parser;


import br.com.marciorafael.filewatcher.dto.Model;
import br.com.marciorafael.filewatcher.dto.Sale;
import br.com.marciorafael.filewatcher.dto.SaleItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class SaleParser implements LineParser {

    private final String SALE_REGEX = "^(003)ç([0-9]{0,})ç(\\[[0-9\\-\\,\\.]+\\])ç([\\s\\S]+)";
    private final String SALE_ITEM_REGEX = "([-+]?[0-9]*\\.?[0-9]*)-([-+]?[0-9]*\\.?[0-9]*)-([-+]?[0-9]*\\.?[0-9]*)";

    @Override
    public Optional<Model> parse(String line) {
        Matcher matcher = getMatcher(line, SALE_REGEX);

        return Optional.of(Sale.of()
                .id(matcher.group(1))
                .saleId(Integer.valueOf(matcher.group(2)))
                .saleItems(getSaleItems(matcher.group(3)))
                .salesmanName(matcher.group(4)).build());
    }

    @Override
    public boolean isEligible(String line) {
        return getMatcher(line, SALE_REGEX).matches();
    }

    private List<SaleItem> getSaleItems(String list) {
        return Arrays.stream(list.split(","))
                .map(itemsLine -> getSaleItem(getMatcher(itemsLine, SALE_ITEM_REGEX)))
                .collect(Collectors.toList());
    }

    private SaleItem getSaleItem(Matcher matcher) {
        return SaleItem
                .of()
                .id(matcher.group(1))
                .quantity(Integer.valueOf(matcher.group(2)))
                .price(new BigDecimal(matcher.group(3)))
                .build();
    }

    private Matcher getMatcher(String line, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        matcher.find();
        return matcher;
    }
}
