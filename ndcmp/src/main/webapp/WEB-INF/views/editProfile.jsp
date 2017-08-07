<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="include/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UNODC</title>
<%@include file="include/commonlib.jsp"%>
<script src="<c:url value="/static/js/user.js" />"></script>
<script type="text/javascript"
	src='<c:url value="/static/scripts/userList.js"></c:url>'></script>
<script type="text/javascript"
	src='<c:url value="/static/scripts/menu.js"></c:url>'></script>
<!-- <script type="text/javascript">

$(document).ready(function(){
	var userId = "${userId}";
	if(userId == null || userId == '' || userId == ""){
		$('#username').val("");
		$('#password').val("");
	}
});

</script> -->
<script type="text/javascript">
	$(document).ready(function() {
		$('#tabs > li').removeClass('active');
		$('#userTab').attr('class', 'active');
		var userRoleName = "${userRoleName}";
		$('#userRoleId').css('pointer-events', 'none');
		if (userRoleName == "STATUS_UPDATER") {
			$('#agencyId').css('pointer-events', 'none');
			$('#agencyDiv').show();
		} else {
			$('#agencyId').css('pointer-events', 'none');
			$('#agencyDiv').hide();
		}

		var userType = '${userObject.userType}';
		var userResult = userType.split(",");
		$.each(userResult, function(ui, uv) {
			$('#' + uv).attr('selected', true);
		});
	});
