package com.googne.zippie.service.file.mapper;

import com.googne.zippie.service.file.dto.FileUploadRequest;
import com.googne.zippie.service.file.dto.FileUploadResponse;
import com.googne.zippie.service.file.dto.parser.ParsedRow;
import com.googne.zippie.service.file.model.FileUpload;
import com.googne.zippie.service.file.model.enums.FileStatus;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-15T08:21:01+0400",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.16 (Homebrew)"
)
@Component
public class FileUploadMapperImpl implements FileUploadMapper {

    @Override
    public FileUploadResponse toDTO(FileUpload entity) {
        if ( entity == null ) {
            return null;
        }

        String id = null;
        FileStatus fileStatus = null;
        Integer totalRecords = null;
        Integer successRecords = null;
        Integer failedRecords = null;
        List<ParsedRow> parsedRowList = null;

        id = entity.getId();
        fileStatus = entity.getFileStatus();
        totalRecords = entity.getTotalRecords();
        successRecords = entity.getSuccessRecords();
        failedRecords = entity.getFailedRecords();
        List<ParsedRow> list = entity.getParsedRowList();
        if ( list != null ) {
            parsedRowList = new ArrayList<ParsedRow>( list );
        }

        FileUploadResponse fileUploadResponse = new FileUploadResponse( id, fileStatus, totalRecords, successRecords, failedRecords, parsedRowList );

        return fileUploadResponse;
    }

    @Override
    public List<FileUploadResponse> toDTO(List<FileUpload> entities) {
        if ( entities == null ) {
            return null;
        }

        List<FileUploadResponse> list = new ArrayList<FileUploadResponse>( entities.size() );
        for ( FileUpload fileUpload : entities ) {
            list.add( toDTO( fileUpload ) );
        }

        return list;
    }

    @Override
    public FileUpload fromDTO(FileUploadRequest request) {
        if ( request == null ) {
            return null;
        }

        FileUpload fileUpload = new FileUpload();

        fileUpload.setSourceType( request.sourceType() );
        fileUpload.setUploadedBy( request.uploadedBy() );

        return fileUpload;
    }

    @Override
    public List<FileUpload> fromDTO(List<FileUploadRequest> request) {
        if ( request == null ) {
            return null;
        }

        List<FileUpload> list = new ArrayList<FileUpload>( request.size() );
        for ( FileUploadRequest fileUploadRequest : request ) {
            list.add( fromDTO( fileUploadRequest ) );
        }

        return list;
    }
}
