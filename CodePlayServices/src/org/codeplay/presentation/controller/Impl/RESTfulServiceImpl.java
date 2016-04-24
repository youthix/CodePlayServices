package org.codeplay.presentation.controller.Impl;

import java.util.ArrayList;
import java.util.List;

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
import org.codeplay.service.delegateService.ServiceDelegator;

@Path("/hotornot")
public class RESTfulServiceImpl implements RESTfulServiceInterface{
	
	private ServiceDelegator serviceDelegator;
		
	@Override
	@POST
	@Path("/pages")
	@Produces(MediaType.APPLICATION_JSON)
	public String fetchPages(@FormParam("tags") String tags) {			
		String pages=serviceDelegator.fetchPages(tags,"1519","female");
		return pages;
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

	@Override
	@POST
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public UserList fetchUsers(@FormParam("tags") String tags,
			@FormParam("pageIds") String pageIds) {
		UserList list=serviceDelegator.fetchUsers(
				tags, pageIds);
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
