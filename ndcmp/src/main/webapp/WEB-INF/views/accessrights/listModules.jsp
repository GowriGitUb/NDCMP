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
<script type="text/javascript" src="../static/scripts/menu.js"></script>
<script type="text/javascript" src="../static/scripts/access.js"></script>
</head>
<body class="nav-md" onload="loadSuperAdminModules();">
<div id="theme-wrapper">
	<%@include file="../include/menu.jsp"%>
	<div id="page-wrapper" class="container">
		<%@include file="../include/menuItems.jsp"%>
		<div id="content-wrapper">
			<div class="row">
					<div class="col-lg-12">
						<div class="main-box clearfix">
							<div class="main-box-body clearfix">
								<br />
								<h1 class="pull-left">
									<span>Roles And Features</span>
								</h1>
								<br /> <br />
								<hr />
								<div class="x_content">
									<div>
										<h4 style="font-weight: bold;">Role : ${roleName }</h4>
										<input type="hidden" name="roleId" id="roleId" value="${roleId }" />
									</div>
									<br /> <br /> <br />
									<div class='container'>
										<div class='col-md-4' id="general1"></div>
										<div class='col-md-4' id="general2"></div>
										<div class='col-md-4' id="general3"></div>
									</div>
									<div class="col-xs-offset-2 col-xs-10" align="center">
										<c:choose>
											<c:when test="${superadminflag == false}">
												<button type="submit" style="margin-left: -210px; display: none;"
											class="btn btn-primary" onclick="updateFeature('${roleId }')">Update</button>
											</c:when>
											<c:otherwise>
												<button type="submit" style="margin-left: -210px;"
											class="btn btn-primary" onclick="updateFeature('${roleId }')">Update</button>
											</c:otherwise>
										</c:choose>
										<a href="accessrightsList" class="btn btn-default">Cancel</a>
									</div>
								</div>
							</div>
						</div>
					</div>
			</div>
		</div>
	</div>
</div>
	<%-- <div class="container body">
		<div class="main_container">
			<%@include file="../include/menu.jsp"%>
			<div class="right_col" role="main" style="min-height: 699px;">
				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="x_panel">
							<div class="x_title">
								<h2 align="left">Roles And Features</h2>
								<div align="right">
									<a href="listAccess"> <img style="width: 24px; height: 24px;"
										title="Back" src="<c:url value="/static/images/back.png" />">
									</a>
								</div>
								<div class="clearfix"></div>
							</div>
							<div class="x_content">
								<div>
									<h4 style="font-weight: bold;">Role : ${roleName }</h4>
									<input type="hidden" name="roleId" id="roleId"
										value="${roleId }" />
								</div>
								<br /> <br /> <br />


								<div class='container'>
									<div class='col-md-4' id="general1"></div>
									<div class='col-md-4' id="general2"></div>
									<div class='col-md-4' id="general3"></div>
								</div>
								<div class="col-xs-offset-2 col-xs-10" align="center">
								
								<c:choose>
										<c:when test="${superadminflag == false}">
											<button type="submit" style="margin-left: -210px; display: none;"
										class="btn btn-primary" onclick="updateFeature('${roleId }')">Update</button>
										</c:when>
										<c:otherwise>
											<button type="submit" style="margin-left: -210px;"
										class="btn btn-primary" onclick="updateFeature('${roleId }')">Update</button>
										</c:otherwise>
									</c:choose>
									<a href="accessrightsList" class="btn btn-default">Cancel</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div> --%>

<!-- BackToTop Button -->
	<a href="javascript:void(0);" id="scroll" title="Scroll to Top" style="display: none;">Top<span></span></a>

</body>
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

</html>