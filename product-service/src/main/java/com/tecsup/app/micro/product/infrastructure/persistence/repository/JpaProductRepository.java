package com.tecsup.app.micro.product.infrastructure.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tecsup.app.micro.product.infrastructure.persistence.entity.ProductEntity;

/**
 * Repositorio JPA de Producto
 * Interface de Spring Data JPA para operaciones de persistencia
 */
public interface JpaProductRepository extends CrudRepository<ProductEntity, Long> {

    List<ProductEntity> findByCategory(String category);

    List<ProductEntity> findByCreatedBy(Long userId);

    @Query("SELECT p FROM ProductEntity p WHERE p.stock > 0")
    List<ProductEntity> findAvailableProducts();

    List<ProductEntity> findByIdIn(List<Long> ids);

}
