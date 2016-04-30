package org.codeplay.repository.dbUtilities;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


public class ConnectionFactory
{

    private static MysqlDataSource mysqlDS;
    private static DriverManagerDataSource driverManagerDataSource;

    private ConnectionFactory()
    {
    }

    private static void initializeDataSource(String dbName)
    {

    	
        try
        {        	
        	String DBURLConstant = "jdbc:mysql://mysql17939-dev-codeplay.cloud.cms500.com:3306/" ;//For Localhost use "jdbc:mysql://localhost:3306/"
        	String userName = "root";
        	String password= "YXRoxi95512";//For Localhost use "root"
        	String DB_URL = DBURLConstant + dbName ;
            mysqlDS = new MysqlDataSource();
            mysqlDS.setURL(DB_URL);
            mysqlDS.setUser(userName);
            mysqlDS.setPassword(password);
            
            driverManagerDataSource = new DriverManagerDataSource();
            driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
            driverManagerDataSource.setPassword(password);
            driverManagerDataSource.setUrl(DB_URL);
            driverManagerDataSource.setUsername(userName);
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(String dbName)
    {
        Connection con = null;
        try
        {

                initializeDataSource(dbName);
                con = mysqlDS.getConnection();

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return con;
    }

	public static MysqlDataSource getMysqlDS() {
		return mysqlDS;
	}

	public static DriverManagerDataSource getDriverManagerDataSource() {
		return driverManagerDataSource;
	}
	
	
    
}
