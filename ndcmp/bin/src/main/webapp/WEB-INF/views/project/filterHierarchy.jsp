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
<link
	href='<c:url value="/static/csslib/jquery.dataTables.min.css"></c:url>'>
<link
	href='<c:url value="/static/csslib/dataTables.colVis.css"></c:url>'>
<script type="text/javascript" src="../static/scripts/filter.js"></script>
<script type="text/javascript" src="../static/scripts/menu.js"></script>
<%-- <script type="text/javascript" src='<c:url value="/static/scripts/role.js"></c:url>'></script> --%>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var accessView = '${accessRightsView}';
						var currentDate = new Date();
						var currentYear = currentDate.getFullYear();
						localStorage.setItem("currentYear", currentYear);
						if (accessView == 'true') {
							$('#tabs > li').removeClass('active');
							$('#viewTab').attr('class', 'active');
						} else {
							$('#tabs > li').removeClass('active');
							$('#planTab').attr('class', 'active');
						}
						var selectedReportingPeriod = document
								.getElementById("strategicPillarId").selectedIndex = "1";
						var repor = {
							value : selectedReportingPeriod
						}
						getThemesByStrategic(repor);
						/* $('#configurationTab').attr('class', 'active');
						$('#roleTab').attr('class', 'active'); */

						/*   var _tooltip = '';
						   	_tooltip += '<label type="button" class="btn btn-danger disabled"></label><label>&nbsp;Expired Events</label><br>';
						   	_tooltip += '<label type="button" class="btn btn-info disabled"></label><label>&nbsp;Unpublished Events</label><br>';
						   	_tooltip += '<label type="button" class="btn btn-primary disabled"></label><label>&nbsp;Published&Not Booked Events</label><br>';
						   	_tooltip += '<label type="button" class="btn btn-success disabled" style="color:#FFF;"></label><label>&nbsp;Published&Booked Events</label><br>';
						   	_tooltip += '';
						   	
						   	// Initialize tool tip
						   	$('#legend').tooltip({
						   		html: true,
						   		title: _tooltip
						   	});	
						 */
					});
</script>
</head>


