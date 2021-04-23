package com.CrackCode.interviewQuestion.designPattern.abstractFactory;

/**
 * This is the main concept for abstract factory
 */
public interface Employee<T> {
    T create(String positionName);
}
