package com.example.demo.controller;
import com.example.demo.model.domain.Person;
import com.example.demo.model.request.PersonRequest;
import com.example.demo.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/persons")
@Tag(name="Person controller", description="To work with person data")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    @Operation(summary = "To fetch person data")
    public List<Person> getPerson(@RequestParam(required = false)String firstName){
         return personService.getPersons(firstName);
      }
      @GetMapping(value = "/{id}")
      @Operation(summary = "To fetch person data by its id")
    public Person getPersonById(@PathVariable Long id) {
        return personService.fetchPersonById(id);
      }

      @PostMapping
      @Operation(summary = "To create person")
    public Person createPerson(@Validated @RequestBody PersonRequest personRequest) {
          return personService.createPerson(personRequest);
      }
      @PutMapping(value = "/{id}")
    @Operation(summary = "Update person")
    public Person updatePerson(@PathVariable Long id,
                                         @RequestBody(required = false) PersonRequest personRequest) {
        return personService.updatePerson(id, personRequest);
      }
      @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete person")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
      }
}
