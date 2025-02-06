package com.CrackCode.designPattern.structuralDesignPattern.adapterPattern;

import java.math.BigDecimal;

public class GroceryProduct implements GroceryItem{
    private String itemName;
    private BigDecimal itemPrice;
    private String shopName;

    public GroceryProduct(String itemName, BigDecimal itemPrice, String shopName) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.shopName = shopName;
    }

    @Override
    public String getName() {
        return this.itemName;
    }

    @Override
    public BigDecimal getPrice() {
        return itemPrice;
    }

    @Override
    public String getStoreName() {
        return shopName;
    }
}