package com.milka.job.controller;

import com.milka.job.model.Job;
import com.milka.job.logic.JobServiceImpl;
import com.milka.job.model.Status;
import com.milka.job.model.WriteJob;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobServiceImpl jobService;

    public JobController(JobServiceImpl jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public List<Job> getJobs(@RequestParam Status status){
        return jobService.getJobs(status);
    }

    @GetMapping("/{id}")
    public Job getJobById(@PathVariable long id){
        return jobService.getJob(id);
    }

    @PostMapping
    public Job addJob(@RequestBody WriteJob toSave){
       return jobService.addJob(toSave);
    }

    public void takeJob(@RequestParam long petsitterId, long employerId){
        jobService.takeJob(petsitterId,employerId);
    }

}
