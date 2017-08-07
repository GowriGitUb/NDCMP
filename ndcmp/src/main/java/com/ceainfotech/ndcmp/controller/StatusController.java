/**
 * 
 */
package com.ceainfotech.ndcmp.controller;

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

import com.ceainfotech.ndcmp.model.Statuss;
import com.ceainfotech.ndcmp.service.StatusService;
import com.ceainfotech.ndcmp.service.UserService;
import com.ceainfotech.ndcmp.util.ConstantUtil;
import com.ceainfotech.ndcmp.util.Status;

/**
 * @author Samy
 * 
 */
@Controller
@RequestMapping(value = "/api/**")
public class StatusController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(StatusController.class);

	@Autowired
	StatusService statusService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private ConstantUtil constantUtil;

	@Autowired
	private HttpServletRequest request;
	
	String action,module="";


	/**
	 * List all the status details
	 * 
	 * @param modelMap
	 * @param authentication
	 * @return
	 */

	@RequestMapping(value = "statusList", method = RequestMethod.GET)
	public ModelAndView listAllStatus(ModelMap modelMap,
			HttpServletRequest request, Authentication authentication) {
		LOGGER.info("Listing All status details {} ");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		//getAccessRights(modelMap, username);
		Integer statusCount = statusService.getStatussCount();
		modelMap.addAttribute("statusCount", statusCount);
		modelMap.addAttribute("statusList", statusService.findAllByOrderByStartRangeAsc());
		return new ModelAndView("status/statusList");
	}

	/**
	 * Add or edit staus details
	 * 
	 * @param status
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/getStatus", method = RequestMethod.GET)
	public ModelAndView getStatus(@ModelAttribute Statuss status,
			@RequestParam Integer id) {

		ModelAndView view = new ModelAndView();
		if (status.getId() > 0) {
			status = statusService.getStatusById(id);
			view.addObject("status", status);
		}
		return new ModelAndView("status/statusadd", "status",
				status);
	}

	/**
	 * Create status information
	 * 
	 * @param status
	 * @return
	 * @throws Exception 
	 */

	@RequestMapping(value = "createStatus", method = RequestMethod.POST)
	public ModelAndView createStatus(@ModelAttribute Statuss status) throws Exception {
		LOGGER.info("Save or update Status data {} : " + status);
		Statuss sta = statusService.findStatusByName(status.getName());
		Statuss staName = statusService.findStatusByDescription(status
				.getDescription());
		Statuss staColor = statusService.findStatusByColor(status
				.getStatusColor());
		ModelAndView modelAndView = new ModelAndView();

		if (status.getId() == 0) {
				modelAndView.addObject("status", status);
				if (sta != null) {
					modelAndView.addObject("errorName", "Name Already Exist");
					modelAndView.setViewName("status/statusadd");
					return modelAndView;
				}
				if (staName != null) {
					modelAndView.addObject("errorDescription",
							"Description Already Exist");
					modelAndView.setViewName("status/statusadd");
					return modelAndView;
				}
				if (staColor != null) {
					modelAndView.addObject("errorColor", "Color Already Exist");
					modelAndView.setViewName("status/statusadd");
					return modelAndView;

				}
				status.setStatus(Status.ACTIVE.getName());
				statusService.addStatus(status);
				
				action="Status "+status.getName()+" is Saved";
				module="Status Module";
				constantUtil.saveAudition(action, module, request);
				
				modelAndView.setViewName("redirect:statusList");
				return modelAndView;
			} else {
				Statuss staa = statusService.findByIdAndNameAndStatusColor(status.getId(),
						status.getName(), status.getDescription(),status.getStatusColor());
				if (staa != null) {
					modelAndView.addObject("status", status);
					modelAndView.setViewName("redirect:statusList");
					status.setStatus(Status.ACTIVE.getName());
					statusService.updateStatus(status);
					
					action="Status "+status.getName()+" is Updated";
					module="Status Module";
					constantUtil.saveAudition(action, module, request);
					
					return modelAndView;
				} else {
					Statuss statusIdAndName = statusService.getByIdAndName(
							status.getId(), status.getName());
					Statuss statusIdAndDescription = statusService
							.statusIdAndDescription(status.getId(),
									status.getDescription());
					Statuss statusIdAndStatusColor = statusService
							.statusIdAndStatusColor(status.getId(),
									status.getStatusColor());
					Statuss sta1 = statusService.findStatusByName(status
							.getName());
					Statuss staName1 = statusService
							.findStatusByDescription(status.getDescription());
					Statuss staColor1 = statusService.findStatusByColor(status
							.getStatusColor());

					if (statusIdAndName != null) {
					} else if (sta1 != null) {
						modelAndView.addObject("status", status);
						modelAndView.addObject("errorName",
								"Name Already Exist");
						modelAndView.setViewName("status/statusadd");
						return modelAndView;
					}
					if(statusIdAndDescription != null){
					}else if (staName1 != null) {
						modelAndView.addObject("status", status);
						modelAndView.addObject("errorDescription","Description Already Exist");
						modelAndView.setViewName("status/statusadd");
						return modelAndView;
					}
					if(statusIdAndStatusColor != null){
					}else if (staColor1 != null) {
						modelAndView.addObject("status", status);
						modelAndView.addObject("errorColor","Color Already Exist");
						modelAndView.setViewName("status/statusadd");
						return modelAndView;
					}

					/*
					 * if (sta1 != null || staName1 != null || staColor1 !=
					 * null) {
					 * 
					 * if (sta1 != null) { if
					 * (sta1.getName().equals(status.getName())) {
					 * modelAndView.addObject("errorName",
					 * "Name Already Exist"); modelAndView
					 * .setViewName("status/statusadd"); return
					 * modelAndView; } if (sta1.getDescription().equals(
					 * status.getDescription())) {
					 * modelAndView.addObject("errorDescription",
					 * "Description Already Exist"); modelAndView
					 * .setViewName("status/statusadd"); return
					 * modelAndView; } if (sta1.getStatusColor().equals(
					 * status.getStatusColor())) {
					 * modelAndView.addObject("errorColor",
					 * "Color Already Exist"); modelAndView
					 * .setViewName("status/statusadd"); return
					 * modelAndView; } } if (staName1 != null) { if
					 * (staName1.getName().equals(status.getName())) {
					 * modelAndView.addObject("errorName",
					 * "Name Already Exist"); modelAndView
					 * .setViewName("status/statusadd"); return
					 * modelAndView; } if (staName1.getDescription().equals(
					 * status.getDescription())) {
					 * modelAndView.addObject("errorDescription",
					 * "Description Already Exist"); modelAndView
					 * .setViewName("status/statusadd"); return
					 * modelAndView; } if (staName1.getStatusColor().equals(
					 * status.getStatusColor())) {
					 * modelAndView.addObject("errorColor",
					 * "Color Already Exist"); modelAndView
					 * .setViewName("status/statusadd"); return
					 * modelAndView; } } if (staColor1 != null) { if
					 * (staColor1.getName().equals(status.getName())) {
					 * modelAndView.addObject("errorName",
					 * "Name Already Exist"); modelAndView
					 * .setViewName("status/statusadd"); return
					 * modelAndView; } if (staColor1.getDescription().equals(
					 * status.getDescription())) {
					 * modelAndView.addObject("errorDescription",
					 * "Description Already Exist"); modelAndView
					 * .setViewName("status/statusadd"); return
					 * modelAndView; } if (staColor1.getStatusColor().equals(
					 * status.getStatusColor())) {
					 * modelAndView.addObject("errorColor",
					 * "Color Already Exist"); modelAndView
					 * .setViewName("status/statusadd"); return
					 * modelAndView; } }
					 * 
					 * }
					 */
					modelAndView.setViewName("redirect:statusList");
					status.setStatus(Status.ACTIVE.getName());
					statusService.updateStatus(status);
					return modelAndView;
				}
			}
	}

	/**
	 * Deleting status information
	 * 
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "deleteStatus", method = RequestMethod.GET)
	public ModelAndView deleteStatus(@RequestParam Integer id) {
		LOGGER.info("Deleting Status information {}");
		statusService.deleteStatusById(id);
		return new ModelAndView("redirect:statusList");
	}

	public void getAccessRights(ModelMap modelMap, String username) {

		/*User user = userService.findByUsername(username);
		List<AccessRights> accessrightslist = new ArrayList<AccessRights>();
		// List<AccessRights> accessrightslist =
		// accessRightsService.listAccessRightsByRole(user.getUserRole().getId().intValue());
		List<String> features = new ArrayList<>();
		boolean STA_ADD = false;
		boolean STA_EDT = false;
		boolean STA_DEL = false;
		boolean STA_VIW = false;

		if (accessrightslist != null) {
			for (AccessRights accessRights : accessrightslist) {
				features.add(accessRights.getFeatures().getFeaturecode());
			}
			if (features != null) {
				for (String feature : features) {
					if (feature.equals(Modules.STA_ADD.toString())) {
						STA_ADD = true;
					}
					if (feature.equals(Modules.STA_VIW.toString())) {
						STA_VIW = true;
					}
					if (feature.equals(Modules.STA_EDT.toString())) {
						STA_EDT = true;
						;
					}
					if (feature.equals(Modules.STA_DEL.toString())) {
						STA_DEL = true;
					}
				}
			}
		}
		modelMap.addAttribute("STA_ADD", STA_ADD);
		modelMap.addAttribute("STA_VIW", STA_VIW);
		modelMap.addAttribute("STA_EDT", STA_EDT);
		modelMap.addAttribute("STA_DEL", STA_DEL);*/
	}
	
	//Active Status
	@RequestMapping(value = "activeStatus", method = RequestMethod.GET)
	public ModelAndView activeStatus(@RequestParam Integer id, Model model) throws Exception{
		if(id != null){
			Statuss status = statusService.getStatusById(id);
			if(status != null){
				status.setStatusStatus(true);
				status.setStatus(Status.ACTIVE.getName());
				statusService.addStatus(status);
				
				action="Status "+status.getName()+" is Activated";
				module="Status Module";
				constantUtil.saveAudition(action, module, request);
				
				return new ModelAndView("redirect:statusList");
			}
		}
		return null;
	}
	
	//Deactive Status
	@RequestMapping(value = "deActiveStatus", method = RequestMethod.GET)
	public ModelAndView deActiveStatus(@RequestParam Integer id, Model model) throws Exception{
		if(id != null){
			Statuss status = statusService.getStatusById(id);
			if(status != null){
				status.setStatusStatus(false);
				status.setStatus(Status.INACTIVE.getName());
				statusService.addStatus(status);
				
				action="Status "+status.getName()+" is deActivated";
				module="Status Module";
				constantUtil.saveAudition(action, module, request);
				return new ModelAndView("redirect:statusList");
			}
		}
		return null;
	}
	
	@RequestMapping(value = "getStatusInformation", method = RequestMethod.GET)
	public @ResponseBody Statuss getStatusInformation(@RequestParam Integer statusId) {
		Statuss statuss = new Statuss();
		if (statusId != 0) {
			statuss = statusService.getStatusById(statusId);
		}
		return statuss;
	}
	
}
