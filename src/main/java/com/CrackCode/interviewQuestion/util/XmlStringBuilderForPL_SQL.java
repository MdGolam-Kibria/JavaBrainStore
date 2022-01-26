package com.CrackCode.interviewQuestion.util;

public class XmlStringBuilderForPL_SQL {
    private String root;
    private StringBuilder buffer;

    public XmlStringBuilderForPL_SQL(String root) {
        this.root = root;
        buffer = new StringBuilder();
        buffer.append("<").append(root).append(">");
    }

    public StringBuilder toStringBuilder() {
        return new StringBuilder(buffer).append("</").append(root).append(">");
    }

    public void createStartTag(String tag) {
        buffer.append("<").append(tag).append(">");
    }

    public void createEndTag(String tag) {
        buffer.append("</").append(tag).append(">");
    }

    public void createTag(String tag, String value) {
        createStartTag(tag);
        buffer.append(value);
        createEndTag(tag);
    }

    public static void main(String[] args) {
        /**
         * Use for send XML to PL/SQL Procedure or same others.
         * @TODO OUR EXPECTED XML IS
         * @TODO <statement>
         *    <rowrecord>
         *       <SIGNATORY_ID>0</SIGNATORY_ID>
         *    </rowrecord>
         *    <rowrecord>
         *       <SIGNATORY_ID>1</SIGNATORY_ID>
         *    </rowrecord>
         *    <rowrecord>
         *       <SIGNATORY_ID>2</SIGNATORY_ID>
         *    </rowrecord>
         *    <rowrecord>
         *       <SIGNATORY_ID>3</SIGNATORY_ID>
         *    </rowrecord>
         *    <rowrecord>
         *       <SIGNATORY_ID>4</SIGNATORY_ID>
         *    </rowrecord>
         *    <rowrecord>
         *       <SIGNATORY_ID>5</SIGNATORY_ID>
         *    </rowrecord>
         *    <rowrecord>
         *       <SIGNATORY_ID>6</SIGNATORY_ID>
         *    </rowrecord>
         *    <rowrecord>
         *       <SIGNATORY_ID>7</SIGNATORY_ID>
         *    </rowrecord>
         *    <rowrecord>
         *       <SIGNATORY_ID>8</SIGNATORY_ID>
         *    </rowrecord>
         *    <rowrecord>
         *       <SIGNATORY_ID>9</SIGNATORY_ID>
         *    </rowrecord>
         * </statement>
         *
         */


        //now write the xml
        XmlStringBuilderForPL_SQL xmlStringBuilderForPLSQL = new XmlStringBuilderForPL_SQL("statement");

        int temp = 10;

        for (int i = 0; i < temp; i++) {
            xmlStringBuilderForPLSQL.createStartTag("rowrecord");
            xmlStringBuilderForPLSQL.createTag("SIGNATORY_ID", String.valueOf(i));
            xmlStringBuilderForPLSQL.createEndTag("rowrecord");
        }
        //This is the expected XML
        String expectedString = xmlStringBuilderForPLSQL.toStringBuilder().toString();
        System.out.println(expectedString);
    }
}
