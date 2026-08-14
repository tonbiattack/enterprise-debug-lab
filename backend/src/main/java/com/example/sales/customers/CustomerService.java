package com.example.sales.customers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Duration;
import java.util.List;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
  private final StringRedisTemplate redis;
  private final ObjectMapper objectMapper;
  private final ExternalCustomerClient externalCustomerClient;
  private final CustomerCachePolicy cachePolicy;

  public CustomerService(StringRedisTemplate redis, ObjectMapper objectMapper, ExternalCustomerClient externalCustomerClient, CustomerCachePolicy cachePolicy) {
    this.redis = redis;
    this.objectMapper = objectMapper;
    this.externalCustomerClient = externalCustomerClient;
    this.cachePolicy = cachePolicy;
  }

  public List<CustomerResponse> search(String keyword, String requestId) {
    String key = "customer-search:" + keyword.toLowerCase();
    String cached = redis.opsForValue().get(key);
    if (cached != null) return decode(cached);
    List<CustomerResponse> customers = externalCustomerClient.search(keyword, requestId);
    if (cachePolicy.shouldCache(customers)) redis.opsForValue().set(key, encode(customers), Duration.ofMinutes(5));
    return customers;
  }

  private List<CustomerResponse> decode(String json) {
    try {
      JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, CustomerResponse.class);
      return objectMapper.readValue(json, type);
    } catch (JsonProcessingException exception) {
      throw new IllegalStateException("Cached customer payload cannot be decoded", exception);
    }
  }

  private String encode(List<CustomerResponse> customers) {
    try {
      return objectMapper.writeValueAsString(customers);
    } catch (JsonProcessingException exception) {
      throw new IllegalStateException("Customer payload cannot be encoded", exception);
    }
  }
}
