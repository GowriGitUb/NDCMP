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
$(document).ready(function(){
	$('#tabs > li').removeClass('active');
	$('#configurationTab').attr('class', 'active');
	$('#statusTab').attr('class','active');
});
</script>
<link href="<c:url value="/static/csslib/jquery.colorpickersliders.css" />"
	rel="stylesheet" type="text/css">
	
	<script type="text/javascript"
	src="<c:url value="/static/jslib/tinycolor.js" />"></script>
	
	<script type="text/javascript"
	src="<c:url value="/static/jslib/jquery.colorpickersliders.js" />"></script>
<script type="text/javascript" src='<c:url value="/static/scripts/status.js"></c:url>'></script>
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
									<span>Status</span>
								</h1>
								<c:if test="${statusCount < 4 }">
									<div class="pull-right top-page-ui">
										<a href="getStatus?id=0" class="btn btn-primary pull-right" title="Add Status">
											<i class="fa fa-plus-circle fa-lg"></i> Add Status
										</a>
									</div>
								</c:if>
								<br />
								<br/>
								<hr />

								<div class="table-responsive">
									<table id="status-list"
										class="table table-hover table-bordered CSSTableGenerator">
										<thead>
											<tr>
												<th style="text-align: center;">#</th>
												<th style="text-align: center;">Name</th>
												<th style="text-align: center;">Description</th>
												<th style="text-align: center;">Status Color</th>
												<th style="text-align: center;">Start Range</th>
												<th style="text-align: center;">End Range</th>
												<th style="text-align: center;">Status</th>
												<th style="text-align: center;">Action</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${statusList}" var="status"
												varStatus="recordCount">
												<tr>
													<td style="text-align: center;"><c:out value="${recordCount.count}" /></td>
													<td><c:out value="${status.name}" /></td>
													<td><c:out value="${status.description}" /></td>
													<td>
														<div
															style="width:50px; height:20px; background: ${status.statusColor};"></div>
													</td>
													<td style="text-align: right;">
														<c:out value="${status.startRange}"></c:out>
													</td>
													<td style="text-align: right;">
														<c:out value="${status.endRange}"></c:out>
													</td>
													<c:choose>
														<c:when test="${status.status == 'ACTIVE'}">
															<td class="text-center"><span class="label label-success"><c:out value="${status.status}" /></span></td>
														</c:when>
														<c:otherwise>
															<td class="text-center"><span class="label label-danger"><c:out value="${status.status}" /></span></td>
														</c:otherwise>
													</c:choose>
													<td style="text-align: center;">
														<%-- <c:if test="${COU_EDT == true }"> --%> <%-- <a
														href="getStatus?id=<c:out value='${status.id}'/>"> <span
															class="fa-stack"> <i
																class="fa fa-square fa-stack-2x"></i> <i
																class="fa fa-pencil fa-stack-1x fa-inverse"></i>
														</span>
													</a> <a href="#" data-toggle="modal" data-target="#myModal"
														data-id=${status.id }
														data-href="deleteStatus?id=${status.id }"
														class="table-link danger" title="Delete"> <span
															class="fa-stack"> <i
																class="fa fa-square fa-stack-2x"></i> <i
																class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
														</span></a> --%> 
														<a href="#" onclick="getStatusInformation(${status.id})" class="table-link" title="View Status"> <span
																class="fa-stack"> <i
																	class="fa fa-square fa-stack-2x"></i> <i
																	class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
															</span>
															</a>
														<a
														href="getStatus?id=<c:out value='${status.id}'/>" class="table-link" title="Edit Status"> <span
															class="fa-stack"> <i
																class="fa fa-square fa-stack-2x"></i> <i
																class="fa fa-pencil fa-stack-1x fa-inverse"></i>
														</span>
													</a>
														<c:choose>
															<c:when test="${status.status == 'ACTIVE'}">
																<a href="#" class="table-link danger" title="Deactivate Status"
																	data-id=${status.id } data-toggle="modal"
																	data-target="#userDeactiveDialog"
																	data-href="deActiveStatus?id=${status.id}"> <span
																	class="fa-stack"> <i
																		class="fa fa-square fa-stack-2x"></i> <i
																		class="fa fa-times fa-stack-1x fa-inverse"></i>
																</span>
																</a>
															</c:when>
															<c:otherwise>
																<a href="#" class="table-link" title="Activate Status"
																	data-id=${status.id } data-toggle="modal"
																	data-target="#userActiveDialog"
																	data-href="activeStatus?id=${status.id}"> <span
																	class="fa-stack"> <i
																		class="fa fa-square fa-stack-2x"></i> <i
																		class="fa fa-check fa-stack-1x fa-inverse"></i>
																</span>
																</a>
															</c:otherwise>
														</c:choose>


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
	<div id="userActiveDialog" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Activate Status</h4>
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
					<h4 class="modal-title">Deactivate Status</h4>
				</div>
				<div class="modal-body" id="userDeactive"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a class="btn btn-danger btn-ok">Deactivate</a>
				</div>
			</div>

		</div>
	</div>

