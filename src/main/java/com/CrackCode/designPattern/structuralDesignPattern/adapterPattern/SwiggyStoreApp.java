package com.CrackCode.designPattern.structuralDesignPattern.adapterPattern;

import java.util.ArrayList;
import java.util.List;

public class SwiggyStoreApp {
   List<Item> items = new ArrayList<>();
   public void addItems(Item item) {
       items.add(item);
   }
   public List<Item> getAllItems(){
       return items;
   }

}