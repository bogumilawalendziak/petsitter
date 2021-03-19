package com.milka.users.service;


import com.milka.users.exception.UserError;
import com.milka.users.exception.UserException;
import com.milka.users.model.Status;
import com.milka.users.model.User;
import com.milka.users.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(Status status) {

        if(status!=null){
            return userRepository.findByStatus(status);
        }else return userRepository.findAll();
    }


    public User addUser(User user) {
        validateUserEmail(user);
        return userRepository.save(user);
    }


    public void deleteUser(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserException(UserError.USER_NOT_FOUND));
        userRepository.delete(user); //lub deleteById()
        //mapowanie wyjatków -> response mapper i mapowanie wyjatów na opowiedzi
    }

    public void softDeleteUser(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserException(UserError.USER_NOT_FOUND));
        user.setStatus(Status.INACTIVE);
        userRepository.save(user);
        //mapowanie wyjatków -> response mapper i mapowanie wyjatów na opowiedzi
    }


    public User putUser(long id, User user) {
        return userRepository.findById(id).map(
                dbUser -> {


                    dbUser.setEmail(user.getEmail());
                    dbUser.setName(user.getName());
                    dbUser.setSurname(user.getSurname());
                    dbUser.setStatus(user.getStatus());
                    validateUserEmail(user);
                    return userRepository.save(dbUser);
                }
        ).orElseThrow(() -> new UserException(UserError.USER_NOT_FOUND));
    }

    private void validateUserEmail(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserException(UserError.EMAIL_NOT_UNIQUE);
        }
    }


    public User patchUser(long id, User user) {
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
                    if (!ObjectUtils.isEmpty(user.getStatus())) {
                        dbUser.setStatus(user.getStatus());
                    }
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserException(UserError.USER_NOT_FOUND));

    }

    @Override
    public User getUser(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserException(UserError.USER_NOT_FOUND));
        if(!Status.ACTIVE.equals(user.getStatus())){
            throw new UserException(UserError.USER_IS_NOT_ACTIVE);
        }
        return user;
    }
}
