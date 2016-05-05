package org.codeplay.presentation.controller.Impl;


import java.util.ArrayList;
import java.util.List;

//github.com/youthix/CodePlayServices.git
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codeplay.presentation.controller.Interface.RESTfulServiceInterface;
import org.codeplay.presentation.entities.RequestObj;
import org.codeplay.presentation.entities.ResponseObj;
import org.codeplay.presentation.entities.SearchFields;
import org.codeplay.presentation.entities.UserList;
import org.codeplay.repository.BObjects.User;
//github.com/youthix/CodePlayServices.git
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
		
		return serviceDelegator.fetchPages(reqparam);
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
	@Path("/index")
	@Produces(MediaType.APPLICATION_JSON)	
	public String doIndexing(@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("dbQualifiers") String dbQualifiers) {		
		return serviceDelegator.doIndexing(username,password,dbQualifiers);
	}

	@Override
	@GET
	@Path("/hello")
	public String helloWorld() {		
		serviceDelegator.doHello();
		return "Welcome to Code Play Services !!";
	}
	
	
	@POST
	@Path("/pageIds")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseObj fetchPageIds(RequestObj requestParam) {
		
		System.out.println(requestParam.getSearchFieldsList().size());
		
		List <SearchFields> searchFieldsList = requestParam.getSearchFieldsList();
		
		for (SearchFields searchFieldsObj : searchFieldsList){
			
			System.out.println(searchFieldsObj.getTag());
		}
		
		ResponseObj responseObj = new ResponseObj();
		
		List<UserList> listOfuserList = new ArrayList<UserList>();
		
		for (int i=1; i<5;i++){
			
			UserList userListObj = new UserList();
			
			userListObj.setPageID("1");
			userListObj.setTag("Tag");
			List<User> users = new ArrayList<User>();
			for(int j=1; j<5;j++){
				User userObj= new User();
				userObj.setAge("15");
				userObj.setDateOfBirth("1234");
				users.add(userObj);
			}

			userListObj.setUserList(users);
			
			listOfuserList.add(userListObj);
		}
		responseObj.setUserList(listOfuserList);
		//String pages=serviceDelegator.fetchPages(tags,"1519","female");
		//return null;
		return responseObj;
	}	

	public ServiceDelegator getServiceDelegator() {
		return serviceDelegator;
	}

	public void setServiceDelegator(ServiceDelegator serviceDelegator) {
		this.serviceDelegator = serviceDelegator;
	}

}
