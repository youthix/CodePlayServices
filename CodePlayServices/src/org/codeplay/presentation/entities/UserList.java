package org.codeplay.presentation.entities;

import java.util.List;
import org.codeplay.repository.BObjects.User;

public class UserList {

	public List<User> userList;
	public String tag;	

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
}
