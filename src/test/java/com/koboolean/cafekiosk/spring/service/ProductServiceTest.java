package com.koboolean.cafekiosk.spring.service;

import com.koboolean.cafekiosk.spring.controller.product.dto.requset.ProductCreateRequest;
import com.koboolean.cafekiosk.spring.domain.product.Product;
import com.koboolean.cafekiosk.spring.domain.product.ProductRepository;
import com.koboolean.cafekiosk.spring.domain.product.ProductSellingStatus;
import com.koboolean.cafekiosk.spring.domain.product.ProductType;
import com.koboolean.cafekiosk.spring.response.ProductResponse;
import com.koboolean.cafekiosk.spring.service.product.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.koboolean.cafekiosk.spring.domain.product.ProductSellingStatus.*;
import static com.koboolean.cafekiosk.spring.domain.product.ProductType.HANDMADE;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class ProductServiceTest {

    @Autowired
    private ProductService productService;


    @Autowired
    private ProductRepository productRepository;

    @DisplayName("신규 상품을 등록한다. 상품번호는 가장 최근 상품번호의 번호에서 1 증가한 값이다.")
    @Test
    void createProduct() {
        // Given
        Product product1 = createProduct("001", HANDMADE, SELLING,"아메리카노", 4000);
        Product product2 = createProduct("002", HANDMADE, HOLD,"카페라떼", 4500);
        Product product3 = createProduct("003", HANDMADE, STOP_SELLING,"팥빙수", 7000);

        productRepository.saveAll(List.of(product1, product2, product3));

        ProductCreateRequest request = ProductCreateRequest.builder()
                .type(HANDMADE)
                .sellingStatus(SELLING)
                .name("카푸치노")
                .price(5000)
                .build();

        // When
        ProductResponse response = productService.createProduct(request.toServiceRequest());

        // Then
        assertThat(response).extracting("productNumber", "type", "sellingStatus", "name", "price")
                .contains("004", HANDMADE, SELLING,"카푸치노",5000);

        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(4)
                .extracting("productNumber", "type", "sellingStatus", "name", "price")
                .containsExactlyInAnyOrder(tuple("001", HANDMADE, SELLING,"아메리카노",4000)
                                                 , tuple("002", HANDMADE, HOLD,"카페라떼",4500)
                                                 , tuple("003", HANDMADE, STOP_SELLING,"팥빙수",7000)
                                                 , tuple("004", HANDMADE, SELLING,"카푸치노",5000));
    }


    @DisplayName("상품이 하나도 존재하지 않을 때 신규 상품을 등록한다. 상품번호는 001이다.")
    @Test
    void createProductWhenProductsIsEmpty() {
        // Given
        ProductCreateRequest request = ProductCreateRequest.builder()
                .type(HANDMADE)
                .sellingStatus(SELLING)
                .name("카푸치노")
                .price(5000)
                .build();

        // When
        ProductResponse response = productService.createProduct(request.toServiceRequest());

        // Then
        assertThat(response).extracting("productNumber", "type", "sellingStatus", "name", "price")
                .contains("001", HANDMADE, SELLING,"카푸치노",5000);

        List<Product> products = productRepository.findAll();
        assertThat(products).extracting("productNumber", "type", "sellingStatus", "name", "price")
                .containsExactlyInAnyOrder(tuple("001", HANDMADE, SELLING, "카푸치노", 5000));
    }

    private Product createProduct(String productNumber, ProductType productType, ProductSellingStatus productSellingStatus, String name, int price) {
        return Product.builder()
                .productNumber(productNumber)
                .type(productType)
                .sellingStatus(productSellingStatus)
                .name(name)
                .price(price)
                .build();
    }
}
