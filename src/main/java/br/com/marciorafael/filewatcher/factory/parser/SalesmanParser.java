package br.com.marciorafael.filewatcher.factory.parser;

import br.com.marciorafael.filewatcher.dto.Model;
import br.com.marciorafael.filewatcher.dto.Salesman;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class SalesmanParser implements LineParser {

    private final String SALESMAN_REGEX = "^(001)ç([0-9]{13})ç([\\s\\S]+)ç([0-9]*\\.?[0-9]+)";

    @Override
    public Optional<Model> parse(String line) {
        Matcher matcher = getMatcher(line);
        return Optional.of(Salesman
                .of()
                .id(matcher.group(1))
                .taxId(matcher.group(2))
                .name(matcher.group(3))
                .salary(new BigDecimal(matcher.group(4)))
                .build());
    }

    @Override
    public boolean isEligible(String line) {
        return getMatcher(line).matches();
    }

    private Matcher getMatcher(String line) {
        Pattern pattern = Pattern.compile(SALESMAN_REGEX);
        Matcher matcher = pattern.matcher(line);
        matcher.find();
        return matcher;
    }

}
