package com.tecsup.app.micro.orden.application.usecase;

import org.springframework.stereotype.Component;

import com.tecsup.app.micro.orden.domain.exception.InvalidOrderDataException;
import com.tecsup.app.micro.orden.domain.model.Order;
import com.tecsup.app.micro.orden.domain.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateOrdertUseCase {

  private final OrderRepository orderRepository;

  public Order execute(Order order) {
    // Validar datos de la orden
    if (!order.isValid()) {
      throw new InvalidOrderDataException("Invalid order data. Customer name and valid total amount are required.");
    }

    // Guardar orden
    Order savedOrder = orderRepository.save(order);
    return savedOrder;
  }

}
