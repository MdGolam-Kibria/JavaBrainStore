package com.CrackCode.interviewQuestion.designPattern.abstractFactory;

public class AbstractFactoryOutput {
    public static void main(String[] args) {
        Developer developer = new SoftwareIndustry().create("Backend Developer");
        System.out.printf(developer.addNewDeveloper("Backend Developer"));

        Developer developer1 = new SoftwareIndustry().create("Backend Developer");
        System.out.printf("\n\n");
        System.out.printf(developer1.addNewDeveloper("Backend Developer"));
    }
}
