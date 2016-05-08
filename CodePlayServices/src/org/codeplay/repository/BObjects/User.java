package org.codeplay.repository.BObjects;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class User {
	
	public String id;
	public String name;
	public String profilePicture;
	public String fbId;
	public String gender;
	public String livesIn;
	public String livesInId;
	public String livesInCountry;
	public String homeTown;
	public String homeTownId;
	public String homeTownCountry;
	public String currentlyAt;
	public String currentlyAtId;
	public String dateOfBirth;
	public String createdDate;
	public String appName;
	public String rawData;
	public String age;
	public String ageGroup;
	public String tags;
	public String email;
	public String active;
	public String userExists;
	public String userType;
	
		
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	public String getFbId() {
		return fbId;
	}
	public void setFbId(String fbId) {
		this.fbId = fbId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getLivesIn() {
		return livesIn;
	}
	public void setLivesIn(String livesIn) {
		this.livesIn = livesIn;
	}
	public String getLivesInCountry() {
		return livesInCountry;
	}
	public void setLivesInCountry(String livesInCountry) {
		this.livesInCountry = livesInCountry;
	}
	public String getHomeTown() {
		return homeTown;
	}
	public void setHomeTown(String homeTown) {
		this.homeTown = homeTown;
	}
	public String getHomeTownId() {
		return homeTownId;
	}
	public void setHomeTownId(String homeTownId) {
		this.homeTownId = homeTownId;
	}
	public String getHomeTownCountry() {
		return homeTownCountry;
	}
	public void setHomeTownCountry(String homeTownCountry) {
		this.homeTownCountry = homeTownCountry;
	}
	public String getCurrentlyAt() {
		return currentlyAt;
	}
	public void setCurrentlyAt(String currentlyAt) {
		this.currentlyAt = currentlyAt;
	}
	public String getCurrentlyAtId() {
		return currentlyAtId;
	}
	public void setCurrentlyAtId(String currentlyAtId) {
		this.currentlyAtId = currentlyAtId;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getRawData() {
		return rawData;
	}
	public void setRawData(String rawData) {
		this.rawData = rawData;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getAgeGroup() {
		return ageGroup;
	}
	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLivesInId() {
		return livesInId;
	}
	public void setLivesInId(String livesInId) {
		this.livesInId = livesInId;
	}
	public String getUserExists() {
		return userExists;
	}
	public void setUserExists(String userExists) {
		this.userExists = userExists;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}	
}
