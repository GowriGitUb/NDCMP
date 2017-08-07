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
import com.ceainfotech.ndcmp.model.Outcome;
import com.ceainfotech.ndcmp.model.Output;
import com.ceainfotech.ndcmp.model.Project;
import com.ceainfotech.ndcmp.model.StrategicPillar;
import com.ceainfotech.ndcmp.model.Target;
import com.ceainfotech.ndcmp.model.Theme;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.model.UserRole;
import com.ceainfotech.ndcmp.model.dto.AddTheme;
import com.ceainfotech.ndcmp.model.dto.StrategicDTO;
import com.ceainfotech.ndcmp.service.IndicatorService;
import com.ceainfotech.ndcmp.service.OutcomeService;
import com.ceainfotech.ndcmp.service.OutputServices;
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
 * @author bosco
 * 
 */
@Controller
@RequestMapping(value = "/api/**")
public class ThemeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ThemeController.class);

	@Autowired
	ThemeService themeService;

	@Autowired
	UserService userService;
	
	@Autowired 
	StrategicPillarService strategicPillarService;
	
	@Autowired
	OutcomeService outcomeService;
	
	@Autowired
	OutputServices outputService;
	
	@Autowired
	OutputServices outputServices;
	
	@Autowired
	IndicatorService indicatorService;
	
	@Autowired
	TargetService targetService;
	
	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private PrincipalUtil principalUtil;
	
	private Integer projectId;
	
	@Autowired
	private ConstantUtil constantUtil;

	@Autowired
	private HttpServletRequest request;
	
	String action,module="";

	/**
	 * To list all the Theme
	 * 
	 * @param model
	 * @param authentication
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "themeList", method = RequestMethod.GET)
	public String userDetailsPage(ModelMap model, Authentication authentication, HttpServletRequest request) {
		LOGGER.info("Getting into user details page with user and roles {} :");

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		//getAccessRights(model, username);

		List<Theme> themeList = themeService.getTheme(Status.ACTIVE.getName());
		model.addAttribute("themes", themeList);
		model.addAttribute("strategicPillar", new StrategicPillar());
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
	 * To get the theme details for edit page
	 * 
	 * @param theme
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "editTheme", method = RequestMethod.GET)
	public @ResponseBody Theme editTheme(@ModelAttribute Theme theme, @RequestParam Integer themeId, Model model) {
		List<StrategicPillar> pillars = strategicPillarService.getStrategicPillars();
		if (themeId != 0) {
			theme = themeService.getById(themeId);
		}
		model.addAttribute("strategicPillars",pillars);
		model.addAttribute("theme", theme);
		return theme;

	}
	
	/**
	 * To get the theme details for edit page
	 * 
	 * @param theme
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "getTheme", method = RequestMethod.GET)
	public @ResponseBody Theme getTheme(@ModelAttribute Theme theme, @RequestParam Integer strategicPillarId, Model model) {
		StrategicPillar strategicPillar = new StrategicPillar();
		if (strategicPillarId != null) {
			strategicPillar = strategicPillarService.getById(strategicPillarId);
			if(strategicPillar != null && strategicPillar.getId() != null) {
				projectId = strategicPillar.getProject().getId();
			}
			model.addAttribute("strategicPillarName", strategicPillar.getName());
			theme.setStrategicPillar(strategicPillar);
		}
		model.addAttribute("strategicPillar",strategicPillar);
		model.addAttribute("theme", theme);
		return theme;

	}
	
	@RequestMapping(value = "/getAddTheme", method = RequestMethod.GET)
	public @ResponseBody AddTheme getAddTheme(@ModelAttribute AddTheme addTheme, @RequestParam Integer strategicPillarId, Model model) {

		LOGGER.info("Creating new Strategicpillar details with page {}: " + addTheme);
		StrategicPillar strategicPillar = new StrategicPillar();
		if (strategicPillarId != null) {
			strategicPillar = strategicPillarService.getById(strategicPillarId);
			if(strategicPillar != null && strategicPillar.getId() != null) {
				addTheme.setStrategicPillar(strategicPillar.getName());
			}
		}
		return addTheme;
	}

	/**
	 * Save or update theme information
	 * 
	 * @param theme
	 * @return
	 * @throws Exception 
	 */

	@RequestMapping(value = "createTheme", method = RequestMethod.POST)
	public ModelAndView createTheme(@ModelAttribute Theme theme) throws Exception {
		LOGGER.info("Saving or updating State data {} : " + theme);
		ModelAndView modelAndView = new ModelAndView();
		StrategicPillar strategicPillar = strategicPillarService.getByName(theme.getStrategicPillar().getName());
		if(strategicPillar != null && strategicPillar.getId() != null) {
			theme.setStrategicPillar(strategicPillar);
		}
		theme.setStatus(Status.ACTIVE.getName());
		if (theme.getId() == null) { // if employee id is 0 then creating
			
			themeService.save(theme);
			
			action="Theme "+theme.getName()+" is Saved";
			module="Theme Module";
			constantUtil.saveAudition(action, module, request);
			
		} else {
			themeService.saveAndFlush(theme);
			
			action="Theme "+theme.getName()+" is Updated";
			module="Theme Module";
			constantUtil.saveAudition(action, module, request);
		}
		modelAndView.setViewName("redirect:themeList");
		return modelAndView;
	}

	
	
	/**
	 * Add theme information
	 * 
	 * @param theme
	 * @return
	 * @throws Exception 
	 */

	/*@RequestMapping(value = "addTheme", method = RequestMethod.POST)
	public ModelAndView addTheme(@ModelAttribute AddTheme addTheme) throws Exception {
		
		StrategicPillar strategicPillar = new StrategicPillar();
		Theme theme = new Theme();
		Outcome outcome = new Outcome();
		Output output = new Output();
		Indicator indicator = new Indicator();
		Target target = new Target();
		
		LOGGER.info("Saving or updating State data {} : " + addTheme);
		ModelAndView modelAndView = new ModelAndView();
		
		if(addTheme != null && addTheme.getStrategicPillar()!=null){
			strategicPillar = strategicPillarService.getByName(addTheme.getStrategicPillar());
		}
		
		if(addTheme.getTheme()!=null){
			theme.setName(addTheme.getTheme());
			theme.setStrategicPillar(strategicPillar);
			theme.setStatus(Status.ACTIVE.getName());
			themeService.save(theme);
			
			action="Theme "+theme.getName()+" is Saved";
			module="Theme Module";
			constantUtil.saveAudition(action, module, request);
		}
		
		if(theme != null && theme.getId() != null) {
			if (addTheme != null && addTheme.getOutcomeSequenceNumber() != null) {
				outcome.setSequenceNumber(addTheme.getOutcomeSequenceNumber());
			} else {
				Integer outcomeCount = outcomeService.getByThemeId(theme);
				outcomeCount++;
				outcome.setSequenceNumber(outcomeCount.toString());
			}
			if(addTheme != null && addTheme.getOutcome() != null) {
				outcome.setName(addTheme.getOutcome());
				outcome.setTheme(theme);
				outcome.setStatus(Status.ACTIVE.getName());
				outcomeService.save(outcome);
				
				action="Outcome "+outcome.getName()+" is Saved";
				module="Theme Module";
				constantUtil.saveAudition(action, module, request);
			}
		}
		if(outcome != null && outcome.getId() != null) {
			if (addTheme != null && addTheme.getOutputSequenceNumber() != null) {
				output.setSequenceNumber(addTheme.getOutputSequenceNumber());
			} else {
				Integer outputCount = outputServices.getByOutcomeId(outcome);
				outputCount++;
				output.setSequenceNumber(outputCount.toString());
			}
			if(addTheme != null && addTheme.getOutcome() != null) {
				output.setOutput(addTheme.getOutput());
				output.setOutcome(outcome);
				output.setStatus(Status.ACTIVE.getName());
				outputServices.save(output);
				
				action="Output "+output.getOutput()+" is Saved";
				module="Theme Module";
				constantUtil.saveAudition(action, module, request);
			}
		}
		
		if(output != null && output.getId() != null) {
			if(addTheme != null && addTheme.getIndicator() != null) {
				indicator.setMessage(addTheme.getIndicator());
				indicator.setOutput(output);
				indicator.setStatus(Status.ACTIVE.getName());
				indicatorService.addIndicator(indicator);
				
				action="Indicator "+indicator.getMessage()+" is Saved";
				module="Theme Module";
				constantUtil.saveAudition(action, module, request);
			}
			if(addTheme != null && addTheme.getTarget() != null) {
				target.setMessage(addTheme.getTarget());
				target.setOutput(output);
				target.setStatus(Status.ACTIVE.getName());
				targetService.addTarget(target);
				
				
				action="Target "+target.getMessage()+" is Saved";
				module="Theme Module";
				constantUtil.saveAudition(action, module, request);
			}
		}
		
		modelAndView.setViewName("redirect:themeList");
		return modelAndView;
	}*/

	@RequestMapping(value = "updateTheme", method = RequestMethod.GET)
	public @ResponseBody StrategicDTO updateTheme(@RequestParam String themeJson) throws Exception {
		
		Theme theme=new Theme();
		
		StrategicPillar strategicPillar=new StrategicPillar();
		StrategicDTO strategicDTO=new StrategicDTO();
		
		Gson gson=new GsonBuilder().create();
		JsonObject jsonObject = gson.fromJson( themeJson, JsonObject.class);
		
		Integer id=jsonObject.get("themeid").getAsInt();
		Integer pillarid=jsonObject.get("pillarid").getAsInt();
		String name=jsonObject.get("editThemeName").getAsString();
		
		
		if(id > 0){
			theme=themeService.getById(id);
			if(theme != null){
				strategicPillar=strategicPillarService.getById(pillarid);
				
				Theme theme2=themeService.getByName(name);
				if(theme2 != null){
					if(!theme2.getId().equals(id)){
						strategicDTO.setThemeNameError("Theme Name already exist");
						return strategicDTO;	
					}
				}
				
				
				if(strategicPillar != null){
					theme.setStrategicPillar(strategicPillar);
				}
				theme.setName(name);
				themeService.save(theme);
				strategicDTO.setTheme(theme);
				action="Theme  "+theme.getName()+" is Updated";
			}
		}
		
		constantUtil.saveAudition(action, module, request);
		return strategicDTO;
	}
	
	@RequestMapping(value="/saveTheme",method=RequestMethod.GET)
	public @ResponseBody StrategicDTO saveTheme(@RequestParam String themeJson) throws Exception{
		
		StrategicDTO strategicDTO=new StrategicDTO();
		
		StrategicPillar strategicPillar = new StrategicPillar();
		Theme theme = new Theme();
		Outcome outcome = new Outcome();
		Output output = new Output();
		Indicator indicator = new Indicator();
		Target target = new Target();
		boolean validation=true;
		
		Gson gson=new GsonBuilder().create();
		JsonObject jsonObject = gson.fromJson( themeJson, JsonObject.class);
		
		module="Theme Module";
		
		Integer id=jsonObject.get("id").getAsInt();
		Integer pillarId=jsonObject.get("pillarId").getAsInt();
		String themeName=jsonObject.get("themeName").getAsString();
		String outcomeSeqN=jsonObject.get("outcomeSeqN").getAsString();
		String theme_outcome=jsonObject.get("theme_outcome").getAsString();
		String outputSeqN=jsonObject.get("outputSeqN").getAsString();
		String theme_output=jsonObject.get("theme_output").getAsString();
		String theme_indicator=jsonObject.get("theme_indicator").getAsString();
		String theme_target=jsonObject.get("theme_target").getAsString();
		
		
		if(pillarId > 0){
			strategicPillar=strategicPillarService.getById(pillarId);
			strategicDTO.setStrategicPillar(strategicPillar);
		}
		
		if(id == 0){
			theme=themeService.getByThemeAndStrategicPillar(themeName, strategicPillar);
			if(theme != null){
				strategicDTO.setThemeNameError("Theme Name already exist");
				validation=false;
			}
			
			if(validation == false){
				return strategicDTO;
			}else{
				strategicPillar=strategicPillarService.getById(pillarId);
				theme=new Theme();
				if(strategicPillar != null){
					if(themeName != ""){
						theme.setName(themeName);
					}
					
					theme.setStrategicPillar(strategicPillar);
					theme.setStatus(Status.ACTIVE.getName());
					themeService.save(theme);
					strategicDTO.setTheme(theme);
					
					action="Strategic Pillar Theme "+strategicPillar.getName()+" is Saved";
					constantUtil.saveAudition(action, module, request);
				}
				
				if(theme != null) {
					if (strategicPillar != null && outcomeSeqN != "") {
						outcome.setSequenceNumber(outcomeSeqN);
					} else {
						Integer outcomeCount = outcomeService.getByThemeId(theme);
						outcomeCount++;
						outcome.setSequenceNumber(outcomeCount.toString());
					}
					if(strategicPillar != null) {
						outcome.setName(theme_outcome);
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
						output.setSequenceNumber(outputSeqN);
					} else {
						Integer outputCount = outputServices.getByOutcomeId(outcome);
						outputCount++;
						output.setSequenceNumber(outputCount.toString());
					}
					if(strategicPillar != null) {
						output.setOutput(theme_output);
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
						indicator.setMessage(theme_indicator);
						indicator.setOutput(output);
						indicator.setStatus(Status.ACTIVE.getName());
						indicatorService.addIndicator(indicator);
						strategicDTO.setIndicator(indicator);
						
						
						action="Indicator "+indicator.getMessage()+" is Saved";
						constantUtil.saveAudition(action, module, request);
					}
					if(strategicPillar != null) {
						target.setMessage(theme_target);
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
	 * Delete the theme information by theme id
	 * 
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/deleteTheme", method = RequestMethod.GET)
	public ModelAndView deleteTheme(@RequestParam Integer themeId) {
		LOGGER.info("Deleting the User By Id {} : " + themeId);
		try {
			if (themeId != null) {
				Theme theme = themeService.getById(themeId);
				if (theme != null && theme.getId() != null) {
					themeService.deleteByTheme(theme);
					
					action="Theme "+theme.getName()+" is Removed";
					module="Theme Module";
					constantUtil.saveAudition(action, module, request);
				}
				// userService.delete(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:themeList");
	}

	public void getAccessRights(ModelMap modelMap, String username) {

		/*User user = userService.findByUsername(username);
		List<AccessRights> accessrightslist = new ArrayList<AccessRights>();
		// List<AccessRights> accessrightslist =
		// accessRightsService.listAccessRightsByRole(user.getUserRole().getId().intValue());
		List<String> features = new ArrayList<>();
		boolean USR_ADD = false;
		boolean USR_VIW = false;
		boolean USR_EDT = false;
		boolean USR_DEL = false;

		if (accessrightslist != null) {
			for (AccessRights accessRights : accessrightslist) {
				features.add(accessRights.getFeatures().getFeaturecode());
			}
			if (features != null) {
				for (String feature : features) {
					if (feature.equals(Modules.USR_ADD.toString())) {
						USR_ADD = true;
					}
					if (feature.equals(Modules.USR_VIW.toString())) {
						USR_VIW = true;
					}
					if (feature.equals(Modules.USR_EDT.toString())) {
						USR_EDT = true;
						;
					}
					if (feature.equals(Modules.USR_DEL.toString())) {
						USR_DEL = true;
					}
				}
			}
		}
		modelMap.addAttribute("USR_ADD", USR_ADD);
		modelMap.addAttribute("USR_VIW", USR_VIW);
		modelMap.addAttribute("USR_EDT", USR_EDT);
		modelMap.addAttribute("USR_DEL", USR_DEL);*/
	}

}
