package com.googne.zippie.service.file.engine.parser;

import com.googne.zippie.service.file.model.FileUpload;
import com.googne.zippie.service.file.model.FileUploadError;
import com.googne.zippie.service.file.repository.FileUploadErrorRepository;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractFileParserStrategy
        implements FileParserStrategy {
    @Autowired
    protected FileUploadErrorRepository errorRepository;

    protected void saveError(
            FileUpload upload,
            int sheetIndex,
            Sheet sheet,
            int rowNumber,
            Exception ex) {

        FileUploadError error = new FileUploadError();
        error.setFileUpload(upload);
        error.setSheetIndex(sheetIndex);
        error.setSheetName(sheet.getSheetName());
        error.setRowNumber(rowNumber);
        error.setErrorCode("ROW_PARSE_ERROR");
        error.setErrorMessage(ex.getMessage());
//        error.setRawData(rawData);

        errorRepository.save(error);
    }
}

