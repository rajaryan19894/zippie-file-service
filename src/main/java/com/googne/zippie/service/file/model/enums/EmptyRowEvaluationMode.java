package com.googne.zippie.service.file.model.enums;

public enum EmptyRowEvaluationMode {
    REQUIRED_ONLY,   // Count only required columns
    ALL_MAPPED,      // Count all mapped columns
    PERCENTAGE       // Based on percentage threshold
}
