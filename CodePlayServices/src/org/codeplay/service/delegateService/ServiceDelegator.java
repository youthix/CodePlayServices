package org.codeplay.service.delegateService;

import org.codeplay.presentation.entities.UserList;
import org.codeplay.repository.RepositoryDelegate.RepositoryDelegator;

public class ServiceDelegator {
	
	RepositoryDelegator repositryDelegator;
	
	public String fetchPages(String tags,String dbQualifier,
			   String tableQualifier) {
		
		return repositryDelegator.fetchPages(tags, dbQualifier, tableQualifier);
	}

	
	public UserList fetchUsers(String pageIds) {
		
		return null;
	}

}
