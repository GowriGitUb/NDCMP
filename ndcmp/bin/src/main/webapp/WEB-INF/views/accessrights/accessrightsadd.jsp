<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../include/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UNODC</title>
<%@include file="../include/commonlib.jsp"%>
<script src="<c:url value="/static/js/accessrights.js" />"></script>
<body>
	<%@include file="../include/menu.jsp"%>
	<div class="container">
		<div align="left">
			<b>Add / Edit Access Rights</b>
		</div>
		<hr />
		<div>
			<form:form id="accessrightsAddform" cssClass="form-horizontal"
				modelAttribute="accessRights" method="post" action="saveAccessRights">

				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="userRoleId">User Role</form:label>
					</div>
					<div class="col-xs-6">
						<form:hidden path="accessrightsId" value="${accessRights.accessrightsId}" />
						<form:select path="userRoleId" id="userRoleId" cssClass="form-control"  onchange="getfeatures()">
							<form:option value="" label="---Select Role---"></form:option>
							<c:forEach items="${listUserRoles}" var="role">
								<form:option value="${role.id}" label="${role.type}"></form:option>
							</c:forEach>
						</form:select>
					</div>
				</div>
				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="featuresId">Features</form:label>
					</div>
					<div class="row">
						<div class="col-xs-3">
							<input type="hidden" id="removed_fields" name="removed_fields">
							<select id="available" name="available" multiple size="10" class="form-control"
								ondblclick="javascript:moveSelected('available', 'assigned')">
								<c:forEach items="${listFeatures}" var="features">
									<option value="${features.featureId }">${features.featurename}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-xs-1">
							<div class="row">
								<input type="button"
									onclick="javascript:moveAll('available', 'assigned')"
									class="btn btn-default" value="&gt;&gt;">
							</div>
							<br>
							<div class="row">
								<input type="button"
									onclick="javascript:moveSelected('available', 'assigned')"
									class="btn btn-default" value=">" />
							</div>
							<br>
							<div class="row">
								<input type="button"
									onclick="moveSelected('assigned', 'available')"
									class="btn btn-default" value="&lt;">
							</div>
							<br>
							<div class="row">
								<input type="button" onclick="moveAll('assigned', 'available')"
									class="btn btn-default" value="&lt;&lt;">
							</div>
						</div>
						<div class="col-xs-3">
							<input type="hidden" id="added_fields" name="added_fields">
							<select id="assigned" name="assigned" class="form-control" multiple size="10"
								ondblclick="javascript:moveSelected('assigned', 'available')">
								<c:forEach items="${listFeatures}" var="features">
									<option value="${features.featureId }">${features.featurename}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="row">
						<div class="col-xs-4"></div>
						<div class="col-xs-4">
							<input type="submit" id="saveUserrole" class="btn btn-primary"
								value="Save" onclick="" /> 
							<input type="button" id="cancel" value="Cancel" class="btn btn-primary" />
						</div>
						<div class="col-xs-4"></div>
					</div>
				</div>

			</form:form>
		</div>
	</div>
</body>
<script type="text/javascript">
$('#cancel').click(function() {
	window.history.back();
});
</script>
</html>
