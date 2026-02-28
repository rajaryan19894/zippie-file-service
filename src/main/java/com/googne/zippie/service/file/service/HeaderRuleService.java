package com.googne.zippie.service.file.service;

import com.googne.zippie.common.lib.exception.ResourceNotFoundException;
import com.googne.zippie.common.lib.validator.CommonValidator;
import com.googne.zippie.service.file.dto.HeaderRuleRequest;
import com.googne.zippie.service.file.mapper.HeaderRuleMapper;
import com.googne.zippie.service.file.model.HeaderRule;
import com.googne.zippie.service.file.repository.HeaderRuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HeaderRuleService {
    private final HeaderRuleRepository headerRuleRepository;
    private final HeaderRuleMapper headerRuleMapper;

    private final CommonValidator commonValidator;

    public List<HeaderRule> findAll() {
        return headerRuleRepository.findAll();
    }

    public HeaderRule findById(String id) {
        return headerRuleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("HeaderRule not found: " + id));
    }

    public List<HeaderRule> getOrCreateHeaderRule(List<HeaderRuleRequest> requestList) {
        commonValidator.requireNonEmpty(requestList);

        List<HeaderRule> headerRules = requestList.stream()
                .map(request ->
                        headerRuleRepository
                                .findByDataTypeAndColumnName(
                                        request.dataType(),
                                        request.columnName()
                                )
                                .orElseGet(() -> headerRuleMapper.fromDTO(request))
                )
                .toList();


//        List<HeaderRule> headerRules = headerRuleRepository.findByDataTypeAndColumnName(request.dataType(), request.columnName())
//                .orElseGet(() -> headerRuleMapper.fromDTO(request));
//
//                headerRuleMapper.fromDTO(requestList);
        return headerRuleRepository.saveAll(headerRules);
//
//
//        return requestList.stream()
//                .map(this::getOrCreateHeaderRule)
//                .toList();
    }

//    public HeaderRule getOrCreateHeaderRule(HeaderRuleRequest request) {
//        commonValidator.requireNonNull(request);
//
//        return headerRuleRepository.findByDataTypeAndColumnName(request.dataType(), request.columnName())
//                .orElseGet(() -> createHeaderRule(request));
//    }

//    private HeaderRule createHeaderRule(HeaderRuleRequest request) {
//        commonValidator.requireNonNull(request);
//
//        HeaderRule headerRule = headerRuleMapper.fromDTO(request);
//        return headerRuleRepository.save(headerRule);
//    }

    public void deleteHeaderRule(String id) {
        HeaderRule headerRule = findById(id);
        headerRuleRepository.delete(headerRule);
    }

    public void deleteAll() {
        headerRuleRepository.deleteAll();
    }
}
