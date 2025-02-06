package com.CrackCode.designPattern.structuralDesignPattern.adapterPattern;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        SwiggyStoreApp swiggyStoreApp = new SwiggyStoreApp();

        //Existing Items
        swiggyStoreApp.addItems(new FoodItem("ICE", new BigDecimal(70), "Kaji Store"));
        swiggyStoreApp.addItems(new FoodItem("Barger", new BigDecimal(150), "Manik Store"));

        //[Adapter] grocery which was incompatible with food. as a new planned service
        swiggyStoreApp.addItems(new GroceryItemAdapter(new GroceryProduct("RICE", new BigDecimal(3245), "Rohim Store")));
        swiggyStoreApp.addItems(new GroceryItemAdapter(new GroceryProduct("Oil", new BigDecimal(100), "Korim Store")));

        //We can create multiple adapter class to adopt multiple functionality as like [Adapter] grocery

        swiggyStoreApp.getAllItems().forEach(current -> {
            System.out.println(current.getItemName());
            System.out.println(current.getPrice());
            System.out.println(current.getRestaurantName());
            System.out.println();
            System.out.println();
        });
    }
}