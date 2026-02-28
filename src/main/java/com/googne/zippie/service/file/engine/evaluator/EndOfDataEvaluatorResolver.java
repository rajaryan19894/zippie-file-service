package com.googne.zippie.service.file.engine.evaluator;

import com.googne.zippie.service.file.model.FileTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EndOfDataEvaluatorResolver {
    private final List<EndOfDataEvaluator> evaluators;

    public EndOfDataEvaluator resolve(FileTemplate fileTemplate) {
        return evaluators.stream()
                .filter(p -> p.supports(fileTemplate))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Unsupported mode"));
    }
}
