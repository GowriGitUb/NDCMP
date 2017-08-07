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
<script type="text/javascript"
	src='<c:url value="/static/jslib/bootstrap-datepicker.js"></c:url>'></script>
<script type="text/javascript"
	src='<c:url value="/static/scripts/reportingPeriod.js"></c:url>'></script>
<script
	src='<c:url value="/static/jslib/jquery.validate.min.js"></c:url>'></script>
<script type="text/javascript" src="../static/scripts/menu.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#tabs > li').removeClass('active');
		$('#configurationTab').attr('class', 'active');
		$('#reportingTab').attr('class', 'active');

		var reportingStatus = '${reportingPeriod.reportingStatus}';
		var openStatus = '${openStatus}';
		if(openStatus == "true"){
		}else{
			if (reportingStatus == 'Open') {
				$('#year').css('pointer-events', 'none');
				$('#name').css('pointer-events', 'none');
				$('#startdate').css('pointer-events', 'none');
				$('#startdatePicker').css('pointer-events', 'none');
				$('#enddate').css('pointer-events', 'none');
				$('#enddatePicker').css('pointer-events', 'none');
			} else if (reportingStatus == 'Closed') {
				$('#year').css('pointer-events', 'none');
				$('#name').css('pointer-events', 'none');
				$('#startdate').css('pointer-events', 'none');
				$('#startdatePicker').css('pointer-events', 'none');
				$('#enddate').css('pointer-events', 'none');
				$('#enddatePicker').css('pointer-events', 'none');
				$('#reportingStatus').css('pointer-events', 'none');
			}
		}
		
		/* if (reportingStatus == 'Not-Started') {
		} else if (reportingStatus == 'Open') {
			$('#year').css('pointer-events', 'none');
			$('#name').css('pointer-events', 'none');
			$('#startdate').css('pointer-events', 'none');
			$('#startdatePicker').css('pointer-events', 'none');
			$('#enddate').css('pointer-events', 'none');
			$('#enddatePicker').css('pointer-events', 'none');
		} else if (reportingStatus == 'Closed') {
			$('#year').css('pointer-events', 'none');
			$('#name').css('pointer-events', 'none');
			$('#startdate').css('pointer-events', 'none');
			$('#startdatePicker').css('pointer-events', 'none');
			$('#enddate').css('pointer-events', 'none');
			$('#enddatePicker').css('pointer-events', 'none');
			$('#reportingStatus').css('pointer-events', 'none');
		} */
	});
