package org.codeplay.repository.DAOImpl;

import java.util.List;

import javax.sql.DataSource;

import org.codeplay.repository.dbUtilities.ConnectionFactory;
import org.springframework.jdbc.core.JdbcTemplate;



public class UserIndexing {
	
	private List<String> dbNameList;
	
	   public void startIndexing() {
		   
		   for(String dbName : dbNameList){
			   
				UserJDBCTemplate userJDBCTemplate = new UserJDBCTemplate();
				
				ConnectionFactory.getConnection(dbName);
				
				userJDBCTemplate.setDataSource(ConnectionFactory.getDriverManagerDataSource());			   
			   
		   }
		   

		   }
}
	


