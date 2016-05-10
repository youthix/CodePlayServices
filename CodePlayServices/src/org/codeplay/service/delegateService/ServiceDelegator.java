package org.codeplay.service.delegateService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.codeplay.presentation.entities.RequestObj;
import org.codeplay.presentation.entities.ResponseObj;
import org.codeplay.presentation.entities.SearchFields;
import org.codeplay.presentation.entities.UserList;
import org.codeplay.presentation.util.ServiceException;
import org.codeplay.repository.BObjects.User;
import org.codeplay.repository.RepositoryDelegate.RepositoryDelegator;

import com.googlecode.ehcache.annotations.Cacheable;

import net.sf.ehcache.CacheManager;

public class ServiceDelegator {

	private RepositoryDelegator repositoryDelegator;
	private UserList userList;
	private CacheManager cacheManager;

	public ResponseObj fetchPages(RequestObj reqparam) {
		ResponseObj responseObj = new ResponseObj();

		for (SearchFields searchFields : reqparam.getSearchFieldsList()) {
			userList = new UserList();
			String tags = returnTags(searchFields);
			String pageIds = repositoryDelegator.fetchPages(tags, searchFields.getAgeGroup(), searchFields.getGender());
			userList = populatePages(userList, pageIds, searchFields.getChapterNo());
			userList.setTag(tags);
			responseObj.getListOfUsers().add(userList);
		}
		return responseObj;
	}

	public ResponseObj fetchUsers(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		for (SearchFields searchFields : reqparam.getSearchFieldsList()) {
			userList = new UserList();
			String tags = searchFields.getTag();
			userList.setPageID(searchFields.getPageID());
			userList.setTag(tags);
			responseObj.getListOfUsers().add(repositoryDelegator.fetchUsers(tags, searchFields.getPageID(),
					returnDbQualifier(tags), returnTableQualifier(tags)));
		}
		return responseObj;
	}

	public ResponseObj fetchUserCount(RequestObj reqparam) {
		ResponseObj responseObj = new ResponseObj();

		for (SearchFields searchFields : reqparam.getSearchFieldsList()) {
			userList = new UserList();
			String tags = returnTags(searchFields);
			String userCount = repositoryDelegator.fetchUserCount(tags, searchFields.getAgeGroup(),
					searchFields.getGender());
			userList.setTag(tags);
			userList.setTotalUserCount(userCount);
			responseObj.getListOfUsers().add(userList);
		}
		return responseObj;
	}

	public ResponseObj registerUser(RequestObj reqparam) {
		ResponseObj responseObj = new ResponseObj();
		User userObj;

		for (SearchFields searchFields : reqparam.getSearchFieldsList()) {

			userObj = searchFields.getUser();
			userObj=setAgeGroup(userObj);
			userList = new UserList();
			if (null != userObj) {				
				User user = repositoryDelegator.registerUser(userObj);
				userList.getUserList().add(user);
				responseObj.getListOfUsers().add(userList);
			}
		}
		return responseObj;
	}
	
	private User setAgeGroup(User userObj){
		int age=Integer.valueOf(userObj.getAge());		
		String ageGroup="";
		if(age>=15 && age<=19){ageGroup="1519";}
		else if(age>=20 && age<=24){ageGroup="2024";}
		else if(age>=25 && age<=29){ageGroup="2529";}
		else if(age>=30 && age<=34){ageGroup="3034";}
		else if(age>=35 && age<=39){ageGroup="3539";}
		else if(age>=40 && age<=44){ageGroup="4044";}
		else if(age>=45 && age<=49){ageGroup="4549";}
		else if(age>=50 && age<=54){ageGroup="5054";}
		else if(age>=55 && age<=59){ageGroup="5559";}
		else if(age>=60 && age<=64){ageGroup="6064";}
		else if(age>=65 && age<=70){ageGroup="6570";}
		else ageGroup="Age Group Not Valid !!";
		userObj.setAgeGroup(ageGroup);		
		return userObj;
	}

	public String doIndexing(String username, String password, String dbQualifiers) {
		String[] dbArray = dbQualifiers.split(",");
		int i = 0;
		if (!checkAuthUser(username, password)) {
			return "Username and Password do not match !!";
		}
		for (String dbId : dbArray) {
			dbArray[i++] = "hotornot_" + dbId;
		}
		List<String> dbnamesList = new ArrayList<String>(Arrays.asList(dbArray));

		repositoryDelegator.setDbNameList(dbnamesList);
		repositoryDelegator.startIndexing();
		cacheManager.clearAll();
		System.out.println("Indexing completed successfully !");
		return "Indexing completed successfully !";
	}

	
	public ResponseObj logout(String fbIdParam) {
		
		repositoryDelegator.logout(fbIdParam);
		return null ;
	}
	
