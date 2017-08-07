/**
 * 
 */
package com.ceainfotech.ndcmp.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.json.JSONArray;
import org.json.JSONObject;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ceainfotech.ndcmp.model.KeyActivity;
import com.ceainfotech.ndcmp.model.Planning;
import com.ceainfotech.ndcmp.model.ReportingPeriod;
import com.ceainfotech.ndcmp.model.StrategicPillar;
import com.ceainfotech.ndcmp.model.SubActivity;
import com.ceainfotech.ndcmp.service.KeyActivityService;
import com.ceainfotech.ndcmp.service.OutcomeService;
import com.ceainfotech.ndcmp.service.OutputServices;
import com.ceainfotech.ndcmp.service.PlanningServices;
import com.ceainfotech.ndcmp.service.ReportingPeriodService;
import com.ceainfotech.ndcmp.service.StrategicPillarService;
import com.ceainfotech.ndcmp.service.SubActivityService;
import com.ceainfotech.ndcmp.service.ThemeService;
import com.ceainfotech.ndcmp.service.UserService;
import com.ceainfotech.ndcmp.util.ConstantUtil;
import com.ceainfotech.ndcmp.util.PrincipalUtil;

/**
 * @author bosco
 *
 */

@Controller
@RequestMapping(value = "/api/**")
public class PlanningController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PlanningController.class);
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PlanningServices planningServices;
	
	@Autowired
	private SubActivityService subActivityService;
	
	
	@Autowired
	private ReportingPeriodService reportingPeriodService;
	
	@Autowired
	private StrategicPillarService strategicPillarService;
	
	@Autowired
	private ThemeService themeService;
	
	@Autowired
	private OutcomeService outcomeService;
	
	@Autowired
	private OutputServices outputServices;
	
	@Autowired
	private KeyActivityService keyActivityService;
	
	@Autowired
	PrincipalUtil principalUtil;
	
	@RequestMapping(value = "uploadFileValidation", method = RequestMethod.POST)
	public @ResponseBody ModelAndView userUploadValidation(@RequestParam("file") MultipartFile file)
			throws IOException {
		Planning planning = new Planning();
		KeyActivity keyActivity = new KeyActivity();
//		MultipartFile file = re.getFile("uploadPlanning");
		Workbook workbook = null;
		List<Integer> reportingPeriodList = new ArrayList<Integer>();
		List<Integer> keyActivityList = new ArrayList<Integer>();
		List<Integer> subActivityList = new ArrayList<Integer>();
		FileInputStream fileInputStream = null;
		ByteArrayInputStream byteArrayInputStream = null;
		Row row = null;
		try {
			fileInputStream = (FileInputStream) file.getInputStream();
		} catch (Exception ex) {
			byteArrayInputStream = (ByteArrayInputStream) file.getInputStream();
		}
		if (fileInputStream != null) {
			try {
				workbook = WorkbookFactory.create(fileInputStream);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (byteArrayInputStream != null) {
			try {
				workbook = WorkbookFactory.create(byteArrayInputStream);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Sheet sheet = workbook.getSheetAt(0);
		Integer prow = sheet.getPhysicalNumberOfRows();
		System.out.println(prow);
		int q = 2;
		String reportingPeriod0 = null,reportingPeriod1 = null,reportingPeriod2 = null,reportingPeriod3 = null,reportingPeriod4 = null,reportingPeriod5 = null,reportingPeriod6 = null,reportingPeriod7 = null,reportingPeriod8 = null,reportingPeriod9 = null,reportingPeriod10 = null,reportingPeriod11 = null,reportingPeriod12 = null,reportingPeriod13 = null,reportingPeriod14 = null,reportingPeriod15 = null;
		String sqno,ActivityName = null;
		if (sheet.getPhysicalNumberOfRows() > 0) {
			int lastRowNum = sheet.getLastRowNum();
			for (int j = 0; j <= lastRowNum; j++) {
				row = sheet.getRow(j);
				if (j == 0) {
					sqno = row.getCell(0, Row.CREATE_NULL_AS_BLANK)
							.toString().trim();
					ActivityName = row
							.getCell(1, Row.CREATE_NULL_AS_BLANK).toString()
							.trim();
					reportingPeriod0 = row.getCell(2, Row.CREATE_NULL_AS_BLANK)
							.toString().trim();
					String y[] = null,year = null,quater1 = null;
					ReportingPeriod period = new ReportingPeriod();
					if (reportingPeriod0 != null && reportingPeriod0 != "") {
						y = reportingPeriod0.split("-");
						year = y[0].trim();
						quater1 = y[1].trim();
						period = reportingPeriodService.getByYearAndName(year, quater1);
						if(period == null){
							reportingPeriodList.add(3);
						}
						reportingPeriod1 = row
								.getCell(3, Row.CREATE_NULL_AS_BLANK)
								.toString().trim();
						q++;
						if (reportingPeriod1 != null && reportingPeriod1 != "") {
							y = reportingPeriod1.split("-");
							year = y[0].trim();
							quater1 = y[1].trim();
							period = reportingPeriodService.getByYearAndName(year, quater1);
							if(period == null){
								reportingPeriodList.add(4);
							}
							reportingPeriod2 = row
									.getCell(4, Row.CREATE_NULL_AS_BLANK)
									.toString().trim();
							q++;
							if (reportingPeriod2 != null
									&& reportingPeriod2 != "") {
								y = reportingPeriod2.split("-");
								year = y[0].trim();
								quater1 = y[1].trim();
								period = reportingPeriodService.getByYearAndName(year, quater1);
								if(period == null){
									reportingPeriodList.add(5);
								}
								reportingPeriod3 = row
										.getCell(5, Row.CREATE_NULL_AS_BLANK)
										.toString().trim();
								q++;
								if (reportingPeriod3 != null
										&& reportingPeriod3 != "") {
									y = reportingPeriod3.split("-");
									year = y[0].trim();
									quater1 = y[1].trim();
									period = reportingPeriodService.getByYearAndName(year, quater1);
									if(period == null){
										reportingPeriodList.add(6);
									}
									reportingPeriod4 = row
											.getCell(6,
													Row.CREATE_NULL_AS_BLANK)
											.toString().trim();
									q++;
									if (reportingPeriod4 != null
											&& reportingPeriod4 != "") {
										y = reportingPeriod4.split("-");
										year = y[0].trim();
										quater1 = y[1].trim();
										period = reportingPeriodService.getByYearAndName(year, quater1);
										if(period == null){
											reportingPeriodList.add(7);
										}
										reportingPeriod5 = row
												.getCell(
														7,
														Row.CREATE_NULL_AS_BLANK)
												.toString().trim();
										q++;
										if (reportingPeriod5 != null
												&& reportingPeriod5 != "") {
											y = reportingPeriod5.split("-");
											year = y[0].trim();
											quater1 = y[1].trim();
											period = reportingPeriodService.getByYearAndName(year, quater1);
											if(period == null){
												reportingPeriodList.add(8);
											}
											reportingPeriod6 = row
													.getCell(
															8,
															Row.CREATE_NULL_AS_BLANK)
													.toString().trim();
											q++;
											if (reportingPeriod6 != null
													&& reportingPeriod6 != "") {
												y = reportingPeriod6.split("-");
												year = y[0].trim();
												quater1 = y[1].trim();
												period = reportingPeriodService.getByYearAndName(year, quater1);
												if(period == null){
													reportingPeriodList.add(9);
												}
												reportingPeriod7 = row
														.getCell(
																9,
																Row.CREATE_NULL_AS_BLANK)
														.toString().trim();
												q++;
												if (reportingPeriod7 != null
														&& reportingPeriod7 != "") {
													y = reportingPeriod7.split("-");
													year = y[0].trim();
													quater1 = y[1].trim();
													period = reportingPeriodService.getByYearAndName(year, quater1);
													if(period == null){
														reportingPeriodList.add(10);
													}
													reportingPeriod8 = row
															.getCell(
																	10,
																	Row.CREATE_NULL_AS_BLANK)
															.toString().trim();
													q++;
													if (reportingPeriod8 != null
															&& reportingPeriod8 != "") {
														y = reportingPeriod8.split("-");
														year = y[0].trim();
														quater1 = y[1].trim();
														period = reportingPeriodService.getByYearAndName(year, quater1);
														if(period == null){
															reportingPeriodList.add(11);
														}
														reportingPeriod9 = row
																.getCell(
																		11,
																		Row.CREATE_NULL_AS_BLANK)
																.toString()
																.trim();
														q++;
														if (reportingPeriod9 != null
																&& reportingPeriod9 != "") {
															y = reportingPeriod9.split("-");
															year = y[0].trim();
															quater1 = y[1].trim();
															period = reportingPeriodService.getByYearAndName(year, quater1);
															if(period == null){
																reportingPeriodList.add(12);
															}
															reportingPeriod10 = row
																	.getCell(
																			12,
																			Row.CREATE_NULL_AS_BLANK)
																	.toString()
																	.trim();
															q++;
															if (reportingPeriod10 != null
																	&& reportingPeriod10 != "") {
																y = reportingPeriod10.split("-");
																year = y[0].trim();
																quater1 = y[1].trim();
																period = reportingPeriodService.getByYearAndName(year, quater1);
																if(period == null){
																	reportingPeriodList.add(13);
																}
																reportingPeriod11 = row
																		.getCell(
																				13,
																				Row.CREATE_NULL_AS_BLANK)
																		.toString()
																		.trim();
																q++;
																if (reportingPeriod11 != null
																		&& reportingPeriod11 != "") {
																	y = reportingPeriod11.split("-");
																	year = y[0].trim();
																	quater1 = y[1].trim();
																	period = reportingPeriodService.getByYearAndName(year, quater1);
																	if(period == null){
																		reportingPeriodList.add(14);
																	}
																	reportingPeriod12 = row
																			.getCell(
																					14,
																					Row.CREATE_NULL_AS_BLANK)
																			.toString()
																			.trim();
																	q++;
																	if (reportingPeriod12 != null
																			&& reportingPeriod12 != "") {
																		y = reportingPeriod12.split("-");
																		year = y[0].trim();
																		quater1 = y[1].trim();
																		period = reportingPeriodService.getByYearAndName(year, quater1);
																		if(period == null){
																			reportingPeriodList.add(15);
																		}
																		reportingPeriod13 = row
																				.getCell(
																						15,
																						Row.CREATE_NULL_AS_BLANK)
																				.toString()
																				.trim();
																		q++;
																		if (reportingPeriod13 != null
																				&& reportingPeriod13 != "") {
																			y = reportingPeriod13.split("-");
																			year = y[0].trim();
																			quater1 = y[1].trim();
																			period = reportingPeriodService.getByYearAndName(year, quater1);
																			if(period == null){
																				reportingPeriodList.add(16);
																			}
																			reportingPeriod14 = row
																					.getCell(
																							16,
																							Row.CREATE_NULL_AS_BLANK)
																					.toString()
																					.trim();
																			q++;
																			if (reportingPeriod14 != null
																					&& reportingPeriod14 != "") {
																				y = reportingPeriod14.split("-");
																				year = y[0].trim();
																				quater1 = y[1].trim();
																				period = reportingPeriodService.getByYearAndName(year, quater1);
																				if(period == null){
																					reportingPeriodList.add(17);
																				}
																				reportingPeriod15 = row
																						.getCell(
																								17,
																								Row.CREATE_NULL_AS_BLANK)
																						.toString()
																						.trim();
																				q++;
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

				}else {
					sqno = row.getCell(0, Row.CREATE_NULL_AS_BLANK)
							.toString().trim();
					String KeyActivityName = null;
					
					if(sqno.length() < 7){
						KeyActivityName = row
								.getCell(1, Row.CREATE_NULL_AS_BLANK).toString()
								.trim();
						keyActivity = keyActivityService.findByName(KeyActivityName);
						if(keyActivity == null){
							keyActivityList.add(row.getRowNum());
						}
					}
					if(sqno.length() == 7 ){
						ActivityName = row
								.getCell(1, Row.CREATE_NULL_AS_BLANK).toString()
								.trim();
						SubActivity subActivity = subActivityService.getSubActivityByNameAndKeyActivity(ActivityName,keyActivity);
						if(subActivity == null){
							subActivityList.add(row.getRowNum());
						}else{
						for(int i = 3 ; i<=q;i++ ){
							if(i==3){
								String y[] = reportingPeriod0.split("-");
								String year = y[0].trim();
								String quater1 = y[1].trim();
								ReportingPeriod period = reportingPeriodService.getByYearAndName(year, quater1);
								if(period != null){
									String quater = row.getCell(2, Row.CREATE_NULL_AS_BLANK)
											.toString().trim();
									if(quater != null && quater.equals("Y")){
										planningServices.save(subActivity.getId(), period.getId());
									}else {
									Planning planning2 = planningServices.findBySubActivityAndReportingPeriod(subActivity, period);
									if(planning2 != null){
										planningServices.deletePlanning(planning2);	
									}
								}
							}
							}if(i==4){
								String y[] = reportingPeriod1.split("-");
								String year = y[0].trim();
								String quater1 = y[1].trim();
								ReportingPeriod period = reportingPeriodService.getByYearAndName(year, quater1);
								if(period != null){
									String quater = row.getCell(3, Row.CREATE_NULL_AS_BLANK)
											.toString().trim();
									if(quater != null && quater.equals("Y")){
										planningServices.save(subActivity.getId(), period.getId());
									}else {
									Planning planning2 = planningServices.findBySubActivityAndReportingPeriod(subActivity, period);
									if(planning2 != null){
										planningServices.deletePlanning(planning2);	
									}
								}
							}
							}if(i==5){
								String y[] = reportingPeriod2.split("-");
								String year = y[0].trim();
								String quater1 = y[1].trim();
								ReportingPeriod period = reportingPeriodService.getByYearAndName(year, quater1);
								if(period != null){
									String quater = row.getCell(4, Row.CREATE_NULL_AS_BLANK)
											.toString().trim();
									if(quater != null && quater.equals("Y")){
										planningServices.save(subActivity.getId(), period.getId());
									}else {
									Planning planning2 = planningServices.findBySubActivityAndReportingPeriod(subActivity, period);
									if(planning2 != null){
										planningServices.deletePlanning(planning2);	
									}
								}
							}
							}if(i==6){
								String y[] = reportingPeriod3.split("-");
								String year = y[0].trim();
								String quater1 = y[1].trim();
								ReportingPeriod period = reportingPeriodService.getByYearAndName(year, quater1);
								if(period != null){
									String quater = row.getCell(5, Row.CREATE_NULL_AS_BLANK)
											.toString().trim();
									if(quater != null && quater.equals("Y")){
										planningServices.save(subActivity.getId(), period.getId());
									}else {
									Planning planning2 = planningServices.findBySubActivityAndReportingPeriod(subActivity, period);
									if(planning2 != null){
										planningServices.deletePlanning(planning2);	
									}
								}
							}
							}if(i==7){
								String y[] = reportingPeriod4.split("-");
								String year = y[0].trim();
								String quater1 = y[1].trim();
								ReportingPeriod period = reportingPeriodService.getByYearAndName(year, quater1);
								if(period != null){
									String quater = row.getCell(6, Row.CREATE_NULL_AS_BLANK)
											.toString().trim();
									if(quater != null && quater.equals("Y")){
										planningServices.save(subActivity.getId(), period.getId());
									}else {
									Planning planning2 = planningServices.findBySubActivityAndReportingPeriod(subActivity, period);
									if(planning2 != null){
										planningServices.deletePlanning(planning2);	
									}
								}
							}
							}if(i==8){
								String y[] = reportingPeriod5.split("-");
								String year = y[0].trim();
								String quater1 = y[1].trim();
								ReportingPeriod period = reportingPeriodService.getByYearAndName(year, quater1);
								if(period != null){
									String quater = row.getCell(7, Row.CREATE_NULL_AS_BLANK)
											.toString().trim();
									if(quater != null && quater.equals("Y")){
										planningServices.save(subActivity.getId(), period.getId());
									}else {
									Planning planning2 = planningServices.findBySubActivityAndReportingPeriod(subActivity, period);
									if(planning2 != null){
										planningServices.deletePlanning(planning2);	
									}
								}
							}
							}if(i==9){
								String y[] = reportingPeriod6.split("-");
								String year = y[0].trim();
								String quater1 = y[1].trim();
								ReportingPeriod period = reportingPeriodService.getByYearAndName(year, quater1);
								if(period != null){
									String quater = row.getCell(8, Row.CREATE_NULL_AS_BLANK)
											.toString().trim();
									if(quater != null && quater.equals("Y")){
										planningServices.save(subActivity.getId(), period.getId());
									}else {
									Planning planning2 = planningServices.findBySubActivityAndReportingPeriod(subActivity, period);
									if(planning2 != null){
										planningServices.deletePlanning(planning2);	
									}
								}
							}
							}if(i==10){
								String y[] = reportingPeriod7.split("-");
								String year = y[0].trim();
								String quater1 = y[1].trim();
								ReportingPeriod period = reportingPeriodService.getByYearAndName(year, quater1);
								if(period != null){
									String quater = row.getCell(9, Row.CREATE_NULL_AS_BLANK)
											.toString().trim();
									if(quater != null && quater.equals("Y")){
										planningServices.save(subActivity.getId(), period.getId());
									}else {
									Planning planning2 = planningServices.findBySubActivityAndReportingPeriod(subActivity, period);
									if(planning2 != null){
										planningServices.deletePlanning(planning2);	
									}
								}
							}
							}if(i==11){
								String y[] = reportingPeriod8.split("-");
								String year = y[0].trim();
								String quater1 = y[1].trim();
								ReportingPeriod period = reportingPeriodService.getByYearAndName(year, quater1);
								if(period != null){
									String quater = row.getCell(10, Row.CREATE_NULL_AS_BLANK)
											.toString().trim();
									if(quater != null && quater.equals("Y")){
										planningServices.save(subActivity.getId(), period.getId());
									}else {
									Planning planning2 = planningServices.findBySubActivityAndReportingPeriod(subActivity, period);
									if(planning2 != null){
										planningServices.deletePlanning(planning2);	
									}
								}
							}
							}if(i==12){
								String y[] = reportingPeriod9.split("-");
								String year = y[0].trim();
								String quater1 = y[1].trim();
								ReportingPeriod period = reportingPeriodService.getByYearAndName(year, quater1);
								if(period != null){
									String quater = row.getCell(11, Row.CREATE_NULL_AS_BLANK)
											.toString().trim();
									if(quater != null && quater.equals("Y")){
										planningServices.save(subActivity.getId(), period.getId());
									}else {
									Planning planning2 = planningServices.findBySubActivityAndReportingPeriod(subActivity, period);
									if(planning2 != null){
										planningServices.deletePlanning(planning2);	
									}
								}
							}
							}if(i==13){
								String y[] = reportingPeriod10.split("-");
								String year = y[0].trim();
								String quater1 = y[1].trim();
								ReportingPeriod period = reportingPeriodService.getByYearAndName(year, quater1);
								if(period != null){
									String quater = row.getCell(12, Row.CREATE_NULL_AS_BLANK)
											.toString().trim();
									if(quater != null && quater.equals("Y")){
										planningServices.save(subActivity.getId(), period.getId());
									}else {
									Planning planning2 = planningServices.findBySubActivityAndReportingPeriod(subActivity, period);
									if(planning2 != null){
										planningServices.deletePlanning(planning2);	
									}
								}
							}
							}if(i==14){
								String y[] = reportingPeriod11.split("-");
								String year = y[0].trim();
								String quater1 = y[1].trim();
								ReportingPeriod period = reportingPeriodService.getByYearAndName(year, quater1);
								if(period != null){
									String quater = row.getCell(13, Row.CREATE_NULL_AS_BLANK)
											.toString().trim();
									if(quater != null && quater.equals("Y")){
										planningServices.save(subActivity.getId(), period.getId());
									}else {
									Planning planning2 = planningServices.findBySubActivityAndReportingPeriod(subActivity, period);
									if(planning2 != null){
										planningServices.deletePlanning(planning2);	
									}
								}
							}
							}if(i==15){
								String y[] = reportingPeriod12.split("-");
								String year = y[0].trim();
								String quater1 = y[1].trim();
								ReportingPeriod period = reportingPeriodService.getByYearAndName(year, quater1);
								if(period != null){
									String quater = row.getCell(14, Row.CREATE_NULL_AS_BLANK)
											.toString().trim();
									if(quater != null && quater.equals("Y")){
										planningServices.save(subActivity.getId(), period.getId());
									}else {
									Planning planning2 = planningServices.findBySubActivityAndReportingPeriod(subActivity, period);
									if(planning2 != null){
										planningServices.deletePlanning(planning2);	
									}
								}
							}
							}if(i==16){
								String y[] = reportingPeriod13.split("-");
								String year = y[0].trim();
								String quater1 = y[1].trim();
								ReportingPeriod period = reportingPeriodService.getByYearAndName(year, quater1);
								if(period != null){
									String quater = row.getCell(15, Row.CREATE_NULL_AS_BLANK)
											.toString().trim();
									if(quater != null && quater.equals("Y")){
										planningServices.save(subActivity.getId(), period.getId());
									}else {
									Planning planning2 = planningServices.findBySubActivityAndReportingPeriod(subActivity, period);
									if(planning2 != null){
										planningServices.deletePlanning(planning2);	
									}
								}
							}
							}if(i==17){
								String y[] = reportingPeriod14.split("-");
								String year = y[0].trim();
								String quater1 = y[1].trim();
								ReportingPeriod period = reportingPeriodService.getByYearAndName(year, quater1);
								if(period != null){
									String quater = row.getCell(16, Row.CREATE_NULL_AS_BLANK)
											.toString().trim();
									if(quater != null && quater.equals("Y")){
										planningServices.save(subActivity.getId(), period.getId());
									}else {
									Planning planning2 = planningServices.findBySubActivityAndReportingPeriod(subActivity, period);
									if(planning2 != null){
										planningServices.deletePlanning(planning2);	
									}
								}
							}
							}if(i==18){
								String y[] = reportingPeriod15.split("-");
								String year = y[0].trim();
								String quater1 = y[1].trim();
								ReportingPeriod period = reportingPeriodService.getByYearAndName(year, quater1);
								if(period != null){
									String quater = row.getCell(17, Row.CREATE_NULL_AS_BLANK)
											.toString().trim();
									if(quater != null && quater.equals("Y")){
										planningServices.save(subActivity.getId(), period.getId());
									}else {
									Planning planning2 = planningServices.findBySubActivityAndReportingPeriod(subActivity, period);
									if(planning2 != null){
										planningServices.deletePlanning(planning2);	
									}
								}
							}
							}
							
						}
					}
					}else {
						
					}
				}
			}
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:filterHierarchy?action=plan");
		return modelAndView;
	
	}
	
	
	
	@RequestMapping(value = "uploadFile", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public ModelAndView userUpload(MultipartHttpServletRequest re, Model model)
			throws IOException {
		Planning planning = new Planning();
		KeyActivity keyActivity = new KeyActivity();
		MultipartFile file = re.getFile("uploadPlanning");
		Workbook workbook = null;
		FileInputStream fileInputStream = null;
		ByteArrayInputStream byteArrayInputStream = null;
		Row row = null;
		try {
			fileInputStream = (FileInputStream) file.getInputStream();
		} catch (Exception ex) {
			byteArrayInputStream = (ByteArrayInputStream) file.getInputStream();
		}
		if (fileInputStream != null) {
			try {
				workbook = WorkbookFactory.create(fileInputStream);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (byteArrayInputStream != null) {
			try {
				workbook = WorkbookFactory.create(byteArrayInputStream);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Sheet sheet = workbook.getSheetAt(0);
		Integer prow = sheet.getPhysicalNumberOfRows();
		System.out.println(prow);
		int q = 2;
		String reportingPeriod0 = null,reportingPeriod1 = null,reportingPeriod2 = null,reportingPeriod3 = null,reportingPeriod4 = null,reportingPeriod5 = null,reportingPeriod6 = null,reportingPeriod7 = null,reportingPeriod8 = null,reportingPeriod9 = null,reportingPeriod10 = null,reportingPeriod11 = null,reportingPeriod12 = null,reportingPeriod13 = null,reportingPeriod14 = null,reportingPeriod15 = null;
		String sqno,ActivityName = null;
		if (sheet.getPhysicalNumberOfRows() > 0) {
			int lastRowNum = sheet.getLastRowNum();
			for (int j = 0; j <= lastRowNum; j++) {
				row = sheet.getRow(j);
				if (j == 0) {
					sqno = row.getCell(0, Row.CREATE_NULL_AS_BLANK)
							.toString().trim();
					ActivityName = row
							.getCell(1, Row.CREATE_NULL_AS_BLANK).toString()
							.trim();
					reportingPeriod0 = row.getCell(2, Row.CREATE_NULL_AS_BLANK)
							.toString().trim();
					if (reportingPeriod0 != null && reportingPeriod0 != "") {
						reportingPeriod1 = row
								.getCell(3, Row.CREATE_NULL_AS_BLANK)
								.toString().trim();
						q++;
						if (reportingPeriod1 != null && reportingPeriod1 != "") {
							reportingPeriod2 = row
									.getCell(4, Row.CREATE_NULL_AS_BLANK)
									.toString().trim();
							q++;
							if (reportingPeriod2 != null
									&& reportingPeriod2 != "") {
								reportingPeriod3 = row
										.getCell(5, Row.CREATE_NULL_AS_BLANK)
										.toString().trim();
								q++;
								if (reportingPeriod3 != null
										&& reportingPeriod3 != "") {
									reportingPeriod4 = row
											.getCell(6,
													Row.CREATE_NULL_AS_BLANK)
											.toString().trim();
									q++;
									if (reportingPeriod4 != null
											&& reportingPeriod4 != "") {

										reportingPeriod5 = row
												.getCell(
														7,
														Row.CREATE_NULL_AS_BLANK)
												.toString().trim();
										q++;
										if (reportingPeriod5 != null
												&& reportingPeriod5 != "") {

											reportingPeriod6 = row
													.getCell(
															8,
															Row.CREATE_NULL_AS_BLANK)
													.toString().trim();
											q++;
											if (reportingPeriod6 != null
													&& reportingPeriod6 != "") {

												reportingPeriod7 = row
														.getCell(
																9,
																Row.CREATE_NULL_AS_BLANK)
														.toString().trim();
												q++;
												if (reportingPeriod7 != null
														&& reportingPeriod7 != "") {

													reportingPeriod8 = row
															.getCell(
																	10,
																	Row.CREATE_NULL_AS_BLANK)
															.toString().trim();
													q++;
													if (reportingPeriod8 != null
															&& reportingPeriod8 != "") {
														reportingPeriod9 = row
																.getCell(
																		11,
																		Row.CREATE_NULL_AS_BLANK)
																.toString()
																.trim();
														q++;
														if (reportingPeriod9 != null
																&& reportingPeriod9 != "") {
															reportingPeriod10 = row
																	.getCell(
																			12,
																			Row.CREATE_NULL_AS_BLANK)
																	.toString()
																	.trim();
															q++;
															if (reportingPeriod10 != null
																	&& reportingPeriod10 != "") {
																reportingPeriod11 = row
																		.getCell(
																				13,
																				Row.CREATE_NULL_AS_BLANK)
																		.toString()
																		.trim();
																q++;
																if (reportingPeriod11 != null
																		&& reportingPeriod11 != "") {
																	reportingPeriod12 = row
																			.getCell(
																					14,
																					Row.CREATE_NULL_AS_BLANK)
																			.toString()
																			.trim();
																	q++;
																	if (reportingPeriod12 != null
																			&& reportingPeriod12 != "") {
																		reportingPeriod13 = row
																				.getCell(
																						15,
																						Row.CREATE_NULL_AS_BLANK)
																				.toString()
																				.trim();
																		q++;
																		if (reportingPeriod13 != null
																				&& reportingPeriod13 != "") {
																			reportingPeriod14 = row
																					.getCell(
																							16,
																							Row.CREATE_NULL_AS_BLANK)
																					.toString()
																					.trim();
																			q++;
																			if (reportingPeriod14 != null
																					&& reportingPeriod14 != "") {
																				reportingPeriod15 = row
																						.getCell(
																								17,
																								Row.CREATE_NULL_AS_BLANK)
																						.toString()
																						.trim();
																				q++;
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

				}else {
					sqno = row.getCell(0, Row.CREATE_NULL_AS_BLANK)
							.toString().trim();
					String KeyActivityName = null;
					
					if(sqno.length() < 7){
						KeyActivityName = row
								.getCell(1, Row.CREATE_NULL_AS_BLANK).toString()
								.trim();
						keyActivity = keyActivityService.findByName(KeyActivityName);
					}
					if(sqno.length() == 7 ){
						ActivityName = row
								.getCell(1, Row.CREATE_NULL_AS_BLANK).toString()
								.trim();
						SubActivity subActivity = subActivityService.getSubActivityByNameAndKeyActivity(ActivityName,keyActivity);
						
						for(int i = 3 ; i<=q;i++ ){
							if(i==3){
								String y[] = reportingPeriod0.split("-");
								String year = y[0].trim();
								String quater1 = y[1].trim();
								ReportingPeriod period = reportingPeriodService.getByYearAndName(year, quater1);
								if(period != null){
									String quater = row.getCell(2, Row.CREATE_NULL_AS_BLANK)
											.toString().trim();
									if(quater != null && quater.equals("Y")){
										planningServices.save(subActivity.getId(), period.getId());
									}else {
									Planning planning2 = planningServices.findBySubActivityAndReportingPeriod(subActivity, period);
									if(planning2 != null){
										planningServices.deletePlanning(planning2);	
									}
								}
							}
							}if(i==4){
								String y[] = reportingPeriod1.split("-");
								String year = y[0].trim();
								String quater1 = y[1].trim();
								ReportingPeriod period = reportingPeriodService.getByYearAndName(year, quater1);
								if(period != null){
									String quater = row.getCell(3, Row.CREATE_NULL_AS_BLANK)
											.toString().trim();
									if(quater != null && quater.equals("Y")){
										planningServices.save(subActivity.getId(), period.getId());
									}else {
									Planning planning2 = planningServices.findBySubActivityAndReportingPeriod(subActivity, period);
									if(planning2 != null){
										planningServices.deletePlanning(planning2);	
									}
								}
							}
							}if(i==5){
								String y[] = reportingPeriod2.split("-");
								String year = y[0].trim();
								String quater1 = y[1].trim();
								ReportingPeriod period = reportingPeriodService.getByYearAndName(year, quater1);
								if(period != null){
									String quater = row.getCell(4, Row.CREATE_NULL_AS_BLANK)
											.toString().trim();
									if(quater != null && quater.equals("Y")){
										planningServices.save(subActivity.getId(), period.getId());
									}else {
									Planning planning2 = planningServices.findBySubActivityAndReportingPeriod(subActivity, period);
									if(planning2 != null){
										planningServices.deletePlanning(planning2);	
									}
								}
							}
							}if(i==6){
								String y[] = reportingPeriod3.split("-");
								String year = y[0].trim();
								String quater1 = y[1].trim();
								ReportingPeriod period = reportingPeriodService.getByYearAndName(year, quater1);
								if(period != null){
									String quater = row.getCell(5, Row.CREATE_NULL_AS_BLANK)
											.toString().trim();
									if(quater != null && quater.equals("Y")){
										planningServices.save(subActivity.getId(), period.getId());
									}else {
									Planning planning2 = planningServices.findBySubActivityAndReportingPeriod(subActivity, period);
									if(planning2 != null){
										planningServices.deletePlanning(planning2);	
									}
								}
							}
							}if(i==7){
								String y[] = reportingPeriod4.split("-");
								String year = y[0].trim();
								String quater1 = y[1].trim();
								ReportingPeriod period = reportingPeriodService.getByYearAndName(year, quater1);
								if(period != null){
									String quater = row.getCell(6, Row.CREATE_NULL_AS_BLANK)
											.toString().trim();
									if(quater != null && quater.equals("Y")){
										planningServices.save(subActivity.getId(), period.getId());
									}else {
									Planning planning2 = planningServices.findBySubActivityAndReportingPeriod(subActivity, period);
									if(planning2 != null){
										planningServices.deletePlanning(planning2);	
									}
								}
							}
							}if(i==8){
								String y[] = reportingPeriod5.split("-");
								String year = y[0].trim();
								String quater1 = y[1].trim();
								ReportingPeriod period = reportingPeriodService.getByYearAndName(year, quater1);
								if(period != null){
									String quater = row.getCell(7, Row.CREATE_NULL_AS_BLANK)
											.toString().trim();
									if(quater != null && quater.equals("Y")){
										planningServices.save(subActivity.getId(), period.getId());
									}else {
									Planning planning2 = planningServices.findBySubActivityAndReportingPeriod(subActivity, period);
									if(planning2 != null){
										planningServices.deletePlanning(planning2);	
									}
								}
							}
							}if(i==9){
								String y[] = reportingPeriod6.split("-");
								String year = y[0].trim();
								String quater1 = y[1].trim();
								ReportingPeriod period = reportingPeriodService.getByYearAndName(year, quater1);
								if(period != null){
									String quater = row.getCell(8, Row.CREATE_NULL_AS_BLANK)
											.toString().trim();
									if(quater != null && quater.equals("Y")){
										planningServices.save(subActivity.getId(), period.getId());
									}else {
									Planning planning2 = planningServices.findBySubActivityAndReportingPeriod(subActivity, period);
									if(planning2 != null){
										planningServices.deletePlanning(planning2);	
									}
								}
							}
							}if(i==10){
								String y[] = reportingPeriod7.split("-");
								String year = y[0].trim();
								String quater1 = y[1].trim();
								ReportingPeriod period = reportingPeriodService.getByYearAndName(year, quater1);
								if(period != null){
									String quater = row.getCell(9, Row.CREATE_NULL_AS_BLANK)
											.toString().trim();
									if(quater != null && quater.equals("Y")){
										planningServices.save(subActivity.getId(), period.getId());
									}else {
									Planning planning2 = planningServices.findBySubActivityAndReportingPeriod(subActivity, period);
									if(planning2 != null){
										planningServices.deletePlanning(planning2);	
									}
								}
							}
							}if(i==11){
								String y[] = reportingPeriod8.split("-");
								String year = y[0].trim();
								String quater1 = y[1].trim();
								ReportingPeriod period = reportingPeriodService.getByYearAndName(year, quater1);
								if(period != null){
									String quater = row.getCell(10, Row.CREATE_NULL_AS_BLANK)
											.toString().trim();
									if(quater != null && quater.equals("Y")){
										planningServices.save(subActivity.getId(), period.getId());
									}else {
									Planning planning2 = planningServices.findBySubActivityAndReportingPeriod(subActivity, period);
									if(planning2 != null){
										planningServices.deletePlanning(planning2);	
									}
								}
							}
							}if(i==12){
								String y[] = reportingPeriod9.split("-");
								String year = y[0].trim();
								String quater1 = y[1].trim();
								ReportingPeriod period = reportingPeriodService.getByYearAndName(year, quater1);
								if(period != null){
									String quater = row.getCell(11, Row.CREATE_NULL_AS_BLANK)
											.toString().trim();
									if(quater != null && quater.equals("Y")){
										planningServices.save(subActivity.getId(), period.getId());
									}else {
									Planning planning2 = planningServices.findBySubActivityAndReportingPeriod(subActivity, period);
									if(planning2 != null){
										planningServices.deletePlanning(planning2);	
									}
								}
							}
							}if(i==13){
								String y[] = reportingPeriod10.split("-");
								String year = y[0].trim();
								String quater1 = y[1].trim();
								ReportingPeriod period = reportingPeriodService.getByYearAndName(year, quater1);
								if(period != null){
									String quater = row.getCell(12, Row.CREATE_NULL_AS_BLANK)
											.toString().trim();
									if(quater != null && quater.equals("Y")){
										planningServices.save(subActivity.getId(), period.getId());
									}else {
									Planning planning2 = planningServices.findBySubActivityAndReportingPeriod(subActivity, period);
									if(planning2 != null){
										planningServices.deletePlanning(planning2);	
									}
								}
							}
							}if(i==14){
								String y[] = reportingPeriod11.split("-");
								String year = y[0].trim();
								String quater1 = y[1].trim();
								ReportingPeriod period = reportingPeriodService.getByYearAndName(year, quater1);
								if(period != null){
									String quater = row.getCell(13, Row.CREATE_NULL_AS_BLANK)
											.toString().trim();
									if(quater != null && quater.equals("Y")){
										planningServices.save(subActivity.getId(), period.getId());
									}else {
									Planning planning2 = planningServices.findBySubActivityAndReportingPeriod(subActivity, period);
									if(planning2 != null){
										planningServices.deletePlanning(planning2);	
									}
								}
							}
							}if(i==15){
								String y[] = reportingPeriod12.split("-");
								String year = y[0].trim();
								String quater1 = y[1].trim();
								ReportingPeriod period = reportingPeriodService.getByYearAndName(year, quater1);
								if(period != null){
									String quater = row.getCell(14, Row.CREATE_NULL_AS_BLANK)
											.toString().trim();
									if(quater != null && quater.equals("Y")){
										planningServices.save(subActivity.getId(), period.getId());
									}else {
									Planning planning2 = planningServices.findBySubActivityAndReportingPeriod(subActivity, period);
									if(planning2 != null){
										planningServices.deletePlanning(planning2);	
									}
								}
							}
							}if(i==16){
								String y[] = reportingPeriod13.split("-");
								String year = y[0].trim();
								String quater1 = y[1].trim();
								ReportingPeriod period = reportingPeriodService.getByYearAndName(year, quater1);
								if(period != null){
									String quater = row.getCell(15, Row.CREATE_NULL_AS_BLANK)
											.toString().trim();
									if(quater != null && quater.equals("Y")){
										planningServices.save(subActivity.getId(), period.getId());
									}else {
									Planning planning2 = planningServices.findBySubActivityAndReportingPeriod(subActivity, period);
									if(planning2 != null){
										planningServices.deletePlanning(planning2);	
									}
								}
							}
							}if(i==17){
								String y[] = reportingPeriod14.split("-");
								String year = y[0].trim();
								String quater1 = y[1].trim();
								ReportingPeriod period = reportingPeriodService.getByYearAndName(year, quater1);
								if(period != null){
									String quater = row.getCell(16, Row.CREATE_NULL_AS_BLANK)
											.toString().trim();
									if(quater != null && quater.equals("Y")){
										planningServices.save(subActivity.getId(), period.getId());
									}else {
									Planning planning2 = planningServices.findBySubActivityAndReportingPeriod(subActivity, period);
									if(planning2 != null){
										planningServices.deletePlanning(planning2);	
									}
								}
							}
							}if(i==18){
								String y[] = reportingPeriod15.split("-");
								String year = y[0].trim();
								String quater1 = y[1].trim();
								ReportingPeriod period = reportingPeriodService.getByYearAndName(year, quater1);
								if(period != null){
									String quater = row.getCell(17, Row.CREATE_NULL_AS_BLANK)
											.toString().trim();
									if(quater != null && quater.equals("Y")){
										planningServices.save(subActivity.getId(), period.getId());
									}else {
									Planning planning2 = planningServices.findBySubActivityAndReportingPeriod(subActivity, period);
									if(planning2 != null){
										planningServices.deletePlanning(planning2);	
									}
								}
							}
							}
							
						}
					}else {
						
					}
				}
			}
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:filterHierarchy?action=plan");
		return modelAndView;
	
	}
	
	

	@RequestMapping(value = "addPlan", method = RequestMethod.GET)
	public @ResponseBody void save(@RequestParam("idValues") String idValues){
		LOGGER.info("add Plan");
		
		
		JSONObject jsonObject=new JSONObject(idValues);
		JSONArray subActivityIds=jsonObject.getJSONArray("subActivityIds");
		JSONArray reportingPeriodIds=jsonObject.getJSONArray("reportingPeriodIds");
		
		for (int i = 0; i < subActivityIds.length(); i++) {
			int subActivityId=subActivityIds.getInt(i);
			int reportingPeriodId=reportingPeriodIds.getInt(i);
			planningServices.save(subActivityId, reportingPeriodId);
		}
		
		/*
		for (int i = 0; i < subActivityIds.length; i++) {
			int subActivityId=Integer.parseInt(subActivityIds[i]);
			int reportingPeriodId=Integer.parseInt(subActivityIds[i]);
			planningServices.save(subActivityId, reportingPeriodId);
		}*/
		
		/*
		planningServices.save(planning);*/
	}
	
	@RequestMapping(value = "planningList")
	public ModelAndView listPlanning(ModelMap modelMap, HttpServletRequest request) {

		LOGGER.info("Listing All Plan ");
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		//getAccessRights(modelMap, username);

		modelMap.addAttribute("planningList", planningServices.getPlannings());
		return new ModelAndView("Planning/planningList");
	}
	
	@RequestMapping(value = "/editPlanning", method = RequestMethod.GET)
	public ModelAndView getStates(@ModelAttribute Planning planning,
			@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView(
				"Planning/planningAdd");
		if (planning.getId() != 0) {
			planning = planningServices.getById(id);
		}
		List<SubActivity> subActivities = new ArrayList<SubActivity>();
		List<SubActivity> subActivities2 = subActivityService.listAllSubActivity();
		for (SubActivity subActivity : subActivities2) {
			subActivities.add(subActivity);
		}
		
		List<ReportingPeriod>reportingPeriods = new ArrayList<ReportingPeriod>();
		List<ReportingPeriod>reportingPeriods2 = reportingPeriodService.getReportingPeriod();
		for(ReportingPeriod reportingPeriod : reportingPeriods2){
			reportingPeriods.add(reportingPeriod);
		}
		modelAndView.addObject("reportingPeriodsList",reportingPeriods);
		modelAndView.addObject("subActivitiesList", subActivities);
		modelAndView.addObject("planning", planning);
		return modelAndView;
	}

	
	
	public void getAccessRights(ModelMap modelMap, String username) {

		/*User user = userService.findByUsername(username);
		List<AccessRights> accessrightslist = new ArrayList<AccessRights>();
		// List<AccessRights> accessrightslist =
		// accessRightsService.listAccessRightsByRole(user.getUserRole().getId().intValue());
		List<String> features = new ArrayList<>();
		boolean STT_ADD = false;
		boolean STT_VIW = false;
		boolean STT_EDT = false;
		boolean STT_DEL = false;

		if (accessrightslist != null) {
			for (AccessRights accessRights : accessrightslist) {
				features.add(accessRights.getFeatures().getFeaturecode());
			}
			if (features != null) {
				for (String feature : features) {
					if (feature.equals(Modules.STT_ADD.toString())) {
						STT_ADD = true;
					}
					if (feature.equals(Modules.STT_VIW.toString())) {
						STT_VIW = true;
					}
					if (feature.equals(Modules.STT_EDT.toString())) {
						STT_EDT = true;
						;
					}
					if (feature.equals(Modules.STT_DEL.toString())) {
						STT_DEL = true;
					}
				}
			}
		}
		modelMap.addAttribute("STT_ADD", STT_ADD);
		modelMap.addAttribute("STT_VIW", STT_VIW);
		modelMap.addAttribute("STT_EDT", STT_EDT);
		modelMap.addAttribute("STT_DEL", STT_DEL);*/
	}
	
	
	
	/*
	 *Purpose	: 	get All plannings to 
	 *				make checked of checkbox of all the Quaters in project activity
	 */
	
	@Transactional
	@RequestMapping(value = "/getAllPlanning", method = RequestMethod.GET)
	public @ResponseBody List<String> getAllPlanning(){
		List<String> planningIdValues=new ArrayList<>();
		List<Planning> planningList=planningServices.getPlannings();
		for (Planning planning : planningList) {
			planningIdValues.add(planning.getSubActivity().getId().toString() +","+planning.getReportingPeriod().getId().toString());
		}
		return planningIdValues;
	}
	
	@RequestMapping(value = "/downloadPlanningTemplate", method = RequestMethod.GET)
	public @ResponseBody String downloadPlanningTemplate(@RequestParam("strPillarIds") String strPillarIds,@RequestParam("repPeriodIds") String repPeriodIds){
		
		String fileName = "Planning"+  ".xls";
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
		
		Font boldFont = hssfWorkbook.createFont();
		boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		boldFont.setColor(IndexedColors.BLACK.getIndex());
		
		CellStyle cellStylekeyName = hssfWorkbook.createCellStyle();
		cellStylekeyName.setLocked(false);
		cellStylekeyName.setFont(boldFont);
		cellStylekeyName.setWrapText(true);//set wraper text
		cellStylekeyName.setAlignment(CellStyle.ALIGN_CENTER);
		cellStylekeyName.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
		cellStylekeyName.setBorderRight(HSSFCellStyle.BORDER_THIN);            
		cellStylekeyName.setBorderTop(HSSFCellStyle.BORDER_THIN);              
		cellStylekeyName.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		
		
		
		CellStyle keyActivityStyle = hssfWorkbook.createCellStyle();
		keyActivityStyle.setWrapText(true);//set wraper text
		keyActivityStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
		keyActivityStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
		keyActivityStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
		keyActivityStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		
		CellStyle subActivityStyle = hssfWorkbook.createCellStyle();
		subActivityStyle.setWrapText(true);//set wraper text
		subActivityStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
		subActivityStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
		subActivityStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
		subActivityStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		
		CellStyle planStyle = hssfWorkbook.createCellStyle();
		planStyle.setWrapText(true);//set wraper text
		cellStylekeyName.setAlignment(CellStyle.ALIGN_CENTER);
		planStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
		planStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);            
		planStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);              
		planStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		
		String[] sColor=ReportController.hex2Rgb("#93C47D").split(",");
		
		int c1=Integer.parseInt(sColor[0]);
		int c2=Integer.parseInt(sColor[1]);
		int c3=Integer.parseInt(sColor[2]);
		
		HSSFColor skyBlue = ReportController.setColor(hssfWorkbook,(byte) c1, (byte) c2,(byte) c3);
		keyActivityStyle.setFillForegroundColor(skyBlue.getIndex());
		keyActivityStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		
		
		HSSFSheet planSheet = hssfWorkbook.createSheet("Plan Project");
		HSSFRow headerRow = planSheet.createRow(0);
		
		headerRow.setHeight((short)500);
		
		
		HSSFCell sequenceNumberTitle = headerRow.createCell(0);
		sequenceNumberTitle.setCellValue("Seq.No");
		sequenceNumberTitle.setCellStyle(cellStylekeyName);
		
		HSSFCell activityTitle = headerRow.createCell(1);
		activityTitle.setCellValue("Activity");
		activityTitle.setCellStyle(cellStylekeyName);
		activityTitle.getCellStyle().setLocked(true);
		
		planSheet.setColumnWidth(1, 12000);
		
		/*HSSFCell responsibleTitle = headerRow.createCell(2);
		responsibleTitle.setCellValue("Responsible Entity");
		
		HSSFCell partnerTitle = headerRow.createCell(3);
		partnerTitle.setCellValue("Partner Entity");
		
		HSSFCell movTitle = headerRow.createCell(4);
		movTitle.setCellValue("MOV");*/
		
		int rpCount=1;
		
		List<Integer> reportingIds=new ArrayList<Integer>();
		List<Integer> strategicIds=new ArrayList<Integer>();
		
		if(repPeriodIds.startsWith("0")){
			List<ReportingPeriod> reportingPeriods=reportingPeriodService.getAll();
			for (ReportingPeriod reportingPeriod : reportingPeriods) {
				rpCount++;
				
				reportingIds.add(reportingPeriod.getId());
				
				HSSFCell reportingPeriodTitle = headerRow.createCell(rpCount);
				reportingPeriodTitle.setCellValue(reportingPeriod.getYear()+" - "+reportingPeriod.getName());
				planSheet.setColumnWidth(rpCount, 2500);
				reportingPeriodTitle.setCellStyle(cellStylekeyName);
				reportingPeriodTitle.setCellType(Cell.CELL_TYPE_STRING);
				reportingPeriodTitle.getCellStyle().setLocked(true);
			}
			
		}else{
			String[] rpIds=repPeriodIds.split(",");
			for (int i = 0; i < rpIds.length; i++) {
				reportingIds.add(Integer.parseInt(rpIds[i]));
			}
			
			for (Integer id : reportingIds) {
				rpCount++;
				ReportingPeriod reportingPeriod=reportingPeriodService.getById(id);
				
				if(reportingPeriod != null){
					HSSFCell reportingPeriodTitle = headerRow.createCell(rpCount);
					reportingPeriodTitle.setCellValue(reportingPeriod.getYear()+" - "+reportingPeriod.getName());
					planSheet.setColumnWidth(rpCount, 2500);
					reportingPeriodTitle.setCellStyle(cellStylekeyName);
					reportingPeriodTitle.setCellType(Cell.CELL_TYPE_STRING);
				}
			}
			
		}
		if(strPillarIds.startsWith("0")){
			List<StrategicPillar> strategicPillars=strategicPillarService.getStrategicPillars();
			for (StrategicPillar strategicPillar : strategicPillars) {
				strategicIds.add(strategicPillar.getId());
			}
		}else{
			
			String[] sIds=strPillarIds.split(",");
			for (int i = 0; i < sIds.length; i++) {
				strategicIds.add(Integer.parseInt(sIds[i]));
			}
		}
		
		int rowCount=0;
		if(reportingIds.size() >0 && strategicIds.size() >0){
			List<KeyActivity> keyActivities=keyActivityService.findKeyActivitiesByStrategicPillarsAndOpenedReportingPeriods(strategicIds, reportingIds);
			if(keyActivities.size() > 0){
				
				for (KeyActivity keyActivity : keyActivities) {
					rowCount++;
					headerRow = planSheet.createRow(rowCount);
					
					if(keyActivity.getName().length() > 90){
						headerRow.setHeight((short)700);
					}else{
						headerRow.setHeight((short)500);
					}
					
					
					if(keyActivity != null){
						HSSFCell keyActSequenceNumber = headerRow.createCell(0);
						keyActSequenceNumber.setCellValue(keyActivity.getSequenceNumber());
						
						HSSFCell keyActivityName = headerRow.createCell(1);
						keyActivityName.setCellValue(keyActivity.getName());
						keyActivityName.setCellStyle(keyActivityStyle);
						
						int repCount=1;
						if(reportingIds.size() > 0){
							for (Integer rpId : reportingIds) {
								repCount++;
								HSSFCell keyActReportingPeriod = headerRow.createCell(repCount);
								keyActReportingPeriod.setCellValue("");
								keyActReportingPeriod.setCellStyle(subActivityStyle);
							}
						}
						
						
						headerRow.setHeight((short)500);
						List<SubActivity> subActivities=subActivityService.findByKeyActivity(keyActivity);
						for(SubActivity subActivity : subActivities){
							rowCount++;
							headerRow = planSheet.createRow(rowCount);
							HSSFCell subActivitySeqNumber = headerRow.createCell(0);
							if(subActivity.getName().length() > 90){
								headerRow.setHeight((short)700);
							}else{
								headerRow.setHeight((short)500);
							}
							
							subActivitySeqNumber.setCellValue(subActivity.getSequenceNumber());
							subActivitySeqNumber.setCellStyle(subActivityStyle);
							subActivitySeqNumber.setCellType(Cell.CELL_TYPE_STRING);
							
							HSSFCell subActivityName = headerRow.createCell(1);
							subActivityName.setCellValue(subActivity.getName());
							subActivityName.setCellStyle(subActivityStyle);
							subActivityName.setCellType(Cell.CELL_TYPE_STRING);
							int planCount=1;
							if(reportingIds.size() > 0){
								for (Integer rpId : reportingIds) {
									planCount++;
									ReportingPeriod reportingPeriod=reportingPeriodService.getById(rpId);
									if(reportingPeriod != null){
										Planning planning=planningServices.findBySubActivityAndReportingPeriod(subActivity, reportingPeriod);
										if(planning != null){
											HSSFCell planValue = headerRow.createCell(planCount);
											planValue.setCellValue("Y");
											planValue.setCellStyle(planStyle);
											planValue.setCellType(Cell.CELL_TYPE_STRING);
										}else{
											HSSFCell planValue = headerRow.createCell(planCount);
											planValue.setCellValue("");
											planValue.setCellStyle(planStyle);
											planValue.setCellType(Cell.CELL_TYPE_STRING);
										}
									}
								}
							}
						}
					}
				}
			}
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
		
	}
	
}
