package com.tecsup.app.micro.product.infrastructure.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tecsup.app.micro.product.application.service.ProductApplicationService;
import com.tecsup.app.micro.product.domain.model.Product;
import com.tecsup.app.micro.product.infrastructure.web.dto.CreateProductRequest;
import com.tecsup.app.micro.product.infrastructure.web.dto.ProductResponse;
import com.tecsup.app.micro.product.infrastructure.web.dto.UpdateProductRequest;
import com.tecsup.app.micro.product.infrastructure.web.mapper.ProductDtoMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Controlador REST de Productos
 * MODIFICADO en Módulo 4 - Sesión 1: Se agregan anotaciones @PreAuthorize
 *
 * Reglas de acceso:
 * GET /api/products → público
 * GET /api/products/available → público
 * GET /api/products/{id} → público
 * GET /api/products/user/{uid} → autenticado
 * POST /api/products → ADMIN
 * PUT /api/products/{id} → ADMIN
 * DELETE /api/products/{id} → ADMIN
 * GET /api/products/health → público
 */
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductApplicationService productApplicationService;
    private final ProductDtoMapper productDtoMapper;

    /**
     * Obtiene todos los productos (público)
     */
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        log.info("REST request to get all products");
        List<Product> products = productApplicationService.getAllProducts();
        return ResponseEntity.ok(productDtoMapper.toResponseList(products));
    }

    /**
     * Obtiene productos disponibles (stock > 0) (público)
     */
    @GetMapping("/available")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ProductResponse>> getAvailableProducts() {
        log.info("REST request to get available products");
        List<Product> products = productApplicationService.getAvailableProducts();
        return ResponseEntity.ok(productDtoMapper.toResponseList(products));
    }

    /**
     * Obtiene un producto por ID (público)
     */
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        log.info("REST request to get product by id: {}", id);
        Product product = productApplicationService.getProductById(id);
        return ResponseEntity.ok(productDtoMapper.toResponse(product));
    }

    /**
     * Obtiene un producto por ID (público)
     */
    @GetMapping("/ids")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ProductResponse>> getProductsByIdsEntity(@RequestParam List<Long> ids) {
        log.info("REST request to get products by ids: {}", ids);
        List<Product> products = productApplicationService.getProductsByIds(ids);
        return ResponseEntity.ok(productDtoMapper.toResponseList(products));
    }

    /**
     * Obtiene productos por usuario creador (autenticado)
     */
    @GetMapping("/user/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ProductResponse>> getProductsByUser(@PathVariable Long userId) {
        log.info("REST request to get products by user: {}", userId);
        List<Product> products = productApplicationService.getProductsByUser(userId);
        return ResponseEntity.ok(productDtoMapper.toResponseList(products));
    }

    /**
     * Crea un nuevo producto (solo ADMIN)
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody CreateProductRequest request) {
        log.info("REST request to create product: {}", request.getName());
        Product product = productDtoMapper.toDomain(request);
        Product createdProduct = productApplicationService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productDtoMapper.toResponse(createdProduct));
    }

    /**
     * Actualiza un producto existente (solo ADMIN)
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductRequest request) {
        log.info("REST request to update product with id: {}", id);
        Product product = productDtoMapper.toDomain(request);
        Product updatedProduct = productApplicationService.updateProduct(id, product);
        return ResponseEntity.ok(productDtoMapper.toResponse(updatedProduct));
    }

    /**
     * Elimina un producto (solo ADMIN)
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.info("REST request to delete product with id: {}", id);
        productApplicationService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint de salud (público)
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Product Service running with Clean Architecture!");
    }
}