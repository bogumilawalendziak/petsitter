package com.milka.job.logic;

import com.milka.job.model.Status;
import com.milka.job.model.Job;
import com.milka.job.model.WriteJob;

import java.util.List;

public interface JobService {

    List<Job> getJobs(Status status);
    Job getJob(long id);
    Job addJob(WriteJob job);
    void deleteJob( long id);
    Job putJob(long id, WriteJob job);
    Job patchJob(long id,WriteJob job);
    void softDeleteJob(long id);
    void takeJob(long petsitterId, long employerId);
    void acceptPetsitter(long petsitterId, long jobId);
}
