package com.nikolaychuks.user.repository;

import com.nikolaychuks.user.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
