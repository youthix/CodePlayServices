package org.codeplay.presentation.entities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "reqparam")
public class RequestObj {
	
	private List<SearchFields> searchFieldsList;
	
	private TransactionDetail trandetail;


	public List<SearchFields> getSearchFieldsList() {
		if (searchFieldsList == null) {
			searchFieldsList = new ArrayList<SearchFields>();
		}
		return searchFieldsList;
	}

	public void setSearchFieldsList(List<SearchFields> searchFieldsList) {
		this.searchFieldsList = searchFieldsList;
	}

	public TransactionDetail getTrandetail() {
		return trandetail;
	}

	public void setTrandetail(TransactionDetail trandetail) {
		this.trandetail = trandetail;
	}
	
	

}
