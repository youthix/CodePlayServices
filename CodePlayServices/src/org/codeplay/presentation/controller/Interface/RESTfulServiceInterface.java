package org.codeplay.presentation.controller.Interface;

import org.codeplay.presentation.entities.RequestObj;
import org.codeplay.presentation.entities.ResponseObj;

public interface RESTfulServiceInterface {
	
	ResponseObj fetchPages(RequestObj reqparam);
	
	
	ResponseObj fetchUsers(RequestObj reqparam);
	
	ResponseObj fetchUserCount(RequestObj reqparam);
	
	
	String helloWorld();


	String doIndexing(String username, 
			String password, String dbQualifiers);
}
