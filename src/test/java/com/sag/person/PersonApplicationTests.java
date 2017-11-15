package com.sag.person;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.sag.person.model.Person;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonApplicationTests {

    private static final String BASE_URL = "http://localhost:9200/saleema_s/person";
    TestRestTemplate testRestTemplate = new TestRestTemplate();
    //Search by City with Elastic Search (POST)
    @Test
    public void postSearchByCityTest() {
        String jsonString = "{\"query\":{\"match\" : {\"city\":\"Chennai\"}}}";
        System.out.println(jsonString);
        ResponseEntity<String> result = testRestTemplate.postForEntity(BASE_URL + "/_search", jsonString, String.class);
        assertThat(result.getStatusCode(), equalTo(HttpStatus.OK));
        System.out.println(result);

    }

    //Search by Age with Elastic Search (POST)
    @Test
    public void postSearchByAgeTest() {
        String jsonString = "{\"query\":{\"match\" : {\"city\":\"Chennai\"}}}";
        System.out.println(jsonString);
        ResponseEntity<String> result = testRestTemplate.postForEntity(BASE_URL + "/_search", jsonString, String.class);
        assertThat(result.getStatusCode(), equalTo(HttpStatus.OK));
        System.out.println(result);

    }

    //Search by Age Ranging from
    // less than equal to(lte)24 with Elastic Search (POST)
    @Test
    public void postSearchByAgeRangeTest() {
        String jsonString = "{\"query\":{\"range\" : {\"age\":{\"lte\":24}}}}";
        System.out.println(jsonString);
        ResponseEntity<String> result = testRestTemplate.postForEntity(BASE_URL + "/_search", jsonString, String.class);
        assertThat(result.getStatusCode(), equalTo(HttpStatus.OK));
        System.out.println(result);

    }
    //Search by City Chennai and within the range of age from 23 -28(POST)
    @Test
    public void postSearchByAgeandCityTest() {
        String jsonString = "{\"size\" : 0,\"query\":{\"match\": {\"city\": \"Chennai\"}},\"aggs\":{\"age_range\": {\"filter\": {\"range\":{\"age\": {\"from\" : 23, \"to\" : 28}}}}}}";
        System.out.println(jsonString);
        ResponseEntity<String> result = testRestTemplate.postForEntity(BASE_URL + "/_search", jsonString, String.class);
        assertThat(result.getStatusCode(), equalTo(HttpStatus.OK));
        System.out.println(result);

    }

    //GET all
    @Test
    public void getTest() {
        ResponseEntity<String> response = testRestTemplate.getForEntity(BASE_URL + "/_search", String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        System.out.println(response);
    }

    public void contextLoads() {

    }

}
//POST
    /*@Test
	public void postTest() {
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		Person person = new Person(1, "Anu", 25, "Delhi");
		ResponseEntity<String> result = testRestTemplate.postForEntity(BASE_URL , person, String.class);
		assertThat(result.getStatusCode(), equalTo(HttpStatus.CREATED));
		System.out.println(result);
	}*/


//POST
	/*@Test
	public void postManyTest() {
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		Person person = new  Person(1,"Sam",23,"chennai");
		ResponseEntity<String> result = testRestTemplate.postForEntity(BASE_URL+"/_bulk" , person, String.class);
		assertThat(result.getStatusCode(), equalTo(HttpStatus.CREATED));
		System.out.println(result);
	}*/
// GET


// DELETE
	/*@Test
	public void deleteTest() throws Exception {
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		ResponseEntity<Person> responseEntity = testRestTemplate.exchange(BASE_URL + "/{id}",
				HttpMethod.DELETE, null, Person.class, 1);

		assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(responseEntity.getBody(), nullValue());
		System.out.println(responseEntity);
	}*/



/*// GET by ID
@Test
public void getByIdTest() {

	TestRestTemplate testRestTemplate = new TestRestTemplate();
	final Person resource = testRestTemplate.getForObject(BASE_URL + "/{id}", Person.class, "1");

	assertThat(resource, notNullValue());
	System.out.println(resource);
}

// POST
@Test
public void postTest() {
	TestRestTemplate testRestTemplate = new TestRestTemplate();
	Person person = new Person(5, "Amrita", 25, "Delhi");
	ResponseEntity<String> result = testRestTemplate.postForEntity(BASE_URL , person, String.class);
	assertThat(result.getStatusCode(), equalTo(HttpStatus.CREATED));
	System.out.println(result);
}*/
/*	// PUT
@Test
public void putTest() {
	final HttpHeaders headers = new HttpHeaders();
	TestRestTemplate testRestTemplate = new TestRestTemplate();
	headers.setContentType(MediaType.APPLICATION_JSON);
	final Person person = new Person(2, "Bala", 25, "Chennai");
	final HttpEntity<Person> requestUpdate = new HttpEntity<>(person, headers);
	final ResponseEntity<Person> response = testRestTemplate.exchange(BASE_URL + "/2", HttpMethod.PUT,
			requestUpdate, Person.class);

	assertThat(response.getStatusCode(), is(HttpStatus.OK));
}
*/