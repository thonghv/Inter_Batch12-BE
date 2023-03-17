package com.example.authbasic.config;

import com.example.authbasic.entity.User;
import com.example.authbasic.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
// @RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired private UserRepo userRepo;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepo.findByUsername(username);
    if (user == null) throw new UsernameNotFoundException(username);
    return new CustomUserDetails(user);
  }
}
