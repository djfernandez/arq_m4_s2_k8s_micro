package com.tecsup.app.micro.orden.infrastructure.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecsup.app.micro.orden.application.service.OrderApplicationService;
import com.tecsup.app.micro.orden.domain.model.Order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

  private final OrderApplicationService orderApplicationService;

  /**
   * Obtiene todas las órdenes (solo ADMIN)
   */
  @GetMapping
  public ResponseEntity<List<Order>> getAllOrders() {
    log.info("REST request to get all orders");
    List<Order> orders = orderApplicationService.getAllOrders();
    return ResponseEntity.ok(orders);
  }

  /**
   * Endpoint de salud (público, sin autenticación)
   */
  @GetMapping("/health")
  public ResponseEntity<String> health() {
    return ResponseEntity.ok("Order Service running with Clean Architecture!");
  }
}
