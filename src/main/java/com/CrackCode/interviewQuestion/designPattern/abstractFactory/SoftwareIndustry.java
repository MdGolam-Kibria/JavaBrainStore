package com.CrackCode.interviewQuestion.designPattern.abstractFactory;

/**
 * This is the main abstract Factory class.
 */
public class SoftwareIndustry implements Employee<Developer> {
    /**
     * And this is the main abstract Factory method.
     */
    @Override
    public Developer create(String positionName) {
        if (positionName.equalsIgnoreCase("Android Developer")) {
            return new AndroidDeveloper();
        }
        if (positionName.equalsIgnoreCase("Backend Developer")) {
            return new Backend();
        }
        return null;
    }
}
