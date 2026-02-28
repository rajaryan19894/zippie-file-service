package com.googne.zippie.service.file.engine.propagation;

import com.googne.zippie.service.file.dto.parser.ParsedRow;

import java.util.List;

public interface FooterPropagationStrategy {

//    boolean isFound(ColumnValues values);

    void handleRow(
            ParsedRow currentRow,
//            Map<String, HeaderRule> footerRules,
            List<ParsedRow> buffer,
//            ColumnValues detectedFooterValues,
            List<ParsedRow> results
    );

    void flush(
            List<ParsedRow> buffer,
//            ColumnValues detectedFooterValues,
            List<ParsedRow> results
    );
}
