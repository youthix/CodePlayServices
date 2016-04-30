package org.codeplay.service.delegateService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.ehcache.CacheManager;

import org.codeplay.presentation.entities.RequestObj;
import org.codeplay.presentation.entities.ResponseObj;
import org.codeplay.presentation.entities.SearchFields;
import org.codeplay.presentation.entities.UserList;
import org.codeplay.repository.RepositoryDelegate.RepositoryDelegator;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;

import com.googlecode.ehcache.annotations.Cacheable;

public class ServiceDelegator {
	
	private RepositoryDelegator repositoryDelegator;
	private UserList userList;
	private CacheManager cacheManager;
	
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
	
	public String doIndexing(String username, 
			String password, String dbQualifiers){
		String[] dbArray=dbQualifiers.split(",");
		int i=0;
		if(!checkAuthUser(username,password)){
			return "Username and Password do not match !!";
		}
		for(String dbId: dbArray){
			dbArray[i++]="hotornot_"+dbId;
		}
		List<String> dbnamesList = 
				new ArrayList<String>(Arrays.asList(dbArray));
		
		repositoryDelegator.setDbNameList(dbnamesList);		
		repositoryDelegator.startIndexing();
		cacheManager.clearAll();
		System.out.println("Indexing completed successfully !");
		return "Indexing completed successfully !";
	}
	@Cacheable(cacheName ="HelloCache")
	public String doHello() {
		System.out.println("Hi I am in dohello");
		return null;
		
	}	
	
	private boolean checkAuthUser(String username,String password){
		return ("codeplay".equalsIgnoreCase(username) && "codeplay".equalsIgnoreCase(password));
	}
	
	private String returnTags(SearchFields searchFields){
		String tags="";
		tags=tags.concat(searchFields.getAgeGroup());tags=tags.concat(",");
		tags=tags.concat(searchFields.getGender());tags=tags.concat(",");
		tags=tags.concat((null==searchFields.getLivesInCountry() || "".equals(searchFields.getLivesInCountry())?
				"%":searchFields.getLivesInCountry()));tags=tags.concat(",");
		tags=tags.concat((null==searchFields.getLiveInId() || "".equals(searchFields.getLiveInId())?"%":searchFields.getLiveInId()));tags=tags.concat(",");
		tags=tags.concat(",");tags=tags.concat(",");
		tags=tags.concat((null==searchFields.getCurrentlyAtId() || "".equals(searchFields.getCurrentlyAtId())?"%":searchFields.getCurrentlyAtId()));
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

	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
}
