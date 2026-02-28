package com.googne.zippie.service.file.engine.parser.impl;

import com.googne.zippie.service.file.dto.parser.ParserResult;
import com.googne.zippie.service.file.engine.parser.AbstractFileParserStrategy;
import com.googne.zippie.service.file.model.FileUpload;
import com.googne.zippie.service.file.model.enums.FileFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class PdfParser extends AbstractFileParserStrategy {

    @Override
    public boolean supports(FileFormat fileFormat) {
        return fileFormat == FileFormat.PDF;
    }

    @Override
    public ParserResult parse(
            MultipartFile file,
            FileUpload upload) {
        throw new UnsupportedOperationException("PDF parsing not implemented");
    }
}

