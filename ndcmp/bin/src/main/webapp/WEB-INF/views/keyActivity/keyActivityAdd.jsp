<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../include/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UNODC</title>
<%@include file="../include/commonlib.jsp"%>
<body>
	<%@include file="../include/menu.jsp"%>
	<div class="container">
		<div align="left">
			<c:choose>
				<c:when test="${keyActivity.id == null}">
					<h2>Add Key Activity</h2>
				</c:when>
				<c:otherwise>
					<h2>Update Key Activity</h2>
				</c:otherwise>
			</c:choose>
		</div>
		<hr />
		<div>
			<form:form id="keyActivityAddForm" cssClass="form-horizontal"
				modelAttribute="keyActivity" method="post" action="saveKeyActivity">
				
				<form:hidden path="id" value="${keyActivity.id}" />
				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="sequenceNumber">Sequence Number : &nbsp;<span
								style="color: red;">*</span>
						</form:label>
					</div>
					<div class="col-xs-6">
						<form:input cssClass="form-control" path="sequenceNumber"
							value="${keyActivity.name}" id="sequenceNumber" />
					</div>
				</div>
				
				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="name">Name : &nbsp;<span
								style="color: red;">*</span>
						</form:label>
					</div>
					<div class="col-xs-6">
						<form:input cssClass="form-control" path="name"
							value="${keyActivity.name}" id="name" />
					</div>
					<div>
						<span style="color: #D00B05">${errormsg}</span>
					</div>
				</div>
				
				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="description"> Description: &nbsp;<span
								style="color: red;">*</span>
						</form:label>
					</div>
					<div class="col-xs-6">
						<form:input cssClass="form-control" path="description"
							value="${keyActivity.description}" id="description" />
					</div>
				</div>

				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="output">Output : &nbsp;<span
								style="color: red;">*</span>
						</form:label>
					</div>
					<div class="col-xs-6">
						<form:select path="output.id" id="outputId" class="form-control">
							<form:option value="" label="---Select Output---">---Select Agency Type---</form:option>
							<c:forEach items="${outPutList}" var="list">
								<form:option value="${list.id}" label="${list.name}"></form:option>
							</c:forEach>
						</form:select>
					</div>
				</div>

				<div class="form-group">
					<div class="row">
						<div class="col-xs-4"></div>
						<div class="col-xs-4">
							<input type="submit" class="btn btn-primary"
								value="Save" onclick="return submitKeyActivityForm();" /> <a
								href="keyActivityList" class="btn btn-default">Cancel</a>
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
			$('#agencyTab').attr('class', 'active');
		});

		$('#cancel').click(function() {
			window.history.back();
		});

		function submitKeyActivityForm() {
			$('#keyActivityAddForm').formValidation({
				icon : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				fields : {
					sequenceNumber : {
						validators : {
							notEmpty : {
								message : 'Sequence Number is required'
							}
						}
					},
					name : {
						validators : {
							notEmpty : {
								message : 'Name is required'
							}
						}
					},
					description : {
						validators : {
							notEmpty : {
								message : 'Description is required'
							}
						}
					},
					'output.id' : {
						validators : {
							notEmpty : {
								message : 'Output is required'
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
