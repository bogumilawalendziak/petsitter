package com.milka.users.service;

import com.milka.users.model.Status;
import com.milka.users.model.User;

import java.util.List;

public interface UserService {

    List<User> getUsers(Status status);
    User addUser(User user);
    void deleteUser( long id);
    User putUser(long id, User user);
    User patchUser(long id,User user);
    User getUser(long id);
    void softDeleteUser(long id);
}
