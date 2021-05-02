package br.com.marciorafael.filewatcher.factory.parser;

import br.com.marciorafael.filewatcher.dto.Salesman;
import br.com.marciorafael.filewatcher.stubs.SalesmanStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class SalesmanParserTest {

    private final SalesmanParser salesmanParser;

    private final String VALID_SALESMAN_LINE = "001ç1234567891234çPedroç50000";
    private final String INVALID_SALESMAN_LINE = "12FDNKJFKEFWEfewfjwn";

    public SalesmanParserTest() {
        this.salesmanParser = new SalesmanParser();
    }

    @Nested
    class parseSaleLine {

        @Test
        public void should_Parse_SalesmanLine_And_Extract_Expected_Values() {
            Salesman expectedSalesman = SalesmanStub.create();

            Salesman parsedSalesman = salesmanParser.parse(VALID_SALESMAN_LINE)
                    .map(customerModel -> (Salesman) customerModel).get();


            Assertions.assertAll(
                    () -> Assertions.assertEquals(expectedSalesman.getName(), parsedSalesman.getName()),
                    () -> Assertions.assertEquals(expectedSalesman.getId(), parsedSalesman.getId()),
                    () -> Assertions.assertEquals(expectedSalesman.getTaxId(), parsedSalesman.getTaxId()),
                    () -> Assertions.assertEquals(expectedSalesman.getSalary(), parsedSalesman.getSalary())
            );
        }

        @Test
        public void should_Throw_IllegalStateException_When_Receive_Invalid_Salesman_Line() {
            Executable executableParser = () -> salesmanParser.parse(INVALID_SALESMAN_LINE);

            Assertions.assertThrows(IllegalStateException.class, executableParser);
        }
    }

    @Nested
    class eligibleParser {

        @Test
        public void should_Return_True_When_Parse_Is_Eligible_To_Parse_Line() {
            boolean parserEligible = salesmanParser.isEligible(VALID_SALESMAN_LINE);

            Assertions.assertTrue(parserEligible);
        }

        @Test
        public void should_Return_False_When_Parse_Is_Ineligible_To_Parse_Line() {
            boolean parserEligible = salesmanParser.isEligible(INVALID_SALESMAN_LINE);

            Assertions.assertFalse(parserEligible);
        }
    }
}
