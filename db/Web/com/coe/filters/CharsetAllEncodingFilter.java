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
     * Request.java �� HttpServletRequestWrapper ��������, ��Ӱ��ԭ���Ĺ��ܲ����ṩ���е� HttpServletRequest �ӿ��еĹ���.
     * ������ͳһ�Ķ� Tomcat Ĭ�������µ�����������н����ֻ��Ҫ���µ�Request �����滻ҳ���е� request ���󼴿�.
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
         * ת���ɱ���ȡ�����ݵ�����. �� ISO �ַ�ת�� ��web.xml�����õ�encoding.
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
         * ��ȡ���� -- ��������������.
         */
        public String getParameter(String name)
        {
            return toChi(getHttpServletRequest().getParameter(name));
        }

        /**
         * ��ȡ�����б� - ��������������.
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
            // post��ʽ���ͣ�ֱ�������ַ���
            request.setCharacterEncoding(encoding);
        }
        else
        {
            // post��ʽ���ͣ�ʹ���Լ����õ�Request��
            request=new Request(httpreq);
        }

        chain.doFilter(request, response);
    }

}
