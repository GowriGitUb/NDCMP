<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../include/taglibs.jsp"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UNODC</title>
<%@include file="../include/commonlib.jsp"%>
<link href='<c:url value="/static/js/jsgrid/css/jquery-ui.css"></c:url>' />
<link href='<c:url value="/static/js/jsgrid/css/jsgrid.css"></c:url>'>
<link href='<c:url value="/static/js/jsgrid/css/theme.css"></c:url>'>
<script type="text/javascript"
	src='<c:url value="/static/jslib/bootstrap.min.js"></c:url>'></script>
<script type="text/javascript"
	src='<c:url value="/static/jsgrid/js/jquery-ui.js"></c:url>'></script>
<script type="text/javascript"
	src='<c:url value="/static/scripts/projectActivity.js"></c:url>'></script>
	<script type="text/javascript" src="../static/scripts/menu.js"></script>
<script type="text/javascript"
	src='<c:url value="/static/scripts/filter.js"></c:url>'></script>
<%@include file="../include/dataTable.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$('#tabs > li').removeClass('active');
		$('#projectTab').attr('class', 'active');

		// Register accordion click event
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
								<div class="container">
									<div id="reworkJobLevelStatus" style="min-width: 1010px; height: 1000px; max-width: 1000px; text-align: center; vertical-align: middle;">
											<img style="height: 650px;" src="<c:url value="/static/img/dashboard_loading.gif" />">
									</div>
									<div align="left">
											<h1 class="pull-left">Partner Agency List</h1>
									</div>
									<hr>
									<div class="container">
										<div class="row">
										<div class="panel-group accordion" id="collapseOne">
											<c:forEach items="${Key}" var="keyActivity">
												<div class="panel panel-default template">
													<div class="panel-heading" style="background-color: #9C9EA0;">
														<h4 class="panel-title">
															<a class="accordion-toggle" data-toggle="collapse" data-parent="#collapseOne" href="#collapseOutput_${keyActivity.id }">
															 <span><b>${keyActivity.keyActivity } </b></span>
															</a>
														</h4>
													</div>
													
													<div id="collapseOutput_${keyActivity.id }" class="panel-collapse collapse in output">
														<div class="panel-body">
															<div class="panel-group accordion" id="collapseTwo">
																<c:forEach items="${keyActivity.subActivities}" var="sub">
																	<div class="panel panel-default template1">
																		<div class="panel-heading" style="background-color: #4D5B69;">
																			<h4 class="panel-title">
																				<a class="accordion-toggle"  data-parent="#collapseTwo"
																					href="" onclick="fun('${sub.id}')"> <span><b>
																							${sub.subActivity } </b></span>
																				</a>
																			</h4>
																		</div>
																		<div id="collapseTheme_${sub.id }" class="panel-collapse collapse in output">
																			<div class="panel-body">
																				<div class="panel-group accordion" id="collapseThree">
																					<c:forEach items="${sub.partnerAgencies}" var="partnerAgency">
																						<div class="panel panel-default template2">
																							<div class="panel-heading" style="background-color: #4D5B69;">
																								<h4 class="panel-title">
																									<a class="accordion-toggle" data-toggle="collapse" data-parent="#collapseThree"
																										href="#collapsePartner_${partnerAgency.id }"> <span><b>
																												${partnerAgency.agency } </b></span>
																									</a>
																								</h4>
																							</div>
																							<div id="collapsePartner_${partnerAgency.id }" class="panel-collapse collapse in output">
																								<div class="panel-body">
																								</div>
																							</div>
																						</div>
																					</c:forEach>
																				</div>
																			</div>
																		</div>
																	</div>
																</c:forEach>
															</div>
														</div>
													</div>
												</div>
											</c:forEach>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>