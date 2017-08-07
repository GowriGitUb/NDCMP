/**
 * 
 */
package com.ceainfotech.ndcmp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ceainfotech.ndcmp.model.Quarter;
import com.ceainfotech.ndcmp.service.QuarterService;
import com.ceainfotech.ndcmp.util.ConstantUtil;
import com.ceainfotech.ndcmp.util.PrincipalUtil;
import com.ceainfotech.ndcmp.util.Status;

/**
 * @author Gowri
 *
 */
@RequestMapping(value = "/api/**")
@Controller
public class QuarterController {

	private static final Logger LOGGER = LoggerFactory.getLogger(QuarterController.class);
	
	@Autowired
	PrincipalUtil principalUtil;
	
	@Autowired
	QuarterService quarterService;
	
	/*
	 * To list quarter
	 */
	@RequestMapping(value = "quarterList", method = RequestMethod.GET)
	public ModelAndView listQuarter(ModelMap modelMap, Authentication authentication, HttpServletRequest request) {
		
		LOGGER.info("Listing All quarters");
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE) || principalUtil.getCurrentUserRole().equals(ConstantUtil.PROJECTADMIN_ROLE) || principalUtil.getCurrentUserRole().equals(ConstantUtil.PROJECT_PLANNER)){
			List<Quarter> quarters = quarterService.listAllQuarters();
			if(quarters != null && quarters.size() > 0 && !quarters.isEmpty()){
				modelMap.addAttribute("quarterList", quarters);
			}
			modelMap.addAttribute("quarter", new Quarter());
			return new ModelAndView("reportingperiod/quarterList");
		}else{
			return new ModelAndView("login");
		}
	}
	
	
	/*
	 * To save quarter
	 */
	@RequestMapping(value = "createQuarter", method = RequestMethod.GET)
	public ModelAndView createQuarter(ModelMap modelMap,@ModelAttribute Quarter quarter) {
		LOGGER.info("get quarter");
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE) || principalUtil.getCurrentUserRole().equals(ConstantUtil.PROJECTADMIN_ROLE) || principalUtil.getCurrentUserRole().equals(ConstantUtil.PROJECT_PLANNER)){
			modelMap.addAttribute("quarter", quarter);
			return new ModelAndView("reportingperiod/quarterAdd");
		}else{
			return new ModelAndView("login");
		}
	}
	
	/*
	 * To add quarter
	 */
	@RequestMapping(value = "saveQuarter", method = RequestMethod.POST)
	public ModelAndView saveQuarter(ModelMap modelMap,@ModelAttribute Quarter quarter) {
		ModelAndView modelAndView = new ModelAndView();
		LOGGER.info("save quarter");
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE) || principalUtil.getCurrentUserRole().equals(ConstantUtil.PROJECTADMIN_ROLE) || principalUtil.getCurrentUserRole().equals(ConstantUtil.PROJECT_PLANNER)){
			if(quarter.getId() == null){
				Quarter quarter2 = quarterService.findByNameAndStatus(quarter.getName(), Status.ACTIVE.getName());
				if(quarter2 != null){
					modelAndView.addObject("quaterAlradyExitMessage", "This Quarter Name Already Exist");
					modelAndView.addObject("quarter", quarter);
					modelAndView.setViewName(("reportingperiod/quarterAdd"));
					return modelAndView;
				}
				quarter.setStatus(Status.ACTIVE.getName());
				quarterService.addQuarter(quarter);
			}else{
				Quarter quarterWithoutId = quarterService.findByNameAndStatus(quarter.getName(), Status.ACTIVE.getName());
				Quarter quarterwithId = quarterService.findByIdAndNameAndStatus(quarter.getId(), quarter.getName(),Status.ACTIVE.getName());
				if(quarterwithId == null){
					if(quarterWithoutId != null){
						modelAndView.addObject("quaterAlradyExitMessage", "This Quarter Name Already Exist");
						modelAndView.addObject("quarter", quarter);
						modelAndView.setViewName(("reportingperiod/quarterAdd"));
						return modelAndView;
					}
				}
				quarter.setStatus(Status.ACTIVE.getName());
				quarterService.addQuarter(quarter);
			}
			modelAndView.setViewName("redirect:quarterList");
			return modelAndView;
		}else{
			return new ModelAndView("login");
		}
	}
	
	
	/*
	 * To get quarter for edit
	 */
	@RequestMapping(value = "editQuarter", method = RequestMethod.GET)
	public ModelAndView editQuarter(ModelMap modelMap,@ModelAttribute Quarter quarter,@RequestParam Integer id) {
		LOGGER.info("get quarter");
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE) || principalUtil.getCurrentUserRole().equals(ConstantUtil.PROJECTADMIN_ROLE) || principalUtil.getCurrentUserRole().equals(ConstantUtil.PROJECT_PLANNER)){
			if(id != null){
				Quarter quarter2 = quarterService.getById(id);
				System.out.println(quarter2);
				modelMap.addAttribute("quarter", quarter2);
			}
			return new ModelAndView("reportingperiod/quarterAdd");
		}else {
			return new ModelAndView("login");
		}
		
	}
	
	/*
	 * To de-activateQuarter
	 */
	@RequestMapping(value = "deactivateQuarter", method = RequestMethod.GET)
	public ModelAndView deleteQuarter(ModelMap modelMap,@ModelAttribute Quarter quarter,@RequestParam Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		LOGGER.info("deactivate quarter");
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE) || principalUtil.getCurrentUserRole().equals(ConstantUtil.PROJECTADMIN_ROLE) || principalUtil.getCurrentUserRole().equals(ConstantUtil.PROJECT_PLANNER)){
			if(id != null){
				quarter = quarterService.getById(id);
				quarter.setStatus(Status.INACTIVE.getName());
				quarterService.addQuarter(quarter);
			}
			modelAndView.setViewName("redirect:quarterList");
			return modelAndView;
		}else{
			return new ModelAndView("login");
		}
	}
	
	/*
	 * To activate Quarter
	 */
	@RequestMapping(value = "activateQuarter", method = RequestMethod.GET)
	public ModelAndView activateQuarter(ModelMap modelMap,@ModelAttribute Quarter quarter,@RequestParam Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		LOGGER.info("activate quarter");
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE) || principalUtil.getCurrentUserRole().equals(ConstantUtil.PROJECTADMIN_ROLE) || principalUtil.getCurrentUserRole().equals(ConstantUtil.PROJECT_PLANNER)){
			if(id != null){
				quarter = quarterService.getById(id);
				quarter.setStatus(Status.ACTIVE.getName());
				quarterService.addQuarter(quarter);
			}
			modelAndView.setViewName("redirect:quarterList");
			return modelAndView;
		}else{
			return new ModelAndView("login");
		}
		
	}
}
