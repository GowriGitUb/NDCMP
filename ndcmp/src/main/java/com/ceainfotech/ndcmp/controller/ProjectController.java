/**
 * 
 */
package com.ceainfotech.ndcmp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ceainfotech.ndcmp.model.Countries;
import com.ceainfotech.ndcmp.model.Indicator;
import com.ceainfotech.ndcmp.model.KeyActivity;
import com.ceainfotech.ndcmp.model.Outcome;
import com.ceainfotech.ndcmp.model.Output;
import com.ceainfotech.ndcmp.model.Project;
import com.ceainfotech.ndcmp.model.Region;
import com.ceainfotech.ndcmp.model.States;
import com.ceainfotech.ndcmp.model.StrategicPillar;
import com.ceainfotech.ndcmp.model.SubActivity;
import com.ceainfotech.ndcmp.model.Target;
import com.ceainfotech.ndcmp.model.Theme;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.model.UserRole;
import com.ceainfotech.ndcmp.repository.ProjectRepository;
import com.ceainfotech.ndcmp.service.CountryService;
import com.ceainfotech.ndcmp.service.IndicatorService;
import com.ceainfotech.ndcmp.service.KeyActivityService;
import com.ceainfotech.ndcmp.service.OutcomeService;
import com.ceainfotech.ndcmp.service.OutputServices;
import com.ceainfotech.ndcmp.service.ProjectService;
import com.ceainfotech.ndcmp.service.RegionService;
import com.ceainfotech.ndcmp.service.ReportingPeriodService;
import com.ceainfotech.ndcmp.service.StatesService;
import com.ceainfotech.ndcmp.service.StrategicPillarService;
import com.ceainfotech.ndcmp.service.SubActivityService;
import com.ceainfotech.ndcmp.service.TargetService;
import com.ceainfotech.ndcmp.service.ThemeService;
import com.ceainfotech.ndcmp.service.UserRoleService;
import com.ceainfotech.ndcmp.service.UserService;
import com.ceainfotech.ndcmp.util.ConstantUtil;
import com.ceainfotech.ndcmp.util.Helper;
import com.ceainfotech.ndcmp.util.HierarchyStrategicPillar;
import com.ceainfotech.ndcmp.util.PrincipalUtil;
import com.ceainfotech.ndcmp.util.Status;

/**
 * @author bosco
 * 
 */
