<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="include/taglibs.jsp"%>
<
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UNODC</title>
<%@include file="include/commonlib.jsp"%>
<body>
	<%@include file="include/menu.jsp"%>
	<div class="container">
		<div align="left">
			<c:choose>
				<c:when test="${output.id == null}">
					<h2>Add Country</h2>
				</c:when>
				<c:otherwise>
					<h2>Update Country</h2>
				</c:otherwise>
			</c:choose>
		</div>
		<hr />
		<div>
			<form:form id="outputAddform" cssClass="form-horizontal"
				modelAttribute="output" method="post" action="saveOutput">

				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="sequenceNumber" cssClass="labelColor">Sequence Number : &nbsp;<span style="color: red">*</span></form:label>
					</div>
					<div class="col-xs-6">
						<form:hidden path="id" value="${output.id}" />
						<form:input id="sequenceNumber" cssClass="form-control" path="sequenceNumber"
							value="${output.sequenceNumber}" />
					</div>
					<div>
						<span style="color:#D00B05">${errormsg}</span>
					</div>
				</div>
				
				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="name" cssClass="labelColor">Name : &nbsp;<span style="color: red">*</span></form:label>
					</div>
					<div class="col-xs-6">
						<form:input id="description" cssClass="form-control" path="name"
							value="${output.name}" />
					</div>
					<div>
						<span style="color:#D00B05">${errormsg1}</span>
					</div>
				</div>
				
				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="description" cssClass="labelColor">Description : &nbsp;<span style="color: red">*</span></form:label>
					</div>
					<div class="col-xs-6">
						<form:input id="description" cssClass="form-control" path="description"
							value="${output.description}" />
					</div>
					<div>
						<span style="color:#D00B05">${errormsg1}</span>
					</div>
				</div>
				
				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="outcome" cssClass="labelColor">Outcome: &nbsp;<span style="color: red">*</span></form:label>
					</div>
					<div class="col-xs-6">
						<form:select id="outcome" path="outcome.id"
							cssClass="form-control">
							<form:option value="" label="---Select Outcome---"></form:option>
							<c:forEach items="${outcomeList}" var="outcome">
								<form:option value="${outcome.id}"
									label="${outcome.name}"></form:option>
							</c:forEach>
						</form:select>
					</div>
				</div>
				
				<%-- <div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="outcome.id" cssClass="labelColor">Outcome : &nbsp;<span style="color: red">*</span></form:label>
					</div>
					<div class="col-xs-6">
						<form:input id="outcome" cssClass="form-control" path="outcome.id"
							value="${output.outcome}" />
					</div>
					<div>
						<span style="color:#D00B05">${errormsg1}</span>
					</div>
				</div> --%>


				<%-- <div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="officialName">Official Name</form:label>
					</div>
					<div class="col-xs-6">
						<form:input id="officialName" cssClass="form-control" path="officialName"
							value="${country.officialName}" />
					</div>
				</div>
 --%>
				<div class="form-group">
					<div class="row">
						<div class="col-xs-4"></div>
						<div class="col-xs-4">
							<input type="submit" id="saveOutput" class="btn btn-primary"
								value="Save" onclick="return submitOutputForm();" />
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
			$('#countryTab').attr('class', 'active');
		});


		$('#cancel').click(function() {
			window.history.back();
		});
		
		function submitOutputForm() {
			$('#outputAddform').formValidation({
		        icon: {
		            valid: 'glyphicon glyphicon-ok',
		            invalid: 'glyphicon glyphicon-remove',
		            validating: 'glyphicon glyphicon-refresh'
		        },
		        fields: {
		        	sequenceNumber: {
		                validators : {
							notEmpty : {
								message : 'Sequence Number is Required'
							},
							stringLength : {
								min : 3,
								max : 25,
								message : 'Sequence Number must be more than 3 and less than 25 characters long'
							}
						}
		            },
		            name: {
		                validators : {
							notEmpty : {
								message : 'Name is Required'
							},
							stringLength : {
								min : 3,
								max : 25,
								message : 'Name must be more than 3 and less than 25 characters long'
							}
						}
		            },
		            description: {
		                validators : {
							notEmpty : {
								message : 'Description is Required'
							},
							stringLength : {
								min : 3,
								max : 25,
								message : 'Description must be more than 3 and less than 25 characters long'
							}
						}
		            },
		            outcome: {
		            	validators : {
							notEmpty : {
								message : 'Outcome is Required'
							},
							stringLength : {
								min : 5,
								max : 25,
								message : 'Outcome must be more than 5 and less than 25 characters long'
							}
						}
		            }
		        }
		    });
			return true;
		};
	</script>
</body>
</html>
