package org.codeplay.presentation.controller.Interface;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codeplay.presentation.entities.UserList;


public interface RESTfulServiceInterface {

	
	String fetchPages(String tags);
	
	
	UserList fetchUsers(String pageIds);
	
	
	String helloWorld();
}
