package com.example.sales.orders;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/orders")
public class OrderController {
  private final OrderService orderService;
  public OrderController(OrderService orderService) { this.orderService = orderService; }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Map<String, String> create(@RequestBody OrderRequest request) {
    orderService.place(request);
    return Map.of("status", "reserved");
  }
}
