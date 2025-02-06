package com.CrackCode.designPattern.structuralDesignPattern.adapterPattern;

import java.math.BigDecimal;

public interface GroceryItem {
    String getName();
    BigDecimal getPrice();
    String getStoreName();
}