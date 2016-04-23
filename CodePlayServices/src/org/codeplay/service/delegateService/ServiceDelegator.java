package org.codeplay.service.delegateService;

import org.codeplay.presentation.entities.UserList;
import org.codeplay.repository.RepositoryDelegate.RepositoryDelegator;

public class ServiceDelegator {
	
	private RepositoryDelegator repositoryDelegator;
	
	public String fetchPages(String tags,String dbQualifier,
			   String tableQualifier) {
		
		return repositoryDelegator.fetchPages(tags, dbQualifier, 
				tableQualifier);
	}

	
	public UserList fetchUsers(String tags,String pageIds) {		
		
		return repositoryDelegator.fetchUsers(tags, pageIds,
				returnDbQualifier(tags), returnTableQualifier(tags));
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
