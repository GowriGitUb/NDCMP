/**
 * 
 */
package com.ceainfotech.ndcmp.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.ceainfotech.ndcmp.model.dto.FilterHierarchy;
import com.ceainfotech.ndcmp.model.dto.KeyActivityDTO;
import com.ceainfotech.ndcmp.model.dto.SubActivityDTO;
import com.ceainfotech.ndcmp.repository.PlanningRepository;
import com.ceainfotech.ndcmp.service.AgencyService;
import com.ceainfotech.ndcmp.service.AuditService;
import com.ceainfotech.ndcmp.service.KeyActivityService;
import com.ceainfotech.ndcmp.service.OutcomeService;
import com.ceainfotech.ndcmp.service.OutputServices;
import com.ceainfotech.ndcmp.service.PlanningServices;
import com.ceainfotech.ndcmp.service.ReportingPeriodService;
import com.ceainfotech.ndcmp.service.StatusService;
import com.ceainfotech.ndcmp.service.StatusTrackingLogService;
import com.ceainfotech.ndcmp.service.StatusTrackingService;
import com.ceainfotech.ndcmp.service.StrategicPillarService;
import com.ceainfotech.ndcmp.service.SubActivityService;
import com.ceainfotech.ndcmp.service.SubmitForReviewService;
import com.ceainfotech.ndcmp.service.ThemeService;
import com.ceainfotech.ndcmp.service.UserService;
import com.ceainfotech.ndcmp.util.ConstantUtil;
import com.ceainfotech.ndcmp.util.PrincipalUtil;
import com.ceainfotech.ndcmp.util.Status;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author bosco
 */
