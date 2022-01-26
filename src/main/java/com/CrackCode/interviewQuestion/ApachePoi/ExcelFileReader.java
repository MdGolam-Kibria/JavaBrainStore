package com.CrackCode.interviewQuestion.ApachePoi;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;

public class ExcelFileReader implements SpreadSheetReader {
    private final Workbook workbook;
    private ContentReadListener contentReadListener;
    private int cells;
    private int rowReadCount;


    public ExcelFileReader(final InputStream stream) throws IOException, InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        this.workbook = WorkbookFactory.create(stream);
    }
    
    public void setContentReadListener(int cells, ContentReadListener contentReadListener) {
        this.cells = cells;
        this.contentReadListener = contentReadListener;
    }

    public void execute(int sheetIndex) {
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        rowReadCount = 0;

        Cell cell;
        String value;
        String[] values;
        for(Row row: sheet) {
            values = new String[cells];
            for(int i = 0; i < cells; i++) {
                cell = row.getCell(i);
                value = cell == null ? "" : cell.toString();
                value = contentReadListener.afterCellRead(rowReadCount, i, value);
                values[i] = value;
            }
            contentReadListener.afterRowRead(rowReadCount, values);
            rowReadCount++;
        }
    }

    public int getRowReadCount() {
        return rowReadCount;
    }
}
