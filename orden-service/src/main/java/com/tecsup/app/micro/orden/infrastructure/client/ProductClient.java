package com.tecsup.app.micro.orden.infrastructure.client;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.tecsup.app.micro.orden.infrastructure.client.dto.ProductDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductClient {

  private final RestTemplate restTemplate;

  @Value("${product.service.url}")
  private String productServiceUrl;

  public List<ProductDTO> getProductById(List<Long> productId, String token) {
    log.info("Calling Product Service to get product with id: {}", productId);

    String url = this.productServiceUrl + "/api/products/ids?ids=" + productId.stream()
        .map(String::valueOf)
        .collect(Collectors.joining(","));

    try {
      HttpHeaders headers = new HttpHeaders();
      headers.set("Authorization", token);
      HttpEntity<String> entity = new HttpEntity<>(headers);

      ProductDTO[] productsArray = restTemplate.exchange(
          url,
          HttpMethod.GET,
          entity,
          ProductDTO[].class).getBody();
      List<ProductDTO> products = List.of(productsArray);
      log.info("Products retrieved successfully: {}", (Object) products);
      return products;
    } catch (Exception e) {
      log.error("Error calling Product Service: {}", e.getMessage());
      throw new RuntimeException("Error calling Product Service: " + e.getMessage());
    }
  }
}
