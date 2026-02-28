package com.googne.zippie.service.file.dto;


import com.googne.zippie.service.file.dto.parser.ParsedRow;
import com.googne.zippie.service.file.model.enums.FileStatus;

import java.util.List;

public record FileUploadResponse(
        String id,
        FileStatus fileStatus,
        Integer totalRecords,
        Integer successRecords,
        Integer failedRecords,
        List<ParsedRow> parsedRowList
) {
}