package com.example.hrmanagementgateway.util;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.security.Key;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

  public static final String SECRET =
      "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

  public void validateToken(final String token) {
    try {
      Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);

    } catch (SignatureException ex) {
      throw new RuntimeException("Invalid JWT signature");
    } catch (MalformedJwtException ex) {
      throw new RuntimeException("Invalid JWT token");
    } catch (ExpiredJwtException ex) {
      throw new RuntimeException("Expired JWT token");
    } catch (UnsupportedJwtException ex) {
      throw new RuntimeException("Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
      throw new RuntimeException("JWT claims string is empty.");
    }
  }

  private Key getSignKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
