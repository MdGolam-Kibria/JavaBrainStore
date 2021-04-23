package com.CrackCode.interviewQuestion.designPattern.Factory;

public class FactoryOutput {
    public static void main(String[] args) {
        ProgrammerType role = SoftwareCompany.getProgrammerRole("SpringBoot");
        System.out.printf(role.getProgrammerName()+"\n");

        ProgrammerType role2 = SoftwareCompany.getProgrammerRole("Html");
        System.out.printf(role2.getProgrammerName());
    }
}
