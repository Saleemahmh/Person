package com.sag.person.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Service;

import com.sag.person.model.Person;

@Service("personService")
public class PersonService {
	
		
		private static List<Person> persons;
		
		static{
			persons= populateDummyPersons();
		}

		public List<Person> findAllPersons() {
			return persons;
		}
		
		public Person findById(long id) {
			for(Person person : persons){
				if(person.getId() == id){
					return person;
				}
			}
			return null;
		}
		
		
		public void savePerson(Person person) {
			person.setId(persons.size()+1);
			persons.add(person);
		}

		public void updatePerson(Person person) {
			int index = persons.indexOf(person);
			persons.set(index, person);
		}

		public void deletePersonById(long id) {
			
			for (Iterator<Person> iterator = persons.iterator(); iterator.hasNext(); ) {
			    Person person = iterator.next();
			    if (person.getId() == id) {
			        iterator.remove();
			    }
			}
		}

		
		private static List<Person> populateDummyPersons(){
			List<Person> persons = new ArrayList<Person>();
			persons.add(new Person(1,"Subha",25, "Chennai"));
			persons.add(new Person(2,"Sneha",23, "Salem"));
			persons.add(new Person(3,"Teena",27, "Trichy"));
			persons.add(new Person(4,"George",32, "Villupuram"));
			return persons;
		}

	}

