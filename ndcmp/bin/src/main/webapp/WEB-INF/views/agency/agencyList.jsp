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
<script type="text/javascript" src='<c:url value="/static/scripts/agency.js"></c:url>'></script>
<script type="text/javascript" src="../static/scripts/menu.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#tabs > li').removeClass('active');
	$('#configurationTab').attr('class', 'active');
	$('#agencyTab').attr('class','active');
});
</script>
</head>


<body onload="advancedTable()">


	<div id="theme-wrapper">
		<%@include file="../include/menu.jsp"%>
		<%@include file="../include/menuItems.jsp"%>
		<div id="page-wrapper" class="container">
			<div id="content-wrapper">
				<div class="row">
					<div class="col-lg-12">
						<div class="main-box clearfix">
							<div class="main-box-body clearfix">
								<br />
								<h1 class="pull-left">
									<span>Agency</span>
								</h1>
								<div class="pull-right top-page-ui">
									<a onclick="getAgency(0)" class="btn btn-primary pull-right" title="Add Agency">
										<i class="fa fa-plus-circle fa-lg"></i> Add Agency
									</a>
								</div>
								<br />
								<br/>
								<hr />

								<div class="table-responsive">
									<table id="agency-list"
										class="table table-hover table-bordered CSSTableGenerator">
										<thead>
											<tr>
												<th style="text-align: center;">#</th>
												<th style="text-align: center;">Code</th>
												<th style="text-align: center;">Name</th>
												<!-- <th style="text-align: center;">Agency Type</th> -->
												<th style="text-align: center;">Status</th>
												<th style="text-align: center;">Action</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${listAgencies}" var="agency"
												varStatus="recordCount">
												<tr>
													<td style="text-align: center;"><c:out value="${recordCount.count}" /></td>
													<td><c:out value="${agency.code}" /></td>
													<td><c:out value="${agency.name}" /></td>
													<%-- <td><c:out value="${agency.agencyType}" /></td> --%>
													<c:choose>
														<c:when test="${agency.status == 'ACTIVE'}">
															<td class="text-center"><span class="label label-success"><c:out value="${agency.status}" /></span></td>
														</c:when>
														<c:otherwise>
															<td class="text-center"><span class="label label-danger"><c:out value="${agency.status}" /></span></td>
														</c:otherwise>
													</c:choose>
													<td style="text-align: center;">
														<%-- <c:if test="${COU_EDT == true }"> --%> <%-- <a
														href="getAgency?id=<c:out value='${agency.id}'/>">
															<span class="fa-stack"> <i
																class="fa fa-square fa-stack-2x"></i> <i
																class="fa fa-pencil fa-stack-1x fa-inverse"></i>
														</span>
													</a> </c:if>  <c:if test="${COU_DEL == true }">
														<a href="#" data-toggle="modal" data-target="#myModal"
															data-id=${agency.id }
															data-href="deleteAgency?id=${agency.id}"
															class="table-link danger" title="Delete"> <span
																class="fa-stack"> <i
																	class="fa fa-square fa-stack-2x"></i> <i
																	class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
															</span></a> --%>
															<a href="#" onclick="getAgencyInformation(${agency.id})" class="table-link" title="View Agency"> <span
																class="fa-stack"> <i
																	class="fa fa-square fa-stack-2x"></i> <i
																	class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
															</span>
															</a>
													<a href="#" onclick="getAgency(${agency.id})" class="table-link" title="Edit Agency">
														<span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i></span>
													</a>
															 <c:choose>
															<c:when test="${agency.status == 'ACTIVE'}">
																<a href="#" class="table-link danger" title="Deactivate Agency"
																	data-id=${agency.id } data-toggle="modal"
																	data-target="#agencyDeactiveDialog"
																	data-href="deActiveAgency?id=${agency.id}"> <span
																	class="fa-stack"> <i
																		class="fa fa-square fa-stack-2x"></i> <i
																		class="fa fa-times fa-stack-1x fa-inverse"></i>
																</span>
																</a>
															</c:when>
															<c:otherwise>
																<a href="#" class="table-link" title="Activate Agency"
																	data-id=${agency.id } data-toggle="modal"
																	data-target="#agencyActiveDialog"
																	data-href="activeAgency?id=${agency.id}"> <span
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
	<div id="agencyActiveDialog" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Activate Agency</h4>
				</div>
				<div class="modal-body" id="agecnyActive"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a class="btn btn-success btn-ok">Activate</a>
				</div>
			</div>

		</div>
	</div>
	
	<!-- Modal window for de-activation -->
	<div id="agencyDeactiveDialog" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Deactivate Agency</h4>
				</div>
				<div class="modal-body" id="agencyDeactive"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a class="btn btn-danger btn-ok">Deactivate</a>
				</div>
			</div>

		</div>
	</div>
	
	<!-- Modal window for View Agency -->
	<div id="agencyInformation" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header" style="height: 43px;">
					<h1 class="pull-left"
						style="margin: -4px 10px 9px -12px; font-size: 154%;">
						<span>Agency Details</span>
					</h1>
					<button type="button" id="button" class="close"
						data-dismiss="modal">×</button>

				</div>
				<div class="modal-body">
					<table class="table table-striped table-bordered" style="font-weight: bold;">
						<tbody>
							<tr>
								<td>Code</td>
								<td><label id="agencyCode" style="font-weight: normal;"></label></td>
							</tr>
							<tr>
								<td>Name</td>
								<td><label id="agencyName" style="font-weight: normal;"></label></td>
							</tr>
							<tr>
								<td>Authority</td>
								<td><label id="agencyAuthority" style="font-weight: normal;"></label></td>
							</tr>
							<tr>
								<td>Country</td>
								<td><label id="agencyCountry" style="font-weight: normal;"></label></td>
							</tr>
							<tr>
								<td>State</td>
								<td><label id="agencyState" style="font-weight: normal;"></label></td>
							</tr>
							<tr>
								<td>Region</td>
								<td><label id="agencyRegion" style="font-weight: normal;"></label></td>
							</tr>
							<tr>
								<td>Status</td>
								<td><label id="agencyStatus" style="font-weight: normal;"></label></td>
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
<!-- 	<div id="myModal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			Modal content
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
	</div> -->

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