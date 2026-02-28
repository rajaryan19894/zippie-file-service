package com.googne.zippie.service.file.engine.parser;

import com.googne.zippie.service.file.dto.parser.ParserResult;
import com.googne.zippie.service.file.model.FileUpload;
import com.googne.zippie.service.file.model.enums.FileFormat;
import org.springframework.web.multipart.MultipartFile;

public interface FileParserStrategy {

    boolean supports(FileFormat fileFormat);

    ParserResult parse(
            MultipartFile file,
            FileUpload upload
    );

}

