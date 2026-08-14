package com.example.sales.customers;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExternalCustomerClient {
  private final RestTemplate restTemplate;
  private final String externalApiUrl;

  public ExternalCustomerClient(RestTemplate restTemplate, @Value("${external.api.url}") String externalApiUrl) {
    this.restTemplate = restTemplate;
    this.externalApiUrl = externalApiUrl;
  }

  public List<CustomerResponse> search(String keyword, String requestId) {
    HttpHeaders headers = new HttpHeaders();
    headers.add("X-Request-Id", requestId);
    ResponseEntity<CustomerResponse[]> response = restTemplate.exchange(
        externalApiUrl + "/external/customers?keyword=" + keyword,
        HttpMethod.GET,
        new HttpEntity<>(headers),
        CustomerResponse[].class);
    CustomerResponse[] body = response.getBody();
    return body == null ? List.of() : Arrays.asList(body);
  }
}
