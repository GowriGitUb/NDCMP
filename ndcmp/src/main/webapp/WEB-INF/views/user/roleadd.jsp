<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../include/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UNODC</title>
<%@include file="../include/commonlib.jsp"%>
<script type="text/javascript" src='<c:url value="/static/scripts/role.js"></c:url>'></script>
<script type="text/javascript" src="../static/scripts/menu.js"></script>
<script type="text/javascript">
$(document).ready(function(){
		$('#tabs > li').removeClass('active');
		$('#configurationTab').attr('class', 'active');
		$('#roleTab').attr('class','active');
});
</script>
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
							<div class="main-box">
								<div class="main-box-body clearfix">
									<h1 class="pull-left">
										<c:choose>
											<c:when test="${role.id == 0}">
												<h1 class="pull-left"><span>Add Role</span></h1>
											</c:when>
											<c:otherwise>
												<h1 class="pull-left"><span>Update Role</span></h1>
											</c:otherwise>
										</c:choose>
									</h1>
									<br/>
									<br /> <br />
									<hr />
									<form:form id="userroleAddForm" cssClass="form-horizontal"
										modelAttribute="role" method="post" action="saveUserrole">
												<div class="form-group">
													<div class="control-label col-xs-3">
														<form:label path="name">Role Name :&nbsp;<span style="color: red;">*</span></form:label>
													</div>
													<div class="col-xs-2">
														<form:hidden path="id" value="${role.id}" />
														<c:choose>
															<c:when test="${role.id == 0}">
																<form:input cssClass="form-control" path="name" value="${role.name}" id="name" placeholder = "Role Name"/>
															</c:when>
															<c:otherwise>
																<form:input cssClass="form-control" path="name" readonly="true" value="${role.name}" id="name" placeholder = "Role Name"/>
															</c:otherwise>
														</c:choose>
														
													</div>
													<div>
														<span style="color: #D00B05">${errormsg}</span>
													</div>
												</div>
												<div class="form-group">
													<div class="control-label col-xs-3">
														<form:label path="description">Description :&nbsp;<span style="color: red;">*</span></form:label>
													</div>
													<div class="col-xs-3">
														<%-- <form:input id="description" cssClass="form-control"
																path="description" value="${userRole.description}" placeholder = "Description" /> --%>
														<form:textarea id="description" cssClass="form-control"
																path="description" value="${role.description}" placeholder = "Description"></form:textarea>
													</div>
												</div>
										
										
										<!-- <br /> -->
											<div class="form-group">
												<div class="row">
													<div class="col-xs-3"></div>
													<div class="col-xs-3">
														<input type="submit" id="saveUserrole"
															class="btn btn-primary" value="Save"
															onclick="return submitRoleForm();" />
														<!-- <input type="button" id="cancel" value="Cancel" class="btn btn-primary" /> -->
														<a href="roleList" class="btn btn-default">Cancel</a>
													</div>
													<div class="col-xs-4"></div>
												</div>
											</div>
										</form:form>
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
