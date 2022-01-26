package com.CrackCode.interviewQuestion.ApachePoi;

import javax.servlet.ServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public interface SpreadSheetWriter {
    /**
     *
     * @param cells - how many cells
     * @param listener
     */
    public void setContentReadListener(int cells, ContentReadListener listener);

    /**
     *
     * @param sheetName
     * @param headers
     * @param rows
     */
    public void execute(String sheetName, String[] headers, List<Object[]> rows);

    /**
     *
     * @param file
     * @throws IOException
     */
    public void toFile(File file) throws IOException;

    /**
     *
     * @param file
     * @throws IOException
     */
    public void toCsv(File file) throws IOException;

    /**
     *
     * @param response
     * @throws IOException
     */
    public void toResponse(ServletResponse response) throws IOException;
}
