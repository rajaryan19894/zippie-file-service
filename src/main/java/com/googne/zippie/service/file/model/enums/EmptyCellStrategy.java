package com.googne.zippie.service.file.model.enums;

public enum EmptyCellStrategy {
    NULL,              // return null
    USE_PREVIOUS,      // use previous row value
    THROW_EXCEPTION,    // if required,
    FOOTER_PROPAGATE        // if copy required, from footer value

    ;

    public boolean isFooterValue() {
        return this == FOOTER_PROPAGATE;
    }
}
