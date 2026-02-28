package com.googne.zippie.service.file.repository;

import com.googne.zippie.service.file.model.FileTemplate;
import com.googne.zippie.service.file.model.enums.SourceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileTemplateRepository extends JpaRepository<FileTemplate, String> {
    Optional<FileTemplate> findBySourceType(SourceType sourceType);
}
