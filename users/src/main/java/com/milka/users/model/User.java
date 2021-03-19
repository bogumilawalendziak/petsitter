package com.milka.users.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @NotNull(message = "Name should be not null")
    String name;
    @NotNull
    String surname;
    @NotNull
    @Email
    @Column(unique = true)
    String email;
    @NotNull
    Status status;


}
