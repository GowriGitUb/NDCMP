/**
 * 
 */
package com.ceainfotech.ndcmp.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.service.UserService;

/**
 * @author Samy
 * 
 */
@Service
public class PrincipalUtil {
	
	@Autowired(required = true)
	public HttpServletRequest request;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(PrincipalUtil.class);

	@Autowired
	UserService userService;

	/**
	 * To get current username for logged in user
	 * 
	 * @return
	 */
	public static String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
	
	/**
	 * Validate User Existence By User Name
	 * 
	 * @param Ssoid
	 * @return
	 */
	public boolean validateUsername(String username, Integer userid) {
		LOGGER.info("Find User By User Name");

		if (userid == null) {
			User user = userService.findByUsername(username);
			if (user != null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Validate Email Existence
	 * 
	 * @param email
	 * @return
	 */
	public boolean validateEmail(String email, int userid) {
		LOGGER.info("Find User By Email");
		if (userid == 0) {
			User user = userService.findByEmail(email);
			if (user != null) {
				return false;
			}
		}
		return true;
	}
	
	public HttpSession getSession() {
		if (request != null) {
			return request.getSession();
		}
		return null;
	}
	
	/**
	 * To get current user password for logged in user
	 * 
	 * @return
	 */
	public String getCurrentPassword() {
		HttpSession session = getSession();
		Object object = session.getAttribute("password");
		if (object != null) {
			return (String) object;
		}
		return null;
	}
	
	/**
	 * To get current role Id for logged in user
	 * 
	 * @return
	 */
	public Integer getCurrentRoleId() {
		HttpSession session = getSession();
		Object object = session.getAttribute("roleId");
		if (object != null) {
			return (Integer) object;
		}
		return null;
	}
	
	/**
	 * Get Current User Role
	 */
	public String getCurrentUserRole() {
		HttpSession session = getSession();
		Object object = session.getAttribute("currentUserRole");
		if (object != null) {
			return (String) object;
		}
		return "";
	}

}
