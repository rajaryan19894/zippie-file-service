package com.googne.zippie.service.file.mapper;

import com.googne.zippie.service.file.dto.HeaderRuleRequest;
import com.googne.zippie.service.file.dto.HeaderRuleResponse;
import com.googne.zippie.service.file.model.HeaderRule;
import com.googne.zippie.service.file.model.enums.ColumnDataType;
import com.googne.zippie.service.file.model.enums.EmptyCellStrategy;
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
public class HeaderRuleMapperImpl implements HeaderRuleMapper {

    @Override
    public HeaderRuleResponse toDTO(HeaderRule entity) {
        if ( entity == null ) {
            return null;
        }

        String id = null;
        ColumnDataType dataType = null;
        String columnName = null;
        List<String> aliases = null;
        boolean required = false;
        EmptyCellStrategy emptyCellStrategy = null;

        id = entity.getId();
        dataType = entity.getDataType();
        columnName = entity.getColumnName();
        List<String> list = entity.getAliases();
        if ( list != null ) {
            aliases = new ArrayList<String>( list );
        }
        required = entity.isRequired();
        emptyCellStrategy = entity.getEmptyCellStrategy();

        HeaderRuleResponse headerRuleResponse = new HeaderRuleResponse( id, dataType, columnName, aliases, required, emptyCellStrategy );

        return headerRuleResponse;
    }

    @Override
    public List<HeaderRuleResponse> toDTO(List<HeaderRule> entities) {
        if ( entities == null ) {
            return null;
        }

        List<HeaderRuleResponse> list = new ArrayList<HeaderRuleResponse>( entities.size() );
        for ( HeaderRule headerRule : entities ) {
            list.add( toDTO( headerRule ) );
        }

        return list;
    }

    @Override
    public HeaderRule fromDTO(HeaderRuleRequest request) {
        if ( request == null ) {
            return null;
        }

        HeaderRule headerRule = new HeaderRule();

        headerRule.setDataType( request.dataType() );
        headerRule.setColumnName( request.columnName() );
        List<String> list = request.aliases();
        if ( list != null ) {
            headerRule.setAliases( new ArrayList<String>( list ) );
        }
        headerRule.setRequired( request.required() );
        headerRule.setEmptyCellStrategy( request.emptyCellStrategy() );

        return headerRule;
    }

    @Override
    public List<HeaderRule> fromDTO(List<HeaderRuleRequest> request) {
        if ( request == null ) {
            return null;
        }

        List<HeaderRule> list = new ArrayList<HeaderRule>( request.size() );
        for ( HeaderRuleRequest headerRuleRequest : request ) {
            list.add( fromDTO( headerRuleRequest ) );
        }

        return list;
    }
}
