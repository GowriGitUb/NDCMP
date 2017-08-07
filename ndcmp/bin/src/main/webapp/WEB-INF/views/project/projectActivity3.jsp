<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../include/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NDCMP</title>
<%@include file="../include/commonlib.jsp"%>
<script type="text/javascript" src="../static/jslib/bootstrap.min.js"></script>
<%@include file="../include/dataTable.jsp"%>
<style>
.panel-default>.panel-heading {
	/* background-color: #03a9f4;
	border-color: #03a9f4; */
	background-color: #2C3E50;
	border-color: #2C3E50 color:     #FFFFFF;
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
<script type="text/javascript">
$(window).load(function(){
	   // PAGE IS FULLY LOADED  
	   // FADE OUT YOUR OVERLAYING DIV
	   $('#reworkJobLevelStatus').fadeOut();
	});
</script>


</head>
<body onload="advancedTable()">
	<%@include file="../include/menu.jsp"%>
	<div class="container">
	<div id="reworkJobLevelStatus" style="min-width: 1010px; height: 1000px; max-width: 1000px;text-align: center; vertical-align: middle;"><img style="height: 650px;" src="<c:url value="/static/images/dashboard_loading.gif" />"></div>
		<div align="left">
			<b>Project Activity List</b>
		</div>
		<hr />
		<div class="container">
		<div align="right">
			<button type="button" style="margin-bottom: -20px" class="btn btn-primary btn-add-panel"
				data-toggle="modal" data-target="#strategicPillarModal">
				<i class="glyphicon glyphicon-plus"></i>Add new Pillar
			</button>
		</div>
			<!-- Strategic Pillar Modal -->
			<div class="modal fade" id="strategicPillarModal" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<div align="left">
								<c:choose>
									<c:when test="${strategicPillar.id == null}">
										<h4>Add StrategicPillar</h4>
									</c:when>
									<c:otherwise>
										<h4>Update StrategicPillar</h4>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="modal-body">
							<div class="container">
								<div>
									<form:form id="addStrategicPillarForm"
										cssClass="form-horizontal" modelAttribute="strategicPillar"
										method="post" action="createStrategicpillar">

										<div class="form-group">
											<div class="control-label col-xs-4">
												<label>StrategicPillar Name :
													&nbsp;<span style="color: red">*</span>
												</label>
											</div>
											<div class="col-xs-4">
												<input type="hidden" id="strategicPillarId"
													name="strategicPillarId" value="${strategicPillar.id}" />
												<form:input type="text" path="name" style="margin-top: 5px;"
													class="form-control" />
											</div>
										</div>

										<div class="form-group">
											<div class="control-label col-xs-4">
												<label cssClass="labelColor">Project Name : &nbsp;<span
													style="color: red">*</span></label>
											</div>
											<div class="col-xs-4">
												<input id="projectName" name="project.name"
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
														onclick="return submitStrategicPillarForm();"
														 /> <input type="button"
														id="cancel" value="Cancel" class="btn btn-primary" />
												</div>
												<div class="col-xs-4"></div>
											</div>
										</div>
									</form:form>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>

				</div>
			</div>

			<!-- Theme Modal -->
			<div class="modal fade" id="themeModal" role="dialog">
				<div class="modal-dialog">

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
													style="color: red">*</span></label>
											</div>
											<div class="col-xs-4">
												<input type="hidden" id="themeId" name="themeId"
													value="${theme.id}" /> <input type="text" name="name"
													style="margin-top: 5px;" class="form-control" />
											</div>
										</div>

										<div class="form-group">
											<div class="control-label col-xs-4">
												<label cssClass="labelColor">Strategic Pillar :
													&nbsp;<span style="color: red">*</span>
												</label>
											</div>
											<div class="col-xs-4">
												<input id="strategicPillarName" name="strategicPillar.name"
													style="margin-top: 5px;" class="form-control"
													readonly="readonly" />
											</div>
										</div>
										<div class="form-group">
											<div class="row">
												<div class="col-xs-4"></div>
												<div class="col-xs-4">
													<input type="submit" id="saveTheme" class="btn btn-primary"
														value="Save" onclick="return submitThemeForm();"
														 /> <input type="button"
														id="cancel" value="Cancel" class="btn btn-primary" />
												</div>
												<div class="col-xs-4"></div>
											</div>
										</div>
									</form:form>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>

			<!-- Outcome Modal -->
			<div class="modal fade" id="outcomeModal" role="dialog">
				<div class="modal-dialog">

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
													style="color: red">*</span></label>
											</div>
											<div class="col-xs-4">
												<input type="hidden" id="outcomeId" name="outcomeId"
													value="${outcome.id}" /> <input type="text" name="name"
													style="margin-top: 5px;" class="form-control" />
											</div>
										</div>

										<div class="form-group">
											<div class="control-label col-xs-4">
												<label cssClass="labelColor">Theme : &nbsp;<span
													style="color: red">*</span>
												</label>
											</div>
											<div class="col-xs-4">
												<input id="themeName" name="theme.name"
													style="margin-top: 5px;" class="form-control"
													readonly="readonly" />
											</div>
										</div>
										<div class="form-group">
											<div class="row">
												<div class="col-xs-4"></div>
												<div class="col-xs-4">
													<input type="submit" id="saveOutcome"
														class="btn btn-primary" value="Save"
														onclick="return submitOutcomeForm();"
														 /> <input type="button"
														id="cancel" value="Cancel" class="btn btn-primary" />
												</div>
												<div class="col-xs-4"></div>
											</div>
										</div>
									</form:form>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>

			<!-- Output Modal -->
			<div class="modal fade" id="outputModal" role="dialog">
				<div class="modal-dialog">

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
												<label cssClass="labelColor">OutPut : &nbsp;<span
													style="color: red">*</span></label>
											</div>
											<div class="col-xs-4">
												<input type="hidden" id="outputId" name="outputId"
													value="${output.id}" /> <input type="text" name="output"
													style="margin-top: 5px;" class="form-control" />
											</div>
										</div>

										<div class="form-group">
											<div class="control-label col-xs-4">
												<label cssClass="labelColor">Outcome : &nbsp;<span
													style="color: red">*</span>
												</label>
											</div>
											<div class="col-xs-4">
												<input id="outcomeName" name="outcome.name"
													style="margin-top: 5px;" class="form-control"
													readonly="readonly" />
											</div>
										</div>
										<div class="form-group">
											<div class="row">
												<div class="col-xs-4"></div>
												<div class="col-xs-4">
													<input type="submit" id="saveOutput"
														class="btn btn-primary" value="Save"
														onclick="return submitOutputForm();"
														 /> <input type="button"
														id="cancel" value="Cancel" class="btn btn-primary" />
												</div>
												<div class="col-xs-4"></div>
											</div>
										</div>
									</form:form>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>

			<!-- Key Activity Modal -->
			<div class="modal fade" id="keyActivityModal" role="dialog">
				<div class="modal-dialog">

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
													style="color: red">*</span></label>
											</div>
											<div class="col-xs-4">
												<input type="hidden" id="keyActivityId" name="keyActivityId"
													value="${keyActivity.id}" /> <input type="text"
													name="name" style="margin-top: 5px;" class="form-control" />
											</div>
										</div>

										<div class="form-group">
											<div class="control-label col-xs-4">
												<label cssClass="labelColor">Output : &nbsp;<span
													style="color: red">*</span>
												</label>
											</div>
											<div class="col-xs-4">
												<input id="outputName" name="output.output"
													style="margin-top: 5px;" class="form-control"
													readonly="readonly" />
											</div>
										</div>
										<div class="form-group">
											<div class="row">
												<div class="col-xs-4"></div>
												<div class="col-xs-4">
													<input type="submit" id="saveOutput"
														class="btn btn-primary" value="Save"
														onclick="return submitKeyActivityForm();"
														 /> <input type="button"
														id="cancel" value="Cancel" class="btn btn-primary" />
												</div>
												<div class="col-xs-4"></div>
											</div>
										</div>
									</form:form>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>


			<!-- Target Modal -->
			<div class="modal fade" id="TargetModal" role="dialog">
				<div class="modal-dialog">

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
											<div class="col-xs-4">
												<input type="hidden" id="targetId" name="targetId"
													value="${target.id}" /> <input type="text " name="message"
													style="margin-top: 5px;" class="form-control" />
											</div>
										</div>

										<div class="form-group">
											<div class="control-label col-xs-4">
												<label cssClass="labelColor">Output : &nbsp;<span
													style="color: red">*</span>
												</label>
											</div>
											<div class="col-xs-4">
												<input id="outName" name="output.output"
													style="margin-top: 5px;" class="form-control"
													readonly="readonly" />
											</div>
										</div>
										<div class="form-group">
											<div class="row">
												<div class="col-xs-4"></div>
												<div class="col-xs-4">
													<input type="submit" id="saveOutput"
														class="btn btn-primary" value="Save"
														onclick="return submitKeyActivityForm();"
														 /> <input type="button"
														id="cancel" value="Cancel" class="btn btn-primary" />
												</div>
												<div class="col-xs-4"></div>
											</div>
										</div>
									</form:form>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>




			<!-- Sub Activity Modal -->
			<div class="modal fade" id="subActivityModal" role="dialog">
				<div class="modal-dialog">

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
													style="color: red">*</span></label>
											</div>
											<div class="col-xs-4">
												<input type="hidden" id="subActivityId" name="id" value="${subActivity.id}" />
												 <input type="text" name="name" style="margin-top: 5px;" class="form-control" />
											</div>
										</div>
										
										<div class="form-group">
											<div class="control-label col-xs-4">
												<label cssClass="labelColor">Key Activity : &nbsp;<span
													style="color: red">*</span></label>
											</div>
											<div class="col-xs-4">
												<input id="keyActivityName" name="keyActivity.name" style="margin-top: 5px;" class="form-control" readonly="readonly" />
											</div>
										</div>

										<div class="form-group">
											<div class="control-label col-xs-4">
												<label cssClass="labelColor">Responsible Entity :
													&nbsp;<span style="color: red">*</span>
												</label>
											</div>
											<div class="col-xs-4">
												<select name="leadAgency.name" id="leadAgencyId" class="form-control">
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
											<div class="col-xs-4">
												<select name="partnerAgencyTypeIds" id="partnerAgencyId" class="form-control" multiple="multiple">
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
											<div class="col-xs-4">
												<input type="text" name="mov" id="mov"
													style="margin-top: 5px;" class="form-control" />
											</div>
										</div>

										<div class="form-group">
											<div class="row">
												<div class="col-xs-4"></div>
												<div class="col-xs-4">
													<input type="submit" id="saveOutput"
														class="btn btn-primary" value="Save"
														onclick="return submitSubActivityForm();"
														/> <input type="button"
														id="cancel" value="Cancel" class="btn btn-primary" />
												</div>
												<div class="col-xs-4"></div>
											</div>
										</div>
									</form:form>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
			
			
			<!-- Indicator Modal -->
			<div class="modal fade" id="indicatorModal" role="dialog">
				<div class="modal-dialog">

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
													style="color: red">*</span></label>
											</div>
											<div class="col-xs-4">
												<input type="hidden" id="IndicatorId" name="id" value="${indicator.id}" /> 
												<!-- <input type="text" name="message" style="margin-top: 5px;" class="form-control" /> -->
												<textarea rows="5" cols="3" name="message" style="margin-top: 5px;" class="form-control" ></textarea>
											</div>
										</div>

										<div class="form-group">
											<div class="control-label col-xs-4">
												<label cssClass="labelColor">Output : &nbsp;<span
													style="color: red">*</span>
												</label>
											</div>
											<div class="col-xs-4">
												<input id="IndicatorOutputName" name="output.output" style="margin-top: 5px;" class="form-control" readonly="readonly" />
											</div>
										</div>
										<div class="form-group">
											<div class="row">
												<div class="col-xs-4"></div>
												<div class="col-xs-4">
													<input type="submit" id="saveOutput"
														class="btn btn-primary" value="Save"
														onclick="return submitIndicatorAddform();"
														 /> <input type="button"
														id="cancel" value="Cancel" class="btn btn-primary" />
												</div>
												<div class="col-xs-4"></div>
											</div>
										</div>
									</form:form> 
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
			<br />
			<div class="row">
				<div class="col-sm-12 col-md-12">
					<div class="panel-group accordion" id="accordion">
						<c:if test="${not empty strategicPillars }">
							<c:forEach items="${ strategicPillars}" var="strategic">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a class="accordion-toggle" data-toggle="collapse"
												data-parent="#accordion"
												href="#collapseStrategic_${strategic.id }"> <span><b>${strategic.name }</b></span>
												<input type="hidden" id="strategicId"
												value="${strategic.id}" />
											</a>
										</h4>
									</div>
									<div id="collapseStrategic_${strategic.id }"
										class="panel-collapse collapse in strategic-pillar">
										<div class="panel-body">
											<c:if test="${not empty strategic.id }">
											<div align="right">
												<button type="button" class="btn btn-primary btn-add-panel1"
													data-toggle="modal" data-target="#themeModal">
													<i class="glyphicon glyphicon-plus"></i>Add new Theme
												</button>
												</div>
											</c:if>
											<div class="panel-group accordion" id="accordion1">
												<c:if test="${not empty strategic.themes }">
													<c:forEach items="${ strategic.themes}" var="theme">
														<div class="panel panel-default template1">
															<div class="panel-heading">
																<h4 class="panel-title">
																	<a class="accordion-toggle" data-toggle="collapse"
																		data-parent="#collapseOne"
																		href="#collapseTheme_${theme.id }"> <span><b>
																				${theme.name } </b></span>
																	</a>
																</h4>
															</div>
															<div id="collapseTheme_${theme.id }"
																class="panel-collapse collapse in theme">
																<div class="panel-body">
																	<c:if test="${not empty theme.id }">
																	<div align="right">
																		<button type="button"
																			class="btn btn-primary btn-add-panel2"
																			data-toggle="modal" data-target="#outcomeModal">
																			<i class="glyphicon glyphicon-plus"></i>Add new
																			Outcome
																		</button>
																		</div>
																	</c:if>
																	<div class="panel-group accordion" id="accordion2">
																		<c:if test="${not empty theme.outcomes }">
																			<c:forEach items="${ theme.outcomes}" var="outcome">
																				<div class="panel panel-default template2">
																					<div class="panel-heading">
																						<h4 class="panel-title">
																							<a class="accordion-toggle"
																								data-toggle="collapse"
																								data-parent="#collapseOne"
																								href="#collapseOutcome_${outcome.id }"> <span><b>
																										${outcome.name } ${outcome.sequenceNumber }</b></span>
																							</a>

																						</h4>
																					</div>
																					<div id="collapseOutcome_${outcome.id }"
																						class="panel-collapse collapse in outcome">
																						<div class="panel-body">
																							<c:if test="${not empty outcome.id }">
																							<div align="right">
																								<button type="button"
																									class="btn btn-primary btn-add-panel3"
																									data-toggle="modal" data-target="#outputModal">
																									<i class="glyphicon glyphicon-plus"></i>Add new
																									Output
																								</button>
																							</div>
																							</c:if>
																							<div class="panel-group accordion"
																								id="accordion3">
																								<c:if test="${not empty outcome.outputs }">
																									<c:forEach items="${ outcome.outputs}"
																										var="output">
																										<div class="panel panel-default template3">
																											<div class="panel-heading">
																												<h4 class="panel-title">
																													<a class="accordion-toggle"
																														data-toggle="collapse"
																														data-parent="#collapseOne"
																														href="#collapseOutput_${output.id }">
																														<span> <b> ${output.name }
																																${output.sequenceNumber }</b></span>
																													</a>
																												</h4>
																											</div>
																											<div id="collapseOutput_${output.id }"
																												class="panel-collapse collapse in output">
																												<div class="panel-body">
																													<c:if test="${not empty output.id }">
																													<div align="right">
																														<button type="button"
																															class="btn btn-primary btn-add-panel4"
																															data-toggle="modal"
																															data-target="#keyActivityModal">
																															<i class="glyphicon glyphicon-plus"></i>Add
																															new Key Activity
																														</button>
																														</div>
																													</c:if>
																													<div class="panel-group accordion"
																														id="accordion4">
																														<c:if
																															test="${not empty output.keyActivities }">
																															<c:forEach
																																items="${ output.keyActivities}"
																																var="keyActivity">
																																<div class="table-responsive">
																																	<table id="table-example" class="table table-hover table-bordered CSSTableGenerator">
																																		<thead>
																																			<tr>
																																				<th>Sequence Number</th>
																																				<th>Key Activity</th>
																																				<th>Sub Activity</th>
																																				<th>Responsible Entity</th>
																																				<th>Partnering Agency</th>
																																				<th>MOV</th>
																																				<th>+</th>
																																			</tr>
																																		</thead>
																																		<tbody>
																																			<tr>
																																				<td><c:out
																																						value="${keyActivity.sequenceNumber }" /></td>
																																				<td><c:out
																																						value="${keyActivity.keyActivity}" /></td>
																																				<c:if test="${not empty keyActivity.subActivities }">
																																					<td></td>
																																					<td></td>
																																					<td></td>
																																					<td></td>
																																				</c:if>
																																				<td><a href="#"
																																					onclick="openKeyActivtyModal(${keyActivity.id});"><i
																																						class="glyphicon glyphicon-plus"></i>Add
																																						new Sub Activity</a></td>
																																			</tr>
																																			<c:if test="${not empty keyActivity.subActivities }">
																																			<c:forEach items="${ keyActivity.subActivities}" var="subActivity">
																																			<tr>
																																				<td><c:out
																																						value="${subActivity.sequenceNumber }" /></td>
																																				<td></td>
																																				<td>${subActivity.subActivity }</td>
																																					<td>${subActivity.leadAgency.agency }</td>
																																						<td></td>
																																					<td>${subActivity.mov }</td>
																																				<td></td>
																																			</tr>
																																			</c:forEach>
																																			</c:if>
																																		</tbody>
																																	</table>
																																	<c:if test="${not empty output.id }">
																																		<button type="button"
																																			class="btn btn-primary"
																																			onclick="indicatorModal(${output.id});">
																																			<i class="glyphicon glyphicon-plus"></i>Add
																																			Indicator
																																		</button>
																																	</c:if>
																																	<c:if
																																		test="${not empty output.indicators }">
																																		<c:forEach
																																			items="${output.indicators }"
																																			var="indicator">
																																			<table>
																																				<tr>
																																					<td>*&nbsp;<c:out
																																							value="${indicator.message}" /></td>
																																				</tr>
																																			</table>
																																		</c:forEach>
																																	</c:if>
																																	<c:if test="${not empty output.id }">
																																		<button type="button"
																																			class="btn btn-primary btn-add-panel6"
																																			data-toggle="modal"
																																			data-target="#TargetModal">
																																			<i class="glyphicon glyphicon-plus"></i>Add
																																			New Target
																																		</button>
																																	</c:if>
																																	<c:if
																																		test="${not empty output.targets }">
																																		<c:forEach
																																			items="${output.targets }"
																																			var="target">
																																			<table>
																																				<tr>
																																					<td>*&nbsp;<c:out
																																							value="${target.message}" /></td>
																																				</tr>
																																			</table>
																																		</c:forEach>
																																	</c:if>
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
							</c:forEach>
						</c:if>
					</div>
				</div>
			</div>
		</div>

	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			$('#tabs > li').removeClass('active');
			$('#projectsTab').attr('class', 'active');
		});

		$(".btn-add-panel").on("click", function() {
			$.ajax({
				url : '/ndcmp/api/getStrategicpillar',
				data : {

				},
				type : 'GET',
				async : false,
				dataType : 'json',
				success : function(data) {
					if (data && data.trim != '') {
						var ele = data.project.name;
						/* $('#projectName').html(ele); */
						document.getElementById("projectName").value = ele;
					}
				}
			});
		});

		$(".btn-add-panel1").on("click",function() {
			var parent = $(this).closest('.strategic-pillar');
			var id = $(parent).attr('id');
			var strategicPillarId = id.split('_')[1];
			//var strategicPillarId = $('#strategicId').val();
			$.ajax({
				url : '/ndcmp/api/getTheme',
				data : {
						strategicPillarId : strategicPillarId
				},
				type : 'GET',
				async : false,
				dataType : 'json',
				success : function(data) {
					if (data && data.trim != '') {
						var ele = data.strategicPillar.name;
						/* $('#projectName').html(ele); */
						document.getElementById("strategicPillarName").value = ele;
					}
				}
			});
		});

		$(".btn-add-panel2").on("click", function() {
			var parent = $(this).closest('.theme');
			var id = $(parent).attr('id');
			var themeId = id.split('_')[1];
			//var strategicPillarId = $('#strategicId').val();
			$.ajax({
				url : '/ndcmp/api/getHierarchyOutcome',
				data : {
					themeId : themeId
				},
				type : 'GET',
				async : false,
				dataType : 'json',
				success : function(data) {
					if (data && data.trim != '') {
						var ele = data.theme.name;
						/* $('#projectName').html(ele); */
						document.getElementById("themeName").value = ele;
					}
				}
			});
		});

		$(".btn-add-panel3").on("click", function() {
			var parent = $(this).closest('.outcome');
			var id = $(parent).attr('id');
			var outcomeId = id.split('_')[1];
			//var strategicPillarId = $('#strategicId').val();
			$.ajax({
				url : '/ndcmp/api/getHierarchyOutput',
				data : {
					outcomeId : outcomeId
				},
				type : 'GET',
				async : false,
				dataType : 'json',
				success : function(data) {
					if (data && data.trim != '') {
						var ele = data.outcome.name;
						/* $('#projectName').html(ele); */
						document.getElementById("outcomeName").value = ele;
					}
				}
			});
		});
		
		//to get output id
		$(".btn-add-panel4").on("click", function() {
			var parent = $(this).closest('.output');
			var id = $(parent).attr('id');
			var outputId = id.split('_')[1];
			//var strategicPillarId = $('#strategicId').val();
			$.ajax({
				url : '/ndcmp/api/getHierarchyKeyActivity',
				data : {
					outputId : outputId
				},
				type : 'GET',
				async : false,
				dataType : 'json',
				success : function(data) {
					if (data && data.trim != '') {
						var ele = data.output.output;
						/* $('#projectName').html(ele); */
						document.getElementById("outputName").value = ele;
					}
				}
			});
		});
		

		//to get target id
		$(".btn-add-panel6").on("click", function() {
			var parent = $(this).closest('.output');
			var id = $(parent).attr('id');
			var outputId = id.split('_')[1];
			//var strategicPillarId = $('#strategicId').val();
			$.ajax({
				url : '/ndcmp/api/getHierarchyKeyActivity',
				data : {
					outputId : outputId
				},
				type : 'GET',
				async : false,
				dataType : 'json',
				success : function(data) {
					if (data && data.trim != '') {
						var ele = data.output.output;
						/* $('#projectName').html(ele); */
						document.getElementById("outName").value = ele;
					}
				}
			});
		});
		
		//to set sub activity modal window values

		/* //to set sub activity modal window values
>>>>>>> .r73
		$(".btn-add-panel5").on("click", function() {
			var parent = $(this).closest('.keyActivity');
			var id = $(parent).attr('id');
			var keyActvityId = id.split('_')[1];
			//var strategicPillarId = $('#strategicId').val();
			$.ajax({
				url : '/ndcmp/api/getHierarchySubActivity',
				data : {
					keyActvityId : keyActvityId
				},
				type : 'GET',
				async : false,
				dataType : 'json',
				success : function(data) {
					if (data && data.trim != '') {
						var ele = data.output.output;
						var leadAgency = '<option value="">--- Select Responsible Entity ---</option>';
						var leadAgencys = data.leadAgency;
						for (var i = 0; i < leadAgencys.length; i++) {
							leadAgency += '<option value="'
									+ leadAgencys[i].id + '">' + leadAgencys[i].processName + '</option>';
						}
					}
				}
			});
		}); */
		function openKeyActivtyModal(keyActvityId){
			$.ajax({
				url : '/ndcmp/api/getHierarchySubActivity',
				data : {
					keyActvityId : keyActvityId
				},
				type : 'GET',
				async : false,
				dataType : 'json',
				success : function(data) {
					if (data && data.trim != '') {
						/* var ele = data.keyActivity.name;
						document.getElementById("keyActivityName").value = ele;
						$('#subActivityModal').modal(); */
// 						leadAgency
// 						partnerAgencyId

						$('#keyActivityName').val(data.keyActivity.name);
						
						var leadAgency = '<option value="">--- Select Responsible Entity ---</option>';
						var leadAgencys = data.leadAgency;
						for (var i = 0; i < leadAgencys.length; i++) {
							leadAgency += '<option value="'	+ leadAgencys[i].id + '">' + leadAgencys[i].name + '</option>';
						}
						$('#leadAgencyId').html(leadAgency);
						
						var partnerAgency = '<option value="">--- Select Partner Agency ---</option>';
						var partnerAgencys = data.partnerAgency;
						for (var i = 0; i < partnerAgencys.length; i++) {
							partnerAgency += '<option value="'	+ partnerAgencys[i].id + '">' + partnerAgencys[i].name + '</option>';
						}
						$('#partnerAgencyId').html(partnerAgency);
						$('#subActivityModal').modal('show')
					}
					/* if (data && data.trim != '') {
						var ele = data.output.output;
						var leadAgency = '<option value="">--- Select Responsible Entity ---</option>';
						var leadAgencys = data.leadAgency;
						for (var i = 0; i < leadAgencys.length; i++) {
							leadAgency += '<option value="'
									+ leadAgencys[i].id + '">' + leadAgencys[i].processName + '</option>';
						}
					} */
				}
			});
		}


