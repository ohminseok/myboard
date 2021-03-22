package com.myboard.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.myboard.interceptor.LoggerInterceptor;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(new LoggerInterceptor()).excludePathPatterns("/css/**", "/img/**", "/js/**", "/scss/**", "/vendor/**");
		
	}
	
	
}
