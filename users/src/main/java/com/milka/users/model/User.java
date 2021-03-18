package com.milka.users.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    @NotNull(message = "Pole imię nie może być puste")
    String name;
    @NotNull
    String surname;
    @NotNull
    String email;


}
