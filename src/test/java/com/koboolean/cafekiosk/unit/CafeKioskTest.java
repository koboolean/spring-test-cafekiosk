package com.koboolean.cafekiosk.unit;

import com.koboolean.cafekiosk.unit.beverages.Americano;
import com.koboolean.cafekiosk.unit.beverages.Latte;
import com.koboolean.cafekiosk.unit.order.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CafeKioskTest {

    @Test
    void add(){
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        cafeKiosk.add(americano);

        assertThat(cafeKiosk.getList().size()).isEqualTo(1);
        assertThat(cafeKiosk.getList().get(0)).isEqualTo(americano);
    }

    @Test
    @DisplayName("카페 키오스크 내 내용 삭제에 대한 테스트")
    void remove(){
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        cafeKiosk.add(americano);


        cafeKiosk.remove(americano);

        assertThat(cafeKiosk.getList().size()).isEqualTo(0);
    }

    @Test
    void clear() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        cafeKiosk.add(americano);
        Latte latte = new Latte();
        cafeKiosk.add(latte);

        cafeKiosk.clear();

        assertThat(cafeKiosk.getList().size()).isEqualTo(0);
    }


    @Test
    void addSeveralBeverages() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        cafeKiosk.add(americano, 2);

        assertThat(cafeKiosk.getList().size()).isEqualTo(2);
        assertThat(cafeKiosk.getList().get(0)).isEqualTo(americano);
        assertThat(cafeKiosk.getList().get(1)).isEqualTo(americano);
    }

    @Test
    void addZeroBeverages() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        assertThatThrownBy(() -> cafeKiosk.add(americano, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음료는 1잔 이상 주문을 하셔야 합니다.");
    }

    @Test
    void createOrder(){
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        cafeKiosk.add(americano);

        Order order = cafeKiosk.createOrder();

        assertThat(order.getBeverages()).hasSize(1);
        assertThat(order.getBeverages().get(0)).isEqualTo(americano);
    }

    @Test
    void createOrderCurrentTime(){
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        cafeKiosk.add(americano);

        Order order = cafeKiosk.createOrder(LocalDateTime.of(2024,1,17,10,0));

        assertThat(order.getBeverages()).hasSize(1);
        assertThat(order.getBeverages().get(0)).isEqualTo(americano);
    }


    @Test
    void calculateTotalPrice(){
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        Latte latte = new Latte();

        cafeKiosk.add(americano);
        cafeKiosk.add(latte);

        int totalPrice = cafeKiosk.calculateTotalPrice();

        assertThat(totalPrice).isGreaterThan(8500);

    }


    @Test
    void createOrderOutsideCurrentTime(){
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        cafeKiosk.add(americano);

        assertThatThrownBy(() -> cafeKiosk.createOrder(LocalDateTime.of(2024,1,17,9,59)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("주문시간이 아닙니다.");

    }

}
