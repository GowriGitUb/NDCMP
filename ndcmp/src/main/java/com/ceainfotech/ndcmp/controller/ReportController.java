package com.ceainfotech.ndcmp.controller;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ceainfotech.ndcmp.model.Agency;
import com.ceainfotech.ndcmp.model.Indicator;
import com.ceainfotech.ndcmp.model.KeyActivity;
import com.ceainfotech.ndcmp.model.LoginAudit;
import com.ceainfotech.ndcmp.model.Outcome;
import com.ceainfotech.ndcmp.model.Output;
import com.ceainfotech.ndcmp.model.ReportingPeriod;
import com.ceainfotech.ndcmp.model.StatusTracking;
import com.ceainfotech.ndcmp.model.StrategicPillar;
import com.ceainfotech.ndcmp.model.SubActivity;
import com.ceainfotech.ndcmp.model.SubmitForReview;
import com.ceainfotech.ndcmp.model.Target;
import com.ceainfotech.ndcmp.model.Theme;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.model.UserRole;
import com.ceainfotech.ndcmp.model.dto.ReviewerDTO;
import com.ceainfotech.ndcmp.repository.CustomReportsRepository;
import com.ceainfotech.ndcmp.repository.StatusTrackingRepository;
import com.ceainfotech.ndcmp.service.AgencyService;
import com.ceainfotech.ndcmp.service.IndicatorService;
import com.ceainfotech.ndcmp.service.KeyActivityService;
import com.ceainfotech.ndcmp.service.LoginAuditService;
import com.ceainfotech.ndcmp.service.OutcomeService;
import com.ceainfotech.ndcmp.service.OutputServices;
import com.ceainfotech.ndcmp.service.ReportingPeriodService;
import com.ceainfotech.ndcmp.service.StrategicPillarService;
import com.ceainfotech.ndcmp.service.SubActivityService;
import com.ceainfotech.ndcmp.service.SubmitForReviewService;
import com.ceainfotech.ndcmp.service.TargetService;
import com.ceainfotech.ndcmp.service.ThemeService;
import com.ceainfotech.ndcmp.service.UserService;
import com.ceainfotech.ndcmp.util.ConstantUtil;
import com.ceainfotech.ndcmp.util.PrincipalUtil;

/**
 * @author Gowri
 */
