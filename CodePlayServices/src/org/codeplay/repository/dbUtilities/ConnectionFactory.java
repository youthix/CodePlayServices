package org.codeplay.repository.dbUtilities;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


public class ConnectionFactory
{

    private static MysqlDataSource mysqlDS;

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
}
