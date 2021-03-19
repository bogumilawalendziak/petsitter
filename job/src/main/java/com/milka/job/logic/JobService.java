package com.milka.job;

import java.util.List;

public interface JobService {

    List<Job> getJobs(Status status);
    Job addJob(Job job);
    void deleteJob( long id);
    Job putJob(long id, Job job);
    Job patchJob(long id,Job job);
    Job getJob(long id);
    void softDeleteJob(long id);
}
