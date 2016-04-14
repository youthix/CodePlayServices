package org.codeplay.repository.DAOImpl;

import java.util.List;

import javax.sql.DataSource;

import org.codeplay.repository.BObjects.User;
import org.codeplay.repository.dbUtilities.ConnectionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserIndexing {

	private List<String> dbNameList;

	public void startIndexing() {

		UserJDBCTemplate userJDBCTemplate = new UserJDBCTemplate();
		String queryMaleString = "select * from users_sorted_male group by tags";
		String queryFeMaleString = "select * from users_sorted_female group by tags";

		for (String dbName : dbNameList) {

			ConnectionFactory.getConnection(dbName);

			userJDBCTemplate.setDataSource(ConnectionFactory.getDriverManagerDataSource());
			/* doing for Male Users */

			List<User> usersUniqueTagList = userJDBCTemplate.listUsers(queryMaleString);
			if (usersUniqueTagList != null) {
				System.out.println(usersUniqueTagList.size());

				for (User record : usersUniqueTagList) {
					String concatpageIDs="";
					String newPageID = "";
					String tagvalue = record.getTags();
					String queryString = "select * from users sorted_male where tags='" +tagvalue +"'";
					List<User> users = userJDBCTemplate.listUsers(queryMaleString);
					if(users!=null && users.size()>0){

						int quotient = users.size()/30;
						int remainder = users.size()%30;
						
						if (quotient > 0){
							
							for (int i=1; i<=quotient;i++){
								newPageID = i+tagvalue;
								concatpageIDs= concatpageIDs + newPageID + "+"  ;
							}
							
						}
						if ( remainder >0){
							
							newPageID = (quotient+1)+tagvalue;
							concatpageIDs= concatpageIDs + newPageID + "+" ;
						}
						
						
						
					}
					
					
				}
			}
		}

	}
}
