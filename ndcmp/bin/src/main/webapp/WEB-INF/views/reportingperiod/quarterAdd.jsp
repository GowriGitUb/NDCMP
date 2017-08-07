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
		$('#quaterTab').attr('class', 'active');
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
										<c:when test="${quarter.id == null}">
											<span>Add Quarter</span>
										</c:when>
										<c:otherwise>
											<span>Update Quarter</span>
										</c:otherwise>
									</c:choose>
								</h1>
								<br /> <br />
								<hr />
								<form:form id="quaterAddForm" cssClass="form-horizontal" modelAttribute="quarter"
									novalidate="novalidate" method="post" action="saveQuarter">
									<input type="hidden" id="roleId" value="<%= session.getAttribute("roleId") %>" />
									<div class="form-group">
										<div class="col-xs-6">
											<form:hidden path="id" value="${quarter.id}" />
										</div>
									</div>

									<div class="form-group">
										<div class="control-label col-xs-3">
											<form:label path="name" id="name" cssClass="labelColor">Quarter Name : &nbsp;<span
													style="color: red">*</span>
											</form:label>
										</div>

										<div class="col-xs-3">
											<div class="input-group"
												style="position: relative; display: table; border-collapse: separate;">
												<form:input cssClass="form-control" path="name" placeholder="Quarter Name"
													value="${quarter.name}" />
											</div>
										</div>
										<div>
											<span style="color: #D00B05">${quaterAlradyExitMessage}</span>
										</div>
									</div>

									<div class="form-group">
										<div class="row">
											<div class="col-xs-3"></div>
											<div class="col-xs-3">
												<input type="submit" id="saveQuarter"
													class="btn btn-primary" value="Save" /> <a
													href="quarterList" class="btn btn-default">Cancel</a>
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
