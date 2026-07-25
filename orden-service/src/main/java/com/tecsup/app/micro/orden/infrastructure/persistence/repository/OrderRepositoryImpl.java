package com.tecsup.app.micro.orden.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.tecsup.app.micro.orden.domain.model.Order;
import com.tecsup.app.micro.orden.domain.repository.OrderRepository;
import com.tecsup.app.micro.orden.infrastructure.persistence.entity.OrderEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class OrderRepositoryImpl implements OrderRepository {

  private final JpaOrderRepository jpaOrderRepository;

  @Override
  public List<Order> findAll() {
    log.debug("Finding all orders");
    return jpaOrderRepository.findAll()
        .stream()
        .map(this::toDomain)
        .collect(Collectors.toList());
  }

  @Override
  public Optional<Order> findById(Long id) {
    log.debug("Finding order by id: {}", id);
    return jpaOrderRepository.findById(id)
        .map(this::toDomain);
  }

  @Override
  public List<Order> findByUserId(Long userId) {
    log.debug("Finding orders by user id: {}", userId);
    return jpaOrderRepository.findByUserId(userId)
        .stream()
        .map(this::toDomain)
        .collect(Collectors.toList());
  }

  @Override
  public List<Order> findAvailableOrders() {
    log.debug("Finding available orders");
    return jpaOrderRepository.findByStatus("AVAILABLE")
        .stream()
        .map(this::toDomain)
        .collect(Collectors.toList());
  }

  @Override
  public Order save(Order order) {
    log.debug("Saving order: {}", order);
    OrderEntity entity = toEntity(order);
    OrderEntity savedEntity = jpaOrderRepository.save(entity);
    return toDomain(savedEntity);
  }

  @Override
  public void deleteById(Long id) {
    log.debug("Deleting order by id: {}", id);
    jpaOrderRepository.deleteById(id);
  }

  @Override
  public boolean existsById(Long id) {
    log.debug("Checking if order exists by id: {}", id);
    return jpaOrderRepository.existsById(id);
  }
  // Implementación del repositorio de Orden (Adaptador)
  // Conecta el dominio con la infraestructura de persistencia

  // Mappers

  private Order toDomain(OrderEntity entity) {
    return Order.builder()
        .id(entity.getId())
        .user_id(entity.getUserId())
        .status(entity.getStatus())
        .total_amount(entity.getTotalAmount())
        .created_at(entity.getCreatedAt())
        .updated_at(entity.getUpdatedAt())
        .build();
  }

  private OrderEntity toEntity(Order order) {
    return OrderEntity.builder()
        .id(order.getId())
        .userId(order.getUser_id())
        .status(order.getStatus())
        .totalAmount(order.getTotal_amount())
        .createdAt(order.getCreated_at())
        .updatedAt(order.getUpdated_at())
        .build();
  }
}
