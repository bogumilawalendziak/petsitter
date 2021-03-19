package com.milka.job;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job,Long> {

   List<Job> findAllByCity(String city);
    List<Job> findAllByStatus(Status status);

}