@Controller
@RequestMapping(value = "/api/**")
public class PartnerFilterController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PartnerFilterController.class);

	@Autowired
	private ReportingPeriodService reportingPeriodService;

	@Autowired
	private PlanningServices planningServices;

	@Autowired
	private OutputServices outputService;

	@Autowired
	private OutcomeService outcomeService;

	@Autowired
	private ThemeService themeService;

	@Autowired
	private SubActivityService subActivityService;

	@Autowired
	private AgencyService agencyService;

	@Autowired
	private KeyActivityService keyActivityService;

	@Autowired
	private UserService userService;

	@Autowired
	private StrategicPillarService strategicPillarService;

	@Autowired
	private StatusTrackingService statusTrackingService;

	@Autowired
	StatusTrackingLogService statusTrackingLogService;
	
	@Autowired
	private SubmitForReviewService submitForReviewService;

	@Autowired
	private StatusService statusService;

	@Autowired
	private AuditService auditService;
	
	@Autowired
	private OutputServices outputServices;

	@Autowired
	private ConstantUtil constantUtil;

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	PrincipalUtil principalUtil;
	
	@Autowired
	PlanningRepository planningRepository;

	String action, module = "";
	
	/**
	 * Get Filter Partner Agency Filter Page
	 * @param model
	 * @param authentication
	 * @return
	 */
	@RequestMapping(value = "partnerFilter", method = RequestMethod.GET)
	public String homePage(ModelMap model, Authentication authentication) {
		LOGGER.info("Getting into dashboard page with user and roles {} :");
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.STATUS_UPDATER)){
			List<ReportingPeriod> reportingPeriods = reportingPeriodService.getReportingPeriodListByStatusOpen();
			model.addAttribute("reportingPeriods", reportingPeriods);
			model.addAttribute("statusTracking", new StatusTracking());
			model.addAttribute("strategicPillarList", strategicPillarService.getStrategicPillars());
			model.addAttribute("filterHierarchy", new FilterHierarchy());
			model.addAttribute("submitForReview",new SubmitForReview());
			return "partnerAgency/filterPartner";
		}else{
			return "login";
		}
		
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "getStatusByYear", method = RequestMethod.GET)
	public @ResponseBody
	List<Statuss> getStatusByYear() {
		List<Statuss> list = new ArrayList<Statuss>();
		list = statusService.listAllStatus();
		return list;
	}
	
	/**
	 * @param reportingPeriodId
	 * @return
	 */
	@RequestMapping(value = "getPartnerStrategicPillar", method = RequestMethod.GET)
	public @ResponseBody
	LinkedHashSet<StrategicPillar> getPartnerStrategicPillar(@RequestParam Integer reportingPeriodId, @RequestParam String status) {
		User user = userService.findByUsername(PrincipalUtil.getPrincipal());
		Agency agency = agencyService.findByLoginUser(user.getUsername());
		LinkedHashSet<StrategicPillar> strategicPillars = new LinkedHashSet<StrategicPillar>();
		ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
		if(reportingPeriod != null){
			List<Integer> reportingPeriodIds = new ArrayList<Integer>();
			List<ReportingPeriod> previousReportingPeriod = reportingPeriodService.getPreviousReportingPeriodByCurrentReportingPeriod(reportingPeriod.getStartdate());
			if(previousReportingPeriod != null && previousReportingPeriod.size() > 0){
				for(ReportingPeriod previousReporting : previousReportingPeriod ){
											reportingPeriodIds.add(previousReporting.getId());
										}
			}
			reportingPeriodIds.add(reportingPeriodId);
			List<SubActivity> subActivities = subActivityService.getSubActivitiesByReportingPeriodsAndPartnerAgency(reportingPeriodIds, agency.getId());
			for (SubActivity subActivity : subActivities) {
				boolean addFlag = false;
				if (status.equals("all")){
					addFlag = true;
				}else {
					StatusTracking statusTracking = statusTrackingService.findByAgencyAndSubActivityAndReportingPeriodAndUserLevel(agency, subActivity, reportingPeriod, 1);
					if (statusTracking != null){
						if (status.equals("readyForReview")){
							if (statusTracking.isComplete() && (statusTracking.getReviewStatus() == 0 || statusTracking.getReviewStatus() == -1)){
								addFlag = true;
							}
						}else if(status.equals("notReadyForReview")){
							if (!statusTracking.isComplete()){
								addFlag = true;
							}
						}else if(status.equals("sentForReview")){
							if (statusTracking.isComplete() && !statusTracking.isReworkRequired()){
								addFlag = true;
							}
						}else if(status.equals("needMoreInfo")){
							if (statusTracking.isReworkRequired() && !statusTracking.isComplete()){
								addFlag = true;
							}
						}
					} else if(status.equals("notReadyForReview")){
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
	
	@RequestMapping(value = "loadStatusForStatusUpdator", method = RequestMethod.GET)
	public @ResponseBody Integer loadStatusForStatusUpdator(@RequestParam Integer reportingPeriodId) {
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
	/**Comment by Gowri
	 * @param reportingPeriodId
	 * @return
	 */
	/*@RequestMapping(value = "getReportStrategicPillar", method = RequestMethod.GET)
	public @ResponseBody
	LinkedHashSet<StrategicPillar> getReportStrategicPillar(@RequestParam("reportingPeriodId") String reportingPeriodId) {
		LinkedHashSet<StrategicPillar> strategicPillars = new LinkedHashSet<StrategicPillar>();
		List<String> rpIds = new ArrayList<String>();
		List<Integer> reportingPeriodIds = new ArrayList<Integer>();
		if(reportingPeriodId != null && reportingPeriodId.trim() != null) {
			rpIds = Arrays.asList(reportingPeriodId.split(","));
			if(rpIds != null && rpIds.size() > 0) {
				String id = "0";
				if(rpIds.contains(id)) {
					List<ReportingPeriod> reportingPeriods = reportingPeriodService.getAll();
					if(reportingPeriods != null && reportingPeriods != null) {
						for(ReportingPeriod period : reportingPeriods) {
							reportingPeriodIds.add(period.getId());
						}
						List<SubActivity> subActivities = subActivityService.getSubActivitiesByReportingPeriods(reportingPeriodIds);
						if(subActivities != null && subActivities.size() > 0) {
							for (SubActivity subActivity : subActivities) {
								strategicPillars.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getStrategicPillar());
							}
						}
					}
				} else {
					for(String rpId : rpIds) {
						ReportingPeriod reportingPeriod = reportingPeriodService.getById(Integer.parseInt(rpId));
						if(reportingPeriod != null){
							List<ReportingPeriod> previousReportingPeriod = reportingPeriodService.getPreviousReportingPeriodByCurrentReportingPeriod(reportingPeriod.getStartdate());
							if(previousReportingPeriod != null && previousReportingPeriod.size() > 0){
								for(ReportingPeriod previousReporting : previousReportingPeriod ){
											reportingPeriodIds.add(previousReporting.getId());
										}
							}
							reportingPeriodIds.add(Integer.parseInt(rpId));
						}
					}
					List<SubActivity> subActivities = subActivityService.getSubActivitiesByReportingPeriods(reportingPeriodIds);
					for (SubActivity subActivity : subActivities) {
						strategicPillars.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getStrategicPillar());
					}
				}
			}
		}
		return strategicPillars;
	}*/
	/**
	 * Changes by Gowri
	 * @param model
	 * @param reportingPeriodId
	 * @return
	 */
	@RequestMapping(value = "getReportStrategicPillar", method = RequestMethod.GET)
	public @ResponseBody List<StrategicPillar> getReportStrategicPillar(ModelMap model,@RequestParam("reportingPeriodId") String reportingPeriodId) {
		String [] reportperiod = reportingPeriodId.split(",");
		List<Integer> reportingPeriods = new ArrayList<Integer>();
		List<StrategicPillar> liStrategicPillars = new ArrayList<StrategicPillar>();
		List<String> list = Arrays.asList(reportperiod);
		if(list.contains("0")){
			List<ReportingPeriod>reportingPeriod = reportingPeriodService.getAllStatusOpenAndClosed();
			for (ReportingPeriod reportingPeriod2 : reportingPeriod) {
				reportingPeriods.add(reportingPeriod2.getId());
			}
		}else {
			for(int j=0 ; j<reportperiod.length; j++){
				
				reportingPeriods.add(Integer.parseInt(reportperiod[j]));
		}
	}
		Agency leadAgency = agencyService.findByLoginUser(PrincipalUtil.getPrincipal());
		liStrategicPillars = strategicPillarService.getStrategicPliiersByReportingPeriodsAndLeadAgency(reportingPeriods, leadAgency.getId());
		return liStrategicPillars;
	}
	
	
	@RequestMapping(value = "getPartnerReportStrategicPillar", method = RequestMethod.GET)
	public @ResponseBody List<StrategicPillar> getPartnerReportStrategicPillar(ModelMap model,@RequestParam("reportingPeriodId") String reportingPeriodId) {
		String [] reportperiod = reportingPeriodId.split(",");
		List<Integer> reportingPeriods = new ArrayList<Integer>();
		List<StrategicPillar> liStrategicPillars = new ArrayList<StrategicPillar>();
		List<String> list = Arrays.asList(reportperiod);
		Agency agency = agencyService.findByLoginUser(PrincipalUtil.getPrincipal());
		if(list.contains("0")){
			List<ReportingPeriod>reportingPeriod = reportingPeriodService.getAllStatusOpenAndClosed();
			for (ReportingPeriod reportingPeriod2 : reportingPeriod) {
				reportingPeriods.add(reportingPeriod2.getId());
			}
		}else {
			for(int j=0 ; j<reportperiod.length; j++){
				
				reportingPeriods.add(Integer.parseInt(reportperiod[j]));
		}
	}
		liStrategicPillars = strategicPillarService.getStrategicPliiersByReportingPeriodAndCurrentPartnerAgency(reportingPeriods,agency.getId());
		return liStrategicPillars;
	}
	
	/*@RequestMapping(value = "getPartnerReportStrategicPillar",method = RequestMethod.GET)
	public @ResponseBody List<StrategicPillar> getPartnerReportStrategicPillar (ModelMap modelMap,@RequestParam("reportingPeriodId") String reportingPeriodId){
		String [] reportperiod = reportingPeriodId.split(",");
		List<Integer> reportingPeriods = new ArrayList<Integer>();
		List<StrategicPillar> liStrategicPillars = new ArrayList<StrategicPillar>();
		List<String> list = Arrays.asList(reportperiod);
		Agency agency = agencyService.findByLoginUser(PrincipalUtil.getPrincipal());
		if(list.contains("0")){
			List<ReportingPeriod>reportingPeriod = reportingPeriodService.getAllStatusOpenAndClosed();
			for (ReportingPeriod reportingPeriod2 : reportingPeriod) {
				reportingPeriods.add(reportingPeriod2.getId());
			}
		}else {
			for(int j=0 ; j<reportperiod.length; j++){
				
				reportingPeriods.add(Integer.parseInt(reportperiod[j]));
		}
	}
		liStrategicPillars = strategicPillarService.getStrategicPliiersByReportingPeriodAndCurrentPartnerAgency(reportingPeriods,agency.getId());
		return liStrategicPillars;
	}*/
	/**
	 * @param reportingPeriodId
	 * @param strategicId
	 * @return
	 */
	@RequestMapping(value = "getThemesByStrategicStatusReport",method = RequestMethod.GET)
	public @ResponseBody LinkedHashSet<Theme> getThemesByStrategicStatusReport(@RequestParam Integer reportingPeriodId,@RequestParam Integer strategicId, @RequestParam String status){
		User user = userService.findByUsername(PrincipalUtil.getPrincipal());
		Agency agency = agencyService.findByLoginUser(user.getUsername());
		LinkedHashSet<Theme> themes = new LinkedHashSet<Theme>();
		ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
		if(reportingPeriod != null){
			List<Integer> reportingPeriodIds = new ArrayList<Integer>();
			List<ReportingPeriod> previousReportingPeriod = reportingPeriodService.getPreviousReportingPeriodByCurrentReportingPeriod(reportingPeriod.getStartdate());
			if(previousReportingPeriod != null && previousReportingPeriod.size() > 0){
				for(ReportingPeriod previousReporting : previousReportingPeriod ){
											reportingPeriodIds.add(previousReporting.getId());
										}
			}
			reportingPeriodIds.add(reportingPeriodId);
			List<SubActivity> subActivities = subActivityService.getSubActivitiesByStrategicPillerAndReportingPeriodsAndPartnerAgency(strategicId, reportingPeriodIds, agency.getId());
			for (SubActivity subActivity : subActivities) {
				boolean addFlag = false;
				if (status.equals("all")){
					addFlag = true;
				}else {
					StatusTracking statusTracking = statusTrackingService.findByAgencyAndSubActivityAndReportingPeriodAndUserLevel(agency, subActivity, reportingPeriod, 1);
					if (statusTracking != null){
						if (status.equals("readyForReview")){
							if (statusTracking.isComplete() && (statusTracking.getReviewStatus() == 0 || statusTracking.getReviewStatus() == -1)){
								addFlag = true;
							}
						}else if(status.equals("notReadyForReview")){
							if (!statusTracking.isComplete()){
								addFlag = true;
							}
						}else if(status.equals("sentForReview")){
							if (statusTracking.isComplete() && !statusTracking.isReworkRequired()){
								addFlag = true;
							}
						}else if(status.equals("needMoreInfo")){
							if (statusTracking.isReworkRequired() && !statusTracking.isComplete()){
								addFlag = true;
							}
						}
					}else if(status.equals("notReadyForReview")){
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
	
	
	
	@RequestMapping(value = "loadPartnerThemeByStrategicPillarAndReportingPeriod",method = RequestMethod.GET)
	public @ResponseBody LinkedHashSet<Theme>loadPartnerThemeByStrategicPillarAndReportingPeriod (@RequestParam String reportingPeriodId,@RequestParam String strategicId){
		
		LinkedHashSet<Theme> themes = new LinkedHashSet<Theme>();
		List<Integer> strategicPillarIds = new ArrayList<Integer>();
		List<Integer> reportingPeriodIds = new ArrayList<Integer>();
		List<String> spIds = Arrays.asList(strategicId.split(","));
		List<String> rpIds = Arrays.asList(reportingPeriodId.split(","));
		Agency agency = agencyService.findByLoginUser(PrincipalUtil.getPrincipal());
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
		if(spIds != null && spIds.size() > 0) {
			String id = "0";
			if(spIds.contains(id)) {
				List<StrategicPillar> strategicPillars = strategicPillarService.getStrategicPillars();
				if (strategicPillars != null && strategicPillars.size() > 0) {
					for(StrategicPillar strategicPillar : strategicPillars) {
						strategicPillarIds.add(strategicPillar.getId());
					}
					List<SubActivity> subActivities = subActivityService.getSubActivitiesByListStrategicPillerAndListOpenedReportingPeriodAndPartnerAgency(strategicPillarIds, reportingPeriodIds,agency.getId());
					if(subActivities != null && subActivities.size() > 0) {
						for (SubActivity subActivity : subActivities) {
							themes.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme());
						}
					}
				}
			} else {
				for(String spId : spIds) {
					StrategicPillar pillar = strategicPillarService.getById(Integer.parseInt(spId));
					if(pillar != null && pillar.getId() != null) {
						strategicPillarIds.add(pillar.getId());
					}
				}
				List<SubActivity> subActivities = subActivityService.getSubActivitiesByListStrategicPillerAndListOpenedReportingPeriodAndPartnerAgency(strategicPillarIds, reportingPeriodIds,agency.getId());
				if(subActivities != null && subActivities.size() > 0) {
					for (SubActivity subActivity : subActivities) {
						themes.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme());
					}
				}
			}
		}
		return themes;
	}
	
	
	/**
	 * @param reportingPeriodId
	 * @param strategicId
	 * @return
	 */
	
	@RequestMapping(value = "getThemesByStrategicReport",method = RequestMethod.GET)
	public @ResponseBody LinkedHashSet<Theme> getThemesByStrategicReport(@RequestParam String reportingPeriodId,@RequestParam String strategicId){
		
		LinkedHashSet<Theme> themes = new LinkedHashSet<Theme>();
		List<Integer> strategicPillarIds = new ArrayList<Integer>();
		List<Integer> reportingPeriodIds = new ArrayList<Integer>();
		List<String> spIds = Arrays.asList(strategicId.split(","));
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
		if(spIds != null && spIds.size() > 0) {
			String id = "0";
			Agency leadAgency = agencyService.findByLoginUser(PrincipalUtil.getPrincipal());
			if(spIds.contains(id)) {
				List<StrategicPillar> strategicPillars = strategicPillarService.getStrategicPillars();
				if (strategicPillars != null && strategicPillars.size() > 0) {
					for(StrategicPillar strategicPillar : strategicPillars) {
						strategicPillarIds.add(strategicPillar.getId());
					}
					List<SubActivity> subActivities = subActivityService.getSubActivitiesByStrategicPillarsAndReportingPeriodsAndLeadAgency(strategicPillarIds, reportingPeriodIds, leadAgency.getId());
					if(subActivities != null && subActivities.size() > 0) {
						for (SubActivity subActivity : subActivities) {
							themes.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme());
						}
					}
				}
			} else {
				for(String spId : spIds) {
					StrategicPillar pillar = strategicPillarService.getById(Integer.parseInt(spId));
					if(pillar != null && pillar.getId() != null) {
						strategicPillarIds.add(pillar.getId());
					}
				}
				List<SubActivity> subActivities = subActivityService.getSubActivitiesByStrategicPillarsAndReportingPeriodsAndLeadAgency(strategicPillarIds, reportingPeriodIds, leadAgency.getId());
				if(subActivities != null && subActivities.size() > 0) {
					for (SubActivity subActivity : subActivities) {
						themes.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme());
					}
				}
			}
		}
		return themes;
	}
	
	/*@RequestMapping(value = "getThemesByStrategicReport",method = RequestMethod.GET)
	public @ResponseBody LinkedHashSet<Theme> getThemesByStrategicReport(@RequestParam String reportingPeriodId,@RequestParam String strategicId){
		
		LinkedHashSet<Theme> themes = new LinkedHashSet<Theme>();
		ReportingPeriod reportingPeriod = reportingPeriodService.getById(Integer.parseInt(reportingPeriodId));
		if(reportingPeriod != null){
			List<Integer> reportingPeriodIds = new ArrayList<Integer>();
			List<ReportingPeriod> previousReportingPeriod = reportingPeriodService.getPreviousReportingPeriodByCurrentReportingPeriod(reportingPeriod.getStartdate());
			if(previousReportingPeriod != null && previousReportingPeriod.size() > 0){
				for(ReportingPeriod previousReporting : previousReportingPeriod ){
											reportingPeriodIds.add(previousReporting.getId());
										}
			}
			reportingPeriodIds.add(Integer.parseInt(reportingPeriodId));
			List<SubActivity> subActivities = subActivityService.getSubActivitiesByStrategicPillarAndReportingPeriods(Integer.parseInt(strategicId), reportingPeriodIds);
			for (SubActivity subActivity : subActivities) {
				themes.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme());
			}
		}
		return themes;
	}*/
	
	
	
	
	/**
	 * @param reportingPeriodId
	 * @param themeId
	 * @return
	 */
	@RequestMapping(value = "getOutcomeByStrategicStatusReportTheme", method = RequestMethod.GET)
	public @ResponseBody
	LinkedHashSet<Outcome> getOutcomeByStrategicStatusReportTheme(@RequestParam Integer reportingPeriodId, @RequestParam Integer themeId, @RequestParam String status) {
		User user = userService.findByUsername(PrincipalUtil.getPrincipal());
		Agency agency = agencyService.findByLoginUser(user.getUsername());
		LinkedHashSet<Outcome> outcomes = new LinkedHashSet<Outcome>();
		ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
		if(reportingPeriod != null){
			List<Integer> reportingPeriodIds = new ArrayList<Integer>();
			List<ReportingPeriod> previousReportingPeriod = reportingPeriodService.getPreviousReportingPeriodByCurrentReportingPeriod(reportingPeriod.getStartdate());
			if(previousReportingPeriod != null && previousReportingPeriod.size() > 0){
				for(ReportingPeriod previousReporting : previousReportingPeriod ){
											reportingPeriodIds.add(previousReporting.getId());
										}
			}
			reportingPeriodIds.add(reportingPeriodId);
			List<SubActivity> subActivities = subActivityService.getSubActivitiesByThemeAndOpenedReportingPeriodAndPartnerAgency(themeId, reportingPeriodIds, agency.getId());
			for (SubActivity subActivity : subActivities) {
				boolean addFlag = false;
				if (status.equals("all")){
					addFlag = true;
				}else {
					StatusTracking statusTracking = statusTrackingService.findByAgencyAndSubActivityAndReportingPeriodAndUserLevel(agency, subActivity, reportingPeriod, 1);
					if (statusTracking != null){
						if (status.equals("readyForReview")){
							if (statusTracking.isComplete() && (statusTracking.getReviewStatus() == 0 || statusTracking.getReviewStatus() == -1)){
								addFlag = true;
							}
						}else if(status.equals("notReadyForReview")){
							if (!statusTracking.isComplete()){
								addFlag = true;
							}
						}else if(status.equals("sentForReview")){
							if (statusTracking.isComplete() && !statusTracking.isReworkRequired()){
								addFlag = true;
							}
						}else if(status.equals("needMoreInfo")){
							if (statusTracking.isReworkRequired() && !statusTracking.isComplete()){
								addFlag = true;
							}
						}
					}else if(status.equals("notReadyForReview")){
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
	
	/**
	 * @author PremKumar
	 * @param reportingPeriodId
	 * @param outcomeId
	 * @param status
	 * @return
	 */
	
	@RequestMapping(value = "loadPartnerOutputByOutcomeAndReportingPeriod", method = RequestMethod.GET)
	public @ResponseBody
	LinkedHashSet<Output> loadPartnerOutputByOutcomeAndReportingPeriod(@RequestParam("reportingPeriodId") String reportingPeriodId,@RequestParam("themeId") String themeId,@RequestParam("strategicId") String strategicPillarId ,@RequestParam("outcomeId") String outcomeId) {
		String [] rp = reportingPeriodId.split(",");
		String [] th = themeId.split(",");
		String [] sp = strategicPillarId.split(",");
		String [] ot = outcomeId.split(",");
		List<Integer> reportperiod = new ArrayList<Integer>();
		List<Integer> theme = new ArrayList<Integer>();
		List<Integer> strategic = new ArrayList<Integer>();
		List<Integer> outcome = new ArrayList<Integer>();
		List<String> rplist = Arrays.asList(rp);
		List<String> thlist = Arrays.asList(th);
		List<String> splist = Arrays.asList(sp);
		List<String> otlist = Arrays.asList(ot);
		Agency agency = agencyService.findByLoginUser(PrincipalUtil.getPrincipal());
		if(rplist.contains("0")){
			List<ReportingPeriod>reportingPeriod = reportingPeriodService.getAllStatusOpenAndClosed();
			for (ReportingPeriod reportingPeriod2 : reportingPeriod) {
				reportperiod.add(reportingPeriod2.getId());
			}
		}else {
			for(int j=0 ; j<rp.length; j++){
				reportperiod.add(Integer.parseInt(rp[j]));
		}
		}
		if(splist.contains("0")){
			List<StrategicPillar> strategicPillars = strategicPillarService.getStrategicPliiersByReportingPeriodAndCurrentPartnerAgency(reportperiod,agency.getId());
			for (StrategicPillar strategicPillar : strategicPillars) {
				strategic.add(strategicPillar.getId());
			}
		}else {
			for(int o=0 ; o<sp.length; o++){
				strategic.add(Integer.parseInt(sp[o]));
			}
		}
		if(thlist.contains("0")){
				LinkedHashSet<Theme> themes2 = new LinkedHashSet<Theme>();
				List<SubActivity> activities = subActivityService.getSubActivitiesBylistOfStrategicPillarAndOpenedReportingPeriodAndCurrentAgency(strategic, reportperiod,agency.getId());
				if(activities != null){
				for (SubActivity subActivity : activities) {
					themes2 .add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme());
				}
				
				for (Theme theme2 : themes2) {
					theme.add(theme2.getId());
				}
				}
		}else {
			for(int i=0 ; i<th.length; i++){
				theme.add(Integer.parseInt(th[i]));
			}
		}
		if(otlist.contains("0")){
			LinkedHashSet<Outcome> outcomes = new LinkedHashSet<Outcome>();
			List<SubActivity> subActivities = subActivityService.getSubActivitiesByListOfPillarListOfThemeAndListOfOpenedReportingPeriodAndPartnerAgency(strategic, theme, reportperiod, agency.getId());
			if(subActivities != null){
				for (SubActivity subActivity : subActivities) {
					outcomes.add(subActivity.getKeyActivity().getOutput().getOutcome());
				}
				for (Outcome outcome2 : outcomes) {
					outcome.add(outcome2.getId());
				}
			}
		}else {
			for(int i=0 ; i<ot.length; i++){
				outcome.add(Integer.parseInt(ot[i]));
			}
		}
		
//		List<SubActivity> subActivities = subActivityService.getSubActivitiesByThemeAndOpenedReportingPeriod(themeId, reportingPeriodId);
		List<SubActivity> subActivities = subActivityService.getSubActivitiesByListOfPillarListOfThemeListOfOutcomeAndListOfOpenedReportingPeriodAndPartnerAgency(strategic, theme, outcome, reportperiod, agency.getId());
		if(subActivities != null){
		LinkedHashSet<Output> outputs = new LinkedHashSet<Output>();
		if(subActivities.size() > 0){
			for (SubActivity subActivity : subActivities) {
				outputs.add(subActivity.getKeyActivity().getOutput());
			}
		}
		return outputs;
		}
		return null;
	}
	
	
	/**
	 * @param reportingPeriodId
	 * @param outcomeId
	 * @return
	 */
	@RequestMapping(value = "getOutputByStrategicStatusReportThemeOutcome", method = RequestMethod.GET)
	public @ResponseBody
	LinkedHashSet<Output> getOutputByStrategicStatusReportThemeOutcome(@RequestParam Integer reportingPeriodId, @RequestParam Integer outcomeId, @RequestParam String status) {
		
		User user = userService.findByUsername(PrincipalUtil.getPrincipal());
		Agency agency = agencyService.findByLoginUser(user.getUsername());
		LinkedHashSet<Output> outputs = new LinkedHashSet<Output>();
		ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
		if(reportingPeriod != null){
			List<Integer> reportingPeriodIds = new ArrayList<Integer>();
			List<ReportingPeriod> previousReportingPeriod = reportingPeriodService.getPreviousReportingPeriodByCurrentReportingPeriod(reportingPeriod.getStartdate());
			if(previousReportingPeriod != null && previousReportingPeriod.size() > 0){
				for(ReportingPeriod previousReporting : previousReportingPeriod ){
											reportingPeriodIds.add(previousReporting.getId());
										}
			}
			reportingPeriodIds.add(reportingPeriodId);
			List<SubActivity> subActivities = subActivityService.getSubActivitiesByOutcomeAndReportingPeriodsAndPartnerAgency(outcomeId, reportingPeriodIds, agency.getId());
			for (SubActivity subActivity : subActivities) {
				boolean addFlag = false;
				if (status.equals("all")){
					addFlag = true;
				}else {
					StatusTracking statusTracking = statusTrackingService.findByAgencyAndSubActivityAndReportingPeriodAndUserLevel(agency, subActivity, reportingPeriod, 1);
					if (statusTracking != null){
						if (status.equals("readyForReview")){
							if (statusTracking.isComplete() && (statusTracking.getReviewStatus() == 0 || statusTracking.getReviewStatus() == -1)){
								addFlag = true;
							}
						}else if(status.equals("notReadyForReview")){
							if (!statusTracking.isComplete()){
								addFlag = true;
							}
						}else if(status.equals("sentForReview")){
							if (statusTracking.isComplete() && !statusTracking.isReworkRequired()){
								addFlag = true;
							}
						}else if(status.equals("needMoreInfo")){
							if (statusTracking.isReworkRequired() && !statusTracking.isComplete()){
								addFlag = true;
							}
						}
					}else if(status.equals("notReadyForReview")){
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

	/**
	 * @param reportingPeriodId
	 * @return
	 */
	@RequestMapping(value = "checkSubmit",method = RequestMethod.GET)
	public @ResponseBody SubmitForReview checkSubmit(@RequestParam Integer reportingPeriodId){
		ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
		Agency agency = agencyService.findByLoginUser(PrincipalUtil.getPrincipal());
		List<SubmitForReview> submitForReviews = null;
		SubmitForReview submitForReview = null;
		if (agency != null) {
			submitForReviews = submitForReviewService.findByAgencyAndReportingPeriod(agency, reportingPeriod);
			if (submitForReviews == null || submitForReviews.size() == 0) {
				submitForReview = new SubmitForReview();
				List<Integer> reportingPeriodIds = new ArrayList<Integer>();
				List<ReportingPeriod> previousReportingPeriod = reportingPeriodService.getPreviousReportingPeriodByCurrentReportingPeriod(reportingPeriod.getStartdate());
				if(previousReportingPeriod != null && previousReportingPeriod.size() > 0){
					for(ReportingPeriod previousReporting : previousReportingPeriod ){
											reportingPeriodIds.add(previousReporting.getId());
										}
				}
				reportingPeriodIds.add(reportingPeriodId);
				List<SubActivity> activities = subActivityService.getSubActivitiesByReportingPeriodsAndPartnerAgency(reportingPeriodIds,agency.getId());
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
				submitForReview = submitForReviews.get(0);
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
	 * @param model
	 * @param subActId
	 * @return
	 */
	@RequestMapping(value = "getSubactivityStatus", method = RequestMethod.GET)
	public String subActivityPage(ModelMap model, @RequestParam Integer subActId) {
		if (subActId != null) {
			model.addAttribute("subActivityId", subActId);
			String userName = PrincipalUtil.getPrincipal();
			if (userName != null) {
				User user = userService.findByUsername(userName);
				if (user != null && user.getId() != null) {
					model.addAttribute("userId", user.getId());
				}
			}
			model.addAttribute("statusTracking", new StatusTracking());
		}
		return "partnerAgency/statusUpdater";
	}

	/**
	 * @param subActId
	 * @return
	 */
	@RequestMapping(value = "getSubactivityStatusById", method = RequestMethod.GET)
	public @ResponseBody
	StatusTracking getStatus(@RequestParam Integer subActId, @RequestParam Integer reportingPeriodId) {
		SubActivity subActivity = new SubActivity();
		ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
		StatusTracking statusTracking = new StatusTracking();
		Agency agency = agencyService.findByLoginUser(PrincipalUtil.getPrincipal());
		if (subActId != null) {
			subActivity = subActivityService.getById(subActId);
			statusTracking = statusTrackingService.findByAgencyAndSubActivityAndReportingPeriodAndUserLevel(agency, subActivity, reportingPeriod, 1);
			if (statusTracking == null) {
				List<ReportingPeriod> previousReportingPeriod = reportingPeriodService.getPreviousReportingPeriodByCurrentReportingPeriod(reportingPeriod.getStartdate());
				if(previousReportingPeriod != null && previousReportingPeriod.size() > 0){
					StatusTracking previousStatusTracking = statusTrackingService.findByAgencyAndSubActivityAndReportingPeriodAndUserLevel(agency, subActivity, previousReportingPeriod.get(0), 1);
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
				}
			}
		}
		return statusTracking;
	}

	/**
	 * @param statusTracking
	 * @param subActivityId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveSubActivityStatus", method = RequestMethod.GET)
	public @ResponseBody
	StatusTracking saveStatus(@RequestParam("statusTracking") String statusTracking, @RequestParam("subActivityId") Integer subActivityId) throws Exception {
		Gson gson = new GsonBuilder().create();
		SubActivity subActivity = subActivityService.getById(subActivityId);
		Statuss statuss = new Statuss();
		StatusTracking statusTracking2 = gson.fromJson(statusTracking, StatusTracking.class);
		ReportingPeriod reportingPeriod = reportingPeriodService.getById(statusTracking2.getReportingPeriodId());
		
		statuss = statusService.findStatusByColor(statusTracking2.getActualStatusColor());
		User user = userService.findByUsername(PrincipalUtil.getPrincipal());
		Agency agency = agencyService.findByLoginUser(PrincipalUtil.getPrincipal());
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDateString = df.format(new Date());
		
		StatusTrackingLog statusTrackingLog = new StatusTrackingLog();
		statusTrackingLog.setActionType("INSERT");
		statusTrackingLog.setSubActivity(subActivity);
		statusTrackingLog.setReportingPeriod(reportingPeriod);
		statusTrackingLog.setUser(user);
		statusTrackingLog.setActualStatusColor(statusTracking2.getActualStatusColor());
		statusTrackingLog.setActualStatusPercentage(statusTracking2.getActualStatusPercentage());
		statusTrackingLog.setKeyProgress(statusTracking2.getKeyProgress());
		statusTrackingLog.setReasonForGap(statusTracking2.getReasonForGap());
		statusTrackingLog.setRectifyTheGap(statusTracking2.getRectifyTheGap());
		statusTrackingLog.setComplete(statusTracking2.isComplete());
		statusTrackingLog.setUserLevel(1);
		statusTrackingLog.setSync_dateTime(formattedDateString);
		
		StatusTracking existingStatusTracking = statusTrackingService.findByAgencyAndSubActivityAndReportingPeriodAndUserLevel(agency, subActivity, reportingPeriod, 1);
		if(existingStatusTracking != null){
			statusTracking2.setReviewedBy(existingStatusTracking.getReviewedBy());
			statusTracking2.setReviewedOn(existingStatusTracking.getReviewedOn());
			statusTracking2.setReviewStatus(existingStatusTracking.getReviewStatus());
			statusTracking2.setReworkRequired(existingStatusTracking.isReworkRequired());
			statusTracking2.setReviewerFeedback(existingStatusTracking.getReviewerFeedback());
			statusTracking2.setId(existingStatusTracking.getId());
			statusTrackingLog.setStatusTracking(existingStatusTracking);
			statusTrackingLog.setActionType("UPDATED");			
			if (existingStatusTracking.isComplete()) {
				statusTrackingLog.setSyncStatus(false);
				statusTrackingLog.setActionType("FAILED. Not Updated");
				statusTrackingLog.setSyncFailureMessage("Data is already set as ready for review");
			}
		}
		
		if (subActivity != null) {
			statusTracking2.setSubActivity(subActivity);
			statusTracking2.setSubActivityStatus(subActivity.getStatus());
		}
		
		if (user != null && user.getId() != null) {
			statusTracking2.setUser(user);
			statusTracking2.setAgency(agency);
			statusTrackingLog.setUser(user);
		}
		
		if(reportingPeriod != null){
			statusTracking2.setReportingPeriod(reportingPeriod);
			statusTracking2.setReportingPeriodStatus(reportingPeriod.getReportingStatus());
		}
		
		if (statuss != null) {
			statusTracking2.setStatuss(statuss);
		}
		statusTracking2.setUserLevel(1);
		statusTracking2.setSync_dateTime(formattedDateString);
		
		if(statusTracking2.isReworkRequired() && statusTracking2.isComplete()){
			statusTracking2.setReviewStatus(0);
		}
		
		if (!statusTrackingLog.getActionType().equals("FAILED. Not Updated")) {
			statusTracking2 = statusTrackingService.add(statusTracking2);
			statusTrackingLog.setStatusTracking(statusTracking2);
		}
		
		statusTrackingLogService.createStatusTrackingLog(statusTrackingLog);
		
		return statusTracking2;
	}

	/**
	 * @param statusTracking
	 * @return
	 */
	@RequestMapping(value = "createStatusTracking", method = RequestMethod.POST)
	public ModelAndView createStatusTracking(@ModelAttribute StatusTracking statusTracking) {
		
		if (statusTracking != null && statusTracking.getId() != null){
			StatusTracking existingStatusTracking = statusTrackingService.getById(statusTracking.getId());
			statusTracking.setReviewedBy(existingStatusTracking.getReviewedBy());
			statusTracking.setReviewedOn(existingStatusTracking.getReviewedOn());
			statusTracking.setReviewStatus(existingStatusTracking.getReviewStatus());
			statusTracking.setReworkRequired(existingStatusTracking.isReworkRequired());
		}
		
		if (statusTracking != null && statusTracking.getUserId() != null) {
			User user = userService.getById(statusTracking.getUserId());
			if (user != null && user.getId() != null) {
				Agency agency = agencyService.findByLoginUser(user.getUsername());
				statusTracking.setUser(user);
				statusTracking.setAgency(agency);
			}
		}
		if (statusTracking != null && statusTracking.getSubActivityId() != null) {
			SubActivity subActivity = subActivityService.getById(statusTracking.getSubActivityId());
			if (subActivity != null && subActivity.getId() != null) {
				statusTracking.setSubActivity(subActivity);
			}
		}
		if (statusTracking != null && statusTracking.getActualStatusColor() != null) {
			Statuss status = statusService.findStatusByColor(statusTracking.getActualStatusColor());
			if (status != null && status.getId() != null) {
				statusTracking.setStatuss(status);
			}
		}
		statusTrackingService.add(statusTracking);
		return new ModelAndView("redirect:partnerFilter");
	}

	/**
	 * @param model
	 * @param outputId
	 * @return
	 */
	@RequestMapping(value = "getResponsibleEntityByOutput", method = RequestMethod.GET)
	public @ResponseBody
	List<Agency> getResponsibleEntityByOutput(ModelMap model, @RequestParam Integer outputId) {
		List<Agency> agencies = agencyService.findByStatus(Status.ACTIVE.getName());
		return agencies;

	}

	@RequestMapping(value = "submitFilter", method = RequestMethod.GET)
	public String submitFilter(ModelMap model, Authentication authentication) {
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.STATUS_UPDATER)){
			model.addAttribute("filterHierarchy", new FilterHierarchy());
			List<ReportingPeriod> reportingPeriods = reportingPeriodService.getReportingPeriodListByStatusOpen();
			
			/*User user = userService.findByUsername(PrincipalUtil.getPrincipal());
			List<ReportingPeriod> reportingPeriods = reportingPeriodService.findByReportingPeriodByUser(user);*/
			
			model.addAttribute("submitForReview",new SubmitForReview());
			model.addAttribute("reportingPeriods", reportingPeriods);
			return "partnerAgency/SubmitFilter";
		}else{
			return "login";
		}
		
	}
	
	/**
	 * @param model
	 * @param authentication
	 * @return
	 */
	@RequestMapping(value = "submitForReview", method = RequestMethod.GET)
	public String submitForReview(ModelMap model, Authentication authentication) {
		model.addAttribute("submitForReview", new SubmitForReview());
		return "partnerAgency/submitForReview";
	}

	/**
	 * @param model
	 * @param filterHierarchy
	 * @return
	 */
	@RequestMapping(value = "getFilterPartner", method = RequestMethod.GET)
	public @ResponseBody
	List<KeyActivityDTO> getFilterPartner(ModelMap model, @ModelAttribute FilterHierarchy filterHierarchy) {

		List<KeyActivityDTO> keyActivityDTOs = new ArrayList<KeyActivityDTO>();
		List<SubActivityDTO> subActivityDTOs = new ArrayList<SubActivityDTO>();
		LinkedHashSet<KeyActivity> keyActivities = new LinkedHashSet<KeyActivity>();
		User currentUser = userService.findByUsername(PrincipalUtil.getPrincipal());
		ReportingPeriod reportingPeriod = reportingPeriodService.getById(Integer.parseInt(filterHierarchy.getYear()));
		Integer outputId = Integer.parseInt( filterHierarchy.getOutput());
		Agency leadAgency = agencyService.findByLoginUser(PrincipalUtil.getPrincipal());
		List<Integer> reportingPeriodIds = new ArrayList<Integer>();
		List<SubActivity> subActivities = new ArrayList<SubActivity>();
		if(reportingPeriod != null){
			
			List<ReportingPeriod> previousReportingPeriod = reportingPeriodService.getPreviousReportingPeriodByCurrentReportingPeriod(reportingPeriod.getStartdate());
			if(previousReportingPeriod != null && previousReportingPeriod.size() > 0){
				for(ReportingPeriod previousReporting : previousReportingPeriod ){
											reportingPeriodIds.add(previousReporting.getId());
										}
			}
			reportingPeriodIds.add(reportingPeriod.getId());
			subActivities= subActivityService.getSubActivitiesByOutputAndReportingPeriodsAndPartnerAgency(outputId, reportingPeriodIds, leadAgency.getId());
			for (SubActivity subActivity : subActivities) {
				keyActivities.add(subActivity.getKeyActivity());
			}
		}
		int count = 0;
		for (KeyActivity keyActivity : keyActivities) {
			subActivityDTOs = new ArrayList<SubActivityDTO>();
			List<SubActivity> subActivities1 = subActivityService.getSubActivitiesByKeyActivityAndReportingPeriodsAndPartnerAgency(keyActivity.getId(),reportingPeriodIds,leadAgency.getId());
			if (subActivities1.size() > 0) {
				for (SubActivity subActivity : subActivities1) {
					Agency agency = agencyService.findByLoginUser(currentUser.getUsername());
					StatusTracking statusTracking = statusTrackingService.findByAgencyAndSubActivityAndReportingPeriodAndUserLevel(agency, subActivity, reportingPeriod,1);
					String statusIcon = "";

					Planning planning = planningRepository.findBySubActivityAndReportingPeriod(subActivity,reportingPeriod);
					if(planning == null){
						subActivity.setCarryOverStatus(true);
					}
					
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
					boolean addFlag = false;
					if(filterHierarchy.getStatus().equals("all")) {
						addFlag = true;
					} else if(filterHierarchy.getStatus().equals("readyForReview") && statusTracking != null && statusTracking.getId() != null && statusTracking.isComplete() && (statusTracking.getReviewStatus() == 0 || statusTracking.getReviewStatus() == -1)) {
						addFlag = true;
					} else if(filterHierarchy.getStatus().equals("notReadyForReview")) {
						if(statusTracking == null || (statusTracking != null && !statusTracking.isComplete())) {
							addFlag = true;
						}
					} else if(filterHierarchy.getStatus().equals("sentForReview") && statusTracking != null && statusTracking.isComplete() && !statusTracking.isReworkRequired()) {
							addFlag = true;
					} else if(filterHierarchy.getStatus().equals("needMoreInfo")&& statusTracking != null && statusTracking.isReworkRequired() && !statusTracking.isComplete()) {
							addFlag = true;
					}
					
					if (addFlag) {
						subActivityDTOs.add(new SubActivityDTO(subActivity.getId(), subActivity.getName(), null, subActivity.getColorCode(),subActivity.getSequenceNumber(), statusIcon,subActivity.isCarryOverStatus()));
					}
				}
			}
			if(subActivityDTOs.size() > 0){
				keyActivityDTOs.add(new KeyActivityDTO(keyActivity.getId(),
						keyActivity.getSequenceNumber(), keyActivity.getName(),
						keyActivity.getStatus(), subActivityDTOs));
			}
		}
		System.out.println(count);
		return keyActivityDTOs;
	}
	
	/**
	 * @param submitForReview
	 * @return
	 */
	@RequestMapping(value = "createSubmitForReview", method = RequestMethod.POST)
	public ModelAndView createSubmitForReview(@ModelAttribute SubmitForReview submitForReview) {
		if (submitForReview != null) {
			User user = userService.findByUsername(PrincipalUtil.getPrincipal());
			ReportingPeriod reportingPeriod = reportingPeriodService.getById(Integer.parseInt(submitForReview.getReportPeriodId()));
			if (user != null && user.getId() != null) {
				Agency agency = agencyService.findByLoginUser(PrincipalUtil.getPrincipal());
				boolean updateFlag = true;
				submitForReview.setUser(user);
				List<SubmitForReview> editSubmitForReviews = submitForReviewService.findByAgencyAndReportingPeriod(agency, reportingPeriod);
				if (editSubmitForReviews != null && editSubmitForReviews.size() > 0) {
					submitForReview.setId(editSubmitForReviews.get(0).getId());
					submitForReview.setLeadReworkStatus(editSubmitForReviews.get(0).isLeadReworkStatus());
					if (editSubmitForReviews.get(0).isLeadReworkStatus() && !editSubmitForReviews.get(0).isPartnerReworkStatus()) {
						submitForReview.setPartnerReworkStatus(false);
						submitForReview.setLeadReworkStatus(false);
						List<StatusTracking> readyForReview = statusTrackingService.findByAgencyAndReportingPeriodAndReworkRequired(agency, reportingPeriod, true);
						for (StatusTracking statusTracking : readyForReview) {
							statusTracking.setReworkRequired(false);
							statusTracking.setReviewStatus(0);
							statusTracking.setSentForReview(true);
							statusTrackingService.add(statusTracking);
							List<StatusTracking> leadReviewedData = statusTrackingService.findBysubActivityAndReportingPeriodAndUserLevel(statusTracking.getSubActivity(), reportingPeriod, 2);
							if(leadReviewedData != null && leadReviewedData.size() > 0){
								for (StatusTracking statusTracking2 : leadReviewedData) {
									if(!statusTracking2.isSentForReview() && statusTracking2.isComplete()){
										statusTracking2.setComplete(false);
										statusTrackingService.add(statusTracking2);
									}
								}
							}
						}
					} else {
						updateFlag = false;
					}
				} else {
					List<StatusTracking> readyForReview = statusTrackingService.findByAgencyAndReportingPeriodAndComplete(agency, reportingPeriod, true);
					for (StatusTracking statusTracking : readyForReview) {
						statusTracking.setReworkRequired(false);
						statusTracking.setReviewStatus(0);
						statusTracking.setSentForReview(true);
						statusTrackingService.add(statusTracking);
						List<StatusTracking> leadReviewedData = statusTrackingService.findBysubActivityAndReportingPeriodAndUserLevel(statusTracking.getSubActivity(), reportingPeriod, 2);
						if(leadReviewedData != null && leadReviewedData.size() > 0){
							for (StatusTracking statusTracking2 : leadReviewedData) {
								if(!statusTracking2.isSentForReview() && statusTracking2.isComplete()){
									statusTracking2.setComplete(false);
									statusTrackingService.add(statusTracking2);
								}
							}
						}
					}	
				}
				

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					submitForReview.setSubmit_dateTime(dateFormat.parse(dateFormat.format(new Date())));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				submitForReview.setAgency(agency);
				submitForReview.setUserLevel(1);
				submitForReview.setReportingPeriod(reportingPeriod);
				if (updateFlag) {
					submitForReviewService.add(submitForReview);	
				}
			}
		}
		//submitForReviewService.add(submitForReview);
		return new ModelAndView("redirect:submitFilter");
	}
}
