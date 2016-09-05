package org.codeplay.presentation.entities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;
@XmlRootElement(name = "resparam")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ResponseObj {

	private List<UserList> listOfUsers;
	
	private String statusCode;
	
	private String statusMsg;

	public List<UserList> getListOfUsers() {

		if (listOfUsers == null) {
			listOfUsers = new ArrayList<UserList>();
		}

		return listOfUsers;
	}

	public void setUserList(List<UserList> listOfUsers) {
		this.listOfUsers = listOfUsers;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}


	
	

}
