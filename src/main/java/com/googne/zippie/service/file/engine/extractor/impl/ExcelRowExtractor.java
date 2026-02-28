package com.googne.zippie.service.file.engine.extractor.impl;

import com.googne.zippie.service.file.dto.parser.ParsedColumn;
import com.googne.zippie.service.file.engine.extractor.RowExtractor;
import com.googne.zippie.service.file.engine.model.ParsedHeader;
import com.googne.zippie.service.file.engine.resolver.impl.ExcelCellValueResolver;
import com.googne.zippie.service.file.model.HeaderRule;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ExcelRowExtractor implements RowExtractor<Row, ParsedHeader> {
    private final ExcelCellValueResolver cellResolver;

    @Override
    public ParsedColumn extract(
            Row row,
            ParsedHeader header,
            List<HeaderRule> rules) {
        ParsedColumn columns = new ParsedColumn();

        for (HeaderRule rule : rules) {
            String columnName = rule.getColumnName();
            Integer columnIndex = header.columnMap().get(columnName);
            String value =
                    cellResolver.resolve(row, columnIndex, rule);
            columns.put(columnName, value);

            if (cellResolver.previousEligible(rule))
                cellResolver.putPrevious(columnName, value);
        }

        return columns;
    }
}
