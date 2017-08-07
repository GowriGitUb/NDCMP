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
<script type="text/javascript" src='<c:url value="/static/scripts/userList.js"></c:url>'></script>
<script type="text/javascript">
$(document).ready(function(){
		$('#tabs > li').removeClass('active');
		$('#userTab').attr('class', 'active');
		
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


<%-- <%
	out.print("${pageContext.servletContext.contextPath}");
%>
 --%>

<body onload="advancedTable()">
	<div id="theme-wrapper">
		<%@include file="../include/menu.jsp"%>
		<div id="page-wrapper" class="container">
			<div class="row">
				<%@include file="../include/menuItems.jsp"%>
				<div id="content-wrapper">
					<div class="col-lg-12">
						<div class="main-box clearfix">
							<div class="main-box-body clearfix">
								<br />
								<h1 class="pull-left">
									<span>User</span>
								</h1>
								<div class="pull-right top-page-ui">
									<a href="createUser" class="btn btn-primary pull-right" title="Add User">
										<i class="fa fa-plus-circle fa-lg"></i> Add User
									</a>
								</div>
								<br />
								<hr />

								<div class="table-responsive">
									<table id="user-list" class="table table-hover table-bordered CSSTableGenerator">
										<thead>
											<tr>
												<th style="text-align: center;">#</th>
												<th style="text-align: center;">Name</th>
												<th style="text-align: center;">User Id</th>
												<th style="text-align: center;">Email</th>
												<th style="text-align: center;">User Role</th>
												<th style="text-align: center;">Agency</th>
												<!-- <th style="text-align: center;">Country</th>
												<th style="text-align: center;">State</th> -->
												<th class="text-center">Status</th>
												<th style="text-align: center;">Action</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${userList}" var="user"
												varStatus="recordCount">
												<tr>
													<td style="text-align: center;"><c:out value="${recordCount.count}" /></td>
													<td><c:out value="${user.firstName}" /> <c:out value="${user.lastName}" /></td>
													<td><c:out value="${user.username}" /></td>
													<td><c:out value="${user.email}" /></td>
													<td><c:forEach items="${user.userRoles}" var="role">
															<c:out value="${role.description}" />
														</c:forEach></td>
													<td><c:out value="${user.agencyName}" /></td>
													<%-- <td><c:out value="${user.country.name}" /></td>
													<td><c:out value="${user.state.name}" /></td> --%>
													<c:choose>
														<c:when test="${user.status == 'ACTIVE'}">
															<td class="text-center"><span class="label label-success"><c:out value="${user.status}" /></span></td>
														</c:when>
														<c:otherwise>
															<td class="text-center"><span class="label label-danger"><c:out value="${user.status}" /></span></td>
														</c:otherwise>
													</c:choose>
													<%-- <td><c:forEach items="${user.userRoles}" var="role">
															<c:if test="${role.name ne 'SUPER_ADMIN'}">
																<a href="editUser?id=<c:out value='${user.id}'/>"><img
																	src="<c:url value="/static/images/edit.png" />"
																	alt="Edit" title="Edit User"></a>
																<a href="#" data-toggle="modal" data-target="#myModal"
																	data-id=${user.id }
																	data-href="deleteUser?id=${user.id}"> <img
																	src="<c:url value="/static/images/delete.png" />"
																	alt="Delete" title="Delete User">
																</a>
															</c:if>
														</c:forEach></td> --%>
												<td style="width: 20%;text-align: center;">
													<c:forEach items="${user.userRoles}" var="role">
														<c:if test="${role.name ne 'SUPER_ADMIN'}">
													<a href="#" onclick="getUserInformation(${user.id})" class="table-link" title="View User">
														 <span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-search-plus fa-stack-1x fa-inverse"></i></span>
													</a> 
													<a href="editUser?id=<c:out value='${user.id}'/>" class="table-link" title="Edit User"> 
														<span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i></span>
													</a> <%-- <a href="#" data-toggle="modal" data-target="#myModal" data-id=${user.id } data-href="deleteUser?id=${user.id}" class="table-link danger" title="Delete"> 
													<span class="fa-stack"> 
														<i class="fa fa-square fa-stack-2x"></i> 
														<i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
													</span>
												</a>  --%>
												<c:choose>
													<c:when test="${user.status == 'ACTIVE'}">
														<a href="#" class="table-link danger" title="Deactivate User" data-id=${user.id } data-toggle="modal"	data-target="#userDeactiveDialog" data-href="deActiveUser?id=${user.id}">
															<span class="fa-stack"> <i
																class="fa fa-square fa-stack-2x"></i> <i
																class="fa fa-times fa-stack-1x fa-inverse"></i>
														</span>
														</a>
													</c:when>
													<c:otherwise>
														<a href="#" class="table-link" title="Activate User" data-id=${user.id } data-toggle="modal"	data-target="#userActiveDialog" data-href="activeUser?id=${user.id}"> <span
															class="fa-stack"> <i
																class="fa fa-square fa-stack-2x"></i> <i
																class="fa fa-check fa-stack-1x fa-inverse"></i>
														</span>
														</a>
													</c:otherwise>
												</c:choose>
														</c:if>
													</c:forEach>
												</td>
												</tr>
												<%-- <c:if test="${USR_EDT == true }">
													<c:forEach items="${user.userRoles}" var="role">
														<c:if test="${role.name ne 'SUPER_ADMIN'}">
															<a href="editUser?id=<c:out value='${user.id}'/>"><img
																src="<c:url value="/static/images/edit.png" />"
																alt="Edit" title="Edit User"></a>
														</c:if>
														<c:if test="${USR_DEL == true }">
															<a href="#" data-toggle="modal" data-target="#myModal"
																data-id=${user.id } data-href="deleteUser?id=${user.id}">
																<img src="<c:url value="/static/images/delete.png" />"
																alt="Delete" title="Delete User">
															</a>
														</c:if>
													</c:forEach>
												</c:if> --%>
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
	</div>
	
	<div id="config-tool" class="closed">
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
			 <br />
			<!-- <h4>Skin Color</h4>
			<ul id="skin-colors" class="clearfix">
				<li><a class="skin-changer" data-skin="" data-toggle="tooltip"
					title="Default" style="background-color: #34495e;"> </a></li>
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
	<div id="userActiveDialog" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Activate User</h4>
				</div>
				<div class="modal-body" id="userActive"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a class="btn btn-success btn-ok">Activate</a>
				</div>
			</div>

		</div>
	</div>
	
	<!-- Modal window for de-activation -->
	<div id="userDeactiveDialog" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Deactivate User</h4>
				</div>
				<div class="modal-body" id="userDeactive"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a class="btn btn-danger btn-ok">Deactivate</a>
				</div>
			</div>

		</div>
	</div>
	
	<div id="userInformation" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header" style="height: 43px;">
					<h1 class="pull-left"
						style="margin: -4px 10px 9px -12px; font-size: 154%;">
						<span>User Details</span>
					</h1>
					<button type="button" id="button" class="close"
						data-dismiss="modal">×</button>

				</div>
				<div class="modal-body">
					<table class="table table-striped table-bordered" style="font-weight: bold;">
						<tbody>
							<tr>
								<td>Name</td>
								<td><label id="name" style="font-weight: normal;"></label></td>
							</tr>
							<tr>
								<td>User Name</td>
								<td><label id="username" style="font-weight: normal;"></label></td>
							</tr>
							<tr>
								<td>Email Address</td>
								<td><label id="emailAddress" style="font-weight: normal;"></label></td>
							</tr>
							<tr>
								<td>Contact Number</td>
								<td><label id="phone" style="font-weight: normal;"></label></td>
							</tr>
							<tr>
								<td>Address1</td>
								<td><label id="address1" style="font-weight: normal;"></label></td>
							</tr>
							<tr>
								<td>Address2</td>
								<td><label id="address2" style="font-weight: normal;"></label></td>
							</tr>
							<tr>
								<td>Address3</td>
								<td><label id="address3" style="font-weight: normal;"></label></td>
							</tr>
							<tr>
								<td>User Type</td>
								<td><label id="userType" style="font-weight: normal;"></label></td>
							</tr>
							<tr>
								<td>Country</td>
								<td><label id="country" style="font-weight: normal;"></label></td>
							</tr>
							<tr>
								<td>State</td>
								<td><label id="state" style="font-weight: normal;"></label></td>
							</tr>
							<tr>
								<td>Zip Code</td>
								<td><label id="zipcode" style="font-weight: normal;"></label></td>
							</tr>
							<tr>
								<td>Status</td>
								<td><label id="status" style="font-weight: normal;"></label></td>
							</tr>
							<tr>
								<td>User Role</td>
								<td><label id="userRoles" style="font-weight: normal;"></label></td>
							</tr>
							<tr>
								<td>Agency</td>
								<td><label id="agencyName" style="font-weight: normal;"></label></td>
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