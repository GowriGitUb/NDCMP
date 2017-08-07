<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../include/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UNODC</title>
<%@include file="../include/commonlib.jsp"%>
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
								<h1 class="pull-left">
									<c:choose>
										<c:when test="${states.id == 0}">
											<span>Add State</span>
										</c:when>
										<c:otherwise>
											<span>Update State</span>
										</c:otherwise>
									</c:choose>
								</h1>
								<br /> <br />
								<hr />
								<form:form action="createState" id="stateAddform"
									cssClass="form-horizontal" modelAttribute="states"
									method="post">
									
												<div class="form-group">
													<div class="control-label col-xs-3">
														<form:label path="country" cssClass="labelColor">Country : &nbsp;<span
																style="color: red">*</span>
														</form:label>
													</div>
													<div class="col-xs-2">
														<form:select id="countryId" path="country.id"
															cssClass="form-control">
															<form:option value="" label="---Select Country---"></form:option>
															<c:forEach items="${countrylist}" var="country">
																<form:option value="${country.id}"
																	label="${country.name}"></form:option>
															</c:forEach>
														</form:select>
													</div>
												</div>

									
												<form:hidden id="id" cssClass="form-control" path="id"
													value="${states.id}" />
												<div class="form-group">
													<div class="control-label col-xs-3">
														<form:label path="code" cssClass="labelColor">State Code : &nbsp;<span
																style="color: red">*</span>
														</form:label>
													</div>
													<div class="col-xs-2">
														<form:input id="code" cssClass="form-control" path="code"
															value="${states.code}" placeholder = "Code" />
													</div>
													<div>
														<span style="color: #D00B05">${codeExitMessage}</span>
													</div>
												</div>

												<div class="form-group">
													<div class="control-label col-xs-3">
														<form:label path="name" cssClass="labelColor">State Name : &nbsp;<span
																style="color: red">*</span>
														</form:label>
													</div>
													<div class="col-xs-3">
														<form:hidden path="Id" value="${states.id}" />
														<form:input id="name" cssClass="form-control" path="name"
															value="${states.name}" placeholder = "State Name" />
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
									<div class="form-group">
										<div class="row">
											<div class="col-xs-3"></div>
											<div class="col-xs-3">
												<input type="submit" id="saveUserrole"
													class="btn btn-primary" value="Save"
													onclick="return submitStateForm();" />
												<!-- <input type="button" id="cancel" value="Cancel" class="btn btn-primary" /> -->
												<a href="stateslist" class="btn btn-default">Cancel</a>
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

