package com.springbootapp.externalapi;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.springbootapp.model.User;
import com.springbootapp.model.Users;


@Service
public class UserService {
	
   //@Autowired
    private RestTemplate restTemplate;
    
    
    @Autowired
    public UserService(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}

	public Object getUsers() throws ApplicationHandlerException {
    	
    	HttpHeaders headers = new HttpHeaders();
		headers.add("user-agent", "Application");
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
    	
		restTemplate.getInterceptors().add(new ClientHttpRequestInterceptor(){
	        @Override
	        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
	            request.getHeaders().set("User-Agent", "Application"); //Set the header for each request
	       		return execution.execute(request, body);
	        }
	    }); 
		
		ResponseEntity resp = 
		          restTemplate.getForEntity("https://reqres.in/api/users", Users.class);
		
		if ((resp.getStatusCode() == HttpStatus.OK)) {
			return  resp.getBody();
		} else {
			throw new ApplicationHandlerException();
		}
				
        
	
    }

	public Object getUserId(String id) throws ApplicationHandlerException {
    	
    	HttpHeaders headers = new HttpHeaders();
		headers.add("user-agent", "Application");
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
    	
	
		restTemplate.getInterceptors().add(new ClientHttpRequestInterceptor(){
	        @Override
	        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
	            request.getHeaders().set("User-Agent", "Application"); //Set the header for each request
				return execution.execute(request, body);
	        }
	    }); 
		
		ResponseEntity resp = 
		          restTemplate.getForEntity("https://reqres.in/api/users/" + id, User.class);
		
		if ((resp.getStatusCode() == HttpStatus.OK)) {
			return  resp.getBody();
		} else {
			throw new ApplicationHandlerException();
		}
				
        
	
    }


}
