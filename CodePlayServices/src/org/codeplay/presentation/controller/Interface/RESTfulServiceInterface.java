package org.codeplay.presentation.controller.Interface;

import javax.ws.rs.core.Response;

import org.codeplay.presentation.entities.RequestObj;
import org.codeplay.presentation.entities.ResponseObj;


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
