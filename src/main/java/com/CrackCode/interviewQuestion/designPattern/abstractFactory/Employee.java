package com.CrackCode.interviewQuestion.designPattern.abstractFactory;

/**
 * This is the main concept for abstract factory
 */
public interface Employee<A,C> {
    A create(String positionName);
    C createInCrackCode(String positionName);
}
