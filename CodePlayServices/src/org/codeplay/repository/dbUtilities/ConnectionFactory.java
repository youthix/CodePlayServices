package org.codeplay.repository.dbUtilities;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


public class ConnectionFactory
{
    private DriverManagerDataSource driverManagerDataSource;
    private String baseUrl;

    private ConnectionFactory()
    {
    }

    private void initializeDataSource(String dbName)
    {   	
        try
        {       	
        	String DB_URL = baseUrl+dbName;
        	driverManagerDataSource.setUrl(DB_URL);
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

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
    
}
