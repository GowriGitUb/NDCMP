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
<%@include file="../include/dataTable.jsp"%>
<link
	href='<c:url value="/static/csslib/jquery.dataTables.min.css"></c:url>'>
	<link
	href='<c:url value="/static/csslib/dataTables.colVis.css"></c:url>'>
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
<%@include file="../include/dataTable.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	 var accessView = '${accessRightsView}'
	if(accessView == 'true'){
		$('#tabs > li').removeClass('active');
		$('#viewTab').attr('class','active');
	}else{
		$('#tabs > li').removeClass('active');
		$('#projectTab').attr('class','active');
	} 
	
	$('.accordion a').on('click', function() {
		var _this = $(this);
		var btnId="";
		var str=$(this).context.id.split('_');
		if(str[0] == "strategic"){
			btnId="#strategic-1";
		}else{
		btnId="#"+str[0]+'-'+str[1];
		}
		var _expanded = $(_this).attr('aria-expanded');
		if(_expanded == 'false') {
			//$('.btn .edit .delete').show();
			$(btnId).hide();
		} else if(_expanded == 'true') {
			//$('.btn .edit .delete').hide();
			$(btnId).show();
		}
	});
	
	//To scroll top
	/* $(window).scroll(function(){ 
        if ($(this).scrollTop() > 100) { 
            $('#scroll').fadeIn(); 
        } else { 
            $('#scroll').fadeOut(); 
        } 
    }); 
    $('#scroll').click(function(){ 
        $("html, body").animate({ scrollTop: 0 }, 600); 
        return false; 
    });  */
	
});
</script>
<script type="text/javascript">
	$('.accordion a').on('click', function(){
	    alert($(this).attr("div.id"));
	});
	
	function validateForm() {
		document.getElementById("submitButtonId").disabled = true;

		$('#UploadForm').submit();
	}
	
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
<!-- 
<style type="text/css">
/* BackToTop button css */
#scroll {
    position:fixed;
    right:10px;
    bottom:10px;
    cursor:pointer;
    width:50px;
    height:50px;
    background-color:#3498db;
    text-indent:-9999px;
    display:none;
    -webkit-border-radius:60px;
    -moz-border-radius:60px;
    border-radius:60px
}
#scroll span {
    position:absolute;
    top:50%;
    left:50%;
    margin-left:-8px;
    margin-top:-12px;
    height:0;
    width:0;
    border:8px solid transparent;
    border-bottom-color:#ffffff
}
#scroll:hover {
    background-color:#e74c3c;
    opacity:1;filter:"alpha(opacity=100)";
    -ms-filter:"alpha(opacity=100)";
}
</style>
 -->
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
									<input type="hidden" id="roleId" value="<%= session.getAttribute("roleId") %>" />
									<div class="container">
										<div id="reworkJobLevelStatus"
											style="text-align: center; vertical-align: middle;">
											<img style="height: 250px;"
												src="<c:url value="/static/img/dashboard_loading.gif" />">
										</div>
										<!-- <div>
											<form action="importLocationExcelData" id="UploadForm" method="post"
												enctype="multipart/form-data">
												<label>Select Excel Sheet To Upload</label> <input style="widows: 180px;" type="file" name="file" id="fileId" /><br />
												<input type="button" value="Upload" id="submitButtonId"
													onClick="validateForm();" /><input type="button"
													value="Cancel" onClick="javascript: history.go(-1)" />
											</form>
										</div> -->
										<input type="hidden" id="action" value="${action}">
										<div align="left">
											<h1 class="pull-left" id="projectHead"></h1>
										</div>
										<hr />
										<div class="container">
											<br />
											<div class="row">
											<c:choose>
												<c:when test="${accessRightsView == true }">
													<sec:authorize access="hasRole('PROJECT_ADMIN')">
														<div class="col-sm-12 col-md-12">
													</sec:authorize>
												</c:when>
												<c:otherwise>
													<sec:authorize access="hasRole('PROJECT_ADMIN')">
														<div class="col-sm-10 col-md-10">
													</sec:authorize>
												</c:otherwise>
												</c:choose>
													<sec:authorize access="!hasRole('PROJECT_ADMIN')">
														<div class="col-sm-12 col-md-12">
													</sec:authorize>
												<!-- Div for project admin -->
												<sec:authorize access="hasRole('PROJECT_ADMIN')">
													<div id="projectAdminDiv"></div>
												</sec:authorize>
												<%-- <sec:authorize access="!hasRole('PROJECT_ADMIN')"> --%>
												<div class="panel-group accordion" id="accordion" style="display: none;">
													<c:if test="${not empty strategicPillars }">
														<sec:authorize access="hasRole('PROJECT_ADMIN')">
															<c:choose>
																<c:when test="${accessRightsView == true }">
																</c:when>
																<c:otherwise>
																	<div align="right" style="margin-bottom: 0px;">
																		<button type="button" id="strategic-1"
																			style="width: 140px; margin: 0px -155px -31px 0px;"
																			class="btn btn-primary btn-add-panel"
																			data-toggle="modal"
																			data-target="#addStrategicPillarModal">New
																			Pillar</button>
																	</div>
																</c:otherwise>
															</c:choose>
														</sec:authorize>
														<c:forEach items="${ strategicPillars}" var="strategic">
															<div class="panel panel-default">
																
																<div class="panel-heading">
																	<h4 class="panel-title">
																		<a class="accordion-toggle" data-toggle="collapse" id="strategic_${strategic.id }"
																			data-parent="#accordion"
																			href="#collapseStrategic_${strategic.id }"> 
																			<span><b>${strategic.sequenceNumber}. ${strategic.name}</b></span>
																			<input type="hidden" id="strategicId"
																			value="${strategic.id}" />
																		</a>
																	</h4>
																</div>
																<div id="collapseStrategic_${strategic.id }"
																	class="panel-collapse collapse in strategic-pillar">
																	<div class="panel-body">
																		<div class="panel-group accordion" id="collapseStra-${strategic.id }">
																			<c:if test="${not empty strategic.id }">
																				<sec:authorize access="hasRole('PROJECT_ADMIN')">
																				<div id="themeAdd-${strategic.id}">
																					<c:choose>
																						<c:when test="${accessRightsView == true }"></c:when>
																						<c:otherwise>
																							<div align="right" style="margin-top: -42px;">
																								<a href="#" title="Edit"
																									class="editStrategicPillar"
																									onclick="editStrategicPillar(${strategic.id});"
																									style="margin: 0px -39px -2px 150px;"> <span
																									class="fa-stack"> <i
																										class="fa fa-square fa-stack-2x"></i> <i
																										class="fa fa-pencil fa-stack-1x fa-inverse"></i>
																								</span>
																								</a>
																							</div>
																							<div align="right" style="margin-bottom: 0px;">
																								<button type="button" 
																									style="margin: 0px -155px -2px 0px; width: 140px;"
																									class="btn btn-primary btn-add-panel1"
																									data-toggle="modal" data-target="#addThemeModal">
																									New Theme</button>
																							</div>
																						</c:otherwise>
																					</c:choose>
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
																									data-parent="#collapseStra-${strategic.id }"
																									href="#collapseTheme_${theme.id }" id="themeAdd_${strategic.id }"> <span><b>
																											${theme.name } </b></span>
																								</a>
																							</h4>
																						</div>
																						
																						<div id="collapseTheme_${theme.id }"
																							class="panel-collapse collapse in theme">
																							<div class="panel-body">
																							<div class="panel-group accordion" id="accordion2_${theme.id }">
																								<c:if test="${not empty theme.id }">
																									<sec:authorize
																										access="hasRole('PROJECT_ADMIN')">
																										<div id="outcomeAdd-${theme.id }">
																											<c:choose>
																												<c:when test="${accessRightsView == true }"></c:when>
																												<c:otherwise>
																													<div align="right" style="margin-top: -30px;">
																														<a href="#" title="Edit" class="editTheme"
																															onclick="editTheme(${theme.id});"
																															style="margin: 0px -47px -2px 150px;">
																															<span class="fa-stack"> <i
																																class="fa fa-square fa-stack-2x"></i> <i
																																class="fa fa-pencil fa-stack-1x fa-inverse"></i>
																														</span>
																														</a>
																													</div>
																													<div align="right"
																														style="margin-bottom: 0px;">
																														<button type="button" id="outcomeAdd"
																															style="margin: 0px -155px 0px 0px; width: 140px;"
																															class="btn btn-primary btn-add-panel2"
																															data-toggle="modal"
																															data-target="#addOutcomeModal">New
																															Outcome</button>
																													</div>
																												</c:otherwise>
																											</c:choose>
																										</div>
																									</sec:authorize>
																								</c:if>
																									<c:if test="${not empty theme.outcomes }">
																										<c:forEach items="${ theme.outcomes}"
																											var="outcome">
																											<div class="panel panel-default">
																												<div class="panel-heading"
																													style="background-color: #77818C;">
																													<h4 class="panel-title">
																														<a class="accordion-toggle"
																															data-toggle="collapse" id="outcomeAdd_${theme.id }"
																															data-parent="#accordion2_${theme.id }"
																															href="#collapseOutcome_${outcome.id }">
																															<span><b>${outcome.sequenceNumber }. ${outcome.name } </b></span>
																														</a>
																													</h4>
																												</div>

																												<div id="collapseOutcome_${outcome.id }"
																													class="panel-collapse collapse in outcome">
																													<div class="panel-group accordion" id="collapseOne">
																														<c:if test="${not empty outcome.id }">
																															<sec:authorize
																																access="hasRole('PROJECT_ADMIN')">
																																<div id="outputAdd-${outcome.id }">
																																	<c:choose>
																																		<c:when test="${accessRightsView == true }"></c:when>
																																		<c:otherwise>
																																			<div align="right" style="margin-top: -30px;">
																																				<a href="#" title="Edit" class="editOutcome" onclick="editOutcome(${outcome.id});"
																																					style="margin: 0px -47px -2px 150px;">
																																					<span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i><i class="fa fa-pencil fa-stack-1x fa-inverse"></i>
																																					</span>
																																				</a>
																																			</div>
																																			<div align="right"
																																				style="margin-bottom: 0px;">
																																				<button type="button" id="outputAdd"
																																					style="margin: 4px -157px -3px -3px; width: 140px;"
																																					class="btn btn-primary btn-add-panel3"
																																					data-toggle="modal"
																																					data-target="#addOutputModal">
																																					New Output</button>
																																			</div>
																																		</c:otherwise>
																																	</c:choose>
																																</div>
																															</sec:authorize>
																														</c:if>
																														<div class="panel-group accordion"
																															id="accordion3_${outcome.id }">
																															<c:if
																																test="${not empty outcome.outputs }">
																																<c:forEach items="${ outcome.outputs}"
																																	var="output">
																																	<div
																																		class="panel panel-default template3">
																																		<div class="panel-heading"
																																			style="background-color: #9C9EA0;">
																																			<h4 class="panel-title">
																																				<a class="accordion-toggle"
																																					data-toggle="collapse" id="outputAdd_${outcome.id }_${theme.id}"
																																					data-parent="#accordion3_${outcome.id }"
																																					href="#collapseOutput_${output.id }" onclick="loadKeyActivityByOutput(${output.id })">
																																					<span> <b>${output.sequenceNumber }. ${output.output } </b></span>
																																				</a>
																																			</h4>
																																		</div>
																																		
																																		 <div id="collapseOutput_${output.id }"
																																			class="panel-collapse collapse in output">
																																				<div class="panel-body">
																																					<div id="keyactivityDatas_${output.id}">
																																		
																																					</div>
																																			<%-- <div class="panel-group accordion" id="accordion4_${output.id }" >
																																				<c:if test="${not empty output.id }">
																																					<sec:authorize
																																						access="hasRole('PROJECT_ADMIN')">
																																						<div id="keyActivityAdd">
																																							<c:choose>
																																								<c:when test="${accessRightsView == true }"></c:when>
																																								<c:otherwise>
																																									<div align="right"
																																										style="margin-top: -30px;">
																																										<a href="#" title="Edit"
																																											class="editOutput"
																																											onclick="editOutput(${output.id});"
																																											style="margin: 0px -47px -2px 150px;">
																																											<span class="fa-stack">
																																												<i
																																												class="fa fa-square fa-stack-2x"></i>
																																												<i
																																												class="fa fa-pencil fa-stack-1x fa-inverse"></i>
																																										</span>
																																										</a>
																																									</div>
																																									<div align="right"
																																										style="margin-bottom: 0px;">
																																										<button type="button"
																																											style="margin: 0px -158px 0px 0px; width: 140px;"
																																											class="btn btn-primary btn-add-panel4"
																																											data-toggle="modal"
																																											data-target="#keyActivityModal">
																																											New Key Activity</button>
																																									</div>
																																								</c:otherwise>
																																							</c:choose>
																																						</div>
																																					</sec:authorize>
																																				</c:if>
																																				<div class="panel-group accordion"
																																					id="accordion4">
																																					<c:if
																																						test="${not empty output.keyActivities }">
																																						<div
																																							class="table-responsive dataTables-wrapper"
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
																																										<sec:authorize
																																											access="hasRole('SUPER_ADMIN')">
																																										<th>Actions</th>
																																										</sec:authorize>
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
																																									
																																									<sec:authorize
																																											access="hasRole('PROJECT_PLANNER')">
																																											<c:choose>
																																									<c:when test="${accessRightsView == true }"></c:when>
																																									<c:otherwise>
																																									<tr
																																										style="background-color: #e84e40;">
																																										<th></th>
																																										<th></th>
																																										<th></th>
																																										<th></th>
																																										<th></th>
																																										<th></th>
																																										<th></th>
																																											<c:if
																																												test="${not empty reportingPeriods }">
																																												<c:forEach
																																													items="${reportingPeriods}"
																																													var="reportingPeriod">
																																													<th style="color: #fff"><c:out
																																															value="${reportingPeriod.name }" /></th>
																																												</c:forEach>
																																											</c:if>
																																										</c:forEach>
																																									</tr></c:otherwise></c:choose>
																																									</sec:authorize>
																																								</thead>
																																								<sec:authorize
																																									access="hasRole('PROJECT_PLANNER')">
																																									<div
																																									style="padding: 5px; padding-left: 0px;">
																																									<b>Show/Hide Column</b>
																																									<a class="response toggle-vis"
																																										data-columnindex="3" id="responsible-${output.id}" onclick="overviewPane()">Responsible
																																										Entity</a>- <a
																																										class="partner toggle-vis"
																																										data-columnindex="4" id="partner-${output.id}" onclick="overviewPane()">Partnering
																																										Agency</a>- <a class="toggle-vis"
																																										data-columnindex="5" id="mov-${output.id}" onclick="overviewPane()">MOV</a>
																																								</div>
																																								<c:choose>
																																								<c:when test="${accessRightsView == true }"></c:when>
																																								<c:otherwise>
																																								<div>
																																									<button
																																										class="btn btn-primary pull-right"
																																										id="save-plan" onclick="savePlan()">Save</button>
																																									<br />
																																									<br />
																																								</div></c:otherwise></c:choose>
																																								</sec:authorize>
																																								<c:forEach
																																									items="${ output.keyActivities}"
																																									var="keyActivity">
																																									<tbody>
																																										<tr>
																																											<td><c:out
																																													value="${keyActivity.sequenceNumber }" /></td>
																																											<td><c:out
																																													value="${keyActivity.name}" /></td>
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
																																											<sec:authorize
																																											access="hasRole('SUPER_ADMIN')">
																																											<td><sec:authorize access="hasRole('PROJECT_ADMIN')">
																																								<c:choose>
																																								<c:when test="${accessRightsView == true }"></c:when>
																																								<c:otherwise>
																																											<c:if
																																												test="${not empty keyActivity }">
																																												<a href="#"
																																													style="color: red; text-decoration: none;"
																																													onclick="openSubActivtyModal(${keyActivity.id});"><img
																																													style="height: 24px; margin-top: -2px;"
																																													src="../static/img/add.png"></a>

																																												<a href="#"
																																													title="Edit"
																																													class="editKeyActivity"
																																													onclick="editKeyActivity(${keyActivity.id});">
																																													<span
																																													class="fa-stack">
																																														<i
																																														class="fa fa-square fa-stack-2x"></i>
																																														<i
																																														class="fa fa-pencil fa-stack-1x fa-inverse"></i>
																																												</span>
																																												</a>
																																											</c:if>
																																									</c:otherwise>
																																								</c:choose>
																																													
																																												</sec:authorize></td></sec:authorize>
																																										</tr>
																																										<c:if
																																											test="${not empty keyActivity.subActivities }">
																																											<c:forEach
																																												items="${ keyActivity.subActivities}"
																																												var="subActivity">
																																												<c:if test="${subActivity.status eq 'ACTIVE'}">
																																													<tr>
																																													<td><c:out
																																															value="${subActivity.sequenceNumber }" /></td>
																																													<td></td>
																																													<td>${subActivity.name}</td>
																																													<td>${subActivity.leadAgency.code}</td>
																																													<td>
																																													<c:forEach
																																															items="${subActivity.partnerAgency}"
																																															var="agency">
																																															<c:out
																																																value="${agency.code}" />
																																														</c:forEach></td>
																																													<td>${subActivity.mov}</td>
																																													<sec:authorize
																																											access="hasRole('SUPER_ADMIN')">
																																													<td><sec:authorize access="hasRole('PROJECT_ADMIN')">
																																																<c:choose>
																																																	<c:when test="${accessRightsView == true }"></c:when>
																																																	<c:otherwise>
																																																		<div
																																																			style="margin-top: -30px; margin: 0px 0px -26px -2px;">
																																																			<a href="#"
																																																				title="Edit"
																																																				class="editSubActivity"
																																																				onclick="editSubActivity(${subActivity.id});">
																																																				<span
																																																				class="fa-stack">
																																																					<i
																																																					class="fa fa-square fa-stack-2x"></i>
																																																					<i
																																																					class="fa fa-pencil fa-stack-1x fa-inverse"></i>
																																																			</span>
																																																			</a>
																																																		</div>
																																																	</c:otherwise>
																																																</c:choose>

																																															</sec:authorize>
																																															<sec:authorize
																																															access="hasRole('SUPER_ADMIN')">
																																															<div
																																																style="margin-top: -30px; margin: 0px 0px 0px 28px">
																																																
																																																		<c:choose>
																																																			<c:when
																																																				test="${subActivity.status == 'ACTIVE'}">
																																																				<a href="#"
																																																					class="table-link danger"
																																																					title="Deactivate Sub Activity"
																																																					data-id=${subActivity.id }
																																																					data-toggle="modal"
																																																					data-target="#subActivityDeactiveDialog"
																																																					data-href="deActiveSubActivity?id=${subActivity.id}"
																																																					>
																																																					<span
																																																					class="fa-stack">
																																																						<i
																																																						class="fa fa-square fa-stack-2x"></i>
																																																						<i
																																																						class="fa fa-times fa-stack-1x fa-inverse"></i>
																																																				</span>
																																																				</a>
																																																			</c:when>
																																																			<c:otherwise>
																																																				<a href="#"
																																																					class="table-link"
																																																					title="Activate Sub Activity"
																																																					data-id=${subActivity.id }
																																																					data-toggle="modal"
																																																					data-target="#subActivityActiveDialog"
																																																					data-href="activeSubActivity?id=${subActivity.id}"
																																																					>
																																																					<span
																																																					class="fa-stack">
																																																						<i
																																																						class="fa fa-square fa-stack-2x"></i>
																																																						<i
																																																						class="fa fa-check fa-stack-1x fa-inverse"></i>
																																																				</span>
																																																				</a>
																																																			</c:otherwise>
																																																		</c:choose>
																																																	</div>
																																														</sec:authorize></td></sec:authorize>
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
																																																value="${subActivity.id},${reportingPeriod.id}"onclick="plan(${subActivity.id}, ${reportingPeriod.id })"></input></td>
																																														</c:forEach>
																																													</sec:authorize>
																																												</tr>
																																												</c:if>
																																												
																																											</c:forEach>
																																										</c:if>
																																									</tbody>
																																								</c:forEach>
																																							</table>
																																						</div>
																																					</c:if>
																																				</div>
																																			</div> --%>
																																			</div>
																																			<c:if test="${not empty output.id }">
																																		<sec:authorize
																																			access="hasRole('PROJECT_ADMIN')">
																																			<c:choose>
																																				<c:when test="${accessRightsView == true }"></c:when>
																																				<c:otherwise>
																																					<div align="right"
																																						style="border-bottom : 0px;">
																																						<button type="button" id="indicatorAdd"
																																							style="margin: 0px -158px 0px 0px; width: 140px;"
																																							class="btn btn-primary"
																																							onclick="indicatorModal(${output.id});">
																																							New Indicator</button>
																																					</div>
																																				</c:otherwise>
																																			</c:choose>
																																		</sec:authorize>
																																	</c:if>
																																	<c:if
																																		test="${not empty output.indicators }">
																																	<table>
																																		<tr>
																																			<td><b>Indicators : </b></td>
																																		</tr>
																																		<c:forEach
																																			items="${output.indicators }"
																																			var="indicator">
																																			<sec:authorize
																																				access="hasRole('PROJECT_ADMIN')">
																																				<c:choose>
																																				<c:when test="${accessRightsView == true }"></c:when>
																																				<c:otherwise>
																																					<div align="right"
																																					style="margin-top: -30px;">
																																					<a href="#" title="Edit"
																																						class="editIndicator"
																																						onclick="editIndicator(${indicator.id});"
																																						style="margin: 0px -188px -2px 150px;">
																																						<span class="fa-stack"> <i
																																							class="fa fa-square fa-stack-2x"></i>
																																							<i
																																							class="fa fa-pencil fa-stack-1x fa-inverse"></i>
																																					</span>
																																					</a>
																																				</div>
																																				</c:otherwise>
																																			</c:choose>
																																			</sec:authorize>
																																				<tr>
																																					<td>*&nbsp;<c:out value="${indicator.message}" /></td>
																																				</tr>
																																		</c:forEach>
																																	</table>
																																	</c:if>

																																	<c:if test="${not empty output.id }">
																																		<sec:authorize
																																			access="hasRole('PROJECT_ADMIN')">
																																			<c:choose>
																																				<c:when test="${accessRightsView == true }"></c:when>
																																				<c:otherwise>
																																					<div align="right"
																																						style="margin-bottom: 0px;">
																																						<button type="button" id="targetAdd"
																																							style="margin: 0px -158px 0px 0px; width: 140px;"
																																							class="btn btn-primary"
																																							onclick="targetModal(${output.id});">
																																							New Target</button>
																																					</div>
																																				</c:otherwise>
																																			</c:choose>
																																		</sec:authorize>
																																	</c:if>
																																	<c:if test="${not empty output.targets }">
																																	<table>
																																		<tr>
																																			<td><b>Target : </b></td>
																																		</tr>
																																		<c:forEach items="${output.targets }"
																																			var="target">
																																			<sec:authorize
																																				access="hasRole('PROJECT_ADMIN')">
																																				<c:choose>
																																				<c:when test="${accessRightsView == true }"></c:when>
																																				<c:otherwise>
																																					<div align="right"
																																					style="margin-top: -30px;">
																																					<a href="#" title="Edit"
																																						class="editTarget"
																																						onclick="editTarget(${target.id});"
																																						style="margin: 0px -188px -2px 150px;">
																																						<span class="fa-stack"> <i
																																							class="fa fa-square fa-stack-2x"></i>
																																							<i
																																							class="fa fa-pencil fa-stack-1x fa-inverse"></i>
																																					</span>
																																					</a>
																																				</div>
																																				</c:otherwise>
																																			</c:choose>
																																			</sec:authorize>
																																				<tr>
																																					<td>*&nbsp;<c:out value="${target.message}" /></td>
																																				</tr>
																																		</c:forEach>
																																		</table>
																																	</c:if>
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
	
	<!-- Add Strategic Pillar Modal -->
	<div class="modal fade bd-example-modal-lg"
		id="addStrategicPillarModal" role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div align="left">
							<h4>Add Strategic Pillar</h4>
					</div>
				</div>
				<div class="modal-body">
					<div class="container">
						<div>
							<form:form id="addStrategicPillarForm" cssClass="form-horizontal"
								modelAttribute="addStrategicPillar" >
								
								

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Project Name : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-2">
										<input id="projectName" name="project"
											style="margin-top: 5px;" readonly="readonly"
											class="form-control" />
									</div>
								</div>
								
								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Strategic Pillar :
											&nbsp;<span style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input type="hidden" id="strategicPillarId" 
											name="strategicPillarId" value="${addStrategicPillar.id}" />
										<input id="strategicPillarName" name="strategicPillar" placeholder="Strategic Pillar"
											style="margin-top: 5px;" class="form-control" />
										<span style="color: red"  id="strategicPillarNameError"></span>
									</div>
								</div>
								
								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Sequence Number of
											Strategic Pillar : &nbsp;<span style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-2">
										<input id="pillarSN" name="strategicPillarSequenceNumber" placeholder="Sequence Number"
											style="margin-top: 5px;" class="form-control" />
										<span style="color: red"  id="pillarSNError"></span>
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Theme : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="theme" name="theme" style="margin-top: 5px;" placeholder="Theme"
											class="form-control" />
										<span style="color: red"  id="themeError"></span>
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Sequence Number of
											Outcome : &nbsp;<span style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-2">
										<input id="outcomeSN" name="outcomeSequenceNumber" placeholder="Sequence Number"
											style="margin-top: 5px;" class="form-control" />
										<span style="color: red"  id="outcomeSNError"></span>
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Outcome : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="outcome" name="outcome" style="margin-top: 5px;" placeholder="Outcome"
											class="form-control" />
										<span style="color: red"  id="outcomeError"></span>
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Sequence Number of Output
											: &nbsp;<span style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-2">
										<input id="outputSN" name="outputSequenceNumber" placeholder="Sequence Number"
											style="margin-top: 5px;" class="form-control" />
										<span style="color: red"  id="outputSNError"></span>
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Output : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="output" name="output" style="margin-top: 5px;" placeholder="Output"
											class="form-control" />
										<span style="color: red"  id="outputError"></span>
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Indicator : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="indicator" name="indicator" placeholder="Indicator"
											style="margin-top: 5px;" class="form-control" />
										<span style="color: red"  id="indicatorError"></span>
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Target : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="target" name="target" style="margin-top: 5px;" placeholder="Target"
											class="form-control" />
										<span style="color: red"  id="targetError"></span>
									</div>
								</div>
								<div class="form-group">
									<div class="row">
										<div class="col-xs-4"></div>
										<div class="col-xs-4">
											<input type="button" id="saveStrategicPillar"
												class="btn btn-primary" value="Save"
												onclick="addStrategicPillar()" /> 
												<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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
								<h4>Update Strategic Pillar</h4>
					</div>
				</div>
				<div class="modal-body">
					<div class="container">
						<div>
							<form:form id="editStrategicPillarForm"
								cssClass="form-horizontal" modelAttribute="strategicPillar">

								

								<div class="form-group">
									<div class="control-label col-xs-3">
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
									<div class="control-label col-xs-3">
										<label cssClass="labelColor">Strategic Pillar Name :
											&nbsp;<span style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-4">
										<input type="hidden" id="editStrategicPillarId"
											name="id" value="${strategicPillar.id}" /> <input
											id="editStrategicPillarName" name="name"
											style="margin-top: 5px;" class="form-control" />
										<span style="color: red"  id="strategicNameError"></span>
										
									</div>
								</div>

								<div class="form-group">
									<div class="row">
										<div class="col-xs-4"></div>
										<div class="col-xs-4">
											<input type="button" id="saveStrategicPillar"
												class="btn btn-primary" value="Save"
												onclick="updateStrategicPillar()" /><!--  <input
												type="button" id="cancel" value="Cancel"
												class="btn btn-primary" /> -->
												<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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
							<h4>Add Theme</h4>
					</div>
				</div>
				<div class="modal-body">
					<div class="container">
						<div>
							<form:form id="addThemeForm" cssClass="form-horizontal"
								modelAttribute="addTheme">

								

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Strategic Pillar : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input type="hidden" id="pillarId">
										<input id="themeStrategicPillarName" name="strategicPillar" placeholder="Strategic Pillar"
											style="margin-top: 5px;" class="form-control"
											readonly="readonly" />
									</div>
								</div>
								
								
								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Theme : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input type="hidden" id="themeId" name="themeId" 
											value="${theme.id}" /> <input id="themeName" type="text" placeholder="Theme"
											name="theme" style="margin-top: 5px;" class="form-control" />
										<span style="color: red"  id="themeNameError"></span>
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Sequence Number of
											Outcome : &nbsp;<span style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-2">
										<input id="outcomeSeqN" name="outcomeSequenceNumber" placeholder="Sequence Number"
											style="margin-top: 5px;" class="form-control" />
										<span style="color: red"  id="outcomeSeqNError"></span>
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Outcome : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="theme_outcome" name="outcome" style="margin-top: 5px;" placeholder="Outcome"
											class="form-control" />
										<span style="color: red"  id="outcmError"></span>
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Sequence Number of Output
											: &nbsp;<span style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-2">
										<input id="outputSeqN" name="outputSequenceNumber" placeholder="Sequence Number"
											style="margin-top: 5px;" class="form-control" />
										<span style="color: red"  id="outputSeqNError"></span>
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Output : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="theme_output" name="output" style="margin-top: 5px;" placeholder="Output"
											class="form-control" />
											<span style="color: red"  id="outptError"></span>
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Indicator : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="theme_indicator" name="indicator" placeholder="Indicator"
											style="margin-top: 5px;" class="form-control" />
										<span style="color: red"  id="indError"></span>
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Target : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="theme_target" name="target" style="margin-top: 5px;" placeholder="Target"
											class="form-control" />
										<span style="color: red"  id="trgError"></span>
									</div>
								</div>
								<div class="form-group">
									<div class="row">
										<div class="col-xs-4"></div>
										<div class="col-xs-4">
											<input type="button" id="saveStrategicPillar"
												class="btn btn-primary" value="Save"
												onclick="saveTheme()" /> <!-- <input
												type="button" id="cancel" value="Cancel"
												class="btn btn-primary" /> -->
												<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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
							<h4>Update Theme</h4>
					</div>
				</div>
				<div class="modal-body">
					<div class="container">
						<div>
							<form:form id="themeAddform" cssClass="form-horizontal"
								modelAttribute="theme">

								

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Strategic Pillar : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
									<input type="hidden" id="pillarid"/> 
										<input id="editThemeStrategicPillarName"
											name="strategicPillar.name" style="margin-top: 5px;"
											class="form-control" readonly="readonly" />
									</div>
								</div>
								
								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Theme Name : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input type="hidden" id="themeid" name="themeid"
											value="${theme.id}" /> <input id="editThemeName" type="text"
											name="name" style="margin-top: 5px;" class="form-control" />
										<span style="color: red"  id="editThemeNameError"></span>
									</div>
								</div>
								
								<div class="form-group">
									<div class="row">
										<div class="col-xs-4"></div>
										<div class="col-xs-4">
											<input type="button" id="saveTheme" class="btn btn-primary"
												value="Save" onclick="updateTheme()" />
												<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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
	</div>

	<!-- Add Outcome Modal -->
	<div class="modal fade bd-example-modal-lg" id="addOutcomeModal"
		role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div align="left">
							<h4>Add Outcome</h4>
					</div>
				</div>
				<div class="modal-body">
					<div class="container">
						<div>
							<form:form id="addThemeForm" cssClass="form-horizontal"
								modelAttribute="addOutcome">

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Theme : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input type="hidden" id="thmId">
										<input id="outcomeThemeName" name="theme" placeholder="Theme"
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
										<input id="outcoSen" name="outcomeSequenceNumber" placeholder="Sequence Number"
											style="margin-top: 5px;" class="form-control" />
										<span style="color: red"  id="outcoSenError"></span>
									</div>
								</div>
								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Outcome : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input type="hidden" id="outcomeId" name="outcomeId"
											value="${outcome.id}" /> <input id="outcmen" type="text" placeholder="Outcome"
											name="outcome" style="margin-top: 5px;" class="form-control" />
										<span style="color: red"  id="outcmenError"></span>
									</div>
								</div>
								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Sequence Number of Output
											: &nbsp;<span style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="outSen" name="outputSequenceNumber" placeholder="Sequence Number"
											style="margin-top: 5px;" class="form-control" />
										<span style="color: red"  id="outSenError"></span>
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Output : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="outputn" name="output" style="margin-top: 5px;" placeholder="Output"
											class="form-control" />
										<span style="color: red"  id="outputnError"></span>
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Indicator : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="indicatorname" name="indicator" placeholder="Indicator"
											style="margin-top: 5px;" class="form-control" />
										<span style="color: red"  id="indicatornameError"></span>
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Target : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="targetname" name="target" style="margin-top: 5px;" placeholder="Target"
											class="form-control" />
										<span style="color: red"  id="targetnameError"></span>
									</div>
								</div>
								<div class="form-group">
									<div class="row">
										<div class="col-xs-4"></div>
										<div class="col-xs-4">
											<input type="button" id="saveOutcome"
												class="btn btn-primary" value="Save"
												onclick="return saveOutCome()" /> <!-- <input
												type="button" id="cancel" value="Cancel"
												class="btn btn-primary" /> -->
												<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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
	</div>

	<!-- Edit Outcome Modal -->
	<div class="modal fade bd-example-modal-lg" id="editOutcomeModal"
		role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div align="left">
							<h4>Update Outcome</h4>
					</div>
				</div>
				<div class="modal-body">
					<div class="container">
						<div>
							<form:form id="outcomeAddform" cssClass="form-horizontal"
								modelAttribute="outcome" >


								<div class="form-group">
									<div class="control-label col-xs-3">
										<label cssClass="labelColor">Theme : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="editOutcomeThemeName" name="theme.name"
											style="margin-top: 5px;" class="form-control"
											readonly="readonly" />
										<input type="hidden" id="themId">
									</div>
								</div>
								
								<div class="form-group">
									<div class="control-label col-xs-3">
										<label cssClass="labelColor">Outcome Name : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input type="hidden" id="outCOMEId" name="outCOMEId"
											value="${outcome.id}" /> <input id="outNAME" type="text"
											name="name" style="margin-top: 5px;" class="form-control" />
											<span style="color: red" id="outNAMEError"></span>
									</div>
								</div>

								
								<div class="form-group">
									<div class="row">
										<div class="col-xs-4"></div>
										<div class="col-xs-4">
											<input type="button" id="saveOutcome" class="btn btn-primary"
												value="Save" onclick="updateOutcome()" /><!--  <input
												type="button" id="cancel" value="Cancel"
												class="btn btn-primary" /> -->
												<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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
	</div>

	<!-- Add Output Modal -->
	<div class="modal fade bd-example-modal-lg" id="addOutputModal"
		role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div align="left">
							<h4>Add Output</h4>
					</div>
				</div>
				<div class="modal-body">
					<div class="container">
						<div>
							<form:form id="addOutputForm" cssClass="form-horizontal"
								modelAttribute="addOutput">
								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Outcome : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
									<input type="hidden" id="OUTCOMEId" />
										<input id="outputOutcomeName" name="outcome" placeholder="Outcome"
											style="margin-top: 5px;" class="form-control"
											readonly="readonly" />
										<span style="color: red" id="outputOutcomeNameError"></span>
									</div>
								</div>
								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Sequence Number of Output
											: &nbsp;<span style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="outputSequenceNumber" name="outputSequenceNumber" placeholder="Sequence Number"
											style="margin-top: 5px;" class="form-control" />
										<span style="color: red" id="outputSequenceNumberError"></span>
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Output : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input type="hidden" id="OUTPUTId" />
										<input id="OUTPUTNAME" name="output" style="margin-top: 5px;" placeholder="Output"
											class="form-control" />
										<span style="color: red" id="OUTPUTNAMEError"></span>
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Indicator : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="INDICATOR" name="indicator" placeholder="Indicator"
											style="margin-top: 5px;" class="form-control" />
											<span style="color: red" id="INDICATORError"></span>
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Target : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input id="TARGET" name="target" style="margin-top: 5px;" placeholder="Target"
											class="form-control" />
										<span style="color: red" id="TARGETError"></span>
									</div>
								</div>
								<div class="form-group">
									<div class="row">
										<div class="col-xs-4"></div>
										<div class="col-xs-4">
											<input type="button" id="saveOutput" class="btn btn-primary"
												value="Save" onclick="addOutput()" /> <!-- <input
												type="button" id="cancel" value="Cancel"
												class="btn btn-primary" /> -->
												<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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
	</div>

	<!-- Edit Output Modal -->
	<div class="modal fade bd-example-modal-lg" id="editOutputModal"
		role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div align="left">
							<h4>Update Output</h4>
					</div>
				</div>
				<div class="modal-body">
					<div class="container">
						<div>
							<form:form id="outputAddform" cssClass="form-horizontal"
								modelAttribute="output" >


								<div class="form-group">
									<div class="control-label col-xs-3">
										<label cssClass="labelColor">Outcome : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input type="hidden" id="editOutputOutcomeId">
										<input id="editOutputOutcomeName" name="outcome.name"
											style="margin-top: 5px;" class="form-control"
											readonly="readonly" />
									</div>
								</div>
								
								
								<div class="form-group">
									<div class="control-label col-xs-3">
										<label cssClass="labelColor">Output : &nbsp;<span
											style="color: red">*</span></label>
									</div>
									<div class="col-xs-8">
										<input type="hidden" id="outpId" name="outpId"
											value="${output.id}" /> <input id="editOutputname" type="text"
											name="output" style="margin-top: 5px;" class="form-control" />
										<span style="color: red" id="editOutcError"></span>
									</div>
								</div>
								
								<div class="form-group">
									<div class="row">
										<div class="col-xs-4"></div>
										<div class="col-xs-4">
											<input type="button" id="saveOutput" class="btn btn-primary"
												value="Save" onclick="updateOutput()" /> <!-- <input
												type="button" id="cancel" value="Cancel"
												class="btn btn-primary" /> -->
												<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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
							<h4 id="add_Key_Activity">Add Key Activity</h4>
							<h4 id="update_Key_Activity">Update Key Activity</h4>
					</div>
				</div>
				<div class="modal-body">
					<div class="container">
						<div>
							<form:form id="keyActivityAddform" cssClass="form-horizontal"
								modelAttribute="keyActivity" method="post" >

								<div class="form-group">
									<div class="control-label col-xs-3">
										<label cssClass="labelColor">Output : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
									<form:hidden path="output.id" id="outputKeyActivityId" />
										<input type="text" style="margin-top: 5px;" class="form-control" id="keyActivityOutputName" readonly="readonly" >
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-3">
										<label cssClass="labelColor">Key Activity : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input type="hidden" id="keyActivityId" name="keyActivityId"
											value="${keyActivity.id}" /> <input id="keyActivityName" placeholder="KeyActivity"
											type="text" name="name" style="margin-top: 5px;"
											class="form-control" />
											<span style="color: red"  id="keyActNameError"></span>
									</div>
								</div>

								
								<div class="form-group">
									<div class="row">
										<div class="col-xs-4"></div>
										<div class="col-xs-4">
											<input type="button" id="saveOutput" class="btn btn-primary"
												value="Save" onclick="saveKeyActivity()" /> <!-- <input
												type="button" id="cancel" value="Cancel"
												class="btn btn-primary" /> -->
												<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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
							<h4 id="add_Target">Add Target</h4>
							<h4 id="update_Target">Update Target</h4>
					</div>
				</div>
				<div class="modal-body">
					<div class="container">
						<div>
							<form:form id="targetAddform" cssClass="form-horizontal"
								modelAttribute="target">

							
								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Output : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
									<form:hidden path="output.id" id="outputTargetId" />
										<input type="text" style="margin-top: 5px;" class="form-control" id="targetOutputName" readonly="readonly" >
									</div>
								</div>
								
									<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Target : &nbsp;<span
											style="color: red">*</span></label>
									</div>
									<div class="col-xs-8">
										<input type="hidden" id="targetId" name="targetId"
											value="${target.id}" />
										<textarea id="targetName" rows="5" cols="3" name="message" placeholder="Target"
											style="margin-top: 5px;" class="form-control"></textarea>
											<span style="color: red"  id="targetError"></span>
									</div>
								</div>
								
								
								<div class="form-group">
									<div class="row">
										<div class="col-xs-4"></div>
										<div class="col-xs-4">
											<input type="button" id="saveTarget" class="btn btn-primary"
												value="Save" onclick="save_Target()" />
												<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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
							<h4 id="add_Sub_Activity">Add Sub Activity</h4>
							<h4 id="update_Sub_Activity">Update Sub Activity</h4>
					</div>
				</div>
				<div class="modal-body">
					<div class="container">
						<div>
							<form:form id="subActivityAddform" cssClass="form-horizontal"
								modelAttribute="subActivity" method="post">
								
								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Key Activity : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<form:hidden path="keyActivity.id" id="key" />
										<input type="text" style="margin-top: 5px;" class="form-control" id="subKeyActivityName" readonly="readonly" >
									</div>
								</div>
								
								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Sub Activity : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input type="hidden" id="subActivityId" name="id"
											value="${subActivity.id}" /> <input id="subActivityName" placeholder="SubActivity"
											type="text" name="name" style="margin-top: 5px;"
											class="form-control" />
											<span style="color: red"  id="subActNameError"></span>
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
										</select>
										<span style="color: red"  id="leadAgencyError"></span>
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
										</select>
										<span style="color: red"  id="partnerAgencyError"></span>
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">MOV : &nbsp;<span
											style="color: red">*</span></label>
									</div>
									<div class="col-xs-8">
										<input type="text" name="mov" id="mov" placeholder="Mov"
											style="margin-top: 5px;" class="form-control" />
											<span style="color: red"  id="movError"></span>
									</div>
								</div>

								<div class="form-group">
									<div class="row">
										<div class="col-xs-4"></div>
										<div class="col-xs-4">
											<input type="button" id="saveOutput" class="btn btn-primary"
												value="Save" onclick="saveSubActivity();" /> 
												<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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
							<h4 id="add_Indicator">Add Indicator</h4>
							<h4 id="update_Indicator">Update Indicator</h4>
					</div>
				</div>
				<div class="modal-body">
					<div class="container">
						<div>
							<form:form id="indicatorAddform" cssClass="form-horizontal"
								modelAttribute="indicator">
								
								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Output : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
									<form:hidden path="output.id" id="outputIndicatorId" />
										<input type="text" style="margin-top: 5px;" class="form-control" id="IndicatorOutputName" readonly="readonly" >
									</div>
								</div>

								<div class="form-group">
									<div class="control-label col-xs-4">
										<label cssClass="labelColor">Message : &nbsp;<span
											style="color: red">*</span>
										</label>
									</div>
									<div class="col-xs-8">
										<input type="hidden" id="IndicatorId" name="id"
											value="${indicator.id}" />
										<textarea id="indicatorName" rows="5" cols="3" name="message" placeholder="Message"
											style="margin-top: 5px;" class="form-control"></textarea>
										<span style="color: red"  id="msgError"></span>
									</div>
								</div>

								
								<div class="form-group">
									<div class="row">
										<div class="col-xs-4"></div>
										<div class="col-xs-4">
											<input type="button" id="saveOutput" class="btn btn-primary"
												value="Save" onclick="saveIndicator()"/>
												<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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
	</div>
	
	<!-- Strategic Pillar Modal window for activation -->
	<div id="strategicActiveDialog" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Strategic Pillar Activation</h4>
				</div>
				<div class="modal-body" id="strategicActive"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a class="btn btn-success btn-ok">Activate</a>
				</div>
			</div>

		</div>
	</div>
	
	<!-- Strategic Pillar Modal window for de-activation -->
	<div id="strategicDeactiveDialog" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Strategic Pillar Deactivation</h4>
				</div>
				<div class="modal-body" id="strategicDeactive"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a class="btn btn-danger btn-ok">Deactivate</a>
				</div>
			</div>

		</div>
	</div>
	
	<!-- Theme Modal window for activation -->
	<div id="themeActiveDialog" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Theme Activation</h4>
				</div>
				<div class="modal-body" id="themeActive"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a class="btn btn-success btn-ok">Activate</a>
				</div>
			</div>

		</div>
	</div>
	
	<!-- Theme Modal window for de-activation -->
	<div id="themeDeactiveDialog" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Theme Deactivation</h4>
				</div>
				<div class="modal-body" id="themeDeactive"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a class="btn btn-danger btn-ok">Deactivate</a>
				</div>
			</div>

		</div>
	</div>
	
	<!-- Outcome Modal window for activation -->
	<div id="outcomeActiveDialog" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Outcome Activation</h4>
				</div>
				<div class="modal-body" id="outcomeActive"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a class="btn btn-success btn-ok">Activate</a>
				</div>
			</div>

		</div>
	</div>
	
	<!-- Outcome Modal window for de-activation -->
	<div id="outcomeDeactiveDialog" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Outcome DeActivation</h4>
				</div>
				<div class="modal-body" id="outcomeDeactive"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a class="btn btn-danger btn-ok">DeActive</a>
				</div>
			</div>

		</div>
	</div>
	
	<!-- Output Modal window for activation -->
	<div id="outputActiveDialog" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Output Activation</h4>
				</div>
				<div class="modal-body" id="outputActive"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a class="btn btn-success btn-ok">Activate</a>
				</div>
			</div>

		</div>
	</div>
	
	<!-- Output Modal window for de-activation -->
	<div id="outputDeactiveDialog" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Output DeActivation</h4>
				</div>
				<div class="modal-body" id="outputDeactive"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a class="btn btn-danger btn-ok">DeActivate</a>
				</div>
			</div>

		</div>
	</div>
	
	<!-- Key Activity Modal window for activation -->
	<div id="keyActivityActiveDialog" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Key Activity Activation</h4>
				</div>
				<div class="modal-body" id="keyActivityActive"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a class="btn btn-success btn-ok">Activate</a>
				</div>
			</div>

		</div>
	</div>
	
	<!-- Key Activity Modal window for de-activation -->
	<div id="keyActivityDeactiveDialog" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Key Activity DeActivation</h4>
				</div>
				<div class="modal-body" id="keyActivityDeactive"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a class="btn btn-danger btn-ok">Deactivate</a>
				</div>
			</div>

		</div>
	</div>
	
	<!-- sub Activity Modal window for activation -->
	<div id="subActivityActiveDialog" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Sub Activity Activation</h4>
				</div>
				<div class="modal-body" id="subActivityActive"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a class="btn btn-success btn-ok" onclick="actDeactSubAct()">Activate</a>
				</div>
			</div>

		</div>
	</div>
	
	<!-- Sub Activity Modal window for de-activation -->
	<div id="subActivityDeactiveDialog" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Sub Activity DeActivation</h4>
				</div>
				<div class="modal-body" id="subActivityDeactive"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a class="btn btn-danger btn-ok" onclick="actDeactSubAct()">Deactivate</a>
				</div>
			</div>

		</div>
	</div>
	
	<!-- Indicator Modal window for activation -->
	<div id="indicatorActiveDialog" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Indicator Activation</h4>
				</div>
				<div class="modal-body" id="indicatorActive"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a class="btn btn-success btn-ok">Activate</a>
				</div>
			</div>

		</div>
	</div>
	
	<!-- Indicator Modal window for de-activation -->
	<div id="indicatorDeactiveDialog" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Indicator DeActivation</h4>
				</div>
				<div class="modal-body" id="indicatorDeactive"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a class="btn btn-danger btn-ok">DeActivate</a>
				</div>
			</div>

		</div>
	</div>
	
	<!-- Target Modal window for activation -->
	<div id="targetActiveDialog" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Target Activation</h4>
				</div>
				<div class="modal-body" id="targetActive"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a class="btn btn-success btn-ok">Activate</a>
				</div>
			</div>

		</div>
	</div>
	
	<!-- Target Modal window for de-activation -->
	<div id="targetDeactiveDialog" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Target DeActivation</h4>
				</div>
				<div class="modal-body" id="targetDeactive"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a class="btn btn-danger btn-ok">DeActivate</a>
				</div>
			</div>

		</div>
	</div>

<!-- BackToTop Button -->
	<a href="javascript:void(0);" id="scroll" title="Scroll to Top" style="display: none;">Top<span></span></a>
	
	<script src="../static/js/demo-rtl.js"></script>
	<script src='<c:url value="/static/js/jquery.dataTables.js"></c:url>'></script>
	<script src="../static/js/dataTables.tableTools.js"></script>
	<script src="../static/js/jquery.dataTables.bootstrap.js"></script>
	<script src="../static/js/demo-skin-changer.js"></script>
	<script src="../static/js/scripts.js"></script>
	<script src="../static/js/pace.min.js"></script>
	<script type="text/javascript" src="../static/scripts/login.js"></script>
</body>

</html>