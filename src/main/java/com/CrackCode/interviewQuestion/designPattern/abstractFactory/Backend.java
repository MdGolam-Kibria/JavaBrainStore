package com.CrackCode.interviewQuestion.designPattern.abstractFactory;

public class Backend implements Developer {
    @Override
    public String addNewDeveloper(String developerName) {
        return "A new" + developerName + "Added";
    }
}
