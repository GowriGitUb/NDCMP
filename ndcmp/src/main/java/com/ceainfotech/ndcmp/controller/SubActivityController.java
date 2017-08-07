/**
 * 
 */
package com.ceainfotech.ndcmp.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

import com.ceainfotech.ndcmp.model.Agency;
import com.ceainfotech.ndcmp.model.Indicator;
import com.ceainfotech.ndcmp.model.KeyActivity;
import com.ceainfotech.ndcmp.model.Output;
import com.ceainfotech.ndcmp.model.SubActivity;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.model.UserRole;
import com.ceainfotech.ndcmp.repository.AgencyRepository;
import com.ceainfotech.ndcmp.repository.KeyActivityRepository;
import com.ceainfotech.ndcmp.service.AgencyService;
import com.ceainfotech.ndcmp.service.KeyActivityService;
import com.ceainfotech.ndcmp.service.OutputServices;
import com.ceainfotech.ndcmp.service.SubActivityService;
import com.ceainfotech.ndcmp.service.UserRoleService;
import com.ceainfotech.ndcmp.service.UserService;
import com.ceainfotech.ndcmp.util.ConstantUtil;
import com.ceainfotech.ndcmp.util.PrincipalUtil;
import com.ceainfotech.ndcmp.util.ProjectCoptData;
import com.ceainfotech.ndcmp.util.Status;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * @author Gowri
 *
 */
