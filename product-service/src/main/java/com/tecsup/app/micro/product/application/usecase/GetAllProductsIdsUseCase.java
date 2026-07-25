package com.tecsup.app.micro.product.application.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tecsup.app.micro.product.domain.model.Product;
import com.tecsup.app.micro.product.domain.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetAllProductsIdsUseCase {
  private final ProductRepository productRepository;

  public List<Product> execute(List<Long> ids) {
    return productRepository.findAllById(ids);
  }
}
