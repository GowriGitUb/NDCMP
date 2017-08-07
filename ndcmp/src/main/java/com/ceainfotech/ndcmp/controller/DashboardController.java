/**
 * 
 */
package com.ceainfotech.ndcmp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ceainfotech.ndcmp.model.Agency;
import com.ceainfotech.ndcmp.model.AllowedDeviceInfo;
import com.ceainfotech.ndcmp.model.ReportingPeriod;
import com.ceainfotech.ndcmp.model.StatusTracking;
import com.ceainfotech.ndcmp.model.SubActivity;
import com.ceainfotech.ndcmp.model.SubmitForReview;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.model.UserRole;
import com.ceainfotech.ndcmp.model.dto.ActivityDashboardDTO;
import com.ceainfotech.ndcmp.model.dto.DashboardDTO;
import com.ceainfotech.ndcmp.model.dto.NotificationDTO;
import com.ceainfotech.ndcmp.service.AgencyService;
import com.ceainfotech.ndcmp.service.AllowedDeviceService;
import com.ceainfotech.ndcmp.service.ReportingPeriodService;
import com.ceainfotech.ndcmp.service.StatusTrackingService;
import com.ceainfotech.ndcmp.service.SubActivityService;
import com.ceainfotech.ndcmp.service.SubmitForReviewService;
import com.ceainfotech.ndcmp.service.UserService;
import com.ceainfotech.ndcmp.util.PrincipalUtil;

/**
 * @author Samy
 * 
 */
@Controller
@RequestMapping(value = "/api/**")
public class DashboardController {
	
	@Autowired
	UserService userService;

	@Autowired
	AgencyService agencyService;
	
	@Autowired
	StatusTrackingService statusTrackingService;
	
	@Autowired
	SubmitForReviewService submitForReviewService;

	@Autowired
	ReportingPeriodService reportingPeriodService;
	
	@Autowired
	SubActivityService subActivityService;
	
	@Autowired
	AllowedDeviceService allowedDeviceService;
	
	public ModelAndView registeredCompany(ModelMap modelMap) {

		return new ModelAndView();
	}
	
