package com.intern.hrmanagementapi.repo;

import com.intern.hrmanagementapi.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, String> {


  Optional<User> findByEmail(String email);
}
