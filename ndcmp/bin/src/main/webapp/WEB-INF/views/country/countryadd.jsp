<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../include/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UNODC</title>
<%@include file="../include/commonlib.jsp"%>
<script type="text/javascript" src='<c:url value="/static/scripts/country.js"></c:url>'></script>
<script type="text/javascript" src="../static/scripts/menu.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#tabs > li').removeClass('active');
	$('#configurationTab').attr('class', 'active');
	$('#countryTab').attr('class','active');
});
</script>
</head>
<body>
	<div id="theme-wrapper">
		<%@include file="../include/menu.jsp"%>
		<div id="page-wrapper" class="container">
		<div class="row">
			<%@include file="../include/menuItems.jsp"%>
			<div id="content-wrapper">
				<div class="row">
					<div class="col-lg-12">
						<div class="main-box">
							<div class="main-box-body clearfix">
								<br />
								<h1 class="pull-left">
									<c:choose>
										<c:when test="${country.id == null}">
											<span>Add Country</span>
										</c:when>
										<c:otherwise>
											<span>Update Country</span>
										</c:otherwise>
									</c:choose>
								</h1>
								<br /> <br />
								<hr />
								<form:form action="createCountry" id="countyAddform"
									cssClass="form-horizontal" modelAttribute="country"
									method="post">
												<div class="form-group">
													<div class="control-label col-xs-3">
														<form:label path="code" cssClass="labelColor">Country Code : &nbsp;<span
																style="color: red">*</span>
														</form:label>
													</div>
													<div class="col-xs-2">
														<form:hidden path="id" value="${country.id}" />
														<form:input id="code" cssClass="form-control" path="code"
															value="${country.code}"  placeholder = "Code"/>
													</div>
													<div>
														<span style="color: #D00B05">${codeExitMessage}</span>
													</div>
												</div>

												<div class="form-group">
													<div class="control-label col-xs-3">
														<form:label path="name" cssClass="labelColor">Country Name : &nbsp;<span
																style="color: red">*</span>
														</form:label>
													</div>
													<div class="col-xs-3">
														<form:input id="name" cssClass="form-control" path="name"
															value="${country.name}" placeholder = "Country Name" />
													</div>
													<div>
														<span style="color: #D00B05">${nameExitMessage}</span>
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
									<!-- <div>
										<input type="submit" id="submit" onclick="return submitCountryForm();" class="btn btn-primary"
											value="Add" /> <input type="button" id="cancel"
											value="Cancel" class="btn btn-default" />
									</div> -->
									<div class="form-group">
												<div class="row">
													<div class="col-xs-3"></div>
													<div class="col-xs-3">
														<input type="submit" id="saveUserrole"
															class="btn btn-primary" value="Save"
															onclick="return submitCountryForm();" />
														<!-- <input type="button" id="cancel" value="Cancel" class="btn btn-primary" /> -->
														<a href="countryList" class="btn btn-default">Cancel</a>
													</div>
													<div class="col-xs-3"></div>
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
	</div>

	<!-- BackToTop Button -->
	<a href="javascript:void(0);" id="scroll" title="Scroll to Top" style="display: none;">Top<span></span></a>

	<script src="../static/js/demo-skin-changer.js"></script>
	<!-- <script src="../static/js/jquery.js"></script> -->
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
