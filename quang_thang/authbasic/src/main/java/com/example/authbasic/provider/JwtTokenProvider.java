package com.example.authbasic.provider;

import com.example.authbasic.config.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

  private final String JWT_SECRET = "lodaaaaaa";

  // Thời gian có hiệu lực của chuỗi jwt
  private final long JWT_EXPIRATION = 604800000L;

  // Tạo ra jwt từ thông tin user
  public String generateToken(CustomUserDetails userDetails) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
    // Tạo chuỗi json web token từ id của user.
    return Jwts.builder()
        .setSubject(userDetails.getUser().getId())
        .setIssuedAt(now)
        .setExpiration(expiryDate)
        .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
        .compact();
  }

  // Lấy thông tin user từ jwt
  public String getUserIdFromJWT(String token) {
    Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();

    return claims.getSubject();
  }

  public boolean validateToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
      return true;
    } catch (MalformedJwtException ex) {
      System.out.print("Invalid JWT token");
    } catch (ExpiredJwtException ex) {
      System.out.print("Expired JWT token");
    } catch (UnsupportedJwtException ex) {
      System.out.print("Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
      System.out.print("JWT claims string is empty.");
    }
    return false;
  }
}
