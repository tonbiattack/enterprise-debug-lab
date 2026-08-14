package com.example.sales.customers;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CustomerCachePolicy {
  public boolean shouldCache(List<CustomerResponse> customers) {
    return !customers.isEmpty();
  }
}
