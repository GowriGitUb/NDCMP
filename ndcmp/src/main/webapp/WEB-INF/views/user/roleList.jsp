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


<body onload="advancedTable()">

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
								
								<h1 class="pull-left">User Role</h1>
								<%-- <c:if test="${ userRoleCount < 6 }">
									<div class="pull-right top-page-ui">
										<a href="getUserrole?id=0" class="btn btn-primary pull-right" title="Add Role">
											<i class="fa fa-plus-circle fa-lg"></i> Add Role
										</a>
									</div>
								</c:if> --%>
									<br/>
									<hr />
									
									<div class="table-responsive">
										<table id="roles-table"
											class="table table-hover table-bordered CSSTableGenerator">
											<thead>
												<tr>
													<th style="text-align: center;">#</th>
													<th style="text-align: center;">Role</th>
													<th style="text-align: center;">Description</th>
													<th style="text-align: center;">Status</th>
													<th style="text-align: center;">Action</th>
												</tr>
											</thead>
											<tbody>

												<c:forEach items="${roleList}" var="role"
													varStatus="recordCount">
													<tr>
														<td style="text-align: center;"><c:out value="${recordCount.count}" /></td>
														<td><c:out value="${role.name}" /></td>
														<td><c:out value="${role.description}" /></td>
														<c:choose>
														<c:when test="${role.status == 'ACTIVE'}">
															<td class="text-center"><span class="label label-success"><c:out value="${role.status}" /></span></td>
														</c:when>
														<c:otherwise>
															<td class="text-center"><span class="label label-danger"><c:out value="${role.status}" /></span></td>
														</c:otherwise>
													</c:choose>
													<td style="width: 20%;text-align: center;">
														<a href="#" onclick="getRoleInformation(${role.id})" class="table-link" title="View Role">
															 <span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-search-plus fa-stack-1x fa-inverse"></i></span>
														</a> 
														<a href="getUserrole?id=<c:out value='${role.id}'/>" class="table-link" title="Edit Role"> <span
																class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i></span>
														</a> <%-- <a href="#" data-toggle="modal" data-target="#myModal" data-id=${role.id } data-href="deleteUserRole?id=${role.id }" class="table-link danger" title="Delete">
															<span class="fa-stack"> <i
																class="fa fa-square fa-stack-2x"></i> <i
																class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
														</span>
													</a>  --%>
													
													<c:choose>
													<c:when test="${role.status == 'ACTIVE'}">
														<a href="#" class="table-link danger" title="Deactivate Role" data-id=${role.id } data-toggle="modal"	data-target="#userRoleDeactiveDialog" data-href="deActiveUserRole?id=${role.id}">
															<span class="fa-stack"> <i
																class="fa fa-square fa-stack-2x"></i> <i
																class="fa fa-times fa-stack-1x fa-inverse"></i>
														</span>
														</a>
													</c:when>
													<c:otherwise>
														<a href="#" class="table-link" title="Activate Role" data-id=${role.id } data-toggle="modal"	data-target="#userRoleActiveDialog" data-href="activeUserRole?id=${role.id}"> <span
															class="fa-stack"> <i
																class="fa fa-square fa-stack-2x"></i> <i
																class="fa fa-check fa-stack-1x fa-inverse"></i>
														</span>
														</a>
													</c:otherwise>
												</c:choose>
													
													</td>
													</tr>
												</c:forEach>

											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- <footer id="footer-bar" class="row">
				<p id="footer-copyright" class="col-xs-12"></p>
			</footer> -->
		</div>
	</div>
	
	<!-- Modal -->
	<!-- <div id="myModal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			Modal content
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Delete Action</h4>
				</div>
				<div class="modal-body" id="process"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a class="btn btn-danger btn-ok">Delete</a>
				</div>
			</div>
		</div>
	</div> -->
	
	<!-- Modal window for activation -->
	<div id="userRoleActiveDialog" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Activate User Role</h4>
				</div>
				<div class="modal-body" id="userRoleActive"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a class="btn btn-success btn-ok">Activate</a>
				</div>
			</div>

		</div>
	</div>
	
	<!-- Modal window for de-activation -->
	<div id="userRoleDeactiveDialog" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Deactivate User Role</h4>
				</div>
				<div class="modal-body" id="userRoleDeactive"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a class="btn btn-danger btn-ok">Deactivate</a>
				</div>
			</div>

		</div>
	</div>
	
	<!-- View Role Information -->
	<div id="roleInformation" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header" style="height: 43px;">
					<h1 class="pull-left"
						style="margin: -4px 10px 9px -12px; font-size: 154%;">
						<span>Role Details</span>
					</h1>
					<button type="button" id="button" class="close"
						data-dismiss="modal">×</button>

				</div>
				<div class="modal-body">
					<table class="table table-striped table-bordered" style="font-weight: bold;">
						<tbody>
							<tr>
								<td>Role Name</td>
								<td><label id="name" style="font-weight: normal;"></label></td>
							</tr>
							<tr>
								<td>Role Description</td>
								<td><label id="description" style="font-weight: normal;"></label></td>
							</tr>
							<tr>
								<td>Role Status</td>
								<td><label id="status" style="font-weight: normal;"></label></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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