	@RequestMapping(value = "showNotification", method = RequestMethod.GET)
	public @ResponseBody NotificationDTO showNotification(){
		User user = userService.findByUsername(PrincipalUtil.getPrincipal());
		Agency agency = agencyService.findByLoginUser(PrincipalUtil.getPrincipal());
		List<UserRole> userRoles =  user.getUserRoles();
		NotificationDTO notification = new NotificationDTO();
 		for (UserRole userRole : userRoles) {
			if((userRole.getName().equals("STATUS_UPDATER") || userRole.getName().equals("STATUS_REVIEWER"))
					&& agency != null){
				List<SubmitForReview> listSubmitForReview = submitForReviewService.findByAgencyAndLeadReworkStatus(agency, true);
				if (listSubmitForReview != null && listSubmitForReview.size() > 0){
					String action = "<a href='partnerFilter'> <i class='fa fa-signal'></i> Update </a>";
					if ( userRole.getName().equals("STATUS_REVIEWER")) {
						action = "<a href='listReviewers'> <i class='fa fa-check-square'></i> Review </a>";
					}
					for (SubmitForReview submitForReview : listSubmitForReview) {
						List<StatusTracking> listStatusTrackings = statusTrackingService.findByAgencyAndReportingPeriodAndReworkRequiredAndComplete(agency, submitForReview.getReportingPeriod(), true, false);
						int reworkCount = (listStatusTrackings != null) ? listStatusTrackings.size() : 0;
						if(reworkCount > 0){
							notification.setType("alert-danger");
							notification.setTitle("Need more Information!");
							
							if (reworkCount > 1) {
								if (notification.getMessage() == null || notification.getMessage().isEmpty()) {
									notification.setMessage(reworkCount + " activities require additional information in " + submitForReview.getReportingPeriod().getYear() + " - " + submitForReview.getReportingPeriod().getName() + ". " + action);	
								} else {
									notification.setMessage(notification.getMessage() + "</br>" + reworkCount + " activities require additional information in " + submitForReview.getReportingPeriod().getYear() + " - " + submitForReview.getReportingPeriod().getName() + ". " + action);
								}
							} else {
								if (notification.getMessage() == null || notification.getMessage().isEmpty()) {
									notification.setMessage(reworkCount + " activity require additional information in " + submitForReview.getReportingPeriod().getYear() + " - " + submitForReview.getReportingPeriod().getName() + "." + action);
								} else {
									notification.setMessage(notification.getMessage() + "</br>" + reworkCount + " activity require additional information in " + submitForReview.getReportingPeriod().getYear() + " - " + submitForReview.getReportingPeriod().getName() + ". " + action);
								}
							}
						} else {
							notification.setType("alert-info");
							notification.setTitle("Reporting Status Updated!");
							if ( userRole.getName().equals("STATUS_REVIEWER")) {
								notification.setMessage("Status updated for rework activities in reporting period " + submitForReview.getReportingPeriod().getYear() + " - " + submitForReview.getReportingPeriod().getName() + ". <a href='submitApprovalFilter'> <i class='fa fa-paper-plane'></i> Submit for Approval </a>");
							}else{
								notification.setMessage("Status updated for rework activities in reporting period " + submitForReview.getReportingPeriod().getYear() + " - " + submitForReview.getReportingPeriod().getName() + ". <a href='submitFilter'> <i class='fa fa-paper-plane'></i> Submit for Review </a>");
							}
							
						}
					}
				} else {
					List<ReportingPeriod> reportingPeriods = reportingPeriodService.getReportingPeriodListByStatusOpen();
					if(userRole.getName().equals("STATUS_UPDATER")) {
						if (reportingPeriods != null && reportingPeriods.size() > 0) {
							ReportingPeriod reportingPeriod = reportingPeriods.get(0);
							List<SubmitForReview> submitForReviews = submitForReviewService.findByAgencyAndReportingPeriod(agency,reportingPeriod);
							if (submitForReviews == null || submitForReviews.size() == 0) {
								List<StatusTracking> statusTrackings = statusTrackingService.findByReportingPeriodAndAgency(reportingPeriod,agency);
								if (statusTrackings != null && statusTrackings.size() > 0) {
									List<ReportingPeriod> previousReportingPeriod = reportingPeriodService.getPreviousReportingPeriodByCurrentReportingPeriod(reportingPeriod.getStartdate());
									List<Integer> reportingPeriodIds = new ArrayList<Integer>();
									
									if(previousReportingPeriod != null && previousReportingPeriod.size() > 0){
										for(ReportingPeriod previousReporting : previousReportingPeriod ){
											reportingPeriodIds.add(previousReporting.getId());
										}
										
									}
									reportingPeriodIds.add(reportingPeriod.getId());
									
									List<SubActivity> activities = subActivityService.getSubActivitiesByReportingPeriodsAndPartnerAgency(reportingPeriodIds, user.getAgencyId());
									List<StatusTracking> completedStatusTrackings = statusTrackingService.findByAgencyAndReportingPeriodAndComplete(agency, reportingPeriod, true);
									if(activities != null && completedStatusTrackings != null && activities.size() == completedStatusTrackings.size()){
										notification.setType("alert-info");
										notification.setTitle("Capture of Reporting Status is Completed");
										notification.setMessage("Status update in reporting period " + reportingPeriod.getYear() + " - " + reportingPeriod.getName() + " is completed. <a href='submitFilter'> <i class='fa fa-paper-plane'></i> Submit for Review </a>");
									} else {	
										notification.setType("alert-info");
										notification.setTitle("Reporting Status Capture is In Progress!");
										notification.setMessage("Status update in reporting period " + reportingPeriod.getYear() + " - " + reportingPeriod.getName() + " is in progress. <a href='partnerFilter'> <i class='fa fa-signal'></i> Continue </a>");	
									}
								} else {
									notification.setType("alert-info");
									notification.setTitle("Reporting Period Open");
									notification.setMessage(reportingPeriod.getYear() + " - " +reportingPeriod.getName() + " is Open to capture the status. <a href='partnerFilter'> <i class='fa fa-signal'></i> Start </a>");
									/*if (reportingPeriods.size() == 1) {
										notification.setMessage(reportingPeriod.getYear() + " - " +reportingPeriod.getName() + " is Open to capture the status. <a href='partnerFilter'> <i class='fa fa-signal'></i> Start </a>");	
									} else {
										String reportings = "";
										for (int i = 0; i < reportingPeriods.size(); i++) {
											if (i == 0) {
												reportings = reportingPeriods.get(i).getYear() + " - " + reportingPeriods.get(i).getName();
											} else {
												reportings += ", " + reportingPeriods.get(i).getYear() + " - " + reportingPeriods.get(i).getName();
											}
										}
										notification.setMessage(reportings + " are Open to capture the status. <a href='partnerFilter'> <i class='fa fa-signal'></i> Start </a>");
									}*/
								}
							} else {
								List<StatusTracking> statusTrackings = statusTrackingService.findByAgencyAndSentForReviewAndReportingPeriod(agency, true, reportingPeriod);
								if (statusTrackings.size() > 0) {
									notification.setType("alert-success");
									notification.setTitle("Sent for Review!");
									if (statusTrackings.size() == 1) {
										notification.setMessage("You have submitted a activity sent for next level review...");		
									} else {
										notification.setMessage("You have submitted " + statusTrackings.size() + " activities for next level review...");
									}
								}
							}
						}
					} else if (userRole.getName().equals("STATUS_REVIEWER")){
						if (reportingPeriods != null && reportingPeriods.size() > 0) {
							ReportingPeriod reportingPeriod = reportingPeriods.get(0);
							List<StatusTracking> statusTrackings = statusTrackingService.findReviewDataByLeadAgency(user.getAgencyId());
							if (statusTrackings != null && statusTrackings.size() > 0) {
								notification.setType("alert-info");
								notification.setTitle("Status Review Pending");
								if (statusTrackings.size() == 1) {
									notification.setMessage("There is a activity waiting for your review! <a href='listReviewers'> <i class='fa fa-check-square'></i> Review </a>");	
								} else {
									notification.setMessage("There are " + statusTrackings.size() + " activities waiting for your review! <a href='listReviewers'> <i class='fa fa-check-square'></i> Review </a>");
								}
							} else {
								List<StatusTracking> tempListstaStatusTrackings = statusTrackingService.findByReviewerAndReviewStatus(user);
								if(tempListstaStatusTrackings != null && tempListstaStatusTrackings.size() > 0){
									notification.setType("alert-info");
									notification.setTitle("Send for Need more Information!");
									if (tempListstaStatusTrackings.size() > 1) {
										notification.setMessage(tempListstaStatusTrackings.size() + " activities require more information from partnering agency. <a href='submitForRework'> <i class='fa fa-reply-all'></i> Send for Rework </a>");	
									} else {
										notification.setMessage("1 activity require more information from partnering agency. <a href='submitForRework'> <i class='fa fa-reply-all'></i> Send for Rework </a>");
									}
								} else {
									List<StatusTracking> submittedStatusTrackings = statusTrackingService.findByAgencyAndSentForReviewAndReportingPeriod(agency, true, reportingPeriod);
									if (submittedStatusTrackings.size() > 0) {
										notification.setType("alert-success");
										notification.setTitle("Sent for Approval!");
										if (submittedStatusTrackings.size() == 0) {
											notification.setMessage("You have submitted a activity sent for next level review & approval...");		
										} else {
											notification.setMessage("You have submitted " + submittedStatusTrackings.size() + " activities for next level review & approval...");
										}
									} else {
										List<StatusTracking> leadStatusTrackings = statusTrackingService.findByReportingPeriodAndAgency(reportingPeriod, agency);
										if (leadStatusTrackings != null && leadStatusTrackings.size() > 0) {
											List<ReportingPeriod> previousReportingPeriod = reportingPeriodService.getPreviousReportingPeriodByCurrentReportingPeriod(reportingPeriod.getStartdate());
											List<Integer> reportingPeriodIds = new ArrayList<Integer>();
											
											if(previousReportingPeriod != null && previousReportingPeriod.size() > 0){
												for(ReportingPeriod previousReporting : previousReportingPeriod ){
													reportingPeriodIds.add(previousReporting.getId());
												}
											}
											reportingPeriodIds.add(reportingPeriod.getId());
											List<SubActivity> activities = subActivityService.getSubActivitiesByReportingPeriodsAndLeadAgency(reportingPeriodIds, user.getAgencyId());
											List<StatusTracking> completedStatusTrackings = statusTrackingService.findByAgencyAndReportingPeriodAndComplete(agency, reportingPeriod, true);
											if(activities != null && completedStatusTrackings != null && activities.size() == completedStatusTrackings.size()){
												notification.setType("alert-info");
												notification.setTitle("Reporting Status Capture is Completed!");
												notification.setMessage("Status update in reporting period " + reportingPeriod.getYear() + " - " + reportingPeriod.getName() + " is completed. <a href='submitApprovalFilter'> <i class='fa fa-paper-plane'></i> Submit for Approval </a>");
											} else {
												notification.setType("alert-info");
												notification.setTitle("Reporting Status Capture is In Progress!");
												notification.setMessage("Status update in reporting period " + reportingPeriod.getYear() + " - " + reportingPeriod.getName() + " is in progress. <a href='listReviewers'> <i class='fa fa-check-square'></i> Continue </a>");	
											}
										} else {
											notification.setType("alert-info");
											notification.setTitle("Review Reporting Period Open");
											notification.setMessage(reportingPeriod.getYear() + " - " + reportingPeriod.getName() + " is Open to review and capture the status. <a href='listReviewers'> <i class='fa fa-check-square'></i> Start </a>");
											/*if (reportingPeriods.size() == 1) {
												notification.setMessage(reportingPeriods.get(0).getYear() + " - " + reportingPeriods.get(0).getName() + " is Open to review and capture the status. <a href='listReviewers'> <i class='fa fa-check-square'></i> Start </a>");	
											} else {
												String reportings = "";
												for (int i = 0; i < reportingPeriods.size(); i++) {
													if (i == 0) {
														reportings = reportingPeriods.get(i).getYear() + " - " + reportingPeriods.get(i).getName();
													} else {
														reportings += ", " + reportingPeriods.get(i).getYear() + " - " + reportingPeriods.get(i).getName();
													}
												}
												notification.setMessage(reportings + " are Open to review and capture the status. <a href='listReviewers'> <i class='fa fa-check-square'></i> Start </a>");
											}*/
										}
									}
								}
							}
						}
						
					}
				}
			} else if (userRole.getName().equals("STATUS_APPROVER") && agency != null) {
				List<StatusTracking> statusTrackings = statusTrackingService.findReviewDataForApprover();
				if (statusTrackings != null && statusTrackings.size() > 0) {
					notification.setType("alert-info");
					notification.setTitle("Status Review & Approval Pending");
					if (statusTrackings.size() == 1) {
						notification.setMessage("There is a activity waiting for your review and approval! <a href='approverFilter'> <i class='fa fa-thumbs-up'></i> Review & Approve </a>");	
					} else {
						notification.setMessage("There are " + statusTrackings.size() + " activities waiting for your review and approval! <a href='approverFilter'> <i class='fa fa-thumbs-up'></i> Review & Approve</a>");
					}
				} else {
					List<StatusTracking> tempListstaStatusTrackings = statusTrackingService.findByReviewerAndReviewStatus(user);
					if(tempListstaStatusTrackings != null && tempListstaStatusTrackings.size() > 0){
						notification.setType("alert-info");
						notification.setTitle("Send for Need more Information!");
						if (tempListstaStatusTrackings.size() > 1) {
							notification.setMessage(tempListstaStatusTrackings.size() + " activities require more information from lead agency. <a href='submitForRework'> <i class='fa fa-reply-all'></i> Submit for Rework </a>");	
						} else {
							notification.setMessage("1 activity require more information from lead agency. <a href='submitForRework'> <i class='fa fa-reply-all'></i> Send for Rework </a>");
						}
					} 
				}
			} else if (userRole.getName().equals("SUPER_ADMIN")) {
				List<AllowedDeviceInfo> listAllowedDeviceInfos = allowedDeviceService.findByDeviceStatus(false);
				if (listAllowedDeviceInfos != null && listAllowedDeviceInfos.size() > 0) {
					notification.setType("alert-info");
					notification.setTitle("Mobile / TAB Activation Pending");
					if (listAllowedDeviceInfos.size() == 1) {
						notification.setMessage("There is a activation pending! <a href='allowdDeviceList'> <i class='fa fa-check-square'></i> Activate </a>");	
					} else {
						notification.setMessage("There are " + listAllowedDeviceInfos.size() + " activation pending! <a href='allowdDeviceList'> <i class='fa fa-check-square'></i> Activate </a>");
					}
				}
			}
		}
		return notification;
	}
	
