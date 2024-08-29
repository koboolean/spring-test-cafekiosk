package com.koboolean.cafekiosk.spring.domain.product;

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
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @DisplayName("원하는 판매상태를 가진 상품을 조회한다.")
    @Test
    void findAllBySellingStatusIn() {
        // Given
        Product product1 = createProduct("001", HANDMADE, SELLING,"아메리카노", 4000);
        Product product2 = createProduct("002", HANDMADE, HOLD,"카페라떼", 4500);

        String targetProductNumber = "003";
        Product product3 = createProduct("003", HANDMADE, STOP_SELLING,"팥빙수", 7000);


        productRepository.saveAll(List.of(product1, product2, product3));

        // When
        List<Product> product = productRepository.findAllBySellingStatusIn(List.of(SELLING, HOLD));

        // Then
        assertThat(product).hasSize(2) // 사이즈 체크
                .extracting("productNumber", "name", "sellingStatus") // 컬럼 목록 가져오기
                .containsExactlyInAnyOrder( // 일치여부 확인하기
                        tuple("001", "아메리카노", SELLING),
                        tuple("002", "카페라떼", HOLD)
                );
    }

    @DisplayName("상품번호 리스트로 상품들을 조회한다.")
    @Test
    void findAllByProductNumberIn() {
        // Given
        Product product1 = createProduct("001", HANDMADE, SELLING,"아메리카노", 4000);
        Product product2 = createProduct("002", HANDMADE, HOLD,"카페라떼", 4500);

        String targetProductNumber = "003";
        Product product3 = createProduct(targetProductNumber, HANDMADE, STOP_SELLING,"팥빙수", 7000);


        productRepository.saveAll(List.of(product1, product2, product3));

        // When
        List<Product> product = productRepository.findAllByProductNumberIn(List.of("001", "002"));

        // Then
        assertThat(product).hasSize(2) // 사이즈 체크
                .extracting("productNumber", "name", "sellingStatus") // 컬럼 목록 가져오기
                .containsExactlyInAnyOrder( // 일치여부 확인하기
                        tuple("001", "아메리카노", SELLING),
                        tuple("002", "카페라떼", HOLD)
                );
    }

    @DisplayName("가장 마지막으로 저장한 상품의 상품번호를 읽어온다.")
    @Test
    void findLatestProductNumber() {
        // Given
        Product product1 = createProduct("001", HANDMADE, SELLING,"아메리카노", 4000);
        Product product2 = createProduct("002", HANDMADE, HOLD,"카페라떼", 4500);

        String targetProductNumber = "003";
        Product product3 = createProduct("003", HANDMADE, STOP_SELLING,"팥빙수", 7000);

        productRepository.saveAll(List.of(product1, product2, product3));

        // When
        String productNumber = productRepository.findLatestProductNumber();

        // Then
        assertThat(productNumber).isEqualTo(targetProductNumber);
    }

    @DisplayName("가장 마지막으로 저장한 상품의 상품번호를 읽어올 경우, 상품이 하나도 없을 때에는 NULL을 반환한다.")
    @Test
    void findLatestProductNumberWhenProductIsEmpty() {
        // Given

        // When
        String productNumber = productRepository.findLatestProductNumber();

        // Then
        assertThat(productNumber).isNull();
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
