package com.koboolean.cafekiosk.unit.order;

import com.koboolean.cafekiosk.unit.Beverage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class Order {

    private LocalDateTime orderDate;
    private List<Beverage> beverages;

}
