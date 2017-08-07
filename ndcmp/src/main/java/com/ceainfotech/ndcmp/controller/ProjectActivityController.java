/**
 * 
 */
package com.ceainfotech.ndcmp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Samy
 * 
 */
@Controller
@RequestMapping(value = "/api/**")
public class ProjectActivityController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectActivityController.class);

	@RequestMapping(value = "projectActivity", method = RequestMethod.GET)
	public ModelAndView projectPage() {
		LOGGER.info("Redirecting to project activity page {}");
		return new ModelAndView("project/projectActivity");
	}

}
