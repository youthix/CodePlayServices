/**
 * 
 */
package org.codeplay.Test;

import java.util.List;

import org.codeplay.repository.BObjects.User;
import org.codeplay.repository.DAOImpl.UserJDBCTemplate;
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
		
/*		UserJDBCTemplate userJDBCTemplate = new UserJDBCTemplate();
		
		ConnectionFactory.getConnection("hotornot_1519");
		
		userJDBCTemplate.setDataSource(ConnectionFactory.getDriverManagerDataSource());*/
		
	      ApplicationContext context = 
	              new ClassPathXmlApplicationContext("Beans.xml");

	       UserJDBCTemplate userJDBCTemplate = 
	       (UserJDBCTemplate)context.getBean("userJDBCTemplate");


		System.out.println("------Listing Multiple Records--------");
		List<User> users = userJDBCTemplate.listUsers();
		for (User record : users) {
			System.out.print("ID : " + record.getId());
			System.out.print(", Name : " + record.getName());
			System.out.println(", Age : " + record.getAge());
		}

	}

}
