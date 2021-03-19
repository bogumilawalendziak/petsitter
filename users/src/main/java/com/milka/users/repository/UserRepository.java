package com.milka.users.repository;

import com.milka.users.model.Status;
import com.milka.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    void deleteById(Long aLong);
    boolean existsByEmail(String email);
    List<User> findByStatus(Status status);
}
