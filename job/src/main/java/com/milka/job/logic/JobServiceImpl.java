package com.milka.job.logic;

import com.milka.job.exception.JobError;
import com.milka.job.exception.JobException;
import com.milka.job.model.Job;
import com.milka.job.model.Status;
import com.milka.job.model.WriteJob;
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
    public Job getJob(long id) {
        return jobRepository.findById(id).orElseThrow(() -> new JobException(JobError.JOB_NOT_EXIST));
    }

    @Override
    public Job addJob(WriteJob toSave) {
        Job job = new Job();
        job.setCity(toSave.getCity());
        job.setPrice(toSave.getPrice());
        job.setStartDate(toSave.getStartDate());
        job.setEndDate(toSave.getEndDate());

        return jobRepository.save(job);
    }


    @Override
    public void deleteJob(long id) {
        if (!jobRepository.existsById(id)) {
            throw new JobException(JobError.JOB_NOT_EXIST);
        }
    }

    @Override

    public Job putJob(long id, WriteJob toSave) {
        if(!jobRepository.existsById(id)) {
            throw new JobException(JobError.JOB_NOT_EXIST);
        }
        Job job = new Job();
        job.setCity(toSave.getCity());
        job.setPrice(toSave.getPrice());
        job.setStartDate(toSave.getStartDate());
        job.setEndDate(toSave.getEndDate());
        return job;
    }

    @Override
    public Job patchJob(long id, WriteJob job) {
        return null;
    }

    @Override
    public void softDeleteJob(long id) {

    }

    @Override //when petsitter wants to take job
    public void takeJob(long petsitterId, long employerId) {
      // TODO : send notification to employer that there is a one person that want job
        // employer cant be petsitter his own job
    }

    @Override // when employer accept petsitter
    public void acceptPetsitter(long petsitterId, long jobId) {
        // TODO : add petsitter to job,
        //  send additional information about job
        //  change job status
        // after job , when job is done, you can set rate for petsitter
    }
}
