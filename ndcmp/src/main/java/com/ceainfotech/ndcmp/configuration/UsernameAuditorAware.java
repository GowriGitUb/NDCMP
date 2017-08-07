/**
 * 
 */
package com.ceainfotech.ndcmp.configuration;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * @author Samy
 * 
 */
public class UsernameAuditorAware implements AuditorAware<String> {

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.AuditorAware#getCurrentAuditor()
	 */
	@Override
	public String getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal() == null
				|| authentication.getPrincipal().equals("anonymousUser")) {
			return "System";
		}
		return ((User) authentication.getPrincipal()).getUsername();
	}
}
