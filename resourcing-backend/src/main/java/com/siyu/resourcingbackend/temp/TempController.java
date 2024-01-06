package com.siyu.resourcingbackend.temp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siyu.resourcingbackend.exceptions.NotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/temps")
public class TempController {
    
    @Autowired
    private TempService tempService;

    @GetMapping
    public ResponseEntity<List<Temp>> getAll() {
        List<Temp> temps = this.tempService.getAll();
        return new ResponseEntity<>(temps, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Temp> getById(@PathVariable Long id) {
        Optional<Temp> found = this.tempService.getById(id);
        if (found.isPresent()) {
            return new ResponseEntity<Temp>(found.get(), HttpStatus.OK);
        }
        throw new NotFoundException(String.format("Temp with id: %d does not exist", id));
    }

    @PostMapping
    public ResponseEntity<Temp> createTemp(@Valid @RequestBody TempCreateDTO data) {
        Temp newTemp = this.tempService.createTemp(data);
        return new ResponseEntity<>(newTemp, HttpStatus.CREATED);
    }
}
