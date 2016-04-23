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

public class RepositoryDelegator {
	
	private UserDAOInterface dao;
	private List<String> dbNameList;	

	public void startIndexing() {

		

		for (String dbName : dbNameList) {

			ConnectionFactory.getConnection(dbName);

			dao.setDataSource(ConnectionFactory.getDriverManagerDataSource());
			/* doing for Male Users */

			createIndexTables();
		}

	}

	private void createIndexTables() {
		List<String> genderList = new ArrayList<>();
		genderList.add("male");
		genderList.add("female");
		ThreadLocalRandom random = ThreadLocalRandom.current();

		for (String gender : genderList) {
			System.out.println("Start for Gender = " + gender);
			System.out.println("Starttime >>"+ System.currentTimeMillis());
			String queryStringTags = "select * from users_sorted_" + gender + " group by tags";
			List<User> usersUniqueTagList = dao.listUsers(queryStringTags);
			if (usersUniqueTagList != null) {
				System.out.println(usersUniqueTagList.size());

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

							//newPageID = (quotient + 1) + tagvalue;
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
			
			System.out.println("EndTime >>"+ System.currentTimeMillis());
			System.out.println("Done for Gender = " + gender);

		}

	}	

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
	 
	 fetchPagesWithFbIds("test", dbQualifier,
			 "female");
	 
	 return pages;
	}
	
	private List<Page> fetchPagesWithFbIds(String ids,String dbQualifier,
			   String tableQualifier) {		  
	 List<Page> pages=dao.listPagesWithFbIds(ids, dbQualifier, tableQualifier);	 
	 return pages;
	}	
	
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
	   }
		System.out.println("fbIds>>"+fbIds);
		return null;
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

}
