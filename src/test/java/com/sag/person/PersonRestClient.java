package com.sag.person;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.sag.person.model.Person;

public class PersonRestClient {
	
	    public static final String REST_SERVICE_URI = "http://localhost:8080/api";
	      
	    /* GET */
	    @SuppressWarnings("unchecked")
	    private static void listAllPersons(){
	        System.out.println("Testing listAllPersons API-----------");
	          
	        RestTemplate restTemplate = new RestTemplate();
	        List<LinkedHashMap<String, Object>> personsMap = restTemplate.getForObject(REST_SERVICE_URI+"/persons", List.class);
	          
	        if(personsMap!=null){
	            for(LinkedHashMap<String, Object> map : personsMap){
	                System.out.println("Person : id="+map.get("id")+", Name="+map.get("name")+", Age="+map.get("age")+", City="+map.get("city"));;
	            }
	        }else{
	            System.out.println("No person exist----------");
	        }
	    }
	      
	    /* GET */
	    private static void getPerson(){
	        System.out.println("Testing getPerson API----------");
	        RestTemplate restTemplate = new RestTemplate();
	        Person person = restTemplate.getForObject(REST_SERVICE_URI+"/person/1", Person.class);
	        System.out.println(person);
	    }
	      
	    /* POST */
	    private static void createPerson() {
	        System.out.println("Testing create Person API----------");
	        RestTemplate restTemplate = new RestTemplate();
	        Person person = new Person(0,"Gowthaman",32,"Chennai");
	        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/addperson", person, Person.class);
	        System.out.println("Location : "+uri.toASCIIString());
	    }
	  
	    /* PUT */
	    private static void updatePerson() {
	        System.out.println("Testing update Person API----------");
	        RestTemplate restTemplate = new RestTemplate();
	        Person person  = new Person(1,"Anu",33, "Madurai");
	        restTemplate.put(REST_SERVICE_URI+"/updateperson/1", person);
	        System.out.println(person);
	    }
	  
	    /* DELETE */
	    private static void deletePerson() {
	        System.out.println("Testing delete Person API----------");
	        RestTemplate restTemplate = new RestTemplate();
	        restTemplate.delete(REST_SERVICE_URI+"/deleteperson/3");
	    }
	  
	  
	    public static void main(String args[]){
	        listAllPersons();
	        getPerson();
	        createPerson();
	        listAllPersons();
	        updatePerson();
	        listAllPersons();
	        deletePerson();
	        listAllPersons();
	        listAllPersons();
	    }
	}

