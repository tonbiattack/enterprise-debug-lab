package com.example.sales.inventory;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
public class Inventory {
  @Id
  private String productId;
  private int availableQuantity;
  @Version
  private Long version;

  protected Inventory() {}

  public Inventory(String productId, int availableQuantity) {
    this.productId = productId;
    this.availableQuantity = availableQuantity;
  }

  public void reserve(int quantity) {
    if (quantity <= 0 || availableQuantity < quantity) throw new IllegalArgumentException("Insufficient inventory");
    availableQuantity -= quantity;
  }

  public String getProductId() { return productId; }
  public int getAvailableQuantity() { return availableQuantity; }
  public Long getVersion() { return version; }
}
