package com.googne.zippie.service.file.engine.parser;

import com.googne.zippie.service.file.model.enums.FileFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FileParserStrategyResolver {

    private final List<FileParserStrategy> parsers;

    public FileParserStrategy resolve(FileFormat fileFormat) {
        return parsers.stream()
                .filter(p -> p.supports(fileFormat))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Unsupported file type"));
    }
}