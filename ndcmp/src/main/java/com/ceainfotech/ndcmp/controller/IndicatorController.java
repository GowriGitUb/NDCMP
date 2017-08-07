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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ceainfotech.ndcmp.model.Indicator;
import com.ceainfotech.ndcmp.model.Output;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.model.UserRole;
import com.ceainfotech.ndcmp.service.IndicatorService;
import com.ceainfotech.ndcmp.service.OutputServices;
import com.ceainfotech.ndcmp.service.UserRoleService;
import com.ceainfotech.ndcmp.service.UserService;
import com.ceainfotech.ndcmp.util.ConstantUtil;
import com.ceainfotech.ndcmp.util.PrincipalUtil;
import com.ceainfotech.ndcmp.util.Status;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 * @author Gowri
 *
 */
@Controller
@RequestMapping(value = "/api/**")
public class IndicatorController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(IndicatorController.class);
	
	@Autowired
	UserRoleService userRoleService;
	
	@Autowired
	IndicatorService indicatorService; 
	
	@Autowired
	OutputServices outputServices;
	
	
	@Autowired
	private ConstantUtil constantUtil;

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PrincipalUtil principalUtil;
	
	String action,module="";
	
	/*
	 * List all the indicators
	 */
	@RequestMapping(value = "indicatorList", method = RequestMethod.GET)
	public String indicatorList(ModelMap model, HttpServletRequest request,Authentication authentication) {
		LOGGER.info("indicatorList");
		List<Indicator> listIndicators = indicatorService.listAllIndicators();
		model.addAttribute("listIndicators", listIndicators);
		User user = userService.findByUsername(PrincipalUtil.getPrincipal());
		UserRole role = new UserRole();
		if(user != null){
			List<UserRole> userRoles = user.getUserRoles();
			for (UserRole userRole : userRoles) {
				role = userRoleService.findByRoleId(userRole.getId());
				break;
			}
		}
		if(role.getName().equals("SUPER_ADMIN")){
			return "redirect:viewProject?projectId="+1+"&action=view";
		}else if(role.getName().equals("PROJECT_ADMIN")){
			return "redirect:configProject?projectId="+1+"&action=config";
		}return null;
	}

	/**
	 * To get add and edit page
	 */
	@RequestMapping(value="/getIndicator",method = RequestMethod.GET)
	public @ResponseBody Indicator getIndicator(@ModelAttribute Indicator indicator, @RequestParam Integer outputID){
		if(outputID != 0){
			LOGGER.info("Get outputID");
			Output output = outputServices.getById(outputID);
			if(output != null){
				indicator.setOutput(output);
			}
		}
		return indicator;
	}
	
	/**
	 * To get add and edit page
	 */
	@RequestMapping(value="/editIndicator",method = RequestMethod.GET)
	public @ResponseBody Indicator editIndicator(@ModelAttribute Indicator indicator, @RequestParam Integer indicatorId, Model model){
		if(indicatorId != 0 && indicatorId != null) {
			LOGGER.info("Get outputID");
			indicator = indicatorService.getById(indicatorId);
			if(indicatorId != null){
				model.addAttribute("indicator", indicator);
			}
		}
		return indicator;
	}


	/**
	 * 
	 * @param indicator
	 * @return
	 * @throws Exception 
	 */
	/*@RequestMapping(value="saveIndicator", method = RequestMethod.POST)
	public ModelAndView saveIndicator(@ModelAttribute Indicator indicator) throws Exception{
		LOGGER.info("Create Indicator");
		if (indicator != null) {// add indicator
			Output output = outputServices.getById(indicator.getOutput().getId());
			indicator.setOutput(output);
			indicator.setStatus(Status.ACTIVE.getName());
			indicatorService.addIndicator(indicator);
			
			action="Indicator"+indicator.getMessage()+" is Saved";
			module="Indicator Module";
			constantUtil.saveAudition(action, module, request);
			
			return new ModelAndView("redirect:indicatorList");
		}
		return new ModelAndView("redirect:indicatorList");
	}*/
	
	/**
	 * 
	 * @param indicator
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="saveIndicator", method = RequestMethod.GET)
	public @ResponseBody Indicator saveIndicator(@RequestParam String indicatorJson) throws Exception{
		LOGGER.info("Create Indicator");
		Indicator indicator=new Indicator();
		
		Gson gson=new GsonBuilder().create();
		JsonObject jsonObject = gson.fromJson( indicatorJson, JsonObject.class);
		
		Integer outputId=jsonObject.get("outputIndicatorId").getAsInt();
		String message=jsonObject.get("message").getAsString();
		Integer id=jsonObject.get("id").getAsInt();
		
		Output output=outputServices.getById(outputId);
		
		Indicator indicator2=indicatorService.getByMessageAndOutput(message, output);
		
		if(indicator2 != null){
			if(indicator2.getId() == id){
				indicator=indicatorService.getById(id);
				indicator.setMessage(message);
				indicator.setStatus(Status.ACTIVE.getName());
				indicator.setOutput(output);
			}else{
				indicator=new Indicator();
				indicator.setError("Indicator Message already exist");
				return indicator;	
			}
				
		}else{
			if(id > 0){
				indicator=indicatorService.getById(id);
				indicator.setMessage(message);
				indicator.setStatus(Status.ACTIVE.getName());
				indicator.setOutput(output);
			}else{
				indicator=new Indicator();
				indicator.setMessage(message);
				indicator.setOutput(output);
				indicator.setStatus(Status.ACTIVE.getName());	
			}
			
		}
		
		indicatorService.addIndicator(indicator);
		
		module="Indicator Module";
		if(id == 0){
			action="Indicator "+indicator.getMessage()+" is Saved";
		}else{
			action="Indicator "+indicator.getMessage()+" is Updated";
		}
		constantUtil.saveAudition(action, module, request);
		
		return indicator;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="deleteIndicator",method=RequestMethod.GET)
	public ModelAndView deleteIndicator(@RequestParam Integer id) throws Exception {
		LOGGER.info("Delete Indicator");
		indicatorService.delete(id);
		
		Indicator indicator=indicatorService.getById(id);
		
		action="Indicator"+indicator.getMessage()+" is Removed";
		module="Indicator Module";
		constantUtil.saveAudition(action, module, request);
		
		return new ModelAndView("redirect:indicatorList");
	}
}
