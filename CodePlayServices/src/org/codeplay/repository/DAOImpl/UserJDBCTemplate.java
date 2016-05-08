
package org.codeplay.repository.DAOImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
		/*
		 * String SQL =
		 * "insert into tags_pages_mapping_male (name, age) values (?, ?)";
		 * 
		 * jdbcTemplateObject.update("name","age"); System.out.println(
		 * "Created Record Name = " + name + " Age = " + age); return;
		 */
	}

	public void create(String sqlQueryString) {
		/*
		 * String SQL =
		 * "insert into tags_pages_mapping_male (name, age) values (?, ?)";
		 */

		jdbcTemplateObject.update(sqlQueryString);

		return;
	}
	
	public void createUser(User user) {
		
		  String SQL =
		  "insert into hotornot.users_sorted (`name`, `profile_picture`,"
		  + " `fbId`, `gender`, `lives_in`, `live_in_id`, `lives_in_country`, `home_town`, "
		  + "`home_town_id`, `home_town_country`, `currently_at`, `currently_at_id`, `date_of_birth`, "
		  + " `app_name`, `raw_data`,`email`,`logouttime`) values ('"+user.getName()+"',' "
		  + user.getProfilePicture()+"','"+user.getFbId()+"','"+ user.getGender()+"','"+ user.getLivesIn()+"','"+ user.getLivesInId()+"','"
		  + user.getLivesInCountry()+"','"+ user.getHomeTown()+"','"+ user.getHomeTownId()+"','"+ user.getHomeTownCountry()+"','"+ user.getCurrentlyAt()+"','"
		  + user.getCurrentlyAtId()+"','"+ user.getDateOfBirth()+"','"+ user.getAppName()+"','"+ user.getRawData()+"','"+ user.getEmail()
		  		+ "',null)";	 

		jdbcTemplateObject.update(SQL);

		return;
	}
	
	public void deleteUser(User user) {
		
		  String SQL =
		  "delete from hotornot.users_sorted where fbid='"+user.getFbId()+"'";
		  jdbcTemplateObject.update(SQL);
		  
		  SQL =
		  "delete from hotornot_"+user.getAgeGroup()+".users_sorted_"+user.getGender()+" where fbid='"+user.getFbId()+"'";
		  jdbcTemplateObject.update(SQL);

		 return;
	}
	
	
	public void updateUser(User user) {
		
		  String SQL =
		  "update hotornot.users_sorted set `name`='"+user.getName()+"',"
		  +"`profile_picture`='"+user.getProfilePicture()+"',`gender`='"+user.getGender()+"',`lives_in`='"+user.getLivesIn()+"',"
		  +"`live_in_id`='"+user.getLivesInId()+"', `lives_in_country`='"+user.getLivesInCountry()+"',`home_town`='"+user.getHomeTown()+"',`home_town_id`='"+user.getHomeTownId()+"',"
		  +"`home_town_country`='"+user.getHomeTownCountry()+"',`currently_at`='"+user.getCurrentlyAt()+"',`currently_at_id`='"+user.getCurrentlyAtId()
		  +"',`date_of_birth`='"+user.getDateOfBirth()+"',"
		  +"`age`='"+user.getAge()+"',"
		  +"`app_name`='"+user.getAppName()+"',`raw_data`='"+user.getRawData()+"',`email`='"+user.getEmail()+"',`logintime`=CURRENT_TIMESTAMP WHERE fbId='"
		  +user.getFbId()+"'";
		  jdbcTemplateObject.update(SQL);
		  
		  SQL =
		  "update hotornot_"+user.getAgeGroup()+".users_sorted_"+user.getGender()+" set `name`='"+user.getName()+"',"
		  +"`profile_picture`='"+user.getProfilePicture()+"',`gender`='"+user.getGender()+"',`lives_in`='"+user.getLivesIn()+"',"
		  +"`live_in_id`='"+user.getLivesInId()+"', `lives_in_country`='"+user.getLivesInCountry()+"',`home_town`='"+user.getHomeTown()+"',`home_town_id`='"+user.getHomeTownId()+"',"
		  +"`home_town_country`='"+user.getHomeTownCountry()+"',`currently_at`='"+user.getCurrentlyAt()+"',`currently_at_id`='"+user.getCurrentlyAtId()
		  +"',`date_of_birth`='"+user.getDateOfBirth()+"',"
		  +"`age`='"+user.getAge()+"',"
		  +"`app_name`='"+user.getAppName()+"',`raw_data`='"+user.getRawData()+"',`email`='"+user.getEmail()+"' WHERE fbId='"+user.getFbId()+"'";
		  jdbcTemplateObject.update(SQL);
				  
		return;
	}
	
	public void updateLogOut(String fbIdParam) {
		
		  String SQL =
		  "update hotornot.users_sorted set `logintime`=CURRENT_TIMESTAMP WHERE fbId='"
		  +fbIdParam+"'";
		  jdbcTemplateObject.update(SQL);
		  
				  
		return;
	}	

	public void insertBatchTagsToPageID(final List<TagPage> tagPagesBatchList, String dbQualifier,
			String tableQualifier) {
		try {
			Connection con = jdbcTemplateObject.getDataSource().getConnection();
			con.setAutoCommit(false);
			Statement stmt = con.createStatement();
			for (TagPage tp : tagPagesBatchList) {

				stmt.addBatch("insert into " + "tags_pages_mapping_" + tableQualifier + "(`tags`, `page_ids`) values ('"
						+ tp.getTags() + "','" + tp.getPageIds() + "')");

			}
			// Create an int[] to hold returned values
			int[] count = stmt.executeBatch();

			// Explicitly commit statements to apply changes
			con.commit();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertBatchPageIDToFBID(final List<Page> pagesBatchList, String dbQualifier,
			String tableQualifier) {

		try {
			Connection con = jdbcTemplateObject.getDataSource().getConnection();
			con.setAutoCommit(false);
			Statement stmt = con.createStatement();
			for (Page p : pagesBatchList) {
				stmt.addBatch("insert into page_details_" + tableQualifier + "(`page_id`, `fbids`,`table`) values ('"
						+ p.getId() + "','" + p.getFbIds() + "','" + p.getTable() + "')");
			}
			// Create an int[] to hold returned values
			int[] count = stmt.executeBatch();

			// Explicitly commit statements to apply changes
			con.commit();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertIndexingInfo(String db,String pageId){
		String SQL="insert into hotornot.indexing_info (`id`,`lastPageId`) values ('"+db+"','"+pageId+"')";
		jdbcTemplateObject.update(SQL);		  
		return;
	}
	
	public void updateIndexingInfo(String db,String pageId){
		String SQL="update hotornot.indexing_info set `lastPageId`= '"+pageId+"',executionDate=CURRENT_TIMESTAMP where id='"+db+"'";
		jdbcTemplateObject.update(SQL);		  
		return;
	}

	public User getUser(Integer id) {
		String SQL = "select * from users_sorted_male where id = ?";
		User user = jdbcTemplateObject.queryForObject(SQL, new Object[] { id }, new UserMapper());
		return null;
	}

	public User getUser(User userParam) {
		
		User userRetObj = null;
		
		String SQL = "select * from hotornot.users_sorted where fbId ='" +userParam.getFbId() + "'";
		
		List<User> users = jdbcTemplateObject.query(SQL, new UserMapper());
		if (null!=users && users.size()>0){
			userRetObj = users.get(0);
		}
		return userRetObj;
	}

	public List<User> listUsers(String sqlQueryString) {
		/*
		 * //String SQL = "select * from users_sorted_male"; String SQL =
		 * "select * from users_sorted_male group by tags";
		 */
		List<User> users = jdbcTemplateObject.query(sqlQueryString, new UserMapper());
		return users;
	}

	public List<User> listUsers(String tags, String dbQualifier, String tableQualifier) {
		String SQL = "select * from hotornot_" + dbQualifier + ".users_sorted_" + tableQualifier + " where tags like '%"
				+ tags + "%'";
		List<User> users = jdbcTemplateObject.query(SQL, new UserMapper());
		return users;
	}

	public void delete(Integer id) {
		String SQL = "delete from tags_pages_mapping_male where id = ?";
		/* jdbcTemplateObject.update(SQL, id); */
		System.out.println("Deleted Record with ID = " + id);
		return;
	}

	public void emptyIndexTables(String dbName) {
		String SQL = "truncate table "+dbName+".page_details_female;";
		jdbcTemplateObject.update(SQL);
		SQL = "truncate table "+dbName+".page_details_male;";
		jdbcTemplateObject.update(SQL);
		SQL = "truncate table "+dbName+".tags_pages_mapping_male;";
		jdbcTemplateObject.update(SQL);
		SQL = "truncate table "+dbName+".tags_pages_mapping_female;";
		jdbcTemplateObject.update(SQL);
		SQL = "delete from hotornot.indexing_info where id='"+dbName+"';";
		jdbcTemplateObject.update(SQL);
		return;
	}

	public void update(Integer id, Integer age) {
		String SQL = "update tags_pages_mapping_male set age = ? where id = ?";
		/* jdbcTemplateObject.update(SQL, age, id); */
		System.out.println("Updated Record with ID = " + id);
		return;
	}

	public List<TagPage> listPages(String tags, String dbQualifier, String tableQualifier) {
		String SQL = "select * from hotornot_" + dbQualifier + ".tags_pages_mapping_" + tableQualifier
				+ " where tags like '%" + tags + "%'";
		System.out.println("Fetch listPages : StartTime >>" + System.currentTimeMillis());
		System.out.println("Query in listPages >> " + SQL);
		List<TagPage> pages = jdbcTemplateObject.query(SQL, new TagsPageIDMapper());
		System.out.println("Fetch listPages size>>" + pages.size());
		System.out.println("Fetch listPages : Endtime >>" + System.currentTimeMillis());
		return pages;
	}

	public List<Page> listPagesWithFbIds(String ids, String dbQualifier, String tableQualifier) {
		String dbName = "hotornot_" + dbQualifier;
		String tableName = "page_details_" + tableQualifier;
		System.out.println("Fetch listPagesWithFbIds : StartTime >>" + System.currentTimeMillis());
		String SQL = "SELECT * FROM " + dbName + "." + tableName + " WHERE FIND_IN_SET(page_id,'" + ids + "')";
		System.out.println("Query in listPagesWithFbIds >> " + SQL);

		List<Page> pages = jdbcTemplateObject.query(SQL, new PageIDDetailsMapper());
		System.out.println("Fetch listPagesWithFbIds : EndTime >>" + System.currentTimeMillis());
		return pages;
	}

	public List<User> listUsersWithFbIds(String fbIds, String dbQualifier, String tableQualifier) {
		String dbName = "hotornot_" + dbQualifier;
		String tableName = "users_sorted_" + tableQualifier;
		String SQL = "SELECT * FROM " + dbName + "." + tableName + " WHERE FIND_IN_SET(fbId,'" + fbIds + "') AND active='Y'";
		System.out.println("Query in listUsersWithFbIds >> " + SQL);
		List<User> users = jdbcTemplateObject.query(SQL, new UserMapper());
		return users;
	}

}
