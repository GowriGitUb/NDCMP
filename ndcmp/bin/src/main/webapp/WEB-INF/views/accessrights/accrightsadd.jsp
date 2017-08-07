<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../include/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UNODC</title>
<%@include file="../include/commonlib.jsp"%>
<body>
	<%@include file="../include/menu.jsp"%>
	<div class="container">
		<div align="left">
			<b>Add / Edit Access Rights</b>
		</div>
		<hr />
		<div>
			<form:form id="accessrightsAddform" cssClass="form-horizontal"
				modelAttribute="accessRights" method="post" action="saveAccRights">

				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="userRoleId">User Role</form:label>
					</div>
					<div class="col-xs-6">
						<form:hidden path="accessrightsId" value="${accessRights.accessrightsId}" />
						<form:select path="userRoleId" id="userRoleId" cssClass="form-control">
							<form:option value="" label="---Select Role---"></form:option>
							<c:forEach items="${listUserRoles}" var="role">
								<form:option value="${role.id}" label="${role.type}"></form:option>
							</c:forEach>
						</form:select>
					</div>
				</div>

				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="modules">Feature</form:label>
					</div>
					<div class="col-xs-6">
						<form:select path="modules" id="modules" cssClass="form-control">
							<form:options items="${featureslist}"/>
						</form:select>
					</div>
				</div>
				
				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="canInsert">Insert</form:label>
					</div>
					<div class="col-xs-6">
						<form:checkbox path="canInsert" id="canInsert" />
					</div>
				</div>
				
				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="canView">View</form:label>
					</div>
					<div class="col-xs-6">
						<form:checkbox path="canView" id="canView" />
					</div>
				</div>
				
				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="canUpdate">Update</form:label>
					</div>
					<div class="col-xs-6">
						<form:checkbox path="canUpdate" id="canUpdate" />
					</div>
				</div>
				
				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="canDelete">Delete</form:label>
					</div>
					<div class="col-xs-6">
						<form:checkbox path="canDelete" id="canDelete" />
					</div>
				</div>
				

				<div class="form-group">
					<div class="row">
						<div class="col-xs-4"></div>
						<div class="col-xs-4">
							<input type="submit" id="saveUserrole" class="btn btn-primary"
								value="Save" onclick="return submitRoleForm();" /> <input
								type="button" id="cancel" value="Cancel" class="btn btn-primary" />
						</div>
						<div class="col-xs-4"></div>
					</div>
				</div>

			</form:form>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#tabs > li').removeClass('active');
			$('#accrightstab').attr('class', 'active');
		});

		$('#cancel').click(function() {
			window.history.back();
		});

		function submitRoleForm() {
			$('#accessrightsAddform')
					.formValidation(
							{
								icon : {
									valid : 'glyphicon glyphicon-ok',
									invalid : 'glyphicon glyphicon-remove',
									validating : 'glyphicon glyphicon-refresh'
								},
								fields : {
									
								}
							});
			return true;
		};
	</script>
</body>
</html>
