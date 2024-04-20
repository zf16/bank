package com.chuwa.bank.security;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
//@WebFilter("/api/*") // same as registrationBean.addUrlPatterns("/api/*")
public class LoggingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化方法，可以执行一些初始化操作
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 记录请求信息
        log.info("Request URL: {}", request.getRequestURL());
        log.info("Request Method: {}", request.getMethod());
        log.info("Request Parameters: {}", request.getParameterMap());

        long startTime = System.currentTimeMillis();

        // 继续执行过滤器链
        filterChain.doFilter(request, response);

        //记录响应信息
        long duration = System.currentTimeMillis() - startTime;

        log.info("{} {} took {} ms", request.getMethod(), request.getRequestURI(), duration);
    }

    @Override
    public void destroy() {
        // 销毁方法，可以执行一些清理操作
        Filter.super.destroy();
    }
}
