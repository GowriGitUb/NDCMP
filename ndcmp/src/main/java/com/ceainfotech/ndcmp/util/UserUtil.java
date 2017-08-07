/**
 * 
 */
package com.ceainfotech.ndcmp.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ceainfotech.ndcmp.model.Countries;
import com.ceainfotech.ndcmp.model.States;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.model.UserRole;
import com.ceainfotech.ndcmp.repository.CountryRepository;
import com.ceainfotech.ndcmp.repository.StateRepository;
import com.ceainfotech.ndcmp.repository.UserRepository;
import com.ceainfotech.ndcmp.repository.UserRoleRepository;

/**
 * @author Samy
 * 
 */
@Component
public class UserUtil {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public void saveSuperAdmin(String username) {
		User user = userRepository.findByUsername(username);
		
		if (user == null || user.getId() == null) {
			user = new User();
			user.setFirstName("Super");
			user.setLastName("Administrator");
			user.setUsername(username);
			String encryptedPassword = passwordEncoder.encode(username);
			user.setPassword(encryptedPassword);
			user.setEmail("admin@gmail.com");
			user.setPhone("9894573724");
			user.setAddress1("Abu South");
			user.setAddress2("");
			user.setAddress3("");
			Countries country = countryRepository.findByCode("NIG");
			if (country == null || country.getId() == null) {
				country = new Countries();
				country.setCode("NIG");
				country.setName("Nigeria");
				country.setStatus(Status.ACTIVE.getName());
				countryRepository.save(country);
			}
			user.setCountry(country);
			States state = stateRepository.findByCode("AB");
			if (state == null || state.getId() == null) {
				state = new States();
				state.setCode("AB");
				state.setName("Abia");
				state.setCountry(country);
				state.setStatus(Status.ACTIVE.getName());
				stateRepository.save(state);
			}
			user.setState(state);
			user.setZipcode("635653");
			user.setStatus(Status.ACTIVE.getName());
			UserRole getUserRole = userRoleRepository.findByName("SUPER_ADMIN");
			List<UserRole> userRoles = new ArrayList<UserRole>();
			if (getUserRole == null || getUserRole.getId() == null) {
				UserRole userRole = new UserRole();
				userRole.setName("SUPER_ADMIN");
				userRole.setDescription("System Administrator");
				userRole.setStatus(Status.ACTIVE.getName());
				userRoleRepository.save(userRole);
				userRoles.add(userRole);
			}
			user.setMobilePassword("");
			user.setUserType("Web");
			user.setUserRoles(userRoles);
			userRepository.save(user);
		}
	}

}
