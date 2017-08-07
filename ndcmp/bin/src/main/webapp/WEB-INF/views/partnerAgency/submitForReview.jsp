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
<link rel="stylesheet" type="text/css"
	href='<c:url value="/static/ui/jquery-ui.css"></c:url>'>
<link
	href="<c:url value="/static/csslib/jquery.colorpickersliders.css" />"
	rel="stylesheet" type="text/css">

<script type="text/javascript"
	src="<c:url value="/static/jslib/tinycolor.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/static/ui/jquery-ui.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/static/jslib/jquery.colorpickersliders.js" />"></script>
<script type="text/javascript"
	src='<c:url value="/static/scripts/status.js"></c:url>'></script>
	<script type="text/javascript"
	src='<c:url value="/static/scripts/menu.js"></c:url>'></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#tabs > li').removeClass('active');
		/* $('#configurationTab').attr('class', 'active'); */
		$('#submitReviewTab').attr('class', 'active');
	});
</script>

<script type="text/javascript"
	src='<c:url value="/static/scripts/status.js"></c:url>'></script>
<body>



	<div id="theme-wrapper">
		<%@include file="../include/menu.jsp"%>
		<div id="page-wrapper" class="container">
			<%@include file="../include/menuItems.jsp"%>
			<div id="content-wrapper">

				<div class="row">
					<div class="col-lg-12">
						<div class="main-box">
							<div class="main-box-body clearfix">
								<br />
								<c:choose>
									<c:when test="${submitForReview.id == null}">
										<h1 class="pull-left"><span>Add Submit for Review</span></h1>
									</c:when>
									<c:otherwise>
										<h1 class="pull-left"><span>Add Submit for Review</span></h1>
									</c:otherwise>
								</c:choose>
								<br /> <br />
								<hr />
								<form:form action="createSubmitForReview" id="submitForReviewAddform"
									cssClass="form-horizontal" modelAttribute="submitForReview"
									method="post">
									<input type="hidden" id="roleId" value="<%= session.getAttribute("roleId") %>" />
									<br>

									<div class="form-group">
										<div class="control-label col-xs-3">
											<form:label path="keyLearning" cssClass="labelColor">Key Learning : &nbsp;
											</form:label>
										</div>
										<div class="col-xs-6">
											<form:textarea cssClass="form-control" path="keyLearning"
												value="${submitForReview.keyLearning}" />
										</div>
										<div>
											<span style="color: #D00B05">${errorDescription}</span>
										</div>
									</div>


									<div class="form-group">
										<div class="control-label col-xs-3">
											<form:label path="keyChallenge" cssClass="labelColor">Key Challenge : &nbsp;
											</form:label>
										</div>
										<div class="col-xs-6">
											<form:textarea cssClass="form-control" path="keyChallenge"
												value="${submitForReview.keyChallenge}" />
										</div>
										<div>
											<span style="color: #D00B05">${errorDescription}</span>
										</div>
									</div>
									
									<div class="form-group">
										<div class="control-label col-xs-3">
											<form:label path="bestPractice" cssClass="labelColor">Best Practice : &nbsp;
											</form:label>
										</div>
										<div class="col-xs-6">
											<form:textarea cssClass="form-control" path="bestPractice"
												value="${submitForReview.bestPractice}" />
										</div>
										<div>
											<span style="color: #D00B05">${errorDescription}</span>
										</div>
									</div>
									
									<div class="form-group">
										<div class="control-label col-xs-3">
											<form:label path="supportNeeds" cssClass="labelColor">Support Needs : &nbsp;
											</form:label>
										</div>
										<div class="col-xs-6">
											<form:textarea cssClass="form-control" path="supportNeeds"
												value="${submitForReview.supportNeeds}" />
										</div>
										<div>
											<span style="color: #D00B05">${errorDescription}</span>
										</div>
									</div>
									
									<br />
									<div class="form-group">
										<div class="row">
											<div class="col-xs-4"></div>
											<div class="col-xs-4">
												<input type="submit" id="saveSubmitForReview"
													class="btn btn-primary" value="Submit"
													 />
												<!-- <input type="button" id="cancel" value="Cancel" class="btn btn-primary" /> -->
												<a href="partnerFilter" class="btn btn-default">Cancel</a>
											</div>
											<div class="col-xs-4"></div>
										</div>
									</div>
								</form:form>
							</div>
						</div>
						<footer id="footer-bar" class="row">
						<p id="footer-copyright" class="col-xs-12"></p>
						</footer>
					</div>
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
<style>
h2 {
	margin-bottom: 0;
}

h3 {
	margin: 0 0 30px;
}

.ui-slider-range {
	background: green;
}

.percent {
	color: green;
	font-weight: bold;
	text-align: center;
	width: 100%;
	border: none;
}
</style>
</html>

