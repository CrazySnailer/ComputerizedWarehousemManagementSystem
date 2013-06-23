package com.coe.bim;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import com.coe.common.ConnectionManager;

public class VendorDAO
{
    @SuppressWarnings("finally")
    public boolean addVendor(VendorVO vo)
    {
        boolean result=false;
       
        Connection conn=ConnectionManager.getInstance().getConnection();
        Statement stmt=null;
        try
        {
            stmt=conn.createStatement();
            StringBuilder sql=new StringBuilder();
            sql.append("insert into vendor(vendor_Name,city_Id,address,postcode,telphone,contact,status) values('");
            sql.append(vo.getVendorName());
            sql.append("','");
            sql.append(vo.getCityId());
            sql.append("','");
            sql.append(vo.getAddress());
            sql.append("','");
            sql.append(vo.getPostcode());
            sql.append("','");
            sql.append(vo.getTelphone());
            sql.append("','");
            sql.append(vo.getContact());
            sql.append("','");
            sql.append(vo.getStatus());
            sql.append("')");
            System.out.println(sql.toString());
            stmt.execute(sql.toString());
            result=true;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            result=false;
        }
        finally
        {
            ConnectionManager.close(null, stmt, conn);
            return result;
        }
    }
    // public boolean updateVendor(VendorVO vdendor)
    // public boolean deleteVendor(VendorVO vdendor)
    // public VendorForm searchVendor(String vendorName)
    // public ArrayList searchAllVendor()
}
