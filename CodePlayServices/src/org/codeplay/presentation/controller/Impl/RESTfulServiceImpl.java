package org.codeplay.presentation.controller.Impl;


import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codeplay.presentation.controller.Interface.RESTfulServiceInterface;
import org.codeplay.presentation.entities.RequestObj;
import org.codeplay.presentation.entities.ResponseObj;
import org.codeplay.service.delegateService.ServiceDelegator;

@Path("/hotornot")
public class RESTfulServiceImpl implements RESTfulServiceInterface{
	
	public ServiceDelegator serviceDelegator;
	
		
	@Override
	@POST
	@Path("/pages")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	public ResponseObj fetchPages(RequestObj reqparam) {			
		ResponseObj respObj = new ResponseObj();
		try{
			respObj = serviceDelegator.fetchPages(reqparam);
			respObj.setErrorStatus("SUCCESS");
		}
		catch(Exception excepObj){
			respObj.setErrorStatus("FAILURE"); 
			respObj.setErrorCode(excepObj.getMessage());
			
		}
		return respObj;
	}
		
	@Override
	@POST
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseObj fetchUsers(RequestObj reqparam) {		
		return serviceDelegator.fetchUsers(reqparam);
	}
	
	@Override
	@POST
	@Path("/users-count")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseObj fetchUserCount(RequestObj reqparam) {
		return serviceDelegator.fetchUserCount(reqparam);
	}	
	
	@Override
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	public ResponseObj registerUser(RequestObj reqparam) {			
		
		return serviceDelegator.registerUser(reqparam);
	}	
	
	@Override
	@POST
	@Path("/index")
	@Produces(MediaType.APPLICATION_JSON)	
	public String doIndexing(@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("dbQualifiers") String dbQualifiers) {		
		return serviceDelegator.doIndexing(username,password,dbQualifiers);
	}
	
	@Override
	@POST
	@Path("/logout")
	@Produces(MediaType.APPLICATION_JSON)	
	public ResponseObj logout(@FormParam("fbID") String fbId) {		
		return serviceDelegator.logout(fbId);
	}	
	
	

	@Override
	@GET
	@Path("/hello")
	public String helloWorld() {
		
		try{
			serviceDelegator.doHelloExceptionTest();
		}
		catch(Exception exceptionObj){
			
			return exceptionObj.getMessage();
		}

		return "Welcome to Code Play Service !!";
	}
	
	public ServiceDelegator getServiceDelegator() {
		return serviceDelegator;
	}

	public void setServiceDelegator(ServiceDelegator serviceDelegator) {
		this.serviceDelegator = serviceDelegator;
	}

}
