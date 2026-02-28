package com.googne.zippie.service.file.dto.parser;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParsedRow {
    String sheetName;
    Integer rowIdx;
    ParsedColumn parsedColumn;
}
