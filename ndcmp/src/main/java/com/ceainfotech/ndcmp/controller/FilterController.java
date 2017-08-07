/**
 * 
 */
package com.ceainfotech.ndcmp.controller;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ceainfotech.ndcmp.model.Agency;
import com.ceainfotech.ndcmp.model.Indicator;
import com.ceainfotech.ndcmp.model.KeyActivity;
import com.ceainfotech.ndcmp.model.Outcome;
import com.ceainfotech.ndcmp.model.Output;
import com.ceainfotech.ndcmp.model.Planning;
import com.ceainfotech.ndcmp.model.ReportingPeriod;
import com.ceainfotech.ndcmp.model.StatusTracking;
import com.ceainfotech.ndcmp.model.StrategicPillar;
import com.ceainfotech.ndcmp.model.SubActivity;
import com.ceainfotech.ndcmp.model.Target;
import com.ceainfotech.ndcmp.model.Theme;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.model.UserRole;
import com.ceainfotech.ndcmp.model.dto.AgencyDTO;
import com.ceainfotech.ndcmp.model.dto.FilterHierarchy;
import com.ceainfotech.ndcmp.model.dto.IndicatorDTO;
import com.ceainfotech.ndcmp.model.dto.JsonResponse;
import com.ceainfotech.ndcmp.model.dto.KeyActivityDTO;
import com.ceainfotech.ndcmp.model.dto.OutputDTO;
import com.ceainfotech.ndcmp.model.dto.SubActivityDTO;
import com.ceainfotech.ndcmp.model.dto.TargetDTO;
import com.ceainfotech.ndcmp.repository.StatusTrackingRepository;
import com.ceainfotech.ndcmp.service.AgencyService;
import com.ceainfotech.ndcmp.service.IndicatorService;
import com.ceainfotech.ndcmp.service.KeyActivityService;
import com.ceainfotech.ndcmp.service.OutcomeService;
import com.ceainfotech.ndcmp.service.OutputServices;
import com.ceainfotech.ndcmp.service.PlanningServices;
import com.ceainfotech.ndcmp.service.ProjectService;
import com.ceainfotech.ndcmp.service.ReportingPeriodService;
import com.ceainfotech.ndcmp.service.StatusService;
import com.ceainfotech.ndcmp.service.StatusTrackingService;
import com.ceainfotech.ndcmp.service.StrategicPillarService;
import com.ceainfotech.ndcmp.service.SubActivityService;
import com.ceainfotech.ndcmp.service.TargetService;
import com.ceainfotech.ndcmp.service.ThemeService;
import com.ceainfotech.ndcmp.service.UserService;
import com.ceainfotech.ndcmp.util.ActualReportUtil;
import com.ceainfotech.ndcmp.util.ConstantUtil;
import com.ceainfotech.ndcmp.util.PrincipalUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



/**
 * @author Jeeva
 *
 */
@Controller
@RequestMapping(value = "/api/**")
public class FilterController {
	
	@Autowired
	ReportingPeriodService reportingPeriodService;
	
	
	@Autowired
	private StatusService statusService;
	
	@Autowired
	private StrategicPillarService strategicPillarService;
	
	@Autowired
	private ThemeService themeService;
	
	@Autowired
	private OutcomeService outcomeService;
	
	@Autowired
	private OutputServices outputServices;
	
	@Autowired
	private AgencyService agencyService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private KeyActivityService keyActivityService;
	
	@Autowired
	private IndicatorService indicatorService;
	
	@Autowired
	private TargetService targetService;
	
	@Autowired
	private SubActivityService subActivityService;
	
	@Autowired
	private PlanningServices planningService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private StatusTrackingService statusTrackingService; 
	
	@Autowired
	private StatusTrackingRepository statusTrackingRepository;
	
	@Autowired
	PrincipalUtil principalUtil;
	
