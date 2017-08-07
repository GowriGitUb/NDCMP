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
				<c:when test="${indicator.id == null}">
					<h2>Add Indicator</h2>
				</c:when>
				<c:otherwise>
					<h2>Update Indicator</h2>
				</c:otherwise>
			</c:choose>
		</div>
		<hr />
		<div>
			<form:form id="indicatorAddForm" cssClass="form-horizontal"
				modelAttribute="indicator" method="post" action="saveIndicator">
				
				<form:hidden path="id" value="${indicator.id}" />
				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="message" cssClass="labelColor">Indicator Message : &nbsp;<span
								style="color: red;">*</span>
						</form:label>
					</div>
					<div class="col-xs-6">
						<form:textarea cssClass="form-control" path="message" cols="5" rows="20"
							value="${indicator.message}" id="message"></form:textarea>
					</div>
				</div>

				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="output" cssClass="labelColor">Output : &nbsp;<span
								style="color: red;">*</span>
						</form:label>
					</div>
					<div class="col-xs-6">
						<form:select path="output.id" id="outputId" class="form-control">
							<form:option value="" label="---Select Output---">---Select Output---</form:option>
							<c:forEach items="${outputList}" var="output">
								<form:option value="${output.id}" label="${output.name}"></form:option>
							</c:forEach>
						</form:select>
					</div>
				</div>

				<div class="form-group">
					<div class="row">
						<div class="col-xs-4"></div>
						<div class="col-xs-4">
							<input type="submit" class="btn btn-primary"
								value="Save" onclick="return submitIndicatorForm();" />
							 <a href="indicatorList" class="btn btn-default">Cancel</a>
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

		function submitIndicatorForm() {
			$('#indicatorAddForm').formValidation({
				icon : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				fields : {
					message : {
						validators : {
							notEmpty : {
								message : 'Indicator Mssage is required'
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
