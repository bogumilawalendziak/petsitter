package com.milka.job;

import com.milka.job.exception.JobError;
import com.milka.job.exception.JobException;
import com.milka.job.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }


    @Override
    public List<Job> getJobs(Status status) {
        if (status != null) {
            return jobRepository.findAllByStatus(status);
        }
        return jobRepository.findAll();
    }

    @Override
    public Job addJob(Job job) {
        validateJob(job);

        return jobRepository.save(job);
    }

    private void validateJob(Job job) {
        if (job.getStatus().equals(Status.DONE)) {
            throw new JobException(JobError.INCORRECT_DATE);
        }
    }

    @Override
    public void deleteJob(long id) {
        if (!jobRepository.existsById(id)){
            throw new JobException(JobError.JOB_NOT_EXIST);
        }
    }

    @Override
    public Job putJob(long id, Job job) {
        return null;
    }

    @Override
    public Job patchJob(long id, Job job) {
        return null;
    }

    @Override
    public Job getJob(long id) {
        return null;
    }

    @Override
    public void softDeleteJob(long id) {

    }
}
