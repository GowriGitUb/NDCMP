<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="include/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UNODC</title>
<%-- <link href="<c:url value='/static/css/theme_style.css' />" rel="stylesheet"></link>
<%@include file="include/commonlib.jsp"%> --%>
<link href="<c:url value='/static/css/bootstrap/bootstrap.min.css' />" rel="stylesheet"></link>
<link href="<c:url value='/static/css/theme_styles.css' />" rel="stylesheet"></link>
<link rel="stylesheet" type="text/css"
	href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
	<script type="text/javascript" src="<c:url value='/static/jslib/jquery-1.12.0.min.js' /> "></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#email').val("");
	});
</script>
</head>

<body id="login-page">
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<div id="login-box">
					<div id="login-box-holder">
						<div class="row">
							<div class="col-xs-12">
								<%-- <header id="login-header">
									<div id="login-logo">
										<img src="<c:url value="/static/images/login.png" />"
											alt="Login Logo" title="Login Logo">
									</div>
								</header> --%>
								<div id="login-box-inner" class="with-heading">
									<h4>Forgot your password?</h4>
									<p>Enter your email address to recover your password.</p>
									<c:url var="forgotUrl" value="/forgotPassAction" />
									<p style="color: red">${msg }</p>
									<form:form id="userRegisterForm" cssClass="form-horizontal"
										modelAttribute="user" method="post" action="${forgotUrl}">

										<div class="input-group reset-pass-input"
											style="display: table;">
											<span class="input-group-addon"><i class="glyphicon glyphicon-envelope" style="color: coral;"></i></span>
											<form:input cssClass="form-control" path="email" />
										</div>
										<div class="row">
											<div class="col-xs-12">
												<button type="submit" class="btn btn-success col-xs-12">Reset
													password</button>
											</div>
											<div class="col-xs-12" align="right">
												 <a href="login" id="login-forget-link"
													class="forgot-link col-xs-12">Back to login</a>
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

</body>
</html>