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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ceainfotech.ndcmp.dao.KeyActivityDAO;
import com.ceainfotech.ndcmp.model.Indicator;
import com.ceainfotech.ndcmp.model.KeyActivity;
import com.ceainfotech.ndcmp.model.Outcome;
import com.ceainfotech.ndcmp.model.Output;
import com.ceainfotech.ndcmp.model.SubActivity;
import com.ceainfotech.ndcmp.model.Target;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.model.UserRole;
import com.ceainfotech.ndcmp.model.dto.AddOutput;
import com.ceainfotech.ndcmp.model.dto.AgencyDTO;
import com.ceainfotech.ndcmp.model.dto.KeyActivityDTO;
import com.ceainfotech.ndcmp.model.dto.OutputDTO;
import com.ceainfotech.ndcmp.model.dto.StrategicDTO;
import com.ceainfotech.ndcmp.model.dto.SubActivityDTO;
import com.ceainfotech.ndcmp.service.IndicatorService;
import com.ceainfotech.ndcmp.service.KeyActivityService;
import com.ceainfotech.ndcmp.service.OutcomeService;
import com.ceainfotech.ndcmp.service.OutputServices;
import com.ceainfotech.ndcmp.service.SubActivityService;
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
 * @author bosco
 *
 */
@Controller
@RequestMapping(value = "/api/**")
public class OutputController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OutputController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	OutputServices outputServices;
	
	@Autowired
	OutcomeService outcomeService;
	
	@Autowired
	IndicatorService indicatorService;
	
	@Autowired
	TargetService targetService;
	
	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private PrincipalUtil principalUtil;
	
	@Autowired
	private ConstantUtil constantUtil;

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private KeyActivityService keyActivityService;
	
	@Autowired
	private SubActivityService subActivityService;
	
	String action,module="";
	
	@RequestMapping(value="/outputList", method = RequestMethod.GET)
	public ModelAndView listAllRegions(ModelMap modelMap, HttpServletRequest request){
		
		LOGGER.info("Listing All Regions ");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		//getAccessRights(modelMap, username);
		modelMap.addAttribute("regionslist", outputServices.getOutput(Status.ACTIVE.getName()));
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
		}return null;
	}
	
	
	@RequestMapping(value="editOutput", method=RequestMethod.GET)
	public @ResponseBody Output editOutput(@ModelAttribute Output output, @RequestParam Integer outputId, Model model){
		List<Outcome> outcomes = outcomeService.getOutcomes();
		if(outputId != 0 && outputId != null) {
			output = outputServices.getById(outputId);
		}
		model.addAttribute("outcomeList",outcomes);
		model.addAttribute("output",output);
		return output;
	}
	
	/**
	 * To get the theme details for edit page
	 * 
	 * @param theme
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "getHierarchyOutput", method = RequestMethod.GET)
	public @ResponseBody Output getTheme(@ModelAttribute Output output, @RequestParam Integer outcomeId, Model model) {
		Outcome outcome = new Outcome();
		if (outcomeId != null) {
			outcome = outcomeService.getById(outcomeId);
			if(outcome != null && outcome.getId() != null) {
				model.addAttribute("outcomeName", outcome.getName());
				output.setOutcome(outcome);
			}
		}
		model.addAttribute("outcome", outcome);
		return output;

	}
	
	
	@RequestMapping(value = "getAddHierarchyOutput", method = RequestMethod.GET)
	public @ResponseBody AddOutput getAddTheme(@ModelAttribute AddOutput addOutput, @RequestParam Integer outcomeId, Model model) {
		Outcome outcome = new Outcome();
		if (outcomeId != null) {
			outcome = outcomeService.getById(outcomeId);
			if(outcome != null && outcome.getId() != null) {
				addOutput.setOutcome(outcome.getName());
				addOutput.setOutcomeId(outcome.getId());
			}
		}
		return addOutput;

	}
	
	/*
	@RequestMapping(value = "addOutput", method = RequestMethod.POST)
	public ModelAndView addOutput(@ModelAttribute AddOutput addOutput) throws Exception {
		Outcome outcome = new Outcome();
		Output output = new Output();
		Indicator indicator = new Indicator();
		Target target = new Target();
		
		LOGGER.info("Saving or updating strategicPillar data {} : " + addOutput);
		ModelAndView modelAndView = new ModelAndView();
		
		if(addOutput != null){
			outcome = outcomeService.getByName(addOutput.getOutcome());
		}
		

		if(outcome != null && outcome.getId() != null) {
			if (addOutput != null && addOutput.getOutputSequenceNumber() != null) {
				output.setSequenceNumber(addOutput.getOutputSequenceNumber());
			} else {
				Integer outputCount = outputServices.getByOutcomeId(outcome);
				outputCount++;
				output.setSequenceNumber(outputCount.toString());
			}
			if(addOutput != null && addOutput.getOutcome() != null) {
				output.setOutput(addOutput.getOutput());
				output.setOutcome(outcome);
				output.setStatus(Status.ACTIVE.getName());
				outputServices.save(output);
				
				action="Output "+output.getOutput()+" is Saved";
				module="Output Module";
				constantUtil.saveAudition(action, module, request);
			}
			if(output != null && output.getId() != null) {
				if(addOutput != null && addOutput.getIndicator() != null) {
					indicator.setMessage(addOutput.getIndicator());
					indicator.setOutput(output);
					indicator.setStatus(Status.ACTIVE.getName());
					indicatorService.addIndicator(indicator);
					
					action="Indicator "+indicator.getMessage()+" is Saved";
					module="Output Module";
					constantUtil.saveAudition(action, module, request);
				}
				if(addOutput != null && addOutput.getTarget() != null) {
					target.setMessage(addOutput.getTarget());
					target.setOutput(output);
					target.setStatus(Status.ACTIVE.getName());
					targetService.addTarget(target);
					
					action="Target"+target.getMessage()+" is Saved";
					module="Output Module";
					constantUtil.saveAudition(action, module, request);
				}
			}
			
		}modelAndView.setViewName("redirect:outputList");
		return modelAndView;
	}*/
	@RequestMapping(value = "updateOutput", method = RequestMethod.GET)
	public @ResponseBody StrategicDTO updateOutput(@RequestParam String outputJson) throws Exception {
		StrategicDTO strategicDTO=new StrategicDTO();
		
		Outcome outcome = new Outcome();
		Output output = new Output();
		
		Gson gson=new GsonBuilder().create();
		JsonObject jsonObject = gson.fromJson( outputJson, JsonObject.class);
		
		Integer id=jsonObject.get("outpId").getAsInt();
		Integer outcomeId=jsonObject.get("editOutputOutcomeId").getAsInt();
		String editOutputname=jsonObject.get("editOutputname").getAsString();
		
		if(id > 0){
			if(outcomeId > 0){
				Output output2=outputServices.getByName(editOutputname);
				if(output2 != null){
					if(!output2.getId().equals(id)){
						strategicDTO.setOutputNameError("Output already exist");
						return strategicDTO;
					}
				}
				outcome=outcomeService.getById(outcomeId);
				strategicDTO.setOutcome(outcome);
				if(outcome != null){
					output=outputServices.getById(id);
					if(output != null){
						output.setOutcome(outcome);
						output.setOutput(editOutputname);
						output.setStatus(Status.ACTIVE.getName());
						outputServices.save(output);
						strategicDTO.setOutput(output);
						
						action="Output"+output.getOutput()+" is Updated";
						module="Output Module";
						constantUtil.saveAudition(action, module, request);
					}
				}
			}
		}
		
		return strategicDTO;
	}
	
	@RequestMapping(value = "saveOutput", method = RequestMethod.GET)
	public @ResponseBody StrategicDTO saveOutput(@RequestParam String outputJson) throws Exception {
		
		StrategicDTO strategicDTO=new StrategicDTO();
		Outcome outcome = new Outcome();
		Output output = new Output();
		Indicator indicator = new Indicator();
		Target target = new Target();
		
		Gson gson=new GsonBuilder().create();
		JsonObject jsonObject = gson.fromJson( outputJson, JsonObject.class);
		
		module="Output Module";
		
		Integer id=jsonObject.get("id").getAsInt();
		Integer outcomeId=jsonObject.get("OUTCOMEId").getAsInt();
		String outputName=jsonObject.get("OUTPUTNAME").getAsString();
		String outputSequenceNumber=jsonObject.get("outputSequenceNumber").getAsString();
		String indicator_name=jsonObject.get("INDICATOR").getAsString();
		String target_name=jsonObject.get("TARGET").getAsString();
		
		if(outcomeId > 0){
			outcome=outcomeService.getById(outcomeId);
			strategicDTO.setOutcome(outcome);
		}
		
		if(id == 0){
			output = outputServices.findByOutputAndOutcome(outputName, outcome);
			if(output != null){
				strategicDTO.setOutputNameError("Output Name already Exist");
				return strategicDTO;
			}else{
				if(outcome != null && outcome.getId() != null) {
					output =new Output();
					if (outputSequenceNumber != null) {
						output.setSequenceNumber(outputSequenceNumber);
					} else {
						Integer outputCount = outputServices.getByOutcomeId(outcome);
						outputCount++;
						output.setSequenceNumber(outputCount.toString());
					}
						output.setOutput(outputName);
						output.setOutcome(outcome);
						output.setStatus(Status.ACTIVE.getName());
						outputServices.save(output);
						strategicDTO.setOutput(output);
						
						action="Output "+output.getOutput()+" is Saved";
						constantUtil.saveAudition(action, module, request);
					if(output != null && output.getId() != null) {
						if(indicator_name != null) {
							indicator.setMessage(indicator_name);
							indicator.setOutput(output);
							indicator.setStatus(Status.ACTIVE.getName());
							indicatorService.addIndicator(indicator);
							strategicDTO.setIndicator(indicator);
							
							action="Indicator "+indicator.getMessage()+" is Saved";
							module="Output Module";
							constantUtil.saveAudition(action, module, request);
						}
						if(target_name != null ) {
							target.setMessage(target_name);
							target.setOutput(output);
							target.setStatus(Status.ACTIVE.getName());
							targetService.addTarget(target);
							strategicDTO.setTarget(target);
							
							action="Target"+target.getMessage()+" is Saved";
							module="Output Module";
							constantUtil.saveAudition(action, module, request);
						}
					}
				}
			}
		}
		return strategicDTO;
	}
	
	
	@RequestMapping(value = "createOutput", method = RequestMethod.POST)
	public ModelAndView createRegion(@ModelAttribute Output output) throws Exception {
		LOGGER.info("Saving or updating State data {} : " + output);
		ModelAndView modelAndView = new ModelAndView();
		if (output.getOutcome() != null && output.getOutcome().getName() != null) {
			Outcome outcome = outcomeService.getByName(output.getOutcome().getName());
			if (outcome != null && outcome.getId() != null) {
				Integer outputCount = outputServices.getByOutcomeId(outcome);
				outputCount++;
				if (outputCount != null) {
					output.setSequenceNumber(outcome.getSequenceNumber() + "." + outputCount.toString());
				}
				output.setOutcome(outcome);
			}
		}
		output.setStatus(Status.ACTIVE.getName());
		if (output.getId() == null) {
			outputServices.save(output);
			
			action="Output"+output.getOutput()+" is Saved";
			module="Output Module";
			constantUtil.saveAudition(action, module, request);
		} else {
			outputServices.saveAndFlush(output);
			
			action="Output"+output.getOutput()+" is Updated";
			module="Output Module";
			constantUtil.saveAudition(action, module, request);
		}
		modelAndView.setViewName("redirect:outputList");
		return modelAndView;
	}
	
	
	@RequestMapping(value="deleteOutpot", method=RequestMethod.GET)
	public ModelAndView deleteOutput(@RequestParam int outputId) throws Exception{
		
		LOGGER.info("Deleting Region info");
		outputServices.deleteOutput(outputId);
		
		Output output=outputServices.getById(outputId);
		
		action="Output"+output.getOutput()+" is Removed";
		module="Output Module";
		constantUtil.saveAudition(action, module, request);
		return new ModelAndView("redirect:outputList");
	}
	
	@RequestMapping(value="getKeyActivitiesAndSubActivities",method=RequestMethod.GET)
	public @ResponseBody Output  getKeyActivitiesAndSubActivities(@RequestParam("output_id") Integer output_id){
		
		List<OutputDTO> outputDTO=new ArrayList<>();
		List<KeyActivityDTO> keyActivityDTO=new ArrayList<>();
		AgencyDTO agencyDTO = new AgencyDTO();
		
		Output output=outputServices.getById(output_id);
		/*if(output != null){
			List<KeyActivity> keyActivities=keyActivityService.findByOutput(output);
			
			if(keyActivities.size() != 0){
				for (KeyActivity keyActivity : keyActivities) {
						KeyActivity keyActivity2=keyActivityService.getById(keyActivity.getId());
						if(keyActivity2 != null){
							subActivities=subActivityService.findByKeyActivity(keyActivity2);
							if(subActivities.size() != 0){
								
							}
						}
					
				}
			}
			
			
		}*/
		
				if(output != null && output.getId() != null) {
					List<KeyActivity> keyActivities = keyActivityService.findByOutput(output);
					if(keyActivities != null && keyActivities.size() > 0) {
						output.setKeyActivities(keyActivities);
						for(KeyActivity keyActivity : keyActivities) {
							List<SubActivityDTO> subActivityDTO=new ArrayList<>();
							if(keyActivity != null && keyActivity.getId() != null) {
								List<SubActivity> subActivities = subActivityService.findByKeyActivity(keyActivity);
								if(subActivities != null && subActivities.size() > 0) {
									keyActivity.setSubActivities(subActivities);
									/*for(SubActivity subActivity : subActivities) {
										
										if(subActivity != null && subActivity.getId() != null) {
											if(subActivity.getLeadAgency() != null && subActivity.getLeadAgency().getId() != null) {
												if(subActivity.getKeyActivity().getId() == keyActivity.getId()){
													agencyDTO = new AgencyDTO();
													if(subActivity != null && subActivity.getLeadAgency() != null && subActivity.getLeadAgency().getId() != null) {
														agencyDTO.setId(subActivity.getLeadAgency().getId());
														agencyDTO.setAgency(subActivity.getLeadAgency().getName());
//														agencyDTO.setType(subActivity.getLeadAgency().getAgencyType());
														agencyDTO.setStatus(subActivity.getLeadAgency().getStatus());
													}			
												}
												
												subActivityDTO.add(new SubActivityDTO(subActivity.getId(), subActivity.getSequenceNumber(), subActivity.getName(), subActivity.getStatus(), agencyDTO, null,null, subActivity.getMov(),null));
											}
										}
										
									}*/
								}
							}
						}
					}
				}
		
		return output;
	}
	
	public void getAccessRights(ModelMap modelMap, String username){
		
		/*User user = userService.findByUsername(username);
		List<AccessRights> accessrightslist = new ArrayList<AccessRights>();
//		List<AccessRights> accessrightslist = accessRightsService.listAccessRightsByRole(user.getUserRole().getId().intValue());
		List<String> features = new ArrayList<>();
		boolean REG_ADD = false;
		boolean REG_EDT = false;
		boolean REG_DEL = false;
		boolean REG_VIW = false;
		
		if(accessrightslist != null){
			for(AccessRights accessRights : accessrightslist){
				features.add(accessRights.getFeatures().getFeaturecode());
			}
			if (features != null){
				for ( String feature : features){
					if (feature.equals(Modules.REG_ADD.toString())){
						REG_ADD = true;
					}
					if(feature.equals(Modules.REG_VIW.toString())){
						REG_VIW = true;
					}
					if(feature.equals(Modules.REG_EDT.toString())){
						REG_EDT = true;;
					}
					if(feature.equals(Modules.REG_DEL.toString())){
						REG_DEL = true;
					}
				}
			}
		}
		modelMap.addAttribute("REG_ADD", REG_ADD);
		modelMap.addAttribute("REG_VIW", REG_VIW);
		modelMap.addAttribute("REG_EDT", REG_EDT);
		modelMap.addAttribute("REG_DEL", REG_DEL);*/
	}

}

