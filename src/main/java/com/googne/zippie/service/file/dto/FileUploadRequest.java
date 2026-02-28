package com.googne.zippie.service.file.dto;

import com.googne.zippie.service.file.model.enums.SourceType;

public record FileUploadRequest(
        SourceType sourceType,
        String uploadedBy
) {
}
