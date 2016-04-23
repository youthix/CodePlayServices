package org.codeplay.presentation.entities;

public class SearchFields {
	
	private String ageGroup;
	
	private String gender;
	
	private String livesInCountry;
	
	private String liveInId;
	
	private String currentlyAtId;
	
	private String pageID;
	
	private String tag;

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

}