@Controller
@RequestMapping(value = "/api/**")
public class SubActivityController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SubActivityController.class);
	
	@Autowired
	UserRoleService userRoleService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PrincipalUtil principalUtil;
	
	@Autowired
	SubActivityService subActivityService; 
	
	@Autowired
	KeyActivityService keyActivityService;
	
	@Autowired
	KeyActivityRepository keyActivityRepository;
	
	@Autowired
	AgencyService agencyService;
	
	@Autowired
	OutputServices outputServices;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ConstantUtil constantUtil;
	
	@Autowired
	AgencyRepository agencyRepository;
	
	String action,module="";
	
	@RequestMapping(value = "subActivityList", method = RequestMethod.GET)
	public String listSubActivityList(ModelMap model, HttpServletRequest request,
			Authentication authentication) {
		LOGGER.info("List all SubActivity");
		List<SubActivity> subActivities = subActivityService.findByStatus(Status.ACTIVE.getName());
		model.addAttribute("subActivities", subActivities);
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
		}
		return null;
	}

	/*@RequestMapping(value="/getHierarchySubActivity",method = RequestMethod.GET)
	public @ResponseBody SubActivity getHierarchySubActivity(@ModelAttribute SubActivity subActivity, @RequestParam Integer keyActvityId, Model model) {
		KeyActivity keyActivity = new KeyActivity();
		if(keyActvityId != 0) {
			LOGGER.info("Get keyActvityId");
			keyActivity = keyActivityService.getById(keyActvityId);
			if(keyActivity != null){
				subActivity.setKeyActivity(keyActivity);
			}
		}
		List<Agency> leadAgencies = agencyService.findByAgencyType("LEAD_AGENCY");
		List<Agency> partnerAgencies = agencyService.findByAgencyType("PARTNER_AGENCY");
		if(leadAgencies != null && leadAgencies.size() > 0) {
			model.addAttribute("leadAgencies", leadAgencies);
		}
		if(partnerAgencies != null && partnerAgencies.size() > 0) {
			model.addAttribute("partnerAgencies", partnerAgencies);
		}
		model.addAttribute("subActivity", subActivity);
		return subActivity;
	}*/
	@RequestMapping(value="/getHierarchySubActivity",method = RequestMethod.GET)
	public @ResponseBody ProjectCoptData getHierarchySubActivity(@ModelAttribute SubActivity subActivity, @RequestParam Integer keyActvityId){
		ProjectCoptData projectCoptData = new ProjectCoptData();
		if(keyActvityId != 0){
			LOGGER.info("Get keyActvityId");
			KeyActivity keyActivity = keyActivityRepository.findById(keyActvityId);
			if(keyActivity != null){
				projectCoptData.setKeyActivity(keyActivity);
			}
			List<Agency> leadAgencies = agencyService.findByStatus(Status.ACTIVE.getName(), new Sort(Sort.Direction.ASC, "name"));
			List<Agency> partnerAgencies = agencyService.findByStatus(Status.ACTIVE.getName(), new Sort(Sort.Direction.ASC, "name"));
			projectCoptData.setLeadAgency(leadAgencies);
			projectCoptData.setPartnerAgency(partnerAgencies);
		}
		return projectCoptData;
	}


	/**
	 * 
	 * @param subActivity
	 * @return
	 * @throws Exception 
	 */
	/*@RequestMapping(value = "saveSubActivity", method = RequestMethod.POST)
	public ModelAndView saveSubActivity(@ModelAttribute SubActivity subActivity) {
		LOGGER.info("Create KeyActivity");
		List<Integer> ids = subActivity.getPartnerAgencyTypeIds();
		Integer leadId = null;
		leadId = Integer.parseInt(subActivity.getLeadAgency().getName());
		if(subActivity.getId() != null){
			subActivity=subActivityService.getById(subActivity.getId());
			subActivity.setSequenceNumber(subActivity.getSequenceNumber());
			subActivity.setKeyActivity(subActivity.getKeyActivity());
		}
		if(subActivity.getKeyActivity() != null && subActivity.getKeyActivity().getId() != null) {
			KeyActivity keyActivity = keyActivityService.getById(subActivity.getKeyActivity().getId());
			if(keyActivity != null && keyActivity.getId() != null) {
				Integer subActivityCount = subActivityService.getByKeyActivityId(keyActivity);
				subActivityCount++;
				if(subActivityCount != null) {
					if(subActivityCount == 1){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "a");}
					if(subActivityCount == 2){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "b");}
					if(subActivityCount == 3){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "c");}
					if(subActivityCount == 4){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "d");}
					if(subActivityCount == 5){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "e");}
					if(subActivityCount == 6){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "f");}
					if(subActivityCount == 7){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "g");}
					if(subActivityCount == 8){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "h");}
					if(subActivityCount == 9){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "i");}
					if(subActivityCount == 10){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "j");}
					if(subActivityCount == 11){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "k");}
					if(subActivityCount == 12){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "l");}
					if(subActivityCount == 13){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "m");}
					if(subActivityCount == 14){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "n");}
					if(subActivityCount == 15){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "o");}
					if(subActivityCount == 16){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "p");}
					if(subActivityCount == 17){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "q");}
					if(subActivityCount == 18){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "r");}
					if(subActivityCount == 19){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "s");}
					if(subActivityCount == 20){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "t");}
					if(subActivityCount == 21){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "u");}
					if(subActivityCount == 22){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "v");}
					if(subActivityCount == 23){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "w");}
					if(subActivityCount == 24){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "x");}
					if(subActivityCount == 25){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "y");}
					if(subActivityCount == 26){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "z");}
				}
				subActivity.setKeyActivity(keyActivity);
			}
		}
		Agency leadAgency = agencyService.findByAgencyId(leadId);
		subActivity.setLeadAgency(leadAgency);
		if (leadAgency != null && leadAgency.getId() != null) {
			leadAgency.setLeadAgency(true);
			agencyService.addAgency(leadAgency);
		}
		List<Agency> partnerAgency = new ArrayList<Agency>();
		
		if (ids != null && ids.size() > 0) {
			for (Integer id : ids) {
				Agency partnerAgency1 = agencyService.findByAgencyId(id);
				partnerAgency1.setPartnerAgency(true);
				agencyService.addAgency(partnerAgency1);
				partnerAgency.add(partnerAgency1);
			}
		}
		subActivity.setPartnerAgency(partnerAgency);
		subActivity.setStatus(Status.ACTIVE.getName());
		if (subActivity != null) {// add keyActivity
			subActivityService.addSubActivity(subActivity);
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
	*/
	
	@RequestMapping(value="saveSubActivity1",method=RequestMethod.GET)
	public @ResponseBody SubActivity saveSubActivity(@RequestParam String subActivityJson,@RequestParam String partner) throws Exception{
		
		
		Gson gson=new GsonBuilder().create();
		SubActivity subActivity=new SubActivity();
		KeyActivity keyActivity=new KeyActivity();
		
		JsonObject jsonObject = gson.fromJson( subActivityJson, JsonObject.class);
		Integer keyId=jsonObject.get("keyActivity").getAsInt();
		Integer leadAgencyId=jsonObject.get("leadAgency").getAsInt();
		String mov=jsonObject.get("mov").getAsString();
		String name=jsonObject.get("name").getAsString();
		
		Integer id=0;
		if(jsonObject.get("id").getAsString().equals("")){
			id=0;
		}else{
			id=jsonObject.get("id").getAsInt();	
		}
		
		
		
		/*if(id > 0){
			subActivity=subActivityService.getById(id);
			if(keyId != 0) {
				keyActivity = keyActivityService.getById(keyId);
				if(keyActivity != null){
					subActivity=subActivityService.getById(subActivity.getId());
					//subActivity.setSequenceNumber(subActivity2.getSequenceNumber());
					subActivity.setKeyActivity(keyActivity);
				}
			}
		}
		*/
		
		
		keyActivity=keyActivityService.getById(keyId);
		
		SubActivity subActivity2=subActivityService.getSubActivityByNameAndKeyActivity(name,keyActivity);
		
		if(subActivity2 != null){
			Integer subActivity2Id=subActivity2.getId();
			if(subActivity2Id.equals(id)){
				subActivity=subActivityService.getById(id);
				subActivity.setName(name);
				subActivity.setStatus(Status.ACTIVE.getName());
				subActivity.setKeyActivity(keyActivity);
			}else{
				subActivity=new SubActivity();
				subActivity.setError("Sub Activity Name already exist");
				return subActivity;	
			}
				
		}else{
			
			if(id > 0){
				subActivity=subActivityService.getById(id);
				subActivity.setName(name);
				subActivity.setStatus(Status.ACTIVE.getName());
				subActivity.setKeyActivity(keyActivity);
			}else{
				Integer asciiStartValue=96;
				subActivity=new SubActivity();
				subActivity.setKeyActivity(keyActivity);
				subActivity.setStatus(Status.ACTIVE.getName());
				Integer subActivityCount=subActivityService.getByKeyActivityId(keyActivity);
				subActivityCount++;
				if(subActivityCount != 0){
					for(int i=1;i<=26;i++){
						if(subActivityCount == i){
							subActivity.setSequenceNumber(keyActivity.getSequenceNumber()+"."+Character.toString ((char) (i+asciiStartValue)));
							break;
						}
					}
				}
			}
		}
		
		
		/*if(id == 0){
			if(keyId > 0){
				Integer asciiStartValue=96;
				
				subActivity.setKeyActivity(keyActivity);
				
				
				if(subActivity2 != null){
					subActivity.setError("Sub Activity Name already exist");
					return subActivity;
				}
				
				if(keyActivity != null){
					
				}
				subActivity.setStatus(Status.ACTIVE.getName());
			}	
		}*/
		
		
		
	/*	if(subActivity.getKeyActivity() != null && subActivity.getKeyActivity().getId() != null) {
			KeyActivity keyActivity1 = keyActivityService.getById(subActivity.getKeyActivity().getId());
			if(keyActivity1 != null && keyActivity1.getId() != null) {
				Integer subActivityCount = subActivityService.getByKeyActivityId(keyActivity1);
				subActivityCount++;
				if(subActivityCount != null) {
					if(subActivityCount == 1){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "a");}
					if(subActivityCount == 2){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "b");}
					if(subActivityCount == 3){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "c");}
					if(subActivityCount == 4){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "d");}
					if(subActivityCount == 5){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "e");}
					if(subActivityCount == 6){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "f");}
					if(subActivityCount == 7){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "g");}
					if(subActivityCount == 8){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "h");}
					if(subActivityCount == 9){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "i");}
					if(subActivityCount == 10){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "j");}
					if(subActivityCount == 11){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "k");}
					if(subActivityCount == 12){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "l");}
					if(subActivityCount == 13){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "m");}
					if(subActivityCount == 14){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "n");}
					if(subActivityCount == 15){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "o");}
					if(subActivityCount == 16){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "p");}
					if(subActivityCount == 17){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "q");}
					if(subActivityCount == 18){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "r");}
					if(subActivityCount == 19){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "s");}
					if(subActivityCount == 20){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "t");}
					if(subActivityCount == 21){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "u");}
					if(subActivityCount == 22){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "v");}
					if(subActivityCount == 23){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "w");}
					if(subActivityCount == 24){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "x");}
					if(subActivityCount == 25){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "y");}
					if(subActivityCount == 26){ subActivity.setSequenceNumber(keyActivity.getSequenceNumber() + "."+ "z");}
				}
				subActivity.setKeyActivity(keyActivity);
			}
		}*/
		Agency leadAgency = agencyService.findByAgencyId(leadAgencyId);
		subActivity.setLeadAgency(leadAgency);
		if (leadAgency != null && leadAgency.getId() != null) {
			leadAgency.setLeadAgency(true);
			agencyService.addAgency(leadAgency);
		}
		
		List<Agency> partnerAgency = new ArrayList<Agency>();
		if(!partner.equals("0")){
			String[] partnersId=partner.substring(1,partner.length()-1).split(",");
					
					for (int i = 0; i < partnersId.length; i++) {
						Integer partnerAgencyId = Integer.parseInt(partnersId[i].replace("\"",""));
						if (partnerAgencyId > 0) {
							Agency partnerAgency1 = agencyService.findByAgencyId(partnerAgencyId);
							partnerAgency1.setPartnerAgency(true);
							agencyService.addAgency(partnerAgency1);
							partnerAgency.add(partnerAgency1);
						}
			}
		}
		
		subActivity.setName(name);
		subActivity.setPartnerAgency(partnerAgency);
		subActivity.setMov(mov);
		
		
		//if (subActivity != null) {// add keyActivity
			subActivityService.addSubActivity(subActivity);
		//}
			module="Sub Activity Module";
			if(id == 0){
				action="Sub Activity "+subActivity.getName()+" is Saved";
			}else{
				action="Sub Activity "+subActivity.getName()+" is Updated";
			}
			constantUtil.saveAudition(action, module, request);
		return subActivity;
	}
	
	@RequestMapping(value="/editSubActivity",method = RequestMethod.GET)
	public @ResponseBody ProjectCoptData editSubActivity(@ModelAttribute SubActivity subActivity, @RequestParam Integer subActvityId){
		ProjectCoptData projectCoptData = new ProjectCoptData();
		if(subActvityId != 0){
			LOGGER.info("Get keyActvityId");
			subActivity = subActivityService.getById(subActvityId);
			if(subActivity != null){
				projectCoptData.setSubActivity(subActivity);
				projectCoptData.setName(subActivity.getName());
				KeyActivity keyActivity = keyActivityService.getById(subActivity.getKeyActivity().getId());
				if(keyActivity != null && keyActivity.getId() != null) {
					projectCoptData.setKeyActivity(keyActivity);
				}
			}
			List<Agency> leadAgencies = agencyService.findByStatus(Status.ACTIVE.getName(), new Sort(Sort.Direction.ASC, "name"));
			List<Agency> partnerAgencies = agencyService.findByStatus(Status.ACTIVE.getName(), new Sort(Sort.Direction.ASC, "name"));
			projectCoptData.setLeadAgency(leadAgencies);
			projectCoptData.setPartnerAgency(partnerAgencies);
			projectCoptData.setMov(subActivity.getMov());
		}
		return projectCoptData;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="deleteSubActivity",method=RequestMethod.GET)
	public ModelAndView deleteSubActivity(@RequestParam Integer id) {
		LOGGER.info("Delete SubActivity");
		subActivityService.deleteSubActivity(id);
		return new ModelAndView("redirect:subActivityList");
	}
	
	//Active Sub Activity
			@RequestMapping(value = "activeSubActivity", method = RequestMethod.GET)
			public ModelAndView activeSubActivity(@RequestParam Integer id, Model model){
				if(id != null){
					SubActivity subActivity = subActivityService.getById(id);
					if(subActivity != null){
						subActivity.setSubActivityStatus(true);
						subActivity.setStatus(Status.ACTIVE.getName());
						subActivityService.addSubActivity(subActivity);
						return new ModelAndView("redirect:subActivityList");
					}
				}
				return null;
			}
			
			//Deactive region
			@RequestMapping(value = "deActiveSubActivity", method = RequestMethod.GET)
			public ModelAndView deActiveRegion(@RequestParam Integer id, Model model){
				if(id != null){
					SubActivity subActivity = subActivityService.getById(id);
					if(subActivity != null){
						subActivity.setSubActivityStatus(false);
						subActivity.setStatus(Status.INACTIVE.getName());
						subActivityService.addSubActivity(subActivity);
						return new ModelAndView("redirect:subActivityList");
					}
				}
				return null;
			}
			
			@RequestMapping(value="getSubActivity")
			public @ResponseBody SubActivity getSubActivityById(@RequestParam("id") int id){
				SubActivity sub=subActivityService.getById(id);
				return sub;
			}
			
			@RequestMapping(value="actDeActSubAct",method=RequestMethod.GET)
			public @ResponseBody SubActivity actDeActSubAct(@RequestParam("act_deactAction") String act_deactAction, @RequestParam("sub_act_id") Integer sub_act_id) throws Exception{
				SubActivity subActivity=new SubActivity();
				if(sub_act_id != null){
					 subActivity = subActivityService.getById(sub_act_id);
					if(subActivity != null){
						if(act_deactAction.equals("deActive")){
							subActivity.setSubActivityStatus(false);
							subActivity.setStatus(Status.INACTIVE.getName());
							subActivityService.addSubActivity(subActivity);
							
							module="Sub Activity Module";
								action="Sub Activity"+subActivity.getName()+" is Deactivated";
							
						}else{
							subActivity.setSubActivityStatus(true);
							subActivity.setStatus(Status.ACTIVE.getName());
							subActivityService.addSubActivity(subActivity);
							
							module="Sub Activity Module";
							action="Sub Activity"+subActivity.getName()+" is Activated";
						}
					}
				}
				constantUtil.saveAudition(action, module, request);
				return subActivity;
			}
}
