package com.tecsup.app.micro.orden.domain.repository;

import java.util.List;
import java.util.Optional;

import com.tecsup.app.micro.orden.domain.model.Order;

public interface OrderRepository {

  /**
   * Obtiene todas las órdenes
   */
  List<Order> findAll();

  /**
   * Busca una orden por ID
   */
  Optional<Order> findById(Long id);

  /**
   * Busca órdenes por el ID del usuario
   */
  List<Order> findByUserId(Long userId);

  /**
   * Busca órdenes disponibles (stock > 0)
   */
  List<Order> findAvailableOrders();

  /**
   * Guarda una nueva orden o actualiza una existente
   */
  Order save(Order order);

  /**
   * Elimina una orden por ID
   */
  void deleteById(Long id);

  /**
   * Verifica si existe una orden con el ID dado
   */
  boolean existsById(Long id);

}
