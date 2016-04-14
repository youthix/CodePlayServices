package org.codeplay.repository.DAOImpl;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class TagJDBCTemplate {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public void create() {
		String SQL = "insert into tags_pages_mapping_male (name, age) values (?, ?)";

		/*
		 * jdbcTemplateObject.update("name","age"); System.out.println(
		 * "Created Record Name = " + name + " Age = " + age);
		 */
		return;
	}

}
