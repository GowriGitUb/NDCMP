<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../include/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UNODC</title>
<%@include file="../include/commonlib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="/static/css/format.css" />
<%@include file="../include/dataTable.jsp"%>
</head>


<body onload="loadAccRights()">
	<%@include file="../include/menu.jsp"%>

	<div class="container">
		<div align="left">
			<b>Role List</b>
		</div>
		<br/>
			<table id="accrightsList"
				class="table table-striped table-bordered table-hover" width="100%"
				cellspacing="0">
				<tbody>

					<c:forEach items="${accessrightslist}" var="accessrights">
						<tr>
							<td><c:out value="${accessrights.userRole.type}" /></td>
							<td><c:out value="${accessrights.modules}"/></td>
							<td class=garislrtb align=middle>
								<c:choose>
									<c:when test="${accessrights.canInsert == true}">
										<input type="checkbox" name="canInsert" id="canInsert" checked="checked" readonly="readonly"/>
									</c:when>
									<c:otherwise>
										<input type="checkbox" name="canInsert" id="canInsert" readonly="readonly"/>
									</c:otherwise>
								</c:choose>
							</td>
							<td class=garislrtb align=middle>
								<c:choose>
									<c:when test="${accessrights.canView == true}"><input type="checkbox" name="canView" id="canView" checked="checked" readonly="readonly"/></c:when>									
									<c:otherwise><input type="checkbox" name="canView" id="canView" readonly="readonly"/></c:otherwise>
								</c:choose>
							</td>
							<td class=garislrtb align=middle>
								<c:choose>
									<c:when test="${accessrights.canUpdate == true}"><input type="checkbox" name="canUpdate" id="canUpdate" checked="checked" readonly="readonly"/></c:when>
									<c:otherwise><input type="checkbox" name="canUpdate" id="canUpdate" readonly="readonly"/></c:otherwise>
								</c:choose>
							</td>
							<td class=garislrtb align=middle>
								<c:choose>
									<c:when test="${accessrights.canDelete == true}"><input type="checkbox" name="canDelete" id="canDelete" checked="checked" readonly="readonly"/></c:when>
									<c:otherwise><input type="checkbox" name="canDelete" id="canDelete" readonly="readonly"/></c:otherwise>
								</c:choose>
							</td>
							<td><a href="getAccRightsList?accessrightsId=<c:out value='${accessrights.accessrightsId}'/>"><img
									src="<c:url value="/static/images/edit.png" />" alt="Edit"
									title="Edit Access Rights"></a> <a
								href="deleteAccRights?accessrightsId=<c:out value='${accessrights.accessrightsId}'/>" onclick="return confirm('Are you sure to delete access rights record  for :  ${accessrights.userRole.type}');"><img
									src="<c:url value="/static/images/delete.png" />" alt="Delete"
									title="Delete Role"></a>
						</tr>
					</c:forEach>

				</tbody>
			</table>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			$('#tabs > li').removeClass('active');
			$('#accrightstab').attr('class', 'active');
		});

	</script>
</body>
</html>