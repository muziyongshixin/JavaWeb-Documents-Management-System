
package com.lutsoft.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter
{
    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException 
    {
        try 
        {
            request.setCharacterEncoding("utf-8");
            chain.doFilter(request, response);
        } catch (Throwable t) 
        {   
            t.printStackTrace();
        }
    }
    public void destroy(){  }
    public void init(FilterConfig filterConfig) {        }
    
}

