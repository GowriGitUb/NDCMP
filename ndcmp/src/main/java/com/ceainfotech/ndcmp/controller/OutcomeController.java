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
import com.ceainfotech.ndcmp.model.StrategicPillar;
import com.ceainfotech.ndcmp.model.Target;
import com.ceainfotech.ndcmp.model.Theme;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.model.UserRole;
import com.ceainfotech.ndcmp.model.dto.AddOutcome;
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
public class OutcomeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OutcomeController.class);
	
	@Autowired
	private OutcomeService outcomeService;
	
	@Autowired
	private ThemeService themeService;
	
	@Autowired
	private OutputServices outputServices;
	
	@Autowired
	private IndicatorService indicatorService;
	
	@Autowired
	private TargetService targetService;

	@Autowired
	private StrategicPillarService strategicPillarService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private PrincipalUtil principalUtil;
	
	

	@Autowired
	private ConstantUtil constantUtil;

	@Autowired
	private HttpServletRequest request;
	
	String action="";
	String module="Outcome Module";

	/**
	 * List All States
	 * 
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "outcomeList")
	public ModelAndView listStates(ModelMap modelMap, HttpServletRequest request) {

		LOGGER.info("Listing all the Outcome ");

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		//getAccessRights(modelMap, username);

		modelMap.addAttribute("outcomeList", outcomeService.getOutcomes());
		modelMap.addAttribute("strategicPillar", new StrategicPillar());
		modelMap.addAttribute("theme", new Theme());
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

	@RequestMapping(value = "/editOutcome", method = RequestMethod.GET)
	public @ResponseBody Outcome getStates(@ModelAttribute Outcome outcome, @RequestParam Integer outcomeId, Model model) {
		if (outcomeId != 0 && outcomeId != null) {
			outcome = outcomeService.getById(outcomeId);
		}
		if(outcome.getTheme() != null && outcome.getTheme().getId() != null) {
			Theme theme = themeService.getById(outcome.getTheme().getId());
			if (theme != null && theme.getId() != null) {
				StrategicPillar strategicPillar = strategicPillarService.getById(theme.getStrategicPillar().getId());
				if(strategicPillar != null && strategicPillar.getId() != null) {
					List<Theme> themes = themeService.getThemeByStrategicPillar(strategicPillar);
					if (themes != null && themes.size() > 0) {
						model.addAttribute("themeList", themes);
					}
				}
			}
		}
		model.addAttribute("outcome", outcome);
		return outcome;
	}

	@RequestMapping(value = "/getOutcome", method = RequestMethod.GET)
	public String createState(@ModelAttribute Outcome outcome, Model model) {
		LOGGER.info("Creating new Outcome details with page {}: ", outcome);
		if(outcome.getTheme() != null && outcome.getTheme().getId() != null) {
			Theme theme = themeService.getById(outcome.getTheme().getId());
			if (theme != null && theme.getId() != null) {
				StrategicPillar strategicPillar = strategicPillarService.getById(theme.getStrategicPillar().getId());
				if(strategicPillar != null && strategicPillar.getId() != null) {
					List<Theme> themes = themeService.getThemeByStrategicPillar(strategicPillar);
					if (themes != null && themes.size() > 0) {
						model.addAttribute("themeList", themes);
					}
				}
			}
		}
		model.addAttribute("outcome", outcome);
		return "outcome/addOutcome";
	}
	
	/**
	 * To get the theme details for edit page
	 * 
	 * @param theme
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "getHierarchyOutcome", method = RequestMethod.GET)
	public @ResponseBody Outcome getTheme(@ModelAttribute Outcome outcome, @RequestParam Integer themeId, Model model) {
		Theme theme = new Theme();
		if (themeId != null) {
			theme = themeService.getById(themeId);
			model.addAttribute("themeName", theme.getName());
			outcome.setTheme(theme);
		}
		model.addAttribute("theme", theme);
		return outcome;

	}
	
	/**
	 * To get the theme details for edit page
	 * 
	 * @param theme
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "getAddOutcome", method = RequestMethod.GET)
	public @ResponseBody AddOutcome getTheme(@ModelAttribute AddOutcome addOutcome, @RequestParam Integer themeId, Model model) {
		Theme theme = new Theme();
		if (themeId != null) {
			theme = themeService.getById(themeId);
			if(theme != null && theme.getId() != null) {
				addOutcome.setTheme(theme.getName());
			}
		}
		return addOutcome;

	}

	/**
	 * Submit edit information or add new state information
	 * 
	 * @param states
	 * @param redirectAttributes
	 * @return
	 * @throws Exception 
	 */

	@RequestMapping(value = "createOutcome", method = RequestMethod.POST)
	public ModelAndView saveOutcome(@ModelAttribute Outcome outcome) throws Exception {

		LOGGER.info("Saving or updating outcome data {} : " + outcome);
		ModelAndView modelAndView = new ModelAndView();

		if(outcome.getTheme() != null && outcome.getTheme().getName() != null) {
			Theme theme = themeService.getByName(outcome.getTheme().getName());
			if(theme != null && theme.getId() != null) {
				Integer outcomeCount = outcomeService.getByThemeId(theme);
				outcomeCount++;
				if(outcomeCount != null) {
					outcome.setSequenceNumber(outcomeCount.toString());
				}
				outcome.setTheme(theme);
			}
		}
		outcome.setStatus(Status.ACTIVE.getName());
		if (outcome.getId() == null) { // if employee id is 0 then
												// creating
			// the user
			// other updating the user
			outcomeService.save(outcome);
			
			action="Outcome "+outcome.getName()+" is Saved";
			module="Outcome Module";
			constantUtil.saveAudition(action, module, request);
			
		} else {
			outcomeService.saveAndFlush(outcome);
			
			action="Outcome "+outcome.getName()+" is Updated";
			module="Outcome Module";
			constantUtil.saveAudition(action, module, request);
			
		}
		modelAndView.setViewName("redirect:outcomeList");

		return modelAndView;
	}


	@RequestMapping(value = "updateOutcome", method = RequestMethod.GET)
	public @ResponseBody StrategicDTO updateOutcome(@RequestParam String outcomeJson) throws Exception {
		Outcome outcome=new Outcome();
		Theme theme=new Theme();
		StrategicDTO strategicDTO=new StrategicDTO();
		
		Gson gson=new GsonBuilder().create();
		JsonObject jsonObject = gson.fromJson( outcomeJson, JsonObject.class);
		
		Integer id=jsonObject.get("outcomeId").getAsInt();
		Integer themeId=jsonObject.get("themeId").getAsInt();
		String outcomeName=jsonObject.get("outcomeName").getAsString();
		
		if(id > 0){
			outcome=outcomeService.getById(id);
			
			Outcome outcome2=outcomeService.getByName(outcomeName);
			
			if(outcome2 != null){
				if(!outcome2.getId().equals(id)){
					strategicDTO.setOutcomeNameError("Outcome is already exist");
					return strategicDTO;
				}
			}
			
			if(outcome != null){
				theme=themeService.getById(themeId);
				if(theme != null){
					outcome.setTheme(theme);
					outcome.setStatus(Status.ACTIVE.getName());
					outcome.setName(outcomeName);
					outcomeService.save(outcome);
					strategicDTO.setOutcome(outcome);
					action="Outcome  "+outcome.getName()+" is Updated";
					constantUtil.saveAudition(action, module, request);
				}
			}
		}
		
		
		return strategicDTO;
	}
	
	
	/**
	 * 
	 * @param saveOutcome
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping(value = "saveOutcome", method = RequestMethod.GET)
	public @ResponseBody StrategicDTO saveOutcome(@RequestParam String outcomeJson) throws Exception {
	
		StrategicDTO strategicDTO=new StrategicDTO();
		
		
		StrategicPillar strategicPillar = new StrategicPillar();
		Theme theme = new Theme();
		Outcome outcome = new Outcome();
		Output output = new Output();
		Indicator indicator = new Indicator();
		Target target = new Target();
		boolean validation=true;
		
		Gson gson=new GsonBuilder().create();
		JsonObject jsonObject = gson.fromJson( outcomeJson, JsonObject.class);
		
		module="Outcome Module";
		
		Integer id=jsonObject.get("outcomeId").getAsInt();
		Integer thmId=jsonObject.get("thmId").getAsInt();
		String outcoSen=jsonObject.get("outcoSen").getAsString();
		String outcmen=jsonObject.get("outcmen").getAsString();
		String outSen=jsonObject.get("outSen").getAsString();
		String outputn=jsonObject.get("outputn").getAsString();
		String indicatorname=jsonObject.get("indicatorname").getAsString();
		String targetname=jsonObject.get("targetname").getAsString();
		
		
		if(thmId > 0){
			theme=themeService.getById(thmId);
			
			strategicPillar=theme.getStrategicPillar();
			strategicDTO.setStrategicPillar(strategicPillar);
			strategicDTO.setTheme(theme);
		}
		
		if(id == 0){
			outcome=outcomeService.findByNameAndTheme(outcmen, theme);
			
			if(outcome != null){
				strategicDTO.setOutcomeNameError("Outcome is already exist");
				validation=false;
			}
			
			if(validation == false){
				return strategicDTO;
			}else{
				outcome=new Outcome();
				
				if(theme != null){
					
					if(outcmen != ""){
						outcome.setName(outcmen);
					}
					
					outcome.setTheme(theme);
					outcome.setSequenceNumber(outcoSen);
					outcome.setStatus(Status.ACTIVE.getName());
					outcome.setName(outcmen);
					outcomeService.save(outcome);
					strategicDTO.setOutcome(outcome);
					
					action="Outcome "+outcome.getName()+" is Saved";
					constantUtil.saveAudition(action, module, request);
				}
				
				
				if(outcome != null ) {
					if (strategicPillar != null ) {
						output.setSequenceNumber(outSen);
					} else {
						Integer outputCount = outputServices.getByOutcomeId(outcome);
						outputCount++;
						output.setSequenceNumber(outputCount.toString());
					}
					if(strategicPillar != null) {
						output.setOutput(outputn);
						output.setOutcome(outcome);
						output.setStatus(Status.ACTIVE.getName());
						outputServices.save(output);
						strategicDTO.setOutput(output);
						
						action="Output "+output.getOutput()+" is Saved";
						constantUtil.saveAudition(action, module, request);
					}
				}
				
				if(output != null) {
					if(outcome != null) {
						indicator.setMessage(indicatorname);
						indicator.setOutput(output);
						indicator.setStatus(Status.ACTIVE.getName());
						indicatorService.addIndicator(indicator);
						strategicDTO.setIndicator(indicator);
						
						
						action="Indicator "+indicator.getMessage()+" is Saved";
						constantUtil.saveAudition(action, module, request);
					}
					if(outcome != null) {
						target.setMessage(targetname);
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
	 * Submit edit information or add new state information
	 * 
	 * @param states
	 * @param redirectAttributes
	 * @return
	 * @throws Exception 
	 */

	/*@RequestMapping(value = "addOutcome", method = RequestMethod.POST)
	public ModelAndView addOutcome(@ModelAttribute AddOutcome addOutcome) throws Exception {
		Theme theme = new Theme();
		Outcome outcome = new Outcome();
		Output output = new Output();
		Indicator indicator = new Indicator();
		Target target = new Target();
		
		LOGGER.info("Saving or updating strategicPillar data {} : " + addOutcome);
		ModelAndView modelAndView = new ModelAndView();
		
		if(addOutcome != null && addOutcome.getTheme() != null) {
			theme = themeService.getByName(addOutcome.getTheme());
		}
		if(theme != null && theme.getId() != null) {
			if (addOutcome != null && addOutcome.getOutcomeSequenceNumber() != null) {
				outcome.setSequenceNumber(addOutcome.getOutcomeSequenceNumber());
			} else {
				Integer outcomeCount = outcomeService.getByThemeId(theme);
				outcomeCount++;
				outcome.setSequenceNumber(outcomeCount.toString());
			}
			if(addOutcome != null && addOutcome.getOutcome() != null) {
				outcome.setName(addOutcome.getOutcome());
				outcome.setTheme(theme);
				outcome.setStatus(Status.ACTIVE.getName());
				outcomeService.save(outcome);
				
				action="Outcome "+outcome.getName()+" is Updated";
				module="Outcome Module";
				constantUtil.saveAudition(action, module, request);
			}
		}
		
		if(outcome != null && outcome.getId() != null) {
			if (addOutcome != null && addOutcome.getOutputSequenceNumber() != null) {
				output.setSequenceNumber(addOutcome.getOutputSequenceNumber());
			} else {
				Integer outputCount = outputServices.getByOutcomeId(outcome);
				outputCount++;
				output.setSequenceNumber(outputCount.toString());
			}
			if(addOutcome != null && addOutcome.getOutcome() != null) {
				output.setOutput(addOutcome.getOutput());
				output.setOutcome(outcome);
				output.setStatus(Status.ACTIVE.getName());
				outputServices.save(output);
				
				action="Output "+output.getOutput()+" is Saved";
				module="Outcome Module";
				constantUtil.saveAudition(action, module, request);
			}
		}
		
		if(output != null && output.getId() != null) {
			if(addOutcome != null && addOutcome.getIndicator() != null) {
				indicator.setMessage(addOutcome.getIndicator());
				indicator.setOutput(output);
				indicatorService.addIndicator(indicator);
				
				action="Indicator "+indicator.getMessage()+" is Saved";
				module="Outcome Module";
				constantUtil.saveAudition(action, module, request);
			}
			if(addOutcome != null && addOutcome.getTarget() != null) {
				target.setMessage(addOutcome.getTarget());
				target.setOutput(output);
				targetService.addTarget(target);
				
				action="Target "+target.getMessage()+" is Saved";
				module="Outcome Module";
				constantUtil.saveAudition(action, module, request);
			}
		}
		modelAndView.setViewName("redirect:outcomeList");

		return modelAndView;
	}*/

	/**
	 * Delete State information by using state id
	 * 
	 * @param stateId
	 * @return
	 * @throws Exception 
	 */

	@RequestMapping(value = "deleteOutcome", method = RequestMethod.GET)
	public ModelAndView deleteOutcome(@RequestParam Integer id) throws Exception {

		LOGGER.info("Deleting Outcome info");
		outcomeService.delete(id);
		
		Outcome outcome=outcomeService.getById(id);
		action="Outcome "+outcome.getName()+" is Removed";
		module="Outcome Module";
		constantUtil.saveAudition(action, module, request);
		
		return new ModelAndView("redirect:outcomeList");
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
		boolean STT_DEL = false;*/

		/*if (accessrightslist != null) {
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
