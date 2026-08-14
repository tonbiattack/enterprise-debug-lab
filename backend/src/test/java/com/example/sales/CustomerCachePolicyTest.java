package com.example.sales;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.sales.customers.CustomerCachePolicy;
import com.example.sales.customers.CustomerResponse;
import java.util.List;
import org.junit.jupiter.api.Test;

class CustomerCachePolicyTest {
  private final CustomerCachePolicy policy = new CustomerCachePolicy();

  @Test
  void B02_doesNotPersistAnEmptyTransientSearchResult() {
    assertFalse(policy.shouldCache(List.of()));
    assertTrue(policy.shouldCache(List.of(new CustomerResponse("c-1", "Acme", "GOLD"))));
  }
}
