/**
 * 
 */
package org.codeplay.Test;

import java.util.ArrayList;
import java.util.List;

import org.codeplay.presentation.controller.Interface.RESTfulServiceInterface;
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
		
		ApplicationContext context = 
	              new ClassPathXmlApplicationContext("Beans.xml");
		
		//Fetch Pages : Start
		RESTfulServiceInterface userIndObj = null;
		
		userIndObj = 
			       (RESTfulServiceInterface)context.getBean("restfulService");
		System.out.println("Fetch Pages : Starttime >>"+ System.currentTimeMillis());
		String pages=userIndObj.fetchPages("Armenia");
		System.out.println("Pages fetched >>"+ pages);
		System.out.println("Fetch Pages : Endtime >>"+ System.currentTimeMillis());
		
		//Fetch Pages : End
		
/*		UserJDBCTemplate userJDBCTemplate = new UserJDBCTemplate();
		
		ConnectionFactory.getConnection("hotornot_1519");
		
		userJDBCTemplate.setDataSource(ConnectionFactory.getDriverManagerDataSource());*/
		
		
		//Test Insertion : Start
	
	/*List<String> dbNameList =  new ArrayList<>();
	
	dbNameList.add("hotornot_1519");
	
	RepositoryDelegator userIndObj = null;
	
	userIndObj = 
		       (RepositoryDelegator)context.getBean("repositoryDelegator");
	
	userIndObj.setDbNameList(dbNameList);
	
	userIndObj.startIndexing();*/
	
	//Test Insertion : END
		
/*		  long startTime,stopTime;
	      ApplicationContext context = 
	              new ClassPathXmlApplicationContext("Beans.xml");

	       UserJDBCTemplate userJDBCTemplate = 
	       (UserJDBCTemplate)context.getBean("userJDBCTemplate");


		System.out.println("------Listing Multiple Records--------");
		startTime=System.currentTimeMillis();
		String queryMaleString = "select * from users_sorted_male group by tags";
		List<User> users = userJDBCTemplate.listUsers(queryMaleString);
		if (users!=null){
			System.out.println(users.size());
			
		
		for (User record : users) {
			System.out.print("ID : " + record.getId());
			System.out.print(", Name : " + record.getName());
			System.out.println(", Age : " + record.getAge());
		}
		}*/
		//stopTime=System.currentTimeMillis();
		//System.out.println("---Task completed in "+(stopTime-startTime)+" millisecinds---");
	System.out.println("done");

	}

}