<body>

	<div id="theme-wrapper">
		<%@include file="../include/menu.jsp"%>
		<div id="page-wrapper" class="container">
			<div class="row">
				<%@include file="../include/menuItems.jsp"%>
				<div id="content-wrapper">
					<div class="row">
						<div class="main-box clearfix">
							<div class="main-box-body clearfix">
								<br />

								<h1 class="pull-left">Plan Project</h1>
								<div class="pull-right">
									<button class="btn btn-primary " id="reset-btn"
										onclick="resetFilterDiv()" style="display: none;"
										title="Show Filter">Show Filter</button>
									<button class="btn btn-primary " id="filter-btn"
										onclick="loadFilterData()" title="Filter">Filter</button>
									<div class="btn-group" id="myDropdown" style="display: none;">
										<a class="btn btn-primary dropdown-toggle"
											data-toggle="dropdown" href="#" title="Show-Hide Column ">
											Show-Hide Column <span class="caret" style="margin-top: -1px"></span>
										</a>
										<ul class="dropdown-menu">
											<li><a class="toggle-vis" data-columnindex="3"
												style="cursor: pointer;"><span id="a3"
													style="display: none"
													class="glyphicon glyphicon-ok text-success"> </span>&nbsp;&nbsp;Responsible
													Entity</a></li>
											<li><a class="toggle-vis" data-columnindex="4"
												style="cursor: pointer;"><span id="a4"
													style="display: none"
													class="glyphicon glyphicon-ok text-success"> </span>&nbsp;&nbsp;Partnering
													Agency</a></li>
											<li><a class="toggle-vis" data-columnindex="5"
												style="cursor: pointer;"><span id="a5"
													style="display: none"
													class="glyphicon glyphicon-ok text-success"> </span>&nbsp;&nbsp;MOV</a></li>
										</ul>
									</div>
									<button class="btn btn-primary " id="save-plan"
										onclick="savePlan()" style="display: none;" title="Save">Save</button>
									<a style="cursor: pointer;" title="Download Template"
										data-toggle="modal" data-target="#templateModal"><img
										src='<c:url value="/static/img/download.png"></c:url>'
										style="width: 38px; height: 39px"></a>
										<a style="cursor: pointer;" title="Upload Template"
										data-toggle="modal" data-target="#uploadModal"><img
										src='<c:url value="/static/img/upload.png"></c:url>'
										style="width: 38px; height: 39px"></a>
									&nbsp; <br /> <br />
								</div>
								<br /> <br />
								<hr />
								<div id="filterDiv">
									<form:form id="filteringForm" cssClass="form-horizontal"
										modelAttribute="filterHierarchy">
										<div class="col-lg-12">
											<input type="hidden" id="roleId"
												value="<%=session.getAttribute("roleId")%>" />
											<div class="col-lg-4">
												<div class="form-group">
													<div class="control-label col-lg-6"
														style="text-align: left">
														<form:label path="strategicPillar" cssClass="labelColor">
															<b>Strategic Pillar :</b> &nbsp;<span style="color: red">*</span>
														</form:label>
													</div>
												</div>
											</div>
											<div class="col-lg-4">
												<div class="form-group">
													<div class="control-label col-lg-4"
														style="text-align: left">
														<form:label path="theme" cssClass="labelColor">
															<b>Theme :</b> &nbsp;<span style="color: red">*</span>
														</form:label>
													</div>
												</div>
											</div>
											<div class="col-lg-4">
												<div class="form-group">
													<div class="control-label col-xs-4">
														<form:label path="status" cssClass="labelColor pull-left">
															<b>Status :</b> &nbsp;<span style="color: red">*</span>
														</form:label>
													</div>

												</div>
											</div>

										</div>
										<div class="col-lg-12">

											<div class="col-lg-4" align="left">
												<div class="form-group">
													<div class="control-label col-lg-12">
														<form:select id="strategicPillarId" path="strategicPillar"
															cssClass="form-control text-left"
															onchange="getThemesByStrategic(this);">
															<form:option value=""
																label="---Select Strategic Pillar---"></form:option>
															<c:forEach items="${strategicPillarList}"
																var="strategicPillar">
																<form:option value="${strategicPillar.id}"
																	label="${strategicPillar.sequenceNumber}. ${strategicPillar.name}"></form:option>
															</c:forEach>
														</form:select>
													</div>
												</div>
											</div>
											<div class="col-lg-4">
												<div class="form-group">
													<div class="control-label col-lg-12">
														<form:select id="themeId" path="theme"
															cssClass="form-control"
															onchange="getOutcomesByTheme(this);">
															<form:option value="" label="---Select Theme---"></form:option>
														</form:select>
													</div>
												</div>
											</div>

											<div class="col-xs-4" style="margin-top: 5px;">
												<div class="radio radio-success">
													<input type="radio" name="status" class="statusId" id="All"
														value="All" checked="checked"> <label for="All">All</label>&nbsp;&nbsp;&nbsp;
													<input type="radio" name="status" class="statusId"
														id="planned" value="Planned"><label for="planned">Planned</label>&nbsp;&nbsp;&nbsp;
													<input type="radio" name="status" class="statusId"
														id="unPlanned" value="Unplanned"><label
														for="unPlanned">Unplanned</label>&nbsp;&nbsp;&nbsp; <input
														type="radio" name="status" class="statusId" id="completed"
														value="Completed"><label for="completed">Completed</label>
												</div>
											</div>

										</div>
										<div class="col-lg-12">
											<div class="col-lg-4">
												<div class="form-group">
													<div class="control-label col-xs-4"
														style="text-align: left">
														<form:label path="outcome" cssClass="labelColor">
															<b>Outcome :</b> &nbsp;<span style="color: red">*</span>
														</form:label>
													</div>
												</div>
											</div>
											<div class="col-lg-4">
												<div class="form-group">
													<div class="control-label col-xs-4"
														style="text-align: left">
														<form:label path="output" cssClass="labelColor">
															<b>Output :</b> &nbsp;<span style="color: red">*</span>
														</form:label>
													</div>
												</div>
											</div>
											<div class="col-lg-4">
												<div class="form-group">
													<div class="control-label col-xs-4">
														<form:label path="status" cssClass="labelColor pull-left">
															<b>Year :</b> &nbsp;<span style="color: red">*</span>
														</form:label>
													</div>

												</div>
											</div>
										</div>

										<div class="col-lg-12">

											<div class="col-lg-4">
												<div class="form-group">
													<div class="control-label col-lg-12">
														<form:select id="outcomeId" path="outcome"
															cssClass="form-control"
															onchange="getOutputsByoutcome(this);">
															<form:option value="" label="---Select Outcome---"></form:option>
														</form:select>
													</div>
												</div>
											</div>
											<div class="col-lg-4">
												<div class="form-group">
													<div class="control-label col-lg-12">
														<form:select id="outputId" path="output"
															cssClass="form-control">
															<form:option value="" label="---Select Output---"></form:option>
														</form:select>
													</div>
												</div>
											</div>
											<div class="col-xs-4" style="margin-top: 5px;">
												<c:forEach items="${years }" var="year">
													<c:choose>
														<c:when test="${currentYear eq year || nextYear eq year}">
															<%-- <input type="checkbox" name="year" class="yearId"
																id="reportingPeriodYearId" value="${year }"
																checked="checked">&nbsp${year }&nbsp;&nbsp;&nbsp; --%>

															<div class="checkbox checkbox-success checkbox-inline">
																<input type="checkbox"
																	class="checkbox checkbox-success checkbox-inline styled yearId"
																	name="year" id="reportingPeriodYearId" value="${year}"
																	checked><label></label>
															</div>
															<span style="position: relative; top: 4px;">
																${year }</span>
														</c:when>
														<c:otherwise>
															<%-- <input type="checkbox" name="year" class="yearId"
																id="reportingPeriodYearId" value="${year }">&nbsp${year }&nbsp;&nbsp;&nbsp;
																 --%>
															<div class="checkbox checkbox-success checkbox-inline">
																<input type="checkbox"
																	class="checkbox checkbox-success checkbox-inline styled yearId"
																	name="year" id="reportingPeriodYearId" value="${year}"><label></label>
															</div>
															<span style="position: relative; top: 4px;">
																${year }</span>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</div>
										</div>
									</form:form>
								</div>

								<div class="table-responsive dataTables-wrapper">
									<br />
									<div id="reworkJobLevelStatus"
										style="text-align: center; vertical-align: middle; display: none;">
										<img style="height: 250px;"
											src="<c:url value="/static/img/dashboard_loading.gif" />">
									</div>
									<%-- 	<div style="display: none;" id="legend_tooltip">
										<a class="legend-tooltip1" onmouseover="setTooltipAlignment()"> <img alt="Legend" id="legend"
											width="28" data-toggle="tooltip" data-placement="bottom"
											src="${pageContext.request.contextPath }/static/img/legend-icon.png"
											style="float: right; padding-right: 5px; margin-top: 10px;">
										</a>
									</div> --%>
									<table
										class="display table table-hover table-bordered CSSTableGenerator"
										id="filterTable">

									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="templateModal" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Download Template</h4>
				</div>
				<div class="modal-body" >
					<div id="validationMsg">
						
					</div>
					<div class="col-lg-12">
						<div class="form-group">
							<div class="control-label col-lg-12">
								<div class="control-label col-md-2">
									<label class=" pull-right"> <b>Strategic Pillar :
											&nbsp;</b>
									</label>
								</div>
								<div class="col-md-5">
									<select id="strPillarId" name="strategicPillar" multiple
										class="form-control text-left">
										<option value="0" selected>---All---</option>
										<c:forEach items="${strategicPillarList}"
											var="strategicPillar">
											<option value="${strategicPillar.id}">${strategicPillar.sequenceNumber}.
												${strategicPillar.name}</option>
										</c:forEach>
									</select>
								</div>
								<div class="control-label col-md-2">
									<label class=" pull-right"> <b>Reporting Period :
											&nbsp;</b>
									</label>
								</div>
								<div class="col-md-3">
									<select multiple id="repPeriodId" class="form-control">
										<option selected="selected" value="0">---All---</option>
										<c:forEach items="${reportingPeriodList}"
											var="reportingperiod">
											<option value="${reportingperiod.id}">${reportingperiod.year}
												- ${reportingperiod.name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
					<br /><br /><br /><br />
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary"
						onclick="downloadPlanningTemplate()">Download</button>
				</div>
			</div>

		</div>
	</div>



	<div id="uploadModal" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			
			<div class="modal-content">
			<form cssClass="form-horizontal" >
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Upload Template</h4>
				</div>
				<div class="modal-body" >
					<div id="validationMsg">
						
					</div>
					
					
					<label style="color: red;" class="pull-left">Choose File :</label>
					<input id="file" type="file" name="uploadPlanning" accept=".xls">
					
					<%-- <div class="col-lg-12">
						<div class="form-group">
							<div class="control-label col-lg-12">
								<div class="control-label col-md-2">
									<label class=" pull-right"> <b>Strategic Pillar :
											&nbsp;</b>
									</label>
								</div>
								<div class="col-md-5">
									<select id="strPillarId" name="strategicPillar" multiple
										class="form-control text-left">
										<option value="0" selected>---All---</option>
										<c:forEach items="${strategicPillarList}"
											var="strategicPillar">
											<option value="${strategicPillar.id}">${strategicPillar.sequenceNumber}.
												${strategicPillar.name}</option>
										</c:forEach>
									</select>
								</div>
								<div class="control-label col-md-2">
									<label class=" pull-right"> <b>Reporting Period :
											&nbsp;</b>
									</label>
								</div>
								<div class="col-md-3">
									<select multiple id="repPeriodId" class="form-control">
										<option selected="selected" value="0">---All---</option>
										<c:forEach items="${reportingPeriodList}"
											var="reportingperiod">
											<option value="${reportingperiod.id}">${reportingperiod.year}
												- ${reportingperiod.name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div> --%>
					<br /><br /><br /><br />
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<!-- <button type="button" class="btn btn-primary"
						onclick="downloadPlanningTemplate()">Download</button> -->
						<input class="btn btn-primary" onclick="submitfile()" type="submit" value="Submit">
				</div>
				</form>
			</div>

		</div>
	</div>



	<!-- BackToTop Button -->
	<a href="javascript:void(0);" id="scroll" title="Scroll to Top"
		style="display: none;">Top<span></span></a>

	<script src="../static/js/demo-skin-changer.js"></script>
	<!-- <script src="../static/js/jquery.js"></script> -->
	<script src="../static/js/bootstrap.js"></script>
	<script src="../static/js/jquery.nanoscroller.min.js"></script>
	<script src="../static/js/demo.js"></script>

	<script src='<c:url value="/static/js/jquery.dataTables.js"></c:url>'></script>
	<script src="../static/js/dataTables.tableTools.js"></script>
	<script src="../static/js/jquery.dataTables.bootstrap.js"></script>


	<script src="../static/js/scripts.js"></script>
	<script src="../static/js/pace.min.js"></script>

	<!-- Js validation file with advanced data Table  -->

	<script type="text/javascript" src="../static/scripts/login.js"></script>
</body>
</html>