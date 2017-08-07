/**
 * 
 */
package com.ceainfotech.ndcmp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ceainfotech.ndcmp.model.Outcome;
import com.ceainfotech.ndcmp.model.Output;
import com.ceainfotech.ndcmp.model.Project;
import com.ceainfotech.ndcmp.model.StrategicPillar;
import com.ceainfotech.ndcmp.model.Target;
import com.ceainfotech.ndcmp.model.Theme;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.model.UserRole;
import com.ceainfotech.ndcmp.model.dto.AddStrategicPillar;
import com.ceainfotech.ndcmp.model.dto.StrategicDTO;
import com.ceainfotech.ndcmp.service.IndicatorService;
import com.ceainfotech.ndcmp.service.OutcomeService;
import com.ceainfotech.ndcmp.service.OutputServices;
import com.ceainfotech.ndcmp.service.ProjectService;
import com.ceainfotech.ndcmp.service.StrategicPillarService;
import com.ceainfotech.ndcmp.service.TargetService;
import com.ceainfotech.ndcmp.service.ThemeService;
import com.ceainfotech.ndcmp.service.UserRoleService;
import com.ceainfotech.ndcmp.service.UserService;
import com.ceainfotech.ndcmp.util.ConstantUtil;
import com.ceainfotech.ndcmp.util.PrincipalUtil;
import com.ceainfotech.ndcmp.util.Status;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 * @author Samy
 * 
 */
@Controller
@RequestMapping(value = "/api/**")
public class StrategicController {

	private static final Logger LOGGER = LoggerFactory.getLogger(StrategicController.class);

	@Autowired
	private StrategicPillarService strategicPillarService;
	
	@Autowired
	private ThemeService themeService;
	
	@Autowired
	private OutcomeService outcomeService;
	
	@Autowired
	private OutputServices outputServices;
	
	@Autowired
	private IndicatorService indicatorService;
	
	@Autowired
	private TargetService targetService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private PrincipalUtil principalUtil;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ConstantUtil constantUtil;

	@Autowired
	private HttpServletRequest request;
	
	String action,module="";
	
	/**
	 * List All States
	 * 
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "strategicPillarList")
	public ModelAndView listStates(ModelMap modelMap, HttpServletRequest request) {

		LOGGER.info("Listing all the StrategicPillars ");

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		//getAccessRights(modelMap, username);

		modelMap.addAttribute("strategicPillars", strategicPillarService.getStrategicPillars());
		//return new ModelAndView("project/projectActivity");
		//return new ModelAndView("redirect:configProject?projectId="+1);
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
			return new ModelAndView("redirect:viewProject?projectId="+1+"&action=view");
		}else if(role.getName().equals("PROJECT_ADMIN")){
			return new ModelAndView("redirect:configProject?projectId="+1+"&action=config");
		}
		return null;
	}

	/**
	 * Get add or add page
	 * 
	 * @param stateId
	 * @param states
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "/editStrategicPillar", method = RequestMethod.GET)
	public @ResponseBody StrategicPillar getStates(@ModelAttribute StrategicPillar strategicPillar, @RequestParam Integer strategicPillarId, Model model) {
		if (strategicPillarId != 0 && strategicPillarId != null) {
			strategicPillar = strategicPillarService.getById(strategicPillarId);
		}
		List<Project> projects = projectService.getProject();
		if (projects != null && projects.size() > 0) {
			for (Project project : projects) {
				model.addAttribute("ProjectName", project.getName());
			}
		}
		model.addAttribute("strategicPillar", strategicPillar);
		return strategicPillar;
	}

	@RequestMapping(value = "/getStrategicpillar", method = RequestMethod.GET)
	public @ResponseBody StrategicPillar createState(@ModelAttribute StrategicPillar strategicPillar, Model model) {

		LOGGER.info("Creating new Strategicpillar details with page {}: " + strategicPillar);
		List<Project> projects = projectService.getProject();
		if (projects != null && projects.size() > 0) {
			for (Project project : projects) {
				model.addAttribute("ProjectName", project.getName());
				strategicPillar.setProject(project);
			}
		}
		model.addAttribute("strategicPillar", strategicPillar);
		return strategicPillar;
	}

	@RequestMapping(value = "/getAddStrategicpillar", method = RequestMethod.GET)
	public @ResponseBody AddStrategicPillar getAddStrategicpillar(@ModelAttribute AddStrategicPillar addStrategicPillar, Model model) {

		LOGGER.info("Creating new Strategicpillar details with page {}: " + addStrategicPillar);
		List<Project> projects = projectService.getProject();
		if (projects != null && projects.size() > 0) {
			for (Project project : projects) {
				addStrategicPillar.setProject(project.getName());
			}
		}
		model.addAttribute("addStrategicPillar", addStrategicPillar);
		return addStrategicPillar;
	}

	/**
	 * Submit edit information or add new state information
	 * 
	 * @param states
	 * @param redirectAttributes
	 * @return
	 * @throws Exception 
	 */