//Indicator modal window
function indicatorModal(outputID){
	$.ajax({
		url : '/ndcmp/api/getIndicator',
		data : {
			outputID : outputID
		},
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data){
			if(data != null){
				var outputName = data.output.output;
				$('#IndicatorOutputName').val(outputName);
				$('#indicatorModal').modal('show');
			}
		}
	});
}
		$('#cancel').click(function() {
			window.history.back();
		});

		function submitStrategicPillarForm() {

			$('#addStrategicPillarForm').formValidation({
				icon : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				fields : {
					name : {
						validators : {
							notEmpty : {
								message : 'StrategicPillar Name is Required'
							}
						}
					},
					description : {
						validators : {
							notEmpty : {
								message : 'Description is Required'
							}
						}
					},
					'project.id' : {
						validators : {
							notEmpty : {
								message : 'Project is Required'
							}
						}
					},
				}
			});

			return true;
		};

		function submitThemeForm() {
			$('#themeAddform')
					.formValidation(
							{
								icon : {
									valid : 'glyphicon glyphicon-ok',
									invalid : 'glyphicon glyphicon-remove',
									validating : 'glyphicon glyphicon-refresh'
								},
								fields : {
									name : {
										validators : {
											notEmpty : {
												message : 'Theme Name is Required'
											},
											stringLength : {
												min : 3,
												max : 25,
												message : 'Theme Name must be more than 3 and less than 25 characters long'
											}
										}
									},
									'strategicPillar.name' : {
										validators : {
											notEmpty : {
												message : 'Description is Required'
											},
										}
									}
								}
							});
			return true;
		};

		function submitOutcomeForm() {
			$('#outcomeAddform')
					.formValidation(
							{
								icon : {
									valid : 'glyphicon glyphicon-ok',
									invalid : 'glyphicon glyphicon-remove',
									validating : 'glyphicon glyphicon-refresh'
								},
								fields : {
									name : {
										validators : {
											notEmpty : {
												message : 'Outcome Name is Required'
											},
											stringLength : {
												min : 3,
												max : 25,
												message : 'Outcome Name must be more than 3 and less than 25 characters long'
											}
										}
									},
									'theme.name' : {
										validators : {
											notEmpty : {
												message : 'Theme Name is Required'
											},
										}
									}
								}
							});
			return true;
		};

		function submitOutputForm() {
			$('#outputAddform')
					.formValidation(
							{
								icon : {
									valid : 'glyphicon glyphicon-ok',
									invalid : 'glyphicon glyphicon-remove',
									validating : 'glyphicon glyphicon-refresh'
								},
								fields : {
									name : {
										validators : {
											notEmpty : {
												message : 'Output Name is Required'
											},
											stringLength : {
												min : 3,
												max : 25,
												message : 'Output Name must be more than 3 and less than 25 characters long'
											}
										}
									},
									'outcome.name' : {
										validators : {
											notEmpty : {
												message : 'Outcome Name is Required'
											},
										}
									}
								}
							});
			return true;
		};
		
		function submitKeyActivityForm() {
			$('#keyActivityAddform')
					.formValidation(
							{
								icon : {
									valid : 'glyphicon glyphicon-ok',
									invalid : 'glyphicon glyphicon-remove',
									validating : 'glyphicon glyphicon-refresh'
								},
								fields : {
									name : {
										validators : {
											notEmpty : {
												message : 'Key Activity is Required'
											},
											stringLength : {
												min : 3,
												max : 25,
												message : 'Key Activity must be more than 3 and less than 25 characters long'
											}
										}
									},
									'output.output' : {
										validators : {
											notEmpty : {
												message : 'Output Name is Required'
											},
										}
									}
								}
							});
			return true;
		};
		
		
		function submitKeyActivityForm() {
			$('#subActivityAddform')
					.formValidation(
							{
								icon : {
									valid : 'glyphicon glyphicon-ok',
									invalid : 'glyphicon glyphicon-remove',
									validating : 'glyphicon glyphicon-refresh'
								},
								fields : {
									name : {
										validators : {
											notEmpty : {
												message : 'Sub Activity is Required'
											},
											stringLength : {
												min : 3,
												max : 25,
												message : 'Sub Activity must be more than 3 and less than 25 characters long'
											}
										}
									},
									'leadAgency.name' : {
										validators : {
											notEmpty : {
												message : 'Responsible Entity is Required'
											},
										}
									},
									'partnerAgency.name' : {
										validators : {
											notEmpty : {
												message : 'Partner Agency is Required'
											},
										}
									},
									mov : {
										validators : {
											notEmpty : {
												message : 'MOV is Required'
											},
										}
									}
								}
							});
			return true;
		};
		
		/* Indicator message validation */
		function submitIndicatorAddform() {
			$('#indicatorAddform').formValidation(
				{
					icon : {
						valid : 'glyphicon glyphicon-ok',
						invalid : 'glyphicon glyphicon-remove',
						validating : 'glyphicon glyphicon-refresh'
					},
					fields : {
						message : {
							validators : {
								notEmpty : {
									message : 'Message is Required'
								}
							}
						},
						'output.output' : {
							validators : {
								notEmpty : {
									message : 'Output Name is Required'
								},
							}
						}
					}
				});
			return true;
		};
	</script>
<script src="../static/js/demo-rtl.js"></script>
<script src="../static/js/jsgrid/css/jquery-ui.css"></script>
<script src="../static/js/jsgrid/css/jsgrid.css"></script>
<script src="../static/js/jsgrid/css/theme.css"></script>
<script src="../static/js/jquery.dataTables.js"></script>
<script src="../static/js/dataTables.tableTools.js"></script>
<script src="../static/js/jquery.dataTables.bootstrap.js"></script>
<script src="../static/js/demo-skin-changer.js"></script>
<script src="../static/js/scripts.js"></script>
<script src="../static/js/pace.min.js"></script>
<script type="text/javascript" src="../static/scripts/login.js"></script>
</body>

</html>