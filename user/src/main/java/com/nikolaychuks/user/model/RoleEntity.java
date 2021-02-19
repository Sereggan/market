package com.nikolaychuks.user.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "role_table")
@Data
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Role role;
}
