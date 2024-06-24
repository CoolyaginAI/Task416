package com.example.Task416.controller;

import com.example.Task416.dto.Person;
import com.example.Task416.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/person")
    public Iterable<Person> getPerson() {
        return personRepository.findAll();
    }

    @GetMapping("/person/{id}")
    public Optional<Person> findPersonById(@PathVariable int id) {
        return personRepository.findById(id);
    }

    @PostMapping("/person")
    public Person addPerson(@RequestBody Person person) {
        personRepository.save(person);
        return person;
    }

    @PutMapping("/person/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
        HttpStatus status = personRepository.existsById(id) ? HttpStatus.OK : HttpStatus.CREATED;

        personRepository.findById(id).get().setFirstname(person.getFirstname());
        personRepository.findById(id).get().setSurname(person.getSurname());
        personRepository.findById(id).get().setLastname(person.getLastname());
        personRepository.findById(id).get().setBirthday(person.getBirthday());

        return  new ResponseEntity(personRepository.save(personRepository.findById(id).get()), status);
    }

    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable int id) {
        personRepository.deleteById(id);
    }

}
