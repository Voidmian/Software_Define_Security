package com.sds.demo.Configuration;

import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: CORSFilter
 * @Description: TODO
 * @author: XQC
 * @date: 2021/4/2  10:22
 */
@Component
public class CORSFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        // 设置允许Cookie
        res.addHeader("Access-Control-Allow-Credentials", "true");
        // 允许http://www.xxx.com域（自行设置，这里只做示例）发起跨域请求
        res.addHeader("Access-Control-Allow-Origin", "*");
        // 设置允许跨域请求的方法
        res.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        // 允许跨域请求包含content-type
        res.addHeader("Access-Control-Allow-Headers", "Content-Type,X-CAF-Authorization-Token,sessionToken,X-TOKEN");
        if (((HttpServletRequest) request).getMethod().equals("OPTIONS")) {
            response.getWriter().println("ok");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}