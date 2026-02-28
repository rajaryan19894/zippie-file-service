package com.googne.zippie.service.file.model;

import com.googne.zippie.common.lib.model.BaseEntity;
import com.googne.zippie.service.file.model.enums.FileFormat;
import com.googne.zippie.service.file.model.enums.FileStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

/**
 * Base entity for file-related operations.
 * Tracks file metadata, status, and processing results.
 */
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@MappedSuperclass
public abstract class FileEntity extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected FileStatus fileStatus;
    @NotBlank
    @Column(nullable = false)
    private String fileName;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private FileFormat fileFormat;
    private String contentType; // MULTIPART_FORM, API
    @NotNull
    @Positive
    @Column(nullable = false)
    private Long fileSize;
    @Column(updatable = false, nullable = false)
    private Instant uploadedAt;
    private Instant processedAt;

    @Min(0)
    private Integer totalRecords;

    @Min(0)
    private Integer successRecords;

    @Min(0)
    private Integer failedRecords;

    @Column(length = 4000)
    private String errorMessage;

    public void markUploaded() {
        this.fileStatus = FileStatus.UPLOADED;
        this.uploadedAt = Instant.now();
    }

    public void markProcessing() {
        this.fileStatus = FileStatus.PROCESSING;
    }

    public void markSuccess(int total, int success) {
        this.totalRecords = total;
        this.successRecords = success;
        this.failedRecords = total - success;
        this.processedAt = Instant.now();
        this.fileStatus = failedRecords == 0
                ? FileStatus.SUCCESS
                : FileStatus.PARTIAL;
    }

    public void markFailure(String errorMessage) {
        this.errorMessage = errorMessage;
        this.processedAt = Instant.now();
        this.fileStatus = FileStatus.FAILED;
    }
}