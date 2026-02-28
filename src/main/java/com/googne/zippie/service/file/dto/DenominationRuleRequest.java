package com.googne.zippie.service.file.dto;

import com.googne.zippie.common.lib.util.CommonUtil;
import com.googne.zippie.service.file.model.enums.ColumnDataType;
import com.googne.zippie.service.file.model.enums.EmptyCellStrategy;

import java.util.List;
import java.util.stream.Stream;

public record DenominationRuleRequest(
        List<Integer> notes,
        List<Integer> coins,
        ColumnDataType dataType,
        boolean required,
        EmptyCellStrategy emptyCellStrategy
) {
    public List<HeaderRuleRequest> toHeaderRules() {
        return Stream.concat(
                toHeaderRules(notes, "N_").stream(),
                toHeaderRules(coins, "C_").stream()
        ).toList();
    }

    private List<HeaderRuleRequest> toHeaderRules(List<Integer> values, String prefix) {
        if (!CommonUtil.isValid(values))
            return List.of();

        return values.stream()
                .map(value -> {
                            String name = prefix + value;
                            return HeaderRuleRequest.builder()
                                    .aliases(List.of(name))
                                    .columnName(name)
                                    .dataType(this.dataType)
                                    .required(this.required)
                                    .emptyCellStrategy(this.emptyCellStrategy)
                                    .build();
                        }
                )
                .toList();
    }
}
