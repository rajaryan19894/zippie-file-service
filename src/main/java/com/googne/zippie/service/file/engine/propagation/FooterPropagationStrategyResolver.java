package com.googne.zippie.service.file.engine.propagation;

import com.googne.zippie.common.lib.util.CommonUtil;
import com.googne.zippie.common.lib.validator.CommonValidator;
import com.googne.zippie.service.file.engine.propagation.impl.DefaultFooterPropagationStrategy;
import com.googne.zippie.service.file.engine.propagation.impl.NoFooterPropagationStrategy;
import com.googne.zippie.service.file.model.FileTemplate;
import com.googne.zippie.service.file.model.HeaderRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FooterPropagationStrategyResolver {
    private final CommonValidator validator;


    public FooterPropagationStrategy resolve(FileTemplate template) {
        Map<String, HeaderRule> footerHeaderRule = template.getHeaderRules().stream()
                .filter(rule -> rule.getEmptyCellStrategy().isFooterValue())
                .collect(Collectors.toMap(HeaderRule::getColumnName, Function.identity()));

        if (CommonUtil.isValid(footerHeaderRule)) {
            return new DefaultFooterPropagationStrategy(validator, footerHeaderRule);
        }

        return new NoFooterPropagationStrategy();
    }
}
