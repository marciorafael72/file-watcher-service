package br.com.marciorafael.filewatcher.factory.parser;

import br.com.marciorafael.filewatcher.dto.Customer;
import br.com.marciorafael.filewatcher.dto.Model;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CustomerParser implements LineParser {

    private final String CUSTOMER_REGEX = "^(002)ç([0-9]{16})ç([\\s\\S]+)ç([\\s\\S]+)";

    @Override
    public Optional<Model> parse(String line) {
        Matcher matcher = getMatcher(line);
        return Optional.of(Customer.of()
                .id(matcher.group(1))
                .cnpj(matcher.group(2))
                .name(matcher.group(3))
                .businessArea(matcher.group(4))
                .build());
    }

    @Override
    public boolean isEligible(String line) {
        return getMatcher(line).matches();
    }

    private Matcher getMatcher(String line) {
        Pattern pattern = Pattern.compile(CUSTOMER_REGEX);
        Matcher matcher = pattern.matcher(line);
        matcher.find();
        return matcher;
    }
}
