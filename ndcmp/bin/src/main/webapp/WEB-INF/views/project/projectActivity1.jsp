<!-- Add Strategic Pillar Modal -->
	<div class="modal fade bd-example-modal-lg"
		id="statusUpdaterModal" role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div align="left">
							<h4>Add Strategic Pillar</h4>
					</div>
				</div>
				<div class="modal-body">
					<div class="container">
						<div>
						<form:form action="createStatusTracking"
							id="statusTrackingAddform" cssClass="form-horizontal"
							modelAttribute="statusTracking" method="post">

							<br />
							<div class="form-group">

								<div class="control-label col-xs-3">
									<div></div>
									<form:label path="actualStatusColor" cssClass="labelColor">Status Color : &nbsp;<span
											style="color: red">*</span>
									</form:label>
								</div>

								<div class="project col-md-6">
									<h2 class="text-center" style="margin-top: -19px">
										<form:hidden path="userId" value="${userId}" />
										<form:hidden path="subActivityId" value="${subActivityId}" />
										<form:hidden path="actualStatusColor" id="percent" />
										<form:input path="actualStatusPercentage" class="percent" />
									</h2>
									<div class="bar" style="margin-top: -1px"></div>

								</div>
							</div>
							<br>

							<div class="form-group">
								<div class="control-label col-xs-3">
									<form:label path="keyProgress" cssClass="labelColor">Key Progress : &nbsp;<span
											style="color: red">*</span>
									</form:label>
								</div>
								<div class="col-xs-6">
									<form:textarea cssClass="form-control" path="keyProgress"
										value="${statusTracking.keyProgress}" />
								</div>
								<div>
									<span style="color: #D00B05">${errorDescription}</span>
								</div>
							</div>


							<div class="form-group">
								<div class="control-label col-xs-3">
									<form:label path="reasonForGap" cssClass="labelColor">Reason For Gap : &nbsp;<span
											style="color: red">*</span>
									</form:label>
								</div>
								<div class="col-xs-6">
									<form:textarea cssClass="form-control" path="reasonForGap"
										value="${statusTracking.reasonForGap}" />
								</div>
								<div>
									<span style="color: #D00B05">${errorDescription}</span>
								</div>
							</div>

							<div class="form-group">
								<div class="control-label col-xs-3">
									<form:label path="rectifyTheGap" cssClass="labelColor">Rectify The Gap : &nbsp;<span
											style="color: red">*</span>
									</form:label>
								</div>
								<div class="col-xs-6">
									<form:textarea cssClass="form-control" path="rectifyTheGap"
										value="${statusTracking.rectifyTheGap}" />
								</div>
								<div>
									<span style="color: #D00B05">${errorDescription}</span>
								</div>
							</div>

							<div class="form-group">
								<%-- <div class="control-label col-xs-3">
											<form:label path="rectifyTheGap" cssClass="labelColor">RectifyTheGap : &nbsp;<span
													style="color: red">*</span>
											</form:label>
										</div> --%>
								<%-- <div class="col-xs-6">
											<form:textarea cssClass="form-control" path="rectifyTheGap"
												value="${statusTracking.rectifyTheGap}" />
										</div>
										<div>
											<span style="color: #D00B05">${errorDescription}</span>
										</div> --%>
								<div class="col-xs-6">
									<c:choose>
										<c:when test="${statusTracking.id == null }">
											<form:radiobutton path="complete" />Completed
												</c:when>
										<c:otherwise>
											<form:radiobutton path="complete" value="Completed" />Completed
													<form:radiobutton path="complete" value="Rework" />Need to be Rework
												</c:otherwise>
									</c:choose>
								</div>
							</div>


							<br />
							<div class="form-group">
								<div class="row">
									<div class="col-xs-4"></div>
									<div class="col-xs-4">
										<input type="submit" id="saveUserrole" class="btn btn-primary"
											value="Save" />
										<!-- <input type="button" id="cancel" value="Cancel" class="btn btn-primary" /> -->
										<a href="partnerFilter" class="btn btn-default">Cancel</a>
									</div>
									<div class="col-xs-4"></div>
								</div>
							</div>
						</form:form>
					</div>
					</div>
				</div>
			</div>

		</div>
	</div>