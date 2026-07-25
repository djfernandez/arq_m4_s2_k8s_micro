package com.tecsup.app.micro.orden.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tecsup.app.micro.orden.application.usecase.CreateOrdertUseCase;
import com.tecsup.app.micro.orden.application.usecase.GetAllOrdersUseCase;
import com.tecsup.app.micro.orden.application.usecase.GetMaxOrdersUseCase;
import com.tecsup.app.micro.orden.domain.model.Order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderApplicationService {

  private final CreateOrdertUseCase createOrderUseCase;
  private final GetAllOrdersUseCase getAllOrdersUseCase;
  private final GetMaxOrdersUseCase getMaxOrdersUseCase;

  @Transactional
  public Order createOrder(Order order) {
    return createOrderUseCase.execute(order);
  }

  @Transactional(readOnly = true)
  public List<Order> getAllOrders() {
    return getAllOrdersUseCase.execute();
  }

  @Transactional(readOnly = true)
  public Long getMaxOrderNumber() {
    return getMaxOrdersUseCase.execute();
  }

}
