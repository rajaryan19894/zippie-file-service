package com.googne.zippie.service.file.model;

import com.googne.zippie.service.file.dto.parser.ParsedRow;
import com.googne.zippie.service.file.model.enums.SourceType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.*;

import java.util.List;

import static com.googne.zippie.service.file.util.FileUtil.resolveFileType;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileUpload extends FileEntity {
    @Column(nullable = false)
    private SourceType sourceType;

    private String uploadedBy;

    @Transient
    @Setter
    private List<ParsedRow> parsedRowList;

    public void setFileName(String fileName) {
        super.setFileName(fileName);
        setFileFormat(resolveFileType(fileName));
    }
}
