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
<%@include file="../include/dataTable.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$('#tabs > li').removeClass('active');
	$('#projectTab').attr('class','active');
	
	// Register accordion click event
	$('.accordion a').on('click', function() {
		var _this = $(this);
		var _expanded = $(_this).attr('aria-expanded');
		if(_expanded == 'false') {
			$('.btn .edit .delete').show();
		} else if(_expanded == 'true') {
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
	<%-- <sec:authorize access="hasRole('PROJECT_PLANNER')">
	<div id="theme-wrapper" style="width: 2000px;">
	</sec:authorize>
	<sec:authorize access="!hasRole('PROJECT_PLANNER')">
	<div id="theme-wrapper">
	</sec:authorize> --%>
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
										<div id="reworkJobLevelStatus"
											style="min-width: 1010px; height: 1000px; max-width: 1000px; text-align: center; vertical-align: middle;">
											<img style="height: 650px;"
												src="<c:url value="/static/img/dashboard_loading.gif" />">
										</div>
										<div align="left">
											<h1 class="pull-left">Project Activity List</h1>
										</div>
										<hr />
										<div class="container">
											<br />
											<div class="row">
												<sec:authorize access="hasRole('PROJECT_PLANNER')">
													<div class="col-sm-12 col-md-12">
												</sec:authorize>
												<sec:authorize access="!hasRole('PROJECT_PLANNER')">
													<div class="col-sm-10 col-md-10">
												</sec:authorize>
												<div class="panel-group accordion" id="accordion">
													<c:if test="${not empty strategicPillars }">
														<sec:authorize access="hasRole('PROJECT_ADMIN')">
															<div align="right" style="margin-bottom: -30px;">
																<button type="button"
																	style="width: 140px; margin: 0px -155px -2px 0px;"
																	class="btn btn-primary btn-add-panel"
																	data-toggle="modal"
																	data-target="#addStrategicPillarModal">New
																	Pillar</button>
															</div>
														</sec:authorize>
														<c:forEach items="${ strategicPillars}" var="strategic">
															<div class="panel panel-default">
																<sec:authorize access="hasRole('PROJECT_ADMIN')">
																<div align="right" style="margin-bottom: -30px;">
																	<button type="button"
																		style="width: 140px; margin: 0px -155px -2px 0px;"
																		class="btn btn-primary btn-add-panel"
																		data-toggle="modal"
																		data-target="#strategicPillarModal">
																		New Pillar
																	</button>
																	</div>
																	<div align="right" style="margin-bottom: -30px;">
																		<a href="#" title="Edit" class="edit"
																			onclick="editStrategicPillar(${strategic.id});"
																			style="margin: 0px -188px -2px 150px;"> <span
																			class="fa-stack"> <i
																				class="fa fa-square fa-stack-2x"></i> <i
																				class="fa fa-pencil fa-stack-1x fa-inverse"></i>
																		</span>
																		</a>
																		<%-- <a href="#"
																			style="margin: 0px -188px -2px 150px;"
																			onclick="editStrategicPillar(${strategic.id});">edit</a> --%>
																	</div>
																	<div align="right" style="margin-bottom: -29px;">
																		<a href="#" data-toggle="modal" data-target="#myModal"
																			data-id=${strategic.id }
																			data-href="deleteStrategicPillar?id=${strategic.id }"
																			class="table-link danger delete" title="Delete"
																			style="margin: 0px -218px -2px 150px;"> <span
																			class="fa-stack"> <i
																				class="fa fa-square fa-stack-2x"></i> <i
																				class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
																		</span>
																		</a>
																	</div>
																</sec:authorize>
																<div class="panel-heading">
																	<h4 class="panel-title">
																		<a class="accordion-toggle" data-toggle="collapse"
																			data-parent="#accordion"
																			href="#collapseStrategic_${strategic.id }"> <span><b>${strategic.name
																					}</b></span> <input type="hidden" id="strategicId"
																			value="${strategic.id}" />
																		</a>
																	</h4>
																</div>
																<div id="collapseStrategic_${strategic.id }"
																	class="panel-collapse collapse in strategic-pillar">
																	<div class="panel-body">
																		<div class="panel-group accordion" id="collapseOne">
																			<c:if test="${not empty strategic.id }">
																				<sec:authorize access="hasRole('PROJECT_ADMIN')">
																					<div align="right" style="margin-bottom: -30px;">
																						<button type="button"
																							style="margin: 0px -155px -2px 0px; width: 140px;"
																							class="btn btn-primary btn-add-panel1"
																							data-toggle="modal" data-target="#addThemeModal">
																							New Theme</button>
																					</div>
																				</sec:authorize>
																			</c:if>
																			<c:if test="${not empty strategic.themes }">
																				<c:forEach items="${ strategic.themes}" var="theme">
																					<div class="panel panel-default template1">
																						<div class="panel-heading"
																							style="background-color: #4D5B69;">
																							<h4 class="panel-title">
																								<a class="accordion-toggle"
																									data-toggle="collapse"
																									data-parent="#collapseOne"
																									href="#collapseTheme_${theme.id }"> <span><b>
																											${theme.name } </b></span>
																								</a>
																							</h4>
																						</div>
																						<sec:authorize access="hasRole('PROJECT_ADMIN')">
																							<div align="right" style="margin-top: -30px;">
																								<a href="#" title="Edit" class="edit"
																									onclick="editTheme(${theme.id});"
																									style="margin: 0px -188px -2px 150px;"> <span
																									class="fa-stack"> <i
																										class="fa fa-square fa-stack-2x"></i> <i
																										class="fa fa-pencil fa-stack-1x fa-inverse"></i>
																								</span>
																								</a>
																							</div>
																							<div align="right" style="margin-top: -30px;">
																								<a href="#" data-toggle="modal"
																									data-target="#myModal" data-id=${theme.id }
																									data-href="deleteTheme?id=${theme.id }"
																									class="table-link danger delete" title="Delete"
																									style="margin: 0px -218px -2px 150px;"> <span
																									class="fa-stack"> <i
																										class="fa fa-square fa-stack-2x"></i> <i
																										class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
																								</span>
																								</a>
																							</div>
																						</sec:authorize>
																						<div id="collapseTheme_${theme.id }"
																							class="panel-collapse collapse in theme">
																							<div class="panel-body">
																								<c:if test="${not empty theme.id }">
																									<sec:authorize
																										access="hasRole('PROJECT_ADMIN')">
																										<div align="right"
																											style="margin-bottom: -33px;">
																											<button type="button"
																												style="margin: 0px -155px 0px 0px; width: 140px;"
																												class="btn btn-primary btn-add-panel2"
																												data-toggle="modal"
																												data-target="#outcomeModal">New
																												Outcome</button>
																										</div>
																									</sec:authorize>
																								</c:if>
																								<div class="panel-group accordion"
																									id="accordion2">
																									<c:if test="${not empty theme.outcomes }">
																										<c:forEach items="${ theme.outcomes}"
																											var="outcome">
																											<sec:authorize
																												access="hasRole('PROJECT_ADMIN')">
																												<div align="right"
																													style="margin-top: -30px;">
																													<a href="#" title="Edit" class="edit"
																														onclick="editOutcome(${outcome.id});"
																														style="margin: 0px -188px -2px 150px;">
																														<span class="fa-stack"> <i
																															class="fa fa-square fa-stack-2x"></i> <i
																															class="fa fa-pencil fa-stack-1x fa-inverse"></i>
																													</span>
																													</a>
																												</div>
																												<div align="right"
																													style="margin-top: -30px;">
																													<a href="#" data-toggle="modal"
																														data-target="#myModal"
																														data-id=${outcome.id }
																														data-href="deleteOutcome?id=${theme.id }"
																														class="table-link danger delete"
																														title="Delete"
																														style="margin: 0px -218px -2px 150px;">
																														<span class="fa-stack"> <i
																															class="fa fa-square fa-stack-2x"></i> <i
																															class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
																													</span>
																													</a>
																												</div>
																											</sec:authorize>
																											<div class="panel panel-default template2">
																												<div class="panel-heading"
																													style="background-color: #77818C;">
																													<h4 class="panel-title">
																														<a class="accordion-toggle"
																															data-toggle="collapse"
																															data-parent="#accordion2"
																															href="#collapseOutcome_${outcome.id }">
																															<span><b>
																																	${outcome.sequenceNumber }
																																	${outcome.name } </b></span>
																														</a>
																													</h4>
																												</div>

																												<div id="collapseOutcome_${outcome.id }"
																													class="panel-collapse collapse in outcome">
																													<div class="panel-body">
																														<c:if test="${not empty outcome.id }">
																															<sec:authorize
																																access="hasRole('PROJECT_ADMIN')">
																																<div align="right"
																																	style="margin-bottom: -33px;">
																																	<button type="button"
																																		style="margin: 4px -157px -3px -3px; width: 140px;"
																																		class="btn btn-primary btn-add-panel3"
																																		data-toggle="modal"
																																		data-target="#outputModal">
																																		New Output</button>
																																</div>
																															</sec:authorize>
																														</c:if>
																														<div class="panel-group accordion"
																															id="accordion3">
																															<c:if
																																test="${not empty outcome.outputs }">
																																<c:forEach items="${ outcome.outputs}"
																																	var="output">
																																	<sec:authorize
																																		access="hasRole('PROJECT_ADMIN')">
																																		<div align="right"
																																			style="margin-top: -30px;">
																																			<a href="#" title="Edit" class="edit"
																																				onclick="editOutput(${output.id});"
																																				style="margin: 0px -192px -2px 150px;">
																																				<span class="fa-stack"> <i
																																					class="fa fa-square fa-stack-2x"></i>
																																					<i
																																					class="fa fa-pencil fa-stack-1x fa-inverse"></i>
																																			</span>
																																			</a>
																																		</div>
																																		<div align="right"
																																			style="margin-top: -30px;">
																																			<a href="#" data-toggle="modal"
																																				data-target="#myModal"
																																				data-id=${output.id }
																																				data-href="deleteOutput?id=${output.id }"
																																				class="table-link danger delete"
																																				title="Delete"
																																				style="margin: 0px -225px -2px 150px;">
																																				<span class="fa-stack"> <i
																																					class="fa fa-square fa-stack-2x"></i>
																																					<i
																																					class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
																																			</span>
																																			</a>
																																		</div>
																																	</sec:authorize>
																																	<div
																																		class="panel panel-default template3">
																																		<div class="panel-heading"
																																			style="background-color: #9C9EA0;">
																																			<h4 class="panel-title">
																																				<a class="accordion-toggle"
																																					data-toggle="collapse"
																																					data-parent="#accordion3"
																																					href="#collapseOutput_${output.id }">
																																					<span> <b>
																																							${output.sequenceNumber }
																																							${output.name } </b></span>
																																				</a>
																																			</h4>
																																		</div>

																																		<div id="collapseOutput_${output.id }"
																																			class="panel-collapse collapse in output">
																																			<div class="panel-body">
																																				<c:if test="${not empty output.id }">
																																					<sec:authorize
																																						access="hasRole('PROJECT_ADMIN')">
																																						<div align="right"
																																							style="margin-bottom: -34px;">
																																							<button type="button"
																																								style="margin: 0px -158px 0px 0px; width: 140px;"
																																								class="btn btn-primary btn-add-panel4"
																																								data-toggle="modal"
																																								data-target="#keyActivityModal">
																																								New Key Activity</button>
																																						</div>
																																					</sec:authorize>
																																				</c:if>
																																				<div class="panel-group accordion" id="accordion4">
																																					<c:if
																																						test="${not empty output.keyActivities }">
																																						<div class="table-responsive dataTables-wrapper"
																																								style="width: 100%">
																																						<table id="planner-table${output.id}"
																																									style="margin-left: 0px;"
																																									class="table table-hover table-bordered CSSTableGenerator">
																																									<thead>
																																										<tr>
																																											<th>Sequence Number</th>
																																											<th>Key Activity</th>
																																											<th>Sub Activity</th>
																																											<th>Responsible Entity</th>
																																											<th>Partnering Agency</th>
																																											<th>MOV</th>
																																											<th>Actions</th>
																																											<sec:authorize
																																												access="hasRole('PROJECT_PLANNER')">
																																												<c:if
																																													test="${not empty years }">
																																													<c:forEach items="${years}"
																																														var="year">
																																														<th colspan="4"><c:out
																																																value="${year }" /></th>
																																													</c:forEach>
																																												</c:if>
																																											</sec:authorize>
																																											<!-- <th colspan="4">Year</th> -->
																																										</tr>
																																										<tr
																																											style="background-color: #e84e40;">
																																											<th></th>
																																											<th></th>
																																											<th></th>
																																											<th></th>
																																											<th></th>
																																											<th></th>
																																											<th></th>
																																											<%-- <c:if test="${not empty output.keyActivities }">
																																													<c:forEach 	items="${output.keyActivities.subActivities.reportingPeriodDTOs }"
																																														var="reportingPeriod">
																																														<th><c:-Out value="${reportingPeriod.year }" /></th>
																																													</c:forEach>
																																											</c:if> --%>
																																											<%-- <c:forEach items="${ keyActivity.subActivities}"
																																													var="subActivity"> --%>
																																											<sec:authorize
																																												access="hasRole('PROJECT_PLANNER')">
																																												<c:if
																																													test="${not empty reportingPeriods }">
																																													<c:forEach
																																														items="${reportingPeriods}"
																																														var="reportingPeriod">
																																														<th style="color: #fff"><c:out
																																																value="${reportingPeriod.name }" /></th>
																																													</c:forEach>
																																												</c:if>
																																											</sec:authorize>
																																											<%-- </c:forEach> --%>
																																										</tr>
																																									</thead>
																																									<sec:authorize
																																								access="hasRole('PROJECT_PLANNER')">
																																								<div
																																									style="padding: 5px; padding-left: 0px;">
																																									<b>Show/Hide Column</b>
																																									<!-- <a
																																										class="toggle-vis"
																																										data-columnindex="0">Sequence
																																										Number</a>- <a class="toggle-vis"
																																										data-columnindex="1">Key
																																										Activity</a>- <a
																																										class="toggle-vis"
																																										data-columnindex="2">Sub
																																										Activity</a>- -->
																																									<a class="response toggle-vis"
																																										data-columnindex="3" id="responsible-${output.id}">Responsible
																																										Entity</a>- <a
																																										class="partner toggle-vis"
																																										data-columnindex="4" id="partner-${output.id}">Partnering
																																										Agency</a>- <a class="toggle-vis"
																																										data-columnindex="5" id="mov-${output.id}">MOV</a>
																																								</div>
																																								<div>
																																									<button
																																										class="btn btn-primary pull-right"
																																										id="save-plan" onclick="savePlan()">Save</button>
																																									<br />
																																									<br />
																																								</div>

																																							</sec:authorize>
																																						<c:forEach
																																							items="${ output.keyActivities}"
																																							var="keyActivity">
																																							
																																							<%-- <div class="table-responsive"
																																								style="width: 100%">
																																								<sec:authorize
																																									access="hasRole('PROJECT_ADMIN')">
																																									
																																								</sec:authorize> --%>
																																								
																																									
																																									<tbody>
																																										<tr>
																																											<td><c:out
																																													value="${keyActivity.sequenceNumber }" /></td>
																																											<td><c:out
																																													value="${keyActivity.keyActivity}" /></td>
																																											<c:if
																																												test="${not empty keyActivity.subActivities }">
																																												<td></td>
																																												<td></td>
																																												<td></td>
																																												<td></td>
																																												<sec:authorize
																																													access="hasRole('PROJECT_PLANNER')">
																																													<c:forEach
																																														items="${reportingPeriods}"
																																														var="reportingPeriod"
																																														varStatus="recordCount">
																																														<td></td>
																																													</c:forEach>
																																												</sec:authorize>
																																											</c:if>
																																											<td><sec:authorize
																																													access="hasRole('PROJECT_ADMIN')">
																																													<c:if
																																														test="${not empty keyActivity }">
																																														<a href="#"
																																															style="color: red; text-decoration: none;"
																																															onclick="openSubActivtyModal(${keyActivity.id});"><img style="height: 24px;margin-top: -2px;"
																																															src="../static/img/add.png" ></a>
																																													
																																													<a href="#" title="Edit" class="edit"
																																														onclick="editKeyActivity(${keyActivity.id});">
																																														<span class="fa-stack">
																																															<i
																																															class="fa fa-square fa-stack-2x"></i>
																																															<i
																																															class="fa fa-pencil fa-stack-1x fa-inverse"></i>
																																													</span>
																																													</a>
																																													<a href="#"
																																														data-toggle="modal"
																																														data-target="#myModal"
																																														data-id=${keyActivity.id }
																																														data-href="deleteKeyActivity?id=${keyActivity.id }"
																																														class="table-link danger delete"
																																														title="Delete"
																																														>
																																														<span class="fa-stack">
																																															<i
																																															class="fa fa-square fa-stack-2x"></i>
																																															<i
																																															class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
																																													</span>
																																													</a>
																																												</c:if>
																																											</sec:authorize></td>
																																										</tr>
																																										<c:if
																																											test="${not empty keyActivity.subActivities }">
																																											<c:forEach
																																												items="${ keyActivity.subActivities}"
																																												var="subActivity">
																																												<tr>
																																													<td><c:out
																																															value="${subActivity.sequenceNumber }" /></td>
																																													<td></td>
																																													<td>${subActivity.subActivity
																																														}</td>
																																													<td>${subActivity.leadAgency.agency
																																														}</td>
																																													<td></td>
																																													<td>${subActivity.mov
																																														}</td>
																																													<td><sec:authorize
																																															access="hasRole('PROJECT_ADMIN')">
																																															<div
																																																style="margin-top: -30px; margin: 0px 0px -26px -2px;">
																																																<a href="#" title="Edit"
																																																	class="edit"
																																																	onclick="editSubActivity(${subActivity.id});">
																																																	<span class="fa-stack">
																																																		<i
																																																		class="fa fa-square fa-stack-2x"></i>
																																																		<i
																																																		class="fa fa-pencil fa-stack-1x fa-inverse"></i>
																																																</span>
																																																</a>
																																															</div>
																																															<div
																																																style="margin-top: -30px; margin: 0px 0px 0px 28px">
																																																<a href="#"
																																																	data-toggle="modal"
																																																	data-target="#myModal"
																																																	data-id=${subActivity.id }
																																																	data-href="deleteSubActivity?id=${subActivity.id }"
																																																	class="table-link danger delete"
																																																	title="Delete"> <span
																																																	class="fa-stack">
																																																		<i
																																																		class="fa fa-square fa-stack-2x"></i>
																																																		<i
																																																		class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
																																																</span>
																																																</a>
																																															</div>
																																														</sec:authorize></td>
																																													<sec:authorize
																																														access="hasRole('PROJECT_PLANNER')">
																																														<c:forEach
																																															items="${reportingPeriods}"
																																															var="reportingPeriod"
																																															varStatus="recordCount">
																																															<td><input
																																																type="checkbox"
																																																name="plan"
																																																id="${readCount.index}"
																																																value="${subActivity.id},${reportingPeriod.id}"<%-- onclick="plan(${subActivity.id}, ${reportingPeriod.id })" --%>></input></td>
																																														</c:forEach>
																																													</sec:authorize>
																																												</tr>
																																											</c:forEach>
																																										</c:if>
																																									</tbody>
																																						</c:forEach>
																																						</table>
																																						</div>
																																					</c:if>
																																				</div>
																																			</div>
																																		</div>
																																	</div>
																																	<c:if test="${not empty output.id }">
																																		<sec:authorize
																																			access="hasRole('PROJECT_ADMIN')">
																																			<div align="right"
																																				style="margin-top: 28px;">
																																				<button type="button"
																																					style="margin: 0px -158px 0px 0px; width: 140px;"
																																					class="btn btn-primary"
																																					onclick="indicatorModal(${output.id});">
																																					New Indicator</button>
																																			</div>
																																		</sec:authorize>
																																	</c:if>
																																	<c:if
																																		test="${not empty output.indicators }">
																																		<c:forEach
																																			items="${output.indicators }"
																																			var="indicator">
																																			<sec:authorize
																																				access="hasRole('PROJECT_ADMIN')">
																																				<div align="right"
																																					style="margin-top: -30px;">
																																					<a href="#" title="Edit"
																																						class="edit"
																																						onclick="editIndicator(${indicator.id});"
																																						style="margin: 0px -188px -2px 150px;">
																																						<span class="fa-stack"> <i
																																							class="fa fa-square fa-stack-2x"></i>
																																							<i
																																							class="fa fa-pencil fa-stack-1x fa-inverse"></i>
																																					</span>
																																					</a>
																																				</div>
																																				<div align="right"
																																					style="margin-top: -30px;">
																																					<a href="#" data-toggle="modal"
																																						data-target="#myModal"
																																						data-id=${indicator.id }
																																						data-href="deleteIndicator?id=${indicator.id }"
																																						class="table-link danger delete"
																																						title="Delete"
																																						style="margin: 0px -218px -2px 150px;">
																																						<span class="fa-stack"> <i
																																							class="fa fa-square fa-stack-2x"></i>
																																							<i
																																							class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
																																					</span>
																																					</a>
																																				</div>
																																			</sec:authorize>
																																			<table>
																																				<tr>
																																					<td><b>Targets : </b></td>
																																				</tr>
																																				<tr>
																																					<td>*&nbsp;<c:out
																																							value="${indicator.message}" /></td>
																																				</tr>
																																			</table>
																																		</c:forEach>
																																	</c:if>

																																	<c:if test="${not empty output.id }">
																																		<%-- <div align="right" style="margin-top: 20px;">
																																											<a type="button" style="margin: 0px -158px 0px 0px; width: 140px;"
																																												class="btn btn-primary"
																																												onclick="targetModal(${output.id});">
																																												New Target
																																											</a>
																																										</div> --%>
																																		<sec:authorize
																																			access="hasRole('PROJECT_ADMIN')">
																																			<div align="right"
																																				style="margin-top: 20px;">
																																				<button type="button"
																																					style="margin: 0px -158px 0px 0px; width: 140px;"
																																					class="btn btn-primary"
																																					onclick="targetModal(${output.id});">
																																					New Target</button>
																																			</div>
																																		</sec:authorize>
																																	</c:if>
																																	<c:if
																																		test="${not empty output.targets }">
																																		<c:forEach items="${output.targets }"
																																			var="target">
																																			<sec:authorize
																																				access="hasRole('PROJECT_ADMIN')">
																																				<div align="right"
																																					style="margin-top: -30px;">
																																					<a href="#" title="Edit"
																																						class="edit"
																																						onclick="editTarget(${target.id});"
																																						style="margin: 0px -188px -2px 150px;">
																																						<span class="fa-stack"> <i
																																							class="fa fa-square fa-stack-2x"></i>
																																							<i
																																							class="fa fa-pencil fa-stack-1x fa-inverse"></i>
																																					</span>
																																					</a>
																																				</div>
																																				<div align="right"
																																					style="margin-top: -30px;">
																																					<a href="#" data-toggle="modal"
																																						data-target="#myModal"
																																						data-id=${target.id }
																																						data-href="deleteTarget?id=${target.id }"
																																						class="table-link danger delete"
																																						title="Delete"
																																						style="margin: 0px -218px -2px 150px;">
																																						<span class="fa-stack"> <i
																																							class="fa fa-square fa-stack-2x"></i>
																																							<i
																																							class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
																																					</span>
																																					</a>
																																				</div>
																																			</sec:authorize>
																																			<table>
																																				<tr>
																																					<td><b>Targets : </b></td>
																																				</tr>
																																				<tr>
																																					<td>*&nbsp;<c:out
																																							value="${target.message}" /></td>
																																				</tr>
																																			</table>
																																		</c:forEach>
																																	</c:if>
																																</c:forEach>
																															</c:if>
																														</div>
																													</div>
																												</div>


																											</div>
																										</c:forEach>
																									</c:if>
																								</div>

																							</div>
																						</div>
																					</div>
																				</c:forEach>
																			</c:if>
																		</div>

																	</div>
																</div>
															</div>
														</c:forEach>
													</c:if>
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
	</div>
	<!-- Add Strategic Pillar Modal -->
	<div class="modal fade bd-example-modal-lg"
		id="addStrategicPillarModal" role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div align="left">
						<c:choose>
							<c:when test="${strategicPillar.id == null}">
								<h4>Add Strategic Pillar</h4>
							</c:when>
							<c:otherwise>
								<h4>Update Strategic Pillar</h4>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="modal-body">
					<div class="container">
						<div>
							<form:form id="addStrategicPillarForm" cssClass="form-horizontal"
								modelAttribute="addStrategicPillar" method="post"
								action="addStrategicpillar">

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Strategic Pillar Name :
											&nbsp;<span style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input type="hidden" id="strategicPillarId"
											name="strategicPillarId" value="${addStrategicPillar.id}" />
										<input id="strategicPillarName" name="strategicPillar"
											style="margin-top: 5px;" class="form-control" />
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Project Name : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="projectName" name="project"
											style="margin-top: 5px;" readonly="readonly"
											class="form-control" />
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Sequence Number of
											Strategic Pillar : &nbsp;<span style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="pillarSN" name="strategicPillarSequenceNumber"
											style="margin-top: 5px;" class="form-control" />
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Theme : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="theme" name="theme" style="margin-top: 5px;"
											class="form-control" />
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Sequence Number of
											Outcome : &nbsp;<span style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="outcomeSN" name="outcomeSequenceNumber"
											style="margin-top: 5px;" class="form-control" />
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Outcome : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="outcome" name="outcome" style="margin-top: 5px;"
											class="form-control" />
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Sequence Number of Output
											: &nbsp;<span style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="outputSN" name="outputSequenceNumber"
											style="margin-top: 5px;" class="form-control" />
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Output : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="output" name="output" style="margin-top: 5px;"
											class="form-control" />
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Indicator : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="indicator" name="indicator"
											style="margin-top: 5px;" class="form-control" />
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Target : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="target" name="target" style="margin-top: 5px;"
											class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<div class="row">
										<div class="col-xs-4"></div>
										<div class="col-xs-4">
											<input type="submit" id="saveStrategicPillar"
												class="btn btn-primary" value="Save"
												onclick="return submitStrategicPillarForm();" /> <input
												type="button" id="cancel" value="Cancel"
												class="btn btn-primary" />
										</div>
										<div class="col-xs-4"></div>
									</div>
								</div>
							</form:form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>

	<!-- Edit Strategic Pillar Modal -->
	<div class="modal fade bd-example-modal-lg"
		id="editStrategicPillarModal" role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div align="left">
						<c:choose>
							<c:when test="${strategicPillar.id == null}">
								<h4>Add Strategic Pillar</h4>
							</c:when>
							<c:otherwise>
								<h4>Update Strategic Pillar</h4>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="modal-body">
					<div class="container">
						<div>
							<form:form id="editStrategicPillarForm"
								cssClass="form-horizontal" modelAttribute="strategicPillar"
								method="post" action="createStrategicpillar">

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Strategic Pillar Name :
											&nbsp;<span style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input type="hidden" id="strategicPillarId"
											name="strategicPillarId" value="${strategicPillar.id}" /> <input
											id="editStrategicPillarName" name="name"
											style="margin-top: 5px;" class="form-control" />
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Project Name : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="editProjectName" name="project.name"
											style="margin-top: 5px;" readonly="readonly"
											class="form-control" />
									</div>
								</div>

								<div class="form-group">
									<div class="row">
										<div class="col-xs-4"></div>
										<div class="col-xs-4">
											<input type="submit" id="saveStrategicPillar"
												class="btn btn-primary" value="Save"
												onclick="return submitStrategicPillarForm();" /> <input
												type="button" id="cancel" value="Cancel"
												class="btn btn-primary" />
										</div>
										<div class="col-xs-4"></div>
									</div>
								</div>
							</form:form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>

	<!-- Add Theme Modal -->
	<div class="modal fade bd-example-modal-lg" id="addThemeModal"
		role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div align="left">
						<c:choose>
							<c:when test="${theme.id == null}">
								<h2>Add Theme</h2>
							</c:when>
							<c:otherwise>
								<h2>Update Theme</h2>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="modal-body">
					<div class="container">
						<div>
							<form:form id="addThemeForm" cssClass="form-horizontal"
								modelAttribute="addTheme" method="post" action="addTheme">

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Theme Name : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input type="hidden" id="themeId" name="themeId"
											value="${theme.id}" /> <input id="themeName" type="text"
											name="theme" style="margin-top: 5px;" class="form-control" />
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Strategic Pillar : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="themeStrategicPillarName" name="strategicPillar"
											style="margin-top: 5px;" class="form-control"
											readonly="readonly" />
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Sequence Number of
											Outcome : &nbsp;<span style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="outcomeSN" name="outcomeSequenceNumber"
											style="margin-top: 5px;" class="form-control" />
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Outcome : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="outcome" name="outcome" style="margin-top: 5px;"
											class="form-control" />
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Sequence Number of Output
											: &nbsp;<span style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="outputSN" name="outputSequenceNumber"
											style="margin-top: 5px;" class="form-control" />
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Output : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="output" name="output" style="margin-top: 5px;"
											class="form-control" />
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Indicator : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="indicator" name="indicator"
											style="margin-top: 5px;" class="form-control" />
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Target : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="target" name="target" style="margin-top: 5px;"
											class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<div class="row">
										<div class="col-xs-4"></div>
										<div class="col-xs-4">
											<input type="submit" id="saveStrategicPillar"
												class="btn btn-primary" value="Save"
												onclick="return submitStrategicPillarForm();" /> <input
												type="button" id="cancel" value="Cancel"
												class="btn btn-primary" />
										</div>
										<div class="col-xs-4"></div>
									</div>
								</div>
							</form:form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>

	<!-- Edit Theme Modal -->
	<div class="modal fade bd-example-modal-lg" id="editThemeModal"
		role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div align="left">
						<c:choose>
							<c:when test="${theme.id == null}">
								<h2>Add Theme</h2>
							</c:when>
							<c:otherwise>
								<h2>Update Theme</h2>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="modal-body">
					<div class="container">
						<div>
							<form:form id="themeAddform" cssClass="form-horizontal"
								modelAttribute="theme" method="post" action="createTheme">

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Theme Name : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input type="hidden" id="themeId" name="themeId"
											value="${theme.id}" /> <input id="editThemeName" type="text"
											name="name" style="margin-top: 5px;" class="form-control" />
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Strategic Pillar : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="editThemeStrategicPillarName"
											name="strategicPillar.name" style="margin-top: 5px;"
											class="form-control" readonly="readonly" />
									</div>
								</div>
								<div class="form-group">
									<div class="row">
										<div class="col-xs-4"></div>
										<div class="col-xs-4">
											<input type="submit" id="saveTheme" class="btn btn-primary"
												value="Save" onclick="return submitThemeForm();" /> <input
												type="button" id="cancel" value="Cancel"
												class="btn btn-primary" />
										</div>
										<div class="col-xs-4"></div>
									</div>
								</div>
							</form:form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Outcome Modal -->
	<div class="modal fade bd-example-modal-lg" id="outcomeModal"
		role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div align="left">
						<c:choose>
							<c:when test="${outcome.id == null}">
								<h2>Add Outcome</h2>
							</c:when>
							<c:otherwise>
								<h2>Update Outcome</h2>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="modal-body">
					<div class="container">
						<div>
							<form:form id="outcomeAddform" cssClass="form-horizontal"
								modelAttribute="outcome" method="post" action="createOutcome">

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Outcome Name : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input type="hidden" id="outcomeId" name="outcomeId"
											value="${outcome.id}" /> <input id="outcomeName" type="text"
											name="name" style="margin-top: 5px;" class="form-control" />
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Theme : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="outcomeThemeName" name="theme.name"
											style="margin-top: 5px;" class="form-control"
											readonly="readonly" />
									</div>
								</div>
								<div class="form-group">
									<div class="row">
										<div class="col-xs-4"></div>
										<div class="col-xs-4">
											<input type="submit" id="saveOutcome" class="btn btn-primary"
												value="Save" onclick="return submitOutcomeForm();" /> <input
												type="button" id="cancel" value="Cancel"
												class="btn btn-primary" />
										</div>
										<div class="col-xs-4"></div>
									</div>
								</div>
							</form:form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Output Modal -->
	<div class="modal fade bd-example-modal-lg" id="outputModal"
		role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div align="left">
						<c:choose>
							<c:when test="${output.id == null}">
								<h2>Add Output</h2>
							</c:when>
							<c:otherwise>
								<h2>Update Output</h2>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="modal-body">
					<div class="container">
						<div>
							<form:form id="outputAddform" cssClass="form-horizontal"
								modelAttribute="output" method="post" action="createOutput">

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Output : &nbsp;<span
											style="color: red">*</span></label>
									</div>
									<div class="col-xs-8">
										<input type="hidden" id="outputId" name="outputId"
											value="${output.id}" /> <input id="outputName" type="text"
											name="output" style="margin-top: 5px;" class="form-control" />
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Outcome : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="outputOutcomeName" name="outcome.name"
											style="margin-top: 5px;" class="form-control"
											readonly="readonly" />
									</div>
								</div>
								<div class="form-group">
									<div class="row">
										<div class="col-xs-4"></div>
										<div class="col-xs-4">
											<input type="submit" id="saveOutput" class="btn btn-primary"
												value="Save" onclick="return submitOutputForm();" /> <input
												type="button" id="cancel" value="Cancel"
												class="btn btn-primary" />
										</div>
										<div class="col-xs-4"></div>
									</div>
								</div>
							</form:form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Key Activity Modal -->
	<div class="modal fade bd-example-modal-lg" id="keyActivityModal"
		role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div align="left">
						<c:choose>
							<c:when test="${keyActivity.id == null}">
								<h2>Add Key Activity</h2>
							</c:when>
							<c:otherwise>
								<h2>Update Key Activity</h2>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="modal-body">
					<div class="container">
						<div>
							<form:form id="keyActivityAddform" cssClass="form-horizontal"
								modelAttribute="keyActivity" method="post"
								action="createKeyActivity">

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Key Activity : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input type="hidden" id="keyActivityId" name="keyActivityId"
											value="${keyActivity.id}" /> <input id="keyActivityName"
											type="text" name="name" style="margin-top: 5px;"
											class="form-control" />
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Output : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="keyActivityOutputName" name="output.output"
											style="margin-top: 5px;" class="form-control"
											readonly="readonly" />
									</div>
								</div>
								<div class="form-group">
									<div class="row">
										<div class="col-xs-4"></div>
										<div class="col-xs-4">
											<input type="submit" id="saveOutput" class="btn btn-primary"
												value="Save" onclick="return submitKeyActivityForm();" /> <input
												type="button" id="cancel" value="Cancel"
												class="btn btn-primary" />
										</div>
										<div class="col-xs-4"></div>
									</div>
								</div>
							</form:form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>


	<!-- Target Modal -->
	<div class="modal fade bd-example-modal-lg" id="TargetModal"
		role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div align="left">
						<c:choose>
							<c:when test="${target.id == null}">
								<h2>Add Target</h2>
							</c:when>
							<c:otherwise>
								<h2>Update Target</h2>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="modal-body">
					<div class="container">
						<div>
							<form:form id="targetAddform" cssClass="form-horizontal"
								modelAttribute="target" method="post" action="saveTarget">

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Target : &nbsp;<span
											style="color: red">*</span></label>
									</div>
									<div class="col-xs-8">
										<input type="hidden" id="targetId" name="targetId"
											value="${target.id}" />
										<!-- <input id="targetName" type="text" name="message"
											style="margin-top: 5px;" class="form-control" /> -->
										<textarea id="targetName" rows="5" cols="3" name="message"
											style="margin-top: 5px;" class="form-control"></textarea>
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Output : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="targetOutputName" name="output.output"
											style="margin-top: 5px;" class="form-control"
											readonly="readonly" />
									</div>
								</div>
								<div class="form-group">
									<div class="row">
										<div class="col-xs-4"></div>
										<div class="col-xs-4">
											<input type="submit" id="saveOutput" class="btn btn-primary"
												value="Save" onclick="return submitTargetForm();" /> <input
												type="button" id="cancel" value="Cancel"
												class="btn btn-primary" />
										</div>
										<div class="col-xs-4"></div>
									</div>
								</div>
							</form:form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>




	<!-- Sub Activity Modal -->
	<div class="modal fade bd-example-modal-lg" id="subActivityModal"
		role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div align="left">
						<c:choose>
							<c:when test="${subActicity.id == null}">
								<h2>Add Sub Activity</h2>
							</c:when>
							<c:otherwise>
								<h2>Update Sub Activity</h2>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="modal-body">
					<div class="container">
						<div>
							<form:form id="subActivityAddform" cssClass="form-horizontal"
								modelAttribute="subActivity" method="post"
								action="saveSubActivity">

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Sub Activity : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input type="hidden" id="subActivityId" name="id"
											value="${subActivity.id}" /> <input id="subActivityName"
											type="text" name="name" style="margin-top: 5px;"
											class="form-control" />
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Key Activity : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="subKeyActivityName" name="keyActivity.name"
											style="margin-top: 5px;" class="form-control"
											readonly="readonly" />
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Responsible Entity :
											&nbsp;<span style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<select name="leadAgency.name" id="leadAgencyId"
											class="form-control">
											<option value="">---Select Responsible Entity</option>
											<%-- <c:forEach items="${leadAgencies}" var="leadAgency">
														<form:option value="${leadAgency.id}">${leadAgency.name }</form:option>
														<c:choose>
															<c:when test="${state.id eq user.state }">
																<form:option value="${state.id }" selected="selected">${state.stateName }</form:option>
															</c:when>
															<c:otherwise>
																<form:option value="${state.id }">${state.stateName }</form:option>
															</c:otherwise>
														</c:choose>
													</c:forEach> --%>
										</select>
										<!-- <input id="leadAgencyId" name="leadAgency.name" style="margin-top: 5px;" class="form-control" /> -->
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Partner Agency : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<select name="partnerAgencyTypeIds" id="partnerAgencyId"
											class="form-control" multiple="multiple">
											<option value="">---Select Partner Agency</option>
											<%-- <c:forEach items="${partnerAgencies}" var="partnerAgency">
														<form:option value="${partnerAgency.id}">${partnerAgency.name }</form:option>
														<c:choose>
															<c:when test="${state.id eq user.state }">
																<form:option value="${state.id }" selected="selected">${state.stateName }</form:option>
															</c:when>
															<c:otherwise>
																<form:option value="${state.id }">${state.stateName }</form:option>
															</c:otherwise>
														</c:choose>
													</c:forEach> --%>
										</select>
										<!-- <input id="leadAgencyId" name="leadAgency.name" style="margin-top: 5px;" class="form-control" /> -->
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">MOV : &nbsp;<span
											style="color: red">*</span></label>
									</div>
									<div class="col-xs-8">
										<input type="text" name="mov" id="mov"
											style="margin-top: 5px;" class="form-control" />
									</div>
								</div>

								<div class="form-group">
									<div class="row">
										<div class="col-xs-4"></div>
										<div class="col-xs-4">
											<input type="submit" id="saveOutput" class="btn btn-primary"
												value="Save" onclick="return submitSubActivityForm();" /> <input
												type="button" id="cancel" value="Cancel"
												class="btn btn-primary" />
										</div>
										<div class="col-xs-4"></div>
									</div>
								</div>
							</form:form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>


	<!-- Indicator Modal -->
	<div class="modal fade bd-example-modal-lg" id="indicatorModal"
		role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div align="left">
						<c:choose>
							<c:when test="${indicator.id == null}">
								<h2>Add Indicator</h2>
							</c:when>
							<c:otherwise>
								<h2>Update Indicator</h2>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="modal-body">
					<div class="container">
						<div>
							<form:form id="indicatorAddform" cssClass="form-horizontal"
								modelAttribute="indicator" method="post" action="saveIndicator">

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Message : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input type="hidden" id="IndicatorId" name="id"
											value="${indicator.id}" />
										<!-- <input type="text" name="message" style="margin-top: 5px;" class="form-control" /> -->
										<textarea id="indicatorName" rows="5" cols="3" name="message"
											style="margin-top: 5px;" class="form-control"></textarea>
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Output : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="IndicatorOutputName" name="output.output"
											style="margin-top: 5px;" class="form-control"
											readonly="readonly" />
									</div>
								</div>
								<div class="form-group">
									<div class="row">
										<div class="col-xs-4"></div>
										<div class="col-xs-4">
											<input type="submit" id="saveOutput" class="btn btn-primary"
												value="Save" onclick="return submitIndicatorAddform();" />
											<input type="button" id="cancel" value="Cancel"
												class="btn btn-primary" />
										</div>
										<div class="col-xs-4"></div>
									</div>
								</div>
							</form:form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	
	<script src="../static/js/demo-rtl.js"></script>
	<script src="../static/js/jquery.dataTables.js"></script>
	<script src="../static/js/dataTables.tableTools.js"></script>
	<script src="../static/js/jquery.dataTables.bootstrap.js"></script>
	<script src="../static/js/demo-skin-changer.js"></script>
	<script src="../static/js/scripts.js"></script>
	<script src="../static/js/pace.min.js"></script>
	<script type="text/javascript" src="../static/scripts/login.js"></script>
</body>

</html>