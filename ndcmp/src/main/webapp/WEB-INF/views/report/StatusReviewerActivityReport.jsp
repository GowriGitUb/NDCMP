<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../include/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UNODC</title>
<%@include file="../include/commonlib.jsp"%>
<%@include file="../include/dataTable.jsp"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src='<c:url value="/static/jslib/bootstrap-datepicker.js"></c:url>' ></script>
<script type="text/javascript" src='<c:url value="/static/scripts/reportingPeriod.js"></c:url>'></script>
<script type="text/javascript" src="../static/scripts/menu.js"></script>
<script type="text/javascript" src="../static/scripts/report.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#tabs > li').removeClass('active');
	$('#reportsTab').attr('class', 'active');
	$('#activityStatusTab').attr('class','active');
	var selectedReportingPeriod = document.getElementById("reportingPeriodId").selectedIndex = "1";
	loadReviewerQuarter(selectedReportingPeriod);	
});
</script>
</head>

<body onload="advancedTable()">
	<div id="theme-wrapper">
		<%@include file="../include/menu.jsp"%>
		<div id="page-wrapper" class="container">
			<div class="row">
				<%@include file="../include/menuItems.jsp"%>
				<div id="content-wrapper">
					<div class="row">
						<div class="col-lg-12">
							<div class="main-box clearfix">
							<input type="hidden" id="roleId" value="<%= session.getAttribute("roleId") %>" />
							<div class="main-box-body clearfix">
									<br />
									<h1 class="pull-left">
										<span>Activity Status</span>
									</h1>
									<br/>
									<br/>
									<hr>
									<div class="form-inline form-group rcorners1"
										>
										<div class="row">
											<div class="col-md-12">
												<div class="col-md-6">
													<div class="control-label col-md-3">
														<label class=" pull-right"> <b>Year
																: &nbsp;</b>
														</label>
													</div>

													<div class="col-md-3">
														<select multiple id="reportingPeriodId" class="form-control"
															onchange="loadReviewerQuarter(this);" style="width: 320%;">
															<option selected="selected" value="0">---All---</option>
															<c:forEach items="${reportingPeriods}"
																var="reportingperiod">
																<option value="${reportingperiod}"
																	label="${reportingperiod}"></option>
															</c:forEach>
														</select>
													</div>

												</div>
												
												
												<div class="col-md-6">
													<div class="col-md-3">
														<label class="labelColor pull-right"> <b>Quarter
																 : &nbsp;</b></label>
													</div>
													<div class="col-md-3">
														<select  multiple class="form-control" id="yearId"
															onclick="loadReportPillars(this);" style="width: 320%;">
															<option selected="selected" value="0" label="---All---"></option>
														</select>

													</div>
												</div>
											</div>

										</div>
										<br />
										<div class="row">
											<div class="col-md-12">
											
												<div class="col-md-6">
													<div class="col-md-3">
														<label class="pull-right"> <b style="font-size: 13px;">Strategic
																Pillar : &nbsp;</b></label>
													</div>
													<div class="col-md-3">
														<select multiple="multiple"  class="form-control" id="strategicPillarId"
															onchange="getThemesByStrategicReport(this);" style="width: 320%;">
															<option value="0" label="---All---"></option>
														</select>

													</div>
												</div>
											
												<div class="col-md-6">
													<div class="col-md-3">
														<label class="pull-right"><b style="margin-left: -18px;">Theme : </b></label>
													</div>

													<div class="col-md-3">
														<select multiple="multiple" class="form-control col-xs-3" name="reporting_id"
															id="themeId" onchange="getOutcomesByTheme(this);" style="width: 320%;">
															<option value="0" label="---All---"></option>
														</select>
													</div>

												</div>
											</div>
										</div>
										<br/>
										<div class="row">
											<div class="col-md-12">
												<div class="col-md-12">
													<div class="control-label col-md-2" style="left: -53px;">
														<label class=" pull-right"> <b>Outcome: &nbsp;</b>
														</label>
													</div>

													<div class="col-md-10" style="left: -47px;">
														<select multiple="multiple" class="form-control col-xs-11" name="reporting_id"
															id="outcomeId" onchange="getOutputsByoutcome(this);" style="width: 104%;">
															<option value="0" label="---All---"></option>
														</select>
													</div>

												</div>
											</div>

										</div>
										
										<br/>
										<div class="row">
											<div class="col-md-12">
												<div class="col-md-12">
													<div class="control-label col-md-2" style="left: -53px;">
														<label class=" pull-right"> <b>Output: &nbsp;</b>
														</label>
													</div>

													<div class="col-md-10" style="left: -47px;">
														<select multiple="multiple" class="form-control col-xs-3" name="reporting_id"
															id="outputId" onchange="getPartnerAgencyByoutput(this);" style="width: 104%;">
															<option value="0" label="---All---"></option>
														</select>
													</div>

												</div>
											</div>

										</div>
										<br/>
											<div class="row">
												<div class="col-md-12">
													<div class="col-md-12">
														<div class="control-label col-md-2" style="left: -53px;">
															<label class=" pull-right"> <b>Partner
																	Entity: &nbsp;</b>
															</label>
														</div>
														<div class="checkbox checkbox-success checkbox-inline" style="left:-50px;">
																	<input type="checkbox" class="checkbox checkbox-success checkbox-inline styled yearId" name="required" id="required"value="-1" onclick="changePartnerEntityMode(this);" ><label for="required">&nbsp;&nbsp;Not Include</label>
															</div>
														<div class="col-md-10" style="left: -47px;">
															
															<!-- <div>
																<input type="checkbox" name="required" id="required"
																	value="-1" onclick="changePartnerEntityMode(this);">&nbsp;Not
																Include
															</div> -->

															<select  multiple="multiple"
																class="form-control col-xs-3" name="response_id"
																id="partnerAgencyId" style="width: 240px;">
																<option value="0" label="---All---"></option>
																<!-- <option value="-1">Not Include</option> -->
															</select>
														</div>

													</div>
												</div>

											</div>
											<%-- <div class="row">
											<div class="col-md-12">
												<div class="col-md-6">
													<div class="control-label col-md-3">
														<label class=" pull-right"> <b>Required: &nbsp;</b>
														</label>
													</div>

													<div class="col-md-3">
														<input type="checkbox" name="required" id="required" value="-1" onclick="changePartnerEntityMode(this);">&nbsp;Not Include
													</div>

												</div>
												
												
												<div class="col-md-6">
													<div class="col-md-3">
														<label class=" pull-right"> <b>Partner Entity: &nbsp;</b>
														</label>
													</div>
													<div class="col-md-3">
														<form:select path="status" multiple="multiple" class="form-control col-xs-3" name="response_id"
															id="partnerAgencyId" style="width: 240px;">
															<option value="0" label="---All---"></option>
															<!-- <option value="-1">Not Include</option> -->
														</form:select>

													</div>
												</div>
											</div>

										</div> --%>
										<br />
										<div class="row">
										<div align="center">
										<br />
											<button class="btn btn-primary" onclick="statusReviewerActualReportDownload();" >Download</button>
										</div>
										</div>
									</div>
									<div>
										<div class="container" id = "reportTable">
											<div class="row">
												<div class="col-sm-12 col-md-12">
													<table id="actualReport-table" class="display table table-hover table-bordered CSSTableGenerator" width="100%"></table>
												</div>
											</div>
										</div>
									</div>
								</div>
								<%-- <div class="main-box-body clearfix">
									<br />
									<h1 class="pull-left">
										<span>Activity Status1</span>
									</h1>
									<br/>
									<br/>
									<!-- <div align="right">
										<a href="#" onclick="statusReviewerActualReportDownload();">
											<img src="../static/img/xls.png" class="xlsalign" title="Generate XLS" style="width: 39px;height: 38px;margin-bottom: -34px;">
										</a>
									</div> -->
									<sec:authorize access="hasRole('SUPER_ADMIN') || hasRole('PROJECT_ADMIN') || hasRole('PROJECT_PLANNER') || hasRole('STATUS_APPROVER')">
										<div align="left" style="margin-right: 47px;">
											<button class="btn btn-primary pull-right" onclick="actualActivityLeadAgencyReport()">Filter</button>									
										</div>
									</sec:authorize>
									<sec:authorize access="hasRole('STATUS_UPDATER') || hasRole('STATUS_REVIEWER')">
										<div align="left" style="margin-right: 47px;">
											<button class="btn btn-primary pull-right" onclick="actualActivityPartnerAgencyReport()">Filter</button>									
										</div>
									</sec:authorize>
									<hr>
									<div class="form-inline form-group rcorners1">
										<div class="row">
											<div class="col-md-12">
												<div class="col-xs-6">
													<div class="control-label col-xs-5">
														<form:label path="reportingperiodId" class=" pull-left"> <b>Reporting Period
																: &nbsp;</b>
														</form:label>
													</div>

													<div class="col-md-4">
														<form:select path="reportingperiodId" id="yearId" multiple="multiple" class="form-control"
															onclick="loadReportPillars(this);" style="width: 240px;">
															<option value="0" label="---All---"></option>
															<c:forEach items="${reportingPeriods}"
																var="reportingperiod">
																<option value="${reportingperiod.id}"
																	label="${reportingperiod.year} - ${reportingperiod.name}"></option>
															</c:forEach>
														</form:select>
													</div>

												</div>

												<div class="col-xs-6">
													<div class="col-md-5">
														<form:label path="strategicPillar" class="labelColor pull-left"> <b>Strategic
																Pillar : &nbsp;</b></form:label>
													</div>
													<div class="col-md-3">
														<form:select path="strategicPillar" multiple="multiple" class="form-control" id="strategicPillarId"
															
															onclick="getThemesByStrategicReport(this);" style="width: 240px;">
															<option value="0" label="---All---"></option>
															<c:forEach items="${strategicPillarList}"
																var="strategicPillar">
																<option value="${strategicPillar.id}"
																	label="${strategicPillar.name}"></option>
															</c:forEach>
														</form:select>

													</div>
												</div>
											</div>

										</div>
										<br />
										<div class="row">
											<div class="col-md-12">
												<div class="col-xs-6">
													<div class="control-label col-xs-5">
														<form:label path="theme" class="pull-left"><b>Theme : </b></form:label>
													</div>

													<div class="col-xs-6">
														<form:select path="theme" multiple="multiple" class="form-control col-xs-3" name="reporting_id"
															id="themeId" onclick="getOutcomesByTheme(this);" style="width: 240px;">
															<option value="0" label="---All---"></option>
														</form:select>
													</div>

												</div>


												<div class="form-group col-xs-6">
													<div class="col-md-5">
														<form:label path="outcome" class="pull-left"><b>Outcome : </b></form:label>
													</div>
													<div class="col-md-3">
														<form:select path="outcome" multiple="multiple" class="form-control col-xs-3" name="reporting_id"
															id="outcomeId" onclick="getOutputsByoutcome(this);"
															style="width: 240px;">
															<option value="0" label="---All---"></option>
														</form:select>
													</div>
												</div>
											</div>
										</div>
										<br />
										<div class="row">
											<div class="col-md-12">
												<div class="col-xs-6">
													<div class="control-label col-xs-5">
														<form:label path="output" class="pull-left"><b>Output : </b></form:label>
													</div>
													<div class="col-xs-6">
														<form:select path="output" multiple="multiple" class="form-control col-xs-3" name="reporting_id"
															id="outputId" 
															onclick="getPartnerAgencyByoutput(this);" style="width: 240px;">
															<option value="0" label="---All---"></option>
														</form:select>
													</div>
												</div>
												<div class="form-group col-xs-6">
													<div class="col-md-5">
														<form:label path="status" class="pull-left"><b>Partner
																Entity : </b></form:label>
													</div>
													<div class="col-md-3">
														<form:select path="status" multiple="multiple" class="form-control col-xs-3" name="response_id"
															id="partnerAgencyId" style="width: 240px;">
															<option value="0" label="---All---"></option>
															<!-- <option value="-1">Not Include</option> -->
														</form:select>
													</div>
												</div>
												<br />
												<br />
												<br />
												<div class="row">
												<div align="center">
												<br />
												<br />
													<button class="btn btn-primary" onclick="statusReviewerActualReportDownload();" >Download</button>
												</div>
												</div>
											</div>
										</div>
									</div>
									<div>
										<div class="container" id = "reportTable">
											<div class="row">
												<div class="col-sm-12 col-md-12">
													<table id="actualReport-table" class="display table table-hover table-bordered CSSTableGenerator" width="100%"></table>
												</div>
											</div>
										</div>
									</div>
								</div> --%>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script src="../static/js/demo-skin-changer.js"></script>
	<script src="../static/js/bootstrap.js"></script>
	<script src="../static/js/jquery.nanoscroller.min.js"></script>
	<script src="../static/js/demo.js"></script>

	<script src="../static/js/jquery.dataTables.js"></script>
	<script src="../static/js/dataTables.tableTools.js"></script>
	<script src="../static/js/jquery.dataTables.bootstrap.js"></script>


	<script src="../static/js/scripts.js"></script>
	<script src="../static/js/pace.min.js"></script>

	<!-- Js validation file with advanced data Table  -->

	<script type="text/javascript" src="../static/scripts/login.js"></script>

</body>
</html>