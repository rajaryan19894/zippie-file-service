package com.googne.zippie.service.file.controller;

import com.googne.zippie.common.lib.annotation.GlobalApiResponses;
import com.googne.zippie.service.file.dto.*;
import com.googne.zippie.service.file.mapper.FileTemplateMapper;
import com.googne.zippie.service.file.mapper.HeaderRuleMapper;
import com.googne.zippie.service.file.service.FileTemplateService;
import com.googne.zippie.service.file.service.HeaderRuleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@GlobalApiResponses
@RequestMapping("/template")
public class FileTemplateController {
    private final FileTemplateService fileTemplateService;
    private final FileTemplateMapper fileTemplateMapper;

    private final HeaderRuleService headerRuleService;
    private final HeaderRuleMapper headerRuleMapper;

    @GetMapping
    public ResponseEntity<List<FileTemplateResponse>> getAll() {
        return ResponseEntity.ok(
                fileTemplateMapper.toDTO(
                        fileTemplateService.findAll()
                )
        );
    }

    @GetMapping("/{templateId}")
    public ResponseEntity<FileTemplateResponse> getById(@PathVariable String templateId) {
        return ResponseEntity.ok(
                fileTemplateMapper.toDTO(
                        fileTemplateService.findById(templateId)
                )
        );
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<FileTemplateResponse>> createAll(
            @Valid @RequestBody List<FileTemplateRequest> requestList) {
        return ResponseEntity.ok(
                fileTemplateMapper.toDTO(
                        fileTemplateService.getOrCreateFileTemplate(requestList)
                )
        );
    }

    @PostMapping
    public ResponseEntity<FileTemplateResponse> create(
            @Valid @RequestBody FileTemplateRequest request) {
        return ResponseEntity.ok(
                fileTemplateMapper.toDTO(
                        fileTemplateService.getOrCreateFileTemplate(request)
                )
        );
    }

    @PutMapping
    public ResponseEntity<FileTemplateResponse> update(
            @Valid @RequestBody UpdateFileTemplateRequest request) {
        return ResponseEntity.ok(
                fileTemplateMapper.toDTO(
                        fileTemplateService.updateFileTemplate(request)
                )
        );
    }

    @DeleteMapping("/{templateId}")
    public ResponseEntity<Void> delete(@PathVariable String templateId) {
        fileTemplateService.deleteFileTemplate(templateId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/corrupted")
    public ResponseEntity<String> deleteAllCorrupted() {
        fileTemplateService.deleteAllCorrupted();
        return ResponseEntity.ok("All corrupted files deleted");
    }

    @PostMapping("/{templateId}/header-rules")
    public ResponseEntity<FileTemplateResponse> addHeaderRules(
            @PathVariable String templateId,
            @Valid @RequestBody List<HeaderRuleRequest> headerRuleRequests) {
        return ResponseEntity.ok(
                fileTemplateMapper.toDTO(
                        fileTemplateService.addHeaderRules(templateId, headerRuleRequests)
                )
        );
    }
}
