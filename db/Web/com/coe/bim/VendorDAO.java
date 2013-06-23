package com.coe.bim;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import com.coe.common.DBUtil;
import com.coe.bim.VendorVO;

public class VendorDAO
{
    public boolean addVendor(VendorVO vo)
    {
        boolean result=false;
        DBUtil db=new DBUtil();
        Connection conn=db.getConn();
        try
        {
            Statement stmt=conn.createStatement();
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
            stmt.close();
            conn.close();
            result=true;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            result=false;
        }
        return result;
    }

    //public boolean updateVendor(VendorVO vdendor)
    //public boolean deleteVendor(VendorVO vdendor)
    //public VendorForm searchVendor(String vendorName)
    //public ArrayList searchAllVendor()
}
