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
        	String DBURLConstant = "jdbc:mysql://localhost:3306/" ;
        	String DB_URL = DBURLConstant + dbName ;
            mysqlDS = new MysqlDataSource();
            mysqlDS.setURL("DB_URL");
            mysqlDS.setUser("root");
            mysqlDS.setPassword("root");
            
            driverManagerDataSource = new DriverManagerDataSource();
            driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
            driverManagerDataSource.setPassword("root");
            driverManagerDataSource.setUrl(DB_URL);
            driverManagerDataSource.setUsername("root");
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
