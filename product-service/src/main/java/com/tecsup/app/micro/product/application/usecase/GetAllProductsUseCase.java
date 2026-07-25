package com.tecsup.app.micro.product.application.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tecsup.app.micro.product.domain.model.Product;
import com.tecsup.app.micro.product.domain.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Caso de uso: Obtener todos los productos
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class GetAllProductsUseCase {

    private final ProductRepository productRepository;

    public List<Product> execute() {
        log.debug("Executing GetAllProductsUseCase");
        return productRepository.findAll();
    }
}
