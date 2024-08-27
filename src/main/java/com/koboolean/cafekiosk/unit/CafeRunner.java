package com.koboolean.cafekiosk.unit;

import com.koboolean.cafekiosk.unit.beverages.Americano;
import com.koboolean.cafekiosk.unit.beverages.Latte;

public class CafeRunner {

    public static void main(String[] args) {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());
        System.out.println(">>> 아메리카노 추가");
        cafeKiosk.add(new Latte());
        System.out.println(">>> 라떼 추가");

        int totalPrice = cafeKiosk.calculateTotalPrice();

        System.out.println("총 주문 가격 : " + totalPrice);
    }
}
