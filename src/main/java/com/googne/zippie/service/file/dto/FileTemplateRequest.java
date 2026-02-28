package com.googne.zippie.service.file.dto;

import com.googne.zippie.service.file.model.enums.EmptyRowEvaluationMode;
import com.googne.zippie.service.file.model.enums.FileFormat;
import com.googne.zippie.service.file.model.enums.SourceType;
import lombok.Builder;
import org.apache.commons.collections.ListUtils;

import java.util.List;
import java.util.stream.Stream;

@Builder
public record FileTemplateRequest(
        SourceType sourceType,
        String name,
        FileFormat format,
        Integer emptyCellThreshold,
        boolean stopOnThresholdCross,
        Double emptyCellPercentageThreshold,
        EmptyRowEvaluationMode emptyRowEvaluationMode,
        List<HeaderRuleRequest> headerRules,
        DenominationRuleRequest denominationRules
) {
    public List<HeaderRuleRequest> getAllHeaderRules() {
        headerRules.addAll(denominationRules.toHeaderRules());
        return  headerRules;
    }
}
