package com.koboolean.cafekiosk.spring.service.product;

import com.koboolean.cafekiosk.spring.controller.product.dto.requset.ProductCreateRequest;
import com.koboolean.cafekiosk.spring.domain.product.Product;
import com.koboolean.cafekiosk.spring.domain.product.ProductRepository;
import com.koboolean.cafekiosk.spring.domain.product.ProductSellingStatus;
import com.koboolean.cafekiosk.spring.response.ProductResponse;
import com.koboolean.cafekiosk.spring.service.product.request.ProductCreateServiceRequest;
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


    public ProductResponse createProduct(ProductCreateServiceRequest request) {
        // productNumber 부여
        // DB에서 마지막 저장된 프로덕트의 상품번호를 가져와 +1

        String productNumber = createNextProductNumber();

        Product product = request.toEntity(productNumber);
        Product savedProduct = productRepository.save(product);

        return ProductResponse.of(savedProduct);
    }

    private String createNextProductNumber(){
        String latestProductNumber = productRepository.findLatestProductNumber();

        if(latestProductNumber == null){
            return "001";
        }

        int latestProductNumberInt = Integer.parseInt(latestProductNumber);
        int nextProductNumberInt = latestProductNumberInt + 1;

        return String.format("%03d", nextProductNumberInt);
    }
}
