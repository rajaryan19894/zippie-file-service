package com.googne.zippie.service.file.mapper;

import com.googne.zippie.service.file.dto.FileTemplateRequest;
import com.googne.zippie.service.file.dto.FileTemplateResponse;
import com.googne.zippie.service.file.dto.HeaderRuleRequest;
import com.googne.zippie.service.file.dto.HeaderRuleResponse;
import com.googne.zippie.service.file.dto.UpdateFileTemplateRequest;
import com.googne.zippie.service.file.model.FileTemplate;
import com.googne.zippie.service.file.model.HeaderRule;
import com.googne.zippie.service.file.model.enums.ColumnDataType;
import com.googne.zippie.service.file.model.enums.EmptyCellStrategy;
import com.googne.zippie.service.file.model.enums.FileFormat;
import com.googne.zippie.service.file.model.enums.SourceType;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-15T13:52:57+0400",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.16 (Homebrew)"
)
@Component
public class FileTemplateMapperImpl implements FileTemplateMapper {

    @Override
    public FileTemplateResponse toDTO(FileTemplate entity) {
        if ( entity == null ) {
            return null;
        }

        String id = null;
        SourceType sourceType = null;
        String name = null;
        FileFormat format = null;
        Integer emptyCellThreshold = null;
        boolean stopOnThresholdCross = false;
        Double emptyCellPercentageThreshold = null;
        List<HeaderRuleResponse> headerRules = null;

        id = entity.getId();
        sourceType = entity.getSourceType();
        name = entity.getName();
        format = entity.getFormat();
        emptyCellThreshold = entity.getEmptyCellThreshold();
        stopOnThresholdCross = entity.isStopOnThresholdCross();
        emptyCellPercentageThreshold = entity.getEmptyCellPercentageThreshold();
        headerRules = headerRuleListToHeaderRuleResponseList( entity.getHeaderRules() );

        FileTemplateResponse fileTemplateResponse = new FileTemplateResponse( id, sourceType, name, format, emptyCellThreshold, stopOnThresholdCross, emptyCellPercentageThreshold, headerRules );

        return fileTemplateResponse;
    }

    @Override
    public List<FileTemplateResponse> toDTO(List<FileTemplate> entities) {
        if ( entities == null ) {
            return null;
        }

        List<FileTemplateResponse> list = new ArrayList<FileTemplateResponse>( entities.size() );
        for ( FileTemplate fileTemplate : entities ) {
            list.add( toDTO( fileTemplate ) );
        }

        return list;
    }

    @Override
    public FileTemplate fromDTO(FileTemplateRequest request) {
        if ( request == null ) {
            return null;
        }

        FileTemplate fileTemplate = new FileTemplate();

        fileTemplate.setSourceType( request.sourceType() );
        fileTemplate.setName( request.name() );
        fileTemplate.setFormat( request.format() );
        fileTemplate.setEmptyCellThreshold( request.emptyCellThreshold() );
        fileTemplate.setStopOnThresholdCross( request.stopOnThresholdCross() );
        fileTemplate.setEmptyCellPercentageThreshold( request.emptyCellPercentageThreshold() );
        fileTemplate.setEmptyRowEvaluationMode( request.emptyRowEvaluationMode() );
        fileTemplate.setHeaderRules( headerRuleRequestListToHeaderRuleList( request.headerRules() ) );

        return fileTemplate;
    }

    @Override
    public List<FileTemplate> fromDTO(List<FileTemplateRequest> request) {
        if ( request == null ) {
            return null;
        }

        List<FileTemplate> list = new ArrayList<FileTemplate>( request.size() );
        for ( FileTemplateRequest fileTemplateRequest : request ) {
            list.add( fromDTO( fileTemplateRequest ) );
        }

        return list;
    }

    @Override
    public void update(UpdateFileTemplateRequest request, FileTemplate fileTemplate) {
        if ( request == null ) {
            return;
        }

        if ( request.id() != null ) {
            fileTemplate.setId( request.id() );
        }
        if ( request.name() != null ) {
            fileTemplate.setName( request.name() );
        }
        if ( request.emptyCellThreshold() != null ) {
            fileTemplate.setEmptyCellThreshold( request.emptyCellThreshold() );
        }
        fileTemplate.setStopOnThresholdCross( request.stopOnThresholdCross() );
        if ( request.emptyCellPercentageThreshold() != null ) {
            fileTemplate.setEmptyCellPercentageThreshold( request.emptyCellPercentageThreshold() );
        }
        if ( request.emptyRowEvaluationMode() != null ) {
            fileTemplate.setEmptyRowEvaluationMode( request.emptyRowEvaluationMode() );
        }
    }

    protected HeaderRuleResponse headerRuleToHeaderRuleResponse(HeaderRule headerRule) {
        if ( headerRule == null ) {
            return null;
        }

        String id = null;
        ColumnDataType dataType = null;
        String columnName = null;
        List<String> aliases = null;
        boolean required = false;
        EmptyCellStrategy emptyCellStrategy = null;

        id = headerRule.getId();
        dataType = headerRule.getDataType();
        columnName = headerRule.getColumnName();
        List<String> list = headerRule.getAliases();
        if ( list != null ) {
            aliases = new ArrayList<String>( list );
        }
        required = headerRule.isRequired();
        emptyCellStrategy = headerRule.getEmptyCellStrategy();

        HeaderRuleResponse headerRuleResponse = new HeaderRuleResponse( id, dataType, columnName, aliases, required, emptyCellStrategy );

        return headerRuleResponse;
    }

    protected List<HeaderRuleResponse> headerRuleListToHeaderRuleResponseList(List<HeaderRule> list) {
        if ( list == null ) {
            return null;
        }

        List<HeaderRuleResponse> list1 = new ArrayList<HeaderRuleResponse>( list.size() );
        for ( HeaderRule headerRule : list ) {
            list1.add( headerRuleToHeaderRuleResponse( headerRule ) );
        }

        return list1;
    }

    protected HeaderRule headerRuleRequestToHeaderRule(HeaderRuleRequest headerRuleRequest) {
        if ( headerRuleRequest == null ) {
            return null;
        }

        HeaderRule headerRule = new HeaderRule();

        headerRule.setDataType( headerRuleRequest.dataType() );
        headerRule.setColumnName( headerRuleRequest.columnName() );
        List<String> list = headerRuleRequest.aliases();
        if ( list != null ) {
            headerRule.setAliases( new ArrayList<String>( list ) );
        }
        headerRule.setRequired( headerRuleRequest.required() );
        headerRule.setEmptyCellStrategy( headerRuleRequest.emptyCellStrategy() );

        return headerRule;
    }

    protected List<HeaderRule> headerRuleRequestListToHeaderRuleList(List<HeaderRuleRequest> list) {
        if ( list == null ) {
            return null;
        }

        List<HeaderRule> list1 = new ArrayList<HeaderRule>( list.size() );
        for ( HeaderRuleRequest headerRuleRequest : list ) {
            list1.add( headerRuleRequestToHeaderRule( headerRuleRequest ) );
        }

        return list1;
    }
}
