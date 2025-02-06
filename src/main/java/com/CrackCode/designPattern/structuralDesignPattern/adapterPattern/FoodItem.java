package com.CrackCode.designPattern.structuralDesignPattern.adapterPattern;

import java.math.BigDecimal;


public class FoodItem implements Item {
    private String name;
    private BigDecimal foodPrice;
    private String shopName;

    public FoodItem(String name, BigDecimal foodPrice, String shopName) {
        this.name = name;
        this.foodPrice = foodPrice;
        this.shopName = shopName;
    }

    @Override
    public String getItemName() {
        return name;
    }

    @Override
    public BigDecimal getPrice() {
        return foodPrice;
    }

    @Override
    public String getRestaurantName() {
        return shopName;
    }
}