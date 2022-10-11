package com.springbootapp.externalapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@Controller
public class RestAppController {
	

	@Autowired
	UserService service;
	
	@GetMapping(value = "/users")
	public String homePage(Model model) throws ApplicationHandlerException {
        model.addAttribute("User", service.getUsers());
        return "index";
    }


	@GetMapping(
     value = "/user/{id}",
    produces = {"application/json" }
	)
	public ResponseEntity<Object> userInfo(@PathVariable("id") String id) throws ApplicationHandlerException {
        return ResponseEntity.ok(service.getUserId(id));
    }
}
