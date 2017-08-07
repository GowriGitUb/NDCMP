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
</head>


<%
	out.print("${pageContext.servletContext.contextPath}");
%>


<body onload="loadAllProject(${projectCount})">
	<%@include file="../include/menu.jsp"%>

	<div class="container">
		<div align="left">
			<b>Project List</b>
		</div>
		<hr />
		<%-- <c:if test="${empty projectList}">
                There are no Projects
            </c:if> --%>
		<%-- <c:if test="${not empty projectList}"> --%>
			<table id="projectsList"
				class="table table-striped table-bordered table-hover" width="100%"
				cellspacing="0">
				 <tbody>
				<c:forEach items="${projectList}" var="project" varStatus="recordCount">
					<tr>
						<td><c:out value="${recordCount.count}" /></td>
						<td><c:out value="${project.name}" /></td>
						<td><c:out value="${project.admin}" /></td>
						<td><c:out value="${project.country.name}" /></td>
						<td><c:out value="${project.states.name}" /></td>
						<td><c:out value="${project.region.name}" /></td>
						<td><c:out value="${project.description}"></c:out></td>
						<td>
							<%-- <c:if test="${USR_EDT == true }"> --%> <a
							href="createProject?projectId=<c:out value='${project.id}'/>"><img
								src="<c:url value="/static/images/edit.png" />" alt="Edit"
								title="Edit Project"></a>
						<%-- </c:if>
							<c:if test="${USR_DEL == true }"> --%> 
							<%-- <a href="deleteProject?projectId=<c:out value='${project.id}'/>"
							onclick="return confirm('Are you sure to delete State record  :  ${user.firstName}');"><img
								src="<c:url value="/static/images/delete.png" />" alt="Delete"
								title="Delete User">
								</a> --%>
								<a href="#" data-toggle="modal" data-target="#myModal" data-id=${project.id } data-href="deleteProject?projectId=${project.id}"> 
									<img src="<c:url value="/static/images/delete.png" />" alt="Delete"	title="Delete Project"></a>
							<a href="configProject?projectId=<c:out value='${project.id}'/>"><span class="glyphicon glyphicon-cog"></span></a>
						<%-- </c:if> --%>
						</td>
					</tr>
				</c:forEach>

				</tbody> 
			</table>
		<%-- </c:if> --%>
	</div>
	
	<!-- Modal -->
	<div id="myModal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
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
	</div>

	<script type="text/javascript">
		$('#myModal').on(
				'show.bs.modal',
				function(event) {
					var getIdFromRow = $(event.relatedTarget).find(
							'td:nth-child(3)').text();

					$(this).find('#process').html(
							$('<b> Are you sure to delete Project Information ? '
									+ getIdFromRow + '</b>'));
				});

		$('#myModal').on(
				'show.bs.modal',
				function(e) {
					$(this).find('.btn-ok').attr('href',
							$(e.relatedTarget).data('href'));
				});
	</script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$('#tabs > li').removeClass('active');
			$('#configTab').attr('class', 'active');
			$('#projectsTab').attr('class', 'active');
			/* $('#logouttab').show(); */

		});
	</script>
</body>
</html>