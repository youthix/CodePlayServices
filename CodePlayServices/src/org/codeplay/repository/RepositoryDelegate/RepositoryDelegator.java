package org.codeplay.repository.RepositoryDelegate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.codeplay.presentation.entities.UserList;
import org.codeplay.repository.BObjects.IndexingInfo;
import org.codeplay.repository.BObjects.Page;
import org.codeplay.repository.BObjects.TagPage;
import org.codeplay.repository.BObjects.User;
import org.codeplay.repository.DAOInterface.UserDAOInterface;
import org.codeplay.repository.dbUtilities.ConnectionFactory;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.KeyGenerator;
import com.googlecode.ehcache.annotations.Property;

public class RepositoryDelegator {

	private UserDAOInterface dao;
	private List<String> dbNameList;
	private ConnectionFactory connectionFactory;

	public void startIndexing() {

		for (String dbName : dbNameList) {

			connectionFactory.getConnection(dbName);
			dao.setDataSource(connectionFactory.getDriverManagerDataSource());
			System.out.println("DatabaseName = " + dbName);
			emptyIndexTables(dbName);
			createIndexTables(dbName);
		}

	}

	private void emptyIndexTables(String dbName) {

		System.out.println("START :: Cleaning Index Tables !");
		dao.emptyIndexTables(dbName);
		System.out.println("END :: Cleaning Index Tables !");

	}

	private void createIndexTables(String dbName) {
		List<String> genderList = new ArrayList<>();
		String lastPageId="";
		genderList.add("male");
		genderList.add("female");
		List<TagPage> tagPageList = null;
		List<Page> pageList = null;

		ThreadLocalRandom random = ThreadLocalRandom.current();
		int pageCount = 0;
		for (String gender : genderList) {
			System.out.println("Start for Gender = " + gender);
			long startTime = System.currentTimeMillis();
			tagPageList = new ArrayList<TagPage>();
			pageList = new ArrayList<Page>();
			String queryStringTags = "select * from users_sorted_" + gender + " group by tags";
			List<User> usersUniqueTagList = dao.listUsers(queryStringTags);
			if (usersUniqueTagList != null) {
				System.out.println("Number of records to be inserted >>> " + usersUniqueTagList.size());

				for (User record : usersUniqueTagList) {
					String concatpageIDs = "";
					String newPageID = "";
					String userFBIdsConcat = "";
					String tagvalue = record.getTags();
					int index = -1;
					String queryStringUsers = "select * from users_sorted_" + gender + " where tags='" + tagvalue + "'";
					List<User> users = dao.listUsers(queryStringUsers);
					if (users != null && users.size() > 0) {

						for (User userObj : users) {
							userFBIdsConcat = userObj.getFbId() + "," + userFBIdsConcat;

						}
						index = userFBIdsConcat.lastIndexOf(",");
						if (index != -1) {
							userFBIdsConcat = userFBIdsConcat.substring(0, index);
						}
						int quotient = users.size() / 30;
						int remainder = users.size() % 30;

						if (quotient > 0) {

							for (int i = 1; i <= quotient; i++) {
								// newPageID = String.valueOf(random.nextInt());
								newPageID = "P" + String.valueOf(++pageCount);
								concatpageIDs = concatpageIDs + newPageID + ",";
							}

						}
						if (remainder > 0) {

							// newPageID = String.valueOf(random.nextInt());
							newPageID = "P" + String.valueOf(++pageCount);
							concatpageIDs = concatpageIDs + newPageID + ",";
						}

						index = concatpageIDs.lastIndexOf(",");
						if (index != -1) {
							concatpageIDs = concatpageIDs.substring(0, index);
						}

					}
					lastPageId=newPageID;
					/*
					 * Creating a list of object to be passed as a parameter for
					 * Batch Insert in TagPageMapping Table
					 */
					TagPage tagPageObj = new TagPage();
					tagPageObj.setTags(tagvalue);
					tagPageObj.setPageIds(concatpageIDs);

					tagPageList.add(tagPageObj);

					/*
					 * Creating a list of object to be passed as a parameter for
					 * Batch Insert in PageDetails Table
					 */
					Page pageObj = new Page();
					pageObj.setId(concatpageIDs);
					pageObj.setFbIds(userFBIdsConcat);
					pageObj.setTable("users_sorted_" + gender);

					pageList.add(pageObj);

					/*
					 * String insertTagPageString =
					 * "insert into tags_pages_mapping_" + gender +
					 * "(`tags`, `page_ids`) values ('" + tagvalue + "','" +
					 * concatpageIDs + "')"; dao.create(insertTagPageString);
					 */

					/*
					 * String insertPagetailsDeString =
					 * "insert into page_details_" + gender +
					 * "(`page_id`, `fbids`,`table`) values ('" + concatpageIDs
					 * + "','" + userFBIdsConcat +
					 * "','users_sorted_"+gender+"')";
					 * dao.create(insertPagetailsDeString);
					 */

				}
			}
			dao.insertBatchTagsToPageID(tagPageList, dbName, gender);
			dao.insertBatchPageIDToFBID(pageList, dbName, gender);
			long endTime = System.currentTimeMillis();
			System.out.println("Total Time Taken (in seconds) >>" + (endTime - startTime) / 1000);
			System.out.println("Done for Gender = " + gender);

		}
		dao.insertIndexingInfo(dbName,lastPageId);
	}

