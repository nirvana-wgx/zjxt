package com.ctevs.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ctevs.common.Constants;
import com.ctevs.service.TokenService;

@Component
public class AppInterceptor extends TimeoutWriter implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(Constants.TOKEN);
        String userId = request.getHeader(Constants.USERID);
        
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter(Constants.TOKEN);
        }
        if (StringUtils.isEmpty(userId)) {
        	    userId = request.getParameter(Constants.USERID);
        }
        if (tokenService.isValidToken(token, userId)) {
            return true;
        } 
        
        return true;
        //writeTimeOutMsg(response);
       // return false;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
