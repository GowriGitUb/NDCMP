<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../include/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UNODC</title>
<%@include file="../include/commonlib.jsp"%>
<script src='<c:url value="/static/jslib/jquery.validate.min.js"></c:url>'></script>
<script type="text/javascript" src='<c:url value="/static/scripts/region.js"></c:url>'></script>
<script type="text/javascript" src="../static/scripts/menu.js"></script>
<script src="<c:url value="/static/js/user.js" />"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#tabs > li').removeClass('active');
	$('#configurationTab').attr('class', 'active');
	$('#regionTab').attr('class','active');
});
</script>
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
									<div align="left">
										<c:choose>
											<c:when test="${region.id == null}">
												<h1 class="pull-left"><span>Add Region</span></h1>
											</c:when>
											<c:otherwise>
												<h1 class="pull-left"><span>Update Region</span></h1>
											</c:otherwise>
										</c:choose>
									</div>
									<br /><br />
									<hr />
									<div>
										<form:form id="regionAddForm" cssClass="form-horizontal"
											modelAttribute="region" method="post" action="saveRegion">

											<div class="form-group">
												<div class="control-label col-xs-3">
													<form:label path="code" cssClass="labelColor">Region Code : &nbsp;<span
															style="color: red">*</span>
													</form:label>
												</div>
												<div class="col-xs-2">
													<form:hidden path="id" value="${region.id}" />
													<form:input cssClass="form-control" path="code"
														value="${region.code}" placeholder = "Code" />
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
													<form:label path="name" cssClass="labelColor">Region Name : &nbsp;<span
															style="color: red">*</span>
													</form:label>
												</div>
												<div class="col-xs-3">
													<form:input cssClass="form-control" path="name"
														value="${region.name}" placeholder = "Region Name" />
												</div>
												<p id="regionnameemsg" class="help-block"></p>
												<div>
														<span style="color: #D00B05">${nameExitMessage}</span>
												</div>
											</div>

											<div class="form-group">
												<div class="control-label col-xs-3">
													<form:label path="country" cssClass="labelColor">Country : &nbsp;<span
															style="color: red">*</span>
													</form:label>
												</div>
												<div class="col-xs-3">
													<form:select id="countryId" path="country.id"
														cssClass="form-control" onchange="getStateByCountry()">
														<form:option value="" label="---Select Country---"></form:option>
														<c:forEach items="${countrylist}" var="country">
															<form:option value="${country.id}"
																label="${country.name}"></form:option>
														</c:forEach>
													</form:select>
												</div>
												<p id="countrymsg" class="help-block"></p>
											</div>

											<c:choose>
												<c:when test="${region.id == null}">
													<div class="form-group">
														<div class="control-label col-xs-3">
															<form:label path="states" cssClass="labelColor">State : &nbsp;<span
																	style="color: red">*</span>
															</form:label>
														</div>
														<div class="col-xs-3">
															<form:select path="states.id" id="state"
																cssClass="form-control">
																<form:option value="" label="---Select State---"></form:option>
															</form:select>
														</div>
														<p id="statemsg" class="help-block"></p>
													</div>
												</c:when>
												<c:otherwise>
													<div class="form-group">
														<div class="control-label col-xs-3">
															<form:label path="states" cssClass="labelColor">State : &nbsp;<span
																	style="color: red">*</span>
															</form:label>
														</div>
														<div class="col-xs-2">
															<form:select path="states.id" id="state"
																cssClass="form-control">
																<form:option value="" label="---Select State---"></form:option>
																<c:forEach items="${statelist}" var="state">
																	<form:option value="${state.id}" label="${state.name}"></form:option>
																</c:forEach>
															</form:select>
														</div>
														<p id="statemsg" class="help-block"></p>
													</div>
												</c:otherwise>
											</c:choose>

										<div class="form-group">
											<div class="row">
												<div class="col-xs-3"></div>
												<div class="col-xs-4">
													<input type="submit" id="saveUserrole"
														class="btn btn-primary" value="Save"
														onclick="return submitRegionForm();" />
													<!-- <input type="button" id="cancel" value="Cancel" class="btn btn-primary" /> -->
													<a href="regionList" class="btn btn-default">Cancel</a>
												</div>
												<div class="col-xs-4"></div>
											</div>
										</div>

									</form:form>
									</div>
								</div>
							</div>
							<!-- <footer id="footer-bar" class="row">
									<p id="footer-copyright" class="col-xs-12"></p>
								</footer> -->
						</div>
					</div>

					<div id="config-tool" class="closed">
						<!-- <a id="config-tool-cog"> <i class="fa fa-cog"></i>
		</a> -->
						<div id="config-tool-options">
							<h4>Layout Options</h4>
							<ul>
								<li>
									<div class="checkbox-nice">
										<input type="checkbox" id="config-fixed-header" /> <label
											for="config-fixed-header"> Fixed Header </label>
									</div>
								</li>
								<li>
									<div class="checkbox-nice">
										<input type="checkbox" id="config-fixed-sidebar" /> <label
											for="config-fixed-sidebar"> Fixed Left Menu </label>
									</div>
								</li>
								<li>
									<div class="checkbox-nice">
										<input type="checkbox" id="config-fixed-footer" /> <label
											for="config-fixed-footer"> Fixed Footer </label>
									</div>
								</li>
							</ul>
							<!-- <br />
							<h4>Skin Color</h4>
							<ul id="skin-colors" class="clearfix">
								<li><a class="skin-changer" data-skin=""
									data-toggle="tooltip" title="Default"
									style="background-color: #34495e;"> </a></li>
								<li><a class="skin-changer" data-skin="theme-white"
									data-toggle="tooltip" title="White/Green"
									style="background-color: #2ecc71;"> </a></li>
								<li><a class="skin-changer blue-gradient"
									data-skin="theme-blue-gradient" data-toggle="tooltip"
									title="Gradient"> </a></li>
								<li><a class="skin-changer" data-skin="theme-turquoise"
									data-toggle="tooltip" title="Green Sea"
									style="background-color: #1abc9c;"> </a></li>
								<li><a class="skin-changer" data-skin="theme-amethyst"
									data-toggle="tooltip" title="Amethyst"
									style="background-color: #9b59b6;"> </a></li>
								<li><a class="skin-changer" data-skin="theme-blue"
									data-toggle="tooltip" title="Blue"
									style="background-color: #2980b9;"> </a></li>
								<li><a class="skin-changer" data-skin="theme-red"
									data-toggle="tooltip" title="Red"
									style="background-color: #e74c3c;"> </a></li>
								<li><a class="skin-changer" data-skin="theme-whbl"
									data-toggle="tooltip" title="White/Blue"
									style="background-color: #3498db;"> </a></li>
							</ul> -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- BackToTop Button -->
	<a href="javascript:void(0);" id="scroll" title="Scroll to Top" style="display: none;">Top<span></span></a>
	
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
