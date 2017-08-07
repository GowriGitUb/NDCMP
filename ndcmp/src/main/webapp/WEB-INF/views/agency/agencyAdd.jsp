<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../include/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UNODC</title>
<%@include file="../include/commonlib.jsp"%>
<script type="text/javascript" src='<c:url value="/static/scripts/agency.js"></c:url>'></script>
<script type="text/javascript" src="../static/scripts/menu.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#tabs > li').removeClass('active');
	$('#configurationTab').attr('class', 'active');
	$('#agencyTab').attr('class','active');
});
</script>
</head>
<body onload="setAgencyValues()">
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
										<c:when test="${agency.id == 0 }">
											<span>Add Agency</span>
										</c:when>
										<c:otherwise>
											<span>Update Agency</span>
										</c:otherwise>
									</c:choose>
								</h1>
								<br /> <br />
								<hr />
								<form:form action="saveAgency" id="agencyAddForm" cssClass="form-horizontal" modelAttribute="agency" method="post">
								<form:hidden path="id" value="${agency.id}" />
									<div class="form-group">
										<div class="control-label col-xs-3">
											<form:label path="code" cssClass="labelColor">Agency Code : &nbsp;<span
													style="color: red;">*</span>
											</form:label>
										</div>
										<div class="col-xs-2">
											<form:input cssClass="form-control" path="code" id="code"  placeholder = "Code"/>
										</div>
										<div>
											<span style="color: #D00B05">${errormsg}</span>
										</div>
										<div>
											<span style="color: #D00B05">${codeExitMessage}</span>
										</div>
									</div>
									
									<div class="form-group">
										<div class="control-label col-xs-3">
											<form:label path="name" cssClass="labelColor">Agency Name : &nbsp;<span
													style="color: red;">*</span>
											</form:label>
										</div>
										<div class="col-xs-4">
											<form:input cssClass="form-control" path="name" id="name" placeholder = "Agency Name" />
										</div>
										<div>
											<span style="color: #D00B05">${errormsg}</span>
										</div>
										<div>
											<span style="color: #D00B05">${nameExitMessage}</span>
										</div>
									</div>
									
									

									<div class="form-group">
										<div class="control-label col-xs-3">
											<form:label path="country" cssClass="labelColor">Country : &nbsp;<span
													style="color: red;">*</span>
											</form:label>
										</div>
										<div class="col-xs-2">
											<form:select path="country.id" id="countryId" class="form-control" onchange="getStateByCountry();">
												<form:option value="" label="---Select Country---">---Select Country---</form:option>
												<c:forEach items="${countryList}" var="country">
													<form:option value="${country.id}" label="${country.name}"></form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>
									
									<c:choose>
										<c:when test="">
											<div class="form-group">
												<div class="control-label col-xs-3">
													<form:label path="state" cssClass="labelColor">State : &nbsp;<span
															style="color: red;">*</span>
													</form:label>
												</div>
												<div class="col-xs-2">
													<form:select path="state.id" id="stateId"
														class="form-control" onchange="getRegionByState();">
														<form:option value="" label="---Select State---"></form:option>
													</form:select>
												</div>
											</div>
										</c:when>
										<c:otherwise>
											<div class="form-group">
												<div class="control-label col-xs-3">
													<form:label path="state" cssClass="labelColor">State : &nbsp;<span
															style="color: red;">*</span>
													</form:label>
												</div>
												<div class="col-xs-2">
													<form:select path="state.id" id="stateId" class="form-control" onchange="getRegionByState();">
														<form:option value="" label="---Select State---">---Select State---</form:option>
														<c:forEach items="${stateList}" var="state">
															<form:option value="${state.id}" label="${state.name}"></form:option>
														</c:forEach>
													</form:select>
												</div>
											</div>
										</c:otherwise>
									
									</c:choose>
									<%-- <div class="form-group">
										<div class="control-label col-xs-3">
											<form:label path="state" cssClass="labelColor">State : &nbsp;<span
													style="color: red;">*</span>
											</form:label>
										</div>
										<div class="col-xs-2">
											<form:select path="state.id" id="stateId" class="form-control" onchange="getRegionByState();">
												<c:choose>
													<c:when test="${agency.id == null }">
														<form:option value="" label="---Select State---"></form:option>
													</c:when>
													<c:otherwise>
														<form:option value="" label="---Select State---">---Select State---</form:option>
														<c:forEach items="${stateList}" var="state">
															<form:option value="${state.id}" label="${state.name}"></form:option>
														</c:forEach>
													</c:otherwise>
												</c:choose>
											</form:select>
										</div>
									</div> --%>

									<div class="form-group">
										<div class="control-label col-xs-3">
											<form:label path="region" cssClass="labelColor">Region : &nbsp;<span
													style="color: red;">*</span>
											</form:label>
										</div>
										<div class="col-xs-2">
											<form:select path="region.id" id="regionId" class="form-control">
												<c:choose>
													<c:when test="${agency.id == null }">
														<form:option value="" label="---Select Region---"></form:option>
													</c:when>
													<c:otherwise>
														<form:option value="" label="---Select Region---"></form:option>
														<c:forEach items="${regionList}" var="region">
															<form:option value="${region.id}" label="${region.name}"></form:option>
														</c:forEach>
													</c:otherwise>
												</c:choose>
											</form:select>
										</div>
									</div>
									
									<div class="form-group">
										<div class="control-label col-xs-3">
											<form:label path="roleIds" cssClass="labelColor">Users: &nbsp;&nbsp;
											</form:label>
										</div>
										<div class="col-xs-3">
											<form:select path="roleIds" id="agencyAuthorityId" multiple="multiple"
												class="form-control">
												<c:choose>
													<c:when test="${agency.id == 0 }">
														<form:option value="" label="---Select Users---"></form:option>
													</c:when>
													<c:otherwise>
														<form:option value="" label="---Select Users---"></form:option>
													</c:otherwise>
												</c:choose>
											</form:select>
										</div>
									</div>
									
							<div class="form-group">
								<div class="row">
									<div class="col-xs-3"></div>
									<div class="col-xs-3">
										<input type="submit" id="saveUserrole" class="btn btn-primary" value="Save" onclick="return submitAgencyForm();" />
										<!-- <input type="button" id="cancel" value="Cancel" class="btn btn-primary" /> -->
										<a href="agencyList" class="btn btn-default">Cancel</a>
									</div>
									<div class="col-xs-3"></div>
								</div>
							</div>
								</form:form>
							</div>
						</div>
					<!-- 	<footer id="footer-bar" class="row">
						<p id="footer-copyright" class="col-xs-12"></p>
						</footer> -->
					</div>
				</div>

			</div>
		</div>
	</div>


	<%-- 
	<%@include file="../../include/menu.jsp"%>
	<div class="container">
		<div align="left">
			<c:choose>
				<c:when test="${agency.id == null}">
					<h2>Add Agency</h2>
				</c:when>
				<c:otherwise>
					<h2>Update Agency</h2>
				</c:otherwise>
			</c:choose>
		</div>
		<hr />
		<div>
			<form:form id="agencyAddForm" cssClass="form-horizontal"
				modelAttribute="agency" method="post" action="saveAgency">
				
				<form:hidden path="id" value="${agency.id}" />
				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="name" cssClass="labelColor">Agency Name : &nbsp;<span
								style="color: red;">*</span>
						</form:label>
					</div>
					<div class="col-xs-6">
						<form:input cssClass="form-control" path="name"
							value="${agency.name}" id="name" />
					</div>
					<div>
						<span style="color: #D00B05">${errormsg}</span>
					</div>
				</div>

				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="agencyType" cssClass="labelColor">Agency Type : &nbsp;<span
								style="color: red;">*</span>
						</form:label>
					</div>
					<div class="col-xs-6">
						<form:select path="agencyType" id="agencyTypeId" class="form-control">
							<form:option value="" label="---Select Agency Type---">---Select Agency Type---</form:option>
							<c:forEach items="${roleList}" var="role">
								<form:option value="${role.id}" label="${role.description}"></form:option>
							</c:forEach>
						</form:select>
					</div>
				</div>

				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="country" cssClass="labelColor">Country : &nbsp;<span
								style="color: red;">*</span>
						</form:label>
					</div>
					<div class="col-xs-6">
						<form:select path="country.id"  id="countryId"  class="form-control" onchange="getStateByCountry();">
							<form:option value="" label="---Select Country---">---Select Country---</form:option>
							<c:forEach items="${countryList}" var="country">
								<form:option value="${country.id}" label="${country.name}"></form:option>
							</c:forEach>
						</form:select>
					</div>
				</div>

				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="state" cssClass="labelColor">State : &nbsp;<span
								style="color: red;">*</span>
						</form:label>
					</div>
					<div class="col-xs-6">
						<form:select path="state.id" id="stateId" class="form-control" onchange="getRegionByState();">
							<form:option value="" label="---Select State---">---Select State---</form:option>
							<c:forEach items="${stateList}" var="state">
								<form:option value="${state.id}" label="${state.name}"></form:option>
							</c:forEach>
						</form:select>
					</div>
				</div>

				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="region" cssClass="labelColor">Region : &nbsp;<span
								style="color: red;">*</span>
						</form:label>
					</div>
					<div class="col-xs-6">
						<form:select path="region.id"  id="regionId" class="form-control">
							<form:option value="" label="---Select Region---">---Select Region---</form:option>
							<c:forEach items="${regionList}" var="region">
								<form:option value="${region.id}" label="${region.name}"></form:option>
							</c:forEach>
						</form:select>
					</div>
				</div>

				<div class="form-group">
					<div class="row">
						<div class="col-xs-4"></div>
						<div class="col-xs-4">
							<input type="submit" id="saveUserrole" class="btn btn-primary"
								value="Save" onclick="return submitAgencyForm();" /> <a
								href="agencyList" class="btn btn-default">Cancel</a>
						</div>
						<div class="col-xs-4"></div>
					</div>
				</div>

			</form:form>
		</div>
	</div>
 --%>
	
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
