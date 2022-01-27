package com.CrackCode.designPattern.abstractFactory.agamisoft;

public class Backend implements AgamiSoftDeveloper {
    @Override
    public String addNewDeveloper(String developerName) {
        return "A new" + developerName + "Added in AgamiSoft";
    }
}
