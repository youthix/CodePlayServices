package org.codeplay.presentation.entities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;
@XmlRootElement(name = "resparam")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ResponseObj {

	private List<UserList> listOfUsers;

	public List<UserList> getListOfUsers() {

		if (listOfUsers == null) {
			listOfUsers = new ArrayList<UserList>();
		}

		return listOfUsers;
	}

	public void setUserList(List<UserList> listOfUsers) {
		this.listOfUsers = listOfUsers;
	}

}