<!-- Modal window for Status Agency -->
	<div id="statusInformation" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header" style="height: 43px;">
					<h1 class="pull-left"
						style="margin: -4px 10px 9px -12px; font-size: 154%;">
						<span>Status Details</span>
					</h1>
					<button type="button" id="button" class="close"
						data-dismiss="modal">×</button>

				</div>
				<div class="modal-body">
					<table class="table table-striped table-bordered" style="font-weight: bold;">
						<tbody>
							<tr>
								<td>Name</td>
								<td><label id="statusName" style="font-weight: normal;"></label></td>
							</tr>
							<tr>
								<td>Description</td>
								<td><label id="statusDesc" style="font-weight: normal;"></label></td>
							</tr>
							<tr>
								<td>Status Color</td>
								<td><div style="width:50px; height:20px;" id="color"></div></td>
							</tr>
							<tr>
								<td>State Range</td>
								<td><label id="statusStartRange" style="font-weight: normal;"></label></td>
							</tr>
							<tr>
								<td>End Range</td>
								<td><label id="statusEndRange" style="font-weight: normal;"></label></td>
							</tr>
							<tr>
								<td>Status</td>
								<td><label id="colorStatus" style="font-weight: normal;"></label></td>
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

	<%-- 
	<%@include file="../../include/menu.jsp"%>

	<div class="container">
		<div align="left">
			<b>Agency List</b>
		</div>
		<br/>
			<table id="agencyList"
				class="table table-striped table-bordered table-hover" width="100%"
				cellspacing="0">
				<tbody>
					<c:forEach items="${listAgencies}" var="agency" varStatus="recordCount">
						<tr>
							<td><c:out value="${recordCount.count}" /></td>
							<td><c:out value="${agency.name}" /></td>
							<td><c:out value="${agency.agencyType}"/></td>
							<td><c:out value="${agency.status}"/></td>
							<td>
								<a href="getAgency?id=<c:out value='${agency.id}'/>">
									<img src="<c:url value="/static/images/edit.png" />" alt="Edit" title="Edit Role"></a>
								<a href="#" data-toggle="modal" data-target="#myModal" data-id=${agency.id } data-href="deleteAgency?id=${agency.id}"> 
									<img src="<c:url value="/static/images/delete.png" />" alt="Delete"	title="Delete Agency"></a>
							</td>					
						</tr>
					</c:forEach>
				</tbody>
			</table>
	</div>
 --%>
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

<!-- 
	<script type="text/javascript">
	
	
	$(document).ready(function(){
		$('#status-list').dataTable({
			 "aoColumnDefs" : [ {
		            'bSortable' : false,
		            'aTargets' : [ 5 ]
		       }],
		       "pagingType": "full_numbers"
		});	
	});
	
		$('#myModal')
				.on(
						'show.bs.modal',
						function(event) {
							var getIdFromRow = $(event.relatedTarget).find(
									'td:nth-child(3)').text();

							$(this)
									.find('#process')
									.html(
											$('<b> Are you sure to delete Status Information ? '
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

		$('#userActiveDialog').on(
				'show.bs.modal',
				function(event) {
					var getIdFromRow = $(event.relatedTarget).find(
							'td:nth-child(3)').text();
					$(this).find('#userActive').html(
							$('<b> Are you sure to Active Status ? '
									+ getIdFromRow + '</b>'));
				});
		$('#userActiveDialog').on(
				'show.bs.modal',
				function(e) {
					$(this).find('.btn-ok').attr('href',
							$(e.relatedTarget).data('href'));
				});

		/* device dis approved modal window */
		$('#userDeactiveDialog').on(
				'show.bs.modal',
				function(event) {
					var getIdFromRow = $(event.relatedTarget).find(
							'td:nth-child(3)').text();
					$(this).find('#userDeactive').html(
							$('<b> Are you sure to Deactive Status ? '
									+ getIdFromRow + '</b>'));
				});
		$('#userDeactiveDialog').on(
				'show.bs.modal',
				function(e) {
					$(this).find('.btn-ok').attr('href',
							$(e.relatedTarget).data('href'));
				});

		$('#myModal').on(
				'show.bs.modal',
				function(event) {
					var getIdFromRow = $(event.relatedTarget).find(
							'td:nth-child(3)').text();

					$(this).find('#process').html(
							$('<b> Are you sure to delete User Information ? '
									+ getIdFromRow + '</b>'));
				});
		$('#myModal').on(
				'show.bs.modal',
				function(e) {
					$(this).find('.btn-ok').attr('href',
							$(e.relatedTarget).data('href'));
				});
	</script>
 -->
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