package org.codeplay.presentation.entities;

import org.codeplay.repository.BObjects.User;

public class SearchFields {
	
	private String ageGroup;
	
	private String gender;
	
	private String livesInCountry;
	
	private String liveInId;
	
	private String currentlyAtId;
	
	private String pageID;
	
	private String tag;
	
	//Paid,New,Old User count to be fetched
	private String bagCount;
	
	private String chapterNo;
	
	private User user;

	public String getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getPageID() {
		return pageID;
	}

	public void setPageID(String pageID) {
		this.pageID = pageID;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getLivesInCountry() {
		return livesInCountry;
	}

	public void setLivesInCountry(String livesInCountry) {
		this.livesInCountry = livesInCountry;
	}

	public String getLiveInId() {
		return liveInId;
	}

	public void setLiveInId(String liveInId) {
		this.liveInId = liveInId;
	}

	public String getCurrentlyAtId() {
		return currentlyAtId;
	}

	public void setCurrentlyAtId(String currentlyAtId) {
		this.currentlyAtId = currentlyAtId;
	}

	public String getChapterNo() {
		return chapterNo;
	}

	public void setChapterNo(String chapterNo) {
		this.chapterNo = chapterNo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getBagCount() {
		return bagCount;
	}

	public void setBagCount(String bagCount) {
		this.bagCount = bagCount;
	}		

}
