package org.codeplay.repository.RepositoryDelegate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.codeplay.presentation.entities.UserList;
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
	
	private void emptyIndexTables(String dbName){
		
		System.out.println("START :: Cleaning Index Tables !");
		dao.emptyIndexTables();		
		System.out.println("END :: Cleaning Index Tables !");
	
	}

	private void createIndexTables(String dbName) {
		List<String> genderList = new ArrayList<>();
		genderList.add("male");
		genderList.add("female");
		ThreadLocalRandom random = ThreadLocalRandom.current();		
		for (String gender : genderList) {
			System.out.println("Start for Gender = " + gender);			
			long startTime=System.currentTimeMillis();
			String queryStringTags = "select * from users_sorted_" + gender + " group by tags";
			List<User> usersUniqueTagList = dao.listUsers(queryStringTags);
			if (usersUniqueTagList != null) {
				System.out.println("Number of records to be inserted >>> "+usersUniqueTagList.size());

				for (User record : usersUniqueTagList) {
					String concatpageIDs = "";
					String newPageID = "";
					String userFBIdsConcat= "";
					String tagvalue = record.getTags();
					int index=-1;
					String queryStringUsers = "select * from users_sorted_" + gender + " where tags='" + tagvalue + "'";
					List<User> users = dao.listUsers(queryStringUsers);
					if (users != null && users.size() > 0) {
						
						for(User userObj : users){							
							userFBIdsConcat = userObj.getFbId()+ ","+userFBIdsConcat;
							
						}
						index=userFBIdsConcat.lastIndexOf(",");
						if(index !=-1) {
							userFBIdsConcat=userFBIdsConcat.substring(0, index);
						}
						int quotient = users.size() / 30;
						int remainder = users.size() % 30;

						if (quotient > 0) {

							for (int i = 1; i <= quotient; i++) {								
								newPageID = String.valueOf(random.nextInt());
								concatpageIDs = concatpageIDs + newPageID + ",";
							}

						}
						if (remainder > 0) {
							
							newPageID = String.valueOf(random.nextInt());
							concatpageIDs = concatpageIDs + newPageID + ",";
						}
						
						index=concatpageIDs.lastIndexOf(",");
						if(index !=-1) {
							concatpageIDs=concatpageIDs.substring(0, index);
						}

					}
					String insertTagPageString = "insert into tags_pages_mapping_" + gender
							+ "(`tags`, `page_ids`) values ('" + tagvalue + "','" + concatpageIDs + "')";
					dao.create(insertTagPageString);
					
					String insertPagetailsDeString = "insert into page_details_" + gender
							+ "(`page_id`, `fbids`,`table`) values ('" + concatpageIDs + "','" + userFBIdsConcat + "','users_sorted_"+gender+"')";
				    dao.create(insertPagetailsDeString);

				}
			}
			long endTime=System.currentTimeMillis();
			System.out.println("Total Time Taken (in seconds) >>"+ (endTime-startTime)/1000);
			System.out.println("Done for Gender = " + gender);

		}

	}	

	@Cacheable(cacheName ="fetchPagesCache",
			keyGenerator = @KeyGenerator (
					name="HashCodeCacheKeyGenerator",
					properties = @Property (
							name="includeMethod", value="false")))
	public String fetchPages(String tags,String dbQualifier,
			   String tableQualifier) {	
	 String pages="";
	 int index=-1;
	 List<TagPage> tagPages=dao.listPages(tags, dbQualifier, tableQualifier);
	 for(TagPage tagPage:tagPages){
		 pages=pages.concat(tagPage.getPageIds());
		 pages=pages.concat(",");
	 }
	 index=pages.lastIndexOf(",");
	 if(index>0){
	  pages=pages.substring(0,index);
	 }	 
	 return pages;
	}
	
	private List<Page> fetchPagesWithFbIds(String ids,String dbQualifier,
			   String tableQualifier) {		  
	 List<Page> pages=dao.listPagesWithFbIds(ids, dbQualifier, tableQualifier);	 
	 return pages;
	}
	
	
	@Cacheable(cacheName ="fetchUsersCache",
	keyGenerator = @KeyGenerator (
			name="HashCodeCacheKeyGenerator",
			properties = @Property (
					name="includeMethod", value="false")))
	public UserList fetchUsers(String tag,String ids,String dbQualifier,
		   String tableQualifier) {
	   List<User> users;
	   UserList userList=new UserList();
	   String fbIds="";
	   List<Page> pages=fetchPagesWithFbIds( ids, dbQualifier,
		    tableQualifier); 	   
	   for(Page tagPage:pages){
		 fbIds=tagPage.getFbIds();	
		 users=dao.listUsersWithFbIds(fbIds, dbQualifier, tableQualifier);
		 userList.setTag(tag);
		 userList.setUserList(users);
		 userList.setPageID(tagPage.getId());
	   }
		System.out.println("fbIds>>"+fbIds);
		return userList;
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
