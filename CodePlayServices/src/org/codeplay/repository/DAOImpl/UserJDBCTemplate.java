
package org.codeplay.repository.DAOImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.codeplay.repository.BObjects.Page;
import org.codeplay.repository.BObjects.TagPage;
import org.codeplay.repository.BObjects.User;
import org.codeplay.repository.DAOInterface.UserDAOInterface;
import org.codeplay.repository.Mapper.PageIDDetailsMapper;
import org.codeplay.repository.Mapper.TagsPageIDMapper;
import org.codeplay.repository.Mapper.UserMapper;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.*;

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
/*		      String SQL = "insert into tags_pages_mapping_male (name, age) values (?, ?)";
		      
		      jdbcTemplateObject.update("name","age");
		      System.out.println("Created Record Name = " + name + " Age = " + age);
		      return;*/
		   }
	   
	   public void create(String sqlQueryString) {
		      /*String SQL = "insert into tags_pages_mapping_male (name, age) values (?, ?)";*/
		      
		      jdbcTemplateObject.update(sqlQueryString);

		      return;
		   }
	   public void insertBatchTagsToPageID(final List<TagPage> tagPagesBatchList,String dbQualifier,String tableQualifier) {
		   
		   final String insertTagPageString = "insert into "+ "tags_pages_mapping_" + tableQualifier
					+ "(`tags`, `page_ids`) values (?,?)";
		   

		   jdbcTemplateObject.batchUpdate(insertTagPageString, 
		                new BatchPreparedStatementSetter() {
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					TagPage tagPageObject = tagPagesBatchList.get(i);
					ps.setString(1, tagPageObject.getTags());
					ps.setString(2, tagPageObject.getPageIds());

				}

				public int getBatchSize() {
					return tagPagesBatchList.size();
				}
			});
		   }
	   public void insertBatchPageIDToFBID(final List<Page> pagesBatchList,String dbQualifier,String tableQualifier) {
		   
	   
		   final String insertPageDetailsString = "insert into page_details_" + tableQualifier
					+ "(`page_id`, `fbids`,`table`) values (?,?,?)";
		   
		   jdbcTemplateObject.batchUpdate(insertPageDetailsString, 
		                new BatchPreparedStatementSetter() {
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Page pageObject = pagesBatchList.get(i);
					ps.setString(1, pageObject.getId());
					ps.setString(2, pageObject.getFbIds());
					ps.setString(3, pageObject.getTable());

				}

				public int getBatchSize() {
					return pagesBatchList.size();
				}
			});
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
		   
		   public void emptyIndexTables(){
			      String SQL = "truncate table page_details_female;";
			      jdbcTemplateObject.update(SQL);
			      SQL = "truncate table page_details_male;";
			      jdbcTemplateObject.update(SQL);
			      SQL = "truncate table tags_pages_mapping_male;";
			      jdbcTemplateObject.update(SQL);
			      SQL = "truncate table tags_pages_mapping_female;";
			      jdbcTemplateObject.update(SQL);			      
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
		    System.out.println("Fetch listPages size>>"+pages.size());
		    System.out.println("Fetch listPages : Endtime >>"+ System.currentTimeMillis());
			return pages;
		}
		   
		   public List<Page> listPagesWithFbIds(String ids,String dbQualifier,String tableQualifier) {	
			    String dbName="hotornot_"+dbQualifier;
			    String tableName="page_details_"+tableQualifier;
			    System.out.println("Fetch listPagesWithFbIds : StartTime >>"+ System.currentTimeMillis());
				String SQL = "SELECT * FROM "+dbName+"."+tableName+" WHERE FIND_IN_SET(page_id,'"+ids+"')";
				System.out.println("Query in listPagesWithFbIds >> "+SQL);
				
			    List <Page> pages = jdbcTemplateObject.query(SQL,
				   		                                new PageIDDetailsMapper());
			    System.out.println("Fetch listPagesWithFbIds : EndTime >>"+ System.currentTimeMillis());
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
