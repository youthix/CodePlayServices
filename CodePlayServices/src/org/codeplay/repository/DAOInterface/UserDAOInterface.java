package org.codeplay.repository.DAOInterface;

import java.util.List;

import javax.sql.DataSource;

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
	   /** 
	    * This is the method to be used to list down
	    * a record from the Student table corresponding
	    * to a passed student id.
	    */
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
	    * This is the method to be used to update
	    * a record into the Student table.
	    */
	   //public void update();

}
