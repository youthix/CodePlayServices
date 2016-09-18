package org.codeplay.presentation.entities;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@XmlRootElement(name = "userList")
public class UserList {	
	private UserDetails userDetails;
	private String tag;	
	private String pageID;
	private String currChapterNo;
	private String totalChapters;
	private String totalUserCount;
	
	private SearchFields searchFields;

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

	public String getTotalChapters() {
		return totalChapters;
	}

	public void setTotalChapters(String totalChapters) {
		this.totalChapters = totalChapters;
	}

	public String getCurrChapterNo() {
		return currChapterNo;
	}

	public void setCurrChapterNo(String currChapterNo) {
		this.currChapterNo = currChapterNo;
	}

	public String getTotalUserCount() {
		return totalUserCount;
	}

	public void setTotalUserCount(String totalUserCount) {
		this.totalUserCount = totalUserCount;
	}

	public UserDetails getUserDetails() {
		if(null == userDetails){
			userDetails = new UserDetails();
		}
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
	
}
