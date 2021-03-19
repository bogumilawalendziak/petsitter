package com.milka.job.repository;

import com.milka.job.model.Job;
import com.milka.job.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job,Long> {

   List<Job> findAllByCity(String city);
    List<Job> findAllByStatus(Status status);

}
