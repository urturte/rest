package com.example.demo.service;

import com.example.demo.model.domain.Person;
import com.example.demo.model.exception.NoPersonFoundException;
import com.example.demo.model.request.PersonRequest;
import com.example.demo.model.response.PersonResponse;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPersons( String firstName){
            if(firstName!= null && !firstName.isBlank()) {
                return getPersonsByFirstName(firstName);
            }
            return fetchPersons();
    }
    public List<Person> getPersonsByFirstName(String name){
        return personRepository.findAllByFirstName(name);
    }

    public Person fetchPersonById(Long id) throws NoPersonFoundException {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
        return person.get();
        } else {
            throw new NoPersonFoundException("Person doesn't exist");
        }
    }

    public Person createPerson(PersonRequest request) {
        Person person = convertPersonRequestToPerson(request);
        return personRepository.save(person);
    }
    public Person updatePerson(Long id, PersonRequest request) {
        Person person = fetchPersonById(id);
        if(request.getFirstName() != null) {
            person.setFirstName(request.getFirstName());
        }
        if(request.getLastName() != null) {
            person.setLastName(request.getLastName());
        }
        if(request.getEmail() != null) {
            person.setEmail(request.getEmail());
        }
        if(request.getPhone() != null) {
            person.setPhone(request.getPhone());
        }
        return personRepository.save(person);
    }
    public void deletePerson(Long id) {
        Person person = fetchPersonById(id);
        personRepository.delete(person);
    }
    private List<Person> fetchPersons(){
        return personRepository.findAll();
    }

    private PersonResponse convertPersonToPersonResponse(Person person){
        return person == null
                ? null
                : new PersonResponse(person.getFirstName(), person.getLastName(), person.getEmail(), person.getPhone());
    }

    private Person convertPersonRequestToPerson(PersonRequest request){
        return request == null
                ? null
                : Person.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();
    }
    
}
