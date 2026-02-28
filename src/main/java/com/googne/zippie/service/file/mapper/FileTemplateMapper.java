package com.googne.zippie.service.file.mapper;

import com.googne.zippie.common.lib.mapper.BaseMapper;
import com.googne.zippie.common.lib.mapper.UpdateMapper;
import com.googne.zippie.service.file.dto.FileTemplateRequest;
import com.googne.zippie.service.file.dto.FileTemplateResponse;
import com.googne.zippie.service.file.dto.UpdateFileTemplateRequest;
import com.googne.zippie.service.file.model.FileTemplate;
import jakarta.validation.Valid;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface FileTemplateMapper extends
        BaseMapper<FileTemplate, FileTemplateRequest, FileTemplateResponse> {

    @Mapping(target = "headerRules", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(UpdateFileTemplateRequest request, @MappingTarget FileTemplate fileTemplate);
}
