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


@Service
public class UserService {
	
   //@Autowired
    private RestTemplate restTemplate;
    
    
    @Autowired
    public UserService(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}

	public Object getUsers() {
    	
    	HttpHeaders headers = new HttpHeaders();
		headers.add("user-agent", "Application");
		headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
		headers.setContentType(MediaType.TEXT_HTML);
    	
		restTemplate.getInterceptors().add(new ClientHttpRequestInterceptor(){
	        @Override
	        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
	            request.getHeaders().set("User-Agent", "Application"); //Set the header for each request
	       		request.getHeaders().setAccept(Arrays.asList(MediaType.TEXT_HTML));
				request.getHeaders().setContentType(MediaType.TEXT_HTML);
				return execution.execute(request, body);
	        }
	    }); 
		
		ResponseEntity resp = 
		          restTemplate.getForEntity("https://reqres.in/api/users", User.class);
		
		return resp.getStatusCode() == HttpStatus.OK ? resp.getBody() : null;
        
	
    }
}
