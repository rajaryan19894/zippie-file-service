package com.googne.zippie.service.file.engine.model;

import java.util.Map;

public record ParsedHeader(
        int headerRowIndex,
        Map<String, Integer> columnMap
//        Map<String, Integer> denominationMap
) {
}
