package com.koboolean.cafekiosk.spring.service;

import com.koboolean.cafekiosk.spring.domain.product.Product;
import com.koboolean.cafekiosk.spring.domain.product.ProductRepository;
import com.koboolean.cafekiosk.spring.domain.product.ProductSellingStatus;
import com.koboolean.cafekiosk.spring.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponse> getSellingProducts() {

        List<Product> products = productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay());

        return products.stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());

    }


}
