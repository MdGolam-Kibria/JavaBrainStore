package com.CrackCode.designPattern.Factory;

/**
 * This is the main factory class
 */
public class SoftwareCompany {
    /**
     * This is the factory method
     */
    public static ProgrammerType getProgrammerRole(String languageName) {
        if (languageName.equals("SpringBoot")) {
            return new Backend();
        }
        if (languageName.equals("Html")) {
            return new Frontend();
        }
        if (languageName.equals("SpringBootAndAngular")) {
            return new FullStack();
        }
        return null;
    }
}
