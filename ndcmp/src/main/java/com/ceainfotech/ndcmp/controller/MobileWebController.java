package com.ceainfotech.ndcmp.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ceainfotech.ndcmp.model.Agency;
import com.ceainfotech.ndcmp.model.AllowedDeviceInfo;
import com.ceainfotech.ndcmp.model.Indicator;
import com.ceainfotech.ndcmp.model.Planning;
import com.ceainfotech.ndcmp.model.ProfileImage;
import com.ceainfotech.ndcmp.model.ReportingPeriod;
import com.ceainfotech.ndcmp.model.StatusTracking;
import com.ceainfotech.ndcmp.model.StatusTrackingLog;
import com.ceainfotech.ndcmp.model.Statuss;
import com.ceainfotech.ndcmp.model.SubActivity;
import com.ceainfotech.ndcmp.model.SubmitForReview;
import com.ceainfotech.ndcmp.model.Target;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.model.UserDeviceSyncInfo;
import com.ceainfotech.ndcmp.model.UserRole;
import com.ceainfotech.ndcmp.repository.ProjectRepository;
import com.ceainfotech.ndcmp.service.AgencyService;
import com.ceainfotech.ndcmp.service.AllowedDeviceService;
import com.ceainfotech.ndcmp.service.CountryService;
import com.ceainfotech.ndcmp.service.IndicatorService;
import com.ceainfotech.ndcmp.service.KeyActivityService;
import com.ceainfotech.ndcmp.service.OutcomeService;
import com.ceainfotech.ndcmp.service.OutputServices;
import com.ceainfotech.ndcmp.service.PlanningServices;
import com.ceainfotech.ndcmp.service.ProfileImageService;
import com.ceainfotech.ndcmp.service.ProjectService;
import com.ceainfotech.ndcmp.service.RegionService;
import com.ceainfotech.ndcmp.service.ReportingPeriodService;
import com.ceainfotech.ndcmp.service.StatesService;
import com.ceainfotech.ndcmp.service.StatusService;
import com.ceainfotech.ndcmp.service.StatusTrackingLogService;
import com.ceainfotech.ndcmp.service.StatusTrackingService;
import com.ceainfotech.ndcmp.service.StrategicPillarService;
import com.ceainfotech.ndcmp.service.SubActivityService;
import com.ceainfotech.ndcmp.service.SubmitForReviewService;
import com.ceainfotech.ndcmp.service.TargetService;
import com.ceainfotech.ndcmp.service.ThemeService;
import com.ceainfotech.ndcmp.service.UserDeviceSyncInfoService;
import com.ceainfotech.ndcmp.service.UserRoleService;
import com.ceainfotech.ndcmp.service.UserService;
import com.ceainfotech.ndcmp.util.AllowedDeviceRegisteration;
/**
 * @author Gowri
 */
