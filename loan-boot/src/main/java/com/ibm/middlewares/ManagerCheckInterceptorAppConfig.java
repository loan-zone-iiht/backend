package com.ibm.middlewares;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Class {ManagerCheckInterceptorAppConfig} is a middleware/interceptor implementor class.
 * It adds the specific middleware/interceptor to the InterceptorRegistry
 * 
 * 
 * @author Saswata Dutta
 */

@Component
public class ManagerCheckInterceptorAppConfig implements WebMvcConfigurer {
	@Autowired
	ManagerCheckInterceptor managerCheckInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// check for paths with prefix manager && manager- && manager/
		registry.addInterceptor(managerCheckInterceptor).addPathPatterns("/manager*", "/manager/**")
				.excludePathPatterns("/manager-signup", "/manager-login", "/manager-send-otp");
	}
}