	@RequestMapping(value = "showReportingStatusSummary", method = RequestMethod.GET)
	public @ResponseBody NotificationDTO showReportingStatusSummary(){
		User user = userService.findByUsername(PrincipalUtil.getPrincipal());
		Agency agency = agencyService.findByLoginUser(PrincipalUtil.getPrincipal());
		List<UserRole> userRoles =  user.getUserRoles();
		NotificationDTO notificationDTO = null;
		// Reporting Status Summary Dashboard
		for (UserRole userRole : userRoles) {
			if (userRole.getName().equals("STATUS_REVIEWER") && agency != null) {
				String tableBody = "";
				String partnarTableBody = "";
				int index = 1;
				List<ReportingPeriod> reportingPeriods = reportingPeriodService.getReportingPeriodListByStatusOpen();
				if (reportingPeriods != null && reportingPeriods.size() > 0) {
					for (ReportingPeriod period : reportingPeriods) {
						List<ReportingPeriod> previousReportingPeriod = reportingPeriodService.getPreviousReportingPeriodByCurrentReportingPeriod(period.getStartdate());
						List<SubActivity> activities = subActivityService.getAllSubActivityByReportingPeriodIdAndAgencyId(period.getId(), user.getAgencyId());
						
						int myReadyForReview = 0;
						int mySentForReview = 0;
						int myApproved = 0;
						int myNeedMoreInfo = 0;
						int myTotalActivities = 0;
						List<Integer> reportingPeriodIds = new ArrayList<Integer>();
						List<SubActivity> previousSubActivities = new ArrayList<SubActivity>();
						if(previousReportingPeriod != null && previousReportingPeriod.size() > 0){
							for(ReportingPeriod previousReporting : previousReportingPeriod ){
								reportingPeriodIds.add(previousReporting.getId());
							}
							previousSubActivities = subActivityService.getSubActivitiesByReportingPeriodsAndLeadAgency(reportingPeriodIds, user.getAgencyId());
							for(SubActivity activity : activities){
								for(SubActivity subActivity : previousSubActivities){
									if(activity.getId().equals(subActivity.getId())){
										previousSubActivities.remove(subActivity);
										break;
									}
								}
							}
						}
						myTotalActivities = activities.size() + previousSubActivities.size();
						List<StatusTracking> myStatusTrackings = statusTrackingService.findByReportingPeriodAndAgency(period, agency);
						if (myStatusTrackings != null && myStatusTrackings.size() > 0) {
							for (StatusTracking statusTracking : myStatusTrackings) {
								if (statusTracking.isReworkRequired()) {
									if (statusTracking.isComplete()) {
										myReadyForReview++;
									}
									myNeedMoreInfo++;
									mySentForReview++;
								} else if (statusTracking.getReviewStatus() == 1) {
									myApproved++;
									mySentForReview++;
								} else if (statusTracking.isComplete() && !statusTracking.isSentForReview()) {
									myReadyForReview++;
								} else if (statusTracking.isSentForReview())  {
									mySentForReview++;
								}
							}
						} 
						
						
						int partnerUpdates = 0;
						int reviewed = 0;
						int approved = 0;
						int needMoreInfo = 0;
						int sentForRework = 0;
						int notReviewed = 0;
						List<SubActivity> allSubActivities = new ArrayList<SubActivity>();
						allSubActivities.addAll(previousSubActivities);
						allSubActivities.addAll(activities);
						for (SubActivity subActivity : allSubActivities) {
							List<StatusTracking> partnerStatusTrackings = statusTrackingService.findBysubActivityAndReportingPeriodAndUserLevel(subActivity, period, 1);
							if (partnerStatusTrackings != null) {
								for (StatusTracking partnerTracking : partnerStatusTrackings) {
									if(partnerTracking.isSentForReview() || partnerTracking.isReworkRequired()){
										partnerUpdates++;
										if (partnerTracking.getReviewStatus() == 0) {
											notReviewed++;
										} else if (partnerTracking.getReviewStatus() == 1) {
											approved++;
											reviewed++;
										} else if (partnerTracking.isReworkRequired() && !partnerTracking.isSentForReview()) {
											reviewed++;
											needMoreInfo++;
											sentForRework++;
										} else {
											reviewed++;
											needMoreInfo++;
										}
									}
								}
							}
						}
						
						int notReadyForReview = (myTotalActivities - mySentForReview - myReadyForReview);
						if (notReadyForReview < 0) {
							notReadyForReview = 0;
						}
						
						tableBody += "<tr style='text-align: center;'><td>" + (index) + "</td><td>" +  period.getYear() + " - " + period.getName() + "</td>";
						tableBody += "<td>" + activities.size() + "</td>";
						tableBody += "<td>" + (myTotalActivities - activities.size()) + "</td>";
						tableBody += "<td>" + myTotalActivities + "</td>";
						tableBody += "<td>" + myReadyForReview + "</td>"; 
						tableBody += "<td>" + notReadyForReview + "</td>"; 
						tableBody += "<td>" + mySentForReview + "</td>"; 
						tableBody += "<td>" + myApproved + "</td>"; 
						tableBody += "<td>" + myNeedMoreInfo + "</td>"; 
						tableBody += "</tr>";
						
						partnarTableBody += "<tr style='text-align: center;'><td>" + (index++) + "</td><td>" +  period.getYear() + " - " + period.getName() + "</td>";
						partnarTableBody += "<td>" + partnerUpdates + "</td>"; 
						partnarTableBody += "<td>" + reviewed + "</td>"; 
						partnarTableBody += "<td>" + approved + "</td>"; 
						partnarTableBody += "<td>" + needMoreInfo + "</td>"; 
						partnarTableBody += "<td>" + sentForRework + "</td>"; 
						partnarTableBody += "<td>" + notReviewed + "</td>"; 
						partnarTableBody += "</tr>";
						
					}
					notificationDTO = new NotificationDTO();
					notificationDTO.setTitle("reviewer");
					notificationDTO.setMessage(tableBody);
					notificationDTO.setType(partnarTableBody);
				}
			} else if (userRole.getName().equals("STATUS_UPDATER") && agency != null) {
				String tableBody = "";
				int index = 1;
				List<ReportingPeriod> reportingPeriods = reportingPeriodService.getReportingPeriodListByStatusOpen();
				if (reportingPeriods != null && reportingPeriods.size() > 0) {
					for (ReportingPeriod period : reportingPeriods) {
						List<SubActivity> activities = subActivityService.getAllSubActivitiesByOpenedReportingPeriodAndPartnerAgency(period.getId(), user.getAgencyId());
						
						int readyForReview = 0;
						int sentForReview = 0;
						int approved = 0;
						int needMoreInfo = 0;
						int outStandingInfo = 0;
						int totalActivities = 0;
						List<ReportingPeriod> previousReportingPeriod = reportingPeriodService.getPreviousReportingPeriodByCurrentReportingPeriod(period.getStartdate());
						
						List<Integer> reportingPeriodIds = new ArrayList<Integer>();
						
						if(previousReportingPeriod != null && previousReportingPeriod.size() > 0){
							for(ReportingPeriod previousReporting : previousReportingPeriod ){
								reportingPeriodIds.add(previousReporting.getId());
							}
						}
						List<SubActivity> previousSubActivities = new ArrayList<SubActivity>();
						if(reportingPeriodIds.size() > 0){
							previousSubActivities = subActivityService.getSubActivitiesByReportingPeriodsAndPartnerAgency(reportingPeriodIds, user.getAgencyId());
							for(SubActivity activity : activities){
								for(SubActivity subActivity : previousSubActivities){
									if(activity.getId().equals(subActivity.getId())){
										previousSubActivities.remove(subActivity);
										break;
									}
								}
							}
						}
						totalActivities = activities.size() + previousSubActivities.size();
						
						List<StatusTracking> outstandingPartnerStatusTrackings = statusTrackingService.findByReportingPeriodAndAgency(period, agency);
						if (outstandingPartnerStatusTrackings != null && outstandingPartnerStatusTrackings.size() > 0) {
							for (StatusTracking statusTracking : outstandingPartnerStatusTrackings) {
								if (statusTracking.isReworkRequired()) {
									if (statusTracking.isComplete()) {
										readyForReview++;
									} 
									needMoreInfo++;
									sentForReview++;
								} else if (statusTracking.getReviewStatus() == 1) {
									approved++;
									sentForReview++;
								} else if (statusTracking.isComplete() && !statusTracking.isSentForReview()) {
									readyForReview++;
								} else if (statusTracking.isSentForReview())  {
									sentForReview++;
								}
							}
						} 
						
						int notReadyForReview = (totalActivities - sentForReview - readyForReview);
						if (notReadyForReview < 0) {
							notReadyForReview = 0;
						}
						outStandingInfo = previousSubActivities.size();
						
						tableBody += "<tr style='text-align: center;'><td>" + (index++) + "</td><td>" +  period.getYear() + " - " + period.getName() + "</td>";
						tableBody += "<td>" + activities.size() + "</td>";
						tableBody += "<td>" + outStandingInfo + "</td>";
						tableBody += "<td>" + totalActivities + "</td>";
						tableBody += "<td>" + readyForReview + "</td>"; 
						tableBody += "<td>" + notReadyForReview + "</td>"; 
						tableBody += "<td>" + sentForReview + "</td>"; 
						tableBody += "<td>" + approved + "</td>"; 
						tableBody += "<td>" + needMoreInfo + "</td>"; 
						tableBody += "</tr>";
					}
					notificationDTO = new NotificationDTO();
					notificationDTO.setTitle("partner");
					notificationDTO.setMessage(tableBody);
				}
			} else if (userRole.getName().equals("STATUS_APPROVER") && agency != null) {
				String tableBody = "";
				int index = 1;
				List<ReportingPeriod> reportingPeriods = reportingPeriodService.getReportingPeriodListByStatusOpenAndClosed();
				if (reportingPeriods != null && reportingPeriods.size() > 0) {
					for (ReportingPeriod period : reportingPeriods) {
						List<StatusTracking> leadStatusTrackings = statusTrackingService.findByReportingPeriodAndSentForReviewOrReviewStatusAndUserLevel(period, true, -1, 2);
						if (leadStatusTrackings != null && leadStatusTrackings.size() > 0) {
							
							int reviewed = 0;
							int approved = 0;
							int needMoreInfo = 0;
							int sentForRework = 0;
							int notReviewed = 0;
							
							for (StatusTracking leadTracking : leadStatusTrackings) {
								if (leadTracking.getReviewStatus() == 0) {
									notReviewed++;
								} else if (leadTracking.getReviewStatus() == 1) {
									approved++;
									reviewed++;
								} else if (leadTracking.isReworkRequired() && !leadTracking.isSentForReview()) {
									reviewed++;
									needMoreInfo++;
									sentForRework++;
								} else {
									reviewed++;
									needMoreInfo++;
								}
							}
							
							tableBody += "<tr style='text-align: center;'><td>" + (index++) + "</td><td>" +  period.getYear() + " - " + period.getName() + "</td>";
							tableBody += "<td>" + leadStatusTrackings.size() + "</td>";
							tableBody += "<td>" + reviewed + "</td>"; 
							tableBody += "<td>" + approved + "</td>"; 
							tableBody += "<td>" + needMoreInfo + "</td>"; 
							tableBody += "<td>" + sentForRework + "</td>"; 
							tableBody += "<td>" + notReviewed + "</td>"; 
							tableBody += "</tr>";
						}
					}
					if (tableBody != null && !tableBody.isEmpty()) {
						notificationDTO = new NotificationDTO();
						notificationDTO.setTitle("approver");
						notificationDTO.setMessage(tableBody);
					}
				}
			}
		}
		return notificationDTO;
	}
	
	
	@RequestMapping(value = "getDataCapturingDashboardGraphCounts", method = RequestMethod.GET)
	public @ResponseBody List<DashboardDTO> getDataCapturingDashboardGraphCounts(@RequestParam("year")String year){
		User user = userService.findByUsername(PrincipalUtil.getPrincipal());
		Agency agency = agencyService.findByLoginUser(PrincipalUtil.getPrincipal());
		List<DashboardDTO> listDashboardDTOs = new ArrayList<DashboardDTO>();
		List<ReportingPeriod> listReportingPeriods = reportingPeriodService.findAllByYear(year);
		List<UserRole> userRoles =  user.getUserRoles();
		for (UserRole userRole : userRoles) {
			if (userRole.getName().equals("STATUS_UPDATER")  && agency != null) {
				for (ReportingPeriod reportingPeriod : listReportingPeriods) {
					
					DashboardDTO dashboardDTO = new DashboardDTO();
					dashboardDTO.setReportingPeriod(reportingPeriod.getYear() + " - " + reportingPeriod.getName());
					boolean isOutstanding = false;
					List<SubActivity> activities = subActivityService.getAllSubActivitiesByOpenedReportingPeriodAndPartnerAgency(reportingPeriod.getId(), user.getAgencyId());
					List<StatusTracking> listStatusTrackings =statusTrackingService.findByReportingPeriodAndAgency(reportingPeriod, agency);
							
					List<Integer> reportingPeriodIds = new ArrayList<Integer>();
					List<SubActivity> previousSubActivities = new ArrayList<SubActivity>();
					List<ReportingPeriod> previousReportingPeriod = reportingPeriodService.getPreviousReportingPeriodByCurrentReportingPeriod(reportingPeriod.getStartdate());
					if(previousReportingPeriod != null && previousReportingPeriod.size() > 0){
						for(ReportingPeriod previousReporting : previousReportingPeriod ){
							reportingPeriodIds.add(previousReporting.getId());
						}
					}
					if(reportingPeriodIds.size() > 0){
						previousSubActivities = subActivityService.getSubActivitiesByReportingPeriodsAndPartnerAgency(reportingPeriodIds, user.getAgencyId());
						isOutstanding = true;
					}
					
					Integer updated = 0 ;
					Integer notUpdated = 0 ;
					Integer outstandingUpdated = 0 ;
					Integer outstandingNotUpdated = 0 ;
					
					for(SubActivity subActivity : activities){
						boolean isUpdated = false;
						for(StatusTracking statusTracking : listStatusTrackings){
							if(subActivity.getId().equals(statusTracking.getSubActivity().getId())){
								if( statusTracking.isComplete()){
									updated++;
									isUpdated = true;
								}
								listStatusTrackings.remove(statusTracking);
								break;
							}
						}
						if(!isUpdated){
							notUpdated++;
						}
						if(previousSubActivities != null){
							for(SubActivity subActivity2 : previousSubActivities){
								if(subActivity.getId().equals(subActivity2.getId())){
									previousSubActivities.remove(subActivity2);
									break;
								}
							}
						}
					}
					for(SubActivity subActivity : previousSubActivities){
						boolean isUpdated = false;
						for(StatusTracking statusTracking : listStatusTrackings){
							if(subActivity.getId().equals(statusTracking.getSubActivity().getId())){
								if(statusTracking.isComplete()){
									outstandingUpdated++;
									isUpdated = true;
								}
								listStatusTrackings.remove(statusTracking);
								break;
							}
						}
						if(!isUpdated){
							outstandingNotUpdated++;
						}
					}
					if(isOutstanding && listStatusTrackings.size() > 0){
						outstandingUpdated = outstandingUpdated + listStatusTrackings.size();
					}else if(!isOutstanding && listStatusTrackings.size() > 0){
						updated = updated + listStatusTrackings.size();
					}
					dashboardDTO.setUpdated(updated);
					dashboardDTO.setNotUpdated(notUpdated);
					if(!reportingPeriod.getReportingStatus().equals("Not-Started")){
						dashboardDTO.setOutstandingUpdated(outstandingUpdated);
						dashboardDTO.setOutstandingNotUpdated(outstandingNotUpdated);
					}
					listDashboardDTOs.add(dashboardDTO);
				}
			}else 
				if (userRole.getName().equals("STATUS_REVIEWER") && agency != null) {
					for (ReportingPeriod reportingPeriod : listReportingPeriods) {
						
						DashboardDTO dashboardDTO = new DashboardDTO();
						dashboardDTO.setReportingPeriod(reportingPeriod.getYear() + " - " + reportingPeriod.getName());
						boolean isOutstanding = false;
						List<SubActivity> activities = subActivityService.getAllSubActivityByReportingPeriodIdAndAgencyId(reportingPeriod.getId(), user.getAgencyId());
						List<StatusTracking> listStatusTrackings =statusTrackingService.findByReportingPeriodAndAgency(reportingPeriod, agency);
								
						List<Integer> reportingPeriodIds = new ArrayList<Integer>();
						List<SubActivity> previousSubActivities = new ArrayList<SubActivity>();
						List<ReportingPeriod> previousReportingPeriod = reportingPeriodService.getPreviousReportingPeriodByCurrentReportingPeriod(reportingPeriod.getStartdate());
						if(previousReportingPeriod != null && previousReportingPeriod.size() > 0){
							for(ReportingPeriod previousReporting : previousReportingPeriod ){
								reportingPeriodIds.add(previousReporting.getId());
							}
						}
						if(reportingPeriodIds.size() > 0){
							previousSubActivities = subActivityService.getSubActivitiesByReportingPeriodsAndLeadAgency(reportingPeriodIds, user.getAgencyId());
							isOutstanding = true;
						}
						
						Integer updated = 0 ;
						Integer notUpdated = 0 ;
						Integer outstandingUpdated = 0 ;
						Integer outstandingNotUpdated = 0 ;
						
						for(SubActivity subActivity : activities){
							boolean isUpdated = false;
							for(StatusTracking statusTracking : listStatusTrackings){
								if(subActivity.getId().equals(statusTracking.getSubActivity().getId())){
									updated++;
									listStatusTrackings.remove(statusTracking);
									isUpdated = true;
									break;
								}
							}
							if(!isUpdated){
								notUpdated++;
							}
							if(previousSubActivities != null){
								for(SubActivity subActivity2 : previousSubActivities){
									if(subActivity.getId().equals(subActivity2.getId())){
										previousSubActivities.remove(subActivity2);
										break;
									}
								}
							}
						}
						for(SubActivity subActivity : previousSubActivities){
							boolean isUpdated = false;
							for(StatusTracking statusTracking : listStatusTrackings){
								if(subActivity.getId().equals(statusTracking.getSubActivity().getId())){
									outstandingUpdated++;
									listStatusTrackings.remove(statusTracking);
									isUpdated = true;
									break;
								}
							}
							if(!isUpdated){
								outstandingNotUpdated++;
							}
						}
						if(isOutstanding && listStatusTrackings.size() > 0){
							outstandingUpdated = outstandingUpdated + listStatusTrackings.size();
						}else if(!isOutstanding && listStatusTrackings.size() > 0){
							updated = updated + listStatusTrackings.size();
						}
						
						dashboardDTO.setNotUpdated(notUpdated);
						if(!reportingPeriod.getReportingStatus().equals("Not-Started")){
							dashboardDTO.setUpdated(updated);
							dashboardDTO.setOutstandingUpdated(outstandingUpdated);
							dashboardDTO.setOutstandingNotUpdated(outstandingNotUpdated);
						}
						listDashboardDTOs.add(dashboardDTO);
					}
				}
			
		}
		
		
		
		return listDashboardDTOs;
	}
	
