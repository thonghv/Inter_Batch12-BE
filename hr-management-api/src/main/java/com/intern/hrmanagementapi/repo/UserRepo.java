package com.intern.hrmanagementapi.repo;

import com.intern.hrmanagementapi.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, String> {

}
