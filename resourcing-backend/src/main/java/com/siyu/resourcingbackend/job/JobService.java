package com.siyu.resourcingbackend.job;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siyu.resourcingbackend.temp.Temp;
import com.siyu.resourcingbackend.temp.TempService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class JobService {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private TempService tempService;

    public List<Job> getAll() {
        return this.jobRepository.findAll();
    }

    public Optional<Job> getById(Long id) {
        return this.jobRepository.findById(id);
    }

    public Job createJob(JobCreateDTO data) {
        String name = data.getName();
        String startDate = data.getStartDate();
        String endDate = data.getEndDate();
        System.out.println(name+startDate+endDate);
        if (data.getTempId() != null) {
            Long tempId = data.getTempId();
            Optional<Temp> temp = this.tempService.getById(tempId);
            if (temp.isPresent()) {
                Job newJob = new Job(name, startDate, endDate, temp.get());
                return this.jobRepository.save(newJob);
            }
            return null;
        }
        Job newJob = new Job(name, startDate, endDate);
        Job created = this.jobRepository.save(newJob);
        return created;
    }

    public Optional<Job> updateById(Long id, JobUpdateDTO data) {
        Optional<Job> foundJob = this.getById(id);
        if (foundJob.isPresent()) {
            Job toUpdate = foundJob.get();
            Long tempId = data.getTempId();
            Optional<Temp> temp = this.tempService.getById(tempId);
            if (temp.isPresent()) {
                toUpdate.setTemp(temp.get());
                return Optional.of(this.jobRepository.save(toUpdate));
            }
            return null;
        }
	    return foundJob;
    }
}
