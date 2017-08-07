/**
 * 
 */
package com.ceainfotech.ndcmp.configuration;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ceainfotech.ndcmp.model.Agency;
import com.ceainfotech.ndcmp.model.ProfileImage;
import com.ceainfotech.ndcmp.model.UserRole;
import com.ceainfotech.ndcmp.repository.AgencyRepository;
import com.ceainfotech.ndcmp.repository.ProfileImageRepository;
import com.ceainfotech.ndcmp.repository.UserRepository;
import com.ceainfotech.ndcmp.service.AgencyService;
import com.ceainfotech.ndcmp.service.UserRoleService;
import com.ceainfotech.ndcmp.service.UserService;
import com.ceainfotech.ndcmp.util.ConstantUtil;

/**
 * @author Samy
 * 
 */
@Component
public class CustomLoginHandler implements AuthenticationSuccessHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomLoginHandler.class);

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private ProfileImageRepository profileImageRepository;
	
	@Autowired
	private AgencyService agencyService;
	
	@Autowired
	private ConstantUtil constantUtil;
	
	/**
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.
	 *      AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.
	 *      HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 *      org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication)
			throws IOException, ServletException {
		String agencyCode="",user_Role="";
		HttpSession session = httpServletRequest.getSession();
		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		com.ceainfotech.ndcmp.model.User user=userRepository.findByUsername(authUser.getUsername());
		
		ProfileImage profileImage=profileImageRepository.findByUser(user);
		LOGGER.info("Getting login success {}" + authUser.getUsername());

		// session.setAttribute("name", authUser.getUsername()+ " " +
		// authUser.getUser().getLastName());
		session.setAttribute("password", authUser.getPassword());
		session.setAttribute("username", authUser.getUsername());
		com.ceainfotech.ndcmp.model.User currentUser = userService.findByUsername(authUser.getUsername());
		List<UserRole> useRole=currentUser.getUserRoles();
		String us = user.getUserType();
		if(us.contains("Web")){
		for (UserRole userRole : useRole) {
			if(userRole != null && userRole.getStatus().equals("ACTIVE")){
		if(currentUser != null ){
			Agency agency=agencyService.findByAgencyId(currentUser.getAgencyId());
			if(agency==null){
				agencyCode="";
			}else{
				agencyCode=agency.getCode();
				session.setAttribute("agencyCode", " (" + agencyCode + ") ");
			}
			user_Role=userRole.getDescription();
			if(user_Role != null && !user_Role.isEmpty()){
				session.setAttribute("userRole", user_Role);
				session.setAttribute("currentUserRole", userRole.getName());
			}
			String loginUser=currentUser.getFirstName() + " " + currentUser.getLastName();
			try {
				constantUtil.saveLoggingHistory(loginUser,user_Role,agencyCode,httpServletRequest);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(currentUser != null && currentUser.getId() != null && currentUser.getFirstName() != null && currentUser.getLastName() != null && userRole.getStatus().equals("ACTIVE") ) {
			session.setAttribute("name", currentUser.getFirstName() + "" + currentUser.getLastName());
		}
		session.setAttribute("authorities", authentication.getAuthorities());
		
		
		session.setAttribute("name", user.getFirstName() + " "+user.getLastName());
		if(profileImage != null){
		session.setAttribute("profileImage", profileImage.getProfileimg());
		}
		com.ceainfotech.ndcmp.model.User user2 = userService.findByUsername(authUser.getUsername());
		if(user2 != null){
			List<UserRole> userRoles = user.getUserRoles();
			for (UserRole userRole2 : userRoles) {
				userRole = userRoleService.findByRoleId(userRole2.getId());
				break;
			}
			session.setAttribute("roleId", userRole.getId());
		}

		// set our response to OK status
		httpServletResponse.setStatus(HttpServletResponse.SC_OK);

		// since we have created our custom success handler, its up to us to
		// where
		// we will redirect the user after successfully login
		httpServletResponse.sendRedirect("api/home");
	}if(userRole.getStatus().equals("INACTIVE")) {
		httpServletResponse.sendRedirect("login?role");
		break;
	}
	
	if(user.getStatus().equals("INACTIVE")) {
		httpServletResponse.sendRedirect("login?status");
		break;
	}
}
	}else {
		httpServletResponse.sendRedirect("login?type");
	}
	}

}
