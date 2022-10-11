package com.springbootapp.externalapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import com.springbootapp.model.User;


import org.mockito.quality.Strictness;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RestAppControllerTest {

	@Mock
	UserService service;
	@InjectMocks
	RestAppController controller;


		@Test
		public void restAppControllerMethod() throws URISyntaxException, ApplicationHandlerException {
	
			User user = new User();
			Mockito
			.when(service.getUserId(anyString()))
			.thenReturn(user);

			assertEquals(user, controller.userInfo("1").getBody());
	
		}

}
