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

import com.ceainfotech.ndcmp.model.KeyActivity;
import com.ceainfotech.ndcmp.model.Output;
import com.ceainfotech.ndcmp.model.SubActivity;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.model.UserRole;
import com.ceainfotech.ndcmp.service.KeyActivityService;
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
public class KeyActivityController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(KeyActivityController.class);
	
	@Autowired
	UserRoleService userRoleService;
	
	@Autowired
	KeyActivityService keyActivityService; 
	
	@Autowired
	OutputServices outputServices;
	
	@Autowired
	private UserService userService;

	@Autowired
	private PrincipalUtil principalUtil;
	
	@Autowired
	private ConstantUtil constantUtil;
	
	@Autowired
	private HttpServletRequest request;
	
	String action,module="";
	
	@RequestMapping(value = "keyActivityList", method = RequestMethod.GET)
	public String listKeyActivites(ModelMap model, HttpServletRequest request,
			Authentication authentication) {
		LOGGER.info("List all KeyActivity");
		System.out.println(Status.ACTIVE.getName());
		List<KeyActivity> keyActivities = keyActivityService.findByStatus(Status.ACTIVE.getName());
		model.addAttribute("keyActivities", keyActivities);
		//return "redirect:configProject?projectId="+1;
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
	 * 
	 * @param keyActivity
	 * @param outputId
	 * @return
	 */
	@RequestMapping(value="/editKeyActivity",method = RequestMethod.GET)
	public @ResponseBody KeyActivity editKeyActivity(@ModelAttribute KeyActivity keyActivity, @RequestParam Integer keyActivityId, Model model){
		if(keyActivityId != 0 && keyActivityId != null) {
			LOGGER.info("edit KeyActivity");
			keyActivity = keyActivityService.getById(keyActivityId);
			if(keyActivity != null){
				model.addAttribute("keyActivity", keyActivity);
			}
		}
		return keyActivity;
	}

	@RequestMapping(value="/getHierarchyKeyActivity",method = RequestMethod.GET)
	public @ResponseBody KeyActivity getHierarchyKeyActivity(@ModelAttribute KeyActivity keyActivity, @RequestParam Integer outputId){
		KeyActivity keyActivity2 = new KeyActivity();
		if(outputId != 0){
			LOGGER.info("Get KeyActivity");
			Output output = outputServices.getById(outputId);
			if(output != null){
				keyActivity2.setOutput(output);
			}
		}
		return keyActivity2;
	}

	/**
	 * 
	 * @param keyActivity
	 * @return
	 */
	@RequestMapping(value="createKeyActivity", method = RequestMethod.POST)
	public ModelAndView saveKeyActivity(@ModelAttribute KeyActivity keyActivity){
		ModelAndView modelAndView = new ModelAndView();
		LOGGER.info("Create KeyActivity");
		if(keyActivity.getOutput() != null && keyActivity.getOutput().getId() != null){//add keyActivity
			Output output = outputServices.getById(keyActivity.getOutput().getId());
			if(output != null && output.getId() != null){
				Integer keyActivityCount = keyActivityService.findByOutputId(output);
				keyActivityCount++;
				if(keyActivityCount != null){
					keyActivity.setSequenceNumber(output.getSequenceNumber() + "." + keyActivityCount.toString());
				}
				keyActivity.setOutput(output);
			}
			keyActivity.setStatus(Status.ACTIVE.getName());
			keyActivityService.addActivity(keyActivity);
			}
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
			return new ModelAndView("redirect:configProject?projectId="+1+"&action=view");
		}else if(role.getName().equals("PROJECT_ADMIN")){
			return new ModelAndView("redirect:configProject?projectId="+1+"&action=config");
		}
		return null;
		}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="deleteKeyActivity",method=RequestMethod.GET)
	public ModelAndView deleteAgency(@RequestParam Integer id) {
		LOGGER.info("Delete KeyActivity");
		keyActivityService.deleteActivity(id);
		return new ModelAndView("redirect:keyActivityList");
	}
	
	@RequestMapping(value="saveKeyActivity",method=RequestMethod.GET)
	public @ResponseBody KeyActivity saveKeyActivity(@RequestParam String keyActivityJson) throws Exception {
		KeyActivity keyActivity=new KeyActivity();
		
		Gson gson=new GsonBuilder().create();
		JsonObject jsonObject = gson.fromJson(keyActivityJson, JsonObject.class);
		Integer id=0;
		if(jsonObject.get("id").getAsString().equals("")){
			id=0;
		}else{
			id=jsonObject.get("id").getAsInt();	
		}
		 
		Integer outputId=jsonObject.get("outputId").getAsInt();
		String name=jsonObject.get("name").getAsString();
		
		
			if(id >0){
				keyActivity=keyActivityService.getById(id);
				keyActivity.setName(name);
				keyActivityService.addActivity(keyActivity);
				
			}else{
				Output output = outputServices.getById(outputId);
				if(output != null){
					keyActivity=keyActivityService.getByNameAndOutput(name, output);
					if(keyActivity !=null){
						keyActivity.setError("Key Activity Name already exist");
						return keyActivity;
					}
				}
				if(output!= null){//add keyActivity
					keyActivity=new KeyActivity();
					keyActivity.setName(name);
					Integer keyActivityCount = keyActivityService.findByOutputId(output);
					keyActivityCount++;
					if(keyActivityCount != null){
						keyActivity.setSequenceNumber(output.getSequenceNumber() + "." + keyActivityCount.toString());
					}
					keyActivity.setOutput(output);
				}
				keyActivity.setStatus(Status.ACTIVE.getName());
				keyActivityService.addActivity(keyActivity);
				
				module="Key Activity Module";
				if(id == 0){
					action="Key Activity "+keyActivity.getName()+" is Saved";
				}else{
					action="Key Activity "+keyActivity.getName()+" is Updated";
				}
				
				constantUtil.saveAudition(action, module, request);
			}
		
		/*User user = userService.findByUsername(principalUtil.getPrincipal());
		UserRole role = new UserRole();
		if(user != null){
			List<UserRole> userRoles = user.getUserRoles();
			for (UserRole userRole : userRoles) {
				role = userRoleService.findByRoleId(userRole.getId());
				break;
			}
		}*/
		
		return keyActivity;
	}
}
