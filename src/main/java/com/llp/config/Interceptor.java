package com.llp.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description:拦截器
 * create by Administrator
 * create on 2021/06/20/19:30
 */
public class Interceptor implements HandlerInterceptor {

    /**
     * 根据session判断是否已经登录
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object uid = request.getSession().getAttribute("uid");
        if(uid==null){
            request.setAttribute("msg","没有权限，请先登录");
            request.getRequestDispatcher("/login").forward(request,response);
            return false;
        }else{
            return true;
        }

    }
}
