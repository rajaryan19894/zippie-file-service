package com.googne.zippie.service.file.dto;

import com.googne.zippie.service.file.model.enums.EmptyRowEvaluationMode;

import java.util.List;
import java.util.stream.Stream;

public record UpdateFileTemplateRequest(
        String id,
        String name,
        Integer emptyCellThreshold,
        boolean stopOnThresholdCross,
        Double emptyCellPercentageThreshold,
        EmptyRowEvaluationMode emptyRowEvaluationMode,
        List<HeaderRuleRequest> headerRules,
        DenominationRuleRequest denominationRules
) {
    public List<HeaderRuleRequest> getAllHeaderRules() {
        List<HeaderRuleRequest> normal =
                headerRules == null ? List.of() : headerRules;

        List<HeaderRuleRequest> denom =
                denominationRules == null ? List.of()
                        : denominationRules.toHeaderRules();

        return Stream.concat(normal.stream(), denom.stream())
                .toList();
    }
}
