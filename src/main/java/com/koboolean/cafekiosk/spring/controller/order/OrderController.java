package com.koboolean.cafekiosk.spring.controller.order;

import com.koboolean.cafekiosk.spring.controller.order.request.OrderCreateRequest;
import com.koboolean.cafekiosk.spring.response.OrderResponse;
import com.koboolean.cafekiosk.spring.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @PostMapping("/api/v1/orders/new")
    public OrderResponse createOrder(@RequestBody OrderCreateRequest request){
        LocalDateTime registeredDateTime = LocalDateTime.now();
        return orderService.createOrder(request, registeredDateTime);
    }
}
