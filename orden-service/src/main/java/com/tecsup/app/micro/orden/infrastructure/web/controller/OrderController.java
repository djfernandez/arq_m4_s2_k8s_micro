package com.tecsup.app.micro.orden.infrastructure.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecsup.app.micro.orden.application.service.OrderApplicationService;
import com.tecsup.app.micro.orden.domain.model.Order;
import com.tecsup.app.micro.orden.infrastructure.web.dto.CreateOrderRequest;
import com.tecsup.app.micro.orden.infrastructure.web.mapper.OrderDtoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

  private final OrderApplicationService orderApplicationService;
  private final OrderDtoMapper orderDtoMapper;

  /**
   * Obtiene todas las órdenes (solo ADMIN)
   */
  @GetMapping
  public ResponseEntity<List<Order>> getAllOrders() {
    log.info("REST request to get all orders");
    List<Order> orders = orderApplicationService.getAllOrders();
    return ResponseEntity.ok(orders);
  }

  @PostMapping
  public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest order) {
    log.info("REST request to create order: {}", order);
    Order orderDomain = orderDtoMapper.toDomain(order);
    Order createdOrder = orderApplicationService.createOrder(orderDomain);
    return ResponseEntity.ok(createdOrder);
  }

  /**
   * Endpoint de salud (público, sin autenticación)
   */
  @GetMapping("/health")
  public ResponseEntity<String> health() {
    return ResponseEntity.ok("Order Service running with Clean Architecture!");
  }
}
