package com.googne.zippie.service.file.engine.detector.impl;

import com.googne.zippie.service.file.engine.detector.HeaderDetector;
import com.googne.zippie.service.file.engine.model.ParsedHeader;
import com.googne.zippie.service.file.exception.InvalidFileException;
import com.googne.zippie.service.file.model.HeaderRule;
import com.googne.zippie.service.file.util.FileUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.googne.zippie.service.file.util.FileUtil.readCellValue;

@Component
public class ExcelHeaderDetector implements HeaderDetector<Sheet, ParsedHeader> {
    @Override
    public ParsedHeader detect(Sheet sheet, List<HeaderRule> rules) {
        Map<String, String> aliasLookup = rules.stream()
                .flatMap(rule -> rule.getAliases().stream()
                        .map(alias -> Map.entry(
                                alias.toLowerCase(),
                                rule.getColumnName())))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue));

        Set<String> requiredColumns = rules.stream()
                .filter(HeaderRule::isRequired)
                .map(HeaderRule::getColumnName)
                .collect(Collectors.toSet());

        for (Row row : sheet) {
            Map<String, Integer> mapping = new HashMap<>();
            int foundRequired = 0;

            for (Cell cell : row) {
                if (FileUtil.isCellEmpty(cell)) continue;

                String normalized = readCellValue(cell).toLowerCase();
                String logicalName = aliasLookup.get(normalized);

                if (logicalName != null && !mapping.containsKey(logicalName)) {
                    mapping.put(logicalName, cell.getColumnIndex());

                    if (requiredColumns.contains(logicalName)) {
                        foundRequired++;
                        // Return early if all required columns found
                        if (foundRequired == requiredColumns.size()) {
                            return new ParsedHeader(row.getRowNum(), mapping);
                        }
                    }
                }
            }
        }

        throw new InvalidFileException(
                "Header row not found in sheet: " + sheet.getSheetName());
    }
}
