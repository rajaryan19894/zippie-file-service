package com.googne.zippie.service.file.engine.propagation.impl;

import com.googne.zippie.common.lib.util.CommonUtil;
import com.googne.zippie.common.lib.validator.CommonValidator;
import com.googne.zippie.service.file.dto.parser.ParsedColumn;
import com.googne.zippie.service.file.dto.parser.ParsedRow;
import com.googne.zippie.service.file.engine.propagation.FooterPropagationStrategy;
import com.googne.zippie.service.file.model.HeaderRule;

import java.util.List;
import java.util.Map;

public class DefaultFooterPropagationStrategy implements FooterPropagationStrategy {
    private final CommonValidator validator;

    private final Map<String, HeaderRule> footerHeaderRules;
    private final ParsedColumn detectedFooterValues = new ParsedColumn();

    public DefaultFooterPropagationStrategy(CommonValidator validator, Map<String, HeaderRule> footerHeaderRules) {
        this.validator = validator;
        validator.requireNonNull(footerHeaderRules, "FooterHeaderRules");

        this.footerHeaderRules = footerHeaderRules;
    }

    @Override
    public void handleRow(ParsedRow currentRow,
                          List<ParsedRow> buffer,
                          List<ParsedRow> results) {
        validator.requireNonNull(currentRow, "CurrentRow");

        ParsedColumn parsedColumn = currentRow.getParsedColumn();

        boolean footerFound = detectFooter(parsedColumn);

        if (footerFound) {
            propagateFooterValues(buffer);
            results.addAll(buffer);
            buffer.clear();

            // include footer row also
            parsedColumn.putAll(detectedFooterValues);
            results.add(currentRow);
        } else {
            buffer.add(currentRow);
        }
    }

    @Override
    public void flush(List<ParsedRow> buffer,
                      List<ParsedRow> results) {
        if (!CommonUtil.isValid(buffer)) return;

        if (!detectedFooterValues.isEmpty()) {
            propagateFooterValues(buffer);
        }

        results.addAll(buffer);
        buffer.clear();
    }

    private boolean detectFooter(ParsedColumn values) {
        if (!CommonUtil.isValid(values)) return false;

        boolean found = false;
        for (String column : footerHeaderRules.keySet()) {
            String value = values.get(column);

            if (CommonUtil.isValid(value)) {
                detectedFooterValues.put(column, value);
                found = true;
            }
        }
        return found;
    }


    private void propagateFooterValues(List<ParsedRow> buffer) {
        if (!CommonUtil.isValid(buffer)) return;

        for (ParsedRow bufferedRow : buffer) {
            bufferedRow.getParsedColumn().putAll(detectedFooterValues);
        }
    }

    public void reset() {
        detectedFooterValues.clear();
    }
}