	/*@RequestMapping(value = "createStrategicpillar", method = RequestMethod.POST)
	public ModelAndView saveStrategicpillar(@ModelAttribute StrategicPillar strategicPillar) throws Exception {

		LOGGER.info("Saving or updating strategicPillar data {} : " + strategicPillar);
		ModelAndView modelAndView = new ModelAndView();
		StrategicPillar pillar = strategicPillarService.getById(strategicPillar.getId());
		if(strategicPillar.getProject() != null && strategicPillar.getProject().getName() != null) {
			String projectName = strategicPillar.getProject().getName();
			Project project = projectService.getByName(projectName);
			pillar.setProject(project);
			pillar.setName(strategicPillar.getName());
		}
		pillar.setStatus(Status.ACTIVE.getName());
		if (strategicPillar.getId() == null) { // if employee id is 0 then
												// creating
			// the user
			// other updating the user
			strategicPillarService.save(strategicPillar);
			
			action="Strategicpillar "+strategicPillar.getName()+" is Saved";
			module="Strategicpillar Module";
			constantUtil.saveAudition(action, module, request);
			
		} else {
			strategicPillarService.saveAndFlush(pillar);
			
			action="Strategicpillar "+strategicPillar.getName()+" is Updated";
			module="Strategicpillar Module";
			constantUtil.saveAudition(action, module, request);
		}
		modelAndView.setViewName("redirect:strategicPillarList");
		
		return modelAndView;
	}*/
	
	/**
	 * Submit edit information or add new state information
	 * 
	 * @param states
	 * @param redirectAttributes
	 * @return
	 * @throws Exception 
	 */

