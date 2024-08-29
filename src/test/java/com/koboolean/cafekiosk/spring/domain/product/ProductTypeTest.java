package com.koboolean.cafekiosk.spring.domain.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ProductTypeTest {

    @DisplayName("상품의 타입이 재고 관련 타입인지를 확인한다.")
    @Test
    void containsStockType() {
        // Given
        ProductType productType = ProductType.HANDMADE;

        // When
        boolean result = ProductType.containsStockType(productType);

        // Then
        assertFalse(result);
    }

    @DisplayName("상품의 타입이 재고 관련 타입인지를 확인한다.")
    @Test
    void containsStockType2() {
        // Given
        ProductType productType = ProductType.BAKERY;

        // When
        boolean result = ProductType.containsStockType(productType);

        // Then
        assertTrue(result);
    }

}
