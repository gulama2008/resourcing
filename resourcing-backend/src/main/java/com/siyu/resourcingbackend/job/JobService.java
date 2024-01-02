package com.siyu.resourcingbackend.job;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siyu.resourcingbackend.temp.Temp;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    public List<Job> getAll() {
        return this.jobRepository.findAll();
    }

    public Optional<Job> getById(Long id) {
        return this.jobRepository.findById(id);
    }
}
