package com.tecsup.app.micro.orden.domain.model;

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
  private Long order_id;
  private Long product_id;
  private Integer quantity;
  private Double price;
  private Double subtotal;

  public boolean isValid() {
    return order_id != null && order_id > 0
        && product_id != null && product_id > 0
        && quantity != null && quantity > 0
        && price != null && price >= 0
        && subtotal != null && subtotal >= 0;
  }

}