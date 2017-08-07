<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../include/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UNODC</title>
<%@include file="../include/commonlib.jsp"%>
<%@include file="../include/dataTable.jsp"%>
<script type="text/javascript" src="../static/scripts/menu.js"></script>
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
									<span>Add / Edit Planning Details</span>
								</h1>
								<br /> <br />
								<hr />
								<form:form action="createPlanning" id="planningAddform"
									cssClass="form-horizontal" modelAttribute="planning"
									method="post">


									<div class="row">
										<div class="col-lg-6">
											<div class="row">
												<div class="form-group">
													<div class="control-label col-xs-3">
														<form:label path="subActivity" cssClass="labelColor">SubActivity : &nbsp;<span
																style="color: red;">*</span>
														</form:label>
													</div>
													<div class="col-xs-6">
														<form:select path="subActivity" id="subActivityId"
															class="form-control">
															<form:option value=""
																label="---Select SubActivity Type---">---Select SubActivity Type---</form:option>
															<c:forEach items="${subActivitiesList}" var="subActivity">
																<form:option value="${subActivity.id}"
																	label="${subActivity.name}"></form:option>
															</c:forEach>
														</form:select>
													</div>
												</div>
											</div>
										</div>
										
										
							<%-- 			<div class="row">
										<div class="col-lg-6">
										<div class="form-group">
										<div class="control-label col-xs-3">
											<form:label path="year" cssClass="labelColor">Year : &nbsp;<span
													style="color: red">*</span>
											</form:label>
										</div>
										
										<div class="col-xs-6">
											<form:select path="year" id="year" cssClass="form-control">
												<form:option value="" label="---Select Year---"></form:option>
												<form:option value="2016" label="2016"></form:option>
												<form:option value="2017" label="2017"></form:option>
												<form:option value="2018" label="2018"></form:option>
												<form:option value="2019" label="2019"></form:option>
												<form:option value="2020" label="2020"></form:option>
											</form:select>
										</div>
									</div> --%>

										<div class="col-lg-6">
											<div class="row">
												<div class="form-group">
													<div class="control-label col-xs-3">
														<form:label path="reportingPeriod" cssClass="labelColor">ReportingPeriod : &nbsp;<span
																style="color: red;">*</span>
														</form:label>
													</div>
													<div class="col-xs-6">
														<form:select path="reportingPeriod" id="reportingPeriodId"
															class="form-control">
															<form:option value=""
																label="---Select ReportingPeriod Type---">---Select ReportingPeriod---</form:option>
															<c:forEach items="${reportingPeriodsList}" var="reportingPeriod">
																<form:option value="${reportingPeriod.id}"
																	label="${reportingPeriod.name}"></form:option>
															</c:forEach>
														</form:select>
													</div>
												</div>
											</div>
										</div>
									</div>


									<div class="row">
										<div class="col-lg-6">
											<div class="row">
												<form:hidden id="id" cssClass="form-control" path="id"
													value="${planning.id}" />
												<div class="form-group">
													<div class="control-label col-xs-3">
														<form:label path="statusOfProgress" cssClass="labelColor">StatusOfProgress : &nbsp;<span
																style="color: red">*</span>
														</form:label>
													</div>
													<div class="col-xs-6">
														<form:input id="statusOfProgress" cssClass="form-control"
															path="statusOfProgress"
															value="${planning.statusOfProgress}" />
													</div>
													<div>
														<span style="color: #D00B05">${codeExitMessage}</span>
													</div>
												</div>
											</div>
										</div>
									</div>

									<div class="form-group">
										<div class="row">
											<div class="col-xs-4"></div>
											<div class="col-xs-4">
												<input type="submit" id="savePlanning"
													class="btn btn-primary" value="Save"
													onclick="return submitPlanningForm();" />
												<!-- <input type="button" id="cancel" value="Cancel" class="btn btn-primary" /> -->
												<a href="planningList" class="btn btn-default">Cancel</a>
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
</div>


<!-- BackToTop Button -->
	<a href="javascript:void(0);" id="scroll" title="Scroll to Top" style="display: none;">Top<span></span></a>
	
<script src="../static/js/demo-skin-changer.js"></script>
		<!-- <script src="../static/js/jquery.js"></script> -->
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