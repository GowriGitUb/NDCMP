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
<script type="text/javascript" src='<c:url value="/static/scripts/state.js"></c:url>'></script>
<script type="text/javascript" src="../static/scripts/menu.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#tabs > li').removeClass('active');
	$('#configurationTab').attr('class', 'active');
	$('#stateTab').attr('class','active');
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
									<br />
									<h1 class="pull-left">
										<span>State</span>
									</h1>
									<div class="pull-right top-page-ui">
										<a href="editStates?id=0" class="btn btn-primary pull-right" title="Add State">
											<i class="fa fa-plus-circle fa-lg"></i> Add State
										</a>
									</div>
									<br /> <br />
									<hr />

									<div class="table-responsive">
										<table id="state-list"
											class="table table-hover table-bordered CSSTableGenerator">
											<thead>
												<tr>
													<th style="text-align: center;">#</th>
													<th style="text-align: center;">State Code</th>
													<th style="text-align: center;">State Name</th>
													<th style="text-align: center;">Country Name</th>
													<th style="text-align: center;">Status</th>
													<th style="text-align: center;">Action</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${stateslist }" var="state"
													varStatus="recordCount">
													<tr>
														<td style="text-align: center;"><c:out value="${recordCount.count}" /></td>
														<td><c:out value="${state.code}" /></td>
														<td><c:out value="${state.name}" /></td>
														<td><c:out value="${state.country.name}" /></td>
														<c:choose>
															<c:when test="${state.status == 'ACTIVE'}">
																<td class="text-center"><span class="label label-success"><c:out value="${state.status}" /></span></td>
															</c:when>
															<c:otherwise>
																<td class="text-center"><span class="label label-danger"><c:out value="${state.status}" /></span></td>
															</c:otherwise>
														</c:choose>
														<td style="text-align: center;">
															<%-- <c:if test="${STT_EDT == true}"> --%>
															<a href="#" onclick="getStateInformation(${state.id})" class="table-link" title="View State"> <span
																class="fa-stack"> <i
																	class="fa fa-square fa-stack-2x"></i> <i
																	class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
															</span>
														</a> <a
															href="editStates?id=<c:out value='${state.id}'/>" class="table-link" title="Edit State"> <span
																class="fa-stack"> <i
																	class="fa fa-square fa-stack-2x"></i> <i
																	class="fa fa-pencil fa-stack-1x fa-inverse"></i>
															</span></a> 
															<c:choose>
															<c:when test="${state.status == 'ACTIVE'}">
																<a href="#" class="table-link danger" title="Deactivate State" data-id=${state.id } data-toggle="modal"	data-target="#userDeactiveDialog" data-href="deActiveState?id=${state.id}">
																	<span class="fa-stack"> <i
																		class="fa fa-square fa-stack-2x"></i> <i
																		class="fa fa-times fa-stack-1x fa-inverse"></i>
																</span>
																</a>
															</c:when>
															<c:otherwise>
																<a href="#" class="table-link" title="Activate State" data-id=${state.id } data-toggle="modal"	data-target="#userActiveDialog" data-href="activeState?id=${state.id}"> <span
																	class="fa-stack"> <i
																		class="fa fa-square fa-stack-2x"></i> <i
																		class="fa fa-check fa-stack-1x fa-inverse"></i>
																</span>
																</a>
															</c:otherwise>
														</c:choose>
															<%-- <a href="#" data-toggle="modal" data-target="#myModal"
															data-id=${state.id }
															data-href="deleteState?id=${state.id }"
															class="table-link danger" title="Delete"> <span
																class="fa-stack"> <i
																	class="fa fa-square fa-stack-2x"></i> <i
																	class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
															</span></a> --%>

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




	<%-- <%@include file="../../include/menu.jsp"%>
	<div class="container">
		<div align="left">
			<b>State List</b>
		</div>
		<h5 style="color: red;" align="center">${stateDeleteMsg}</h5>
		<hr />
		<table id="stateslist"
			class="table table-striped table-bordered table-hover" width="100%"
			cellspacing="0">
			<tbody>
				<c:forEach items="${stateslist }" var="state">
					<tr>
						<td><c:out value="${state.name}" /></td>
						<td><c:out value="${state.code}" /></td>
						<td><c:out value="${state.country.name}" /></td>
						<td>
							<c:if test="${STT_EDT == true}"> <a
							href="editStates?id=<c:out value='${state.id}'/>"> <img
								src="<c:url value="/static/images/edit.png" />" alt="Edit"
								title="Edit State"></a> </c:if>
						<c:if test="${STT_DEL == true}"> <a href="#"
							data-toggle="modal" data-target="#myModal" data-id=${state.id }
							data-href="deleteState?id=${state.id }"> <img
								src="<c:url value="/static/images/delete.png" />" alt="Delete"
								title="Delete Role"></a> </c:if>

						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div> --%>

	<!-- Modal -->
	<!-- <div id="myModal" class="modal fade" role="dialog">
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
	
	<!-- Modal window for activation -->
	<div id="userActiveDialog" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Activate State</h4>
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
					<h4 class="modal-title">Deactivate State</h4>
				</div>
				<div class="modal-body" id="userDeactive"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a class="btn btn-danger btn-ok">Deactivate</a>
				</div>
			</div>

		</div>
	</div>
	
	<!-- Modal window for View States -->
	<div id="stateInformation" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header" style="height: 43px;">
					<h1 class="pull-left"
						style="margin: -4px 10px 9px -12px; font-size: 154%;">
						<span>State Details</span>
					</h1>
					<button type="button" id="button" class="close"
						data-dismiss="modal">×</button>

				</div>
				<div class="modal-body">
					<table class="table table-striped table-bordered" style="font-weight: bold;">
						<tbody>
							<tr>
								<td>State Code</td>
								<td><label id="code" style="font-weight: normal;"></label></td>
							</tr>
							<tr>
								<td>State Name</td>
								<td><label id="name" style="font-weight: normal;"></label></td>
							</tr>
							<tr>
								<td>Country</td>
								<td><label id="country" style="font-weight: normal;"></label></td>
							</tr>
							<tr>
								<td>Status</td>
								<td><label id="status" style="font-weight: normal;"></label></td>
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