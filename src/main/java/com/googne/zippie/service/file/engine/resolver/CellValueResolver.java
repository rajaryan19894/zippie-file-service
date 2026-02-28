package com.googne.zippie.service.file.engine.resolver;

import com.googne.zippie.service.file.model.HeaderRule;
import com.googne.zippie.service.file.model.enums.EmptyCellStrategy;

import java.util.HashMap;
import java.util.Map;

public interface CellValueResolver<ROW> {
    Map<String, Object> previousValues = new HashMap<>();

    default boolean previousEligible(HeaderRule rule) {
        return rule.getEmptyCellStrategy() == EmptyCellStrategy.USE_PREVIOUS;
    }

    default <T> T getPrevious(String logicalName, Class<T> type) {
        Object value = previousValues.get(logicalName);
        if (value == null) return null;
        return type.cast(value);
    }

    default void putPrevious(String logicalName, Object value) {
        if (value != null) {
            previousValues.put(logicalName, value);
        }
    }

    String resolve(
            ROW row,
            Integer columnIndex,
            HeaderRule rule);
}

