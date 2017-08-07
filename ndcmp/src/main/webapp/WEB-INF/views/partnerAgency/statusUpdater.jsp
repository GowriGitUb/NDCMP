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
</head>
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
	<script type="text/javascript"
	src='<c:url value="/static/scripts/menu.js"></c:url>'></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#tabs > li').removeClass('active');
		$('#reviewTab').attr('class', 'active');
	});
</script>

<script type="text/javascript"
	src='<c:url value="/static/scripts/status.js"></c:url>'></script>
<body>



	<div id="theme-wrapper">
		<%@include file="../include/menu.jsp"%>
		<div id="page-wrapper" class="container">
			<%@include file="../include/menuItems.jsp"%>
			<div id="content-wrapper">

				<div class="row">
					<div class="col-lg-12">
						<div class="main-box">
							<div class="main-box-body clearfix">
								<br />
								<c:choose>
									<c:when test="${statusTracking.id == null}">
										<h1 class="pull-left"><span>Status Updater</span></h1>
									</c:when>
									<c:otherwise>
										<h1 class="pull-left"><span>Status Reviewer</span></h1>
									</c:otherwise>
								</c:choose>
								<br /> <br />
								<hr />
								<form:form action="createStatusTracking" id="statusTrackingAddform"
									cssClass="form-horizontal" modelAttribute="statusTracking"
									method="post">
									<input type="hidden" id="roleId" value="<%= session.getAttribute("roleId") %>" />
									<br/>
									<div class="form-group">
										<form:hidden path="id" value="${statusTracking.id}" />
										<div class="control-label col-xs-3">
										<div></div>
											<label cssClass="labelColor">Status Color : &nbsp;<span
													style="color: red">*</span>
											</label>
										</div>

										<div class="project col-md-6">
											<h2 class="text-center" style="margin-top : -19px">
													<form:hidden path="userId" value="${statusTracking.user.id}"/>
													 <form:hidden path="subActivityId" value="${statusTracking.subActivity.id}"/>
													<form:hidden path="actualStatusColor" id="percent" />
													<form:input path="actualStatusPercentage" class="percent" id="colorPercent"/>
											</h2>
											<div class="bar" style="margin-top : -1px"></div>

										</div>
									</div>
									<br>

									<div class="form-group">
										<div class="control-label col-xs-3">
											<form:label path="keyProgress" cssClass="labelColor">Key Progress in the quarter : &nbsp;<span
													style="color: red">*</span>
											</form:label>
										</div>
										<div class="col-xs-6">
											<form:textarea cssClass="form-control" path="keyProgress"
												value="${statusTracking.keyProgress}" />
										</div>
										<div>
											<span style="color: #D00B05">${errorDescription}</span>
										</div>
									</div>


									<div class="form-group">
										<div class="control-label col-xs-3">
											<form:label path="reasonForGap" cssClass="labelColor">Reason for gap if any : &nbsp;<span
													style="color: red">*</span>
											</form:label>
										</div>
										<div class="col-xs-6">
											<form:textarea cssClass="form-control" path="reasonForGap"
												value="${statusTracking.reasonForGap}" />
										</div>
										<div>
											<span style="color: #D00B05">${errorDescription}</span>
										</div>
									</div>
									
									<div class="form-group">
										<div class="control-label col-xs-3">
											<form:label path="rectifyTheGap" cssClass="labelColor">Plan of action to rectify the gap : &nbsp;<span
													style="color: red">*</span>
											</form:label>
										</div>
										<div class="col-xs-6">
											<form:textarea cssClass="form-control" path="rectifyTheGap"
												value="${statusTracking.rectifyTheGap}" />
										</div>
										<div>
											<span style="color: #D00B05">${errorDescription}</span>
										</div>
									</div>
									
									<%-- <div class="form-group">
										
										<div class="col-xs-6">
											<c:choose>
												<c:when test="${statusTracking.id == null }">
													<form:radiobutton path="completeValue" />Completed
												</c:when>
												<c:otherwise>
													<form:radiobutton path="completeValue" value="completeValue" />Completed
													<form:radiobutton path="completeValue" value="needToWorkValue"/>Need to be Rework
												</c:otherwise>
											</c:choose>
										</div>
									</div> --%>
									
									
									<br />
									<div class="form-group">
										<div class="row">
											<div class="col-xs-4"></div>
											<div class="col-xs-4">
												<input type="submit" id="saveUserrole"
													class="btn btn-primary" value="Save"
													 />
												<!-- <input type="button" id="cancel" value="Cancel" class="btn btn-primary" /> -->
												<a href="partnerFilter" class="btn btn-default">Cancel</a>
											</div>
											<div class="col-xs-4"></div>
										</div>
									</div>
								</form:form>
							</div>
						</div>
						<footer id="footer-bar" class="row">
						<p id="footer-copyright" class="col-xs-12"></p>
						</footer>
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
<style>
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

</html>

