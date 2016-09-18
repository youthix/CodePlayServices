package org.codeplay.presentation.entities;

import java.util.ArrayList;
import java.util.List;

import org.codeplay.repository.BObjects.User;

public class UserDetails {
	private List<User> user;
	public List<User> getUser() {
		if(user==null){
			user= new ArrayList<User>();
		}
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}



}
