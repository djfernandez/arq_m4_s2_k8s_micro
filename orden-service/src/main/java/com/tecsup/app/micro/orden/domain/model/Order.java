package com.tecsup.app.micro.orden.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

  private Long id;
  private Long order_number;
  private Long user_id;
  private String status;
  private BigDecimal total_amount;
  private LocalDateTime created_at;
  private LocalDateTime updated_at;
  private List<OrderItem> items;

  public boolean isValid() {
    return order_number != null && order_number > 0
        && user_id != null && user_id > 0
        && status != null && !status.trim().isEmpty()
        && total_amount != null && total_amount.compareTo(BigDecimal.ZERO) >= 0
        && created_at != null
        && updated_at != null
        && items != null && !items.isEmpty();
  }

}
