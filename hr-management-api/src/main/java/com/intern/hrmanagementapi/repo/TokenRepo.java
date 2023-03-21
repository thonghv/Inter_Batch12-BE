package com.intern.hrmanagementapi.repo;

import com.intern.hrmanagementapi.entity.TokenEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepo extends JpaRepository<TokenEntity, Integer> {

  @Query(value = """
      select t from TokenEntity t inner join UserEntity u\s
      on t.user.id  = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)
//  @Query(value = """
//      select t from TokenEntity t inner join UserEntity u\s
//      on t.user.id = u.id\s
//      where CAST(u.id as org.hibernate.type.UUIDCharType) = :id and (t.expired = false or t.revoked = false)\s
//      """)
  List<TokenEntity> findAllValidTokenByUserId(UUID id);

  Optional<TokenEntity> findByToken(String token);

}
