package com.coe.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class CharsetAllEncodingFilter implements Filter
{

    private String encoding=null;

    public void init(FilterConfig filterConfig) throws ServletException
    {
        encoding=filterConfig.getInitParameter("encoding");
    }

    /**
     * Request.java 对 HttpServletRequestWrapper 进行扩充, 不影响原来的功能并能提供所有的 HttpServletRequest 接口中的功能.
     * 它可以统一的对 Tomcat 默认设置下的中文问题进行解决而只需要用新的Request 对象替换页面中的 request 对象即可.
     */
    class Request extends HttpServletRequestWrapper
    {

        private String encoding=null;

        public Request(HttpServletRequest request)
        {
            super(request);
            encoding=CharsetAllEncodingFilter.this.encoding;
        }

        /**
         * 转换由表单读取的数据的内码. 从 ISO 字符转到 在web.xml中设置的encoding.
         */
        public String toChi(String input)
        {
            if(input == null)
            {
                return null;
            }
            else
            {
                try
                {
                    byte[] bytes=input.getBytes("ISO8859-1");
                    return new String(bytes, encoding);
                }
                catch(Exception ex)
                {

                }
                return null;
            }
        }

        /**
         * Return the HttpServletRequest holded by this object.
         */
        private HttpServletRequest getHttpServletRequest()
        {
            return (HttpServletRequest) super.getRequest();
        }

        /**
         * 读取参数 -- 修正了中文问题.
         */
        public String getParameter(String name)
        {
            return toChi(getHttpServletRequest().getParameter(name));
        }

        /**
         * 读取参数列表 - 修正了中文问题.
         */
        public String[] getParameterValues(String name)
        {
            String values[]=getHttpServletRequest().getParameterValues(name);
            if(values != null)
            {
                for(int i=0; i < values.length; i++)
                {
                    values[i]=toChi(values[i]);
                }
            }
            return values;
        }
    } // end class Request

    public void destroy()
    {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
        HttpServletRequest httpreq=(HttpServletRequest) request;
        if(httpreq.getMethod().equals("POST"))
        {
            // post方式发送，直接设置字符集
            request.setCharacterEncoding(encoding);
        }
        else
        {
            // post方式发送，使用自己设置的Request类
            request=new Request(httpreq);
        }

        chain.doFilter(request, response);
    }

}
