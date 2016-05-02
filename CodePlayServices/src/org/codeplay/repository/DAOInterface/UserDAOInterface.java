package org.codeplay.repository.DAOInterface;

import java.util.List;

import javax.sql.DataSource;

import org.codeplay.repository.BObjects.Page;
import org.codeplay.repository.BObjects.TagPage;
import org.codeplay.repository.BObjects.User;

public interface UserDAOInterface {
	

	   /** 
	    * This is the method to be used to initialize
	    * database resources ie. connection.
	    */
	   public void setDataSource(DataSource ds);
	   
		/** 
	    * This is the method to be used to group data on basis of Tags and divide 
	    * them into pages. And create a mapping between Tags and Page Id and 
	    * to also create a mapping between PgIds and FbIds.
	    */
	   //public void createDataMapping(DataSource ds);
	   
	   
	   /** 
	    * This is the method to be used to create
	    * a record in the Student table.
	    */
	   public void create();
	   
	   public void create(String sqlQueryString);
	   
	   public User getUser(Integer id);
	   /** 
	    * This is the method to be used to list down
	    * all the records from the Student table.
	    */
	   public List<User> listUsers(String sqlQueryString);
	   /** 
	    * This is the method to be used to delete
	    * a record from the Student table corresponding
	    * to a passed student id.
	    */
	   public void delete(Integer id);
	   /** 
	    * This method to be used to fetch
	    * pages based on tags,age,gender
	    */
	   //public void update();
	   public List<TagPage> listPages(String tags,String dbQualifier,
			   String tableQualifier);
	   /** 
	    * This method to be used to fetch
	    * pages based on pageids,age,gender
	    */
	   List<Page> listPagesWithFbIds(String ids,String dbQualifier,
			   String tableQualifier);
	   /** 
	    * This method to be used to fetch
	    * users based on fbids,age,gender
	    */
	   List<User> listUsersWithFbIds(String fbIds,String dbQualifier,
			   String tableQualifier) ;
	   
	   void emptyIndexTables();
	   
	   /** 
	    * This method to be used to batch insert records in the TapPageMapping Table
	    */	   
	   public void insertBatchTagsToPageID(final List<TagPage> tagPagesBatchList,String dbQualifier,String tableQualifier);
	   
	   /** 
	    * This method to be used to batch insert records in the PageDetails Table
	    */	   
	   public void insertBatchPageIDToFBID(final List<Page> pagesBatchList,String dbQualifier,String tableQualifier) ;

}
