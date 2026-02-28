package com.googne.zippie.service.file.service;

import com.googne.zippie.common.lib.exception.ResourceNotFoundException;
import com.googne.zippie.common.lib.validator.CommonValidator;
import com.googne.zippie.service.file.dto.FileTemplateRequest;
import com.googne.zippie.service.file.dto.HeaderRuleRequest;
import com.googne.zippie.service.file.dto.UpdateFileTemplateRequest;
import com.googne.zippie.service.file.mapper.FileTemplateMapper;
import com.googne.zippie.service.file.mapper.HeaderRuleMapper;
import com.googne.zippie.service.file.model.FileTemplate;
import com.googne.zippie.service.file.model.HeaderRule;
import com.googne.zippie.service.file.model.enums.SourceType;
import com.googne.zippie.service.file.repository.FileTemplateRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FileTemplateService {
    private final FileTemplateRepository fileTemplateRepository;
    private final FileTemplateMapper fileTemplateMapper;
    private final CommonValidator commonValidator;

    private final HeaderRuleService headerRuleService;
    private final HeaderRuleMapper headerRuleMapper;

    public FileTemplate getTemplateBySourceType(SourceType sourceType) {
        return fileTemplateRepository.findBySourceType(sourceType)
                .orElseThrow(() -> new ResourceNotFoundException("Template", Map.of("SourceType", sourceType)));
    }

    public List<FileTemplate> findAll() {
        return fileTemplateRepository.findAll();
    }

    public FileTemplate findById(String id) {
        return fileTemplateRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("FileTemplate not found: " + id));
    }

    public List<FileTemplate> getOrCreateFileTemplate(List<FileTemplateRequest> requestList) {
        commonValidator.requireNonEmpty(requestList);

        return requestList.stream()
                .map(this::getOrCreateFileTemplate)
                .toList();
    }

    public FileTemplate getOrCreateFileTemplate(FileTemplateRequest request) {
        commonValidator.requireNonNull(request);

        FileTemplate fileTemplate = fileTemplateRepository.findBySourceType(request.sourceType())
                .orElseGet(() -> fileTemplateMapper.fromDTO(request));

        List<HeaderRule> headerRules = headerRuleService
                .getOrCreateHeaderRule(request.getAllHeaderRules());

        headerRules.forEach(headerRule -> headerRule.setTemplate(fileTemplate));

        fileTemplate.setHeaderRules(headerRules);
        return fileTemplateRepository.save(fileTemplate);
    }

    public FileTemplate updateFileTemplate(@Valid UpdateFileTemplateRequest request) {
        commonValidator.requireNonNull(request);

        FileTemplate fileTemplate = findById(request.id());
        fileTemplateMapper.update(request, fileTemplate);

        List<HeaderRule> headerRules = headerRuleService
                .getOrCreateHeaderRule(request.getAllHeaderRules());

        headerRules.forEach(headerRule -> headerRule.setTemplate(fileTemplate));

        fileTemplate.addHeaderRule(headerRules);
        return fileTemplateRepository.save(fileTemplate);
    }

    public void deleteFileTemplate(String id) {
        FileTemplate fileTemplate = findById(id);
        fileTemplateRepository.delete(fileTemplate);
    }

    public FileTemplate addHeaderRules(String templateId,
                                       List<HeaderRuleRequest> headerRuleRequests) {
        commonValidator.requireNonNull(templateId);
        commonValidator.requireNonEmpty(headerRuleRequests);

        FileTemplate fileTemplate = findById(templateId);

        return populateHeaderRule(fileTemplate, headerRuleRequests);
    }

    private FileTemplate populateHeaderRule(FileTemplate fileTemplate,
                                            List<HeaderRuleRequest> headerRuleRequests) {
        return null;
    }

    private FileTemplate createFileTemplate(FileTemplateRequest request) {
        FileTemplate template = fileTemplateMapper.fromDTO(request);
        template.clearHeaderRule();

        return fileTemplateRepository.save(template);
    }

    public void deleteAllCorrupted() {
        fileTemplateRepository.deleteAll();
        headerRuleService.deleteAll();
    }
}
