package com.googne.zippie.service.file.repository;

import com.googne.zippie.service.file.model.HeaderRule;
import com.googne.zippie.service.file.model.enums.ColumnDataType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeaderRuleRepository extends JpaRepository<HeaderRule, String> {
    Optional<HeaderRule> findByDataTypeAndColumnName(ColumnDataType columnDataType, String columnName);
}
