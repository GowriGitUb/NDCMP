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
		
		$('#submitForReworkTab').attr('class', 'active');
		//$('#submitReviewTab').attr('class', 'active');
		$('.collapse').collapse();

		$('.accordion a').on('click', function() {
			var _this = $(this);
			var btnId = "";
			var str = $(this).context.id.split('_');
			if (str[0] == "strategic") {
				btnId = "#strategic-1";
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
		document.getElementById("reportingPeriodId").selectedIndex = "1";
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

									<h1 class="pull-left">Submit for Rework</h1>
									<div id="partnerDiv">
										<div class="container-fluid">
											<div class="col-lg-12">
												<div class="form-group">
													<form:form id="filteringForm" cssClass="form-horizontal"
														modelAttribute="filterHierarchy" method="get">
														<input type="hidden" id="roleId"
															value="<%=session.getAttribute("roleId")%>" />
														<div class="control-label col-xs-4">
															<form:label path="year" cssClass="labelColor">
																<b>Reporting Period : &nbsp;</b>
															</form:label>
														</div>
														<div class="col-xs-3">
															<form:select id="reportingPeriodId" path="year"
																cssClass="form-control">
																<form:option value=""
																	label="---Select Reporting Period---"></form:option>
																<c:forEach items="${reportingPeriods}"
																	var="reportingPeriod">
																	<form:option value="${reportingPeriod.id}"
																		label="${reportingPeriod.year} - ${reportingPeriod.name}"></form:option>
																</c:forEach>
															</form:select>
														</div>

													</form:form>
													<button class="btn btn-primary" title="Show"
														onclick="checkForReworkShow(true)"
														id="submitForReviewShow">Show</button>
												</div>
												<div
													style="margin-top: 30px; width: 70%; margin-left: 15%; display: none;"
													id="reworkSummaryDiv">
													<button class="btn btn-primary pull-right" title="Submit"
														onclick="submitForRework()" id="submitForReview"
														style="display: none;">Submit</button>
													<table class="table table-striped table-bordered"
														style="width: 88%;">
														<thead>
															<tr style="background-color: #e84e40; color: white;">
																<th>#</th>
																<th>Agency</th>
																<th>Need more Information</th>
																<th><input type="checkbox" id="selectAllCheckBox" />
																	Select All</th>
															</tr>
														</thead>
														<tbody id="reworkSummaryTable">
														</tbody>
													</table>

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

	<div id="checkSubmitModal" class="modal fade bd-example-modal-lg"
		role="dialog">
		<div class="modal-dialog modal-lg">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
						<span><b style="color: red;">Send for Rework</b></span>
					</h4>
					<hr>
					<div class="modal-body">
						<div class="alert alert-success" id="checkForReworkMessage"></div>
						<div class="alert alert-danger" id="checkForReworkMessage2"></div>
					</div>
					<div class="modal-footer">
						<div style="height: 25px;">
							<button id="sendForReworkSubmitBtn" type="button"
								onclick="submitForRework();" class="btn btn-primary">Yes</button>
							&nbsp;
							<button type="button" class="btn btn-default"
								data-dismiss="modal" id="sendForReeviewNoBtn">No</button>
						</div>
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
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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