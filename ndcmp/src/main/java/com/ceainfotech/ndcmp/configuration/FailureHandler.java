/**
 * 
 */
package com.ceainfotech.ndcmp.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.service.UserService;

/**
 * @author samy
 *
 */
@Component
public class FailureHandler implements AuthenticationFailureHandler{

	@Autowired
	UserService userService;
	
	 
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		session.removeAttribute("invaliduser");
		session.removeAttribute("invalidUserStatus");
		session.removeAttribute("invalidRoleStatus");
		
		String username = request.getParameter("username");
		User user = userService.findByUsername(username);
		if(user != null){
			if(user.getStatus().equals("INACTIVE")){
				session.setAttribute("invalidUserStatus", "You are inactive user,Kindly approch admin");
				response.sendRedirect(request.getContextPath() + "/login");
			}else{
				session.setAttribute("invaliduser", "Invalid Username and Password");
				response.sendRedirect(request.getContextPath() + "/login");
			}
		}else{
			session.setAttribute("invaliduser", "Invalid Username and Password");
			response.sendRedirect(request.getContextPath() + "/login");
		}
		
		
		
		/*if(companyValue == null){
			session.setAttribute("superAdminErrorMessage", superAdminErrorMessage);
			response.sendRedirect(request.getContextPath() + "/superadminlogin");
		}else{
			session.setAttribute("errormsg", errormsg);
			response.sendRedirect(request.getContextPath() + "/login");
		}*/
	}
}
