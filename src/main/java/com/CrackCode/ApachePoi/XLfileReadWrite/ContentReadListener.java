package com.CrackCode.ApachePoi.XLfileReadWrite;

public interface ContentReadListener {
    void afterRowRead(int rowIndex, String[] values);
    String afterCellRead(int rowIndex, int cellIndex, String value);
}
