<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../include/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UNODC</title>
<%@include file="../include/commonlib.jsp"%>
<link rel="stylesheet" type="text/css" href="/static/css/format.css" />
<script type="text/javascript" src="../static/js/dataTable_list.js"></script>
<script type="text/javascript" src="../static/scripts/menu.js"></script>
<%@include file="../include/dataTable.jsp"%>
<script type="text/javascript">
		$(document).ready(function() {
			$('#tabs > li').removeClass('active');
			$('#accesRightsTab').attr('class', 'active');
		});

</script>
</head>


<body onload="loadAccessRights()">
<div id="theme-wrapper">
	<%@include file="../include/menu.jsp"%>
		<div id="page-wrapper" class="container">
			<div class="row">
				<%@include file="../include/menuItems.jsp"%>
				<div id="content-wrapper">
					<div class="col-lg-12">
						<div class="main-box clearfix">
							<div class="main-box-body clearfix">
								<br />
								<h1 class="pull-left">
									<span>Access Rights</span>
								</h1>
								<br /> <br />
								<hr />
								<div class="table-responsive">
									<table id="accessrightsList" class="table table-hover table-bordered CSSTableGenerator">
										<thead>
											<tr>
												<!-- <th style="display: none;"></th> -->
												<th style="text-align: center;">User Role</th>
												<th style="text-align: center;">Action</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${accessrightslist}" var="accessrights">
												<tr>
													<%-- <td style="display: none;"><c:out value="${accessrights.id}" /></td> --%>
													<td><c:out value="${accessrights.name}" /></td>
													<td align="center"><a href="assign?id=<c:out value='${accessrights.id}'/>">
														<span class="glyphicon glyphicon-cog" title="Access Role"></span>
														</a> 
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
	
	<!-- BackToTop Button -->
	<a href="javascript:void(0);" id="scroll" title="Scroll to Top" style="display: none;">Top<span></span></a>
	
	<!-- <footer id="footer-bar" class="row">
		<p id="footer-copyright" class="col-xs-12"></p>
	</footer> -->
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