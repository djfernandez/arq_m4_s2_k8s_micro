package com.tecsup.app.micro.orden.domain.model;

import java.math.BigDecimal;

import com.tecsup.app.micro.orden.application.dto.ProductResumenDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

  private Long id;
  private Long orderId;
  private Long productId;
  private Integer quantity;
  private BigDecimal unitPrice;
  private BigDecimal subtotal;

  private ProductResumenDTO product;

  public boolean isValid() {
    return productId != null && productId > 0
        && quantity != null && quantity > 0;
  }

}