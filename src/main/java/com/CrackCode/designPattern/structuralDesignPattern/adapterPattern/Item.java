package com.CrackCode.designPattern.structuralDesignPattern.adapterPattern;

import java.math.BigDecimal;

public interface Item {
    String getItemName();
    BigDecimal getPrice();
    String getRestaurantName();
}