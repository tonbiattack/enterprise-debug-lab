package com.example.sales.orders;

import com.example.sales.inventory.Inventory;
import com.example.sales.inventory.InventoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
  private final InventoryRepository inventoryRepository;

  public OrderService(InventoryRepository inventoryRepository) { this.inventoryRepository = inventoryRepository; }

  public void place(OrderRequest request) {
    Inventory inventory = inventoryRepository.findById(request.productId()).orElseThrow(() -> new IllegalArgumentException("Product not found"));
    inventory.reserve(request.quantity());
    inventoryRepository.save(inventory);
  }
}
