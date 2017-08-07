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

<script type="text/javascript"
	src="<c:url value="/static/ui/jquery-ui.js" />"></script>
<script type="text/javascript" src="../static/scripts/menu.js"></script>
<script type="text/javascript" src="../static/scripts/report.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#tabs > li').removeClass('active');
		$('#reportsTab').attr('class', 'active');
		$('#approverReportTab').attr('class','active');
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
						<div class="col-lg-12">
							<div class="main-box clearfix">
								<div class="main-box-body clearfix">
									<br />

									<h1 class="pull-left">Activity Status</h1>
									&nbsp;&nbsp;
									<br /> <br />
									<div id="partnerDiv">
										
											<div class="container-fluid">
												<div class="col-lg-12">
													<div class="form-group">
													<form id="filteringForm" class="form-horizontal" method="get">
														<input type="hidden" id="roleId" value="<%=session.getAttribute("roleId")%>" />
														<div class="control-label col-xs-4">
															<label id="year" cssClass="labelColor">
																<b>Reporting Period : &nbsp;</b>
																<span style="color: red">*</span>
															</label>
														</div>
														<div class="col-xs-3">
															<select id="reportingPeriodId" name="reportingPeriodId"
																class="form-control">
																<option value="" label="---Select Reporting Period---"></option>
																<c:forEach items="${reportingPeriods}" var="reportingPeriod">
																	<option value="${reportingPeriod.id}" label="${reportingPeriod.year} - ${reportingPeriod.name}"></option>
																</c:forEach>
															</select>
														</div>
														</form>
														<button class="btn btn-primary" onclick="downloadApproverReport()" id="submitForReview">Download</button>
													</div>
												</div>
											</div>
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