@Controller
@RequestMapping(value = "/api/**")
public class ProjectController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

	@Autowired
	ProjectService projectService;
	
	@Autowired
	PrincipalUtil principalUtil;

	@Autowired
	StrategicPillarService strategicPillarService;

	@Autowired
	ThemeService themeService;

	@Autowired
	OutcomeService outcomeService;
	
	@Autowired
	OutputServices outputServices;
	
	@Autowired
	KeyActivityService keyActivityService;
	
	@Autowired
	SubActivityService subActivityService;
	
	@Autowired
	ReportingPeriodService reportingPeriodService;

	@Autowired
	UserService userService;

	@Autowired
	CountryService countryService;

	@Autowired
	StatesService statesService;

	@Autowired
	RegionService regionService;

	@Autowired
	UserRoleService userRoleService;
	
	@Autowired
	TargetService targetService;
	
	@Autowired
	ProjectRepository projectRepository; 

	@Autowired
	IndicatorService indicatorService; 
	
	@Autowired
	private Helper helper;
	
	@Autowired
	private HttpServletRequest request;
	
	/**
	 * Get all the project
	 * 
	 * @param model
	 * @param authentication
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "projectList", method = RequestMethod.GET)
	public String listProject(ModelMap model, Authentication authentication, HttpServletRequest request) {
		// LOGGER.info("Getting into user details page with user and roles {} :"
		// + principalUtil.getPrincipal());
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		getAccessRights(model, username);
		List<Project> projectList = projectService.getProject();
		int projectCount = projectService.getProjectCount();
		model.addAttribute("projectCount", projectCount);
		model.addAttribute("projectList", projectList);
		return "projects/projectslist";
	}
	
	/**
	 * Get all the project
	 * 
	 * @param model
	 * @param authentication
	 * @param request
	 * @return
	 */

	/*@RequestMapping(value = "filterHierarchy", method = RequestMethod.GET)
	public String filterHierarchy(ModelMap model, Authentication authentication, HttpServletRequest request) {
		List<String> years = reportingPeriodService.getYears();
		model.addAttribute("years", years);
		model.addAttribute("filterHierarchy", new FilterHierarchy());
		return "project/filterHierarchy";
	}*/

	/**
	 * Get the add details of the project
	 * 
	 * @param project
	 * @param projectId
	 * @param httpServletRequest
	 * @return
	 */

	@RequestMapping(value = "createProject")
	public ModelAndView addProjectpage(@ModelAttribute Project project, @RequestParam Integer projectId, HttpServletRequest httpServletRequest) {
		ModelAndView modelAndView = new ModelAndView("projects/projectsadd");
		if (projectId != 0) {
			project = projectService.getById(projectId);
		}
		List<Countries> countries1 = new ArrayList<Countries>();
		List<Countries> countries = countryService.getCountries();
		for (Countries countries2 : countries) {
			countries1.add(countries2);
		}
		modelAndView.addObject("countrylist", countries1);
		List<States> states = new ArrayList<States>();
		List<States> states2 = statesService.getStates();
		for (States states3 : states2) {
			states.add(states3);
		}
		modelAndView.addObject("statelist", states);
		List<Region> regions = new ArrayList<Region>();
		List<Region> regions2 = regionService.getRegions();
		for (Region region : regions2) {
			regions.add(region);
		}
		UserRole userRole = userRoleService.findByName("PROJECT_ADMIN");
		if (userRole != null && userRole.getId() != null) {
			User projectadmin = userService.getByProjectadmin(userRole);
			if (projectadmin != null && projectadmin.getId() != null) {
				modelAndView.addObject("projectadmin", projectadmin.getFirstName() + " " + projectadmin.getLastName());
			}
		}
		modelAndView.addObject("regionlist", regions);
		modelAndView.addObject("project", project);
		return modelAndView;
	}

	/**
	 * Add new project
	 * 
	 * @param project
	 * @return
	 */

	@RequestMapping(value = "addAndEditProject", method = RequestMethod.POST)
	public ModelAndView addAndEditProject(@ModelAttribute Project project) {
		LOGGER.info("Save or update Project data {} : " + project);
		ModelAndView modelAndView = new ModelAndView();

		Integer countryId = project.getCountry().getId();
		Countries countries = countryService.getById(countryId);
		project.setCountry(countries);

		Integer stateId = project.getStates().getId();
		States states = statesService.getById(stateId);
		project.setStates(states);

		Integer regionId = project.getRegion().getId();
		Region region = regionService.getById(regionId);
		project.setRegion(region);
		project.setStatus(Status.ACTIVE.getName());

		if (project.getId() == null || project.getId() == 0) {
			projectService.save(project);
		} else {
			projectService.update(project);
		}
		modelAndView.setViewName("redirect:projectList");
		return modelAndView;
	}

	/**
	 * Delete the project by project id
	 * 
	 * @param projectId
	 * @return
	 */

	@RequestMapping(value = "deleteProject")
	public ModelAndView deleteProject(@RequestParam Integer projectId) {
		projectService.delete(projectId);
		return new ModelAndView("redirect:projectList");
	}

	/**
	 * Get all the project hierarchy
	 * 
	 * @param model
	 * @param authentication
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "viewProject", method = RequestMethod.GET)
	public String viewProject(ModelMap model, @RequestParam Integer projectId,@RequestParam("action") String action, HttpServletRequest request) {
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE) || principalUtil.getCurrentUserRole().equals(ConstantUtil.PROJECTADMIN_ROLE) ||principalUtil.getCurrentUserRole().equals(ConstantUtil.PROJECT_PLANNER) ||principalUtil.getCurrentUserRole().equals(ConstantUtil.STATUS_APPROVER)){
			model.addAttribute("strategicPillar", new StrategicPillar());
			model.addAttribute("theme", new Theme());
			model.addAttribute("outcome", new Outcome());
			model.addAttribute("output", new Output());
			model.addAttribute("keyActivity", new KeyActivity());
			model.addAttribute("subActivity", new SubActivity());
			model.addAttribute("indicator", new Indicator());
			model.addAttribute("target", new Target());
			Project project = projectService.getById(projectId);
			List<StrategicPillar> strategicPillars = new ArrayList<StrategicPillar>();
			if(action.equalsIgnoreCase("view")){
			if (project != null && project.getId() != null) {
				strategicPillars = strategicPillarService.getByProject(project);
				if (strategicPillars != null && strategicPillars.size() > 0) {
					for (StrategicPillar strategicPillar : strategicPillars) {
						if(strategicPillar != null && strategicPillar.getId() != null) {
							List<Theme> themes = themeService.getThemeByStrategicPillar(strategicPillar);
							if(themes != null && themes.size() > 0) {
								strategicPillar.setThemes(themes);
								for(Theme theme : themes) {
									if(theme != null && theme.getId() != null) {
										List<Outcome> outcomes = outcomeService.getByTheme(theme);
										if (outcomes != null && outcomes.size() > 0) {
											theme.setOutcomes(outcomes);
											for (Outcome outcome: outcomes) {
												if(outcome != null && outcome.getId() != null) {
													List<Output> outputs = outputServices.getByOutcome(outcome);
													if(outputs != null && outputs.size() > 0) {
														outcome.setOutputs(outputs);
														/*for(Output output : outputs) {
															if(output != null && output.getId() != null) {
																List<KeyActivity> keyActivities = keyActivityService.findByOutput(output);
																if(keyActivities != null && keyActivities.size() > 0) {
																	output.setKeyActivities(keyActivities);
																	for(KeyActivity keyActivity : keyActivities) {
																		if(keyActivity != null && keyActivity.getId() != null) {
																			List<SubActivity> subActivities = subActivityService.findByKeyActivity(keyActivity);
																			if(subActivities != null && subActivities.size() > 0) {
																				keyActivity.setSubActivities(subActivities);
																			}
																		}
																	}
																}
																List<Indicator> indicators = indicatorService.findByOutput(output);
																if (indicators != null && indicators.size() > 0) {
																	output.setIndicators(indicators);
																}
																List<Target> targets = targetService.findByOutput(output);
																if (targets != null && targets.size() > 0) {
																	output.setTargets(targets);
																}
															}
														}*/
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			}
			if(action.equalsIgnoreCase("view")){
				model.addAttribute("accessRightsView", true);
				model.addAttribute("action", "view");
			}else{
				model.addAttribute("accessRightsView", false);
				model.addAttribute("action", "config");
			}
			model.addAttribute("strategicPillars", strategicPillars);
			return "/project/projectActivity";
		}else{
			return "login";
		}
		
	}
	
	@RequestMapping(value = "getConfigProject", method = RequestMethod.GET)
	public String getConfigProject(ModelMap model, @RequestParam Integer projectId,@RequestParam("action") String action, HttpServletRequest request) {
		model.addAttribute("strategicPillar", new StrategicPillar());
		model.addAttribute("theme", new Theme());
		model.addAttribute("outcome", new Outcome());
		model.addAttribute("output", new Output());
		model.addAttribute("keyActivity", new KeyActivity());
		model.addAttribute("subActivity", new SubActivity());
		model.addAttribute("indicator", new Indicator());
		model.addAttribute("target", new Target());
		model.addAttribute("action","config");
		return "project/projectActivity";
	}
	
	@RequestMapping(value = "configProject", method = RequestMethod.GET)
	public @ResponseBody List<StrategicPillar> configProject(ModelMap model, @RequestParam Integer projectId,@RequestParam("action") String action, HttpServletRequest request) {
		List<StrategicPillar> strategicPillars = new ArrayList<StrategicPillar>();
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.PROJECTADMIN_ROLE)){
			model.addAttribute("strategicPillar", new StrategicPillar());
			model.addAttribute("theme", new Theme());
			model.addAttribute("outcome", new Outcome());
			model.addAttribute("output", new Output());
			model.addAttribute("keyActivity", new KeyActivity());
			model.addAttribute("subActivity", new SubActivity());
			model.addAttribute("indicator", new Indicator());
			model.addAttribute("target", new Target());
			Project project = projectService.getById(projectId);
			if (project != null && project.getId() != null) {
				strategicPillars = strategicPillarService.getByProject(project);
				if (strategicPillars != null && strategicPillars.size() > 0) {
					for (StrategicPillar strategicPillar : strategicPillars) {
						if(strategicPillar != null && strategicPillar.getId() != null) {
							List<Theme> themes = themeService.getThemeByStrategicPillar(strategicPillar);
							if(themes != null && themes.size() > 0) {
								strategicPillar.setThemes(themes);
								for(Theme theme : themes) {
									if(theme != null && theme.getId() != null) {
										List<Outcome> outcomes = outcomeService.getByTheme(theme);
										if (outcomes != null && outcomes.size() > 0) {
											theme.setOutcomes(outcomes);
											for (Outcome outcome: outcomes) {
												if(outcome != null && outcome.getId() != null) {
													List<Output> outputs = outputServices.getByOutcome(outcome);
													if(outputs != null && outputs.size() > 0) {
														outcome.setOutputs(outputs);
														/*for(Output output : outputs) {
															if(output != null && output.getId() != null) {
																List<KeyActivity> keyActivities = keyActivityService.findByOutput(output);
																if(keyActivities != null && keyActivities.size() > 0) {
																	output.setKeyActivities(keyActivities);
																	for(KeyActivity keyActivity : keyActivities) {
																		if(keyActivity != null && keyActivity.getId() != null) {
																			List<SubActivity> subActivities = subActivityService.findByKeyActivity(keyActivity);
																			if(subActivities != null && subActivities.size() > 0) {
																				keyActivity.setSubActivities(subActivities);
																				for(SubActivity subActivity : subActivities) {
																					if(subActivity != null && subActivity.getId() != null) {
																						if(subActivity.getLeadAgency() != null && subActivity.getLeadAgency().getId() != null) {
																							
																						}
																					}
																				}
																			}
																		}
																	}
																}
																List<Indicator> indicators = indicatorService.findByOutput(output);
																if (indicators != null && indicators.size() > 0) {
																	output.setIndicators(indicators);
																}
																List<Target> targets = targetService.findByOutput(output);
																if (targets != null && targets.size() > 0) {
																	output.setTargets(targets);
																}
															}
														}*/
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(action.equalsIgnoreCase("view")){
				model.addAttribute("accessRightsView", true);
				model.addAttribute("action", "view");
			}else{
				model.addAttribute("accessRightsView", false);
				model.addAttribute("action", "config");
			}
			model.addAttribute("strategicPillars", strategicPillars);
			return strategicPillars;
		}else{
			return strategicPillars;
		}
		
	}

	@RequestMapping(value = "hierarchyByStrategicPillar", method = RequestMethod.GET)
	public @ResponseBody HierarchyStrategicPillar hierarchyByStrategicPillar(ModelMap model, @RequestParam Integer strategicPillarId, HttpServletRequest request) {
		// LOGGER.info("Getting into user details page with user and roles {} :"
		// + principalUtil.getPrincipal());
		model.addAttribute("strategicPillar", new StrategicPillar());
		model.addAttribute("theme", new Theme());
		model.addAttribute("themes", new ArrayList<Theme>());
		List<Theme> themes = new ArrayList<Theme>();
		List<Outcome> outcomes = new ArrayList<Outcome>();
		List<StrategicPillar> strategicPillars = new ArrayList<StrategicPillar>();
		HierarchyStrategicPillar hierarchyStrategicPillar = new HierarchyStrategicPillar();
		if (strategicPillarId != null) {
			StrategicPillar strategicPillar = strategicPillarService.getById(strategicPillarId);
			if (strategicPillar != null && strategicPillar.getId() != null) {
				hierarchyStrategicPillar.setStrategicPillar(strategicPillar);
				List<Theme> themesList = themeService.getThemeByStrategicPillar(strategicPillar);
				if (themesList != null && themesList.size() > 0) {
					for (Theme theme : themesList) {
						if (theme != null && theme.getId() != null) {
							themes.add(theme);
							outcomes = outcomeService.getByTheme(theme);
						}
					}
				}
			}
		}
		hierarchyStrategicPillar.setThemes(themes);
		/*model.addAttribute("strategicPillars", strategicPillars);
		model.addAttribute("themes", themes);
		model.addAttribute("outcomes", outcomes);*/
		return hierarchyStrategicPillar;
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
		
	
	
	@RequestMapping(value = "loadKeyActivityByOutput", method = RequestMethod.GET)
	public @ResponseBody Output loadKeyActivityByOutput(@RequestParam Integer outputId) {
		Output output = outputServices.getById(outputId);
		List<KeyActivity> activities = new ArrayList<KeyActivity>();
		List<Target> targets = targetService.findByOutput(output);
		List<Indicator> indicators = indicatorService.findByOutput(output);
		List<KeyActivity>keyActivities = keyActivityService.findByOutput(output);
		if(keyActivities.size() > 0){
		for (KeyActivity keyActivity : keyActivities) {
			List<SubActivity> subActivity = subActivityService.findByKeyActivity(keyActivity);
			keyActivity.setSubActivities(subActivity);
			activities.add(keyActivity);
		}
		}
		output.setKeyActivities(activities);
		output.setIndicators(indicators);
		output.setTargets(targets);
		return output;
		
	}
	
}
