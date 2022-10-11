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
import com.springbootapp.model.Users;

import org.mockito.quality.Strictness;
import org.springframework.ui.Model;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceTest {

	@Mock
	UserService service;
	@InjectMocks
	RestAppController controller;


		@Test
		public void userServiceMethod() throws URISyntaxException, ApplicationHandlerException {
	
			Users users = new Users();
			Mockito
			.when(service.getUsers())
			.thenReturn(users);

			assertEquals((users.getClass()), Users.class);
	
		}

}
