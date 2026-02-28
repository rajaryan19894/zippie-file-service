package com.googne.zippie.service.file.exception;

import com.googne.zippie.common.lib.exception.BusinessException;

public class InvalidFileException extends BusinessException {
    public InvalidFileException(String message) {
        super(message);
    }
}
