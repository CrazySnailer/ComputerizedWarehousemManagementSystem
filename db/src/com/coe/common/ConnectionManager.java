package com.coe.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

/**
 * ���ݿ����Ӳ�����
 * @author ����
 */
public final class ConnectionManager
{
    private static ConnectionManager instance;
    private static ComboPooledDataSource ds;

    // ��ʼ��,ִֻ��һ��
    static
    {
        ResourceBundle rb=ResourceBundle.getBundle("c3p0");
        ds=new ComboPooledDataSource();
        try
        {
            ds.setDriverClass(rb.getString("driver"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        ds.setJdbcUrl(rb.getString("url"));
        ds.setUser(rb.getString("username"));
        ds.setPassword(rb.getString("password"));
    }

    /**
     * ��ȡ���ݿ�ʵ��
     * @return ���Ӷ���ConnectionManager
     */
    public synchronized static final ConnectionManager getInstance()
    {
        if(instance == null)
        {
            try
            {
                instance=new ConnectionManager();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        return instance;
    }

    /**
     * ��ȡ���ݿ�����
     * @return ���ݿ����Ӷ���Connection
     */
    public synchronized final Connection getConnection()
    {
        try
        {
            // �鿴�������
            // System.out.println("------->busy connections: " + ds.getNumBusyConnections());
            return ds.getConnection();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * �ر����ݿ�����
     * @return void
     */
    public static void close(ResultSet rs, Statement stmt, Connection con)
    {
        try
        {
            if(rs != null)
                rs.close();
            if(stmt != null)
                stmt.close();
            if(con != null)
                con.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * �ͷ����ݿ���Դ
     * @return void
     */
    @Override
    protected void finalize() throws Throwable
    {
        // �ر�datasource
        DataSources.destroy(ds);
        super.finalize();
    }

}
