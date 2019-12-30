package com.xp.app;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.jayway.jsonpath.JsonPath;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactSpecVersion;
import au.com.dius.pact.model.RequestResponsePact;


public class SaveStudentConsumerTest{

	@Rule
	public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("student_provider",PactSpecVersion.V3, this);
	private RestTemplate restTemplate=new RestTemplate();


	@Pact(provider = "student_provider", consumer = "student_consumer_1")
	public RequestResponsePact createPact(PactDslWithProvider builder) {
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);

		PactDslJsonBody bodyResponse = new PactDslJsonBody()
				.stringValue("name", "Ideal Name")
				.stringType("locationName", "Pune")
				.integerType("rank", 100);

		return builder.given("create student").uponReceiving("a request to save student")
				.path("/api/student")
				.body(bodyResponse)
				.method(RequestMethod.POST.name())
				.willRespondWith()
				.headers(headers)
				.status(200).body(bodyResponse).toPact();
	}





	@Test
	@PactVerification
	public void testCreateStudentConsumer() throws IOException {

		Student student=new Student("Ideal Name", "Pune", 100);
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<Object> request=new HttpEntity<Object>(student, headers);
		ResponseEntity<String> responseEntity=restTemplate.postForEntity(mockProvider.getUrl()+"/api/student", request, String.class);
		assertEquals("Ideal Name", JsonPath.read(responseEntity.getBody(),"$.name"));
		assertEquals("Pune", JsonPath.read(responseEntity.getBody(),"$.locationName"));
		assertEquals((Integer)100, (Integer)JsonPath.read(responseEntity.getBody(),"$.rank"));
	}

}
