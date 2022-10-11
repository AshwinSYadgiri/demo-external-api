package com.springbootapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Users {
	
	int page;
	int total;
	Data data[];
	
	public Users(int page, int total, Data[] data) {
		super();
		this.page = page;
		this.total = total;
		this.data = data;
	}
	
	
	
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Data[] getData() {
		return data;
	}
	public void setData(Data[] data) {
		this.data = data;
	}
}
