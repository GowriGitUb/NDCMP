<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../include/taglibs.jsp"%>
<
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UNODC</title>
<%@include file="../include/commonlib.jsp"%>
<body>
	<%@include file="../include/menu.jsp"%>
	<div class="container">
		<div align="left">
			<b>Add / Edit Project</b>
		</div>
		<hr />
		<div>
			<form:form id="projectAddform" cssClass="form-horizontal"
				modelAttribute="project" method="post" action="addAndEditProject">

				<div class="form-group">
					<form:hidden path="id" value="${project.id}" />
					
						
						<div class="col-xs-2">
							<form:label path="name" cssClass="labelColor">Project Name : &nbsp;<span style="color: red">*</span></form:label>
						</div>
						<div class="col-xs-6">
						<form:input id="name" cssClass="form-control" path="name"
							value="${project.name}" />
					</div>
					<div>
						<span style="color:#D00B05">${errormsg}</span>
					</div>
				</div>

				<div class="form-group">
					<div class="col-xs-2">
						<form:label path="admin" cssClass="labelColor">Admin : &nbsp;<span style="color: red">*</span></form:label>
					</div>
					<div class="col-xs-6">
						<form:input id="admin" cssClass="form-control" path="admin"
							value="${projectadmin}" readonly="true" />
					</div>
					<div>
						<span style="color:#D00B05">${errormsg1}</span>
					</div>
				</div>

				<%-- <div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="country">Country</form:label>
					</div>
					<div class="col-xs-6">
						<form:input id="country" cssClass="form-control" path="country"
							value="${project.country}" />
					</div>
					<div>
						<span style="color:#D00B05">${errormsg1}</span>
					</div>
				</div> --%>
				
				<%-- <div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="admin">Admin : &nbsp;<span style="color: red">*</span></form:label>
					</div>
					<div class="col-xs-6">
						<form:select id="adminId" path="admin"
							cssClass="form-control">
							<form:option value="" label="---Select Country---"></form:option>
							<c:forEach items="${countrylist}" var="country">
								<form:option value="${country.id}"
									label="${country.name}"></form:option>
							</c:forEach>
						</form:select>
					</div>
					<div>
						<span style="color:#D00B05">${errormsg1}</span>
					</div>
				</div> --%>
				
				<div class="form-group">
					<div class="col-xs-2">
						<form:label path="country" cssClass="labelColor">Country : &nbsp;<span style="color: red">*</span></form:label>
					</div>
					<div class="col-xs-6">
						<form:select id="countryId" path="country.id"
							cssClass="form-control">
							<form:option value="" label="---Select Country---"></form:option>
							<c:forEach items="${countrylist}" var="country">
								<form:option value="${country.id}"
									label="${country.name}"></form:option>
							</c:forEach>
						</form:select>
					</div>
					<div>
						<span style="color:#D00B05">${errormsg1}</span>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-xs-2">
						<form:label path="states" cssClass="labelColor">State : &nbsp;<span style="color: red">*</span></form:label>
					</div>
					<div class="col-xs-6">
						<form:select path="states.id" id="stateId" cssClass="form-control">
							<form:option value="" label="---Select State---"></form:option>
							<c:forEach items="${statelist}" var="state">
								<form:option value="${state.id}" label="${state.name}"></form:option>
							</c:forEach>
						</form:select>
					</div>
					<div>
						<span style="color:#D00B05">${errormsg1}</span>
					</div>
				</div>
				
				
				<div class="form-group">
					<div class="col-xs-2">
						<form:label path="region" cssClass="labelColor">Region : &nbsp;<span style="color: red">*</span></form:label>
					</div>
					<div class="col-xs-6">
						<form:select path="region.id" id="regionId" cssClass="form-control">
							<form:option value="" label="---Select State---"></form:option>
							<c:forEach items="${regionlist}" var="region">
								<form:option value="${region.id}" label="${region.name}"></form:option>
							</c:forEach>
						</form:select>
					</div>
					<div>
						<span style="color:#D00B05">${errormsg1}</span>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-xs-2">
						<form:label path="description" cssClass="labelColor">Description : &nbsp;<span style="color: red">*</span></form:label>
					</div>
					<div class="col-xs-6">
						<form:input id="description" cssClass="form-control" path="description"
							value="${project.description}" />
					</div>
					<div>
						<span style="color:#D00B05">${errormsg1}</span>
					</div>
				</div>
				<%-- <div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="states">State</form:label>
					</div>
					<div class="col-xs-6">
						<form:input id="states" cssClass="form-control" path="states"
							value="${project.states}" />
					</div>
					<div>
						<span style="color:#D00B05">${errormsg1}</span>
					</div>
				</div> --%>
				
				<%-- <div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="region">Region</form:label>
					</div>
					<div class="col-xs-6">
						<form:input id="region" cssClass="form-control" path="region"
							value="${project.region}" />
					</div>
					<div>
						<span style="color:#D00B05">${errormsg1}</span>
					</div>
				</div> --%>

				<div class="form-group">
					<div class="row">
						<div class="col-xs-4"></div>
						<div class="col-xs-4">
							<input type="submit" id="saveCountry" class="btn btn-primary"
								value="Save" onclick="return submitCountryForm();" />
							<%-- <a href='<c:url value="getCountry?countryId=0" />'>  
								<button type="button" class="btn btn-primary"> Cancel </button>
							</a> --%>
							<input type="button" id="cancel" value="Cancel"
								class="btn btn-default" />
						</div>
						<div class="col-xs-4"></div>
					</div>
				</div>

			</form:form>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#tabs > li').removeClass('active');
			$('#configTab').attr('class', 'active');
			$('#projectsTab').attr('class', 'active');
		});


		$('#cancel').click(function() {
			window.history.back();
		});
		
		function submitCountryForm() {
			$('#projectAddform').formValidation({
		        icon: {
		            valid: 'glyphicon glyphicon-ok',
		            invalid: 'glyphicon glyphicon-remove',
		            validating: 'glyphicon glyphicon-refresh'
		        },
		        fields: {
		        	name: {
		                validators : {
							notEmpty : {
								message : 'name is Required'
							},
						}
		            },
		            admin: {
		                validators : {
							notEmpty : {
								message : 'admin is Required'
							},
						}
		            },
		            'country.id': {
		                validators : {
							notEmpty : {
								message : 'Country is Required'
							},
						}
		            },
		            'states.id': {
		                validators : {
							notEmpty : {
								message : 'states is Required'
							},
						}
		            },
		            'region.id': {
		                validators : {
							notEmpty : {
								message : 'region is Required'
							},
						}
		            },
		            description: {
		            	validators : {
							notEmpty : {
								message : 'description is Required'
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
