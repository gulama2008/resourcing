package com.siyu.resourcingbackend.job;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siyu.resourcingbackend.exceptions.NotFoundException;
import com.siyu.resourcingbackend.temp.Temp;

@RestController
@RequestMapping
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping
    public ResponseEntity<List<Job>> getAll() {
        List<Job> jobs = this.jobService.getAll();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getById(@PathVariable Long id) {
        Optional<Job> found = this.jobService.getById(id);
        if (found.isPresent()) {
            return new ResponseEntity<Job>(found.get(), HttpStatus.OK);
        }
        throw new NotFoundException(String.format("Job with id: %d does not exist", id));
    }
    
}
