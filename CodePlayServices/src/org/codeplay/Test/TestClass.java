/**
 * 
 */
package org.codeplay.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.codeplay.repository.RepositoryDelegate.RepositoryDelegator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author surabh
 *
 */
public class TestClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

		// Fetch Pages : Start

		/*
		 * RESTfulServiceInterface userIndObj = null; userIndObj =
		 * (RESTfulServiceInterface)context.getBean("restfulService");
		 * System.out.println("Fetch Pages : Starttime >>"+
		 * System.currentTimeMillis()); String
		 * pages=userIndObj.fetchPages("Armenia"); System.out.println(
		 * "Pages fetched >>"+ pages); System.out.println(
		 * "Fetch Pages : Endtime >>"+ System.currentTimeMillis());
		 */
		// Fetch Pages : End

		/*
		 * UserJDBCTemplate userJDBCTemplate = new UserJDBCTemplate();
		 * 
		 * ConnectionFactory.getConnection("hotornot_1519");
		 * 
		 * userJDBCTemplate.setDataSource(ConnectionFactory.
		 * getDriverManagerDataSource());
		 */

		// Test Indexing : Start

/*		List<String> dbNameList = new ArrayList<>();

		dbNameList.add("hotornot_1519");

		RepositoryDelegator userIndObj = null;

		userIndObj = (RepositoryDelegator) context.getBean("repositoryDelegator");

		userIndObj.setDbNameList(dbNameList);

		userIndObj.startIndexing();*/

		// Test Indexing : END

		/*
		 * long startTime,stopTime; ApplicationContext context = new
		 * ClassPathXmlApplicationContext("Beans.xml");
		 * 
		 * UserJDBCTemplate userJDBCTemplate =
		 * (UserJDBCTemplate)context.getBean("userJDBCTemplate");
		 * 
		 * 
		 * System.out.println("------Listing Multiple Records--------");
		 * startTime=System.currentTimeMillis(); String queryMaleString =
		 * "select * from users_sorted_male group by tags"; List<User> users =
		 * userJDBCTemplate.listUsers(queryMaleString); if (users!=null){
		 * System.out.println(users.size());
		 * 
		 * 
		 * for (User record : users) { System.out.print("ID : " +
		 * record.getId()); System.out.print(", Name : " + record.getName());
		 * System.out.println(", Age : " + record.getAge()); } }
		 */
		// stopTime=System.currentTimeMillis();
		// System.out.println("---Task completed in "+(stopTime-startTime)+"
		// millisecinds---");
		
		testDataTransfer();
		System.out.println("done");

		/*
		 * for (int i=0;i<8;i++){ ThreadLocalRandom random =
		 * ThreadLocalRandom.current(); //random. //random.setSeed(1234);
		 * System.out.println(random.nextInt()); System.out.println(random.);
		 * 
		 * }
		 */

	}

	private static void testDataTransfer() {
		System.out.println("in");
		try {

			Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1", "root", "root");

			Connection connection2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/test2", "root", "root");
			final Statement statement1 = connection1.createStatement();
			final PreparedStatement insertStatement = connection2
					.prepareStatement("insert into testtable values(?,?, ?)");
			statement1.execute("delete  from testtable");

			String insertQueryStr = "";

			for (int i = 1; i < 100; i++) {
				insertQueryStr = "insert  into testtable (id, firstname, lastname) values (" + i + ",'saurabh','agarwal')";
				System.out.println(insertQueryStr);
				statement1.execute(insertQueryStr);

			}
			
			System.out.println("in1");

			try (final ResultSet resultSet = statement1.executeQuery("select id,firstname, lastname from testtable")) {
				while (resultSet.next()) {
					// Get the values from the table1 record
					final int id = resultSet.getInt("id");
					final String firstname = resultSet.getString("firstname");
					final String lastname = resultSet.getString("lastname");

					// Insert a row with these values into table2
					insertStatement.clearParameters();
					insertStatement.setInt(1, id);
					insertStatement.setString(2, firstname);
					insertStatement.setString(3, lastname);
					insertStatement.executeUpdate();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

}
