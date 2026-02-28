package com.googne.zippie.service.file.engine.propagation.impl;

import com.googne.zippie.service.file.dto.parser.ParsedRow;
import com.googne.zippie.service.file.engine.propagation.FooterPropagationStrategy;

import java.util.List;

public class NoFooterPropagationStrategy implements FooterPropagationStrategy {
//    @Override
//    public boolean isFound(ColumnValues values) {
//        return true;
//    }

    @Override
    public void handleRow(ParsedRow currentRow,
//                          Map<String, HeaderRule> footerRules,
                          List<ParsedRow> buffer,
//                          ColumnValues detectedFooterValues,
                          List<ParsedRow> results) {
        results.add(currentRow);
    }

    @Override
    public void flush(List<ParsedRow> buffer,
//                      ColumnValues detectedFooterValues,
                      List<ParsedRow> results) {

    }
}
