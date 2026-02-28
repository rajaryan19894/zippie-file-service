package com.googne.zippie.service.file.engine.evaluator;

import com.googne.zippie.service.file.dto.parser.ParsedColumn;
import com.googne.zippie.service.file.model.FileTemplate;

public interface EndOfDataEvaluator {

    boolean supports(FileTemplate template);

    Integer getEmptyCount(ParsedColumn parsedColumn, FileTemplate template);

    default boolean isEndOfData(ParsedColumn parsedColumn, FileTemplate template) {
        return getEmptyCount(parsedColumn, template) >= template.getEmptyCellThreshold();
    }

    default boolean isEmpty(Object value) {
        if (value == null) return true;

        if (value instanceof String s) {
            return s.isBlank();
        }

        return false;
    }
}
