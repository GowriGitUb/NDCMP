/**
 * 
 */
package com.ceainfotech.ndcmp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ceainfotech.ndcmp.model.Agency;
import com.ceainfotech.ndcmp.model.KeyActivity;
import com.ceainfotech.ndcmp.model.Outcome;
import com.ceainfotech.ndcmp.model.Output;
import com.ceainfotech.ndcmp.model.Planning;
import com.ceainfotech.ndcmp.model.ReportingPeriod;
import com.ceainfotech.ndcmp.model.StatusTracking;
import com.ceainfotech.ndcmp.model.StrategicPillar;
import com.ceainfotech.ndcmp.model.SubActivity;
import com.ceainfotech.ndcmp.model.SubmitForReview;
import com.ceainfotech.ndcmp.model.Theme;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.model.UserRole;
import com.ceainfotech.ndcmp.model.dto.AgencyDTO;
import com.ceainfotech.ndcmp.model.dto.KeyActivityDTO;
import com.ceainfotech.ndcmp.model.dto.ReviewerDTO;
import com.ceainfotech.ndcmp.model.dto.SubActivityDTO;
import com.ceainfotech.ndcmp.repository.PlanningRepository;
import com.ceainfotech.ndcmp.repository.ReportingPeriodRepository;
import com.ceainfotech.ndcmp.service.AgencyService;
import com.ceainfotech.ndcmp.service.KeyActivityService;
import com.ceainfotech.ndcmp.service.OutputServices;
import com.ceainfotech.ndcmp.service.PlanningServices;
import com.ceainfotech.ndcmp.service.ReportingPeriodService;
import com.ceainfotech.ndcmp.service.StatusTrackingService;
import com.ceainfotech.ndcmp.service.SubActivityService;
import com.ceainfotech.ndcmp.service.SubmitForReviewService;
import com.ceainfotech.ndcmp.service.UserService;
import com.ceainfotech.ndcmp.util.ConstantUtil;
import com.ceainfotech.ndcmp.util.PrincipalUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author pushpa
 *
 */
@Controller
@RequestMapping(value = "/api/**")
public class ApproverController {
	
	@Autowired
	ReportingPeriodService reportingPeriodService;
	
	@Autowired
	UserService userService;

	@Autowired
	AgencyService agencyService;
	
	@Autowired
	SubActivityService subActivityService;
	
	@Autowired
	OutputServices outputServices;
	
	@Autowired
	KeyActivityService keyActivityService;
	
	@Autowired
	StatusTrackingService statusTrackingService;
	
	@Autowired
	SubmitForReviewService submitForReviewService;
	
	@Autowired
	PrincipalUtil principalUtil;
	
	@Autowired
	ReportingPeriodRepository reportingPeriodRepository; 
	
	@Autowired
	PlanningServices planningServices;
	
	@Autowired
	PlanningRepository planningRepository;
	
