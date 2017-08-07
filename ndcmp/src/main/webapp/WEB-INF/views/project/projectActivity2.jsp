<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../include/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NDCMP</title>
<%@include file="../include/commonlib.jsp"%>
<%@include file="../include/dataTable.jsp"%>
<style>
.panel-default>.panel-heading {
	/* background-color: #03a9f4;
	border-color: #03a9f4; */
	background-color: #2C3E50;
	border-color: #2C3E50 color:     #FFFFFF;
	border-radius: 0;
	background-clip: padding-box;
}

.panel-default>.panel-heading a:focus {
	color: #fff;
}

.panel-group .panel {
	border-radius: 0;
	background-clip: padding-box;
	border: 0 none;
}

.panel-collapse {
	border: 2px solid #e1e1e1;
	border-top: 0;
}

.accordion .panel-title>a {
	display: block;
	position: relative;
	outline: none;
	text-decoration: none;
	color: #FFFFFF;
	padding-right: 15px;
}

.accordion .panel-title>a:hover {
	text-decoration: none;
	color: #FFFFFF;
}

.accordion .panel-title>a:after {
	content: "\2212";
	font-family: FontAwesome;
	font-style: normal;
	font-weight: normal;
	text-decoration: inherit;
	margin-top: -5px;
	font-size: 0.75em;
	position: absolute;
	right: 0;
	top: 50%;
	font-family: FontAwesome;
}

.accordion .panel-title>a.accordion-toggle.collapsed:after {
	content: "\2b"
}