	@Cacheable(cacheName = "HelloCache")
	public String doHello() {
		System.out.println("Hi I am in dohello");
		throw new ServiceException("ExceptionTest");
		//return null;

	}
	
	public String doHelloExceptionTest()  {
		System.out.println("Hi I am in dohello");
		throw new ServiceException("ExceptionTest");
		//return null;

	}

	private boolean checkAuthUser(String username, String password) {
		return ("codeplay".equalsIgnoreCase(username) && "codeplay".equalsIgnoreCase(password));
	}

	private String returnTags(SearchFields searchFields) {
		String tags = "";
		tags = tags.concat(searchFields.getAgeGroup());
		tags = tags.concat(",");
		tags = tags.concat(searchFields.getGender());
		tags = tags.concat(",");
		tags = tags.concat((null == searchFields.getLivesInCountry() || "".equals(searchFields.getLivesInCountry())
				? "%" : searchFields.getLivesInCountry()));
		tags = tags.concat(",");
		tags = tags.concat((null == searchFields.getLiveInId() || "".equals(searchFields.getLiveInId()) ? "%"
				: searchFields.getLiveInId()));
		tags = tags.concat(",");
		tags = tags.concat(",");
		tags = tags.concat(",");
		tags = tags.concat((null == searchFields.getCurrentlyAtId() || "".equals(searchFields.getCurrentlyAtId()) ? "%"
				: searchFields.getCurrentlyAtId()));
		return tags;
	}

	private String returnTags(User userObj) {
		String tags = "";
		if (userObj != null) {

			tags = tags.concat(userObj.getAgeGroup());
			tags = tags.concat(",");
			tags = tags.concat(userObj.getGender());
			tags = tags.concat(",");
			tags = tags.concat((null == userObj.getLivesInCountry() || "".equals(userObj.getLivesInCountry()) ? "%"
					: userObj.getLivesInCountry()));
			tags = tags.concat(",");
			tags = tags.concat(
					(null == userObj.getLivesIn() || "".equals(userObj.getLivesIn()) ? "%" : userObj.getLivesIn()));
			tags = tags.concat(",");
			tags = tags.concat(",");
			tags = tags.concat(",");
			tags = tags.concat((null == userObj.getCurrentlyAtId() || "".equals(userObj.getCurrentlyAtId()) ? "%"
					: userObj.getCurrentlyAtId()));

		}
		return tags;
	}

	private String returnDbQualifier(String tags) {
		String[] temp = tags.split(",");
		return temp[0];
	}

	private String returnTableQualifier(String tags) {
		String[] temp = tags.split(",");
		return temp[1];
	}

	private UserList populatePages(UserList userList, String pageIds, String chapterNo) {
		// Setting total number of chapters
		String[] pageIdArr = pageIds.split(",");
		String pageId = "";
		int totalChapters = 0;
		int totalPages = pageIdArr.length;
		int remainder = totalPages % 5;
		int quotient = totalPages / 5;
		int chapterNumber = 0;
		int startIndex = -1;
		if ("".equals(pageIds) || ",".equals(pageIds)) {
			totalChapters = 0;
			chapterNo = "0";
		} else if (remainder == 0) {
			totalChapters = quotient;
		} else {
			totalChapters = quotient + 1;
		}

		userList.setTotalChapters(String.valueOf(totalChapters));

		// Setting pageIds based on chapterNo
		if (totalChapters != 0) {

			chapterNo = (null == chapterNo || "".equals(chapterNo)) ? "1" : chapterNo;
			chapterNumber = Integer.valueOf(chapterNo);
			startIndex = chapterNumber * 5 - 5;
			for (int i = startIndex; i < totalPages && i < startIndex + 5; i++) {
				pageId = pageId.concat(pageIdArr[i]).concat(",");
			}
			int index = pageId.lastIndexOf(",");
			if (index > 0) {
				pageId = pageId.substring(0, index);
			}
		}
		userList.setCurrChapterNo(chapterNo);
		userList.setPageID(pageId);

		return userList;
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
