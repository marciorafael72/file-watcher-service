package br.com.marciorafael.filewatcher.factory.parser;

import br.com.marciorafael.filewatcher.dto.Sale;
import br.com.marciorafael.filewatcher.stubs.SaleStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class SaleParserTest {

    private final SaleParser saleParser;

    private final String VALID_SALE_LINE = "003ç10ç[1-10-100,2-30-2.50]çPedro";
    private final String INVALID_SALE_LINE = "12FDNKJFKEFWEfewfjwn";

    public SaleParserTest() {
        this.saleParser = new SaleParser();
    }

    @Nested
    class parseSaleLine {

        @Test
        public void should_Parse_SaleLine_And_Extract_Expected_Values() {
            Sale expectedSale = SaleStub.create();

            Sale parsedSale = saleParser.parse(VALID_SALE_LINE)
                    .map(customerModel -> (Sale) customerModel).get();


            Assertions.assertAll(
                    () -> Assertions.assertEquals(expectedSale.getSaleId(), parsedSale.getSaleId()),
                    () -> Assertions.assertEquals(expectedSale.getId(), parsedSale.getId()),
                    () -> Assertions.assertEquals(expectedSale.getSalesmanName(), parsedSale.getSalesmanName()),
                    () -> Assertions.assertEquals(expectedSale.getSaleItems().size(), parsedSale.getSaleItems().size())
            );
        }

        @Test
        public void should_Throw_IllegalStateException_When_Receive_Invalid_Sale_Line() {
            Executable executableParser = () -> saleParser.parse(INVALID_SALE_LINE);

            Assertions.assertThrows(IllegalStateException.class, executableParser);
        }
    }

    @Nested
    class eligibleParser {

        @Test
        public void should_Return_True_When_Parse_Is_Eligible_To_Parse_Line() {
            boolean parserEligible = saleParser.isEligible(VALID_SALE_LINE);

            Assertions.assertTrue(parserEligible);
        }

        @Test
        public void should_Return_False_When_Parse_Is_Ineligible_To_Parse_Line() {
            boolean parserEligible = saleParser.isEligible(INVALID_SALE_LINE);

            Assertions.assertFalse(parserEligible);
        }
    }
}