	@Cacheable(cacheName = "fetchPagesCache", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false") ) )
	public String fetchPages(String tags, String dbQualifier, String tableQualifier) {
		String pages = "";
		int index = -1;
		List<TagPage> tagPages = dao.listPages(tags, dbQualifier, tableQualifier);
		for (TagPage tagPage : tagPages) {
			pages = pages.concat(tagPage.getPageIds());
			pages = pages.concat(",");
		}
		index = pages.lastIndexOf(",");
		if (index > 0) {
			pages = pages.substring(0, index);
		}
		return pages;
	}

	@Cacheable(cacheName = "fetchUserCount", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false") ) )
	public String fetchUserCount(String tags, String dbQualifier, String tableQualifier) {

		List<User> users = dao.listUsers(tags, dbQualifier, tableQualifier);

		if (null != users) {
			return String.valueOf(users.size());
		} else {
			return "0";
		}

	}
	
	public User registerUser(User userObjParam) {

		User userObjDBase = dao.getUser(userObjParam);
		if (null!=userObjDBase){
			
			/*Update*/
			if("N".equalsIgnoreCase(userObjDBase.getActive())){
				//TODO::return error that user is disabled by admin				
			}
			else{
				updateUser( userObjParam, userObjDBase);
				userObjDBase=dao.getUser(userObjParam);
				userObjDBase.setUserExists("Y");
			}
		}
		else {
			
			/*Insert*/
			insertUser(userObjParam);
			userObjDBase=dao.getUser(userObjParam);
			userObjDBase.setUserExists("N");
		}

		return userObjDBase;

	}
	
	public User logout(String fbIdParam) {
		
		updateLogOut(fbIdParam);

		return null;
	}
	

	@Cacheable(cacheName = "fetchUsersCache", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false") ) )
	public UserList fetchUsers(String tag, String pageIds, String dbQualifier, String tableQualifier) {
		List<User> users;
		UserList userList = new UserList();
		String fbIds = "";
		List<Page> pages = fetchPagesWithFbIds(pageIds, dbQualifier, tableQualifier);
		for (Page tagPage : pages) {
			fbIds = tagPage.getFbIds();
			users = dao.listUsersWithFbIds(fbIds, dbQualifier, tableQualifier);
			userList.setTag(tag);
			userList.setUserList(users);
			userList.setPageID(tagPage.getId());
		}
		System.out.println("fbIds>>" + fbIds);
		return userList;
	}

	private List<Page> fetchPagesWithFbIds(String ids, String dbQualifier, String tableQualifier) {
		List<Page> pages = dao.listPagesWithFbIds(ids, dbQualifier, tableQualifier);
		return pages;
	}
	
	private void updateUser(User userObjParam,User userObjDBase){		
		String tagsObjParam=returnTags(userObjParam);
		userObjParam.setTags(tagsObjParam);
		String tagsObjDBase=returnTags(userObjDBase);
		userObjDBase.setTags(tagsObjDBase);
		if(tagsObjParam.equalsIgnoreCase(tagsObjDBase)){
			updateProfile(userObjParam);
		}
		else{
			copyProfile(userObjParam, userObjDBase);
			deleteProfile(userObjDBase);
			createProfile(userObjParam);
			repaginate(userObjParam, userObjDBase);
		}
		
	}
	
	private void insertUser(User userObjParam){	
		String tagsObjParam=returnTags(userObjParam);
		userObjParam.setTags(tagsObjParam);
		createProfile(userObjParam);		
		insertIntoNewPage(userObjParam);
	}
	
	private void updateProfile(User userObjParam){
		dao.updateUser(userObjParam);
	}
	
	private void updateLogOut(String fbIdParam){
		dao.updateLogOut(fbIdParam);
	}
	
	private void copyProfile(User userObjParam,User userObjDBase){
		//To be done when we will introduce likes,dislikes,ratings and rankings
		//In case we are maintaining separate tables it will not be needed
	}
	
	private void repaginate(User userObjParam,User userObjDBase){
		deleteFromOldPage(userObjDBase);
		insertIntoNewPage(userObjParam);
	}
	
	private void deleteFromOldPage(User userObjDBase){
		String tags=userObjDBase.getTags();
		String dbQualifier=returnDbQualifier(tags);
		String tableQualifier=returnTableQualifier(tags);		
		List<Page> oldPage=
				dao.listPagesWithFbIds(userObjDBase.getFbId(),
						dbQualifier, 
						tableQualifier);
		String oldUserList=oldPage.get(0).getFbIds();
		String newUserList=oldUserList.replaceAll(
				userObjDBase.getFbId(),"");
		dao.updateFbUsersList(oldPage.get(0).getId(),newUserList,
				dbQualifier, 
				tableQualifier);
	}
	
	private void insertIntoNewPage(User userObjParam){
		String tags=userObjParam.getTags();
		String dbQualifier=returnDbQualifier(tags);
		String tableQualifier=returnTableQualifier(tags);		
		List<TagPage> tagPage=
				dao.listPages(tags,
						dbQualifier, 
						tableQualifier);
		String [] pageList=tagPage.get(0).getPageIds().split(",");
		int pageListSize=pageList.length;
		if(pageListSize==0){
			createNewPage(userObjParam, tags, dbQualifier, tableQualifier,
					tagPage);	
		}
		else{			
		String lastPageId=pageList[pageListSize-1];
		List<Page> pageDetails=dao.listPagesWithFbIds(lastPageId, dbQualifier, tableQualifier);
		String[] usersArray=pageDetails.get(0).getFbIds().split(",");
		if(usersArray.length==30){
			//create new page
			createNewPage(userObjParam, tags, dbQualifier, tableQualifier,
					tagPage);
		}
		else{
			//update existing list
			String newUserList=(usersArray.length==0)?userObjParam.getFbId():
					pageDetails.get(0).getFbIds()+","+userObjParam.getFbId();
			dao.updateFbUsersList(lastPageId, newUserList, dbQualifier, tableQualifier);
		}
	  }
	}

	private void createNewPage(User userObjParam, String tags,
			String dbQualifier, String tableQualifier, List<TagPage> tagPage) {
		IndexingInfo ii=dao.selectIndexingInfo(userObjParam.getAgeGroup());
		String pageId=ii.getLastPageId();			
		int pageIdentifier=Integer.valueOf(pageId.substring(1,pageId.length()));
		String newPageId="P"+String.valueOf(++pageIdentifier);
		String newPageList=tagPage.get(0)+","+newPageId;
		dao.updateIndexingInfo(userObjParam.getAgeGroup(), newPageId);
		dao.updatePageIdList(tags, newPageList, dbQualifier, tableQualifier);
	}
	
	private void deleteProfile(User userObjDBase){
		dao.deleteUser(userObjDBase);
	}
	
	private void createProfile(User userObjParam){
		dao.createUser(userObjParam);
	}

	private String returnTags(User userObj) {
		String tags = "";
		tags = tags.concat(userObj.getAgeGroup());
		tags = tags.concat(",");
		tags = tags.concat(userObj.getGender());
		tags = tags.concat(",");
		tags = tags.concat((null == userObj.getLivesInCountry())
				? "" : userObj.getLivesInCountry());
		tags = tags.concat(",");
		tags = tags.concat((null == userObj.getLivesInId()) ? ""
				: userObj.getLivesInId());
		tags = tags.concat(",");
		tags = tags.concat(",");
		tags = tags.concat(",");
		tags = tags.concat((null == userObj.getCurrentlyAtId()) ? ""
				: userObj.getCurrentlyAtId());
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

	public List<String> getDbNameList() {
		return dbNameList;
	}

	public void setDbNameList(List<String> dbNameList) {
		this.dbNameList = dbNameList;
	}

	public UserDAOInterface getDao() {
		return dao;
	}

	public void setDao(UserDAOInterface dao) {
		this.dao = dao;
	}

	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

}
