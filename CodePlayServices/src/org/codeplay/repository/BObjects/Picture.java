package org.codeplay.repository.BObjects;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Picture {
	
	private String id;
	private String name;
	private String binaryCode;
	private String url;
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
	public String getBinaryCode() {
		return binaryCode;
	}
	public void setBinaryCode(String binaryCode) {
		this.binaryCode = binaryCode;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
