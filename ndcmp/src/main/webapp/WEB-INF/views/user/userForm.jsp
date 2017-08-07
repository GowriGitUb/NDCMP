<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../include/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UNODC</title>
<%@include file="../include/commonlib.jsp"%>
<script src="<c:url value="/static/js/user.js" />"></script>
<script type="text/javascript"
	src='<c:url value="/static/scripts/userList.js"></c:url>'></script>
<script type="text/javascript" src="../static/scripts/menu.js"></script>
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
	$(document).ready(
			function() {
				$('#tabs > li').removeClass('active');
				$('#userTab').attr('class', 'active');
				//getStateByCountry();

				var userRoleName = "${userRoleName}";
				if (userRoleName == "STATUS_UPDATER"
						|| userRoleName == "STATUS_REVIEWER"
						|| userRoleName == "STATUS_APPROVER") {
					$('#agencyDiv').show();
				} else {
					$('#agencyDiv').hide();
				}

				/* var userImage = "${userImage}";
				if (userImage != null && userImage != "") {
					$('#imageId').show();
				} else {
					$('#imageId').hide();
				} */

				var countryId = "${countyId}";
				var stateId = "${stateId}";
				var agencyId = "${agencyId}";
				var roleName = "${roleName}";
				if (countryId == null || countryId == "" && stateId == null
						|| stateId == "") {
				} else {
					loadStates(countryId, stateId, agencyId, roleName);
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
										<c:when test="${userObject.id == null }">
											<span>Add User</span>
										</c:when>
										<c:otherwise>
											<span>Update User</span>
										</c:otherwise>
									</c:choose>
								</h1>
								<br /> <br />
								<hr />
								<form:form id="userRegisterForm" cssClass="form-horizontal"
									modelAttribute="userObject" method="post" action="saveUser"
									enctype="multipart/form-data">

									<div class="form-group">
										<div class="control-label col-xs-3">
											<form:label path="firstName" cssClass="labelColor">First Name : &nbsp;<span
													style="color: red">*</span>
											</form:label>
										</div>
										<div class="col-xs-3">
											<form:hidden path="id" value="${userObject.id}" />
											<form:input cssClass="form-control" path="firstName"
												placeholder="First Name" />
										</div>
									</div>
									<div class="form-group">
										<div class="control-label col-xs-3">
											<form:label path="lastName" cssClass="labelColor">Last Name : &nbsp;<span
													style="color: red">*</span>
											</form:label>
										</div>
										<div class="col-xs-3">
											<form:input cssClass="form-control" path="lastName"
												placeholder="Last Name" />
										</div>
									</div>

									<div class="form-group">
										<div class="control-label col-xs-3">
											<form:label path="username" cssClass="labelColor">User Name : &nbsp;<span
													style="color: red">*</span>
											</form:label>
										</div>
										<div class="col-xs-3">
											<c:if test="${userObject.id != null }">
												<form:input cssClass="form-control" path="username"
													readonly="true" placeholder="User Id" />
											</c:if>
											<c:if test="${userObject.id == null}">
												<form:input cssClass="form-control" path="username"
													value="${userObject.username}" placeholder="User Id" />
											</c:if>
										</div>
										<div>
											<span style="color: #D00B05">${usrerrormsg}</span>
										</div>
									</div>

									<c:choose>
										<c:when test="${userObject.id == null}">
											<div class="form-group">
												<div class="control-label col-xs-3">
													<form:label path="password" cssClass="labelColor">Password : &nbsp;<span
															style="color: red">*</span>
													</form:label>
												</div>
												<div class="col-xs-3">
													<form:password cssClass="form-control" path="password"
														placeholder="Password" />
												</div>
											</div>
										</c:when>

										<c:otherwise>
											<form:hidden cssClass="form-control" path="password" />
											<form:hidden cssClass="form-control" path="mobilePassword" />
										</c:otherwise>
									</c:choose>
									<div class="form-group">
										<div class="control-label col-xs-3">
											<form:label path="userType" cssClass="labelColor">User Type : &nbsp;<span
													style="color: red">*</span>
											</form:label>
										</div>
										<div class="col-xs-2">
											<form:select path="userType" class="form-control"
												id="userType" multiple="multiple">
												<c:choose>
													<c:when test="${userObject.id == null }">
														<form:option selected="selected" value="Web" id="Web">Web</form:option>
														<form:option value="Mobile" id="Mobile">Mobile</form:option>
													</c:when>
													<c:otherwise>
														<form:option value="Web" id="Web">Web</form:option>
														<form:option value="Mobile" id="Mobile">Mobile</form:option>
													</c:otherwise>
												</c:choose>
											</form:select>
										</div>
									</div>

									<div class="form-group">
										<div class="control-label col-xs-3">
											<form:label path="email" cssClass="labelColor">Email : &nbsp;<span
													style="color: red">*</span>
											</form:label>
										</div>
										<div class="col-xs-3">
											<c:if test="${userObject.id != null }">
												<form:input cssClass="form-control" path="email"
													value="${userObject.email}" readonly="true"
													placeholder="Email" />
												<!-- onmousedown="return validateEmail(this);" -->
											</c:if>
											<c:if test="${userObject.id == null }">
												<form:input cssClass="form-control" path="email"
													value="${userObject.email}" placeholder="Email" />
												<!-- onmousedown="return validateEmail(this);" -->
											</c:if>
											<%-- <form:input cssClass="form-control" path="email" /> --%>
										</div>
										<div>
											<span style="color: #D00B05">${emailErrormsg}</span>
										</div>
									</div>
									<div class="form-group">
										<div class="control-label col-xs-3">
											<form:label path="phone" cssClass="labelColor">Phone : &nbsp;<span
													style="color: red">*</span>
											</form:label>
										</div>
										<div class="col-xs-2">
											<form:input cssClass="form-control" path="phone" onkeypress="return isNumber(event)"
												placeholder="Phone" style="text-align: right;"
												maxlength="10" />
											<form:errors path="phone"></form:errors>
										</div>
									</div>

									<div class="form-group">
										<div class="control-label col-xs-3">
											<form:label path="address1" cssClass="labelColor">Address1 : &nbsp;<span
													style="color: red">*</span>
											</form:label>
										</div>
										<div class="col-xs-3">
											<form:input cssClass="form-control" path="address1"
												placeholder="Address1" />
										</div>
									</div>
									<div class="form-group">
										<div class="control-label col-xs-3">
											<form:label path="address2" cssClass="labelColor">Address2 :&nbsp;&nbsp;&nbsp;</form:label>
										</div>
										<div class="col-xs-3">
											<form:input cssClass="form-control" path="address2"
												placeholder="Address2" />
										</div>
									</div>

									<div class="form-group">
										<div class="control-label col-xs-3">
											<form:label path="address3" cssClass="labelColor">Address3 :&nbsp;&nbsp;&nbsp;</form:label>
										</div>
										<div class="col-xs-3">
											<form:input cssClass="form-control" path="address3"
												placeholder="Address3" />
										</div>
									</div>
									<div class="form-group">
										<div class="control-label col-xs-3">
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
												<div class="control-label col-xs-3">
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
												<div class="control-label col-xs-3">
													<form:label path="state" cssClass="labelColor">State : &nbsp;<span
															style="color: red">*</span>
													</form:label>
												</div>
												<div class="col-xs-3">
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
										<div class="control-label col-xs-3">
											<form:label path="firstName" cssClass="labelColor">Zipcode : &nbsp;<span
													style="color: red">*</span>
											</form:label>
										</div>
										<div class="col-xs-2">
											<form:input cssClass="form-control" path="zipcode"  onkeypress="return isNumber(event)"
												placeholder="Zipcode" style="text-align: right;"
												maxlength="6" />
										</div>
									</div>
									<div class="form-group">
										<div class="control-label col-xs-3">
											<form:label path="roleId" cssClass="labelColor">User Role : &nbsp;<span
													style="color: red">*</span>
											</form:label>
										</div>
										<div class="col-xs-3">
											<form:select id="userRoleId" path="userRole.id"
												cssClass="form-control" onchange="loadAgencies(this)">
												<form:option value="" label="---Select User Role---"></form:option>
												<c:forEach items="${userRoles}" var="r">
													<form:option value="${r.id}" label="${r.description}"></form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>
									<%-- <c:if test="${ userObject.id == null}">
											<div class="form-group">
												<div class="control-label col-xs-3">
													<form:label path="roleIds" cssClass="labelColor">User Role : &nbsp;<span
															style="color: red">*</span>
													</form:label>
												</div>
												<div class="col-xs-3">
													<form:select path="roleIds" cssClass="form-control" onclick="loadAgencies(this)">
														<form:options items="${userRoles}" itemLabel="description"
															itemValue="id" />
													</form:select>
												</div>
											</div>
											</c:if>
											<c:if test="${ userObject.id != null}">
											<div class="form-group">
												<div class="control-label col-xs-3">
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
											</div>
											
													<div class="form-group">
														<div class="control-label col-xs-3">
															<form:label path="agencyId" cssClass="labelColor">Agency : &nbsp;<span
																	style="color: red">*</span>
															</form:label>
														</div>
														<div class="col-xs-2">
															<form:select id="agencyId" path="agencyId"
																cssClass="form-control">
																<form:option value="" label="---Select Agency---"></form:option>
																<c:forEach items="${agencyList}" var="agency">
																	<form:option value="${agency.id}" label="${agency.code}"></form:option>
																</c:forEach>
															</form:select>
														</div>
													</div>
											</c:if> --%>
									<div id="agencyDiv">
										<div class="form-group">
											<div class="control-label col-xs-3">
												<form:label path="agencyId" cssClass="labelColor">Agency : &nbsp;<span
														style="color: red">*</span>
												</form:label>
											</div>
											<div class="col-xs-2">
												<form:select id="agencyId" path="agencyId"
													cssClass="form-control">
													<form:option value="" label="---Select Agency---"></form:option>
													<c:forEach items="${agencyList}" var="agency">
														<form:option value="${agency.id}" label="${agency.code}"></form:option>
													</c:forEach>
												</form:select>
											</div>
										</div>
									</div>

									<div class="form-group">
										<div class="control-label col-xs-3">
											<form:label path="roleIds" cssClass="labelColor">Profile Image : &nbsp;&nbsp;
													</form:label>
										</div>
										<div class="col-xs-2">
											<input type="file" class="form-control" name="multipartFile"
												id="multipartFile" accept="image/gif, image/jpeg, image/png"
												onchange="encodeImageFileAsURL()" />
											<%-- <input type="file" accept="image/gif, image/jpeg, image/png" cssClass="form-control" id="profile" onchange="encodeImageFileAsURL()" />
													<input type="hidden" name="profileimg" id="profileimg" value="${userImage}"/> --%>
										</div>
										<br />
										<div id="imageId">
											<%-- <img id="imgFile" alt="" src="data:image/jpeg;base64,${userImage}" class="img-circle" style="margin-left: 119px;margin-top: -19px;" height="100px" width="100px"> --%>
											<c:if test="${(empty userImage)}">
												<img id="imgFile" class="img-circle" src="<c:url value="/static/img/default_profile.png"></c:url>" alt=""  style="margin-left: 119px; margin-top: -19px;"
													height="100px" width="100px"/>
											</c:if>
											<c:if test="${not empty userImage}">
												<img id="imgFile" alt=""
													src="data:image/jpeg;base64,${userImage}"
													class="img-circle"
													style="margin-left: 119px; margin-top: -19px;"
													height="100px" width="100px">
											</c:if>
												
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
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="config-tool" class="closed">
		<div id="config-tool-options">
			<h4>Layout Options</h4>
			<ul>
				<li>
					<div class="checkbox-nice">
						<input type="checkbox" id="config-fixed-header" /> <label
							for="config-fixed-header"> Fixed Header </label>
					</div>
				</li>
				<li>
					<div class="checkbox-nice">
						<input type="checkbox" id="config-fixed-sidebar" /> <label
							for="config-fixed-sidebar"> Fixed Left Menu </label>
					</div>
				</li>
				<li>
					<div class="checkbox-nice">
						<input type="checkbox" id="config-fixed-footer" /> <label
							for="config-fixed-footer"> Fixed Footer </label>
					</div>
				</li>
			</ul>
			<br />
			<!-- <h4>Skin Color</h4>
			<ul id="skin-colors" class="clearfix">
				<li><a class="skin-changer" data-skin="" data-toggle="tooltip"
					title="Default" style="background-color: #34495e;"> </a></li>
				<li><a class="skin-changer" data-skin="theme-white"
					data-toggle="tooltip" title="White/Green"
					style="background-color: #2ecc71;"> </a></li>
				<li><a class="skin-changer blue-gradient"
					data-skin="theme-blue-gradient" data-toggle="tooltip"
					title="Gradient"> </a></li>
				<li><a class="skin-changer" data-skin="theme-turquoise"
					data-toggle="tooltip" title="Green Sea"
					style="background-color: #1abc9c;"> </a></li>
				<li><a class="skin-changer" data-skin="theme-amethyst"
					data-toggle="tooltip" title="Amethyst"
					style="background-color: #9b59b6;"> </a></li>
				<li><a class="skin-changer" data-skin="theme-blue"
					data-toggle="tooltip" title="Blue"
					style="background-color: #2980b9;"> </a></li>
				<li><a class="skin-changer" data-skin="theme-red"
					data-toggle="tooltip" title="Red"
					style="background-color: #e74c3c;"> </a></li>
				<li><a class="skin-changer" data-skin="theme-whbl"
					data-toggle="tooltip" title="White/Blue"
					style="background-color: #3498db;"> </a></li>
			</ul> -->
		</div>
	</div>
	<%-- <%@include file="../include/menu.jsp"%>

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
				modelAttribute="userObject" method="post" action="saveUser">

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

				<c:choose>
					<c:when test="${userObject.id == null}">
						<div class="form-group">
							<div class="control-label col-xs-2">
								<form:label path="password" cssClass="labelColor">Password : &nbsp;<span
										style="color: red">*</span>
								</form:label>
							</div>
							<div class="col-xs-6">
								<form:password cssClass="form-control" path="password" />
							</div>
						</div>
					</c:when>

					<c:otherwise>
						<form:hidden cssClass="form-control" path="password" />
					</c:otherwise>
				</c:choose>
				<div class="form-group">
					<div class="control-label col-xs-2">
						<form:label path="userType" cssClass="labelColor">User Type : &nbsp;<span
								style="color: red">*</span>
						</form:label>
					</div>
					<div class="col-xs-6">
						<form:select id="userType" path="userType" cssClass="form-control">
							<form:option value="" label="---Select User Type---"></form:option>
							<form:option value="Web">Web</form:option>
							<form:option value="Mobile">Mobile</form:option>
						</form:select>
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

				<c:choose>
					<c:when test="${userObject.id == null}">
						<div class="form-group">
							<div class="control-label col-xs-2">
								<form:label path="state" cssClass="labelColor">State : &nbsp;<span
										style="color: red">*</span>
								</form:label>
							</div>
							<div class="col-xs-6">
								<form:select id="state" path="state.id" cssClass="form-control">
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
							<div class="col-xs-6">
								<form:select id="state" path="state.id" cssClass="form-control">
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
					<div class="col-xs-6">
						<form:input cssClass="form-control" path="zipcode" />
					</div>
				</div>


				<div class="form-group">
					<div class="control-label col-xs-2">
						<form:label path="roleIds" cssClass="labelColor">User Role : &nbsp;<span
								style="color: red">*</span>
						</form:label>
					</div>
					<div class="col-xs-6">
						<form:select path="roleIds" cssClass="form-control">
							<form:options items="${userRoles}" itemLabel="description"
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