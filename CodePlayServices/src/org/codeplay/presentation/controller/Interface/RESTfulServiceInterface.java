package org.codeplay.presentation.controller.Interface;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codeplay.presentation.entities.UserList;

@Path("/hotornot")
public interface RESTfulServiceInterface {

	@POST
	@Path("/pages")
	@Produces(MediaType.APPLICATION_JSON)
	String fetchPages(String tags);
	
	@POST
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	UserList fetchUsers(String pageIds);
	
	@GET
	String helloWorld();
}
