package com.googne.zippie.service.file.engine.evaluator.impl;

import com.googne.zippie.service.file.dto.parser.ParsedColumn;
import com.googne.zippie.service.file.engine.evaluator.EndOfDataEvaluator;
import com.googne.zippie.service.file.model.FileTemplate;
import com.googne.zippie.service.file.model.HeaderRule;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.googne.zippie.service.file.model.enums.EmptyRowEvaluationMode.PERCENTAGE;

@Component
public class PercentageEmptyEvaluator implements EndOfDataEvaluator {
    @Override
    public boolean supports(FileTemplate template) {
        return template.getEmptyRowEvaluationMode() == PERCENTAGE;
    }

    @Override
    public Integer getEmptyCount(ParsedColumn parsedColumn, FileTemplate template) {
        List<HeaderRule> rules = template.getHeaderRules();

        return Math.toIntExact(rules.stream()
                .filter(rule -> isEmpty(parsedColumn.get(rule.getColumnName())))
                .count());
    }

    @Override
    public boolean isEndOfData(ParsedColumn parsedColumn, FileTemplate template) {
        List<HeaderRule> rules = template.getHeaderRules();

        long total = rules.size();
        long empty = getEmptyCount(parsedColumn, template);

        double ratio = (double) empty / total;
        return ratio >= template.getEmptyCellPercentageThreshold();
    }
}
