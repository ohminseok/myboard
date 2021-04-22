package com.myboard.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoggerInterceptor implements HandlerInterceptor {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//컨트롤러에 접근하기 전
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception { 
		// URL 정보 파악
		logger.debug("==============================");
		logger.debug("=========BEGIN=========");
		logger.debug("Request URI ===> " + request.getRequestURI());
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	//컨트롤러를 경유한 다음, 화면으로 결과를 전달하기 전
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception { 
		// 요청에 끝
		logger.debug("=================END===================");
		logger.debug("=======================================");
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	
}
