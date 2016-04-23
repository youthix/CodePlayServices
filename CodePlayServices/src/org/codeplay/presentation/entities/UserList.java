package org.codeplay.presentation.entities;

import java.util.List;

import org.codeplay.repository.BObjects.User;

public class UserList {

	private List<User> userList;
	private String tag;	
	private String pageID;
	
	private SearchFields searchFields;

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

	public String getPageID() {
		return pageID;
	}

	public void setPageID(String pageID) {
		this.pageID = pageID;
	}

	public SearchFields getSearchFields() {
		return searchFields;
	}

	public void setSearchFields(SearchFields searchFields) {
		this.searchFields = searchFields;
	}
	
	
	
}
