package com.googne.zippie.service.file.engine.extractor;

import com.googne.zippie.service.file.dto.parser.ParsedColumn;
import com.googne.zippie.service.file.model.HeaderRule;

import java.util.List;

public interface RowExtractor<ROW, HEADER> {

    ParsedColumn extract(
            ROW row,
            HEADER header,
            List<HeaderRule> rules);
}

