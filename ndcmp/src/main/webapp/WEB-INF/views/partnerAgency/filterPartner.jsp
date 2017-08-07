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
<link rel="stylesheet" type="text/css"
	href='<c:url value="/static/ui/jquery-ui.css"></c:url>'>
<link
	href="<c:url value="/static/csslib/jquery.colorpickersliders.css" />"
	rel="stylesheet" type="text/css">

<script type="text/javascript"
	src="<c:url value="/static/jslib/tinycolor.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/static/ui/jquery-ui.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/static/jslib/jquery.colorpickersliders.js" />"></script>
<script type="text/javascript"
	src='<c:url value="/static/scripts/reviewer.js"></c:url>'></script>
<script type="text/javascript" src="../static/scripts/filter.js"></script>
<script type="text/javascript" src="../static/scripts/menu.js"></script>
<%-- <script type="text/javascript" src='<c:url value="/static/scripts/role.js"></c:url>'></script> --%>
<script type="text/javascript"
	src='<c:url value="/static/scripts/status.js"></c:url>'></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#tabs > li').removeClass('active');
		$('#partnerTab').attr('class', 'active');
		$('.collapse').collapse();
		$('.hideHint').hide();
		$('.showHint').css('cursor', 'pointer');
		$('.hideHint').css('cursor', 'pointer');
		$('.accordion a').on('click', function() {
			var _this = $(this);
			var btnId = "";
			var str = $(this).context.id.split('_');
			if (str[0] == "strategic") {
				btnId = "#strategic-1"
			} else {
				btnId = "#" + str[0] + '-' + str[1];
			}
			var _expanded = $(_this).attr('aria-expanded');
			if (_expanded == 'false') {
				//$('.btn .edit .delete').show();
				$(btnId).hide();
			} else if (_expanded == 'true') {
				//$('.btn .edit .delete').hide();
				$(btnId).show();
			}
		});
		var selectedReportingPeriod = document.getElementById("yearId").selectedIndex = "1";
		loadStatusForStatusUpdator(selectedReportingPeriod);
	});
</script>
<script type="text/javascript">
	$('.accordion a').on('click', function() {
		alert($(this).attr("div.id"));
	});
</script>
<style>
.panel-default>.panel-heading {
	/* background-color: #03a9f4;
	border-color: #03a9f4; */
	background-color: #2C3E50;
	border-color: #2C3E50 color:       #FFFFFF;
	border-radius: 0;
	background-clip: padding-box;
}

.panel-default>.panel-heading a:focus {
	color: #fff;
}

.panel-group .panel {
	border-radius: 0;
	background-clip: padding-box;
	border: 0 none;
}

.panel-collapse {
	border: 2px solid #e1e1e1;
	border-top: 0;
}

.accordion .panel-title>a {
	display: block;
	position: relative;
	outline: none;
	text-decoration: none;
	color: #FFFFFF;
	padding-right: 15px;
}

.accordion .panel-title>a:hover {
	text-decoration: none;
	color: #FFFFFF;
}

.accordion .panel-title>a:after {
	content: "\2212";
	font-family: FontAwesome;
	font-style: normal;
	font-weight: normal;
	text-decoration: inherit;
	margin-top: -5px;
	font-size: 0.75em;
	position: absolute;
	right: 0;
	top: 50%;
	font-family: FontAwesome;
}

.accordion .panel-title>a.accordion-toggle.collapsed:after {
	content: "\2b"
}

.accordion .panel-title>a.accordion-toggle>i {
	width: 24px;
	text-align: center;
	padding-right: 6px;
}

h2 {
	margin-bottom: 0;
}

h3 {
	margin: 0 0 30px;
}

.ui-slider-range {
	background: green;
}

.percent {
	color: green;
	font-weight: bold;
	text-align: center;
	width: 100%;
	border: none;
}
</style>

</head>


