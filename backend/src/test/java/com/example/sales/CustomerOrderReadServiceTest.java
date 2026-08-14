package com.example.sales;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.sales.customers.CustomerOrderReadService;
import org.junit.jupiter.api.Test;

class CustomerOrderReadServiceTest {
  @Test
  void B07_batchesCustomerOrderCountsIntoOneParameterizedQuery() {
    assertEquals(
        "select customer_id, count(*) as order_count from sales_order where customer_id in (?, ?, ?) group by customer_id",
        CustomerOrderReadService.batchOrderCountSql(3));
  }

  @Test
  void B07_rejectsEmptyBatchConstruction() {
    assertThrows(IllegalArgumentException.class, () -> CustomerOrderReadService.batchOrderCountSql(0));
  }
}
