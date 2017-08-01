package com.sag.person.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sag.person.model.Person;
import com.sag.person.service.PersonService;
import com.sag.person.util.ErrorMessage;

@RestController
@RequestMapping("/api")
public class PersonController {


		public static final Logger logger = LoggerFactory.getLogger(PersonController.class);

		@Autowired
		PersonService personService; //Service which will do all data retrieval/manipulation work

		// Retrieve All Persons
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@GetMapping("/persons")
		public ResponseEntity<List<Person>> listAllPersons() {
			List<Person> persons = personService.findAllPersons();
			if (persons.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
				// You many decide to return HttpStatus.NOT_FOUND
			}
			return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
		}

		// Retrieve Single Person by ID

		@SuppressWarnings({ "unchecked", "rawtypes" })
		@GetMapping("/person/{id}")
		public ResponseEntity<?> getPerson(@PathVariable("id") long id) {
			logger.info("Fetching Person with id {}", id);
			Person person = personService.findById(id);
			if (person == null) {
				logger.error("Person with id {} not found.", id);
				return new ResponseEntity(new ErrorMessage("Person with id " + id 
						+ " not found"), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Person>(person, HttpStatus.OK);
		}

		// Create a Person
		
		@PostMapping("/addperson")
		public ResponseEntity<?> createPerson(@RequestBody Person person, UriComponentsBuilder ucBuilder) {
			logger.info("Creating Person : {}", person);
			personService.savePerson(person);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/api/person/{id}").buildAndExpand(person.getId()).toUri());
			return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		}

		// Update a Person

		@SuppressWarnings({ "unchecked", "rawtypes" })
		@PutMapping("/updateperson/{id}")
		public ResponseEntity<?> updatePerson(@PathVariable("id") long id, @RequestBody Person person) {
			logger.info("Updating Person with id {}", id);

			Person currentPerson = personService.findById(id);

			if (currentPerson == null) {
				logger.error("Unable to update. Person with id {} not found.", id);
				return new ResponseEntity(new ErrorMessage("Unable to upate. Person with id " + id + " not found."),
						HttpStatus.NOT_FOUND);
			}

			currentPerson.setName(person.getName());
			currentPerson.setAge(person.getAge());
			currentPerson.setCity(person.getCity());

			personService.updatePerson(currentPerson);
			return new ResponseEntity<Person>(currentPerson, HttpStatus.OK);
		}

		//Delete a Persons
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@DeleteMapping("/deleteperson/{id}")
		public ResponseEntity<?> deletePerson(@PathVariable("id") long id) {
			logger.info("Fetching & Deleting Person with id {}", id);

			Person person = personService.findById(id);
			if (person == null) {
				logger.error("Unable to delete. Person with id {} not found.", id);
				return new ResponseEntity(new ErrorMessage("Unable to delete. Person with id " + id + " not found."),
						HttpStatus.NOT_FOUND);
			}
			personService.deletePersonById(id);
			return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
		}


	}