<body>

	<div id="theme-wrapper">
		<%@include file="../include/menu.jsp"%>
		<div id="page-wrapper" class="container">
			<div class="row">
				<%@include file="../include/menuItems.jsp"%>
				<div id="content-wrapper">
					<div class="row">
						<div class="col-lg-12">
							<div class="main-box clearfix">
								<div class="main-box-body clearfix">
									<br />

									<h1 class="pull-left">Update Reporting Status</h1>
									<button class="btn btn-primary pull-right" id="reset"
										onclick="resetFuncion()" title="Show Filter" style="display: none;">Show Filter</button>
									&nbsp;&nbsp;
									<button class="btn btn-primary pull-right" title="Filter"
										onclick="loadKeyActivity()" id="getFilterHierarchy">Filter</button>
									<br /> <br />
									<div id="partnerDiv">
										<form:form id="filteringForm" cssClass="form-horizontal"
											modelAttribute="filterHierarchy" method="get">
											<input type="hidden" id="roleId"
												value="<%=session.getAttribute("roleId")%>" />
											<div class="container-fluid">
												<div class="col-lg-12">
													<div class="form-group">
														<div class="control-label col-xs-3">
															<form:label path="year" cssClass="labelColor">
																<b>Reporting Period : &nbsp;</b>
																<span style="color: red">*</span>
															</form:label>
														</div>
														<div class="col-xs-3">
															<form:select id="yearId" path="year"
																cssClass="form-control" onchange="loadStatusForStatusUpdator();">
																<form:option value=""
																	label="---Select Reporting Period---"></form:option>
																<c:forEach items="${reportingPeriods}"
																	var="reportingPeriod">
																	<form:option value="${reportingPeriod.id}"
																		label="${reportingPeriod.year} - ${reportingPeriod.name}"></form:option>
																</c:forEach>
															</form:select>
														</div>
														<div class="control-label col-xs-2">
															<form:label path="status" cssClass="labelColor">
																<b>Status : &nbsp;</b>
																<span style="color: red">*</span>
															</form:label>
														</div>
														<div class="col-xs-3">
															<form:select id="statusId" path="status" cssClass="form-control" onchange="loadPillars();">
																<form:option value="" label="---Select Status---"></form:option>
															</form:select>
														</div>
													</div>
												</div>
											</div>


											<br />
											<div class="container-fluid">
												<div class="col-lg-12">
													<div class="form-group">
														<div class="control-label col-xs-3">
															<form:label path="strategicPillar" cssClass="labelColor">
																<b>Strategic Pillar : &nbsp;</b>
																<span style="color: red">*</span>
															</form:label>
														</div>
														<div class="col-xs-3">
															<form:select id="strategicPillarId"
																path="strategicPillar" cssClass="form-control"
																onchange="getThemesByStrategicStatusReport(this.value);">
																<form:option value=""
																	label="---Select Strategic Pillar---"></form:option>
															</form:select>
														</div>
														<div class="control-label col-xs-2">
															<form:label path="theme" cssClass="labelColor">
																<b>Theme : &nbsp;</b>
																<span style="color: red">*</span>
															</form:label>
														</div>
														<div class="col-xs-3">
															<form:select id="themeId" path="theme"
																cssClass="form-control"
																onchange="getStatusUpdatorOutcomesByTheme(this.value);">
																<form:option value="" label="---Select Theme---"></form:option>
															</form:select>
														</div>
													</div>
												</div>
											</div>
											<br />
											<div class="container-fluid">
												<div class="col-lg-12">
													<div class="form-group">
														<div class="control-label col-xs-3">
															<form:label path="outcome" cssClass="labelColor">
																<b>Outcome : &nbsp;</b>
																<span style="color: red">*</span>
															</form:label>
														</div>
														<div class="col-xs-3">
															<form:select id="outcomeId" path="outcome"
																cssClass="form-control"
																onchange="getStatusUpdatorOutputsByoutcome(this.value);">
																<form:option value="" label="---Select Outcome---"></form:option>
															</form:select>
														</div>
														<div class="control-label col-xs-2">
															<form:label path="output" cssClass="labelColor">
																<b>Output : &nbsp;</b>
																<span style="color: red">*</span>
															</form:label>
														</div>
														<div class="col-xs-3">
															<form:select id="outputId" path="output"
																cssClass="form-control"
																onchange="getResponsibleEntityByOutput(this);">
																<form:option value="" label="---Select Output---"></form:option>
															</form:select>
														</div>
													</div>
												</div>
											</div>
											<br />
										</form:form>
									</div>
								</div>
								<div id="queues-accordion" class="col-md-12"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

	<div id="checkSubmitModal" class="modal fade bd-example-modal-lg"
		role="dialog">
		<div class="modal-dialog modal-lg">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
						<b>Status Updater</b>
					</h4>
					<hr>
					<div id="msg"></div>
					<hr>
					<div style="height: 25px;">
						<button class="btn btn-primary pull-right"
							onclick="proceedSubmitForReview()" id="submitForReview">Yes</button>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div id="proceedCheckSubmitModal"
		class="modal fade bd-example-modal-lg" role="dialog">
		<div class="modal-dialog modal-lg">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<span style="color: red;"><h4 class="modal-title">Submit
							For Review</h4></span>
					<hr>
					<div class="modal-body" id="targetActive">
						<form:form action="createSubmitForReview"
							id="submitForReviewAddform" cssClass="form-horizontal"
							modelAttribute="submitForReview" method="post">

							<div class="form-group">
								<div class="control-label col-xs-3">
									<form:label path="keyLearning" cssClass="labelColor">Key Learning : &nbsp;
											</form:label>
								</div>
								<div class="col-xs-6">
									<form:textarea cssClass="form-control" path="keyLearning"
										value="${submitForReview.keyLearning}" />
								</div>
								<div>
									<span style="color: #D00B05">${errorDescription}</span>
								</div>
							</div>


							<div class="form-group">
								<div class="control-label col-xs-3">
									<form:label path="keyChallenge" cssClass="labelColor">Key Challenge : &nbsp;
											</form:label>
								</div>
								<div class="col-xs-6">
									<form:textarea cssClass="form-control" path="keyChallenge"
										value="${submitForReview.keyChallenge}" />
								</div>
								<div>
									<span style="color: #D00B05">${errorDescription}</span>
								</div>
							</div>

							<div class="form-group">
								<div class="control-label col-xs-3">
									<form:label path="bestPractice" cssClass="labelColor">Best Practice : &nbsp;
											</form:label>
								</div>
								<div class="col-xs-6">
									<form:textarea cssClass="form-control" path="bestPractice"
										value="${submitForReview.bestPractice}" />
								</div>
								<div>
									<span style="color: #D00B05">${errorDescription}</span>
								</div>
							</div>

							<div class="form-group">
								<div class="control-label col-xs-3">
									<form:label path="supportNeeds" cssClass="labelColor">Support Needs : &nbsp;
											</form:label>
								</div>
								<div class="col-xs-6">
									<form:textarea cssClass="form-control" path="supportNeeds"
										value="${submitForReview.supportNeeds}" />
								</div>
								<div>
									<span style="color: #D00B05">${errorDescription}</span>
								</div>
							</div>

							<br />
							<div class="form-group">
								<div class="row">
									<div class="col-xs-4"></div>
									<div class="col-xs-4">
										<input type="submit" id="saveSubmitForReview"
											class="btn btn-primary" value="Save" />
										<!-- <input type="button" id="cancel" value="Cancel" class="btn btn-primary" /> -->
										<a href="partnerFilter" class="btn btn-default">Cancel</a>
									</div>
									<div class="col-xs-4"></div>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div id="statusModal" class="modal fade bd-example-modal-lg"
		role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content" style="width: 975px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>

					<h4 class="modal-title" id="subName" style="color: #212121;"></h4>
				</div>
				<div class="modal-body" id="targetActive">
					<form:form action="createStatusTracking" id="statusTrackingform"
						cssClass="form-horizontal" modelAttribute="statusTracking"
						method="post">
						<input type="hidden" id="roleId"
							value="<%=session.getAttribute("roleId")%>" />
						<br />
						
						<div class="form-group" id="carriedFromPreviousRPDiv" style="display: none;">
							<div class="control-label col-xs-4">
								<label class="labelColor"><b>Carried From : &nbsp;</b></label>
							</div>
							<div class="col-xs-7">
								<label class="control-label labelColor" style="text-align: left;" id="carriedFromPreviousRP"></label>
							</div>
						</div>
						
						<div class="form-group">
							<form:hidden path="id" id="id" />
							<div class="control-label col-xs-4">
								<div></div>
								<label cssClass="labelColor" style="padding-top: 15px;"><b>Status
										Color : &nbsp;</b> </label>
							</div>

							<div class="project col-md-7">
								<h2 class="text-center" style="margin-top: -19px">
									<form:hidden path="userId" id="userId" />
									<form:hidden path="subActivityId" id="subActivityId" />
									<form:hidden path="actualStatusColor" id="percent" />
									<form:input path="actualStatusPercentage"
										class="percent project" readonly="true" id="colorPercent" />
									<!-- <label id="colorPer"></label> -->
								</h2>
								<div class="bar" style="margin-top: -1px"></div>

							</div>
							<div style="margin: 21px -4px -1px 2px;">
								<span class="showHint"><span style="padding-top: 7px;"
									class="glyphicon glyphicon-info-sign" title="Show Colors"
									data-original-title="Show Colors"></span></span> <span
									class="hideHint"><span style="padding-top: 7px;"
									class="glyphicon glyphicon-info-sign" title="Hide Colors"
									data-original-title="Show Colors"></span></span>
								<!-- <label id="hint" class="checkbox-inline"> <input type="checkbox" value="" class="showHint">Show Hint</label> -->
							</div>
						</div>
						<br>
						<div class="form-group" style="margin: -17px 69px 36px 318px;"
							id="showColorHint">
							<div class="col-xs-1"
								style="width: 53px; height: 19px; background: #DDA6A1;">
								<label
									style="padding-top: 31px; font-size: smaller; width: -223px;">01-10</label>
							</div>
							<div class="col-xs-1"
								style="width: 55px; height: 19px; background: #E77B70;">
								<label
									style="padding-top: 31px; font-size: smaller; width: -223px;">11-20</label>
							</div>
							<div class="col-xs-1"
								style="width: 53px; height: 19px; background: #F14D41;">
								<label
									style="padding-top: 31px; font-size: smaller; width: -223px;">21-30</label>
							</div>
							<div class="col-xs-1"
								style="width: 55px; height: 19px; background: #F66A37;">
								<label
									style="padding-top: 31px; font-size: smaller; width: -223px;">31-40</label>
							</div>
							<div class="col-xs-1"
								style="width: 55px; height: 19px; background: #F89A3A;">
								<label
									style="padding-top: 31px; font-size: smaller; width: -223px;">41-50</label>
							</div>
							<div class="col-xs-1"
								style="width: 55px; height: 19px; background: #FECB3A;">
								<label
									style="padding-top: 31px; font-size: smaller; width: -223px;">51-60</label>
							</div>
							<div class="col-xs-1"
								style="width: 55px; height: 19px; background: #F3E83B;">
								<label
									style="padding-top: 31px; font-size: smaller; width: -223px;">61-70</label>
							</div>
							<div class="col-xs-1"
								style="width: 55px; height: 19px; background: #CDDA42;">
								<label
									style="padding-top: 31px; font-size: smaller; width: -223px;">71-80</label>
							</div>
							<div class="col-xs-1"
								style="width: 53px; height: 19px; background: #ABD045;">
								<label
									style="padding-top: 31px; font-size: smaller; width: -223px;">81-90</label>
							</div>
							<div class="col-xs-1"
								style="width: 63px; height: 19px; background: #9FCA47;">
								<label
									style="padding-top: 31px; font-size: smaller; width: -223px;">91-100</label>
							</div>
						</div>
						<div class="form-group">
							<div class="control-label col-xs-4">
								<form:label path="keyProgress" cssClass="labelColor"><b>Key Progress in the quarter : &nbsp;</b>
								</form:label>
							</div>
							<div class="col-xs-7">
								<form:textarea cssClass="form-control" path="keyProgress"
									id="keyProgress" />
							</div>
							<div>
								<span style="color: #D00B05">${errorDescription}</span>
							</div>
						</div>


						<div class="form-group">
							<div class="control-label col-xs-4">
								<form:label path="reasonForGap" cssClass="labelColor">
									<b>Reason for gap if any : &nbsp;</b>
								</form:label>
							</div>
							<div class="col-xs-7">
								<form:textarea cssClass="form-control" path="reasonForGap"
									id="reasonForGap" />
							</div>
							<div>
								<span style="color: #D00B05">${errorDescription}</span>
							</div>
						</div>

						<div class="form-group">
							<div class="control-label col-xs-4">
								<form:label path="rectifyTheGap" cssClass="labelColor">
									<b>Plan of action to rectify the gap : &nbsp;</b>
								</form:label>
							</div>
							<div class="col-xs-7">
								<form:textarea cssClass="form-control" path="rectifyTheGap"
									id="rectifyTheGap" />
							</div>
							<div>
								<span style="color: #D00B05">${errorDescription}</span>
							</div>
						</div>
						
						<div class="form-group" id="lastUpdatedBy" style="display: none;">
							<div class="control-label col-xs-4">
								<label class="labelColor"><b>Last Updated By : &nbsp;</b></label>
							</div>
							<div class="col-xs-7">
								<label class="control-label labelColor" style="text-align: left;" id="partnerName"></label>
							</div>
						</div>
						
						<div class="form-group" id="reviewedComment" style="display: none;">
							<div class="control-label col-xs-4">
								<label class="labelColor"><b> Reviewer Feedback : &nbsp;</b>
								</label>
							</div>
							<div class="col-xs-7">
								<label class="control-label labelColor" style="color:#e84e40;font-weight: bold;text-align: left;" id="reviewerFeedback"></label>
							</div>
							<div>
								<span style="color: #D00B05">${errorDescription}</span>
							</div>
							
						</div>
						<div class="form-group" style="display: none;">
							<div class="control-label col-xs-4">
								<form:label path="rectifyTheGap" cssClass="labelColor">
									<b>Ready For Review : &nbsp;</b>
								</form:label>
							</div>
							<div class="col-xs-7" style="margin-top: 8px;">
								<form:checkbox path="complete" value="1" id="complete" />
							</div>
						</div>

					</form:form>

				</div>
				<div class="modal-footer">
					<div class="col-xs-11">
						<button type="button" class="btn btn-primary"
							id="readyforreview-statusTracking" onclick="saveAndReadyForReview()">Ready for Review</button>
						<button type="button" class="btn btn-primary"
							id="save-statusTracking" onclick="saveStatus()">Save</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					</div>
				</div>
			</div>

		</div>
	</div>
	
	<!-- validation -->
		<div id="statusUpdaterFilerValidationErrorModelWindow" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
						<b id="observationTitle">Validation</b>
					</h4>
				</div>
				<div class="modal-body">
					<div class="alert alert-danger" id="reportingValidationErrorMsg" style="display: none">
						<span><b style="color: red;">Reporting Period is Required !</b></span>
					</div>
					<div class="alert alert-danger" id="statusValidationErrorMsg" style="display: none">
						<span><b style="color: red;">Status is Required !</b></span>
					</div>
					<div class="alert alert-danger" id="strategicValidationErrorMsg" style="display: none">
						<span><b style="color: red;">Strategic Pillar is Required !</b></span>
					</div>
					<div class="alert alert-danger" id="themeValidationErrorMsg" style="display: none">
						<span><b style="color: red;">Theme is Required !</b></span>
					</div>
					<div class="alert alert-danger" id="outcomeValidationErrorMsg" style="display: none">
						<span><b style="color: red;">Outcome is Required !</b></span>
					</div>
					<div class="alert alert-danger" id="outputValidationErrorMsg" style="display: none">
						<span><b style="color: red;">Output is Required !</b></span>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>

	<script src="../static/js/demo-skin-changer.js"></script>
	<!-- <script src="../static/js/jquery.js"></script> -->
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