	@RequestMapping(value = "getDataReviewedDashboardGraphCounts", method = RequestMethod.GET)
	public @ResponseBody List<DashboardDTO> getDataReviewedDashboardGraphCounts(@RequestParam("year")String year){
		User user = userService.findByUsername(PrincipalUtil.getPrincipal());
		Agency agency = agencyService.findByLoginUser(PrincipalUtil.getPrincipal());
		List<DashboardDTO> listDashboardDTOs = new ArrayList<DashboardDTO>();
		List<ReportingPeriod> listReportingPeriods = reportingPeriodService.findAllByYear(year);
		List<UserRole> userRoles =  user.getUserRoles();
		for (UserRole userRole : userRoles) {
			if (userRole.getName().equals("STATUS_REVIEWER") && agency != null) {
				for (ReportingPeriod reportingPeriod : listReportingPeriods) {
					
					DashboardDTO dashboardDTO = new DashboardDTO();
					dashboardDTO.setReportingPeriod(reportingPeriod.getYear() + " - " + reportingPeriod.getName());
					List<SubActivity> activities = subActivityService.getAllSubActivityByReportingPeriodIdAndAgencyId(reportingPeriod.getId(), user.getAgencyId());
							
					List<Integer> reportingPeriodIds = new ArrayList<Integer>();
					List<SubActivity> previousSubActivities = new ArrayList<SubActivity>();
					List<ReportingPeriod> previousReportingPeriod = reportingPeriodService.getPreviousReportingPeriodByCurrentReportingPeriod(reportingPeriod.getStartdate());
					if(previousReportingPeriod != null && previousReportingPeriod.size() > 0){
						for(ReportingPeriod previousReporting : previousReportingPeriod ){
							reportingPeriodIds.add(previousReporting.getId());
						}
						previousSubActivities = subActivityService.getSubActivitiesByReportingPeriodsAndLeadAgency(reportingPeriodIds, user.getAgencyId());
						for(SubActivity activity : activities){
							for(SubActivity subActivity : previousSubActivities){
								if(activity.getId().equals(subActivity.getId())){
									previousSubActivities.remove(subActivity);
									break;
								}
							}
						}
					}
					
					Integer expected = 0;
					Integer updated = 0 ;
					Integer notUpdated = 0 ;
					Integer outstandingExpected = 0;
					Integer outstandingUpdated = 0 ;
					Integer outstandingNotUpdated = 0 ;
					
					for(SubActivity subActivity : activities){
						if(subActivity.getPartnerAgency() != null){
							expected += subActivity.getPartnerAgency().size();
						}
						List<StatusTracking> partnerStatusTrackings = statusTrackingService.findBysubActivityAndReportingPeriodAndUserLevel(subActivity, reportingPeriod, 1);
						if (partnerStatusTrackings != null) {
							for(StatusTracking statusTracking : partnerStatusTrackings){
								if(statusTracking.isSentForReview() || statusTracking.isReworkRequired()){
									if(statusTracking.getReviewStatus() != 0){
										updated++;
									}else{
										notUpdated++;
									}
								}
							}
						}
					}
					for(SubActivity subActivity : previousSubActivities){
						if(subActivity.getPartnerAgency() != null){
							outstandingExpected += subActivity.getPartnerAgency().size();
						}
						List<StatusTracking> partnerStatusTrackings = statusTrackingService.findBysubActivityAndReportingPeriodAndUserLevel(subActivity, reportingPeriod, 1);
						if (partnerStatusTrackings != null) {
							for(StatusTracking statusTracking : partnerStatusTrackings){
								if(statusTracking.isSentForReview() || statusTracking.isReworkRequired()){
									if(statusTracking.getReviewStatus() != 0){
										outstandingUpdated++;
									}else{
										outstandingNotUpdated++;
									}
								}
							}
						}
					}
					dashboardDTO.setExpected(expected);
					if(!reportingPeriod.getReportingStatus().equals("Not-Started")){
						dashboardDTO.setUpdated(updated);
						dashboardDTO.setNotUpdated(notUpdated);
						dashboardDTO.setOutstandingExpected(outstandingExpected);
						dashboardDTO.setOutstandingUpdated(outstandingUpdated);
						dashboardDTO.setOutstandingNotUpdated(outstandingNotUpdated);
					}
					listDashboardDTOs.add(dashboardDTO);
				}
			}
		}
		return listDashboardDTOs;
	}
	
