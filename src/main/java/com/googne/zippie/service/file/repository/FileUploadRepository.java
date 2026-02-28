package com.googne.zippie.service.file.repository;

import com.googne.zippie.service.file.model.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileUploadRepository extends JpaRepository<FileUpload, String> {
}
