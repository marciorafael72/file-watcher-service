package br.com.marciorafael.filewatcher.service;

import br.com.marciorafael.filewatcher.dto.Model;
import br.com.marciorafael.filewatcher.factory.ParseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParseService {

    private final ParseFactory parseFactory;
    private final Logger logger = LoggerFactory.getLogger(ParseService.class);

    public ParseService(ParseFactory parseFactory) {
        this.parseFactory = parseFactory;
    }

    List<Model> parseModels(List<String> fileLines) {
        return fileLines.stream()
                .filter(line -> !line.isBlank())
                .map(this::getModelFactory)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<Model> getModelFactory(String line) {
        try {
            return parseFactory.getParser()
                    .filter(parseFactory -> parseFactory.isEligible(line))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Nenhum parser encontrado para a linha do arquivo"))
                    .parse(line);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Optional.empty();
        }
    }
}
