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


<body onload="loadAllOutcome()">
	<%@include file="../include/menu.jsp"%>

	<div class="container">
		<div align="left">
			<b>Outcome List</b>
		</div>
		<hr />
			<table id="outcomeList"
				class="table table-striped table-bordered table-hover" width="100%"
				cellspacing="0">
				 <tbody>

					<c:forEach items="${outcomeList}" var="outcome">
						<tr>
							<td><c:out value="${outcome.sequenceNumber}" /></td>
							<td><c:out value="${outcome.name}" /></td>
							<td><c:out value="${outcome.status}" /></td>
							<td><c:out value="${outcome.description}" /></td>
							<td><c:out value="${outcome.theme.name}" /></td>
							<td>
							<%-- <c:if test="${USR_EDT == true }"> --%>
								<a href="editOutcome?id=<c:out value='${outcome.id}'/>"><img
									src="<c:url value="/static/images/edit.png" />" alt="Edit"
									title="Edit Outcome"></a><%-- </c:if> --%>
							<%-- <c:if test="${USR_DEL == true }"> --%>
								 <a href="deleteOutcome?id=<c:out value='${outcome.id}'/>" 
								onclick="return confirm('Are you sure to delete Outcome record  :  ${outcome.name}');"><img
									src="<c:url value="/static/images/delete.png" />" alt="Delete"
									title="Delete Outcome"></a><%-- </c:if> --%>
							</td>
						</tr>
					</c:forEach>

				</tbody> 
			</table>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#tabs > li').removeClass('active');
			$('#usertab').attr('class', 'active');
			/* $('#logouttab').show(); */

		});
	</script>
</body>
</html>