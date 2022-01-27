package com.CrackCode.ApachePoi.XLfileReadWrite;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.servlet.ServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ExcelFileWriter implements SpreadSheetWriter {
    private XSSFWorkbook workbook;
    private ContentReadListener contentReadListener;

    public ExcelFileWriter() {
        workbook = new XSSFWorkbook();
    }

    public void execute(String sheetName, String[] headers, List<Object[]> rows) {
        Sheet sheet = workbook.createSheet(sheetName);

        Row row;
        int rowIndex = 0;

        Cell cell;
        int cellIndex = 0;

        row = sheet.createRow(rowIndex++);
        for (String header: headers) {
            cell = row.createCell(cellIndex++);
            cell.setCellValue(header);
            sheet.autoSizeColumn(cell.getColumnIndex());
        }

        for (Object[] values: rows) {
            row = sheet.createRow(rowIndex++);

            cellIndex = 0;
            for (Object value: values) {
                cell = row.createCell(cellIndex++);
                value = contentReadListener.afterCellRead(rowIndex, cellIndex, value == null ? "" : value.toString());
                cell.setCellValue(value.toString());
            }
        }
    }

    public void setContentReadListener(int cells, ContentReadListener listener) {
        contentReadListener = listener;
    }

    public void toFile(File file)  {

    }

    public void toCsv(File file)  {

    }

    public void toResponse(ServletResponse response) throws IOException  {
        workbook.write(response.getOutputStream());
    }
}