</script>
</head>
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
								<h1 class="pull-left">
									<c:choose>
										<c:when test="${reportingPeriod.id == null}">
											<span>Add Reporting Period</span>
										</c:when>
										<c:otherwise>
											<span>Update Reporting Period</span>
										</c:otherwise>
									</c:choose>
								</h1>
								<br /> <br />
								<hr />
								<form:form id="reportingPeriodAddForm"
									cssClass="form-horizontal" modelAttribute="reportingPeriod"
									novalidate="novalidate" method="post"
									action="saveReportingPeriod	">
									<input type="hidden" id="roleId" value="<%= session.getAttribute("roleId") %>" />
									<div class="form-group">
										<div class="col-xs-6">
											<form:hidden path="id" value="${reportingPeriod.id}" />
										</div>
									</div>

									<div class="form-group">
										<div class="control-label col-xs-3">
											<form:label path="year" cssClass="labelColor">Year : &nbsp;<span
													style="color: red">*</span>
											</form:label>
										</div>
										<%-- <div class="col-xs-6">
											<form:select id="year" path="year" cssClass="form-control">
												<form:option value="" label="---Select Year---"></form:option>
												<c:forEach items="${reportingYear}" var="y">
													<form:option value="${y.year}">${y.year}</form:option>
												</c:forEach>
											</form:select>
										</div> --%>

										<div class="col-xs-3">
											<form:select path="year" id="year" cssClass="form-control">
												<form:option value="" label="---Select Year---"></form:option>
												<form:option value="2016" label="2016"></form:option>
												<form:option value="2017" label="2017"></form:option>
												<form:option value="2018" label="2018"></form:option>
												<form:option value="2019" label="2019"></form:option>
												<form:option value="2020" label="2020"></form:option>
											</form:select>
										</div>
									</div>

									<div class="form-group">
										<div class="control-label col-xs-3">
											<form:label path="name" cssClass="labelColor">Quarter : &nbsp;<span
													style="color: red">*</span>
											</form:label>
										</div>
										<%-- <div class="col-xs-6">
											<form:input cssClass="form-control" path="name" />
										</div> --%>

										<div class="col-xs-3">
											<form:select path="name" id="name" cssClass="form-control">
												<form:option value="" label="---Select Quarter---"></form:option>
												<%-- <form:option value="" label="---Select Quarter---"></form:option>
												<form:option value="Q1" label="Q1"></form:option>
												<form:option value="Q2" label="Q2"></form:option>
												<form:option value="Q3" label="Q3"></form:option>
												<form:option value="Q4" label="Q4"></form:option> --%>
												<c:forEach items="${quarterList}" var="quarter">
													<form:option value="${quarter.name}">${quarter.name}</form:option>
												</c:forEach>
											</form:select>
										</div>
										<div>
											<span style="color: #D00B05">${nameErrorMessage}</span>
										</div>
									</div>

									<input type="hidden" id="sDate"
										value="${reportingPeriod.sDate}">
									<input type="hidden" id="eDate"
										value="${reportingPeriod.eDate}">

									<div class="form-group">
										<div class="control-label col-xs-3">
											<form:label path="sDate" id="sDate" cssClass="labelColor">Start Date : &nbsp;<span
													style="color: red">*</span>
											</form:label>
										</div>

										<div class="col-xs-3">
											<div class="input-group"
												style="position: relative; display: table; border-collapse: separate;">
												<form:input cssClass="form-control datePicker"
													data-provide="datepicker" data-date-format="YYYY-MM-DD"
													path="sDate" id="startdate" placeholder="Start Date"
													value="${reportingPeriod.startdate}" />
												<label id="startdatePicker" for="startdate" style="cursor: pointer;"
													class="input-group-addon"><span
													class="glyphicon glyphicon-calendar" style="font-size: 18px;"></span></label>
											</div>
											<%-- <form:input cssClass="form-control datePicker"
												data-provide="datepicker" data-date-format="YYYY-MM-DD"
												path="sDate" id="startdate" placeholder="Start Date"
												value="${reportingPeriod.startdate}" /> --%>
										</div>
										
										<div>
											<span style="color: #D00B05">${dateComparisonErrorMessage1}</span>
										</div>

									</div>

									<div class="form-group">
										<div class="control-label col-xs-3">
											<form:label path="eDate" cssClass="labelColor">End Date : &nbsp;<span
													style="color: red">*</span>
											</form:label>
										</div>

										<div class="col-xs-3">
											<div class="input-group"
												style="position: relative; display: table; border-collapse: separate;">
												<form:input cssClass="form-control datePicker"
													data-provide="datepicker" data-date-format="YYYY-MM-DD"
												path="eDate" id="enddate" placeholder="End Date"
												value="${reportingPeriod.enddate}" />
												<label id="enddatePicker" for="enddate" style="cursor: pointer;"
													class="input-group-addon"><span
													class="glyphicon glyphicon-calendar" style="font-size: 18px;"></span></label>
											</div>
										
											<%-- <form:input cssClass="form-control datePicker"
												data-provide="datepicker" data-date-format="YYYY-MM-DD"
												path="eDate" id="enddate" placeholder="End Date"
												value="${reportingPeriod.enddate}" /> --%>
										</div>
										<div>
											<span style="color: #D00B05">${dateErrorMessage}</span>
										</div>
										<div>
											<span style="color: #D00B05">${dateComparisonErrorMessage}</span>
										</div>
										
										<div>
											<span style="color: #D00B05">${dateComparisonErrorMessage2}</span>
										</div>
									</div>

									<div class="form-group">
										<div class="control-label col-xs-3">
											<form:label path="reportingStatus" cssClass="labelColor">Reporting Period Status : &nbsp;<span
													style="color: red">*</span>
											</form:label>
										</div>
										<%-- <div class="col-xs-6">
											<form:input cssClass="form-control" path="name" />
										</div> --%>

										<div class="col-xs-3">
											<form:select path="reportingStatus" id="reportingStatus"
												cssClass="form-control" onclick="closeMessage(this);">
												<%-- <form:option value="" label="--Select Reporting Period Status--"></form:option> --%>
												<c:choose>
													<c:when test="${reportingPeriod.id == null}">
														<form:option value="Not-Started" label="Not-Started"
															selected="selected"></form:option>
														<form:option value="Open" label="Open"></form:option>
													</c:when>
													<c:otherwise>
														<c:if
															test="${reportingPeriod.reportingStatus == 'Not-Started'}">
															<form:option value="Not-Started" label="Not-Started"></form:option>
															<form:option value="Open" label="Open"></form:option>
														</c:if>
														<c:if test="${reportingPeriod.reportingStatus == 'Open'}">
															<form:option value="Open" label="Open"></form:option>
															<form:option value="Closed" label="Closed"></form:option>
														</c:if>
														<c:if test="${reportingPeriod.reportingStatus == 'Closed'}">
															<form:option value="Closed" label="Closed"></form:option>
														</c:if>
													</c:otherwise>
												</c:choose>
											</form:select>
										</div>
										<div>
											<span style="color: #D00B05">${statusErrorMessage}</span>
										</div>
									</div>

									<div class="form-group">
										<div class="row">
											<div class="col-xs-3"></div>
											<div class="col-xs-3">
												<input type="submit" id="saveReportingPeriond"
													class="btn btn-primary" value="Save" /> <a
													href="reportingList" class="btn btn-default">Cancel</a>
											</div>
											<div class="col-xs-3"></div>
										</div>
									</div>
								</form:form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

