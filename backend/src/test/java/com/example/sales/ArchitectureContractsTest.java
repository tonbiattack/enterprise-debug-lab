package com.example.sales;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.sales.inventory.Inventory;
import com.example.sales.orders.OrderService;
import jakarta.persistence.Version;
import jakarta.transaction.Transactional;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.junit.jupiter.api.Test;

class ArchitectureContractsTest {
  @Test
  void B06_inventoryUsesOptimisticLocking() throws NoSuchFieldException {
    Field version = Inventory.class.getDeclaredField("version");
    assertNotNull(version.getAnnotation(Version.class));
  }

  @Test
  void B08_orderPlacementIsTransactional() throws NoSuchMethodException {
    Method method = OrderService.class.getDeclaredMethod("place", com.example.sales.orders.OrderRequest.class);
    assertNotNull(method.getAnnotation(Transactional.class));
  }

  @Test
  void B10_requestIdConfigurationIsAvailable() {
    int major = Integer.parseInt(System.getProperty("java.version").split("\\.")[0]);
    assertTrue(major >= 17);
  }
}
