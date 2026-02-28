package com.googne.zippie.service.file.dto;

import com.googne.zippie.service.file.model.enums.FileFormat;
import com.googne.zippie.service.file.model.enums.SourceType;

import java.util.List;

public record FileTemplateResponse(
        String id,
        SourceType sourceType,
        String name,
        FileFormat format,
        Integer emptyCellThreshold,
        boolean stopOnThresholdCross,
        Double emptyCellPercentageThreshold,
        List<HeaderRuleResponse> headerRules
) {
}