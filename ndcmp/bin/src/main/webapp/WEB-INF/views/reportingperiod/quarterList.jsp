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
	$('#quaterTab').attr('class','active');
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
										<span>Quarter</span>
									</h1>
									<div class="pull-right top-page-ui">
										<a href="createQuarter"
											class="btn btn-primary pull-right" title="Add Quarter"> <i
											class="fa fa-plus-circle fa-lg"></i> Add Quarter
										</a>
									</div>
									<br />
									<br/>
									<hr />
									<div class="table-responsive">
										<table id="quarter-list"
											class="table table-hover table-bordered CSSTableGenerator" width:"96%">
											<thead>
												<tr>
													<th style="text-align: center;">#</th>
													<th style="text-align: center;">Quarter Name</th>
													<th style="text-align: center;">Status</th>
													<th style="text-align: center;">Action</th>
												</tr>
											</thead>
											<tbody>

												<c:forEach items="${quarterList}" var="quarter" varStatus="recordCount">
														<tr>
															<td style="text-align: center;"><c:out value="${recordCount.count}" /></td>
															<td style="text-align: center;"><c:out value="${quarter.name}" /></td>
															<c:choose>
																<c:when test="${quarter.status == 'ACTIVE'}">
																	<td class="text-center"><span
																		class="label label-success"><c:out
																				value="${quarter.status}" /></span></td>
																</c:when>
																<c:otherwise>
																	<td class="text-center"><span
																		class="label label-danger"><c:out
																				value="${quarter.status}" /></span></td>
																</c:otherwise>
															</c:choose>
															<td style="text-align: center;">
															
														 <a href="editQuarter?id=<c:out value='${quarter.id}'/>" class="table-link" title="Edit Quarter">
																<span class="fa-stack"> <i
																	class="fa fa-square fa-stack-2x"></i> <i
																	class="fa fa-pencil fa-stack-1x fa-inverse"></i>
															</span>
														</a> 
														<c:choose>
															<c:when test="${quarter.status == 'ACTIVE'}">
																<a href="#" class="table-link danger" title="Deactivate Quarter" data-id=${quarter.id } data-toggle="modal"	data-target="#quarterDeactiveDialog" data-href="deactivateQuarter?id=${quarter.id}">
																	<span class="fa-stack"> <i
																		class="fa fa-square fa-stack-2x"></i> <i
																		class="fa fa-times fa-stack-1x fa-inverse"></i>
																</span>
																</a>
															</c:when>
															<c:otherwise>
																<a href="#" class="table-link" title="Activate Quarter" data-id=${quarter.id } data-toggle="modal"	data-target="#quarterActiveDialog" data-href="activateQuarter?id=${quarter.id}"> <span
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
	
		<!-- Modal window for activation -->
	<div id="quarterActiveDialog" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Activate Quarter</h4>
				</div>
				<div class="modal-body" id="quarterActive"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a class="btn btn-success btn-ok">Activate</a>
				</div>
			</div>

		</div>
	</div>
	
	<!-- Modal window for de-activation -->
	<div id="quarterDeactiveDialog" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Deactivate Quarter</h4>
				</div>
				<div class="modal-body" id="quarterDeactive"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a class="btn btn-danger btn-ok">Deactivate</a>
				</div>
			</div>

		</div>
	</div>
	
	<!-- Modal window for View Country -->
	<div id="countryInformation" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header" style="height: 43px;">
					<h1 class="pull-left"
						style="margin: -4px 10px 9px -12px; font-size: 154%;">
						<span>Country Details</span>
					</h1>
					<button type="button" id="button" class="close"
						data-dismiss="modal">×</button>

				</div>
				<div class="modal-body">
					<table class="table table-striped table-bordered" style="font-weight: bold;">
						<tbody>
							<tr>
								<td>Country Code</td>
								<td><label id="countryCode" style="font-weight: normal;"></label></td>
							</tr>
							<tr>
								<td>Country Name</td>
								<td><label id="countryName" style="font-weight: normal;"></label></td>
							</tr>
							<tr>
								<td>Status</td>
								<td><label id="countryStatus" style="font-weight: normal;"></label></td>
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
			
			 $('#quarter-list').dataTable({
				 "aoColumnDefs" : [ {
			            'bSortable' : false,
			            'aTargets' : [ 3 ]
			       } ],
			       "pagingType": "full_numbers"
			});
			 
			 $('#quarterActiveDialog').on('show.bs.modal',function(event) {
					var getIdFromRow = $(event.relatedTarget).find('td:nth-child(3)').text();
					$(this).find('#quarterActive').html(
					$('<b> Are you sure to Activate Quarter ? '+ getIdFromRow + '</b>'));
			});
			
			$('#quarterActiveDialog').on('show.bs.modal',function(e) {
					$(this).find('.btn-ok').attr('href',$(e.relatedTarget).data('href'));
			});
			
			/* device dis approved modal window */
			$('#quarterDeactiveDialog').on('show.bs.modal',function(event) {
					var getIdFromRow = $(event.relatedTarget).find('td:nth-child(3)').text();
					$(this).find('#quarterDeactive').html($('<b> Are you sure to Deactivate Quarter ? '+ getIdFromRow + '</b>'));
			});
			$('#quarterDeactiveDialog').on('show.bs.modal',function(e) {
					$(this).find('.btn-ok').attr('href',
					$(e.relatedTarget).data('href'));
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