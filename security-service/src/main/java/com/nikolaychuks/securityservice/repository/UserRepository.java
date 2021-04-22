package com.nikolaychuks.securityservice.repository;

import com.nikolaychuks.securityservice.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
