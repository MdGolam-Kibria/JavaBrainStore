package com.CrackCode.util;

public class DiscountMathUtil {
    public static void main(String[] args) {
        System.out.println("Discounted Amount = " + DiscountMathUtil.getDiscountedAmount(100.0, 25.0));
        System.out.println("Discount Percentage = " + DiscountMathUtil.discountPercentage(500.0, 1000.0));
    }

    public static Double getDiscountedAmount(Double marketPriceOrCurrentPrice, Double percentageLike25) {
        Double temp = 100 - percentageLike25;//here percentageLike25 = 25 means 25%
        Double discountedAmount = (temp * marketPriceOrCurrentPrice) / 100;
        return discountedAmount;
    }

    public static Double discountPercentage(Double currentAmount, Double previousAmount) {
        // Calculating discount
        Double discount = previousAmount - currentAmount;

        // Calculating discount percentage
        Double disPercent = (discount / previousAmount) * 100;
        return disPercent;
    }
}
