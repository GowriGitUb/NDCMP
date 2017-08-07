package com.ceainfotech.ndcmp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.ceainfotech.ndcmp.model.Agency;
import com.ceainfotech.ndcmp.model.ChangePassword;
import com.ceainfotech.ndcmp.model.Countries;
import com.ceainfotech.ndcmp.model.ProfileImage;
import com.ceainfotech.ndcmp.model.Region;
import com.ceainfotech.ndcmp.model.States;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.model.UserRole;
import com.ceainfotech.ndcmp.repository.AgencyRepository;
import com.ceainfotech.ndcmp.repository.ProfileImageRepository;
import com.ceainfotech.ndcmp.repository.UserRepository;
import com.ceainfotech.ndcmp.repository.UserRoleRepository;
import com.ceainfotech.ndcmp.service.AgencyService;
import com.ceainfotech.ndcmp.service.AuditService;
import com.ceainfotech.ndcmp.service.CountryService;
import com.ceainfotech.ndcmp.service.KeyActivityService;
import com.ceainfotech.ndcmp.service.ProjectService;
import com.ceainfotech.ndcmp.service.RegionService;
import com.ceainfotech.ndcmp.service.StatesService;
import com.ceainfotech.ndcmp.service.UserRoleService;
import com.ceainfotech.ndcmp.service.UserService;
import com.ceainfotech.ndcmp.util.ConstantUtil;
import com.ceainfotech.ndcmp.util.PrincipalUtil;
import com.ceainfotech.ndcmp.util.StateData;
import com.ceainfotech.ndcmp.util.Status;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * @author Samy
 * 
 */

