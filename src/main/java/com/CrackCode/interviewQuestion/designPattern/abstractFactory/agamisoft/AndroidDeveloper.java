package com.CrackCode.interviewQuestion.designPattern.abstractFactory.agamisoft;

public class AndroidDeveloper implements AgamiSoftDeveloper {
    @Override
    public String addNewDeveloper(String developerName) {
        return "A new"+developerName +"Added in AgamiSoft";
    }
}
