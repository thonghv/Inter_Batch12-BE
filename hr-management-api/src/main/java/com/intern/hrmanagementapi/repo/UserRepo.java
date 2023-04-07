package com.intern.hrmanagementapi.repo;

import com.intern.hrmanagementapi.entity.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, UUID> {
  Optional<UserEntity> findByEmail(String email);
  Optional<UserEntity> findByUsername(String username);
  @Query(value = "SELECT * FROM users WHERE username LIKE %:username%",nativeQuery = true)
  List<UserEntity> findByUsernameContaining(@Param("username") String username, Pageable pageable);
  @Query(value = "SELECT * FROM users WHERE state = :state", nativeQuery = true)
  List<UserEntity> findByActive(@Param("state") String state);
}
