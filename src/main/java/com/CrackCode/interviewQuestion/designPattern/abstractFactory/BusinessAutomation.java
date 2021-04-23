package com.CrackCode.interviewQuestion.designPattern.abstractFactory;

import com.CrackCode.interviewQuestion.designPattern.abstractFactory.agamisoft.AgamiSoftDeveloper;
import com.CrackCode.interviewQuestion.designPattern.abstractFactory.agamisoft.AndroidDeveloper;
import com.CrackCode.interviewQuestion.designPattern.abstractFactory.agamisoft.Backend;
import com.CrackCode.interviewQuestion.designPattern.abstractFactory.crackCode.CTO;
import com.CrackCode.interviewQuestion.designPattern.abstractFactory.crackCode.CrackCodeDeveloper;
import com.CrackCode.interviewQuestion.designPattern.abstractFactory.crackCode.MarketingManager;

/**
 * This is the main abstract Factory class................................
 */
public class BusinessAutomation implements Employee<AgamiSoftDeveloper, CrackCodeDeveloper> {
    /**
     * And this is the main abstract Factory method.
     */
    @Override
    public AgamiSoftDeveloper createInAgamiSoft(String positionName) {
        if (positionName.equalsIgnoreCase("Android Developer")) {
            return new AndroidDeveloper();
        }
        if (positionName.equalsIgnoreCase("Backend Developer")) {
            return new Backend();
        }
        return null;
    }

    /**
     * And this is the main abstract Factory method.
     */
    @Override
    public CrackCodeDeveloper createInCrackCode(String positionName) {
        if (positionName.equalsIgnoreCase("Marketing Manager")) {
            return new MarketingManager();
        }
        if (positionName.equalsIgnoreCase("CTO")) {
            return new CTO();
        }
        return null;
    }
}
