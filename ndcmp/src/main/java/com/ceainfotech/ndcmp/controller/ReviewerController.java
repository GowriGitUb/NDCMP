/**
 * 
 */
package com.ceainfotech.ndcmp.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.ceainfotech.ndcmp.model.Agency;
import com.ceainfotech.ndcmp.model.KeyActivity;
import com.ceainfotech.ndcmp.model.Outcome;
import com.ceainfotech.ndcmp.model.Output;
import com.ceainfotech.ndcmp.model.Planning;
import com.ceainfotech.ndcmp.model.ReportingPeriod;
import com.ceainfotech.ndcmp.model.StatusTracking;
import com.ceainfotech.ndcmp.model.StatusTrackingLog;
import com.ceainfotech.ndcmp.model.Statuss;
import com.ceainfotech.ndcmp.model.StrategicPillar;
import com.ceainfotech.ndcmp.model.SubActivity;
import com.ceainfotech.ndcmp.model.SubmitForReview;
import com.ceainfotech.ndcmp.model.Theme;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.model.UserRole;
import com.ceainfotech.ndcmp.model.dto.AgencyDTO;
import com.ceainfotech.ndcmp.model.dto.FilterHierarchy;
import com.ceainfotech.ndcmp.model.dto.KeyActivityDTO;
import com.ceainfotech.ndcmp.model.dto.ReviewerDTO;
import com.ceainfotech.ndcmp.model.dto.ReworkSummaryDTO;
import com.ceainfotech.ndcmp.model.dto.SubActivityDTO;
import com.ceainfotech.ndcmp.repository.PlanningRepository;
import com.ceainfotech.ndcmp.service.AgencyService;
import com.ceainfotech.ndcmp.service.KeyActivityService;
import com.ceainfotech.ndcmp.service.OutputServices;
import com.ceainfotech.ndcmp.service.PlanningServices;
import com.ceainfotech.ndcmp.service.ReportingPeriodService;
import com.ceainfotech.ndcmp.service.StatusService;
import com.ceainfotech.ndcmp.service.StatusTrackingLogService;
import com.ceainfotech.ndcmp.service.StatusTrackingService;
import com.ceainfotech.ndcmp.service.StrategicPillarService;
import com.ceainfotech.ndcmp.service.SubActivityService;
import com.ceainfotech.ndcmp.service.SubmitForReviewService;
import com.ceainfotech.ndcmp.service.UserService;
import com.ceainfotech.ndcmp.util.ConstantUtil;
import com.ceainfotech.ndcmp.util.PrincipalUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author jeeva
 *
 */
@Controller
@RequestMapping(value = "/api/**")
public class ReviewerController {
	
	@Autowired
	ReportingPeriodService reportingPeriodService;
	
	@Autowired
	private StatusService statusService;
	
	@Autowired
	private StrategicPillarService strategicPillarService;
	
	@Autowired
	private PlanningServices planningServices;
	
	@Autowired
	private SubActivityService subActivityService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private StatusTrackingService statusTrackingService;
	
	@Autowired
	StatusTrackingLogService statusTrackingLogService;
	
	@Autowired
	AgencyService agencyService;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	private ConstantUtil constantUtil;
	
	@Autowired
	private KeyActivityService keyActivityService;
	
	@Autowired
	private OutputServices outputServices; 
	
	@Autowired
	private SubmitForReviewService submitForReviewService;
	
	@Autowired
	PrincipalUtil principalUtil;
	
	@Autowired
	PlanningRepository planningRepository;
	
	String action,module="";
	
	
	/**
	 * Navigate to the reviewer page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "listReviewers", method = RequestMethod.GET)
	public String listReviewers(ModelMap model) {
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.STATUS_REVIEWER_ROLE)){
			List<ReportingPeriod> reportingPeriods = reportingPeriodService.getReportingPeriodListByStatusOpen();
			model.addAttribute("reportingPeriods", reportingPeriods);
			model.addAttribute("addReviewer", new ReviewerDTO());
			return "reviewer/addReviewer";
		}else{
			return "login";
		}
		
	}
	
	/**
	 * Get all the Strategic Pillars 
	 * @param yearId,quaterId
	 * @return
	 */

