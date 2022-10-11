package com.springbootapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

	Data data;
	
	public User(Data data) {
		super();
		this.data = data;
	}
	
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
}
