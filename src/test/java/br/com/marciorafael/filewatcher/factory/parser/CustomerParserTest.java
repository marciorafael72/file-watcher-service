package br.com.marciorafael.filewatcher.factory.parser;

import br.com.marciorafael.filewatcher.dto.Customer;
import br.com.marciorafael.filewatcher.stubs.CustomerStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class CustomerParserTest {

    private final CustomerParser customerParser;

    private final String VALID_CUSTOMER_LINE = "002ç2345675434544345çtesteçbusinessArea";
    private final String INVALID_CUSTOMER_LINE = "12FDNKJFKEFWEfewfjwn";

    public CustomerParserTest() {
        this.customerParser = new CustomerParser();
    }

    @Nested
    class parseCustomerLine {

        @Test
        public void should_Parse_CustomerLine_And_Extract_Expected_Values() {
            Customer expectedCustomer = CustomerStub.create();

            Customer parsedCustomer = customerParser.parse(VALID_CUSTOMER_LINE)
                    .map(customerModel -> (Customer) customerModel).get();


            Assertions.assertAll(
                    () -> Assertions.assertEquals(expectedCustomer.getCnpj(), parsedCustomer.getCnpj()),
                    () -> Assertions.assertEquals(expectedCustomer.getId(), parsedCustomer.getId()),
                    () -> Assertions.assertEquals(expectedCustomer.getBusinessArea(), parsedCustomer.getBusinessArea()),
                    () -> Assertions.assertEquals(expectedCustomer.getName(), parsedCustomer.getName())
            );
        }

        @Test
        public void should_Throw_IllegalStateException_When_Receive_Invalid_Customer_Line() {
            Executable executableParser = () -> customerParser.parse(INVALID_CUSTOMER_LINE);

            Assertions.assertThrows(IllegalStateException.class, executableParser);
        }
    }

    @Nested
    class eligibleParser {

        @Test
        public void should_Return_True_When_Parse_Is_Eligible_To_Parse_Line() {
            boolean parserEligible = customerParser.isEligible(VALID_CUSTOMER_LINE);

            Assertions.assertTrue(parserEligible);
        }

        @Test
        public void should_Return_False_When_Parse_Is_Ineligible_To_Parse_Line() {
            boolean parserEligible = customerParser.isEligible(INVALID_CUSTOMER_LINE);

            Assertions.assertFalse(parserEligible);
        }
    }
}
