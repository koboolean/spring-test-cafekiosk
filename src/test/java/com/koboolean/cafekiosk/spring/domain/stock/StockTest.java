package com.koboolean.cafekiosk.spring.domain.stock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockTest {

    @DisplayName("재고의 수량이 제공된 수량보다 작은지 확인한다.")
    @Test
    void isQuantityLessThan() {
        // Given
        Stock stock = Stock.create("001", 1);

        int quantity = 2;

        // When
        boolean quantityLessThan = stock.isQuantityLessThan(quantity);


        // Then
        assertTrue(quantityLessThan);
    }

    @DisplayName("재고를 주어진 갯수만큼 차감한다.")
    @Test
    void deductQuantity() {
        // Given
        Stock stock = Stock.create("001", 1);
        int quantity = 1;

        // When
        stock.deductQuantity(quantity);

        // Then
        assertEquals(0, stock.getQuantity());

    }

    @DisplayName("재고보다 많은 수량으로 차감을 시도하는 경우 예외가 발생한다.")
    @Test
    void deductQuantity2() {
        // Given
        Stock stock = Stock.create("001", 1);
        int quantity = 2;

        // Then
        assertThrows(IllegalArgumentException.class, () -> stock.deductQuantity(quantity));

    }

}
