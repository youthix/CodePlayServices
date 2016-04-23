package org.codeplay.presentation.entities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "resparam")
public class ResponseObj {

	private List<UserList> userList;

	public List<UserList> getUserList() {

		if (userList == null) {
			userList = new ArrayList<UserList>();
		}

		return userList;
	}

	public void setUserList(List<UserList> userList) {
		this.userList = userList;
	}

}