@Controller
@RequestMapping(value = "/api/**")
public class ReportController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);
	
	@Autowired
	StrategicPillarService strategicPillarService;
	
	@Autowired
	ReportingPeriodService reportingPeriodService;
	
	@Autowired
	AgencyService agencyService;
	
	@Autowired
	private OutputServices outputServices;
	
	@Autowired
	private KeyActivityService keyActivityService;
	
	@Autowired
	private SubActivityService subActivityService;
	
	@Autowired
	private StatusTrackingRepository statusTrackingRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ThemeService themeService;
	
	@Autowired
	private OutcomeService outcomeService;
	
	@Autowired
	private IndicatorService indicatorService;
	
	@Autowired
	private TargetService targetService;
	
	@Autowired
	SubmitForReviewService submitForReviewService;
	
	@Autowired
	LoginAuditService loginAuditService;
	
	@Autowired
	CustomReportsRepository customReportsRepository;
	
	@Autowired
	PrincipalUtil principalUtil;
	
	@RequestMapping(value = "activityStatus", method = RequestMethod.GET)
	public ModelAndView listAllActivityReport(ModelMap model, Authentication authentication, HttpServletRequest request) {
		LOGGER.info("activityStatus");
		List<ReportingPeriod> reportingPeriods = reportingPeriodService.getReportingPeriodListByStatusOpen();
		model.addAttribute("reportingPeriods", reportingPeriods);
		List<StrategicPillar> strategicPillars = strategicPillarService.getStrategicPillars();
		model.addAttribute("strategicPillarList",strategicPillars);
		return new ModelAndView("report/activityStatusReport");
	}
	
	@RequestMapping(value = "planVsStatus", method = RequestMethod.GET)
	public ModelAndView planVsStatus(ModelMap model, Authentication authentication, HttpServletRequest request) {
		LOGGER.info("planVsStatus");
		List<ReportingPeriod> reportingPeriods = reportingPeriodService.getReportingPeriodListByStatusOpen();
		model.addAttribute("reportingPeriods", reportingPeriods);
		return new ModelAndView("report/planVsActualReport");
	}
	
	@RequestMapping(value = "statusUpdaterReport", method = RequestMethod.GET)
	public ModelAndView statusUpdaterReport(ModelMap model, Authentication authentication, HttpServletRequest request) {
		LOGGER.info("statusUpdaterReport");
		List<ReportingPeriod> reportingPeriods = reportingPeriodService.getAllReportingPeriodList();
		TreeSet<String> list = new TreeSet<String>();
		for (ReportingPeriod reportingPeriod : reportingPeriods) {
			list.add(reportingPeriod.getYear());
		}
		List<String>yourHashSet = new ArrayList<String>(list);
		Collections.sort(yourHashSet);
		model.addAttribute("reportingPeriods", yourHashSet);
		Agency agency = agencyService.findByLoginUser(PrincipalUtil.getPrincipal());
		model.addAttribute("partnerAgency", agency);
		return new ModelAndView("report/statusUpdaterActivityReport");
	}
	
	//to load partner agnecy
	@RequestMapping(value = "getActualReportByStatusUpdater", method = RequestMethod.GET)
	public @ResponseBody Agency getActualReportByStatusUpdater(ModelMap model,@RequestParam Integer outputId) {
		Agency agency = new Agency();
		User user = userService.findByUsername(PrincipalUtil.getPrincipal());
		if(user != null){
			if(user.getAgencyId() != null){
				agency = agencyService.findByAgencyId(user.getAgencyId());
			}
		}
		return agency;
	}
	
	/**
	 * Purpose : To download the status updater report
	 * @param request
	 * @param httpServletResponse
	 * @param yearId
	 * @param spId
	 * @param themeId
	 * @param outcomeId
	 * @param outputId
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "statusUpdaterActualReportDownload" , method = RequestMethod.GET)
	public @ResponseBody String statusUpdaterActualReportDownload(HttpServletRequest request, 
			HttpServletResponse httpServletResponse , @RequestParam("year") String year,@RequestParam("yearId") String yearId,
			@RequestParam("spId") String spId , @RequestParam("themeId") String themeId,
			@RequestParam("outcomeId") String outcomeId , @RequestParam("outputId") String outputId , 
			HttpServletResponse response) throws IOException{
		User user = userService.findByUsername(PrincipalUtil.getPrincipal());
		Agency currentAagency = agencyService.findByLoginUser(PrincipalUtil.getPrincipal());
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
		HSSFSheet hssfSheet = hssfWorkbook.createSheet("Actual Status Report Details");
		
		String reportingPeriodlength = yearId;
		List<ReportingPeriod> listReportingPeriods = new ArrayList<ReportingPeriod>();
		List<StrategicPillar> listStrategicPillers = new ArrayList<StrategicPillar>();
		List<Theme> listThemes = new ArrayList<Theme>();
		List<Outcome> listOutcomes = new ArrayList<Outcome>();
		List<Output> listOutputs = new ArrayList<Output>();
		Set<Integer> partneringAgencySet = new HashSet<Integer>();
		
		StringBuilder partnerReportSPQuery = new StringBuilder();
		StringBuilder partnerReportThemesQuery = new StringBuilder();
		StringBuilder partnerReportOutcomesQuery = new StringBuilder();
		StringBuilder partnerReportOutputsQuery = new StringBuilder();
		StringBuilder partnerReportKeyActivityQuery = new StringBuilder();
		StringBuilder partnerReportSubActivityQuery = new StringBuilder();

		String reportingPeriodQueryConditonString = "";
		String strategicPillerQueryConditonString = "";
		String themeQueryConditionString = "";
		String outcomeQueryCondtionString = "";
		String outputQueryConditionString = "";
		
		if(yearId != null && !yearId.isEmpty()){
			if(yearId.startsWith("0")){
				if(!year.equals("0")){
					List<String> yearsStringArray = Arrays.asList(year);
					listReportingPeriods = reportingPeriodService.findAllReportingPeriodByYears(yearsStringArray);
				}else{
					listReportingPeriods = reportingPeriodService.getAllStatusOpenAndClosed();
				}
			}else{
				String[] reportingPeriodStringArray = yearId.split(",");
				for(int i = 0 ; i < reportingPeriodStringArray.length ; i++){
					ReportingPeriod reportingPeriod = reportingPeriodService.getById(Integer.parseInt(reportingPeriodStringArray[i]));
					listReportingPeriods.add(reportingPeriod);
					reportingPeriodQueryConditonString = " AND p.reportingPeriod.id IN (" + yearId + ") AND p.reportingPeriod.status = 'ACTIVE'";
				}
			}
		}
		
		if(spId != null && !spId.isEmpty()){
			partnerReportSPQuery.append("SELECT DISTINCT p.subActivity.keyActivity.output.outcome.theme.strategicPillar "
					+ "FROM Planning p  LEFT JOIN p.subActivity.partnerAgency AS pa");
			partnerReportSPQuery.append(" WHERE p.subActivity.status = 'ACTIVE'" + reportingPeriodQueryConditonString);
			if(!spId.startsWith("0")){
				strategicPillerQueryConditonString = " AND p.subActivity.keyActivity.output.outcome.theme.strategicPillar.id IN ( " + spId + ")";
				partnerReportSPQuery.append(strategicPillerQueryConditonString);
			}
			partnerReportSPQuery.append(" AND  pa.id = " + currentAagency.getId());
			partnerReportSPQuery.append(" ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber");
			listStrategicPillers = customReportsRepository.getStrategicPillersByReportParameters(partnerReportSPQuery.toString());
		}
		
		if(themeId != null && !themeId.isEmpty()){
			partnerReportThemesQuery.append("SELECT DISTINCT p.subActivity.keyActivity.output.outcome.theme "
					+ "FROM Planning p  LEFT JOIN p.subActivity.partnerAgency AS pa");
			partnerReportThemesQuery.append(" WHERE p.subActivity.status = 'ACTIVE'" + reportingPeriodQueryConditonString + strategicPillerQueryConditonString);
			if(!themeId.startsWith("0")){
				themeQueryConditionString = " AND p.subActivity.keyActivity.output.outcome.theme.id IN ( " + themeId + ")";
				partnerReportThemesQuery.append(themeQueryConditionString);
			}
			partnerReportThemesQuery.append(" AND  pa.id = " + currentAagency.getId());
			partnerReportThemesQuery.append(" ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber");
			listThemes = customReportsRepository.getThemesByReportParameters(partnerReportThemesQuery.toString());
		}
		
		if(outcomeId != null && !outcomeId.isEmpty()){
			partnerReportOutcomesQuery.append("SELECT DISTINCT p.subActivity.keyActivity.output.outcome "
					+ "FROM Planning p  LEFT JOIN p.subActivity.partnerAgency AS pa");
			partnerReportOutcomesQuery.append(" WHERE p.subActivity.status = 'ACTIVE'" + reportingPeriodQueryConditonString 
					+ strategicPillerQueryConditonString + themeQueryConditionString);
			if(!outcomeId.startsWith("0")){
				outcomeQueryCondtionString = " AND p.subActivity.keyActivity.output.outcome.id IN ( " + outcomeId + ")";
				partnerReportOutcomesQuery.append(outcomeQueryCondtionString);
			}
			partnerReportOutcomesQuery.append(" AND  pa.id = " + currentAagency.getId());
			partnerReportOutcomesQuery.append(" ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber");
			listOutcomes = customReportsRepository.getOutcomesByReportParameters(partnerReportOutcomesQuery.toString());
		}
		
		if(outputId != null && !outputId.isEmpty()){
			partnerReportOutputsQuery.append("SELECT DISTINCT p.subActivity.keyActivity.output FROM Planning p"
					+ " LEFT JOIN p.subActivity.partnerAgency AS pa");
			partnerReportOutputsQuery.append(" WHERE p.subActivity.status = 'ACTIVE'" + reportingPeriodQueryConditonString 
					+ strategicPillerQueryConditonString + themeQueryConditionString + outcomeQueryCondtionString);
			if(!outputId.startsWith("0")){
				outputQueryConditionString = " AND p.subActivity.keyActivity.output.id IN ( " + outputId + ")";
				partnerReportOutputsQuery.append(outputQueryConditionString);
			}
			partnerReportOutputsQuery.append(" AND  pa.id = " + currentAagency.getId());
			partnerReportOutputsQuery.append(" ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber");
			listOutputs = customReportsRepository.getOutputsByReportParameters(partnerReportOutputsQuery.toString());
		}
		
		partnerReportKeyActivityQuery.append("SELECT DISTINCT p.subActivity.keyActivity FROM Planning p  "
				+ "LEFT JOIN p.subActivity.partnerAgency AS pa");
		partnerReportKeyActivityQuery.append(" WHERE p.subActivity.status = 'ACTIVE'");
		partnerReportKeyActivityQuery.append(reportingPeriodQueryConditonString + strategicPillerQueryConditonString + themeQueryConditionString + outcomeQueryCondtionString);
		partnerReportKeyActivityQuery.append(" AND  pa.id = " + currentAagency.getId());
		partnerReportKeyActivityQuery.append(" ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber");
		List<KeyActivity> listKeyActivities = customReportsRepository.getKeyActivitiesByReportParameters(partnerReportKeyActivityQuery.toString());
		
		
		partnerReportSubActivityQuery.append("SELECT DISTINCT p.subActivity FROM Planning p  LEFT JOIN p.subActivity.partnerAgency AS pa");
		partnerReportSubActivityQuery.append(" WHERE p.subActivity.status = 'ACTIVE'");
		partnerReportSubActivityQuery.append(reportingPeriodQueryConditonString + strategicPillerQueryConditonString + themeQueryConditionString + outcomeQueryCondtionString);
		partnerReportSubActivityQuery.append(" AND  pa.id = " + currentAagency.getId());
		partnerReportSubActivityQuery.append(" ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber");
		List<SubActivity> listSubActivities = customReportsRepository.getSubActivitiesByReportParameters(partnerReportSubActivityQuery.toString());
		partneringAgencySet.add(currentAagency.getId());
		
		hssfWorkbook = statusUpdaterActualReportDownloadCommonFunction(currentAagency, user, listStrategicPillers, listReportingPeriods, listThemes, listOutcomes, listOutputs, listKeyActivities, listSubActivities,reportingPeriodlength);
	
		hssfSheet.autoSizeColumn(0);
		/*hssfSheet.autoSizeColumn(1);
		hssfSheet.autoSizeColumn(2);
		hssfSheet.autoSizeColumn(3);*/
		hssfSheet.autoSizeColumn(4);
		hssfSheet.autoSizeColumn(5);
		hssfSheet.autoSizeColumn(6);
		hssfSheet.autoSizeColumn(7);
		hssfSheet.autoSizeColumn(8);
		hssfSheet.autoSizeColumn(9);
		hssfSheet.autoSizeColumn(10);
		
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
	
	
	

	/**
	 * Common Function For Updater
	 * @author Jeeva
	 * @param currentAagency
	 * @param user
	 * @param strategicPillars
	 * @param reportingPeriodValues
	 * @param themes
	 * @param outcomes
	 * @param outputs
	 * @param keyActivities
	 * @param subActivities
	 * @return
	 */
	public HSSFWorkbook statusUpdaterActualReportDownloadCommonFunction(Agency currentAagency,
			User user,List<StrategicPillar> strategicPillars,
			List<ReportingPeriod> reportingPeriodlist,List<Theme> themes,
			List<Outcome> outcomes,List<Output> outputs,List<KeyActivity> keyActivities,
			List<SubActivity> subActivities,String reportingPeriodlength){
		
		
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		HSSFSheet hssfSheet = hssfWorkbook.createSheet("Actual Status Report Details");
		HSSFRow headerRow = hssfSheet.createRow(0);

		Font boldFont = hssfWorkbook.createFont();
		boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		boldFont.setColor(IndexedColors.BLACK.getIndex());
		
		Font outputFont = hssfWorkbook.createFont();
		outputFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		outputFont.setColor(IndexedColors.RED.getIndex());
		
		Font outcomeFont = hssfWorkbook.createFont();
		outcomeFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		outcomeFont.setColor(IndexedColors.BLUE.getIndex());
		
		Font mainFont = hssfWorkbook.createFont();
		mainFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		mainFont.setFontHeightInPoints((short) 20);
		mainFont.setColor(IndexedColors.BLACK.getIndex());
		
		CellStyle mainHeaderStyle = hssfWorkbook.createCellStyle();
		mainHeaderStyle.setBorderBottom(CellStyle.BORDER_THIN);
		mainHeaderStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		mainHeaderStyle.setBorderLeft(CellStyle.BORDER_THIN);
		mainHeaderStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		mainHeaderStyle.setBorderRight(CellStyle.BORDER_THIN);
		mainHeaderStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		mainHeaderStyle.setBorderTop(CellStyle.BORDER_THIN);
		mainHeaderStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		mainHeaderStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		mainHeaderStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		mainHeaderStyle.setAlignment(CellStyle.ALIGN_CENTER);
		mainHeaderStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		mainHeaderStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		mainHeaderStyle.setFont(mainFont);
		mainHeaderStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
		mainHeaderStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
		mainHeaderStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
		mainHeaderStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		
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
		tableHeaderStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
		tableHeaderStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
		tableHeaderStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
		tableHeaderStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		
		CellStyle tableHeaderStyleLeft = hssfWorkbook.createCellStyle();
		tableHeaderStyleLeft.setBorderBottom(CellStyle.BORDER_THIN);
		tableHeaderStyleLeft.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		tableHeaderStyleLeft.setBorderLeft(CellStyle.BORDER_THIN);
		tableHeaderStyleLeft.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		tableHeaderStyleLeft.setBorderRight(CellStyle.BORDER_THIN);
		tableHeaderStyleLeft.setRightBorderColor(IndexedColors.BLACK.getIndex());
		tableHeaderStyleLeft.setBorderTop(CellStyle.BORDER_THIN);
		tableHeaderStyleLeft.setTopBorderColor(IndexedColors.BLACK.getIndex());
		tableHeaderStyleLeft.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		tableHeaderStyleLeft.setFillPattern(CellStyle.SOLID_FOREGROUND);
		tableHeaderStyleLeft.setAlignment(CellStyle.ALIGN_LEFT);
		tableHeaderStyleLeft.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		tableHeaderStyleLeft.setFillPattern(CellStyle.SOLID_FOREGROUND);
		tableHeaderStyleLeft.setFont(boldFont);
		tableHeaderStyleLeft.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
		tableHeaderStyleLeft.setBorderRight(HSSFCellStyle.BORDER_THIN);            
		tableHeaderStyleLeft.setBorderTop(HSSFCellStyle.BORDER_THIN);              
		tableHeaderStyleLeft.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		
		CellStyle tableHeaderStyle1 = hssfWorkbook.createCellStyle();
		Font font = hssfWorkbook.createFont();
		font.setColor(IndexedColors.RED.getIndex());
		tableHeaderStyle1.setFont(font);
		
		//Create First Row
		HSSFCell seqNumber = headerRow.createCell(0);
		seqNumber.setCellValue("National Drug Control Master Plan (NDCMP)");
		seqNumber.setCellStyle(mainHeaderStyle);
		seqNumber.setCellType(Cell.CELL_TYPE_STRING);
		headerRow.setHeight((short)700);

		hssfSheet.addMergedRegion(new CellRangeAddress(0,0,0,13));
		Integer rowValue = 0;
		
		rowValue++;
		//Second Row for set Agency
		headerRow = hssfSheet.createRow(1);
		CellStyle labelStyle = hssfWorkbook.createCellStyle();
		labelStyle.setFont(boldFont);
		HSSFCell nameOfAgency = headerRow.createCell(0);
		nameOfAgency.setCellValue("Report By : "+user.getFirstName()+" "+user.getLastName()+" ("+currentAagency.getName()+")");
		nameOfAgency.setCellStyle(tableHeaderStyleLeft);
		nameOfAgency.setCellType(Cell.CELL_TYPE_STRING);
		hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,0,2));
		

		HSSFCell reportingPeriod  = headerRow.createCell(3);
		StringBuffer reportingPeriods = new StringBuffer();
		if(reportingPeriodlist != null && reportingPeriodlist.size() > 0){
			for (ReportingPeriod period : reportingPeriodlist) {
				if(reportingPeriods.length() == 0){
					reportingPeriods.append("Reporting Period : "+period.getYear()+" - "+period.getName());
				}else{
					reportingPeriods.append(" , "+period.getYear()+" - "+period.getName());
				}
			}
		}
		
		reportingPeriod.setCellValue(reportingPeriods.toString());
		reportingPeriod.setCellStyle(tableHeaderStyle);
		reportingPeriod.setCellType(Cell.CELL_TYPE_STRING);
		
		hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,3,7));
		
		
		SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		Date date = new Date();
		
		HSSFCell reportTime = headerRow.createCell(8);
		reportTime.setCellValue("Report Time : "+sd.format(date));
		reportTime.setCellStyle(tableHeaderStyle);
		reportTime.setCellType(Cell.CELL_TYPE_STRING);
		hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,8,13));
		
		HSSFCellStyle strategicStyle = hssfWorkbook.createCellStyle();
		strategicStyle.setFont(boldFont);
		Font strategicFont = hssfWorkbook.createFont();
		font.setColor(HSSFColor.AQUA.index);
		strategicFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		
		String[] sColor=hex2Rgb("#33cccc").split(",");
		String[] yColor=hex2Rgb("#FFCC99").split(",");
		String[] keyBarColor=hex2Rgb("#CCFFCC").split(",");
		
		int c1=Integer.parseInt(sColor[0]);
		int c2=Integer.parseInt(sColor[1]);
		int c3=Integer.parseInt(sColor[2]);
		
		int yc1=Integer.parseInt(yColor[0]);
		int yc2=Integer.parseInt(yColor[1]);
		int yc3=Integer.parseInt(yColor[2]);
		
		int kc1=Integer.parseInt(keyBarColor[0]);
		int kc2=Integer.parseInt(keyBarColor[1]);
		int kc3=Integer.parseInt(keyBarColor[2]);
		
		HSSFCellStyle style2=hssfWorkbook.createCellStyle();
		HSSFColor skyBlue =  setColor(hssfWorkbook,(byte) c1, (byte) c2,(byte) c3);
		style2.setFillForegroundColor(skyBlue.getIndex());
		style2.setFont(boldFont);
		style2.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);            
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);              
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		
		HSSFCellStyle keyBarstyle2=hssfWorkbook.createCellStyle();
		HSSFColor green =  setColor(hssfWorkbook,(byte) kc1, (byte) kc2,(byte) kc3);
		keyBarstyle2.setFillForegroundColor(green.getIndex());
		keyBarstyle2.setFont(boldFont);
		keyBarstyle2.setFillPattern(CellStyle.SOLID_FOREGROUND);
		keyBarstyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
		keyBarstyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);            
		keyBarstyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);              
		keyBarstyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		
		HSSFCellStyle borderStyle = hssfWorkbook.createCellStyle();
		borderStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
		borderStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
		borderStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
		borderStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		
		rowValue++;
		for (StrategicPillar strategicPillar : strategicPillars) {
			headerRow = hssfSheet.createRow(rowValue);
			HSSFCell sPillarName = headerRow.createCell(0);
			sPillarName.setCellValue("Strategic Pillar  "+strategicPillar.getId()+" : "+strategicPillar.getName());
			sPillarName.setCellStyle(style2);
			hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,0,13));
			rowValue++;
			List<Theme> themesByStrategicPillar = themeService.getThemeByStrategicPillar(strategicPillar);
			for (Theme theme : themesByStrategicPillar) {
				for (Theme themeByAgencyId : themes) {
					if(theme.getId() == themeByAgencyId.getId()){
						
						HSSFCellStyle yellowStyle=hssfWorkbook.createCellStyle();
						HSSFColor sandal =  setColor(hssfWorkbook,(byte) yc1, (byte) yc2,(byte) yc3);
						yellowStyle.setFillForegroundColor(sandal.getIndex());
						yellowStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
						yellowStyle.setFont(boldFont);
						yellowStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
						yellowStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
						yellowStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
						yellowStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
						
						headerRow = hssfSheet.createRow(rowValue);
						HSSFCell themeName = headerRow.createCell(0);
						themeName.setCellValue("Theme : "+theme.getName());
						themeName.setCellStyle(yellowStyle);
						themeName.setCellType(Cell.CELL_TYPE_STRING);
						
						hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,0,13));
						
						rowValue++;
						List<Outcome> outcomes2 = outcomeService.getByTheme(themeByAgencyId);
						for (Outcome outcome : outcomes2) {
							for (Outcome outcomeByAgencyId : outcomes) {
								if(outcomeByAgencyId.getId() == outcome.getId()){
									headerRow = hssfSheet.createRow(rowValue);
									
									HSSFCellStyle outcomeStyle=hssfWorkbook.createCellStyle();
									HSSFColor outcomeSandal = setColor(hssfWorkbook,(byte) yc1, (byte) yc2,(byte) yc3);
									outcomeStyle.setFillForegroundColor(outcomeSandal.getIndex());
									outcomeStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
									outcomeStyle.setFont(outcomeFont);
									outcomeStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
									outcomeStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
									outcomeStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
									outcomeStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
									
									HSSFCell outcomeName = headerRow.createCell(0);
									outcomeName.setCellValue("Outcome "+outcome.getSequenceNumber() + " : "+ outcome.getName());
									outcomeName.setCellStyle(outcomeStyle);
									outcomeName.setCellType(Cell.CELL_TYPE_STRING);
									
									hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,0,13));
									
									rowValue++;
									List<Output> outputs2 = outputServices.getByOutcome(outcomeByAgencyId);
									for (Output output : outputs2) {
										for (Output outputByAgencyid : outputs) {
											if(outputByAgencyid.getId() == output.getId()){
												
												HSSFCellStyle outputStyle=hssfWorkbook.createCellStyle();
												HSSFColor outputSandal = setColor(hssfWorkbook,(byte) yc1, (byte) yc2,(byte) yc3);
												outputStyle.setFillForegroundColor(outputSandal.getIndex());
												outputStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
												outputStyle.setFont(outputFont);
												outputStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
												outputStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
												outputStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
												outputStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
												
												HSSFCellStyle activityStyle=hssfWorkbook.createCellStyle();
												activityStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
												activityStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
												activityStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
												activityStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
												
												headerRow = hssfSheet.createRow(rowValue);
												HSSFCell outputName = headerRow.createCell(0);
												outputName.setCellValue("Output "+output.getSequenceNumber() + " : "+output.getOutput());
												outputName.setCellStyle(outputStyle);
												outputName.setCellType(Cell.CELL_TYPE_STRING);
												
												hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,0,13));
												
												rowValue++;
												List<Indicator> indicators = indicatorService.findByOutput(outputByAgencyid);
												for (Indicator indicator : indicators) {
													headerRow = hssfSheet.createRow(rowValue);
													HSSFCell indicatorName = headerRow.createCell(0);
													indicatorName.setCellValue("Indicators : "+indicator.getMessage());
													indicatorName.setCellStyle(yellowStyle);
													indicatorName.setCellType(Cell.CELL_TYPE_STRING);
													hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,0,13));
													rowValue++;
												}
												List<Target> targets = targetService.findByOutput(outputByAgencyid);
												for (Target target : targets) {
													headerRow = hssfSheet.createRow(rowValue);
													HSSFCell targetName = headerRow.createCell(0);
													targetName.setCellValue("Targets : "+target.getMessage());
													targetName.setCellStyle(yellowStyle);
													targetName.setCellType(Cell.CELL_TYPE_STRING);
													hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,0,13));
													rowValue++;
												}
												headerRow = hssfSheet.createRow(rowValue);
												HSSFCell seqenceNumber = headerRow.createCell(0);
												seqenceNumber.setCellValue("S.No");
												seqenceNumber.setCellStyle(labelStyle);
												seqenceNumber.setCellStyle(keyBarstyle2);
												seqenceNumber.setCellType(Cell.CELL_TYPE_STRING); 
												
												HSSFCell keyActivitys = headerRow.createCell(1);
												keyActivitys.setCellValue("Key Activities");
												keyActivitys.setCellStyle(labelStyle);
												keyActivitys.setCellStyle(keyBarstyle2);
												keyActivitys.setCellType(Cell.CELL_TYPE_STRING);

												HSSFCell subActivitys  = headerRow.createCell(2);
												subActivitys.setCellValue("Sub Activities");
												subActivitys.setCellStyle(labelStyle);
												subActivitys.setCellStyle(keyBarstyle2);
												subActivitys.setCellType(Cell.CELL_TYPE_STRING);
												
												HSSFCell status = headerRow.createCell(3);
												status.setCellValue("Status");
												status.setCellStyle(labelStyle);
												status.setCellStyle(keyBarstyle2);
												status.setCellType(Cell.CELL_TYPE_STRING);
												
												HSSFCell percentage = headerRow.createCell(4);
												percentage.setCellValue("Completed Percentage");
												percentage.setCellStyle(labelStyle);
												percentage.setCellStyle(keyBarstyle2);
												percentage.setCellType(Cell.CELL_TYPE_STRING);
												
												HSSFCell keyProgress = headerRow.createCell(5);
												keyProgress.setCellValue("Key Progress");
												keyProgress.setCellStyle(labelStyle);
												keyProgress.setCellStyle(keyBarstyle2);
												keyProgress.setCellType(Cell.CELL_TYPE_STRING);
												
												HSSFCell reasongap = headerRow.createCell(6);
												reasongap.setCellValue("Reasons for gap if any");
												reasongap.setCellStyle(labelStyle);
												reasongap.setCellStyle(keyBarstyle2);
												reasongap.setCellType(Cell.CELL_TYPE_STRING);
												
												HSSFCell reactify = headerRow.createCell(7);
												reactify.setCellValue("Plan of Action to rectify the gap");
												reactify.setCellStyle(labelStyle);
												reactify.setCellStyle(keyBarstyle2);
												reactify.setCellType(Cell.CELL_TYPE_STRING);
												
												HSSFCell feedBackHeading = headerRow.createCell(8);
												feedBackHeading.setCellValue("Feedback/Comments");
												feedBackHeading.setCellStyle(labelStyle);
												feedBackHeading.setCellStyle(keyBarstyle2);
												feedBackHeading.setCellType(Cell.CELL_TYPE_STRING);
												
												HSSFCell lastUpdatedByHeading = headerRow.createCell(9);
												lastUpdatedByHeading.setCellValue("Last Updated By");
												lastUpdatedByHeading.setCellStyle(labelStyle);
												lastUpdatedByHeading.setCellStyle(keyBarstyle2);
												lastUpdatedByHeading.setCellType(Cell.CELL_TYPE_STRING);
												
												HSSFCell lastUpdatedTimeHeading = headerRow.createCell(10);
												lastUpdatedTimeHeading.setCellValue("Last Updated Time");
												lastUpdatedTimeHeading.setCellStyle(labelStyle);
												lastUpdatedTimeHeading.setCellStyle(keyBarstyle2);
												lastUpdatedTimeHeading.setCellType(Cell.CELL_TYPE_STRING);
												
												HSSFCell reviewedByHeading = headerRow.createCell(11);
												reviewedByHeading.setCellValue("Reviewed By");
												reviewedByHeading.setCellStyle(labelStyle);
												reviewedByHeading.setCellStyle(keyBarstyle2);
												reviewedByHeading.setCellType(Cell.CELL_TYPE_STRING);
												
												HSSFCell reviewedTimeHeading = headerRow.createCell(12);
												reviewedTimeHeading.setCellValue("Reviewed Time");
												reviewedTimeHeading.setCellStyle(labelStyle);
												reviewedTimeHeading.setCellStyle(keyBarstyle2);
												reviewedTimeHeading.setCellType(Cell.CELL_TYPE_STRING);
												
												HSSFCell statuslevel = headerRow.createCell(13);
												statuslevel.setCellValue("Activity Status");
												statuslevel.setCellStyle(labelStyle);
												statuslevel.setCellStyle(keyBarstyle2);
												statuslevel.setCellType(Cell.CELL_TYPE_STRING);
												
												rowValue++;
												List<KeyActivity> activities = keyActivityService.findByOutput(outputByAgencyid);
												for (KeyActivity keyActivity : activities) {
													for (KeyActivity keyActivityByReportingPeriod : keyActivities) {
														if(keyActivityByReportingPeriod.getId() == keyActivity.getId()){
															headerRow = hssfSheet.createRow(rowValue);
															HSSFCell keySeqNumber = headerRow.createCell(0);
															keySeqNumber.setCellValue(keyActivity.getSequenceNumber());
															keySeqNumber.setCellStyle(borderStyle);
															
															HSSFCell keyActivityName = headerRow.createCell(1);
															keyActivityName.setCellValue(keyActivity.getName());
															keyActivityName.setCellStyle(borderStyle);
															CellStyle cellStylekeyName = hssfWorkbook.createCellStyle();
															cellStylekeyName.setWrapText(true);//set wraper text
															keyActivityName.setCellStyle(cellStylekeyName);
															headerRow.setHeightInPoints((2*hssfSheet.getDefaultRowHeightInPoints()));
															hssfSheet.setColumnWidth(1,11000);
															
															HSSFCell subActivity = headerRow.createCell(2);
															subActivity.setCellValue("");
															subActivity.setCellStyle(borderStyle);
															
															HSSFCell status1 = headerRow.createCell(3);
															status1.setCellValue("");
															status1.setCellStyle(borderStyle);
															
															HSSFCell percentage1 = headerRow.createCell(4);
															percentage1.setCellValue("");
															percentage1.setCellStyle(borderStyle);
															
															HSSFCell keyProgress1 = headerRow.createCell(5);
															keyProgress1.setCellValue("");
															keyProgress1.setCellStyle(borderStyle);
															
															HSSFCell reason = headerRow.createCell(6);
															reason.setCellValue("");
															reason.setCellStyle(borderStyle);
															
															HSSFCell rectify = headerRow.createCell(7);
															rectify.setCellValue("");
															rectify.setCellStyle(borderStyle);
															
															HSSFCell feedBackEmpty = headerRow.createCell(8);
															feedBackEmpty.setCellValue("");
															feedBackEmpty.setCellStyle(borderStyle);
															
															HSSFCell lastUpdatedByEmpty = headerRow.createCell(9);
															lastUpdatedByEmpty.setCellValue("");
															lastUpdatedByEmpty.setCellStyle(borderStyle);

															HSSFCell lastUpdatedTimeEmpty = headerRow.createCell(10);
															lastUpdatedTimeEmpty.setCellValue("");
															lastUpdatedTimeEmpty.setCellStyle(borderStyle);
															
															HSSFCell reviewedByEmpty = headerRow.createCell(11);
															reviewedByEmpty.setCellValue("");
															reviewedByEmpty.setCellStyle(borderStyle);

															HSSFCell reviewedTimeEmpty = headerRow.createCell(12);
															reviewedTimeEmpty.setCellValue("");
															reviewedTimeEmpty.setCellStyle(borderStyle);
															
															HSSFCell activityStatus = headerRow.createCell(13);
															activityStatus.setCellValue("");
															activityStatus.setCellStyle(borderStyle);
															
															rowValue++;
															List<SubActivity> subActivities3 = subActivityService.findByKeyActivity(keyActivityByReportingPeriod);
															if(subActivities3.size() > 0){
																for(SubActivity subActivity1 : subActivities3){
																	for (SubActivity subActivity2 : subActivities) {
																		if(subActivity2.getId() == subActivity1.getId()){
																			Integer currentRowValue = rowValue;
																			headerRow = hssfSheet.createRow(rowValue);
																			HSSFCell subSeySeqNumber = headerRow.createCell(0);
																			subSeySeqNumber.setCellValue(subActivity2.getSequenceNumber());
																			subSeySeqNumber.setCellStyle(borderStyle);
																			
																			HSSFCell keyActivityNameForSubactivity = headerRow.createCell(1);
																			keyActivityNameForSubactivity.setCellValue("");
																			keyActivityNameForSubactivity.setCellStyle(borderStyle);
																			
																			HSSFCell subActivityName = headerRow.createCell(2);
																			subActivityName.setCellValue(subActivity2.getName());
																			CellStyle cellStylesubName = hssfWorkbook.createCellStyle();
																			cellStylesubName.setWrapText(true);//set wraper text
																			cellStylesubName.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
																			cellStylesubName.setBorderRight(HSSFCellStyle.BORDER_THIN);            
																			cellStylesubName.setBorderTop(HSSFCellStyle.BORDER_THIN);              
																			cellStylesubName.setBorderBottom(HSSFCellStyle.BORDER_THIN);
																			subActivityName.setCellStyle(cellStylesubName);
																			headerRow.setHeightInPoints((2*hssfSheet.getDefaultRowHeightInPoints()));
																			hssfSheet.setColumnWidth(2,11000);
																			
																			int temp1 = 0;
																			StatusTracking subActivityStatusTracking = new StatusTracking();
																			for(ReportingPeriod reportingPeriod2 : reportingPeriodlist){
																				StatusTracking lastUpdatedStatusTrackin = statusTrackingRepository.findByUserAndSubActivityAndReportingPeriodAndUserLevel(user, subActivity1, reportingPeriod2, 1);
																				if(lastUpdatedStatusTrackin != null){
																					subActivityStatusTracking = new StatusTracking();
																					subActivityStatusTracking = statusTrackingRepository.findByUserAndSubActivityAndReportingPeriodAndUserLevel(user, subActivity1, reportingPeriod2, 1);
																				}
																			}
																			
																			if(subActivityStatusTracking != null && subActivityStatusTracking.getId() != null){
																				HSSFCellStyle statusColor=hssfWorkbook.createCellStyle();
																				if(subActivityStatusTracking.getActualStatusColor() != null){
																					String[] staColor=hex2Rgb(subActivityStatusTracking.getActualStatusColor()).split(",");
																					int ac1=Integer.parseInt(staColor[0]);
																					int ac2=Integer.parseInt(staColor[1]);
																					int ac3=Integer.parseInt(staColor[2]);
																					
																					HSSFPalette palette = hssfWorkbook.getCustomPalette();
																					HSSFColor myColor = palette.findSimilarColor(ac1, ac2, ac3);
																					short palIndex = myColor.getIndex();
																					statusColor.setFillForegroundColor(palIndex);
																					statusColor.setFillPattern(CellStyle.SOLID_FOREGROUND);
																					statusColor.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
																					statusColor.setBorderRight(HSSFCellStyle.BORDER_THIN);            
																					statusColor.setBorderTop(HSSFCellStyle.BORDER_THIN);              
																					statusColor.setBorderBottom(HSSFCellStyle.BORDER_THIN);
																					
																					
																				}
																				HSSFCell status2 = headerRow.createCell(3);
																				status2.setCellStyle(statusColor);
																				
																				HSSFCell percentage2 = headerRow.createCell(4);
																				percentage2.setCellValue(Integer.parseInt(subActivityStatusTracking.getActualStatusPercentage()));
																				percentage2.setCellStyle(borderStyle);
																				
																				HSSFCell keyProgress2 = headerRow.createCell(5);
																				keyProgress2.setCellValue(subActivityStatusTracking.getKeyProgress());
																				keyProgress2.setCellStyle(borderStyle);
																				
																				HSSFCell reason1 = headerRow.createCell(6);
																				reason1.setCellValue(subActivityStatusTracking.getReasonForGap());
																				reason1.setCellStyle(borderStyle);
																				
																				HSSFCell rectify1 = headerRow.createCell(7);
																				rectify1.setCellValue(subActivityStatusTracking.getRectifyTheGap());
																				rectify1.setCellStyle(borderStyle);
																				
																				HSSFCell feedBackValue = headerRow.createCell(8);
																				feedBackValue.setCellValue(subActivityStatusTracking.getReviewerFeedback());
																				feedBackValue.setCellStyle(borderStyle);
																				
																				HSSFCell lastUpdatedByValue = headerRow.createCell(9);
																				if(subActivityStatusTracking.getUser() != null){
																					lastUpdatedByValue.setCellValue(subActivityStatusTracking.getUser().getFirstName()+" "+subActivityStatusTracking.getUser().getLastName()+" ( "+subActivityStatusTracking.getAgency().getName()+" )");
																				}else{
																					lastUpdatedByValue.setCellValue("");
																				}
																				lastUpdatedByValue.setCellStyle(borderStyle);

																				HSSFCell lastUpdatedTimeValue = headerRow.createCell(10);
																				if(subActivityStatusTracking.getModificationTime() != null){
																					SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
																					try {
																						Date lastUpdatedDate = subActivityStatusTracking.getModificationTime();
																						lastUpdatedTimeValue.setCellValue(dateformat.format(lastUpdatedDate));
																					} catch (Exception e) {
																						e.printStackTrace();
																					}
																					
																				}else{
																					lastUpdatedTimeValue.setCellValue("");
																				}
																				lastUpdatedTimeValue.setCellStyle(borderStyle);
																				
																				HSSFCell reviewedByValue = headerRow.createCell(11);
																				if(subActivityStatusTracking.getReviewedBy() != null){
																					reviewedByValue.setCellValue(subActivityStatusTracking.getReviewedBy().getFirstName()+" "+subActivityStatusTracking.getReviewedBy().getLastName());
																				}else{
																					reviewedByValue.setCellValue("");
																				}
																				reviewedByValue.setCellStyle(borderStyle);

																				HSSFCell reviewedTimeValue = headerRow.createCell(12);
																				if(subActivityStatusTracking.getReviewedOn() != null && subActivityStatusTracking.getReviewedOn() != ""){
																					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
																					SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
																					String reviewOn = subActivityStatusTracking.getReviewedOn();
																					try {
																						Date reviewDate = sdf.parse(reviewOn);
																						reviewedTimeValue.setCellValue(dateformat.format(reviewDate));
																					} catch (ParseException e) {
																						e.printStackTrace();
																					}
																					
																				}else{
																					reviewedTimeValue.setCellValue("");
																				}
																				reviewedTimeValue.setCellStyle(borderStyle);
																				if(subActivityStatusTracking.getUserLevel() == 1){
																					if(!subActivityStatusTracking.isComplete() && subActivityStatusTracking.getReviewStatus() == 0 && !subActivityStatusTracking.isSentForReview()){
																						HSSFCell dataCapture = headerRow.createCell(13);
																						dataCapture.setCellValue("Data Captured");
																						dataCapture.setCellStyle(borderStyle);
																					}else if (subActivityStatusTracking.isComplete() && subActivityStatusTracking.getReviewStatus() == 0 && !subActivityStatusTracking.isSentForReview()) {
																						HSSFCell readyForReview = headerRow.createCell(13);
																						readyForReview.setCellValue("Ready For Review");
																						readyForReview.setCellStyle(borderStyle);
																					}else if (subActivityStatusTracking.isComplete() && subActivityStatusTracking.getReviewStatus() == 0 && subActivityStatusTracking.isSentForReview()) {
																						HSSFCell submittedForReview = headerRow.createCell(13);
																						submittedForReview.setCellValue("Submitted For Review");
																						submittedForReview.setCellStyle(borderStyle);
																					}
																					
																				}
																				
																				
																				
																				temp1 = 1;
																			}
																			if(temp1 == 0){
																				HSSFCell status3 = headerRow.createCell(3);
																				status3.setCellValue("");
																				status3.setCellStyle(borderStyle);
																				
																				HSSFCell percentage3 = headerRow.createCell(4);
																				percentage3.setCellValue("");
																				percentage3.setCellStyle(borderStyle);
																				
																				HSSFCell keyProgress3 = headerRow.createCell(5);
																				keyProgress3.setCellValue("");
																				keyProgress3.setCellStyle(borderStyle);
																				
																				HSSFCell reason3 = headerRow.createCell(6);
																				reason3.setCellValue("");
																				reason3.setCellStyle(borderStyle);
																				
																				HSSFCell rectify3 = headerRow.createCell(7);
																				rectify3.setCellValue("");
																				rectify3.setCellStyle(borderStyle);
																				
																				HSSFCell feedBack1 = headerRow.createCell(8);
																				feedBack1.setCellValue("");
																				feedBack1.setCellStyle(borderStyle);
																				
																				HSSFCell lastUpdatedBy = headerRow.createCell(9);
																				lastUpdatedBy.setCellValue("");
																				lastUpdatedBy.setCellStyle(borderStyle);

																				HSSFCell lastUpdatedTime = headerRow.createCell(10);
																				lastUpdatedTime.setCellValue("");
																				lastUpdatedTime.setCellStyle(borderStyle);
																				
																				HSSFCell reviewedBy = headerRow.createCell(11);
																				reviewedBy.setCellValue("");
																				reviewedBy.setCellStyle(borderStyle);

																				HSSFCell reviewedTime = headerRow.createCell(12);
																				reviewedTime.setCellValue("");
																				reviewedTime.setCellStyle(borderStyle);
																				
																				HSSFCell activityStatus1 = headerRow.createCell(13);
																				activityStatus1.setCellValue("");
																				activityStatus1.setCellStyle(borderStyle);
																			}
																			
																			int temp = 0;
																			for(ReportingPeriod reportingPeriod2 : reportingPeriodlist){
																				StatusTracking statusTracking = statusTrackingRepository.findByUserAndSubActivityAndReportingPeriodAndUserLevel(user, subActivity1, reportingPeriod2, 1);
																				if(statusTracking != null){
																							
																					HSSFCellStyle statusColor=hssfWorkbook.createCellStyle();
																					String[] staColor=hex2Rgb(statusTracking.getActualStatusColor()).split(",");
																					int ac1=Integer.parseInt(staColor[0]);
																					int ac2=Integer.parseInt(staColor[1]);
																					int ac3=Integer.parseInt(staColor[2]);
																					
																					HSSFPalette palette = hssfWorkbook.getCustomPalette();
																					HSSFColor myColor = palette.findSimilarColor(ac1, ac2, ac3);
																					short palIndex = myColor.getIndex();
																					statusColor.setFillForegroundColor(palIndex);
																					statusColor.setFillPattern(CellStyle.SOLID_FOREGROUND);
																					statusColor.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
																					statusColor.setBorderRight(HSSFCellStyle.BORDER_THIN);            
																					statusColor.setBorderTop(HSSFCellStyle.BORDER_THIN);              
																					statusColor.setBorderBottom(HSSFCellStyle.BORDER_THIN);
																					
																					if(currentRowValue == rowValue){
																						rowValue++;
																						headerRow = hssfSheet.createRow(rowValue);
																						
																						HSSFCell emptyKey = headerRow.createCell(0);
																						emptyKey.setCellValue(" ");
																						emptyKey.setCellStyle(activityStyle);
																						
																						HSSFCell emptySub = headerRow.createCell(1);
																						emptySub.setCellValue("");
																						emptySub.setCellStyle(activityStyle);
																						
																						HSSFCell reportingperiod = headerRow.createCell(2);
																						reportingperiod.setCellValue("   "+reportingPeriod2.getYear() +" - "+reportingPeriod2.getName());
																						reportingperiod.setCellStyle(activityStyle);
																						
																						HSSFCell statusReporting = headerRow.createCell(3);
																						statusReporting.setCellStyle(statusColor);
																						
																						HSSFCell comPercentReporting = headerRow.createCell(4);
																						comPercentReporting.setCellValue(Integer.parseInt(statusTracking.getActualStatusPercentage()));
																						comPercentReporting.setCellStyle(activityStyle);
																						
																						HSSFCell keyProgressReporting = headerRow.createCell(5);
																						keyProgressReporting.setCellValue(statusTracking.getKeyProgress());
																						keyProgressReporting.setCellStyle(activityStyle);
																						
																						HSSFCell reasonReporting = headerRow.createCell(6);
																						reasonReporting.setCellValue(statusTracking.getReasonForGap());
																						reasonReporting.setCellStyle(activityStyle);
																						
																						HSSFCell rectifyReporting = headerRow.createCell(7);
																						rectifyReporting.setCellValue(statusTracking.getRectifyTheGap());
																						rectifyReporting.setCellStyle(activityStyle);
																						
																						HSSFCell feedbackReporting = headerRow.createCell(8);
																						feedbackReporting.setCellValue(statusTracking.getReviewerFeedback());
																						feedbackReporting.setCellStyle(activityStyle);
																						
																						HSSFCell lastUpdatedByValueReporting = headerRow.createCell(9);
																						if(statusTracking.getUser() != null){
																							lastUpdatedByValueReporting.setCellValue(statusTracking.getUser().getFirstName()+" "+statusTracking.getUser().getLastName()+" ( "+statusTracking.getAgency().getName()+" )");
																						}else{
																							lastUpdatedByValueReporting.setCellValue("");
																						}
																						lastUpdatedByValueReporting.setCellStyle(activityStyle);

																						HSSFCell lastUpdatedTimeValueReporting = headerRow.createCell(10);
																						if(statusTracking.getModificationTime() != null){
																							SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
																							try {
																								Date lastUpdatedDate = statusTracking.getModificationTime();
																								lastUpdatedTimeValueReporting.setCellValue(dateformat.format(lastUpdatedDate));
																							} catch (Exception e) {
																								e.printStackTrace();
																							}
																							
																						}else{
																							lastUpdatedTimeValueReporting.setCellValue("");
																						}
																						lastUpdatedTimeValueReporting.setCellStyle(activityStyle);
																						
																						HSSFCell reviewedByValueReporting = headerRow.createCell(11);
																						if(statusTracking.getReviewedBy() != null){
																							reviewedByValueReporting.setCellValue(statusTracking.getReviewedBy().getFirstName()+" "+statusTracking.getReviewedBy().getLastName());
																						}else{
																							reviewedByValueReporting.setCellValue("");
																						}
																						reviewedByValueReporting.setCellStyle(activityStyle);

																						HSSFCell reviewedTimeValueReporting = headerRow.createCell(12);
																						if(statusTracking.getReviewedOn() != null && statusTracking.getReviewedOn() != ""){
																							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
																							SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
																							String reviewOn = statusTracking.getReviewedOn();
																							try {
																								Date reviewDate = sdf.parse(reviewOn);
																								reviewedTimeValueReporting.setCellValue(dateformat.format(reviewDate));
																							} catch (ParseException e) {
																								e.printStackTrace();
																							}
																							
																						}else{
																							reviewedTimeValueReporting.setCellValue("");
																						}
																						reviewedTimeValueReporting.setCellStyle(activityStyle);
																						
																						if(statusTracking.getUserLevel() == 1){
																							if(!statusTracking.isComplete() && statusTracking.getReviewStatus() == 0 && !statusTracking.isSentForReview()){
																								HSSFCell dataCapture = headerRow.createCell(13);
																								dataCapture.setCellValue("Data Captured");
																								dataCapture.setCellStyle(borderStyle);
																							}else if (statusTracking.isComplete() && statusTracking.getReviewStatus() == 0 && !statusTracking.isSentForReview()) {
																								HSSFCell readyForReview = headerRow.createCell(13);
																								readyForReview.setCellValue("Ready For Review");
																								readyForReview.setCellStyle(borderStyle);
																							}else if (statusTracking.isComplete() && statusTracking.getReviewStatus() == 0 && statusTracking.isSentForReview()) {
																								HSSFCell submittedForReview = headerRow.createCell(13);
																								submittedForReview.setCellValue("Submitted For Review");
																								submittedForReview.setCellStyle(borderStyle);
																							}
																							
																						}
																						
																						temp = 1;
																						
																					}else{
																						headerRow = hssfSheet.createRow(rowValue);
																						
																						HSSFCell emptyKey1 = headerRow.createCell(0);
																						emptyKey1.setCellValue(" ");
																						emptyKey1.setCellStyle(activityStyle);
																						
																						HSSFCell emptySub1 = headerRow.createCell(1);
																						emptySub1.setCellValue("");
																						emptySub1.setCellStyle(activityStyle);
																						
																						HSSFCell reportingPeriodValue = headerRow.createCell(2);
																						reportingPeriodValue.setCellValue("   "+reportingPeriod2.getYear()+" - "+reportingPeriod2.getName());
																						reportingPeriodValue.setCellStyle(activityStyle);
																						
																						HSSFCell statusReporting2 = headerRow.createCell(3);
																						statusReporting2.setCellStyle(statusColor);
																						
																						HSSFCell comPercentReporting2 = headerRow.createCell(4);
																						comPercentReporting2.setCellValue(Integer.parseInt(statusTracking.getActualStatusPercentage()));
																						comPercentReporting2.setCellStyle(activityStyle);
																						
																						HSSFCell keyProgressReporting2 = headerRow.createCell(5);
																						keyProgressReporting2.setCellValue(statusTracking.getKeyProgress());
																						keyProgressReporting2.setCellStyle(activityStyle);
																						
																						HSSFCell reasonReporting1 = headerRow.createCell(6);
																						reasonReporting1.setCellValue(statusTracking.getReasonForGap());
																						reasonReporting1.setCellStyle(activityStyle);
																						
																						HSSFCell rectifyReporting1 = headerRow.createCell(7);
																						rectifyReporting1.setCellValue(statusTracking.getRectifyTheGap());
																						rectifyReporting1.setCellStyle(activityStyle);
																						
																						HSSFCell feedbackReporting1 = headerRow.createCell(8);
																						feedbackReporting1.setCellValue(statusTracking.getReviewerFeedback());
																						feedbackReporting1.setCellStyle(activityStyle);
																						
																						HSSFCell lastUpdatedByValueReporting1 = headerRow.createCell(9);
																						if(statusTracking.getUser() != null){
																							lastUpdatedByValueReporting1.setCellValue(statusTracking.getUser().getFirstName()+" "+statusTracking.getUser().getLastName()+" ( "+statusTracking.getAgency().getName()+" )");
																						}else{
																							lastUpdatedByValueReporting1.setCellValue("");
																						}
																						lastUpdatedByValueReporting1.setCellStyle(activityStyle);

																						HSSFCell lastUpdatedTimeValueReporting1 = headerRow.createCell(10);
																						if(statusTracking.getModificationTime() != null){
																							SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
																							try {
																								Date lastUpdatedDate = statusTracking.getModificationTime();
																								lastUpdatedTimeValueReporting1.setCellValue(dateformat.format(lastUpdatedDate));
																							} catch (Exception e) {
																								e.printStackTrace();
																							}
																							
																						}else{
																							lastUpdatedTimeValueReporting1.setCellValue("");
																						}
																						lastUpdatedTimeValueReporting1.setCellStyle(activityStyle);
																						
																						HSSFCell reviewedByValueReporting1 = headerRow.createCell(11);
																						if(statusTracking.getReviewedBy() != null){
																							reviewedByValueReporting1.setCellValue(statusTracking.getReviewedBy().getFirstName()+" "+statusTracking.getReviewedBy().getLastName());
																						}else{
																							reviewedByValueReporting1.setCellValue("");
																						}
																						reviewedByValueReporting1.setCellStyle(activityStyle);

																						HSSFCell reviewedTimeValueReporting1 = headerRow.createCell(12);
																						if(statusTracking.getReviewedOn() != null && statusTracking.getReviewedOn() != ""){
																							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
																							SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
																							String reviewOn = statusTracking.getReviewedOn();
																							try {
																								Date reviewDate = sdf.parse(reviewOn);
																								reviewedTimeValueReporting1.setCellValue(dateformat.format(reviewDate));
																							} catch (ParseException e) {
																								e.printStackTrace();
																							}
																							
																						}else{
																							reviewedTimeValueReporting1.setCellValue("");
																						}
																						reviewedTimeValueReporting1.setCellStyle(activityStyle);
																						
																						if(statusTracking.getUserLevel() == 1){
																							if(!statusTracking.isComplete() && statusTracking.getReviewStatus() == 0 && !statusTracking.isSentForReview()){
																								HSSFCell dataCapture = headerRow.createCell(13);
																								dataCapture.setCellValue("Data Captured");
																								dataCapture.setCellStyle(borderStyle);
																							}else if (statusTracking.isComplete() && statusTracking.getReviewStatus() == 0 && !statusTracking.isSentForReview()) {
																								HSSFCell readyForReview = headerRow.createCell(13);
																								readyForReview.setCellValue("Ready For Review");
																								readyForReview.setCellStyle(borderStyle);
																							}else if (statusTracking.isComplete() && statusTracking.getReviewStatus() == 0 && statusTracking.isSentForReview()) {
																								HSSFCell submittedForReview = headerRow.createCell(13);
																								submittedForReview.setCellValue("Submitted For Review");
																								submittedForReview.setCellStyle(borderStyle);
																							}
																							
																						}
																						
																						temp = 1;
																					}
																				rowValue++;		
																				}
																			}
																			if(temp == 0){
																				HSSFCell status3 = headerRow.createCell(3);
																				status3.setCellValue("");
																				status3.setCellStyle(borderStyle);
																				
																				HSSFCell percentage3 = headerRow.createCell(4);
																				percentage3.setCellValue("");
																				percentage3.setCellStyle(borderStyle);
																				
																				HSSFCell keyProgress3 = headerRow.createCell(5);
																				keyProgress3.setCellValue("");
																				keyProgress3.setCellStyle(borderStyle);
																				
																				HSSFCell reason3 = headerRow.createCell(6);
																				reason3.setCellValue("");
																				reason3.setCellStyle(borderStyle);
																				
																				HSSFCell rectify3 = headerRow.createCell(7);
																				rectify3.setCellValue("");
																				rectify3.setCellStyle(borderStyle);
																				
																				HSSFCell feedBack1 = headerRow.createCell(8);
																				feedBack1.setCellValue("");
																				feedBack1.setCellStyle(borderStyle);
																				
																				HSSFCell lastupdatedBy = headerRow.createCell(9);
																				lastupdatedBy.setCellValue("");
																				lastupdatedBy.setCellStyle(borderStyle);

																				HSSFCell lastUpdatedTime = headerRow.createCell(10);
																				lastUpdatedTime.setCellValue("");
																				lastUpdatedTime.setCellStyle(borderStyle);
																				
																				HSSFCell reviewedBy = headerRow.createCell(11);
																				reviewedBy.setCellValue("");
																				reviewedBy.setCellStyle(borderStyle);

																				HSSFCell reviewedTime = headerRow.createCell(12);
																				reviewedTime.setCellValue("");
																				reviewedTime.setCellStyle(borderStyle);
																				
																				HSSFCell activitySatus = headerRow.createCell(12);
																				activitySatus.setCellValue("");
																				activitySatus.setCellStyle(borderStyle);
																				rowValue++;
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
							}
							
						}
					}
				}
				
			}
		}
		
		hssfSheet.autoSizeColumn(0);
		hssfSheet.autoSizeColumn(4);
		hssfSheet.autoSizeColumn(5);
		hssfSheet.autoSizeColumn(6);
		hssfSheet.autoSizeColumn(7);
		hssfSheet.autoSizeColumn(8);
		hssfSheet.autoSizeColumn(9);
		hssfSheet.autoSizeColumn(10);
		hssfSheet.autoSizeColumn(11);
		hssfSheet.autoSizeColumn(12);
		hssfSheet.autoSizeColumn(13);
		
		return hssfWorkbook;
	}
	
	
	
	public static String hex2Rgb(String colorStr) {
	    Color c = new Color(
	        Integer.valueOf(colorStr.substring(1, 3), 16), 
	        Integer.valueOf(colorStr.substring(3, 5), 16), 
	        Integer.valueOf(colorStr.substring(5, 7), 16));
	    return c.getRed()+","+c.getGreen()+","+c.getBlue();
	}
	
	public static HSSFColor setColor(HSSFWorkbook workbook, byte r,byte g, byte b){
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

	
	// Reviewer
	/**
	 * @author prem
	 * Show Reviewer Page
	 * @param model
	 * @param authentication
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "statusReviewerReport", method = RequestMethod.GET)
	public ModelAndView statusReviewerReport(ModelMap model, Authentication authentication, HttpServletRequest request) {
		LOGGER.info("statusUpdaterReport");
		
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.STATUS_REVIEWER_ROLE)){
			List<ReportingPeriod> reportingPeriods = reportingPeriodService.getAllReportingPeriodList();
			TreeSet<String> list = new TreeSet<String>();
			for (ReportingPeriod reportingPeriod : reportingPeriods) {
				list.add(reportingPeriod.getYear());
			}
			List<String>yourHashSet = new ArrayList<String>(list);
			Collections.sort(yourHashSet);
			model.addAttribute("reportingPeriods", yourHashSet);
			model.addAttribute("addReviewer", new ReviewerDTO());
			return new ModelAndView("report/StatusReviewerActivityReport");
		}else{
			return new ModelAndView("login");
		}
	}
	
	/**
	 * Status Reviewer Report Download
	 * @author prem
	 * @param request
	 * @param httpServletResponse
	 * @param yearId
	 * @param spId
	 * @param themeId
	 * @param outcomeId
	 * @param outputId
	 * @param partnerAgencyId
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "statusReviewerActualReportDownload" , method = RequestMethod.GET)
	public @ResponseBody String statusReviewerActualReportDownload(HttpServletRequest request, 
			HttpServletResponse httpServletResponse ,@RequestParam("year") String year, @RequestParam("yearId") String reportingPeriodId,
			@RequestParam("spId") String spId , @RequestParam("themeId") String themeId,
			@RequestParam("outcomeId") String outcomeId , @RequestParam("outputId") String outputId , 
			@RequestParam("partnerAgencyId") String partnerAgencyId, HttpServletResponse response) throws IOException{
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.STATUS_REVIEWER_ROLE)){
			Agency currentAagency = agencyService.findByLoginUser(PrincipalUtil.getPrincipal());
			User user = userService.findByUsername(PrincipalUtil.getPrincipal());
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
			HSSFSheet hssfSheet = hssfWorkbook.createSheet("Actual Status Reviewer Report Details");
			
//			Start Create Reports Queries
			StringBuilder approverReportSPQuery = new StringBuilder();
			StringBuilder approverReportThemesQuery = new StringBuilder();
			StringBuilder approverReportOutcomesQuery = new StringBuilder();
			StringBuilder approverReportOutputsQuery = new StringBuilder();
			StringBuilder approverReportKeyActivityQuery = new StringBuilder();
			StringBuilder approverReportSubActivityQuery = new StringBuilder();

			String reportinPeriodLength = reportingPeriodId;
			String reportingPeriodQueryConditonString = "";
			String strategicPillerQueryConditonString = "";
			String themeQueryConditionString = "";
			String outcomeQueryCondtionString = "";
			String outputQueryConditionString = "";
			String partnerQueryConditionString1 = " LEFT JOIN p.subActivity.partnerAgency AS pa";
			String partnerQueryConditionString2 = " AND  pa.id IN ( " + partnerAgencyId + ")";
			
			List<ReportingPeriod> listReportingPeriods = new ArrayList<ReportingPeriod>();
			List<StrategicPillar> listStrategicPillers = new ArrayList<StrategicPillar>();
			List<Theme> listThemes = new ArrayList<Theme>();
			List<Outcome> listOutcomes = new ArrayList<Outcome>();
			List<Output> listOutputs = new ArrayList<Output>();
			Set<Integer> partneringAgencySet = new HashSet<Integer>();	
			
			if(reportingPeriodId != null && !reportingPeriodId.isEmpty()){
				if(reportingPeriodId.startsWith("0")){
					if(!year.equals("0")){
						List<String> yearsStringArray = Arrays.asList(year);
						listReportingPeriods = reportingPeriodService.findAllReportingPeriodByYears(yearsStringArray);
					}else{
						listReportingPeriods = reportingPeriodService.getAllStatusOpenAndClosed();
					}
				}else{
					String[] reportingPeriodStringArray = reportingPeriodId.split(",");
					for(int i = 0 ; i < reportingPeriodStringArray.length ; i++){
						ReportingPeriod reportingPeriod = reportingPeriodService.getById(Integer.parseInt(reportingPeriodStringArray[i]));
						listReportingPeriods.add(reportingPeriod);
						reportingPeriodQueryConditonString = " AND p.reportingPeriod.id IN (" + reportingPeriodId + ") AND p.reportingPeriod.status = 'ACTIVE'";
					}
				}
			}
			
			
			if(spId != null && !spId.isEmpty()){
				approverReportSPQuery.append("SELECT DISTINCT p.subActivity.keyActivity.output.outcome.theme.strategicPillar FROM Planning p");
				if(partnerAgencyId != null && !partnerAgencyId.isEmpty() && !partnerAgencyId.contains("-1") && !partnerAgencyId.contains("0")){
					approverReportSPQuery.append(partnerQueryConditionString1);
				}
				approverReportSPQuery.append(" WHERE p.subActivity.status = 'ACTIVE'" + reportingPeriodQueryConditonString);
				if(!spId.startsWith("0")){
					strategicPillerQueryConditonString = " AND p.subActivity.keyActivity.output.outcome.theme.strategicPillar.id IN ( " + spId + ")";
					approverReportSPQuery.append(strategicPillerQueryConditonString);
				}
				approverReportSPQuery.append(" AND p.subActivity.leadAgency.id = " + currentAagency.getId());
				if(partnerAgencyId != null && !partnerAgencyId.isEmpty() && !partnerAgencyId.contains("-1") && !partnerAgencyId.contains("0")){
					approverReportSPQuery.append(partnerQueryConditionString2);
				}
				approverReportSPQuery.append(" ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber");
				listStrategicPillers = customReportsRepository.getStrategicPillersByReportParameters(approverReportSPQuery.toString());
			}
			
			if(themeId != null && !themeId.isEmpty()){
				approverReportThemesQuery.append("SELECT DISTINCT p.subActivity.keyActivity.output.outcome.theme FROM Planning p");
				if(partnerAgencyId != null && !partnerAgencyId.isEmpty() && !partnerAgencyId.contains("-1") && !partnerAgencyId.contains("0")){
					approverReportThemesQuery.append(partnerQueryConditionString1);
				}
				approverReportThemesQuery.append(" WHERE p.subActivity.status = 'ACTIVE'" + reportingPeriodQueryConditonString + strategicPillerQueryConditonString);
				if(!themeId.startsWith("0")){
					themeQueryConditionString = " AND p.subActivity.keyActivity.output.outcome.theme.id IN ( " + themeId + ")";
					approverReportThemesQuery.append(themeQueryConditionString);
				}
				approverReportThemesQuery.append(" AND p.subActivity.leadAgency.id = " + currentAagency.getId());
				if(partnerAgencyId != null && !partnerAgencyId.isEmpty() && !partnerAgencyId.contains("-1") && !partnerAgencyId.contains("0")){
					approverReportThemesQuery.append(partnerQueryConditionString2);
				}
				approverReportThemesQuery.append(" ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber");
				listThemes = customReportsRepository.getThemesByReportParameters(approverReportThemesQuery.toString());
			}
			if(outcomeId != null && !outcomeId.isEmpty()){
				approverReportOutcomesQuery.append("SELECT DISTINCT p.subActivity.keyActivity.output.outcome FROM Planning p");
				if(partnerAgencyId != null && !partnerAgencyId.isEmpty() && !partnerAgencyId.contains("-1") && !partnerAgencyId.contains("0")){
					approverReportOutcomesQuery.append(partnerQueryConditionString1);
				}
				approverReportOutcomesQuery.append(" WHERE p.subActivity.status = 'ACTIVE'" + reportingPeriodQueryConditonString 
						+ strategicPillerQueryConditonString + themeQueryConditionString);
				if(!outcomeId.startsWith("0")){
					outcomeQueryCondtionString = " AND p.subActivity.keyActivity.output.outcome.id IN ( " + outcomeId + ")";
					approverReportOutcomesQuery.append(outcomeQueryCondtionString);
				}
				approverReportOutcomesQuery.append(" AND p.subActivity.leadAgency.id = " + currentAagency.getId());
				if(partnerAgencyId != null && !partnerAgencyId.isEmpty() && !partnerAgencyId.contains("-1") && !partnerAgencyId.contains("0")){
					approverReportOutcomesQuery.append(partnerQueryConditionString2);
				}
				approverReportOutcomesQuery.append(" ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber");
				listOutcomes = customReportsRepository.getOutcomesByReportParameters(approverReportOutcomesQuery.toString());
			}
			
			if(outputId != null && !outputId.isEmpty()){
				approverReportOutputsQuery.append("SELECT DISTINCT p.subActivity.keyActivity.output FROM Planning p");
				if(partnerAgencyId != null && !partnerAgencyId.isEmpty() && !partnerAgencyId.contains("-1") && !partnerAgencyId.contains("0")){
					approverReportOutputsQuery.append(partnerQueryConditionString1);
				}
				approverReportOutputsQuery.append(" WHERE p.subActivity.status = 'ACTIVE'" + reportingPeriodQueryConditonString 
						+ strategicPillerQueryConditonString + themeQueryConditionString + outcomeQueryCondtionString);
				if(!outputId.startsWith("0")){
					outputQueryConditionString = " AND p.subActivity.keyActivity.output.id IN ( " + outputId + ")";
					approverReportOutputsQuery.append(outputQueryConditionString);
				}
				approverReportOutputsQuery.append(" AND p.subActivity.leadAgency.id = " + currentAagency.getId());
				if(partnerAgencyId != null && !partnerAgencyId.isEmpty() && !partnerAgencyId.contains("-1") && !partnerAgencyId.contains("0")){
					approverReportOutputsQuery.append(partnerQueryConditionString2);
				}
				approverReportOutputsQuery.append(" ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber");
				listOutputs = customReportsRepository.getOutputsByReportParameters(approverReportOutputsQuery.toString());
			}
			
			approverReportKeyActivityQuery.append("SELECT DISTINCT p.subActivity.keyActivity FROM Planning p");
			if(partnerAgencyId != null && !partnerAgencyId.isEmpty() && !partnerAgencyId.contains("-1") && !partnerAgencyId.contains("0")){
				approverReportKeyActivityQuery.append(partnerQueryConditionString1);
			}
			approverReportKeyActivityQuery.append(" WHERE p.subActivity.status = 'ACTIVE'");
			approverReportKeyActivityQuery.append(reportingPeriodQueryConditonString + strategicPillerQueryConditonString + themeQueryConditionString + outcomeQueryCondtionString);
			approverReportKeyActivityQuery.append(" AND p.subActivity.leadAgency.id = " + currentAagency.getId());
			if(partnerAgencyId != null && !partnerAgencyId.isEmpty() && !partnerAgencyId.contains("-1") && !partnerAgencyId.contains("0")){
				approverReportKeyActivityQuery.append(partnerQueryConditionString2);
			}
			approverReportKeyActivityQuery.append(" ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber");
			List<KeyActivity> listKeyActivities = customReportsRepository.getKeyActivitiesByReportParameters(approverReportKeyActivityQuery.toString());
			
			
			approverReportSubActivityQuery.append("SELECT DISTINCT p.subActivity FROM Planning p");
			if(partnerAgencyId != null && !partnerAgencyId.isEmpty() && !partnerAgencyId.contains("-1") && !partnerAgencyId.contains("0")){
				approverReportSubActivityQuery.append(partnerQueryConditionString1);
			}
			approverReportSubActivityQuery.append(" WHERE p.subActivity.status = 'ACTIVE'");
			approverReportSubActivityQuery.append(reportingPeriodQueryConditonString + strategicPillerQueryConditonString + themeQueryConditionString + outcomeQueryCondtionString);
			approverReportSubActivityQuery.append(" AND p.subActivity.leadAgency.id = " + currentAagency.getId());
			if(partnerAgencyId != null && !partnerAgencyId.isEmpty() && !partnerAgencyId.contains("-1") && !partnerAgencyId.contains("0")){
				approverReportSubActivityQuery.append(partnerQueryConditionString2);
			}
			approverReportSubActivityQuery.append(" ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber");
			List<SubActivity> listSubActivities = customReportsRepository.getSubActivitiesByReportParameters(approverReportSubActivityQuery.toString());
			if(partnerAgencyId.startsWith("0")){
				if(listSubActivities != null && !listSubActivities.isEmpty()){
					for (SubActivity subActivity : listSubActivities) {
						List<Agency> listAgency = subActivity.getPartnerAgency();
						if(listAgency != null && listAgency.size() > 0){
							for(Agency agency : listAgency){
								partneringAgencySet.add(agency.getId());
							}
						}
					}
				}
			}else if(!partnerAgencyId.contains("-1")){
				String[] agencyStringArray = partnerAgencyId.split(",");
				for(int i = 0 ; i < agencyStringArray.length ; i++){
					partneringAgencySet.add(Integer.parseInt(agencyStringArray[i]));
				}
				
			}
			
			hssfWorkbook = statusReviewerActualReportDownloadCommonFunction(currentAagency, user, listStrategicPillers, listReportingPeriods, listThemes, listOutcomes, listOutputs, listKeyActivities, listSubActivities, partneringAgencySet,reportinPeriodLength);
			/**
			 * @author pushpa
			 * Add Observation Sheet 
			 */
			HSSFSheet observationSheet = hssfWorkbook.createSheet("Observation");
			
			Font boldFont = hssfWorkbook.createFont();
			boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
			boldFont.setColor(IndexedColors.BLACK.getIndex());
			
			CellStyle cellStyle = hssfWorkbook.createCellStyle();
			cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
			cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
			cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			cellStyle.setBorderRight(CellStyle.BORDER_THIN);
			cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
			cellStyle.setBorderTop(CellStyle.BORDER_THIN);
			cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
			cellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
			cellStyle.setFont(boldFont);

			CellStyle cellStyle2 = hssfWorkbook.createCellStyle();
			cellStyle2.setAlignment(CellStyle.ALIGN_LEFT);
			cellStyle2.setBorderBottom(CellStyle.BORDER_THIN);
			cellStyle2.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			cellStyle2.setBorderLeft(CellStyle.BORDER_THIN);
			cellStyle2.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			cellStyle2.setBorderRight(CellStyle.BORDER_THIN);
			cellStyle2.setRightBorderColor(IndexedColors.BLACK.getIndex());
			cellStyle2.setBorderTop(CellStyle.BORDER_THIN);
			cellStyle2.setTopBorderColor(IndexedColors.BLACK.getIndex());
			
			
//			Read Observation data
			List<SubmitForReview> finalSubmitforReviewList = new ArrayList<SubmitForReview>();
			List<UserRole> userRoles =  user.getUserRoles();
			for (UserRole userRole : userRoles) {
				if(userRole.getName().equals("STATUS_REVIEWER")){
					Agency leadagency = agencyService.findByLoginUser(user.getUsername());
					for(ReportingPeriod reportPeriod : listReportingPeriods){
						List<SubmitForReview> listSubmitForReviews = submitForReviewService.findByReportingPeriodAndUserLevel(reportPeriod, 1);
						for (SubmitForReview submitForReview : listSubmitForReviews) {
							List<SubActivity> subActivities = subActivityService.getSubActivitiesByOpenedReportingPeriodAndPartnerAgencyAndLeadAgency(reportPeriod.getId(), submitForReview.getAgency().getId(), leadagency.getId());
							if(subActivities != null && subActivities.size() > 0){
								finalSubmitforReviewList.add(submitForReview);
							}
						}
					}
				}
			}
			
//			Set row values in excel sheet
			int Startrow = 0;
			for (int i = 0; i < finalSubmitforReviewList.size(); i++) {
				
				SubmitForReview submitForReview = finalSubmitforReviewList.get(i);
				
				HSSFRow userAgencyRow = observationSheet.createRow(Startrow);
				Cell userCell = userAgencyRow.createCell(0);
				String userAgencyName = "By " + submitForReview.getUser().getFirstName();
				if(submitForReview.getUser().getLastName() != null){
					userAgencyName += " " + submitForReview.getUser().getLastName();
				}
				if(submitForReview.getAgency().getName() != null){
					userAgencyName += " ( " + submitForReview.getAgency().getName() + " ) ";
				}
				userCell.setCellValue(userAgencyName);
				userCell.setCellStyle(cellStyle);
				
				Startrow = Startrow + 1;
				HSSFRow keyLearningRow = observationSheet.createRow(Startrow);
				Cell keyLearningCellLabel = keyLearningRow.createCell(1);
				keyLearningCellLabel.setCellValue("Key Learnings");
				keyLearningCellLabel.setCellStyle(cellStyle);
				Cell keyLearningCellValue = keyLearningRow.createCell(2);
				keyLearningCellValue.setCellValue(submitForReview.getKeyLearning());
				keyLearningCellValue.setCellStyle(cellStyle2);
				
				Startrow = Startrow + 1;
				HSSFRow keyChallengesRow = observationSheet.createRow(Startrow);
				Cell keyChallengesCellLabel = keyChallengesRow.createCell(1);
				keyChallengesCellLabel.setCellValue("Key Challanges");
				keyChallengesCellLabel.setCellStyle(cellStyle);
				Cell keyChallengesCellValue = keyChallengesRow.createCell(2);
				keyChallengesCellValue.setCellValue(submitForReview.getKeyChallenge());
				keyChallengesCellValue.setCellStyle(cellStyle2);
				
				Startrow = Startrow + 1;
				HSSFRow bestPracticesRow = observationSheet.createRow(Startrow);
				Cell bestPracticesCellLabel = bestPracticesRow.createCell(1);
				bestPracticesCellLabel.setCellValue("Best Practices");
				bestPracticesCellLabel.setCellStyle(cellStyle);
				Cell bestPracticesCellValue = bestPracticesRow.createCell(2);
				bestPracticesCellValue.setCellValue(submitForReview.getBestPractice());
				bestPracticesCellValue.setCellStyle(cellStyle2);
				
				Startrow = Startrow + 1;
				HSSFRow supportNeedsRow = observationSheet.createRow(Startrow);
				Cell supportNeedsCellLabel = supportNeedsRow.createCell(1);
				supportNeedsCellLabel.setCellValue("Support Needs");
				supportNeedsCellLabel.setCellStyle(cellStyle);
				Cell supportNeedsCellValue = supportNeedsRow.createCell(2);
				supportNeedsCellValue.setCellValue(submitForReview.getSupportNeeds());
				supportNeedsCellValue.setCellStyle(cellStyle2);
				
				Startrow = Startrow + 1;
				HSSFRow submittedTimeRow = observationSheet.createRow(Startrow);
				Cell submittedTimeCellLabel = submittedTimeRow.createCell(1);
				submittedTimeCellLabel.setCellValue("Submitted Time");
				submittedTimeCellLabel.setCellStyle(cellStyle);
				Cell submittedTimeCellValue = submittedTimeRow.createCell(2);
				SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
				Date submittedTime = submitForReview.getSubmit_dateTime();
				String submittedTimeString = (submittedTime != null) ? sd.format(submittedTime) : "";
				submittedTimeCellValue.setCellValue(submittedTimeString);
				submittedTimeCellValue.setCellStyle(cellStyle2);
			}
			observationSheet.autoSizeColumn(0);
			observationSheet.autoSizeColumn(1);
			observationSheet.autoSizeColumn(2);
//			End Observation Sheet Details creation
			
			hssfSheet.autoSizeColumn(0);
			hssfSheet.autoSizeColumn(4);
			hssfSheet.autoSizeColumn(5);
			hssfSheet.autoSizeColumn(6);
			hssfSheet.autoSizeColumn(7);
			hssfSheet.autoSizeColumn(8);
			hssfSheet.autoSizeColumn(9);
			hssfSheet.autoSizeColumn(10);

			
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
		}else{
			return null;
		}
//		End Create Report Queries
	}
	
	/**
	 * Common function for status Reviewer
	 * @author Prem Kumar
	 * @param currentAagency
	 * @param user
	 * @param listStrategicPillers
	 * @param listReportingPeriods
	 * @param listThemes
	 * @param listOutcomes
	 * @param listOutputs
	 * @param listKeyActivities
	 * @param subActivities
	 * @return
	 */
	public HSSFWorkbook statusReviewerActualReportDownloadCommonFunction(Agency currentAagency,User user,List<StrategicPillar> listStrategicPillers,
			List<ReportingPeriod> reportingPeriods,List<Theme> listThemes,List<Outcome> listOutcomes,
			List<Output> listOutputs,List<KeyActivity> listKeyActivities,
			List<SubActivity> subActivities, Set<Integer> partneringAgencySet,String reportinPeriodLength){
		
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		HSSFSheet hssfSheet = hssfWorkbook.createSheet("Activity Report Details");
		
		HSSFRow headerRow = hssfSheet.createRow(0);
		
		HSSFCellStyle agencystyle = hssfWorkbook.createCellStyle();
		agencystyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
		agencystyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
		agencystyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
		agencystyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		
		Font boldFont = hssfWorkbook.createFont();
		boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		boldFont.setColor(IndexedColors.BLACK.getIndex());
		
		Font mainFont = hssfWorkbook.createFont();
		mainFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		mainFont.setFontHeightInPoints((short) 20);
		mainFont.setColor(IndexedColors.BLACK.getIndex());
		
		CellStyle mainHeaderStyle = hssfWorkbook.createCellStyle();
		mainHeaderStyle.setBorderBottom(CellStyle.BORDER_THIN);
		mainHeaderStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		mainHeaderStyle.setBorderLeft(CellStyle.BORDER_THIN);
		mainHeaderStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		mainHeaderStyle.setBorderRight(CellStyle.BORDER_THIN);
		mainHeaderStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		mainHeaderStyle.setBorderTop(CellStyle.BORDER_THIN);
		mainHeaderStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		mainHeaderStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		mainHeaderStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		mainHeaderStyle.setAlignment(CellStyle.ALIGN_CENTER);
		mainHeaderStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		mainHeaderStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		mainHeaderStyle.setFont(mainFont);
		mainHeaderStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
		mainHeaderStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
		mainHeaderStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
		mainHeaderStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		
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
		
		CellStyle tableHeaderStyleLeft = hssfWorkbook.createCellStyle();
		tableHeaderStyleLeft.setBorderBottom(CellStyle.BORDER_THIN);
		tableHeaderStyleLeft.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		tableHeaderStyleLeft.setBorderLeft(CellStyle.BORDER_THIN);
		tableHeaderStyleLeft.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		tableHeaderStyleLeft.setBorderRight(CellStyle.BORDER_THIN);
		tableHeaderStyleLeft.setRightBorderColor(IndexedColors.BLACK.getIndex());
		tableHeaderStyleLeft.setBorderTop(CellStyle.BORDER_THIN);
		tableHeaderStyleLeft.setTopBorderColor(IndexedColors.BLACK.getIndex());
		tableHeaderStyleLeft.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		tableHeaderStyleLeft.setFillPattern(CellStyle.SOLID_FOREGROUND);
		tableHeaderStyleLeft.setAlignment(CellStyle.ALIGN_LEFT);
		tableHeaderStyleLeft.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		tableHeaderStyleLeft.setFillPattern(CellStyle.SOLID_FOREGROUND);
		tableHeaderStyleLeft.setFont(boldFont);
		tableHeaderStyleLeft.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
		tableHeaderStyleLeft.setBorderRight(HSSFCellStyle.BORDER_THIN);            
		tableHeaderStyleLeft.setBorderTop(HSSFCellStyle.BORDER_THIN);              
		tableHeaderStyleLeft.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		
		CellStyle tableHeaderStyle1 = hssfWorkbook.createCellStyle();
		Font font = hssfWorkbook.createFont();
		font.setColor(IndexedColors.RED.getIndex());
		tableHeaderStyle1.setFont(font);
		
		//Create First Row
		HSSFCell seqNumber = headerRow.createCell(0);
		seqNumber.setCellValue("National Drug Control Master Plan (NDCMP)");
		seqNumber.setCellStyle(mainHeaderStyle);
		seqNumber.setCellType(Cell.CELL_TYPE_STRING);
		headerRow.setHeight((short)700);
		
		hssfSheet.addMergedRegion(new CellRangeAddress(0,0,0,13));
		
		HSSFCellStyle headerstyle = hssfWorkbook.createCellStyle();
		headerstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
		headerstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
		headerstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
		headerstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		headerstyle.setFont(boldFont);
		Integer rowValue = 0;
		rowValue++;
		
		headerRow = hssfSheet.createRow(rowValue);
		CellStyle labelStyle = hssfWorkbook.createCellStyle();
		labelStyle.setFont(boldFont);
		HSSFCell nameOfAgency = headerRow.createCell(0);
		nameOfAgency.setCellValue("Report By : "+user.getFirstName()+" "+user.getLastName()+" ("+currentAagency.getName()+")");
		//nameOfAgency.setCellStyle(labelStyle);
		nameOfAgency.setCellStyle(tableHeaderStyleLeft);
		nameOfAgency.setCellType(Cell.CELL_TYPE_STRING);
		hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,0,1));
		
		HSSFCell agencyName  = headerRow.createCell(1);
		agencyName.setCellType(Cell.CELL_TYPE_STRING);

		HSSFCell reportingPeriod  = headerRow.createCell(2);
		StringBuffer reportingPeriodsName = new StringBuffer();
		if(reportinPeriodLength.equals("0")){
			reportingPeriodsName.append("Reporting Period : "+" All ");
		}else{
			for (ReportingPeriod period : reportingPeriods) {
				if(reportingPeriodsName.length() == 0){
					reportingPeriodsName.append("Reporting Period : "+period.getYear()+" - "+period.getName());
				}else{
					reportingPeriodsName.append(" , "+period.getYear()+" - "+period.getName());
				}
			}
		}
		
		reportingPeriod.setCellValue(reportingPeriodsName.toString());
		reportingPeriod.setCellStyle(tableHeaderStyle);
		reportingPeriod.setCellType(Cell.CELL_TYPE_STRING);
		hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,2,7));
		
		
		SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		Date date = new Date();
		
		HSSFCell reportTime = headerRow.createCell(8);
		reportTime.setCellValue("Report Time : "+sd.format(date));
		reportTime.setCellStyle(tableHeaderStyle);
		reportTime.setCellType(Cell.CELL_TYPE_STRING);
		hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,8,13));
	
		HSSFCellStyle row2=hssfWorkbook.createCellStyle();
		row2.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
		row2.setBorderRight(HSSFCellStyle.BORDER_THIN);            
		row2.setBorderTop(HSSFCellStyle.BORDER_THIN);              
		row2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	
		String[] sColor=hex2Rgb("#33cccc").split(",");
		String[] yColor=hex2Rgb("#FFCC99").split(",");
		String[] keyBarColor=hex2Rgb("#CCFFCC").split(",");
		
		int c1=Integer.parseInt(sColor[0]);
		int c2=Integer.parseInt(sColor[1]);
		int c3=Integer.parseInt(sColor[2]);
		
		int yc1=Integer.parseInt(yColor[0]);
		int yc2=Integer.parseInt(yColor[1]);
		int yc3=Integer.parseInt(yColor[2]);
		
		int kc1=Integer.parseInt(keyBarColor[0]);
		int kc2=Integer.parseInt(keyBarColor[1]);
		int kc3=Integer.parseInt(keyBarColor[2]);
	
		HSSFCellStyle style2=hssfWorkbook.createCellStyle();
		HSSFColor skyBlue =  setColor(hssfWorkbook,(byte) c1, (byte) c2,(byte) c3);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);            
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);              
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setFillForegroundColor(skyBlue.getIndex());
		style2.setFont(boldFont);
		style2.setFillPattern(CellStyle.SOLID_FOREGROUND);
	
	
		HSSFCellStyle keyBarstyle2=hssfWorkbook.createCellStyle();
		HSSFColor green =  setColor(hssfWorkbook,(byte) kc1, (byte) kc2,(byte) kc3);
		keyBarstyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
		keyBarstyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);            
		keyBarstyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);              
		keyBarstyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		keyBarstyle2.setFillForegroundColor(green.getIndex());
		keyBarstyle2.setFont(boldFont);
		keyBarstyle2.setFillPattern(CellStyle.SOLID_FOREGROUND);
		rowValue++;
		for (StrategicPillar strategicPillar : listStrategicPillers) {
		
			headerRow = hssfSheet.createRow(rowValue);
			HSSFCell sPillarName = headerRow.createCell(0);
			sPillarName.setCellValue("Strategic Pillar  "+strategicPillar.getId()+" : "+strategicPillar.getName());
			sPillarName.setCellStyle(style2);
			sPillarName.setCellType(Cell.CELL_TYPE_STRING);
			
			hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,0,13));
			
			rowValue++;
			List<Theme> themesByStrategicPillar = themeService.getThemeByStrategicPillar(strategicPillar);
			for (Theme theme : themesByStrategicPillar) {
				for (Theme themeByAgencyId : listThemes) {
					if(theme.getId() == themeByAgencyId.getId()){
						headerRow = hssfSheet.createRow(rowValue);
						
						HSSFCellStyle yellowStyle=hssfWorkbook.createCellStyle();
						yellowStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
						yellowStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
						yellowStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
						yellowStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
						HSSFColor sandal =  setColor(hssfWorkbook,(byte) yc1, (byte) yc2,(byte) yc3);
						yellowStyle.setFillForegroundColor(sandal.getIndex());
						yellowStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
						yellowStyle.setFont(boldFont);
						
						HSSFCell themeName = headerRow.createCell(0);
						themeName.setCellValue("Theme : "+theme.getName());
						themeName.setCellStyle(yellowStyle);
						themeName.setCellType(Cell.CELL_TYPE_STRING);
						
						hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,0,13));
						
						rowValue++;
						List<Outcome> outcomes2 = outcomeService.getByTheme(themeByAgencyId);
						for (Outcome outcome : outcomes2) {
							for (Outcome outcomeByAgencyId : listOutcomes) {
								if(outcomeByAgencyId.getId() == outcome.getId()){
									Font outcomeFont = hssfWorkbook.createFont();
									outcomeFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
									outcomeFont.setColor(IndexedColors.BLUE.getIndex());
								
									HSSFCellStyle outcomeStyle=hssfWorkbook.createCellStyle();
									HSSFColor outcomeSandal = setColor(hssfWorkbook,(byte) yc1, (byte) yc2,(byte) yc3);
									outcomeStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
									outcomeStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
									outcomeStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
									outcomeStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
									outcomeStyle.setFillForegroundColor(outcomeSandal.getIndex());
									outcomeStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
									outcomeStyle.setFont(outcomeFont);
									
									headerRow = hssfSheet.createRow(rowValue);
									HSSFCell outcomeName = headerRow.createCell(0);
									outcomeName.setCellValue("Outcome "+outcome.getSequenceNumber()+ " : "+outcome.getName());
									outcomeName.setCellStyle(outcomeStyle);
									outcomeName.setCellType(Cell.CELL_TYPE_STRING);
									
									hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,0,13));
									
									rowValue++;
									List<Output> outputs2 = outputServices.getByOutcome(outcomeByAgencyId);
									for (Output output : outputs2) {
										for (Output outputByAgencyid : listOutputs) {
											if(outputByAgencyid.getId() == output.getId()){
												Font outputFont = hssfWorkbook.createFont();
												outputFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
												outputFont.setColor(IndexedColors.RED.getIndex());
												
												HSSFCellStyle outputStyle=hssfWorkbook.createCellStyle();
												HSSFColor outputSandal = setColor(hssfWorkbook,(byte) yc1, (byte) yc2,(byte) yc3);
												outputStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
												outputStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
												outputStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
												outputStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
												outputStyle.setFillForegroundColor(outputSandal.getIndex());
												outputStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
												outputStyle.setFont(outputFont);
												
												HSSFCellStyle activityStyle=hssfWorkbook.createCellStyle();
												activityStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
												activityStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
												activityStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
												activityStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
												
												headerRow = hssfSheet.createRow(rowValue);
												HSSFCell outputName = headerRow.createCell(0);
												outputName.setCellValue("Output "+output.getSequenceNumber()+" : "+output.getOutput());
												outputName.setCellStyle(outputStyle);
												outputName.setCellType(Cell.CELL_TYPE_STRING);
												
												hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,0,13));
												
												rowValue++;
												List<Indicator> indicators = indicatorService.findByOutput(outputByAgencyid);
												for (Indicator indicator : indicators) {
													headerRow = hssfSheet.createRow(rowValue);
													HSSFCell indicatorName = headerRow.createCell(0);
													indicatorName.setCellValue("Indicators : "+indicator.getMessage());
													indicatorName.setCellStyle(yellowStyle);
													indicatorName.setCellType(Cell.CELL_TYPE_STRING);
													hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,0,13));
													
													rowValue++;
												}
												//int temp = 0;
												List<Target> targets = targetService.findByOutput(outputByAgencyid);
												for (Target target : targets) {
													headerRow = hssfSheet.createRow(rowValue);
													
													HSSFCell targetName = headerRow.createCell(0);
													targetName.setCellValue("Targets : "+target.getMessage());
													targetName.setCellStyle(yellowStyle);
													targetName.setCellType(Cell.CELL_TYPE_STRING);
													hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,0,13));
													rowValue++;
												}
												headerRow = hssfSheet.createRow(rowValue);
												HSSFCell seqenceNumber = headerRow.createCell(0);
												seqenceNumber.setCellValue("S.No");
												seqenceNumber.setCellStyle(keyBarstyle2);
												seqenceNumber.setCellType(Cell.CELL_TYPE_STRING); 
												
												HSSFCell keyActivitys = headerRow.createCell(1);
												keyActivitys.setCellValue("Key Activities");
												keyActivitys.setCellStyle(keyBarstyle2);
												keyActivitys.setCellType(Cell.CELL_TYPE_STRING);
	
												HSSFCell subActivitys  = headerRow.createCell(2);
												subActivitys.setCellValue("Sub Activities");
												subActivitys.setCellStyle(keyBarstyle2);
												subActivitys.setCellType(Cell.CELL_TYPE_STRING);
												
												HSSFCell status = headerRow.createCell(3);
												status.setCellValue("Status");
												status.setCellStyle(keyBarstyle2);
												status.setCellType(Cell.CELL_TYPE_STRING);
												
												HSSFCell percentage = headerRow.createCell(4);
												percentage.setCellValue("Completion Percentage");
												percentage.setCellStyle(keyBarstyle2);
												percentage.setCellType(Cell.CELL_TYPE_STRING);
												
												HSSFCell keyProgress = headerRow.createCell(5);
												keyProgress.setCellValue("Key Progress");
												keyProgress.setCellStyle(keyBarstyle2);
												keyProgress.setCellType(Cell.CELL_TYPE_STRING);
											
												HSSFCell reasongap = headerRow.createCell(6);
												reasongap.setCellValue("Reasons for gap if any");
												reasongap.setCellStyle(keyBarstyle2);
												reasongap.setCellType(Cell.CELL_TYPE_STRING);
												
												HSSFCell reactify = headerRow.createCell(7);
												reactify.setCellValue("Plan of Action to rectify the gap");
												reactify.setCellStyle(keyBarstyle2);
												reactify.setCellType(Cell.CELL_TYPE_STRING);
												
												HSSFCell feedbac = headerRow.createCell(8);
												feedbac.setCellValue("Reviewer Feedback");
												feedbac.setCellStyle(keyBarstyle2);
												feedbac.setCellType(Cell.CELL_TYPE_STRING);
												hssfSheet.setColumnWidth(7,10000);
												
												HSSFCell updatedByHeading = headerRow.createCell(9);
												updatedByHeading.setCellValue("Last Updated By");
												updatedByHeading.setCellStyle(keyBarstyle2);
												updatedByHeading.setCellType(Cell.CELL_TYPE_STRING);
	
												HSSFCell updatedTimeHeading = headerRow.createCell(10);
												updatedTimeHeading.setCellValue("Last Updated Time");
												updatedTimeHeading.setCellStyle(keyBarstyle2);
												updatedTimeHeading.setCellType(Cell.CELL_TYPE_STRING);
												
												HSSFCell reviewedByHeading = headerRow.createCell(11);
												reviewedByHeading.setCellValue("Reviewed By");
												reviewedByHeading.setCellStyle(keyBarstyle2);
												reviewedByHeading.setCellType(Cell.CELL_TYPE_STRING);
	
												HSSFCell reviewedTimeHeading = headerRow.createCell(12);
												reviewedTimeHeading.setCellValue("Reviewed Time");
												reviewedTimeHeading.setCellStyle(keyBarstyle2);
												reviewedTimeHeading.setCellType(Cell.CELL_TYPE_STRING);
												
												HSSFCell reviewedActivityStatus = headerRow.createCell(13);
												reviewedActivityStatus.setCellValue("Activity Status");
												hssfSheet.setColumnWidth(13,9000);
												reviewedActivityStatus.setCellStyle(keyBarstyle2);
												reviewedActivityStatus.setCellType(Cell.CELL_TYPE_STRING);
												
											
												rowValue++;
												List<KeyActivity> activities = keyActivityService.findByOutput(outputByAgencyid);
												for (KeyActivity keyActivity : activities) {
													for (KeyActivity keyActivityByReportingPeriod : listKeyActivities) {
														if(keyActivityByReportingPeriod.getId() == keyActivity.getId()){
															headerRow = hssfSheet.createRow(rowValue);
															HSSFCell keySeqNumber = headerRow.createCell(0);
															keySeqNumber.setCellValue(keyActivity.getSequenceNumber());
															keySeqNumber.setCellStyle(activityStyle);
															
															HSSFCell keyActivityName = headerRow.createCell(1);
															keyActivityName.setCellValue(keyActivity.getName());
															keyActivityName.setCellStyle(activityStyle);
															
															CellStyle cellStylekeyName = hssfWorkbook.createCellStyle();
															cellStylekeyName.setWrapText(true);//set wraper text
															keyActivityName.setCellStyle(cellStylekeyName);
															headerRow.setHeightInPoints((2*hssfSheet.getDefaultRowHeightInPoints()));
															hssfSheet.setColumnWidth(1,11000);
															
														
															HSSFCell subActivity = headerRow.createCell(2);
															subActivity.setCellValue("");
															subActivity.setCellStyle(activityStyle);
															
															HSSFCell status1 = headerRow.createCell(3);
															status1.setCellValue("");
															status1.setCellStyle(activityStyle);
															
															HSSFCell percentage1 = headerRow.createCell(4);
															percentage1.setCellValue("");
															percentage1.setCellStyle(activityStyle);
															
															HSSFCell keyProgress1 = headerRow.createCell(5);
															keyProgress1.setCellValue("");
															keyProgress1.setCellStyle(activityStyle);
															
															HSSFCell reason = headerRow.createCell(6);
															reason.setCellValue("");
															reason.setCellStyle(activityStyle);
															
															HSSFCell rectify = headerRow.createCell(7);
															rectify.setCellValue("");
															rectify.setCellStyle(activityStyle);
															
															HSSFCell feedback = headerRow.createCell(8);
															feedback.setCellValue("");
															feedback.setCellStyle(activityStyle);
															
															HSSFCell updatedByEmpty = headerRow.createCell(9);
															updatedByEmpty.setCellValue("");
															updatedByEmpty.setCellStyle(activityStyle);
															
															HSSFCell  updatedTimeEmpty= headerRow.createCell(10);
															updatedTimeEmpty.setCellValue("");
															updatedTimeEmpty.setCellStyle(activityStyle);
	
															HSSFCell reviewedByEmpty = headerRow.createCell(11);
															reviewedByEmpty.setCellValue("");
															reviewedByEmpty.setCellStyle(activityStyle);
															
															HSSFCell  reviewedTimeEmpty= headerRow.createCell(12);
															reviewedTimeEmpty.setCellValue("");
															reviewedTimeEmpty.setCellStyle(activityStyle);
															
															HSSFCell  reviewedActStatusEmpty= headerRow.createCell(13);
															reviewedActStatusEmpty.setCellValue("");
															reviewedActStatusEmpty.setCellStyle(activityStyle);
															
															rowValue++;
															List<SubActivity> subActivities3 = subActivityService.findByKeyActivity(keyActivityByReportingPeriod);
															if(subActivities3.size() > 0){
																for(SubActivity subActivity1 : subActivities3){
																	for (SubActivity subActivity2 : subActivities) {
																		if(subActivity2.getId() == subActivity1.getId()){
																			Integer currentRowValue = rowValue;
																			headerRow = hssfSheet.createRow(rowValue);
																			HSSFCell subSeySeqNumber = headerRow.createCell(0);
																			subSeySeqNumber.setCellValue(subActivity2.getSequenceNumber());
																			subSeySeqNumber.setCellStyle(activityStyle);
																			
																			HSSFCell keyActivityNameForSubactivity = headerRow.createCell(1);
																			keyActivityNameForSubactivity.setCellValue("");
																			keyActivityNameForSubactivity.setCellStyle(activityStyle);
																		
																			HSSFCell subActivityName = headerRow.createCell(2);
																			subActivityName.setCellValue(subActivity2.getName());
																			subActivityName.setCellStyle(activityStyle);
																			
																			CellStyle cellStyle1 = hssfWorkbook.createCellStyle();
																			cellStyle1.setWrapText(true);//set wraper text
																			cellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
																			cellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);            
																			cellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);              
																			cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
																			subActivityName.setCellStyle(cellStyle1);
																			headerRow.setHeightInPoints((2*hssfSheet.getDefaultRowHeightInPoints()));
																			hssfSheet.setColumnWidth(2,11000);
																			
																			int temp1 = 0;
																			StatusTracking subActivityStatusTracking = new StatusTracking();
																			for (ReportingPeriod listReportingPeriods : reportingPeriods) {
																				StatusTracking lastaUpdatedStatusTracking = statusTrackingRepository.findByUserAndSubActivityAndReportingPeriodAndUserLevel(user, subActivity1, listReportingPeriods, 2);
																				if(lastaUpdatedStatusTracking != null){
																				subActivityStatusTracking = new StatusTracking();
																				subActivityStatusTracking = statusTrackingRepository.findByUserAndSubActivityAndReportingPeriodAndUserLevel(user, subActivity1, listReportingPeriods, 2);
																			}
																		}
																		
																		if(subActivityStatusTracking != null){
																			HSSFCellStyle statusColor = hssfWorkbook.createCellStyle();
																			
																			if(subActivityStatusTracking.getActualStatusColor() != null){
																				String[] staColor=hex2Rgb(subActivityStatusTracking.getActualStatusColor()).split(",");
																				int ac1=Integer.parseInt(staColor[0]);
																				int ac2=Integer.parseInt(staColor[1]);
																				int ac3=Integer.parseInt(staColor[2]);
																				
																				HSSFPalette palette = hssfWorkbook.getCustomPalette();
																				HSSFColor myColor = palette.findSimilarColor(ac1, ac2, ac3);
																				short palIndex = myColor.getIndex();
																				
																				statusColor.setFillForegroundColor(palIndex);
																				statusColor.setFillPattern(CellStyle.SOLID_FOREGROUND);
																				statusColor.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
																				statusColor.setBorderRight(HSSFCellStyle.BORDER_THIN);            
																				statusColor.setBorderTop(HSSFCellStyle.BORDER_THIN);              
																				statusColor.setBorderBottom(HSSFCellStyle.BORDER_THIN);
																			}
																			
																			HSSFCell status2 = headerRow.createCell(3);
																			status2.setCellStyle(statusColor);
																			
																			HSSFCell percentage2 = headerRow.createCell(4);
																			if(subActivityStatusTracking.getActualStatusPercentage() != null){
																				percentage2.setCellValue(Integer.parseInt(subActivityStatusTracking.getActualStatusPercentage()));
																			}
																			percentage2.setCellStyle(activityStyle);
																			
																			HSSFCell keyProgress2 = headerRow.createCell(5);
																			keyProgress2.setCellValue(subActivityStatusTracking.getKeyProgress());
																			keyProgress2.setCellStyle(activityStyle);
																			
																			HSSFCell reason1 = headerRow.createCell(6);
																			reason1.setCellValue(subActivityStatusTracking.getReasonForGap());
																			reason1.setCellStyle(activityStyle);
																			
																			HSSFCell rectify1 = headerRow.createCell(7);
																			rectify1.setCellValue(subActivityStatusTracking.getRectifyTheGap());
																			rectify1.setCellStyle(activityStyle);
																			
																			HSSFCell feedback1 = headerRow.createCell(8);
																			feedback1.setCellValue(subActivityStatusTracking.getReviewerFeedback());
																			feedback1.setCellStyle(activityStyle);
																			
																			HSSFCell updatedByValueAgency3 = headerRow.createCell(9);
																			String updatedByname3 = "";
																			if(subActivityStatusTracking.getUser() != null){
																				if(subActivityStatusTracking.getUser().getFirstName() != null){
																					updatedByname3 = subActivityStatusTracking.getUser().getFirstName();
																				}
																				if(subActivityStatusTracking.getUser().getLastName() != null){
																					updatedByname3 += " "+ subActivityStatusTracking.getUser().getLastName();
																				}
																				updatedByname3 += " (" + subActivityStatusTracking.getAgency().getName() + ")";
																				updatedByValueAgency3.setCellValue(updatedByname3);
																			}else{
																				updatedByValueAgency3.setCellValue("");
																			}
																			updatedByValueAgency3.setCellStyle(activityStyle);
																			
																			HSSFCell lastUpdatedOnValue3 = headerRow.createCell(10);
																			if(subActivityStatusTracking.getModificationTime() != null){
																				SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
																				try {
																					Date lastUpdatedOn = subActivityStatusTracking.getModificationTime();
																					lastUpdatedOnValue3.setCellValue(dateformat.format(lastUpdatedOn));
																				} catch (Exception e) {
																					e.printStackTrace();
																				}
																				
																			}else{
																				lastUpdatedOnValue3.setCellValue("");
																			}
																			lastUpdatedOnValue3.setCellStyle(activityStyle);
																			
																			HSSFCell reviewedByValue = headerRow.createCell(11);
																			if(subActivityStatusTracking.getReviewedBy() != null){
																				reviewedByValue.setCellValue(subActivityStatusTracking.getReviewedBy().getFirstName()+" "+subActivityStatusTracking.getReviewedBy().getLastName());
																			}else{
																				reviewedByValue.setCellValue("");
																			}
																			reviewedByValue.setCellStyle(activityStyle);

																			HSSFCell reviewedTimeValue = headerRow.createCell(12);
																			if(subActivityStatusTracking.getReviewedOn() != null){
																				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
																				SimpleDateFormat dsf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
																				Date date2 = null;
																				String reviewTime = subActivityStatusTracking.getReviewedOn();
																				try {
																					date2 = sdf.parse(reviewTime);
																					reviewedTimeValue.setCellValue(dsf.format(date2));
																				} catch (ParseException e) {
																					e.printStackTrace();
																				}
																			}else{
																				reviewedTimeValue.setCellValue("");
																			}
																			reviewedTimeValue.setCellStyle(activityStyle);
																			temp1 = 1;
																			

																			HSSFCell subActReviewedStatus= headerRow.createCell(13);
																			String subActReviewedData="";
																													
																			if(subActivityStatusTracking.getUserLevel() !=  null && subActivityStatusTracking.getUserLevel().equals(1)){
																				if(subActivityStatusTracking.isComplete() && subActivityStatusTracking.getReviewStatus().equals(1) && subActivityStatusTracking.isSentForReview()){
																					subActReviewedData="Approved";
																				}else if(subActivityStatusTracking.isComplete() && subActivityStatusTracking.getReviewStatus().equals(-1) && subActivityStatusTracking.isSentForReview()){
																					subActReviewedData="Need More Information";
																				}else {
																					subActReviewedData="";
																				}
																			}else if(subActivityStatusTracking.getUserLevel() !=  null && subActivityStatusTracking.getUserLevel().equals(2)){
																				if(subActivityStatusTracking.isComplete() && subActivityStatusTracking.getReviewStatus().equals(0) && !subActivityStatusTracking.isSentForReview()){
																					subActReviewedData="Ready For Approval";
																				}else if(!subActivityStatusTracking.isComplete() && subActivityStatusTracking.getReviewStatus().equals(0) && !subActivityStatusTracking.isSentForReview()){
																					subActReviewedData="Saved";
																				}else if(subActivityStatusTracking.isComplete() && subActivityStatusTracking.getReviewStatus().equals(0) && subActivityStatusTracking.isSentForReview()){
																					subActReviewedData="Submitted For Approval";
																				} else if(subActivityStatusTracking.isComplete() && subActivityStatusTracking.getReviewStatus().equals(1) && subActivityStatusTracking.isSentForReview()){
																					subActReviewedData="Approved";
																				}else if(subActivityStatusTracking.isComplete() && subActivityStatusTracking.getReviewStatus().equals(-1) && subActivityStatusTracking.isSentForReview()){
																					subActReviewedData="Need More Information";
																				}else {
																					subActReviewedData="";
																				}
																			}else{
																					subActReviewedData="";
																		}
																													
																	subActReviewedStatus.setCellValue(subActReviewedData);
																	subActReviewedStatus.setCellStyle(activityStyle);
																			
																			
																			rowValue++;
																		}
																		if(temp1 == 0){
																			HSSFCell status2 = headerRow.createCell(3);
																			status2.setCellStyle(activityStyle);
																			
																			HSSFCell keyProgress2 = headerRow.createCell(4);
																			keyProgress2.setCellStyle(activityStyle);
																			
																			HSSFCell reason1 = headerRow.createCell(5);
																			reason1.setCellStyle(activityStyle);
																			
																			HSSFCell rectify1 = headerRow.createCell(6);
																			rectify1.setCellStyle(activityStyle);
																			
																			HSSFCell feedback2 = headerRow.createCell(7);
																			feedback2.setCellStyle(activityStyle);
																			
																			HSSFCell updatedBy = headerRow.createCell(8);
																			updatedBy.setCellStyle(activityStyle);
																			
																			HSSFCell updatedTime = headerRow.createCell(9);
																			updatedTime.setCellStyle(activityStyle);
																			
																			HSSFCell reviewedBy = headerRow.createCell(10);
																			reviewedBy.setCellValue("");
																			reviewedBy.setCellStyle(activityStyle);

																			HSSFCell reviewedTime = headerRow.createCell(11);
																			reviewedTime.setCellValue("");
																			reviewedTime.setCellStyle(activityStyle);
																			
																			HSSFCell empty = headerRow.createCell(12);
																			empty.setCellValue("");
																			empty.setCellStyle(activityStyle);
																		}
																		
																		int temp = 0;
																		for (ReportingPeriod listReportingPeriods : reportingPeriods) {
																			StatusTracking statusTracking = statusTrackingRepository.findByUserAndSubActivityAndReportingPeriodAndUserLevel(user, subActivity1, listReportingPeriods, 2);
																			if(statusTracking != null){
																				HSSFCellStyle	statusColor=hssfWorkbook.createCellStyle();
																				
																				String[] staColor=hex2Rgb(statusTracking.getActualStatusColor()).split(",");
																				int ac1=Integer.parseInt(staColor[0]);
																				int ac2=Integer.parseInt(staColor[1]);
																				int ac3=Integer.parseInt(staColor[2]);
																				
																				HSSFPalette palette = hssfWorkbook.getCustomPalette();
																				HSSFColor myColor = palette.findSimilarColor(ac1, ac2, ac3);
																				short palIndex = myColor.getIndex();
																				
																				statusColor.setFillForegroundColor(palIndex);
																				statusColor.setFillPattern(CellStyle.SOLID_FOREGROUND);
																				statusColor.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
																				statusColor.setBorderRight(HSSFCellStyle.BORDER_THIN);            
																				statusColor.setBorderTop(HSSFCellStyle.BORDER_THIN);              
																				statusColor.setBorderBottom(HSSFCellStyle.BORDER_THIN);
																				
																				if(currentRowValue == rowValue){
																					rowValue++;
																					headerRow = hssfSheet.createRow(rowValue);
																					
																					HSSFCell emptyKey = headerRow.createCell(0);
																					emptyKey.setCellValue(" ");
																					emptyKey.setCellStyle(activityStyle);
																					
																					HSSFCell emptySub = headerRow.createCell(1);
																					emptySub.setCellValue("");
																					emptySub.setCellStyle(activityStyle);
																					
																					HSSFCell reportingperiod = headerRow.createCell(2);
																					reportingperiod.setCellValue("   "+listReportingPeriods.getYear() +" - "+listReportingPeriods.getName());
																					reportingperiod.setCellStyle(activityStyle);
																					
																					HSSFCell statusReporting = headerRow.createCell(3);
																					statusReporting.setCellStyle(statusColor);
																					
																					HSSFCell comPercentReporting = headerRow.createCell(4);
																					comPercentReporting.setCellValue(Integer.parseInt(statusTracking.getActualStatusPercentage()));
																					comPercentReporting.setCellStyle(activityStyle);
																					
																					HSSFCell keyProgressReporting = headerRow.createCell(5);
																					keyProgressReporting.setCellValue(statusTracking.getKeyProgress());
																					keyProgressReporting.setCellStyle(activityStyle);
																					
																					HSSFCell reasonReporting = headerRow.createCell(6);
																					reasonReporting.setCellValue(statusTracking.getReasonForGap());
																					reasonReporting.setCellStyle(activityStyle);
																					
																					HSSFCell rectifyReporting = headerRow.createCell(7);
																					rectifyReporting.setCellValue(statusTracking.getRectifyTheGap());
																					rectifyReporting.setCellStyle(activityStyle);
																					
																					HSSFCell feedbackReporting = headerRow.createCell(8);
																					feedbackReporting.setCellValue(statusTracking.getReviewerFeedback());
																					feedbackReporting.setCellStyle(activityStyle);
																					
																					HSSFCell updatedByValueAgency2 = headerRow.createCell(9);
																					String updatedByname1 = "";
																					if(statusTracking.getUser() != null){
																						if(statusTracking.getUser().getFirstName() != null){
																							updatedByname1 = statusTracking.getUser().getFirstName();
																						}
																						if(statusTracking.getUser().getLastName() != null){
																							updatedByname1 += " "+ statusTracking.getUser().getLastName();
																						}
																						updatedByname1 += " (" + statusTracking.getAgency().getName() + ")";
																						updatedByValueAgency2.setCellValue(updatedByname1);
																					}else{
																						updatedByValueAgency2.setCellValue("");
																					}
																					updatedByValueAgency2.setCellStyle(activityStyle);
																					
																					HSSFCell lastUpdatedOnValue2 = headerRow.createCell(10);
																					if(statusTracking.getModificationTime() != null){
																						SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
																						try {
																							Date lastUpdatedOn = statusTracking.getModificationTime();
																							lastUpdatedOnValue2.setCellValue(dateformat.format(lastUpdatedOn));
																						} catch (Exception e) {
																							e.printStackTrace();
																						}
																						
																					}else{
																						lastUpdatedOnValue2.setCellValue("");
																					}
																					lastUpdatedOnValue2.setCellStyle(activityStyle);
																					
																					HSSFCell reviewedByValueReporting = headerRow.createCell(11);
																					if(statusTracking.getReviewedBy() != null){
																						reviewedByValueReporting.setCellValue(statusTracking.getReviewedBy().getFirstName()+" "+statusTracking.getReviewedBy().getLastName());
																					}else{
																						reviewedByValueReporting.setCellValue("");
																					}
																					reviewedByValueReporting.setCellStyle(activityStyle);

																					HSSFCell reviewedTimeValueReporting = headerRow.createCell(12);
																					if(statusTracking.getReviewedOn() != null && statusTracking.getReviewedOn() != ""){
																						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
																						SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
																						String reviewOn = statusTracking.getReviewedOn();
																						try {
																							Date reviewDate = sdf.parse(reviewOn);
																							reviewedTimeValueReporting.setCellValue(dateformat.format(reviewDate));
																						} catch (ParseException e) {
																							e.printStackTrace();
																						}
																						
																					}else{
																						reviewedTimeValueReporting.setCellValue("");
																					}
																					reviewedTimeValueReporting.setCellStyle(activityStyle);
																					
																					HSSFCell subActReviewedStatus= headerRow.createCell(13);
																					String subActReviewedData="";
																															
																					if(statusTracking.getUserLevel() !=  null && statusTracking.getUserLevel().equals(1)){
																						if(statusTracking.isComplete() && statusTracking.getReviewStatus().equals(1) && statusTracking.isSentForReview()){
																							subActReviewedData="Approved";
																						}else if(statusTracking.isComplete() && statusTracking.getReviewStatus().equals(-1) && statusTracking.isSentForReview()){
																							subActReviewedData="Need More Information";
																						}else {
																							subActReviewedData="";
																						}
																					}else if(statusTracking.getUserLevel() !=  null && statusTracking.getUserLevel().equals(2)){
																						if(statusTracking.isComplete() && statusTracking.getReviewStatus().equals(0) && !statusTracking.isSentForReview()){
																							subActReviewedData="Ready For Approval";
																						}else if(!statusTracking.isComplete() && statusTracking.getReviewStatus().equals(0) && !statusTracking.isSentForReview()){
																							subActReviewedData="Saved";
																						}else if(statusTracking.isComplete() && statusTracking.getReviewStatus().equals(0) && statusTracking.isSentForReview()){
																							subActReviewedData="Submitted For Approval";
																						} else if(statusTracking.isComplete() && statusTracking.getReviewStatus().equals(1) && statusTracking.isSentForReview()){
																							subActReviewedData="Approved";
																						}else if(statusTracking.isComplete() && statusTracking.getReviewStatus().equals(-1) && statusTracking.isSentForReview()){
																							subActReviewedData="Need More Information";
																						}else {
																							subActReviewedData="";
																						}
																					}else{
																							subActReviewedData="";
																				}
																															
																			subActReviewedStatus.setCellValue(subActReviewedData);
																			subActReviewedStatus.setCellStyle(activityStyle);
																					
																					temp = 1;
																					for (Integer listPartnerAgencies : partneringAgencySet) {
																						if(listPartnerAgencies != -1){
																							Agency agency = agencyService.findByAgencyId(listPartnerAgencies);
																									List<User> users1 = agency.getAgencyAuthority();
																									if(users1.size() > 0){
																										for (User user3 : users1) {
																											StatusTracking statusTracking1 = statusTrackingRepository.findByUserAndSubActivityAndReportingPeriodAndUserLevel(user3, subActivity2, listReportingPeriods, 1);
																												if(statusTracking1 != null){
																													rowValue++;
																													headerRow = hssfSheet.createRow(rowValue);
																													HSSFCell cell0 = headerRow.createCell(0);
																													cell0.setCellStyle(activityStyle);
																													HSSFCell cell1 = headerRow.createCell(1);
																													cell1.setCellStyle(activityStyle);
																													HSSFCell userAgency = headerRow.createCell(2);
																													userAgency.setCellValue("       By  "+agency.getName()+" (" +user3.getFirstName() +" "+ user3.getLastName()+")");
																													userAgency.setCellStyle(agencystyle);
																															
																													HSSFCellStyle statusColorAgency=hssfWorkbook.createCellStyle();
																													int ac11= 0;
																													int ac22=0;
																													int ac33=0;
																													if(statusTracking1.getActualStatusColor() != null){
																														String[] staColorAgency=hex2Rgb(statusTracking1.getActualStatusColor()).split(",");
																														ac11=Integer.parseInt(staColorAgency[0]);
														 																ac22=Integer.parseInt(staColorAgency[1]);
																														ac33=Integer.parseInt(staColorAgency[2]);
																														
																														HSSFPalette palette1 = hssfWorkbook.getCustomPalette();
																														HSSFColor myColor1 = palette1.findSimilarColor(ac11, ac22, ac33);
																														short palIndex1 = myColor1.getIndex();
																														statusColorAgency.setFillForegroundColor(palIndex1);
																														statusColorAgency.setFillPattern(CellStyle.SOLID_FOREGROUND);
																															
																														HSSFCell statusAgency = headerRow.createCell(3);
																														statusAgency.setCellStyle(statusColorAgency);
																														
																														HSSFCell percentageAgency = headerRow.createCell(4);
																														percentageAgency.setCellValue(Integer.parseInt(statusTracking1.getActualStatusPercentage()));
																														percentageAgency.setCellStyle(activityStyle);
																													}else{
																														HSSFCell statusAgency = headerRow.createCell(3);
																														statusAgency.setCellValue("");
																														statusAgency.setCellStyle(statusColor);
																														
																														HSSFCell percentageAgency = headerRow.createCell(4);
																														percentageAgency.setCellValue(Integer.parseInt(statusTracking1.getActualStatusPercentage()));
																														percentageAgency.setCellStyle(activityStyle);
																													}
																														
																													HSSFCell keyProgressAgency = headerRow.createCell(5);
																													keyProgressAgency.setCellValue(statusTracking1.getKeyProgress());
																													keyProgressAgency.setCellStyle(activityStyle);
																													
																													HSSFCell reasons = headerRow.createCell(6);
																													reasons.setCellValue(statusTracking1.getReasonForGap());
																													reasons.setCellStyle(activityStyle);
																													
																													HSSFCell rectifi = headerRow.createCell(7);
																													rectifi.setCellValue(statusTracking1.getRectifyTheGap());
																													rectifi.setCellStyle(activityStyle);
																													
																													HSSFCell feedbackAgency = headerRow.createCell(8);
																													feedbackAgency.setCellValue(statusTracking1.getReviewerFeedback());
																													feedbackAgency.setCellStyle(activityStyle);
																													
																													HSSFCell updatedByValueAgency1 = headerRow.createCell(9);
																													String updatedByname2 = "";
																													if(statusTracking.getUser() != null){
																														if(statusTracking.getUser().getFirstName() != null){
																															updatedByname2 = statusTracking.getUser().getFirstName();
																														}
																														if(statusTracking.getUser().getLastName() != null){
																															updatedByname2 += " "+ statusTracking.getUser().getLastName();
																														}
																														updatedByname2 += " (" + statusTracking.getAgency().getName() + ")";
																														updatedByValueAgency1.setCellValue(updatedByname2);
																													}else{
																														updatedByValueAgency1.setCellValue("");
																													}
																													updatedByValueAgency1.setCellStyle(activityStyle);
																													
																													HSSFCell lastUpdatedOnValue1 = headerRow.createCell(10);
																													if(statusTracking.getModificationTime() != null){
																														SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
																														try {
																															Date lastUpdatedOn = statusTracking.getModificationTime();
																															lastUpdatedOnValue1.setCellValue(dateformat.format(lastUpdatedOn));
																														} catch (Exception e) {
																															e.printStackTrace();
																														}
																														
																													}else{
																														lastUpdatedOnValue1.setCellValue("");
																													}
																													lastUpdatedOnValue1.setCellStyle(activityStyle);
																													
																													HSSFCell reviewedByValueAgency = headerRow.createCell(11);
																													String name = "";
																													if(statusTracking1.getReviewedBy() != null){
																														if(statusTracking1.getReviewedBy().getFirstName() != null){
																															name = statusTracking1.getReviewedBy().getFirstName();
																														}
																														if(statusTracking1.getReviewedBy().getLastName() != null){
																															name += " "+ statusTracking1.getReviewedBy().getLastName();
																														}
																														reviewedByValueAgency.setCellValue(name);
																													}else{
																														reviewedByValueAgency.setCellValue("");
																													}
																													reviewedByValueAgency.setCellStyle(activityStyle);
																													
																													
								
																													HSSFCell reviewedTimeValueAgency = headerRow.createCell(12);
																													if(statusTracking1.getReviewedOn() != "" && statusTracking1.getReviewedOn() != null){
																														SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
																														SimpleDateFormat dsf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
																														Date date2 = null;
																														String reviewTime = statusTracking1.getReviewedOn();
																														try {
																															date2 = sdf.parse(reviewTime);
																															reviewedTimeValueAgency.setCellValue(dsf.format(date2));
																														} catch (ParseException e) {
																															e.printStackTrace();
																														}
																													}else{
																														reviewedTimeValueAgency.setCellValue("");
																													}
																													reviewedTimeValueAgency.setCellStyle(activityStyle);
																													
																													HSSFCell subActReviewedStatus1= headerRow.createCell(13);
																													String subActReviewedData1="";
																																							
																													if(statusTracking1.getUserLevel() !=  null && statusTracking1.getUserLevel().equals(1)){
																														if(statusTracking1.isComplete() && statusTracking1.getReviewStatus().equals(1) && subActivityStatusTracking.isSentForReview()){
																															subActReviewedData1="Approved";
																														}else if(statusTracking1.isComplete() && statusTracking1.getReviewStatus().equals(-1) && subActivityStatusTracking.isSentForReview()){
																															subActReviewedData1="Need More Information";
																														}else {
																															subActReviewedData1="";
																														}
																													}else if(statusTracking1.getUserLevel() !=  null && statusTracking1.getUserLevel().equals(2)){
																														if(statusTracking1.isComplete() && statusTracking1.getReviewStatus().equals(0) && !statusTracking1.isSentForReview()){
																															subActReviewedData1="Ready For Approval";
																														}else if(!statusTracking1.isComplete() && statusTracking1.getReviewStatus().equals(0) && !statusTracking1.isSentForReview()){
																															subActReviewedData1="Saved";
																														}else if(statusTracking1.isComplete() && statusTracking1.getReviewStatus().equals(0) && statusTracking1.isSentForReview()){
																															subActReviewedData1="Submitted For Approval";
																														} else if(statusTracking1.isComplete() && statusTracking1.getReviewStatus().equals(1) && statusTracking1.isSentForReview()){
																															subActReviewedData1="Approved";
																														}else if(statusTracking1.isComplete() && statusTracking1.getReviewStatus().equals(-1) && statusTracking1.isSentForReview()){
																															subActReviewedData1="Need More Information";
																														}else {
																															subActReviewedData1="";
																														}
																													}else{
																															subActReviewedData1="";
																												}
																																							
																											subActReviewedStatus1.setCellValue(subActReviewedData1);
																											subActReviewedStatus1.setCellStyle(activityStyle);
																													
																													temp = 1;
																											}
																											if(temp == 0){
																												HSSFCell status3 = headerRow.createCell(3);
																												status3.setCellValue("");
																												status3.setCellStyle(activityStyle);
																												
																												HSSFCell percentage3 = headerRow.createCell(4);
																												percentage3.setCellValue("");
																												percentage3.setCellStyle(activityStyle);
																												
																												HSSFCell keyProgress3 = headerRow.createCell(5);
																												keyProgress3.setCellValue("");
																												keyProgress3.setCellStyle(activityStyle);
																												
																												HSSFCell reason3 = headerRow.createCell(6);
																												reason3.setCellValue("");
																												reason3.setCellStyle(activityStyle);
																												
																												HSSFCell rectify3 = headerRow.createCell(7);
																												rectify3.setCellValue("");
																												rectify3.setCellStyle(activityStyle);
																												
																												HSSFCell feedBack3 = headerRow.createCell(8);
																												feedBack3.setCellValue("");
																												feedBack3.setCellStyle(activityStyle);
																												
																												HSSFCell updatedBy = headerRow.createCell(9);
																												updatedBy.setCellValue("");
																												updatedBy.setCellStyle(activityStyle);
																												
																												HSSFCell updatedTime = headerRow.createCell(10);
																												updatedTime.setCellValue("");
																												updatedTime.setCellStyle(activityStyle);
																												
																												HSSFCell reviewedBy = headerRow.createCell(11);
																												reviewedBy.setCellValue("");
																												reviewedBy.setCellStyle(activityStyle);
							
																												HSSFCell reviewedTime = headerRow.createCell(12);
																												reviewedTime.setCellValue("");
																												reviewedTime.setCellStyle(activityStyle);
																												
																												
																												HSSFCell reviewdActStatus=headerRow.createCell(13);
																												reviewdActStatus.setCellValue("");
																												reviewdActStatus.setCellStyle(activityStyle);
																											}
																									}
																							}
																						}
																					}
																				}else{
																					headerRow = hssfSheet.createRow(rowValue);
																					
																					HSSFCell emptyKey1 = headerRow.createCell(0);
																					emptyKey1.setCellValue(" ");
																					emptyKey1.setCellStyle(activityStyle);
																					
																					HSSFCell emptySub1 = headerRow.createCell(1);
																					emptySub1.setCellValue("");
																					emptySub1.setCellStyle(activityStyle);
																					
																					HSSFCell reportingPeriodValue = headerRow.createCell(2);
																					reportingPeriodValue.setCellValue("   "+listReportingPeriods.getYear()+" - "+listReportingPeriods.getName());
																					reportingPeriodValue.setCellStyle(activityStyle);
																					
																					HSSFCell statusReporting2 = headerRow.createCell(3);
																					statusReporting2.setCellStyle(statusColor);
																					
																					HSSFCell comPercentReporting2 = headerRow.createCell(4);
																					comPercentReporting2.setCellValue(Integer.parseInt(statusTracking.getActualStatusPercentage()));
																					comPercentReporting2.setCellStyle(activityStyle);
																					
																					HSSFCell keyProgressReporting2 = headerRow.createCell(5);
																					keyProgressReporting2.setCellValue(statusTracking.getKeyProgress());
																					keyProgressReporting2.setCellStyle(activityStyle);
																					
																					HSSFCell reasonReporting1 = headerRow.createCell(6);
																					reasonReporting1.setCellValue(statusTracking.getReasonForGap());
																					reasonReporting1.setCellStyle(activityStyle);
																					
																					HSSFCell rectifyReporting1 = headerRow.createCell(7);
																					rectifyReporting1.setCellValue(statusTracking.getRectifyTheGap());
																					rectifyReporting1.setCellStyle(activityStyle);
																					
																					HSSFCell feedbackReporting1 = headerRow.createCell(8);
																					feedbackReporting1.setCellValue(statusTracking.getReviewerFeedback());
																					feedbackReporting1.setCellStyle(activityStyle);
																					
																					HSSFCell updatedByValueAgency1 = headerRow.createCell(9);
																					String updatedByname1 = "";
																					if(statusTracking.getUser() != null){
																						if(statusTracking.getUser().getFirstName() != null){
																							updatedByname1 = statusTracking.getUser().getFirstName();
																						}
																						if(statusTracking.getUser().getLastName() != null){
																							updatedByname1 += " "+ statusTracking.getUser().getLastName();
																						}
																						updatedByname1 += " (" + statusTracking.getAgency().getName() + ")";
																						updatedByValueAgency1.setCellValue(updatedByname1);
																					}else{
																						updatedByValueAgency1.setCellValue("");
																					}
																					updatedByValueAgency1.setCellStyle(activityStyle);
																					
																					HSSFCell lastUpdatedOnValue1 = headerRow.createCell(10);
																					if(statusTracking.getModificationTime() != null){
																						SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
																						try {
																							Date lastUpdatedOn = statusTracking.getModificationTime();
																							lastUpdatedOnValue1.setCellValue(dateformat.format(lastUpdatedOn));
																						} catch (Exception e) {
																							e.printStackTrace();
																						}
																						
																					}else{
																						lastUpdatedOnValue1.setCellValue("");
																					}
																					lastUpdatedOnValue1.setCellStyle(activityStyle);
																					
																					HSSFCell reviewedByValueReporting1 = headerRow.createCell(11);
																					if(statusTracking.getReviewedBy() != null){
																						reviewedByValueReporting1.setCellValue(statusTracking.getReviewedBy().getFirstName()+" "+statusTracking.getReviewedBy().getLastName());
																					}else{
																						reviewedByValueReporting1.setCellValue("");
																					}
																					reviewedByValueReporting1.setCellStyle(activityStyle);

																					HSSFCell reviewedTimeValueReporting1 = headerRow.createCell(12);
																					if(statusTracking.getReviewedOn() != null && statusTracking.getReviewedOn() != ""){
																						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
																						SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
																						String reviewOn = statusTracking.getReviewedOn();
																						try {
																							Date reviewDate = sdf.parse(reviewOn);
																							reviewedTimeValueReporting1.setCellValue(dateformat.format(reviewDate));
																						} catch (ParseException e) {
																							e.printStackTrace();
																						}
																						
																					}else{
																						reviewedTimeValueReporting1.setCellValue("");
																					}
																					reviewedTimeValueReporting1.setCellStyle(activityStyle);
																					
																					HSSFCell subActReviewedStatus= headerRow.createCell(13);
																					String subActReviewedData="";
																															
																					if(statusTracking.getUserLevel() !=  null && statusTracking.getUserLevel().equals(1)){
																						if(statusTracking.isComplete() && statusTracking.getReviewStatus().equals(1) && statusTracking.isSentForReview()){
																							subActReviewedData="Approved";
																						}else if(statusTracking.isComplete() && statusTracking.getReviewStatus().equals(-1) && statusTracking.isSentForReview()){
																							subActReviewedData="Need More Information";
																						}else {
																							subActReviewedData="";
																						}
																					}else if(statusTracking.getUserLevel() !=  null && statusTracking.getUserLevel().equals(2)){
																						if(statusTracking.isComplete() && statusTracking.getReviewStatus().equals(0) && !statusTracking.isSentForReview()){
																							subActReviewedData="Ready For Approval";
																						}else if(!statusTracking.isComplete() && statusTracking.getReviewStatus().equals(0) && !statusTracking.isSentForReview()){
																							subActReviewedData="Saved";
																						}else if(statusTracking.isComplete() && statusTracking.getReviewStatus().equals(0) && statusTracking.isSentForReview()){
																							subActReviewedData="Submitted For Approval";
																						} else if(statusTracking.isComplete() && statusTracking.getReviewStatus().equals(1) && statusTracking.isSentForReview()){
																							subActReviewedData="Approved";
																						}else if(statusTracking.isComplete() && statusTracking.getReviewStatus().equals(-1) && statusTracking.isSentForReview()){
																							subActReviewedData="Need More Information";
																						}else {
																							subActReviewedData="";
																						}
																					}else{
																							subActReviewedData="";
																				}
																															
																			subActReviewedStatus.setCellValue(subActReviewedData);
																			subActReviewedStatus.setCellStyle(activityStyle);
																					
																					temp = 1;
																					for (Integer listPartnerAgencies : partneringAgencySet) {
																						if(listPartnerAgencies != -1){

																							Agency agency = agencyService.findByAgencyId(listPartnerAgencies);
																									List<User> users1 = agency.getAgencyAuthority();
																									if(users1.size() > 0){
																										for (User user3 : users1) {
																											StatusTracking statusTracking1 = statusTrackingRepository.findByUserAndSubActivityAndReportingPeriodAndUserLevel(user3, subActivity2, listReportingPeriods, 1);
																												if(statusTracking1 != null){
																													rowValue++;
																													headerRow = hssfSheet.createRow(rowValue);
																													HSSFCell cell0 = headerRow.createCell(0);
																													cell0.setCellStyle(activityStyle);
																													HSSFCell cell1 = headerRow.createCell(1);
																													cell1.setCellStyle(activityStyle);
																													HSSFCell userAgency = headerRow.createCell(2);
																													userAgency.setCellValue("     By  "+agency.getName()+" (" +user3.getFirstName() +" "+ user3.getLastName()+")");
																													userAgency.setCellStyle(agencystyle);
																															
																													HSSFCellStyle statusColorAgency=hssfWorkbook.createCellStyle();
																													int ac11= 0;
																													int ac22=0;
																													int ac33=0;
																													if(statusTracking1.getActualStatusColor() != null){
																														String[] staColorAgency=hex2Rgb(statusTracking1.getActualStatusColor()).split(",");
																														ac11=Integer.parseInt(staColorAgency[0]);
														 																ac22=Integer.parseInt(staColorAgency[1]);
																														ac33=Integer.parseInt(staColorAgency[2]);
																														
																														HSSFPalette paletteAgency = hssfWorkbook.getCustomPalette();
																														HSSFColor myColorAgency = paletteAgency.findSimilarColor(ac11, ac22, ac33);
																														short palIndexAgency = myColorAgency.getIndex();
																														statusColorAgency.setFillForegroundColor(palIndexAgency);
																														statusColorAgency.setFillPattern(CellStyle.SOLID_FOREGROUND);
																															
																														HSSFCell statusAgency = headerRow.createCell(3);
																														statusAgency.setCellStyle(statusColorAgency);
																														
																														HSSFCell percentageAgency = headerRow.createCell(4);
																														percentageAgency.setCellValue(Integer.parseInt(statusTracking1.getActualStatusPercentage()));
																														percentageAgency.setCellStyle(activityStyle);
																													}else{
																														HSSFCell statusAgency = headerRow.createCell(3);
																														statusAgency.setCellValue("");
																														statusAgency.setCellStyle(statusColor);
																														
																														HSSFCell percentageAgency = headerRow.createCell(4);
																														percentageAgency.setCellValue(Integer.parseInt(statusTracking1.getActualStatusPercentage()));
																														percentageAgency.setCellStyle(activityStyle);
																													}
																														
																													HSSFCell keyProgressAgency = headerRow.createCell(5);
																													keyProgressAgency.setCellValue(statusTracking1.getKeyProgress());
																													keyProgressAgency.setCellStyle(activityStyle);
																													
																													HSSFCell reasons = headerRow.createCell(6);
																													reasons.setCellValue(statusTracking1.getReasonForGap());
																													reasons.setCellStyle(activityStyle);
																													
																													HSSFCell rectifi = headerRow.createCell(7);
																													rectifi.setCellValue(statusTracking1.getRectifyTheGap());
																													rectifi.setCellStyle(activityStyle);
																													
																													HSSFCell feedbackAgency = headerRow.createCell(8);
																													feedbackAgency.setCellValue(statusTracking1.getReviewerFeedback());
																													feedbackAgency.setCellStyle(activityStyle);
																													
																													HSSFCell updatedByValueAgency = headerRow.createCell(9);
																													String updatedByname = "";
																													if(statusTracking1.getUser() != null){
																														if(statusTracking1.getUser().getFirstName() != null){
																															updatedByname = statusTracking1.getUser().getFirstName();
																														}
																														if(statusTracking1.getUser().getLastName() != null){
																															updatedByname += " "+ statusTracking1.getUser().getLastName();
																														}
																														updatedByname += " (" + statusTracking.getAgency().getName() + ")";
																														updatedByValueAgency.setCellValue(updatedByname);
																													}else{
																														updatedByValueAgency.setCellValue("");
																													}
																													updatedByValueAgency.setCellStyle(activityStyle);
																													
																													HSSFCell lastUpdatedOnValue = headerRow.createCell(10);
																													if(statusTracking.getModificationTime() != null){
																														SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
																														try {
																															Date lastUpdatedOn = statusTracking.getModificationTime();
																															lastUpdatedOnValue.setCellValue(dateformat.format(lastUpdatedOn));
																														} catch (Exception e) {
																															e.printStackTrace();
																														}
																														
																													}else{
																														lastUpdatedOnValue.setCellValue("");
																													}
																													lastUpdatedOnValue.setCellStyle(activityStyle);
																													
																													HSSFCell reviewedByValueAgency = headerRow.createCell(11);
																													String name = "";
																													if(statusTracking1.getReviewedBy() != null){
																														if(statusTracking1.getReviewedBy().getFirstName() != null){
																															name = statusTracking1.getReviewedBy().getFirstName();
																														}
																														if(statusTracking1.getReviewedBy().getLastName() != null){
																															name += " "+ statusTracking1.getReviewedBy().getLastName();
																														}
																														reviewedByValueAgency.setCellValue(name);
																													}else{
																														reviewedByValueAgency.setCellValue("");
																													}
																													reviewedByValueAgency.setCellStyle(activityStyle);
																													
																													
								
																													HSSFCell reviewedTimeValueAgency = headerRow.createCell(12);
																													if(statusTracking1.getReviewedOn() != "" && statusTracking1.getReviewedOn() != null){
																														SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
																														SimpleDateFormat dsf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
																														Date date2 = null;
																														String reviewTime = statusTracking1.getReviewedOn();
																														try {
																															date2 = sdf.parse(reviewTime);
																															reviewedTimeValueAgency.setCellValue(dsf.format(date2));
																														} catch (ParseException e) {
																															e.printStackTrace();
																														}
																													}else{
																														reviewedTimeValueAgency.setCellValue("");
																													}
																													reviewedTimeValueAgency.setCellStyle(activityStyle);
																													
																													HSSFCell subActReviewedStatus1= headerRow.createCell(13);
																													String subActReviewedData1="";
																																							
																													if(statusTracking1.getUserLevel() !=  null && statusTracking1.getUserLevel().equals(1)){
																														if(statusTracking1.isComplete() && statusTracking1.getReviewStatus().equals(1) && statusTracking1.isSentForReview()){
																															subActReviewedData1="Approved";
																														}else if(statusTracking1.isComplete() && statusTracking1.getReviewStatus().equals(-1) && statusTracking1.isSentForReview()){
																															subActReviewedData1="Need More Information";
																														}else {
																															subActReviewedData1="";
																														}
																													}else if(statusTracking1.getUserLevel() !=  null && statusTracking1.getUserLevel().equals(2)){
																														if(statusTracking1.isComplete() && statusTracking1.getReviewStatus().equals(0) && !statusTracking1.isSentForReview()){
																															subActReviewedData1="Ready For Approval";
																														}else if(!statusTracking1.isComplete() && statusTracking1.getReviewStatus().equals(0) && !statusTracking1.isSentForReview()){
																															subActReviewedData1="Saved";
																														}else if(statusTracking1.isComplete() && statusTracking1.getReviewStatus().equals(0) && statusTracking1.isSentForReview()){
																															subActReviewedData1="Submitted For Approval";
																														} else if(statusTracking1.isComplete() && statusTracking1.getReviewStatus().equals(1) && statusTracking1.isSentForReview()){
																															subActReviewedData1="Approved";
																														}else if(statusTracking1.isComplete() && statusTracking1.getReviewStatus().equals(-1) && statusTracking1.isSentForReview()){
																															subActReviewedData1="Need More Information";
																														}else {
																															subActReviewedData1="";
																														}
																													}else{
																															subActReviewedData1="";
																												}
																																							
																											subActReviewedStatus1.setCellValue(subActReviewedData1);
																											subActReviewedStatus1.setCellStyle(activityStyle);
																													
																													
																													temp = 1;
																											}
																											if(temp == 0){
																												HSSFCell status3 = headerRow.createCell(3);
																												status3.setCellValue("");
																												status3.setCellStyle(activityStyle);
																												
																												HSSFCell percentage3 = headerRow.createCell(4);
																												percentage3.setCellValue("");
																												percentage3.setCellStyle(activityStyle);
																												
																												HSSFCell keyProgress3 = headerRow.createCell(5);
																												keyProgress3.setCellValue("");
																												keyProgress3.setCellStyle(activityStyle);
																												
																												HSSFCell reason3 = headerRow.createCell(6);
																												reason3.setCellValue("");
																												reason3.setCellStyle(activityStyle);
																												
																												HSSFCell rectify3 = headerRow.createCell(7);
																												rectify3.setCellValue("");
																												rectify3.setCellStyle(activityStyle);
																												
																												HSSFCell feedBack3 = headerRow.createCell(8);
																												feedBack3.setCellValue("");
																												feedBack3.setCellStyle(activityStyle);
																												
																												HSSFCell updatedBy = headerRow.createCell(9);
																												updatedBy.setCellValue("");
																												updatedBy.setCellStyle(activityStyle);
																												
																												HSSFCell updatedTime = headerRow.createCell(10);
																												updatedTime.setCellValue("");
																												updatedTime.setCellStyle(activityStyle);
																												
																												HSSFCell reviewedBy = headerRow.createCell(11);
																												reviewedBy.setCellValue("");
																												reviewedBy.setCellStyle(activityStyle);
							
																												HSSFCell reviewedTime = headerRow.createCell(12);
																												reviewedTime.setCellValue("");
																												reviewedTime.setCellStyle(activityStyle);
																												
																												HSSFCell reviewedActStatus = headerRow.createCell(13);
																												reviewedActStatus.setCellValue("");
																												reviewedActStatus.setCellStyle(activityStyle);
							
																											}
																									}
																							}
																						}
																					}
																				}
																				rowValue++;
																			}
																			if(temp == 0){
																				HSSFCell status2 = headerRow.createCell(3);
																				status2.setCellStyle(activityStyle);
																				
																				HSSFCell keyProgress2 = headerRow.createCell(4);
																				keyProgress2.setCellStyle(activityStyle);
																				
																				HSSFCell reason1 = headerRow.createCell(5);
																				reason1.setCellStyle(activityStyle);
																				
																				HSSFCell rectify1 = headerRow.createCell(6);
																				rectify1.setCellStyle(activityStyle);
																				
																				HSSFCell feedback2 = headerRow.createCell(7);
																				feedback2.setCellStyle(activityStyle);
																				
																				HSSFCell updatedBy = headerRow.createCell(8);
																				updatedBy.setCellStyle(activityStyle);
																				
																				HSSFCell updatedTime = headerRow.createCell(9);
																				updatedTime.setCellStyle(activityStyle);
																				
																				HSSFCell reviewedBy = headerRow.createCell(10);
																				reviewedBy.setCellValue("");
																				reviewedBy.setCellStyle(activityStyle);

																				HSSFCell reviewedTime = headerRow.createCell(11);
																				reviewedTime.setCellValue("");
																				reviewedTime.setCellStyle(activityStyle);
																				
																				HSSFCell empty = headerRow.createCell(12);
																				empty.setCellValue("");
																				empty.setCellStyle(activityStyle);
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
							}
						}
					}
				}
			}
		}
	}
		
	hssfSheet.autoSizeColumn(0);
	hssfSheet.autoSizeColumn(4);
	hssfSheet.autoSizeColumn(5);
	hssfSheet.autoSizeColumn(6);
	hssfSheet.autoSizeColumn(7);
	hssfSheet.autoSizeColumn(8);
	hssfSheet.autoSizeColumn(9);
	hssfSheet.autoSizeColumn(10);
	hssfSheet.autoSizeColumn(11);
	hssfSheet.autoSizeColumn(12);
	return hssfWorkbook;
}
	
	@RequestMapping(value = "getReportPartnerAgency" , method = RequestMethod.GET)
	public @ResponseBody Set<Agency> getReportPartnerAgency(HttpServletRequest request, 
			HttpServletResponse httpServletResponse , @RequestParam Integer reportingPeriodId,
			 HttpServletResponse response) throws IOException{
		Agency agency = agencyService.findByLoginUser(PrincipalUtil.getPrincipal());
		Set<Agency> agencies = new HashSet<Agency>();
		if(reportingPeriodId != 0){
			List<SubActivity> activities = subActivityService.getSubActivityByReportingPeriodIdAndAgencyId(reportingPeriodId, agency.getId());
			if(activities.size() > 0){
			for (SubActivity subActivity : activities) {
				List<Agency> agency2 = subActivity.getPartnerAgency();
				for (Agency agency3 : agency2) {
					agencies.add(agency3);
				}
			}
			}
		}
		return agencies;
	}
	
	
	/**
	 * Approver Report Download
	 * @param request
	 * @param httpServletResponse
	 * @param reportingPeriodId
	 * @param spId
	 * @param themeId
	 * @param outcomeId
	 * @param outputId
	 * @param partnerAgencyId
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "approverActualReportDownload" , method = RequestMethod.GET)
	public @ResponseBody String approverActualReportDownload(HttpServletRequest request, 
			HttpServletResponse httpServletResponse ,@RequestParam("year") String year, @RequestParam("reportingPeriodId") String reportingPeriodId,
			@RequestParam("spId") String spId , @RequestParam("themeId") String themeId,
			@RequestParam("outcomeId") String outcomeId , @RequestParam("outputId") String outputId , 
			HttpServletResponse response) throws IOException{
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE) || principalUtil.getCurrentUserRole().equals(ConstantUtil.PROJECTADMIN_ROLE) || 
				principalUtil.getCurrentUserRole().equals(ConstantUtil.PROJECT_PLANNER) || principalUtil.getCurrentUserRole().equals(ConstantUtil.STATUS_APPROVER)){
			User user = userService.findByUsername(PrincipalUtil.getPrincipal());
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
			
			HSSFWorkbook approverHssfWorkbook = new HSSFWorkbook();
			
			StringBuilder approverReportSPQuery = new StringBuilder();
			StringBuilder approverReportThemesQuery = new StringBuilder();
			StringBuilder approverReportOutcomesQuery = new StringBuilder();
			StringBuilder approverReportOutputsQuery = new StringBuilder();
			StringBuilder approverReportKeyActivityQuery = new StringBuilder();
			StringBuilder approverReportSubActivityQuery = new StringBuilder();

			String reportingPeriodQueryConditonString = "";
			String strategicPillerQueryConditonString = "";
			String themeQueryConditionString = "";
			String outcomeQueryCondtionString = "";
			String outputQueryConditionString = "";
			
			String reportingPeriodLength = reportingPeriodId;
			List<ReportingPeriod> listReportingPeriods = new ArrayList<ReportingPeriod>();
			List<StrategicPillar> listStrategicPillers = new ArrayList<StrategicPillar>();
			List<Theme> listThemes = new ArrayList<Theme>();
			List<Outcome> listOutcomes = new ArrayList<Outcome>();
			List<Output> listOutputs = new ArrayList<Output>();
			
			if(reportingPeriodId != null && !reportingPeriodId.isEmpty()){
				if(reportingPeriodId.startsWith("0")){
					if(!year.equals("0")){
						List<String> yearsStringArray = Arrays.asList(year);
						listReportingPeriods = reportingPeriodService.findAllReportingPeriodByYears(yearsStringArray);
					}else{
						listReportingPeriods = reportingPeriodService.getAllStatusOpenAndClosed();
					}
				}else{
					String[] reportingPeriodStringArray = reportingPeriodId.split(",");
					for(int i = 0 ; i < reportingPeriodStringArray.length ; i++){
						ReportingPeriod reportingPeriod = reportingPeriodService.getById(Integer.parseInt(reportingPeriodStringArray[i]));
						listReportingPeriods.add(reportingPeriod);
						reportingPeriodQueryConditonString = " AND p.reportingPeriod.id IN (" + reportingPeriodId + ") AND p.reportingPeriod.status = 'ACTIVE'";
					}
				}
			}
			
			if(spId != null && !spId.isEmpty()){
				approverReportSPQuery.append("SELECT DISTINCT p.subActivity.keyActivity.output.outcome.theme.strategicPillar FROM Planning p"
						+ " WHERE p.subActivity.status = 'ACTIVE'" + reportingPeriodQueryConditonString );
				if(!spId.startsWith("0")){
					strategicPillerQueryConditonString = " AND p.subActivity.keyActivity.output.outcome.theme.strategicPillar.id IN ( " + spId + ")";
					approverReportSPQuery.append(strategicPillerQueryConditonString);
				}
				approverReportSPQuery.append(" ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber");
				listStrategicPillers = customReportsRepository.getStrategicPillersByReportParameters(approverReportSPQuery.toString());
			}
			
			if(themeId != null && !themeId.isEmpty()){
				approverReportThemesQuery.append("SELECT DISTINCT p.subActivity.keyActivity.output.outcome.theme FROM Planning p"
						+ " WHERE p.subActivity.status = 'ACTIVE'" + reportingPeriodQueryConditonString + strategicPillerQueryConditonString );
				if(!themeId.startsWith("0")){
					themeQueryConditionString = " AND p.subActivity.keyActivity.output.outcome.theme.id IN ( " + themeId + ")";
					approverReportThemesQuery.append(themeQueryConditionString);
				}
				approverReportThemesQuery.append(" ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber");
				listThemes = customReportsRepository.getThemesByReportParameters(approverReportThemesQuery.toString());
			}
			
			if(outcomeId != null && !outcomeId.isEmpty()){
				approverReportOutcomesQuery.append("SELECT DISTINCT p.subActivity.keyActivity.output.outcome FROM Planning p"
						+ " WHERE p.subActivity.status = 'ACTIVE'" + reportingPeriodQueryConditonString 
						+ strategicPillerQueryConditonString + themeQueryConditionString);
				if(!outcomeId.startsWith("0")){
					outcomeQueryCondtionString = " AND p.subActivity.keyActivity.output.outcome.id IN ( " + outcomeId + ")";
					approverReportOutcomesQuery.append(outcomeQueryCondtionString);
				}
				approverReportOutcomesQuery.append(" ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber");
				listOutcomes = customReportsRepository.getOutcomesByReportParameters(approverReportOutcomesQuery.toString());
			}
			
			if(outputId != null && !outputId.isEmpty()){
				approverReportOutputsQuery.append("SELECT DISTINCT p.subActivity.keyActivity.output FROM Planning p"
						+ " WHERE p.subActivity.status = 'ACTIVE'" + reportingPeriodQueryConditonString 
						+ strategicPillerQueryConditonString + themeQueryConditionString + outcomeQueryCondtionString);
				if(!outputId.startsWith("0")){
					outputQueryConditionString = " AND p.subActivity.keyActivity.output.id IN ( " + outputId + ")";
					approverReportOutputsQuery.append(outputQueryConditionString);
				}
				approverReportOutputsQuery.append(" ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber");
				listOutputs = customReportsRepository.getOutputsByReportParameters(approverReportOutputsQuery.toString());
				
			}
			
			approverReportKeyActivityQuery.append("SELECT DISTINCT p.subActivity.keyActivity FROM Planning p WHERE p.subActivity.status = 'ACTIVE'");
			approverReportKeyActivityQuery.append(reportingPeriodQueryConditonString + strategicPillerQueryConditonString + themeQueryConditionString + outcomeQueryCondtionString);
			approverReportKeyActivityQuery.append(" ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber");
			List<KeyActivity> listKeyActivities = customReportsRepository.getKeyActivitiesByReportParameters(approverReportKeyActivityQuery.toString());
			
			approverReportSubActivityQuery.append("SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.status = 'ACTIVE'");
			approverReportSubActivityQuery.append(reportingPeriodQueryConditonString + strategicPillerQueryConditonString + themeQueryConditionString + outcomeQueryCondtionString);
			approverReportSubActivityQuery.append(" ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber");
			List<SubActivity> listSubActivities = customReportsRepository.getSubActivitiesByReportParameters(approverReportSubActivityQuery.toString());
			
//			End Create Report Headers
			approverHssfWorkbook = approverActualReportDownloadCommonFunction(user, listStrategicPillers, listReportingPeriods, listThemes, listOutcomes, listOutputs, listKeyActivities, listSubActivities,reportingPeriodLength);
			
			/**
			 * @author pushpa
			 * Add Observation Sheet 
			 */
			
			HSSFSheet observationSheet = approverHssfWorkbook.createSheet("Observation");
			
			Font boldFont = approverHssfWorkbook.createFont();
			boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
			boldFont.setColor(IndexedColors.BLACK.getIndex());
			
			CellStyle cellStyle = approverHssfWorkbook.createCellStyle();
			cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
			cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
			cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			cellStyle.setBorderRight(CellStyle.BORDER_THIN);
			cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
			cellStyle.setBorderTop(CellStyle.BORDER_THIN);
			cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
			cellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
			cellStyle.setFont(boldFont);

			CellStyle cellStyle2 = approverHssfWorkbook.createCellStyle();
			cellStyle2.setAlignment(CellStyle.ALIGN_LEFT);
			cellStyle2.setBorderBottom(CellStyle.BORDER_THIN);
			cellStyle2.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			cellStyle2.setBorderLeft(CellStyle.BORDER_THIN);
			cellStyle2.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			cellStyle2.setBorderRight(CellStyle.BORDER_THIN);
			cellStyle2.setRightBorderColor(IndexedColors.BLACK.getIndex());
			cellStyle2.setBorderTop(CellStyle.BORDER_THIN);
			cellStyle2.setTopBorderColor(IndexedColors.BLACK.getIndex());
			
