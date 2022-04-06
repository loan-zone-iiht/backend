package com.ibm.middlewares;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(exposedHeaders = "*", allowedHeaders = "*", origins = "*")
public class ManagerCheckInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

//		System.err.println(request.getHeader("role"));
		System.err.println("prehandle "+request.getParameter("role"));
		if (request.getParameter("role") != null && request.getParameter("role").equals(RoleOptions.MANAGER.toString())) {
			return true;
		} else {
			try {
				response.setStatus(200);
//				response.getWriter().write("Your role is not a manager");
				response.sendError(200, "Your role is not a manager");
				return true;
			} catch (IOException e) {
				throw new GlobalLoanException("403", "Your role is not a manager the path maybe wrong");
			}
		}
	}
}
