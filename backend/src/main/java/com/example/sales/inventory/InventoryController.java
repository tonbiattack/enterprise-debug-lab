package com.example.sales.inventory;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal")
public class InventoryController {
  private final InventoryRepository inventoryRepository;

  public InventoryController(InventoryRepository inventoryRepository) { this.inventoryRepository = inventoryRepository; }

  @GetMapping("/products")
  public List<Map<String, Object>> products(@RequestParam String customerId) {
    return inventoryRepository.findAll().stream().map(item -> Map.<String, Object>of(
        "productId", item.getProductId(), "productName", "Standard Product", "availableQuantity", item.getAvailableQuantity())).toList();
  }
}
