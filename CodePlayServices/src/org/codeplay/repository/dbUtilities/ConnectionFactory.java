package org.codeplay.repository.dbUtilities;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


public class ConnectionFactory
{
    private DriverManagerDataSource driverManagerDataSource;

    private ConnectionFactory()
    {
    }

    private void initializeDataSource(String dbName)
    {   	
        try
        {        
        	String DBURLConstant = "jdbc:mysql://localhost:3306/" ;//For JElastic use "jdbc:mysql://mysql17939-dev-codeplay.cloud.cms500.com:3306/"
        	String DB_URL = DBURLConstant+dbName;
        	driverManagerDataSource.setUrl(DB_URL); 
        	/*String DBURLConstant = "jdbc:mysql://localhost:3306/" ;//For JElastic use "jdbc:mysql://mysql17939-dev-codeplay.cloud.cms500.com:3306/"
        	String userName = "root";
        	String password= "root";//For Localhost use "root"
            driverManagerDataSource = new DriverManagerDataSource();
            driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
            driverManagerDataSource.setPassword(password);            
            driverManagerDataSource.setUsername(userName);*/

        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public Connection getConnection(String dbName)
    {
        Connection con = null;
        try
        {
                initializeDataSource(dbName);
                con = driverManagerDataSource.getConnection();

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return con;
    }

	public DriverManagerDataSource getDriverManagerDataSource() {
		return driverManagerDataSource;
	}

	public void setDriverManagerDataSource(
			DriverManagerDataSource driverManagerDataSource) {
		this.driverManagerDataSource = driverManagerDataSource;
	}
    
}