<!-- BackToTop Button -->
	<a href="javascript:void(0);" id="scroll" title="Scroll to Top" style="display: none;">Top<span></span></a>
	
	<script src='<c:url value="/static/js/demo-skin-changer.js"></c:url>'></script>
	<script src='<c:url value="/static/js/bootstrap.js"></c:url>'></script>
	<script
		src='<c:url value="/static/js/jquery.nanoscroller.min.js"></c:url>'></script>
	<script src='<c:url value="/static/js/demo.js"></c:url>'></script>

	<script src='<c:url value="/static/js/jquery.dataTables.js"></c:url>'></script>
	<script
		src='<c:url value="/static/js/dataTables.tableTools.js"></c:url>'></script>
	<script
		src='<c:url value="/static/js/jquery.dataTables.bootstrap.js"></c:url>'></script>


	<script src='<c:url value="/static/js/scripts.js"></c:url>'></script>
	<script src='<c:url value="/static/js/pace.min.js"></c:url>'></script>

	<!-- Js validation file with advanced data Table  -->

	<script type="text/javascript"
		src='<c:url value="/static/scripts/login.js"></c:url>'></script>

</body>

<!-- show download confirmation MOdal window start9-->
<div id="closeModal" class="modal fade" role="dialog">
	<div class="modal-dialog" style="width: 500px;">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">
					<strong>Reporting period close confirmation</strong>
				</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class=" col-xs-12">
						Once closed, you will not be able to open the period for status
						update.<br>
					</div>
				</div>
			</div>
			<div class="modal-footer" style="margin: -16px 150px -6px 109px;">
				<a class="btn btn-success btn-ok" onclick="saveReportingPeriod()">Ok</a>
				<button type="button" class="btn btn-default"
					onclick="setReportingPeriodStatus()">Cancel</button>
			</div>
		</div>

	</div>
</div>
<!-- show download confirmation MOdal window end9-->
</html>
