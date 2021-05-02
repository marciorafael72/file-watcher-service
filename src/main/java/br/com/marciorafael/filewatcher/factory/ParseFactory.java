package br.com.marciorafael.filewatcher.factory;

import br.com.marciorafael.filewatcher.factory.parser.CustomerParser;
import br.com.marciorafael.filewatcher.factory.parser.LineParser;
import br.com.marciorafael.filewatcher.factory.parser.SaleParser;
import br.com.marciorafael.filewatcher.factory.parser.SalesmanParser;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class ParseFactory {

    private final CustomerParser customerParser;
    private final SalesmanParser salesmanParser;
    private final SaleParser saleParser;

    public ParseFactory(CustomerParser customerParser, SalesmanParser salesmanParser, SaleParser saleParser) {
        this.customerParser = customerParser;
        this.salesmanParser = salesmanParser;
        this.saleParser = saleParser;
    }

    public Stream<LineParser> getParser() {
        return Stream.of(customerParser, salesmanParser, saleParser);
    }
}