@RestController
@RequestMapping(value = "/mobile/restApi/**")
@PropertySource(value = { "classpath:application.properties" })
public class MobileWebController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MobileWebController.class);

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	AllowedDeviceService allowedDeviceService;
	
	@Autowired
	UserDeviceSyncInfoService userDeviceSyncInfoService;

	@Autowired
	UserService userService;
	
	@Autowired
	ProjectService projectService;

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
	AgencyService agencyService;
	
	@Autowired
	PlanningServices planningServices;
	
	@Autowired
	ProfileImageService profileImageService;
	
	@Autowired
	StatusService statusService;
	
	@Autowired
	SubmitForReviewService submitForReviewService;
	
	@Autowired
	StatusTrackingService statusTrackingService;

	@Autowired
	StatusTrackingLogService statusTrackingLogService;
	
	@Autowired
	Environment environment;
	
	/**
	 * modified By :  @author pushpa
	 * 1. Register mobile device with agency within the limit specified in the properties file
	 * @param allowedDeviceRegisteration
	 * @return
	 */
	@RequestMapping(value = "device/registration", method = RequestMethod.POST)
	public @ResponseBody String registerationDevice(@RequestBody AllowedDeviceRegisteration allowedDeviceRegisteration) {
		LOGGER.info("Getting all the allowed device registration deails : {}");
		JSONObject json = new JSONObject();
		try{
			if((allowedDeviceRegisteration.getMobileName() != null && !allowedDeviceRegisteration.getMobileName().isEmpty() && allowedDeviceRegisteration.getMobileName().length() > 0)
				&& (allowedDeviceRegisteration.getStaffId() != null && !allowedDeviceRegisteration.getStaffId().isEmpty() && allowedDeviceRegisteration.getStaffId().length() > 0)
				&& (allowedDeviceRegisteration.getOsVersion() != null && !allowedDeviceRegisteration.getOsVersion().isEmpty() && allowedDeviceRegisteration.getOsVersion().length() > 0) 
				&& (allowedDeviceRegisteration.getSdkVersion() != null && !allowedDeviceRegisteration.getSdkVersion().isEmpty() && allowedDeviceRegisteration.getSdkVersion().length() > 0)
				&& (allowedDeviceRegisteration.getDeviceId() != null && !allowedDeviceRegisteration.getDeviceId().isEmpty() && allowedDeviceRegisteration.getDeviceId().length() > 0)
				&& (allowedDeviceRegisteration.getAgencyCode() != null && !allowedDeviceRegisteration.getAgencyCode().isEmpty() && allowedDeviceRegisteration.getAgencyCode().length() > 0)) 
			{
				String agencyCode = allowedDeviceRegisteration.getAgencyCode();
				List<String> agencyStringList = Arrays.asList(agencyCode.split(","));
				StringBuilder notExistedAgencyCodes = new StringBuilder();
				String agencyIds = "";
				for (String stringAgencyCode : agencyStringList) {
					Agency agency = agencyService.findByAgencyCode(stringAgencyCode);
					if (agency == null){
						notExistedAgencyCodes.append(stringAgencyCode);
						notExistedAgencyCodes.append(",");
					}else{
						if (agencyIds.isEmpty()) {
							agencyIds = "" + agency.getId();
						} else {
							agencyIds += "," + agency.getId();	
						}
					}
				}
				if (notExistedAgencyCodes != null && notExistedAgencyCodes.length() > 0){
					notExistedAgencyCodes.deleteCharAt(notExistedAgencyCodes.length() - 1);
					json.put("result", false);
					json.put("msg", "Invalid Agency Code : " + notExistedAgencyCodes);
				}else{
					
					AllowedDeviceInfo existingDeviceInfo = allowedDeviceService.findByDeviceIdAndStaffId(allowedDeviceRegisteration.getDeviceId(), allowedDeviceRegisteration.getStaffId());
					if (existingDeviceInfo == null) {
						AllowedDeviceInfo staffDeviceInfo = allowedDeviceService.findByStaffId(allowedDeviceRegisteration.getStaffId());
						if (staffDeviceInfo == null) {
							existingDeviceInfo = allowedDeviceService.findByDeviceId(allowedDeviceRegisteration.getDeviceId());
							if (existingDeviceInfo == null) {
								Integer allowedDeviceCount = Integer.parseInt(environment.getRequiredProperty("alloweddevice.count"));
								if (allowedDeviceService.getActiveMobileDeiveCount(true) <= allowedDeviceCount) {
									
									AllowedDeviceInfo allowedDeviceInfo = new AllowedDeviceInfo();
									Date date = new Date();
									SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
									
									allowedDeviceInfo.setAgencyIds(agencyIds);
									allowedDeviceInfo.setAgencyCodes(agencyCode);
									allowedDeviceInfo.setStaffId(allowedDeviceRegisteration.getStaffId());
									allowedDeviceInfo.setMobileName(allowedDeviceRegisteration.getMobileName());
									allowedDeviceInfo.setOsVersion(allowedDeviceRegisteration.getOsVersion());
									allowedDeviceInfo.setDeviceId(allowedDeviceRegisteration.getDeviceId());
									allowedDeviceInfo.setSdkVersion(allowedDeviceRegisteration.getSdkVersion());
									allowedDeviceInfo.setRegisterationDateTime(dateformat.format(date));
									allowedDeviceInfo.setDeviceStatus(false);
									allowedDeviceService.deviceRegisteration(allowedDeviceInfo);
									json.put("result", true);
									json.put("msg", "Your device registration process is pending with System Admin, try again later");
								}else {
									json.put("result", false);
									json.put("msg", "Device registration reached the maximum, contact System Admin");
								}
								
							} else {
								json.put("result", false);
								json.put("msg", "Your device is already registered with another Staff Id, contact System Admin");
							}
						} else {
							json.put("result", false);
							json.put("msg", "Staff Id already registered with another device. To re-register, contact System Admin");
						}
					} else if (existingDeviceInfo.isDeviceStatus()) {
						json.put("result", true);
						json.put("msg", "Your device is already registered and approved");
					} else {
						json.put("result", true);
						json.put("msg", "Your device registration process is pending with System Admin, try again later");
					}
				}
			}else{
				json.put("result", false);
				json.put("msg", "Invalid Data");
			}
		}catch(JSONException jsonException){
			jsonException.printStackTrace();
		}
		return json.toString();
	}
	
	/**
	 * @author pushpa
	 * 2. Device Status Verification
	 * @param deviceId
	 * @return
	 */
	@RequestMapping(value = "device/deviceRegistrationStatus", method = RequestMethod.POST)
	public @ResponseBody String deviceRegistrationStatus(@RequestBody String registrationString){
		LOGGER.debug("deviceRegistrationStatus web service");
		JSONObject registrationStatusResult = new JSONObject();
		JSONObject requestedString = new JSONObject(registrationString);
		if (requestedString.has("deviceId")){
			String deviceId = requestedString.getString("deviceId");
			if (deviceId != null && !deviceId.trim().isEmpty()){
				AllowedDeviceInfo existingDeviceInfo = allowedDeviceService.findByDeviceId(deviceId);
				if (existingDeviceInfo != null) {
					  if (existingDeviceInfo.isDeviceStatus()) {
						  registrationStatusResult.put("result", true);
						  registrationStatusResult.put("approvedStatus", true);
						  registrationStatusResult.put("msg", "Your device is already registered and approved");
						} else {
							registrationStatusResult.put("result", true);
							registrationStatusResult.put("approvedStatus", false);
							registrationStatusResult.put("msg", "Your device registration process is pending with System Admin, try again later");
						}
				}else{
					registrationStatusResult.put("result", false);
					registrationStatusResult.put("msg", "Your Device is not yet registered");
				}
			}else{
				registrationStatusResult.put("result", false);
				registrationStatusResult.put("msg", "Invalid Data");
			}
		}else{
			registrationStatusResult.put("result", false);
			registrationStatusResult.put("msg", "Invalid Requested String");
		}
		return registrationStatusResult.toString();
	}
	
	/**
	 * @author pushpa / Leo
	 * 3 .Sync Whole data based on the device and agency
	 * @param deviceId
	 * @param agencyCode
	 * @return
	 */
	@RequestMapping(value="initialSyncData", method = RequestMethod.POST)
	public @ResponseBody String initialSyncData(@RequestBody String syncRequestedString){
		LOGGER.debug("Sync Start Time : " + new Date());
		
		JSONObject syncData = new JSONObject();
		JSONObject requestedString = new JSONObject(syncRequestedString);
		
		if (requestedString.has("deviceId")){
			String deviceId = requestedString.getString("deviceId");
			String syncRequestedTime = "";
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			syncRequestedTime = sd.format(new Date());
			
			boolean isApprover = false;
			
			JSONObject mobileWBSyncData = new JSONObject();
			JSONArray statusTrackingArray = new JSONArray();
			JSONArray submitForReviewArray = new JSONArray();
			JSONArray agencyArray = new JSONArray();
			JSONArray reportingPeriodArray = new JSONArray();
			JSONArray statusArray = new JSONArray();
			JSONArray subActivityArray = new JSONArray();
			JSONArray keyActivityArray = new JSONArray();
			JSONArray outputArray = new JSONArray();
			JSONArray outcomeArray = new JSONArray();
			JSONArray themeArray = new JSONArray();
			JSONArray strategyArray = new JSONArray();
			JSONArray listActivityDashboardDTOs = new JSONArray();
			
			Set<Integer> keyActivityArraySet = new HashSet<Integer>();
			Set<Integer> outputArraySet = new HashSet<Integer>();
			Set<Integer> outcomeArraySet = new HashSet<Integer>();
			Set<Integer> themeArraySet = new HashSet<Integer>();
			Set<Integer> strategyArraySet = new HashSet<Integer>();
			Set<Agency> partneringAgencySet = new HashSet<Agency>();
			
			String approveragency = environment.getProperty("approver.agency");//Agency code should be dynamic ,here its showing "NCU"
			if(deviceId != null && !deviceId.isEmpty()){
				AllowedDeviceInfo allowedDeviceInfo = allowedDeviceService.findByDeviceId(deviceId);
				if(allowedDeviceInfo != null){
					if (allowedDeviceInfo.isDeviceStatus()){
//						Start	1. Agency Info ---> 
							List<String> listAgencyString = Arrays.asList((allowedDeviceInfo.getAgencyIds()).split(","));
							List<Integer> listAgencyIds = new ArrayList<Integer>();
							List<Integer> listPartnerIds = new ArrayList<Integer>();
							Set<Agency> listRegisteredAgencies = new HashSet<Agency>();
							for (String agencyCodeString : listAgencyString) {
								if (agencyCodeString != null && !agencyCodeString.trim().isEmpty()) {
									Agency agency = new Agency();
									agency = agencyService.findByAgencyId(Integer.parseInt(agencyCodeString));
									if(agency != null){
//please check the approver code ,its getting failed ====AAGN.equals("NCU")
										if(agency.getCode().equals(approveragency)){
											isApprover = true;
										}
										listRegisteredAgencies.add(agency);
										listAgencyIds.add(agency.getId());
										JSONObject agencyObj = new JSONObject();
										JSONArray userArray = new JSONArray();
										List<User> listUsers = agency.getAgencyAuthority();
										if (listUsers != null && !listUsers.isEmpty() && listUsers.size() > 0){
											for (User user : listUsers) {
												if(user.getUserType().contains("Mobile")){
													JSONObject userobj = new JSONObject();
													userobj.put("userId", user.getId());
													userobj.put("username", user.getUsername());
													userobj.put("password", user.getMobilePassword());
													userobj.put("status", user.getStatus());
													StringBuffer userRolesString = new StringBuffer();
													for (UserRole userRole : user.getUserRoles()) {
														if(userRolesString.length() <= 0){
															userRolesString.append(userRole.getName());
														}else{
															userRolesString.append(",");
															userRolesString.append(userRole.getName());
														}
													}
													userobj.put("userRoles", userRolesString);
													userArray.put(userobj);
												}
											}
										}else{
											LOGGER.debug("No User is mapped with this agency");
										}
										
										agencyObj.put("AgencyId", agency.getId());
										agencyObj.put("AgencyCode", agency.getCode());
										agencyObj.put("UserDetails", userArray );
										agencyObj.put("Status", agency.getStatus());
										agencyArray.put(agencyObj);
									}
								}else{
									LOGGER.debug("Agency Code : " + agencyCodeString + " : Not Found");
								}
							}
//						End	1. Agency Info 
							
//						Start	2. Reporting Period Info  	---> 
							List<ReportingPeriod> listreReportingPeriods = reportingPeriodService.getReportingPeriod();
							if (listreReportingPeriods != null && !listreReportingPeriods.isEmpty()){
								for (ReportingPeriod reportingPeriod : listreReportingPeriods) {
									JSONObject reportingperiodObj = new JSONObject();
									reportingperiodObj.put("ReportingPeriodId", reportingPeriod.getId());
									reportingperiodObj.put("Name", reportingPeriod.getName());
									reportingperiodObj.put("Year", reportingPeriod.getYear());
									reportingperiodObj.put("StartDate", reportingPeriod.getStartdate());
									reportingperiodObj.put("EndDate", reportingPeriod.getEnddate());
									reportingperiodObj.put("Status", reportingPeriod.getReportingStatus());
									reportingperiodObj.put("Active", reportingPeriod.getStatus().equals("ACTIVE") ? 1 : 0);
									reportingPeriodArray.put(reportingperiodObj);
								}
							}
//						End 2. Reporting Period Info
							
//						Start Status Info 	---->
							List<Statuss> listStatus = statusService.listAllStatus();
							if(listStatus != null && listStatus.size() > 0){
								for (Statuss statuss : listStatus) {
									JSONObject statusObject = new JSONObject();
									statusObject.put("StatusId", statuss.getId());
									statusObject.put("Name", statuss.getName());
									statusObject.put("Color", statuss.getStatusColor());
									statusObject.put("Percentage", statuss.getColorPercent());
									statusObject.put("StartRange", statuss.getStartRange());
									statusObject.put("EndRange", statuss.getEndRange());
									statusObject.put("Status", statuss.getStatus());
									statusArray.put(statusObject);
								}
							}
//						End Status Info
							
							List<SubActivity> listSubActivities = subActivityService.findSubActivityByAgencyIds(listAgencyIds);
							if(listSubActivities != null && !listSubActivities.isEmpty()){
								for (SubActivity subActivity : listSubActivities) {
//						Start	3. Sub Activity Info	 ---> 	
									JSONObject subActvitiyObj = new JSONObject();
									subActvitiyObj.put("SubActivityId", subActivity.getId());
									subActvitiyObj.put("SequenceNumber", subActivity.getSequenceNumber());
									subActvitiyObj.put("SubActivityName", subActivity.getSequenceNumber() + ". " + subActivity.getName());
									subActvitiyObj.put("ResponsibleEntity", subActivity.getLeadAgency().getCode());
									subActvitiyObj.put("Status", subActivity.getStatus());
									subActvitiyObj.put("IsCompleted", subActivity.isApproveORCompleteStatus() ? 1 : 0);
									List<Agency> listPartnerAgencies =  subActivity.getPartnerAgency();
									StringBuilder partneragencyStringList = new StringBuilder();
									for (Agency agency : listPartnerAgencies) {
										if (partneragencyStringList != null && partneragencyStringList.length() == 0) {
											partneragencyStringList.append(agency.getCode());
										} else  {
											partneragencyStringList.append(",");
											partneragencyStringList.append(agency.getCode());
										}
									}
									
									subActvitiyObj.put("PartneringAgency",partneragencyStringList);
									subActvitiyObj.put("MoV", subActivity.getMov()); 
									List<Planning> listPlannings = planningServices.getByPlanning(subActivity);
									List<Integer> listReportingPeriodIds = new ArrayList<Integer>();
									for (Planning planning : listPlannings) {
										listReportingPeriodIds.add(planning.getReportingPeriod().getId());
									}
									subActvitiyObj.put("ReportingPeriodId", listReportingPeriodIds);
									subActvitiyObj.put("ActivityId", subActivity.getKeyActivity().getId());
									subActivityArray.put(subActvitiyObj);
//						End 3. Sub Activity Info
									
//						Start	4. Key Activity Info	 ---> 		
									if (keyActivityArraySet.add(subActivity.getKeyActivity().getId())){
										JSONObject keyActivityObj = new JSONObject();
										keyActivityObj.put("ActivityId", subActivity.getKeyActivity().getId());
										keyActivityObj.put("SequenceNumber", subActivity.getKeyActivity().getSequenceNumber());
										keyActivityObj.put("ActivityName", subActivity.getKeyActivity().getSequenceNumber() + ". " + subActivity.getKeyActivity().getName());
										keyActivityObj.put("OutputId", subActivity.getKeyActivity().getOutput().getId());
										keyActivityObj.put("Status", subActivity.getKeyActivity().getStatus());
										keyActivityArray.put(keyActivityObj);
									}
//						End	4. Key Activity Info							

//						Start	5. Output Info	----->
									if (outputArraySet.add(subActivity.getKeyActivity().getOutput().getId())){
										JSONObject outputObj = new JSONObject();
										JSONArray jsonIndicatorArray = new JSONArray();
										JSONArray jsonTargetArray = new JSONArray();
										List<Indicator> listIndicators =  indicatorService.findByOutput(subActivity.getKeyActivity().getOutput());
										for (Indicator indicator : listIndicators) {
											jsonIndicatorArray.put(indicator.getMessage());
										}
										List<Target> listTargets = targetService.findByOutput(subActivity.getKeyActivity().getOutput());
										for (Target target : listTargets) {
											jsonTargetArray.put(target.getMessage());
										}
										outputObj.put("OutPutId", subActivity.getKeyActivity().getOutput().getId());
										outputObj.put("SequenceNumber", subActivity.getKeyActivity().getOutput().getSequenceNumber());
										outputObj.put("OutPutName", subActivity.getKeyActivity().getOutput().getSequenceNumber() 
												+ ". " + subActivity.getKeyActivity().getOutput().getOutput());
										outputObj.put("Indicators", jsonIndicatorArray);
										outputObj.put("Target", jsonTargetArray);
										outputObj.put("Status", subActivity.getKeyActivity().getOutput().getStatus());
										outputObj.put("OutComeId", subActivity.getKeyActivity().getOutput().getOutcome().getId());
										outputArray.put(outputObj);
									}
//						End	5. Output Info
									
//						Start	6. Outcome Info	----->
									if (outcomeArraySet.add(subActivity.getKeyActivity().getOutput().getOutcome().getId())){
										JSONObject outcomeObj = new JSONObject();
										outcomeObj.put("OutComeId", subActivity.getKeyActivity().getOutput().getOutcome().getId());
										outcomeObj.put("SequenceNumber", subActivity.getKeyActivity().getOutput().getOutcome().getSequenceNumber());
										outcomeObj.put("OutComeName", subActivity.getKeyActivity().getOutput().getOutcome().getSequenceNumber()
												+ ". " + subActivity.getKeyActivity().getOutput().getOutcome().getName());
										outcomeObj.put("ThemeId", subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getId());
										outcomeObj.put("Status", subActivity.getKeyActivity().getOutput().getOutcome().getStatus());
										outcomeArray.put(outcomeObj);	
									}
//						End	6. Outcome Info	
								
//						Start	7. Theme Info	----->
									if (themeArraySet.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getId())){
										JSONObject themeObj = new JSONObject();
										themeObj.put("ThemeId", subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getId());
										themeObj.put("ThemeName", subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getName());
										themeObj.put("StrategiesId", subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getStrategicPillar().getId());
										themeObj.put("Status", subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getStatus());
										themeArray.put(themeObj);
									}
//						End	7. Theme Info
									
//						Start	8. Strategy Info	----->
									if (strategyArraySet.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getStrategicPillar().getId())){
										JSONObject startegyObj = new JSONObject();
										startegyObj.put("StrategiesId", subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getStrategicPillar().getId());
										startegyObj.put("SequenceNumber", subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getStrategicPillar().getSequenceNumber());
										startegyObj.put("StrategiesName",  subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getStrategicPillar().getSequenceNumber() 
												+ ". " + subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getStrategicPillar().getName());
										startegyObj.put("Status", subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getStrategicPillar().getStatus());
										strategyArray.put(startegyObj);	
									}
//						End	6. Strategy Info
									
//						Start Reviewer Data		-------->
									for(String registeredAgency : listAgencyString){
										if(Integer.parseInt(registeredAgency) == subActivity.getLeadAgency().getId()){
											partneringAgencySet.addAll(subActivity.getPartnerAgency());
											for (Agency partnerAgency : subActivity.getPartnerAgency()) {
												listPartnerIds.add(partnerAgency.getId());
											}
										}
									}

								}
							}
							
							// Summary Details - Submit for Review
							List<SubmitForReview> listsummeryData = new ArrayList<SubmitForReview>();
							if (listRegisteredAgencies.size() > 0)
								listsummeryData = submitForReviewService.findByAgency(listRegisteredAgencies);
							if (partneringAgencySet.size() > 0)
								listsummeryData.addAll(submitForReviewService.findByAgency(partneringAgencySet));
							for(SubmitForReview summeryData : listsummeryData){
								JSONObject summeryDataObj = new JSONObject();
								summeryDataObj.put("BestPractice", summeryData.getBestPractice());
								summeryDataObj.put("KeyChallenge", summeryData.getKeyChallenge());
								summeryDataObj.put("KeyLearning", summeryData.getKeyLearning());
								summeryDataObj.put("SupportNeeds", summeryData.getSupportNeeds());
								summeryDataObj.put("UserId", summeryData.getUser().getId());
								summeryDataObj.put("UserName", summeryData.getUser().getUsername());
								summeryDataObj.put("ReportingPeriod", summeryData.getReportingPeriod().getId());
								summeryDataObj.put("Agency", summeryData.getAgency().getId());
								summeryDataObj.put("AgencyName", summeryData.getAgency().getName());
								summeryDataObj.put("UserLevel", summeryData.getUserLevel());
								summeryDataObj.put("LeadReworkStatus", summeryData.isLeadReworkStatus() == true ? 1 : 0);
								summeryDataObj.put("ParnterReworkStatus", summeryData.isPartnerReworkStatus() == true ? 1 : 0);
								submitForReviewArray.put(summeryDataObj);
								
							}
							
//	Status Tracking
							List<StatusTracking> listDetailedData = new ArrayList<StatusTracking>();
							if (listAgencyIds.size() > 0)
								listDetailedData = statusTrackingService.findStatusTrackingByAgencyIds(listAgencyIds);
							if (listPartnerIds.size() > 0)
								listDetailedData.addAll(statusTrackingService.findStatusTrackingByAgencyIds(listPartnerIds));
							for(StatusTracking detailedData : listDetailedData){
								JSONObject detailedDataObj = new JSONObject();
								detailedDataObj.put("SubActivityId", detailedData.getSubActivity().getId());
								detailedDataObj.put("ReportingPeriod", detailedData.getReportingPeriod().getId());
								detailedDataObj.put("UserId", detailedData.getUser().getId());
								detailedDataObj.put("UserName", detailedData.getUser().getUsername());
								detailedDataObj.put("AgencyId", detailedData.getAgency().getId());
								detailedDataObj.put("AgencyName", detailedData.getAgency().getName());
								detailedDataObj.put("StatusColorCode", detailedData.getActualStatusColor());
								detailedDataObj.put("StatusOfProgressValue", detailedData.getActualStatusPercentage());
								detailedDataObj.put("KeyProgress", detailedData.getKeyProgress());
								detailedDataObj.put("ReasonForGap", detailedData.getReasonForGap());
								detailedDataObj.put("RectifyTheGap", detailedData.getRectifyTheGap());
								detailedDataObj.put("ReadyforReview", detailedData.isComplete() == true ? 1 : 0);
								detailedDataObj.put("SentForReview", detailedData.isSentForReview() == true ? 1 : 0);
								detailedDataObj.put("UserLevel", detailedData.getUserLevel());
								if (detailedData.getReviewedBy() != null) {
									detailedDataObj.put("ReviewedBy", detailedData.getReviewedBy().getId());
								} else {
									detailedDataObj.put("ReviewedBy", 0);
								}
								detailedDataObj.put("ReviewedOn", detailedData.getReviewedOn());
								detailedDataObj.put("ReviewerFeedback", detailedData.getReviewerFeedback());
								detailedDataObj.put("ReviewStatus", detailedData.getReviewStatus());
								detailedDataObj.put("ReworkRequired", detailedData.isReworkRequired() == true ? 1 : 0);
								statusTrackingArray.put(detailedDataObj);
							}
							
//	Start Approver Dashboard graph counts
							if(isApprover){
								List<ReportingPeriod> listReportingPeriods = reportingPeriodService.getAll();
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
												if(activity.getId() == subActivity.getId()){
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
														if(subActivity.getId() == completedSubActivity.getId() 
																&& completedSubActivity.getCompletedReportingPeriod().getId() == reportingPeriod.getId()){
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
												if(subActivity.getId() == completedSubActivity.getId() 
														&& completedSubActivity.getCompletedReportingPeriod().getId() == reportingPeriod.getId()){
													completed++;
													completedSubActivities.remove(completedSubActivity);
													isCompleted = true;
													break;
												}
											}
											if (!isCompleted) {
												if(subActivity.getCompletedReportingPeriod() != null){
													if (subActivity.getCompletedReportingPeriod().getId() == reportingPeriod.getId()) {
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
									
									JSONObject activityDashboardDTO = new JSONObject();
									activityDashboardDTO.put("Year",reportingPeriod.getYear() );
									activityDashboardDTO.put("Quarter" ,reportingPeriod.getName());
									activityDashboardDTO.put("Status", reportingPeriod.getStatus());
									activityDashboardDTO.put("OpenActivities",currentTotal);
									if(!reportingPeriod.getReportingStatus().equals("Not-Started")){
										activityDashboardDTO.put("ToBeCompleted",toBeCompleted);
										activityDashboardDTO.put("Completed",completed);
										activityDashboardDTO.put("NotCompleted",notCompleted);
										activityDashboardDTO.put("CarryForwardedTotal",outstandingTotal);
										activityDashboardDTO.put("CarryForwardedCompleted",outstandingCompleted);
										activityDashboardDTO.put("CarryForwardedNotCompleted",outstandingNotCompleted);
									}else{
										activityDashboardDTO.put("ToBeCompleted","");
										activityDashboardDTO.put("Completed","");
										activityDashboardDTO.put("NotCompleted","");
										activityDashboardDTO.put("CarryForwardedTotal","");
										activityDashboardDTO.put("CarryForwardedCompleted","");
										activityDashboardDTO.put("CarryForwardedNotCompleted","");
									}
									listActivityDashboardDTOs.put(activityDashboardDTO);
								}
							}
//	End Approver Dashboard graph counts
							
							mobileWBSyncData.put("AgencyDetails", agencyArray);
							mobileWBSyncData.put("ReportingPeriod", reportingPeriodArray);
							mobileWBSyncData.put("StatusDetails", statusArray);
							mobileWBSyncData.put("SubActivityDetails", subActivityArray);
							mobileWBSyncData.put("ActivityDetails", keyActivityArray);
							mobileWBSyncData.put("OutPutDetails", outputArray);
							mobileWBSyncData.put("OutcomeDetails", outcomeArray);
							mobileWBSyncData.put("ThemeDetails", themeArray);
							mobileWBSyncData.put("StrategiesDetails", strategyArray);
							mobileWBSyncData.put("SubmitForReviewData", submitForReviewArray);
							mobileWBSyncData.put("StatusTrackingData", statusTrackingArray);
							mobileWBSyncData.put("ActivityStatus", listActivityDashboardDTOs);
							
							allowedDeviceInfo.setTempSyncRequestedTime(syncRequestedTime);
							allowedDeviceService.deviceRegisteration(allowedDeviceInfo);
							
							syncData.put("result", true);
							syncData.put("syncTime", syncRequestedTime);
							syncData.put("syncData", mobileWBSyncData);
							
						}else if (!allowedDeviceInfo.isDeviceStatus()){
							LOGGER.debug("Device not yet approved");
							syncData.put("result", false);
							syncData.put("msg", "Device not yet approved");
						}else{
							
							LOGGER.debug("Invalid Device");
							syncData.put("result", false);
							syncData.put("msg", "Invalid Device");
						}
					}else{
						syncData.put("result", false);
						syncData.put("msg", "Invalid Data");
					}
				}else{
					syncData.put("result", false);
					syncData.put("msg", "Invalid Device");
				}
		}else{
			syncData.put("result", false);
			syncData.put("msg", "Invalid Data");
		}
		
		byte[] encodedBytes = Base64.encodeBase64((syncData.toString()).getBytes());
		String encodedString = new String(encodedBytes);
		LOGGER.debug("End Sync Time : " + new Date());
		return encodedString;
	}
	
	/**
	 * @author pushpa / Leo
	 * 4. Sync data based on the device and agency and Time Frame ( Last synced date to current requested time)
	 * @param deviceId
	 * @return
	 */
	@RequestMapping(value="syncLatestDataByUser", method = RequestMethod.POST)
	public @ResponseBody String syncLatestDataByUser(@RequestBody String syndDataRequest){
		LOGGER.debug("Start Sync Time : " + new Date());
		JSONObject syncData = new JSONObject();
		
		JSONObject requestedString = new JSONObject(syndDataRequest);
		if (requestedString.has("deviceId") && requestedString.has("userId")){
			String deviceId = requestedString.getString("deviceId");
			int userId = requestedString.getInt("userId");
			String syncRequestedTime = "";
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			syncRequestedTime = sd.format(new Date());
			
			boolean isApprover = false;
			
			JSONObject mobileWBSyncData = new JSONObject();
			JSONArray agencyArray = new JSONArray();
			JSONArray reportingPeriodArray = new JSONArray();
			JSONArray statusArray = new JSONArray();
			JSONArray subActivityArray = new JSONArray();
			JSONArray keyActivityArray = new JSONArray();
			JSONArray outputArray = new JSONArray();
			JSONArray outcomeArray = new JSONArray();
			JSONArray themeArray = new JSONArray();
			JSONArray strategyArray = new JSONArray();
			JSONArray statusTrackingArray = new JSONArray();
			JSONArray submitForReviewArray = new JSONArray();
			JSONArray listActivityDashboardDTOs = new JSONArray();
			
			Set<Integer> keyActivityArraySet = new HashSet<Integer>();
			Set<Integer> outputArraySet = new HashSet<Integer>();
			Set<Integer> outcomeArraySet = new HashSet<Integer>();
			Set<Integer> themeArraySet = new HashSet<Integer>();
			Set<Integer> strategyArraySet = new HashSet<Integer>();
			Set<Agency> partneringAgencySet = new HashSet<Agency>();
			
			String approveragency = environment.getProperty("approver.agency");
			
			if(deviceId != null && !deviceId.isEmpty()){
				AllowedDeviceInfo allowedDeviceInfo = allowedDeviceService.findByDeviceId(deviceId);
				if (allowedDeviceInfo != null){
					
					User syncUser = userService.getById(userId);
					UserDeviceSyncInfo userDeviceSyncInfo = userDeviceSyncInfoService.findByDeviceIdAndUser(deviceId, syncUser);
					
					if (userDeviceSyncInfo == null) {
						userDeviceSyncInfo = new UserDeviceSyncInfo();
						userDeviceSyncInfo.setDeviceId(deviceId);
						userDeviceSyncInfo.setUser(syncUser);
						userDeviceSyncInfo.setLastSyncedTime(allowedDeviceInfo.getLastSyncedTime());
					}
					
					String lastSyncedTime = userDeviceSyncInfo.getLastSyncedTime();
//		Start	1. Agency Info ---> 
					List<String> listAgencyString = Arrays.asList((allowedDeviceInfo.getAgencyIds()).split(","));
					List<Integer> listAgencyIds = new ArrayList<Integer>();
					List<Integer> listPartnerIds = new ArrayList<Integer>();
					Set<Agency> listRegisteredAgencies = new HashSet<Agency>();
					for (String agencyIdString : listAgencyString) {
						if (agencyIdString != null && !agencyIdString.trim().isEmpty()) {
							Agency agency = new Agency();
							Agency agencyNew = new Agency();
							agency = agencyService.findByAgencyId(Integer.parseInt(agencyIdString));
							if(agency != null){
								listRegisteredAgencies.add(agency);
								listAgencyIds.add(agency.getId());
								if(agency.getCode().equals(approveragency)){
									isApprover = true;
								}
							}
							agencyNew = agencyService.findAgencyByIdAndDate(Integer.parseInt(agencyIdString),lastSyncedTime, syncRequestedTime);
							if (agencyNew != null){
								JSONObject agencyObj = new JSONObject();
								JSONArray userArray = new JSONArray();
								List<User> listUsers = agency.getAgencyAuthority();
								if (listUsers != null && !listUsers.isEmpty() && listUsers.size() > 0){
									for (User user : listUsers) {
										if(user.getUserType().contains("Mobile")){
											JSONObject userobj = new JSONObject();
											userobj.put("userId", user.getId());
											userobj.put("username", user.getUsername());
											userobj.put("password", user.getMobilePassword());
											userobj.put("status", user.getStatus());
											StringBuffer userRolesString = new StringBuffer();
											for (UserRole userRole : user.getUserRoles()) {
												if(userRolesString.length() <= 0){
													userRolesString.append(userRole.getName());
												}else{
													userRolesString.append(",");
													userRolesString.append(userRole.getName());
												}
											}
											userobj.put("userRoles", userRolesString);
											userArray.put(userobj);
										}
									}
								}else{
									LOGGER.debug("No User is mapped with this agency");
								}
								
								agencyObj.put("AgencyId", agency.getId());
								agencyObj.put("AgencyCode", agency.getCode());
								agencyObj.put("UserDetails", userArray );
								agencyObj.put("Status", agency.getStatus());
								agencyArray.put(agencyObj);
							
							}
						}else{
							LOGGER.debug("Agency Code : " + agencyIdString + " : Not Found");
						}
					}
//		End	1. Agency Info 
					
//		Start	2. Reporting Period Info  	---> 
					List<ReportingPeriod> listreReportingPeriods = reportingPeriodService.getReportingPeriodListByDate(lastSyncedTime,syncRequestedTime);
					if (listreReportingPeriods != null && !listreReportingPeriods.isEmpty()){
						for (ReportingPeriod reportingPeriod : listreReportingPeriods) {
							JSONObject reportingperiodObj = new JSONObject();
							reportingperiodObj.put("ReportingPeriodId", reportingPeriod.getId());
							reportingperiodObj.put("Name", reportingPeriod.getName());
							reportingperiodObj.put("Year", reportingPeriod.getYear());
							reportingperiodObj.put("StartDate", reportingPeriod.getStartdate());
							reportingperiodObj.put("EndDate", reportingPeriod.getEnddate());
							reportingperiodObj.put("Status", reportingPeriod.getReportingStatus());
							reportingperiodObj.put("Active", reportingPeriod.getStatus().equals("ACTIVE") ? 1 : 0);
							reportingPeriodArray.put(reportingperiodObj);
						}
					}
//		End 2. Reporting Period Info
					
//		Start Status Info 	---->
					List<Statuss> listStatus = statusService.findStatusListByDate(lastSyncedTime, syncRequestedTime);
					if(listStatus != null && listStatus.size() > 0){
						for (Statuss statuss : listStatus) {
							JSONObject statusObject = new JSONObject();
							statusObject.put("StatusId", statuss.getId());
							statusObject.put("Name", statuss.getName());
							statusObject.put("Color", statuss.getStatusColor());
							statusObject.put("Percentage", statuss.getColorPercent());
							statusObject.put("StartRange", statuss.getStartRange());
							statusObject.put("EndRange", statuss.getEndRange());
							statusObject.put("Status", statuss.getStatus());
							statusArray.put(statusObject);
						}
					}
//		End Status Info
					
					List<SubActivity> listSubActivities = subActivityService.findSubActivityByAgencyIdsAndDate(listAgencyIds, lastSyncedTime, syncRequestedTime);
					if(listSubActivities != null && !listSubActivities.isEmpty()){
						for (SubActivity subActivity : listSubActivities) {
//		Start	3. Sub Activity Info	 ---> 	
							JSONObject subActvitiyObj = new JSONObject();
							subActvitiyObj.put("SubActivityId", subActivity.getId());
							subActvitiyObj.put("SequenceNumber", subActivity.getSequenceNumber());
							subActvitiyObj.put("SubActivityName", subActivity.getSequenceNumber() + ". " + subActivity.getName());
							subActvitiyObj.put("ResponsibleEntity", subActivity.getLeadAgency().getCode());
							subActvitiyObj.put("Status", subActivity.getStatus());
							subActvitiyObj.put("IsCompleted", subActivity.isApproveORCompleteStatus() ? 1 : 0);
							List<Agency> listPartnerAgencies =  subActivity.getPartnerAgency();
							StringBuilder partneragencyStringList = new StringBuilder();
							for (Agency agency : listPartnerAgencies) {
								if (partneragencyStringList != null && partneragencyStringList.length() == 0) {
									partneragencyStringList.append(agency.getCode());
								} else  {
									partneragencyStringList.append(",");
									partneragencyStringList.append(agency.getCode());
								}
							}
							
							subActvitiyObj.put("PartneringAgency",partneragencyStringList);
							subActvitiyObj.put("MoV", subActivity.getMov());
							List<Planning> listPlannings = planningServices.getByPlanning(subActivity);
							List<Integer> listReportingPeriodIds = new ArrayList<Integer>();
							for (Planning planning : listPlannings) {
								listReportingPeriodIds.add(planning.getReportingPeriod().getId());
							}
							subActvitiyObj.put("ReportingPeriodId", listReportingPeriodIds);
							subActvitiyObj.put("ActivityId", subActivity.getKeyActivity().getId());
							subActivityArray.put(subActvitiyObj);
//		End 3. Sub Activity Info
							
//		Start	4. Key Activity Info	 ---> 		
							if (keyActivityArraySet.add(subActivity.getKeyActivity().getId())){
								JSONObject keyActivityObj = new JSONObject();
								keyActivityObj.put("ActivityId", subActivity.getKeyActivity().getId());
								keyActivityObj.put("SequenceNumber", subActivity.getKeyActivity().getSequenceNumber());
								keyActivityObj.put("ActivityName", subActivity.getKeyActivity().getSequenceNumber() + ". " + subActivity.getKeyActivity().getName());
								keyActivityObj.put("OutputId", subActivity.getKeyActivity().getOutput().getId());
								keyActivityObj.put("Status", subActivity.getKeyActivity().getStatus());
								keyActivityArray.put(keyActivityObj);
							}
//		End	4. Key Activity Info							

//		Start	5. Output Info	----->
							if (outputArraySet.add(subActivity.getKeyActivity().getOutput().getId())){
								JSONObject outputObj = new JSONObject();
								JSONArray jsonIndicatorArray = new JSONArray();
								JSONArray jsonTargetArray = new JSONArray();
								List<Indicator> listIndicators =  indicatorService.findByOutput(subActivity.getKeyActivity().getOutput());
								for (Indicator indicator : listIndicators) {
									jsonIndicatorArray.put(indicator.getMessage());
								}
								List<Target> listTargets = targetService.findByOutput(subActivity.getKeyActivity().getOutput());
								for (Target target : listTargets) {
									jsonTargetArray.put(target.getMessage());
								}
								outputObj.put("OutPutId", subActivity.getKeyActivity().getOutput().getId());
								outputObj.put("SequenceNumber", subActivity.getKeyActivity().getOutput().getSequenceNumber());
								outputObj.put("OutPutName", subActivity.getKeyActivity().getOutput().getSequenceNumber() 
										+ ". " + subActivity.getKeyActivity().getOutput().getOutput());
								outputObj.put("Indicators", jsonIndicatorArray);
								outputObj.put("Target", jsonTargetArray);
								outputObj.put("Status", subActivity.getKeyActivity().getOutput().getStatus());
								outputObj.put("OutComeId", subActivity.getKeyActivity().getOutput().getOutcome().getId());
								outputArray.put(outputObj);
							}
//		End	5. Output Info
							
//		Start	6. Outcome Info	----->
							if (outcomeArraySet.add(subActivity.getKeyActivity().getOutput().getOutcome().getId())){
								JSONObject outcomeObj = new JSONObject();
								outcomeObj.put("OutComeId", subActivity.getKeyActivity().getOutput().getOutcome().getId());
								outcomeObj.put("SequenceNumber", subActivity.getKeyActivity().getOutput().getOutcome().getSequenceNumber());
								outcomeObj.put("OutComeName", subActivity.getKeyActivity().getOutput().getOutcome().getSequenceNumber()
										+ ". " + subActivity.getKeyActivity().getOutput().getOutcome().getName());
								outcomeObj.put("ThemeId", subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getId());
								outcomeObj.put("Status", subActivity.getKeyActivity().getOutput().getOutcome().getStatus());
								outcomeArray.put(outcomeObj);	
							}
//		End	6. Outcome Info	
						
//		Start	7. Theme Info	----->
							if (themeArraySet.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getId())){
								JSONObject themeObj = new JSONObject();
								themeObj.put("ThemeId", subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getId());
								themeObj.put("ThemeName", subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getName());
								themeObj.put("StrategiesId", subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getStrategicPillar().getId());
								themeObj.put("Status", subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getStatus());
								themeArray.put(themeObj);
							}
//		End	7. Theme Info
							
//		Start	8. Strategy Info	----->
							if (strategyArraySet.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getStrategicPillar().getId())){
								JSONObject startegyObj = new JSONObject();
								startegyObj.put("StrategiesId", subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getStrategicPillar().getId());
								startegyObj.put("SequenceNumber", subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getStrategicPillar().getSequenceNumber());
								startegyObj.put("StrategiesName",  subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getStrategicPillar().getSequenceNumber() 
										+ ". " + subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getStrategicPillar().getName());
								startegyObj.put("Status", subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getStrategicPillar().getStatus());
								strategyArray.put(startegyObj);	
							}
//		End	6. Strategy Info
						}
					}
	
//		Start Reviewer Data
					List<SubActivity> listAllSubActivities = subActivityService.findSubActivityByAgencyIds(listAgencyIds);
					if(listAllSubActivities != null && !listAllSubActivities.isEmpty()){
						for (SubActivity subActivity : listAllSubActivities) {
							for(String registeredAgency : listAgencyString){
								if(Integer.parseInt(registeredAgency) == subActivity.getLeadAgency().getId()){
									partneringAgencySet.addAll(subActivity.getPartnerAgency());
									for (Agency partnerAgency : subActivity.getPartnerAgency()) {
										listPartnerIds.add(partnerAgency.getId());
									}
								}
							}
						}
					}
					
					Set<Agency> syncAgencySet = new HashSet<Agency>();
					List<Integer> syncAgencyId = new ArrayList<Integer>();
					Agency syncAgency = agencyService.findByLoginUser(syncUser.getUsername());
					syncAgencySet.add(syncAgency);
					syncAgencyId.add(syncAgency.getId());
					// Summary Details - Submit for Review
					
					List<SubmitForReview> listsummeryData = new ArrayList<SubmitForReview>();
					if (listRegisteredAgencies.size() > 0)
						listsummeryData = submitForReviewService.findByAgencyAndModificationTime(syncAgencySet, lastSyncedTime, syncRequestedTime);
					if (partneringAgencySet.size() > 0)
						listsummeryData.addAll(submitForReviewService.findByAgencyAndModificationTime(partneringAgencySet, lastSyncedTime, syncRequestedTime));
					
					for(SubmitForReview summeryData : listsummeryData){
						JSONObject summeryDataObj = new JSONObject();
						summeryDataObj.put("BestPractice", summeryData.getBestPractice());
						summeryDataObj.put("KeyChallenge", summeryData.getKeyChallenge());
						summeryDataObj.put("KeyLearning", summeryData.getKeyLearning());
						summeryDataObj.put("SupportNeeds", summeryData.getSupportNeeds());
						summeryDataObj.put("UserId", summeryData.getUser().getId());
						summeryDataObj.put("UserName", summeryData.getUser().getUsername());
						summeryDataObj.put("ReportingPeriod", summeryData.getReportingPeriod().getId());
						summeryDataObj.put("Agency", summeryData.getAgency().getId());
						summeryDataObj.put("AgencyName", summeryData.getAgency().getName());
						summeryDataObj.put("UserLevel", summeryData.getUserLevel());
						summeryDataObj.put("LeadReworkStatus", summeryData.isLeadReworkStatus() == true ? 1 : 0);
						summeryDataObj.put("ParnterReworkStatus", summeryData.isPartnerReworkStatus() == true ? 1 : 0);
						submitForReviewArray.put(summeryDataObj);
					}
					
					// Status Tracking
					List<StatusTracking> listDetailedData = new ArrayList<StatusTracking>();
					if (syncAgencyId.size() > 0)
						listDetailedData = statusTrackingService.findStatusTrackingByAgencyIdsAndModificationTime(syncAgencyId, lastSyncedTime, syncRequestedTime);
					if(listPartnerIds.size() > 0)
						listDetailedData.addAll(statusTrackingService.findStatusTrackingByAgencyIdsAndModificationTime(listPartnerIds, lastSyncedTime, syncRequestedTime));
					for(StatusTracking detailedData : listDetailedData){
						JSONObject detailedDataObj = new JSONObject();
						detailedDataObj.put("SubActivityId", detailedData.getSubActivity().getId());
						detailedDataObj.put("ReportingPeriod", detailedData.getReportingPeriod().getId());
						detailedDataObj.put("UserId", detailedData.getUser().getId());
						detailedDataObj.put("UserName", detailedData.getUser().getUsername());
						detailedDataObj.put("AgencyId", detailedData.getAgency().getId());
						detailedDataObj.put("AgencyName", detailedData.getAgency().getName());
						detailedDataObj.put("StatusColorCode", detailedData.getActualStatusColor());
						detailedDataObj.put("StatusOfProgressValue", detailedData.getActualStatusPercentage());
						detailedDataObj.put("KeyProgress", detailedData.getKeyProgress());
						detailedDataObj.put("ReasonForGap", detailedData.getReasonForGap());
						detailedDataObj.put("RectifyTheGap", detailedData.getRectifyTheGap());
						detailedDataObj.put("ReadyforReview", detailedData.isComplete() == true ? 1 : 0);
						detailedDataObj.put("SentForReview", detailedData.isSentForReview() == true ? 1 : 0);
						detailedDataObj.put("UserLevel", detailedData.getUserLevel());
						if (detailedData.getReviewedBy() != null) {
							detailedDataObj.put("ReviewedBy", detailedData.getReviewedBy().getId());
						} else {
							detailedDataObj.put("ReviewedBy", 0);
						}
						detailedDataObj.put("ReviewedOn", detailedData.getReviewedOn());
						detailedDataObj.put("ReviewerFeedback", detailedData.getReviewerFeedback());
						detailedDataObj.put("ReviewStatus", detailedData.getReviewStatus());
						detailedDataObj.put("ReworkRequired", detailedData.isReworkRequired() == true ? 1 : 0);
						statusTrackingArray.put(detailedDataObj);
					}
					
//					Start Approver Dashboard graph counts
					if(isApprover){
						List<ReportingPeriod> listReportingPeriods = reportingPeriodService.getAll();
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
										if(activity.getId() == subActivity.getId()){
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
												if(subActivity.getId() == completedSubActivity.getId() 
														&& completedSubActivity.getCompletedReportingPeriod().getId() == reportingPeriod.getId()){
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
										if(subActivity.getId() == completedSubActivity.getId() 
												&& completedSubActivity.getCompletedReportingPeriod().getId() == reportingPeriod.getId()){
											completed++;
											completedSubActivities.remove(completedSubActivity);
											isCompleted = true;
											break;
										}
									}
									if (!isCompleted) {
										if(subActivity.getCompletedReportingPeriod() != null){
											if (subActivity.getCompletedReportingPeriod().getId() == reportingPeriod.getId()) {
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
							
							JSONObject activityDashboardDTO = new JSONObject();
							activityDashboardDTO.put("Year",reportingPeriod.getYear() );
							activityDashboardDTO.put("Quarter" ,reportingPeriod.getName());
							activityDashboardDTO.put("Status", reportingPeriod.getStatus());
							activityDashboardDTO.put("OpenActivities",currentTotal);
							if(!reportingPeriod.getReportingStatus().equals("Not-Started")){
								activityDashboardDTO.put("ToBeCompleted",toBeCompleted);
								activityDashboardDTO.put("Completed",completed);
								activityDashboardDTO.put("NotCompleted",notCompleted);
								activityDashboardDTO.put("CarryForwardedTotal",outstandingTotal);
								activityDashboardDTO.put("CarryForwardedCompleted",outstandingCompleted);
								activityDashboardDTO.put("CarryForwardedNotCompleted",outstandingNotCompleted);
							}else{

								activityDashboardDTO.put("ToBeCompleted","");
								activityDashboardDTO.put("Completed","");
								activityDashboardDTO.put("NotCompleted","");
								activityDashboardDTO.put("CarryForwardedTotal","");
								activityDashboardDTO.put("CarryForwardedCompleted","");
								activityDashboardDTO.put("CarryForwardedNotCompleted","");
							
							}
							listActivityDashboardDTOs.put(activityDashboardDTO);
						}
					}
//End Approver Dashboard graph counts

					mobileWBSyncData.put("AgencyDetails", agencyArray);
					mobileWBSyncData.put("ReportingPeriod", reportingPeriodArray);
					mobileWBSyncData.put("StatusDetails", statusArray);
					mobileWBSyncData.put("SubActivityDetails", subActivityArray);
					mobileWBSyncData.put("ActivityDetails", keyActivityArray);
					mobileWBSyncData.put("OutPutDetails", outputArray);
					mobileWBSyncData.put("OutcomeDetails", outcomeArray);
					mobileWBSyncData.put("ThemeDetails", themeArray);
					mobileWBSyncData.put("StrategiesDetails", strategyArray);
					mobileWBSyncData.put("SubmitForReviewData", submitForReviewArray);
					mobileWBSyncData.put("StatusTrackingData", statusTrackingArray);
					mobileWBSyncData.put("ActivityStatus", listActivityDashboardDTOs);
					
					userDeviceSyncInfo.setTempSyncRequestedTime(syncRequestedTime);
					userDeviceSyncInfoService.saveUserDeviceSyncInfo(userDeviceSyncInfo);
					
					syncData.put("result", true);
					syncData.put("syncTime", syncRequestedTime);
					syncData.put("syncData", mobileWBSyncData);
				}else{
					LOGGER.debug("Invalid Device");
					syncData.put("result", false);
					syncData.put("msg", "Invalid Device");
				}
			}else{
				syncData.put("result", false);
				syncData.put("msg", "Invalid Data");
			}
		}else{
			syncData.put("result", false);
			syncData.put("msg", "Invalid Requested String");
		}
		
		byte[] encodedBytes = Base64.encodeBase64((syncData.toString()).getBytes());
		String encodedString = new String(encodedBytes);
		LOGGER.debug("End Sync Time : " + new Date());
		return encodedString;
	}
	
	/**
	 * @author pushpa
	 * 5. Get profile Images of the user
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "user/getProfileImage", method = RequestMethod.POST)
	public @ResponseBody String getUserProfileImage(@RequestBody String profileImageRequest){
		JSONObject profileImageResult = new JSONObject();
		JSONObject requestedString = new JSONObject(profileImageRequest);
		if (requestedString.has("userId")){
			Integer userId = requestedString.getInt("userId");
			if(userId != null){
				User user = userService.getById(userId);
				if (user != null){
					ProfileImage profileImage = profileImageService.findByUser(user);
					profileImageResult.put("result", true);
					if(profileImage != null && profileImage.getProfileimg() != null){
						profileImageResult.put("profileImage", profileImage.getProfileimg());
					}else{
						profileImageResult.put("profileImage", "");
					}
				}else{
					profileImageResult.put("result", false);
					profileImageResult.put("msg", "User not found");
				}
			}else{
				profileImageResult.put("result", false);
				profileImageResult.put("msg", "Invalid Data");
			}
		}else{
			profileImageResult.put("result", false);
			profileImageResult.put("msg", "Invalid Request String");
		}
		return profileImageResult.toString();
	}
	
	/**
	 * @author pushpa / Leo
	 * 6. This method updates the status with last sync time after success of the sync in mobile 
	 * @param deviceId
	 * @param syncTime
	 * @return
	 */
	@RequestMapping(value="syncStatusUpdate", method = RequestMethod.POST)
	public String syncStatusUpdate(@RequestBody String syncData){
		LOGGER.debug("syncStatusUpdate Sync Start Time : " + new Date());
		JSONObject syncStatusResult = new JSONObject();
		JSONObject requestedString = new JSONObject(syncData);
		
		if(requestedString.has("deviceId")){
			String tempSyncRequestedTime = "";
			String deviceId = requestedString.getString("deviceId");
			if(requestedString.has("syncTime")){
				tempSyncRequestedTime = requestedString.getString("syncTime");
			}else{
				syncStatusResult.put("result", false);
				syncStatusResult.put("msg", "Invalid Data");
			}
			
			if ((deviceId != null && !deviceId.isEmpty()) && (tempSyncRequestedTime != null && !tempSyncRequestedTime.isEmpty())){
				
				if (requestedString.has("userId")) {
					int userId = requestedString.getInt("userId");
					User syncUser = userService.getById(userId);
					
					UserDeviceSyncInfo userDeviceSyncInfo = userDeviceSyncInfoService.findByDeviceIdAndUserAndTempSyncRequestedTime(deviceId, syncUser, tempSyncRequestedTime);
					
					if (userDeviceSyncInfo != null){
						userDeviceSyncInfo.setLastSyncedTime(tempSyncRequestedTime);
						userDeviceSyncInfo.setTempSyncRequestedTime(null);
						userDeviceSyncInfoService.saveUserDeviceSyncInfo(userDeviceSyncInfo);
						
						syncStatusResult.put("result", true);
						syncStatusResult.put("msg", "Last sync time updated successfully");
					}else{
						syncStatusResult.put("result", false);
						syncStatusResult.put("msg", "Device not found with the given synced time : " +  tempSyncRequestedTime);
					}
				} else {
					AllowedDeviceInfo allowedDeviceInfo = new AllowedDeviceInfo();
					allowedDeviceInfo = allowedDeviceService.findByDeviceIdAndTempSyncRequestedTime(deviceId, tempSyncRequestedTime);
					if (allowedDeviceInfo != null){
						allowedDeviceInfo.setLastSyncedTime(tempSyncRequestedTime);
						allowedDeviceInfo.setTempSyncRequestedTime(null);
						allowedDeviceService.deviceRegisteration(allowedDeviceInfo);
						
						syncStatusResult.put("result", true);
						syncStatusResult.put("msg", "Last sync time updated successfully");
					}else{
						syncStatusResult.put("result", false);
						syncStatusResult.put("msg", "Device not found with the given synced time : " +  tempSyncRequestedTime);
					}
				}
			}else{
				syncStatusResult.put("result", false);
				syncStatusResult.put("msg", "Invalid Data");
			}
		}else{
			syncStatusResult.put("result", false);
			syncStatusResult.put("msg", "Invalid Requested String");
		}
		LOGGER.debug("Sync Status update End Sync Time : " + new Date());
		return syncStatusResult.toString();
	}
	
	/**
	 * @author Leo
	 * 7. Getting while status tracking table data
	 * @param syncStatusUpdateData
	 * @return
	 */
	@RequestMapping(value = "uploadStatusTrackingData", method = RequestMethod.POST)
	public @ResponseBody String uploadStatusTrackingData(@RequestBody String uploadStatusTrackingData){
		JSONObject syncStatusUpdateDataResult = new JSONObject();
		
		JSONObject jsonObject = new JSONObject(uploadStatusTrackingData);
		if(!jsonObject.has("uploadStatusTrackingData")){
			LOGGER.debug("uploadStatusTrackingData Does Not Found");
			syncStatusUpdateDataResult.put("result", false);
			syncStatusUpdateDataResult.put("msg", "uploadStatusTrackingData Does Not Found");
			return syncStatusUpdateDataResult.toString();
		}
		
		String submitReworkDataEncryptedString = jsonObject.getString("uploadStatusTrackingData");
		byte[] uploadStatusTrackingDataByte = Base64.decodeBase64(submitReworkDataEncryptedString);
		String uploadStatusTrackingDataDecryptedString = new String(uploadStatusTrackingDataByte);
		JSONObject syncStatusUpdateDataObj = new JSONObject(uploadStatusTrackingDataDecryptedString);
		if(!syncStatusUpdateDataObj.has("SyncUserId") || 
				!syncStatusUpdateDataObj.has("ReportingPeriodId") || 
				!syncStatusUpdateDataObj.has("DeviceId") ||
				!syncStatusUpdateDataObj.has("StatusTrackingArray")){
			LOGGER.debug("Invalid Data");
			syncStatusUpdateDataResult.put("result", false);
			syncStatusUpdateDataResult.put("msg", "Invalid Data");
			return syncStatusUpdateDataResult.toString();
		}
		
		Integer syncUserId = syncStatusUpdateDataObj.getInt("SyncUserId");
		Integer reportingPeriodId = syncStatusUpdateDataObj.getInt("ReportingPeriodId");
		String deviceIdString = syncStatusUpdateDataObj.getString("DeviceId");
		boolean lead = false;
		User syncUser = userService.getById(syncUserId);
		Agency syncAgency = agencyService.findByLoginUser(syncUser.getUsername());
		
		List<UserRole> userRoles =  syncUser.getUserRoles();
		
		for (UserRole userRole : userRoles) {
			if(userRole.getName().equals("STATUS_REVIEWER")){
				lead = true;
			}
		}
		ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
		if(syncUser == null || reportingPeriod == null){
			LOGGER.debug("Invalid Data");
			syncStatusUpdateDataResult.put("result", false);
			syncStatusUpdateDataResult.put("msg", "Invalid Data");
			return syncStatusUpdateDataResult.toString();
		}
		
		List<StatusTracking> listStatusTrackings = statusTrackingService.findReworkDataByReportingPeriodAndAgencyAndReadyForReview(reportingPeriod, syncAgency);
		JSONArray webUpdatedDataArray = new JSONArray();
		
		int remaining = 0;
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDateString = df.format(new Date());
		
		JSONArray statusTrackingArray = syncStatusUpdateDataObj.getJSONArray("StatusTrackingArray");
		if (statusTrackingArray != null && statusTrackingArray.length() > 0){
			for (int i = 0 ; i < statusTrackingArray.length(); i++){
				StatusTracking statusTracking;
				StatusTrackingLog statusTrackingLog = new StatusTrackingLog();
				JSONObject statusTrackingObj =  (JSONObject) statusTrackingArray.get(i);
				
				Integer subActivityId = (statusTrackingObj.has("SubActivityID")) ? statusTrackingObj.getInt("SubActivityID") : 0;
				Integer actualStatusPercentage = (statusTrackingObj.has("StatusOfProgressValue")) ? statusTrackingObj.getInt("StatusOfProgressValue") : 0;
				String statusColorCode = (statusTrackingObj.has("StatusColorCode")) ? statusTrackingObj.getString("StatusColorCode") : null;
				String keyProgress = (statusTrackingObj.has("KeyProgress")) ? statusTrackingObj.getString("KeyProgress") : "";
				String reasonForGap = (statusTrackingObj.has("ReasonForGap")) ? statusTrackingObj.getString("ReasonForGap") : "";
				String rectifyTheGap = (statusTrackingObj.has("RectifyTheGap")) ? statusTrackingObj.getString("RectifyTheGap") : "";
				int readyForReview = (statusTrackingObj.has("ReadyForReview")) ? statusTrackingObj.getInt("ReadyForReview") : 0;
				int sentForReview = (statusTrackingObj.has("SentForReview")) ? statusTrackingObj.getInt("SentForReview") : 0;
				int userLevel = (statusTrackingObj.has("UserLevel")) ? statusTrackingObj.getInt("UserLevel") : 0;
				int reviewStatus = (statusTrackingObj.has("ReviewStatus")) ? statusTrackingObj.getInt("ReviewStatus") : 0;
				int reviewerId = (statusTrackingObj.has("ReviewerId")) ? statusTrackingObj.getInt("ReviewerId") : 0;
				String reviewedOn = (statusTrackingObj.has("ReviewedOn")) ? statusTrackingObj.getString("ReviewedOn") : null;
				String reviewFeedback = (statusTrackingObj.has("ReviewFeedback")) ? statusTrackingObj.getString("ReviewFeedback") : "";
				int reworkRequired = (statusTrackingObj.has("ReworkRequired")) ? statusTrackingObj.getInt("ReworkRequired") : 0;
				Integer userId = statusTrackingObj.getInt("StatusUpdateUserId");
				User user = userService.getById(userId);
				Agency agency = agencyService.findByLoginUser(user.getUsername());
				SubActivity subActivity = subActivityService.getById(subActivityId);
				User reviewer = userService.getById(reviewerId);
				if(subActivity == null){
					LOGGER.debug("Invalid Sub Activity Id : " + subActivityId );
					syncStatusUpdateDataResult.put("result", false);
					syncStatusUpdateDataResult.put("msg", "Invalid Sub Activity Id : " + subActivityId );
					return syncStatusUpdateDataResult.toString();
				}
				
				statusTracking = statusTrackingService.findByAgencyAndSubActivityAndReportingPeriodAndUserLevel(agency, subActivity, reportingPeriod, userLevel);
				
				if(statusTracking == null){
					statusTracking  = new StatusTracking();
				}
				if(!statusTracking.isComplete() || (statusTracking.isSentForReview() && reviewStatus != 0)){
					statusTracking.setActualStatusColor(statusColorCode);
					statusTracking.setActualStatusPercentage(actualStatusPercentage.toString());
					statusTracking.setKeyProgress(keyProgress);
					statusTracking.setReasonForGap(reasonForGap);
					statusTracking.setRectifyTheGap(rectifyTheGap);
					statusTracking.setSubActivity(subActivity);
					statusTracking.setUser(user);
					statusTracking.setAgency(agency);
					statusTracking.setDeviceId(deviceIdString);
					statusTracking.setSync_dateTime(formattedDateString);
					statusTracking.setReportingPeriodStatus(reportingPeriod.getReportingStatus());
					statusTracking.setSubActivityStatus(subActivity.getStatus());
					statusTracking.setReportingPeriod(reportingPeriod);
					statusTracking.setComplete(readyForReview == 1 ? true : false);
					statusTracking.setSentForReview(sentForReview == 1 ? true : false);
					statusTracking.setUserLevel(userLevel);
					statusTracking.setReviewStatus(reviewStatus);
					statusTracking.setReviewedBy(reviewer);
					statusTracking.setReviewedOn(reviewedOn);
					statusTracking.setReviewerFeedback(reviewFeedback);
					statusTracking.setReworkRequired(reworkRequired == 1 ? true : false);
					if (statusTracking.getId() == null) {
						statusTrackingLog.setActionType("INSERT");
					} else {
						statusTrackingLog.setActionType("UPDATED");
					}
					statusTrackingLog.setSyncStatus(true);
					statusTracking = statusTrackingService.add(statusTracking);
				}else{
					statusTrackingLog.setSyncStatus(false);
					statusTrackingLog.setActionType("FAILED. Not Updated");
					statusTrackingLog.setSyncFailureMessage("Data is already set as ready for review");
				}
				statusTrackingLog.setSubActivity(subActivity);
				statusTrackingLog.setReportingPeriod(reportingPeriod);
				statusTrackingLog.setUser(user);
				statusTrackingLog.setStatusTracking(statusTracking);
				statusTrackingLog.setActualStatusColor(statusColorCode);
				statusTrackingLog.setActualStatusPercentage(actualStatusPercentage.toString());
				statusTrackingLog.setKeyProgress(keyProgress);
				statusTrackingLog.setReasonForGap(reasonForGap);
				statusTrackingLog.setRectifyTheGap(rectifyTheGap);
				statusTrackingLog.setComplete(readyForReview == 1 ? true : false);
				statusTrackingLog.setSentForReview(sentForReview == 1 ? true : false);
				statusTrackingLog.setUserLevel(userLevel);
				statusTrackingLog.setReviewStatus(reviewStatus);
				statusTrackingLog.setReviewedBy(reviewer);
				statusTrackingLog.setReviewedOn(reviewedOn);
				statusTrackingLog.setReviewerFeedback(reviewFeedback);
				statusTrackingLog.setReworkRequired(reworkRequired == 1 ? true : false);
				statusTrackingLog.setDeviceId(deviceIdString);
				statusTrackingLog.setSync_dateTime(formattedDateString);
				statusTrackingLogService.createStatusTrackingLog(statusTrackingLog);
			}
		}
		
//		Check for the remaining submit for review record
		List<SubmitForReview> submitForReviews = submitForReviewService.findByAgencyAndReportingPeriod(syncAgency, reportingPeriod);
		if (submitForReviews == null || submitForReviews.size() == 0) {
			List<SubActivity> activities = new ArrayList<>();
			List<Integer> reportingPeriodIds = new ArrayList<Integer>();
			List<ReportingPeriod> previousReportingPeriod = reportingPeriodService.getPreviousReportingPeriodByCurrentReportingPeriod(reportingPeriod.getStartdate());
			if(previousReportingPeriod != null && previousReportingPeriod.size() > 0){
				for(ReportingPeriod previousReporting : previousReportingPeriod ){
										reportingPeriodIds.add(previousReporting.getId());
									}
			}
			reportingPeriodIds.add(reportingPeriodId);
			if (lead) {
				activities = subActivityService.getSubActivitiesByReportingPeriodsAndLeadAgency(reportingPeriodIds,syncAgency.getId());
			} else {
				activities = subActivityService.getSubActivitiesByReportingPeriodsAndPartnerAgency(reportingPeriodIds,syncAgency.getId());
			}
			
			List<StatusTracking> statusTrackings = statusTrackingService.findByAgencyAndReportingPeriodAndComplete(syncAgency, reportingPeriod, true);
			if(activities != null && statusTrackings != null){
				remaining = activities.size() - statusTrackings.size();
			}
		} else {
			if (submitForReviews.get(0).isLeadReworkStatus() && !submitForReviews.get(0).isPartnerReworkStatus()) {
				List<StatusTracking> reworkStatusTracking = statusTrackingService.findByAgencyAndReportingPeriodAndReworkRequired(syncAgency, reportingPeriod, true);
				List<StatusTracking> completedStatusTracking = statusTrackingService.findByAgencyAndReportingPeriodAndReworkRequiredAndComplete(syncAgency, reportingPeriod, true, true);
				if(reworkStatusTracking != null && completedStatusTracking != null && reworkStatusTracking.size() > 0){
					remaining = reworkStatusTracking.size() - completedStatusTracking.size();
				}else{
					remaining = -1;
				}
			} else {
				remaining = -1;
			}
		}
//		End Check for the remaining submit for review record
		
		for(StatusTracking detailedData : listStatusTrackings){
			JSONObject detailedDataObj = new JSONObject();
			detailedDataObj.put("SubActivityId", detailedData.getSubActivity().getId());
			detailedDataObj.put("ReportingPeriod", detailedData.getReportingPeriod().getId());
			detailedDataObj.put("UserId", detailedData.getUser().getId());
			detailedDataObj.put("UserName", detailedData.getUser().getUsername());
			detailedDataObj.put("AgencyId", detailedData.getAgency().getId());
			detailedDataObj.put("AgencyName", detailedData.getAgency().getName());
			detailedDataObj.put("StatusColorCode", detailedData.getActualStatusColor());
			detailedDataObj.put("StatusOfProgressValue", detailedData.getActualStatusPercentage());
			detailedDataObj.put("KeyProgress", detailedData.getKeyProgress());
			detailedDataObj.put("ReasonForGap", detailedData.getReasonForGap());
			detailedDataObj.put("RectifyTheGap", detailedData.getRectifyTheGap());
			detailedDataObj.put("ReadyforReview", detailedData.isComplete() == true ? 1 : 0);
			detailedDataObj.put("SentForReview", detailedData.isSentForReview() == true ? 1 : 0);
			detailedDataObj.put("UserLevel", detailedData.getUserLevel());
			if (detailedData.getReviewedBy() != null) {
				detailedDataObj.put("ReviewedBy", detailedData.getReviewedBy().getId());
			} else {
				detailedDataObj.put("ReviewedBy", 0);
			}
			detailedDataObj.put("ReviewedOn", detailedData.getReviewedOn());
			detailedDataObj.put("ReviewerFeedback", detailedData.getReviewerFeedback());
			detailedDataObj.put("ReviewStatus", detailedData.getReviewStatus());
			detailedDataObj.put("ReworkRequired", detailedData.isReworkRequired() == true ? 1 : 0);
			webUpdatedDataArray.put(detailedDataObj);
		}
		
		syncStatusUpdateDataResult.put("result", true);
		syncStatusUpdateDataResult.put("remaining", remaining);
		syncStatusUpdateDataResult.put("webUpdatedData", webUpdatedDataArray);
		syncStatusUpdateDataResult.put("msg", "Successfully synced data");
		
		return syncStatusUpdateDataResult.toString();
	}
	
	/**
	 * @author pushpa
	 * 8. Create submit for review data and status tracking data entries
	 * @param submitForReviewData
	 * @return
	 */
	@RequestMapping(value = "submitForReview", method = RequestMethod.POST)
	public @ResponseBody String submitForReview(@RequestBody String submitForReviewData){
		LOGGER.debug("Getting into submitForReview update web service");
		JSONObject updateUsubmitForReviewResult = new JSONObject();
		List<SubmitForReview> submitForReviews = null;
		SubmitForReview submitForReview = null;
		String submitForReviewDecryptedString = "";
		String formattedDateString = "";
		Date formattedDate = null;
		try{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			formattedDateString = df.format(new Date());
			formattedDate = df.parse(formattedDateString);
			JSONObject submitForReviewDataEncrypted = new JSONObject(submitForReviewData.trim());
			String submitForReviewDataEncryptedString = submitForReviewDataEncrypted.getString("ReportingPeriodFeedback");
			byte[] submitForReviewByteData = Base64.decodeBase64(submitForReviewDataEncryptedString);
			submitForReviewDecryptedString = new String(submitForReviewByteData);
		}catch(Exception e){
			LOGGER.debug("Invalid Data");
			updateUsubmitForReviewResult.put("result", false);
			updateUsubmitForReviewResult.put("msg", "Invalid Data");
			return updateUsubmitForReviewResult.toString();
		}
		
		if(submitForReviewDecryptedString != null && !submitForReviewDecryptedString.isEmpty()){
			JSONObject userFeedback = new JSONObject(submitForReviewDecryptedString);
			String deviceIdString = (userFeedback.has("DeviceId")) ?  userFeedback.getString("DeviceId") : "";
			Integer agencyId = (userFeedback.has("AgencyId")) ? userFeedback.getInt("AgencyId") : 0;
			Integer userId = (userFeedback.has("UserId")) ? userFeedback.getInt("UserId") : 0;
			Integer reportingPeriodId = (userFeedback.has("ReportingPeriodId")) ? userFeedback.getInt("ReportingPeriodId") : 0;
			String keyLearning = (userFeedback.has("KeyLearning")) ? userFeedback.getString("KeyLearning") : "";
			String keyChallenge = (userFeedback.has("KeyChallenge")) ? userFeedback.getString("KeyChallenge") : "";
			String bestPractise = (userFeedback.has("BestPractise")) ? userFeedback.getString("BestPractise") : "";
			String supportNeeds = (userFeedback.has("SupportNeeded")) ? userFeedback.getString("SupportNeeded") : "";
			
			AllowedDeviceInfo allowedDeviceInfo = allowedDeviceService.findByDeviceId(deviceIdString);
			if(allowedDeviceInfo == null){
				LOGGER.debug("Invalid Device : " + deviceIdString);
				updateUsubmitForReviewResult.put("result", false);
				updateUsubmitForReviewResult.put("msg", "Invalid Device : " + deviceIdString);
				return updateUsubmitForReviewResult.toString();
			}
			Agency agency = agencyService.findByAgencyId(agencyId);
			if(agency == null){
				LOGGER.debug("Invalid AgencyId : " + agencyId);
				updateUsubmitForReviewResult.put("result", false);
				updateUsubmitForReviewResult.put("msg", "Invalid AgencyId : " + agencyId);
				return updateUsubmitForReviewResult.toString();
			}
			User user = userService.getById(userId);
			if (user == null){
				LOGGER.debug("Invalid User : " + userId);
				updateUsubmitForReviewResult.put("result", false);
				updateUsubmitForReviewResult.put("msg", "Invalid User : " + userId);
				return updateUsubmitForReviewResult.toString();
			}
			ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
			if(reportingPeriod == null){
				LOGGER.debug("Invalid Reporting Period : " + reportingPeriodId);
				updateUsubmitForReviewResult.put("result", false);
				updateUsubmitForReviewResult.put("msg", "Invalid Reporting Period : " + reportingPeriodId);
				return updateUsubmitForReviewResult.toString();
			}
			
//	Create submit for review 
			boolean checkStatusTracking = false;
			submitForReviews = submitForReviewService.findByAgencyAndReportingPeriod(agency, reportingPeriod);
			if ( submitForReviews == null || submitForReviews.size() == 0){
				checkStatusTracking = true;
				submitForReview = new SubmitForReview();
			}else{
				submitForReview = submitForReviews.get(0);
			}
			submitForReview.setDeviceId(allowedDeviceInfo.getDeviceId());
			submitForReview.setKeyLearning(keyLearning);
			submitForReview.setKeyChallenge(keyChallenge);
			submitForReview.setBestPractice(bestPractise);
			submitForReview.setSupportNeeds(supportNeeds);
			submitForReview.setUser(user);
			submitForReview.setAgency(agency);
			submitForReview.setReportingPeriod(reportingPeriod);
			submitForReview.setSubmit_dateTime(formattedDate);
			submitForReview.setUserLevel(1);
			submitForReview.setPartnerReworkStatus(false);
			submitForReview.setLeadReworkStatus(false);
			submitForReviewService.add(submitForReview);
			
			List<StatusTracking> readyForReview =  new ArrayList<StatusTracking>();
			if(checkStatusTracking){
				readyForReview = statusTrackingService.findByAgencyAndReportingPeriodAndComplete(agency, reportingPeriod, true);
			}else{
				readyForReview = statusTrackingService.findByAgencyAndReportingPeriodAndReworkRequired(agency, reportingPeriod, true);
			}
			for (StatusTracking existingStatusTracking : readyForReview) {
				existingStatusTracking.setReworkRequired(false);
				existingStatusTracking.setReviewStatus(0);
				existingStatusTracking.setComplete(true);
				existingStatusTracking.setSentForReview(true);
				statusTrackingService.add(existingStatusTracking);
				List<StatusTracking> leadReviewedData = statusTrackingService.findBysubActivityAndReportingPeriodAndUserLevel(existingStatusTracking.getSubActivity(), reportingPeriod, 2);
				if(leadReviewedData != null && leadReviewedData.size() > 0){
					for (StatusTracking statusTracking2 : leadReviewedData) {
						if(!statusTracking2.isSentForReview() && statusTracking2.isComplete()){
							statusTracking2.setComplete(false);
							statusTrackingService.add(statusTracking2);
						}
					}
				}
			}
			updateUsubmitForReviewResult.put("result", true);
			updateUsubmitForReviewResult.put("msg", "Data updated successfully");
			return updateUsubmitForReviewResult.toString();
		}else{
			LOGGER.debug("Invalid Data");
			updateUsubmitForReviewResult.put("result", false);
			updateUsubmitForReviewResult.put("msg", "Invalid Data");
			return updateUsubmitForReviewResult.toString();
		}
	}
	
	
	/**
	 * @author pushpa
	 * 9. Create submit for approve data and status tracking entries
	 * @param submitForApproveData
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "submitForApproval", method = RequestMethod.POST)
	public @ResponseBody String submitForApproval(@RequestBody String submitForApproveData) throws ParseException{
		LOGGER.debug("Getting into submitForReview update web service");
		JSONObject updateUsubmitForReviewResult = new JSONObject();
		SubmitForReview submitForReview = null;
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDateString = df.format(new Date());
		
		JSONObject submitForReviewDataEncrypted = new JSONObject(submitForApproveData);
		if(!submitForReviewDataEncrypted.has("submitForApproveData")){
			LOGGER.debug("Invalid Data");
			updateUsubmitForReviewResult.put("result", false);
			updateUsubmitForReviewResult.put("msg", "Invalid Data");
			return updateUsubmitForReviewResult.toString();
		}
		String submitForReviewDataEncryptedString = (submitForReviewDataEncrypted.has("submitForApproveData")) ? submitForReviewDataEncrypted.getString("submitForApproveData") : null;
		byte[] submitForReviewByteData = Base64.decodeBase64(submitForReviewDataEncryptedString);
		String submitForReviewDecryptedString = new String(submitForReviewByteData);
		
		if(submitForReviewDecryptedString != null && !submitForReviewDecryptedString.isEmpty()){
			JSONObject userFeedback = new JSONObject(submitForReviewDecryptedString);
			String deviceIdString = (userFeedback.has("DeviceId")) ?  userFeedback.getString("DeviceId") : "";
			Integer agencyId = (userFeedback.has("AgencyId")) ? userFeedback.getInt("AgencyId") : 0;
			Integer userId = (userFeedback.has("UserId")) ? userFeedback.getInt("UserId") : 0;
			Integer reportingPeriodId = (userFeedback.has("ReportingPeriodId")) ? userFeedback.getInt("ReportingPeriodId") : 0;
			String keyLearning = (userFeedback.has("KeyLearning")) ? userFeedback.getString("KeyLearning") : "";
			String keyChallenge = (userFeedback.has("KeyChallenge")) ? userFeedback.getString("KeyChallenge") : "";
			String bestPractise = (userFeedback.has("BestPractise")) ? userFeedback.getString("BestPractise") : "";
			String supportNeeds = (userFeedback.has("SupportNeeded")) ? userFeedback.getString("SupportNeeded") : "";
			
			AllowedDeviceInfo allowedDeviceInfo = allowedDeviceService.findByDeviceId(deviceIdString);
			if(allowedDeviceInfo == null){
				LOGGER.debug("Invalid Device : " + deviceIdString);
				updateUsubmitForReviewResult.put("result", false);
				updateUsubmitForReviewResult.put("msg", "Invalid Device : " + deviceIdString);
				return updateUsubmitForReviewResult.toString();
			}
			Agency agency = agencyService.findByAgencyId(agencyId);
			if(agency == null){
				LOGGER.debug("Invalid AgencyId : " + agencyId);
				updateUsubmitForReviewResult.put("result", false);
				updateUsubmitForReviewResult.put("msg", "Invalid AgencyId : " + agencyId);
				return updateUsubmitForReviewResult.toString();
			}
			User user = userService.getById(userId);
			if (user == null){
				LOGGER.debug("Invalid User : " + userId);
				updateUsubmitForReviewResult.put("result", false);
				updateUsubmitForReviewResult.put("msg", "Invalid User : " + userId);
				return updateUsubmitForReviewResult.toString();
			}
			ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
			if(reportingPeriod == null){
				LOGGER.debug("Invalid Reporting Period : " + reportingPeriodId);
				updateUsubmitForReviewResult.put("result", false);
				updateUsubmitForReviewResult.put("msg", "Invalid Reporting Period : " + reportingPeriodId);
				return updateUsubmitForReviewResult.toString();
			}
			
//	Create submit for review 
			boolean checkStatusTracking = false;
			submitForReview = submitForReviewService.findByAgencyAndReportingPeriodAndUserLevel(agency, reportingPeriod, 2);
			if ( submitForReview == null){
				checkStatusTracking = true;
				submitForReview = new SubmitForReview();
			}
			submitForReview.setDeviceId(allowedDeviceInfo.getDeviceId());
			submitForReview.setKeyLearning(keyLearning);
			submitForReview.setKeyChallenge(keyChallenge);
			submitForReview.setBestPractice(bestPractise);
			submitForReview.setSupportNeeds(supportNeeds);
			submitForReview.setUser(user);
			submitForReview.setAgency(agency);
			submitForReview.setReportingPeriod(reportingPeriod);
			submitForReview.setSubmit_dateTime(df.parse(formattedDateString));
			submitForReview.setLeadReworkStatus(false);
			submitForReview.setPartnerReworkStatus(false);
			submitForReview.setUserLevel(2);
//	End Create submit for review 
			
			List<StatusTracking> readyForReview =  new ArrayList<StatusTracking>();
			if(checkStatusTracking){
				readyForReview = statusTrackingService.findByAgencyAndReportingPeriodAndComplete(agency, reportingPeriod, true);
			}else{
				readyForReview = statusTrackingService.findByAgencyAndReportingPeriodAndReworkRequired(agency, reportingPeriod, true);
			}
			for (StatusTracking existingStatusTracking : readyForReview) {
				existingStatusTracking.setReworkRequired(false);
				existingStatusTracking.setReviewStatus(0);
				existingStatusTracking.setSentForReview(true);
				statusTrackingService.add(existingStatusTracking);
			}
			submitForReviewService.add(submitForReview);
			updateUsubmitForReviewResult.put("result", true);
			updateUsubmitForReviewResult.put("msg", "Data updated successfully");
			return updateUsubmitForReviewResult.toString();

		}else{
			LOGGER.debug("Invalid Data");
			updateUsubmitForReviewResult.put("result", false);
			updateUsubmitForReviewResult.put("msg", "Invalid Data");
			return updateUsubmitForReviewResult.toString();
		}
	}
	
	/**
	 * @author Leo
	 * 10. Getting list of rework data
	 * @param reviewerData
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "listReworkData", method = RequestMethod.POST)
	public @ResponseBody String listReworkData(@RequestBody String reviewerData) throws ParseException{
		LOGGER.debug("Getting into submitForReview update web service");
		JSONObject submitReworkDataResult = new JSONObject();
		
		JSONObject reviewerDataEncrypted = new JSONObject(reviewerData);
		if(!reviewerDataEncrypted.has("reviewerData")){
			LOGGER.debug("Invalid Data");
			submitReworkDataResult.put("result", false);
			submitReworkDataResult.put("msg", "Invalid Data");
			return submitReworkDataResult.toString();
		}
		String reviewerDataEncryptedString = reviewerDataEncrypted.getString("reviewerData");
		byte[] reviewerDataByteData = Base64.decodeBase64(reviewerDataEncryptedString);
		String submitReworkDataDecryptedString = new String(reviewerDataByteData);
		
		if(submitReworkDataDecryptedString != null && !submitReworkDataDecryptedString.isEmpty()){
			JSONObject submitReworkDataObj = new JSONObject(submitReworkDataDecryptedString);

			if(submitReworkDataObj.has("Summary")){
				JSONObject Summary = submitReworkDataObj.getJSONObject("Summary");
				Integer reportingPeriodId = (Summary.has("ReportingPeriodId")) ? Summary.getInt("ReportingPeriodId") : 0;
				Integer reviewerId = (Summary.has("ReviewerId")) ? Summary.getInt("ReviewerId") : 0;
				
				User reviewerUser = userService.getById(reviewerId);
				ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
				
				List<StatusTracking> tempListstaStatusTrackings = statusTrackingService.findByReportingPeriodAndReviewerAndReviewStatus(reportingPeriod, reviewerUser);
				
				JSONArray reworkSummaryDTOs = new JSONArray();
				
				if(tempListstaStatusTrackings != null && tempListstaStatusTrackings.size() > 0){
					int tempAgencyId = 0;
					int reworkCount = 0;
					JSONObject reworkSummaryDTO = null;
					for (StatusTracking statusTracking : tempListstaStatusTrackings) {
						Agency agency = statusTracking.getAgency();
						if (tempAgencyId != agency.getId()) {
							if (reworkSummaryDTO != null) {
								reworkSummaryDTO.put("TotalRework", reworkCount);
								reworkSummaryDTOs.put(reworkSummaryDTO);
								reworkCount = 0;
							}
							reworkSummaryDTO = new JSONObject();
							reworkSummaryDTO.put("UserId", agency.getId());
							reworkSummaryDTO.put("AgencyName", agency.getName());
							reworkSummaryDTO.put("UserName", "");
							tempAgencyId = agency.getId();
						}
						reworkCount++;
						
					}
					if (reworkSummaryDTO != null) {
						reworkSummaryDTO.put("TotalRework", reworkCount);
						reworkSummaryDTOs.put(reworkSummaryDTO);
					}
					submitReworkDataResult.put("result", true);
					submitReworkDataResult.put("ReworkData", reworkSummaryDTOs);
					return submitReworkDataResult.toString();
				} else {
					submitReworkDataResult.put("result", false);
					submitReworkDataResult.put("msg", "There is no activity required for more information.");
					return submitReworkDataResult.toString();
				}
			}else{
				LOGGER.debug("Invalid Data");
				submitReworkDataResult.put("result", false);
				submitReworkDataResult.put("msg", "Invalid Data");
				return submitReworkDataResult.toString();
			}
		
		}else{
			LOGGER.debug("Invalid Data");
			submitReworkDataResult.put("result", false);
			submitReworkDataResult.put("msg", "Invalid Data");
			return submitReworkDataResult.toString();
		}
	}
	
	
	/**
	 * 11. Submit rework data
	 * @param submitReworkData
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "sendForReworkData", method = RequestMethod.POST)
	public @ResponseBody String submitReworkData(@RequestBody String submitReworkData) throws ParseException{
		LOGGER.debug("Getting into submitForReview update web service");
		JSONObject submitReworkDataResult = new JSONObject();
		
		JSONObject submitForReviewDataEncrypted = new JSONObject(submitReworkData);
		if(!submitForReviewDataEncrypted.has("submitReworkData")){
			LOGGER.debug("Invalid Data");
			submitReworkDataResult.put("result", false);
			submitReworkDataResult.put("msg", "Invalid Data");
			return submitReworkDataResult.toString();
		}
		String submitReworkDataEncryptedString = submitForReviewDataEncrypted.getString("submitReworkData");
		byte[] submitForReviewByteData = Base64.decodeBase64(submitReworkDataEncryptedString);
		String submitReworkDataDecryptedString = new String(submitForReviewByteData);
		
		if(submitReworkDataDecryptedString != null && !submitReworkDataDecryptedString.isEmpty()){
			JSONObject submitReworkDataObj = new JSONObject(submitReworkDataDecryptedString);

			if(submitReworkDataObj.has("Summary")){
				JSONObject Summary = submitReworkDataObj.getJSONObject("Summary");
				Integer reportingPeriodId = (Summary.has("ReportingPeriodId")) ? Summary.getInt("ReportingPeriodId") : 0;
				Integer reviewerId = (Summary.has("ReviewerId")) ? Summary.getInt("ReviewerId") : 0;
				JSONArray partnersId = (Summary.has("UserIdList")) ? Summary.getJSONArray("UserIdList") : null;
				
				User reviewerUser = userService.getById(reviewerId);
				ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
				for (int i = 0; i < partnersId.length(); i++) {
					Integer partnerId = partnersId.getInt(i);

					Agency partnerAgency = agencyService.findByAgencyId(partnerId);
					List<UserRole> userRoles =  reviewerUser.getUserRoles();
					for (UserRole userRole : userRoles) {
						if(userRole.getName().equals("STATUS_REVIEWER")){
							List<StatusTracking> tempListstaStatusTrackings = statusTrackingService.findByReportingPeriodAndReviewerAndAgencyAndReviewStatus(reportingPeriod, reviewerUser, partnerAgency);
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
							}
						}else if(userRole.getName().equals("STATUS_APPROVER")){
							List<StatusTracking> tempListstaStatusTrackings = statusTrackingService.findByReportingPeriodAndReviewerAndAgencyAndReviewStatus(reportingPeriod, reviewerUser, partnerAgency);
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
							}
						}
					}
				
				}
			}
		
			LOGGER.debug("Data Updated Successfully");
			submitReworkDataResult.put("result", true);
			submitReworkDataResult.put("msg", "Data Updated Successfully");
			return submitReworkDataResult.toString();
		}else{
			LOGGER.debug("Invalid Data");
			submitReworkDataResult.put("result", false);
			submitReworkDataResult.put("msg", "Invalid Data");
			return submitReworkDataResult.toString();
		}
	}
}