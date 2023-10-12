package com.api.employment.domain.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Member {

    @Id
    private String id;

    @NotNull
    private String password;

    @NotNull
    private String name;

}