//			Read Observation data
			List<SubmitForReview> finalSubmitforReviewList = new ArrayList<SubmitForReview>();
			List<UserRole> userRoles =  user.getUserRoles();
			for (UserRole userRole : userRoles) {
				if(userRole.getName().equals("STATUS_APPROVER") ||
						userRole.getName().equals("SUPER_ADMIN") ||
						userRole.getName().equals("PROJECT_ADMIN") ||
						userRole.getName().equals("PROJECT_PLANNER")){
					for(ReportingPeriod reportPeriod : listReportingPeriods){
						List<SubmitForReview> listSubmitForReviews = submitForReviewService.findByReportingPeriodAndUserLevel(reportPeriod, 2);
						if(listSubmitForReviews != null && listSubmitForReviews.size() > 0){
							finalSubmitforReviewList.addAll(listSubmitForReviews);
						}
					}
				}
			}
			
//			Set row values in excel sheet
			int Startrow = 0;
			for (int i = 0; i < finalSubmitforReviewList.size(); i++) {
				
				SubmitForReview submitForReview = finalSubmitforReviewList.get(i);
				
				HSSFRow userAgencyRow = observationSheet.createRow(Startrow);
				Cell userCell = userAgencyRow.createCell(0);
				String userAgencyName = "By " + submitForReview.getUser().getFirstName();
				if(submitForReview.getUser().getLastName() != null){
					userAgencyName += " " + submitForReview.getUser().getLastName();
				}
				if(submitForReview.getAgency().getName() != null){
					userAgencyName += " ( " + submitForReview.getAgency().getName() + " ) ";
				}
				userCell.setCellValue(userAgencyName);
				userCell.setCellStyle(cellStyle);
				
				Startrow = Startrow + 1;
				HSSFRow keyLearningRow = observationSheet.createRow(Startrow);
				Cell keyLearningCellLabel = keyLearningRow.createCell(1);
				keyLearningCellLabel.setCellValue("Key Learnings");
				keyLearningCellLabel.setCellStyle(cellStyle);
				Cell keyLearningCellValue = keyLearningRow.createCell(2);
				keyLearningCellValue.setCellValue(submitForReview.getKeyLearning());
				keyLearningCellValue.setCellStyle(cellStyle2);
				
				Startrow = Startrow + 1;
				HSSFRow keyChallengesRow = observationSheet.createRow(Startrow);
				Cell keyChallengesCellLabel = keyChallengesRow.createCell(1);
				keyChallengesCellLabel.setCellValue("Key Challanges");
				keyChallengesCellLabel.setCellStyle(cellStyle);
				Cell keyChallengesCellValue = keyChallengesRow.createCell(2);
				keyChallengesCellValue.setCellValue(submitForReview.getKeyChallenge());
				keyChallengesCellValue.setCellStyle(cellStyle2);
				
				Startrow = Startrow + 1;
				HSSFRow bestPracticesRow = observationSheet.createRow(Startrow);
				Cell bestPracticesCellLabel = bestPracticesRow.createCell(1);
				bestPracticesCellLabel.setCellValue("Best Practices");
				bestPracticesCellLabel.setCellStyle(cellStyle);
				Cell bestPracticesCellValue = bestPracticesRow.createCell(2);
				bestPracticesCellValue.setCellValue(submitForReview.getBestPractice());
				bestPracticesCellValue.setCellStyle(cellStyle2);
				
				Startrow = Startrow + 1;
				HSSFRow supportNeedsRow = observationSheet.createRow(Startrow);
				Cell supportNeedsCellLabel = supportNeedsRow.createCell(1);
				supportNeedsCellLabel.setCellValue("Support Needs");
				supportNeedsCellLabel.setCellStyle(cellStyle);
				Cell supportNeedsCellValue = supportNeedsRow.createCell(2);
				supportNeedsCellValue.setCellValue(submitForReview.getSupportNeeds());
				supportNeedsCellValue.setCellStyle(cellStyle2);
				
				Startrow = Startrow + 1;
				HSSFRow submittedTimeRow = observationSheet.createRow(Startrow);
				Cell submittedTimeCellLabel = submittedTimeRow.createCell(1);
				submittedTimeCellLabel.setCellValue("Submitted Time");
				submittedTimeCellLabel.setCellStyle(cellStyle);
				Cell submittedTimeCellValue = submittedTimeRow.createCell(2);
				SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
				Date submittedTime = submitForReview.getSubmit_dateTime();
				String submittedTimeString = (submittedTime != null) ? sd.format(submittedTime) : "";
				submittedTimeCellValue.setCellValue(submittedTimeString);
				submittedTimeCellValue.setCellStyle(cellStyle2);
				}
				observationSheet.autoSizeColumn(0);
				observationSheet.autoSizeColumn(1);
				observationSheet.autoSizeColumn(2);
	//			End Observation Sheet Details creation
				
			try {
				if (approverHssfWorkbook != null) {
					approverHssfWorkbook.write(fileOutputStream);
				}
				if (fileOutputStream != null) {
					fileOutputStream.flush();
					fileOutputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return ConstantUtil.REPORT_PATH + fileName;
		}else{
			return null;
		}
	}
	
	/**
	 * @param request
	 * @param httpServletResponse
	 * @param reportingPeriodId
	 * @param spId
	 * @param themeId
	 * @param outcomeId
	 * @param outputId
	 * @param partnerAgencyId
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "superAdminActualReportDownload" , method = RequestMethod.GET)
	public @ResponseBody String superAdminActualReportDownload(HttpServletRequest request, 
			HttpServletResponse httpServletResponse , @RequestParam("reportingPeriodId") Integer reportingPeriodId,
			@RequestParam("spId") Integer spId , @RequestParam("themeId") Integer themeId,
			@RequestParam("outcomeId") Integer outcomeId , @RequestParam("outputId") Integer outputId , 
			@RequestParam("partnerAgencyId") Integer partnerAgencyId, HttpServletResponse response) throws IOException{
		
		ReportingPeriod reportingPeriodValues = reportingPeriodService.getById(reportingPeriodId);
		User user = userService.findByUsername(PrincipalUtil.getPrincipal());
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
		
		HSSFWorkbook superAdminHssfWorkbook = new HSSFWorkbook();
		HSSFSheet hssfSheet = superAdminHssfWorkbook.createSheet("Actual Report Details");
		//For Reporting Period and StrategicPillar
		if(spId == 0 && themeId == 0 && outcomeId == 0 && outputId == 0 && partnerAgencyId == 0){
			List<SubActivity> subActivities = subActivityService.getSubActivitiesByOpenedReportingPeriod(reportingPeriodId);
			LinkedHashSet<StrategicPillar> strategicPillars = new LinkedHashSet<StrategicPillar>();
			LinkedHashSet<Theme> themes = new LinkedHashSet<Theme>();
			LinkedHashSet<Outcome> outcomes  = new LinkedHashSet<Outcome>();
			LinkedHashSet<Output> outputs = new LinkedHashSet<Output>();
			LinkedHashSet<KeyActivity> keyActivities = new LinkedHashSet<KeyActivity>();
			if(subActivities.size() > 0){
				for (SubActivity subActivity : subActivities) {
					strategicPillars.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getStrategicPillar());
					themes.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme());
					outcomes.add(subActivity.getKeyActivity().getOutput().getOutcome());
					outputs.add(subActivity.getKeyActivity().getOutput());
					keyActivities.add(subActivity.getKeyActivity());
				}
			}
			superAdminHssfWorkbook = superAdminActualReportDownloadCommonFunction(user, strategicPillars, reportingPeriodValues, themes, outcomes, outputs, keyActivities, subActivities,partnerAgencyId);
		}else if (spId != 0 && themeId == 0 && outcomeId == 0 && outputId == 0 && partnerAgencyId == 0) {
			List<SubActivity> subActivities = subActivityService.getSubActivitiesByStrategicPillarAndOpenedReportingPeriod(spId, reportingPeriodId);
			LinkedHashSet<StrategicPillar> strategicPillars = new LinkedHashSet<StrategicPillar>();
			LinkedHashSet<Theme> themes = new LinkedHashSet<Theme>();
			LinkedHashSet<Outcome> outcomes  = new LinkedHashSet<Outcome>();
			LinkedHashSet<Output> outputs = new LinkedHashSet<Output>();
			LinkedHashSet<KeyActivity> keyActivities = new LinkedHashSet<KeyActivity>();
			if(subActivities.size() > 0){
				for (SubActivity subActivity : subActivities) {
					strategicPillars.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getStrategicPillar());
					themes.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme());
					outcomes.add(subActivity.getKeyActivity().getOutput().getOutcome());
					outputs.add(subActivity.getKeyActivity().getOutput());
					keyActivities.add(subActivity.getKeyActivity());
				}
			}
			superAdminHssfWorkbook = superAdminActualReportDownloadCommonFunction(user, strategicPillars, reportingPeriodValues, themes, outcomes, outputs, keyActivities, subActivities,partnerAgencyId);
		}else if (spId != 0 && themeId != 0 && outcomeId == 0 && outputId == 0 && partnerAgencyId == 0) {
			List<SubActivity> subActivities = subActivityService.getSubActivitiesByThemeAndOpenedReportingPeriod(themeId, reportingPeriodId);
			LinkedHashSet<StrategicPillar> strategicPillars = new LinkedHashSet<StrategicPillar>();
			LinkedHashSet<Theme> themes = new LinkedHashSet<Theme>();
			LinkedHashSet<Outcome> outcomes  = new LinkedHashSet<Outcome>();
			LinkedHashSet<Output> outputs = new LinkedHashSet<Output>();
			LinkedHashSet<KeyActivity> keyActivities = new LinkedHashSet<KeyActivity>();
			if(subActivities.size() > 0){
				for (SubActivity subActivity : subActivities) {
					strategicPillars.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getStrategicPillar());
					themes.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme());
					outcomes.add(subActivity.getKeyActivity().getOutput().getOutcome());
					outputs.add(subActivity.getKeyActivity().getOutput());
					keyActivities.add(subActivity.getKeyActivity());
				}
			}
			superAdminHssfWorkbook = superAdminActualReportDownloadCommonFunction(user, strategicPillars, reportingPeriodValues, themes, outcomes, outputs, keyActivities, subActivities,partnerAgencyId);
		}else if (spId != 0 && themeId != 0 && outcomeId != 0 && outputId == 0 && partnerAgencyId == 0) {
			List<SubActivity> subActivities = subActivityService.getSubActivitiesByOutcomeAndOpenedReportingPeriod(outcomeId, reportingPeriodId);
			LinkedHashSet<StrategicPillar> strategicPillars = new LinkedHashSet<StrategicPillar>();
			LinkedHashSet<Theme> themes = new LinkedHashSet<Theme>();
			LinkedHashSet<Outcome> outcomes  = new LinkedHashSet<Outcome>();
			LinkedHashSet<Output> outputs = new LinkedHashSet<Output>();
			LinkedHashSet<KeyActivity> keyActivities = new LinkedHashSet<KeyActivity>();
			if(subActivities.size() > 0){
				for (SubActivity subActivity : subActivities) {
					strategicPillars.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getStrategicPillar());
					themes.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme());
					outcomes.add(subActivity.getKeyActivity().getOutput().getOutcome());
					outputs.add(subActivity.getKeyActivity().getOutput());
					keyActivities.add(subActivity.getKeyActivity());
				}
			}
			superAdminHssfWorkbook = superAdminActualReportDownloadCommonFunction(user, strategicPillars, reportingPeriodValues, themes, outcomes, outputs, keyActivities, subActivities,partnerAgencyId);
		}else if(spId != 0 && themeId != 0 && outcomeId != 0 && outputId != 0 && partnerAgencyId == 0){
			List<SubActivity> subActivities = subActivityService.getSubActivitiesByOutputAndOpenedReportingPeriod(outputId, reportingPeriodId);
			LinkedHashSet<StrategicPillar> strategicPillars = new LinkedHashSet<StrategicPillar>();
			LinkedHashSet<Theme> themes = new LinkedHashSet<Theme>();
			LinkedHashSet<Outcome> outcomes  = new LinkedHashSet<Outcome>();
			LinkedHashSet<Output> outputs = new LinkedHashSet<Output>();
			LinkedHashSet<KeyActivity> keyActivities = new LinkedHashSet<KeyActivity>();
			if(subActivities.size() > 0){
				for (SubActivity subActivity : subActivities) {
					strategicPillars.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getStrategicPillar());
					themes.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme());
					outcomes.add(subActivity.getKeyActivity().getOutput().getOutcome());
					outputs.add(subActivity.getKeyActivity().getOutput());
					keyActivities.add(subActivity.getKeyActivity());
				}
			}
			superAdminHssfWorkbook = superAdminActualReportDownloadCommonFunction(user, strategicPillars, reportingPeriodValues, themes, outcomes, outputs, keyActivities, subActivities,partnerAgencyId);
		}else if(spId == 0 && themeId == 0 && outcomeId == 0 && outputId == 0 && partnerAgencyId == -1){
			List<SubActivity> subActivities = subActivityService.getSubActivitiesByOpenedReportingPeriod(reportingPeriodId);
			LinkedHashSet<StrategicPillar> strategicPillars = new LinkedHashSet<StrategicPillar>();
			LinkedHashSet<Theme> themes = new LinkedHashSet<Theme>();
			LinkedHashSet<Outcome> outcomes  = new LinkedHashSet<Outcome>();
			LinkedHashSet<Output> outputs = new LinkedHashSet<Output>();
			LinkedHashSet<KeyActivity> keyActivities = new LinkedHashSet<KeyActivity>();
			if(subActivities.size() > 0){
				for (SubActivity subActivity : subActivities) {
					strategicPillars.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getStrategicPillar());
					themes.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme());
					outcomes.add(subActivity.getKeyActivity().getOutput().getOutcome());
					outputs.add(subActivity.getKeyActivity().getOutput());
					keyActivities.add(subActivity.getKeyActivity());
				}
			}
			superAdminHssfWorkbook = superAdminActualReportDownloadCommonFunction(user, strategicPillars, reportingPeriodValues, themes, outcomes, outputs, keyActivities, subActivities,partnerAgencyId);
		}else if (spId != 0 && themeId == 0 && outcomeId == 0 && outputId == 0 && partnerAgencyId == -1) {
			List<SubActivity> subActivities = subActivityService.getSubActivitiesByStrategicPillarAndOpenedReportingPeriod(spId, reportingPeriodId);
			LinkedHashSet<StrategicPillar> strategicPillars = new LinkedHashSet<StrategicPillar>();
			LinkedHashSet<Theme> themes = new LinkedHashSet<Theme>();
			LinkedHashSet<Outcome> outcomes  = new LinkedHashSet<Outcome>();
			LinkedHashSet<Output> outputs = new LinkedHashSet<Output>();
			LinkedHashSet<KeyActivity> keyActivities = new LinkedHashSet<KeyActivity>();
			if(subActivities.size() > 0){
				for (SubActivity subActivity : subActivities) {
					strategicPillars.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getStrategicPillar());
					themes.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme());
					outcomes.add(subActivity.getKeyActivity().getOutput().getOutcome());
					outputs.add(subActivity.getKeyActivity().getOutput());
					keyActivities.add(subActivity.getKeyActivity());
				}
			}
			superAdminHssfWorkbook = superAdminActualReportDownloadCommonFunction(user, strategicPillars, reportingPeriodValues, themes, outcomes, outputs, keyActivities, subActivities,partnerAgencyId);
		}else if (spId != 0 && themeId != 0 && outcomeId == 0 && outputId == 0 && partnerAgencyId == -1) {
			List<SubActivity> subActivities = subActivityService.getSubActivitiesByThemeAndOpenedReportingPeriod(themeId, reportingPeriodId);
			LinkedHashSet<StrategicPillar> strategicPillars = new LinkedHashSet<StrategicPillar>();
			LinkedHashSet<Theme> themes = new LinkedHashSet<Theme>();
			LinkedHashSet<Outcome> outcomes  = new LinkedHashSet<Outcome>();
			LinkedHashSet<Output> outputs = new LinkedHashSet<Output>();
			LinkedHashSet<KeyActivity> keyActivities = new LinkedHashSet<KeyActivity>();
			if(subActivities.size() > 0){
				for (SubActivity subActivity : subActivities) {
					strategicPillars.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getStrategicPillar());
					themes.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme());
					outcomes.add(subActivity.getKeyActivity().getOutput().getOutcome());
					outputs.add(subActivity.getKeyActivity().getOutput());
					keyActivities.add(subActivity.getKeyActivity());
				}
			}
			superAdminHssfWorkbook = superAdminActualReportDownloadCommonFunction(user, strategicPillars, reportingPeriodValues, themes, outcomes, outputs, keyActivities, subActivities,partnerAgencyId);
		}else if (spId != 0 && themeId != 0 && outcomeId != 0 && outputId == 0 && partnerAgencyId == -1) {
			List<SubActivity> subActivities = subActivityService.getSubActivitiesByOutcomeAndOpenedReportingPeriod(outcomeId, reportingPeriodId);
			LinkedHashSet<StrategicPillar> strategicPillars = new LinkedHashSet<StrategicPillar>();
			LinkedHashSet<Theme> themes = new LinkedHashSet<Theme>();
			LinkedHashSet<Outcome> outcomes  = new LinkedHashSet<Outcome>();
			LinkedHashSet<Output> outputs = new LinkedHashSet<Output>();
			LinkedHashSet<KeyActivity> keyActivities = new LinkedHashSet<KeyActivity>();
			if(subActivities.size() > 0){
				for (SubActivity subActivity : subActivities) {
					strategicPillars.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getStrategicPillar());
					themes.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme());
					outcomes.add(subActivity.getKeyActivity().getOutput().getOutcome());
					outputs.add(subActivity.getKeyActivity().getOutput());
					keyActivities.add(subActivity.getKeyActivity());
				}
			}
			superAdminHssfWorkbook = superAdminActualReportDownloadCommonFunction(user, strategicPillars, reportingPeriodValues, themes, outcomes, outputs, keyActivities, subActivities,partnerAgencyId);
		}else if(spId != 0 && themeId != 0 && outcomeId != 0 && outputId != 0 && partnerAgencyId == -1){
			List<SubActivity> subActivities = subActivityService.getSubActivitiesByOutputAndOpenedReportingPeriod(outputId, reportingPeriodId);
			LinkedHashSet<StrategicPillar> strategicPillars = new LinkedHashSet<StrategicPillar>();
			LinkedHashSet<Theme> themes = new LinkedHashSet<Theme>();
			LinkedHashSet<Outcome> outcomes  = new LinkedHashSet<Outcome>();
			LinkedHashSet<Output> outputs = new LinkedHashSet<Output>();
			LinkedHashSet<KeyActivity> keyActivities = new LinkedHashSet<KeyActivity>();
			if(subActivities.size() > 0){
				for (SubActivity subActivity : subActivities) {
					strategicPillars.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getStrategicPillar());
					themes.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme());
					outcomes.add(subActivity.getKeyActivity().getOutput().getOutcome());
					outputs.add(subActivity.getKeyActivity().getOutput());
					keyActivities.add(subActivity.getKeyActivity());
				}
			}
			superAdminHssfWorkbook = superAdminActualReportDownloadCommonFunction(user, strategicPillars, reportingPeriodValues, themes, outcomes, outputs, keyActivities, subActivities,partnerAgencyId);
		}else{
			HSSFRow headerRow = hssfSheet.createRow(0);
			
			Font boldFont = superAdminHssfWorkbook.createFont();
			boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
			boldFont.setColor(IndexedColors.BLACK.getIndex());
			
			Font outputFont = superAdminHssfWorkbook.createFont();
			outputFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
			outputFont.setColor(IndexedColors.RED.getIndex());
			
			Font outcomeFont = superAdminHssfWorkbook.createFont();
			outcomeFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
			outcomeFont.setColor(IndexedColors.BLUE.getIndex());
			
			Font mainFont = superAdminHssfWorkbook.createFont();
			mainFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
			mainFont.setFontHeightInPoints((short) 20);
			mainFont.setColor(IndexedColors.BLACK.getIndex());
			
			CellStyle mainHeaderStyle = superAdminHssfWorkbook.createCellStyle();
			mainHeaderStyle.setBorderBottom(CellStyle.BORDER_THIN);
			mainHeaderStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			mainHeaderStyle.setBorderLeft(CellStyle.BORDER_THIN);
			mainHeaderStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			mainHeaderStyle.setBorderRight(CellStyle.BORDER_THIN);
			mainHeaderStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
			mainHeaderStyle.setBorderTop(CellStyle.BORDER_THIN);
			mainHeaderStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
			mainHeaderStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			mainHeaderStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			mainHeaderStyle.setAlignment(CellStyle.ALIGN_CENTER);
			mainHeaderStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			mainHeaderStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			mainHeaderStyle.setFont(mainFont);
			mainHeaderStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
			mainHeaderStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
			mainHeaderStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
			mainHeaderStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			
			CellStyle tableHeaderStyle = superAdminHssfWorkbook.createCellStyle();
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
			tableHeaderStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
			tableHeaderStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
			tableHeaderStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
			tableHeaderStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			
			CellStyle tableHeaderStyleLeft = superAdminHssfWorkbook.createCellStyle();
			tableHeaderStyleLeft.setBorderBottom(CellStyle.BORDER_THIN);
			tableHeaderStyleLeft.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			tableHeaderStyleLeft.setBorderLeft(CellStyle.BORDER_THIN);
			tableHeaderStyleLeft.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			tableHeaderStyleLeft.setBorderRight(CellStyle.BORDER_THIN);
			tableHeaderStyleLeft.setRightBorderColor(IndexedColors.BLACK.getIndex());
			tableHeaderStyleLeft.setBorderTop(CellStyle.BORDER_THIN);
			tableHeaderStyleLeft.setTopBorderColor(IndexedColors.BLACK.getIndex());
			tableHeaderStyleLeft.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			tableHeaderStyleLeft.setFillPattern(CellStyle.SOLID_FOREGROUND);
			tableHeaderStyleLeft.setAlignment(CellStyle.ALIGN_LEFT);
			tableHeaderStyleLeft.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			tableHeaderStyleLeft.setFillPattern(CellStyle.SOLID_FOREGROUND);
			tableHeaderStyleLeft.setFont(boldFont);
			tableHeaderStyleLeft.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
			tableHeaderStyleLeft.setBorderRight(HSSFCellStyle.BORDER_THIN);            
			tableHeaderStyleLeft.setBorderTop(HSSFCellStyle.BORDER_THIN);              
			tableHeaderStyleLeft.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			
			CellStyle tableHeaderStyle1 = superAdminHssfWorkbook.createCellStyle();
			Font font = superAdminHssfWorkbook.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			tableHeaderStyle1.setFont(font);
			
			HSSFCellStyle borderStyle = superAdminHssfWorkbook.createCellStyle();
			borderStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
			borderStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
			borderStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
			borderStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			
			
			//Create First Row
			HSSFCell seqNumber = headerRow.createCell(0);
			seqNumber.setCellValue("National Drug Control Master Plan (NDCMP)");
			seqNumber.setCellStyle(mainHeaderStyle);
			seqNumber.setCellType(Cell.CELL_TYPE_STRING);
			headerRow.setHeight((short)700);

			hssfSheet.addMergedRegion(new CellRangeAddress(0,0,0,9));
			
			Agency agency = null;
			if(partnerAgencyId != null){
				agency = agencyService.findByAgencyId(partnerAgencyId);
			}
			
			//Second Row for set Agency
			headerRow = hssfSheet.createRow(1);
			HSSFCellStyle agencystyle = superAdminHssfWorkbook.createCellStyle();
			agencystyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
			agencystyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
			agencystyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
			agencystyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			agencystyle.setFont(boldFont);

			HSSFCell nameOfAgency = headerRow.createCell(0);
			
			if(agency != null){
				nameOfAgency.setCellValue("Report By : "+user.getFirstName() +" "+user.getLastName()+" ("+agency.getName()+")");
			}else{
				nameOfAgency.setCellValue("");
			}
			nameOfAgency.setCellStyle(tableHeaderStyleLeft);
			nameOfAgency.setCellType(Cell.CELL_TYPE_STRING);
			hssfSheet.addMergedRegion(new CellRangeAddress(1,1,0,1));
			
			HSSFCell agencyName  = headerRow.createCell(1);
			agencyName.setCellType(Cell.CELL_TYPE_STRING);

			HSSFCell reportingPeriod  = headerRow.createCell(2);
			reportingPeriod.setCellValue("Reporting period : "+reportingPeriodValues.getYear()+ " - "+reportingPeriodValues.getName());
			reportingPeriod.setCellStyle(tableHeaderStyle);
			reportingPeriod.setCellType(Cell.CELL_TYPE_STRING);
			hssfSheet.addMergedRegion(new CellRangeAddress(1,1,2,5));
			
			SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date date = new Date();
			
			HSSFCell reportTime = headerRow.createCell(6);
			reportTime.setCellValue("Report Time : "+sd.format(date));
			reportTime.setCellStyle(tableHeaderStyle);
			reportTime.setCellType(Cell.CELL_TYPE_STRING);
			hssfSheet.addMergedRegion(new CellRangeAddress(1,1,6,9));
			
			HSSFCellStyle strategicStyle = superAdminHssfWorkbook.createCellStyle();
			strategicStyle.setFont(boldFont);
			Font strategicFont = superAdminHssfWorkbook.createFont();
			font.setColor(HSSFColor.AQUA.index);
			strategicFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
			
			String[] sColor=hex2Rgb("#33cccc").split(",");
			String[] keyBarColor=hex2Rgb("#CCFFCC").split(",");
			
			int c1=Integer.parseInt(sColor[0]);
			int c2=Integer.parseInt(sColor[1]);
			int c3=Integer.parseInt(sColor[2]);
			
			int kc1=Integer.parseInt(keyBarColor[0]);
			int kc2=Integer.parseInt(keyBarColor[1]);
			int kc3=Integer.parseInt(keyBarColor[2]);
			
			HSSFCellStyle style2=superAdminHssfWorkbook.createCellStyle();
			HSSFColor skyBlue = setColor(superAdminHssfWorkbook,(byte) c1, (byte) c2,(byte) c3);
			style2.setFillForegroundColor(skyBlue.getIndex());
			style2.setFont(boldFont);
			style2.setFillPattern(CellStyle.SOLID_FOREGROUND);
			style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
			style2.setBorderRight(HSSFCellStyle.BORDER_THIN);            
			style2.setBorderTop(HSSFCellStyle.BORDER_THIN);              
			style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			
			HSSFCellStyle keyBarstyle2=superAdminHssfWorkbook.createCellStyle();
			HSSFColor green =  setColor(superAdminHssfWorkbook,(byte) kc1, (byte) kc2,(byte) kc3);
			keyBarstyle2.setFillForegroundColor(green.getIndex());
			keyBarstyle2.setFont(boldFont);
			keyBarstyle2.setFillPattern(CellStyle.SOLID_FOREGROUND);
			keyBarstyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
			keyBarstyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);            
			keyBarstyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);              
			keyBarstyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			
			StrategicPillar strategicPillar = strategicPillarService.getById(spId);
			headerRow = hssfSheet.createRow(3);
			HSSFCell sPillarName = headerRow.createCell(0);
			if(strategicPillar != null){
				sPillarName.setCellValue("Strategic Pillar  "+strategicPillar.getId()+" : "+strategicPillar.getName());
			}else{
				sPillarName.setCellValue("");
			}
			
			sPillarName.setCellStyle(style2);
			sPillarName.setCellType(Cell.CELL_TYPE_STRING);
			hssfSheet.addMergedRegion(new CellRangeAddress(3,3,0,9));
			
			//Forth Row for Theme
			String[] yColor=hex2Rgb("#FFCC99").split(",");
			int yc1=Integer.parseInt(yColor[0]);
			int yc2=Integer.parseInt(yColor[1]);
			int yc3=Integer.parseInt(yColor[2]);
			
			HSSFCellStyle yellowStyle=superAdminHssfWorkbook.createCellStyle();
			HSSFColor sandal =  setColor(superAdminHssfWorkbook,(byte) yc1, (byte) yc2,(byte) yc3);
			yellowStyle.setFillForegroundColor(sandal.getIndex());
			yellowStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			yellowStyle.setFont(boldFont);
			yellowStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
			yellowStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
			yellowStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
			yellowStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			
			Theme theme = themeService.getById(themeId);
			headerRow = hssfSheet.createRow(4);
			HSSFCell themeName = headerRow.createCell(0);
			if(theme != null){
				themeName.setCellValue("Theme : "+theme.getName());
			}else{
				themeName.setCellValue("");
			}
			
			themeName.setCellStyle(yellowStyle);
			themeName.setCellType(Cell.CELL_TYPE_STRING);
			hssfSheet.addMergedRegion(new CellRangeAddress(4,4,0,9));
			
			
			//Fivth row for outcome
			HSSFCellStyle outcomeStyle1=superAdminHssfWorkbook.createCellStyle();
			HSSFColor outcomeSandal = setColor(superAdminHssfWorkbook,(byte) yc1, (byte) yc2,(byte) yc3);
			outcomeStyle1.setFillForegroundColor(outcomeSandal.getIndex());
			outcomeStyle1.setFillPattern(CellStyle.SOLID_FOREGROUND);
			outcomeStyle1.setFont(outcomeFont);
			outcomeStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
			outcomeStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);            
			outcomeStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);              
			outcomeStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			
			Outcome outcome = outcomeService.getById(outcomeId);
			headerRow = hssfSheet.createRow(5);
			HSSFCell outcomeName1 = headerRow.createCell(0);
			if(outcome != null){
				outcomeName1.setCellValue("Outcome "+outcome.getSequenceNumber()+" : "+outcome.getName());
			}else{
				outcomeName1.setCellValue("");
			}
			outcomeName1.setCellStyle(outcomeStyle1);
			outcomeName1.setCellType(Cell.CELL_TYPE_STRING);
			hssfSheet.addMergedRegion(new CellRangeAddress(5,5,0,9));
			
			//Sixth row for output
			HSSFCellStyle outputStyle=superAdminHssfWorkbook.createCellStyle();
			HSSFColor outputSandal = setColor(superAdminHssfWorkbook,(byte) yc1, (byte) yc2,(byte) yc3);
			outputStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
			outputStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
			outputStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
			outputStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			outputStyle.setFillForegroundColor(outputSandal.getIndex());
			outputStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			outputStyle.setFont(outputFont);
			
			Output output = outputServices.getById(outputId);
			headerRow = hssfSheet.createRow(6);
			HSSFCell outputName = headerRow.createCell(0);
			if(output != null){
				outputName.setCellValue("Output "+output.getSequenceNumber()+" : "+output.getOutput());
			}else{
				outputName.setCellValue("");
			}
			
			outputName.setCellStyle(outputStyle);
			outputName.setCellType(Cell.CELL_TYPE_STRING);
			hssfSheet.addMergedRegion(new CellRangeAddress(6,6,0,9));
			
			Integer rowValue = 7;
			List<Indicator> indicators = indicatorService.findByOutput(output);
			if(indicators != null && indicators.size() > 0){
				for(Indicator indicator : indicators){
					headerRow = hssfSheet.createRow(rowValue);
					HSSFCell indicatorName = headerRow.createCell(0);
					indicatorName.setCellValue("Indicators : "+indicator.getMessage());
					indicatorName.setCellStyle(yellowStyle);
					indicatorName.setCellType(Cell.CELL_TYPE_STRING);
					
					hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,0,9));
					rowValue++;
				}
			}else{
				headerRow = hssfSheet.createRow(rowValue);
				HSSFCell indicatorName = headerRow.createCell(0);
				indicatorName.setCellValue("Indicators : ");
				indicatorName.setCellStyle(yellowStyle);
				indicatorName.setCellType(Cell.CELL_TYPE_STRING);
				
				hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,0,9));
				rowValue++;
			}
			
			List<Target> targets = targetService.findByOutput(output);
			if(targets != null && targets.size() > 0){
				for(Target target : targets){
					headerRow = hssfSheet.createRow(rowValue);
					HSSFCell targetName = headerRow.createCell(0);
					targetName.setCellValue("Targets : "+target.getMessage());
					targetName.setCellStyle(yellowStyle);
					targetName.setCellType(Cell.CELL_TYPE_STRING);
					hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,0,9));
					rowValue++;
				}
			}else{
				headerRow = hssfSheet.createRow(rowValue);
				HSSFCell targetName = headerRow.createCell(0);
				targetName.setCellValue("Targets : ");
				targetName.setCellStyle(yellowStyle);
				targetName.setCellType(Cell.CELL_TYPE_STRING);
				hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,0,7));
				rowValue++;
			}
			headerRow = hssfSheet.createRow(rowValue);
			HSSFCell seqenceNumber = headerRow.createCell(0);
			seqenceNumber.setCellValue("S.No");
			seqenceNumber.setCellStyle(keyBarstyle2);
			seqenceNumber.setCellType(Cell.CELL_TYPE_STRING); 
			
			HSSFCell keyActivitys = headerRow.createCell(1);
			keyActivitys.setCellValue("Key Activities");
			keyActivitys.setCellStyle(keyBarstyle2);
			keyActivitys.setCellType(Cell.CELL_TYPE_STRING);

			HSSFCell subActivitys  = headerRow.createCell(2);
			subActivitys.setCellValue("Sub Activities");
			subActivitys.setCellStyle(keyBarstyle2);
			subActivitys.setCellType(Cell.CELL_TYPE_STRING);
			
			HSSFCell status = headerRow.createCell(3);
			status.setCellValue("Status");
			status.setCellStyle(keyBarstyle2);
			status.setCellType(Cell.CELL_TYPE_STRING);
			
			HSSFCell keyProgress = headerRow.createCell(4);
			keyProgress.setCellValue("Key Progress");
			keyProgress.setCellStyle(keyBarstyle2);
			keyProgress.setCellType(Cell.CELL_TYPE_STRING);
			
			HSSFCell reasongap = headerRow.createCell(5);
			reasongap.setCellValue("Reasons for gap if any");
			reasongap.setCellStyle(keyBarstyle2);
			reasongap.setCellType(Cell.CELL_TYPE_STRING);
			
			HSSFCell reactify = headerRow.createCell(6);
			reactify.setCellValue("Plan of Action to rectify the gap");
			reactify.setCellStyle(keyBarstyle2);
			reactify.setCellType(Cell.CELL_TYPE_STRING);
			
			HSSFCell feedbac = headerRow.createCell(7);
			feedbac.setCellValue("Reviewer Feedback");
			feedbac.setCellStyle(keyBarstyle2);
			feedbac.setCellType(Cell.CELL_TYPE_STRING);
			
			HSSFCell reviewedByHeading = headerRow.createCell(8);
			reviewedByHeading.setCellValue("Reviewed By");
			reviewedByHeading.setCellStyle(keyBarstyle2);
			reviewedByHeading.setCellType(Cell.CELL_TYPE_STRING);
			
			HSSFCell reviewedTimeHeading = headerRow.createCell(9);
			reviewedTimeHeading.setCellValue("Reviewed Time");
			reviewedTimeHeading.setCellStyle(keyBarstyle2);
			reviewedTimeHeading.setCellType(Cell.CELL_TYPE_STRING);
			
			HSSFCellStyle activityStyle = superAdminHssfWorkbook.createCellStyle();
			activityStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
			activityStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
			activityStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
			activityStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			
			List<SubActivity> subActivities = subActivityService.getSubActivitiesByOutputAndOpenedReportingPeriodAndLeadAgency(outputId, reportingPeriodId, partnerAgencyId);
			LinkedHashSet<KeyActivity> keyActivities = new  LinkedHashSet<KeyActivity>();
			if(subActivities.size() > 0){
				for(SubActivity subActivity : subActivities){
					int temp =0;
					KeyActivity subBykeyActivity = keyActivityService.getById(subActivity.getKeyActivity().getId()); 
					if(subBykeyActivity != null){
						if(keyActivities.size() == 0){
							keyActivities.add(subBykeyActivity);
						}else{
							for (KeyActivity keyActivity : keyActivities) {
								if(keyActivity.getId() == subBykeyActivity.getId()){
									temp=0;
									break;
								}else{
									temp=1;
								}
							}
							if(temp == 1){
								keyActivities.add(subBykeyActivity);
							}
						}
					}
				}
			}
			rowValue++;
			
			for(KeyActivity keyActivity : keyActivities){
					headerRow = hssfSheet.createRow(rowValue);
					HSSFCell keySeqNumber = headerRow.createCell(0);
					keySeqNumber.setCellValue(keyActivity.getSequenceNumber());
					keySeqNumber.setCellStyle(activityStyle);
					
					HSSFCell keyActivityName = headerRow.createCell(1);
					keyActivityName.setCellValue(keyActivity.getName());
					CellStyle cellStyleWrap = superAdminHssfWorkbook.createCellStyle();
					cellStyleWrap.setWrapText(true);//set wraper text
					cellStyleWrap.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
					cellStyleWrap.setBorderRight(HSSFCellStyle.BORDER_THIN);            
					cellStyleWrap.setBorderTop(HSSFCellStyle.BORDER_THIN);              
					cellStyleWrap.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					keyActivityName.setCellStyle(cellStyleWrap);
					headerRow.setHeightInPoints((2*hssfSheet.getDefaultRowHeightInPoints()));
					hssfSheet.setColumnWidth(1,11000);
					
					HSSFCell subActivity = headerRow.createCell(2);
					subActivity.setCellValue("");
					subActivity.setCellStyle(activityStyle);
					
					HSSFCell status1 = headerRow.createCell(3);
					status1.setCellValue("");
					status1.setCellStyle(activityStyle);
					
					HSSFCell keyProgress1 = headerRow.createCell(4);
					keyProgress1.setCellValue("");
					keyProgress1.setCellStyle(activityStyle);
					
					HSSFCell reason = headerRow.createCell(5);
					reason.setCellValue("");
					reason.setCellStyle(activityStyle);
					
					HSSFCell rectify = headerRow.createCell(6);
					rectify.setCellValue("");
					rectify.setCellStyle(activityStyle);
					
					HSSFCell feedback = headerRow.createCell(7);
					feedback.setCellValue("");
					feedback.setCellStyle(activityStyle);
					
					HSSFCell rBy = headerRow.createCell(8);
					rBy.setCellValue("");
					rBy.setCellStyle(activityStyle);
					
					HSSFCell rOn = headerRow.createCell(9);
					rOn.setCellValue("");
					rOn.setCellStyle(activityStyle);
					rowValue++;
					
					List<SubActivity> subActivities3 = subActivityService.getSubActivitiesByKeyActivityAndOpenedReportingPeriodAndLeadAgency(keyActivity, reportingPeriodId, partnerAgencyId);
					for(SubActivity subActivityByKeyActivity : subActivities3){
						headerRow = hssfSheet.createRow(rowValue);
						HSSFCell subSeySeqNumber = headerRow.createCell(0);
						subSeySeqNumber.setCellValue(subActivityByKeyActivity.getSequenceNumber());
						subSeySeqNumber.setCellStyle(activityStyle);
						
						keyActivityName = headerRow.createCell(1);
						keyActivityName.setCellValue("");
						keyActivityName.setCellStyle(activityStyle);
						
						HSSFCell subActivityName = headerRow.createCell(2);
						subActivityName.setCellValue(subActivityByKeyActivity.getName());
						CellStyle cellStyleWrap1 = superAdminHssfWorkbook.createCellStyle();
						cellStyleWrap1.setWrapText(true);//set wraper text
						cellStyleWrap1.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
						cellStyleWrap1.setBorderRight(HSSFCellStyle.BORDER_THIN);            
						cellStyleWrap1.setBorderTop(HSSFCellStyle.BORDER_THIN);              
						cellStyleWrap1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
						subActivityName.setCellStyle(cellStyleWrap1);
						headerRow.setHeightInPoints((2*hssfSheet.getDefaultRowHeightInPoints()));
						hssfSheet.setColumnWidth(2,10000);
						
						int temp = 0;
						List<StatusTracking> subStatusTrackings = statusTrackingRepository.findBysubActivityAndReportingPeriodAndUserLevel(subActivityByKeyActivity, reportingPeriodValues, 2);
						if(subStatusTrackings != null && subStatusTrackings.size() > 0){
							for (StatusTracking statusTracking : subStatusTrackings) {
								HSSFCellStyle	statusColor=superAdminHssfWorkbook.createCellStyle();
								
								String[] staColor=hex2Rgb(statusTracking.getActualStatusColor()).split(",");
								int ac1=Integer.parseInt(staColor[0]);
								int ac2=Integer.parseInt(staColor[1]);
								int ac3=Integer.parseInt(staColor[2]);
								
								HSSFPalette palette = superAdminHssfWorkbook.getCustomPalette();
								HSSFColor myColor = palette.findSimilarColor(ac1, ac2, ac3);
								short palIndex = myColor.getIndex();
								statusColor.setFillForegroundColor(palIndex);
								statusColor.setFillPattern(CellStyle.SOLID_FOREGROUND);
								statusColor.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
								statusColor.setBorderRight(HSSFCellStyle.BORDER_THIN);            
								statusColor.setBorderTop(HSSFCellStyle.BORDER_THIN);              
								statusColor.setBorderBottom(HSSFCellStyle.BORDER_THIN);
									
								HSSFCell subStatus1 = headerRow.createCell(3);
								subStatus1.setCellValue(Integer.parseInt(statusTracking.getActualStatusPercentage()));
								subStatus1.setCellStyle(statusColor);
								
								HSSFCell subKeyProgress1 = headerRow.createCell(4);
								subKeyProgress1.setCellValue(statusTracking.getKeyProgress());
								subKeyProgress1.setCellStyle(activityStyle);
								
								HSSFCell subReason = headerRow.createCell(5);
								subReason.setCellValue(statusTracking.getReasonForGap());
								subReason.setCellStyle(activityStyle);
								
								HSSFCell subRectify = headerRow.createCell(6);
								subRectify.setCellValue(statusTracking.getRectifyTheGap());
								subRectify.setCellStyle(activityStyle);
								
								HSSFCell subFeedBack = headerRow.createCell(7);
								subFeedBack.setCellValue(statusTracking.getReviewerFeedback());
								subFeedBack.setCellStyle(activityStyle);
								
								HSSFCell subReviewedByValue = headerRow.createCell(8);
								if(statusTracking.getReviewedBy() != null){
									subReviewedByValue.setCellValue(statusTracking.getReviewedBy().getFirstName()+" "+statusTracking.getReviewedBy().getLastName());
								}else{
									subReviewedByValue.setCellValue("");
								}
								subReviewedByValue.setCellStyle(activityStyle);

								HSSFCell subReviewedTimeValue = headerRow.createCell(9);
								if(statusTracking.getReviewedOn() != null && statusTracking.getReviewedOn() != ""){

									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
									SimpleDateFormat dsf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
									Date date2 = null;
									String reviewTime = statusTracking.getReviewedOn();
									try {
										date2 = sdf.parse(reviewTime);
										subReviewedTimeValue.setCellValue(dsf.format(date2));
									} catch (ParseException e) {
										e.printStackTrace();
									}
								}else{
									subReviewedTimeValue.setCellValue("");
								}
								subReviewedTimeValue.setCellStyle(activityStyle);
								temp =1;
							}
						}
						if(temp == 0){
							HSSFCell subStatus = headerRow.createCell(3);
							subStatus.setCellValue("");
							subStatus.setCellStyle(activityStyle);
							
							HSSFCell subKeyProgress = headerRow.createCell(4);
							subKeyProgress.setCellValue("");
							subKeyProgress.setCellStyle(activityStyle);
							
							HSSFCell subReason = headerRow.createCell(5);
							subReason.setCellValue("");
							subReason.setCellStyle(activityStyle);
							
							HSSFCell subRectify = headerRow.createCell(6);
							subRectify.setCellValue("");
							subRectify.setCellStyle(activityStyle);
							
							HSSFCell subFeedbackReviewer = headerRow.createCell(7);
							subFeedbackReviewer.setCellValue("");
							subFeedbackReviewer.setCellStyle(activityStyle);
							
							HSSFCell subReviewedBy = headerRow.createCell(8);
							subReviewedBy.setCellValue("");
							subReviewedBy.setCellStyle(activityStyle);

							HSSFCell subReviewedTime = headerRow.createCell(9);
							subReviewedTime.setCellValue("");
							subReviewedTime.setCellStyle(activityStyle);
						}
						
						if(partnerAgencyId != -1){
							Agency agency2 = agencyService.findByAgencyId(partnerAgencyId);
							List<StatusTracking> statusTrackings = statusTrackingRepository.findBysubActivityAndReportingPeriodAndUserLevel(subActivityByKeyActivity, reportingPeriodValues, 1);
							for(StatusTracking statusTracking : statusTrackings){
								if(statusTracking != null){
									rowValue++;
									headerRow = hssfSheet.createRow(rowValue);
									HSSFCell cell0 = headerRow.createCell(0);
									cell0.setCellStyle(agencystyle);
									HSSFCell cell1 = headerRow.createCell(1);
									cell1.setCellStyle(agencystyle);
									HSSFCell userAgency = headerRow.createCell(2);
									userAgency.setCellValue("   By  "+agency2.getName()+" (" +user.getFirstName() +" "+ user.getLastName()+")");
									userAgency.setCellStyle(activityStyle);
									if(statusTracking.getSubActivity().getId().equals(subActivityByKeyActivity.getId())){
										HSSFCellStyle	statusColor=superAdminHssfWorkbook.createCellStyle();
										
										String[] staColor=hex2Rgb(statusTracking.getActualStatusColor()).split(",");
										int ac1=Integer.parseInt(staColor[0]);
										int ac2=Integer.parseInt(staColor[1]);
										int ac3=Integer.parseInt(staColor[2]);
										
										HSSFPalette palette = superAdminHssfWorkbook.getCustomPalette();
										HSSFColor myColor = palette.findSimilarColor(ac1, ac2, ac3);
										short palIndex = myColor.getIndex();
										statusColor.setFillForegroundColor(palIndex);
										statusColor.setFillPattern(CellStyle.SOLID_FOREGROUND);
										statusColor.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
										statusColor.setBorderRight(HSSFCellStyle.BORDER_THIN);            
										statusColor.setBorderTop(HSSFCellStyle.BORDER_THIN);              
										statusColor.setBorderBottom(HSSFCellStyle.BORDER_THIN);
										
										status1 = headerRow.createCell(3);
										status1.setCellValue(Integer.parseInt(statusTracking.getActualStatusPercentage()));
										status1.setCellStyle(statusColor);
										
										keyProgress1 = headerRow.createCell(4);
										keyProgress1.setCellValue(statusTracking.getKeyProgress());
										keyProgress1.setCellStyle(activityStyle);
										
										reason = headerRow.createCell(5);
										reason.setCellValue(statusTracking.getReasonForGap());
										reason.setCellStyle(activityStyle);
										
										rectify = headerRow.createCell(6);
										rectify.setCellValue(statusTracking.getRectifyTheGap());
										rectify.setCellStyle(activityStyle);
										
										HSSFCell feedBack = headerRow.createCell(7);
										feedBack.setCellValue(statusTracking.getReviewerFeedback());
										feedBack.setCellStyle(activityStyle);
										
										HSSFCell reviewedByValue = headerRow.createCell(8);
										if(statusTracking.getReviewedBy() != null){
											reviewedByValue.setCellValue(statusTracking.getReviewedBy().getFirstName()+" "+statusTracking.getReviewedBy().getLastName());
										}else{
											reviewedByValue.setCellValue("");
										}
										reviewedByValue.setCellStyle(activityStyle);
			
										HSSFCell reviewedTimeValue = headerRow.createCell(9);
										if(statusTracking.getReviewedOn() != null && statusTracking.getReviewedOn() != ""){
											SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
											SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
											String reviewOn = statusTracking.getReviewedOn();
											try {
												Date reviewDate = sdf.parse(reviewOn);
												reviewedTimeValue.setCellValue(dateformat.format(reviewDate));
											} catch (ParseException e) {
												e.printStackTrace();
											}
											
										}else{
											reviewedTimeValue.setCellValue("");
										}
										reviewedTimeValue.setCellStyle(activityStyle);
										temp = 1;
									}
								}
								if(temp == 0){
									status1 = headerRow.createCell(3);
									status1.setCellValue("");
									status1.setCellStyle(activityStyle);
									
									keyProgress1 = headerRow.createCell(4);
									keyProgress1.setCellValue("");
									keyProgress1.setCellStyle(activityStyle);
									
									reason = headerRow.createCell(5);
									reason.setCellValue("");
									reason.setCellStyle(activityStyle);
									
									rectify = headerRow.createCell(6);
									rectify.setCellValue("");
									rectify.setCellStyle(activityStyle);
									
									HSSFCell feedbackReviewer = headerRow.createCell(7);
									feedbackReviewer.setCellValue("");
									feedbackReviewer.setCellStyle(activityStyle);
									
									HSSFCell reviewedByEmpty = headerRow.createCell(8);
									reviewedByEmpty.setCellValue("");
									reviewedByEmpty.setCellStyle(activityStyle);

									HSSFCell reviewedTimeEmpty = headerRow.createCell(9);
									reviewedTimeEmpty.setCellValue("");
									reviewedTimeEmpty.setCellStyle(activityStyle);
								}
							}
						}
						rowValue++;
					}
			}
			}
		hssfSheet.autoSizeColumn(0);
		hssfSheet.autoSizeColumn(4);
		hssfSheet.autoSizeColumn(5);
		hssfSheet.autoSizeColumn(6);
		hssfSheet.autoSizeColumn(7);
		hssfSheet.autoSizeColumn(8);
		hssfSheet.autoSizeColumn(9);
		
		/**
		 * @author pushpa
		 * Add Observation Sheet 
		 */
		
		HSSFSheet observationSheet = superAdminHssfWorkbook.createSheet("Observation");
		
		Font boldFont = superAdminHssfWorkbook.createFont();
		boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		boldFont.setColor(IndexedColors.BLACK.getIndex());
		
		CellStyle cellStyle = superAdminHssfWorkbook.createCellStyle();
		cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderRight(CellStyle.BORDER_THIN);
		cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderTop(CellStyle.BORDER_THIN);
		cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		cellStyle.setFont(boldFont);

		CellStyle cellStyle2 = superAdminHssfWorkbook.createCellStyle();
		cellStyle2.setAlignment(CellStyle.ALIGN_LEFT);
		cellStyle2.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyle2.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle2.setBorderLeft(CellStyle.BORDER_THIN);
		cellStyle2.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle2.setBorderRight(CellStyle.BORDER_THIN);
		cellStyle2.setRightBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle2.setBorderTop(CellStyle.BORDER_THIN);
		cellStyle2.setTopBorderColor(IndexedColors.BLACK.getIndex());
		
		
