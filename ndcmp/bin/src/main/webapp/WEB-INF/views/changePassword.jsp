<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="include/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UNODC</title>
<%@include file="include/commonlib.jsp"%>
<script type="text/javascript" src='<c:url value="/static/scripts/menu.js"></c:url>'></script>
<script src="<c:url value="/static/js/user.js" />"></script>

</head>


<body>
	<div id="theme-wrapper">
		<%@include file="include/menu.jsp"%>
		<div id="page-wrapper" class="container">
			<%@include file="include/menuItems.jsp"%>
			<div id="content-wrapper">
				<div class="row">
					<div class="col-lg-12">
						<div class="main-box">
							<div class="main-box-body clearfix">
								<div class="container">
								<input type="hidden" id="roleId" value="<%= session.getAttribute("roleId") %>" />
									<div align="left">
										<h1>Change Password</h1>
									</div>
									<hr />
									<div>
										<form:form id="changepasswd" cssClass="form-horizontal"
											modelAttribute="changePassword" method="post"
											action="saveChangePass">
											<c:if test="${msg == 1}">
												<div class="alert alert-danger">
													<p>Failed to update Password.</p>
												</div>
											</c:if>
											<c:if test="${msg == 2}">
												<div class="alert alert-success">
													<p>Password has been updated successfully.</p>
												</div>
											</c:if>
											<c:if test="${msg == 3 }">
												<div class="alert alert-danger">
													<p>New password cannot be same as old password</p>
												</div>
											</c:if>

											<div class="form-group">
												<div class="control-label col-xs-3">
													<form:label path="currentPassword">Current Password&nbsp;<span
															style="color: red;">*</span>
													</form:label>
												</div>
												<div class="col-xs-2">
													<form:password cssClass="form-control"
														path="currentPassword" id="currentPassword"
														placeholder="Current Password" />
												</div>
												<div class="col-xs-3" style="padding-top: 8px;">
													<span style="color: #D00B05;">${passworderrormsg}</span>
												</div>

											</div>

											<div class="form-group">
												<div class="control-label col-xs-3">
													<form:label path="newPassword">New Password&nbsp;<span
															style="color: red;">*</span>
													</form:label>
												</div>
												<div class="col-xs-2">
													<form:password cssClass="form-control" path="newPassword"
														id="newPassword" placeholder="New Password" />
												</div>
												<div class="col-xs-3" style="padding-top: 8px;">
													<span style="color: #D00B05;">${errormsg2}</span>
												</div>
											</div>

											<div class="form-group">
												<div class="control-label col-xs-3">
													<form:label path="confirmPassword">Confirm Password&nbsp;<span
															style="color: red;">*</span>
													</form:label>
												</div>
												<div class="col-xs-2">
													<form:password cssClass="form-control"
														path="confirmPassword" id="confirmPassword"
														placeholder="Confirm Password" />
												</div>
											</div>
											<div class="form-group">
												<div class="row">
													<div class="col-xs-4"></div>
													<div class="col-xs-4">
														<input type="submit" id="saveProfile"
															class="btn btn-primary" value="Save"
															onclick="return submitUserForm();" /> <input
															type="button" id="cancel" value="Cancel" class="btn btn-default"
															onclick="cancelButton()" />
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
		</div>
	</div>


	<script type="text/javascript">
		$(document).ready(function() {
			$('#righttab > li').removeClass('active');
			$('#logoutTab').attr('class', 'active');
			$('#changeTab').attr('class', 'active');
		});

		$('#cancel').click(function() {
			window.history.back();
		});

		function submitUserForm() {
			$('#changepasswd')
					.formValidation(
							{
								icon : {
									valid : 'glyphicon glyphicon-ok',
									invalid : 'glyphicon glyphicon-remove',
									validating : 'glyphicon glyphicon-refresh'
								},
								fields : {
									currentPassword : {
										validators : {
											notEmpty : {
												message : '<span style="color:#D00B05">Current Password should not be empty</span>'
											}
										}
									},
									newPassword : {
										validators : {
											notEmpty : {
												message : '<span style="color:#D00B05">New Password should not be empty</span>'
											}
										}
									},
									confirmPassword : {
										validators : {
											notEmpty : {
												message : '<span style="color:#D00B05">Confirm Password should not be empty</span>'
											}
										/* ,
																					identical : {
																						field : 'newPassword',
																						message : 'The password and its confirm are not the same'
																					} */
										}
									}
								}
							});
			return true;
		}
	</script>
	
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