	/**
	 * Get all the project
	 * @param model
	 * @param authentication
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value = "getFilterHierarchy1")
	public @ResponseBody JsonResponse  getFilterHier(@RequestParam Integer projectId, @RequestParam String filterHier, @RequestParam String rpYears){
		
		Gson gson=new GsonBuilder().serializeNulls().create();;
		FilterHierarchy filterHierarchy=gson.fromJson(filterHier, FilterHierarchy.class);
		List<Planning> planningList = new ArrayList<Planning>();
		List<SubActivity> subActivities1 = new ArrayList<SubActivity>();
		Set<KeyActivity> keyActivities2 = new HashSet<KeyActivity>();
		List<OutputDTO> outputDTOs = new ArrayList<OutputDTO>(); 
		List<TargetDTO> targetDTOs = new ArrayList<TargetDTO>();
		List<KeyActivityDTO> keyActivityDTOs = new ArrayList<KeyActivityDTO>();
		List<SubActivityDTO> subActivityDTOs = new ArrayList<SubActivityDTO>();
		AgencyDTO agencyDTO = new AgencyDTO();
		List<AgencyDTO> partnerAgencyDTOs = new ArrayList<AgencyDTO>();
		List<IndicatorDTO> indicatorDTOs = new ArrayList<IndicatorDTO>();
		JsonResponse jsonResponse=new JsonResponse();
		Output output1 = outputServices.getById(Integer.parseInt(filterHierarchy.getOutput()));
		List<KeyActivity> keyActivities1 = keyActivityService.findByOutput(output1);
		keyActivityDTOs = new ArrayList<KeyActivityDTO>();
		if(keyActivities1 != null && keyActivities1.size() > 0) {
			for(KeyActivity keyActivity : keyActivities1) {
				if(keyActivity != null && keyActivity.getId() != null) {
					List<SubActivity> subActivities = subActivityService.findByKeyActivityAndApproveAndCompletionStatus(keyActivity);
					subActivityDTOs = new ArrayList<SubActivityDTO>();
					if(subActivities != null && subActivities.size() > 0) {
						for(SubActivity subActivity1 : subActivities) {
							if(filterHierarchy.getOutput() != null){
								if(filterHierarchy.getStatus() .equalsIgnoreCase("Planned")){
									planningList=planningService.getByPlanning(subActivity1);
									for (Planning planning2 : planningList) {
										int temp =0;
										SubActivity subActivity2 = new SubActivity();
										subActivity2 = subActivityService.getById(planning2.getSubActivity().getId());
										if(subActivities1.size() == 0){
											subActivities1.add(subActivity2);
										}else{
											for (SubActivity subActivity : subActivities1) {
												if(subActivity.getId() == subActivity2.getId()){
													temp=0;
													break;
												}else{
													temp=1;
												}
											}
											if(temp == 1){
												subActivities1.add(subActivity2);
											}
										}
										
									}
									for(SubActivity subActivity3 : subActivities1){
										int temp=0;
										KeyActivity keyActivity2 = new KeyActivity();
										keyActivity2 = keyActivityService.getById(subActivity3.getKeyActivity().getId());
										/*if(!keyActivities2.contains(keyActivity2))
										{
											keyActivities2.add(keyActivity2);
											
										}*/
										if(keyActivities2.size() == 0){
											keyActivities2.add(keyActivity2);
										}else{
											for (KeyActivity keyActivity3 : keyActivities2) {
												if(keyActivity3.getId() == keyActivity2.getId()){
													temp=0;
													break;
												}else{
													temp=1;
												}
											}
											if(temp == 1){
												keyActivities2.add(keyActivity2);
											}
										}
										
									}
									
								}if(filterHierarchy.getStatus().equalsIgnoreCase("Unplanned")){
									planningList=planningService.getByPlanning(subActivity1);
									int temp = 0;
									if(planningList.size() == 0){
									SubActivity subActivity=planningService.getSubActivityById(subActivity1.getId());
									if(subActivities1.size() == 0){
										subActivities1.add(subActivity);
									}else{
										for (SubActivity subActivity2 : subActivities1) {
											if(subActivity.getId() == subActivity2.getId()){
												temp=0;
												break;
											}else{
												temp=1;
											}
										}
										if(temp == 1){
											subActivities1.add(subActivity);
										}
									}
									
									}
									
									for(SubActivity subActivity3 : subActivities1){
										KeyActivity keyActivity2 = new KeyActivity();
										keyActivity2 = keyActivityService.getById(subActivity3.getKeyActivity().getId());
										if(keyActivities2.size() == 0){
											keyActivities2.add(keyActivity2);
										}else{
											for (KeyActivity keyActivity3 : keyActivities2) {
												if(keyActivity3.getId() == keyActivity2.getId()){
													temp=0;
													break;
												}else{
													temp=1;
												}
											}
											if(temp == 1){
												keyActivities2.add(keyActivity2);
											}
										}
									}
									}else if (filterHierarchy.getStatus().equals("Completed")){
										planningList=planningService.getByPlanning(subActivity1);
										for (Planning planning2 : planningList) {
											int temp =0;
											SubActivity subActivity2 = new SubActivity();
											subActivity2 = subActivityService.getById(planning2.getSubActivity().getId());
											if(subActivity2.isApproveORCompleteStatus()){
												if(subActivities1.size() == 0){
													subActivities1.add(subActivity2);
												}else{
													for (SubActivity subActivity : subActivities1) {
														if(subActivity.getId() == subActivity2.getId()){
															temp=0;
															break;
														}else{
															temp=1;
														}
													}
													if(temp == 1){
														subActivities1.add(subActivity2);
													}
												}
											}
										}
										for(SubActivity subActivity3 : subActivities1){
											int temp=0;
											KeyActivity keyActivity2 = new KeyActivity();
											keyActivity2 = keyActivityService.getById(subActivity3.getKeyActivity().getId());
											/*if(!keyActivities2.contains(keyActivity2))
											{
												keyActivities2.add(keyActivity2);
												
											}*/
											if(keyActivities2.size() == 0){
												keyActivities2.add(keyActivity2);
											}else{
												for (KeyActivity keyActivity3 : keyActivities2) {
													if(keyActivity3.getId() == keyActivity2.getId()){
														temp=0;
														break;
													}else{
														temp=1;
													}
												}
												if(temp == 1){
													keyActivities2.add(keyActivity2);
												}
											}
											
										}
										
									}
									else if(filterHierarchy.getStatus().equalsIgnoreCase("All")){
										

										planningList=planningService.getByPlanning(subActivity1);
										for (Planning planning2 : planningList) {
											int temp =0;
											SubActivity subActivity2 = new SubActivity();
											subActivity2 = subActivityService.getById(planning2.getSubActivity().getId());
											if(subActivities1.size() == 0){
												subActivities1.add(subActivity2);
											}else{
												for (SubActivity subActivity : subActivities1) {
													if(subActivity.getId() == subActivity2.getId()){
														temp=0;
														break;
													}else{
														temp=1;
													}
												}
												if(temp == 1){
													subActivities1.add(subActivity2);
												}
											}
											
										}
										for(SubActivity subActivity3 : subActivities1){
											int temp=0;
											KeyActivity keyActivity2 = new KeyActivity();
											keyActivity2 = keyActivityService.getById(subActivity3.getKeyActivity().getId());
											/*if(!keyActivities2.contains(keyActivity2))
											{
												keyActivities2.add(keyActivity2);
												
											}*/
											if(keyActivities2.size() == 0){
												keyActivities2.add(keyActivity2);
											}else{
												for (KeyActivity keyActivity3 : keyActivities2) {
													if(keyActivity3.getId() == keyActivity2.getId()){
														temp=0;
														break;
													}else{
														temp=1;
													}
												}
												if(temp == 1){
													keyActivities2.add(keyActivity2);
												}
											}
											
										}
										

										planningList=planningService.getByPlanning(subActivity1);
										int temp = 0;
										if(planningList.size() == 0){
										SubActivity subActivity=planningService.getSubActivityById(subActivity1.getId());
										if(subActivities1.size() == 0){
											subActivities1.add(subActivity);
										}else{
											for (SubActivity subActivity2 : subActivities1) {
												if(subActivity.getId() == subActivity2.getId()){
													temp=0;
													break;
												}else{
													temp=1;
												}
											}
											if(temp == 1){
												subActivities1.add(subActivity);
											}
										}
										}
										
										for(SubActivity subActivity3 : subActivities1){
											KeyActivity keyActivity2 = new KeyActivity();
											keyActivity2 = keyActivityService.getById(subActivity3.getKeyActivity().getId());
											if(keyActivities2.size() == 0){
												keyActivities2.add(keyActivity2);
											}else{
												for (KeyActivity keyActivity3 : keyActivities2) {
													if(keyActivity3.getId() == keyActivity2.getId()){
														temp=0;
														break;
													}else{
														temp=1;
													}
												}
												if(temp == 1){
													keyActivities2.add(keyActivity2);
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
		
						if(filterHierarchy.getOutput() != ""){
							Output output = outputServices.getById(Integer.parseInt(filterHierarchy.getOutput()));
							outputDTOs = new ArrayList<OutputDTO>();
							if(output != null && output.getId() != null) {
								List<KeyActivity> keyActivities = keyActivityService.findByOutput(output);
								keyActivityDTOs = new ArrayList<KeyActivityDTO>();
								if(keyActivities != null && keyActivities.size() > 0) {
									for(KeyActivity keyActivity : keyActivities) {
										if(keyActivity != null && keyActivity.getId() != null) {
											List<SubActivity> subActivities = subActivityService.findByKeyActivity(keyActivity);
											subActivityDTOs = new ArrayList<SubActivityDTO>();
											if(subActivities != null && subActivities.size() > 0) {
												List<SubActivity> activities = new ArrayList<SubActivity>();
												for(SubActivity subActivity : subActivities) {
													if(subActivity != null && subActivity.getId() != null) {
															if(subActivity != null && subActivity.getId() != null) {
																for(SubActivity activity : subActivities1){
																	int activityId=activity.getId();
																	int subActId=subActivity.getId();
																	
																	if(activityId == subActId){
																		activities.add(subActivity);
																		break;
																	}
																}
															}
													}
												}
																for(SubActivity activity1 : activities){
																	//activity1.setPartnerAgency(partnerAgency);
																	agencyDTO = new AgencyDTO();
																	if(activity1 != null && activity1.getLeadAgency() != null && activity1.getLeadAgency().getId() != null) {
																		agencyDTO.setId(activity1.getLeadAgency().getId());
																		agencyDTO.setAgency(activity1.getLeadAgency().getName());
//																		agencyDTO.setType(activity1.getLeadAgency().getAgencyType());
																		agencyDTO.setPartnerAgency(activity1.getPartnerAgency());
																		agencyDTO.setStatus(activity1.getLeadAgency().getStatus());
																	}
																	List<String> years = new ArrayList<String>();
																	List<ReportingPeriod> reportingPeriods = new ArrayList<ReportingPeriod>();
//																	years = reportingPeriodService.getYears();
//																	Calendar now = Calendar.getInstance();
//																	int cyear = now.get(Calendar.YEAR);
//																	String currentYear = String.valueOf(cyear);
//																	if(currentYear != null) {
//																		years.add(currentYear);
//																		Calendar next = Calendar.getInstance();
//																		next.add(Calendar.YEAR, 1);
//																		int nyear = next.get(Calendar.YEAR);
//																		String nextYear = String.valueOf(nyear);
//																		if(nextYear != null) {
//																			years.add(nextYear);
//																		}
//																	}
																	String[] splitYears = rpYears.split(",");
																	if(splitYears != null && splitYears.length > 0) {
																		for(int i=0; i < splitYears.length; i ++) {
																			String year = splitYears[i];
																			if (year != null && year.trim() != null && !year.isEmpty()) {
																				years.add(year);
																			}
																			
																		}
																	}
																	jsonResponse.setYears(years);
																	if(years != null && years.size() > 0) {
																		for(String year : years) {
																			List<ReportingPeriod> rps = reportingPeriodService.findAllReportingPeriodByYear(year);
																			if(rps != null && rps.size() > 0) {
																				for(ReportingPeriod reportingPeriod : rps) {
																					if(reportingPeriod != null && reportingPeriod.getId() != null) {
//																						reportingPeriodDTOs.add(new ReportingPeriodDTO(reportingPeriod.getId(), reportingPeriod.getName(), reportingPeriod.getYear(), reportingPeriod.getStartdate(), reportingPeriod.getEnddate(), reportingPeriod.getStatus()));
																						reportingPeriods.add(reportingPeriod);
																					}
																				}
																			}
																			}
																	}
														jsonResponse.setReportingPeriods(reportingPeriods);
														boolean status=activity1.isApproveORCompleteStatus();
														subActivityDTOs.add(new SubActivityDTO(activity1.getId(), activity1.getSequenceNumber(), activity1.getName(), activity1.getStatus(), agencyDTO, partnerAgencyDTOs,null, activity1.getMov(),null,activity1.isApproveORCompleteStatus(),activity1.getCompletedReportingPeriod()));
													}
												}
											
											keyActivityDTOs.add(new KeyActivityDTO(keyActivity.getId(), keyActivity.getSequenceNumber(), keyActivity.getName(), keyActivity.getStatus(), subActivityDTOs));
										}
									}
								}
								List<Indicator> indicators = indicatorService.findByOutput(output);
								indicatorDTOs = new ArrayList<IndicatorDTO>();
								if (indicators != null && indicators.size() > 0) {
									for (Indicator indicator : indicators) {
										if (indicator != null && indicator.getId() != null) {
											indicatorDTOs.add(new IndicatorDTO(indicator.getId(), indicator.getMessage(),indicator.getStatus()));
										}
									}
								}
								List<Target> targets = targetService.findByOutput(output);
								targetDTOs = new ArrayList<TargetDTO>();
								if (targets != null && targets.size() > 0) {
									for (Target target : targets) {
										if (target != null && target.getId() != null) {
											targetDTOs.add(new TargetDTO(target.getId(), target.getMessage(),target.getStatus()));
										}
									}
								}
							}outputDTOs.add(new OutputDTO(output.getId(), output.getSequenceNumber(), output.getOutput(), output.getStatus(), keyActivityDTOs,indicatorDTOs,targetDTOs));
						}
						
							jsonResponse.setOutputDTOs(outputDTOs);	
		
		return jsonResponse;
	}

	/*@RequestMapping(value = "getFilterHierarchy",method = RequestMethod.POST)
	public String getFilterHierarchy(ModelMap model,@RequestParam Integer projectId, @ModelAttribute FilterHierarchy filterHierarchy){
		model.addAttribute("strategicPillar", new StrategicPillar());
		model.addAttribute("theme", new Theme());
		model.addAttribute("outcome", new Outcome());
		model.addAttribute("output", new Output());
		model.addAttribute("keyActivity", new KeyActivity());
		model.addAttribute("subActivity", new SubActivity());
		model.addAttribute("indicator", new Indicator());
		model.addAttribute("target", new Target());
		List<HierarchyStrategicPillar> hierarchyStrategicPillars = new ArrayList<HierarchyStrategicPillar>();
		List<StrategicPillar> strategicPillars = new ArrayList<StrategicPillar>();
		List<ReportingPeriodDTO> reportingPeriodDTOs = new ArrayList<ReportingPeriodDTO>();
		List<StrategicPillarDTO> strategicPillarDTOs = new ArrayList<StrategicPillarDTO>();
		List<ThemeDTO> themeDTOs = new ArrayList<ThemeDTO>();
		List<OutcomeDTO> outcomeDTOs = new ArrayList<OutcomeDTO>();
		List<OutputDTO> outputDTOs = new ArrayList<OutputDTO>(); 
		List<TargetDTO> targetDTOs = new ArrayList<TargetDTO>();
		List<KeyActivityDTO> keyActivityDTOs = new ArrayList<KeyActivityDTO>();
		List<SubActivityDTO> subActivityDTOs = new ArrayList<SubActivityDTO>();
		AgencyDTO agencyDTO = new AgencyDTO();
		List<AgencyDTO> partnerAgencyDTOs = new ArrayList<AgencyDTO>();
		List<IndicatorDTO> indicatorDTOs = new ArrayList<IndicatorDTO>();
		List<ReportingPeriod>periods = new ArrayList<ReportingPeriod>();
		
		
		List<Planning> planningList = new ArrayList<Planning>();
		List<SubActivity> subActivities1 = new ArrayList<SubActivity>();
		Set<KeyActivity> keyActivities2 = new HashSet<KeyActivity>();
		
		
		
		Output output1 = outputServices.getById(Integer.parseInt(filterHierarchy.getOutput()));
		List<KeyActivity> keyActivities1 = keyActivityService.findByOutput(output1);
		keyActivityDTOs = new ArrayList<KeyActivityDTO>();
		if(keyActivities1 != null && keyActivities1.size() > 0) {
			for(KeyActivity keyActivity : keyActivities1) {
				if(keyActivity != null && keyActivity.getId() != null) {
					List<SubActivity> subActivities = subActivityService.findByKeyActivity(keyActivity);
					subActivityDTOs = new ArrayList<SubActivityDTO>();
					if(subActivities != null && subActivities.size() > 0) {
						for(SubActivity subActivity1 : subActivities) {
							if(filterHierarchy.getOutput() != null){
								if(filterHierarchy.getStatus() .equalsIgnoreCase("Planned")){
									planningList=planningService.getByPlanning(subActivity1);
									for (Planning planning2 : planningList) {
										int temp =0;
										SubActivity subActivity2 = new SubActivity();
										subActivity2 = subActivityService.getById(planning2.getSubActivity().getId());
										if(subActivities1.size() == 0){
											subActivities1.add(subActivity2);
										}else{
											for (SubActivity subActivity : subActivities1) {
												if(subActivity.getId() == subActivity2.getId()){
													temp=0;
													break;
												}else{
													temp=1;
												}
											}
											if(temp == 1){
												subActivities1.add(subActivity2);
											}
										}
										
									}
									for(SubActivity subActivity3 : subActivities1){
										int temp=0;
										KeyActivity keyActivity2 = new KeyActivity();
										keyActivity2 = keyActivityService.getById(subActivity3.getKeyActivity().getId());
										if(!keyActivities2.contains(keyActivity2))
										{
											keyActivities2.add(keyActivity2);
											
										}
										if(keyActivities2.size() == 0){
											keyActivities2.add(keyActivity2);
										}else{
											for (KeyActivity keyActivity3 : keyActivities2) {
												if(keyActivity3.getId() == keyActivity2.getId()){
													temp=0;
													break;
												}else{
													temp=1;
												}
											}
											if(temp == 1){
												keyActivities2.add(keyActivity2);
											}
										}
										
									}
									
								}if(filterHierarchy.getStatus().equalsIgnoreCase("Unplanned")){
										planningList=planningService.getByPlanning(subActivity1);
										int temp = 0;
										if(planningList.size() == 0){
										SubActivity subActivity=planningService.getSubActivityById(subActivity1.getId());
										if(subActivities1.size() == 0){
											subActivities1.add(subActivity);
										}else{
											for (SubActivity subActivity2 : subActivities1) {
												if(subActivity.getId() == subActivity2.getId()){
													temp=0;
													break;
												}else{
													temp=1;
												}
											}
											if(temp == 1){
												subActivities1.add(subActivity);
											}
										}
										}
										
										for(SubActivity subActivity3 : subActivities1){
											KeyActivity keyActivity2 = new KeyActivity();
											keyActivity2 = keyActivityService.getById(subActivity3.getKeyActivity().getId());
											if(keyActivities2.size() == 0){
												keyActivities2.add(keyActivity2);
											}else{
												for (KeyActivity keyActivity3 : keyActivities2) {
													if(keyActivity3.getId() == keyActivity2.getId()){
														temp=0;
														break;
													}else{
														temp=1;
													}
												}
												if(temp == 1){
													keyActivities2.add(keyActivity2);
												}
											}
										}
									}
								
								if(filterHierarchy.getStatus().equalsIgnoreCase("All")){
									planningList=planningService.getByPlanning(subActivity1);
									int temp = 0;
									if(planningList.size() == 0){
									SubActivity subActivity=planningService.getSubActivityById(subActivity1.getId());
									if(subActivities1.size() == 0){
										subActivities1.add(subActivity);
									}else{
										for (SubActivity subActivity2 : subActivities1) {
											if(subActivity.getId() == subActivity2.getId()){
												subActivities1.add(subActivity);
											}
										}
										
									}
									}
									
									for(SubActivity subActivity3 : subActivities1){
										KeyActivity keyActivity2 = new KeyActivity();
										keyActivity2 = keyActivityService.getById(subActivity3.getKeyActivity().getId());
										if(keyActivities2.size() == 0){
											keyActivities2.add(keyActivity2);
										}else{
											for (KeyActivity keyActivity3 : keyActivities2) {
												if(keyActivity3.getId() == keyActivity2.getId()){
													keyActivities2.add(keyActivity2);
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
		
		Set<KeyActivity> keyActivities3 = new HashSet<KeyActivity>(keyActivities2);
		
		if(projectId!=null){
			Project project = projectService.getById(projectId);
			if(project != null && projectId != null){
				StrategicPillar strategicPillar = strategicPillarService.getById(Integer.parseInt(filterHierarchy.getStrategicPillar()));
						if(strategicPillar != null && strategicPillar.getId() != null ){
							if(filterHierarchy.getTheme() == ""){
							List<Theme> themes = themeService.getThemeByStrategicPillar(strategicPillar);
							themeDTOs = new ArrayList<ThemeDTO>();
							if(themes != null && themes.size() > 0) {
								for(Theme theme : themes) {
									if(theme != null && theme.getId() != null) {
										// Outcome details
										List<Outcome> outcomes = outcomeService.getByTheme(theme);
										outcomeDTOs = new ArrayList<OutcomeDTO>();
										if (outcomes != null && outcomes.size() > 0) {
											for (Outcome outcome: outcomes) {
												if(outcome != null && outcome.getId() != null) {
													List<Output> outputs = outputServices.getByOutcome(outcome);
													outputDTOs = new ArrayList<OutputDTO>();
													if(outputs != null && outputs.size() > 0) {
														for(Output output : outputs) {
															if(output != null && output.getId() != null) {
																List<KeyActivity> keyActivities = keyActivityService.findByOutput(output);
																keyActivityDTOs = new ArrayList<KeyActivityDTO>();
																if(keyActivities != null && keyActivities.size() > 0) {
																	for(KeyActivity keyActivity : keyActivities) {
																		if(keyActivity != null && keyActivity.getId() != null) {
																			List<SubActivity> subActivities = subActivityService.findByKeyActivity(keyActivity);
																			subActivityDTOs = new ArrayList<SubActivityDTO>();
																			if(subActivities != null && subActivities.size() > 0) {
																				for(SubActivity subActivity : subActivities) {
																					if(subActivity != null && subActivity.getId() != null) {
																						agencyDTO = new AgencyDTO();
																						if(subActivity != null && subActivity.getLeadAgency() != null && subActivity.getLeadAgency().getId() != null) {
																							agencyDTO.setId(subActivity.getLeadAgency().getId());
																							agencyDTO.setAgency(subActivity.getLeadAgency().getName());
//																							agencyDTO.setType(subActivity.getLeadAgency().getAgencyType());
																							agencyDTO.setStatus(subActivity.getLeadAgency().getStatus());
																						}
																						if(subActivity != null && subActivity.getPartnerAgency() != null && subActivity.getPartnerAgency().size() > 0) {
																							partnerAgencyDTOs = new ArrayList<AgencyDTO>();
																							for(Agency agency : subActivity.getPartnerAgency()) {
																								AgencyDTO partnerAgencyDTO = new AgencyDTO();
																								agencyDTO.setId(agency.getId());
																								agencyDTO.setAgency(agency.getName());
																								agencyDTO.setType(agency.getAgencyType());
																								agencyDTO.setStatus(agency.getStatus());
																								partnerAgencyDTOs.add(partnerAgencyDTO);
																							}
																						}
																						List<String> years = new ArrayList<String>();
																						List<ReportingPeriod> reportingPeriods = new ArrayList<ReportingPeriod>();
																						years = reportingPeriodService.getYears();
																						model.addAttribute("years", years);
																						Date date = new Date();
																						int currentYear = date.getYear();
																						model.addAttribute("currentYear", currentYear);
																						if(years != null && years.size() > 0) {
																							for(String year : years) {
																								List<ReportingPeriod> rps = reportingPeriodService.getByYear(year);
																								if(rps != null && rps.size() > 0) {
																									for(ReportingPeriod reportingPeriod : rps) {
																										if(reportingPeriod != null && reportingPeriod.getId() != null) {
//																											reportingPeriodDTOs.add(new ReportingPeriodDTO(reportingPeriod.getId(), reportingPeriod.getName(), reportingPeriod.getYear(), reportingPeriod.getStartdate(), reportingPeriod.getEnddate(), reportingPeriod.getStatus()));
																											reportingPeriods.add(reportingPeriod);
																										}
																									}
																								}
  																							}
																						}
																						model.addAttribute("reportingPeriods", reportingPeriods);
																						subActivityDTOs.add(new SubActivityDTO(subActivity.getId(), subActivity.getSequenceNumber(), subActivity.getName(), subActivity.getStatus(), agencyDTO, partnerAgencyDTOs,null, subActivity.getMov(),null));
																					}
																				}
																			}
																			keyActivityDTOs.add(new KeyActivityDTO(keyActivity.getId(), keyActivity.getSequenceNumber(), keyActivity.getName(), keyActivity.getStatus(), subActivityDTOs));
																		}
																	}
																}
																List<Indicator> indicators = indicatorService.findByOutput(output);
																indicatorDTOs = new ArrayList<IndicatorDTO>();
																if (indicators != null && indicators.size() > 0) {
																	for (Indicator indicator : indicators) {
																		if (indicator != null && indicator.getId() != null) {
																			indicatorDTOs.add(new IndicatorDTO(indicator.getId(), indicator.getMessage(), indicator.getStatus()));
																		}
																	}
																}
																List<Target> targets = targetService.findByOutput(output);
																targetDTOs = new ArrayList<TargetDTO>();
																if (targets != null && targets.size() > 0) {
																	for (Target target : targets) {
																		if (target != null && target.getId() != null) {
																			targetDTOs.add(new TargetDTO(target.getId(), target.getMessage(), target.getStatus()));
																		}
																	}
																}
															}
															outputDTOs.add(new OutputDTO(output.getId(), output.getSequenceNumber(), output.getOutput(), output.getStatus(), keyActivityDTOs,indicatorDTOs,targetDTOs));
														}
													}
												}
												outcomeDTOs.add(new OutcomeDTO(outcome.getId(), outcome.getSequenceNumber(), outcome.getName(),
														outcome.getStatus(), outputDTOs));
											}
										}
										// Create Theme dtos
										themeDTOs.add(new ThemeDTO(theme.getId(), theme.getName(), theme.getStatus(), outcomeDTOs));
									}
								}
							}
							// Create Strategic Pillar dtos.
							strategicPillarDTOs.add(new StrategicPillarDTO(strategicPillar.getId(), strategicPillar.getName(), 
									strategicPillar.getStatus(), themeDTOs));
						}if(filterHierarchy.getTheme() != ""){
							Theme theme = themeService.getById(Integer.parseInt(filterHierarchy.getTheme()));
							themeDTOs = new ArrayList<ThemeDTO>();
									if(theme != null && theme.getId() != null) {
										// Outcome details
										if(filterHierarchy.getOutcome() == ""){
										List<Outcome> outcomes = outcomeService.getByTheme(theme);
										outcomeDTOs = new ArrayList<OutcomeDTO>();
										if (outcomes != null && outcomes.size() > 0) {
											for (Outcome outcome: outcomes) {
												if(outcome != null && outcome.getId() != null) {
													List<Output> outputs = outputServices.getByOutcome(outcome);
													outputDTOs = new ArrayList<OutputDTO>();
													if(outputs != null && outputs.size() > 0) {
														for(Output output : outputs) {
															if(output != null && output.getId() != null) {
																List<KeyActivity> keyActivities = keyActivityService.findByOutput(output);
																keyActivityDTOs = new ArrayList<KeyActivityDTO>();
																if(keyActivities != null && keyActivities.size() > 0) {
																	for(KeyActivity keyActivity : keyActivities) {
																		if(keyActivity != null && keyActivity.getId() != null) {
																			List<SubActivity> subActivities = subActivityService.findByKeyActivity(keyActivity);
																			subActivityDTOs = new ArrayList<SubActivityDTO>();
																			if(subActivities != null && subActivities.size() > 0) {
																				for(SubActivity subActivity : subActivities) {
																					if(subActivity != null && subActivity.getId() != null) {
																						agencyDTO = new AgencyDTO();
																						if(subActivity != null && subActivity.getLeadAgency() != null && subActivity.getLeadAgency().getId() != null) {
																							agencyDTO.setId(subActivity.getLeadAgency().getId());
																							agencyDTO.setAgency(subActivity.getLeadAgency().getName());
//																							agencyDTO.setType(subActivity.getLeadAgency().getAgencyType());
																							agencyDTO.setStatus(subActivity.getLeadAgency().getStatus());
																						}
																						if(subActivity != null && subActivity.getPartnerAgency() != null && subActivity.getPartnerAgency().size() > 0) {
																							partnerAgencyDTOs = new ArrayList<AgencyDTO>();
																							for(Agency agency : subActivity.getPartnerAgency()) {
																								AgencyDTO partnerAgencyDTO = new AgencyDTO();
																								agencyDTO.setId(agency.getId());
																								agencyDTO.setAgency(agency.getName());
																								agencyDTO.setType(agency.getAgencyType());
																								agencyDTO.setStatus(agency.getStatus());
																								partnerAgencyDTOs.add(partnerAgencyDTO);
																							}
																						}
																						
																						
																						
																						List<String> years = new ArrayList<String>();
																						List<ReportingPeriod> reportingPeriods = new ArrayList<ReportingPeriod>();
																						years = reportingPeriodService.getYears();
																						model.addAttribute("years", years);
																						Date date = new Date();
																						int currentYear = date.getYear();
																						model.addAttribute("currentYear", currentYear);
																						if(years != null && years.size() > 0) {
																							for(String year : years) {
																								List<ReportingPeriod> rps = reportingPeriodService.getByYear(year);
																								if(rps != null && rps.size() > 0) {
																									for(ReportingPeriod reportingPeriod : rps) {
																										if(reportingPeriod != null && reportingPeriod.getId() != null) {
//																											reportingPeriodDTOs.add(new ReportingPeriodDTO(reportingPeriod.getId(), reportingPeriod.getName(), reportingPeriod.getYear(), reportingPeriod.getStartdate(), reportingPeriod.getEnddate(), reportingPeriod.getStatus()));
																											reportingPeriods.add(reportingPeriod);
																										}
																									}
																								}
  																							}
																						}
																						model.addAttribute("reportingPeriods", reportingPeriods);
																						subActivityDTOs.add(new SubActivityDTO(subActivity.getId(), subActivity.getSequenceNumber(), subActivity.getName(), subActivity.getStatus(), agencyDTO, partnerAgencyDTOs,null, subActivity.getMov(),null));
																					}
																				}
																			}
																			keyActivityDTOs.add(new KeyActivityDTO(keyActivity.getId(), keyActivity.getSequenceNumber(), keyActivity.getName(), keyActivity.getStatus(), subActivityDTOs));
																		}
																	}
																}
																List<Indicator> indicators = indicatorService.findByOutput(output);
																indicatorDTOs = new ArrayList<IndicatorDTO>();
																if (indicators != null && indicators.size() > 0) {
																	for (Indicator indicator : indicators) {
																		if (indicator != null && indicator.getId() != null) {
																			indicatorDTOs.add(new IndicatorDTO(indicator.getId(), indicator.getMessage(), indicator.getStatus()));
																		}
																	}
																}
																List<Target> targets = targetService.findByOutput(output);
																targetDTOs = new ArrayList<TargetDTO>();
																if (targets != null && targets.size() > 0) {
																	for (Target target : targets) {
																		if (target != null && target.getId() != null) {
																			targetDTOs.add(new TargetDTO(target.getId(), target.getMessage(), target.getStatus()));
																		}
																	}
																}
															}
															outputDTOs.add(new OutputDTO(output.getId(), output.getSequenceNumber(), output.getOutput(), output.getStatus(), keyActivityDTOs,indicatorDTOs,targetDTOs));
														}
													}
												}
												outcomeDTOs.add(new OutcomeDTO(outcome.getId(), outcome.getSequenceNumber(), outcome.getName(),
														outcome.getStatus(), outputDTOs));
											}
										}
										// Create Theme dtos
										themeDTOs.add(new ThemeDTO(theme.getId(), theme.getName(), theme.getStatus(), outcomeDTOs));
									}if(filterHierarchy.getOutcome() != ""){
										Outcome outcome = outcomeService.getById(Integer.parseInt(filterHierarchy.getOutcome()));
										outcomeDTOs = new ArrayList<OutcomeDTO>();
												if(outcome != null && outcome.getId() != null) {
													if(filterHierarchy.getOutput() == ""){
													List<Output> outputs = outputServices.getByOutcome(outcome);
													outputDTOs = new ArrayList<OutputDTO>();
													if(outputs != null && outputs.size() > 0) {
														for(Output output : outputs) {
															if(output != null && output.getId() != null) {
																List<KeyActivity> keyActivities = keyActivityService.findByOutput(output);
																keyActivityDTOs = new ArrayList<KeyActivityDTO>();
																if(keyActivities != null && keyActivities.size() > 0) {
																	for(KeyActivity keyActivity : keyActivities) {
																		if(keyActivity != null && keyActivity.getId() != null) {
																			List<SubActivity> subActivities = subActivityService.findByKeyActivity(keyActivity);
																			subActivityDTOs = new ArrayList<SubActivityDTO>();
																			if(subActivities != null && subActivities.size() > 0) {
																				for(SubActivity subActivity : subActivities) {
																					if(subActivity != null && subActivity.getId() != null) {
																						agencyDTO = new AgencyDTO();
																						if(subActivity != null && subActivity.getLeadAgency() != null && subActivity.getLeadAgency().getId() != null) {
																							agencyDTO.setId(subActivity.getLeadAgency().getId());
																							agencyDTO.setAgency(subActivity.getLeadAgency().getName());
//																							agencyDTO.setType(subActivity.getLeadAgency().getAgencyType());
																							agencyDTO.setStatus(subActivity.getLeadAgency().getStatus());
																						}
																						if(subActivity != null && subActivity.getPartnerAgency() != null && subActivity.getPartnerAgency().size() > 0) {
																							partnerAgencyDTOs = new ArrayList<AgencyDTO>();
																							for(Agency agency : subActivity.getPartnerAgency()) {
																								AgencyDTO partnerAgencyDTO = new AgencyDTO();
																								agencyDTO.setId(agency.getId());
																								agencyDTO.setAgency(agency.getName());
																								agencyDTO.setType(agency.getAgencyType());
																								agencyDTO.setStatus(agency.getStatus());
																								partnerAgencyDTOs.add(partnerAgencyDTO);
																							}
																						}
																						List<String> years = new ArrayList<String>();
																						List<ReportingPeriod> reportingPeriods = new ArrayList<ReportingPeriod>();
																						years = reportingPeriodService.getYears();
																						model.addAttribute("years", years);
																						Date date = new Date();
																						int currentYear = date.getYear();
																						model.addAttribute("currentYear", currentYear);
																						if(years != null && years.size() > 0) {
																							for(String year : years) {
																								List<ReportingPeriod> rps = reportingPeriodService.getByYear(year);
																								if(rps != null && rps.size() > 0) {
																									for(ReportingPeriod reportingPeriod : rps) {
																										if(reportingPeriod != null && reportingPeriod.getId() != null) {
//																											reportingPeriodDTOs.add(new ReportingPeriodDTO(reportingPeriod.getId(), reportingPeriod.getName(), reportingPeriod.getYear(), reportingPeriod.getStartdate(), reportingPeriod.getEnddate(), reportingPeriod.getStatus()));
																											reportingPeriods.add(reportingPeriod);
																										}
																									}
																								}
  																							}
																						}
																						model.addAttribute("reportingPeriods", reportingPeriods);
																						
																					}subActivityDTOs.add(new SubActivityDTO(subActivity.getId(), subActivity.getSequenceNumber(), subActivity.getName(), subActivity.getStatus(), agencyDTO, partnerAgencyDTOs,null, subActivity.getMov(),null));
																				}
																			}
																			keyActivityDTOs.add(new KeyActivityDTO(keyActivity.getId(), keyActivity.getSequenceNumber(), keyActivity.getName(), keyActivity.getStatus(), subActivityDTOs));
																		}
																	}
																}
																List<Indicator> indicators = indicatorService.findByOutput(output);
																indicatorDTOs = new ArrayList<IndicatorDTO>();
																if (indicators != null && indicators.size() > 0) {
																	for (Indicator indicator : indicators) {
																		if (indicator != null && indicator.getId() != null) {
																			indicatorDTOs.add(new IndicatorDTO(indicator.getId(), indicator.getMessage(), indicator.getStatus()));
																		}
																	}
																}
																List<Target> targets = targetService.findByOutput(output);
																targetDTOs = new ArrayList<TargetDTO>();
																if (targets != null && targets.size() > 0) {
																	for (Target target : targets) {
																		if (target != null && target.getId() != null) {
																			targetDTOs.add(new TargetDTO(target.getId(), target.getMessage(), target.getStatus()));
																		}
																	}
																}
															}
															outputDTOs.add(new OutputDTO(output.getId(), output.getSequenceNumber(), output.getOutput(), output.getStatus(), keyActivityDTOs,indicatorDTOs,targetDTOs));
														}
													}
												}
													if(filterHierarchy.getOutput() != ""){
														Output output = outputServices.getById(Integer.parseInt(filterHierarchy.getOutput()));
														outputDTOs = new ArrayList<OutputDTO>();
														if(output != null && output.getId() != null) {
															List<KeyActivity> keyActivities = keyActivityService.findByOutput(output);
															keyActivityDTOs = new ArrayList<KeyActivityDTO>();
															if(keyActivities != null && keyActivities.size() > 0) {
																for(KeyActivity keyActivity : keyActivities2) {
																	if(keyActivity != null && keyActivity.getId() != null) {
																		List<SubActivity> subActivities = subActivityService.findByKeyActivity(keyActivity);
																		subActivityDTOs = new ArrayList<SubActivityDTO>();
																		if(subActivities != null && subActivities.size() > 0) {
																			List< SubActivity > activities = new ArrayList<SubActivity>();
																			for(SubActivity subActivity : subActivities) {
																				if(subActivity != null && subActivity.getId() != null) {
																						if(subActivity != null && subActivity.getId() != null) {
																							for(SubActivity activity : subActivities1){
																							if(subActivity.getId() == activity.getId()){
																								activities.add(subActivity);
																							}
																							}
																						}
																				}
																			}
																							for(SubActivity activity1 : activities){
																								agencyDTO = new AgencyDTO();
																								if(activity1 != null && activity1.getLeadAgency() != null && activity1.getLeadAgency().getId() != null) {
																									agencyDTO.setId(activity1.getLeadAgency().getId());
																									agencyDTO.setAgency(activity1.getLeadAgency().getName());
//																									agencyDTO.setType(activity1.getLeadAgency().getAgencyType());
																									agencyDTO.setStatus(activity1.getLeadAgency().getStatus());
																								}
																								List<String> years = new ArrayList<String>();
																								List<ReportingPeriod> reportingPeriods = new ArrayList<ReportingPeriod>();
																								years = reportingPeriodService.getYears();
																								model.addAttribute("years", years);
																								Date date = new Date();
																								int currentYear = date.getYear();
																								model.addAttribute("currentYear", currentYear);
																								if(years != null && years.size() > 0) {
																									for(String year : years) {
																										List<ReportingPeriod> rps = reportingPeriodService.getByYear(year);
																										if(rps != null && rps.size() > 0) {
																											for(ReportingPeriod reportingPeriod : rps) {
																												if(reportingPeriod != null && reportingPeriod.getId() != null) {
//																													reportingPeriodDTOs.add(new ReportingPeriodDTO(reportingPeriod.getId(), reportingPeriod.getName(), reportingPeriod.getYear(), reportingPeriod.getStartdate(), reportingPeriod.getEnddate(), reportingPeriod.getStatus()));
																													reportingPeriods.add(reportingPeriod);
																												}
																											}
																										}
																										}
																								}
																					model.addAttribute("reportingPeriods", reportingPeriods);
																					subActivityDTOs.add(new SubActivityDTO(activity1.getId(), activity1.getSequenceNumber(), activity1.getName(), activity1.getStatus(), agencyDTO, partnerAgencyDTOs,null, activity1.getMov(),null));
																				}
																			}
																		keyActivityDTOs.add(new KeyActivityDTO(keyActivity.getId(), keyActivity.getSequenceNumber(), keyActivity.getName(), keyActivity.getStatus(), subActivityDTOs));
																	}
																}
															}
															List<Indicator> indicators = indicatorService.findByOutput(output);
															indicatorDTOs = new ArrayList<IndicatorDTO>();
															if (indicators != null && indicators.size() > 0) {
																for (Indicator indicator : indicators) {
																	if (indicator != null && indicator.getId() != null) {
																		indicatorDTOs.add(new IndicatorDTO(indicator.getId(), indicator.getMessage(), indicator.getStatus()));
																	}
																}
															}
															List<Target> targets = targetService.findByOutput(output);
															targetDTOs = new ArrayList<TargetDTO>();
															if (targets != null && targets.size() > 0) {
																for (Target target : targets) {
																	if (target != null && target.getId() != null) {
																		targetDTOs.add(new TargetDTO(target.getId(), target.getMessage(), target.getStatus()));
																	}
																}
															}
														}outputDTOs.add(new OutputDTO(output.getId(), output.getSequenceNumber(), output.getOutput(), output.getStatus(), keyActivityDTOs,indicatorDTOs,targetDTOs));
													}
												}
															
												outcomeDTOs.add(new OutcomeDTO(outcome.getId(), outcome.getSequenceNumber(), outcome.getName(),
														outcome.getStatus(), outputDTOs));
											}
										}themeDTOs.add(new ThemeDTO(theme.getId(), theme.getName(), theme.getStatus(), outcomeDTOs));
									}
							}// Create Strategic Pillar dtos.
						strategicPillarDTOs.add(new StrategicPillarDTO(strategicPillar.getId(), strategicPillar.getName(), 
								strategicPillar.getStatus(), themeDTOs));
							
						
						}
						}
		model.addAttribute("strategicPillars", strategicPillarDTOs);
		return "/project/projectActivity";
	}*/
	
	
	@RequestMapping(value = "filterHierarchy", method = RequestMethod.GET)
	public String filterHierarchy(ModelMap model,@RequestParam("action") String action) {
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.PROJECT_PLANNER)){
			List<String> years = reportingPeriodService.findAllYears();
			model.addAttribute("years", years);
			Calendar now = Calendar.getInstance();
			int year = now.get(Calendar.YEAR);
			String currentYear = String.valueOf(year);
			if(currentYear != null) {
				model.addAttribute("currentYear", currentYear);
				Calendar next = Calendar.getInstance();
				next.add(Calendar.YEAR, 1);
				int nyear = next.get(Calendar.YEAR);
				String nextYear = String.valueOf(nyear);
				if(nextYear != null) {
					model.addAttribute("nextYear", nextYear);
				}
			}
			List<ReportingPeriod> reportingPeriodList=reportingPeriodService.getReportingPeriod();
			model.addAttribute("reportingPeriodList",reportingPeriodList);
			model.addAttribute("statusList",statusService.listAllStatus());
			model.addAttribute("strategicPillarList",strategicPillarService.getStrategicPillars());
			model.addAttribute("leadAgencyList",agencyService.getByPartnerAgency(Boolean.TRUE));
			model.addAttribute("partnerAgencyList",agencyService.getByLeadAgency(Boolean.TRUE));
			if(action.equalsIgnoreCase("view")){
				model.addAttribute("accessRightsView", true);
			}else{
				model.addAttribute("accessRightsView", false);
			}
			model.addAttribute("filterHierarchy", new FilterHierarchy());
			return "project/filterHierarchy";
		}else{
			return "login";
		}
		
	}
	
	/**
	 * Get all the ReportingPeriod
	 * @param yearId
	 * @return
	 */

	@RequestMapping(value = "getQuartersByYear", method = RequestMethod.GET)
	public @ResponseBody List<ReportingPeriod> getQuartersByYear(ModelMap model,@RequestParam String yearId) {
		List<ReportingPeriod> quarters = reportingPeriodService.getByYear(yearId);
		return quarters;
	}
	
	/**
	 * Get all the Themes
	 * @param strategicId
	 * @return
	 */

	@RequestMapping(value = "getThemesByStrategic", method = RequestMethod.GET)
	public @ResponseBody List<Theme> getThemesByStrategic(ModelMap model,@RequestParam("strategicId") String strategicId) {
		List<Theme> themes = new ArrayList<Theme>();
		if(strategicId != null){
			if(strategicId.equals("All")){
				return themes;
			}else{
				int sId = Integer.parseInt(strategicId);
				StrategicPillar strategicPillar = strategicPillarService.getById(sId);
				themes = themeService.getThemeByStrategicPillar(strategicPillar);
				return themes;
			}
		}
		return null;
	}
	
	/**
	 * Get all the outcomes
	 * @param themeId
	 * @return
	 */

	@RequestMapping(value = "getOutcomesByThemeReport", method = RequestMethod.GET)
	public @ResponseBody LinkedHashSet<Outcome> getOutcomesByTheme(ModelMap model,@RequestParam String reportingPeriodId,@RequestParam String themeId,@RequestParam String strategic) {
		LinkedHashSet<Outcome> outcomes = new LinkedHashSet<Outcome>();
		List<Integer> themeIds = new ArrayList<Integer>();
		List<Integer> reportingPeriodIds = new ArrayList<Integer>();
		List<Integer> strategicIds = new ArrayList<Integer>();
		List<String> tIds = Arrays.asList(themeId.split(","));
		List<String> rpIds = Arrays.asList(reportingPeriodId.split(","));
		List<String> sIds = Arrays.asList(strategic.split(","));
		Agency leadAgency = agencyService.findByLoginUser(PrincipalUtil.getPrincipal());
		if(rpIds != null && rpIds.size() > 0) {
			if(rpIds.contains("0")) {
				List<ReportingPeriod> reportingPeriods = reportingPeriodService.getAll();
				if(reportingPeriods != null && reportingPeriods.size() > 0) {
					for(ReportingPeriod period : reportingPeriods) {
						reportingPeriodIds.add(period.getId());
					}
				}
			} else {
				for(String rpId : rpIds) {
					reportingPeriodIds.add(Integer.parseInt(rpId));
				}
			}
		}
		
		if(sIds != null && sIds.size() > 0){
			if(sIds.contains("0")){
				List<StrategicPillar> strategicPillars = strategicPillarService.getStrategicPliiersByReportingPeriodsAndLeadAgency(reportingPeriodIds, leadAgency.getId());
				for (StrategicPillar strategicPillar : strategicPillars) {
					strategicIds.add(strategicPillar.getId());
				}
			}
			else {
				for (String sId : sIds) {
					strategicIds.add(Integer.parseInt(sId));
				}
			}
		}
		
		/*if(sIds != null && sIds.size() > 0){
			if(sIds.contains("0")){
				List<StrategicPillar> strategicPillars = strategicPillarService.getStrategicPliiersByReportingPeriods(reportingPeriodIds);
				for (StrategicPillar strategicPillar : strategicPillars) {
					strategicIds.add(strategicPillar.getId());
				}
			}
			else {
				for (String sId : sIds) {
					strategicIds.add(Integer.parseInt(sId));
				}
			}
		}*/
		
		if(tIds != null && tIds.size() > 0){
			if(tIds.contains("0")){
				LinkedHashSet<Theme> themes = new LinkedHashSet<Theme>();
				List<SubActivity> activities = subActivityService.getSubActivitiesByStrategicPillarsAndReportingPeriodsAndLeadAgency(strategicIds, reportingPeriodIds,leadAgency.getId());
				if(activities != null){
				for (SubActivity subActivity : activities) {
					themes .add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme());
				}
				
				for (Theme theme2 : themes) {
					themeIds.add(theme2.getId());
				}
				}
			}
			else {
				for (String tId : tIds) {
					themeIds.add(Integer.parseInt(tId));
				}
			}
		}
		
		/*if(tIds != null && tIds.size() > 0) {
			Agency leadAgency = agencyService.findByLoginUser(PrincipalUtil.getPrincipal());
			if(tIds.contains("0")) {
				LinkedHashSet<Theme> themes = new LinkedHashSet<Theme>();
				List<SubActivity> themesubActivities = subActivityService.getSubActivitiesByStrategicPillarsAndReportingPeriodsAndLeadAgency(strategicIds,reportingPeriodIds, leadAgency.getId());
				if(themesubActivities != null && themesubActivities.size() > 0){
					for (SubActivity subActivity : themesubActivities) {
						themes.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme());
					}
					for (Theme theme : themes) {
						themeIds.add(theme.getId());
					}
				}
				for (Integer str : strategicIds) {
					StrategicPillar strategicPillar = strategicPillarService.getById(str);
					List<Theme> themes = themeService.getThemeByStrategicPillarReportingPeriodAndLeadAgency(strategicPillar);fggh
					for (Theme theme : themes) {
						themeIds.add(theme.getId());
					}
				}
				List<SubActivity> subActivities = subActivityService.getSubActivitiesByThemesAndReportingPeriodsAndLeadAgency(themeIds, reportingPeriodIds, leadAgency.getId());
				if(subActivities != null && subActivities.size() > 0) {
					for (SubActivity subActivity : subActivities) {
						outcomes.add(subActivity.getKeyActivity().getOutput().getOutcome());
					}
				}
				
			} else {
				for(String tId : tIds) {
					Theme theme = themeService.getById(Integer.parseInt(tId));
					if(theme != null && theme.getId() != null) {
						themeIds.add(theme.getId());
					}
				}
				List<SubActivity> subActivities = subActivityService.getSubActivitiesByThemesAndReportingPeriodsAndLeadAgency(themeIds, reportingPeriodIds, leadAgency.getId());
				if(subActivities != null && subActivities.size() > 0) {
					for (SubActivity subActivity : subActivities) {
						outcomes.add(subActivity.getKeyActivity().getOutput().getOutcome());
					}
				}
			}
		}*/
		
		List<SubActivity> subActivities = subActivityService.getSubActivitiesByThemesAndReportingPeriodsAndLeadAgency(themeIds, reportingPeriodIds, leadAgency.getId());
		if(subActivities != null && subActivities.size() > 0) {
			for (SubActivity subActivity : subActivities) {
				outcomes.add(subActivity.getKeyActivity().getOutput().getOutcome());
			}
		}
		return outcomes;
	}
	
	@RequestMapping(value = "getOutcomesByTheme", method = RequestMethod.GET)
	public @ResponseBody List<Outcome> getOutcomesByTheme(ModelMap model,@RequestParam Integer themeId) {
		Theme theme = themeService.getById(themeId);
		List<Outcome> outcomes = outcomeService.getByTheme(theme);
		return outcomes;
	}

	/**
	 * Get all the outputs
	 * @param outcomeId
	 * @return
	 */

	@RequestMapping(value = "getOutputsByoutcome", method = RequestMethod.GET)
	public @ResponseBody List<Output> getOutputsByoutcome(ModelMap model,@RequestParam Integer outcomeId) {
		Outcome outcome = outcomeService.getById(outcomeId);
		List<Output> outputs = outputServices.getByOutcome(outcome);
		return outputs;
	}
	
	@RequestMapping(value = "getOutputsByoutcomeReport", method = RequestMethod.GET)
	public @ResponseBody LinkedHashSet<Output> getOutputsByoutcome(ModelMap model,@RequestParam String reportingPeriodId,@RequestParam String outcomeId,
			@RequestParam String themeId,@RequestParam String strategicId) {
		//LinkedHashSet<Output> outputs = new LinkedHashSet<Output>();
		List<Integer> outcomeIds = new ArrayList<Integer>();
		List<Integer> reportingPeriodIds = new ArrayList<Integer>();
		List<Integer> strategicIds = new ArrayList<Integer>();
		List<Integer> themeIds = new ArrayList<Integer>();
		List<String> ocIds = Arrays.asList(outcomeId.split(","));
		List<String> rpIds = Arrays.asList(reportingPeriodId.split(","));
		List<String> spIds = Arrays.asList(strategicId.split(","));
		List<String> tIds = Arrays.asList(themeId.split(","));
		Agency leadAgency = agencyService.findByLoginUser(PrincipalUtil.getPrincipal());
		if(rpIds != null && rpIds.size() > 0) {
			if(rpIds.contains("0")) {
				List<ReportingPeriod> reportingPeriods = reportingPeriodService.getAll();
				if(reportingPeriods != null && reportingPeriods.size() > 0) {
					for(ReportingPeriod period : reportingPeriods) {
						reportingPeriodIds.add(period.getId());
					}
				}
			} else {
				for(String rpId : rpIds) {
					reportingPeriodIds.add(Integer.parseInt(rpId));
				}
			}
		}
		if(spIds != null && spIds.size() > 0){
			if(spIds.contains("0")){
				List<StrategicPillar> strategicPillars = strategicPillarService.getStrategicPliiersByReportingPeriodsAndLeadAgency(reportingPeriodIds, leadAgency.getId());
				for (StrategicPillar strategicPillar : strategicPillars) {
					strategicIds.add(strategicPillar.getId());
				}
			}
			else {
				for (String sId : spIds) {
					strategicIds.add(Integer.parseInt(sId));
				}
			}
		}
		
		if(tIds != null && tIds.size() > 0){
			if(tIds.contains("0")){
				LinkedHashSet<Theme> themes = new LinkedHashSet<Theme>();
				List<SubActivity> activities = subActivityService.getSubActivitiesByStrategicPillarsAndReportingPeriodsAndLeadAgency(strategicIds, reportingPeriodIds,leadAgency.getId());
				if(activities != null){
				for (SubActivity subActivity : activities) {
					themes .add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme());
				}
				
				for (Theme theme2 : themes) {
					themeIds.add(theme2.getId());
				}
				}
			}
			else {
				for (String tId : tIds) {
					themeIds.add(Integer.parseInt(tId));
				}
			}
		}
		
		if(ocIds != null && ocIds.size() > 0){
			if(ocIds.contains("0")){
				LinkedHashSet<Outcome> outcomes = new LinkedHashSet<Outcome>();
				List<SubActivity> subActivities = subActivityService.getSubActivitiesByListOfPillarListOfThemeAndListOfOpenedReportingPeriodAndLeadAgency(strategicIds, themeIds, reportingPeriodIds, leadAgency.getId());
				if(subActivities != null){
				for (SubActivity subActivity : subActivities) {
					outcomes.add(subActivity.getKeyActivity().getOutput().getOutcome());
				}
				
				for (Outcome outcome : outcomes) {
					outcomeIds.add(outcome.getId());
				}
				}
			}
			else {
				for (String oId : ocIds) {
					outcomeIds.add(Integer.parseInt(oId));
				}
			}
		}
		
		
		List<SubActivity> subActivities = subActivityService.getSubActivitiesByListOfPillarListOfThemeListOfOutcomeAndListOfOpenedReportingPeriodAndLeadAgency(strategicIds, themeIds, outcomeIds, reportingPeriodIds, leadAgency.getId());
		if(subActivities != null){
			LinkedHashSet<Output> outputs = new LinkedHashSet<Output>();
			if(subActivities.size() > 0){
				for (SubActivity subActivity : subActivities) {
					outputs.add(subActivity.getKeyActivity().getOutput());
				}
			}
		return outputs;
		}
		
		/*if(ocIds != null && ocIds.size() > 0) {
			String id = "0";
			Agency leadAgency = agencyService.findByLoginUser(PrincipalUtil.getPrincipal());
			if(ocIds.contains(id)) {
				List<Outcome> outcomes = outcomeService.getOutcomes();
				if (outcomes != null && outcomes.size() > 0) {
					for(Outcome outcome : outcomes) {
						outcomeIds.add(outcome.getId());
					}
					List<SubActivity> subActivities = subActivityService.getSubActivitiesByOutcomesAndReportingPeriodsAndLeadAgency(outcomeIds, reportingPeriodIds, leadAgency.getId());
					if(subActivities != null && subActivities.size() > 0) {
						for (SubActivity subActivity : subActivities) {
							outputs.add(subActivity.getKeyActivity().getOutput());
						}
					}
				}
			} else {
				for(String ocId : ocIds) {
					Outcome outcome = outcomeService.getById(Integer.parseInt(ocId));
					if(outcome != null && outcome.getId() != null) {
						outcomeIds.add(outcome.getId());
					}
				}
				List<SubActivity> subActivities = subActivityService.getSubActivitiesByOutcomesAndReportingPeriodsAndLeadAgency(outcomeIds, reportingPeriodIds, leadAgency.getId());
				if(subActivities != null && subActivities.size() > 0) {
					for (SubActivity subActivity : subActivities) {
						outputs.add(subActivity.getKeyActivity().getOutput());
					}
				}
			}
		}*/
		return null;
	}
	
	@RequestMapping(value = "getLeadAgencyByoutput", method = RequestMethod.GET)
	public @ResponseBody List<Agency> getLeadAgencyByoutput(ModelMap model,@RequestParam Integer outputId) {
		List<Agency> agencies = new ArrayList<Agency>();
		List<Integer> leadAgencyIds = new ArrayList<Integer>();
		Output output = outputServices.getById(outputId);
		List<KeyActivity> keyActivities = keyActivityService.findByOutput(output);
		for(KeyActivity keyActivity : keyActivities){
			List<SubActivity> subActivity = subActivityService.findByKeyActivity(keyActivity);
			for(SubActivity subActivity2 : subActivity){
				leadAgencyIds.add(subActivity2.getLeadAgency().getId());
			}
		}
		Set<Integer> integers = new HashSet<Integer>(leadAgencyIds);
		for(Integer agencyId : integers){
			agencies.add(agencyService.findByAgencyId(agencyId));
		}
		return agencies;
	}
	
	@RequestMapping(value = "getPartnerAgencyByoutput", method = RequestMethod.GET)
	public @ResponseBody LinkedHashSet<Agency> getPartnerAgencyByoutput(ModelMap model,@RequestParam String reportingPeriodId,@RequestParam String outputId) {
		LinkedHashSet<Agency> agencies = new LinkedHashSet<Agency>();
		List<Integer> outputIds = new ArrayList<Integer>();
		List<Integer> reportingPeriodIds = new ArrayList<Integer>();
		List<Integer> partnerAgencyIds = new ArrayList<Integer>();
		List<String> opIds = Arrays.asList(outputId.split(","));
		List<String> rpIds = Arrays.asList(reportingPeriodId.split(","));
		if(rpIds != null && rpIds.size() > 0) {
			if(rpIds.contains("0")) {
				List<ReportingPeriod> reportingPeriods = reportingPeriodService.getAll();
				if(reportingPeriods != null && reportingPeriods.size() > 0) {
					for(ReportingPeriod period : reportingPeriods) {
						reportingPeriodIds.add(period.getId());
					}
				}
			} else {
				for(String rpId : rpIds) {
					reportingPeriodIds.add(Integer.parseInt(rpId));
				}
			}
		}
		if(opIds != null && opIds.size() > 0) {
			String id = "0";
			Agency leadAgency = agencyService.findByLoginUser(PrincipalUtil.getPrincipal());
			if(opIds.contains(id)) {
				List<Output> outputs = outputServices.getAll();
				if (outputs != null && outputs.size() > 0) {
					for(Output output : outputs) {
						outputIds.add(output.getId());
					}
					List<SubActivity> subActivities = subActivityService.getSubActivitiesByOutputsAndReportingPeriodsAndLeadAgency(outputIds, reportingPeriodIds, leadAgency.getId());
					if(subActivities != null && subActivities.size() > 0) {
						for (SubActivity subActivity : subActivities) {
							if(subActivity != null && subActivity.getId() != null && subActivity.getPartnerAgency() != null && subActivity.getPartnerAgency().size() > 0) {
								for(Agency agency : subActivity.getPartnerAgency()) {
									partnerAgencyIds.add(agency.getId());
								}
							}
						}
					}
					Set<Integer> integers = new HashSet<Integer>(partnerAgencyIds);
					for(Integer agencyId : integers){
						agencies.add(agencyService.findByAgencyId(agencyId));
					}
				}
			} else {
				for(String opId : opIds) {
					Output output = outputServices.getById(Integer.parseInt(opId));
					if(output != null && output.getId() != null) {
						outputIds.add(output.getId());
					}
				}
				List<SubActivity> subActivities = subActivityService.getSubActivitiesByOutputsAndReportingPeriodsAndLeadAgency(outputIds, reportingPeriodIds, leadAgency.getId());
				if(subActivities != null && subActivities.size() > 0) {
					for (SubActivity subActivity : subActivities) {
						if(subActivity != null && subActivity.getId() != null && subActivity.getPartnerAgency() != null && subActivity.getPartnerAgency().size() > 0) {
							for(Agency agency : subActivity.getPartnerAgency()) {
								partnerAgencyIds.add(agency.getId());
							}
						}
					}
				}
				Set<Integer> integers = new HashSet<Integer>(partnerAgencyIds);
				for(Integer agencyId : integers){
					agencies.add(agencyService.findByAgencyId(agencyId));
				}
			}
		}
		return agencies;
	}
	
	/*@RequestMapping(value = "getPartnerAgencyByoutput", method = RequestMethod.GET)
	public @ResponseBody List<Agency> getPartnerAgencyByoutput(ModelMap model,@RequestParam Integer outputId) {
		List<Agency> agencies = new ArrayList<Agency>();
		List<Integer> partnerAgencyIds = new ArrayList<Integer>();
		Output output = outputServices.getById(outputId);
		List<KeyActivity> keyActivities = keyActivityService.findByOutput(output);
		for(KeyActivity keyActivity : keyActivities){
			List<SubActivity> subActivity = subActivityService.findByKeyActivity(keyActivity);
			for(SubActivity subActivity2 : subActivity){
				if(subActivity2.getPartnerAgency() != null && subActivity2.getPartnerAgency().size() > 0) {
					for(Agency agency : subActivity2.getPartnerAgency()) {
						partnerAgencyIds.add(agency.getId());
					}
				}
				//partnerAgencyIds.add(subActivity2.getLeadAgency().getId());
			}
		}
		Set<Integer> integers = new HashSet<Integer>(partnerAgencyIds);
		for(Integer agencyId : integers){
			agencies.add(agencyService.findByAgencyId(agencyId));
		}
		return agencies;
	}*/
	
	@RequestMapping(value = "getActivitysByLeadAgency", method = RequestMethod.GET)
	public @ResponseBody ActualReportUtil getActivitysByLeadAgency(ModelMap model,@RequestParam Integer outputId ,@RequestParam Integer leadAgencyId) {
		ActualReportUtil reportUtil = new ActualReportUtil();
		Output output = outputServices.getById(outputId);
		List<KeyActivity> keyActivities = keyActivityService.findByOutput(output);
		List<KeyActivity> keyActivities2 = new ArrayList<KeyActivity>();
		Agency agency = agencyService.findByAgencyId(leadAgencyId);
		List<SubActivity> subActivities2 = new ArrayList<SubActivity>();
		for(KeyActivity keyActivity : keyActivities){
			subActivities2 = new ArrayList<SubActivity>();
			List<SubActivity> subActivities = subActivityService.findByKeyActivity(keyActivity);
			for(SubActivity subActivity : subActivities){
				if(subActivity.getLeadAgency().getId().equals(agency.getId())){
					subActivities2.add(subActivity);
				}
			}
			keyActivity.setSubActivities(subActivities2);
			keyActivities2.add(keyActivity);
		}
		List<StatusTracking> statusTrackings = statusTrackingRepository.findAll();
		reportUtil.setKeyActivities(keyActivities2);
		reportUtil.setStatusTrackings(statusTrackings);
		return reportUtil;
	}
	
	@RequestMapping(value = "getActivitysByPartnerAgency", method = RequestMethod.GET)
	public @ResponseBody ActualReportUtil getActivitysByPartnerAgency(ModelMap model,@RequestParam Integer outputId ,@RequestParam Integer leadAgencyId) {
		ActualReportUtil reportUtil = new ActualReportUtil();
		Output output = outputServices.getById(outputId);
		List<KeyActivity> keyActivities = keyActivityService.findByOutput(output);
		List<KeyActivity> keyActivities2 = new ArrayList<KeyActivity>();
		Agency agency = agencyService.findByAgencyId(leadAgencyId);
		List<SubActivity> subActivities2 = new ArrayList<SubActivity>();
		for(KeyActivity keyActivity : keyActivities){
			subActivities2 = new ArrayList<SubActivity>();
			List<SubActivity> subActivities = subActivityService.findByKeyActivity(keyActivity);
			for(SubActivity subActivity : subActivities){
				if(subActivity.getPartnerAgency() != null && subActivity.getPartnerAgency().size() > 0) {
					for(Agency partnerAgency : subActivity.getPartnerAgency()) {
						if(partnerAgency.getId().equals(agency.getId())){
							subActivities2.add(subActivity);
						}
					}
				}
			}
			keyActivity.setSubActivities(subActivities2);
			keyActivities2.add(keyActivity);
		}
		List<StatusTracking> statusTrackings = statusTrackingRepository.findAll();
		reportUtil.setKeyActivities(keyActivities2);
		reportUtil.setStatusTrackings(statusTrackings);
		return reportUtil;
	}
	
	@SuppressWarnings("null")
	@RequestMapping(value = "actualReportDownload" , method = RequestMethod.GET)
	public @ResponseBody String actualReportDownload(HttpServletRequest request, HttpServletResponse httpServletResponse , @RequestParam("leadAgencyId") Integer leadAgencyId , @RequestParam("outputId") Integer outputId){
		
		String fileName = "Activity Status Report"+  ".xls";
		String contextPath = ConstantUtil.CONTEXT_PATH;
		FileOutputStream fileOutputStream = null;
		File file = null;
		try {
			file = new File(contextPath + fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			fileOutputStream = new FileOutputStream(file);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		HSSFSheet hssfSheet = hssfWorkbook.createSheet(" Actual Status Report Details ");
		HSSFRow headerRow = hssfSheet.createRow(0);

		Font boldFont = hssfWorkbook.createFont();
		boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		boldFont.setColor(IndexedColors.BLACK.getIndex());
		
		CellStyle tableHeaderStyle = hssfWorkbook.createCellStyle();
		tableHeaderStyle.setBorderBottom(CellStyle.BORDER_THIN);
		tableHeaderStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		tableHeaderStyle.setBorderLeft(CellStyle.BORDER_THIN);
		tableHeaderStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		tableHeaderStyle.setBorderRight(CellStyle.BORDER_THIN);
		tableHeaderStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		tableHeaderStyle.setBorderTop(CellStyle.BORDER_THIN);
		tableHeaderStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		tableHeaderStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		tableHeaderStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		tableHeaderStyle.setAlignment(CellStyle.ALIGN_CENTER);
		tableHeaderStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		tableHeaderStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		tableHeaderStyle.setFont(boldFont);
		
		CellStyle tableHeaderStyle1 = hssfWorkbook.createCellStyle();
		Font font = hssfWorkbook.createFont();
		font.setColor(IndexedColors.RED.getIndex());
		tableHeaderStyle1.setFont(font);
		
		HSSFCell seqNumber = headerRow.createCell(0);
		seqNumber.setCellValue("#");
		seqNumber.setCellStyle(tableHeaderStyle);
		seqNumber.setCellType(Cell.CELL_TYPE_STRING);

		HSSFCell keyActivitys = headerRow.createCell(1);
		keyActivitys.setCellValue("Key Activities");
		keyActivitys.setCellStyle(tableHeaderStyle);
		keyActivitys.setCellType(Cell.CELL_TYPE_STRING);

		HSSFCell subActivitys  = headerRow.createCell(2);
		subActivitys.setCellValue("Sub Activities");
		subActivitys.setCellStyle(tableHeaderStyle);
		subActivitys.setCellType(Cell.CELL_TYPE_STRING);
		
		HSSFCell status = headerRow.createCell(3);
		status.setCellValue("Status");
		status.setCellStyle(tableHeaderStyle);
		status.setCellType(Cell.CELL_TYPE_STRING);
		
		HSSFCell keyProgress = headerRow.createCell(4);
		keyProgress.setCellValue("Key Progress");
		keyProgress.setCellStyle(tableHeaderStyle);
		keyProgress.setCellType(Cell.CELL_TYPE_STRING);
		
		
		
		HSSFCell reasongap = headerRow.createCell(5);
		reasongap.setCellValue("Reasons for gap if any");
		reasongap.setCellStyle(tableHeaderStyle);
		reasongap.setCellType(Cell.CELL_TYPE_STRING);
		
		HSSFCell reactify = headerRow.createCell(6);
		reactify.setCellValue("Plan of Action to rectify the gap");
		reactify.setCellStyle(tableHeaderStyle);
		reactify.setCellType(Cell.CELL_TYPE_STRING);

		
		ActualReportUtil reportUtil = new ActualReportUtil();
		Output output = outputServices.getById(outputId);
		List<KeyActivity> keyActivities = keyActivityService.findByOutput(output);
		List<KeyActivity> keyActivities2 = new ArrayList<KeyActivity>();
		Agency agency = agencyService.findByAgencyId(leadAgencyId);
		List<SubActivity> subActivities2 = new ArrayList<SubActivity>();
		for(KeyActivity keyActivity : keyActivities){
			subActivities2 = new ArrayList<SubActivity>();
			List<SubActivity> subActivities = subActivityService.findByKeyActivity(keyActivity);
			for(SubActivity subActivity : subActivities){
				if(subActivity.getLeadAgency().getId().equals(agency.getId())){
					subActivities2.add(subActivity);
				}
			}
			keyActivity.setSubActivities(subActivities2);
			keyActivities2.add(keyActivity);
		}
		List<StatusTracking> statusTrackings = statusTrackingRepository.findAll();
		int n = 1;
		for(KeyActivity keyActivity : keyActivities2){
			headerRow = hssfSheet.createRow(n);
			if(keyActivity.getSubActivities() != null){
				HSSFCell keySeqNumber = headerRow.createCell(0);
				keySeqNumber.setCellValue(keyActivity.getSequenceNumber());
				
				HSSFCell keyActivityName = headerRow.createCell(1);
				keyActivityName.setCellValue(keyActivity.getName());
				
				HSSFCell subActivity = headerRow.createCell(2);
				subActivity.setCellValue("");
				
				HSSFCell status1 = headerRow.createCell(3);
				status1.setCellValue("");
				
				HSSFCell keyProgress1 = headerRow.createCell(4);
				keyProgress1.setCellValue("");
				
				HSSFCell reason = headerRow.createCell(5);
				reason.setCellValue("");
				
				HSSFCell rectify = headerRow.createCell(6);
				rectify.setCellValue("");
			}
			n++;
			if(keyActivity.getSubActivities() != null){
				List<SubActivity> subActivities3 = keyActivity.getSubActivities();
				for(SubActivity subActivity : subActivities3){
					headerRow = hssfSheet.createRow(n);
					
					HSSFCell subSeySeqNumber = headerRow.createCell(0);
					subSeySeqNumber.setCellValue(subActivity.getSequenceNumber());
					
					HSSFCell keyActivityName = headerRow.createCell(1);
					keyActivityName.setCellValue("");
					
					HSSFCell subActivityName = headerRow.createCell(2);
					subActivityName.setCellValue(subActivity.getName());
					
					int temp = 0;
					
					for(StatusTracking statusTracking : statusTrackings){
						if(statusTracking.getSubActivity() != null){
							if(statusTracking.getSubActivity().getId().equals(subActivity.getId())){
								User user = userService.findByUsername(PrincipalUtil.getPrincipal());
								if(user != null && user.getId() != null && user.getUserRoles() != null && user.getUserRoles().size() > 0) {
									for(UserRole userRole : user.getUserRoles()) {
										if(userRole.getName().equals("SUPER_ADMIN") || userRole.getName().equals("PROJECT_ADMIN") || userRole.getName().equals("PROJECT_PLANNER") || userRole.getName().equals("STATUS_APPROVER")) {
											if(statusTracking.getUserLevel() == 1) {
												HSSFCellStyle style=hssfWorkbook.createCellStyle();
												CellStyle statusColor = hssfWorkbook.createCellStyle();
												
												String[] strColor=hex2Rgb(statusTracking.getActualStatusColor()).split(",");
												String hexStr1,hexStr2,hexStr3="";
													hexStr1=Integer.toHexString(Integer.parseInt(strColor[0]));
													hexStr2=Integer.toHexString(Integer.parseInt(strColor[1]));
													hexStr3=Integer.toHexString(Integer.parseInt(strColor[2]));
												
												
												int b1=Integer.decode("0x"+hexStr1);
												int b2=Integer.decode("0x"+hexStr2);
												int b3=Integer.decode("0x"+hexStr3);
												
												
												
												HSSFColor lightGray =  setColor(hssfWorkbook,(byte) b1, (byte) b2,(byte) b3);
												style.setFillBackgroundColor(lightGray.getIndex());
												//style.setFillPattern(CellStyle.SOLID_FOREGROUND);
												
												
												HSSFCell status1 = headerRow.createCell(3);
												status1.setCellValue(statusTracking.getActualStatusColor());
												status1.setCellStyle(style);
												
												HSSFCell keyProgress1 = headerRow.createCell(4);
												keyProgress1.setCellValue(statusTracking.getKeyProgress());
												
												HSSFCell reason = headerRow.createCell(5);
												reason.setCellValue(statusTracking.getReasonForGap());
												
												HSSFCell rectify = headerRow.createCell(6);
												rectify.setCellValue(statusTracking.getRectifyTheGap());
												temp = 1;
											}
										}
										if(userRole.getName().equals("STATUS_REVIEWER") || userRole.getName().equals("STATUS_UPDATER")) {
											if(statusTracking.getUserLevel() == 2) {
												HSSFCell status1 = headerRow.createCell(3);
												status1.setCellValue(statusTracking.getActualStatusColor());
				//								status1.setCellStyle(style);
												
												HSSFCell keyProgress1 = headerRow.createCell(4);
												keyProgress1.setCellValue(statusTracking.getKeyProgress());
												
												HSSFCell reason = headerRow.createCell(5);
												reason.setCellValue(statusTracking.getReasonForGap());
												
												HSSFCell rectify = headerRow.createCell(6);
												rectify.setCellValue(statusTracking.getRectifyTheGap());
												temp = 1;
											}
										}
									}
								}
							}
						}
						
					}
					if(temp == 0){
						HSSFCell status1 = headerRow.createCell(3);
						status1.setCellValue("");
						
						HSSFCell keyProgress1 = headerRow.createCell(4);
						keyProgress1.setCellValue("");
						
						HSSFCell reason = headerRow.createCell(5);
						reason.setCellValue("");
						
						HSSFCell rectify = headerRow.createCell(6);
						rectify.setCellValue("");
					}
					n++;
				}
			}
		}
		
		hssfSheet.autoSizeColumn(0);
		hssfSheet.autoSizeColumn(1);
		hssfSheet.autoSizeColumn(2);
		hssfSheet.autoSizeColumn(3);
		hssfSheet.autoSizeColumn(4);
		hssfSheet.autoSizeColumn(5);
		hssfSheet.autoSizeColumn(6);
		
		try {
			if (hssfWorkbook != null) {
				hssfWorkbook.write(fileOutputStream);
			}
			if (fileOutputStream != null) {
				fileOutputStream.flush();
				fileOutputStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ConstantUtil.REPORT_PATH + fileName;
	}
	
	
	@RequestMapping(value = "getPlanVsActual", method = RequestMethod.GET)
	public @ResponseBody ActualReportUtil getPlanVsActual(ModelMap model,@RequestParam Integer outputId ,@RequestParam Integer leadAgencyId) {
		
		ActualReportUtil reportUtil = new ActualReportUtil();
		Output output = outputServices.getById(outputId);
		List<KeyActivity> keyActivities = keyActivityService.findByOutput(output);
		List<KeyActivity> keyActivities2 = new ArrayList<KeyActivity>();
		Agency agency = agencyService.findByAgencyId(leadAgencyId);
		List<SubActivity> subActivities2 = new ArrayList<SubActivity>();
		List<Planning> plannings = new ArrayList<Planning>();
		List<Planning> listPlanning = new ArrayList<Planning>();
		
		List<Integer> testSubId = new ArrayList<Integer>();
		List<Integer> planId = new ArrayList<Integer>();
		
		for(KeyActivity keyActivity : keyActivities){
			subActivities2 = new ArrayList<SubActivity>();
			listPlanning = new ArrayList<Planning>();
			List<SubActivity> subActivities = subActivityService.findByKeyActivity(keyActivity);
			for(SubActivity subActivity : subActivities){
				if(subActivity.getLeadAgency().getId().equals(agency.getId())){
					testSubId.add(subActivity.getId());
					List<Planning> listPlannings = planningService.getByPlanning(subActivity);
					subActivity.setPlannings(listPlannings);
					subActivities2.add(subActivity);
					listPlanning = planningService.getByPlanning(subActivity);
					for(Planning planning : listPlanning){
						planId.add(planning.getId());
						plannings.add(planning);
					}
					
				}
			}
			keyActivity.setSubActivities(subActivities2);
			keyActivities2.add(keyActivity);
		}
		List<String> years = reportingPeriodService.getYears();
		List<ReportingPeriod> reportingPeriods = reportingPeriodService.getReportingPeriod();
		List<StatusTracking> statusTrackings = statusTrackingRepository.findAll();
		reportUtil.setStatusTrackings(statusTrackings);
		reportUtil.setKeyActivities(keyActivities2);
		reportUtil.setPlannings(plannings);
		reportUtil.setReportingPeriods(reportingPeriods);
		reportUtil.setYears(years);
		return reportUtil;
		
	}
	
	@RequestMapping(value = "getPartnerPlanVsActual", method = RequestMethod.GET)
	public @ResponseBody ActualReportUtil getPartnerPlanVsActual(ModelMap model,@RequestParam Integer outputId ,@RequestParam Integer leadAgencyId) {
		
		ActualReportUtil reportUtil = new ActualReportUtil();
		Output output = outputServices.getById(outputId);
		List<KeyActivity> keyActivities = keyActivityService.findByOutput(output);
		List<KeyActivity> keyActivities2 = new ArrayList<KeyActivity>();
		Agency agency = agencyService.findByAgencyId(leadAgencyId);
		List<SubActivity> subActivities2 = new ArrayList<SubActivity>();
		List<Planning> plannings = new ArrayList<Planning>();
		List<Planning> listPlanning = new ArrayList<Planning>();
		
		List<Integer> testSubId = new ArrayList<Integer>();
		List<Integer> planId = new ArrayList<Integer>();
		
		for(KeyActivity keyActivity : keyActivities){
			subActivities2 = new ArrayList<SubActivity>();
			listPlanning = new ArrayList<Planning>();
			List<SubActivity> subActivities = subActivityService.findByKeyActivity(keyActivity);
			for(SubActivity subActivity : subActivities){
				if(subActivity.getPartnerAgency() != null && subActivity.getPartnerAgency().size() > 0){
					for(Agency partnerAgency : subActivity.getPartnerAgency()) {
						if(partnerAgency.getId().equals(agency.getId())) {
							testSubId.add(subActivity.getId());
							List<Planning> listPlannings = planningService.getByPlanning(subActivity);
							subActivity.setPlannings(listPlannings);
							subActivities2.add(subActivity);
							listPlanning = planningService.getByPlanning(subActivity);
							for(Planning planning : listPlanning){
								planId.add(planning.getId());
								plannings.add(planning);
							}
						}
					}
				}
			}
			keyActivity.setSubActivities(subActivities2);
			keyActivities2.add(keyActivity);
		}
		List<String> years = reportingPeriodService.getYears();
		List<ReportingPeriod> reportingPeriods = reportingPeriodService.getReportingPeriod();
		List<StatusTracking> statusTrackings = statusTrackingRepository.findAll();
		reportUtil.setStatusTrackings(statusTrackings);
		reportUtil.setKeyActivities(keyActivities2);
		reportUtil.setPlannings(plannings);
		reportUtil.setReportingPeriods(reportingPeriods);
		reportUtil.setYears(years);
		return reportUtil;
		
	}
	
	@RequestMapping(value = "planReportDownload" , method = RequestMethod.GET)
	public @ResponseBody String planReportDownload(HttpServletRequest request, HttpServletResponse httpServletResponse , @RequestParam("leadAgencyId") Integer leadAgencyId , @RequestParam("outputId") Integer outputId,HttpServletResponse response){
		String fileName = "Plan vs Actual Report"+  ".xls";
		String contextPath = ConstantUtil.CONTEXT_PATH;
		FileOutputStream fileOutputStream = null;
		File file = null;
		try {
			file = new File(contextPath + fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			fileOutputStream = new FileOutputStream(file);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		HSSFSheet hssfSheet = hssfWorkbook.createSheet(" Plan vs Actual Report Details ");
		HSSFRow headerRow = hssfSheet.createRow(0);

		Font boldFont = hssfWorkbook.createFont();
		boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		boldFont.setColor(IndexedColors.BLACK.getIndex());
		
		CellStyle tableHeaderStyle = hssfWorkbook.createCellStyle();
		tableHeaderStyle.setBorderBottom(CellStyle.BORDER_THIN);
		tableHeaderStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		tableHeaderStyle.setBorderLeft(CellStyle.BORDER_THIN);
		tableHeaderStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		tableHeaderStyle.setBorderRight(CellStyle.BORDER_THIN);
		tableHeaderStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		tableHeaderStyle.setBorderTop(CellStyle.BORDER_THIN);
		tableHeaderStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		tableHeaderStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		tableHeaderStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		tableHeaderStyle.setAlignment(CellStyle.ALIGN_CENTER);
		tableHeaderStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		tableHeaderStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		tableHeaderStyle.setFont(boldFont);
		
		CellStyle tableHeaderStyle1 = hssfWorkbook.createCellStyle();
		Font font = hssfWorkbook.createFont();
		font.setColor(IndexedColors.RED.getIndex());
		tableHeaderStyle1.setFont(font);
		
		HSSFCell seqNumber = headerRow.createCell(0);
		seqNumber.setCellValue("");
		seqNumber.setCellStyle(tableHeaderStyle);
		seqNumber.setCellType(Cell.CELL_TYPE_STRING);

		HSSFCell keyActivitys = headerRow.createCell(1);
		keyActivitys.setCellValue("");
		keyActivitys.setCellStyle(tableHeaderStyle);
		keyActivitys.setCellType(Cell.CELL_TYPE_STRING);

		HSSFCell subActivitys  = headerRow.createCell(2);
		subActivitys.setCellValue("");
		subActivitys.setCellStyle(tableHeaderStyle);
		subActivitys.setCellType(Cell.CELL_TYPE_STRING);
		
		HSSFCell status = headerRow.createCell(3);
		status.setCellValue("");
		status.setCellStyle(tableHeaderStyle);
		status.setCellType(Cell.CELL_TYPE_STRING);
		List<String> years = reportingPeriodService.getYears();
		List<ReportingPeriod> reportingPeriods = reportingPeriodService.getReportingPeriod();
		Output output = outputServices.getById(outputId);
		List<KeyActivity> keyActivities = keyActivityService.findByOutput(output);
		List<KeyActivity> keyActivities2 = new ArrayList<KeyActivity>();
		Agency agency = agencyService.findByAgencyId(leadAgencyId);
		List<SubActivity> subActivities2 = new ArrayList<SubActivity>();
		List<Planning> plannings = new ArrayList<Planning>();
		List<Planning> listPlanning = new ArrayList<Planning>();
		List<Integer> testSubId = new ArrayList<Integer>();
		List<Integer> planId = new ArrayList<Integer>();
		List<StatusTracking> statusTrackings = statusTrackingRepository.findAll();
		
		for(KeyActivity keyActivity : keyActivities){
			subActivities2 = new ArrayList<SubActivity>();
			listPlanning = new ArrayList<Planning>();
			List<SubActivity> subActivities = subActivityService.findByKeyActivity(keyActivity);
			for(SubActivity subActivity : subActivities){
				if(subActivity.getLeadAgency().getId().equals(agency.getId())){
					testSubId.add(subActivity.getId());
					List<Planning> listPlannings = planningService.getByPlanning(subActivity);
					subActivity.setPlannings(listPlannings);
					subActivities2.add(subActivity);
					listPlanning = planningService.getByPlanning(subActivity);
					for(Planning planning : listPlanning){
						planId.add(planning.getId());
						plannings.add(planning);
					}
					
				}
			}
			keyActivity.setSubActivities(subActivities2);
			keyActivities2.add(keyActivity);
		}
		
		int yearCount = 4;
		for(String year : years){
			HSSFCell reportingYear = headerRow.createCell(yearCount);
			reportingYear.setCellValue(year);
			reportingYear.setCellStyle(tableHeaderStyle);
			reportingYear.setCellType(Cell.CELL_TYPE_STRING);
			int yearCountEnd = yearCount + 3;
			hssfSheet.addMergedRegion(new CellRangeAddress(0,0,yearCount,yearCountEnd));
			yearCount = yearCountEnd;
			yearCount++;
		}
		
		headerRow = hssfSheet.createRow(1);
		
		HSSFCell squenceNumber = headerRow.createCell(0);
		squenceNumber.setCellValue("#");
		squenceNumber.setCellStyle(tableHeaderStyle);
		squenceNumber.setCellType(Cell.CELL_TYPE_STRING);

		HSSFCell keyactivitys = headerRow.createCell(1);
		keyactivitys.setCellValue("Key Activities");
		keyactivitys.setCellStyle(tableHeaderStyle);
		keyactivitys.setCellType(Cell.CELL_TYPE_STRING);

		HSSFCell subactivitys  = headerRow.createCell(2);
		subactivitys.setCellValue("Sub Activities");
		subactivitys.setCellStyle(tableHeaderStyle);
		subactivitys.setCellType(Cell.CELL_TYPE_STRING);
		
		HSSFCell mov = headerRow.createCell(3);
		mov.setCellValue("MOV");
		mov.setCellStyle(tableHeaderStyle);
		mov.setCellType(Cell.CELL_TYPE_STRING);
		
		int quterRow = 4;
		for(String year : years){
			for(ReportingPeriod reportingPeriod : reportingPeriods){
				if(year.equalsIgnoreCase(reportingPeriod.getYear())){
					HSSFCell quarter = headerRow.createCell(quterRow);
					quarter.setCellValue(reportingPeriod.getName());
					quarter.setCellStyle(tableHeaderStyle);
					quarter.setCellType(Cell.CELL_TYPE_STRING);
					quterRow++;
				}
			}
		}
		
		int n = 2;
		for(KeyActivity keyActivity : keyActivities2){
			headerRow = hssfSheet.createRow(n);
			if(keyActivity.getSubActivities() != null){
				HSSFCell keySeqNumber = headerRow.createCell(0);
				keySeqNumber.setCellValue(keyActivity.getSequenceNumber());
				
				HSSFCell keyActivityName = headerRow.createCell(1);
				keyActivityName.setCellValue(keyActivity.getName());
				
				int quaterColumn = 4;
				for(String year : years){
					for(ReportingPeriod reportingPeriod : reportingPeriods){
						if(year.equalsIgnoreCase(reportingPeriod.getYear())){
							HSSFCell quarter = headerRow.createCell(quaterColumn);
							quarter.setCellValue("");
							quaterColumn++;
						}
					}
				}
			}
			n++;
			if(keyActivity.getSubActivities() != null){
				List<SubActivity> subActivities3 = keyActivity.getSubActivities();
				for(SubActivity subActivity : subActivities3){
					headerRow = hssfSheet.createRow(n);
					
					HSSFCell subSeySeqNumber = headerRow.createCell(0);
					subSeySeqNumber.setCellValue(subActivity.getSequenceNumber());
					
					HSSFCell keyActivityName = headerRow.createCell(1);
					keyActivityName.setCellValue("");
					
					HSSFCell subActivityName = headerRow.createCell(2);
					subActivityName.setCellValue(subActivity.getName());
					
					HSSFCell movName = headerRow.createCell(3);
					movName.setCellValue(subActivity.getMov());
					
					int temp = 0;
					int quaterColumnCount = 4;
					Integer subActId=subActivity.getId();
					
					for(String year : years){
						List<Planning> plannings2 = subActivity.getPlannings();
						for(Planning planning : plannings2){
							ReportingPeriod reportingPeriod = planning.getReportingPeriod();
							if(year.equalsIgnoreCase(reportingPeriod.getYear())){
								for(int k=1;k<=4;k++){
									String  qValue = "Q"+k;
									if(qValue.equalsIgnoreCase(reportingPeriod.getName())){
										if(planning.getStatusOfProgress()== true){
											for(StatusTracking statusTracking : statusTrackings){
												if(statusTracking.getSubActivity() != null){
													if(statusTracking.getSubActivity().getId().equals(subActId)){
														if(statusTracking.getReportingPeriod().getName().equals("Q"+k)){
															if(statusTracking.getUserLevel().equals(1)){
																HSSFCell qName = headerRow.createCell(quaterColumnCount);
																qName.setCellValue("X("+statusTracking.getActualStatusPercentage()+"%)");
																quaterColumnCount++;
															}
														}/*else{
															HSSFCell qName = headerRow.createCell(quaterColumnCount);
															qName.setCellValue("X");
															quaterColumnCount++;
														}*/
													}else{
														//quaterColumnCount++;
														HSSFCell qName = headerRow.createCell(quaterColumnCount);
														qName.setCellValue("");
														temp = 1;
													}

												}
											}
										}
									}
							 }
						}
					}
				}
					
					n++;
				}
			}
		}
		
		hssfSheet.autoSizeColumn(0);
		hssfSheet.autoSizeColumn(1);
		hssfSheet.autoSizeColumn(2);
		hssfSheet.autoSizeColumn(3);
		hssfSheet.autoSizeColumn(4);
		hssfSheet.autoSizeColumn(5);
		hssfSheet.autoSizeColumn(6);
		
		
		try {
			if (hssfWorkbook != null) {
				hssfWorkbook.write(fileOutputStream);
			}
			if (fileOutputStream != null) {
				fileOutputStream.flush();
				fileOutputStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ConstantUtil.REPORT_PATH + fileName;
	}
	
	public static String hex2Rgb(String colorStr) {
	    Color c = new Color(
	        Integer.valueOf(colorStr.substring(1, 3), 16), 
	        Integer.valueOf(colorStr.substring(3, 5), 16), 
	        Integer.valueOf(colorStr.substring(5, 7), 16));
	    return c.getRed()+","+c.getGreen()+","+c.getBlue();
	}
	
	public HSSFColor setColor(HSSFWorkbook workbook, byte r,byte g, byte b){
		HSSFPalette palette = workbook.getCustomPalette();
		HSSFColor hssfColor = null;
		try {
		hssfColor= palette.findColor(r, g, b); 
		if (hssfColor == null ){
		    palette.setColorAtIndex(HSSFColor.LAVENDER.index, r, g,b);
		    hssfColor = palette.getColor(HSSFColor.LAVENDER.index);
		}
		 } catch (Exception e) {
		}

		 return hssfColor;
		}
	
	public byte[] intToByteArray(int value) {
	    return new byte[] {
	            (byte)(value >>> 24),
	            (byte)(value >>> 16),
	            (byte)(value >>> 8),
	            (byte)value};
	}
}