	/*@RequestMapping(value = "addStrategicpillar", method = RequestMethod.POST)
	public ModelAndView addStrategicpillar(@ModelAttribute AddStrategicPillar addStrategicPillar) throws Exception {
		StrategicPillar strategicPillar = new StrategicPillar();
		Project project = new Project();
		Theme theme = new Theme();
		Outcome outcome = new Outcome();
		Output output = new Output();
		Indicator indicator = new Indicator();
		Target target = new Target();
		
		LOGGER.info("Saving or updating strategicPillar data {} : " + addStrategicPillar);
		ModelAndView modelAndView = new ModelAndView();
		
		if(addStrategicPillar != null && addStrategicPillar.getStrategicPillar() != null) {
			strategicPillar.setName(addStrategicPillar.getStrategicPillar());
		}

		if(addStrategicPillar.getProject() != null && addStrategicPillar.getProject() != null) {
			project = projectService.getByName(addStrategicPillar.getProject());
			strategicPillar.setProject(project);
		}
		
		if(addStrategicPillar != null && addStrategicPillar.getStrategicPillarSequenceNumber() != null) {
			strategicPillar.setSequenceNumber(addStrategicPillar.getStrategicPillarSequenceNumber());
		} else {
			Integer strategicPillarCount = strategicPillarService.getByProjectId(project);
			strategicPillarCount++;
			strategicPillar.setSequenceNumber(strategicPillarCount.toString());
		}
		strategicPillar.setStatus(Status.ACTIVE.getName());
		if (strategicPillar.getId() == null) { // if employee id is 0 then
												// creating
			// the user
			// other updating the user
			strategicPillarService.save(strategicPillar);
			
			action="StrategicPillar "+strategicPillar.getName()+" is Saved";
			module="Strategicpillar Module";
			constantUtil.saveAudition(action, module, request);
			
		} else {
			strategicPillarService.saveAndFlush(strategicPillar);
			
			action="StrategicPillar "+strategicPillar.getName()+" is Update";
			module="Strategicpillar Module";
			constantUtil.saveAudition(action, module, request);
			
		}
		if(strategicPillar != null && strategicPillar.getId() != null) {
			if (addStrategicPillar != null && addStrategicPillar.getTheme() != null) {
				theme.setName(addStrategicPillar.getTheme());
				theme.setStrategicPillar(strategicPillar);
				theme.setStatus(Status.ACTIVE.getName());
				themeService.save(theme);
				
				action="Theme "+theme.getName()+" is Saved";
				module="Strategicpillar Module";
				constantUtil.saveAudition(action, module, request);
			}
		}
		
		if(theme != null && theme.getId() != null) {
			if (addStrategicPillar != null && addStrategicPillar.getOutcomeSequenceNumber() != null) {
				outcome.setSequenceNumber(addStrategicPillar.getOutcomeSequenceNumber());
			} else {
				Integer outcomeCount = outcomeService.getByThemeId(theme);
				outcomeCount++;
				outcome.setSequenceNumber(outcomeCount.toString());
			}
			if(addStrategicPillar != null && addStrategicPillar.getOutcome() != null) {
				outcome.setName(addStrategicPillar.getOutcome());
				outcome.setTheme(theme);
				outcome.setStatus(Status.ACTIVE.getName());
				outcomeService.save(outcome);
				
				action="Outcome "+outcome.getName()+" is Saved";
				module="Strategicpillar Module";
				constantUtil.saveAudition(action, module, request);
			}
		}
		
		if(outcome != null && outcome.getId() != null) {
			if (addStrategicPillar != null && addStrategicPillar.getOutputSequenceNumber() != null) {
				output.setSequenceNumber(addStrategicPillar.getOutputSequenceNumber());
			} else {
				Integer outputCount = outputServices.getByOutcomeId(outcome);
				outputCount++;
				output.setSequenceNumber(outputCount.toString());
			}
			if(addStrategicPillar != null && addStrategicPillar.getOutcome() != null) {
				output.setOutput(addStrategicPillar.getOutput());
				output.setOutcome(outcome);
				output.setStatus(Status.ACTIVE.getName());
				outputServices.save(output);
				
				action="Output "+output.getOutput()+" is Saved";
				module="Strategicpillar Module";
				constantUtil.saveAudition(action, module, request);
			}
		}
		
		if(output != null && output.getId() != null) {
			if(addStrategicPillar != null && addStrategicPillar.getIndicator() != null) {
				indicator.setMessage(addStrategicPillar.getIndicator());
				indicator.setOutput(output);
				indicator.setStatus(Status.ACTIVE.getName());
				indicatorService.addIndicator(indicator);
				
				action="Indicator "+indicator.getMessage()+" is Saved";
				module="Strategicpillar Module";
				constantUtil.saveAudition(action, module, request);
			}
			if(addStrategicPillar != null && addStrategicPillar.getTarget() != null) {
				target.setMessage(addStrategicPillar.getTarget());
				target.setOutput(output);
				target.setStatus(Status.ACTIVE.getName());
				targetService.addTarget(target);
				
				action="Target "+target.getMessage()+" is Saved";
				module="Strategicpillar Module";
				constantUtil.saveAudition(action, module, request);
				
			}
		}
		modelAndView.setViewName("redirect:strategicPillarList");

		return modelAndView;
	}*/
	@RequestMapping(value = "updateStrategicPillar", method = RequestMethod.GET)
	public @ResponseBody StrategicDTO updateStrategicPillar(@RequestParam String strategicPillarJson) throws Exception {
		
		StrategicPillar strategicPillar = new StrategicPillar();
		StrategicDTO strategicDTO=new StrategicDTO();
		
		Gson gson=new GsonBuilder().create();
		JsonObject jsonObject = gson.fromJson( strategicPillarJson, JsonObject.class);
		
		Integer id=jsonObject.get("id").getAsInt();
		String strategicPillarName=jsonObject.get("strategicPillarName").getAsString();
		if(id > 0){
			StrategicPillar stPillar=strategicPillarService.getByName(strategicPillarName);
			if(stPillar !=null){
				if(!stPillar.getId().equals(id)){
					strategicDTO.setStrategicPillarNameError("Strategic Pillar Name already exist");
					return strategicDTO;
				}
			}
			strategicPillar=strategicPillarService.getById(id);
			strategicPillar.setName(strategicPillarName);
			
			strategicPillarService.save(strategicPillar);
			strategicDTO.setStrategicPillar(strategicPillar);
			action="Strategic Pillar  "+strategicPillar.getName()+" is Updated";
			constantUtil.saveAudition(action, module, request);
		}
		return strategicDTO;
	}
	@RequestMapping(value = "saveStrategicPillar", method = RequestMethod.GET)
	public @ResponseBody StrategicDTO saveStrategicPillar(@RequestParam String strategicPillarJson) throws Exception {
		
		StrategicDTO strategicDTO=new StrategicDTO();
		
		StrategicPillar strategicPillar = new StrategicPillar();
		Project project = new Project();
		Theme theme = new Theme();
		Outcome outcome = new Outcome();
		Output output = new Output();
		Indicator indicator = new Indicator();
		Target target = new Target();
		
		
		Gson gson=new GsonBuilder().create();
		JsonObject jsonObject = gson.fromJson( strategicPillarJson, JsonObject.class);
		
		module="Strategicpillar Module";
		boolean validation=true;
		Integer id=jsonObject.get("id").getAsInt();
		String strategicPillarName=jsonObject.get("strategicPillarName").getAsString();
		String pillarSN=jsonObject.get("pillarSN").getAsString();
		String themeName=jsonObject.get("theme").getAsString();
		String outcomeSN=jsonObject.get("outcomeSN").getAsString();
		String outcomeName=jsonObject.get("outcome").getAsString();
		String outputSN=jsonObject.get("outputSN").getAsString();
		String outputName=jsonObject.get("output").getAsString();
		String indicatorName=jsonObject.get("indicator").getAsString();
		String targetName=jsonObject.get("target").getAsString();
		
		if(id == 0){
			strategicPillar=strategicPillarService.getByName(strategicPillarName);
			if(strategicPillar != null){
				strategicDTO.setStrategicPillarNameError("Strategic Pillar Name already exist");
				validation=false;
			}
			strategicPillar=strategicPillarService.getBySequenceNumber(pillarSN);
			if(strategicPillar !=null){
				strategicDTO.setPillarSNError("Pillar Sequence Number already exist");
				validation=false;
			}
			
			if(validation == false){
				return strategicDTO;
			}else{
				strategicPillar=new StrategicPillar();
				if(strategicPillarName != ""){
					strategicPillar.setName(strategicPillarName);
				}
				if(pillarSN != ""){
					strategicPillar.setSequenceNumber(pillarSN);
				}
				project=projectService.getById(1);
				if(project != null){
					strategicPillar.setProject(project);
				}
				strategicPillar.setStatus(Status.ACTIVE.getName());
				
				strategicPillarService.save(strategicPillar);
				strategicDTO.setStrategicPillar(strategicPillar);
				
				action="Strategic Pillar "+strategicPillar.getName()+" is Saved";
				constantUtil.saveAudition(action, module, request);
				
				if(strategicPillar != null){
					theme.setName(themeName);
					theme.setStrategicPillar(strategicPillar);
					theme.setStatus(Status.ACTIVE.getName());
					themeService.save(theme);
					strategicDTO.setTheme(theme);
					
					action="Strategic Pillar Theme "+strategicPillar.getName()+" is Saved";
					constantUtil.saveAudition(action, module, request);
				}
				
				if(theme != null) {
					if (strategicPillar != null && outcomeSN != "") {
						outcome.setSequenceNumber(outcomeSN);
					} else {
						Integer outcomeCount = outcomeService.getByThemeId(theme);
						outcomeCount++;
						outcome.setSequenceNumber(outcomeCount.toString());
					}
					if(strategicPillar != null) {
						outcome.setName(outcomeName);
						outcome.setTheme(theme);
						outcome.setStatus(Status.ACTIVE.getName());
						outcomeService.save(outcome);
						strategicDTO.setOutcome(outcome);
						
						action="Outcome "+outcome.getName()+" is Saved";
						constantUtil.saveAudition(action, module, request);
					}
				}
				if(outcome != null ) {
					if (strategicPillar != null ) {
						output.setSequenceNumber(outputSN);
					} else {
						Integer outputCount = outputServices.getByOutcomeId(outcome);
						outputCount++;
						output.setSequenceNumber(outputCount.toString());
					}
					if(strategicPillar != null) {
						output.setOutput(outputName);
						output.setOutcome(outcome);
						output.setStatus(Status.ACTIVE.getName());
						outputServices.save(output);
						strategicDTO.setOutput(output);
						
						action="Output "+output.getOutput()+" is Saved";
						constantUtil.saveAudition(action, module, request);
					}
				}
				
				if(output != null) {
					if(strategicPillar != null) {
						indicator.setMessage(indicatorName);
						indicator.setOutput(output);
						indicator.setStatus(Status.ACTIVE.getName());
						indicatorService.addIndicator(indicator);
						strategicDTO.setIndicator(indicator);
						
						
						action="Indicator "+indicator.getMessage()+" is Saved";
						constantUtil.saveAudition(action, module, request);
					}
					if(strategicPillar != null) {
						target.setMessage(targetName);
						target.setOutput(output);
						target.setStatus(Status.ACTIVE.getName());
						targetService.addTarget(target);
						strategicDTO.setTarget(target);
						
						action="Target "+target.getMessage()+" is Saved";
						constantUtil.saveAudition(action, module, request);
						
					}
				}
			}
		}
	
		return strategicDTO;
	}
	
	
	/**
	 * Delete State information by using state id
	 * 
	 * @param stateId
	 * @return
	 * @throws Exception 
	 */

