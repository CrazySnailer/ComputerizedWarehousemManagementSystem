package com.coe.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coe.bim.VendorDAO;
import com.coe.bim.VendorVO;

public class VendorServlet extends javax.servlet.http.HttpServlet
{
    public VendorServlet()
    {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        //获取前台提交数据，存入VO中
        VendorVO vo=new VendorVO();
        vo.setVendorName(request.getParameter("vendorName"));
        vo.setCityId(request.getParameter("cityId"));
        vo.setAddress(request.getParameter("address"));
        vo.setPostcode(request.getParameter("postcode"));
        vo.setTelphone(request.getParameter("telphone"));
        vo.setContact(request.getParameter("contact"));
        vo.setStatus(request.getParameter("status"));

        //可以根据实际应用，添加一系列操作，如下只是入库
        VendorDAO vendor=new VendorDAO();
        if(vendor.addVendor(vo))
            request.setAttribute("result", "添加供应商成功!");
        else
            request.setAttribute("result", "添加供应商失败!");

        //不管提交成功与否，都返回到addVendor.jsp
        RequestDispatcher rd=request.getRequestDispatcher("bim/addVendor.jsp");
        rd.forward(request, response);
    }
}