package com.sbs.cuni.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component("needToLogoutInterceptor")
public class NeedToLogoutInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean isLogined = (boolean) request.getAttribute("isLogined");

		if (isLogined) {
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().append("<script>");
			response.getWriter().append("history.back();");
			response.getWriter().append("</script>");
			return false;
		}

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}