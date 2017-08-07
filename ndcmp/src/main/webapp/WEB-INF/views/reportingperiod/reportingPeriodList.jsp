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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src='<c:url value="/static/jslib/bootstrap-datepicker.js"></c:url>' ></script>
<script type="text/javascript" src='<c:url value="/static/scripts/reportingPeriod.js"></c:url>'></script>
<script type="text/javascript" src="../static/scripts/menu.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#tabs > li').removeClass('active');
	$('#configurationTab').attr('class', 'active');
	$('#reportingTab').attr('class','active');
});
</script>
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
								<input type="hidden" id="roleId" value="<%= session.getAttribute("roleId") %>" />
									<br />
									<h1 class="pull-left">
										<span>Reporting Period</span>
									</h1>
									<div class="pull-right top-page-ui">
										<a href="createReportingPeriod?reportingPeriodId=0"
											class="btn btn-primary pull-right" title="Add reporting Period"> <i
											class="fa fa-plus-circle fa-lg"></i> Add Reporting Period
										</a>
									</div>
									<br />
									<br/>
									<hr />
									<div class="table-responsive">
										<table id="reportingPeriod-list"
											class="table table-hover table-bordered CSSTableGenerator" width:"96%">
											<thead>
												<tr>
													<th style="text-align: center;">#</th>
													<th style="text-align: center;">Year</th>
													<th style="text-align: center;">Quarter</th>
													<th style="text-align: center;">Start Date</th>
													<th style="text-align: center;">End Date</th>
													<th style="text-align: center;">Reporting Period</th>
													<th style="text-align: center;">Action</th>
												</tr>
											</thead>
											<tbody>

												<c:forEach items="${reportinglist}" var="list"
													varStatus="recordCount">
													<c:if test="${list.name ne null}">

														<tr>
															<td style="text-align: center;"><c:out value="${recordCount.count}" /></td>
															<td style="text-align: center;"><c:out value="${list.year}" /></td>
															<td style="text-align: center;"><c:out value="${list.name}" /></td>
															<td style="text-align: center;"><fmt:formatDate value="${list.startdate}" pattern="dd-MM-yyyy" /></td>
															<td style="text-align: center;"><fmt:formatDate value="${list.enddate}" pattern="dd-MM-yyyy" /></td>
															<td style="text-align: center;"><c:out value="${list.reportingStatus}" /></td> 
															<%-- <td><c:out value="${list.enddate}" /></td>--%>
															<%-- <c:choose>
																<c:when test="${list.status == 'ACTIVE'}">
																	<td class="text-center"><span
																		class="label label-success"><c:out
																				value="${list.status}" /></span></td>
																</c:when>
																<c:otherwise>
																	<td class="text-center"><span
																		class="label label-danger"><c:out
																				value="${list.status}" /></span></td>
																</c:otherwise>
															</c:choose> --%>
															<td style="text-align: center;">
															
															<c:choose>
																<c:when test="${list.reportingStatus == 'Closed' || list.reportingStatus == 'Open'}">
																	<a href="#" onclick="getReportPeriodInfo(${list.id})" class="table-link" title="View reporting period"> 
																	<span class="fa-stack" style="margin-left: -35px;"> 
																	<i class="fa fa-square fa-stack-2x"></i>
																	 <i class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
															</span>
															</a>
																</c:when>
																<c:otherwise>
																<a href="#" onclick="getReportPeriodInfo(${list.id})" class="table-link" title="View reporting period"> <span
																class="fa-stack"> <i
																	class="fa fa-square fa-stack-2x"></i> <i
																	class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
															</span>
															</a>
																<a href="createReportingPeriod?reportingPeriodId=<c:out value='${list.id}'/>"
																	class="table-link" title="Edit reporting period">
																	<span class="fa-stack"> 
																	<i class="fa fa-square fa-stack-2x"></i> 
																	<i class="fa fa-pencil fa-stack-1x fa-inverse"></i>
																	</span>
																</a>
																	</c:otherwise>
															</c:choose>
															
															<%-- <c:choose>
																	<c:when test="${list.status == 'ACTIVE'}">
																		<a href="#" class="table-link danger"
																			title="Deactivate Reporting Period" data-id=${list.id }
																			data-toggle="modal" data-target="#reportingDeactiveDialog"
																			data-href="deActiveReporting?id=${list.id}"> <span
																			class="fa-stack"> <i
																				class="fa fa-square fa-stack-2x"></i> <i
																				class="fa fa-times fa-stack-1x fa-inverse"></i>
																		</span>
																		</a>
																	</c:when>
																	<c:otherwise>
																		<a href="#" class="table-link" title="Activate Reporting Period"
																			data-id=${list.id } data-toggle="modal"
																			data-target="#reportingActiveDialog"
																			data-href="activeReporting?id=${list.id}"> <span
																			class="fa-stack"> <i
																				class="fa fa-square fa-stack-2x"></i> <i
																				class="fa fa-check fa-stack-1x fa-inverse"></i>
																		</span>
																		</a>
																	</c:otherwise>
																</c:choose> --%>
																<c:if test="${list.reportingStatus == 'Open'}">
																	<%-- <a href="#" class="table-link" title="Closeing Reporting Period"
																			data-id=${list.id } data-toggle="modal"
																			data-target="#reportingCloseDialog"
																			data-href="closeReporting?id=${list.id}">
																			<span class="fa-stack" style="margin: -12px -30px -12px 1px;"> 
																			  <i class="fa fa-square fa-stack-2x"></i>
																			  <i class="glyphicon glyphicon-folder-close" style="color: white;margin-top: 8px;"></i>
																		</span>
																		</a> --%>
																		<a onclick="showCloseReportingConfirmation(this.id,${list.id})" id="${list.year} - ${list.name}" class="table-link" title="Closeing Reporting Period">
																			<span class="fa-stack" style="margin: -12px -30px -12px 1px; cursor: pointer;"> 
																			  <!-- <i class="fa fa-square fa-stack-2x"></i> -->
																			  <!-- <i class="glyphicon glyphicon-folder-close" style="color: white;margin-top: 8px;"></i> -->
																			  <img src="<c:url value="/static/img/report_close.png" />" alt="Close Reporting Period" title="Close reporting period" style="width: 26px;margin-top: 3px;">
																		</span>
																		</a>
																</c:if>
																</td>
														</tr>
													</c:if>
												</c:forEach>

											</tbody>
												
										</table>
										<%-- </c:if> --%>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	<!-- Modal window for close status -->
	<div id="reportingCloseDialog" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title"><b>Reporting Period Close Confirmation</b></h4>
				</div>
				<div class="modal-body" id="ReportingClose"></div>
				<div class="modal-footer">
					<a class="btn btn-success btn-ok" onclick="colseStatus()">Ok</a>
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					
				</div>
			</div>

		</div>
	</div>
	
	<!-- Modal window for activation -->
	<!-- <div id="reportingActiveDialog" class="modal fade" role="dialog">
		<div class="modal-dialog">

			Modal content
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Reporting Period Activation</h4>
				</div>
				<div class="modal-body" id="ReportingClose"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a class="btn btn-success btn-ok">Active</a>
				</div>
			</div>

		</div>
	</div> -->
	
	<!-- Modal window for de-activation -->
	<!-- <div id="reportingDeactiveDialog" class="modal fade" role="dialog">
		<div class="modal-dialog">

			Modal content
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Deactivate Reporting Period</h4>
				</div>
				<div class="modal-body" id="ReportingDeactive"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a class="btn btn-danger btn-ok">Deactive</a>
				</div>
			</div>

		</div>
	</div> -->
	
	<!-- Modal window for View Reporting Period -->
	<div id="reportPeriodInformation" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<!-- <div class="modal-header">
				<h1 class="pull-left" style="margin: -12px 10px -4px -22px;font-size: 154%;"> <span>Reporting Period Details</span> </h1>
					<button type="button" id="button" class="close" data-dismiss="modal">&times;</button>
					
				</div> -->
				<div class="modal-header" style="height: 43px;">
					<h1 class="pull-left"
						style="margin: -4px 10px 9px -12px; font-size: 154%;">
						<span>Reporting Period Details</span>
					</h1>
					<button type="button" id="button" class="close"
						data-dismiss="modal">×</button>

				</div>
				<div class="modal-body">
					<table class="table table-striped table-bordered" style="font-weight: bold;">
						<tbody>
							
							<tr>
								<td>Year	</td>
								<td><label id="reportYear" style="font-weight: normal;"></label></td>
							</tr>
							<tr>
								<td>Name</td>
								<td><label id="reportName" style="font-weight: normal;"></label></td>
							</tr>
							<tr>
								<td>Start Date	</td>
								<td><label id="reportStartDate" style="font-weight: normal;"></label></td>
							</tr>
							<tr>
								<td>End Date	</td>
								<td><label id="reportEndDate" style="font-weight: normal;"></label></td>
							</tr>
							<tr>
								<td>Reporting Period Status</td>
								<td><label id="periodStatus" style="font-weight: normal;"></label></td>
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
	
	
	<script type="text/javascript">
		 $(document).ready(function() {
			 var table = $('#table-example').DataTable( {
			    });
			 $('a.toggle-vis').on( 'click', function (e) {
			        e.preventDefault();
			 
			        // Get the column API object
			        var column = table.column( $(this).attr('data-columnindex') );
			 
			        // Toggle the visibility
			        column.visible( ! column.visible() );
			    } );
			
			$('#reportingPeriod-list').dataTable({
			 "aoColumnDefs" : [ {
		            'bSortable' : false,
		            'aTargets' : [ 6 ]
		       }],
		       "pagingType": "full_numbers"
		});	
		});
		
		/* $('#reportingActiveDialog').on(
				'show.bs.modal',
				function(event) {
					var getIdFromRow = $(event.relatedTarget).find(
							'td:nth-child(3)').text();
					$(this).find('#ReportingActive').html(
							$('<b> Are you sure to Active Reporting Period ? '
									+ getIdFromRow + '</b>'));
				});
		$('#reportingActiveDialog').on(
				'show.bs.modal',
				function(e) {
					$(this).find('.btn-ok').attr('href',
							$(e.relatedTarget).data('href'));
				}); */
		
		/* device dis approved modal window */
		
		/* $('#reportingDeactiveDialog').on(
				'show.bs.modal',
				function(event) {
					var getIdFromRow = $(event.relatedTarget).find(
							'td:nth-child(3)').text();
					$(this).find('#ReportingDeactive').html(
							$('<b> Are you sure to Deactive Reporting Period ? '
									+ getIdFromRow + '</b>'));
				});
		$('#reportingDeactiveDialog').on(
				'show.bs.modal',
				function(e) {
					$(this).find('.btn-ok').attr('href',
							$(e.relatedTarget).data('href'));
				}); */
		
				
				/* Reporting period close modal window */
			/*  $('#reportingCloseDialog').on(
					'show.bs.modal',
					function(event) {
						var getIdFromRow = $(event.relatedTarget).find('td:nth-child(3)').text();
						$(this).find('#ReportingClose').html(
								$('<b> Are you sure to Close Reporting Period ? ' + getIdFromRow + '</b>'));
					});
			$('#reportingCloseDialog').on(
					'show.bs.modal',
					function(e) {
						$(this).find('.btn-ok').attr('href',
								$(e.relatedTarget).data('href'));
					});  */
		
		
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