.accordion .panel-title>a.accordion-toggle>i {
	width: 24px;
	text-align: center;
	padding-right: 6px;
}
</style>
</head>
<body>
	<%@include file="../include/menu.jsp"%>
	<div class="container">
		<div align="left">
			<b>Project Activity List</b>
		</div>
		<hr />
		<div class="container">
			<button type="button" class="btn btn-primary btn-add-panel" data-toggle="modal" data-target="#strategicPillarModal"><i class="glyphicon glyphicon-plus"></i>Add new Pillar</button>
			
			<!-- Strategic Pillar Modal -->
					<div class="modal fade" id="strategicPillarModal" role="dialog">
						<div class="modal-dialog">

							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
							<div align="left">
								<c:choose>
									<c:when test="${strategicPillar.id == null}">
										<h4>Add StrategicPillar</h4>
									</c:when>
									<c:otherwise>
										<h4>Update StrategicPillar</h4>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
								<div class="modal-body">
									 <div class="container">
										<div>
											<form:form id="addStrategicPillarForm"
												cssClass="form-horizontal" modelAttribute="strategicPillar"
												method="post" action="createStrategicpillar">

												<div class="form-group">
													<div class="control-label col-xs-2">
														<label cssClass="labelColor">StrategicPillar Name : &nbsp;<span
																style="color: red">*</span></label>
													</div>
													<div class="col-xs-4">
														<input type="hidden" id="strategicPillarId" name="strategicPillarId" value="${strategicPillar.id}" />
														<form:input type="text" path="name" style="margin-top: 5px;" class="form-control" />
													</div>
												</div>
												
												<div class="form-group">
													<div class="control-label col-xs-2">
														<label cssClass="labelColor">Project Name : &nbsp;<span
																style="color: red">*</span></label>
													</div>
													<div class="col-xs-4">
														<input id="projectName" name="project.name" style="margin-top: 5px;" readonly="readonly" class="form-control" />
													</div>
												</div>
												<div class="form-group">
													<div class="row">
														<div class="col-xs-4"></div>
														<div class="col-xs-4">
															<input type="submit" id="saveStrategicPillar"
																class="btn btn-primary" value="Save"
																onclick="return submitStrategicPillarForm();" style="margin-left: -232px;" /> <input
																type="button" id="cancel" value="Cancel"
																class="btn btn-primary" />
														</div>
														<div class="col-xs-4"></div>
													</div>
												</div>
											</form:form>
										</div>
									</div>
									</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
								</div>
							</div>

						</div>
					</div>
					
					<!-- Theme Modal -->
			<div class="modal fade" id="themeModal" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<div align="left">
								<c:choose>
									<c:when test="${theme.id == null}">
										<h2>Add Theme</h2>
									</c:when>
									<c:otherwise>
										<h2>Update Theme</h2>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="modal-body">
							<div class="container">
								<div>
									<form:form id="themeAddform" cssClass="form-horizontal"
										modelAttribute="theme" method="post" action="createTheme">

										<div class="form-group">
											<div class="control-label col-xs-2">
												<label cssClass="labelColor">Theme Name : &nbsp;<span
													style="color: red">*</span></label>
											</div>
											<div class="col-xs-4">
												<input type="hidden" id="themeId" name="themeId"
													value="${theme.id}" /> <input type="text" name="name"
													style="margin-top: 5px;" class="form-control" />
											</div>
										</div>

										<div class="form-group">
											<div class="control-label col-xs-2">
												<label cssClass="labelColor">Strategic Pillar :
													&nbsp;<span style="color: red">*</span>
												</label>
											</div>
											<div class="col-xs-4">
												<input id="strategicPillarName" name="strategicPillar.name"
													style="margin-top: 5px;" class="form-control"
													readonly="readonly" />
											</div>
										</div>
										<div class="form-group">
											<div class="row">
												<div class="col-xs-4"></div>
												<div class="col-xs-4">
													<input type="submit" id="saveTheme" class="btn btn-primary"
														value="Save" onclick="return submitThemeForm();"
														style="margin-left: -232px;" /> <input type="button"
														id="cancel" value="Cancel" class="btn btn-primary" />
												</div>
												<div class="col-xs-4"></div>
											</div>
										</div>
									</form:form>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
			
			<!-- Outcome Modal -->
			<div class="modal fade" id="outcomeModal" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<div align="left">
								<c:choose>
									<c:when test="${outcome.id == null}">
										<h2>Add Outcome</h2>
									</c:when>
									<c:otherwise>
										<h2>Update Outcome</h2>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="modal-body">
							<div class="container">
								<div>
									<form:form id="outcomeAddform" cssClass="form-horizontal"
										modelAttribute="outcome" method="post" action="createOutcome">

										<div class="form-group">
											<div class="control-label col-xs-2">
												<label cssClass="labelColor">Outcome Name : &nbsp;<span
													style="color: red">*</span></label>
											</div>
											<div class="col-xs-4">
												<input type="hidden" id="outcomeId" name="outcomeId"
													value="${outcome.id}" /> <input type="text" name="name"
													style="margin-top: 5px;" class="form-control" />
											</div>
										</div>

										<div class="form-group">
											<div class="control-label col-xs-2">
												<label cssClass="labelColor">Theme :
													&nbsp;<span style="color: red">*</span>
												</label>
											</div>
											<div class="col-xs-4">
												<input id="themeName" name="theme.name"
													style="margin-top: 5px;" class="form-control"
													readonly="readonly" />
											</div>
										</div>
										<div class="form-group">
											<div class="row">
												<div class="col-xs-4"></div>
												<div class="col-xs-4">
													<input type="submit" id="saveOutcome" class="btn btn-primary"
														value="Save" onclick="return submitOutcomeForm();"
														style="margin-left: -232px;" /> <input type="button"
														id="cancel" value="Cancel" class="btn btn-primary" />
												</div>
												<div class="col-xs-4"></div>
											</div>
										</div>
									</form:form>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>

			<br />
			<div class="row">
				<div class="col-sm-12 col-md-12">
					<div class="panel-group accordion" id="accordion">
					<c:if test="${not empty strategicPillars }">
					<c:forEach items="${ strategicPillars}" var="strategic">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle" data-toggle="collapse"
										data-parent="#accordion" href="#collapseOne_${strategic.strategicPillar.id }"> <span><b>${strategic.strategicPillar.name }</b></span>
										<input type="hidden" id="strategicId" value="${strategic.strategicPillar.id}" />
									</a>
								</h4>
							</div>
							<div id="collapseOne_${strategic.strategicPillar.id }" class="panel-collapse collapse in strategic-pillar">
								<div class="panel-body">
								<c:if test="${not empty strategic.strategicPillar.id }">
								<button type="button" class="btn btn-primary btn-add-panel1" data-toggle="modal" data-target="#themeModal"><i class="glyphicon glyphicon-plus"></i>Add new Theme</button>
								</c:if>
									<div class="panel-group accordion" id="accordion1">
									<c:if test="${not empty strategic.themes }">
									<c:forEach items="${ strategic.themes}" var="theme">
										<div class="panel panel-default template1">
											<div class="panel-heading">
												<h4 class="panel-title">
													<a class="accordion-toggle" data-toggle="collapse"
														data-parent="#collapseOne" href="#collapseOne_${theme.id }"> <span><b>
																${theme.name } </b></span>
													</a>
												</h4>
											</div>
											<div id="collapseOne_${theme.id }" class="panel-collapse collapse in theme">
												<div class="panel-body">
													<c:if test="${not empty theme.id }">
														<button type="button" class="btn btn-primary btn-add-panel2" data-toggle="modal" data-target="#outcomeModal"><i class="glyphicon glyphicon-plus"></i>Add new Outcome</button>
													</c:if>
													<div class="panel-group accordion" id="accordion2">
													<c:if test="${not empty hierarchyTheme.outcomes }">
													<c:forEach items="${ hierarchyTheme.outcomes}" var="outcome">
														<div class="panel panel-default template2">
															<div class="panel-heading">
																<h4 class="panel-title">
																	<a class="accordion-toggle" data-toggle="collapse"
																		data-parent="#collapseOne" href="#collapseOne${outcome.id }">
																			<span><b> ${outcome.name } </b></span>
																	</a>
																		
																</h4>
															</div>
														</div>
														</c:forEach>
														</c:if>
													</div>

												</div>
											</div>
										</div>
										</c:forEach>
									</c:if>
									</div>

								</div>
							</div>
						</div>
						</c:forEach></c:if>
					</div>
				</div>
			</div>
		</div>

	</div>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$('#tabs > li').removeClass('active');
			$('#projectsTab').attr('class', 'active');
		});
		
		$(".btn-add-panel").on("click", function() {
			$.ajax({
				url : '/ndcmp/api/getStrategicpillar',
				data : {
					
				},
				type : 'GET',
				async : false,
				dataType : 'json',
				success : function(data) {
					if (data && data.trim != '') {
						var ele = data.project.name;
						/* $('#projectName').html(ele); */
						document.getElementById("projectName").value = ele;
					}
				}
			});
		});
		
		$(".btn-add-panel1").on("click", function() {
			var parent = $(this).closest('.strategic-pillar');
			var id = $(parent).attr('id');
			var strategicPillarId = id.split('_')[1];
			//var strategicPillarId = $('#strategicId').val();
			$.ajax({
				url : '/ndcmp/api/getTheme',
				data : {
					strategicPillarId : strategicPillarId
				},
				type : 'GET',
				async : false,
				dataType : 'json',
				success : function(data) {
					if (data && data.trim != '') {
						var ele = data.strategicPillar.name;
						/* $('#projectName').html(ele); */
						document.getElementById("strategicPillarName").value = ele;
					}
				}
			});
		});
		
		$(".btn-add-panel2").on("click", function() {
			var parent = $(this).closest('.theme');
			var id = $(parent).attr('id');
			var themeId = id.split('_')[1];
			//var strategicPillarId = $('#strategicId').val();
			$.ajax({
				url : '/ndcmp/api/getHierarchyOutcome',
				data : {
					themeId : themeId
				},
				type : 'GET',
				async : false,
				dataType : 'json',
				success : function(data) {
					if (data && data.trim != '') {
						var ele = data.theme.name;
						/* $('#projectName').html(ele); */
						document.getElementById("themeName").value = ele;
					}
				}
			});
		});
		
		$('#cancel').click(function() {
			window.history.back();
		});

		function submitStrategicPillarForm(){
			
			$('#addStrategicPillarForm').formValidation({
				icon : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				fields : {
					name: {
		                validators: {
		                    notEmpty: {
		                        message: 'StrategicPillar Name is Required'
		                    }
		                }
		            },
		            description: {
		                validators: {
		                    notEmpty: {
		                        message: 'Description is Required'
		                    }
		                }
		            },
		            'project.id': {
		                validators : {
							notEmpty : {
								message : 'Project is Required'
							}
						}
		            },
				}
			});
			
			return true;
		};
		
		function submitThemeForm() {
			$('#themeAddform').formValidation({
		        icon: {
		            valid: 'glyphicon glyphicon-ok',
		            invalid: 'glyphicon glyphicon-remove',
		            validating: 'glyphicon glyphicon-refresh'
		        },
		        fields: {
		        	name: {
		                validators : {
							notEmpty : {
								message : 'Theme Name is Required'
							},
							stringLength : {
								min : 3,
								max : 25,
								message : 'Theme Name must be more than 3 and less than 25 characters long'
							}
						}
		            },
		            'strategicPillar.name': {
		            	validators : {
							notEmpty : {
								message : 'Description is Required'
							},
						}
		            }
		        }
		    });
			return true;
		};
		
		function submitOutcomeForm() {
			$('#outcomeAddform').formValidation({
		        icon: {
		            valid: 'glyphicon glyphicon-ok',
		            invalid: 'glyphicon glyphicon-remove',
		            validating: 'glyphicon glyphicon-refresh'
		        },
		        fields: {
		        	name: {
		                validators : {
							notEmpty : {
								message : 'Outcome Name is Required'
							},
							stringLength : {
								min : 3,
								max : 25,
								message : 'Outcome Name must be more than 3 and less than 25 characters long'
							}
						}
		            },
		            'theme.name': {
		            	validators : {
							notEmpty : {
								message : 'Theme Name is Required'
							},
						}
		            }
		        }
		    });
			return true;
		};
	</script>
	
</body>
</html>