package com.googne.zippie.service.file.controller;

import com.googne.zippie.common.lib.annotation.GlobalApiResponses;
import com.googne.zippie.service.file.dto.FileUploadRequest;
import com.googne.zippie.service.file.dto.FileUploadResponse;
import com.googne.zippie.service.file.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@GlobalApiResponses
@RequestMapping("/upload")
public class FileUploadController {
    private final FileUploadService fileUploadService;

    @PostMapping
    public ResponseEntity<FileUploadResponse> uploadFile(
            @RequestPart("file") MultipartFile file,
            @RequestPart("data") FileUploadRequest request) {

        FileUploadResponse response =
                fileUploadService.extractData(file, request);

        return ResponseEntity.ok(response);
    }

}
