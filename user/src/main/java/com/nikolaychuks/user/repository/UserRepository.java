package com.nikolaychuks.user.repository;

import com.nikolaychuks.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
