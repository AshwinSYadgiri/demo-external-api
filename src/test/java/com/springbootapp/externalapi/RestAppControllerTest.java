package com.springbootapp.externalapi;



import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.springbootapp.model.Data;
import com.springbootapp.model.User;
//import reactor.test.StepVerifier;
import org.mockito.quality.Strictness;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RestAppControllerTest {

	@Mock
	private RestTemplate restTemplate = new RestTemplate();


		@Test
		public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject() throws URISyntaxException {
	
			String baseUrl = "http://externalapi.test.com/users"; // dummy url
			URI uri = new URI(baseUrl);
	
			Data[] data = new Data[1];
			data[0] = new Data("1", "George", "george.bluth@reqres.in", "Bluth");
			User user = new User(1, 6, data);
			Mockito
			.when(restTemplate.getForEntity(baseUrl, User.class))
			.thenReturn(new ResponseEntity(user, HttpStatus.OK));
	
		}

}
