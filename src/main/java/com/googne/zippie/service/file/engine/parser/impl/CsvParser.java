package com.googne.zippie.service.file.engine.parser.impl;

import com.googne.zippie.service.file.dto.parser.ParserResult;
import com.googne.zippie.service.file.engine.parser.AbstractFileParserStrategy;
import com.googne.zippie.service.file.model.FileUpload;
import com.googne.zippie.service.file.model.enums.FileFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class CsvParser extends AbstractFileParserStrategy {

    @Override
    public boolean supports(FileFormat fileFormat) {
        return fileFormat == FileFormat.CSV;
    }

    @Override
    public ParserResult parse(
            MultipartFile file,
            FileUpload upload) {
        throw new UnsupportedOperationException("CSV parsing not implemented");

//        Map<LocalDate, List<RowData>> grouped = new LinkedHashMap<>();
//        int rowNum = 0;
//
//        try (BufferedReader reader = new BufferedReader(
//                new InputStreamReader(file.getInputStream()))) {
//
//            reader.readLine(); // header
//            String line;
//
//            while ((line = reader.readLine()) != null) {
//                rowNum++;
//
//                try {
//                    RowData data = RowData.fromCsv(line);
//                    grouped
//                            .computeIfAbsent(data.transactionDate(), d -> new ArrayList<>())
//                            .add(data);
//
//                } catch (Exception ex) {
//                    saveError(upload, rowNum, line, ex);
//                }
//            }
//        } catch (Exception ex) {
//            throw new RuntimeException("CSV parsing failed", ex);
//        }
//
//        return buildResult(grouped, upload, account, rowNum);
    }
}

