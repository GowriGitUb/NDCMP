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
<script type="text/javascript" src="../static/scripts/menu.js"></script>
<script type="text/javascript"
	src='<c:url value="/static/scripts/status.js"></c:url>'></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#tabs > li').removeClass('active');
		$('#configurationTab').attr('class', 'active');
		$('#statusTab').attr('class', 'active');
		$('.hideHint').hide();
		$('.showHint').css('cursor', 'pointer');
		$('.hideHint').css('cursor', 'pointer');
		
$('#showColorHint').hide();
		
		$(".showHint").click(function(){
			
			
				$('#showColorHint').show();
			
				//$('#showColorHint').hide();
				$('.showHint').hide();
				$('.hideHint').show();
			
	    });
		
		$(".hideHint").click(function(){
			$('#showColorHint').hide();
			$('.showHint').show();
			$('.hideHint').hide();
		});
		
		
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
									<c:when test="${status.id == 0}">
										<h1 class="pull-left">
											<span>Add Status</span>
										</h1>
									</c:when>
									<c:otherwise>
										<h1 class="pull-left">
											<span>Update Status</span>
										</h1>
									</c:otherwise>
								</c:choose>
								<br /> <br />
								<hr />
								<form:form action="createStatus" id="statusAddform"
									cssClass="form-horizontal" modelAttribute="status"
									method="post">
									<div class="form-group">
										<div class="control-label col-xs-3">
											<form:label path="name" cssClass="labelColor">Status Name : &nbsp;<span
													style="color: red">*</span>
											</form:label>
										</div>
										<div class="col-xs-2">
											<form:hidden path="id" value="${status.id}" />
											<form:input cssClass="form-control" path="name"
												value="${status.name}" placeholder="Status Name" />
										</div>
										<div>
											<span style="color: #D00B05">${errorName}</span>
										</div>
									</div>

									<div class="form-group">
										<div class="control-label col-xs-3">
											<form:label path="description" cssClass="labelColor">Description : &nbsp;<span
													style="color: red">*</span>
											</form:label>
										</div>
										<div class="col-xs-3">
											<form:textarea cssClass="form-control" path="description"
												value="${status.description}" placeholder="Description" />
										</div>
										<div>
											<span style="color: #D00B05">${errorDescription}</span>
										</div>
									</div>


									<%-- <div class="form-group">
										<div class="control-label col-xs-3">
											<form:label path="statusColor" cssClass="labelColor">Status Color : &nbsp;<span
													style="color: red">*</span>
											</form:label>
										</div>
										<div class="col-xs-6">
											<form:input cssClass="form-control onchange-example"
												path="statusColor" data-color-format="hex"
												value="${status.statusColor}" />
										</div>
										<div>
											<span style="color: #D00B05">${errorColor}</span>
										</div>
									</div> --%>
									</br>

									<div class="form-group">

										<div class="control-label col-xs-3">
											<form:label path="statusColor" cssClass="labelColor">Status Color : &nbsp;<span
													style="color: red">*</span>
											</form:label>
										</div>

										<div class="project col-md-4">
											<h2 class="text-center" style="margin-top: -19px;">
												<!-- <label id="colorPer"></label> -->
												<input type="text" name="colorPer" id="colorPer" class="form-control" style="width: 83px;"/>
												<form:hidden path="colorPercent" id="colorPercent" />

												<form:hidden path="statusColor" cssClass="percent" id="percent" />
											</h2>
											<div class="bar" style="margin-top: -1px;"></div>
											<div style="margin: -16px 14px -1px 381px;">
												<span class="showHint">
													<span style="padding-top: 7px;" class="glyphicon glyphicon-info-sign" title="Show Colors"
														data-original-title="Show Colors">
													</span>
												</span>
												 <span class="hideHint">
													<span style="padding-top: 7px;" class="glyphicon glyphicon-info-sign" title="Hide Colors"
														data-original-title="Show Colors">
													</span>
												</span>
												<!-- <label id="hint" class="checkbox-inline"> <input type="checkbox" value="" class="showHint">Show Hint</label> -->
											</div>
										</div>
										<div class="form-group"
												style="margin: 47px 50px 30px 174px;display: block;" id="showColorHint">
												<div class="col-xs-1"
													style="width: 53px; height: 32px; background: #DDA6A1;">
													<label style="padding-top: 31px; width: -223px;">00-09</label>
												</div>
												<div class="col-xs-1"
													style="width: 55px; height: 32px; background: #E77B70;">
													<label style="padding-top: 31px; width: -223px;">10-19</label>
												</div>
												<div class="col-xs-1"
													style="width: 53px; height: 32px; background: #F14D41;">
													<label style="padding-top: 31px; width: -223px;">20-29</label>
												</div>
												<div class="col-xs-1"
													style="width: 55px; height: 32px; background: #F66A37;">
													<label style="padding-top: 31px; width: -223px;">30-39</label>
												</div>
												<div class="col-xs-1"
													style="width: 55px; height: 32px; background: #F89A3A;">
													<label style="padding-top: 31px; width: -223px;">40-49</label>
												</div>
												<div class="col-xs-1"
													style="width: 55px; height: 32px; background: #FECB3A;">
													<label style="padding-top: 31px; width: -223px;">50-59</label>
												</div>
												<div class="col-xs-1"
													style="width: 55px; height: 32px; background: #F3E83B;">
													<label style="padding-top: 31px; width: -223px;">60-69</label>
												</div>
												<div class="col-xs-1"
													style="width: 55px; height: 32px; background: #CDDA42;">
													<label style="padding-top: 31px; width: -223px;">70-79</label>
												</div>
												<div class="col-xs-1"
													style="width: 53px; height: 32px; background: #ABD045;">
													<label style="padding-top: 31px; width: -223px;">80-89</label>
												</div>
												<div class="col-xs-1"
													style="width: 63px; height: 32px; background: #9FCA47;">
													<label style="padding-top: 31px; width: -223px;">90-100</label>
												</div>
											</div>
										<div>
											<span style="color: #D00B05">${errorColor}</span>
										</div>
									</div>


									<div class="form-group">
										<div class="control-label col-xs-3">

											<form:label path="startRange" cssClass="labelColor">Start Range : &nbsp;<span
													style="color: red">*</span>
											</form:label>
										</div>
										<div class="col-xs-1">
											<%-- <form:input cssClass="form-control" path="startRange" id="startRange" placeholder = "Start Range" /> --%>
											<input id="startRange" name="startRange" class="form-control"
												type="number" value="${status.startRange}">
											<!-- <input type="number" name="startRange" cssClass="form-control" id="startRange"/> -->
										</div>
										<div>
											<span style="color: #D00B05">${errorName}</span>
										</div>
									</div>

									<div class="form-group">
										<div class="control-label col-xs-3">
											<form:label path="endRange" cssClass="labelColor">End Range : &nbsp;<span
													style="color: red">*</span>
											</form:label>
										</div>
										<div class="col-xs-1">
											<%-- <form:input cssClass="form-control" path="endRange" placeholder = "Range" /> --%>
											<input id="endRange" name="endRange" class="form-control"
												type="number" value="${status.endRange}">
											<!-- <input type="number" name="endRange" cssClass="form-control" id="endRange"/> -->
										</div>
										<div>
											<span style="color: #D00B05">${errorName}</span>
										</div>
									</div>


									<!-- <div class="row">
										<div class="col-lg-6">
											<div class="row">
												<div class="form-group col-md-12">
													<label for="officialname">Official Name</label>
													<div class="input-group">
														<textarea rows="3" cols="3" class="form-control"
															id="officialname"></textarea>
													</div>
												</div>
											</div>
										</div>
									</div> -->
									<br />
									<div class="form-group">
										<div class="row">
											<div class="col-xs-3"></div>
											<div class="col-xs-4">
												<input type="submit" id="saveUserrole"
													class="btn btn-primary" value="Save"
													onclick="return submitStatusForm();" />
												<!-- <input type="button" id="cancel" value="Cancel" class="btn btn-primary" /> -->
												<a href="statusList" class="btn btn-default">Cancel</a>
											</div>
											<div class="col-xs-4"></div>
										</div>
									</div>
								</form:form>
							</div>
						</div>
						<!-- <footer id="footer-bar" class="row">
						<p id="footer-copyright" class="col-xs-12"></p>
						</footer> -->
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

