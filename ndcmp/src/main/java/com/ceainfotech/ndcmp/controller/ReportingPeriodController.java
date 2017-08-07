/**
 * 
 */
package com.ceainfotech.ndcmp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.ceainfotech.ndcmp.model.Quarter;
import com.ceainfotech.ndcmp.model.ReportingPeriod;
import com.ceainfotech.ndcmp.repository.ReportingPeriodRepository;
import com.ceainfotech.ndcmp.service.QuarterService;
import com.ceainfotech.ndcmp.service.ReportingPeriodService;
import com.ceainfotech.ndcmp.service.UserService;
import com.ceainfotech.ndcmp.util.ConstantUtil;
import com.ceainfotech.ndcmp.util.PrincipalUtil;
import com.ceainfotech.ndcmp.util.Status;

/**
 * @author bosco
 * 
 */

@RequestMapping(value = "/api/**")
@Controller
public class ReportingPeriodController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

	@Autowired
	UserService userService;

	@Autowired
	ReportingPeriodService reportingPeriodService;
	
	@Autowired
	private ConstantUtil constantUtil;

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ReportingPeriodRepository reportingPeriodRepository;
	
	@Autowired
	PrincipalUtil principalUtil;
	
	@Autowired
	QuarterService quarterService; 
	
	String action,module="";


	/**
	 * List all the reporting period
	 * 
	 * @param modelMap
	 * @param authentication
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "reportingList", method = RequestMethod.GET)
	public ModelAndView reportingPeriod(ModelMap modelMap, Authentication authentication, HttpServletRequest request) {

		LOGGER.info("Listing All ReportingPeriods ");
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE) || principalUtil.getCurrentUserRole().equals(ConstantUtil.PROJECTADMIN_ROLE) || principalUtil.getCurrentUserRole().equals(ConstantUtil.PROJECT_PLANNER)){
			//HttpSession session = request.getSession();
			//String username = (String) session.getAttribute("username");
			//getAccessRights(modelMap, username);
			List<ReportingPeriod> reportingPeriod = new ArrayList<ReportingPeriod>();
			reportingPeriod = reportingPeriodService.getReportingPeriod();
			modelMap.addAttribute("reportinglist", reportingPeriod);
			return new ModelAndView("reportingperiod/reportingPeriodList");
		}else{
			return new ModelAndView("login");
		}
	}

	/**
	 * Get the reporting period to add or edit
	 * 
	 * @param reportingPeriod
	 * @param reportingPeriodId
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "/createReportingPeriod", method = RequestMethod.GET)
	public ModelAndView getcreateReportingPeriodPage(@ModelAttribute ReportingPeriod reportingPeriod, @RequestParam Integer reportingPeriodId,
			HttpServletRequest request) {
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE) || principalUtil.getCurrentUserRole().equals(ConstantUtil.PROJECTADMIN_ROLE) || principalUtil.getCurrentUserRole().equals(ConstantUtil.PROJECT_PLANNER)){
			ModelAndView view = new ModelAndView("reportingperiod/reportingPeriodadd");

			if (reportingPeriodId != 0) {
				reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
			}
			List<ReportingPeriod> reportingPeriods = new ArrayList<ReportingPeriod>();
			reportingPeriods = reportingPeriodService.getReportingPeriod();
			view.addObject("reportingYear", reportingPeriods);
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			if (reportingPeriodId != 0) {
				reportingPeriod.setsDate(format.format(reportingPeriod.getStartdate()));
				reportingPeriod.seteDate(format.format(reportingPeriod.getEnddate()));
			}
			List<Quarter> quarters = quarterService.listAllQuarters();
			view.addObject("quarterList", quarters);
			view.addObject("reportingPeriod", reportingPeriod);
			return view;
		}else{
			return new ModelAndView("login");
		}
		
	}

	/**
	 * Save or update the reporting period
	 * 
	 * @param reportingPeriod
	 * @return
	 * @throws Exception 
	 */

	@RequestMapping(value = "/saveReportingPeriod", method = RequestMethod.POST)
	public ModelAndView saveReportingPeriod(@ModelAttribute ReportingPeriod reportingPeriod) throws Exception {
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE) || principalUtil.getCurrentUserRole().equals(ConstantUtil.PROJECTADMIN_ROLE) || principalUtil.getCurrentUserRole().equals(ConstantUtil.PROJECT_PLANNER)){
			ModelAndView modelAndView = new ModelAndView();
			String startDate = reportingPeriod.getsDate();
			String endDate = reportingPeriod.geteDate();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date date = sdf.parse(startDate);
			Date date2 = sdf.parse(endDate);
			String quarter = reportingPeriod.getName();
			if(reportingPeriod.getId() == null){
				ReportingPeriod yearAndName = reportingPeriodService.getByYearAndName(reportingPeriod.getYear(), reportingPeriod.getName());
				if(yearAndName != null){
					ModelAndView view = new ModelAndView("reportingperiod/reportingPeriodadd");
					List<ReportingPeriod> reportingPeriods1 = new ArrayList<ReportingPeriod>();
					reportingPeriods1 = reportingPeriodService.getReportingPeriod();
					view.addObject("reportingYear", reportingPeriods1);
					//reportingPeriod.setReportingStatus("Not-Started");
					view.addObject("openStatus", true);
					List<Quarter> quarters = quarterService.listAllQuarters();
					view.addObject("quarterList", quarters);
					view.addObject("reportingPeriod", reportingPeriod);
					view.addObject("nameErrorMessage", "Quarter already exist for this Year");
					return view;
				}
			}else{
				ReportingPeriod yearAndName = reportingPeriodService.getByYearAndName(reportingPeriod.getYear(), reportingPeriod.getName());
				ReportingPeriod yearAndNameWithId = reportingPeriodService.findByIdAndNameAndYear(reportingPeriod.getId(),reportingPeriod.getName(),reportingPeriod.getYear());
				if(yearAndNameWithId == null){
					if(yearAndName != null){
						ModelAndView view = new ModelAndView("reportingperiod/reportingPeriodadd");
						List<ReportingPeriod> reportingPeriods1 = new ArrayList<ReportingPeriod>();
						reportingPeriods1 = reportingPeriodService.getReportingPeriod();
						view.addObject("reportingYear", reportingPeriods1);
						view.addObject("reportingPeriod", reportingPeriod);
						view.addObject("openStatus", true);
						List<Quarter> quarters = quarterService.listAllQuarters();
						view.addObject("quarterList", quarters);
						view.addObject("nameErrorMessage", "Quarter already exist for this Year");
						return view;
					}
				}
			}
			
			if(date.after(date2)){
				ModelAndView view = new ModelAndView("reportingperiod/reportingPeriodadd");
				List<ReportingPeriod> reportingPeriods = new ArrayList<ReportingPeriod>();
				reportingPeriods = reportingPeriodService.getReportingPeriod();
				view.addObject("reportingYear", reportingPeriods);
				view.addObject("reportingPeriod", reportingPeriod);
				view.addObject("openStatus", true);
				List<Quarter> quarters = quarterService.listAllQuarters();
				view.addObject("quarterList", quarters);
				view.addObject("dateErrorMessage", "End date should be greater than the start date");
				return view;
			}
			
			if(reportingPeriod.getId() == null){
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
				SimpleDateFormat queryDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date dateStartDate = simpleDateFormat.parse(startDate);
				Date dateEndDate = simpleDateFormat.parse(endDate);
				String formattedStartDate = queryDateFormat.format(dateStartDate);
				String formattedEndDate = queryDateFormat.format(dateEndDate);
				List<ReportingPeriod> listOfReportingPeriod = reportingPeriodService.getReportingPeriodListByStartAndEndDate(formattedStartDate,formattedEndDate);
				
				if(listOfReportingPeriod != null && listOfReportingPeriod.size() > 0 && !listOfReportingPeriod.isEmpty()){
					ModelAndView view = new ModelAndView("reportingperiod/reportingPeriodadd");
					List<ReportingPeriod> reportingPeriods1 = new ArrayList<ReportingPeriod>();
					reportingPeriods1 = reportingPeriodService.getReportingPeriod();
					view.addObject("reportingYear", reportingPeriods1);
					view.addObject("reportingPeriod", reportingPeriod);
					view.addObject("openStatus", true);
					List<Quarter> quarters = quarterService.listAllQuarters();
					view.addObject("quarterList", quarters);
					view.addObject("dateComparisonErrorMessage", "Reporting period date is over lapping");
					return view;
				}
				
				/*String year = reportingPeriod.getYear();
				int startDateYear = date.getYear() + 1900;
				int endDateYear = date2.getYear() + 1900;
				int orginalYear = Integer.parseInt(year);
				if(orginalYear != startDateYear){
					ModelAndView view = new ModelAndView("reportingperiod/reportingPeriodadd");
					List<ReportingPeriod> reportingPeriods1 = new ArrayList<ReportingPeriod>();
					reportingPeriods1 = reportingPeriodService.getReportingPeriod();
					view.addObject("reportingYear", reportingPeriods1);
					view.addObject("reportingPeriod", reportingPeriod);
					view.addObject("openStatus", true);
					view.addObject("dateComparisonErrorMessage1", "Selected year not match with selected date");
					return view;
				}
				if(orginalYear != endDateYear){
					ModelAndView view = new ModelAndView("reportingperiod/reportingPeriodadd");
					List<ReportingPeriod> reportingPeriods1 = new ArrayList<ReportingPeriod>();
					reportingPeriods1 = reportingPeriodService.getReportingPeriod();
					view.addObject("reportingYear", reportingPeriods1);
					view.addObject("reportingPeriod", reportingPeriod);
					view.addObject("openStatus", true);
					view.addObject("dateComparisonErrorMessage2", "Selected year not match with selected date");
					return view;
				}*/
				
				
			}else{
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
				SimpleDateFormat queryDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date dateStartDate = simpleDateFormat.parse(startDate);
				Date dateEndDate = simpleDateFormat.parse(endDate);
				String formattedStartDate = queryDateFormat.format(dateStartDate);
				String formattedEndDate = queryDateFormat.format(dateEndDate);
				List<ReportingPeriod> listOfReportingPeriod = reportingPeriodService.getReportingPeriodListByStartAndEndDate(formattedStartDate,formattedEndDate);
				if(listOfReportingPeriod.size() > 1){
					for(ReportingPeriod period : listOfReportingPeriod){
						if (!period.getId().equals(reportingPeriod.getId())) {
							ModelAndView view = new ModelAndView("reportingperiod/reportingPeriodadd");
							List<ReportingPeriod> reportingPeriods1 = new ArrayList<ReportingPeriod>();
							reportingPeriods1 = reportingPeriodService.getReportingPeriod();
							view.addObject("reportingYear", reportingPeriods1);
							view.addObject("reportingPeriod", reportingPeriod);
							view.addObject("openStatus", true);
							List<Quarter> quarters = quarterService.listAllQuarters();
							view.addObject("quarterList", quarters);
							view.addObject("dateComparisonErrorMessage","Reporting period date is over lapping");
							return view;
						}
					}
				}
				
				/*String year = reportingPeriod.getYear();
				int startDateYear = date.getYear() + 1900;
				int endDateYear = date2.getYear() + 1900;
				int orginalYear = Integer.parseInt(year);
				if(orginalYear != startDateYear){
					ModelAndView view = new ModelAndView("reportingperiod/reportingPeriodadd");
					List<ReportingPeriod> reportingPeriods1 = new ArrayList<ReportingPeriod>();
					reportingPeriods1 = reportingPeriodService.getReportingPeriod();
					view.addObject("reportingYear", reportingPeriods1);
					view.addObject("reportingPeriod", reportingPeriod);
					view.addObject("openStatus", true);
					view.addObject("dateComparisonErrorMessage1", "Selected year not match with selected date");
					return view;
				}
				if(orginalYear != endDateYear){
					ModelAndView view = new ModelAndView("reportingperiod/reportingPeriodadd");
					List<ReportingPeriod> reportingPeriods1 = new ArrayList<ReportingPeriod>();
					reportingPeriods1 = reportingPeriodService.getReportingPeriod();
					view.addObject("reportingYear", reportingPeriods1);
					view.addObject("reportingPeriod", reportingPeriod);
					view.addObject("openStatus", true);
					view.addObject("dateComparisonErrorMessage2", "Selected year not match with selected date");
					return view;
				}*/
			}
			
			if(reportingPeriod.getReportingStatus() != null){
				if(reportingPeriod.getReportingStatus().equalsIgnoreCase("Open")){
					if(reportingPeriod.getId() == null){
						List<ReportingPeriod> reportingPeriods = reportingPeriodService.findByReportingStatus(reportingPeriod.getReportingStatus());
						if(!reportingPeriods.isEmpty() && reportingPeriods.size() > 0 && reportingPeriods != null){
							ModelAndView view = new ModelAndView("reportingperiod/reportingPeriodadd");
							List<ReportingPeriod> reportingPeriods1 = new ArrayList<ReportingPeriod>();
							reportingPeriods1 = reportingPeriodService.getReportingPeriod();
							view.addObject("reportingYear", reportingPeriods1);
							view.addObject("reportingPeriod", reportingPeriod);
							view.addObject("openStatus", true);
							List<Quarter> quarters = quarterService.listAllQuarters();
							view.addObject("quarterList", quarters);
							view.addObject("statusErrorMessage", "Already another reporting period is Open");
							return view;
						}
					}else{
						ReportingPeriod reportingPeriod2 = reportingPeriodService.findByIdAndReportingStatus(reportingPeriod.getId(), reportingPeriod.getReportingStatus());
						List<ReportingPeriod> reportingPeriods = reportingPeriodService.findByReportingStatus(reportingPeriod.getReportingStatus());
						if(reportingPeriod2 == null){
							if(!reportingPeriods.isEmpty() && reportingPeriods.size() > 0 && reportingPeriods != null){
								ReportingPeriod reportingPeriod3 = reportingPeriodService.getById(reportingPeriod.getId());
								ModelAndView view = new ModelAndView("reportingperiod/reportingPeriodadd");
								List<ReportingPeriod> reportingPeriods1 = new ArrayList<ReportingPeriod>();
								reportingPeriods1 = reportingPeriodService.getReportingPeriod();
								view.addObject("reportingYear", reportingPeriods1);
								view.addObject("reportingPeriod", reportingPeriod);
								view.addObject("openStatus", true);
								List<Quarter> quarters = quarterService.listAllQuarters();
								view.addObject("quarterList", quarters);
								view.addObject("statusErrorMessage", "Already another reporting period is Open");
								return view;
							}
						}
					}
				}
			}
			
			reportingPeriod.setStartdate(date);
			reportingPeriod.setEnddate(date2);
			if (reportingPeriod.getId() == null || reportingPeriod.getId() == 0) {
				reportingPeriod.setStatus(Status.ACTIVE.getName());
				reportingPeriodService.save(reportingPeriod);
				
				action="Reporting Period "+reportingPeriod.getName()+" of Year "+reportingPeriod.getYear()+" is Saved";
				module="Reporting Period Module";
				constantUtil.saveAudition(action, module, request);
				
				modelAndView.setViewName("redirect:reportingList");
			} else {
				reportingPeriod.setStatus(Status.ACTIVE.getName());
				reportingPeriodService.update(reportingPeriod);
				
				action="Reporting Period "+reportingPeriod.getName()+" of Year "+reportingPeriod.getYear()+" is Updated";
				module="Reporting Period Module";
				constantUtil.saveAudition(action, module, request);
				
				modelAndView.setViewName("redirect:reportingList");
			}

			return modelAndView;
		}else{
			return new ModelAndView("login");
		}
		
	}
	
	/**
	 * purpose : To save the reporting period when chosse close
	 */
	
	@RequestMapping(value = "saveReportingPeriod", method = RequestMethod.GET)
	public @ResponseBody ReportingPeriod saveReportingPeriod(@RequestParam Integer reportId) {
		ReportingPeriod reportingPeriod = new ReportingPeriod();
		if (reportId != 0) {
			reportingPeriod = reportingPeriodService.getById(reportId);
			reportingPeriod.setReportingStatus("Closed");
			reportingPeriodService.save(reportingPeriod);
		}
		return reportingPeriod;
	}

	/**
	 * Delete the reporting period by reporting period Id
	 * 
	 * @param reportingperiodId
	 * @return
	 */

	@RequestMapping(value = "deleteReportingPeriod", method = RequestMethod.GET)
	public ModelAndView deleteReportingPeriod(@RequestParam int reportingperiodId) {

		LOGGER.info("Deleting ReportingPeriod info");
		// stateService.deletestate(id);
		reportingPeriodService.deleteReportingPeriod(reportingperiodId);
		return new ModelAndView("redirect:reportingList");
	}

	public void getAccessRights(ModelMap modelMap, String username) {

		/*User user = userService.findByUsername(username);
		List<AccessRights> accessrightslist = new ArrayList<AccessRights>();
		// List<AccessRights> accessrightslist =
		// accessRightsService.listAccessRightsByRole(user.getUserRole().getId().intValue());
		List<String> features = new ArrayList<>();
		boolean COU_ADD = false;
		boolean COU_VIW = false;
		boolean COU_EDT = false;
		boolean COU_DEL = false;

		if (accessrightslist != null) {
			for (AccessRights accessRights : accessrightslist) {
				features.add(accessRights.getFeatures().getFeaturecode());
			}
			if (features != null) {
				for (String feature : features) {
					if (feature.equals(Modules.COU_ADD.toString())) {
						COU_ADD = true;
					}
					if (feature.equals(Modules.COU_VIW.toString())) {
						COU_VIW = true;
					}
					if (feature.equals(Modules.COU_EDT.toString())) {
						COU_EDT = true;
						;
					}
					if (feature.equals(Modules.COU_DEL.toString())) {
						COU_DEL = true;
					}
				}
			}
		}
		modelMap.addAttribute("COU_ADD", COU_ADD);
		modelMap.addAttribute("COU_VIW", COU_VIW);
		modelMap.addAttribute("COU_EDT", COU_EDT);
		modelMap.addAttribute("COU_DEL", COU_DEL);*/
	}
	
	
	//Active reportingPeriod
			@RequestMapping(value = "deActiveReporting", method = RequestMethod.GET)
			public ModelAndView activeReporting(@RequestParam Integer id, Model model) throws Exception{
				if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE) || principalUtil.getCurrentUserRole().equals(ConstantUtil.PROJECTADMIN_ROLE) || principalUtil.getCurrentUserRole().equals(ConstantUtil.PROJECT_PLANNER)){
					if(id != null){
						ReportingPeriod reportingPeriod = reportingPeriodService.getById(id);
						if(reportingPeriod != null){
							//reportingPeriod.setReportingStatus(false);
							reportingPeriod.setStatus(Status.INACTIVE.getName());
							reportingPeriodService.save(reportingPeriod);
							
							action="Reporting Period "+reportingPeriod.getName()+" of Year "+reportingPeriod.getYear()+" is deActivated";
							module="Reporting Period Module";
							constantUtil.saveAudition(action, module, request);
							
							return new ModelAndView("redirect:reportingList");
						}
					}
					return null;
				}else{
					return new ModelAndView("login");
				}
			}
			
			//Deactive reportingPeriod
			@RequestMapping(value = "activeReporting", method = RequestMethod.GET)
			public ModelAndView deActiveReportingList(@RequestParam Integer id, Model model) throws Exception{
				if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE) || principalUtil.getCurrentUserRole().equals(ConstantUtil.PROJECTADMIN_ROLE) || principalUtil.getCurrentUserRole().equals(ConstantUtil.PROJECT_PLANNER)){
					if(id != null){
						ReportingPeriod reportingPeriod = reportingPeriodService.getById(id);
						if(reportingPeriod != null){
							//reportingPeriod.setReportingStatus(true);
							reportingPeriod.setStatus(Status.ACTIVE.getName());
							reportingPeriodService.save(reportingPeriod);

							action="Reporting Period "+reportingPeriod.getName()+" of Year "+reportingPeriod.getYear()+" is Activated";
							module="Reporting Period Module";
							constantUtil.saveAudition(action, module, request);
							
							return new ModelAndView("redirect:reportingList");
						}
					}
					return null;
				}else{
					return new ModelAndView("login");
				}
				
			}
	
			@RequestMapping(value = "getReportPeriodInfo", method = RequestMethod.GET)
			public @ResponseBody ReportingPeriod getReportPeriodInfo(@RequestParam Integer reportId) {
				ReportingPeriod reportingPeriod = new ReportingPeriod();
				if (reportId != 0) {
					reportingPeriod = reportingPeriodService.getById(reportId);
				}
				return reportingPeriod;
			}
			
			//colse reportingPeriod
			/*@RequestMapping(value = "closeReporting", method = RequestMethod.GET)
			public ModelAndView closeReporting(@RequestParam Integer id, Model model) throws Exception{
				if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE) || principalUtil.getCurrentUserRole().equals(ConstantUtil.PROJECTADMIN_ROLE) || principalUtil.getCurrentUserRole().equals(ConstantUtil.PROJECT_PLANNER)){
					if(id != null){
						ReportingPeriod reportingPeriod = reportingPeriodService.getById(id);
						if(reportingPeriod != null){
							reportingPeriod.setReportingStatus("Closed");
							reportingPeriod.setStatus(Status.ACTIVE.getName());
							reportingPeriodService.save(reportingPeriod);
							return new ModelAndView("redirect:reportingList");
						}
					}
					return null;
				}else{
					return new ModelAndView("login");
				}
				
			}*/
			
			@RequestMapping(value = "closeReporting", method = RequestMethod.GET)
			public @ResponseBody ReportingPeriod closeReporting(@RequestParam Integer reportId){
				if(reportId != null){
					ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportId);
					if(reportingPeriod != null){
						reportingPeriod.setReportingStatus("Closed");
						reportingPeriod.setStatus(Status.ACTIVE.getName());
						reportingPeriodService.save(reportingPeriod);
						return reportingPeriod;
					}
				}
				return null;
			}
		
	@RequestMapping(value = "getAllReportingYears", method = RequestMethod.GET)
	public @ResponseBody List<String> getAllReportingYears(){
		List<String> years = new ArrayList<String>();
		years = reportingPeriodService.findAllYears();
		return years;
	}
}
