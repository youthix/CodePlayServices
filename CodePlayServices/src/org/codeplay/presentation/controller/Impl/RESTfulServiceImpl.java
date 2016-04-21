package org.codeplay.presentation.controller.Impl;

import org.codeplay.presentation.controller.Interface.RESTfulServiceInterface;
import org.codeplay.presentation.entities.UserList;
import org.codeplay.service.delegateService.ServiceDelegator;

public class RESTfulServiceImpl implements RESTfulServiceInterface{

	ServiceDelegator serviceDelegator;
	@Override
	public String fetchPages(String tags) {		
		String pages=serviceDelegator.fetchPages(tags,"1519","male");
		return pages;
	}

	@Override
	public UserList fetchUsers(String pageIds) {
		UserList list=serviceDelegator.fetchUsers(pageIds);
		return list;
	}

	@Override
	public String helloWorld() {		
		return "Welcome to Code Play Services !!";
	}

}
