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
<script type="text/javascript">
	$(document).ready(function() {
		$('#tabs > li').removeClass('active');
		$('#configurationTab').attr('class', 'active');
		$('#allowdDeviceTab').attr('class', 'active');
	});
</script>
</head>
<body onload="advancedTable()">
	<div id="theme-wrapper">
		<%@include file="../include/menu.jsp"%>
		<%@include file="../include/menuItems.jsp"%>
		<div id="page-wrapper" class="container">
				<div class="row">
				<div id="content-wrapper">
					<div class="col-lg-12">
						<div class="main-box clearfix">
							<div class="main-box-body clearfix">
								<br />
								<h1 class="pull-left">
									<span> Mobile / TAB Registration <%-- ( ${allowedDeviceCount } ) --%></span>
								</h1>
								<br /> <br />
								<hr />
								<c:if test="${allowedDeviceMsg  != null}">
									<span style="color: red;">${allowedDeviceMsg }</span>
									<br />
									<br />
								</c:if>

								<div class="table-responsive">
									<table id="agency-list"
										class="table table-hover table-bordered CSSTableGenerator">
										<thead>
											<tr>
												<th style="text-align: center;">#</th>
												<th style="text-align: center;">Agency</th>
												<th style="text-align: center;">Staff Id</th>
												<th style="text-align: center;">Mobile Name</th>
												<th style="text-align: center;">OS Version</th>
												<th style="text-align: center;">SDK Version</th>
												<th style="text-align: center;">Status</th>
												<th style="text-align: center;">Action</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${listAllowedDevice}" var="device" varStatus="recordCount">
												<tr>
													<td style="text-align: center;"><c:out value ="${recordCount.count }"/></td>
													<td><c:out value="${device.agencyCodes}" /></td>
													<td><c:out value="${device.staffId}" /></td>
													<td><c:out value="${device.mobileName}" /></td>
													<td><c:out value="${device.osVersion}" /></td>
													<td><c:out value="${device.sdkVersion}" /></td>
													<c:if test="${device.deviceStatus == true}">
														<td class="text-center"><span class="label label-success">Approved</span></td>
													</c:if>
													<c:if test="${device.deviceStatus == false}">
														<td class="text-center"><span class="label label-warning">Waiting for Approval</span></td>
													</c:if>
													<td style="text-align: center;">
														<c:if test="${device.deviceStatus == false}">
																<a href="#" class="table-link" title="Activate Device"
																	data-id=${device.id } data-toggle="modal"
																	data-target="#deivceApprovedDialog"
																	data-href="approveDevice?id=${device.id}"> <span
																	class="fa-stack"> <i
																		class="fa fa-square fa-stack-2x"></i> <i
																		class="fa fa-check fa-stack-1x fa-inverse"></i>
																</span>
																</a>
															</c:if> 
															
															<c:if test="${device.deviceStatus == true}">
																<a href="#" class="table-link danger" title="Deactivate Device"
																	data-id=${device.id } data-toggle="modal"
																	data-target="#deivceDisApprovedDialog"
																	data-href="disApproveDevice?id=${device.id}"> <span
																	class="fa-stack"> <i
																		class="fa fa-square fa-stack-2x"></i> <i
																		class="fa fa-times fa-stack-1x fa-inverse"></i>
																</span>
																</a>
															</c:if> 
															
														<a href="#" data-toggle="modal" data-target="#deleteDeviceDialog"
															data-id=${device.id }
															data-href="deleteDevice?id=${device.id}"
															class="table-link danger" title="Delete Device"> <span
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

	<!-- Modal window for activation -->
	<div id="deivceApprovedDialog" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Activate Device</h4>
				</div>
				<div class="modal-body" id="deviceApprove"></div>
				<div class="modal-footer">
					<a class="btn btn-success btn-ok">Activate</a>
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				</div>
			</div>

		</div>
	</div>

	<!-- Modal window for de-activation -->
	<div id="deivceDisApprovedDialog" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Deactivate Device</h4>
				</div>
				<div class="modal-body" id="disApproveDevice"></div>
				<div class="modal-footer">
					<a class="btn btn-danger btn-ok">Deactivate</a>
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				</div>
			</div>

		</div>
	</div>


	<div id="deleteDeviceDialog" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Delete Device</h4>
				</div>
				<div class="modal-body" id="deleteDevice"></div>
				<div class="modal-footer">
					<a class="btn btn-danger btn-ok">Delete</a>
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				</div>
			</div>

		</div>
	</div>

	<script type="text/javascript">
		$('#deivceApprovedDialog').on(
				'show.bs.modal',
				function(event) {
					var getIdFromRow = $(event.relatedTarget).find(
							'td:nth-child(3)').text();
					$(this).find('#deviceApprove').html(
							$('<b> Are you sure to Activate the Device ? '
									+ getIdFromRow + '</b>'));
				});
		$('#deivceApprovedDialog').on(
				'show.bs.modal',
				function(e) {
					$(this).find('.btn-ok').attr('href',
							$(e.relatedTarget).data('href'));
				});

		/* device dis approved modal window */
		$('#deivceDisApprovedDialog').on(
				'show.bs.modal',
				function(event) {
					var getIdFromRow = $(event.relatedTarget).find(
							'td:nth-child(3)').text();
					$(this).find('#disApproveDevice').html(
							$('<b> Are you sure to Deactivate the Device ? '
									+ getIdFromRow + '</b>'));
				});
		$('#deivceDisApprovedDialog').on(
				'show.bs.modal',
				function(e) {
					$(this).find('.btn-ok').attr('href',
							$(e.relatedTarget).data('href'));
				});

		$('#deleteDeviceDialog').on(
				'show.bs.modal',
				function(event) {
					var getIdFromRow = $(event.relatedTarget).find(
							'td:nth-child(3)').text();
					$(this).find('#deleteDevice').html(
							$('<b> Are you sure to Delete the Device? '
									+ getIdFromRow + '</b>'));
				});
		$('#deleteDeviceDialog').on(
				'show.bs.modal',
				function(e) {
					$(this).find('.btn-ok').attr('href',
							$(e.relatedTarget).data('href'));
				});

		$(document).ready(function() {
			$('#tabs > li').removeClass('active');
			$('#configTab').attr('class', 'active');
			$('#allowdDeviceTab').attr('class', 'active');
			
			$('#agency-list').dataTable({
				"aoColumnDefs" : [ {
		            'bSortable' : false,
		            'aTargets' : [ 7 ]
		       }],
		       "pagingType": "full_numbers"
			});
			
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