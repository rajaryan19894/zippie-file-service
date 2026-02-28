package com.googne.zippie.service.file.dto.parser;

import java.util.List;

public record ParserResult(
        List<ParsedRow> parsedRowList,
        int totalRows,
        int successRows
) {
}
