package com.googne.zippie.service.file.dto;

import com.googne.zippie.service.file.model.enums.ColumnDataType;
import com.googne.zippie.service.file.model.enums.EmptyCellStrategy;

import java.util.List;

public record HeaderRuleResponse(
        String id,
        ColumnDataType dataType,
        String columnName,
        List<String> aliases,
        boolean required,
        EmptyCellStrategy emptyCellStrategy
) {
}
