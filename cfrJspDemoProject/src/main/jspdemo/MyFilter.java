package main.jspdemo;

import javax.servlet.*;
import java.io.IOException;

public class MyFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
        System.out.println("---MyFilter init");
        String myFilterParam = config.getInitParameter("MyFilterParam");
        System.out.println("过滤器初始化参数：" + myFilterParam);
    }
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("---doFilter");
        // 把请求传回过滤链
        chain.doFilter(request, response);
    }
    public void destroy(){
        System.out.println("---MyFilter destroy");
    }
}
