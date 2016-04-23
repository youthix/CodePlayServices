package org.codeplay.presentation.controller.Interface;

import org.codeplay.presentation.entities.UserList;

public interface RESTfulServiceInterface {

	
	String fetchPages(String tags);
	
	
	UserList fetchUsers(String pageIds);
	
	
	String helloWorld();
}
