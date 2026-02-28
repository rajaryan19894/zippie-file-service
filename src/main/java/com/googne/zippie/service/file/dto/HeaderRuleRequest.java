package com.googne.zippie.service.file.dto;

import com.googne.zippie.service.file.model.enums.ColumnDataType;
import com.googne.zippie.service.file.model.enums.EmptyCellStrategy;
import lombok.Builder;

import java.util.List;

@Builder
public record HeaderRuleRequest(
        ColumnDataType dataType,
        String columnName,
        List<String> aliases,
        boolean required,
        EmptyCellStrategy emptyCellStrategy
) {
}
