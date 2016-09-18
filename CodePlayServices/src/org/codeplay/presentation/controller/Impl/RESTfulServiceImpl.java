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
import org.codeplay.presentation.entities.Rating;
import org.codeplay.presentation.entities.RequestObj;
import org.codeplay.presentation.entities.ResponseObj;
import org.codeplay.presentation.entities.TransactionDetail;
import org.codeplay.presentation.entities.UserList;
import org.codeplay.presentation.util.ServiceConstant;
import org.codeplay.presentation.util.ServiceException;
import org.codeplay.presentation.util.ServiceExceptionMapper;
import org.codeplay.repository.BObjects.User;
import org.codeplay.service.delegateService.ServiceDelegator;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Path("/hotornot")
public class RESTfulServiceImpl implements RESTfulServiceInterface {

	public ServiceDelegator serviceDelegator;

	@Override
	@POST
	@Path("/pages")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseObj fetchPages(RequestObj reqparam) {
		ResponseObj respObj = new ResponseObj();
		try {
			respObj = serviceDelegator.fetchPages(reqparam);
			respObj.setStatusMsg("SUCCESS");
		} catch (Exception excepObj) {
			return ServiceExceptionMapper.toResponse(excepObj);

		}
		return respObj;
	}

	@Override
	@POST
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseObj fetchUsers(RequestObj reqparam) {

		ResponseObj respObj = new ResponseObj();
		try {
			//respObj = serviceDelegator.fetchUsers(reqparam);
			respObj = serviceDelegator.fetchUsersDemo(reqparam);
			respObj.setStatusMsg("SUCCESS");
		} catch (Exception excepObj) {
			return ServiceExceptionMapper.toResponse(excepObj);

		}
		return respObj;

	}

	@Override
	@POST
	@Path("/users-count")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseObj fetchUserCount(RequestObj reqparam) {

		ResponseObj respObj = new ResponseObj();
		try {
			respObj = serviceDelegator.fetchUserCount(reqparam);
			respObj.setStatusMsg("SUCCESS");
		} catch (Exception excepObj) {
			return ServiceExceptionMapper.toResponse(excepObj);

		}
		return respObj;
	}

	@Override
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseObj registerUser(RequestObj reqparam) {

		ResponseObj respObj = new ResponseObj();
		try {
			respObj = serviceDelegator.registerUser(reqparam);
			respObj.setStatusMsg("SUCCESS");
		} catch (Exception excepObj) {
			return ServiceExceptionMapper.toResponse(excepObj);

		}
		return respObj;
	}

	@Override
	@POST
	@Path("/index")
	@Produces(MediaType.APPLICATION_JSON)
	public String doIndexing(@FormParam("username") String username, @FormParam("password") String password,
			@FormParam("dbQualifiers") String dbQualifiers) {
		return serviceDelegator.doIndexing(username, password, dbQualifiers);
	}

	@Override
	@POST
	@Path("/logout")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseObj logout(@FormParam("fbID") String fbId) {

		ResponseObj respObj = new ResponseObj();
		try {
			serviceDelegator.logout(fbId);
			respObj.setStatusMsg("SUCCESS");
		} catch (Exception excepObj) {
			return ServiceExceptionMapper.toResponse(excepObj);

		}
		return respObj;
	}

	@Override
	@POST
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseObj deleteUser(RequestObj reqparam) {

		ResponseObj respObj = new ResponseObj();
		try {
			boolean success = serviceDelegator.deleteUser(reqparam);
			if (success) {
				respObj.setStatusMsg("SUCCESS");
			} else {
				respObj.setStatusMsg("FAILURE");
				respObj.setStatusCode(ServiceConstant.GENERIC_ERROR);
			}

		} catch (Exception excepObj) {
			return ServiceExceptionMapper.toResponse(excepObj);

		}
		return respObj;
	}
	
	@Override
	@POST
	@Path("/fetchRating")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseObj fetchRating(RequestObj reqparam) {

		ResponseObj respObj = new ResponseObj();
		try {
/*			boolean success = serviceDelegator.deleteUser(reqparam);
			if (success) {
				respObj.setStatusMsg("SUCCESS");
			} else {
				respObj.setStatusMsg("FAILURE");
				respObj.setStatusCode(ServiceConstant.GENERIC_ERROR);
			}*/
			respObj.setStatusCode("0");
			respObj.setStatusMsg("SUCCESS");
			 List<UserList> userListforEachTag = new ArrayList<UserList>();
			 
			 UserList userListObj = new UserList();
			 
			 List<User> listOfUser = new ArrayList<User>();
			 
			 User userObj = new User();
			 userObj.setFbId("123");
			 
			 Rating rating = new Rating ();
			  rating.setAgeGroup("10");
			  rating.setHomeTown("5");
			  rating.setFbId("12");
			  userObj.setRating(rating);
			 
			 listOfUser.add(userObj);
			 
			 userListObj.getUserDetails().setUser(listOfUser);
			 
			 userListforEachTag.add(userListObj);
			 
			 respObj.setListOfUsers(userListforEachTag);
	

		} catch (Exception excepObj) {
			return ServiceExceptionMapper.toResponse(excepObj);

		}
		return respObj;
	}	
	
	@Override
	@POST
	@Path("/saveTransaction")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseObj saveTransaction(RequestObj reqparam) {

		ResponseObj respObj = new ResponseObj();
		try {
			boolean success = serviceDelegator.deleteUser(reqparam);
			if (success) {
				respObj.setStatusMsg("SUCCESS");
			} else {
				respObj.setStatusMsg("FAILURE");
				respObj.setStatusCode(ServiceConstant.GENERIC_ERROR);
			}

		} catch (Exception excepObj) {
			return ServiceExceptionMapper.toResponse(excepObj);

		}
		return respObj;
	}	
	
	@Override
	@POST
	@Path("/getTransaction")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseObj getTransaction(RequestObj reqparam) {

		ResponseObj respObj = new ResponseObj();
		try {

			/*boolean success = serviceDelegator.deleteUser(reqparam);
			if (success) {
				respObj.setStatusMsg("SUCCESS");
			} else {
				respObj.setStatusMsg("FAILURE");
				respObj.setStatusCode(ServiceConstant.GENERIC_ERROR);
			}*/

			respObj.setStatusCode("0");
			respObj.setStatusMsg("SUCCESS");
			TransactionDetail transactionDetailObj = new TransactionDetail();
			transactionDetailObj.setStatus("SUCCESS");
			transactionDetailObj.setTransactionId("1111");
			respObj.setTrandetail(transactionDetailObj);
			
		} catch (Exception excepObj) {
			return ServiceExceptionMapper.toResponse(excepObj);

		}
		return respObj;
	}	

	@Override
	@GET
	@Path("/hello")
	public String helloWorld() {

		try {
			serviceDelegator.doHello();
		} catch (Exception exceptionObj) {

			ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return "Welcome to Code Play Service !!";
	}

	@ExceptionHandler(ServiceException.class)
	public String handleCustomException(ServiceException ex) {

		System.out.println("Hi");
		return "In Exception Handler";

	}

	public ServiceDelegator getServiceDelegator() {
		return serviceDelegator;
	}

	public void setServiceDelegator(ServiceDelegator serviceDelegator) {
		this.serviceDelegator = serviceDelegator;
	}

}
