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
<!-- <script type="text/javascript">

$(document).ready(function(){
	var userId = "${userId}";
	if(userId == null || userId == '' || userId == ""){
		$('#username').val("");
		$('#password').val("");
	}
});

</script> -->
</head>
<body>
	<%@include file="../include/menu.jsp"%>

	<div class="container">
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
		<hr />
		<div>
			<form:form id="addOutcomeForm" cssClass="form-horizontal"
				modelAttribute="outcome" method="post" action="saveOutcome">

				<div class="form-group">
					<div class="control-label col-xs-2">
						<form:label path="name" cssClass="labelColor">Outcome Name : &nbsp;<span
									style="color: red">*</span></form:label>
					</div>
					<div class="col-xs-4">
						<form:hidden path="id" value="${outcome.id}" />
						<form:input cssClass="form-control" path="name"/>
					</div>
				</div>
				
				<div class="form-group">
					<div class="control-label col-xs-2">
						<form:label path="description" cssClass="labelColor">Description : &nbsp;</form:label>
					</div>
					<div class="col-xs-4">
						<form:input cssClass="form-control" path="description" />
					</div>
				</div>
				
				<div class="form-group">
					<div class="control-label col-xs-2">
						<form:label path="theme" cssClass="labelColor">Theme : &nbsp;<span
									style="color: red">*</span></form:label>
					</div>
					<div class="col-xs-4">
						<form:select id="themeId" path="theme.id"
							cssClass="form-control">
							<form:option value="" label="---Select Theme---"></form:option>
							<c:forEach items="${themeList}" var="theme">
								<form:option value="${theme.id}"
									label="${theme.name}"></form:option>
							</c:forEach>
						</form:select>
					</div>
				</div>
				
				<div class="form-group">
					<div class="row">
						<div class="col-xs-4"></div>
						<div class="col-xs-4">
							<input type="submit" id="saveOutcome" class="btn btn-primary"
								value="Save"  onclick="return submitOutcomeForm();" />
							<input
								type="button" id="cancel" value="Cancel" class="btn btn-primary" />
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
			$('#usertab').attr('class', 'active');
		});
		
		$('#cancel').click(function() {
			window.history.back();
		});

		function submitOutcomeForm(){
			
			$('#addOutcomeForm').formValidation({
				icon : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				fields : {
					name: {
		                validators: {
		                    notEmpty: {
		                        message: 'Outcome Name is Required'
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
		            'theme.id': {
		                validators : {
							notEmpty : {
								message : 'Theme is Required'
							}
						}
		            },
				}
			});
			
			return true;
		};
	</script>
</body>
</html>