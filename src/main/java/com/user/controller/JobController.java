package com.user.controller;

import com.user.entities.Job;
import com.user.repositories.JobRepository;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobRepository jobRepository;

    @Autowired
    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    // CRUD operations using built-in methods

    @PostMapping
    public ResponseEntity<Job> createJob(@Valid @RequestBody Job job) {
        Job savedJob = jobRepository.save(job);
        return new ResponseEntity<>(savedJob, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        return jobRepository.findById(id)
                .map(job -> new ResponseEntity<>(job, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @Valid @RequestBody Job job) {
        if (!jobRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        job.setId(id);
        Job updatedJob = jobRepository.save(job);
        return new ResponseEntity<>(updatedJob, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        jobRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Custom finder methods

    @GetMapping("/search/title/{title}")
    public ResponseEntity<List<Job>> searchByTitle(@PathVariable String title) {
        List<Job> jobs = jobRepository.findByTitleContainingIgnoreCase(title);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @GetMapping("/search/description/{description}")
    public ResponseEntity<List<Job>> searchByDescription(@PathVariable String description) {
        List<Job> jobs = jobRepository.findByDescriptionContainingIgnoreCase(description);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    // Custom update and delete methods using @Query

    @PutMapping("/customUpdate/{id}")
    public ResponseEntity<Void> customUpdateJob(@PathVariable Long id, @Valid @RequestBody Job job) {
        int updatedRows = jobRepository.updateJob(id, job.getTitle(), job.getDescription());
        return updatedRows > 0
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/customDelete/{id}")
    public ResponseEntity<Void> customDeleteJob(@PathVariable Long id) {
        int deletedRows = jobRepository.deleteJob(id);
        return deletedRows > 0
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
