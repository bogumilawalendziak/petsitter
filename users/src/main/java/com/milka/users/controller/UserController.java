package com.milka.users.controller;

import com.milka.users.model.User;
import com.milka.users.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public User addUser(@RequestBody @Valid User user) {

        return userRepository.save(user);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<User> getUsers() {

        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        return userRepository.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User user) {

        return userRepository.findById(id).map(
                dbUser ->
                {
                    if (!user.getName().isEmpty()) {
                        dbUser.setName(user.getName());
                    }
                    if (!user.getSurname().isEmpty()) {
                        dbUser.setSurname(user.getSurname());
                    }
                    if (!user.getEmail().isEmpty()) {
                        dbUser.setEmail(user.getEmail());
                    }
                    return ResponseEntity.ok(userRepository.save(user));
                }).orElse(ResponseEntity.notFound().build());

    }

    @PutMapping("/{id}")
    public ResponseEntity<User> changeUser(@PathVariable long id, @RequestBody @Valid User user) {
        return userRepository.findById(id).map(
                dbUser -> {
                    dbUser.setName(user.getName());
                    dbUser.setSurname(user.getSurname());
                    dbUser.setEmail(user.getEmail());
                    return ResponseEntity.ok(userRepository.save(user));
                }).orElseGet(() -> ResponseEntity.notFound().build());


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id) {

        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());

    }
}
