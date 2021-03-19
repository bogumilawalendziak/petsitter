package com.milka.users.controller;

import com.milka.users.model.Status;
import com.milka.users.model.User;
import com.milka.users.service.UserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public User addUser(@RequestBody @Valid User user) {
        return userService.addUser(user);
    }

    @GetMapping
    @Cacheable(cacheNames = "users")
    public List<User> getUsers(@RequestParam(required = false) Status status) {

        return userService.getUsers(status);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable long id) {
       return userService.getUser(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User user) {

        if(userService.patchUser(id,user)!=null) {
            return ResponseEntity.ok().build();
        }else return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public User changeUser(@PathVariable long id, @RequestBody @Valid User user) {
        return userService.putUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {

        userService.softDeleteUser(id);
    }
}