//		Read Observation data
		List<SubmitForReview> finalSubmitforReviewList = new ArrayList<SubmitForReview>();
		ReportingPeriod reportingPeriod = reportingPeriodService.getById(reportingPeriodId);
		List<UserRole> userRoles =  user.getUserRoles();
		for (UserRole userRole : userRoles) {
			if(userRole.getName().equals("STATUS_APPROVER") ||
					userRole.getName().equals("SUPER_ADMIN") ||
					userRole.getName().equals("PROJECT_ADMIN") ||
					userRole.getName().equals("PROJECT_PLANNER")){
				List<SubmitForReview> listSubmitForReviews = submitForReviewService.findByReportingPeriodAndUserLevel(reportingPeriod, 2);
				if(listSubmitForReviews != null && listSubmitForReviews.size() > 0){
					finalSubmitforReviewList.addAll(listSubmitForReviews);
				}
			}
		}
		
//		Set row values in excel sheet
		int Startrow = 0;
		for (int i = 0; i < finalSubmitforReviewList.size(); i++) {
			
			SubmitForReview submitForReview = finalSubmitforReviewList.get(i);
			
			HSSFRow userAgencyRow = observationSheet.createRow(Startrow);
			Cell userCell = userAgencyRow.createCell(0);
			String userAgencyName = "By " + submitForReview.getUser().getFirstName();
			if(submitForReview.getUser().getLastName() != null){
				userAgencyName += " " + submitForReview.getUser().getLastName();
			}
			if(submitForReview.getAgency().getName() != null){
				userAgencyName += " ( " + submitForReview.getAgency().getName() + " ) ";
			}
			userCell.setCellValue(userAgencyName);
			userCell.setCellStyle(cellStyle);
			
			Startrow = Startrow + 1;
			HSSFRow keyLearningRow = observationSheet.createRow(Startrow);
			Cell keyLearningCellLabel = keyLearningRow.createCell(1);
			keyLearningCellLabel.setCellValue("Key Learnings");
			keyLearningCellLabel.setCellStyle(cellStyle);
			Cell keyLearningCellValue = keyLearningRow.createCell(2);
			keyLearningCellValue.setCellValue(submitForReview.getKeyLearning());
			keyLearningCellValue.setCellStyle(cellStyle2);
			
			Startrow = Startrow + 1;
			HSSFRow keyChallengesRow = observationSheet.createRow(Startrow);
			Cell keyChallengesCellLabel = keyChallengesRow.createCell(1);
			keyChallengesCellLabel.setCellValue("Key Challanges");
			keyChallengesCellLabel.setCellStyle(cellStyle);
			Cell keyChallengesCellValue = keyChallengesRow.createCell(2);
			keyChallengesCellValue.setCellValue(submitForReview.getKeyChallenge());
			keyChallengesCellValue.setCellStyle(cellStyle2);
			
			Startrow = Startrow + 1;
			HSSFRow bestPracticesRow = observationSheet.createRow(Startrow);
			Cell bestPracticesCellLabel = bestPracticesRow.createCell(1);
			bestPracticesCellLabel.setCellValue("Best Practices");
			bestPracticesCellLabel.setCellStyle(cellStyle);
			Cell bestPracticesCellValue = bestPracticesRow.createCell(2);
			bestPracticesCellValue.setCellValue(submitForReview.getBestPractice());
			bestPracticesCellValue.setCellStyle(cellStyle2);
			
			Startrow = Startrow + 1;
			HSSFRow supportNeedsRow = observationSheet.createRow(Startrow);
			Cell supportNeedsCellLabel = supportNeedsRow.createCell(1);
			supportNeedsCellLabel.setCellValue("Support Needs");
			supportNeedsCellLabel.setCellStyle(cellStyle);
			Cell supportNeedsCellValue = supportNeedsRow.createCell(2);
			supportNeedsCellValue.setCellValue(submitForReview.getSupportNeeds());
			supportNeedsCellValue.setCellStyle(cellStyle2);
			
			Startrow = Startrow + 1;
			HSSFRow submittedTimeRow = observationSheet.createRow(Startrow);
			Cell submittedTimeCellLabel = submittedTimeRow.createCell(1);
			submittedTimeCellLabel.setCellValue("Submitted Time");
			submittedTimeCellLabel.setCellStyle(cellStyle);
			Cell submittedTimeCellValue = submittedTimeRow.createCell(2);
			SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			Date submittedTime = submitForReview.getSubmit_dateTime();
			String submittedTimeString = (submittedTime != null) ? sd.format(submittedTime) : "";
			submittedTimeCellValue.setCellValue(submittedTimeString);
			submittedTimeCellValue.setCellStyle(cellStyle2);
		}
		observationSheet.autoSizeColumn(0);
		observationSheet.autoSizeColumn(1);
		observationSheet.autoSizeColumn(2);
