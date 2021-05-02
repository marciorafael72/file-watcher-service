package br.com.marciorafael.filewatcher.factory.parser;

import br.com.marciorafael.filewatcher.dto.Model;

import java.util.Optional;

public interface LineParser {

    Optional<Model> parse(String line);

    boolean isEligible(String line);
}
