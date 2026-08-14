package com.example.sales.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RequestIdFilter implements Filter {
  private static final Logger log = LoggerFactory.getLogger(RequestIdFilter.class);

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    String requestId = httpRequest.getHeader("X-Request-Id");
    if (requestId == null || requestId.isBlank()) requestId = UUID.randomUUID().toString();
    long startedAt = System.nanoTime();
    httpResponse.setHeader("X-Request-Id", requestId);
    try {
      chain.doFilter(request, response);
    } finally {
      long durationMs = (System.nanoTime() - startedAt) / 1_000_000;
      log.info("timestamp={} requestId={} service=backend method={} endpoint={} status={} duration={}", Instant.now(), requestId, httpRequest.getMethod(), httpRequest.getRequestURI(), httpResponse.getStatus(), durationMs);
    }
  }
}
