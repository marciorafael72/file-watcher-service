package br.com.marciorafael.filewatcher.stubs;

import br.com.marciorafael.filewatcher.factory.parser.CustomerParser;
import br.com.marciorafael.filewatcher.factory.parser.LineParser;
import br.com.marciorafael.filewatcher.factory.parser.SaleParser;
import br.com.marciorafael.filewatcher.factory.parser.SalesmanParser;

import java.util.stream.Stream;

public class ParseFactoryStub {

    private static CustomerParser customerParser = new CustomerParser();
    private static SaleParser saleParser = new SaleParser();
    private static SalesmanParser salesmanParser = new SalesmanParser();

    public static Stream<LineParser> getLineParsers(){
        return Stream.of(customerParser, saleParser, salesmanParser);
    }
}
