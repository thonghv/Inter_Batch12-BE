package com.intern.hrmanagementapi.service;

import com.intern.hrmanagementapi.constant.JwtConst;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * The type Jwt service.
 */
@Service
public class JwtService {


  /**
   * Extract username from token
   *
   * @param token the token
   * @return the username
   */
  public String extractUsername(String token) {
    //    System.out.println(extractClaim(token, Claims::getSubject));
    return extractClaim(token, Claims::getSubject);
  }

  /**
   * Extract claim t.
   *
   * @param <T>            the type parameter
   * @param token          the token
   * @param claimsResolver the claims resolver
   * @return the t
   */
  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  /**
   * Generate token string.
   *
   * @param userDetails the user details
   * @return the string
   */
  public String generateToken(UserDetails userDetails) {
    return generateToken(new HashMap<>(), userDetails);
  }

  /**
   * Generate token string.
   *
   * @param extraClaims the extra claims
   * @param userDetails the user details
   * @return the string
   */
  public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
    return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + JwtConst.JWT_EXPIRE_TIME))
        .signWith(SignatureAlgorithm.HS256, getSignInKey()).compact();
  }

  /**
   * Check token is valid or not
   *
   * @param token       the token
   * @param userDetails the user details
   * @return the boolean
   */
  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String userEmail = extractUsername(token);
    return (userEmail.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser().setSigningKey(getSignInKey()).parseClaimsJws(token).getBody();
  }

  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(JwtConst.JWT_SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
