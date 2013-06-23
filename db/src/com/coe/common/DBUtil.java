package com.coe.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

public class DBUtil
{
    private Connection conn=null;
    @SuppressWarnings("finally")
    public Connection getConn()
    {
        try
        {
            ResourceBundle rb=ResourceBundle.getBundle("database");
            Class.forName(rb.getString("driver"));
            conn=DriverManager.getConnection(rb.getString("url"), rb.getString("user"), rb.getString("pass"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return conn;
        }
    }
}

