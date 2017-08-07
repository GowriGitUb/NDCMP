<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="include/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UNODC</title>
<link href="<c:url value='/static/css/bootstrap/bootstrap.min.css' />" rel="stylesheet"></link>
<link href="<c:url value='/static/css/theme_styles.css' />" rel="stylesheet"></link>
<link rel="stylesheet" type="text/css"
	href='<c:url value="/static/css/libs/font-awesome.css"></c:url>' />
</head>

<body id="login-page">
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<div id="login-box">
					<div id="login-box-holder">
						<div class="row">
							<div class="col-xs-12">
								<header id="login-header">
									<div id="login-logo">
										<img src="<c:url value="/static/img/logo.png" />"
											alt="Login Logo" title="Login Logo">
									</div>
								</header>
								<div id="login-box-inner">
									<c:url var="loginUrl" value="/login" />
										<c:if test="${param.error != null}">
												<p style="color: #dd191d">Invalid user Id and password.</p>
										</c:if>
										<c:if test="${param.logout != null}">
												<p style="color: #689f38">You have been logged out successfully.</p>
										</c:if>
										
										<c:if test="${session != null && param.logout == null && param.error == null}">
												<p style="color: #689f38">${session}</p>
										</c:if>
										
										<c:if test="${param.role != null}">
												<p style="color: #dd191d">Your role has been disabled</p>
										</c:if>
										
										<c:if test="${param.type != null}">
												<p style="color: #dd191d">You don't have rights to access web</p>
										</c:if>
										
										
										<c:if test="${invalidUserStatus != null}">
												<p style="color: #dd191d">${invalidUserStatus}</p>
										</c:if>
										
										<c:if test="${invaliduser != null }">
											<p style="color: #dd191d">${invaliduser}</p>
										</c:if>
										
										<c:if test="${invalidRoleStatus != null }">
											<p style="color: #dd191d">${invalidRoleStatus}</p>
										</c:if>
									
									<form action="${loginUrl}" method="post"
										class="form-horizontal">
										<div class="input-group" style="display: table;">
											<span class="input-group-addon"><i class="fa fa-user"></i></span>
											<input type="text" id="username" name="username" class="form-control"
												placeholder="User Id">
										</div>

										<div class="input-group" style="display: table;">
											<span class="input-group-addon"><i class="fa fa-key"></i></span>
											<input type="password" id="password" name="password" class="form-control"
												placeholder="Password">
										</div>
										<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
										<div id="remember-me-wrapper">
											<div class="row">
												<div class="col-xs-6">
													<div class="checkbox-nice">
														<input type="checkbox" id="remember-me" checked="checked" />
														<label for="remember-me"> Remember me </label>
													</div>
												</div>
												<a href="forgotPassword"
													id="login-forget-link" class="col-xs-6 checkbox-nice" style="color: red;">
													Forgot password? </a>
											</div>
										</div>
										<div class="row">
											<div class="col-xs-12">
												<button type="submit" class="btn btn-success col-xs-12">Login</button>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>