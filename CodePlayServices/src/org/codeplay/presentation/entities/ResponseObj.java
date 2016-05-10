package org.codeplay.presentation.entities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;
@XmlRootElement(name = "resparam")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ResponseObj {

	private List<UserList> listOfUsers;
	
	private String errorCode;
	
	private String errorStatus;

	public List<UserList> getListOfUsers() {

		if (listOfUsers == null) {
			listOfUsers = new ArrayList<UserList>();
		}

		return listOfUsers;
	}

	public void setUserList(List<UserList> listOfUsers) {
		this.listOfUsers = listOfUsers;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorStatus() {
		return errorStatus;
	}

	public void setErrorStatus(String errorStatus) {
		this.errorStatus = errorStatus;
	}
	
	

}
