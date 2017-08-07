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
import com.ceainfotech.ndcmp.model.Target;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.model.UserRole;
import com.ceainfotech.ndcmp.service.OutcomeService;
import com.ceainfotech.ndcmp.service.OutputServices;
import com.ceainfotech.ndcmp.service.TargetService;
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
public class TargetController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TargetController.class);
	
	@Autowired
	UserRoleService userRoleService;
	
	@Autowired
	TargetService targetService; 
	
	@Autowired
	OutputServices outputService;
	

	@Autowired
	private ConstantUtil constantUtil;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PrincipalUtil principalUtil;

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private OutputServices outputServices;
	
	String action,module="";
	
	/*
	 * List all the targets
	 */
	@RequestMapping(value = "targetList", method = RequestMethod.GET)
	public String targetList(ModelMap model, HttpServletRequest request,Authentication authentication) {
		LOGGER.info("targetList");
		List<Target> listTargets = targetService.listAllTargets();
		model.addAttribute("listTargets", listTargets);
		User user = userService.findByUsername(principalUtil.getPrincipal());
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
	@RequestMapping(value="/getTarget",method = RequestMethod.GET)
	public @ResponseBody Target getTarget(@ModelAttribute Target target, @RequestParam Integer outputId){
		if(outputId != 0 && outputId != null) {//get target for edit
			LOGGER.info("Get target");
			Output output = outputService.getById(outputId);
			if(output != null){
				target.setOutput(output);
			}
		}
		return target;
	}
	
	/**
	 * To get add and edit page
	 */
	@RequestMapping(value="/editTarget",method = RequestMethod.GET)
	public @ResponseBody Target editTarget(@ModelAttribute Target target, @RequestParam Integer targetId, Model model){
		if(targetId != 0 && targetId != null) {//get target for edit
			target = targetService.getById(targetId);
			if(target != null && target.getId() != null) {
				model.addAttribute("target", target);
			}
		}
		return target;
	}

	/**
	 * 
	 * @param target
	 * @return
	 * @throws Exception 
	 */
	/*@RequestMapping(value="saveTarget", method = RequestMethod.POST)
	public ModelAndView saveTarget(@ModelAttribute Target target) throws Exception{
		LOGGER.info("Create Target");
		if(target != null){//add keyActivity
			System.out.println(target.getId());
			if( target.getId() == null){
				Output output = outputService.getById(target.getOutput().getId());
				target.setOutput(output);
				target.setStatus(Status.ACTIVE.getName());
				targetService.addTarget(target);
				
				action="Target"+target.getMessage()+" is Saved";
				module="Target Module";
				constantUtil.saveAudition(action, module, request);
				
				return new ModelAndView("redirect:targetList");
			}else{
				targetService.updateTarget(target);
				return new ModelAndView("redirect:targetList");
				}
			}
			return new ModelAndView("redirect:targetList");
		}
	
	*/
	
	@RequestMapping(value="saveTarget", method = RequestMethod.GET)
	public @ResponseBody Target saveTarget(@RequestParam String targetJson) throws Exception{
		Target target=new Target(); 
		
		
		Gson gson=new GsonBuilder().create();
		JsonObject jsonObject = gson.fromJson( targetJson, JsonObject.class);
		
		Integer outputId=jsonObject.get("outputId").getAsInt();
		Integer id=jsonObject.get("id").getAsInt();
		String message=jsonObject.get("target").getAsString();
		
		Output output=outputServices.getById(outputId);
		
		Target target2=targetService.getByMessageAndOutput(message, output);
		
		if(target2 != null){
			if(target2.getId() == id){
				target=targetService.getById(id);
				target.setMessage(message);
				target.setStatus(Status.ACTIVE.getName());
				target.setOutput(output);
			}else{
				target=new Target();
				target.setError("Target Message already exist");
				return target;	
			}
		}else{
			if(id > 0){
				target=targetService.getById(id);
				target.setMessage(message);
				target.setStatus(Status.ACTIVE.getName());
				target.setOutput(output);
			}else{
				target=new Target();
				target.setMessage(message);
				target.setOutput(output);
				target.setStatus(Status.ACTIVE.getName());	
			}
		}
		targetService.addTarget(target);
		
		module="Target Module";
		if(id == 0){
			action="Target "+target.getMessage()+" is Saved";
		}else{
			action="Target "+target.getMessage()+" is Updated";
		}
		constantUtil.saveAudition(action, module, request);
		
		return target;
	}
	
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="deleteTarget",method=RequestMethod.GET)
	public ModelAndView deleteTarget(@RequestParam Integer id) throws Exception {
		LOGGER.info("Delete Target");
		targetService.delete(id);
		
		Target target=targetService.getById(id);
		
		action="Target"+target.getMessage()+" is Removed";
		module="Target Module";
		constantUtil.saveAudition(action, module, request);
		
		return new ModelAndView("redirect:targetList");
	}
}
