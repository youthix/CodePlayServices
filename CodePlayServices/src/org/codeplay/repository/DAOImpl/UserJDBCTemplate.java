
package org.codeplay.repository.DAOImpl;

import java.util.List;

import javax.sql.DataSource;

import org.codeplay.repository.BObjects.Page;
import org.codeplay.repository.BObjects.TagPage;
import org.codeplay.repository.BObjects.User;
import org.codeplay.repository.DAOInterface.UserDAOInterface;
import org.codeplay.repository.Mapper.PageIDDetailsMapper;
import org.codeplay.repository.Mapper.TagsPageIDMapper;
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

		   public List<TagPage> listPages(String tags,String dbQualifier,String tableQualifier) {		      
			String SQL = "select * from hotornot_"+dbQualifier+".tags_pages_mapping_"+tableQualifier+" where tags like '%"+tags+"%'";
			System.out.println("Fetch listPages : StartTime >>"+ System.currentTimeMillis());
			System.out.println("Query in listPages >> "+SQL);
		    List <TagPage> pages = jdbcTemplateObject.query(SQL, 
			   		                                new TagsPageIDMapper());
		    System.out.println("Fetch listPages : Endtime >>"+ System.currentTimeMillis());
			return pages;
		}
		   
		   public List<Page> listPagesWithFbIds(String ids,String dbQualifier,String tableQualifier) {	
			    String dbName="hotornot_"+dbQualifier;
			    String tableName="page_details_"+tableQualifier;
			    System.out.println("Fetch listPagesWithFbIds : StartTime >>"+ System.currentTimeMillis());
				String SQL = "SELECT * FROM "+dbName+"."+tableName+" WHERE FIND_IN_SET(page_id,'"+ids+"')";
				System.out.println("Query in listPagesWithFbIds >> "+SQL);
				System.out.println("Fetch listPagesWithFbIds : EndTime >>"+ System.currentTimeMillis());
			    List <Page> pages = jdbcTemplateObject.query(SQL,
				   		                                new PageIDDetailsMapper());
				return pages;
			}
		   
		   public List<User> listUsersWithFbIds(String fbIds,String dbQualifier,String tableQualifier) {	
			    String dbName="hotornot_"+dbQualifier;
			    String tableName="users_sorted_"+tableQualifier;
				String SQL = "SELECT * FROM "+dbName+"."+tableName+" WHERE FIND_IN_SET(fbId,'"+fbIds+"')";
				System.out.println("Query in listUsersWithFbIds >> "+SQL);
			    List <User> users = jdbcTemplateObject.query(SQL,
				   		                                new UserMapper());
				return users;
			}

}
