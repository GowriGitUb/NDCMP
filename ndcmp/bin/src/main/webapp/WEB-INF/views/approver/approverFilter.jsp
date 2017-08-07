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
	src='<c:url value="/static/scripts/filter.js"></c:url>'></script>
<script type="text/javascript"
	src='<c:url value="/static/scripts/menu.js"></c:url>'></script>
<script type="text/javascript"
	src='<c:url value="/static/scripts/approver.js"></c:url>'></script>
<script type="text/javascript"
	src='<c:url value="/static/scripts/status.js"></c:url>'></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#tabs > li').removeClass('active');
		$('#approveTab').attr('class', 'active');

		$('.accordion a').on('click', function() {
			var _this = $(this);
			var _expanded = $(_this).attr('aria-expanded');
			if (_expanded == 'false') {
				$('.btn .edit .delete').show();
			} else if (_expanded == 'true') {
				$('.btn .edit .delete').hide();
			}
		});
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
								<div class="main-box-body clearfix"></div>
								<br />
								<h1 class="pull-left">Review & Approve Reporting Status</h1>
								<button class="btn btn-primary pull-right" id="approver_all-btn" title="Approve All" style="margin-right: 20px;display: none;" onclick="showApproveAllConfirmation();">Approve All</button>
								<button class="btn btn-primary pull-right" id="approver_observation-btn"
									onclick="showApproverObservation();" title="Observation"
									style="margin-right: 20px; display: none;">Observation</button>
								<button class="btn btn-primary pull-right" id="reset" title="Show Filter"
									onclick="resetFuncion()"
									style="margin-right: 20px; display: none;">Show Filter</button>
								<button class="btn btn-primary pull-right" title="Filter"
									onclick="loadApproverFilterData()" style="margin-right: 20px;"
									id="filter-btn">Filter</button>
								<br />
								<br />
								<div id="approverDiv">
									<form:form id="approverForm" cssClass="form-horizontal"
										action="#" modelAttribute="approverFilter" method="get">
										<input type="hidden" id="roleId"
											value="<%=session.getAttribute("roleId")%>" />
										<div class="container-fluid">
											<div class="col-lg-12">
												<div class="form-group">
													<div class="control-label col-xs-3">
														<form:label path="reportingperiodId" cssClass="labelColor">
															<b>Reporting Period : &nbsp;</b>
														</form:label>
													</div>
													<div class="col-xs-3">
														<form:select id="reportingId" path="reportingperiodId" cssClass="form-control"
															onchange="getApproverStatusByReportingPeriod(this);">
															<form:option value="" label="---Select Reporting Period---"></form:option>
															<c:forEach items="${reportingPeriods}"
																var="reportingperiod">
																<form:option value="${reportingperiod.id}"
																	label="${reportingperiod.year} - ${reportingperiod.name}"></form:option>
															</c:forEach>
														</form:select>
													</div>

													<div class="control-label col-xs-2">
														<form:label path="status" cssClass="labelColor">
															<b>Status : &nbsp;</b>
														</form:label>
													</div>
													<div class="col-xs-3">
														<form:select id="statusId" path="status" cssClass="form-control" onchange="getApproverStrategicPillerByStatus();">
															<form:option value="" label="---Select Status---"></form:option>
															<form:option value="All" label="All"></form:option>
															<form:option value="Reviewed" label="Reviewed"></form:option>
															<form:option value="NotReviewed" label="Not Reviewed"></form:option>
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
														</form:label>
													</div>
													<div class="col-xs-3">
														<form:select id="strategicPillarId" path="strategicPillar"
															cssClass="form-control"
															onchange="getApproverThemesByStrategicPiller(this.value);">
															<form:option value=""
																label="---Select Strategic Pillar---"></form:option>
														</form:select>
													</div>
													<div class="control-label col-xs-2">
														<form:label path="theme" cssClass="labelColor">
															<b>Theme : &nbsp;</b>
														</form:label>
													</div>
													<div class="col-xs-3">
														<form:select id="themeId" path="theme"
															cssClass="form-control"
															onchange="getApproverOutcomesByThemes(this.value);">
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
														</form:label>
													</div>
													<div class="col-xs-3">
														<form:select id="outcomeId" path="outcome"
															cssClass="form-control"
															onchange="getApproverOutputsByOutcome(this.value);">
															<form:option value="" label="---Select Outcome---"></form:option>
														</form:select>
													</div>
													<div class="control-label col-xs-2">
														<form:label path="output" cssClass="labelColor">
															<b>Output : &nbsp;</b>
														</form:label>
													</div>
													<div class="col-xs-3">
														<form:select id="outputId" path="output"
															cssClass="form-control">
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
							<div id="approverDetails"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- To show the modal window -->
	<div class="modal fade bd-example-modal-lg"
		id="approverInformation" role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content"  style="width: 991px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div align="left">
						<h4 id="subActivityNameReadonly"></h4>
					</div>
				</div>
				<div class="modal-body">
					<div class="container">
						<div>
							<form:form id="approverFilterForm" cssClass="form-horizontal"
								modelAttribute="statusTracking" method="post"
								action="createStatusTracking">
								<input type="hidden" id="id" name="id" />
								<div class="form-group">
									<div class="control-label col-xs-4">
										<label class="labelColor"><b>From : &nbsp;</b></label>
									</div>
									<div class="col-xs-7">
										<label class="control-label labelColor" style="text-align: left;" id="reviewerName"></label>
									</div>
								</div>
								<div class="form-group">
									<div class="control-label col-xs-4">
										<label class="labelColor" style="padding-top: 16px;"><b>Status
												Color : &nbsp;</b> </label>
									</div>
									<div class="project col-xs-7">
										<h2 class="text-center" style="margin-top: -19px">
											<input type="hidden" id="userIdReadonly"
												name="userIdReadonly" /> <input type="hidden"
												id="subActivityIdReadonly" name="subActivityIdReadonly" />
											<input type="hidden" id="percentReadonly" readonly="readonly"
												name="actualStatusColorReadonly" /> <input type="text"
												class="percent" id="colorPercentReadonly"
												readonly="readonly" name="actualStatusPercentageReadonly" />
										</h2>
										<div class="bar"
											style="margin-top: -1px; pointer-events: none;"></div>
									</div>
									<div style="margin: 21px -4px -1px 2px;">
											<span class="showHint"><span style="padding-top: 7px;"
												class="glyphicon glyphicon-info-sign" title="Show Colors"
												data-original-title="Show Colors"></span></span> <span
												class="hideHint"><span style="padding-top: 7px;"
												class="glyphicon glyphicon-info-sign" title="Hide Colors"
												data-original-title="Show Colors"></span></span>
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
										<label class="labelColor"><b>Key Progress in the
												quarter : &nbsp;</b> </label>
									</div>
									<div class="col-xs-7">
										<textarea class="form-control" id="keyProgressReadonly"
											readonly="readonly" name="keyProgressReadonly"
											style="margin: 0px; height: 75px;"></textarea>
									</div>
									<div>
										<span style="color: #D00B05">${errorDescription}</span>
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label class="labelColor"><b>Reason for gap if any
												: &nbsp;</b> </label>
									</div>
									<div class="col-xs-7">
										<textarea class="form-control" id="reasonForGapReadonly"
											readonly="readonly" name="reasonForGapReadonly"
											style="margin: 0px; height: 75px;"></textarea>
									</div>
									<div>
										<span style="color: #D00B05">${errorDescription}</span>
									</div>
								</div>
								<div class="form-group">
									<div class="control-label col-xs-4">
										<label class="labelColor"><b>Plan of action to
												rectify the gap : &nbsp;</b> </label>
									</div>
									<div class="col-xs-7">
										<textarea class="form-control" id="rectifyTheGapReadonly"
											readonly="readonly" name="rectifyTheGapReadonly"
											style="margin: 0px; height: 75px;"></textarea>
									</div>
									<div>
										<span style="color: #D00B05">${errorDescription}</span>
									</div>
								</div>
								<div class="form-group">
									<div class="control-label col-xs-4">
										<label class="labelColor"><b> Feedback / Comments : &nbsp;</b></label>
									</div>
									<div class="col-xs-7">
										<textarea class="form-control" id="approverFeedback" name="approverFeedback"
											style="margin: 0px; height: 75px;"></textarea>
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label class="labelColor"><b>Status : &nbsp;</b></label>
									</div>
									<div class="col-xs-8" id="approveId1" style="padding-top: 6px;">
										<input type="radio" id="completeValueReadonly" name="review"
											value="completeValue" onchange="showApproveOrCompleteButton()"> Approved <input type="radio"
											id="needToWorkValueReadonly" name="review"
											value="needToWorkValue" onchange="showApproveOrCompleteButton()"> Need more Information
									</div>
									<div id="approveId2" style="pointer-events: none;padding-top: 6px;">
										<input type="radio" id="completeValueReadonly1" name="review"
											value="completeValue"> Approved 
											<input type="radio" id="needToWorkValueReadonly1" name="review"
											value="needToWorkValue"> Need more Information
									</div>
								</div>

							</form:form>
						</div>
						<div class="modal-footer">
							<div class="col-xs-11">
								<button type="button" class="btn btn-primary" value="0" id="approveORcomplete" onclick="saveApproverStatusTrackerCompleted()">Approve and Complete</button>
								<button type="button" class="btn btn-primary" value="1" id="saveOptionButton" onclick="saveApproverStatusTracker()">Save</button>
								<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	
	<div id="approverObservationModelWindow" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
						<b id="approverObservationTitle"> Lead Agency's Observation </b>
					</h4>
				</div>
				<div class="modal-body">
					<div class="panel-group accordion" id="approverObservationViewAccordion">
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>
	
	
	<div id="observationErrorModelWindow" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
						<b id="observationTitle">Lead Agency's Observation</b>
					</h4>
				</div>
				<div class="modal-body">
					<div class="alert alert-danger" id="errorMsg" style="display:none">
						<strong>There is no observation found for this reporting period</strong>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>
	<div id="confirmApproveAllModal" class="modal fade bd-example-modal-lg" role="dialog">
		<div class="modal-dialog modal-lg">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
						<span><b style="color: red;">Approve All Filtered Activities</b></span>
					</h4>
					<hr>
					<div class="alert alert-success" id="checkForReworkMessage">Are you sure to approve all the filtered actvities?</div>
					<hr>
					<div style="height: 25px;">
					<button id="confirmApprovalAllBtn" type="button" onclick="approveAll();" class="btn btn-primary">Yes</button> &nbsp;
					<button type="button" class="btn btn-default" data-dismiss="modal">No</button>
					</div> 
				</div>
			</div>
		</div>
	</div>
	
	
	<div id="confirmApproveAndCompleteAllModal" class="modal fade bd-example-modal-lg" role="dialog">
		<div class="modal-dialog modal-lg">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
						<span><b style="color: red;">Complete Confirmation</b></span>
					</h4>
					<hr>
					<div class="alert alert-success" id="checkForReworkMessage">This Activity is planned for future reporting periods too, Are you sure to complete the activity?</div>
					<hr>
					<div style="height: 25px;">
					<button id="confirmApprovalAndCompleteBtn" type="button" onclick="saveApproverStatusTrackerCompleted(0);" class="btn btn-primary">Yes</button> &nbsp;
					<button type="button" class="btn btn-default" onclick="getStatusReviewerShow();">No</button>
					</div> 
				</div>
			</div>
		</div>
	</div>
	
	<!-- validation -->
		<div id="validationErrorModelWindow" class="modal fade" role="dialog">
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
	
	
	<!-- BackToTop Button -->
	<a href="javascript:void(0);" id="scroll" title="Scroll to Top" style="display: none;">Top<span></span></a>
	
	<script src="../static/js/demo-skin-changer.js"></script>
	<script src="../static/js/bootstrap.js"></script>
	<script src="../static/js/jquery.nanoscroller.min.js"></script>
	<script src="../static/js/demo.js"></script>
	<script src="../static/js/jquery.dataTables.js"></script>
	<script src="../static/js/dataTables.tableTools.js"></script>
	<script src="../static/js/jquery.dataTables.bootstrap.js"></script>
	<script src="../static/js/scripts.js"></script>
	<script src="../static/js/pace.min.js"></script>
	<script type="text/javascript" src="../static/scripts/login.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var selectedReportingPeriod = document.getElementById("reportingId").selectedIndex = "1";
	getApproverStatusByReportingPeriod(selectedReportingPeriod);
	$('#approveORcomplete').hide();
});
</script>
</body>
</html>