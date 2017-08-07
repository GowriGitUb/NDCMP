package com.ceainfotech.ndcmp.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceainfotech.ndcmp.dao.UserDAO;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.model.UserRole;

/**
 * 
 * @author muthu
 * 
 */

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired
	private UserDAO userDAO;

	/**
	 * 
	 */
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDAO.findByUsername(username);
		if (user == null) {
			System.out.println("User not found");
			throw new UsernameNotFoundException("Username not found");
		}
		LOGGER.info("Getting all the user details {} :", user.getEmail());
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getStatus().equals("ACTIVE"),
				true, true, true, getGrantedAuthorities(user));
	}

	/**
	 * Load all the user authorities by using user details
	 * 
	 * @param user
	 * @return
	 */

	private List<GrantedAuthority> getGrantedAuthorities(User user) {

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (UserRole userRole : user.getUserRoles()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + userRole.getName()));
		}
		LOGGER.info("Getting all the authorities details {} :", authorities);
		return authorities;

	}

}
