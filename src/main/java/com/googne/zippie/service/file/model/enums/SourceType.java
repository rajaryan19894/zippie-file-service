package com.googne.zippie.service.file.model.enums;

public enum SourceType {

    CASH_AED_XLSX_OLDER,
    CASH_INR_XLSX_OLDER,
    CASH_AED_CSV_OLDER,
    CASH_INR_CSV_OLDER,
    CASH_AED_PDF_OLDER,
    CASH_INR_PDF_OLDER;

    private final String accountType;
    private final String currencyCode;
    private final String fileType;
    private final String detail;

    SourceType() {
        String[] parts = this.name().split("_");

        if (parts.length != 4) {
            throw new IllegalStateException(
                    "Invalid SourceType format: " + this.name()
            );
        }

        this.accountType = parts[0];
        this.currencyCode = parts[1];
        this.fileType = parts[2];
        this.detail = parts[3];
    }

    public String getAccountType() {
        return accountType;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getFileType() {
        return fileType;
    }

    public String getDetail() {
        return detail;
    }
}


