package com.sbs.cuni.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component("needToLoginInterceptor")
public class NeedToLoginInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean isLogined = (boolean) request.getAttribute("isLogined");

		if (isLogined == false) {
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().append("<script>");
			response.getWriter().append("alert('로그인 후 이용해주세요.');");
			response.getWriter().append("location.replace('/member/login');");
			response.getWriter().append("</script>");
			return false;
		}

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}