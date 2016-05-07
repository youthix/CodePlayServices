package org.codeplay.presentation.controller.Interface;

import javax.ws.rs.FormParam;

import org.codeplay.presentation.entities.RequestObj;
import org.codeplay.presentation.entities.ResponseObj;
import org.springframework.http.HttpRequest;


public interface RESTfulServiceInterface {
	
	ResponseObj fetchPages(RequestObj reqparam);
	
	ResponseObj fetchUsers(RequestObj reqparam);
	
	ResponseObj fetchUserCount(RequestObj reqparam);
	
	ResponseObj registerUser(RequestObj reqparam);	
	
	ResponseObj logout(String fbId);
	
	String helloWorld();

	String doIndexing(String username, 
			String password, String dbQualifiers);	
}
