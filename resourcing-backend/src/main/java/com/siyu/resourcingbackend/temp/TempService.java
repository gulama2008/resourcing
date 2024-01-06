package com.siyu.resourcingbackend.temp;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TempService {

    @Autowired
    private TempRepository tempRepository;

    public List<Temp> getAll() {
        return this.tempRepository.findAll();
    }

    public Optional<Temp> getById(Long id) {
        return this.tempRepository.findById(id);
    }

    public Temp createTemp(TempCreateDTO data) {
        String firstName = data.getFirstName();
        String lastName = data.getLastName();
        Temp newTemp = new Temp(firstName, lastName);
        Temp created = this.tempRepository.save(newTemp);
        return created;
    }
}
