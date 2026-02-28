package com.googne.zippie.service.file.engine.resolver.impl;

import com.googne.zippie.service.file.engine.resolver.CellValueResolver;
import com.googne.zippie.service.file.model.HeaderRule;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

import static com.googne.zippie.service.file.util.FileUtil.isCellEmpty;
import static com.googne.zippie.service.file.util.FileUtil.readCellValue;


@Component
public class ExcelCellValueResolver implements CellValueResolver<Row> {

    @Override
    public String resolve(Row row, Integer columnIndex, HeaderRule rule) {
        Cell cell = columnIndex != null ? row.getCell(columnIndex) : null;

        if (isCellEmpty(cell)) {
            return handleEmpty(rule);
        }

        return readCellValue(cell);
    }

    private String handleEmpty(HeaderRule rule) {
        return switch (rule.getEmptyCellStrategy()) {
            case USE_PREVIOUS -> getPrevious(rule.getColumnName(), String.class);
            case THROW_EXCEPTION -> throw new IllegalStateException("Required column missing: "
                    + rule.getColumnName());
            default -> null;
        };
    }
}
