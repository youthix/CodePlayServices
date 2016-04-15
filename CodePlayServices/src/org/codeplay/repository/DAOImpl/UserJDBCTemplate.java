
package org.codeplay.repository.DAOImpl;

import java.util.List;

import javax.sql.DataSource;

import org.codeplay.repository.BObjects.User;
import org.codeplay.repository.DAOInterface.UserDAOInterface;
import org.codeplay.repository.Mapper.UserMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author saurabh
 *
 */
public class UserJDBCTemplate implements UserDAOInterface {
	
	   private DataSource dataSource;
	   private JdbcTemplate jdbcTemplateObject;
	   
	   public void setDataSource(DataSource dataSource) {
	      this.dataSource = dataSource;
	      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	   }
	   
	   public void create() {
		      String SQL = "insert into tags_pages_mapping_male (name, age) values (?, ?)";
		      
/*		      jdbcTemplateObject.update("name","age");
		      System.out.println("Created Record Name = " + name + " Age = " + age);*/
		      return;
		   }
	   
	   public void create(String sqlQueryString) {
		      String SQL = "insert into tags_pages_mapping_male (name, age) values (?, ?)";
		      
		      jdbcTemplateObject.update(sqlQueryString);

		      return;
		   }	   

		   public User getUser(Integer id) {
		      String SQL = "select * from users_sorted_male where id = ?";
		      User user = jdbcTemplateObject.queryForObject(SQL, 
		                        new Object[]{id}, new UserMapper());
		      return null;
		   }

		   public List<User> listUsers(String sqlQueryString) {
/*		      //String SQL = "select * from users_sorted_male";
		      String SQL = "select * from users_sorted_male group by tags";*/
		      List <User> users = jdbcTemplateObject.query(sqlQueryString, 
		                                new UserMapper());
		      return users;
		   }

		   public void delete(Integer id){
		      String SQL = "delete from tags_pages_mapping_male where id = ?";
		      /*jdbcTemplateObject.update(SQL, id);*/
		      System.out.println("Deleted Record with ID = " + id );
		      return;
		   }

		   public void update(Integer id, Integer age){
		      String SQL = "update tags_pages_mapping_male set age = ? where id = ?";
		      /*jdbcTemplateObject.update(SQL, age, id);*/
		      System.out.println("Updated Record with ID = " + id );
		      return;
		   }



}
