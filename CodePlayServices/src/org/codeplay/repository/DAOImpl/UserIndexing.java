package org.codeplay.repository.DAOImpl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.codeplay.repository.BObjects.User;
import org.codeplay.repository.dbUtilities.ConnectionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserIndexing {

	private List<String> dbNameList;
	private UserJDBCTemplate userJDBCTemplate;

	public void startIndexing() {

		userJDBCTemplate = new UserJDBCTemplate();

		for (String dbName : dbNameList) {

			ConnectionFactory.getConnection(dbName);

			userJDBCTemplate.setDataSource(ConnectionFactory.getDriverManagerDataSource());
			/* doing for Male Users */

			createIndexTables();
		}

	}

	private void createIndexTables() {
		List<String> genderList = new ArrayList<>();
		genderList.add("male");
		genderList.add("female");

		for (String gender : genderList) {
			System.out.println("Start for Gender = " + gender);
			String queryStringTags = "select * from users_sorted_" + gender + " group by tags";
			List<User> usersUniqueTagList = userJDBCTemplate.listUsers(queryStringTags);
			if (usersUniqueTagList != null) {
				System.out.println(usersUniqueTagList.size());

				for (User record : usersUniqueTagList) {
					String concatpageIDs = "";
					String newPageID = "";
					String userFBIdsConcat= "";
					String tagvalue = record.getTags();
					String queryStringUsers = "select * from users_sorted_" + gender + " where tags='" + tagvalue + "'";
					List<User> users = userJDBCTemplate.listUsers(queryStringUsers);
					if (users != null && users.size() > 0) {
						
						for(User userObj : users){
							
							userFBIdsConcat = userFBIdsConcat + "~~"+userObj.getFbId();
							
						}

						int quotient = users.size() / 30;
						int remainder = users.size() % 30;

						if (quotient > 0) {

							for (int i = 1; i <= quotient; i++) {
								newPageID = i + tagvalue;
								concatpageIDs = concatpageIDs + newPageID + "~~";
							}

						}
						if (remainder > 0) {

							newPageID = (quotient + 1) + tagvalue;
							concatpageIDs = concatpageIDs + newPageID + "~~";
						}

					}
					String insertTagPageString = "insert into tags_pages_mapping_" + gender
							+ "(tags, page_ids) values ('" + tagvalue + "','" + concatpageIDs + "')";
					userJDBCTemplate.create(insertTagPageString);
					
					String insertPagetailsDeString = "insert into page_details_" + gender
							+ "(page_id, fbids,table) values ('" + concatpageIDs + "','" + userFBIdsConcat + "','tab1')";
					//userJDBCTemplate.create(insertPagetailsDeString);

				}
			}
			
			System.out.println("Done for Gender = " + gender);

		}

	}

	public List<String> getDbNameList() {
		return dbNameList;
	}

	public void setDbNameList(List<String> dbNameList) {
		this.dbNameList = dbNameList;
	}

}
