/**
 * 
 */
package com.ceainfotech.ndcmp.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ceainfotech.ndcmp.model.Countries;
import com.ceainfotech.ndcmp.model.Project;
import com.ceainfotech.ndcmp.model.Region;
import com.ceainfotech.ndcmp.model.States;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.model.UserRole;
import com.ceainfotech.ndcmp.repository.CountryRepository;
import com.ceainfotech.ndcmp.repository.ProjectRepository;
import com.ceainfotech.ndcmp.repository.RegionRepository;
import com.ceainfotech.ndcmp.repository.StateRepository;
import com.ceainfotech.ndcmp.repository.UserRepository;
import com.ceainfotech.ndcmp.repository.UserRoleRepository;

/**
 * @author Samy
 * 
 */
@Component
public class ProjectUtil {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private RegionRepository regionRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public void saveProjectAdmin(String username) {
		User user = userRepository.findByUsername(username);
		if (user == null || user.getId() == null) {
			user = new User();
			user.setFirstName("Project");
			user.setLastName("Administrator");
			user.setUsername(username);
			String encryptedPassword = passwordEncoder.encode(username);
			user.setPassword(encryptedPassword);
			String mobilePassword = Base64.encodeBase64String(username.getBytes());
			user.setMobilePassword(mobilePassword);
			user.setEmail("projectadmin@gmail.com");
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
			UserRole getUserRole = userRoleRepository.findByName("PROJECT_ADMIN");
			List<UserRole> userRoles = new ArrayList<UserRole>();
			if (getUserRole == null || getUserRole.getId() == null) {
				UserRole userRole = new UserRole();
				userRole.setName("PROJECT_ADMIN");
				userRole.setDescription("Project Administrator");
				userRole.setStatus(Status.ACTIVE.getName());
				userRoleRepository.save(userRole);
				userRoles.add(userRole);
			}
			user.setUserType("Web");
			user.setUserRoles(userRoles);
			userRepository.save(user);
		}
	}
	
	public void addProject(String projectName) {
		Project project = projectRepository.findByName(projectName);
		if (project == null || project.getId() == null) {
			project = new Project();
			project.setName(projectName);
			UserRole getUserRole = userRoleRepository.findByName("PROJECT_ADMIN");
			if(getUserRole != null && getUserRole.getId() != null) {
				User user = userRepository.findByUserRoles(getUserRole);
				if(user != null && user.getId() != null) {
					project.setAdmin(user.getUsername());
				}
			}
			Countries country = countryRepository.findByCode("NIG");
			if (country == null || country.getId() == null) {
				country = new Countries();
				country.setCode("NIG");
				country.setName("Nigeria");
				country.setStatus(Status.ACTIVE.getName());
				countryRepository.save(country);
			}
			project.setCountry(country);
			States state = stateRepository.findByCode("AB");
			if (state == null || state.getId() == null) {
				state = new States();
				state.setCode("AB");
				state.setName("Abia");
				state.setCountry(country);
				state.setStatus(Status.ACTIVE.getName());
				stateRepository.save(state);
			}
			project.setStates(state);
			Region region = regionRepository.findByCode("Abu South");
			if (region == null || region.getId() == null) {
				region = new Region();
				region.setCode("Abu South");
				region.setName("Abia");
				region.setCountry(country);
				region.setStates(state);
				region.setStatus(Status.ACTIVE.getName());
				regionRepository.save(region);
			}
			project.setRegion(region);
			project.setDescription("This is the UNODC Project");
			project.setStatus(Status.ACTIVE.getName());
			projectRepository.save(project);
		}
	}

}
