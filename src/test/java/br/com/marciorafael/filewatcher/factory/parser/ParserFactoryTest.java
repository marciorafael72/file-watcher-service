package br.com.marciorafael.filewatcher.factory.parser;

import br.com.marciorafael.filewatcher.factory.ParseFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

public class ParserFactoryTest {

    private final CustomerParser customerParser;
    private final SaleParser saleParser;
    private final SalesmanParser salesmanParser;
    private final ParseFactory parseFactory;

    public ParserFactoryTest() {
        this.customerParser = new CustomerParser();
        this.saleParser = new SaleParser();
        this.salesmanParser = new SalesmanParser();
        this.parseFactory = new ParseFactory(customerParser, salesmanParser, saleParser);
    }

    @Test
    public void should_Return_Stream_Of_Parsers(){

        Stream<LineParser> parseStream = parseFactory.getParser();

        Assertions.assertAll(
                () -> Assertions.assertTrue(parseStream.anyMatch(lineParser -> lineParser.getClass().equals(CustomerParser.class)))
        );
    }
}
