package com.tecsup.app.micro.orden.application.usecase;

import org.springframework.stereotype.Component;

import com.tecsup.app.micro.orden.domain.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetMaxOrdersUseCase {

  private final OrderRepository orderRepository;

  public Long execute() {
    log.debug("Executing GetMaxOrdersUseCase");
    return orderRepository.maxOrderNumber();
  }
}
