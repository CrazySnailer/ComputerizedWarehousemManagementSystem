package com.coe.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BimFilter implements Filter
{
    private FilterConfig filterConfig=null;

    public BimFilter()
    {
        super();
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
        HttpServletRequest req=(HttpServletRequest) request;
        HttpServletResponse res=(HttpServletResponse) response;
        String flag=(String) req.getSession().getAttribute("bim");
        System.out.println("==================>enter bim directory: " + flag);
        if(flag != null && flag.equals("ok"))
        {
            chain.doFilter(req, res);
        }
        else
        {
            RequestDispatcher rd=req.getRequestDispatcher("/common/nogrant.jsp");
            req.setAttribute("desc", "bim");
            rd.forward(req, res);
        }

    }

    public void init(FilterConfig config) throws ServletException
    {
        filterConfig=config;
    }

    public void destroy()
    {}
}