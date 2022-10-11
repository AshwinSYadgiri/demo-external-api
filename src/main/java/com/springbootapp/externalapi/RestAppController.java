package com.springbootapp.externalapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class RestAppController {
	

	
	@Autowired
	UserService service;
	
	@GetMapping(value = "/users")
	public String homePage(Model model) {
        model.addAttribute("User", service.getUsers());
        return "index";
    }
	
}
