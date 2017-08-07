<!-- <script type="text/javascript" src="../static/scripts/menu.js"></script> -->
<script type="text/javascript" src="../static/scripts/check-timeout.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	var timeout = '${pageContext.session.maxInactiveInterval}';
	var timedout;
	var warning;
	var warningTimeout = 2;
	function OpenInNewTab(url) {
		var win = window.open(url, '_blank');
		win.focus();
	}
	$(document).ready(
			function() {
				clearTimeout(warning);
				clearTimeout(timedout);
				warning = window.setTimeout(alertSessionTimeout,
						(timeout - (warningTimeout * 60)) * 1000);
				timedout = window.setTimeout(sessionExpired, timeout * 1000);

			});
</script>

<header class="navbar" id="header-navbar">
	<div class="container">
		<a href="#" id="logo" class="navbar-brand"> <img
			src="../static/img/logo.png" alt="UNODC"
			class="normal-logo logo-white" /> <!-- <img src="/static/img/logo-black.png" alt="UNODC"
					class="normal-logo logo-black" /> <img src="img/logo-small.png"
					alt="UNODC" class="small-logo hidden-xs hidden-sm hidden" /> -->
		</a>
		<div class="clearfix">
			<button class="navbar-toggle" data-target=".navbar-ex1-collapse"
				data-toggle="collapse" type="button">
				<span class="sr-only">Toggle navigation</span> <span
					class="fa fa-bars"></span>
			</button>
			<div
				class="nav-no-collapse navbar-left pull-left hidden-sm hidden-xs">
				<ul class="nav navbar-nav pull-left">
					<li><a class="btn" id="make-small-nav"> <i
							class="fa fa-bars"></i>
					</a></li>
				</ul>
			</div>
			<div class="nav-no-collapse pull-right" id="header-nav">
				<ul class="nav navbar-nav pull-right">
					<li class="dropdown profile-dropdown"><a style="cursor: pointer;"
						class="dropdown-toggle" data-toggle="dropdown"> 
						<c:if test="${empty profileImage}">
						<img
							src="<c:url value="/static/img/default_profile.png"></c:url>" alt="" />
						</c:if>
						<c:if test="${not empty profileImage}">
						<img
							src="data:image/jpeg;base64,${profileImage}" alt="" />
						</c:if>
						  <span
							class="hidden-xs" style="margin-top: -7px;"> <label
								style="margin-top: -8px; font-weight: bold ;cursor: pointer;" > ${name}
									${agencyCode} </label> <br /><label
								style="position: absolute; margin-top: -23px;cursor: pointer;">
									${userRole} </label></span> <b style="margin-top:8px;" class="caret"></b>
					</a>
						<ul class="dropdown-menu dropdown-menu-right">
							<li><a href="editProfile"><i class="fa fa-user"></i>Profile</a></li>
							<li><a href="changePassword"><i class="fa fa-cog"></i>Change
									Password</a></li>
							<li><a href="logout"><i class="fa fa-power-off"></i>Logout</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</div>
</header>

<div id="usageModal" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Usage Report</h4>
			</div>
			<div class="modal-body">
				<div class="main-box-body clearfix">
					<div class="row">
						<br />
						<div class="col-md-12" style="margin-left: 61px;position: relative;">
							<div class="col-xs-5">
								<div class="control-label col-xs-4">
									<label class=" pull-left" style="position: relative;top: 5px;right: -19px;"> <b>From : &nbsp;</b>
									</label>
								</div>

								<div class="col-md-8">
									<!-- <div style="position: relative; display: table; border-collapse: separate;">
										<input type='text' class="form-control datePicker"  id="fromDate"  placeholder="From"/> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-calendar"></span> </span>
									</div> -->
									<div class="input-group" style="position: relative; display: table; border-collapse: separate;">
										<input type="text" id="fromDate" class="form-control datePicker"  style="width:92px"
											placeholder="From Date" /> <label for="fromDate" style="cursor:pointer;"
											class="input-group-addon"><span
											class="glyphicon glyphicon-calendar"></span></label>
									</div>
								</div>

							</div>
							<div class="col-xs-5" style="left: -35px">
								<div class="control-label col-xs-3">
									<label class=" pull-left" style="position: relative;top: 5px;right: -19px;"> <b>To : &nbsp;</b>
									</label>
								</div>

								<div class="col-md-8">
									<!-- <input class="form-control datePicker" id="toDate"
										placeholder="To" /> -->
										<div class="input-group" style="position: relative; display: table; border-collapse: separate;">
										<input type="text" id="toDate" class="form-control datePicker" style="width:92px"
											placeholder="To Date" /> <label for="toDate" style="cursor:pointer;"
											class="input-group-addon"><span
											class="glyphicon glyphicon-calendar"></span></label>
									</div>
								</div>
							</div>

						</div>
						<br /> <br />
						<div class="col-md-12" id="errorAlert" style="display: none;">
							<div class="alert alert-danger" id="fromDateValid"
								style="display: none">
								<strong>From date </strong>is required !
							</div>
							<div class="alert alert-danger" id="toDateValid"
								style="display: none">
								<strong>To date </strong>is required !
							</div>
							<div class="alert alert-danger" id="futureDateValid"
								style="display: none">
								<strong>Future date </strong>is not allowed !
							</div>
							<div class="alert alert-danger" id="fromDateBig"
								style="display: none">
								<strong>From date </strong>can not be longer than<strong>
									To date !</strong>
							</div>
							<div class="alert alert-info" id="noUsage" style="display: none">
								<strong>No Usage</strong> Found for these dates
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-primary"
					onclick="downloadLoginStatusReport()">Download</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>

	</div>
</div>
<script type="text/javascript"
	src='<c:url value="/static/jslib/bootstrap-datepicker.js"></c:url>'></script>
<script type="text/javascript"
	src="<c:url value="/static/scripts/usage.js" />"></script>
<script type="text/javascript">
	/* $(document).ready(function(){ */
	$('.datePicker').datepicker({
		autoclose : true,
		format : 'dd-mm-yyyy',
		endDate : new Date(),
		todayHighlight : 1
	});
	/* }); */
</script>

