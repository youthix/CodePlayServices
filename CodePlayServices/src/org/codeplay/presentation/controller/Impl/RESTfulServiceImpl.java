package org.codeplay.presentation.controller.Impl;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codeplay.presentation.controller.Interface.RESTfulServiceInterface;
import org.codeplay.presentation.entities.UserList;
import org.codeplay.service.delegateService.ServiceDelegator;

@Path("/hotornot")
public class RESTfulServiceImpl implements RESTfulServiceInterface{

	public ServiceDelegator serviceDelegator;
	
	@Override
	@POST
	@Path("/pages")
	@Produces(MediaType.APPLICATION_JSON)
	public String fetchPages(@FormParam("tags") String tags) {		
		String pages=serviceDelegator.fetchPages(tags,"1519","female");
		return pages;
	}

	@Override
	@POST
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public UserList fetchUsers(String pageIds) {
		UserList list=serviceDelegator.fetchUsers(pageIds);
		return list;
	}

	@Override
	@GET
	@Path("/hello")
	public String helloWorld() {		
		return "Welcome to Code Play Services !!";
	}

	public ServiceDelegator getServiceDelegator() {
		return serviceDelegator;
	}

	public void setServiceDelegator(ServiceDelegator serviceDelegator) {
		this.serviceDelegator = serviceDelegator;
	}

}