//		End Observation Sheet Details creation
		
		try {
			if (superAdminHssfWorkbook != null) {
				superAdminHssfWorkbook.write(fileOutputStream);
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
	
	/**
	 * Approver Common function to download report
	 * @param user
	 * @param listStrategicPillers
	 * @param reportingPeriodValues
	 * @param listThemes
	 * @param listOutcomes
	 * @param listOutputs
	 * @param listKeyActivities
	 * @param subActivities
	 * @param partnerAgencyId
	 * @return
	 */
	private HSSFWorkbook approverActualReportDownloadCommonFunction(
			User user, List<StrategicPillar> listStrategicPillers,
			List<ReportingPeriod> reportingPeriodList, List<Theme> listThemes,
			List<Outcome> listOutcomes, List<Output> listOutputs,
			List<KeyActivity> listKeyActivities, List<SubActivity> subActivities,String reportingPeriodLength) {

		Integer rowValue = 0;
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		HSSFSheet hssfSheet = hssfWorkbook.createSheet("Actual Report Details");
		
		HSSFRow headerRow = hssfSheet.createRow(0);
		
		Font boldFont = hssfWorkbook.createFont();
		boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		boldFont.setColor(IndexedColors.BLACK.getIndex());
		
		Font mainFont = hssfWorkbook.createFont();
		mainFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		mainFont.setFontHeightInPoints((short) 20);
		mainFont.setColor(IndexedColors.BLACK.getIndex());
		
		CellStyle mainHeaderStyle = hssfWorkbook.createCellStyle();
		mainHeaderStyle.setBorderBottom(CellStyle.BORDER_THIN);
		mainHeaderStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		mainHeaderStyle.setBorderLeft(CellStyle.BORDER_THIN);
		mainHeaderStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		mainHeaderStyle.setBorderRight(CellStyle.BORDER_THIN);
		mainHeaderStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		mainHeaderStyle.setBorderTop(CellStyle.BORDER_THIN);
		mainHeaderStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		mainHeaderStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		mainHeaderStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		mainHeaderStyle.setAlignment(CellStyle.ALIGN_CENTER);
		mainHeaderStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		mainHeaderStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		mainHeaderStyle.setFont(mainFont);
		mainHeaderStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
		mainHeaderStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
		mainHeaderStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
		mainHeaderStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		
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
		
		CellStyle tableHeaderStyleLeft = hssfWorkbook.createCellStyle();
		tableHeaderStyleLeft.setBorderBottom(CellStyle.BORDER_THIN);
		tableHeaderStyleLeft.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		tableHeaderStyleLeft.setBorderLeft(CellStyle.BORDER_THIN);
		tableHeaderStyleLeft.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		tableHeaderStyleLeft.setBorderRight(CellStyle.BORDER_THIN);
		tableHeaderStyleLeft.setRightBorderColor(IndexedColors.BLACK.getIndex());
		tableHeaderStyleLeft.setBorderTop(CellStyle.BORDER_THIN);
		tableHeaderStyleLeft.setTopBorderColor(IndexedColors.BLACK.getIndex());
		tableHeaderStyleLeft.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		tableHeaderStyleLeft.setFillPattern(CellStyle.SOLID_FOREGROUND);
		tableHeaderStyleLeft.setAlignment(CellStyle.ALIGN_LEFT);
		tableHeaderStyleLeft.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		tableHeaderStyleLeft.setFillPattern(CellStyle.SOLID_FOREGROUND);
		tableHeaderStyleLeft.setFont(boldFont);
		tableHeaderStyleLeft.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
		tableHeaderStyleLeft.setBorderRight(HSSFCellStyle.BORDER_THIN);            
		tableHeaderStyleLeft.setBorderTop(HSSFCellStyle.BORDER_THIN);              
		tableHeaderStyleLeft.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		
		CellStyle tableHeaderStyle1 = hssfWorkbook.createCellStyle();
		Font font = hssfWorkbook.createFont();
		font.setColor(IndexedColors.RED.getIndex());
		tableHeaderStyle1.setFont(font);
		
		//Create First Row
		HSSFCell seqNumber = headerRow.createCell(0);
		seqNumber.setCellValue("National Drug Control Master Plan (NDCMP)");
		seqNumber.setCellStyle(mainHeaderStyle);
		seqNumber.setCellType(Cell.CELL_TYPE_STRING);
		headerRow.setHeight((short)700);
		hssfSheet.addMergedRegion(new CellRangeAddress(0,0,0,12));
		
		HSSFCellStyle headerstyle = hssfWorkbook.createCellStyle();
		headerstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
		headerstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
		headerstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
		headerstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		headerstyle.setFont(boldFont);
		
		//Second Row for set Agency
		headerRow = hssfSheet.createRow(1);
		HSSFCellStyle agencystyle = hssfWorkbook.createCellStyle();
		agencystyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
		agencystyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
		agencystyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
		agencystyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		rowValue++;	
		headerRow = hssfSheet.createRow(rowValue);
		HSSFCell nameOfAgency = headerRow.createCell(0);
		nameOfAgency.setCellValue("Report By : "+user.getFirstName() +" "+user.getLastName());
		nameOfAgency.setCellStyle(tableHeaderStyleLeft);
		nameOfAgency.setCellType(Cell.CELL_TYPE_STRING);
		hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,0,1));
		
		HSSFCell agencyName  = headerRow.createCell(1);
		agencyName.setCellType(Cell.CELL_TYPE_STRING);

		HSSFCell reportingPeriod  = headerRow.createCell(2);
		StringBuffer reportingPeriods = new StringBuffer();
		if(reportingPeriodLength.equals("0")){
			reportingPeriods.append("Reporting Period : "+" All ");
		}else{
			for (ReportingPeriod period : reportingPeriodList) {
				if(reportingPeriods.length() == 0){
					reportingPeriods.append("Reporting Period : "+period.getYear()+" - "+period.getName());
				}else{
					reportingPeriods.append(" , "+period.getYear()+" - "+period.getName());
				}
			}
		}
		
		reportingPeriod.setCellValue(reportingPeriods.toString());
		reportingPeriod.setCellStyle(tableHeaderStyle);
		reportingPeriod.setCellType(Cell.CELL_TYPE_STRING);
		hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,2,7));
		
		
		SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();
		
		HSSFCell reportTime = headerRow.createCell(8);
		reportTime.setCellValue("Report Time : "+sd.format(date));
		reportTime.setCellStyle(tableHeaderStyle);
		reportTime.setCellType(Cell.CELL_TYPE_STRING);
		hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,8,12));
		
		
		HSSFCellStyle row2=hssfWorkbook.createCellStyle();
		row2.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
		row2.setBorderRight(HSSFCellStyle.BORDER_THIN);            
		row2.setBorderTop(HSSFCellStyle.BORDER_THIN);              
		row2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		
		String[] sColor=hex2Rgb("#33cccc").split(",");
		String[] yColor=hex2Rgb("#FFCC99").split(",");
		String[] keyBarColor=hex2Rgb("#CCFFCC").split(",");
		
		int c1=Integer.parseInt(sColor[0]);
		int c2=Integer.parseInt(sColor[1]);
		int c3=Integer.parseInt(sColor[2]);
		
		int yc1=Integer.parseInt(yColor[0]);
		int yc2=Integer.parseInt(yColor[1]);
		int yc3=Integer.parseInt(yColor[2]);
		
		int kc1=Integer.parseInt(keyBarColor[0]);
		int kc2=Integer.parseInt(keyBarColor[1]);
		int kc3=Integer.parseInt(keyBarColor[2]);
		
		HSSFCellStyle style2=hssfWorkbook.createCellStyle();
		HSSFColor skyBlue =  setColor(hssfWorkbook,(byte) c1, (byte) c2,(byte) c3);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);            
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);              
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setFillForegroundColor(skyBlue.getIndex());
		style2.setFont(boldFont);
		style2.setFillPattern(CellStyle.SOLID_FOREGROUND);
		
		HSSFCellStyle keyBarstyle2=hssfWorkbook.createCellStyle();
		HSSFColor green =  setColor(hssfWorkbook,(byte) kc1, (byte) kc2,(byte) kc3);
		keyBarstyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
		keyBarstyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);            
		keyBarstyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);              
		keyBarstyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		keyBarstyle2.setFillForegroundColor(green.getIndex());
		keyBarstyle2.setFont(boldFont);
		keyBarstyle2.setFillPattern(CellStyle.SOLID_FOREGROUND);
		rowValue++;
		for (StrategicPillar strategicPillar : listStrategicPillers) {
			headerRow = hssfSheet.createRow(rowValue);
			HSSFCell strategicName = headerRow.createCell(0);
			strategicName.setCellValue("Strategic Pillar  "+strategicPillar.getId()+" : "+strategicPillar.getName());
			strategicName.setCellStyle(style2);
			strategicName.setCellType(Cell.CELL_TYPE_STRING);
			
			hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,0,12));
			
			rowValue++;
			List<Theme> themesByStrategicPillar = themeService.getThemeByStrategicPillar(strategicPillar);
			for (Theme theme : themesByStrategicPillar) {
				for (Theme themeByAgencyId : listThemes) {
					if(theme.getId() == themeByAgencyId.getId()){
						headerRow = hssfSheet.createRow(rowValue);
						
						HSSFCellStyle yellowStyle=hssfWorkbook.createCellStyle();
						yellowStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
						yellowStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
						yellowStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
						yellowStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
						HSSFColor sandal =  setColor(hssfWorkbook,(byte) yc1, (byte) yc2,(byte) yc3);
						yellowStyle.setFillForegroundColor(sandal.getIndex());
						yellowStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
						yellowStyle.setFont(boldFont);
						
						HSSFCell themeName = headerRow.createCell(0);
						themeName.setCellValue("Theme : "+theme.getName());
						themeName.setCellStyle(yellowStyle);
						themeName.setCellType(Cell.CELL_TYPE_STRING);
						
						hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,0,12));
						
						rowValue++;
						List<Outcome> outcomes2 = outcomeService.getByTheme(themeByAgencyId);
						for (Outcome outcome : outcomes2) {
							for (Outcome outcomeByAgencyId : listOutcomes) {
								if(outcomeByAgencyId.getId() == outcome.getId()){
									Font outcomeFont = hssfWorkbook.createFont();
									outcomeFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
									outcomeFont.setColor(IndexedColors.BLUE.getIndex());
									
									HSSFCellStyle outcomeStyle=hssfWorkbook.createCellStyle();
									HSSFColor outcomeSandal = setColor(hssfWorkbook,(byte) yc1, (byte) yc2,(byte) yc3);
									outcomeStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
									outcomeStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
									outcomeStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
									outcomeStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
									outcomeStyle.setFillForegroundColor(outcomeSandal.getIndex());
									outcomeStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
									outcomeStyle.setFont(outcomeFont);
									
									headerRow = hssfSheet.createRow(rowValue);
									HSSFCell outcomeName = headerRow.createCell(0);
									outcomeName.setCellValue("Outcome "+outcome.getSequenceNumber()+ " : "+outcome.getName());
									outcomeName.setCellStyle(outcomeStyle);
									outcomeName.setCellType(Cell.CELL_TYPE_STRING);
									
									hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,0,12));
									 
									rowValue++;
									List<Output> outputs2 = outputServices.getByOutcome(outcomeByAgencyId);
									for (Output output : outputs2) {
										for (Output outputByAgencyid : listOutputs) {	
											if(outputByAgencyid.getId() == output.getId()){
												Font outputFont = hssfWorkbook.createFont();
												outputFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
												outputFont.setColor(IndexedColors.RED.getIndex());
												
												HSSFCellStyle outputStyle=hssfWorkbook.createCellStyle();
												HSSFColor outputSandal = setColor(hssfWorkbook,(byte) yc1, (byte) yc2,(byte) yc3);
												outputStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
												outputStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
												outputStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
												outputStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
												outputStyle.setFillForegroundColor(outputSandal.getIndex());
												outputStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
												outputStyle.setFont(outputFont);
												
												HSSFCellStyle activityStyle=hssfWorkbook.createCellStyle();
												activityStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
												activityStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
												activityStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
												activityStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
												
												headerRow = hssfSheet.createRow(rowValue);
												HSSFCell outputName = headerRow.createCell(0);
												outputName.setCellValue("Output "+output.getSequenceNumber()+" : "+output.getOutput());
												outputName.setCellStyle(outputStyle);
												outputName.setCellType(Cell.CELL_TYPE_STRING);
												
												hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,0,12));
												
												rowValue++;
												List<Indicator> indicators = indicatorService.findByOutput(outputByAgencyid);
												for (Indicator indicator : indicators) {
													headerRow = hssfSheet.createRow(rowValue);
													HSSFCell indicatorName = headerRow.createCell(0);
													indicatorName.setCellValue("Indicators : "+indicator.getMessage());
													indicatorName.setCellStyle(yellowStyle);
													indicatorName.setCellType(Cell.CELL_TYPE_STRING);
													hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,0,12));
													
													rowValue++;
												}
												//int temp = 0;
												List<Target> targets = targetService.findByOutput(outputByAgencyid);
												for (Target target : targets) {
													headerRow = hssfSheet.createRow(rowValue);
													
													HSSFCell targetName = headerRow.createCell(0);
													targetName.setCellValue("Targets : "+target.getMessage());
													targetName.setCellStyle(yellowStyle);
													targetName.setCellType(Cell.CELL_TYPE_STRING);
													hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,0,12));
													rowValue++;
												}
												headerRow = hssfSheet.createRow(rowValue);
												HSSFCell seqenceNumber = headerRow.createCell(0);
												seqenceNumber.setCellValue("S.No");
												seqenceNumber.setCellStyle(keyBarstyle2);
												seqenceNumber.setCellType(Cell.CELL_TYPE_STRING); 
												
												HSSFCell keyActivitys = headerRow.createCell(1);
												keyActivitys.setCellValue("Key Activities");
												keyActivitys.setCellStyle(keyBarstyle2);
												keyActivitys.setCellType(Cell.CELL_TYPE_STRING);

												HSSFCell subActivitys  = headerRow.createCell(2);
												subActivitys.setCellValue("Sub Activities");
												subActivitys.setCellStyle(keyBarstyle2);
												subActivitys.setCellType(Cell.CELL_TYPE_STRING);
												
												HSSFCell status = headerRow.createCell(3);
												status.setCellValue("Status");
												status.setCellStyle(keyBarstyle2);
												status.setCellType(Cell.CELL_TYPE_STRING);
												
												HSSFCell completionPercentage = headerRow.createCell(4);
												completionPercentage.setCellValue("Completion Percentage");
												completionPercentage.setCellStyle(keyBarstyle2);
												completionPercentage.setCellType(Cell.CELL_TYPE_STRING);
												
												HSSFCell keyProgress = headerRow.createCell(5);
												keyProgress.setCellValue("Key Progress");
												keyProgress.setCellStyle(keyBarstyle2);
												keyProgress.setCellType(Cell.CELL_TYPE_STRING);
												
												HSSFCell reasongap = headerRow.createCell(6);
												reasongap.setCellValue("Reasons for gap if any");
												reasongap.setCellStyle(keyBarstyle2);
												reasongap.setCellType(Cell.CELL_TYPE_STRING);
												
												HSSFCell reactify = headerRow.createCell(7);
												reactify.setCellValue("Plan of Action to rectify the gap");
												reactify.setCellStyle(keyBarstyle2);
												reactify.setCellType(Cell.CELL_TYPE_STRING);
												
												HSSFCell feedbac = headerRow.createCell(8);
												feedbac.setCellValue("Reviewer Feedback");
												feedbac.setCellStyle(keyBarstyle2);
												feedbac.setCellType(Cell.CELL_TYPE_STRING);
												hssfSheet.setColumnWidth(7,10000);
												
												HSSFCell lastUpdatedByHeading = headerRow.createCell(9);
												lastUpdatedByHeading.setCellValue("Last Updated By");
												lastUpdatedByHeading.setCellStyle(keyBarstyle2);
												lastUpdatedByHeading.setCellType(Cell.CELL_TYPE_STRING);
												
												HSSFCell lastUpdatedOnHeading = headerRow.createCell(10);
												lastUpdatedOnHeading.setCellValue("Last Updated Time");
												lastUpdatedOnHeading.setCellStyle(keyBarstyle2);
												lastUpdatedOnHeading.setCellType(Cell.CELL_TYPE_STRING);
												
												HSSFCell reviewedByHeading = headerRow.createCell(11);
												reviewedByHeading.setCellValue("Reviewed By");
												reviewedByHeading.setCellStyle(keyBarstyle2);
												reviewedByHeading.setCellType(Cell.CELL_TYPE_STRING);

												HSSFCell reviewedTimeHeading = headerRow.createCell(12);
												reviewedTimeHeading.setCellValue("Reviewed Time");
												reviewedTimeHeading.setCellStyle(keyBarstyle2);
												reviewedTimeHeading.setCellType(Cell.CELL_TYPE_STRING);
												
												rowValue++;
												List<KeyActivity> activities = keyActivityService.findByOutput(outputByAgencyid);
												for (KeyActivity keyActivity : activities) {
													for (KeyActivity keyActivityByReportingPeriod : listKeyActivities) {
														if(keyActivityByReportingPeriod.getId() == keyActivity.getId() || keyActivityByReportingPeriod.getId().equals(keyActivity.getId())){
															headerRow = hssfSheet.createRow(rowValue);
															HSSFCell keySeqNumber = headerRow.createCell(0);
															keySeqNumber.setCellValue(keyActivity.getSequenceNumber());
															keySeqNumber.setCellStyle(activityStyle);
															
															HSSFCell keyActivityName = headerRow.createCell(1);
															keyActivityName.setCellValue(keyActivity.getName());
															keyActivityName.setCellStyle(activityStyle);
															
															CellStyle cellStylekeyName = hssfWorkbook.createCellStyle();
															cellStylekeyName.setWrapText(true);//set wraper text
															keyActivityName.setCellStyle(cellStylekeyName);
															headerRow.setHeightInPoints((2*hssfSheet.getDefaultRowHeightInPoints()));
															hssfSheet.setColumnWidth(1,11000);
															
															HSSFCell subActivity = headerRow.createCell(2);
															subActivity.setCellValue("");
															subActivity.setCellStyle(activityStyle);
															
															HSSFCell status1 = headerRow.createCell(3);
															status1.setCellValue("");
															status1.setCellStyle(activityStyle);
															
															HSSFCell comPercent1 = headerRow.createCell(4);
															comPercent1.setCellValue("");
															comPercent1.setCellStyle(activityStyle);
															
															HSSFCell keyProgress1 = headerRow.createCell(5);
															keyProgress1.setCellValue("");
															keyProgress1.setCellStyle(activityStyle);
															
															HSSFCell reason = headerRow.createCell(6);
															reason.setCellValue("");
															reason.setCellStyle(activityStyle);
															
															HSSFCell rectify = headerRow.createCell(7);
															rectify.setCellValue("");
															rectify.setCellStyle(activityStyle);
															
															HSSFCell feedback = headerRow.createCell(8);
															feedback.setCellValue("");
															feedback.setCellStyle(activityStyle);
															
															HSSFCell lastUpBY1 = headerRow.createCell(9);
															lastUpBY1.setCellValue("");
															lastUpBY1.setCellStyle(activityStyle);
															
															HSSFCell lastUpON1 = headerRow.createCell(10);
															lastUpON1.setCellValue("");
															lastUpON1.setCellStyle(activityStyle);
															
															HSSFCell revBY1 = headerRow.createCell(11);
															revBY1.setCellValue("");
															revBY1.setCellStyle(activityStyle);
															
															HSSFCell revON1 = headerRow.createCell(12);
															revON1.setCellValue("");
															revON1.setCellStyle(activityStyle);
															
															rowValue++;
															List<SubActivity> subActivities3 = subActivityService.findByKeyActivity(keyActivityByReportingPeriod);
															if(subActivities3.size() > 0){
																for(SubActivity subActivity1 : subActivities3){
																	for (SubActivity subActivity2 : subActivities) {
																		if(subActivity2.getId() == subActivity1.getId() || subActivity2.getId().equals(subActivity1.getId())){
																			Integer currentRowValue = rowValue;
																			headerRow = hssfSheet.createRow(rowValue);
																			HSSFCell subSeySeqNumber = headerRow.createCell(0);
																			subSeySeqNumber.setCellValue(subActivity2.getSequenceNumber());
																			subSeySeqNumber.setCellStyle(activityStyle);
																			
																			HSSFCell keyActivityNameForSubactivity = headerRow.createCell(1);
																			keyActivityNameForSubactivity.setCellValue("");
																			keyActivityNameForSubactivity.setCellStyle(activityStyle);
																			
																			HSSFCell subActivityName = headerRow.createCell(2);
																			subActivityName.setCellValue(subActivity2.getName());
																			subActivityName.setCellStyle(activityStyle);
																			
																			CellStyle cellStyle1 = hssfWorkbook.createCellStyle();
																			cellStyle1.setWrapText(true);//set wraper text
																			cellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
																			cellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);            
																			cellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);              
																			cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
																			subActivityName.setCellStyle(cellStyle1);
																			headerRow.setHeightInPoints((2*hssfSheet.getDefaultRowHeightInPoints()));
																			hssfSheet.setColumnWidth(2,11000);
																			
																			List<StatusTracking> subActivityStatusTracking = new ArrayList<StatusTracking>();
																			for (ReportingPeriod reportingPeriodValues : reportingPeriodList) {
																				List<StatusTracking> lastUpdatedStatusReporting = statusTrackingRepository.findBysubActivityAndReportingPeriodAndUserLevel(subActivity1, reportingPeriodValues, 2);
																				if(lastUpdatedStatusReporting != null && lastUpdatedStatusReporting.size() > 0){
																					subActivityStatusTracking = new ArrayList<StatusTracking>();
																					subActivityStatusTracking = statusTrackingRepository.findBysubActivityAndReportingPeriodAndUserLevel(subActivity1, reportingPeriodValues, 2);
																				}
																			}
																			int temp1 = 0;
																			if(subActivityStatusTracking != null && subActivityStatusTracking.size() > 0){
																				for (StatusTracking statusTracking : subActivityStatusTracking) {
																					HSSFCellStyle	statusColor=hssfWorkbook.createCellStyle();
																					
																					String[] staColor=hex2Rgb(statusTracking.getActualStatusColor()).split(",");
																					int ac1=Integer.parseInt(staColor[0]);
																					int ac2=Integer.parseInt(staColor[1]);
																					int ac3=Integer.parseInt(staColor[2]);
																					
																					HSSFPalette palette = hssfWorkbook.getCustomPalette();
																					HSSFColor myColor = palette.findSimilarColor(ac1, ac2, ac3);
																					short palIndex = myColor.getIndex();
																					
																					statusColor.setFillForegroundColor(palIndex);
																					statusColor.setFillPattern(CellStyle.SOLID_FOREGROUND);
																					statusColor.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
																					statusColor.setBorderRight(HSSFCellStyle.BORDER_THIN);            
																					statusColor.setBorderTop(HSSFCellStyle.BORDER_THIN);              
																					statusColor.setBorderBottom(HSSFCellStyle.BORDER_THIN);
																					
																					HSSFCell status2 = headerRow.createCell(3);
																					status2.setCellStyle(statusColor);
																					
																					HSSFCell comPercent2 = headerRow.createCell(4);
																					comPercent2.setCellValue(Integer.parseInt(statusTracking.getActualStatusPercentage()));
																					comPercent2.setCellStyle(activityStyle);
																					
																					HSSFCell keyProgress2 = headerRow.createCell(5);
																					keyProgress2.setCellValue(statusTracking.getKeyProgress());
																					keyProgress2.setCellStyle(activityStyle);
																					
																					HSSFCell reason1 = headerRow.createCell(6);
																					reason1.setCellValue(statusTracking.getReasonForGap());
																					reason1.setCellStyle(activityStyle);
																					
																					HSSFCell rectify1 = headerRow.createCell(7);
																					rectify1.setCellValue(statusTracking.getRectifyTheGap());
																					rectify1.setCellStyle(activityStyle);
																					
																					HSSFCell feedback1 = headerRow.createCell(8);
																					feedback1.setCellValue(statusTracking.getReviewerFeedback());
																					feedback1.setCellStyle(activityStyle);
																					
																					HSSFCell lastUpadatedByValue = headerRow.createCell(9);
																					if(statusTracking.getUser() != null){
																						lastUpadatedByValue.setCellValue(statusTracking.getUser().getFirstName()+" "+statusTracking.getUser().getLastName()+" ( "+statusTracking.getAgency().getName()+" )");
																					}else{
																						lastUpadatedByValue.setCellValue("");
																					}
																					lastUpadatedByValue.setCellStyle(activityStyle);
																					
																					HSSFCell lastUpdatedOnValue = headerRow.createCell(10);
																					if(statusTracking.getModificationTime() != null){
																						SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
																						try {
																							Date lastUpdatedOn = statusTracking.getModificationTime();
																							lastUpdatedOnValue.setCellValue(dateformat.format(lastUpdatedOn));
																						} catch (Exception e) {
																							e.printStackTrace();
																						}
																						
																					}else{
																						lastUpdatedOnValue.setCellValue("");
																					}
																					lastUpdatedOnValue.setCellStyle(activityStyle);
																					
																					HSSFCell reviewedByValue = headerRow.createCell(11);
																					if(statusTracking.getReviewedBy() != null){
																						reviewedByValue.setCellValue(statusTracking.getReviewedBy().getFirstName()+" "+statusTracking.getReviewedBy().getLastName());
																					}else{
																						reviewedByValue.setCellValue("");
																					}
																					reviewedByValue.setCellStyle(activityStyle);

																					HSSFCell reviewedTimeValue = headerRow.createCell(12);
																					if(statusTracking.getReviewedOn() != null && statusTracking.getReviewedOn() != ""){
																						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
																						SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
																						String reviewOn = statusTracking.getReviewedOn();
																						try {
																							Date reviewDate = sdf.parse(reviewOn);
																							reviewedTimeValue.setCellValue(dateformat.format(reviewDate));
																						} catch (ParseException e) {
																							e.printStackTrace();
																						}
																						
																					}else{
																						reviewedTimeValue.setCellValue("");
																					}
																					reviewedTimeValue.setCellStyle(activityStyle);
																					temp1 = 1;
																					//rowValue++;
																				}
																			}
																			if(temp1 == 0){
																				HSSFCell status3 = headerRow.createCell(3);
																				status3.setCellValue("");
																				status3.setCellStyle(activityStyle);
																				
																				HSSFCell comPercent3 = headerRow.createCell(4);
																				comPercent3.setCellValue("");
																				comPercent3.setCellStyle(activityStyle);
																				
																				HSSFCell keyProgress3 = headerRow.createCell(5);
																				keyProgress3.setCellValue("");
																				keyProgress3.setCellStyle(activityStyle);
																				
																				HSSFCell reason3 = headerRow.createCell(6);
																				reason3.setCellValue("");
																				reason3.setCellStyle(activityStyle);
																				
																				HSSFCell rectify3 = headerRow.createCell(7);
																				rectify3.setCellValue("");
																				rectify3.setCellStyle(activityStyle);
																				
																				HSSFCell feedBack3 = headerRow.createCell(8);
																				feedBack3.setCellValue("");
																				feedBack3.setCellStyle(activityStyle);
																				
																				HSSFCell lastUpdatedBy = headerRow.createCell(9);
																				lastUpdatedBy.setCellValue("");
																				lastUpdatedBy.setCellStyle(activityStyle);
																				
																				HSSFCell lastUpdatedOn = headerRow.createCell(10);
																				lastUpdatedOn.setCellValue("");
																				lastUpdatedOn.setCellStyle(activityStyle);
																				
																				HSSFCell revBy = headerRow.createCell(11);
																				revBy.setCellValue("");
																				revBy.setCellStyle(activityStyle);
																				
																				HSSFCell revOn = headerRow.createCell(12);
																				revOn.setCellValue("");
																				revOn.setCellStyle(activityStyle);
																				//rowValue++;
																			}
																			int temp = 0;
																			for (ReportingPeriod reportingPeriodValues : reportingPeriodList) {
																				List<StatusTracking> statusTrackings = statusTrackingRepository.findBysubActivityAndReportingPeriodAndUserLevel(subActivity1, reportingPeriodValues, 2);
																				if(statusTrackings != null && statusTrackings.size() > 0){
																					for (StatusTracking statusTracking : statusTrackings) {
																						HSSFCellStyle	statusColor=hssfWorkbook.createCellStyle();
																						
																						String[] staColor=hex2Rgb(statusTracking.getActualStatusColor()).split(",");
																						int ac1=Integer.parseInt(staColor[0]);
																						int ac2=Integer.parseInt(staColor[1]);
																						int ac3=Integer.parseInt(staColor[2]);
																						
																						HSSFPalette palette = hssfWorkbook.getCustomPalette();
																						HSSFColor myColor = palette.findSimilarColor(ac1, ac2, ac3);
																						short palIndex = myColor.getIndex();
																						
																						statusColor.setFillForegroundColor(palIndex);
																						statusColor.setFillPattern(CellStyle.SOLID_FOREGROUND);
																						statusColor.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
																						statusColor.setBorderRight(HSSFCellStyle.BORDER_THIN);            
																						statusColor.setBorderTop(HSSFCellStyle.BORDER_THIN);              
																						statusColor.setBorderBottom(HSSFCellStyle.BORDER_THIN);
																						
																						if(currentRowValue == rowValue){
																							rowValue++;
																							headerRow = hssfSheet.createRow(rowValue);
																							
																							HSSFCell emptyKey = headerRow.createCell(0);
																							emptyKey.setCellValue(" ");
																							emptyKey.setCellStyle(activityStyle);
																							
																							HSSFCell emptySub = headerRow.createCell(1);
																							emptySub.setCellValue("");
																							emptySub.setCellStyle(activityStyle);
																							
																							HSSFCell reportingperiod = headerRow.createCell(2);
																							reportingperiod.setCellValue("   "+reportingPeriodValues.getYear() +" - "+reportingPeriodValues.getName());
																							reportingperiod.setCellStyle(activityStyle);
																							
																							HSSFCell statusReporting = headerRow.createCell(3);
																							statusReporting.setCellStyle(statusColor);
																							
																							HSSFCell comPercentReporting = headerRow.createCell(4);
																							comPercentReporting.setCellValue(Integer.parseInt(statusTracking.getActualStatusPercentage()));
																							comPercentReporting.setCellStyle(activityStyle);
																							
																							HSSFCell keyProgressReporting = headerRow.createCell(5);
																							keyProgressReporting.setCellValue(statusTracking.getKeyProgress());
																							keyProgressReporting.setCellStyle(activityStyle);
																							
																							HSSFCell reasonReporting = headerRow.createCell(6);
																							reasonReporting.setCellValue(statusTracking.getReasonForGap());
																							reasonReporting.setCellStyle(activityStyle);
																							
																							HSSFCell rectifyReporting = headerRow.createCell(7);
																							rectifyReporting.setCellValue(statusTracking.getRectifyTheGap());
																							rectifyReporting.setCellStyle(activityStyle);
																							
																							HSSFCell feedbackReporting = headerRow.createCell(8);
																							feedbackReporting.setCellValue(statusTracking.getReviewerFeedback());
																							feedbackReporting.setCellStyle(activityStyle);
																							
																							HSSFCell lastUpdatedByValueReporting = headerRow.createCell(9);
																							if(statusTracking.getUser() != null){
																								lastUpdatedByValueReporting.setCellValue(statusTracking.getUser().getFirstName()+" "+statusTracking.getUser().getLastName()+" ( "+statusTracking.getAgency().getName()+" )");
																							}else{
																								lastUpdatedByValueReporting.setCellValue("");
																							}
																							lastUpdatedByValueReporting.setCellStyle(activityStyle);

																							HSSFCell lastUpdatedOnValueReporting = headerRow.createCell(10);
																							if(statusTracking.getModificationTime() != null){
																								SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
																								try {
																									Date lastUpdatedOn = statusTracking.getModificationTime();
																									lastUpdatedOnValueReporting.setCellValue(dateformat.format(lastUpdatedOn));
																								} catch (Exception e) {
																									e.printStackTrace();
																								}
																								
																							}else{
																								lastUpdatedOnValueReporting.setCellValue("");
																							}
																							lastUpdatedOnValueReporting.setCellStyle(activityStyle);
																							
																							HSSFCell reviewedByValueReporting = headerRow.createCell(11);
																							if(statusTracking.getReviewedBy() != null){
																								reviewedByValueReporting.setCellValue(statusTracking.getReviewedBy().getFirstName()+" "+statusTracking.getReviewedBy().getLastName());
																							}else{
																								reviewedByValueReporting.setCellValue("");
																							}
																							reviewedByValueReporting.setCellStyle(activityStyle);

																							HSSFCell reviewedTimeValueReporting = headerRow.createCell(12);
																							if(statusTracking.getReviewedOn() != null && statusTracking.getReviewedOn() != ""){
																								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
																								SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
																								String reviewOn = statusTracking.getReviewedOn();
																								try {
																									Date reviewDate = sdf.parse(reviewOn);
																									reviewedTimeValueReporting.setCellValue(dateformat.format(reviewDate));
																								} catch (ParseException e) {
																									e.printStackTrace();
																								}
																								
																							}else{
																								reviewedTimeValueReporting.setCellValue("");
																							}
																							reviewedTimeValueReporting.setCellStyle(activityStyle);
																							temp = 1;
																						}else{
																							headerRow = hssfSheet.createRow(rowValue);
																							
																							HSSFCell emptyKey1 = headerRow.createCell(0);
																							emptyKey1.setCellValue(" ");
																							emptyKey1.setCellStyle(activityStyle);
																							
																							HSSFCell emptySub1 = headerRow.createCell(1);
																							emptySub1.setCellValue("");
																							emptySub1.setCellStyle(activityStyle);
																							
																							HSSFCell reportingPeriodValue = headerRow.createCell(2);
																							reportingPeriodValue.setCellValue("   "+reportingPeriodValues.getYear()+" - "+reportingPeriodValues.getName());
																							reportingPeriodValue.setCellStyle(activityStyle);
																							
																							HSSFCell statusReporting2 = headerRow.createCell(3);
																							statusReporting2.setCellStyle(statusColor);
																							
																							HSSFCell comPercentReporting2 = headerRow.createCell(4);
																							comPercentReporting2.setCellValue(Integer.parseInt(statusTracking.getActualStatusPercentage()));
																							comPercentReporting2.setCellStyle(activityStyle);
																							
																							HSSFCell keyProgressReporting2 = headerRow.createCell(5);
																							keyProgressReporting2.setCellValue(statusTracking.getKeyProgress());
																							keyProgressReporting2.setCellStyle(activityStyle);
																							
																							HSSFCell reasonReporting1 = headerRow.createCell(6);
																							reasonReporting1.setCellValue(statusTracking.getReasonForGap());
																							reasonReporting1.setCellStyle(activityStyle);
																							
																							HSSFCell rectifyReporting1 = headerRow.createCell(7);
																							rectifyReporting1.setCellValue(statusTracking.getRectifyTheGap());
																							rectifyReporting1.setCellStyle(activityStyle);
																							
																							HSSFCell feedbackReporting1 = headerRow.createCell(8);
																							feedbackReporting1.setCellValue(statusTracking.getReviewerFeedback());
																							feedbackReporting1.setCellStyle(activityStyle);
																							
																							HSSFCell lastUpdatedByValueReporting1 = headerRow.createCell(9);
																							if(statusTracking.getUser() != null){
																								lastUpdatedByValueReporting1.setCellValue(statusTracking.getUser().getFirstName()+" "+statusTracking.getUser().getLastName()+" ( "+statusTracking.getAgency().getName()+" )");
																							}else{
																								lastUpdatedByValueReporting1.setCellValue("");
																							}
																							lastUpdatedByValueReporting1.setCellStyle(activityStyle);

																							HSSFCell lastUpdatedOnValueReporting1 = headerRow.createCell(10);
																							if(statusTracking.getModificationTime() != null){
																								SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
																								try {
																									Date lastUpdatedOn = statusTracking.getModificationTime();
																									lastUpdatedOnValueReporting1.setCellValue(dateformat.format(lastUpdatedOn));
																								} catch (Exception e) {
																									e.printStackTrace();
																								}
																								
																							}else{
																								lastUpdatedOnValueReporting1.setCellValue("");
																							}
																							lastUpdatedOnValueReporting1.setCellStyle(activityStyle);
																							
																							HSSFCell reviewedByValueReporting1 = headerRow.createCell(11);
																							if(statusTracking.getReviewedBy() != null){
																								reviewedByValueReporting1.setCellValue(statusTracking.getReviewedBy().getFirstName()+" "+statusTracking.getReviewedBy().getLastName());
																							}else{
																								reviewedByValueReporting1.setCellValue("");
																							}
																							reviewedByValueReporting1.setCellStyle(activityStyle);

																							HSSFCell reviewedTimeValueReporting1 = headerRow.createCell(12);
																							if(statusTracking.getReviewedOn() != null && statusTracking.getReviewedOn() != ""){
																								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
																								SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
																								String reviewOn = statusTracking.getReviewedOn();
																								try {
																									Date reviewDate = sdf.parse(reviewOn);
																									reviewedTimeValueReporting1.setCellValue(dateformat.format(reviewDate));
																								} catch (ParseException e) {
																									e.printStackTrace();
																								}
																								
																							}else{
																								reviewedTimeValueReporting1.setCellValue("");
																							}
																							reviewedTimeValueReporting1.setCellStyle(activityStyle);
																							temp = 1;
//																							rowValue++;
																						}
																						
																					}
																					//rowValue++;
																				}
																				if(temp == 0){
																					HSSFCell status3 = headerRow.createCell(3);
																					status3.setCellValue("");
																					status3.setCellStyle(activityStyle);
																					
																					HSSFCell comPercent3 = headerRow.createCell(4);
																					comPercent3.setCellValue("");
																					comPercent3.setCellStyle(activityStyle);
																					
																					HSSFCell keyProgress3 = headerRow.createCell(5);
																					keyProgress3.setCellValue("");
																					keyProgress3.setCellStyle(activityStyle);
																					
																					HSSFCell reason3 = headerRow.createCell(6);
																					reason3.setCellValue("");
																					reason3.setCellStyle(activityStyle);
																					
																					HSSFCell rectify3 = headerRow.createCell(7);
																					rectify3.setCellValue("");
																					rectify3.setCellStyle(activityStyle);
																					
																					HSSFCell feedBack3 = headerRow.createCell(8);
																					feedBack3.setCellValue("");
																					feedBack3.setCellStyle(activityStyle);
																					
																					HSSFCell upBy = headerRow.createCell(9);
																					upBy.setCellValue("");
																					upBy.setCellStyle(activityStyle);
																					
																					HSSFCell modificationOn = headerRow.createCell(10);
																					modificationOn.setCellValue("");
																					modificationOn.setCellStyle(activityStyle);
																					
																					HSSFCell revBy = headerRow.createCell(11);
																					revBy.setCellValue("");
																					revBy.setCellStyle(activityStyle);
																					
																					HSSFCell revOn = headerRow.createCell(12);
																					revOn.setCellValue("");
																					revOn.setCellStyle(activityStyle);
																					//rowValue++;
																				}
																			}
																			rowValue++;
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
						}
					}
					
				}
			}
		}
		hssfSheet.autoSizeColumn(0);
		//hssfSheet.autoSizeColumn(1);
		//hssfSheet.autoSizeColumn(2);
		//hssfSheet.autoSizeColumn(3);
		hssfSheet.autoSizeColumn(4);
		hssfSheet.autoSizeColumn(5);
		hssfSheet.autoSizeColumn(6);
		hssfSheet.autoSizeColumn(7);
		hssfSheet.autoSizeColumn(8);
		hssfSheet.autoSizeColumn(9);
		hssfSheet.autoSizeColumn(10);
		hssfSheet.autoSizeColumn(11);
		hssfSheet.autoSizeColumn(12);
		return hssfWorkbook;
	}
	
	/**
	 * @author Prem
	 * @param user
	 * @param strategicPillars
	 * @param reportingPeriodValues
	 * @param themes
	 * @param outcomes
	 * @param outputs
	 * @param keyActivities
	 * @param subActivities
	 * @return
	 */
	private HSSFWorkbook superAdminActualReportDownloadCommonFunction(
			User user, LinkedHashSet<StrategicPillar> strategicPillars,
			ReportingPeriod reportingPeriodValues, LinkedHashSet<Theme> themes,
			LinkedHashSet<Outcome> outcomes, LinkedHashSet<Output> outputs,
			LinkedHashSet<KeyActivity> keyActivities, List<SubActivity> subActivities,Integer partnerAgencyId) {

		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		HSSFSheet hssfSheet = hssfWorkbook.createSheet("Actual Report Details");
		
		HSSFRow headerRow = hssfSheet.createRow(0);
		
		Font boldFont = hssfWorkbook.createFont();
		boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		boldFont.setColor(IndexedColors.BLACK.getIndex());
		
		Font mainFont = hssfWorkbook.createFont();
		mainFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		mainFont.setFontHeightInPoints((short) 20);
		mainFont.setColor(IndexedColors.BLACK.getIndex());
		
		CellStyle mainHeaderStyle = hssfWorkbook.createCellStyle();
		mainHeaderStyle.setBorderBottom(CellStyle.BORDER_THIN);
		mainHeaderStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		mainHeaderStyle.setBorderLeft(CellStyle.BORDER_THIN);
		mainHeaderStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		mainHeaderStyle.setBorderRight(CellStyle.BORDER_THIN);
		mainHeaderStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		mainHeaderStyle.setBorderTop(CellStyle.BORDER_THIN);
		mainHeaderStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		mainHeaderStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		mainHeaderStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		mainHeaderStyle.setAlignment(CellStyle.ALIGN_CENTER);
		mainHeaderStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		mainHeaderStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		mainHeaderStyle.setFont(mainFont);
		mainHeaderStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
		mainHeaderStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
		mainHeaderStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
		mainHeaderStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		
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
		
		CellStyle tableHeaderStyleLeft = hssfWorkbook.createCellStyle();
		tableHeaderStyleLeft.setBorderBottom(CellStyle.BORDER_THIN);
		tableHeaderStyleLeft.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		tableHeaderStyleLeft.setBorderLeft(CellStyle.BORDER_THIN);
		tableHeaderStyleLeft.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		tableHeaderStyleLeft.setBorderRight(CellStyle.BORDER_THIN);
		tableHeaderStyleLeft.setRightBorderColor(IndexedColors.BLACK.getIndex());
		tableHeaderStyleLeft.setBorderTop(CellStyle.BORDER_THIN);
		tableHeaderStyleLeft.setTopBorderColor(IndexedColors.BLACK.getIndex());
		tableHeaderStyleLeft.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		tableHeaderStyleLeft.setFillPattern(CellStyle.SOLID_FOREGROUND);
		tableHeaderStyleLeft.setAlignment(CellStyle.ALIGN_LEFT);
		tableHeaderStyleLeft.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		tableHeaderStyleLeft.setFillPattern(CellStyle.SOLID_FOREGROUND);
		tableHeaderStyleLeft.setFont(boldFont);
		tableHeaderStyleLeft.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
		tableHeaderStyleLeft.setBorderRight(HSSFCellStyle.BORDER_THIN);            
		tableHeaderStyleLeft.setBorderTop(HSSFCellStyle.BORDER_THIN);              
		tableHeaderStyleLeft.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		
		CellStyle tableHeaderStyle1 = hssfWorkbook.createCellStyle();
		Font font = hssfWorkbook.createFont();
		font.setColor(IndexedColors.RED.getIndex());
		tableHeaderStyle1.setFont(font);
		
		//Create First Row
		HSSFCell seqNumber = headerRow.createCell(0);
		seqNumber.setCellValue("National Drug Control Master Plan (NDCMP)");
		seqNumber.setCellStyle(mainHeaderStyle);
		seqNumber.setCellType(Cell.CELL_TYPE_STRING);
		headerRow.setHeight((short)700);
		hssfSheet.addMergedRegion(new CellRangeAddress(0,0,0,10));
		
		HSSFCellStyle headerstyle = hssfWorkbook.createCellStyle();
		headerstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
		headerstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
		headerstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
		headerstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		headerstyle.setFont(boldFont);
		
		//Second Row for set Agency
		headerRow = hssfSheet.createRow(1);
		HSSFCellStyle agencystyle = hssfWorkbook.createCellStyle();
		agencystyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
		agencystyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
		agencystyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
		agencystyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		HSSFCell nameOfAgency = headerRow.createCell(0);
		nameOfAgency.setCellValue("Report By : "+user.getFirstName() +" "+user.getLastName());
		nameOfAgency.setCellStyle(tableHeaderStyleLeft);
		nameOfAgency.setCellType(Cell.CELL_TYPE_STRING);
		hssfSheet.addMergedRegion(new CellRangeAddress(1,1,0,1));
		
		HSSFCell agencyName  = headerRow.createCell(1);
		agencyName.setCellType(Cell.CELL_TYPE_STRING);

		HSSFCell reportingPeriod  = headerRow.createCell(2);
		reportingPeriod.setCellValue("Reporting period : "+reportingPeriodValues.getYear()+ " - "+reportingPeriodValues.getName());
		reportingPeriod.setCellStyle(tableHeaderStyle);
		reportingPeriod.setCellType(Cell.CELL_TYPE_STRING);
		hssfSheet.addMergedRegion(new CellRangeAddress(1,1,2,5));
		
		
		SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();
		
		HSSFCell reportTime = headerRow.createCell(6);
		reportTime.setCellValue("Report Time : "+sd.format(date));
		reportTime.setCellStyle(tableHeaderStyle);
		reportTime.setCellType(Cell.CELL_TYPE_STRING);
		hssfSheet.addMergedRegion(new CellRangeAddress(1,1,6,10));
		
		
		Integer rowValue = 2;
		HSSFCellStyle row2=hssfWorkbook.createCellStyle();
		row2.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
		row2.setBorderRight(HSSFCellStyle.BORDER_THIN);            
		row2.setBorderTop(HSSFCellStyle.BORDER_THIN);              
		row2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,0,10));
		rowValue++;
		
		String[] sColor=hex2Rgb("#33cccc").split(",");
		String[] yColor=hex2Rgb("#FFCC99").split(",");
		String[] keyBarColor=hex2Rgb("#CCFFCC").split(",");
		
		int c1=Integer.parseInt(sColor[0]);
		int c2=Integer.parseInt(sColor[1]);
		int c3=Integer.parseInt(sColor[2]);
		
		int yc1=Integer.parseInt(yColor[0]);
		int yc2=Integer.parseInt(yColor[1]);
		int yc3=Integer.parseInt(yColor[2]);
		
		int kc1=Integer.parseInt(keyBarColor[0]);
		int kc2=Integer.parseInt(keyBarColor[1]);
		int kc3=Integer.parseInt(keyBarColor[2]);
		
		HSSFCellStyle style2=hssfWorkbook.createCellStyle();
		HSSFColor skyBlue =  setColor(hssfWorkbook,(byte) c1, (byte) c2,(byte) c3);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);            
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);              
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setFillForegroundColor(skyBlue.getIndex());
		style2.setFont(boldFont);
		style2.setFillPattern(CellStyle.SOLID_FOREGROUND);
		
		
		HSSFCellStyle keyBarstyle2=hssfWorkbook.createCellStyle();
		HSSFColor green =  setColor(hssfWorkbook,(byte) kc1, (byte) kc2,(byte) kc3);
		keyBarstyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
		keyBarstyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);            
		keyBarstyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);              
		keyBarstyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		keyBarstyle2.setFillForegroundColor(green.getIndex());
		keyBarstyle2.setFont(boldFont);
		keyBarstyle2.setFillPattern(CellStyle.SOLID_FOREGROUND);
		
		for (StrategicPillar strategicPillar : strategicPillars) {
			
			headerRow = hssfSheet.createRow(rowValue);
			HSSFCell sPillarName = headerRow.createCell(0);
			sPillarName.setCellValue("Strategic Pillar  "+strategicPillar.getId()+" : "+strategicPillar.getName());
			sPillarName.setCellStyle(style2);
			sPillarName.setCellType(Cell.CELL_TYPE_STRING);
			
			hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,0,10));
			
			rowValue++;
			List<Theme> themesByStrategicPillar = themeService.getThemeByStrategicPillar(strategicPillar);
			for (Theme theme : themesByStrategicPillar) {
				for (Theme themeByAgencyId : themes) {
					if(theme.getId() == themeByAgencyId.getId()){
						headerRow = hssfSheet.createRow(rowValue);
						
						HSSFCellStyle yellowStyle=hssfWorkbook.createCellStyle();
						yellowStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
						yellowStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
						yellowStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
						yellowStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
						HSSFColor sandal =  setColor(hssfWorkbook,(byte) yc1, (byte) yc2,(byte) yc3);
						yellowStyle.setFillForegroundColor(sandal.getIndex());
						yellowStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
						yellowStyle.setFont(boldFont);
						
						HSSFCell themeName = headerRow.createCell(0);
						themeName.setCellValue("Theme : "+theme.getName());
						themeName.setCellStyle(yellowStyle);
						themeName.setCellType(Cell.CELL_TYPE_STRING);
						
						hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,0,10));
						
						rowValue++;
						List<Outcome> outcomes2 = outcomeService.getByTheme(themeByAgencyId);
						for (Outcome outcome : outcomes2) {
							for (Outcome outcomeByAgencyId : outcomes) {
								if(outcomeByAgencyId.getId() == outcome.getId()){
									Font outcomeFont = hssfWorkbook.createFont();
									outcomeFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
									outcomeFont.setColor(IndexedColors.BLUE.getIndex());
									
									HSSFCellStyle outcomeStyle=hssfWorkbook.createCellStyle();
									HSSFColor outcomeSandal = setColor(hssfWorkbook,(byte) yc1, (byte) yc2,(byte) yc3);
									outcomeStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
									outcomeStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
									outcomeStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
									outcomeStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
									outcomeStyle.setFillForegroundColor(outcomeSandal.getIndex());
									outcomeStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
									outcomeStyle.setFont(outcomeFont);
									
									headerRow = hssfSheet.createRow(rowValue);
									HSSFCell outcomeName = headerRow.createCell(0);
									outcomeName.setCellValue("Outcome "+outcome.getSequenceNumber()+ " : "+outcome.getName());
									outcomeName.setCellStyle(outcomeStyle);
									outcomeName.setCellType(Cell.CELL_TYPE_STRING);
									
									hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,0,10));
									 
									rowValue++;
									List<Output> outputs2 = outputServices.getByOutcome(outcomeByAgencyId);
									for (Output output : outputs2) {
										for (Output outputByAgencyid : outputs) {	
											if(outputByAgencyid.getId() == output.getId()){
												Font outputFont = hssfWorkbook.createFont();
												outputFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
												outputFont.setColor(IndexedColors.RED.getIndex());
												
												HSSFCellStyle outputStyle=hssfWorkbook.createCellStyle();
												HSSFColor outputSandal = setColor(hssfWorkbook,(byte) yc1, (byte) yc2,(byte) yc3);
												outputStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
												outputStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
												outputStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
												outputStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
												outputStyle.setFillForegroundColor(outputSandal.getIndex());
												outputStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
												outputStyle.setFont(outputFont);
												
												HSSFCellStyle activityStyle=hssfWorkbook.createCellStyle();
												activityStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
												activityStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
												activityStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
												activityStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
												
												headerRow = hssfSheet.createRow(rowValue);
												HSSFCell outputName = headerRow.createCell(0);
												outputName.setCellValue("Output "+output.getSequenceNumber()+" : "+output.getOutput());
												outputName.setCellStyle(outputStyle);
												outputName.setCellType(Cell.CELL_TYPE_STRING);
												
												hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,0,10));
												
												rowValue++;
												List<Indicator> indicators = indicatorService.findByOutput(outputByAgencyid);
												for (Indicator indicator : indicators) {
													headerRow = hssfSheet.createRow(rowValue);
													HSSFCell indicatorName = headerRow.createCell(0);
													indicatorName.setCellValue("Indicators : "+indicator.getMessage());
													indicatorName.setCellStyle(yellowStyle);
													indicatorName.setCellType(Cell.CELL_TYPE_STRING);
													hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,0,10));
													
													rowValue++;
												}
												List<Target> targets = targetService.findByOutput(outputByAgencyid);
												for (Target target : targets) {
													headerRow = hssfSheet.createRow(rowValue);
													
													HSSFCell targetName = headerRow.createCell(0);
													targetName.setCellValue("Targets : "+target.getMessage());
													targetName.setCellStyle(yellowStyle);
													targetName.setCellType(Cell.CELL_TYPE_STRING);
													hssfSheet.addMergedRegion(new CellRangeAddress(rowValue,rowValue,0,10));
													rowValue++;
												}
												headerRow = hssfSheet.createRow(rowValue);
												HSSFCell seqenceNumber = headerRow.createCell(0);
												seqenceNumber.setCellValue("S.No");
												seqenceNumber.setCellStyle(keyBarstyle2);
												seqenceNumber.setCellType(Cell.CELL_TYPE_STRING); 
												
												HSSFCell keyActivitys = headerRow.createCell(1);
												keyActivitys.setCellValue("Key Activities");
												keyActivitys.setCellStyle(keyBarstyle2);
												keyActivitys.setCellType(Cell.CELL_TYPE_STRING);

												HSSFCell subActivitys  = headerRow.createCell(2);
												subActivitys.setCellValue("Sub Activities");
												subActivitys.setCellStyle(keyBarstyle2);
												subActivitys.setCellType(Cell.CELL_TYPE_STRING);
												
												HSSFCell status = headerRow.createCell(3);
												status.setCellValue("Status");
												status.setCellStyle(keyBarstyle2);
												status.setCellType(Cell.CELL_TYPE_STRING);
												
												HSSFCell completionPercentage = headerRow.createCell(4);
												completionPercentage.setCellValue("Completion Percentage");
												completionPercentage.setCellStyle(keyBarstyle2);
												completionPercentage.setCellType(Cell.CELL_TYPE_STRING);
												
												HSSFCell keyProgress = headerRow.createCell(5);
												keyProgress.setCellValue("Key Progress");
												keyProgress.setCellStyle(keyBarstyle2);
												keyProgress.setCellType(Cell.CELL_TYPE_STRING);
												
												HSSFCell reasongap = headerRow.createCell(6);
												reasongap.setCellValue("Reasons for gap if any");
												reasongap.setCellStyle(keyBarstyle2);
												reasongap.setCellType(Cell.CELL_TYPE_STRING);
												
												HSSFCell reactify = headerRow.createCell(7);
												reactify.setCellValue("Plan of Action to rectify the gap");
												reactify.setCellStyle(keyBarstyle2);
												reactify.setCellType(Cell.CELL_TYPE_STRING);
												
												HSSFCell feedbac = headerRow.createCell(8);
												feedbac.setCellValue("Reviewer Feedback");
												feedbac.setCellStyle(keyBarstyle2);
												feedbac.setCellType(Cell.CELL_TYPE_STRING);
												hssfSheet.setColumnWidth(7,10000);
												
												HSSFCell reviewedByHeading = headerRow.createCell(9);
												reviewedByHeading.setCellValue("Reviewed By");
												reviewedByHeading.setCellStyle(keyBarstyle2);
												reviewedByHeading.setCellType(Cell.CELL_TYPE_STRING);

												HSSFCell reviewedTimeHeading = headerRow.createCell(10);
												reviewedTimeHeading.setCellValue("Reviewed Time");
												reviewedTimeHeading.setCellStyle(keyBarstyle2);
												reviewedTimeHeading.setCellType(Cell.CELL_TYPE_STRING);
												
												rowValue++;
												List<KeyActivity> activities = keyActivityService.findByOutput(outputByAgencyid);
												for (KeyActivity keyActivity : activities) {
													for (KeyActivity keyActivityByReportingPeriod : keyActivities) {
														if(keyActivityByReportingPeriod.getId() == keyActivity.getId() || keyActivityByReportingPeriod.getId().equals(keyActivity.getId())){
															headerRow = hssfSheet.createRow(rowValue);
															HSSFCell keySeqNumber = headerRow.createCell(0);
															keySeqNumber.setCellValue(keyActivity.getSequenceNumber());
															keySeqNumber.setCellStyle(activityStyle);
															
															HSSFCell keyActivityName = headerRow.createCell(1);
															keyActivityName.setCellValue(keyActivity.getName());
															keyActivityName.setCellStyle(activityStyle);
															
															CellStyle cellStylekeyName = hssfWorkbook.createCellStyle();
															cellStylekeyName.setWrapText(true);//set wraper text
															keyActivityName.setCellStyle(cellStylekeyName);
															headerRow.setHeightInPoints((2*hssfSheet.getDefaultRowHeightInPoints()));
															hssfSheet.setColumnWidth(1,11000);
															
															HSSFCell subActivity = headerRow.createCell(2);
															subActivity.setCellValue("");
															subActivity.setCellStyle(activityStyle);
															
															HSSFCell status1 = headerRow.createCell(3);
															status1.setCellValue("");
															status1.setCellStyle(activityStyle);
															
															HSSFCell comPercent1 = headerRow.createCell(4);
															comPercent1.setCellValue("");
															comPercent1.setCellStyle(activityStyle);
															
															HSSFCell keyProgress1 = headerRow.createCell(5);
															keyProgress1.setCellValue("");
															keyProgress1.setCellStyle(activityStyle);
															
															HSSFCell reason = headerRow.createCell(6);
															reason.setCellValue("");
															reason.setCellStyle(activityStyle);
															
															HSSFCell rectify = headerRow.createCell(7);
															rectify.setCellValue("");
															rectify.setCellStyle(activityStyle);
															
															HSSFCell feedback = headerRow.createCell(8);
															feedback.setCellValue("");
															feedback.setCellStyle(activityStyle);
															
															HSSFCell revBY1 = headerRow.createCell(9);
															revBY1.setCellValue("");
															revBY1.setCellStyle(activityStyle);
															
															HSSFCell revON1 = headerRow.createCell(10);
															revON1.setCellValue("");
															revON1.setCellStyle(activityStyle);
															
															rowValue++;
															List<SubActivity> subActivities3 = subActivityService.findByKeyActivity(keyActivityByReportingPeriod);
															if(subActivities3.size() > 0){
																for(SubActivity subActivity1 : subActivities3){
																	for (SubActivity subActivity2 : subActivities) {
																		if(subActivity2.getId() == subActivity1.getId() || subActivity2.getId().equals(subActivity1.getId())){
																			headerRow = hssfSheet.createRow(rowValue);
																			HSSFCell subSeySeqNumber = headerRow.createCell(0);
																			subSeySeqNumber.setCellValue(subActivity2.getSequenceNumber());
																			subSeySeqNumber.setCellStyle(activityStyle);
																			
																			HSSFCell keyActivityNameForSubactivity = headerRow.createCell(1);
																			keyActivityNameForSubactivity.setCellValue("");
																			keyActivityNameForSubactivity.setCellStyle(activityStyle);
																			
																			HSSFCell subActivityName = headerRow.createCell(2);
																			subActivityName.setCellValue(subActivity2.getName());
																			subActivityName.setCellStyle(activityStyle);
																			
																			CellStyle cellStyle1 = hssfWorkbook.createCellStyle();
																			cellStyle1.setWrapText(true);//set wraper text
																			cellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
																			cellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);            
																			cellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);              
																			cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
																			subActivityName.setCellStyle(cellStyle1);
																			headerRow.setHeightInPoints((2*hssfSheet.getDefaultRowHeightInPoints()));
																			hssfSheet.setColumnWidth(2,11000);
																			
																			int temp = 0;
																			List<StatusTracking> statusTrackings = statusTrackingRepository.findBysubActivityAndReportingPeriodAndUserLevel(subActivity1, reportingPeriodValues, 2);
																				if(statusTrackings != null && statusTrackings.size() > 0){
																					for (StatusTracking statusTracking : statusTrackings) {
																						HSSFCellStyle	statusColor=hssfWorkbook.createCellStyle();
																						
																						String[] staColor=hex2Rgb(statusTracking.getActualStatusColor()).split(",");
																						int ac1=Integer.parseInt(staColor[0]);
																						int ac2=Integer.parseInt(staColor[1]);
																						int ac3=Integer.parseInt(staColor[2]);
																						
																						HSSFPalette palette = hssfWorkbook.getCustomPalette();
																						HSSFColor myColor = palette.findSimilarColor(ac1, ac2, ac3);
																						short palIndex = myColor.getIndex();
																						
																						statusColor.setFillForegroundColor(palIndex);
																						statusColor.setFillPattern(CellStyle.SOLID_FOREGROUND);
																						statusColor.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
																						statusColor.setBorderRight(HSSFCellStyle.BORDER_THIN);            
																						statusColor.setBorderTop(HSSFCellStyle.BORDER_THIN);              
																						statusColor.setBorderBottom(HSSFCellStyle.BORDER_THIN);
																						
																						HSSFCell status2 = headerRow.createCell(3);
																						status2.setCellStyle(statusColor);
																						
																						HSSFCell comPercent2 = headerRow.createCell(4);
																						comPercent2.setCellValue(Integer.parseInt(statusTracking.getActualStatusPercentage()));
																						comPercent2.setCellStyle(activityStyle);
																						
																						HSSFCell keyProgress2 = headerRow.createCell(5);
																						keyProgress2.setCellValue(statusTracking.getKeyProgress());
																						keyProgress2.setCellStyle(activityStyle);
																						
																						HSSFCell reason1 = headerRow.createCell(6);
																						reason1.setCellValue(statusTracking.getReasonForGap());
																						reason1.setCellStyle(activityStyle);
																						
																						HSSFCell rectify1 = headerRow.createCell(7);
																						rectify1.setCellValue(statusTracking.getRectifyTheGap());
																						rectify1.setCellStyle(activityStyle);
																						
																						HSSFCell feedback1 = headerRow.createCell(8);
																						feedback1.setCellValue(statusTracking.getReviewerFeedback());
																						feedback1.setCellStyle(activityStyle);
																						
																						HSSFCell reviewedByValue = headerRow.createCell(9);
																						if(statusTracking.getReviewedBy() != null){
																							reviewedByValue.setCellValue(statusTracking.getReviewedBy().getFirstName()+" "+statusTracking.getReviewedBy().getLastName());
																						}else{
																							reviewedByValue.setCellValue("");
																						}
																						reviewedByValue.setCellStyle(activityStyle);

																						HSSFCell reviewedTimeValue = headerRow.createCell(10);
																						if(statusTracking.getReviewedOn() != null && statusTracking.getReviewedOn() != ""){
																							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
																							SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
																							String reviewOn = statusTracking.getReviewedOn();
																							try {
																								Date reviewDate = sdf.parse(reviewOn);
																								reviewedTimeValue.setCellValue(dateformat.format(reviewDate));
																							} catch (ParseException e) {
																								e.printStackTrace();
																							}
																							
																						}else{
																							reviewedTimeValue.setCellValue("");
																						}
																						reviewedTimeValue.setCellStyle(activityStyle);
																						temp = 1;
																					}
																				}
																				if(temp == 0){
																					HSSFCell status3 = headerRow.createCell(3);
																					status3.setCellValue("");
																					status3.setCellStyle(activityStyle);
																					
																					HSSFCell comPercent3 = headerRow.createCell(4);
																					comPercent3.setCellValue("");
																					comPercent3.setCellStyle(activityStyle);
																					
																					HSSFCell keyProgress3 = headerRow.createCell(5);
																					keyProgress3.setCellValue("");
																					keyProgress3.setCellStyle(activityStyle);
																					
																					HSSFCell reason3 = headerRow.createCell(6);
																					reason3.setCellValue("");
																					reason3.setCellStyle(activityStyle);
																					
																					HSSFCell rectify3 = headerRow.createCell(7);
																					rectify3.setCellValue("");
																					rectify3.setCellStyle(activityStyle);
																					
																					HSSFCell feedBack3 = headerRow.createCell(8);
																					feedBack3.setCellValue("");
																					feedBack3.setCellStyle(activityStyle);
																					
																					HSSFCell revBy = headerRow.createCell(9);
																					revBy.setCellValue("");
																					revBy.setCellStyle(activityStyle);
																					
																					HSSFCell revOn = headerRow.createCell(10);
																					revOn.setCellValue("");
																					revOn.setCellStyle(activityStyle);
																				}
																				if(partnerAgencyId != -1){
																					List<Agency> agency3 = subActivity2.getPartnerAgency();
																					for (Agency agency : agency3) {
																						List<User> users1 = agency.getAgencyAuthority();
																						if(users1.size() > 0){
																							for (User user3 : users1) {
																								StatusTracking statusTracking1 = statusTrackingRepository.findByUserAndSubActivityAndReportingPeriodAndUserLevel(user3, subActivity2, reportingPeriodValues, 1);
																								if(statusTracking1 != null){
																									rowValue++;
																									headerRow = hssfSheet.createRow(rowValue);
																									HSSFCell cell0 = headerRow.createCell(0);
																									cell0.setCellStyle(activityStyle);
																									HSSFCell cell1 = headerRow.createCell(1);
																									cell1.setCellStyle(activityStyle);
																									HSSFCell userAgency = headerRow.createCell(2);
																									userAgency.setCellValue("   By  "+agency.getName()+" (" +user3.getFirstName() +" "+ user3.getLastName()+")");
																									userAgency.setCellStyle(agencystyle);
																									HSSFCellStyle	statusColor=hssfWorkbook.createCellStyle();
																									
																									String[] staColor=hex2Rgb(statusTracking1.getActualStatusColor()).split(",");
																									int ac1=Integer.parseInt(staColor[0]);
																									int ac2=Integer.parseInt(staColor[1]);
																									int ac3=Integer.parseInt(staColor[2]);
																									
																									HSSFPalette palette = hssfWorkbook.getCustomPalette();
																									HSSFColor myColor = palette.findSimilarColor(ac1, ac2, ac3);
																									short palIndex = myColor.getIndex();
																									statusColor.setFillForegroundColor(palIndex);
																									statusColor.setFillPattern(CellStyle.SOLID_FOREGROUND);
																								
																									HSSFCell status2 = headerRow.createCell(3);
																									status2.setCellStyle(statusColor);
																									
																									HSSFCell comPercent2 = headerRow.createCell(4);
																									comPercent2.setCellValue(Integer.parseInt(statusTracking1.getActualStatusPercentage()));
																									comPercent2.setCellStyle(activityStyle);
																									
																									HSSFCell keyProgress2 = headerRow.createCell(5);
																									keyProgress2.setCellValue(statusTracking1.getKeyProgress());
																									keyProgress2.setCellStyle(activityStyle);
																									
																									HSSFCell reasons = headerRow.createCell(6);
																									reasons.setCellValue(statusTracking1.getReasonForGap());
																									reasons.setCellStyle(activityStyle);
																									
																									HSSFCell rectifi = headerRow.createCell(7);
																									rectifi.setCellValue(statusTracking1.getRectifyTheGap());
																									rectifi.setCellStyle(activityStyle);
																									
																									HSSFCell feedback1 = headerRow.createCell(8);
																									feedback1.setCellValue(statusTracking1.getReviewerFeedback());
																									feedback1.setCellStyle(activityStyle);
																									
																									HSSFCell reviewedByValue = headerRow.createCell(9);
																									if(statusTracking1.getReviewedBy() != null){
																										reviewedByValue.setCellValue(statusTracking1.getReviewedBy().getFirstName()+" "+statusTracking1.getReviewedBy().getLastName());
																									}else{
																										reviewedByValue.setCellValue("");
																									}
																									reviewedByValue.setCellStyle(activityStyle);

																									HSSFCell reviewedTimeValue = headerRow.createCell(10);
																									if(statusTracking1.getReviewedOn() != null && statusTracking1.getReviewedOn() != ""){
																										SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
																										SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
																										String reviewOn = statusTracking1.getReviewedOn();
																										try {
																											Date reviewDate = sdf.parse(reviewOn);
																											reviewedTimeValue.setCellValue(dateformat.format(reviewDate));
																										} catch (ParseException e) {
																											e.printStackTrace();
																										}
																										
																									}else{
																										reviewedTimeValue.setCellValue("");
																									}
																									reviewedTimeValue.setCellStyle(activityStyle);
																									temp = 1;
																								}
																								if(temp == 0){
																									HSSFCell status3 = headerRow.createCell(3);
																									status3.setCellValue("");
																									status3.setCellStyle(activityStyle);
																									
																									HSSFCell comPercent3 = headerRow.createCell(4);
																									comPercent3.setCellValue("");
																									comPercent3.setCellStyle(activityStyle);
																									
																									HSSFCell keyProgress3 = headerRow.createCell(5);
																									keyProgress3.setCellValue("");
																									keyProgress3.setCellStyle(activityStyle);
																									
																									HSSFCell reason3 = headerRow.createCell(6);
																									reason3.setCellValue("");
																									reason3.setCellStyle(activityStyle);
																									
																									HSSFCell rectify3 = headerRow.createCell(7);
																									rectify3.setCellValue("");
																									rectify3.setCellStyle(activityStyle);
																									
																									HSSFCell feedBack3 = headerRow.createCell(8);
																									feedBack3.setCellValue("");
																									feedBack3.setCellStyle(activityStyle);
																									
																									HSSFCell revBy = headerRow.createCell(9);
																									revBy.setCellValue("");
																									revBy.setCellStyle(activityStyle);
																									
																									HSSFCell revOn = headerRow.createCell(10);
																									revOn.setCellValue("");
																									revOn.setCellStyle(activityStyle);
																								}
																							}
																						}
																			
																					}
																				}
																	rowValue++;
																}}
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
				
			}
		}
		}
		hssfSheet.autoSizeColumn(0);
		//hssfSheet.autoSizeColumn(1);
		//hssfSheet.autoSizeColumn(2);
		//hssfSheet.autoSizeColumn(3);
		hssfSheet.autoSizeColumn(4);
		hssfSheet.autoSizeColumn(5);
		hssfSheet.autoSizeColumn(6);
		hssfSheet.autoSizeColumn(7);
		hssfSheet.autoSizeColumn(8);
		hssfSheet.autoSizeColumn(9);
		hssfSheet.autoSizeColumn(10);
		return hssfWorkbook;
	}

	/**
	 * @author PremKumar
	 * @param model
	 * @param authentication
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "superAdminActivityStatus")
	public ModelAndView superAdminActivityStatus(ModelMap model, Authentication authentication, HttpServletRequest request) {
		LOGGER.info("activityStatus");
		List<ReportingPeriod> reportingPeriods = reportingPeriodService.getAllReportingPeriodList();
		TreeSet<String> list = new TreeSet<String>();
		for (ReportingPeriod reportingPeriod : reportingPeriods) {
			list.add(reportingPeriod.getYear());
		}
		List<String>yourHashSet = new ArrayList<String>(list);
		Collections.sort(yourHashSet);
		model.addAttribute("reportingPeriods", yourHashSet);
		return new ModelAndView("report/superAdminReport");
	}
	
	/**
	 * @param model
	 * @param authentication
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "approverActivityStatus")
	public ModelAndView approverActivityStatus(ModelMap model, Authentication authentication, HttpServletRequest request) {
		LOGGER.info("activityStatus");
		
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE) || principalUtil.getCurrentUserRole().equals(ConstantUtil.PROJECTADMIN_ROLE) || 
				principalUtil.getCurrentUserRole().equals(ConstantUtil.PROJECT_PLANNER) || principalUtil.getCurrentUserRole().equals(ConstantUtil.STATUS_APPROVER)){
			List<ReportingPeriod> reportingPeriods = reportingPeriodService.getAll();
			TreeSet<String> list = new TreeSet<String>();
			for (ReportingPeriod reportingPeriod : reportingPeriods) {
				list.add(reportingPeriod.getYear());
			}
			List<String> yourHashSet = new ArrayList<String>(list);
			Collections.sort(yourHashSet);
			model.addAttribute("reportingPeriods", yourHashSet);
			return new ModelAndView("report/approverReport");
		}else{
			return new ModelAndView("login");
		}
	}
	
	/**
	 * @param model
	 * @param reportingPeriodId
	 * @return
	 */
	@RequestMapping(value = "loadQuater", method = RequestMethod.GET)
	public @ResponseBody List<ReportingPeriod> loadQuater(ModelMap model,@RequestParam("reportingPeriodId") String reportingPeriodId) {
		List<ReportingPeriod> reportingPeriods = new ArrayList<ReportingPeriod>();
		String [] reportperiod = reportingPeriodId.split(",");
		List<String> list = Arrays.asList(reportperiod);
		if(list.contains("0")){
			reportingPeriods = reportingPeriodService.getAllStatusOpenAndClosed();
		}else {
			for(int j=0 ; j<reportperiod.length; j++){
				List<ReportingPeriod>reportingPeriodlist = reportingPeriodService.findAllByYear(reportperiod[j]);
				for (ReportingPeriod reportingPeriod : reportingPeriodlist) {
					reportingPeriods.add(reportingPeriod);
				}
			}
		}
		if(reportingPeriods.size() > 0){
		return reportingPeriods;
		}
		return null;
	}
	
	/**
	 * @param model
	 * @param reportingPeriodId
	 * @return
	 */
	@RequestMapping(value = "loadStrategicPillarByReportingPeriod", method = RequestMethod.GET)
	public @ResponseBody LinkedHashSet<StrategicPillar> loadStrategicPillarByReportingPeriod(ModelMap model,@RequestParam("reportingPeriodId") String reportingPeriodId) {
		LinkedHashSet<StrategicPillar> strategicPillars = new LinkedHashSet<StrategicPillar>();
		List<ReportingPeriod> reportingPeriods = new ArrayList<ReportingPeriod>();
		List<SubActivity> subActivities = new ArrayList<SubActivity>();
		String [] reportperiod = reportingPeriodId.split(",");
		for(int j=0 ; j<reportperiod.length; j++){
			ReportingPeriod reportingPeriod = reportingPeriodService.getById(Integer.parseInt(reportperiod[j].toString()));
			subActivities = subActivityService.getSubActivitiesByOpenedReportingPeriod(Integer.parseInt(reportperiod[j]));
			reportingPeriods.add(reportingPeriod);
			if(subActivities.size() > 0){
				for (SubActivity subActivity : subActivities) {
					strategicPillars.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme().getStrategicPillar());
				}
			}
		}
		return strategicPillars;
	}
	
	/**
	 * @param model
	 * @param reportingPeriodId
	 * @return
	 */
	@RequestMapping(value = "loadApproverStrategicPillarByReportingPeriod", method = RequestMethod.GET)
	public @ResponseBody Set<StrategicPillar> loadApproverStrategicPillarByReportingPeriod(ModelMap model,@RequestParam("reportingPeriodId") String reportingPeriodId) {
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
		liStrategicPillars = strategicPillarService.getStrategicPliiersByReportingPeriods(reportingPeriods);
		Set<StrategicPillar> pillars = new LinkedHashSet<StrategicPillar>(liStrategicPillars);
		return pillars;
	}
	
	/**
	 * @param model
	 * @param reportingPeriodId
	 * @param strategicPillarId
	 * @return
	 */
	@RequestMapping(value = "loadThemeByStrategicPillarAndReportingPeriod", method = RequestMethod.GET)
	public @ResponseBody LinkedHashSet<Theme> loadThemeByStrategicPillarAndReportingPeriod(ModelMap model,@RequestParam Integer reportingPeriodId,@RequestParam Integer strategicPillarId) {
		List<SubActivity> subActivities = subActivityService.getSubActivitiesByStrategicPillerAndOpenedReportingPeriod(strategicPillarId, reportingPeriodId);
		LinkedHashSet<Theme> themes = new LinkedHashSet<Theme>();
		if(subActivities.size() > 0){
			for (SubActivity subActivity : subActivities) {
				themes.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme());
			}
		}
		return themes;
	}
	
	/**
	 * @param model
	 * @param reportingPeriodId
	 * @param strategicPillarId
	 * @return
	 */
	@RequestMapping(value = "loadApproverThemeByStrategicPillarAndReportingPeriod", method = RequestMethod.GET)
	public @ResponseBody LinkedHashSet<Theme> loadApproverThemeByStrategicPillarAndReportingPeriod(ModelMap model,@RequestParam String reportingPeriodId,@RequestParam String strategicPillarId) {
		String [] rp = reportingPeriodId.split(",");
		String [] sp = strategicPillarId.split(",");
		List<Integer> reportperiod = new ArrayList<Integer>();
		List<Integer> strategicPillar = new ArrayList<Integer>();
		List<String> rplist = Arrays.asList(rp);
		List<String> splist = Arrays.asList(sp);
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
			List<StrategicPillar> strategicPillars = strategicPillarService.getStrategicPliiersByReportingPeriods(reportperiod);
			for (StrategicPillar strategicPillar2 : strategicPillars) {
				strategicPillar.add(strategicPillar2.getId());
			}
			
		}else {
			for(int i=0 ; i<sp.length; i++){
				strategicPillar.add(Integer.parseInt(sp[i]));
			}
		}
		List<SubActivity> subActivities = subActivityService.getSubActivitiesByListOfStrategicPillerAndListOfOpenedReportingPeriod( strategicPillar , reportperiod);
		LinkedHashSet<Theme> themes = new LinkedHashSet<Theme>();
		if(subActivities.size() > 0){
			for (SubActivity subActivity : subActivities) {
				themes.add(subActivity.getKeyActivity().getOutput().getOutcome().getTheme());
			}
		}
		return themes;
	}
	
	/**
	 * @param model
	 * @param reportingPeriodId
	 * @param themeId
	 * @return
	 */
	@RequestMapping(value = "loadOutcomeByThemeAndReportingPeriod", method = RequestMethod.GET)
	public @ResponseBody LinkedHashSet<Outcome> loadOutcomeByThemeAndReportingPeriod(ModelMap model,@RequestParam("reportingPeriodId") Integer reportingPeriodId,@RequestParam("themeId") Integer themeId) {
		List<SubActivity> subActivities = subActivityService.getSubActivitiesByThemeAndOpenedReportingPeriod(themeId, reportingPeriodId);
		LinkedHashSet<Outcome> outcomes = new LinkedHashSet<Outcome>();
		if(subActivities.size() > 0){
			for (SubActivity subActivity : subActivities) {
				outcomes.add(subActivity.getKeyActivity().getOutput().getOutcome());
			}
		}
		return outcomes;
	}
	
	/**
	 * @param model
	 * @param reportingPeriodId
	 * @param themeId
	 * @param strategicPillarId
	 * @return
	 */
	@RequestMapping(value = "loadPartnerOutcomeByThemeAndReportingPeriod", method = RequestMethod.GET)
	public @ResponseBody LinkedHashSet<Outcome> loadPartnerOutcomeByThemeAndReportingPeriod(ModelMap model,@RequestParam("reportingPeriodId") String reportingPeriodId,@RequestParam("themeId") String themeId,@RequestParam("strategicPillarId")String strategicPillarId) {
		String [] rp = reportingPeriodId.split(",");
		String [] th = themeId.split(",");
		String [] sp = strategicPillarId.split(",");
		List<Integer> reportperiod = new ArrayList<Integer>();
		List<Integer> theme = new ArrayList<Integer>();
		List<Integer> strategic = new ArrayList<Integer>();
		List<String> rplist = Arrays.asList(rp);
		List<String> thlist = Arrays.asList(th);
		List<String> splist = Arrays.asList(sp);
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
		
		List<SubActivity> subActivities = subActivityService.getSubActivitiesByListOfThemeAndListOfOpenedReportingPeriodAndPartnerAgency(theme,reportperiod,agency.getId());
		if(subActivities != null){
		LinkedHashSet<Outcome> outcomes = new LinkedHashSet<Outcome>();
		if(subActivities.size() > 0){
			for (SubActivity subActivity : subActivities) {
				outcomes.add(subActivity.getKeyActivity().getOutput().getOutcome());
			}
		}
		
		return outcomes;
		}
		return null;
	}
	
	/**
	 * @param model
	 * @param reportingPeriodId
	 * @param themeId
	 * @param strategicPillarId
	 * @return
	 */
	@RequestMapping(value = "loadApproverOutcomeByThemeAndReportingPeriod", method = RequestMethod.GET)
	public @ResponseBody LinkedHashSet<Outcome> loadApproverOutcomeByThemeAndReportingPeriod(ModelMap model,@RequestParam("reportingPeriodId") String reportingPeriodId,@RequestParam("themeId") String themeId,@RequestParam("strategicPillarId")String strategicPillarId) {
		String [] rp = reportingPeriodId.split(",");
		String [] th = themeId.split(",");
		String [] sp = strategicPillarId.split(",");
		List<Integer> reportperiod = new ArrayList<Integer>();
		List<Integer> theme = new ArrayList<Integer>();
		List<Integer> strategic = new ArrayList<Integer>();
		List<String> rplist = Arrays.asList(rp);
		List<String> thlist = Arrays.asList(th);
		List<String> splist = Arrays.asList(sp);
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
			List<StrategicPillar> strategicPillars = strategicPillarService.getStrategicPliiersByReportingPeriods(reportperiod);
			for (StrategicPillar strategicPillar : strategicPillars) {
				strategic.add(strategicPillar.getId());
			}
		}else {
			for(int o=0 ; o<sp.length; o++){
				strategic.add(Integer.parseInt(sp[o]));
			}
		}
		if(thlist.contains("0")){
			for (Integer strategicPillar2 : strategic) {
				List<Theme> themes2 = new ArrayList<Theme>();
				StrategicPillar strategicPillar = strategicPillarService.getById(strategicPillar2);
				themes2 = themeService.getThemeByStrategicPillar(strategicPillar);
				for (Theme theme2 : themes2) {
					theme.add(theme2.getId());
				}
			}
		}else {
			for(int i=0 ; i<th.length; i++){
				theme.add(Integer.parseInt(th[i]));
			}
		}
		
		List<SubActivity> subActivities = subActivityService.getSubActivitiesByListOfThemeAndListOfOpenedReportingPeriod(theme,reportperiod);
		LinkedHashSet<Outcome> outcomes = new LinkedHashSet<Outcome>();
		if(subActivities.size() > 0){
			for (SubActivity subActivity : subActivities) {
				outcomes.add(subActivity.getKeyActivity().getOutput().getOutcome());
			}
		}
		return outcomes;
	}
	
	/**
	 * @param model
	 * @param reportingPeriodId
	 * @param outcomeId
	 * @param themeId
	 * @param strategicId
	 * @return
	 */
	@RequestMapping(value = "loadApproverOutputByOutcomeAndReportingPeriod", method = RequestMethod.GET)
	public @ResponseBody LinkedHashSet<Output> loadApproverOutputByOutcomeAndReportingPeriod(ModelMap model,@RequestParam String reportingPeriodId,@RequestParam String outcomeId,@RequestParam String themeId,@RequestParam String strategicId) {
		String [] rp = reportingPeriodId.split(",");
		String [] ot = outcomeId.split(",");
		String [] th = themeId.split(",");
		String [] sp = strategicId.split(",");
		List<Integer> reportperiod = new ArrayList<Integer>();
		List<Integer> outcome = new ArrayList<Integer>();
		List<Integer> theme = new ArrayList<Integer>();
		List<Integer> strategic = new ArrayList<Integer>();
		List<String> rplist = Arrays.asList(rp);
		List<String> otlist = Arrays.asList(ot);
		List<String> thlist = Arrays.asList(th);
		List<String> splist = Arrays.asList(sp);
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
			List<StrategicPillar> strategicPillars = strategicPillarService.getStrategicPliiersByReportingPeriods(reportperiod);
			for (StrategicPillar strategicPillar : strategicPillars) {
				strategic.add(strategicPillar.getId());
			}
		}else {
			for(int o=0 ; o<sp.length; o++){
				strategic.add(Integer.parseInt(sp[o]));
			}
		}
		if(thlist.contains("0")){
			for (Integer strategicPillar2 : strategic) {
				List<Theme> themes2 = new ArrayList<Theme>();
				StrategicPillar strategicPillar = strategicPillarService.getById(strategicPillar2);
				themes2 = themeService.getThemeByStrategicPillar(strategicPillar);
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
			for(Integer themeIds:theme){
				Theme theme2 = themeService.getById(themeIds);
				List<Outcome>outcomes = outcomeService.getByTheme(theme2);
				for (Outcome outcome2 : outcomes) {
					outcome.add(outcome2.getId());
				}
			}
		}else {
			for(int i=0 ; i<ot.length; i++){
				outcome.add(Integer.parseInt(ot[i]));
			}
		}
		
		List<SubActivity> subActivities = subActivityService.getSubActivitiesByListOfOutcomeAndListOfOpenedReportingPeriod(outcome, reportperiod);
		LinkedHashSet<Output> outputs = new LinkedHashSet<Output>();
		if(subActivities.size() > 0){
			for (SubActivity subActivity : subActivities) {
				outputs.add(subActivity.getKeyActivity().getOutput());
			}
		}
		return outputs;
	}
	
	/**
	 * @param model
	 * @param reportingPeriodId
	 * @param outcomeId
	 * @return
	 */
	@RequestMapping(value = "loadOutputByOutcomeAndReportingPeriod", method = RequestMethod.GET)
	public @ResponseBody LinkedHashSet<Output> loadOutputByOutcomeAndReportingPeriod(ModelMap model,@RequestParam Integer reportingPeriodId,@RequestParam Integer outcomeId) {
		List<SubActivity> subActivities = subActivityService.getSubActivitiesByOutcomeAndOpenedReportingPeriod(outcomeId, reportingPeriodId);
		LinkedHashSet<Output> outputs = new LinkedHashSet<Output>();
		if(subActivities.size() > 0){
			for (SubActivity subActivity : subActivities) {
				outputs.add(subActivity.getKeyActivity().getOutput());
			}
		}
		return outputs;
	}
	
	/**
	 * @param model
	 * @param reportingPeriodId
	 * @param outputId
	 * @return
	 */
	@RequestMapping(value = "loadLeadAgency", method = RequestMethod.GET)
	public @ResponseBody LinkedHashSet<Agency> loadLeadAgency(ModelMap model,@RequestParam("reportingPeriodId") Integer reportingPeriodId,@RequestParam("outputId") Integer outputId) {
		List<SubActivity> subActivities = subActivityService.getSubActivitiesByOutputAndOpenedReportingPeriod(outputId, reportingPeriodId);
		LinkedHashSet<Agency> agencies = new LinkedHashSet<Agency>();
		if(subActivities.size() > 0){
			for (SubActivity subActivity : subActivities) {
				agencies.add(subActivity.getLeadAgency());
			}
		}
		return agencies;
	}
	
	/**
	 * @param fromDate
	 * @param toDate
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "superAdminLoginActualReportDownload" , method = RequestMethod.GET)
	public @ResponseBody String superAdminLoginActualReportDownload(@RequestParam("fromDate") String fromDate,@RequestParam("toDate") String toDate) throws ParseException{
		
		List<LoginAudit> loginAuditList=loginAuditService.getLoginAuditByFromDateAndToDate(fromDate, toDate);
		String fileName = "Usage Report"+  ".xls";
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
		
		if(loginAuditList.size() != 0){
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
			HSSFSheet hssfSheet = hssfWorkbook.createSheet("Usage Report");
			
			Font mainFont = hssfWorkbook.createFont();
			mainFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
			mainFont.setFontHeightInPoints((short) 20);
			mainFont.setColor(IndexedColors.BLACK.getIndex());
			
			String[] keyBarColor=hex2Rgb("#CCFFCC").split(",");
			
			int kc1=Integer.parseInt(keyBarColor[0]);
			int kc2=Integer.parseInt(keyBarColor[1]);
			int kc3=Integer.parseInt(keyBarColor[2]);
			
			Font boldFont = hssfWorkbook.createFont();
			boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
			boldFont.setColor(IndexedColors.BLACK.getIndex());
			
			HSSFCellStyle keyBarstyle=hssfWorkbook.createCellStyle();
			HSSFColor green =  setColor(hssfWorkbook,(byte) kc1, (byte) kc2,(byte) kc3);
			keyBarstyle.setFillForegroundColor(green.getIndex());
			keyBarstyle.setFont(boldFont);
			keyBarstyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			keyBarstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
			keyBarstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
			keyBarstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
			keyBarstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			keyBarstyle.setAlignment(CellStyle.ALIGN_CENTER);
			
			HSSFCellStyle activityStyle=hssfWorkbook.createCellStyle();
			activityStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
			activityStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
			activityStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
			activityStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			
			HSSFRow headerRow = hssfSheet.createRow(0);
			
			String[] title={"#","User","Role","Agency Code","Date","System IP"/*,"Host"*/};
			int[] size={2000,6000,6000,5500,6000,5500,5500};
			
			for(int i=0;i<title.length;i++){
				HSSFCell seqenceNumber = headerRow.createCell(i);
				seqenceNumber.setCellValue(title[i]);
				seqenceNumber.setCellStyle(keyBarstyle);
				seqenceNumber.setCellType(Cell.CELL_TYPE_STRING); 
				hssfSheet.setColumnWidth(i, size[i]);
			}
			int rowValue=1;
			int sNo=0;
			for (LoginAudit loginAudit : loginAuditList) {
				sNo++;
				headerRow = hssfSheet.createRow(rowValue);
				HSSFCell cellSNo = headerRow.createCell(0);
				cellSNo.setCellValue(sNo);
				activityStyle.setAlignment(CellStyle.ALIGN_CENTER);
				cellSNo.setCellStyle(activityStyle);
				
				activityStyle.setAlignment(CellStyle.ALIGN_LEFT);
				
				HSSFCell cellUser = headerRow.createCell(1);
				cellUser.setCellValue(loginAudit.getLoginUser());
				cellUser.setCellStyle(activityStyle);
				
				HSSFCell cellUserRole = headerRow.createCell(2);
				cellUserRole.setCellValue(loginAudit.getUserRole());
				cellUserRole.setCellStyle(activityStyle);
				
				HSSFCell cellAgencyCode = headerRow.createCell(3);
				cellAgencyCode.setCellValue(loginAudit.getAgencyCode());
				cellAgencyCode.setCellStyle(activityStyle);
				
				CreationHelper createHelper = hssfWorkbook.getCreationHelper();
				HSSFCellStyle dateStyle=hssfWorkbook.createCellStyle();
				HSSFCell cellDate = headerRow.createCell(4);
				dateStyle.setDataFormat(
					    createHelper.createDataFormat().getFormat("dd-MM-yyyy h:mm:ss aaa"));
				dateStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				dateStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
				dateStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
				dateStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
				dateStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				
				cellDate.setCellValue(loginAudit.getLoginDate());
				cellDate.setCellStyle(dateStyle);

				HSSFCell cellHost = headerRow.createCell(5);
				cellHost.setCellValue(loginAudit.getAccessedFrom());
				cellHost.setCellStyle(activityStyle);
				
				rowValue++;
			}
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
		}else{
			return "Empty";
		}
		
	}
}