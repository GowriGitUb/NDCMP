

<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
	<div id="page-wrapper" class="container">
<div class="row">
	<div id="nav-col">
		<section id="col-left" class="col-left-nano">
			<div id="col-left-inner" class="col-left-nano-content">
				<div class="collapse navbar-collapse navbar-ex1-collapse"
					id="sidebar-nav">
					<sec:authorize access="hasRole('SUPER_ADMIN')">
						<div id="modules"></div>
					</sec:authorize>

					<sec:authorize access="hasRole('PROJECT_ADMIN')">
						<div id="modules"></div>
					</sec:authorize>

					<sec:authorize access="hasRole('PROJECT_PLANNER')">
						<div id="modules"></div>
					</sec:authorize>

					<sec:authorize access="hasRole('STATUS_REVIEWER')">
						<div id="modules"></div>
					</sec:authorize>

					<sec:authorize access="hasRole('STATUS_UPDATER')">
						<div id="modules"></div>
					</sec:authorize>

					<sec:authorize access="hasRole('STATUS_APPROVER')">
						<div id="modules"></div>
					</sec:authorize>

				</div>
			</div>
		</section>
		
		<div id="nav-col-submenu"></div>
	</div>


</div>
</div>
<!-- <footer id="footer-bar" class="row" style="background-color: #272d33;border-top: #272d33">
	<p id="footer-copyright" class="col-xs-12" style="background-color: #272d33"></p>
</footer> -->
