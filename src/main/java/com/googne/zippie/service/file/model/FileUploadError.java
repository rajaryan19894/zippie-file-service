package com.googne.zippie.service.file.model;

import com.googne.zippie.common.lib.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(
        name = "file_upload_error",
        indexes = {
                @Index(name = "idx_file_upload_error_file", columnList = "file_upload_id"),
                @Index(name = "idx_file_upload_error_row", columnList = "rowNumber")
        }
)
public class FileUploadError extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "file_upload_id", nullable = false)
    private FileUpload fileUpload;

    private Integer sheetIndex;
    private String sheetName;

    private Integer rowNumber;      // Excel row number (1-based)
    private String columnName;      // Optional
    private String rawData;        // What failed

    private String errorCode;

    @Column(length = 2000)
    private String errorMessage;
}
