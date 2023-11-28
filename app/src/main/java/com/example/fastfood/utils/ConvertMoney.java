package com.example.fastfood.utils;

public class ConvertMoney {
    public static String formatCurrency(double amount) {
        return String.format("%,.0fđ", amount);
    }
}
