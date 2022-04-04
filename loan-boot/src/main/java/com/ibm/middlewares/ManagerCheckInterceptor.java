package com.ibm.middlewares;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ibm.enums.RoleOptions;
import com.ibm.exception.GlobalLoanException;

/**
 * Class {ManagerCheckInterceptor} is a middleware/interceptor class. It checks
 * if a user is of a specific role, be it Manager or Customer.
 * 
 * @author Saswata Dutta
 */

@Component
public class ManagerCheckInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

//		System.err.println(request.getHeader("role"));
		System.err.println("prehandle");
		if (request.getHeader("role") != null && request.getHeader("role").equals(RoleOptions.MANAGER.toString())) {
			return true;
		} else {
			try {
				response.setStatus(403);
				response.getWriter().write("Your role is not a manager");
				return false;
			} catch (IOException e) {
				throw new GlobalLoanException("403", "Your role is not a manager the path maybe wrong");
			}
		}
	}
}
