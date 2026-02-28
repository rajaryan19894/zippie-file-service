package com.googne.zippie.service.file.repository;

import com.googne.zippie.service.file.model.FileUploadError;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileUploadErrorRepository extends JpaRepository<FileUploadError, String> {
}
