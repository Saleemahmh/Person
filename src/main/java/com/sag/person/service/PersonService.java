package com.sag.person.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Service;

import com.sag.person.model.Person;

//Person Service
@Service("personService")
public class PersonService {

	// Creating object for Person as persons
	private static List<Person> persons;
	// Storing dummy data in persons object
	static {
		persons = populateDummyPersons();
	}

	// Return the list containing all persons
	public List<Person> findAllPersons() {
		return persons;
	}

	// Returns the person with the given id specified by the user by iterarting
	// 'persons' object.
	public Person findById(long id) {
		for (Person person : persons) {
			if (person.getId() == id) {
				return person;
			}
		}
		return null;
	}

	/*
	 * Saving a person by automatically setting Id based on the size of the
	 * 'persons' and adding 1 value each time a new entry is added
	 */

	public void savePerson(Person person) {
		person.setId(persons.size() + 1);
		persons.add(person);
	}

	// Updating a person with indexOf
	public void updatePerson(Person person) {
		int index = persons.indexOf(person);
		persons.set(index, person);
	}

	// Deletes the person with the given id specified by the user by iterating
	// 'persons' object.
	// Using Iterator
	public void deletePersonById(long id) {

		for (Iterator<Person> iterator = persons.iterator(); iterator.hasNext();) {
			Person person = iterator.next();
			if (person.getId() == id) {
				iterator.remove();
			}
		}
	}

	// Populating dummy data into Person model
	private static List<Person> populateDummyPersons() {
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person(1, "Subha", 25, "Chennai"));
		persons.add(new Person(2, "Sneha", 23, "Salem"));
		persons.add(new Person(3, "Teena", 27, "Trichy"));
		persons.add(new Person(4, "George", 32, "Villupuram"));
		return persons;
	}

}
