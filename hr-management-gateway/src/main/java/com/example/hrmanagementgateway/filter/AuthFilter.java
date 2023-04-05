package com.example.hrmanagementgateway.filter;

import com.example.hrmanagementgateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

  @Autowired private RouteValidator validator;

  //    @Autowired
  //    private RestTemplate template;
  @Autowired private JwtUtil jwtUtil;

  public AuthFilter() {
    super(Config.class);
  }

  @Override
  public GatewayFilter apply(Config config) {
    return ((exchange, chain) -> {
      if (validator.isSecured.test(exchange.getRequest())) {
        // header contains token or not
        if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
          throw new RuntimeException("Missing authorization header");
        }

        String authHeader =
            exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
          authHeader = authHeader.substring(7);
        }
        jwtUtil.validateToken(authHeader);
      }
      return chain.filter(exchange);
    });
  }

  public static class Config {}
}
