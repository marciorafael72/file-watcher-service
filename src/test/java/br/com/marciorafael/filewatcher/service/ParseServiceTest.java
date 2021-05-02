package br.com.marciorafael.filewatcher.service;

import br.com.marciorafael.filewatcher.dto.Model;
import br.com.marciorafael.filewatcher.factory.ParseFactory;
import br.com.marciorafael.filewatcher.stubs.ParseFactoryStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;

public class ParseServiceTest {

    private final ParseService parseService;
    private final ParseFactory parseFactory;

    private final String VALID_CUSTOMER_LINE = "002ç2345675434544345çtesteçbusinessArea";
    private final String VALID_SALE_LINE = "003ç10ç[1-10-100,2-30-2.50]çPedro";
    private final String VALID_SALESMAN_LINE = "001ç1234567891234çPedroç50000";
    private final String INVALID_LINE = "fwjefiwejoiwvowi";

    public ParseServiceTest() {
        this.parseFactory = Mockito.mock(ParseFactory.class);
        this.parseService = new ParseService(parseFactory);
    }

    @Nested
    class ParseModels {

        @Test
        public void should_Parse_Model_Sale_Line() {
            List<String> validLines = Collections.singletonList(VALID_SALE_LINE);
            given(parseFactory.getParser()).willReturn(ParseFactoryStub.getLineParsers());

            List<Model> modelList = parseService.parseModels(validLines);

            Assertions.assertEquals(1, modelList.size());
        }

        @Test
        public void should_Parse_Model_Salesman_Line() {
            List<String> validLines = Collections.singletonList(VALID_SALESMAN_LINE);
            given(parseFactory.getParser()).willReturn(ParseFactoryStub.getLineParsers());

            List<Model> modelList = parseService.parseModels(validLines);

            Assertions.assertEquals(1, modelList.size());
        }

        @Test
        public void should_Parse_Model_Customer_Line() {
            List<String> validLines = Collections.singletonList(VALID_CUSTOMER_LINE);
            given(parseFactory.getParser()).willReturn(ParseFactoryStub.getLineParsers());

            List<Model> modelList = parseService.parseModels(validLines);

            Assertions.assertEquals(1, modelList.size());
        }


        @Test
        public void should_Return_Empty_Model_When_Parse_Invalid_Line() {
            List<String> validLines = Collections.singletonList(INVALID_LINE);
            given(parseFactory.getParser()).willReturn(ParseFactoryStub.getLineParsers());

            List<Model> modelList = parseService.parseModels(validLines);

            Assertions.assertTrue( modelList.isEmpty());
        }
    }
}
