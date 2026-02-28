package com.googne.zippie.service.file.mapper;

import com.googne.zippie.common.lib.mapper.BaseMapper;
import com.googne.zippie.service.file.dto.HeaderRuleRequest;
import com.googne.zippie.service.file.dto.HeaderRuleResponse;
import com.googne.zippie.service.file.model.HeaderRule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HeaderRuleMapper extends
        BaseMapper<HeaderRule, HeaderRuleRequest, HeaderRuleResponse> {
}
