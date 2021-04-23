package com.CrackCode.interviewQuestion.designPattern.abstractFactory.agamisoft;

public class AndroidAgamiSoftDeveloper implements AgamiSoftDeveloper {
    @Override
    public String addNewDeveloper(String developerName) {
        return "A new"+developerName +"Added in AgamiSoft";
    }
}
