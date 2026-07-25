package com.tecsup.app.micro.orden.application.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResumenDTO {
  private Long id;
  private String name;
  private BigDecimal price;

}
