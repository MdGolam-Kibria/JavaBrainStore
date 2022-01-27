package com.CrackCode.designPattern.abstractFactory;

/**
 * This is the main concept for abstract factory
 */
public interface Employee<A,C> {
    A createInAgamiSoft(String positionName);
    C createInCrackCode(String positionName);
}