</script>
</head>
<body>

	<div id="theme-wrapper">
		<%@include file="include/menu.jsp"%>
		<div id="page-wrapper" class="container">
			<%@include file="include/menuItems.jsp"%>
			<div id="content-wrapper">
				<div class="row">
					<div class="col-lg-12">
						<div class="main-box">
							<div class="main-box-body clearfix">
								<input type="hidden" id="roleId"
									value="<%=session.getAttribute("roleId")%>" /> <br />
								<h1 class="pull-left">
									<c:choose>
										<c:when test="${userObject.id == null}">
											<span>Add User</span>
										</c:when>
										<c:otherwise>
											<span>Edit Profile</span>
										</c:otherwise>
									</c:choose>
								</h1>
								<div class="pull-right">
									<c:if test="${msg == 1}">
										<h4 class="text-success" id="updateMsg">Updated
											Successfully</h4>
									</c:if>
								</div>
								<br /> <br />

								<hr />
								<div>
									<form:form id="userRegisterForm" cssClass="form-horizontal"
										modelAttribute="userObject" method="post" action="saveProfile"
										enctype="multipart/form-data">

										<div class="form-group">
											<div class="control-label col-xs-2">
												<form:label path="firstName" cssClass="labelColor">First Name : &nbsp;<span
														style="color: red">*</span>
												</form:label>
											</div>
											<div class="col-xs-3">
												<form:hidden path="id" value="${userObject.id}" />
												<form:input cssClass="form-control" path="firstName" />
											</div>
										</div>
										<div class="form-group">
											<div class="control-label col-xs-2">
												<form:label path="lastName" cssClass="labelColor">Last Name : &nbsp;<span
														style="color: red">*</span>
												</form:label>
											</div>
											<div class="col-xs-3">
												<form:input cssClass="form-control" path="lastName" />
											</div>
										</div>

										<div class="form-group">
											<div class="control-label col-xs-2">
												<form:label path="username" cssClass="labelColor">User Id : &nbsp;<span
														style="color: red">*</span>
												</form:label>
											</div>
											<div class="col-xs-3">
												<c:if test="${userObject.id != null }">
													<form:input cssClass="form-control" path="username"
														readonly="true" />
												</c:if>
												<c:if test="${userObject.id == null}">
													<form:input cssClass="form-control" path="username"
														value="${userObject.username}" />
												</c:if>
											</div>
											<div>
												<span style="color: #D00B05">${usrerrormsg}</span>
											</div>
										</div>

										<c:choose>
											<c:when test="${userObject.id == null}">
												<div class="form-group">
													<div class="control-label col-xs-2">
														<form:label path="password" cssClass="labelColor">Password : &nbsp;<span
																style="color: red">*</span>
														</form:label>
													</div>
													<div class="col-xs-3">
														<form:password cssClass="form-control" path="password" />
													</div>
												</div>
											</c:when>

											<c:otherwise>
												<form:hidden cssClass="form-control" path="password" />
												<form:hidden cssClass="form-control" path="mobilePassword" />
											</c:otherwise>
										</c:choose>
										<div class="form-group">
											<div class="control-label col-xs-2">
												<form:label path="userType" cssClass="labelColor">User Type : &nbsp;<span
														style="color: red">*</span>
												</form:label>
											</div>
											<div class="col-xs-2">
												<form:select id="userType" path="userType"
													multiple="multiple" cssClass="form-control">
													<form:option value="" label="---Select User Type---"></form:option>
													<form:option id="Web" value="Web">Web</form:option>
													<form:option id="Mobile" value="Mobile">Mobile</form:option>
												</form:select>
											</div>
										</div>

										<div class="form-group">
											<div class="control-label col-xs-2">
												<form:label path="email" cssClass="labelColor">Email : &nbsp;<span
														style="color: red">*</span>
												</form:label>
											</div>
											<div class="col-xs-3">
												<c:if test="${userObject.id != null }">
													<form:input cssClass="form-control" path="email"
														value="${userObject.email}" readonly="true" />
													<!-- onmousedown="return validateEmail(this);" -->
												</c:if>
												<c:if test="${userObject.id == null }">
													<form:input cssClass="form-control" path="email"
														value="${userObject.email}" />
													<!-- onmousedown="return validateEmail(this);" -->
												</c:if>
												<%-- <form:input cssClass="form-control" path="email" /> --%>
											</div>
											<div>
												<span style="color: #D00B05">${emailErrormsg}</span>
											</div>
										</div>
										<div class="form-group">
											<div class="control-label col-xs-2">
												<form:label path="phone" cssClass="labelColor">Phone : &nbsp;<span
														style="color: red">*</span>
												</form:label>
											</div>
											<div class="col-xs-2">
												<form:input cssClass="form-control" path="phone" />
												<form:errors path="phone"></form:errors>
											</div>
										</div>

										<div class="form-group">
											<div class="control-label col-xs-2">
												<form:label path="address1" cssClass="labelColor">Address1 : &nbsp;<span
														style="color: red">*</span>
												</form:label>
											</div>
											<div class="col-xs-3">
												<form:input cssClass="form-control" path="address1" />
											</div>
										</div>
										<div class="form-group">
											<div class="control-label col-xs-2">
												<form:label path="address2" cssClass="labelColor">Address2 :&nbsp;&nbsp;&nbsp;</form:label>
											</div>
											<div class="col-xs-3">
												<form:input cssClass="form-control" path="address2" />
											</div>
										</div>

										<div class="form-group">
											<div class="control-label col-xs-2">
												<form:label path="address3" cssClass="labelColor">Address3 :&nbsp;&nbsp;&nbsp;</form:label>
											</div>
											<div class="col-xs-3">
												<form:input cssClass="form-control" path="address3" />
											</div>
										</div>
										<div class="form-group">
											<div class="control-label col-xs-2">
												<form:label path="country" cssClass="labelColor">Country : &nbsp;<span
														style="color: red">*</span>
												</form:label>
											</div>
											<div class="col-xs-2">
												<form:select id="countryId" path="country.id"
													cssClass="form-control" onchange="getStateByCountry()">
													<form:option value="" label="---Select Country---"></form:option>
													<c:forEach items="${countrylist}" var="country">
														<form:option value="${country.id}" label="${country.name}"></form:option>
													</c:forEach>
												</form:select>
											</div>
										</div>

										<c:choose>
											<c:when test="${userObject.id == null}">
												<div class="form-group">
													<div class="control-label col-xs-2">
														<form:label path="state" cssClass="labelColor">State : &nbsp;<span
																style="color: red">*</span>
														</form:label>
													</div>
													<div class="col-xs-2">
														<form:select id="state" path="state.id"
															cssClass="form-control">
															<form:option value="" label="---Select State---"></form:option>
														</form:select>
													</div>
												</div>
											</c:when>
											<c:otherwise>
												<div class="form-group">
													<div class="control-label col-xs-2">
														<form:label path="state" cssClass="labelColor">State : &nbsp;<span
																style="color: red">*</span>
														</form:label>
													</div>
													<div class="col-xs-2">
														<form:select id="state" path="state.id"
															cssClass="form-control">
															<form:option value="" label="---Select State---"></form:option>
															<c:forEach items="${statelist}" var="state">
																<form:option value="${state.id}" label="${state.name}"></form:option>
															</c:forEach>
														</form:select>
													</div>
												</div>
											</c:otherwise>
										</c:choose>


										<div class="form-group">
											<div class="control-label col-xs-2">
												<form:label path="firstName" cssClass="labelColor">Zipcode : &nbsp;<span
														style="color: red">*</span>
												</form:label>
											</div>
											<div class="col-xs-2">
												<form:input cssClass="form-control" path="zipcode" />
											</div>
										</div>



										<%-- <div class="form-group">
												<div class="control-label col-xs-2">
													<form:label path="roleIds" cssClass="labelColor">User Role : &nbsp;<span
															style="color: red">*</span>
													</form:label>
												</div>
												<div class="col-xs-3">
													<form:select path="roleIds" cssClass="form-control">
														<form:options items="${userRoles}" itemLabel="description"
															itemValue="id" />
													</form:select>
												</div>
											</div> --%>


										<div class="form-group">
											<div class="control-label col-xs-2">
												<form:label path="roleId" cssClass="labelColor">User Role : &nbsp;<span
														style="color: red">*</span>
												</form:label>
											</div>
											<div class="col-xs-3">
												<input type="hidden" id="userRoleID" name="userRoleID"
													value="${userObject.userRole.id }" />
												<form:select id="userRoleId" path="userRole.id"
													cssClass="form-control">
													<form:option value="" label="---Select User Role---"></form:option>
													<c:forEach items="${userRoles}" var="r">
														<form:option value="${r.id}" label="${r.description}"></form:option>
													</c:forEach>
												</form:select>
											</div>
										</div>


										<div id="agencyDiv">
											<div class="form-group">
												<div class="control-label col-xs-2">
													<form:label path="agencyId" cssClass="labelColor">Agency : &nbsp;<span
															style="color: red">*</span>
													</form:label>
												</div>
												<div class="col-xs-2">
													<input type="hidden" id="agencyID" name="agencyID"
														value="${userObject.agencyId }" />
													<form:select id="agencyId" path="agencyId"
														cssClass="form-control" disabled="true">
														<form:option value="" label="---Select Agency---"></form:option>
														<c:forEach items="${agencyList}" var="agency">
															<form:option value="${agency.id}" label="${agency.code}"></form:option>
														</c:forEach>
													</form:select>
												</div>
											</div>
										</div>

										<div class="form-group">
											<div class="control-label col-xs-2">
												<form:label path="roleIds" cssClass="labelColor">Profile Image : &nbsp;&nbsp;
												</form:label>
											</div>
											<div class="col-xs-2">
												<input type="file" class="form-control" name="multipartFile"
													id="multipartFile"
													accept="image/gif, image/jpeg, image/png"
													onchange="encodeImageFileAsURL()" />
												<%-- <input type="hidden"
													name="multipartFile" id="multipartFile" value="${profileImage}" /> --%>

											</div>
											<br />
											<c:if test="${empty profileImage}">
												<img id="imgFile" class="img-circle"
													src="<c:url value="/static/img/default_profile.png"></c:url>"
													alt="" style="margin-left: 119px; margin-top: -19px;"
												height="100px" width="100px" />
											</c:if>
											<c:if test="${not empty profileImage}">
												<img id="imgFile" alt=""
												src="data:image/jpeg;base64,${profileImage}"
												class="img-circle"
												style="margin-left: 119px; margin-top: -19px;"
												height="100px" width="100px">
											</c:if>
											
										</div>

										<div class="form-group">
											<div class="row">
												<div class="col-xs-3"></div>
												<div class="col-xs-4">
													<input type="submit" id="saveUser" class="btn btn-primary"
														value="Save" onclick="return submitUserForm();" /> <a
														href="home" class="btn btn-default">Cancel</a>
												</div>
												<div class="col-xs-4"></div>
											</div>
										</div>
									</form:form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<%-- <%@include file="include/menu.jsp"%>

	<div class="container">
		<div align="left">
			<c:choose>
				<c:when test="${userObject.id == null}">
					<h2>Add User</h2>
				</c:when>
				<c:otherwise>
					<h2>Update User</h2>
				</c:otherwise>
			</c:choose>
		</div>
		<hr />
		<div>
			<form:form id="userRegisterForm" cssClass="form-horizontal"
				modelAttribute="userObject" method="post" action="saveProfile">

				<div class="form-group">
					<div class="control-label col-xs-2">
						<form:label path="firstName" cssClass="labelColor">First Name : &nbsp;<span
								style="color: red">*</span>
						</form:label>
					</div>
					<div class="col-xs-6">
						<form:hidden path="id" value="${userObject.id}" />
						<form:input cssClass="form-control" path="firstName" />
					</div>
				</div>
				<div class="form-group">
					<div class="control-label col-xs-2">
						<form:label path="lastName" cssClass="labelColor">Last Name : &nbsp;<span
								style="color: red">*</span>
						</form:label>
					</div>
					<div class="col-xs-6">
						<form:input cssClass="form-control" path="lastName" />
					</div>
				</div>

				<div class="form-group">
					<div class="control-label col-xs-2">
						<form:label path="username" cssClass="labelColor">Username : &nbsp;<span
								style="color: red">*</span>
						</form:label>
					</div>
					<div class="col-xs-6">
						<c:if test="${userObject.id != null }">
							<form:input cssClass="form-control" path="username"
								readonly="true" />
						</c:if>
						<c:if test="${userObject.id == null}">
							<form:input cssClass="form-control" path="username"
								value="${userObject.username}" />
						</c:if>
					</div>
					<div>
						<span style="color: #D00B05">${usrerrormsg}</span>
					</div>
				</div>
				
				
				<form:hidden path="password" value="${userObject.password}" />
				
				<div class="form-group">
					<div class="control-label col-xs-2">
						<form:label path="password" cssClass="labelColor">Password : &nbsp;<span
								style="color: red">*</span>
						</form:label>
					</div>
					<form:hidden path="id" value="${userObject.id}" />
					<div class="col-xs-6">
						<form:password cssClass="form-control" path="password" />
					</div>
				</div>

				<div class="form-group">
					<div class="control-label col-xs-2">
						<form:label path="email" cssClass="labelColor">Email : &nbsp;<span
								style="color: red">*</span>
						</form:label>
					</div>
					<div class="col-xs-6">
						<c:if test="${userObject.id != 0 }">
							<form:input cssClass="form-control" path="email"
								value="${userObject.email}" readonly="true"/><!-- onmousedown="return validateEmail(this);" -->
						</c:if>
						<c:if test="${userObject.id == 0 }">
							<form:input cssClass="form-control" path="email"
								value="${userObject.email}"/><!-- onmousedown="return validateEmail(this);" -->
						</c:if>
						<form:input cssClass="form-control" path="email" />
					</div>
					<div>
						<span style="color: #D00B05">${emailErrormsg}</span>
					</div>
				</div>
				<div class="form-group">
					<div class="control-label col-xs-2">
						<form:label path="phone" cssClass="labelColor">Phone : &nbsp;<span
								style="color: red">*</span>
						</form:label>
					</div>
					<div class="col-xs-6">
						<form:input cssClass="form-control" path="phone" />
						<form:errors path="phone"></form:errors>
					</div>
				</div>

				<div class="form-group">
					<div class="control-label col-xs-2">
						<form:label path="address1" cssClass="labelColor">Address1 : &nbsp;<span
								style="color: red">*</span>
						</form:label>
					</div>
					<div class="col-xs-6">
						<form:input cssClass="form-control" path="address1" />
					</div>
				</div>
				<div class="form-group">
					<div class="control-label col-xs-2">
						<form:label path="address2" cssClass="labelColor">Address2 :&nbsp;&nbsp;&nbsp;</form:label>
					</div>
					<div class="col-xs-6">
						<form:input cssClass="form-control" path="address2" />
					</div>
				</div>

				<div class="form-group">
					<div class="control-label col-xs-2">
						<form:label path="address3" cssClass="labelColor">Address3 :&nbsp;&nbsp;&nbsp;</form:label>
					</div>
					<div class="col-xs-6">
						<form:input cssClass="form-control" path="address3" />
					</div>
				</div>
				<div class="form-group">
					<div class="control-label col-xs-2">
						<form:label path="country" cssClass="labelColor">Country : &nbsp;<span
								style="color: red">*</span>
						</form:label>
					</div>
					<div class="col-xs-6">
						<form:select id="countryId" path="country.id"
							cssClass="form-control" onchange="getStateByCountry()">
							<form:option value="" label="---Select Country---"></form:option>
							<c:forEach items="${countrylist}" var="country">
								<form:option value="${country.id}" label="${country.name}"></form:option>
							</c:forEach>
						</form:select>
					</div>
				</div>

				<div class="form-group">
					<div class="control-label col-xs-2">
						<form:label path="state" cssClass="labelColor">State : &nbsp;<span
								style="color: red">*</span>
						</form:label>
					</div>
					<div class="col-xs-6">
						<form:select id="state" path="state.id" cssClass="form-control">
							<form:option value="" label="---Select State---"></form:option>
							<c:forEach items="${statelist}" var="state">
								<form:option value="${state.id}" label="${state.name}"></form:option>
							</c:forEach>
						</form:select>
					</div>
				</div>

				<div class="form-group">
					<div class="control-label col-xs-2">
						<form:label path="firstName" cssClass="labelColor">Zipcode : &nbsp;<span
								style="color: red">*</span>
						</form:label>
					</div>
					<div class="col-xs-6">
						<form:input cssClass="form-control" path="zipcode" />
					</div>
				</div>


				<div class="form-group">
					<div class="control-label col-xs-2">
						<label>User Role : &nbsp;<span style="color: red" >*</span></label>
					</div>
					<div class="col-xs-6">
						<form:select id="sel" path="roleIds" cssClass="form-control" >
							<form:options items="${userRoles}" itemLabel="name"
								itemValue="id" />
						</form:select>
					</div>
				</div>

				<div class="form-group">
					<div class="row">
						<div class="col-xs-4"></div>
						<div class="col-xs-4">
							<input type="submit" id="saveUser" class="btn btn-primary"
								value="Save" onclick="return submitUserForm();" /> <a
								href="userList" class="btn btn-default">Cancel</a>
						</div>
						<div class="col-xs-4"></div>
					</div>
				</div>
			</form:form>
		</div>
	</div> --%>

	<script type="text/javascript">
		$(document).ready(function() {
			$('#tabs > li').removeClass('active');
			$('#righttab').attr('class', 'active');
			$('#editTab').attr('class', 'active');
			$('#sel').prop('disabled', true);
		});

		$('#cancel').click(function() {
			window.history.back();
		});

		function submitUserForm() {

			$('#userRegisterForm')
					.formValidation(
							{
								icon : {
									valid : 'glyphicon glyphicon-ok',
									invalid : 'glyphicon glyphicon-remove',
									validating : 'glyphicon glyphicon-refresh'
								},
								fields : {
									firstName : {
										validators : {
											notEmpty : {
												message : 'First Name is Required'
											}
										}
									},
									lastName : {
										validators : {
											notEmpty : {
												message : 'Last Name is Required'
											}
										}
									},
									username : {
										message : 'User Id is not valid',
										validators : {
											notEmpty : {
												message : 'User Id is Required'
											},
											stringLength : {
												min : 2,
												max : 30,
												message : 'The user Id must be more than 2 and less than 30 characters long'
											},
											regexp : {
												regexp : /^[a-zA-Z0-9_\.]+$/,
												message : 'The user Id can only consist of alphabetical, number, dot and underscore'
											}
										}
									},
									password : {
										validators : {
											notEmpty : {
												message : 'Password is Required'
											}
										}
									},
									email : {
										validators : {
											notEmpty : {
												message : 'Email is Required'
											},
											emailAddress : {
												message : 'Not a valid Email Address'
											}

										}
									},
									phone : {
										validators : {
											notEmpty : {
												message : 'Phone is Required'
											},
											phone : {
												message : 'Not a valid Phone'
											},
											stringLength : {
												min : 0,
												max : 10,
												message : 'Phone Number must be 10 characters long'
											},
											regexp : {
												regexp : /^[0-9\-\(\)\+]+$/,
												message : 'The Phone Number can only consist of number, Brackets and plus symbole'
											}
										}
									},
									address1 : {
										validators : {
											notEmpty : {
												message : 'Address1 is Required'
											},
											stringLength : {
												min : 3,
												max : 30,
												message : 'Address1 must be more than 3 and less than 30 characters long'
											}
										}
									},
									'country.id' : {
										validators : {
											notEmpty : {
												message : 'Country is Required'
											}
										}
									},
									'state.id' : {
										validators : {
											notEmpty : {
												message : 'State is Required'
											}
										}
									},
									zipcode : {
										validators : {
											notEmpty : {
												message : 'ZipCode is Required'
											},
											stringLength : {
												min : 3,
												max : 7,
												message : 'ZipCode must be more than 3 and less than 7 characters long'
											}
										}
									},
									roleIds : {
										validators : {
											notEmpty : {
												message : 'User Role is Required'
											}
										}
									},
								}
							});

			return true;
		};
	</script>
	<!-- BackToTop Button -->
	<a href="javascript:void(0);" id="scroll" title="Scroll to Top"
		style="display: none;">Top<span></span></a>

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