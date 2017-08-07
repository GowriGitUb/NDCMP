/**
 * 
 */
package com.ceainfotech.ndcmp.controller;

import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.service.UserRoleService;
import com.ceainfotech.ndcmp.service.UserService;
import com.ceainfotech.ndcmp.util.PrincipalUtil;
import com.ceainfotech.ndcmp.util.SendMail;

/**
 * @author Samy
 * 
 */

@Controller
public class LoginController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(LoginController.class);

	@Autowired
	UserService userService;

	@Autowired
	UserRoleService userRoleService;

	@Autowired
	PrincipalUtil principalUtil;

	@Autowired
	SendMail sendMail;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired(required = true)
	public HttpServletRequest request;
	
	/**
	 * Access denied page if password is wrong
	 * 
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/access_denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		LOGGER.info("Getting into access denied page {}");
		model.addAttribute("user", PrincipalUtil.getPrincipal());
		return "accessDenied";
	}

	/**
	 * Application started with login page
	 * 
	 * @return
	 */

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public String loginPage(Model model) {
		LOGGER.info("Getting into login page {}");
		HttpSession session = request.getSession();
		String invaliduser = (String) session.getAttribute("invaliduser");
		String invalidUserStatus = (String) session.getAttribute("invalidUserStatus");
		String invalidRoleStatus = (String) session.getAttribute("invalidRoleStatus");
		
		if(invaliduser != null){
			model.addAttribute(invaliduser, invaliduser);
		}
		if(invalidUserStatus != null){
			model.addAttribute(invalidUserStatus, invalidUserStatus);
		}
		
		if(invalidRoleStatus != null){
			model.addAttribute(invalidRoleStatus, invalidRoleStatus);
		}
		// model.addAttribute("roles", userRoleService.listAllRoles());
		/*if(session.getAttribute("userId") == null){
			model.addAttribute("session","Your Session Has Been Expired..!");
		}*/
		return "login";
	}

	/**
	 * Getting into forgot password page
	 * 
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
	public ModelAndView forgotPasswordPage(@ModelAttribute User user,
			Model model) {
		LOGGER.info("Getting into forgotpassword page {}");
		return new ModelAndView("forgotPassword");

	}

	/**
	 * Getting email value from user and send password to email
	 * 
	 * @param email
	 * @return
	 * @throws MessagingException 
	 * @throws AddressException 
	 */

	@RequestMapping(value = "/forgotPassAction", method = RequestMethod.POST)
	public ModelAndView sendEmailtoforgotPass(@ModelAttribute User user) throws AddressException, MessagingException {
		String msg = "";

		if (user.getEmail() != null) {
			User user2 = userService.findByEmail(user.getEmail());
			if (user2 != null) {
				String password = UUID.randomUUID().toString();
				String updatedPassword = password.substring(0, 4);
				user2.setPassword(updatedPassword);
				emailSender(user2);
				user2.setPassword(passwordEncoder.encode(updatedPassword));
				userService.add(user2);
				msg = "Mail has been sent successfully";
			} else {
				msg = "Email has not registered.Please register it";
			}
		}

		return new ModelAndView("forgotPassword", "msg", msg);
	}
	
	private void emailSender(User user) throws AddressException, MessagingException {
		/**
		 * Purpose: Mailing Properties
		 */
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("gowri.jtjjtj@gmail.com", "appaappa");
			}
		});
		
		final String subject = "Password Confirmation";
		Message message = new MimeMessage(session);
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
		message.setFrom(new InternetAddress("gowri.jtjtjt@gmail.com"));
		message.setSubject(subject);
		message.setText("Username :"+ user.getUsername() +" \r\n" +"Password :" +user.getPassword());
		Transport.send(message);
		System.out.println("Mail Sent Successfully.");
		}
}