	@RequestMapping(value = "getApproverDashboardGraphCounts", method = RequestMethod.GET)
	public @ResponseBody List<ActivityDashboardDTO> getApproverDashboardGraphCounts(@RequestParam("year")String year){
		User user = userService.findByUsername(PrincipalUtil.getPrincipal());
		List<ActivityDashboardDTO> listActivityDashboardDTOs = new ArrayList<ActivityDashboardDTO>();
		List<ReportingPeriod> listReportingPeriods = reportingPeriodService.findAllByYear(year);
		List<UserRole> userRoles =  user.getUserRoles();
		for (UserRole userRole : userRoles) {
			if (!userRole.getName().equals("STATUS_UPDATER") && !userRole.getName().equals("STATUS_REVIEWER")) {
				for (ReportingPeriod reportingPeriod : listReportingPeriods) {
					List<SubActivity> subActivities = subActivityService.getSubActivitiesByOpenedReportingPeriod(reportingPeriod.getId());
					List<SubActivity> completedSubActivities = subActivityService.findByCompletedReportingPeriod(reportingPeriod);
					List<Integer> previousReportingPeriodIds = new ArrayList<Integer>();
					List<SubActivity> previousSubActivities = new ArrayList<SubActivity>();
					List<ReportingPeriod> previousReportingPeriod = reportingPeriodService.getPreviousReportingPeriodByCurrentReportingPeriod(reportingPeriod.getStartdate());
					if(previousReportingPeriod != null && previousReportingPeriod.size() > 0){
						for(ReportingPeriod previousReporting : previousReportingPeriod){
							previousReportingPeriodIds.add(previousReporting.getId());
						}
						previousSubActivities = subActivityService.getSubActivitiesByReportingPeriodsAndNotCompleted(previousReportingPeriodIds);
						for(SubActivity activity : subActivities){
							for(SubActivity subActivity : previousSubActivities){
								if(activity.getId().equals(subActivity.getId())){
									previousSubActivities.remove(subActivity);
									break;
								}
							}
						}
					}
					
					List<Integer> futureReportingPeriodIds = new ArrayList<Integer>();
					List<ReportingPeriod> listFutureReportingPeriods = reportingPeriodService.getNextReportingPeriodByCurrentReportingPeriod(reportingPeriod.getStartdate());
					if(listFutureReportingPeriods != null && listFutureReportingPeriods.size() > 0){
						for(ReportingPeriod futureReporting : listFutureReportingPeriods){
							futureReportingPeriodIds.add(futureReporting.getId());
						}
					}
					
					Integer currentTotal = 0;
					Integer toBeCompleted = 0;
					Integer completed = 0;
					Integer notCompleted = 0;
					Integer outstandingTotal = 0;
					Integer outstandingCompleted = 0;
					Integer outstandingNotCompleted = 0;
					
					List<SubActivity> toBeRemoved = new ArrayList<SubActivity>();
					
					for(SubActivity subActivity : subActivities){
						boolean isToBeCompleted = false;
						if(futureReportingPeriodIds.size() > 0){
							SubActivity futureSubActivity = subActivityService.getSubActivitiesBySubActivityAndReportingPeriods(subActivity, futureReportingPeriodIds);
							if(futureSubActivity == null){
								toBeCompleted++;
								if(subActivity.isApproveORCompleteStatus()){
									for(SubActivity completedSubActivity : completedSubActivities){
										if(subActivity.getId().equals(completedSubActivity.getId()) 
												&& completedSubActivity.getCompletedReportingPeriod().getId().equals(reportingPeriod.getId())){
											completed++;
											completedSubActivities.remove(completedSubActivity);
											break;
										}
									}
								}else{
									notCompleted++;
								}
								isToBeCompleted = true;
							}
							
						}
						if(!isToBeCompleted && subActivity.isApproveORCompleteStatus()){
							boolean isCompleted = false;
							for(SubActivity completedSubActivity : completedSubActivities){
								if(subActivity.getId().equals(completedSubActivity.getId()) 
										&& completedSubActivity.getCompletedReportingPeriod().getId().equals(reportingPeriod.getId())){
									completed++;
									completedSubActivities.remove(completedSubActivity);
									isCompleted = true;
									break;
								}
							}
							if (!isCompleted) {
								if(subActivity.getCompletedReportingPeriod() != null){
									if (subActivity.getCompletedReportingPeriod().getId().equals(reportingPeriod.getId())) {
										completed++;
									}
								}
								else if (subActivity.getCompletedReportingPeriod() != null){ 
									if (subActivity.getCompletedReportingPeriod().getStartdate().before(reportingPeriod.getStartdate())){
										toBeRemoved.add(subActivity);
									}
								}
							}
						} 
					}
					currentTotal = subActivities.size() - toBeRemoved.size();
					outstandingNotCompleted = previousSubActivities.size();
					outstandingCompleted = completedSubActivities.size();
					outstandingTotal = outstandingNotCompleted + outstandingCompleted;
					
					ActivityDashboardDTO activityDashboardDTO = new ActivityDashboardDTO();
					activityDashboardDTO.setReportingPeriod(reportingPeriod.getYear() + " - " + reportingPeriod.getName());
					activityDashboardDTO.setCurrentTotal(currentTotal);
					if(!reportingPeriod.getReportingStatus().equals("Not-Started")){
						activityDashboardDTO.setCompleted(completed);
						activityDashboardDTO.setNotCompleted(notCompleted);
						activityDashboardDTO.setToBeCompleted(toBeCompleted);
						activityDashboardDTO.setOutstandingTotal(outstandingTotal);
						activityDashboardDTO.setOutstandingCompleted(outstandingCompleted);
						activityDashboardDTO.setOutstandingNotCompleted(outstandingNotCompleted);
					}
					listActivityDashboardDTOs.add(activityDashboardDTO);
				}
			}
		}
		return listActivityDashboardDTOs;
	}
	@RequestMapping(value = "getApproverDashboardGraphCountsByReportingPeriod", method = RequestMethod.GET)
	public @ResponseBody List<ActivityDashboardDTO> getApproverDashboardGraphCountsByReportingPeriod(@RequestParam("year")String year, @RequestParam("quarter")String quarter){
		User user = userService.findByUsername(PrincipalUtil.getPrincipal());
		List<ActivityDashboardDTO> listActivityDashboardDTOs = new ArrayList<ActivityDashboardDTO>();
		ReportingPeriod reportingPeriod = reportingPeriodService.getByYearAndName(year, quarter);
		List<UserRole> userRoles =  user.getUserRoles();
		for (UserRole userRole : userRoles) {
			if (!userRole.getName().equals("STATUS_UPDATER") && !userRole.getName().equals("STATUS_REVIEWER")) {
				List<SubActivity> subActivities = subActivityService.getSubActivitiesByOpenedReportingPeriod(reportingPeriod.getId());
				List<SubActivity> completedSubActivities = subActivityService.findByCompletedReportingPeriod(reportingPeriod);
				List<Integer> previousReportingPeriodIds = new ArrayList<Integer>();
				List<SubActivity> previousSubActivities = new ArrayList<SubActivity>();
				List<ReportingPeriod> previousReportingPeriod = reportingPeriodService.getPreviousReportingPeriodByCurrentReportingPeriod(reportingPeriod.getStartdate());
				if(previousReportingPeriod != null && previousReportingPeriod.size() > 0){
					for(ReportingPeriod previousReporting : previousReportingPeriod){
						previousReportingPeriodIds.add(previousReporting.getId());
					}
					previousSubActivities = subActivityService.getSubActivitiesByReportingPeriodsAndNotCompleted(previousReportingPeriodIds);
					for(SubActivity activity : subActivities){
						for(SubActivity subActivity : previousSubActivities){
							if(activity.getId().equals(subActivity.getId())){
								previousSubActivities.remove(subActivity);
								break;
							}
						}
					}
				}
				
				List<Integer> futureReportingPeriodIds = new ArrayList<Integer>();
				List<ReportingPeriod> listFutureReportingPeriods = reportingPeriodService.getNextReportingPeriodByCurrentReportingPeriod(reportingPeriod.getStartdate());
				if(listFutureReportingPeriods != null && listFutureReportingPeriods.size() > 0){
					for(ReportingPeriod futureReporting : listFutureReportingPeriods){
						futureReportingPeriodIds.add(futureReporting.getId());
					}
				}
				
//				List<SubActivity> toBeRemoved = new ArrayList<SubActivity>();
				boolean toBeRemovedFlag = true;
				ActivityDashboardDTO activityDashboardDTO = null;
			
				for(SubActivity subActivity : subActivities){
					if(activityDashboardDTO == null){
						activityDashboardDTO = new ActivityDashboardDTO();
						activityDashboardDTO.setReportingPeriod(subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getName());
					}else if(!subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getName().equals(activityDashboardDTO.getReportingPeriod())){
						listActivityDashboardDTOs.add(activityDashboardDTO);
						activityDashboardDTO = new ActivityDashboardDTO();
						activityDashboardDTO.setReportingPeriod(subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getName());
					}
					boolean isToBeCompleted = false;
					if(futureReportingPeriodIds.size() > 0){
						SubActivity futureSubActivity = subActivityService.getSubActivitiesBySubActivityAndReportingPeriods(subActivity, futureReportingPeriodIds);
						if(futureSubActivity == null){
//							toBeCompleted++;
							if(!reportingPeriod.getReportingStatus().equals("Not-Started")){
								activityDashboardDTO.setToBeCompleted(activityDashboardDTO.getToBeCompleted() + 1);
							}
							if(subActivity.isApproveORCompleteStatus()){
								for(SubActivity completedSubActivity : completedSubActivities){
									if(subActivity.getId().equals(completedSubActivity.getId()) 
											&& completedSubActivity.getCompletedReportingPeriod().getId().equals(reportingPeriod.getId())){
//										completed++;
										if(!reportingPeriod.getReportingStatus().equals("Not-Started")){
											activityDashboardDTO.setCompleted(activityDashboardDTO.getCompleted() + 1);
										}
										completedSubActivities.remove(completedSubActivity);
										break;
									}
								}
							}else{
//								notCompleted++;
								if(!reportingPeriod.getReportingStatus().equals("Not-Started")){
									activityDashboardDTO.setNotCompleted(activityDashboardDTO.getNotCompleted() + 1);
								}
							}
							isToBeCompleted = true;
						}
						
					}
					if(!isToBeCompleted && subActivity.isApproveORCompleteStatus()){
						boolean isCompleted = false;
						for(SubActivity completedSubActivity : completedSubActivities){
							if(subActivity.getId().equals(completedSubActivity.getId()) 
									&& completedSubActivity.getCompletedReportingPeriod().getId().equals(reportingPeriod.getId())){
//								completed++;
								if(!reportingPeriod.getReportingStatus().equals("Not-Started")){
									activityDashboardDTO.setCompleted(activityDashboardDTO.getCompleted() + 1);
								}
								completedSubActivities.remove(completedSubActivity);
								isCompleted = true;
								break;
							}
						}
						if (!isCompleted) {
							if(subActivity.getCompletedReportingPeriod() != null){
								if (subActivity.getCompletedReportingPeriod().getId().equals(reportingPeriod.getId())) {
//									completed++;
									if(!reportingPeriod.getReportingStatus().equals("Not-Started")){
										activityDashboardDTO.setCompleted(activityDashboardDTO.getCompleted() + 1);
									}
								}
							}
							else if (subActivity.getCompletedReportingPeriod() != null){ 
								if (subActivity.getCompletedReportingPeriod().getStartdate().before(reportingPeriod.getStartdate())){
//									toBeRemoved.add(subActivity);
									toBeRemovedFlag = false;
								}
							}
						}
					} 
					if(toBeRemovedFlag)
						activityDashboardDTO.setCurrentTotal(activityDashboardDTO.getCurrentTotal() + 1 );
				}
				if(activityDashboardDTO != null){
					listActivityDashboardDTOs.add(activityDashboardDTO);
				}
				
				if(!reportingPeriod.getReportingStatus().equals("Not-Started")){
					for(SubActivity outstanding : previousSubActivities){
						boolean isExisting = false; 
						for(ActivityDashboardDTO existing : listActivityDashboardDTOs){
							if(existing.getReportingPeriod().equals(outstanding.getKeyActivity().getOutput().getOutcome().getTheme().getName())){
								existing.setOutstandingNotCompleted(existing.getOutstandingNotCompleted() + 1);
								existing.setOutstandingTotal(existing.getOutstandingTotal() + 1);
								isExisting = true;
								break;
							}
						}
						if(!isExisting){
							ActivityDashboardDTO newActivityDashboardDTO = new ActivityDashboardDTO();
							newActivityDashboardDTO.setReportingPeriod(outstanding.getKeyActivity().getOutput().getOutcome().getTheme().getName());
							newActivityDashboardDTO.setOutstandingNotCompleted(1);
							newActivityDashboardDTO.setOutstandingTotal(1);
							listActivityDashboardDTOs.add(newActivityDashboardDTO);
						}
					}
					
					for(SubActivity completed : completedSubActivities){
						boolean isExisting = false; 
						for(ActivityDashboardDTO existing : listActivityDashboardDTOs){
							if(existing.getReportingPeriod().equals(completed.getKeyActivity().getOutput().getOutcome().getTheme().getName())){
								existing.setOutstandingCompleted(existing.getOutstandingCompleted() + 1);
								existing.setOutstandingTotal(existing.getOutstandingTotal() + 1);
								isExisting = true;
								break;
							}
						}
						if(!isExisting){
							ActivityDashboardDTO newActivityDashboardDTO = new ActivityDashboardDTO();
							newActivityDashboardDTO.setReportingPeriod(completed.getKeyActivity().getOutput().getOutcome().getTheme().getName());
							newActivityDashboardDTO.setOutstandingCompleted(1);
							newActivityDashboardDTO.setOutstandingTotal(1);
							listActivityDashboardDTOs.add(newActivityDashboardDTO);
						}
					}
				}
				/*currentTotal = subActivities.size() - toBeRemoved.size();
				outstandingNotCompleted = previousSubActivities.size();
				outstandingCompleted = completedSubActivities.size();
				outstandingTotal = outstandingNotCompleted + outstandingCompleted;*/
				
				/*activityDashboardDTO.setReportingPeriod(reportingPeriod.getYear() + " - " + reportingPeriod.getName());
				activityDashboardDTO.setCurrentTotal(currentTotal);
				if(!reportingPeriod.getReportingStatus().equals("Not-Started")){
					activityDashboardDTO.setCompleted(completed);
					activityDashboardDTO.setNotCompleted(notCompleted);
					activityDashboardDTO.setToBeCompleted(toBeCompleted);
					activityDashboardDTO.setOutstandingTotal(outstandingTotal);
					activityDashboardDTO.setOutstandingCompleted(outstandingCompleted);
					activityDashboardDTO.setOutstandingNotCompleted(outstandingNotCompleted);
				}*/
				
			}
		}
		return listActivityDashboardDTOs;
	}
}
