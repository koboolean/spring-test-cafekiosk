package com.koboolean.cafekiosk.spring.domain.orderproduct;

import com.koboolean.cafekiosk.spring.domain.BaseEntity;
import com.koboolean.cafekiosk.spring.domain.order.Order;
import com.koboolean.cafekiosk.spring.domain.product.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OrderProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    public OrderProduct(Order order, Product product) {
        this.product = product;
        this.order = order;
    }
}
