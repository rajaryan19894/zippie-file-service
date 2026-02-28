package com.googne.zippie.service.file.util;

import com.googne.zippie.service.file.model.enums.FileFormat;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;

import java.math.BigDecimal;

public class FileUtil {

    public static FileFormat resolveFileType(String fileName) {
        String extension = FilenameUtils.getExtension(fileName).toUpperCase();

        return switch (extension) {
            case "XLS", "XLSX" -> FileFormat.XLSX;
            case "CSV" -> FileFormat.CSV;
            case "PDF" -> FileFormat.PDF;
            default -> FileFormat.NA;
        };
    }

    public static boolean isCellEmpty(Cell cell) {
        return cell == null || cell.getCellType() == CellType.BLANK;
    }

    public static String readCellValue(Cell cell) {
        if (cell == null) return "";

        String cellValue = switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> DateUtil.isCellDateFormatted(cell)
                    ? cell.getLocalDateTimeCellValue().toString()
                    : BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString();
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
//            case FORMULA -> cell.getCellFormula();
            default -> "";
        };
        return cellValue.trim();
    }
}
