package com.example.sales.customers;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal")
public class CustomerController {
  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping("/customers")
  public Map<String, List<CustomerResponse>> search(@RequestParam String keyword, @RequestHeader("X-Request-Id") String requestId) {
    return Map.of("items", customerService.search(keyword, requestId));
  }

  @GetMapping("/customers/{id}")
  public Map<String, Object> detail(@PathVariable String id) {
    return Map.of("id", id, "displayName", "Acme Corporation", "recentOrderCount", 2);
  }
}
