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
        //��ȡǰ̨�ύ���ݣ�����VO��
        VendorVO vo=new VendorVO();
        vo.setVendorName(request.getParameter("vendorName"));
        vo.setCityId(request.getParameter("cityId"));
        vo.setAddress(request.getParameter("address"));
        vo.setPostcode(request.getParameter("postcode"));
        vo.setTelphone(request.getParameter("telphone"));
        vo.setContact(request.getParameter("contact"));
        vo.setStatus(request.getParameter("status"));

        //���Ը���ʵ��Ӧ�ã����һϵ�в���������ֻ�����
        VendorDAO vendor=new VendorDAO();
        if(vendor.addVendor(vo))
            request.setAttribute("result", "��ӹ�Ӧ�̳ɹ�!");
        else
            request.setAttribute("result", "��ӹ�Ӧ��ʧ��!");

        //�����ύ�ɹ���񣬶����ص�addVendor.jsp
        RequestDispatcher rd=request.getRequestDispatcher("bim/addVendor.jsp");
        rd.forward(request, response);
    }
}