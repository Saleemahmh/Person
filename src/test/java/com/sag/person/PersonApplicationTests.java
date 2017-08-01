package com.sag.person;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.sag.person.model.Person;

import ch.qos.logback.core.net.server.Client;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonApplicationTests {
	
	    private static final String BASE_URL = "http://localhost:8080/api";
	    
	    @Before
	    public void beforeTest() {
	        new RestTemplate();
	    }

	    // GET
	    @Test
	    public void getTest() {
	        TestRestTemplate testRestTemplate = new TestRestTemplate();
	        ResponseEntity<String> response = testRestTemplate.getForEntity(BASE_URL + "/persons", String.class);
	        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
	        System.out.println(response);
	    }
	   
	    @Test
	    public void getByIdTest() {
	        

	    	TestRestTemplate testRestTemplate = new TestRestTemplate();
	        final Person resource = testRestTemplate.getForObject(BASE_URL + "/person/{id}", Person.class, "1");

	        assertThat(resource, notNullValue());
	        System.out.println(resource);
	    }
	@Test
	public void postTest(){
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		Person person = new Person(5, "Amrita", 25, "Delhi");
	    ResponseEntity<String> result = testRestTemplate.postForEntity( BASE_URL + "/addperson", person, String.class);
	    assertThat(result.getStatusCode(),equalTo(HttpStatus.CREATED));
	    System.out.println(result);
	}
	/*@Test
	public void putTest (){
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		Person person = new Person(1, "Amrita", 25, "Delhi");
	    ResponseEntity<String> result = testRestTemplate.put( BASE_URL + "/updateperson/{id}",String.class, "1");
	    assertThat(result.getStatusCode(),equalTo(HttpStatus.CREATED));
	    System.out.println(result);
	}*/
	
	/*private static void putTest()
	{
	     
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("id", "2");
	     
	    EmployeeVO updatedEmployee = new EmployeeVO(2, "New Name", "Gilly", "test@email.com");
	     
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.put ( uri, updatedEmployee, params);
	}*/
	@Test
	public void deleteTest() throws Exception {
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		ResponseEntity<Person> responseEntity = testRestTemplate.exchange(BASE_URL + "/deleteperson/{id}",HttpMethod.DELETE, null, Person.class, 1);

		assertThat(responseEntity.getStatusCode(), is(HttpStatus.NO_CONTENT));
		assertThat(responseEntity.getBody(), nullValue());
		System.out.println(responseEntity);
	}
	
	@Test
    public void givenFooService_whenPutObject_thenUpdatedObjectIsReturned() {
        final HttpHeaders headers = new HttpHeaders();
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        headers.setContentType(MediaType.APPLICATION_JSON);
        final Person person = new Person(2, "Bala",25,"Chennai");
        final HttpEntity<Person> requestUpdate = new HttpEntity<>(person, headers);
        final ResponseEntity<Person> response = testRestTemplate.exchange(BASE_URL + "/updateperson/2", HttpMethod.PUT, requestUpdate, Person.class);

        assertThat(response.getStatusCode(),is(HttpStatus.OK) );
    }
	public void contextLoads() {
		
	}

}
