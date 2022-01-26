package com.CrackCode.interviewQuestion.ApachePoi;

public class XmlStringBuilder {
    private String root;
    private StringBuilder buffer;

    public XmlStringBuilder(String root) {
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
}