	@RequestMapping(value = "deleteStrategicPillar", method = RequestMethod.GET)
	public ModelAndView deletestrategicPillar(@RequestParam Integer id) throws Exception {

		LOGGER.info("Deleting strategic Pillar info");
		strategicPillarService.delete(id);
		
		StrategicPillar strategicPillar=strategicPillarService.getById(id);
		
		action="Strategicpillar "+strategicPillar.getName()+" is Removed";
		module="Strategicpillar Module";
		constantUtil.saveAudition(action, module, request);
		
		return new ModelAndView("redirect:strategicPillarList");
	}

	public void getAccessRights(ModelMap modelMap, String username) {

		/*User user = userService.findByUsername(username);
		List<AccessRights> accessrightslist = new ArrayList<AccessRights>();
		// List<AccessRights> accessrightslist =
		// accessRightsService.listAccessRightsByRole(user.getUserRole().getId().intValue());
		List<String> features = new ArrayList<>();
		boolean STT_ADD = false;
		boolean STT_VIW = false;
		boolean STT_EDT = false;
		boolean STT_DEL = false;

		if (accessrightslist != null) {
			for (AccessRights accessRights : accessrightslist) {
				features.add(accessRights.getFeatures().getFeaturecode());
			}
			if (features != null) {
				for (String feature : features) {
					if (feature.equals(Modules.STT_ADD.toString())) {
						STT_ADD = true;
					}
					if (feature.equals(Modules.STT_VIW.toString())) {
						STT_VIW = true;
					}
					if (feature.equals(Modules.STT_EDT.toString())) {
						STT_EDT = true;
						;
					}
					if (feature.equals(Modules.STT_DEL.toString())) {
						STT_DEL = true;
					}
				}
			}
		}
		modelMap.addAttribute("STT_ADD", STT_ADD);
		modelMap.addAttribute("STT_VIW", STT_VIW);
		modelMap.addAttribute("STT_EDT", STT_EDT);
		modelMap.addAttribute("STT_DEL", STT_DEL);*/
	}
}
