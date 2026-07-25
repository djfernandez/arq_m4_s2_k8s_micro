package com.tecsup.app.micro.orden.infrastructure.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.tecsup.app.micro.orden.domain.model.Order;
import com.tecsup.app.micro.orden.infrastructure.web.dto.CreateOrderRequest;

@Mapper(componentModel = "spring")
public interface OrderDtoMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "orderNumber", ignore = true)
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "totalAmount", ignore = true)
  Order toDomain(CreateOrderRequest createOrderRequest);

}
