package org.codeplay.repository.BObjects;

import java.util.ArrayList;
import java.util.List;

public class PictureList {
	private List<Picture> picture;
	public List<Picture> getPicture() {
		if (picture == null) {
			picture = new ArrayList<Picture>();
		}
		return picture;
	}
	public void setPicture(List<Picture> pictureList) {
		this.picture = pictureList;
	}	
}
