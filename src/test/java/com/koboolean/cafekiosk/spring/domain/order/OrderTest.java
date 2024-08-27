package com.koboolean.cafekiosk.spring.domain.order;

import com.koboolean.cafekiosk.spring.domain.product.Product;
import com.koboolean.cafekiosk.spring.domain.product.ProductType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static com.koboolean.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING;
import static org.assertj.core.api.Assertions.*;

class OrderTest {


    @DisplayName("주문 생성 시 주문상태는 INIT이다.")
    @Test
    void init() {
        // Given
        List<Product> product = List.of(createProduct("001", 1000),
                createProduct("002", 2000));
        // When
        Order order = Order.create(product, LocalDateTime.now());

        // Then
        assertThat(order.getOrderStatus()).isEqualByComparingTo(OrderStatus.INIT);
    }

    @DisplayName("주문 생성 시 주문 등록 시간을 넣어준다.")
    @Test
    void registerdDateTime() {
        // Given
        List<Product> product = List.of(createProduct("001", 1000),
                createProduct("002", 2000));

        LocalDateTime localDateTime = LocalDateTime.now();

        // When
        Order order = Order.create(product, localDateTime);

        // Then
        assertThat(order.getRegisteredDateTime()).isEqualTo(localDateTime);

    }

    @DisplayName("주문 생성 시 상품 리스트에서 주문의 총 금액을 계산한다.")
    @Test
    void calculateTotalPrice() {
        // Given
        List<Product> product = List.of(createProduct("001", 1000),
                createProduct("002", 2000));

        // When
        Order order = Order.create(product, LocalDateTime.now());

        // Then
        assertThat(order.getTotalPrice()).isEqualTo(3000);
    }

    private Product createProduct(String productNumber, int price) {
        return Product.builder()
                .type(ProductType.HANDMADE)
                .productNumber(productNumber)
                .price(price)
                .sellingStatus(SELLING)
                .name("메뉴 이름")
                .build();
    }

}
