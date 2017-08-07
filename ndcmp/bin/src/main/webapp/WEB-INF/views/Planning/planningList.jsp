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
									<h1 class="pull-left">
										<span>Planning Details</span>
									</h1>
									<div class="pull-right top-page-ui">
										<a href="editPlanning?id=0" class="btn btn-success pull-right">
											<i class="fa fa-plus-circle fa-lg"></i> Add Planning
										</a>
									</div>
									<br /> <br />
									<hr />

									<div class="table-responsive">
										<table id="table-example"
											class="table table-hover table-bordered CSSTableGenerator">
											<thead>
												<tr>
													<th>#</th>
													<th>SubActivity</th>
													<th>Reporting Period</th>
													<th>Status Of Progress</th>
													<th>Action</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${planningList }" var="planning"
													varStatus="recordCount">
													<tr>
														<td><c:out value="${planning.count}" /></td>
														<td><c:out value="${planning.subActivity.name}" /></td>
														<td><c:out value="${planning.reportingPeriod.name}" /></td>
														<td><c:out value="${planning.statusOfProgress}" /></td>
														<td>
															<%-- <c:if test="${STT_EDT == true}"> --%> <a
															href="editPlanning?id=<c:out value='${planning.id}'/>"> <span
																class="fa-stack"> <i
																	class="fa fa-square fa-stack-2x"></i> <i
																	class="fa fa-pencil fa-stack-1x fa-inverse"></i>
															</span></a> <a href="#" data-toggle="modal" data-target="#myModal"
															data-id=${planning.id }
															data-href="deletePlanning?id=${planning.id }"
															class="table-link danger" title="Delete"> <span
																class="fa-stack"> <i
																	class="fa fa-square fa-stack-2x"></i> <i
																	class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
															</span></a>

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
		</div>
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
							$('<b> Are you sure to delete State Information ? '
									+ getIdFromRow + '</b>'));
				});

		$('#myModal').on(
				'show.bs.modal',
				function(e) {
					$(this).find('.btn-ok').attr('href',
							$(e.relatedTarget).data('href'));
				});
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