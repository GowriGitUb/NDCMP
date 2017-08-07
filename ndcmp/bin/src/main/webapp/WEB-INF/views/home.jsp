<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>UNODC</title>
<%@include file="include/taglibs.jsp"%>
<%@include file="include/commonlib.jsp"%>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<script type="text/javascript" src="../static/scripts/menu.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#tabs > li').removeClass('active');
	$('#dashboardTab').attr('class', 'active');
});

</script>
<title>UNODC</title>
</head>
<body onload="loadChart('${name} ${agencyCode}')">
	<div id="theme-wrapper">
		<%@include file="include/menu.jsp"%>

		<div id="page-wrapper" class="container">
			<div class="row">
				<div id="nav-col">
					<section id="col-left" class="col-left-nano">
					<div id="col-left-inner" class="col-left-nano-content">
						<div class="collapse navbar-collapse navbar-ex1-collapse"
							id="sidebar-nav">
							<input type="hidden" id="roleId" value="<%= session.getAttribute("roleId") %>" />
							<sec:authorize access="hasRole('SUPER_ADMIN')">
								<div id="modules"></div>
							</sec:authorize>
							
							<sec:authorize access="hasRole('PROJECT_ADMIN')">
								<div id="modules"></div>
							</sec:authorize>
							
							<sec:authorize access="hasRole('PROJECT_PLANNER')">
								<div id="modules"></div>
							</sec:authorize>

							<sec:authorize access="hasRole('STATUS_REVIEWER')">
								<div id="modules"></div>
							</sec:authorize>

							<sec:authorize access="hasRole('STATUS_UPDATER')">
								<div id="modules"></div>
							</sec:authorize>

							<sec:authorize access="hasRole('STATUS_APPROVER')">
								<div id="modules"></div>
							</sec:authorize>

						</div>
					</div>
					</section>
					<div id="nav-col-submenu"></div>
				</div>

				<div id="content-wrapper">
					<div class="row">
						
					</div>
					<div class="row">
						<div class="col-lg-12">
							<div class="alert alert-success" id="successNotificationDiv" style="display: none;">
							  <strong id="successNotificationTitle">Success!</strong> <div id="successNotificationMessage">message</div>
							</div>
							<div class="alert alert-info" id="infoNotificationDiv" style="display: none;">
							  <strong id="infoNotificationTitle">Info!</strong> <div id="infoNotificationMessage">message</div>
							</div>
							<div class="alert alert-danger" id="reworkNotificationDiv" style="display: none;">
							  <strong id="reworkNotificationTitle">Danger!</strong> <div id="reworkNotificationMessage">message</div>
							</div>
							
							<div id="partnerReportingStatusSummaryDiv" style="display: none;">
								<h1 style="font-size: 25px; padding-left: 5px;">Reporting Status Summary</h1>
								<table class="table table-striped table-bordered">
									<thead>
										<tr style="background-color: #e84e40;color: white;">
											<th style="vertical-align: middle;">#</th>
											<th style="vertical-align: middle;">Reporting</th>
											<th style="vertical-align: middle;">Activities</th>
											<th style="vertical-align: middle;">Carry forwarded</th>
											<th style="vertical-align: middle;">Total</th>
											<th style="vertical-align: middle;">Ready for Review</th>
											<th style="vertical-align: middle;">Not Ready for Review</th>
											<th style="vertical-align: middle;">Sent for Review</th>
											<th style="vertical-align: middle;">Approved</th>
											<th style="vertical-align: middle;">Need more Information</th>
										</tr>
									</thead>
									<tbody id="partnerReportingStatusSummmary">
									</tbody>							
								</table>
							</div>
							
							<div id="reviewerReportingStatusSummaryDiv" style="display: none;">
								<h1 style="font-size: 25px; padding-left: 5px;"></h1>
								<ul class="nav nav-tabs" id="reviewerTab" role="tablist" style="border-bottom: 1px solid #e84e40;margin-bottom: 1px;">
									<li class="nav-item">
								    	<a class="nav-link active" data-toggle="tab" href="#dashboardTab1" role="tab" aria-controls="reporitngStatusSummary">Reporting Status Summary</a>
								  	</li>
								  	<li class="nav-item">
								    	<a class="nav-link" data-toggle="tab" href="#dashboardTab2" role="tab" aria-controls="reviewReportingSummmary">Review Reporting Status Summary</a>
								  	</li>
								</ul>
								<div class="tab-content">
								  	<div class="tab-pane active" id="dashboardTab1" role="tabpanel">
								  		<table class="table table-striped table-bordered">
											<thead>
												<tr style="background-color: #e84e40;color: white;">
													<th style="vertical-align: middle;">#</th>
													<th style="vertical-align: middle;">Reporting</th>
													<th style="vertical-align: middle;">Activities</th>
													<th style="vertical-align: middle;">Carry forwarded</th>
													<th style="vertical-align: middle;">Total</th>
													<th style="vertical-align: middle;">Ready for Approval</th>
													<th style="vertical-align: middle;">Not Ready for Approval</th>
													<th style="vertical-align: middle;">Sent for Approval</th>
													<th style="vertical-align: middle;">Approved</th>
													<th style="vertical-align: middle;">Need more Information</th>
												</tr>
											</thead>
											<tbody id="reviewerReportingStatusSummmary">
											</tbody>							
										</table>
								  	</div>
								  	<div class="tab-pane" id="dashboardTab2" role="tabpanel">
								  		<table class="table table-striped table-bordered">
											<thead>
												<tr style="background-color: #e84e40;color: white;">
													<th style="vertical-align: middle;">#</th>
													<th style="vertical-align: middle;">Reporting</th>
													<th style="vertical-align: middle;">Agency Status Update</th>
													<th style="vertical-align: middle;">Reviewed</th>
													<th style="vertical-align: middle;">Approved</th>
													<th style="vertical-align: middle;">Need more Information</th>
													<th style="vertical-align: middle;">Sent for Rework</th>
													<th style="vertical-align: middle;">Not Reviewed</th>
												</tr>
											</thead>
											<tbody id="reviewerPartnerReportingStatusSummmary">
											</tbody>							
										</table>
								  	</div>
								</div>								
							</div>
							
							<div id="approverReportingStatusSummaryDiv" style="display: none;">
								<h1 style="font-size: 25px; padding-left: 5px;">Reporting Status Review Summary</h1>
								<table class="table table-striped table-bordered">
									<thead>
										<tr style="background-color: #e84e40;color: white;">
											<th style="vertical-align: middle;">#</th>
											<th style="vertical-align: middle;">Reporting</th>
											<th style="vertical-align: middle;">Lead Status Update</th>
											<th style="vertical-align: middle;">Reviewed</th>
											<th style="vertical-align: middle;">Approved</th>
											<th style="vertical-align: middle;">Need more Info</th>
											<th style="vertical-align: middle;">Sent for Rework</th>
											<th style="vertical-align: middle;">Not Reviewed</th>
										</tr>
									</thead>
									<tbody id="approverReportingStatusSummmary">
									</tbody>							
								</table>
							</div>
							
							<sec:authorize access="hasRole('STATUS_UPDATER')">
								<div class="row">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<div id="datacapturingDashboardDiv" class="main-box">
											<header class="main-box-header clearfix">
												<div class="pull-right">
													<button type="button" class="btn btn-primary"  style="margin-left: 10px;" onclick="statusUpdaterDashboard('${name} ${agencyCode}');">Go</button>
												</div>
												<div class="pull-right">
													<select class="form-control" id="datacapturingYear">
													</select>
												</div>
											</header>
											<div class="main-box-body clearfix">
												<div id="statusUpdaterDashboardDiv" style="min-width: 310px; height: 400px; margin: 0 auto;text-align: center;vertical-align: middle;">
													<img alt="" src="../static/img/dashboardloading.gif" style="margin-top:100px ">
												</div>
											</div>
										</div>
									</div>
								</div>
							</sec:authorize>
							<sec:authorize access="hasAnyRole('STATUS_REVIEWER')">
								<div class="row">
									<div class="col-md-6 col-sm-6 col-xs-12">
										<div id="datacapturingDashboardDiv" class="main-box">
											<header class="main-box-header clearfix">
												<div class="pull-right">
													<button type="button" class="btn btn-primary"  style="margin-left: 10px;" onclick="statusUpdaterDashboard('${name} ${agencyCode}');">Go</button>
												</div>
												<div class="pull-right">
													<select class="form-control" id="datacapturingYear">
													</select>
												</div>
											</header>
											<div class="main-box-body clearfix">
												<div id="statusUpdaterDashboardDiv" style="min-width: 310px; height: 400px; margin: 0 auto;text-align: center;vertical-align: middle;">
													<img alt="" src="../static/img/dashboardloading.gif" style="margin-top:100px ">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6 col-sm-6 col-xs-12">
										<div id="dataReviewedDashboardDiv" class="main-box">
											<header class="main-box-header clearfix">
												<div class="pull-right">
													<button type="button" class="btn btn-primary"  style="margin-left: 10px;" onclick="statusReviewedDashboard('${name} ${agencyCode}');">Go</button>
												</div>
												<div class="pull-right">
													<select class="form-control" id="dataReviewedYear">
													</select>
												</div>
											</header>
											<div class="main-box-body clearfix">
												<div id="statusReviewerDashboardDiv" style="min-width: 310px; height: 400px; margin: 0 auto;vertical-align: middle;text-align: center;">
													<img alt="" src="../static/img/dashboardloading.gif" style="margin-top:100px ">
												</div>
											</div>
										</div>
									</div>
								</div>
							</sec:authorize>
							<sec:authorize access="hasAnyRole('SUPER_ADMIN','PROJECT_ADMIN', 'PROJECT_PLANNER', 'STATUS_APPROVER')">
								<div class="row">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<div id="approverDashboardDiv" class="main-box">
											<header class="main-box-header clearfix">
												<div class="pull-right">
													<button type="button" id="approverGoButton" class="btn btn-primary"  style="margin-left: 10px;" onclick="statusApproverDashboard('${name} ${agencyCode}');">Go</button>
												</div>
												<div class="pull-right">
													<select class="form-control" id="ApproverDashboardYear">
													</select>
												</div>
												<div class="pull-right">
													<button type="button" id="approverBackButton" class="btn btn-default"  style="margin-left: 10px; display: none;" onclick="showApproverChartLevel1();">Back</button>
												</div>
											</header>
											<div class="main-box-body clearfix">
												<div id="statusApproverDashboardDiv" style="min-width: 310px; height: 400px; margin: 0 auto;text-align: center;vertical-align: middle;">
													<img alt="" src="../static/img/dashboardloading.gif" style="margin-top:100px ">
												</div>
												<div id="statusApproverDashboardLevel2Div" style="min-width: 310px; height: 400px; margin: 0 auto;text-align: center;vertical-align: middle; display: none;">
													<img alt="" src="../static/img/dashboardloading.gif" style="margin-top:100px ">
												</div>
											</div>
										</div>
									</div>
								</div>
							</sec:authorize>
							<!-- <div class="main-box">
								<header class="main-box-header clearfix">
								<h2>
									Data Capturing Status - <span id="yearAct">2016</span>
								</h2>
								<div class="pull-right">
									<select class="form-control" id="yearActivity" onchange="yearWiReport()">
										<option value="2016">2016</option>
										<option value="2017">2017</option>
										<option value="2018">2018</option>
										<option value="2019">2019</option>
										<option value="2020">2020</option>
									</select>
									<button type="button" class="btn btn-primary">Go</button>
								</div>

								</header>
								<div class="main-box-body clearfix">
									<div id="placeholder"
										style="height: 400px; padding: 0px; position: relative;"></div>
								</div>
							</div> -->
						</div>
					</div>

					<!-- <div class="row">
						<div class="col-lg-12">
							<div class="main-box">
								<header class="main-box-header clearfix">
								<h2>
									Planned Vs Actual - <span id="year"></span>
								</h2>
								<div class="pull-right">
									<select id="planVsActual" onchange="yearPlanVsActual()">
										<option value="2016">2016</option>
										<option value="2017">2017</option>
										<option value="2018">2018</option>
										<option value="2019">2019</option>
										<option value="2020">2020</option>
									</select>

								</div>
								</header>
								<div class="main-box-body clearfix">
									<div id="bar-chart"
										style="height: 400px; padding: 0px; position: relative;"></div>
								</div>
							</div>
						</div>
					</div> -->
				</div>
			</div>
		</div>
	</div>
	
	<div id="config-tool" class="closed">
		<div id="config-tool-options">
			<h4>Layout Options</h4>
			<ul>
				<li>
					<div class="checkbox-nice">
						<input type="checkbox" id="config-fixed-header" /> <label
							for="config-fixed-header"> Fixed Header </label>
					</div>
				</li>
				<li>
					<div class="checkbox-nice">
						<input type="checkbox" id="config-fixed-sidebar" /> <label
							for="config-fixed-sidebar"> Fixed Left Menu </label>
					</div>
				</li>
				<li>
					<div class="checkbox-nice">
						<input type="checkbox" id="config-fixed-footer" /> <label
							for="config-fixed-footer"> Fixed Footer </label>
					</div>
				</li>
			</ul>
			<br />
		</div>
	</div>

	<!-- Modal -->
	<div id="myModal" class="modal fade" role="dialog">
		<div class="modal-dialog" style="width: 1100px;">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-body">
					<div id="container"
						style="width: 1000px; height: 500px; margin: 0 auto"></div>

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
	<script src="../static/js/jquery.js"></script>
	<script src="../static/js/bootstrap.js"></script>
	<script src="../static/js/jquery.nanoscroller.min.js"></script>
	<script src="../static/js/demo.js"></script>

	<script src="../static/js/d3.min.js"></script>
	<script src="../static/js/xcharts.js"></script>
	<script src="../static/js/rainbow.min.js"></script>

	<script src="../static/js/flot/jquery.flot.js"></script>
	<script src="../static/js/flot/jquery.flot.min.js"></script>
	<script src="../static/js/flot/jquery.flot.pie.min.js"></script>
	<script src="../static/js/flot/jquery.flot.stack.min.js"></script>
	<script src="../static/js/flot/jquery.flot.resize.min.js"></script>
	<script src="../static/js/flot/jquery.flot.time.min.js"></script>
	<script src="../static/js/flot/jquery.flot.orderBars.js"></script>
	<script src="../static/js/flot/jquery.flot.axislabels.js"></script>


	<script src="../static/js/highcharts.js"></script>
	<script src="../static/js/exporting.js"></script>

	<script src="../static/js/scripts.js"></script>
	<script src="../static/js/pace.min.js"></script>

	<script type="text/javascript"
		src="../static/scripts/admin-dashboard.js"></script>

</body>

</html>