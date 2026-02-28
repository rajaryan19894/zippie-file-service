package com.googne.zippie.service.file.service;

import com.googne.zippie.service.file.dto.FileUploadRequest;
import com.googne.zippie.service.file.dto.FileUploadResponse;
import com.googne.zippie.service.file.dto.parser.ParserResult;
import com.googne.zippie.service.file.engine.parser.FileParserStrategy;
import com.googne.zippie.service.file.engine.parser.FileParserStrategyResolver;
import com.googne.zippie.service.file.mapper.FileUploadMapper;
import com.googne.zippie.service.file.model.FileUpload;
import com.googne.zippie.service.file.repository.FileUploadRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileUploadService {
    private final FileUploadRepository fileUploadRepository;
    private final FileUploadMapper fileUploadMapper;
    private final FileParserStrategyResolver parserResolver;

    @Transactional
    public FileUploadResponse extractData(MultipartFile file, FileUploadRequest request) {
        FileUpload fileUpload = createFileUpload(file, request);
        fileUploadRepository.save(fileUpload);

        try {
            fileUpload.markProcessing();

            FileParserStrategy parser =
                    parserResolver.resolve(fileUpload.getFileFormat());

            ParserResult result =
                    parser.parse(file, fileUpload);

            fileUpload.markSuccess(
                    result.totalRows(),
                    result.successRows()
            );
            fileUpload.setParsedRowList(result.parsedRowList());

        } catch (Exception ex) {
            fileUpload.markFailure(ex.getMessage());
            throw ex;
        } finally {
            fileUploadRepository.save(fileUpload);
        }

        return fileUploadMapper.toDTO(fileUpload);
    }

    private FileUpload createFileUpload(MultipartFile file, FileUploadRequest request) {
        FileUpload upload = fileUploadMapper.fromDTO(request);
        upload.markUploaded();

        upload.setFileName(file.getOriginalFilename());
        upload.setContentType(file.getContentType());
        upload.setFileSize(file.getSize());

        return upload;
    }
}
