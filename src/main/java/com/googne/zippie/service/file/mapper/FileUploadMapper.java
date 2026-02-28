package com.googne.zippie.service.file.mapper;

import com.googne.zippie.common.lib.mapper.BaseMapper;
import com.googne.zippie.service.file.dto.FileUploadRequest;
import com.googne.zippie.service.file.dto.FileUploadResponse;
import com.googne.zippie.service.file.model.FileUpload;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileUploadMapper extends
        BaseMapper<FileUpload, FileUploadRequest, FileUploadResponse> {

}