@Controller
@RequestMapping(value = "/api/**")
@PropertySource(value = { "classpath:application.properties" })
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserRoleRepository userRoleRepository;

	@Autowired
	PrincipalUtil principalUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;


	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	CountryService countryService;

	@Autowired
	StatesService statesService;

	@Autowired
	private HttpSession session;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	RegionService regionService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private KeyActivityService keyActivityService;
	
	@Autowired
	private AgencyService agencyService;
	
	@Autowired
	private ProfileImageRepository profileImageRepository;
	
	@Autowired
	private AgencyRepository agencyRepository;
	
	@Autowired
	private AuditService auditService;
	
	@Autowired
	private ConstantUtil constantUtil;
	
	@Autowired
	Environment environment;
	
	/**
	 * after login redirect to dashboard page with access permission
	 * @param model
	 * @param authentication
	 * @return
	 */

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String homePage(ModelMap model, Authentication authentication) {
		LOGGER.info("Getting into dashboard page with user and roles {} :" + PrincipalUtil.getPrincipal());
		model.addAttribute("user", PrincipalUtil.getPrincipal());
		model.addAttribute("projectCount",projectService.getProjectCount());
		model.addAttribute("keyActivityCount",keyActivityService.getKeyActivityCount());
		model.addAttribute("agenciesCount",agencyService.getAgenciesCount());
		model.addAttribute("usersCount",userService.getUsersCount());
		return "home";
	}

	/**
	 * List out all the user information
	 * 
	 * @param model
	 * @param authentication
	 * @return
	 */

	@RequestMapping(value = "userList", method = RequestMethod.GET)
	public String userDetailsPage(ModelMap model, Authentication authentication) {
		LOGGER.info("Getting into user details page with user and roles {} :" + PrincipalUtil.getPrincipal());
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
			List<User> userList = userService.listAllUsers();
			List<User> filterUserList = new ArrayList<User>();
			for (User user : userList) {
				if(user.getAgencyId() != null && user.getAgencyId() != 0){
					Agency agency = agencyService.findByAgencyId(user.getAgencyId());
					user.setAgencyName(agency.getName());
					filterUserList.add(user);
				}else{
					filterUserList.add(user);
				}
			}
			model.addAttribute("userList", filterUserList);
			return "user/userList";
		}else{
			return "login";
		}
	}

	/**
	 * To get the user's information for add or edit page
	 * @param user
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/createUser", method = RequestMethod.GET)
	public String createUser(@ModelAttribute User userObject, Model model,HttpSession session) {

		LOGGER.info("Creating new user details with page {}: " + userObject);
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
			List<UserRole> userRoles2 = userRoleService.findByStatus(Status.ACTIVE.getName());
			List<UserRole> userRoles = new ArrayList<UserRole>();
			if (userRoles2 != null && !userRoles2.isEmpty()) {
				for (UserRole role : userRoles2) {
					if (!role.getName().equalsIgnoreCase("SUPER_ADMIN")) {
						userRoles.add(role);
					}
				}
			}
			List<Countries> countrylist = countryService.findByStatus(Status.ACTIVE.getName());
			List<States> statelist = statesService.findByStatus(Status.ACTIVE.getName());
				model.addAttribute("countrylist", countrylist);
				session.setAttribute("userImage", "");
			model.addAttribute("statelist", statelist);
			model.addAttribute("userRoles", userRoles);
			model.addAttribute("userObject", userObject);
			return "user/userForm";
		}else{
			return "login";
		}
		
	}

	/**
	 * saving new or existing user details
	 * 
	 * @param uer
	 * @return
	 * @throws Exception 
	 */

	@RequestMapping(value = "saveUser", method = RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute User userObject, Model model,@RequestParam CommonsMultipartFile multipartFile,HttpServletRequest request) throws Exception {

		LOGGER.info("Saving or updating user data {} : " + userObject);
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
			String profileimg=Base64.encodeBase64String(multipartFile.getBytes());
			ModelAndView modelAndView = new ModelAndView();
			String action,module="";
			User user = userService.getById(userObject.getId());
			if(user != null){
				userObject.setStatus(user.getStatus());
			}
			boolean emailStatus = false;
			boolean usernameStatus = false;
			
			ProfileImage profileImage=new ProfileImage();
			
			if(userObject.getAgencyId() != null) {
				Agency agency = agencyService.findByAgencyId(userObject.getAgencyId());
				if(agency != null && agency.getId() != null) {
					
					
					if(user != null) {
						
						Agency checkAgency = agencyService.findByLoginUser(userObject.getUsername());
						if(checkAgency != null && checkAgency.getId() != null && checkAgency.getAgencyAuthority() != null && checkAgency.getAgencyAuthority().size() > 0) {
							for(User agencyUser : checkAgency.getAgencyAuthority()) {
								if(user.getId() == agencyUser.getId()) {
									agencyService.deleteUserAgencies(user.getId(), checkAgency.getId());
									
								} 
							}
						}
					}
				}
			}
			
			User validateEmail = userService.findByEmail(userObject.getEmail());
			if(userObject.getId() == null){
				if(validateEmail != null){
					emailStatus = false;
					modelAndView.addObject("emailErrormsg", "Email Already Exists");
				}else{
					emailStatus = true;;
				}
			}else{
				User email = userService.findByIdAndEmail(userObject.getId(), userObject.getEmail());
				if(email != null){
					emailStatus = true;
					userObject.setEmail(userObject.getEmail());
				}else{
					User eamil1 = userService.findByEmail(userObject.getEmail());
					if(eamil1 != null){
						emailStatus = false;
						modelAndView.addObject("emailErrormsg", "Email Already Exists");
					}
				}
			}
			
			if (!principalUtil.validateUsername(userObject.getUsername(), userObject.getId())) {
				usernameStatus = false;
				modelAndView.addObject("usrerrormsg", "User Name  Already Exists");
			}else{
				usernameStatus = true;
			}
			
			if(usernameStatus == false || emailStatus == false){
				List<UserRole> userRoles2 = userRoleService.findByStatus(Status.ACTIVE.getName());
				List<UserRole> userRoles = new ArrayList<UserRole>();
				
				States state = statesService.getById(userObject.getState().getId());
				States states = new States();
				states.setId(state.getId());
				states.setName(state.getName());
				List<States> statesList = statesService.getByCountry(countryService.getById(userObject.getCountry().getId()));
				if (userRoles2 != null && !userRoles2.isEmpty()) {
					for (UserRole role : userRoles2) {
						if (!role.getName().equalsIgnoreCase("SUPER_ADMIN")) {
							userRoles.add(role);
						}
					}
				}
				List<Countries> countrylist = countryService.findByStatus(Status.ACTIVE.getName());
				UserRole userRole = userRoleService.findByRoleId(userObject.getUserRole().getId());
				Gson gson = new GsonBuilder().serializeNulls().create();
				modelAndView.addObject("countrylist", countrylist);
				modelAndView.addObject("statelist", gson.toJson(statesList));
				modelAndView.addObject("state", states);
				modelAndView.addObject("userRoles", userRoles);
				modelAndView.addObject("userObject", userObject);
				modelAndView.addObject("countyId", userObject.getCountry().getId());
				modelAndView.addObject("stateId", userObject.getState().getId());
				modelAndView.addObject("agencyId", userObject.getAgencyId());
				modelAndView.addObject("roleName", userRole.getName());
				modelAndView.setViewName("user/userForm");
				return modelAndView;
			}
			if(userObject.getId() == null){
				String encryptedValue = Base64.encodeBase64String(userObject.getPassword().getBytes());
				userObject.setMobilePassword(encryptedValue);
				
				/*String encryptedPassword = passwordEncoder.encode(changePassword.getNewPassword());
				String mobilePassword = Base64.encodeBase64String(changePassword.getNewPassword().getBytes());*/
				
				
				String encryptedPassword = passwordEncoder.encode(userObject.getPassword());	
				userObject.setPassword(encryptedPassword);
			}
			
			Countries country = countryService.getById(userObject.getCountry().getId());
			States state = statesService.getById(userObject.getState().getId());
			if (country != null && country.getId() != null && state != null && state.getId() != null) {
				userObject.setCountry(country);
				userObject.setState(state);
			}
			
			List<UserRole> userRoles = new ArrayList<UserRole>();
			UserRole userRole = userRoleService.findByRoleId(userObject.getUserRole().getId());
			userRoles.add(userRole);
			userObject.setUserRoles(userRoles);
			if(userObject.getId() == null){
			userObject.setStatus(Status.ACTIVE.getName());
			}
			if (userObject.getId() == null) { // if employee id is 0 then creating the user other updating the user
				userObject.setUserStatus(true);
				userService.add(userObject);
				profileImage.setUser(userObject);
				profileImage.setProfileimg(profileimg);
				profileImageRepository.save(profileImage);
				
				
				action="User "+userObject.getFirstName()+" "+userObject.getLastName()+" is Saved";
				module="User Module";
				constantUtil.saveAudition(action, module, request);
				
			} else {
				ProfileImage pImage=profileImageRepository.findByUser(userObject);
				userObject.setUserStatus(true);
				userRepository.saveAndFlush(userObject);
				if(pImage != null){
					if(profileimg.equals("")){
						profileImage=new ProfileImage();
						//if(pImage != null){
							profileImage.setId(pImage.getId());
							profileImage.setUser(userObject);
							profileImage.setProfileimg(pImage.getProfileimg());
							/*	}else{
							profileImage.setUser(userObject);
							profileImage.setProfileimg("");
						}*/
						
					}else{
						profileImage=new ProfileImage();
						profileImage.setId(pImage.getId());
						profileImage.setUser(userObject);
						profileImage.setProfileimg(profileimg);
					}
				}else{
					profileImage=new ProfileImage();
					profileImage.setUser(userObject);
					profileImage.setProfileimg(profileimg);
				}
				
				profileImageRepository.save(profileImage);
				
					action="User "+userObject.getFirstName()+" "+userObject.getLastName()+" is Updated";
					module="User Module";
					constantUtil.saveAudition(action, module, request);
			}
			modelAndView.setViewName("redirect:userList");
				if(userObject.getAgencyId() != null) {
					Agency agency = agencyService.findByAgencyId(userObject.getAgencyId());
					List<User> users = new ArrayList<User>(); 
					if(agency != null && agency.getId() != null) {
						user = userService.getById(userObject.getId());
						if(user != null) {
							Agency checkAgency = agencyRepository.findByLoginUser(user.getUsername());
							if(checkAgency != null && checkAgency.getId() != null && checkAgency.getAgencyAuthority() != null && checkAgency.getAgencyAuthority().size() > 0) {
								for(User agencyUser : checkAgency.getAgencyAuthority()) {
									if(user.getId() == agencyUser.getId()) {
										users.remove(agencyUser);
									} 
								}
							}
							for (User user2 : agency.getAgencyAuthority()) {
								users.add(user2);
							}
							users.add(user);
							agency.setAgencyAuthority(users);
							agencyService.addAgency(agency);
						}
					}
				}
				modelAndView.setViewName("redirect:userList");
			return modelAndView;
		}else{
			return new ModelAndView("login");
		}
	}
	
	/**
	 * Edit the existing user details
	 * @param id
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/editUser", method = RequestMethod.GET)
	public ModelAndView editUser(@RequestParam Integer id, @ModelAttribute User userObject, Model model) {

		LOGGER.info("Updating the User for the Id {} : " + id);
		String approverAgency = environment.getRequiredProperty("approver.agency");
		
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
			ModelAndView modelAndView = new ModelAndView("user/userForm");

			ProfileImage profileImage=new ProfileImage();
		
			
			if (userObject.getId() != 0) {
				userObject = userService.getById(userObject.getId());
				session.setAttribute("userId", userObject.getId());
				profileImage=profileImageRepository.findByUser(userObject);
			}
			List<UserRole> userRoles2 = userRoleService.findByStatus(Status.ACTIVE.getName());
			List<UserRole> userRoles = new ArrayList<UserRole>();
			if (userRoles2 != null && !userRoles2.isEmpty()) {
				for (UserRole role : userRoles2) {
					if (!role.getName().equalsIgnoreCase("SUPER_ADMIN")) {
						userRoles.add(role);
					}
				}
			}
			List<Countries> countrylist = countryService.findByStatus(Status.ACTIVE.getName());
			List<States> statelist = statesService.findByStatus(Status.ACTIVE.getName());
			model.addAttribute("countrylist", countrylist);
			for (UserRole role : userObject.getUserRoles()) {
				if(role.getName().equals("STATUS_APPROVER")){
					Agency agency = agencyService.findByAgencyCode(approverAgency);
					List<Agency> agencies = new ArrayList<Agency>();
					agencies.add(agency);
					model.addAttribute("agencyList", agencies);
				}else {
					model.addAttribute("agencyList", agencyService.findByStatus(Status.ACTIVE.getName(), new Sort(Sort.Direction.ASC, "name")));
				}
			}
			
			model.addAttribute("statelist", statelist);
			model.addAttribute("userRoles", userRoles);
			if(profileImage != null){
				model.addAttribute("userImage", profileImage.getProfileimg());
			}
			List<Integer> ids = new ArrayList<Integer>();
			for (UserRole role : userObject.getUserRoles()) {
				userObject.setUserRole(role);
				//userObject.setRoleId(role.getId());
				modelAndView.addObject("userRoleName", role.getName());
				//ids.add(role.getId());
			}
			//userObject.setRoleIds(ids);
			userObject.setAgencyId(userObject.getAgencyId());
			modelAndView.addObject("userObject", userObject);
			return modelAndView;
		}else{
			return new ModelAndView("login");
		}
		
	}

	/**
	 * Edit profile information details
	 * 
	 * @param user
	 * @return
	 */

	@RequestMapping(value = "editProfile", method = RequestMethod.GET)
	public ModelAndView editProfile(@ModelAttribute User userObject, Model model, HttpServletRequest request) {

		String userName = PrincipalUtil.getPrincipal();
		LOGGER.info("Updating the User for the Id {} : " + userName);
		//if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
			userObject = userService.findByUsername(userName);
			ProfileImage profileImage=new ProfileImage();
			
			List<UserRole> rolelist = userObject.getUserRoles();
			List<Integer> ids = new ArrayList<Integer>();
			for (UserRole role : rolelist) {
				model.addAttribute("userRoleName", role.getName());
				userObject.setUserRole(role);
				ids.add(role.getId());
			}
			userObject.setRoleIds(ids);
			profileImage=profileImageRepository.findByUser(userObject);
			ModelAndView modelAndView = new ModelAndView("editProfile");
			userObject.setAgencyId(userObject.getAgencyId());
			modelAndView.addObject("userObject", userObject);
			modelAndView.addObject("userRoles", rolelist);
			List<Countries> countrylist = countryService.findByStatus(Status.ACTIVE.getName());
			List<States> statelist = statesService.findByStatus(Status.ACTIVE.getName());
			model.addAttribute("countrylist", countrylist);
			model.addAttribute("statelist", statelist);
			model.addAttribute("agencyList", agencyService.findByStatus(Status.ACTIVE.getName(), new Sort(Sort.Direction.ASC, "name")));
			if(profileImage != null){
				session.setAttribute("profileImage", profileImage.getProfileimg());
			}
			
			if (request != null) {
				Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
				if (flashMap != null) {
					int msg = (Integer) flashMap.get("msg");
					modelAndView.addObject("msg", msg);
				}
			}

			return modelAndView;
		/*}else{
			return new ModelAndView("login");
		}*/
		
	}

	/**
	 * Save existing profile details with new user information
	 * 
	 * @param userObject
	 * @param redirectAttributes
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveProfile", method = RequestMethod.POST)
	public String saveProfile(@ModelAttribute User userObject,@RequestParam CommonsMultipartFile multipartFile,  RedirectAttributes redirectAttributes, Model model,HttpSession session,HttpServletRequest request) throws Exception {
		String action,module="";
		LOGGER.info("Saving or updating user data {} : " + userObject);
		String profileimg=Base64.encodeBase64String(multipartFile.getBytes());
		//String agencyId = (request.getParameter("agencyID"));
	//	Integer agencyID = Integer.parseInt(agencyId);
		//String userRoleid = request.getParameter("userRoleID");
		//Integer userRoleID = Integer.parseInt(userRoleid);
		ProfileImage profileImage=new ProfileImage();
		int msg;
		if(userObject.getId() != 0){
			userObject.setPassword(userObject.getPassword());
		}
		/*List<Integer> role = userRepository.findByUserId(userObject.getId());*/
		if (userObject.getId() != 0) {
			
			List<UserRole> userRoles = new ArrayList<UserRole>();
			UserRole userRole = userRoleService.findByRoleId(userObject.getUserRole().getId());
			userRoles.add(userRole);
			userObject.setUserRoles(userRoles);
			userObject.setStatus(Status.ACTIVE.getName());
			userObject.setAgencyId(userObject.getAgencyId());
			User user =userService.getById(userObject.getId());
			userRepository.saveAndFlush(userObject);
			
			action="User Profile of "+userObject.getFirstName()+" "+userObject.getLastName()+" is Updated";
			module="User Module";
			constantUtil.saveAudition(action, module, request);
			
			if(profileimg.equals("")){
				profileImage=profileImageRepository.findByUser(userObject);
				if(profileImage == null){
					profileImage=new ProfileImage();
				}
				profileImage.setUser(user);
				profileImage.setProfileimg(profileImage.getProfileimg());
			}else{
				profileImage=profileImageRepository.findByUser(userObject);
				if(profileImage == null){
					profileImage=new ProfileImage();
				}
				
				profileImage.setUser(user);
				profileImage.setProfileimg(profileimg);
			}
			
			profileImageRepository.saveAndFlush(profileImage);
			
			if(userObject.getAgencyId() != null) {
				Agency agency = agencyService.findByAgencyId(userObject.getAgencyId());
				List<User> users = new ArrayList<User>(); 
				if(agency != null && agency.getId() != null) {
					User user1 = userService.getById(userObject.getId());
					if(user1 != null) {
						users.add(user1);
						agency.setAgencyAuthority(users);
					}
					agencyService.addAgency(agency);
				}
			}
			
			msg = 1;
		} else {
			msg = 0;
		}
		model.addAttribute("userObject", userObject);
		session.setAttribute("profileImage", profileImage.getProfileimg());
		model.addAttribute("msg", msg);
		redirectAttributes.addFlashAttribute("msg", msg);
		return "redirect:editProfile";
	}

	/**
	 * Load change password page
	 * @param changePassword
	 * @return
	 */
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public ModelAndView changePassword(@ModelAttribute ChangePassword changePassword,Model model) {
		model.addAttribute("CurrentPassword", principalUtil.getCurrentPassword());
		return new ModelAndView("changePassword");
	}

	/**
	 * Create or Update change password
	 * @param changePassword
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/saveChangePass", method = RequestMethod.POST)
	public ModelAndView changePasswordAction(@ModelAttribute ChangePassword changePassword) throws Exception {
				User user = userService.findByUsername(PrincipalUtil.getPrincipal());
				if(user != null){
					return changePasswordInformation(user, changePassword);
		}else{
			String userName = PrincipalUtil.getPrincipal();//Super Admin
			if(userName != null && userName.equals(ConstantUtil.SUPERADMIN_USERNAME)) {
				User user1 = userService.findByUsername(userName);
				if(user1 != null){
					return changePasswordInformation(user1, changePassword);
				}
			}
		}
		return null;
	}
	/**
	 * change password information
	 * @param user
	 * @param changePassword
	 * @return
	 * @throws Exception 
	 */
	
	public ModelAndView changePasswordInformation(User user, ChangePassword changePassword) throws Exception{
		int msg;
		String action,module="";
		if(userService.checkIfValidOldPassword(user, changePassword.getCurrentPassword())){
			if(changePassword.getNewPassword().equals(changePassword.getConfirmPassword())){
				String encryptedPassword = passwordEncoder.encode(changePassword.getNewPassword());
				String mobilePassword = Base64.encodeBase64String(changePassword.getNewPassword().getBytes());
				msg = 2;
				user.setPassword(encryptedPassword);
				user.setMobilePassword(mobilePassword);
				userService.add(user);
				action="User Password of "+user.getFirstName()+" "+user.getLastName()+" is Changed";
				module="User Module";
				constantUtil.saveAudition(action, module, request);
				return new ModelAndView("changePassword", "msg", msg);
			}else{
				ModelAndView modelAndView = new ModelAndView();
				modelAndView.addObject("errormsg2","New password and Confirmpassword are not valid");
				modelAndView.setViewName("changePassword");
				return modelAndView;
			}
			
		}else{
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("passworderrormsg","Current user password is wrong");
			modelAndView.setViewName("changePassword");
			return modelAndView;
		}
	}

	/**
	 * Deleting the user information
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public ModelAndView deleteUser(@RequestParam Integer id) {
		LOGGER.info("Deleting the User By Id {} : " + id);
		try {
			if (id != null) {
				User user = userService.getById(id);
				if (user != null && user.getId() != null) {
					userService.deleteByUser(user);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:userList");
	}


	/**
	 * Get the state information by country
	 * @param model
	 * @param countryId
	 * @return
	 */
	
	@RequestMapping(value = "/getstatebycountry", method = RequestMethod.GET)
	public @ResponseBody
	List<States> getByCountry(Model model, @RequestParam Integer countryId) {
		model.addAttribute("state", new States());
		Countries country = countryService.getById(countryId);
		System.out.println(statesService.getByCountry(country));
		return statesService.getByCountry(country);
	}
	
	/**
	 * Get the state information by country
	 * @param model
	 * @param countryId
	 * @return
	 */
	
	@RequestMapping(value = "/getAgencyList", method = RequestMethod.GET)
	public @ResponseBody List<Agency> getAgencyByUserRole(Model model, @RequestParam Integer userRoleId) {
		String approverAgency = environment.getRequiredProperty("approver.agency");
		List<Agency> agencies = new ArrayList<Agency>();
		UserRole userRole = userRoleService.findByRoleId(userRoleId);
		if(userRole != null && userRole.getId() != null && userRole.getName().equals("STATUS_REVIEWER") || userRole.getName().equals("STATUS_UPDATER")) {
			//agencies = agencyService.findByStatus(Status.ACTIVE.getName());
			agencies = agencyService.findByStatus(Status.ACTIVE.getName(), new Sort(Sort.Direction.ASC, "name"));
		}if(userRole != null &&  userRole.getName().equals("STATUS_APPROVER")){
			Agency agency = new Agency();
			agency = agencyService.findByAgencyCode(approverAgency);
			agencies.add(agency);
			
		}
		return agencies;
	}
	
	/**
	 * Get the region by state
	 * @param stateId
	 * @return
	 */
	@RequestMapping(value = "/getRegionByState", method = RequestMethod.GET)
	public @ResponseBody
	List<Region> getRegionByState(@RequestParam Integer stateId) {
		States states = statesService.getById(stateId);
		List<Region> regions = regionService.findByStates(states);
		return regions;
	}
	
	/**
	 * Logout action is called to exit from the application
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request,HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}
	
	//Active user
	@RequestMapping(value = "activeUser", method = RequestMethod.GET)
	public ModelAndView activeUser(@RequestParam Integer id, Model model) throws Exception{
		String action,module="";
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
			if(id != null){
				User user = userService.getById(id);
				if(user != null){
					user.setUserStatus(true);
					user.setStatus(Status.ACTIVE.getName());
					userService.add(user);
					
					action="User "+user.getFirstName()+" "+user.getLastName()+" is Activated";
					module="User Module";
					constantUtil.saveAudition(action, module, request);
					
					return new ModelAndView("redirect:userList");
				}
			}
			//return null;
		}/*else{
			return new ModelAndView("login");
		}*/
		return new ModelAndView("login");
	}
	
	//Deactive user
	@RequestMapping(value = "deActiveUser", method = RequestMethod.GET)
	public ModelAndView deActiveUser(@RequestParam Integer id, Model model) throws Exception{
		String action,module="";
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
			if(id != null){
				User user = userService.getById(id);
				if(user != null){
					user.setUserStatus(false);
					user.setStatus(Status.INACTIVE.getName());
					userService.add(user);
					
					action="User "+user.getFirstName()+" "+user.getLastName()+" is deActivated";
					module="User Module";
					constantUtil.saveAudition(action, module, request);
					
					
					return new ModelAndView("redirect:userList");
				}
			}
			//return null;
		}/*else{
			return new ModelAndView("login");
		}*/
		return new ModelAndView("login");
	}
	
	/**
	 * Get user information based on user id
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "getUserDetails", method = RequestMethod.GET)
	public @ResponseBody User getUser(@RequestParam Integer userId) {
		User user = new User();
		if (userId != 0) {
			user = userService.getById(userId);
			if(user != null && user.getId() != null && user.getAgencyId() != null){
				Agency agency = agencyService.findByAgencyId(user.getAgencyId());
				user.setAgencyName(agency.getName());
			}
			
		}
		return user;
	}
	
	//load all the 
	@RequestMapping(value = "loadStates", method = RequestMethod.GET)
	public @ResponseBody StateData loadStates(@RequestParam("countryID") Integer countryID,@RequestParam("stateID") Integer stateID,
			@RequestParam("agencyID") Integer agencyID) {
		StateData stateData = new StateData();
		States states = statesService.getById(stateID);
		List<States> listStates = statesService.findByCountryId(countryID);
		List<Agency> agencies = agencyService.findByStatus(Status.ACTIVE.getName());
		Agency agency = agencyService.findByAgencyId(agencyID);
		stateData.setStates(states);
		stateData.setListStates(listStates);
		stateData.setAgency(agency);
		stateData.setAgencies(agencies);
		return stateData;
	}
}