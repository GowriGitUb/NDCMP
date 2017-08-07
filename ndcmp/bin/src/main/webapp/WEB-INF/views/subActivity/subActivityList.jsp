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


<body onload="loadAllKeyActivity()">
	<%@include file="../include/menu.jsp"%>

	<div class="container">
		<div align="left">
			<b>Sub Activity List</b>
		</div>
		<br/>
			<table id="subActivityList"
				class="table table-striped table-bordered table-hover" width="100%"
				cellspacing="0">
				<tbody>
					<c:forEach items="${subActivities}" var="sub">
						<tr>
							<td><c:out value="${sub.sequenceNumber}" /></td>
							<td><c:out value="${sub.name}"/></td>
							<td><c:out value="${sub.keyActivity.name}"/></td>
							<td><c:out value = "${sub.leadAgency.name}"/></td>
							<td><c:out value = "${sub.partnerAgency.name}"/></td>
							<td><c:out value = "${sub.mov}"/></td>
							<td>
								<a href="getActivity?id=<c:out value='${sub.id}'/>">
									<img src="<c:url value="/static/images/edit.png" />" alt="Edit" title="Edit Sub Activity"></a>
								<a href="#" data-toggle="modal" data-target="#myModal" data-id=${sub.id } data-href="deleteKeyActivity?id=${sub.id}"> 
									<img src="<c:url value="/static/images/delete.png" />" alt="Delete"	title="Delete Sub Activity"></a>
							</td>					
						</tr>
					</c:forEach>
				</tbody>
			</table>
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
					//var getIdFromRow = $(event.relatedTarget).attr('data-id');
					// if you wnat to take the text of the first cell 
					var getIdFromRow = $(event.relatedTarget).find(
							'td:nth-child(3)').text();

					$(this).find('#process').html(
							$('<b> Are you sure to delete Sub Activity Information ? '
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
			$('#agencyTab').attr('class', 'active');
		});

	</script>
</body>
</html>