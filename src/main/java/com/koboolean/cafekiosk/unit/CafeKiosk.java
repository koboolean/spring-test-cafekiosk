package com.koboolean.cafekiosk.unit;

import com.koboolean.cafekiosk.unit.order.Order;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CafeKiosk {

    private static final LocalTime SHOP_OPEN_TIME = LocalTime.of(10, 0);
    private static final LocalTime SHOP_CLOSE_TIME = LocalTime.of(22, 0);

    private final List<Beverage> list = new ArrayList<>();

    public void add(Beverage beverage) {
        list.add(beverage);
    }

    public void add(Beverage beverage, int cnt) {

        if(cnt <= 0){
            throw new IllegalArgumentException("음료는 1잔 이상 주문을 하셔야 합니다.");
        }

        for(int i = 0; i < cnt; i++) {
            list.add(beverage);
        }
    }

    public void remove(Beverage beverage) {
        list.remove(beverage);
    }

    public void clear(){
        list.clear();
    }


    public int calculateTotalPrice() {
        return list.stream().mapToInt(Beverage::getPrice).sum();
    }


    public Order createOrder(){
        LocalDateTime now = LocalDateTime.now();
        LocalTime localTime = now.toLocalTime();

        if(localTime.isBefore(SHOP_OPEN_TIME) || localTime.isAfter(SHOP_CLOSE_TIME)){
            throw new IllegalArgumentException("주문시간이 아닙니다.");
        }

        return new Order(now, list);
    }

    public Order createOrder(LocalDateTime localDateTime) {
        LocalTime localTime = localDateTime.toLocalTime();

        if(localTime.isBefore(SHOP_OPEN_TIME) || localTime.isAfter(SHOP_CLOSE_TIME)){
            throw new IllegalArgumentException("주문시간이 아닙니다.");
        }

        return new Order(localDateTime, list);
    }
}
