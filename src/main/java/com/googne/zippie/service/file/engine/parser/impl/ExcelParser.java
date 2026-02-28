package com.googne.zippie.service.file.engine.parser.impl;

import com.googne.zippie.common.lib.validator.CommonValidator;
import com.googne.zippie.service.file.dto.parser.ParsedColumn;
import com.googne.zippie.service.file.dto.parser.ParsedRow;
import com.googne.zippie.service.file.dto.parser.ParserResult;
import com.googne.zippie.service.file.engine.detector.impl.ExcelHeaderDetector;
import com.googne.zippie.service.file.engine.evaluator.EndOfDataEvaluator;
import com.googne.zippie.service.file.engine.evaluator.EndOfDataEvaluatorResolver;
import com.googne.zippie.service.file.engine.extractor.impl.ExcelRowExtractor;
import com.googne.zippie.service.file.engine.model.ParsedHeader;
import com.googne.zippie.service.file.engine.parser.AbstractFileParserStrategy;
import com.googne.zippie.service.file.engine.propagation.FooterPropagationStrategy;
import com.googne.zippie.service.file.engine.propagation.FooterPropagationStrategyResolver;
import com.googne.zippie.service.file.model.FileTemplate;
import com.googne.zippie.service.file.model.FileUpload;
import com.googne.zippie.service.file.model.HeaderRule;
import com.googne.zippie.service.file.model.enums.FileFormat;
import com.googne.zippie.service.file.service.FileTemplateService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ExcelParser extends AbstractFileParserStrategy {
    private final FileTemplateService fileTemplateService;
    private final ExcelHeaderDetector headerDetector;
    private final ExcelRowExtractor rowExtractor;
    private final EndOfDataEvaluatorResolver endOfDataEvaluatorResolver;
    private final FooterPropagationStrategyResolver footerPropagationStrategyResolver;

    private final CommonValidator validator;

    @Override
    public boolean supports(FileFormat fileFormat) {
        return fileFormat == FileFormat.XLSX;
    }

    @Override
    public ParserResult parse(MultipartFile file, FileUpload upload) {
        FileTemplate template = fileTemplateService.getTemplateBySourceType(upload.getSourceType());
        List<HeaderRule> headerRules = template.getHeaderRules();

        validator.requireNonEmpty(headerRules);

        EndOfDataEvaluator rowEvaluator = endOfDataEvaluatorResolver.resolve(template);
        FooterPropagationStrategy footerStrategy =
                footerPropagationStrategyResolver.resolve(template);

        List<ParsedRow> results = new ArrayList<>();
        int totalRows = 0;
        int successRows = 0;

        try (Workbook wb = WorkbookFactory.create(file.getInputStream())) {
            for (int s = 0; s < wb.getNumberOfSheets(); s++) {
                Sheet sheet = wb.getSheetAt(s);

                ParsedHeader header;
                try {
                    header = headerDetector.detect(sheet, headerRules);
                } catch (Exception e) {
                    saveError(upload, s, sheet, -1, e);
                    continue;
                }

                List<ParsedRow> buffer = new ArrayList<>();
                for (int rowIdx = header.headerRowIndex() + 1; rowIdx <= sheet.getLastRowNum(); rowIdx++) {
                    totalRows++;

                    Row row = sheet.getRow(rowIdx);
                    if (row == null) continue;

                    try {
                        ParsedColumn parsedColumn =
                                rowExtractor.extract(row, header, headerRules);
                        ParsedRow currentRow = new ParsedRow(sheet.getSheetName(), rowIdx, parsedColumn);

                        if (rowEvaluator.isEndOfData(parsedColumn, template)) {
                            footerStrategy.flush(buffer, results);

                            if (template.isStopOnThresholdCross()) break;
                            continue;
                        }

                        footerStrategy.handleRow(currentRow, buffer, results);

                        successRows++;
                    } catch (Exception e) {
                        saveError(upload, s, sheet, rowIdx, e);
                    }
                }
                footerStrategy.flush(buffer, results);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Excel parsing failed", ex);
        }

        return new ParserResult(results, totalRows, successRows);
    }

}

