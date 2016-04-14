package org.codeplay.repository.DAOImpl;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class PageJDBCTemplate {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public void create(String pageid, String fbids, String table) {
	     String SQL = "insert into page_details_male (page_id, fbids,table) values (?, ?,?)";
	      
	      jdbcTemplateObject.update( SQL, pageid, fbids, table);
/*	      System.out.println("Created Record Name = " + name + " Age = " + age);*/
	      return;
	}

}
