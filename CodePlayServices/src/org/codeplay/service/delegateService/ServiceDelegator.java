package org.codeplay.service.delegateService;

import org.codeplay.presentation.entities.RequestObj;
import org.codeplay.presentation.entities.ResponseObj;
import org.codeplay.presentation.entities.SearchFields;
import org.codeplay.presentation.entities.UserList;
import org.codeplay.repository.RepositoryDelegate.RepositoryDelegator;

public class ServiceDelegator {
	
	private RepositoryDelegator repositoryDelegator;
	private UserList userList;
	
	public ResponseObj fetchPages(RequestObj reqparam) {
		ResponseObj responseObj= new ResponseObj();		
		
		for(SearchFields searchFields:reqparam.getSearchFieldsList()){
		userList= new UserList();
		String tags=returnTags(searchFields);
		userList.setPageID(
				repositoryDelegator.fetchPages(tags, 
						searchFields.getAgeGroup(), 
						searchFields.getGender()));
		userList.setTag(tags);
		responseObj.getListOfUsers().add(userList);
	   }
	  return responseObj;
	}

	
	public ResponseObj fetchUsers(RequestObj reqparam) {	

		ResponseObj responseObj= new ResponseObj();		
		for(SearchFields searchFields:reqparam.getSearchFieldsList()){
		userList= new UserList();
		String tags=searchFields.getTag();
		userList.setPageID(searchFields.getPageID());
		userList.setTag(tags);
		responseObj.getListOfUsers().add(
				repositoryDelegator.fetchUsers(tags, searchFields.getPageID(),
				returnDbQualifier(tags), returnTableQualifier(tags)));
	   }
	  return responseObj;
	}
	
	private String returnTags(SearchFields searchFields){
		String tags="";
		tags=tags.concat(searchFields.getAgeGroup());tags=tags.concat(",");
		tags=tags.concat(searchFields.getGender());tags=tags.concat(",");
		tags=tags.concat(("".equals(searchFields.getLivesInCountry())?"%":searchFields.getLivesInCountry()));tags=tags.concat(",");
		tags=tags.concat(("".equals(searchFields.getLiveInId())?"%":searchFields.getLiveInId()));tags=tags.concat(",");
		tags=tags.concat(",");tags=tags.concat(",");
		tags=tags.concat(("".equals(searchFields.getCurrentlyAtId())?"%":searchFields.getCurrentlyAtId()));
		return tags;
	}
	
	private String returnDbQualifier(String tags){
		String[] temp=tags.split(",");
		return temp[0];
	}
	
	private String returnTableQualifier(String tags){
		String[] temp=tags.split(",");
		return temp[1];
	}



	public RepositoryDelegator getRepositoryDelegator() {
		return repositoryDelegator;
	}


	public void setRepositoryDelegator(RepositoryDelegator repositoryDelegator) {
		this.repositoryDelegator = repositoryDelegator;
	}

}