	/**
	 * Go to approver filter page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "approverFilter", method = RequestMethod.GET)
	public String approverFilter(ModelMap model) {
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.STATUS_APPROVER)){
			List<ReportingPeriod> reportingPeriods = reportingPeriodService.getReportingPeriodListByStatusOpenAndClosed();
			model.addAttribute("reportingPeriods", reportingPeriods);
			model.addAttribute("approverFilter", new ReviewerDTO());
			return "approver/approverFilter";
		}else{
			return "login";
		}
		
	}
	
	/**
	 * Get all strategic pillers based on reporting period selected 
	 * @param model
	 * @param reportingPeriodId
	 * @return
	 */
	@RequestMapping(value = "getApproverStrategicPillerByReportingPeriod", method = RequestMethod.GET)
	public @ResponseBody LinkedHashSet<StrategicPillar> getApproverStrategicPillerByReportingPeriod(ModelMap model,@RequestParam Integer reportingPeriodId, @RequestParam String status) {
		User user = userService.findByUsername(PrincipalUtil.getPrincipal());
		if(user != null){
			ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
			List<UserRole> userRoles =  user.getUserRoles();
			for (UserRole userRole : userRoles) {
				if(userRole.getName().equals("STATUS_APPROVER")){
					LinkedHashSet<StrategicPillar> strategicPillars = new LinkedHashSet<StrategicPillar>();
					if(reportingPeriodId != null){
						List<Integer> reportingPeriodIds = new ArrayList<Integer>();
						List<SubActivity> totalSubActivities =  subActivityService.getSubActivitiesByOpenedReportingPeriod(reportingPeriodId);
						
						List<ReportingPeriod> previousReportingPeriod = reportingPeriodService.getPreviousReportingPeriodByCurrentReportingPeriod(reportingPeriod.getStartdate());
						if(previousReportingPeriod != null && previousReportingPeriod.size() > 0){
							for(ReportingPeriod previousReporting : previousReportingPeriod){
								reportingPeriodIds.add(previousReporting.getId());
							}
							totalSubActivities.addAll(subActivityService.getSubActivitiesByReportingPeriodsAndNotCompleted(reportingPeriodIds));
						}
						
						for (SubActivity subActivity : totalSubActivities) {
							boolean addFlag = false;
							if (status.equals("All")){
								addFlag = true;
							} else {
								List<StatusTracking> paStatusTrackings = statusTrackingService.findBysubActivityAndReportingPeriodAndUserLevelAndSentForReview(subActivity, reportingPeriod, 2, true);
								for (StatusTracking paStatusTracking : paStatusTrackings) {
									if (paStatusTracking.getReviewStatus() != 0 && status.equals("Reviewed")) {
										addFlag = true;
										break;
									} else if (paStatusTracking.getReviewStatus() == 0 && status.equals("NotReviewed")) {
										addFlag = true;
										break;
									}
								}
							} 
							if (addFlag) {
								boolean isAvailable = false;
								for (StrategicPillar pillar : strategicPillars) {
									if (pillar.getId() == subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getStrategicPillar().getId()) {
										isAvailable = true;
										break;
									}
								}
								if (!isAvailable)
									strategicPillars.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getStrategicPillar());
							}
						}
					}
					return strategicPillars;
				}
			}
		}
		return null;
	}
	
	/**
	 * Load all Themes based on the strategic piller 
	 * @param model
	 * @param reportingPeriodId
	 * @param strategicId
	 * @return
	 */
	@RequestMapping(value = "getApproverThemesByStrategicPiller", method = RequestMethod.GET)
	public @ResponseBody LinkedHashSet<Theme> getApproverThemesByStrategicPiller(ModelMap model,@RequestParam Integer reportingPeriodId, @RequestParam Integer strategicId, @RequestParam String status) {
		User user = userService.findByUsername(PrincipalUtil.getPrincipal());
		if(user != null){
			List<UserRole> userRoles =  user.getUserRoles();
			ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
			for (UserRole userRole : userRoles) {
				if(userRole.getName().equals("STATUS_APPROVER")){
					LinkedHashSet<Theme> themes = new LinkedHashSet<Theme>();
					List<SubActivity> totalSubActivities = new ArrayList<SubActivity>();
					if(reportingPeriodId != null && strategicId != null){
						totalSubActivities.addAll(subActivityService.getSubActivitiesByStrategicPillarAndOpenedReportingPeriod(strategicId, reportingPeriodId));
						
						List<Integer> previousReportingPeriodIds = new ArrayList<Integer>();
						List<ReportingPeriod> previousReportingPeriod = reportingPeriodService.getPreviousReportingPeriodByCurrentReportingPeriod(reportingPeriod.getStartdate());
						if(previousReportingPeriod != null && previousReportingPeriod.size() > 0){
							for(ReportingPeriod previousReporting : previousReportingPeriod){
								previousReportingPeriodIds.add(previousReporting.getId());
							}
							totalSubActivities.addAll(subActivityService.getSubActivitiesByStrategicPillerAndReportingPeriodsNotCompleted(strategicId, previousReportingPeriodIds));
						}
							
						for (SubActivity subActivity : totalSubActivities) {
							boolean addFlag = false;
							if (status.equals("All")){
								addFlag = true;
							} else {
								List<StatusTracking> paStatusTrackings = statusTrackingService.findBysubActivityAndReportingPeriodAndUserLevelAndSentForReview(subActivity, reportingPeriod, 2, true);
								for (StatusTracking paStatusTracking : paStatusTrackings) {
									if (paStatusTracking.getReviewStatus() != 0 && status.equals("Reviewed")) {
										addFlag = true;
										break;
									} else if (paStatusTracking.getReviewStatus() == 0 && status.equals("NotReviewed")) {
										addFlag = true;
										break;
									}
								}
							} 
							if (addFlag) {
								boolean isAvailable = false;
								for (Theme theme : themes) {
									if (theme.getId() == subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getId()) {
										isAvailable = true;
										break;
									}
								}
								if (!isAvailable)
									themes.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme());
							}
						}
					}
					return themes;
				}
			}
		}
		return null;
	}
	
	/**
	 * Load all outcomes based on the theme selected
	 * @param model
	 * @param reportingPeriodId
	 * @param themeId
	 * @return
	 */
	@RequestMapping(value = "getApproverOutcomesByThemes", method = RequestMethod.GET)
	public @ResponseBody LinkedHashSet<Outcome> getApproverOutcomesByThemes(ModelMap model,@RequestParam Integer reportingPeriodId, @RequestParam Integer themeId, @RequestParam String status) {
		User user = userService.findByUsername(PrincipalUtil.getPrincipal());
		if(user != null){
			List<UserRole> userRoles =  user.getUserRoles();
			for (UserRole userRole : userRoles) {
				if(userRole.getName().equals("STATUS_APPROVER")){
					ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
					LinkedHashSet<Outcome> outcomes = new LinkedHashSet<Outcome>();
					List<SubActivity> totalSubActivities = subActivityService.getSubActivitiesByThemeAndOpenedReportingPeriod(themeId, reportingPeriodId);
					if(reportingPeriod != null && themeId != null){
						List<Integer> reportingPeriodIds = new ArrayList<Integer>();
						List<ReportingPeriod> previousReportingPeriod = reportingPeriodService.getPreviousReportingPeriodByCurrentReportingPeriod(reportingPeriod.getStartdate());
						if(previousReportingPeriod != null && previousReportingPeriod.size() > 0){
							for(ReportingPeriod previousReporting : previousReportingPeriod){
								reportingPeriodIds.add(previousReporting.getId());
							}
							totalSubActivities.addAll(subActivityService.getSubActivitiesByThemeAndReportingPeriodsNotCompleted(themeId, reportingPeriodIds));
						}
						
						for (SubActivity subActivity : totalSubActivities) {
							boolean addFlag = false;
							if (status.equals("All")){
								addFlag = true;
							} else {
								List<StatusTracking> paStatusTrackings = statusTrackingService.findBysubActivityAndReportingPeriodAndUserLevelAndSentForReview(subActivity, reportingPeriod, 2, true);
								for (StatusTracking paStatusTracking : paStatusTrackings) {
									if (paStatusTracking.getReviewStatus() != 0 && status.equals("Reviewed")) {
										addFlag = true;
										break;
									} else if (paStatusTracking.getReviewStatus() == 0 && status.equals("NotReviewed")) {
										addFlag = true;
										break;
									}
								}
							}
							if (addFlag) {
								boolean isAvailable = false;
								for (Outcome outcome : outcomes) {
									if (outcome.getId() == subActivity.getKeyActivity().getOutput().getOutcome().getId()) {
										isAvailable = true;
										break;
									}
								}
								if (!isAvailable)
									outcomes.add(subActivity.getKeyActivity().getOutput().getOutcome());
							}
						}
					}
					return outcomes;
				}
			}
		}
		return null;
	}
	
	/**
	 * Load all outputs based on the outcome selected 
	 * @param model
	 * @param reportingPeriodId
	 * @param outcomeId
	 * @return
	 */
	@RequestMapping(value = "getApproverOutputsByOutcome", method = RequestMethod.GET)
	public @ResponseBody LinkedHashSet<Output> getApproverOutputsByOutcome(ModelMap model,@RequestParam Integer reportingPeriodId, @RequestParam Integer outcomeId, @RequestParam String status) {
		User user = userService.findByUsername(PrincipalUtil.getPrincipal());
		if(user != null){
			List<UserRole> userRoles =  user.getUserRoles();
			for (UserRole userRole : userRoles) {
				if(userRole.getName().equals("STATUS_APPROVER")){
					LinkedHashSet<Output> outputs = new LinkedHashSet<Output>();
					if(reportingPeriodId != null && outcomeId != null){
						List<SubActivity> totalSubActivities = subActivityService.getSubActivitiesByOutcomeAndOpenedReportingPeriod(outcomeId, reportingPeriodId);
						ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
						List<Integer> reportingPeriodIds = new ArrayList<Integer>();
						List<ReportingPeriod> previousReportingPeriod = reportingPeriodService.getPreviousReportingPeriodByCurrentReportingPeriod(reportingPeriod.getStartdate());
						if(previousReportingPeriod != null && previousReportingPeriod.size() > 0){
							for(ReportingPeriod previousReporting : previousReportingPeriod){
								reportingPeriodIds.add(previousReporting.getId());
							}
							totalSubActivities.addAll(subActivityService.getSubActivitiesByOutcomeAndReportingPeriodsNotCompleted(outcomeId, reportingPeriodIds));
						}
						for (SubActivity subActivity : totalSubActivities) {
							boolean addFlag = false;
							if (status.equals("All")){
								addFlag = true;
							} else {
								List<StatusTracking> paStatusTrackings = statusTrackingService.findBysubActivityAndReportingPeriodAndUserLevelAndSentForReview(subActivity, reportingPeriod, 2, true);
								for (StatusTracking paStatusTracking : paStatusTrackings) {
									if (paStatusTracking.getReviewStatus() != 0 && status.equals("Reviewed")) {
										addFlag = true;
										break;
									} else if (paStatusTracking.getReviewStatus() == 0 && status.equals("NotReviewed")) {
										addFlag = true;
										break;
									}
								}
							}
							if (addFlag) {
								boolean isAvailable = false;
								for (Output output : outputs) {
									if (output.getId() == subActivity.getKeyActivity().getOutput().getId()) {
										isAvailable = true;
										break;
									}
								}
								if (!isAvailable)
									outputs.add(subActivity.getKeyActivity().getOutput());
							}
						}
					}
					return outputs;
				}
			}
		}
		return null;
	}
	
	/**
	 * Load approver accordion data
	 * @param reviewerFilter
	 * @param statusId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "loadApproverData", method = RequestMethod.GET)
	public @ResponseBody List<KeyActivityDTO> loadApproverData(@RequestParam("reviewerFilter") String reviewerFilter,
			@RequestParam("statusId") String statusId,Model model){
		Gson gson=new GsonBuilder().serializeNulls().create();
		ReviewerDTO addReviewer = gson.fromJson(reviewerFilter, ReviewerDTO.class);
		List<KeyActivityDTO> keyActivityDTOs = new ArrayList<KeyActivityDTO>();
		List<SubActivityDTO> subActivityDTOs = new ArrayList<SubActivityDTO>();
		List<KeyActivity> keyActivities = new ArrayList<KeyActivity>();
		
		Integer reportingPeriodId = addReviewer.getReportingperiodId();
		ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
		Integer outputId = Integer.parseInt( addReviewer.getOutput());
		Output output = outputServices.getById(outputId);
		
		keyActivities = keyActivityService.findByOutput(output);
		for (KeyActivity keyActivity : keyActivities) {
			List<SubActivity> totalSubActivities = subActivityService.getSubActivitiesByKeyActivityAndOpenedReportingPeriod(keyActivity, reportingPeriodId);
			subActivityDTOs = new ArrayList<SubActivityDTO>();
			List<Integer> reportingPeriodIds = new ArrayList<Integer>();
			List<ReportingPeriod> previousReportingPeriod = reportingPeriodService.getPreviousReportingPeriodByCurrentReportingPeriod(reportingPeriod.getStartdate());
			if(previousReportingPeriod != null && previousReportingPeriod.size() > 0){
				for(ReportingPeriod previousReporting : previousReportingPeriod){
					reportingPeriodIds.add(previousReporting.getId());
				}
				totalSubActivities.addAll(subActivityService.getSubActivitiesByKeyActivityAndReportingPeriodsNotCompleted(keyActivity, reportingPeriodIds));
			}
			
			List<AgencyDTO> agencyDTOs = new ArrayList<AgencyDTO>();
			
			if (totalSubActivities.size() > 0) {
				for (SubActivity subActivity : totalSubActivities) {
					
					Planning planning = planningRepository.findBySubActivityAndReportingPeriod(subActivity,reportingPeriod);
					if(planning == null){
						subActivity.setCarryOverStatus(true);
					}
					
					if(subActivity.getId() != null){
						agencyDTOs = new ArrayList<AgencyDTO>();
						Agency agency = subActivity.getLeadAgency();
						boolean addFlag = false;
						if(agency != null){
							AgencyDTO agencyDTO = new AgencyDTO();
							agencyDTO.setId(agency.getId());
							agencyDTO.setAgency(agency.getName());
							agencyDTO.setStatus(agency.getStatus());
							
							List<SubmitForReview> submitForReviews = submitForReviewService.findByAgencyAndReportingPeriod(agency, reportingPeriod);
							if(submitForReviews != null && submitForReviews.size() > 0){
								agencyDTO.setUserId(submitForReviews.get(0).getUser().getId());
								agencyDTO.setUserName(submitForReviews.get(0).getUser().getUsername());
								StatusTracking paStatusTracking = statusTrackingService.findByAgencyAndSubActivityAndReportingPeriodAndUserLevel(submitForReviews.get(0).getAgency(), subActivity, reportingPeriod, 2);
								if(paStatusTracking != null){
									
									if (paStatusTracking.getReviewStatus() == 1) {
										agencyDTO.setStatusIcon("fa fa-check-circle-o");
									} else if (paStatusTracking.getReviewStatus() == -1) {
										agencyDTO.setStatusIcon("fa fa-info-circle");
									}
									
									if (statusId.equals("Reviewed") && paStatusTracking.getReviewStatus() != 0) {
										agencyDTO.setColorCode(paStatusTracking.getActualStatusColor());
										agencyDTOs.add(agencyDTO);
										addFlag = true;
									} else if (statusId.equals("NotReviewed") && paStatusTracking.getReviewStatus() == 0) {
										agencyDTO.setColorCode(paStatusTracking.getActualStatusColor());
										agencyDTOs.add(agencyDTO);
										addFlag = true;
									} else if (statusId.equals("All")){
										agencyDTO.setColorCode(paStatusTracking.getActualStatusColor());
										agencyDTOs.add(agencyDTO);
										addFlag = true;
									}
								}
							} else if (statusId.equals("All")) {
								addFlag = true;
							}
							if (addFlag) {
								boolean isAvailable = false;
								for (SubActivityDTO subActivityDTO : subActivityDTOs) {
									if (subActivityDTO.getId() == subActivity.getId()) {
										isAvailable = true;
										break;
									}
								}
								if (!isAvailable)
									subActivityDTOs.add(new SubActivityDTO(subActivity.getId(), subActivity.getName(), new LinkedHashSet<AgencyDTO>(agencyDTOs), subActivity.getColorCode(),subActivity.getSequenceNumber(),null,subActivity.isCarryOverStatus(),subActivity.isApproveORCompleteStatus()));
							}
						}
					}
				}
			}
			if(subActivityDTOs.size() > 0){
				keyActivityDTOs.add(new KeyActivityDTO(keyActivity.getId(),
						keyActivity.getSequenceNumber(), keyActivity.getName(),
						keyActivity.getStatus(), subActivityDTOs));
			}
		}
		return keyActivityDTOs;
	}
	
	/**
	 * purpose : set approved for all the status tracking
	 */
	@RequestMapping(value = "approveAll" , method = RequestMethod.GET)
	public @ResponseBody boolean approveAll(@RequestParam("reviewerFilter") String reviewerFilter,
			@RequestParam("statusId") String statusId,Model model){
		Gson gson=new GsonBuilder().serializeNulls().create();
		ReviewerDTO addReviewer = gson.fromJson(reviewerFilter, ReviewerDTO.class);
		Integer reportingPeriodId = addReviewer.getReportingperiodId();
		ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
		
		List<KeyActivityDTO> keyActivityDTOs =  setKeyActivityDTOs(reviewerFilter,statusId);
		boolean result = false;
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String formattedDate = dateformat.format(new Date());
		User currentUser = userService.findByUsername(PrincipalUtil.getPrincipal());
		for(KeyActivityDTO activityDTO: keyActivityDTOs){
			for(SubActivityDTO subActivityDTO : activityDTO.getSubActivities()){
				SubActivity subActivity=subActivityService.getById(subActivityDTO.getId());
				for(AgencyDTO agencyDTO : subActivityDTO.getAgencyDTOs()){
					User partnerAgencyUser = userService.getById(agencyDTO.getUserId());
					Agency partnerAgency = agencyService.findByLoginUser(partnerAgencyUser.getUsername());
					StatusTracking partnerAgencyStatusTracking = statusTrackingService.findByAgencyAndSubActivityAndReportingPeriodAndUserLevel(partnerAgency, subActivity, reportingPeriod, 2);
					if (partnerAgencyStatusTracking != null){
						if(partnerAgencyStatusTracking.getReviewStatus() == 0 ){
							partnerAgencyStatusTracking.setReviewedBy(currentUser);
							partnerAgencyStatusTracking.setReviewedOn(formattedDate);
							partnerAgencyStatusTracking.setReviewStatus(1);
							partnerAgencyStatusTracking.setReviewerFeedback(partnerAgencyStatusTracking.getReviewerFeedback());
							statusTrackingService.add(partnerAgencyStatusTracking);
							result = true;
						}
					}
				}
			}
		}
//		List<KeyActivityDTO> keyActivityDTOs1 =  setKeyActivityDTOs(reviewerFilter,statusId);
		return result;
	}
	
	/**
	 * purpose : To get approver data
	 * @param reviewerFilter
	 * @param statusId
	 * @return
	 */
	private List<KeyActivityDTO> setKeyActivityDTOs(String reviewerFilter, String statusId) {
		
		Gson gson=new GsonBuilder().serializeNulls().create();
		ReviewerDTO addReviewer = gson.fromJson(reviewerFilter, ReviewerDTO.class);
		List<KeyActivityDTO> keyActivityDTOs = new ArrayList<KeyActivityDTO>();
		List<SubActivityDTO> subActivityDTOs = new ArrayList<SubActivityDTO>();
		List<KeyActivity> keyActivities = new ArrayList<KeyActivity>();
		
		Integer reportingPeriodId = addReviewer.getReportingperiodId();
		ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
		Integer outputId = Integer.parseInt( addReviewer.getOutput());
		Output output = outputServices.getById(outputId);
		
		keyActivities = keyActivityService.findByOutput(output);
		for (KeyActivity keyActivity : keyActivities) {
			subActivityDTOs = new ArrayList<SubActivityDTO>();
			List<SubActivity> totalSubActivities = subActivityService.getSubActivitiesByKeyActivityAndOpenedReportingPeriod(keyActivity, reportingPeriodId);
			subActivityDTOs = new ArrayList<SubActivityDTO>();
			List<Integer> reportingPeriodIds = new ArrayList<Integer>();
			List<ReportingPeriod> previousReportingPeriod = reportingPeriodService.getPreviousReportingPeriodByCurrentReportingPeriod(reportingPeriod.getStartdate());
			if(previousReportingPeriod != null && previousReportingPeriod.size() > 0){
				for(ReportingPeriod previousReporting : previousReportingPeriod){
					reportingPeriodIds.add(previousReporting.getId());
				}
				totalSubActivities.addAll(subActivityService.getSubActivitiesByKeyActivityAndReportingPeriodsNotCompleted(keyActivity, reportingPeriodIds));
			}
			List<AgencyDTO> agencyDTOs = new ArrayList<AgencyDTO>();
			
			if (totalSubActivities.size() > 0) {
				for (SubActivity subActivity : totalSubActivities) {

					if(subActivity.getId() != null){
						agencyDTOs = new ArrayList<AgencyDTO>();
						Agency agency = subActivity.getLeadAgency();
						boolean addFlag = false;
						if(agency != null){
							AgencyDTO agencyDTO = new AgencyDTO();
							agencyDTO.setId(agency.getId());
							agencyDTO.setAgency(agency.getName());
							agencyDTO.setStatus(agency.getStatus());
							List<SubmitForReview> submitForReviews = submitForReviewService.findByAgencyAndReportingPeriod(agency, reportingPeriod);
							if(submitForReviews != null && submitForReviews.size() > 0){
								agencyDTO.setUserId(submitForReviews.get(0).getUser().getId());
								agencyDTO.setUserName(submitForReviews.get(0).getUser().getUsername());
								StatusTracking paStatusTracking = statusTrackingService.findByAgencyAndSubActivityAndReportingPeriodAndUserLevel(submitForReviews.get(0).getAgency(), subActivity, reportingPeriod, 2);
								if(paStatusTracking != null){
									
									if (paStatusTracking.getReviewStatus() == 1) {
										agencyDTO.setStatusIcon("fa fa-check-circle-o");
									} else if (paStatusTracking.getReviewStatus() == -1) {
										agencyDTO.setStatusIcon("fa fa-info-circle");
									}
									
									if (statusId.equals("Reviewed") && paStatusTracking.getReviewStatus() != 0) {
										agencyDTO.setColorCode(paStatusTracking.getActualStatusColor());
										agencyDTOs.add(agencyDTO);
										addFlag = true;
									} else if (statusId.equals("NotReviewed") && paStatusTracking.getReviewStatus() == 0) {
										agencyDTO.setColorCode(paStatusTracking.getActualStatusColor());
										agencyDTOs.add(agencyDTO);
										addFlag = true;
									} else if (statusId.equals("All")){
										agencyDTO.setColorCode(paStatusTracking.getActualStatusColor());
										agencyDTOs.add(agencyDTO);
										addFlag = true;
									}
								}
							}
							if (addFlag) {
								boolean isAvailable = false;
								for (SubActivityDTO subActivityDTO : subActivityDTOs) {
									if (subActivityDTO.getId() == subActivity.getId()) {
										isAvailable = true;
										break;
									}
								}
								if (!isAvailable)
									subActivityDTOs.add(new SubActivityDTO(subActivity.getId(), subActivity.getName(), new LinkedHashSet<AgencyDTO>(agencyDTOs), subActivity.getColorCode(),subActivity.getSequenceNumber(),null));
							}
						}
					}
				}
			}
			if(subActivityDTOs.size() > 0){
				keyActivityDTOs.add(new KeyActivityDTO(keyActivity.getId(),
						keyActivity.getSequenceNumber(), keyActivity.getName(),
						keyActivity.getStatus(), subActivityDTOs));
			}
		}
		return keyActivityDTOs;
		
	}

	/**
	 * Get reviewer status tracking details for the approver
	 * @param model
	 * @param userId
	 * @param subId
	 * @param reportingPeriodId
	 * @return
	 */
	@RequestMapping(value = "getApproverStatusTrackingDetails" , method = RequestMethod.GET)
	public @ResponseBody StatusTracking getStatusTrackingDetails(ModelMap model,@RequestParam Integer userId,@RequestParam Integer subId, @RequestParam Integer reportingPeriodId ){
		if(subId != null){
			SubActivity subActivity = subActivityService.getById(subId);
			User user = userService.getById(userId);
			Agency agency = agencyService.findByLoginUser(user.getUsername());
			ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
			if(subActivity != null && user != null){
				StatusTracking statusTracking = statusTrackingService.findByAgencyAndSubActivityAndReportingPeriodAndUserLevel(agency, subActivity, reportingPeriod, 2);
				if(statusTracking != null){
					return statusTracking;
				}else{
					return new StatusTracking();
				}
			}
		}
		return null;
	}
	
	/**
	 * Save approver status tracking details
	 * @param subActivityId
	 * @param reviewerUserId
	 * @param reportingPeriodId
	 * @param reviewStatus
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveApproverStatusTrackerDetails" , method = RequestMethod.GET)
	public @ResponseBody StatusTracking saveApproverStatusTrackerDetails(@RequestParam("subActivityId") Integer subActivityId,@RequestParam("userId") Integer reviewerUserId,@RequestParam("reportingPeriodId") 
		Integer reportingPeriodId, @RequestParam("reviewStatus")Integer reviewStatus, @RequestParam("approverFeedback") String approverFeedback) throws Exception{
		SubActivity subActivity=subActivityService.getById(subActivityId);
		User partnerAgencyUser = userService.getById(reviewerUserId);
		Agency agency = agencyService.findByLoginUser(partnerAgencyUser.getUsername());
		ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
		StatusTracking partnerAgencyStatusTracking = statusTrackingService.findByAgencyAndSubActivityAndReportingPeriodAndUserLevel(agency, subActivity, reportingPeriod, 2);
		if (partnerAgencyStatusTracking != null){
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			User currentUser = userService.findByUsername(PrincipalUtil.getPrincipal());
			partnerAgencyStatusTracking.setReviewedBy(currentUser);
			partnerAgencyStatusTracking.setReviewedOn(dateformat.format(new Date()));
			partnerAgencyStatusTracking.setReviewStatus(reviewStatus);
			partnerAgencyStatusTracking.setReviewerFeedback(approverFeedback);
			statusTrackingService.add(partnerAgencyStatusTracking);
		}
		return new StatusTracking();
	}
	
	
	/**
	 * purpose : to set complete and approve data
	 * 
	 */
	@RequestMapping(value = "saveApproverCompletedStatusTrackerDetails" , method = RequestMethod.GET)
	public @ResponseBody StatusTracking saveApproverCompletedStatusTrackerDetails(@RequestParam("subActivityId") Integer subActivityId,
			@RequestParam("userId") Integer reviewerUserId,	@RequestParam("reportingPeriodId") Integer reportingPeriodId,
			@RequestParam("reviewStatus")Integer reviewStatus, @RequestParam("approverFeedback") String approverFeedback,@RequestParam("selectRange") Integer selectRange) throws Exception{
		SubActivity subActivity=subActivityService.getById(subActivityId);
		User partnerAgencyUser = userService.getById(reviewerUserId);
		Agency agency = agencyService.findByLoginUser(partnerAgencyUser.getUsername());
		ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
		List<Planning> plannings = planningServices.getByPlanning(subActivity);
		List<ReportingPeriod> listPeriod = new ArrayList<ReportingPeriod>();
		if(selectRange == 1){
			for(Planning planning : plannings){
				//if(planning.getReportingPeriod().getReportingStatus().equals("Not-Started") && reportingPeriod.getStartdate().before(planning.getReportingPeriod().getStartdate())){
				if(reportingPeriod.getStartdate().before(planning.getReportingPeriod().getStartdate())){
					listPeriod.add(planning.getReportingPeriod());
				}
			}
			
			if(listPeriod.size() > 0){
				StatusTracking statusTracking = new StatusTracking();
				statusTracking.setErrorStatus(true);
				return statusTracking;
			}
		}
		
		//List<ReportingPeriod> list = reportingPeriodRepository.getNextReportingPeriodByCurrentReportingPeriod(reportingPeriod.getStartdate());
		//System.out.println(list.size());
		StatusTracking partnerAgencyStatusTracking = statusTrackingService.findByAgencyAndSubActivityAndReportingPeriodAndUserLevel(agency, subActivity, reportingPeriod, 2);
		if (partnerAgencyStatusTracking != null){
			//set value true in status tracking level
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			User currentUser = userService.findByUsername(PrincipalUtil.getPrincipal());
			partnerAgencyStatusTracking.setReviewedBy(currentUser);
			partnerAgencyStatusTracking.setReviewedOn(dateformat.format(new Date()));
			partnerAgencyStatusTracking.setReviewStatus(reviewStatus);
			partnerAgencyStatusTracking.setReviewerFeedback(approverFeedback);
			statusTrackingService.add(partnerAgencyStatusTracking);
			
			//set value true in sub activity level
			subActivity.setApproveORCompleteStatus(true);
			subActivity.setCompletedReportingPeriod(partnerAgencyStatusTracking.getReportingPeriod());
			subActivityService.addSubActivity(subActivity);
		}
		return partnerAgencyStatusTracking;
	}
	
	
	/**
	 * Confirm reviewer data for rework by approver
	 * @param reportingPeriodId
	 * @return
	 */
	@RequestMapping(value = "confirmApproverReworkSubmition", method = RequestMethod.POST)
	public @ResponseBody Integer SubmitForRework(@RequestParam("reportingPeriodId") Integer reportingPeriodId){
		if (reportingPeriodId != null){
			User user = userService.findByUsername(PrincipalUtil.getPrincipal());
			ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
			List<StatusTracking> liststaStatusTrackings = statusTrackingService.findByReportingPeriodAndReviewerAndReviewStatus(reportingPeriod, user);
			if(liststaStatusTrackings != null && liststaStatusTrackings.size() > 0){
				for (StatusTracking statusTracking : liststaStatusTrackings) {
					statusTracking.setComplete(false);
					statusTracking.setReworkRequired(true);
					SubmitForReview submitForReview = submitForReviewService.findByAgencyAndReportingPeriodAndUserLevel(statusTracking.getAgency(), reportingPeriod, 2);
					if (submitForReview != null){
						submitForReview.setLeadReworkStatus(true);
						submitForReview.setPartnerReworkStatus(false);
						statusTrackingService.add(statusTracking);
						submitForReviewService.add(submitForReview);
					}
				}
				return 1;
			}
		}
		return 0;
	}
}
