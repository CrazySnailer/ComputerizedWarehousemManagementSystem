package com.coe.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//首页登录此Servlet，设置过滤器所使用的标志，根据权限转到导航页或返回首页
public class LoginServlet extends javax.servlet.http.HttpServlet
{
    public LoginServlet()
    {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String userName=request.getParameter("userName");
        String password=request.getParameter("password");

        request.getSession().setAttribute("sm", null);
        request.getSession().setAttribute("sq", null);
        request.getSession().setAttribute("bim", null);
        request.getSession().setAttribute("um", null);

        if(userName.equals("guest1") && password.equals("1111"))
        {
            request.getSession().setAttribute("sm", "ok");
            response.sendRedirect("common/mainpage.html");
        }
        else
            if(userName.equals("guest2") && password.equals("2222"))
            {
                request.getSession().setAttribute("sq", "ok");
                response.sendRedirect("common/mainpage.html");
            }
            else
                if(userName.equals("guest3") && password.equals("3333"))
                {
                    request.getSession().setAttribute("bim", "ok");
                    response.sendRedirect("common/mainpage.html");
                }
                else
                    if(userName.equals("guest4") && password.equals("4444"))
                    {
                        request.getSession().setAttribute("um", "ok");
                        response.sendRedirect("common/mainpage.html");
                    }
                    else
                    {
                        // Servlet默认根目录为站点，不包含目录名，这里不能加/
                        RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
                        request.setAttribute("desc", "该用户无权访问本系统!");
                        rd.forward(request, response);
                    }
    }
}