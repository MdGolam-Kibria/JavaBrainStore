package com.CrackCode.interviewQuestion.ApachePoi;

public interface ContentReadListener {
    void afterRowRead(int rowIndex, String[] values);
    String afterCellRead(int rowIndex, int cellIndex, String value);
}
