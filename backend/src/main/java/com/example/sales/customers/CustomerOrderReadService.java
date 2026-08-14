package com.example.sales.customers;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CustomerOrderReadService {
  private final JdbcTemplate jdbcTemplate;

  public CustomerOrderReadService(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<CustomerOrderCount> findRecentOrderCounts(List<String> customerIds) {
    if (customerIds.isEmpty()) return List.of();
    String sql = batchOrderCountSql(customerIds.size());
    return jdbcTemplate.query(sql, customerIds.toArray(), (resultSet, rowNum) -> new CustomerOrderCount(
        resultSet.getString("customer_id"), resultSet.getInt("order_count")));
  }

  public static String batchOrderCountSql(int customerCount) {
    if (customerCount <= 0) throw new IllegalArgumentException("customerCount must be positive");
    String placeholders = String.join(", ", java.util.Collections.nCopies(customerCount, "?"));
    return "select customer_id, count(*) as order_count from sales_order where customer_id in (" + placeholders + ") group by customer_id";
  }

  public record CustomerOrderCount(String customerId, int orderCount) {}
}