	@RequestMapping(value = "getStrategicPillarByQuarter", method = RequestMethod.GET)
	public @ResponseBody LinkedHashSet<StrategicPillar> getStrategicPillarByQuarter(ModelMap model,@RequestParam Integer reportingPeriodId, @RequestParam String status) {
		User user = userService.findByUsername(PrincipalUtil.getPrincipal());
		if(user != null){
			Agency agency = agencyService.findByLoginUser(user.getUsername());
			if(agency != null){
				List<UserRole> userRoles =  user.getUserRoles();
				for (UserRole userRole : userRoles) {
					if(userRole.getName().equals("STATUS_REVIEWER")){
						ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
						LinkedHashSet<StrategicPillar> strategicPillars = new LinkedHashSet<StrategicPillar>();
						if(reportingPeriodId != null){
							List<Integer> reportingPeriodIds = new ArrayList<Integer>();
							List<ReportingPeriod> previousReportingPeriod = reportingPeriodService.getPreviousReportingPeriodByCurrentReportingPeriod(reportingPeriod.getStartdate());
							if(previousReportingPeriod != null && previousReportingPeriod.size() > 0){
								for(ReportingPeriod previousReporting : previousReportingPeriod ){
											reportingPeriodIds.add(previousReporting.getId());
										}
							}
							reportingPeriodIds.add(reportingPeriodId);
							List<SubActivity> subActivities = subActivityService.getSubActivitiesByReportingPeriodsAndLeadAgency(reportingPeriodIds, agency.getId());
							for (SubActivity subActivity : subActivities) {
								boolean addFlag = false;
								if (status.equals("All")){
									addFlag = true;
								} else if (status.equals("Reviewed") || status.equals("NotReviewed")) {
									List<StatusTracking> paStatusTrackings = statusTrackingService.findBysubActivityAndReportingPeriodAndUserLevel(subActivity, reportingPeriod, 1);
									for (StatusTracking paStatusTracking : paStatusTrackings) {
										if (paStatusTracking.getReviewStatus() != 0 && status.equals("Reviewed")) {
											addFlag = true;
											break;
										} else if (paStatusTracking.getReviewStatus() == 0 && status.equals("NotReviewed")) {
											addFlag = true;
											break;
										}
									}
								} else {
									StatusTracking statusTracking = statusTrackingService.findByAgencyAndSubActivityAndReportingPeriodAndUserLevel(agency, subActivity, reportingPeriod, 2);
									if (statusTracking != null){
										if (status.equals("ReadyForApproval")){
											if (statusTracking.isComplete() && (statusTracking.getReviewStatus() == 0 || statusTracking.getReviewStatus() == -1)){
												addFlag = true;
											}
										}else if(status.equals("NotReadyForApproval")){
											if (statusTracking == null || !statusTracking.isComplete()){
												addFlag = true;
											}
										}else if(status.equals("SentForApproval")){
											if (statusTracking.isComplete() && !statusTracking.isReworkRequired()){
												addFlag = true;
											}
										}else if(status.equals("NeedMoreInfo")){
											if (statusTracking.isReworkRequired()){
												addFlag = true;
											}
										}
									} else if(status.equals("NotReadyForApproval")){
										addFlag = true;
									}
								}
								if(addFlag){
									strategicPillars.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getStrategicPillar());
								}
							}
						}
						return strategicPillars;
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * @param projectId
	 * @param reviewerFilter
	 * @param statusId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "getReviewerDetails", method = RequestMethod.GET)
	public @ResponseBody List<KeyActivityDTO> loadReviewers(@RequestParam("projectId") Integer projectId,
			@RequestParam("reviewerFilter") String reviewerFilter,@RequestParam("statusId") String statusId,Model model){
		Gson gson=new GsonBuilder().serializeNulls().create();
		ReviewerDTO addReviewer = gson.fromJson(reviewerFilter, ReviewerDTO.class);
		
		List<KeyActivityDTO> keyActivityDTOs = new ArrayList<KeyActivityDTO>();
		List<SubActivityDTO> subActivityDTOs = new ArrayList<SubActivityDTO>();
		List<KeyActivity> keyActivities = new ArrayList<KeyActivity>();
		User currentUser = userService.findByUsername(PrincipalUtil.getPrincipal());
		
		Integer reportingPeriodId = addReviewer.getReportingperiodId();
		ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
		Integer outputId = Integer.parseInt( addReviewer.getOutput());
		Output output = outputServices.getById(outputId);
		Agency leadAgency = agencyService.findByLoginUser(PrincipalUtil.getPrincipal());
		
		List<Integer> reportingPeriodIds = new ArrayList<Integer>();
		List<ReportingPeriod> previousReportingPeriod = reportingPeriodService.getPreviousReportingPeriodByCurrentReportingPeriod(reportingPeriod.getStartdate());
		if(previousReportingPeriod != null && previousReportingPeriod.size() > 0){
			for(ReportingPeriod previousReporting : previousReportingPeriod ){
											reportingPeriodIds.add(previousReporting.getId());
										}
		}
		reportingPeriodIds.add(reportingPeriodId);
		
		keyActivities = keyActivityService.findByOutput(output);
		for (KeyActivity keyActivity : keyActivities) {
			subActivityDTOs = new ArrayList<SubActivityDTO>();
			List<SubActivity> subActivities1 = subActivityService.getSubActivitiesByKeyActivityAndReportingPeriodsAndLeadAgency(keyActivity,reportingPeriodIds,leadAgency.getId());
			List<AgencyDTO> agencyDTOs = new ArrayList<AgencyDTO>();
			if (subActivities1.size() > 0) {
				for (SubActivity subActivity : subActivities1) {
					
					Planning planning = planningRepository.findBySubActivityAndReportingPeriod(subActivity,reportingPeriod);
					if(planning == null){
						subActivity.setCarryOverStatus(true);
					}
					
					LinkedHashSet<AgencyDTO> agencyDTOs2 = new LinkedHashSet<AgencyDTO>();
					StatusTracking statusTracking = statusTrackingService.findByUserAndSubActivityAndReportingPeriodAndUserLevel(currentUser, subActivity, reportingPeriod, 2);
					boolean loadLeadDataFlag = false;
					if (statusId.equals("All") || (statusId.equals("ReadyForApproval") && statusTracking != null && statusTracking.isComplete() && (statusTracking.getReviewStatus() == 0 || statusTracking.getReviewStatus() == -1)) ||
							(statusId.equals("NotReadyForApproval") && (statusTracking == null || !statusTracking.isComplete())) ||
							(statusId.equals("SentForApproval") && statusTracking != null && statusTracking.isComplete() && !statusTracking.isReworkRequired()) ||
							(statusId.equals("NeedMoreInfo") && statusTracking != null && statusTracking.isReworkRequired() && !statusTracking.isComplete())){
						loadLeadDataFlag = true;
					} else if (statusId.equals("Reviewed") || statusId.equals("NotReviewed")) {
						List<StatusTracking> paStatusTrackings = statusTrackingService.findBysubActivityAndReportingPeriodAndUserLevel(subActivity, reportingPeriod, 1);
						for (StatusTracking paStatusTracking : paStatusTrackings) {
							if (paStatusTracking.getReviewStatus() != 0 && statusId.equals("Reviewed")) {
								loadLeadDataFlag = true;
								break;
							} else if (paStatusTracking.getReviewStatus() == 0 && statusId.equals("NotReviewed")) {
								loadLeadDataFlag = true;
								break;
							}
						}
					}
					if(loadLeadDataFlag){
						/*if(statusTracking != null){
							subActivity.setColorCode(statusTracking.getActualStatusColor());
						}else {
							subActivity.setColorCode("#f5f5f5");
						}*/
						
						String statusIcon = "";
						
						if(statusTracking != null){
							subActivity.setColorCode(statusTracking.getActualStatusColor());
							if (statusTracking.isReworkRequired() && !statusTracking.isComplete()) {
								statusIcon = "fa fa-info-circle";
							} else if (statusTracking.isComplete() && statusTracking.getReviewStatus() == 1){
								statusIcon = "fa fa-check-square-o";
							} else if (statusTracking.isComplete() && statusTracking.isSentForReview()){
								statusIcon = "fa fa-external-link-square";
							} else if (statusTracking.isComplete()) {
								statusIcon = "fa fa-thumbs-o-up";
							}
						}else {
							subActivity.setColorCode("#f5f5f5");
						}

						if(subActivity.getId() != null){
							agencyDTOs = new ArrayList<AgencyDTO>();
							List<Agency> agencies = subActivity.getPartnerAgency();
							if(agencies.size() > 0){
								for (Agency agency : agencies) {
									/*List<User> users = agency.getAgencyAuthority();
									
									for (User user : users) {
										AgencyDTO agencyDTO = new AgencyDTO();
										agencyDTO.setId(agency.getId());
										agencyDTO.setAgency(agency.getName());
										agencyDTO.setStatus(agency.getStatus());
										agencyDTO.setUserId(user.getId());
										agencyDTO.setUserName(user.getUsername());
										SubmitForReview submitForReview = submitForReviewService.findByAgencyAndReportingPeriod(agency, reportingPeriod);
										if(submitForReview != null){
											StatusTracking paStatusTracking = statusTrackingService.findByAgencyAndSubActivityAndReportingPeriodAndUserLevel(submitForReview.getAgency(), subActivity, reportingPeriod, 1);
											if(paStatusTracking != null){
												
												if (paStatusTracking.getReviewStatus() == 1) {
													agencyDTO.setStatusIcon("fa fa-check-circle-o");
												} else if (paStatusTracking.getReviewStatus() == -1) {
													agencyDTO.setStatusIcon("fa fa-info-circle");
												}
												
												if (statusId.equals("Reviewed") && paStatusTracking.getReviewStatus() != 0) {
													agencyDTO.setColorCode(paStatusTracking.getActualStatusColor());
													agencyDTOs.add(agencyDTO);
												} else if (statusId.equals("NotReviewed") && paStatusTracking.getReviewStatus() == 0) {
													agencyDTO.setColorCode(paStatusTracking.getActualStatusColor());
													agencyDTOs.add(agencyDTO);
												} else if(statusId.equals("All")){
													agencyDTO.setColorCode(paStatusTracking.getActualStatusColor());
													agencyDTOs.add(agencyDTO);
												}
											}
										}
									}*/
									

									AgencyDTO agencyDTO = new AgencyDTO();
									agencyDTO.setId(agency.getId());
									agencyDTO.setAgency(agency.getName());
									agencyDTO.setStatus(agency.getStatus());
									List<SubmitForReview> submitForReviews = submitForReviewService.findByAgencyAndReportingPeriod(agency, reportingPeriod);
									if(submitForReviews != null && submitForReviews.size() > 0){
										agencyDTO.setUserId(submitForReviews.get(0).getUser().getId());
										agencyDTO.setUserName(submitForReviews.get(0).getUser().getUsername());
										StatusTracking paStatusTracking = statusTrackingService.findByAgencyAndSubActivityAndReportingPeriodAndUserLevel(submitForReviews.get(0).getAgency(), subActivity, reportingPeriod, 1);
										if(paStatusTracking != null){
											
											if (paStatusTracking.getReviewStatus() == 1) {
												agencyDTO.setStatusIcon("fa fa-check-circle-o");
											} else if (paStatusTracking.getReviewStatus() == -1) {
												agencyDTO.setStatusIcon("fa fa-info-circle");
											}
											
											if (statusId.equals("Reviewed") && paStatusTracking.getReviewStatus() != 0) {
												agencyDTO.setColorCode(paStatusTracking.getActualStatusColor());
												agencyDTOs.add(agencyDTO);
											} else if (statusId.equals("NotReviewed") && paStatusTracking.getReviewStatus() == 0) {
												agencyDTO.setColorCode(paStatusTracking.getActualStatusColor());
												agencyDTOs.add(agencyDTO);
											} else if(statusId.equals("All")){
												agencyDTO.setColorCode(paStatusTracking.getActualStatusColor());
												agencyDTOs.add(agencyDTO);
											}
										}
									}
								
								}
								agencyDTOs2 = new LinkedHashSet<AgencyDTO>(agencyDTOs);
							}
							subActivityDTOs.add(new SubActivityDTO(subActivity.getId(), subActivity.getName(), agencyDTOs2, subActivity.getColorCode(),subActivity.getSequenceNumber(), statusIcon,subActivity.isCarryOverStatus()));
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
	
	@RequestMapping(value = "getStatusTrackingDetails" , method = RequestMethod.GET)
	public @ResponseBody StatusTracking getStatusTrackingDetails(ModelMap model,@RequestParam Integer userId,@RequestParam Integer subId, @RequestParam Integer reportingPeriodId ){
		if(subId != null){
			SubActivity subActivity = subActivityService.getById(subId);
			User user = userService.getById(userId);
			Agency agency = agencyService.findByLoginUser(user.getUsername());
			ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
			if(subActivity != null && user != null){
				StatusTracking statusTracking = statusTrackingService.findByAgencyAndSubActivityAndReportingPeriodAndUserLevel(agency, subActivity, reportingPeriod, 1);
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
	 * @return
	 */
	@RequestMapping(value = "getStatusInfo" , method = RequestMethod.GET)
	public @ResponseBody List<Statuss> getStatusInfo(){
		return statusService.listAllStatus();
	}
	
	/**
	 * @param subActivityId
	 * @param partnerAgencyUserId
	 * @param reportingPeriodId
	 * @param reviewStatus
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveStatusTrackerDetails" , method = RequestMethod.GET)
	public @ResponseBody StatusTracking saveStatusTrackerDetails(@RequestParam("subActivityId") Integer subActivityId,@RequestParam("userId") Integer partnerAgencyUserId,@RequestParam("reportingPeriodId") 
	Integer reportingPeriodId, @RequestParam("reviewStatus")Integer reviewStatus, @RequestParam("reviewerFeedback") String reviewerFeedback) throws Exception{
		SubActivity subActivity=subActivityService.getById(subActivityId);
		User partnerAgencyUser = userService.getById(partnerAgencyUserId);
		Agency partnerAgency = agencyService.findByLoginUser(partnerAgencyUser.getUsername());
		ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
		StatusTracking partnerAgencyStatusTracking = statusTrackingService.findByAgencyAndSubActivityAndReportingPeriodAndUserLevel(partnerAgency, subActivity, reportingPeriod, 1);
		if (partnerAgencyStatusTracking != null){
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			User currentUser = userService.findByUsername(PrincipalUtil.getPrincipal());
			partnerAgencyStatusTracking.setReviewedBy(currentUser);
			partnerAgencyStatusTracking.setReviewedOn(dateformat.format(new Date()));
			partnerAgencyStatusTracking.setReviewStatus(reviewStatus);
			if (reviewStatus == -1) {
				partnerAgencyStatusTracking.setReviewerFeedback(reviewerFeedback);	
			}
			statusTrackingService.add(partnerAgencyStatusTracking);
		}
		return partnerAgencyStatusTracking;
	}
	
	/**
	 * @param statusTrackingJson
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveSubActivityDetails", method = RequestMethod.GET)
	public @ResponseBody StatusTracking saveSubActivityDetails(@RequestParam("statusTracking") String statusTrackingJson) throws Exception {
		StatusTracking statusTracking = null;
		Gson gson = new GsonBuilder().create();
		StatusTracking statusTrackingValues = gson.fromJson(statusTrackingJson, StatusTracking.class);
		Integer subActivitiId = statusTrackingValues.getSubActivityId();
		SubActivity subActivity = subActivityService.getById(subActivitiId);
		User currentUser = userService.findByUsername(PrincipalUtil.getPrincipal());
		Agency agency = agencyService.findByLoginUser(PrincipalUtil.getPrincipal());
		ReportingPeriod reportingPeriod = reportingPeriodService.getById(statusTrackingValues.getReportingPeriodId());
		StatusTrackingLog statusTrackingLog = new StatusTrackingLog();
		
		statusTracking = statusTrackingService.findByAgencyAndSubActivityAndReportingPeriodAndUserLevel(agency, subActivity, reportingPeriod, 2);
		if (statusTracking == null){
			statusTracking = new StatusTracking();
			statusTrackingLog.setActionType("INSERT");
		} else {
			statusTrackingLog.setActionType("UPDATED");			
			if (statusTracking.isComplete()) {
				statusTrackingLog.setSyncStatus(false);
				statusTrackingLog.setActionType("FAILED. Not Updated");
				statusTrackingLog.setSyncFailureMessage("Data is already set as ready for review");
			}
		}
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		statusTracking.setActualStatusColor(statusTrackingValues.getActualStatusColor());
		statusTracking.setActualStatusPercentage(statusTrackingValues.getActualStatusPercentage());
		statusTracking.setKeyProgress(statusTrackingValues.getKeyProgress());
		statusTracking.setReasonForGap(statusTrackingValues.getReasonForGap());
		statusTracking.setRectifyTheGap(statusTrackingValues.getRectifyTheGap());
		statusTracking.setSubActivity(subActivity);
		statusTracking.setReportingPeriod(reportingPeriod);
		statusTracking.setComplete(statusTrackingValues.isComplete());
		statusTracking.setUserLevel(2);
		statusTracking.setUser(currentUser);
		statusTracking.setAgency(agency);
		statusTracking.setReportingPeriodStatus(reportingPeriod.getReportingStatus());
		statusTracking.setSubActivityStatus(subActivity.getStatus());
		statusTracking.setSync_dateTime(dateformat.format(new Date()));
		
		if (!statusTrackingLog.getActionType().equals("FAILED. Not Updated")) {
			statusTracking = statusTrackingService.add(statusTracking);
			statusTrackingLog.setStatusTracking(statusTracking);
			
//			Make All the status tracking under this subactivity as Approved if readyfor approval is given with reviewed by and reviewed on details
			if(statusTracking.isComplete()){
				List<StatusTracking> listPartnerStatusTracking = statusTrackingService.findBySubActivityAgencyAndReportingPeriod(subActivity, reportingPeriod);
				for (StatusTracking partnerAgencyStatusTracking : listPartnerStatusTracking) {
					if (partnerAgencyStatusTracking != null){
						partnerAgencyStatusTracking.setReviewedBy(currentUser);
						partnerAgencyStatusTracking.setReviewedOn(dateformat.format(new Date()));
						partnerAgencyStatusTracking.setReviewStatus(1);
						statusTrackingService.add(partnerAgencyStatusTracking);
					}
				}
			}
		}
		statusTrackingLog.setSubActivity(subActivity);
		statusTrackingLog.setReportingPeriod(reportingPeriod);
		statusTrackingLog.setUser(currentUser);
		statusTrackingLog.setActualStatusColor(statusTrackingValues.getActualStatusColor());
		statusTrackingLog.setActualStatusPercentage(statusTrackingValues.getActualStatusPercentage());
		statusTrackingLog.setKeyProgress(statusTrackingValues.getKeyProgress());
		statusTrackingLog.setReasonForGap(statusTrackingValues.getReasonForGap());
		statusTrackingLog.setRectifyTheGap(statusTrackingValues.getRectifyTheGap());
		statusTrackingLog.setComplete(statusTrackingValues.isComplete());
		statusTrackingLog.setUserLevel(2);
		statusTrackingLog.setSync_dateTime(statusTracking.getSync_dateTime());
		statusTrackingLogService.createStatusTrackingLog(statusTrackingLog);
		
		return statusTracking;
	}
	
	/**
	 * @param subActId
	 * @param reportingPeriodId
	 * @return
	 */
	@RequestMapping(value = "getStatusTrackingReviewerInfo", method = RequestMethod.GET)
	public @ResponseBody
	StatusTracking getStatusTrackingReviewerInfo(@RequestParam("subActId") Integer subActId, @RequestParam("reportingPeriodId")Integer reportingPeriodId) {
		SubActivity subActivity = new SubActivity();
		boolean notApproveFlag = false;
		StatusTracking statusTracking = new StatusTracking();
		if (subActId != null) {
			subActivity = subActivityService.getById(subActId);
			ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
			Agency agency = agencyService.findByLoginUser(PrincipalUtil.getPrincipal());
			statusTracking = statusTrackingService.findByAgencyAndSubActivityAndReportingPeriodAndUserLevel(agency, subActivity, reportingPeriod, 2);
			if (statusTracking == null) {
				
				List<ReportingPeriod> previousReportingPeriod = reportingPeriodService.getPreviousReportingPeriodByCurrentReportingPeriod(reportingPeriod.getStartdate());
				if(previousReportingPeriod != null && previousReportingPeriod.size() > 0){
					StatusTracking previousStatusTracking = statusTrackingService.findByAgencyAndSubActivityAndReportingPeriodAndUserLevel(agency, subActivity, previousReportingPeriod.get(0), 2);
					if(previousStatusTracking != null){
						statusTracking = new StatusTracking();
						statusTracking.setActualStatusColor(previousStatusTracking.getActualStatusColor());
						statusTracking.setActualStatusPercentage(previousStatusTracking.getActualStatusPercentage());
						statusTracking.setKeyProgress(previousStatusTracking.getKeyProgress());
						statusTracking.setReasonForGap(previousStatusTracking.getReasonForGap());
						statusTracking.setRectifyTheGap(previousStatusTracking.getRectifyTheGap());
						statusTracking.setUser(previousStatusTracking.getUser());
						statusTracking.setCarriedFrom(previousStatusTracking.getReportingPeriod().getYear() + " - " + previousStatusTracking.getReportingPeriod().getName());
						statusTracking.setSubActivity(subActivity);
					}
				}
				if(statusTracking == null){
					statusTracking = new StatusTracking();
					statusTracking.setSubActivity(subActivity);
					statusTracking.setReportingPeriod(reportingPeriod);
				}
				
			}
			List<StatusTracking> listPartnerReworkStatusTracking = statusTrackingService.findBySubActivityAgencyAndReportingPeriodAndReviewedStatus(subActivity, reportingPeriod, -1);
			if (listPartnerReworkStatusTracking != null && listPartnerReworkStatusTracking.size() > 0){
				notApproveFlag = true;
			}
			statusTracking.setApproveStatus(notApproveFlag);
		}
		return statusTracking;
	}
	
	
	/**
	 * Get all the Themes
	 * @param yearId,quaterId
	 * @return
	 */
	@RequestMapping(value = "getReviewrThemesByStrategic", method = RequestMethod.GET)
	public @ResponseBody LinkedHashSet<Theme> getReviewrThemesByStrategic(ModelMap model,@RequestParam Integer reportingPeriodId, @RequestParam Integer strategicId, @RequestParam String status) {
		User user = userService.findByUsername(PrincipalUtil.getPrincipal());
		if(user != null){
			Agency agency = agencyService.findByLoginUser(user.getUsername());
			ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
			if(agency != null){
				List<UserRole> userRoles =  user.getUserRoles();
				for (UserRole userRole : userRoles) {
					if(userRole.getName().equals("STATUS_REVIEWER")){
						LinkedHashSet<Theme> themes = new LinkedHashSet<Theme>();
						if(reportingPeriodId != null){
							List<Integer> reportingPeriodIds = new ArrayList<Integer>();
							List<ReportingPeriod> previousReportingPeriod = reportingPeriodService.getPreviousReportingPeriodByCurrentReportingPeriod(reportingPeriod.getStartdate());
							if(previousReportingPeriod != null && previousReportingPeriod.size() > 0){
								for(ReportingPeriod previousReporting : previousReportingPeriod ){
											reportingPeriodIds.add(previousReporting.getId());
										}
							}
							reportingPeriodIds.add(reportingPeriodId);
							List<SubActivity> subActivities = subActivityService.getSubActivitiesByStrategicPillarAndReportingPeriodsAndLeadAgency(strategicId, reportingPeriodIds, agency.getId());
							for (SubActivity subActivity : subActivities) {
								boolean addFlag = false;
								if (status.equals("All")){
									addFlag = true;
								} else if (status.equals("Reviewed") || status.equals("NotReviewed")) {
									List<StatusTracking> paStatusTrackings = statusTrackingService.findBysubActivityAndReportingPeriodAndUserLevel(subActivity, reportingPeriod, 1);
									for (StatusTracking paStatusTracking : paStatusTrackings) {
										if (paStatusTracking.getReviewStatus() != 0 && status.equals("Reviewed")) {
											addFlag = true;
											break;
										} else if (paStatusTracking.getReviewStatus() == 0 && status.equals("NotReviewed")) {
											addFlag = true;
											break;
										}
									}
								} else {
									StatusTracking statusTracking = statusTrackingService.findByAgencyAndSubActivityAndReportingPeriodAndUserLevel(agency, subActivity, reportingPeriod, 2);
									if (statusTracking != null){
										if (status.equals("ReadyForApproval")){
											if (statusTracking.isComplete() && (statusTracking.getReviewStatus() == 0 || statusTracking.getReviewStatus() == -1)){
												addFlag = true;
											}
										}else if(status.equals("NotReadyForApproval")){
											if (!statusTracking.isComplete()){
												addFlag = true;
											}
										}else if(status.equals("SentForApproval")){
											if (statusTracking.isComplete() && !statusTracking.isReworkRequired()){
												addFlag = true;
											}
										}else if(status.equals("NeedMoreInfo")){
											if (statusTracking.isReworkRequired()){
												addFlag = true;
											}
										}
									} else if(status.equals("NotReadyForApproval")){
										addFlag = true;
									}
								}
								if(addFlag){
									themes.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme());
								}
							}
						}
						return themes;
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Get all the Outcome
	 * @param yearId,quaterId
	 * @return
	 */
	@RequestMapping(value = "getReviewrOutcomesByTheme", method = RequestMethod.GET)
	public @ResponseBody LinkedHashSet<Outcome> getReviewrOutcomesByTheme(ModelMap model,@RequestParam Integer reportingPeriodId, @RequestParam Integer themeId, @RequestParam String status) {
		User user = userService.findByUsername(PrincipalUtil.getPrincipal());
		if(user != null){
			Agency agency = agencyService.findByLoginUser(user.getUsername());
			if(agency != null){
				List<UserRole> userRoles =  user.getUserRoles();
				for (UserRole userRole : userRoles) {
					if(userRole.getName().equals("STATUS_REVIEWER")){
						ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
						LinkedHashSet<Outcome> outcomes = new LinkedHashSet<Outcome>();
						if(reportingPeriod != null && themeId != null){
							List<Integer> reportingPeriodIds = new ArrayList<Integer>();
							List<ReportingPeriod> previousReportingPeriod = reportingPeriodService.getPreviousReportingPeriodByCurrentReportingPeriod(reportingPeriod.getStartdate());
							if(previousReportingPeriod != null && previousReportingPeriod.size() > 0){
								for(ReportingPeriod previousReporting : previousReportingPeriod ){
											reportingPeriodIds.add(previousReporting.getId());
										}
							}
							reportingPeriodIds.add(reportingPeriodId);
							List<SubActivity> subActivities = subActivityService.getSubActivitiesByThemeAndReportingPeriodsAndLeadAgency(themeId, reportingPeriodIds, agency.getId());
							for (SubActivity subActivity : subActivities) {
								boolean addFlag = false;
								if (status.equals("All")){
									addFlag = true;
								} else if (status.equals("Reviewed") || status.equals("NotReviewed")) {
									List<StatusTracking> paStatusTrackings = statusTrackingService.findBysubActivityAndReportingPeriodAndUserLevel(subActivity, reportingPeriod, 1);
									for (StatusTracking paStatusTracking : paStatusTrackings) {
										if (paStatusTracking.getReviewStatus() != 0 && status.equals("Reviewed")) {
											addFlag = true;
											break;
										} else if (paStatusTracking.getReviewStatus() == 0 && status.equals("NotReviewed")) {
											addFlag = true;
											break;
										}
									}
								} else {
									StatusTracking statusTracking = statusTrackingService.findByAgencyAndSubActivityAndReportingPeriodAndUserLevel(agency, subActivity, reportingPeriod, 2);
									if (statusTracking != null){
										if (status.equals("ReadyForApproval")){
											if (statusTracking.isComplete() && (statusTracking.getReviewStatus() == 0 || statusTracking.getReviewStatus() == -1)){
												addFlag = true;
											}
										}else if(status.equals("NotReadyForApproval")){
											if (!statusTracking.isComplete()){
												addFlag = true;
											}
										}else if(status.equals("SentForApproval")){
											if (statusTracking.isComplete() && !statusTracking.isReworkRequired()){
												addFlag = true;
											}
										}else if(status.equals("NeedMoreInfo")){
											if (statusTracking.isReworkRequired()){
												addFlag = true;
											}
										}
									} else if(status.equals("NotReadyForApproval")){
										addFlag = true;
									}
								}
								if(addFlag){
									outcomes.add(subActivity.getKeyActivity().getOutput().getOutcome());
								}
							}
						}
						return outcomes;
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Get all the output
	 * @param yearId,quaterId
	 * @return
	 */
	@RequestMapping(value = "getReviewerOutputsByoutcome", method = RequestMethod.GET)
	public @ResponseBody LinkedHashSet<Output> getReviewerOutputsByoutcome(ModelMap model,@RequestParam Integer reportingPeriodId, @RequestParam Integer outcomeId, @RequestParam String status) {
		User user = userService.findByUsername(PrincipalUtil.getPrincipal());
		if(user != null){
			Agency agency = agencyService.findByLoginUser(user.getUsername());
			ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
			if(agency != null){
				List<UserRole> userRoles =  user.getUserRoles();
				for (UserRole userRole : userRoles) {
					if(userRole.getName().equals("STATUS_REVIEWER")){
						LinkedHashSet<Output> outputs = new LinkedHashSet<Output>();
						if(reportingPeriodId != null && outcomeId != null){
							List<Integer> reportingPeriodIds = new ArrayList<Integer>();
							List<ReportingPeriod> previousReportingPeriod = reportingPeriodService.getPreviousReportingPeriodByCurrentReportingPeriod(reportingPeriod.getStartdate());
							if(previousReportingPeriod != null && previousReportingPeriod.size() > 0){
								for(ReportingPeriod previousReporting : previousReportingPeriod ){
											reportingPeriodIds.add(previousReporting.getId());
										}
							}
							reportingPeriodIds.add(reportingPeriodId);
							List<SubActivity> subActivities = subActivityService.getSubActivitiesByOutcomeAndReportingsPeriodAndLeadAgency(outcomeId, reportingPeriodIds, agency.getId());
							for (SubActivity subActivity : subActivities) {
								boolean addFlag = false;
								if (status.equals("All")){
									addFlag = true;
								} else if (status.equals("Reviewed") || status.equals("NotReviewed")) {
									List<StatusTracking> paStatusTrackings = statusTrackingService.findBysubActivityAndReportingPeriodAndUserLevel(subActivity, reportingPeriod, 1);
									for (StatusTracking paStatusTracking : paStatusTrackings) {
										if (paStatusTracking.getReviewStatus() != 0 && status.equals("Reviewed")) {
											addFlag = true;
											break;
										} else if (paStatusTracking.getReviewStatus() == 0 && status.equals("NotReviewed")) {
											addFlag = true;
											break;
										}
									}
								} else {
									StatusTracking statusTracking = statusTrackingService.findByAgencyAndSubActivityAndReportingPeriodAndUserLevel(agency, subActivity, reportingPeriod, 2);
									if (statusTracking != null){
										if (status.equals("ReadyForApproval")){
											if (statusTracking.isComplete() && (statusTracking.getReviewStatus() == 0 || statusTracking.getReviewStatus() == -1)){
												addFlag = true;
											}
										}else if(status.equals("NotReadyForApproval")){
											if (!statusTracking.isComplete()){
												addFlag = true;
											}
										}else if(status.equals("SentForApproval")){
											if (statusTracking.isComplete() && !statusTracking.isReworkRequired()){
												addFlag = true;
											}
										}else if(status.equals("NeedMoreInfo")){
											if (statusTracking.isReworkRequired()){
												addFlag = true;
											}
										}
									} else if(status.equals("NotReadyForApproval")){
										addFlag = true;
									}
								}
								if(addFlag){
									outputs.add(subActivity.getKeyActivity().getOutput());
								}
							}
						}
						return outputs;
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * @author pushpa
	 * Show observation of partner agency data for the reviewer
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/goObservation", method = RequestMethod.GET)
	public @ResponseBody List<SubmitForReview> goObservation(ModelMap modelMap, @RequestParam("reportingPeriodId")Integer reportingPeriodId){
		User user = userService.findByUsername(PrincipalUtil.getPrincipal());
		List<SubmitForReview> finalSubmitforReviewList = new ArrayList<SubmitForReview>();
		ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
		List<UserRole> userRoles =  user.getUserRoles();
		for (UserRole userRole : userRoles) {
			if(userRole.getName().equals("STATUS_REVIEWER")){
				Agency leadagency = agencyService.findByLoginUser(user.getUsername());
				List<SubmitForReview> listSubmitForReviews = submitForReviewService.findByReportingPeriodAndUserLevel(reportingPeriod, 1);
				for (SubmitForReview submitForReview : listSubmitForReviews) {
					List<SubActivity> subActivities = subActivityService.getSubActivitiesByOpenedReportingPeriodAndPartnerAgencyAndLeadAgency(reportingPeriodId, submitForReview.getAgency().getId(), leadagency.getId());
					if(subActivities != null && subActivities.size() > 0){
						finalSubmitforReviewList.add(submitForReview);
					}
				}
			}else if (userRole.getName().equals("STATUS_APPROVER")){
				List<SubmitForReview> listSubmitForReviews = submitForReviewService.findByReportingPeriodAndUserLevel(reportingPeriod, 2);
				if(listSubmitForReviews != null && listSubmitForReviews.size() > 0){
					finalSubmitforReviewList.addAll(listSubmitForReviews);
				}
			}
		}
		return finalSubmitforReviewList;
	}
	
	
	/**
	 * @param model
	 * @param authentication
	 * @return
	 */
	@RequestMapping(value = "submitApprovalFilter", method = RequestMethod.GET)
	public String submitFilter(ModelMap model, Authentication authentication) {
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.STATUS_REVIEWER_ROLE)){
			model.addAttribute("filterHierarchy", new FilterHierarchy());
			List<ReportingPeriod> reportingPeriods = reportingPeriodService.getReportingPeriodListByStatusOpen();
			model.addAttribute("submitForApproval",new SubmitForReview());
			model.addAttribute("reportingPeriods", reportingPeriods);
			return "reviewer/SubmitApprovalFilter";
		}else{
			return "login";
		}
		
	}
	
	/**
	 * @param reportingPeriodId
	 * @return
	 */
	@RequestMapping(value = "checkApprovalSubmit",method = RequestMethod.GET)
	public @ResponseBody SubmitForReview checkSubmit(@RequestParam Integer reportingPeriodId){
		ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
		User user = userService.findByUsername(PrincipalUtil.getPrincipal());
		SubmitForReview submitForReview = submitForReviewService.findByUserAndReportingPeriod(user, reportingPeriod);
		Agency agency = agencyService.findByLoginUser(PrincipalUtil.getPrincipal());
		
		if (agency != null) {
			if (submitForReview == null) {
				submitForReview = new SubmitForReview();
				List<Integer> reportingPeriodIds = new ArrayList<Integer>();
				List<ReportingPeriod> previousReportingPeriod = reportingPeriodService.getPreviousReportingPeriodByCurrentReportingPeriod(reportingPeriod.getStartdate());
				if(previousReportingPeriod != null && previousReportingPeriod.size() > 0){
					for(ReportingPeriod previousReporting : previousReportingPeriod ){
											reportingPeriodIds.add(previousReporting.getId());
										}
				}
				reportingPeriodIds.add(reportingPeriodId);
				List<SubActivity> activities = subActivityService.getSubActivitiesByReportingPeriodsAndLeadAgency(reportingPeriodIds,agency.getId());
				List<StatusTracking> statusTrackings = statusTrackingService.findByAgencyAndReportingPeriodAndComplete(agency, reportingPeriod, true);
				if(activities != null && activities.size() > 0){
					submitForReview.setTotal("" + activities.size());
				}
				
				if(activities != null && statusTrackings != null){
					int remaining = activities.size() - statusTrackings.size();
					int completed = statusTrackings.size();
					submitForReview.setRemaining("" + (remaining > 0 ? remaining : 0));
					submitForReview.setCompleted("" + (completed > 0 ? completed : 0));
				}
				submitForReview.setReportingPeriod(reportingPeriod);
			} else {
				if (submitForReview.isLeadReworkStatus() && !submitForReview.isPartnerReworkStatus()) {
					
					List<StatusTracking> reworkStatusTracking = statusTrackingService.findByAgencyAndReportingPeriodAndReworkRequired(agency, reportingPeriod, true);
					List<StatusTracking> completedStatusTracking = statusTrackingService.findByAgencyAndReportingPeriodAndReworkRequiredAndComplete(agency, reportingPeriod, true, true);
					if(reworkStatusTracking != null && reworkStatusTracking.size() > 0 ){
						submitForReview.setTotal("" + reworkStatusTracking.size());
					}
						
					if(reworkStatusTracking != null && completedStatusTracking != null ){
						int remaining = reworkStatusTracking.size() - completedStatusTracking.size();
						int completed = completedStatusTracking.size();
						submitForReview.setRemaining("" + (remaining > 0 ? remaining : 0));
						submitForReview.setCompleted("" + (completed > 0 ? completed : 0));
					}
					
					submitForReview.setReportingPeriod(reportingPeriod);
				}
			}
		}
		return submitForReview;
	}
	
	/**
	 * @param submitForApproval
	 * @return
	 */
	@RequestMapping(value = "createSubmitForApproval", method = RequestMethod.POST)
	public ModelAndView createSubmitForReview(@ModelAttribute SubmitForReview submitForApproval) {
		if (submitForApproval != null) {
			User user = userService.findByUsername(PrincipalUtil.getPrincipal());
			Agency agency = agencyService.findByLoginUser(PrincipalUtil.getPrincipal());
			ReportingPeriod reportingPeriod = reportingPeriodService.getById(Integer.parseInt(submitForApproval.getReportPeriodId()));
			if (user != null && user.getId() != null) {
				boolean updateFlag = true;
				submitForApproval.setUser(user);
				List<SubmitForReview> editSubmitForReviews = submitForReviewService.findByAgencyAndReportingPeriod(agency, reportingPeriod);
				if (editSubmitForReviews != null && editSubmitForReviews.size() > 0) {
					submitForApproval.setId(editSubmitForReviews.get(0).getId());
					submitForApproval.setLeadReworkStatus(editSubmitForReviews.get(0).isLeadReworkStatus());
					if (editSubmitForReviews.get(0).isLeadReworkStatus() && !editSubmitForReviews.get(0).isPartnerReworkStatus()) {
						
						submitForApproval.setPartnerReworkStatus(false);
						submitForApproval.setLeadReworkStatus(false);
						List<StatusTracking> reworkStatusTrackings = statusTrackingService.findByAgencyAndReportingPeriodAndReworkRequired(agency, editSubmitForReviews.get(0).getReportingPeriod(), true);
						for (StatusTracking statusTracking : reworkStatusTrackings) {
							statusTracking.setReworkRequired(false);
							statusTracking.setSentForReview(true);
							statusTracking.setReviewStatus(0);
							statusTrackingService.add(statusTracking);
						}
					} else {
						updateFlag = false;
					}
				}  else {
					List<StatusTracking> readyForReview = statusTrackingService.findByAgencyAndReportingPeriodAndComplete(agency, reportingPeriod, true);
					for (StatusTracking statusTracking : readyForReview) {
						statusTracking.setReworkRequired(false);
						statusTracking.setReviewStatus(0);
						statusTracking.setSentForReview(true);
						statusTrackingService.add(statusTracking);
					}	
				}
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					submitForApproval.setSubmit_dateTime(dateFormat.parse(dateFormat.format(new Date())));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				submitForApproval.setAgency(agency);
				submitForApproval.setUserLevel(2);
				submitForApproval.setReportingPeriod(reportingPeriod);
				if (updateFlag) {
					submitForReviewService.add(submitForApproval);	
				}
			}
		}
		return new ModelAndView("redirect:submitApprovalFilter");
	}
	
	/**
	 * @author pushpa
	 * Submit For Rework
	 * @param model
	 * @param authentication
	 * @return
	 */
	@RequestMapping(value = "submitForRework", method = RequestMethod.GET)
	public String getSubmitForRework(ModelMap model, Authentication authentication) {
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.STATUS_REVIEWER_ROLE) || principalUtil.getCurrentUserRole().equals(ConstantUtil.STATUS_APPROVER)){
			model.addAttribute("filterHierarchy", new FilterHierarchy());
			List<ReportingPeriod> reportingPeriods = reportingPeriodService.getReportingPeriodListByStatusOpen();
			model.addAttribute("reportingPeriods", reportingPeriods);
			return "reviewer/sendForRework";
		}else{
			return "login";
		}
		
	}
	
	/**
	 * @author pushpa
	 * @param reportingPeriodId
	 * @return
	 */
	@RequestMapping(value = "checkForRework", method = RequestMethod.GET)
	public @ResponseBody Integer checkForRework(@RequestParam("reportingPeriodId") Integer reportingPeriodId){
		User user = userService.findByUsername(PrincipalUtil.getPrincipal());
		ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
		List<StatusTracking> liststaStatusTrackings = new ArrayList<StatusTracking>();
		List<UserRole> userRoles =  user.getUserRoles();
		for (UserRole userRole : userRoles) {
			if(userRole.getName().equals("STATUS_REVIEWER") || userRole.getName().equals("STATUS_APPROVER")){
				List<StatusTracking> tempListstaStatusTrackings = statusTrackingService.findByReportingPeriodAndReviewerAndReviewStatus(reportingPeriod, user);
				liststaStatusTrackings.addAll(tempListstaStatusTrackings);
			}
		}
		if(liststaStatusTrackings != null && liststaStatusTrackings.size() > 0){
			return liststaStatusTrackings.size();
		}
		return 0;
	}
	
	@RequestMapping(value = "checkForReworkShow", method = RequestMethod.GET)
	public @ResponseBody List<ReworkSummaryDTO> checkForReworkShow(@RequestParam("reportingPeriodId") Integer reportingPeriodId){
		User user = userService.findByUsername(PrincipalUtil.getPrincipal());
		ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
		List<StatusTracking> liststaStatusTrackings = new ArrayList<StatusTracking>();
		List<UserRole> userRoles =  user.getUserRoles();
		for (UserRole userRole : userRoles) {
			if(userRole.getName().equals("STATUS_REVIEWER") || userRole.getName().equals("STATUS_APPROVER")){ 
				List<StatusTracking> tempListstaStatusTrackings = statusTrackingService.findByReportingPeriodAndReviewerAndReviewStatus(reportingPeriod, user);
				liststaStatusTrackings.addAll(tempListstaStatusTrackings);
			}
		}
		
		List<ReworkSummaryDTO> reworkSummaryDTOs = new ArrayList<ReworkSummaryDTO>();
		
		if(liststaStatusTrackings != null && liststaStatusTrackings.size() > 0){
			int tempAgencyId = 0;
			ReworkSummaryDTO reworkSummaryDTO = null;
			for (StatusTracking statusTracking : liststaStatusTrackings) {
				Agency agency = statusTracking.getAgency();
				if (tempAgencyId != agency.getId()) {
					if (reworkSummaryDTO != null) {
						reworkSummaryDTOs.add(reworkSummaryDTO);
					}
					reworkSummaryDTO = new ReworkSummaryDTO();
					reworkSummaryDTO.setUserId(agency.getId());
					reworkSummaryDTO.setAgencyName(agency.getName());
					tempAgencyId = agency.getId();
				}
				reworkSummaryDTO.setTotalRework(reworkSummaryDTO.getTotalRework()+1);
			}
			if (reworkSummaryDTO != null) {
				reworkSummaryDTOs.add(reworkSummaryDTO);
			}
		}
		return reworkSummaryDTOs;
	}
	
	/**
	 * change values for rework
	 * @param reportingPeriodId
	 * @return
	 */
	@RequestMapping(value = "confirmReworkSubmition", method = RequestMethod.POST)
	public @ResponseBody Integer SubmitForRework(@RequestParam("reportingPeriodId") Integer reportingPeriodId, @RequestParam("partnerUserId") String partnerUserIds){
		int result = 0;
		if (reportingPeriodId != null){
			User user = userService.findByUsername(PrincipalUtil.getPrincipal());
			
			String[] partnersId=partnerUserIds.split(",");
			
			for (int i = 0; i < partnersId.length; i++) {
				Agency partnerAgency = agencyService.findByAgencyId(Integer.parseInt(partnersId[i]));
				ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
				List<UserRole> userRoles =  user.getUserRoles();
				for (UserRole userRole : userRoles) {
					if(userRole.getName().equals("STATUS_REVIEWER")){
						List<StatusTracking> tempListstaStatusTrackings = statusTrackingService.findByReportingPeriodAndReviewerAndAgencyAndReviewStatus(reportingPeriod, user, partnerAgency);
						if(tempListstaStatusTrackings != null && tempListstaStatusTrackings.size() > 0){
							for (StatusTracking statusTracking : tempListstaStatusTrackings) {
								statusTracking.setComplete(false);
								statusTracking.setReworkRequired(true);
								statusTracking.setSentForReview(false);
								SubmitForReview submitForReview = submitForReviewService.findByAgencyAndReportingPeriodAndUserLevel(statusTracking.getAgency(), reportingPeriod, 1);
								if (submitForReview != null){
									Integer reworkCount = submitForReview.getReworkCount();
									if (reworkCount == null || reworkCount == 0) {
										reworkCount = 1;
									} else {
										reworkCount++;
									}
									submitForReview.setReworkCount(reworkCount);
									submitForReview.setLeadReworkStatus(true);
									submitForReview.setPartnerReworkStatus(false);
									statusTrackingService.add(statusTracking);
									submitForReviewService.add(submitForReview);
								}
							}
							result++;
						}
					}else if(userRole.getName().equals("STATUS_APPROVER")){
						List<StatusTracking> tempListstaStatusTrackings = statusTrackingService.findByReportingPeriodAndReviewerAndAgencyAndReviewStatus(reportingPeriod, user, partnerAgency);
						if(tempListstaStatusTrackings != null && tempListstaStatusTrackings.size() > 0){
							for (StatusTracking statusTracking : tempListstaStatusTrackings) {
								statusTracking.setComplete(false);
								statusTracking.setReworkRequired(true);
								statusTracking.setSentForReview(false);
								SubmitForReview submitForReview = submitForReviewService.findByAgencyAndReportingPeriodAndUserLevel(statusTracking.getAgency(), reportingPeriod, 2);
								if (submitForReview != null){
									Integer reworkCount = submitForReview.getReworkCount();
									if (reworkCount == null || reworkCount == 0) {
										reworkCount = 1;
									} else {
										reworkCount++;
									}
									submitForReview.setReworkCount(reworkCount);
									submitForReview.setLeadReworkStatus(true);
									submitForReview.setPartnerReworkStatus(false);
									statusTrackingService.add(statusTracking);
									submitForReviewService.add(submitForReview);
								}
							}
							result++;
						}
					}
				}
			}
		}
		return result;
	}
	
	@RequestMapping(value = "loadStatusForStatusReviewer", method = RequestMethod.GET)
	public @ResponseBody Integer loadStatusForStatusReviewer(@RequestParam Integer reportingPeriodId) {
		Agency agency = agencyService.findByLoginUser(PrincipalUtil.getPrincipal());
		Integer status = 0;
		ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
		if(reportingPeriod != null){
			List<SubmitForReview> submitForReviews = submitForReviewService.findByAgencyAndReportingPeriod(agency, reportingPeriod);
			if (submitForReviews == null || submitForReviews.size() == 0){
				status = 1;
			}else if (submitForReviews.get(0).isLeadReworkStatus()){
				status = 3;
			}else {
				status = 2;
			}
		}
		return status;
	}
}
