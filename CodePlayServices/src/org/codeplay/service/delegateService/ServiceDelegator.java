package org.codeplay.service.delegateService;

import org.codeplay.presentation.entities.UserList;
import org.codeplay.repository.RepositoryDelegate.RepositoryDelegator;

public class ServiceDelegator {
	
	public RepositoryDelegator repositoryDelegator;
	
	public String fetchPages(String tags,String dbQualifier,
			   String tableQualifier) {
		
		return repositoryDelegator.fetchPages(tags, dbQualifier, tableQualifier);
	}

	
	public UserList fetchUsers(String pageIds) {
		
		return null;
	}


	public RepositoryDelegator getRepositoryDelegator() {
		return repositoryDelegator;
	}


	public void setRepositoryDelegator(RepositoryDelegator repositoryDelegator) {
		this.repositoryDelegator = repositoryDelegator;
	}

}
