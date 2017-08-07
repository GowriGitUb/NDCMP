/*
 * Added by Jeeva
 */

var selectedSubActivityForDataCapture;
var selectedPASubActivityForReview;
var reviewId=[];

Array.prototype.remove = function(x) { 
    var i = 0;
    for(i in this){
        if(this[i].toString() == x.toString()){
            this.splice(i,1);
        }
    }
};

$(document).ready(function(){
	$('#reset').hide();
	$('#reviewerDetails').hide();
	$('#filter-btn-show').hide();
	
	$('.hideHint').hide();
	$('.showHint').css('cursor', 'pointer');
	$('.hideHint').css('cursor', 'pointer');
	
	$('.hideUserHint').hide();
	$('.showUserHint').css('cursor', 'pointer');
	$('.hideUserHint').css('cursor', 'pointer');
	
	$('.accordion').collapse();
	
	$('#tabs > li').removeClass('active');
	$('#reviewTab').attr('class','active');
	$('#showColorHint').hide();
	$('#showUserColorHint').hide();
	
	$(".showHint").click(function(){
			$('#showColorHint').show();
			$('.showHint').hide();
			$('.hideHint').show();
    });
	
	$(".hideHint").click(function(){
		$('#showColorHint').hide();
		$('.showHint').show();
		$('.hideHint').hide();
	});
	
	$(".showUserHint").click(function(){
		
		
		$('#showUserColorHint').show();
	
		$('.showUserHint').hide();
		$('.hideUserHint').show();
	
	});

	
	$("#selectAllCheckBox").click(function () {
	    $(".reworkChkBox").prop('checked', $(this).prop('checked'));
	    if ($(".reworkChkBox:checked").length > 0) {
			$('#submitForReview').show();
		} else {
			$('#submitForReview').hide();
		}
	    reviewId=[];
	    if (this.checked) {
	    	$(".reworkChkBox:checked").each(function(){
	    		reviewId.push($(this).val());
	    	});
		}
	});
	
	$(".hideUserHint").click(function(){
		$('#showUserColorHint').hide();
		$('.showUserHint').show();
		$('.hideUserHint').hide();
	});
	
	
});
	
	var colorPercent=$('#colorPercent').val();
	
	$('#percent').val(colorPercent);
	
	$('.project').each(function() {
		var $projectBar = $(this).find('.bar');
		var $projectPercent = $(this).find('.percent');
		$projectBar.slider({
			range : "min",
			animate : true,
			value : colorPercent,
			min : 0,
			max : 100,
			step : 1,
			slide : function(event, ui) {
				$projectPercent.val(ui.value );
			},
			change : function(event, ui) {
				var $projectRange = $(this).find('.ui-slider-range');
				var percent = ui.value;
				if (percent == 0) {
					$('#percent').val('#FFFFFF');
					$('#colorPercent').val(percent);
					$('#colorPer').html(percent);
					$projectPercent.css({
						'color' : '#FFFFFF'
					});
					$projectRange.css({
						'background' : '#FFFFFF'
					});
				} else if (percent <= 10) {
					$('#percent').val('#DDA6A1');
					$('#colorPercent').val(percent);
					$('#colorPer').html(percent);
					$projectPercent.css({
						'color' : '#DDA6A1'
					});
					$projectRange.css({
						'background' : '#DDA6A1'
					});
				} else if (percent > 10 && percent <= 20) {
					$('#percent').val('#E77B70');
					$('#colorPercent').val(percent);
					$('#colorPer').html(percent);
					$projectPercent.css({
						'color' : '#E77B70'
					});
					$projectRange.css({
						'background' : '#E77B70'
					});
				} else if (percent > 20 && percent <= 30) {
					$('#percent').val('#F14D41');
					$('#colorPercent').val(percent);
					$('#colorPer').html(percent);
					$projectPercent.css({
						'color' : '#F14D41'
					});
					$projectRange.css({
						'background' : '#F14D41'
					});
				} else if (percent > 30 && percent <= 40) {
					$('#percent').val('#F66A37');
					$('#colorPercent').val(percent);
					$('#colorPer').html(percent);
					$projectPercent.css({
						'color' : '#F66A37'
					});
					$projectRange.css({
						'background' : '#F66A37'
					});
				} else if (percent > 40 && percent <= 50) {
					$('#percent').val('#F89A3A');
					$('#colorPercent').val(percent);
					$('#colorPer').html(percent);
					$projectPercent.css({
						'color' : '#F89A3A'
					});
					$projectRange.css({
						'background' : '#F89A3A'
					});
				}else if (percent > 50 && percent <= 60) {
					$('#percent').val('#FECB3A');
					$('#colorPercent').val(percent);
					$('#colorPer').html(percent);
					$projectPercent.css({
						'color' : '#FECB3A'
					});
					$projectRange.css({
						'background' : '#FECB3A'
					});
				}else if (percent > 60 && percent <= 70) {
					$('#percent').val('#F3E83B');
					$('#colorPercent').val(percent);
					$('#colorPer').html(percent);
					$projectPercent.css({
						'color' : '#F3E83B'
					});
					$projectRange.css({
						'background' : '#F3E83B'
					});
				}else if (percent > 70 && percent <= 80) {
					$('#percent').val('#CDDA42');
					$('#colorPercent').val(percent);
					$('#colorPer').html(percent);
					$projectPercent.css({
						'color' : '#CDDA42'
					});
					$projectRange.css({
						'background' : '#CDDA42'
					});
				}else if (percent > 80 && percent <= 90) {
					$('#percent').val('#ABD045');
					$('#colorPercent').val(percent);
					$('#colorPer').html(percent);
					$projectPercent.css({
						'color' : '#ABD045'
					});
					$projectRange.css({
						'background' : '#ABD045'
					});
				}else if (percent > 90) {
					$('#percent').val('#9FCA47');
					$('#colorPercent').val(percent);
					$('#colorPer').html(percent);
					$projectPercent.css({
						'color' : '#9FCA47'
					});
					$projectRange.css({
						'background' : '#9FCA47'
					});
				}
			}
		});
	});
	
	
	
	$("input.onchange-example").ColorPickerSliders({
		previewontriggerelement : true,
		flat : false,
		color : '#cf966f',
		customswatches : false,
		swatches : [ '#DDA6A1', '#E77B70', '#F14D41', '#F66A37','#F89A3A','#FECB3A','#F3E83B','#CDDA42','#ABD045','#9FCA47' ],
		order : {
			hsl : 1
		}
	});
	
	
	// Register accordion click event
	$('.accordion a').on('click', function() {
		var _this = $(this);
		var _expanded = $(_this).attr('aria-expanded');
		if (_expanded == 'false') {
			$('.btn .edit .delete').show();
		} else if (_expanded == 'true') {
			$('.btn .edit .delete').hide();
		}
	});

$(".statusId").change(function() {
    var checked = $(this).is(':checked');
    $(".statusId").prop('checked',false);
    if(checked) {
        $(this).prop('checked',true);
         status=$(this).val();
    }
});


function loadReviewerFilterData(){
	if($('#reportingId').val() == ""){
		alert("Reporting Period  is required");
		$('#reportingId').focus();
		return false;
	}else if($('#statusId').val() == ""){
		alert("Statusis required");
		$('#statusId').focus();
		return false;
	}else if($('#strategicPillarId').val() == ""){
		alert("Strategic Pillar is required");
		$('#strategicPillarId').focus();
		return false;
	}else if($('#themeId').val() == ""){
		alert("Theme is required");
		$('#themeId').focus();
		return false;
	}else if($('#outcomeId').val() == ""){
		alert("Outcome  is required");
		$('#outcomeId').focus();
		return false;
	}else if($('#outputId').val() == ""){
		alert("Output is required");
		$('#outputId').focus();
		return false;
	}
	if($('#reportingId').val() != "" && $('#strategicPillarId').val() != "" && $('#themeId').val() != "" && $('#outcomeId').val() != "" && $('#outputId').val() != ""){
		$('#reviewerDetails').empty();	
		$('#reviewerDiv').hide();
		$('#filter-btn').hide();
		$('#reset').show();
		var projectId=1;
		var statusId = $('#statusId').val();
		var filterHierForReviewer={};
		filterHierForReviewer={
					"reportingperiodId" : $('#reportingId').val(),
					"strategicPillar" : $('#strategicPillarId').val(),
					"theme" : $('#themeId').val(),
					"outcome" : $('#outcomeId').val(),
					"output" : $('#outputId').val()
			};
		
		$.ajax({
			url : '/ndcmp/api/getReviewerDetails',
			data : {
				projectId : projectId,
				reviewerFilter : JSON.stringify(filterHierForReviewer),
				statusId : statusId
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				if(data != null && data.length != 0){
					var modalBody = '<div class="panel-group accordion" id="collapseOne">';
					for(var i = 0;i < data.length; i++){
						
						if (i == 0) {
							modalBody += '<div class="panel panel-default template">'
							  	+ '<div class="panel-heading" style="background-color: #4D5B69;">'
							  	+ '<h4 class="panel-title">'
							  	+ '<a class="accordion-toggle" data-toggle="collapse" data-parent="#collapseOne" href="#collapseOutput_'+ data[i].id +'">'
							  	+ '<span><b>'+data[i].sequenceNumber+' '+ data[i].keyActivity +' </b></span></a></h4></div>'
							  	
							  	+ '<div id="collapseOutput_'+ data[i].id +'" class="panel-collapse collapse in">'
							  	+ '<div class="panel-body">'
							  	+ '<div class="panel-group accordion" id="collapseTwo'+ data[i].id +'">';
						} else {
							modalBody += '<div class="panel panel-default template">'
							  	+ '<div class="panel-heading" style="background-color: #4D5B69;">'
							  	+ '<h4 class="panel-title">'
							  	+ '<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#collapseOne" href="#collapseOutput_'+ data[i].id +'">'
							  	+ '<span><b>'+data[i].sequenceNumber+' '+ data[i].keyActivity +' </b></span></a></h4></div>'
							  	
							  	+ '<div id="collapseOutput_'+ data[i].id +'" class="panel-collapse collapse">'
							  	+ '<div class="panel-body">'
							  	+ '<div class="panel-group accordion" id="collapseTwo'+ data[i].id +'">';
						}
						
						
						subActivities = data[i].subActivities;
						for(var j =0;j < subActivities.length;j++){
							if(subActivities[j].carryOverStatus == true){
								modalBody += '<span><div title="Click to view" onclick="statusReviewer('+ subActivities[j].id +', this)" style="height:35px;border: 1px solid #e4d6d6;background-color: #CE7173;cursor: pointer;padding: 5px;">'
								+ '<span>'+subActivities[j].sequenceNumber+' '+ subActivities[j].subActivity 
								+ '<div class="pull-right" style="width:22px; height:22px; border: 1px solid; margin-right:12px; border-radius: 5px; background-color: '+ subActivities[j].colorCode +'"></div>'
								+ '<span class="pull-right" style="width:22px;height: 22px;margin-right:5px;text-align: center;"><i style="margin-top:5px;" class="'+ subActivities[j].statusIcon +'"></i></span>'
								+ '</span><br></div>';
							}else{
								modalBody += '<span><div title="Click to view" onclick="statusReviewer('+ subActivities[j].id +', this)" style="height:35px;border: 1px solid #e4d6d6;background-color: #f3bcb7;cursor: pointer;padding: 5px;">'
								+ '<span>'+subActivities[j].sequenceNumber+' '+ subActivities[j].subActivity 
								+ '<div class="pull-right" style="width:22px; height:22px; border: 1px solid; margin-right:12px; border-radius: 5px; background-color: '+ subActivities[j].colorCode +'"></div>'
								+ '<span class="pull-right" style="width:22px;height: 22px;margin-right:5px;text-align: center;"><i style="margin-top:5px;" class="'+ subActivities[j].statusIcon +'"></i></span>'
								+ '</span><br></div>';
							}
							
							var partnerAgencies = subActivities[j].agencyDTOs;
							for(var k =0;k < partnerAgencies.length; k++){
									modalBody += '<div title="Click to view" onclick="getStatusReviewer(this, '+ partnerAgencies[k].userId +','+ subActivities[j].id +', \''+ partnerAgencies[k].agency +'\', \''+partnerAgencies[k].userName+'\')" style="height:35px;border: 1px solid #e4d6d6;background-color: #FBDEDB;cursor: pointer;padding: 5px;padding-right: 12px;">'
									+ '<span style="margin-left: 20px;">'+"by"+' '+partnerAgencies[k].agency+' ('+partnerAgencies[k].userName+')<div class="pull-right" style="width:18px; height:18px; margin-top: 2px; border: 1px solid; border-radius: 5px; margin-right:35px; background-color: '+ partnerAgencies[k].colorCode +'"></div>'
									+ '<span class="pull-right" style="width:18px;height: 18px;margin-right:5px;text-align: center;"><i style="margin-top:5px;" class="'+ partnerAgencies[k].statusIcon +'"></i></span>'
									+'</span><br></div>';
							}
							modalBody +='</span>';
						}
						modalBody += '</div></div></div></div>';		  
					}
					modalBody += '</div>';
					$('#reviewerDetails').html(modalBody);
					$('#reviewerDetails').show();
				}else{
					alert("Record not found.."); 
					$('#reviewerDiv').show();
					$('#reset').hide();
					$('#filter-btn').show();
					
				}
			}
		});
		return true;
	}
}


function getStatusByReportingPeriod(selected){
	var selectedValue = selected.value;
	if(selectedValue == ''){
			$('#statusId').html('<option value="">---Select Status ---</option>');
			$('#strategicPillarId').html('<option value="">---Select Starategic Pillar ---</option>');
			$('#themeId').html('<option value="">---Select Theme ---</option>');
			$('#outcomeId').html('<option value="">---Select Outcome ---</option>');
			$('#outputId').html('<option value="">---Select Output ---</option>');
	}else{
		$.ajax({
			url : '/ndcmp/api/getStatusInfo',
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				if (data != null && data.length > 0) {
					var status = '<option value="">---Select Status ---</option>';
					var statusData = data;
					for (var i = 0; i < statusData.length; i++) {
						status += '<option value="'
								+ statusData[i].id + '">'
								+ statusData[i].name
								+ '</option>';
					}
					$('#statusId').html(status);
				} else {
					$('#statusId').html('<option value="">---Select Status ---</option>');
					$('#strategicPillarId').html('<option value="">---Select Starategic Pillar ---</option>');
					$('#themeId').html('<option value="">---Select Theme ---</option>');
					$('#outcomeId').html('<option value="">---Select Outcome ---</option>');
					$('#outputId').html('<option value="">---Select Output ---</option>');
				}
			}
		});
	}
	
}
function loadStatusForStatusReviewer(){
	var reportingPeriodId = $('#reportingId').val();
	if (reportingPeriodId != undefined && reportingPeriodId != '') {
		$.ajax({
			url : '/ndcmp/api/loadStatusForStatusReviewer',
			data :{
				reportingPeriodId : reportingPeriodId
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				
				if(reportingPeriodId != null && reportingPeriodId != ""){
					$('#observation-btn').show();
				}else{
					$('#observation-btn').hide();
				}
				
				var statusValue = '<option value="">---Select Status---</option>';
				
				if (data > 0) {
					if (data == 1) {
						statusValue += '<option value="All" selected="selected">All</option>' +
										'<option value="Reviewed">Reviewed</option>' +
										'<option value="NotReviewed">Not Reviewed</option>'+
										'<option value="ReadyForApproval">Ready for Approval</option>'+
										'<option value="NotReadyForApproval">Not Ready for Approval</option>';
					}else if (data == 2) {
						statusValue += '<option value="All" selected="selected">All</option>' + 
										'<option value="SentForApproval" selected="selected">Sent for Approval</option>';
						
					}else{
						statusValue += '<option value="All" selected="selected">All</option>' +
									'<option value="SentForApproval">Sent for Approval</option>' +
									'<option value="NeedMoreInfo">Need More Information</option>' +
									'<option value="ReadyForApproval">Ready for Approval</option>';
					}
				}
				$('#statusId').html(statusValue);
				getStrategicPillarByQuarter();
			}
		});
	} else {
		$('#statusId').html('<option value="">---Select Status---</option>');
		$('#themeId').html('<option value="">---Select Theme ---</option>');
		$('#outcomeId').html('<option value="">---Select Outcome ---</option>');
		$('#outputId').html('<option value="">---Select Output ---</option>');
		$('#strategicPillarId').html('<option value="">---Select Starategic Pillar ---</option>');
	}
}


function getStrategicPillarByQuarter(){
	var reporting = $('#reportingId').val();
	var status = $('#statusId').val();
	if (reporting == undefined || reporting == '') {
		$('#themeId').html('<option value="">---Select Theme ---</option>');
		$('#outcomeId').html('<option value="">---Select Outcome ---</option>');
		$('#outputId').html('<option value="">---Select Output ---</option>');
		$('#strategicPillarId').html('<option value="">---Select Starategic Pillar ---</option>');
	} else if (reporting != undefined && reporting != '' &&
			status != undefined && status != '') {
		$.ajax({
			url : '/ndcmp/api/getStrategicPillarByQuarter',
			data : {
				reportingPeriodId : reporting,
				status : status
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				
				if (data != null && data.length > 0) {
					var pillarsBasedOnQuarters = '<option value="">---Select Strategic Pillar---</option>';
					var pillars = data;
					for (var i = 0; i < pillars.length; i++) {
						if (i == 0) {
							pillarsBasedOnQuarters += '<option value="'
								+ pillars[i].id + '" selected="selected">'
								+ pillars[i].sequenceNumber + '. '
								+ pillars[i].name
								+ '</option>';
						} else {
							pillarsBasedOnQuarters += '<option value="'
								+ pillars[i].id + '">'
								+ pillars[i].sequenceNumber + '. '
								+ pillars[i].name
								+ '</option>';
						}
					}
					$('#strategicPillarId').html(pillarsBasedOnQuarters);
					getThemesByStrategic(pillars[0].id);
				}else{
					$('#strategicPillarId').html('<option value="">---Select Starategic Pillar ---</option>');
					$('#themeId').html('<option value="">---Select Theme ---</option>');
					$('#outcomeId').html('<option value="">---Select Outcome ---</option>');
					$('#outputId').html('<option value="">---Select Output ---</option>');
				}
			}
		});
	}
	
}

function getThemesByStrategic(slectedStrategic){
	var strategicId = slectedStrategic;
	var reporting = $('#reportingId').val();
	var status = $('#statusId').val();
	if (strategicId == undefined || strategicId == '') {
		$('#themeId').html('<option value="">---Select Theme ---</option>');
		$('#outcomeId').html('<option value="">---Select Outcome ---</option>');
		$('#outputId').html('<option value="">---Select Output ---</option>');
	} else if (strategicId != undefined && strategicId != '' &&
			reporting != undefined && reporting != '' &&
			status != undefined && status != '') {
		$.ajax({
			url : '/ndcmp/api/getReviewrThemesByStrategic',
			data : {
				reportingPeriodId : reporting,
				strategicId : strategicId,
				status : status 
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				if (data != null && data.length > 0) {
					var themesbasedonstrategic = '<option value="">---Select Theme ---</option>';
					var themes = data;
					for (var i = 0; i < themes.length; i++) {
						if (i == 0) {
							themesbasedonstrategic += '<option value="'
								+ themes[i].id + '" selected="selected">'
								+ themes[i].name
								+ '</option>';
						} else {
							themesbasedonstrategic += '<option value="'
								+ themes[i].id + '">'
								+ themes[i].name
								+ '</option>';
						}
						
					}
					$('#themeId').html(themesbasedonstrategic);
					getOutcomesByTheme(themes[0].id);
				}else{
					$('#themeId').html('<option value="">---Select Theme ---</option>');
					$('#outcomeId').html('<option value="">---Select Outcome ---</option>');
					$('#outputId').html('<option value="">---Select Output ---</option>');
				}
			}
		});
	}
}

function getOutcomesByTheme(selectedThemeId){
	var themeId = selectedThemeId;
	var status = $('#statusId').val();
	var reporting = $('#reportingId').val();
	if (themeId == undefined || themeId == ''){
		
	} else if (themeId != undefined && themeId != '' &&
			status != undefined && status != '' &&
			reporting != undefined && reporting != '') {
		$.ajax({
			url : '/ndcmp/api/getReviewrOutcomesByTheme',
			data : {
				reportingPeriodId : reporting,
				themeId : themeId,
				status : status
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				if (data != null && data.length > 0 ) {
					var outcomebasedontheme = '<option value="">---Select Outcome ---</option>';
					var outcomes = data;
					for (var i = 0; i < outcomes.length; i++) {
						if (i == 0) {
							outcomebasedontheme += '<option value="'
								+ outcomes[i].id + '" selected=""selected">'
								+ outcomes[i].sequenceNumber + '. '
								+ outcomes[i].name
								+ '</option>';
						} else {
							outcomebasedontheme += '<option value="'
								+ outcomes[i].id + '">'
								+ outcomes[i].sequenceNumber + '. '
								+ outcomes[i].name
								+ '</option>';
						}
					}
					$('#outcomeId').html(outcomebasedontheme);
					getOutputsByoutcome(outcomes[0].id);
				}else{
					$('#outcomeId').html('<option value="">---Select Outcome ---</option>');
					$('#outputId').html('<option value="">---Select Output ---</option>');
				}
			}
		});
	}
}

function getOutputsByoutcome(selectedOutcomeId){
	var outcomeId = selectedOutcomeId;
	var status = $('#statusId').val();
	var reporting = $('#reportingId').val();
	
	if (outcomeId == undefined || outcomeId == '') {
		$('#outputId').html('<option value="">---Select Output ---</option>');
	} else if (outcomeId != undefined && outcomeId != '' &&
			status != undefined && status != '' &&
			reporting != undefined && reporting != '') {
		$.ajax({
			url : '/ndcmp/api/getReviewerOutputsByoutcome',
			data : {
				reportingPeriodId : reporting,
				outcomeId : outcomeId,
				status : status
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				if (data != null && data.length > 0) {
					var outputbasedonoutcome = '<option value="">---Select Output---</option>';
					var outputs = data;
					for (var i = 0; i < outputs.length; i++) {
						if (i == 0) {
							outputbasedonoutcome += '<option value="'
								+ outputs[i].id + '" selected="selected">'
								+ outputs[i].sequenceNumber + '. '
								+ outputs[i].output
								+ '</option>';
						} else {
							outputbasedonoutcome += '<option value="'
								+ outputs[i].id + '">'
								+ outputs[i].sequenceNumber + '. '
								+ outputs[i].output
								+ '</option>';
						}
					}
					$('#outputId').html(outputbasedonoutcome);
				}else{
					$('#outputId').html('<option value="">---Select Output ---</option>');
				}
			}
		});
	}
	
}

function getStatusReviewer(element, userId, subId, agency, userName){
	selectedPASubActivityForReview = element;
	$.ajax({
		url : '/ndcmp/api/getStatusTrackingDetails',
		data : {
			userId : userId,
			subId : subId,
			reportingPeriodId : $('#reportingId').val()
		},
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data) {
			hideReviewerFeedback();
			if (data.id != null) {
				$('#id').val(data.id);
				$('#userIdReadonly').val(data.user.id);
				$('#subActivityIdReadonly').val(data.subActivity.id);
				$('#subActivityId').val(data.subActivity.id);
				$('#partnerName').html(agency + ' (' + userName + ')');
				$('#percentReadonly').val(data.actualStatusColor);
				$('#colorPercentReadonly').val(data.actualStatusPercentage);
				$('#keyProgressReadonly').val(data.keyProgress);
				$('#reasonForGapReadonly').val(data.reasonForGap);
				$('#rectifyTheGapReadonly').val(data.rectifyTheGap);
				$('.bar').css('pointer-events','none');
				if (data.reviewedBy != null) {
					$('#lastUpdatedBy').show();
					$('#coPartnerName').html(data.reviewedBy.firstName + " " + data.reviewedBy.lastName);
				} else {
					$('#lastUpdatedBy').hide();
				}
				var reviewerStatus = data.reviewStatus;
				var reworkStatus = data.reworkRequired;
				if(reviewerStatus == -1){
					$('#approvedLblDiv').show();
					if(reworkStatus){
						$('#completeValueReadonly').hide();
						$('#approvedLblDiv').hide();
					}
					$('#approveId1').show();
					$('#approveId2').hide();
					$('#needToWorkValueReadonly').prop("checked", true);
					$('#saveOptionActive1').show();
					$('#saveOptionDisable1').hide();
					$('#reviewerFeedbackDiv').show();
					$('#reviewerFeedback').val(data.reviewerFeedback);
				}else if(reviewerStatus == 1){
					$('#approvedLblDiv').show();
					$('#completeValueReadonly1').prop("checked", true);
					$('#approveId1').hide();
					$('#approveId2').show();
					$('#saveOptionActive1').hide();
					$('#saveOptionDisable1').show();
				}else{
					$('#approvedLblDiv').show();
					$('#completeValueReadonly').attr('checked',false);
					$('#needToWorkValueReadonly').attr('checked',false);
					$('#approveId1').show();
					$('#approveId2').hide();
					$('#saveOptionActive1').show();
					$('#saveOptionDisable1').hide();
					
					if (data.reviewerFeedback != null && data.reviewerFeedback != '') {
						$('#reviewerFeedback').val(data.reviewerFeedback);
						$('#reviewerFeedbackDiv').show();
					}
				}
				$('.project').each(function() {
					var $projectBar = $(this).find('.bar');
					var $projectPercent = $(this).find('.percent');
					$projectBar.slider({
						range : "min",
						animate : true,
						value : data.actualStatusPercentage,
						min : 0,
						max : 100,
						step : 1,
						slide : function(event, ui) {
							$projectPercent.val(ui.value );
						},
						change : function(event, ui) {
							var $projectRange = $(this).find('.ui-slider-range');
							var percent = ui.value;
							if (percent == 0) {
								$('#percent').val('#FFFFFF');
								$('#colorPercent').val(percent);
								$('#colorPer').html(percent);
								$projectPercent.css({
									'color' : '#FFFFFF'
								});
								$projectRange.css({
									'background' : '#FFFFFF'
								});
							} else if (percent <= 10) {
								$('#percent').val('#DDA6A1');
								$('#colorPercent').val(percent);
								$('#colorPer').html(percent);
								$projectPercent.css({
									'color' : '#DDA6A1'
								});
								$projectRange.css({
									'background' : '#DDA6A1'
								});
							} else if (percent > 10 && percent <= 20) {
								$('#percent').val('#E77B70');
								$('#colorPercent').val(percent);
								$('#colorPer').html(percent);
								$projectPercent.css({
									'color' : '#E77B70'
								});
								$projectRange.css({
									'background' : '#E77B70'
								});
							} else if (percent > 20 && percent <= 30) {
								$('#percent').val('#F14D41');
								$('#colorPercent').val(percent);
								$('#colorPer').html(percent);
								$projectPercent.css({
									'color' : '#F14D41'
								});
								$projectRange.css({
									'background' : '#F14D41'
								});
							} else if (percent > 30 && percent <= 40) {
								$('#percent').val('#F66A37');
								$('#colorPercent').val(percent);
								$('#colorPer').html(percent);
								$projectPercent.css({
									'color' : '#F66A37'
								});
								$projectRange.css({
									'background' : '#F66A37'
								});
							} else if (percent > 40 && percent <= 50) {
								$('#percent').val('#F89A3A');
								$('#colorPercent').val(percent);
								$('#colorPer').html(percent);
								$projectPercent.css({
									'color' : '#F89A3A'
								});
								$projectRange.css({
									'background' : '#F89A3A'
								});
							}else if (percent > 50 && percent <= 60) {
								$('#percent').val('#FECB3A');
								$('#colorPercent').val(percent);
								$('#colorPer').html(percent);
								$projectPercent.css({
									'color' : '#FECB3A'
								});
								$projectRange.css({
									'background' : '#FECB3A'
								});
							}else if (percent > 60 && percent <= 70) {
								$('#percent').val('#F3E83B');
								$('#colorPercent').val(percent);
								$('#colorPer').html(percent);
								$projectPercent.css({
									'color' : '#F3E83B'
								});
								$projectRange.css({
									'background' : '#F3E83B'
								});
							}else if (percent > 70 && percent <= 80) {
								$('#percent').val('#CDDA42');
								$('#colorPercent').val(percent);
								$('#colorPer').html(percent);
								$projectPercent.css({
									'color' : '#CDDA42'
								});
								$projectRange.css({
									'background' : '#CDDA42'
								});
							}else if (percent > 80 && percent <= 90) {
								$('#percent').val('#ABD045');
								$('#colorPercent').val(percent);
								$('#colorPer').html(percent);
								$projectPercent.css({
									'color' : '#ABD045'
								});
								$projectRange.css({
									'background' : '#ABD045'
								});
							}else if (percent > 90) {
								$('#percent').val('#9FCA47');
								$('#colorPercent').val(percent);
								$('#colorPer').html(percent);
								$projectPercent.css({
									'color' : '#9FCA47'
								});
								$projectRange.css({
									'background' : '#9FCA47'
								});
							}
						}
					});
				});
				$('#subActivityNameReadonly').html(data.subActivity.sequenceNumber + ". " + data.subActivity.name);
				$('#statusReviewerInformation').modal('show');
				
			}else{
				alert("Failed to load status tracking");
				$('#statusReviewerInformation').modal('hide');
			}
		}
	});
}

function resetFuncion(){
	$('#reviewerDiv').show();
	$('#filter-btn').show();
	$('#reset').hide();
	$('#filter-btn-show').hide();
	getStrategicPillarByQuarter();
}

function showFuncion(){
	$('#reviewerDiv').show();
	$('#filter-btn').show();
	$('#reviewerDetails').hide();
	$('#reset').hide();
	$('#filter-btn-show').hide();
}

function saveStatusTracker(){
	
	var reviewStatus=$("input[name='review']:checked").val();
	if(reviewStatus == ""){
		reviewStatus=0;
	}else if(reviewStatus == "completeValue"){
		reviewStatus=1;
	}else if(reviewStatus == "needToWorkValue"){
		reviewStatus=-1;
	}
	var feedback = $('#reviewerFeedback').val();
	if (feedback == undefined) {
		feedback = '';
	}
	$.ajax({
		url : '/ndcmp/api/saveStatusTrackerDetails',
		data :{
			"subActivityId" : $('#subActivityIdReadonly').val(),
			"userId" : $('#userIdReadonly').val(),
			"reportingPeriodId" : $('#reportingId').val(),
			"reviewStatus" : reviewStatus,
			"reviewerFeedback" : feedback
			
		},
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data) {
			if (selectedPASubActivityForReview != null && selectedPASubActivityForReview.childElementCount == 2) {
				var statusIconDiv = selectedPASubActivityForReview.children[0].children[1].children[0];
				if (reviewStatus == 1) {
					statusIconDiv.setAttribute('class', 'fa fa-check-circle-o');
				} else if (reviewStatus == -1) {
					statusIconDiv.setAttribute('class', 'fa fa-info-circle');
				}
			}
			$('#statusReviewerInformation').modal('hide');
		}
	});
}

function statusReviewer(id, element){
	selectedSubActivityForDataCapture = element;
	 $.ajax({
			url : '/ndcmp/api/getStatusTrackingReviewerInfo',
			data :{
				"subActId" : id,
				"reportingPeriodId" : $('#reportingId').val()
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				$('#approverFeedbackDiv').hide();
				$('#save-reviewTracking').show();
				$('#readyforApproval-reviewTracking').show();
				if(data.id != null){
					$('#id').val(data.id);
					$('#userId').val(data.user.id);
					$('#subActivityId').val(data.subActivity.id);
					$('#percent').val(data.actualStatusColor);
					$('#colorPercent').val(data.actualStatusPercentage);
					$('#keyProgress').val(data.keyProgress);
					$('#reasonForGap').val(data.reasonForGap);
					$('#rectifyTheGap').val(data.rectifyTheGap);
					$('#approverFeedback').html(data.reviewerFeedback);
					if (data.reworkRequired) {
						$('#approverFeedbackDiv').show();
					}
					$('#carriedFromPreviousRPDiv').hide();
					$("#statusTrackingReviewForm").find("textarea,input").removeAttr('disabled');
					$('.bar').css('pointer-events','auto');
					if(data.complete == true){
						$('#save-reviewTracking').hide();
						$('#readyforApproval-reviewTracking').hide();
						$("#statusTrackingReviewForm").find("textarea,input").attr("disabled", "disabled");
						$('#colorPercent').removeAttr('disabled');
						$('.showUserHint').removeAttr('disabled');
						$('.bar').css('pointer-events','none');
					} else if (data.approveStatus == true){
						$('#readyforApproval-reviewTracking').hide();
					} 
					
					if (data.user != null) {
						$('#lastReviewedBy').show();
						$('#reviewerName').html(data.user.firstName + " " + data.user.lastName);
					} else {
						$('#lastReviewedBy').hide();
					}
					
					$('.project').each(function() {
						var $projectBar = $(this).find('.bar');
						var $projectPercent = $(this).find('.percent');
						$projectBar.slider({
							range : "min",
							animate : true,
							value : data.actualStatusPercentage,
							min : 0,
							max : 100,
							step : 1,
							slide : function(event, ui) {
								$projectPercent.val(ui.value);
							},
							change : function(event, ui) {
								var $projectRange = $(this).find('.ui-slider-range');
								var percent = ui.value;
								if (percent == 0) {
									$('#percent').val('#FFFFFF');
									$('#colorPercent').val(percent);
									$('#colorPer').html(percent);
									$projectPercent.css({
										'color' : '#FFFFFF'
									});
									$projectRange.css({
										'background' : '#FFFFFF'
									});
								} else if (percent <= 10) {
									$('#percent').val('#DDA6A1');
									$('#colorPercent').val(percent);
									$('#colorPer').html(percent);
									$projectPercent.css({
										'color' : '#DDA6A1'
									});
									$projectRange.css({
										'background' : '#DDA6A1'
									});
								} else if (percent > 10 && percent <= 20) {
									$('#percent').val('#E77B70');
									$('#colorPercent').val(percent);
									$('#colorPer').html(percent);
									$projectPercent.css({
										'color' : '#E77B70'
									});
									$projectRange.css({
										'background' : '#E77B70'
									});
								} else if (percent > 20 && percent <= 30) {
									$('#percent').val('#F14D41');
									$('#colorPercent').val(percent);
									$('#colorPer').html(percent);
									$projectPercent.css({
										'color' : '#F14D41'
									});
									$projectRange.css({
										'background' : '#F14D41'
									});
								} else if (percent > 30 && percent <= 40) {
									$('#percent').val('#F66A37');
									$('#colorPercent').val(percent);
									$('#colorPer').html(percent);
									$projectPercent.css({
										'color' : '#F66A37'
									});
									$projectRange.css({
										'background' : '#F66A37'
									});
								} else if (percent > 40 && percent <= 50) {
									$('#percent').val('#F89A3A');
									$('#colorPercent').val(percent);
									$('#colorPer').html(percent);
									$projectPercent.css({
										'color' : '#F89A3A'
									});
									$projectRange.css({
										'background' : '#F89A3A'
									});
								}else if (percent > 50 && percent <= 60) {
									$('#percent').val('#FECB3A');
									$('#colorPercent').val(percent);
									$('#colorPer').html(percent);
									$projectPercent.css({
										'color' : '#FECB3A'
									});
									$projectRange.css({
										'background' : '#FECB3A'
									});
								}else if (percent > 60 && percent <= 70) {
									$('#percent').val('#F3E83B');
									$('#colorPercent').val(percent);
									$('#colorPer').html(percent);
									$projectPercent.css({
										'color' : '#F3E83B'
									});
									$projectRange.css({
										'background' : '#F3E83B'
									});
								}else if (percent > 70 && percent <= 80) {
									$('#percent').val('#CDDA42');
									$('#colorPercent').val(percent);
									$('#colorPer').html(percent);
									$projectPercent.css({
										'color' : '#CDDA42'
									});
									$projectRange.css({
										'background' : '#CDDA42'
									});
								}else if (percent > 80 && percent <= 90) {
									$('#percent').val('#ABD045');
									$('#colorPercent').val(percent);
									$('#colorPer').html(percent);
									$projectPercent.css({
										'color' : '#ABD045'
									});
									$projectRange.css({
										'background' : '#ABD045'
									});
								}else if (percent > 90) {
									$('#percent').val('#9FCA47');
									$('#colorPercent').val(percent);
									$('#colorPer').html(percent);
									$projectPercent.css({
										'color' : '#9FCA47'
									});
									$projectRange.css({
										'background' : '#9FCA47'
									});
								}
							}
						});
					});
					
					
				}else{
					$('#id').val(0);
					$('#subActivityId').val(data.subActivity.id);
					if (data.actualStatusColor != null)
						$('#percent').val(data.actualStatusColor);
					else
						$('#percent').val("");
					if (data.actualStatusPercentage != null)	
						$('#colorPercent').val(data.actualStatusPercentage);
					else 
						$('#colorPercent').val("");
					if (data.keyProgress != null)
						$('#keyProgress').val(data.keyProgress);
					else
						$('#keyProgress').val("");
					if (data.reasonForGap != null)
						$('#reasonForGap').val(data.reasonForGap);
					else
						$('#reasonForGap').val("");
					if (data.rectifyTheGap != null)
						$('#rectifyTheGap').val(data.rectifyTheGap);
					else
						$('#rectifyTheGap').val("");

					if (data.user != null) {
						$('#lastReviewedBy').show();
						$('#reviewerName').html(data.user.firstName + " " + data.user.lastName);
					} else {
						$('#lastReviewedBy').hide();
					}
					if (data.carriedFrom != null && data.carriedFrom != '') {
						$('#carriedFromPreviousRPDiv').show();
						$('#carriedFromPreviousRP').html(data.carriedFrom);
					} else {
						$('#carriedFromPreviousRPDiv').hide();
					}
					
					$('#reviewStatus').attr('checked',false);
					
					$("#statusTrackingReviewForm").find("textarea,input").removeAttr('disabled');
					$('#save-reviewTracking').removeAttr("disabled");
					$('.bar').css('pointer-events','auto');
					
					$('#readyforApproval-reviewTracking').show();
					if (data.approveStatus == true){
						$('#readyforApproval-reviewTracking').hide();
					} 
					$('.project').each(function() {
						var $projectBar = $(this).find('.bar');
						var $projectPercent = $(this).find('.percent');
						$projectBar.slider({
							range : "min",
							animate : true,
							value : data.actualStatusPercentage,
							min : 0,
							max : 100,
							step : 1,
							slide : function(event, ui) {
								$projectPercent.val(ui.value);
							},
							change : function(event, ui) {
								var $projectRange = $(this).find('.ui-slider-range');
								var percent = ui.value;
								if (percent == 0) {
									$('#percent').val('#FFFFFF');
									$('#colorPercent').val(percent);
									$('#colorPer').html(percent);
									$projectPercent.css({
										'color' : '#FFFFFF'
									});
									$projectRange.css({
										'background' : '#FFFFFF'
									});
								} else if (percent <= 10) {
									$('#percent').val('#DDA6A1');
									$('#colorPercent').val(percent);
									$('#colorPer').html(percent);
									$projectPercent.css({
										'color' : '#DDA6A1'
									});
									$projectRange.css({
										'background' : '#DDA6A1'
									});
								} else if (percent > 10 && percent <= 20) {
									$('#percent').val('#E77B70');
									$('#colorPercent').val(percent);
									$('#colorPer').html(percent);
									$projectPercent.css({
										'color' : '#E77B70'
									});
									$projectRange.css({
										'background' : '#E77B70'
									});
								} else if (percent > 20 && percent <= 30) {
									$('#percent').val('#F14D41');
									$('#colorPercent').val(percent);
									$('#colorPer').html(percent);
									$projectPercent.css({
										'color' : '#F14D41'
									});
									$projectRange.css({
										'background' : '#F14D41'
									});
								} else if (percent > 30 && percent <= 40) {
									$('#percent').val('#F66A37');
									$('#colorPercent').val(percent);
									$('#colorPer').html(percent);
									$projectPercent.css({
										'color' : '#F66A37'
									});
									$projectRange.css({
										'background' : '#F66A37'
									});
								} else if (percent > 40 && percent <= 50) {
									$('#percent').val('#F89A3A');
									$('#colorPercent').val(percent);
									$('#colorPer').html(percent);
									$projectPercent.css({
										'color' : '#F89A3A'
									});
									$projectRange.css({
										'background' : '#F89A3A'
									});
								}else if (percent > 50 && percent <= 60) {
									$('#percent').val('#FECB3A');
									$('#colorPercent').val(percent);
									$('#colorPer').html(percent);
									$projectPercent.css({
										'color' : '#FECB3A'
									});
									$projectRange.css({
										'background' : '#FECB3A'
									});
								}else if (percent > 60 && percent <= 70) {
									$('#percent').val('#F3E83B');
									$('#colorPercent').val(percent);
									$('#colorPer').html(percent);
									$projectPercent.css({
										'color' : '#F3E83B'
									});
									$projectRange.css({
										'background' : '#F3E83B'
									});
								}else if (percent > 70 && percent <= 80) {
									$('#percent').val('#CDDA42');
									$('#colorPercent').val(percent);
									$('#colorPer').html(percent);
									$projectPercent.css({
										'color' : '#CDDA42'
									});
									$projectRange.css({
										'background' : '#CDDA42'
									});
								}else if (percent > 80 && percent <= 90) {
									$('#percent').val('#ABD045');
									$('#colorPercent').val(percent);
									$('#colorPer').html(percent);
									$projectPercent.css({
										'color' : '#ABD045'
									});
									$projectRange.css({
										'background' : '#ABD045'
									});
								}else if (percent > 90) {
									$('#percent').val('#9FCA47');
									$('#colorPercent').val(percent);
									$('#colorPer').html(percent);
									$projectPercent.css({
										'color' : '#9FCA47'
									});
									$projectRange.css({
										'background' : '#9FCA47'
									});
								}
							}
						});
					});
				}
				$('#subActivityName').html(data.subActivity.sequenceNumber + '. ' + data.subActivity.name);
				$('#statusSubActivityReviewer').modal('show');
			}
		});
}

function saveAndReadyForApproval() {
	var approveStatus = true;
	var colorParcent = $('#colorPercent').val();
	var percent = $('#percent').val();
	if (colorParcent == 0) {
		percent = "#FFFFFF";
	}
	var statusTracking={
			"id" : $('#id').val(),
			"subActivityId" : $('#subActivityId').val(),
			"reportingPeriodId" : $('#reportingId').val(),
			"actualStatusColor" : percent,
			"actualStatusPercentage" : colorParcent,
			"keyProgress" : $('#keyProgress').val(),
			"reasonForGap" : $('#reasonForGap').val(),
			"rectifyTheGap" : $('#rectifyTheGap').val(),
			"complete" : approveStatus
	};
	$.ajax({
		url : '/ndcmp/api/saveSubActivityDetails',
		data :{
			"statusTracking" : JSON.stringify(statusTracking)
		},
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data) {
			if (selectedSubActivityForDataCapture != null && selectedSubActivityForDataCapture.childElementCount == 2) {
				var colorDiv = selectedSubActivityForDataCapture.children[0].children[0];
				var colorDivStyle = colorDiv.style;
				
				colorDivStyle.setProperty('background-color', percent, 'important'); 
				var statusIcon = "";
				if (data.reworkRequired && !data.complete) {
					statusIcon = "fa fa-info-circle";
				} else if (data.complete && data.reviewStatus == 1){
					statusIcon = "fa fa-check-square-o";
				} else if (data.complete && data.sentForReview) {
					statusIcon = "fa fa-external-link-square";
				} else if (data.complete) {
					statusIcon = "fa fa-thumbs-o-up";
				}
				var statusIconDiv = selectedSubActivityForDataCapture.children[0].children[1].children[0];
				statusIconDiv.setAttribute('class', statusIcon);
				
				if (approveStatus) {
					for (var i = 1; i < selectedSubActivityForDataCapture.parentNode.childElementCount; i++) {
						var paStatusIconDiv = selectedSubActivityForDataCapture.parentNode.children[i].children[0].children[1].children[0];
						paStatusIconDiv.setAttribute('class', 'fa fa-check-circle-o');						
					}
				}
			}
			$('#statusSubActivityReviewer').modal('hide');
		}
	});
}

function saveStatusTrackerBySubActivity(){
	var approveStatus = false;
	var colorParcent = $('#colorPercent').val();
	var percent = $('#percent').val();
	if (colorParcent == 0) {
		percent = "#FFFFFF";
	}
	var statusTracking={
			"id" : $('#id').val(),
			"subActivityId" : $('#subActivityId').val(),
			"reportingPeriodId" : $('#reportingId').val(),
			"actualStatusColor" : percent,
			"actualStatusPercentage" : $('#colorPercent').val(),
			"keyProgress" : $('#keyProgress').val(),
			"reasonForGap" : $('#reasonForGap').val(),
			"rectifyTheGap" : $('#rectifyTheGap').val(),
			"complete" : approveStatus
	};
	$.ajax({
		url : '/ndcmp/api/saveSubActivityDetails',
		data :{
			"statusTracking" : JSON.stringify(statusTracking)
		},
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data) {
			if (selectedSubActivityForDataCapture != null && selectedSubActivityForDataCapture.childElementCount == 2) {
				var colorDiv = selectedSubActivityForDataCapture.children[0].children[0];
				var colorDivStyle = colorDiv.style;
				
				colorDivStyle.setProperty('background-color', percent, 'important'); 
			}
			$('#statusSubActivityReviewer').modal('hide');
		}
	});
}

function showObservation(){
	$.ajax({
		url : '/ndcmp/api/goObservation',
		data :{
			"reportingPeriodId" : $('#reportingId').val()
		},
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data) {
			if (data != null && data.length > 0) {
				$("#observationTitle").text("Partner Agency's Observation : " + data[0].reportingPeriod.year + " - " + data[0].reportingPeriod.name);
				$('#observationViewAccordion').html('');
				for (var i = 0; i < data.length; i++) {
					var agencyTitle = 'By ' + data[i].agency.code + ' - ( '+ data[i].user.firstName + ' ' + data[i].user.lastName + ' )';
					var date = new Date(data[i].submit_dateTime);
					var formattedDate = moment(date).format('DD-MM-YYYY HH:mm A');
					var collapse = "accordion-toggle collapsed";
					var collapsein = "";
					if (i  == 0 ){
						collapse = "accordion-toggle collapse";
						collapsein = "in";
					}
					var toggle = '<div class="panel panel-default">' +
											'<div class="panel-heading">' +
										'<h4 class="panel-title">' +
											'<a class="'+collapse+'" data-toggle="collapse" data-parent="#observationViewAccordion" href="#obsBody'+i+'">' +
											'<span> <b> ' + agencyTitle + ' </b></span></a>' +
										'</h4>' +
									'</div>' +
									'<div id="obsBody'+i+'" class="collapse '+collapsein+'">' +
										'<div class="panel-collapse" >' +
											'<div class="panel-body">' +
												'<table class="table table-striped table-bordered">' +
														'<tr>' +
															'<td style="width:25%;font-weight:bold;">Key Learnings</td>' +
															'<td>'+ data[i].keyLearning + '</td>' +
														'</tr>' +
														'<tr>' +
															'<td style="font-weight:bold;">Key Challenges</td>' +
															'<td>'+ data[i].keyChallenge + '</td>' +
														'</tr>' +
														'<tr>' +
															'<td style="font-weight:bold;">Best Practices</td>' +
															'<td>'+ data[i].bestPractice + '</td>' +
														'</tr>' +
														'<tr>' +
															'<td style="font-weight:bold;">Support Needs</td>' +
															'<td>'+ data[i].supportNeeds + '</td>' +
														'</tr>' +
														'<tr>' +
															'<td style="font-weight:bold;">Submitted Time</td>' +
															'<td>'+ formattedDate + '</td>' +
														'</tr>' +
												'</table>' +
											'</div>' +
										'</div>' +
									'</div>' +
							'</div>';
					$('#observationViewAccordion').append(toggle);
				}
				$('#observationModelWindow').modal('show');
			} else {
				$('#errorMsg').show();
				$('#observationErrorModelWindow').modal('show')
				//alert ("There is no observation found for this reporting period");
			}
		}
	});
}

function checkForReworkShow(showAlert) {
	var reportingPeriodId = $('#reportingPeriodId').val();
	if(reportingPeriodId != null && reportingPeriodId != ''){
		$.ajax({
			url : '/ndcmp/api/checkForReworkShow',
			data :{
				"reportingPeriodId" : reportingPeriodId
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				$('#reworkSummaryTable').html('');
				$('#reworkSummaryDiv').hide();
				if (data != null && data.length > 0) {
					var trs = '';
					for (var i = 0; i < data.length; i++) {
						trs += '<tr> <td width="5%">' + (i+1) + '</td> <td width="20%">' + data[i].agencyName + '</td> <td style="width:30%;text-align:center;" >' + data[i].totalRework + 
							'</td> <td style="width:20%;text-align:center;"> <input type="checkbox" class="reworkChkBox" value='+data[i].userId+' /> </td> </tr>'; //<button class="btn btn-primary" onclick="submitForRework('+data[i].userId+')">Send</button>
					}
					$('#reworkSummaryTable').html(trs);
					$('.reworkChkBox').click(function(){
						
						if (this.checked){
							reviewId.push($(this).val());
						}else {
							reviewId.remove($(this).val());
						}
						if($(".reworkChkBox").length == $(".reworkChkBox:checked").length) {
							$("#selectAllCheckBox").prop("checked", true);
						}else {
							$("#selectAllCheckBox").prop("checked", false);            
						}
						
						if ($(".reworkChkBox:checked").length > 0) {
							$('#submitForReview').show();
						} else {
							$('#submitForReview').hide();
						}
					});
					$('#reworkSummaryDiv').show();
				} else if (showAlert) {
					var msg = "There is no activity required for more information.";
					$('#sendForReeviewNoBtn').html('Close');
					$('#checkForReworkMessage2').show();
					$('#checkForReworkMessage').hide();
					$('#checkForReworkMessage2').html(msg);
					$('#sendForReworkSubmitBtn').hide();
					$('#checkSubmitModal').modal('show');
				}
			}
		});
	} else{
		$('#reportingValidationErrorMsg').show();
		$('#validationErrorModelWindow').modal('show');
		return false;
	}
}

function checkForRework(){
	var reportingPeriodId = $('#reportingPeriodId').val();
	if(reportingPeriodId != null && reportingPeriodId != ''){
		$.ajax({
			url : '/ndcmp/api/checkForRework',
			data :{
				"reportingPeriodId" : reportingPeriodId
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				if(data != null){
					if(data > 0){
						var msg = "<strong>There are " + data + " activities required for more information.</strong> Do you want to send for rework ?";
						if (data == 1) {
							msg = "<strong>There is " + data + " activity required for more information.</strong> Do you want to send for rework ?";	
						}
						$('#checkForReworkMessage').html(msg);
						$('#sendForReworkSubmitBtn').show();
						$('#checkForReworkMessage2').hide();
						$('#checkForReworkMessage').show();
						$('#checkSubmitModal').modal('show');
					} else {
						var msg = "There is no activity required for more information.";
						$('#checkForReworkMessage2').show();
						$('#checkForReworkMessage').hide();
						$('#checkForReworkMessage2').html(msg);
						$('#sendForReworkSubmitBtn').hide();
						$('#checkSubmitModal').modal('show');
					} 
				}
			}
		});
	}else{
		alert("Please select reporting period");
	}
}

function submitForRework(){
	partnerUserId = reviewId.toString();
	$('#checkSubmitModal').modal('hide');
	var reportingPeriodId = $('#reportingPeriodId').val();
	if(reportingPeriodId != null && reportingPeriodId != ''){
		$.ajax({
			url : '/ndcmp/api/confirmReworkSubmition',
			data :{
				"reportingPeriodId" : reportingPeriodId,
				"partnerUserId" : partnerUserId
			},
			type : 'POST',
			async : false,
			dataType : 'json',
			success : function(data) {
				if(data != null && data > 0){
					var msg = "Sent for rework successfully!";
					$('#sendForReeviewNoBtn').html('Close');
					$('#checkForReworkMessage').show();
					$('#checkForReworkMessage2').hide();
					$('#checkForReworkMessage').html(msg);
					$('#sendForReworkSubmitBtn').hide();
					$('#checkSubmitModal').modal('show');
					checkForReworkShow(false);
				}
			}
		});
	}else{
		alert("Please select reporting period");
	}
}

function submitForApproval(){
	var reportingPeriodId = $('#yearId').val();
	if(reportingPeriodId != null && reportingPeriodId != ''){
		$.ajax({
			url : '/ndcmp/api/checkApprovalSubmit',
			data :{
				"reportingPeriodId" : reportingPeriodId
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				
				if(data != null && data.remaining != null){
					var string = data;
					//pillarsBasedOnQuarters += string.total +"remaining" + string.remaining;
					if(string.remaining > 0){
						var msg = string.completed + " out of " + string.total + " activities have been completed.";
						if (string.total == 1) {
							msg = string.completed + " out of " + string.total + " activity have been completed.";
						}
						if (string.remaining == 1){
							msg += " Please complete pending " +  string.remaining + " activity to be sent for next level approval.";
						} else {
							msg += " Please complete pending " +  string.remaining + " activities to be sent for next level approval.";
						}
						$('#submitReviewMessage').html(msg);
						$('#submitReviewMessage').show();
						$('#submitReviewMessage2').hide();
						$('#checkSubmitModal').modal('show');
					}else{
						$('#supportNeeds').val(data.supportNeeds);
						$('#bestPractice').val(data.bestPractice);
						$('#keyChallenge').val(data.keyChallenge);
						$('#keyLearning').val(data.keyLearning);
						$('#reportingPeriod').val(data.reportingPeriod.id);
						$('#proceedCheckSubmitModal').modal('show');
					}
				} else {
					var msg = "There is no activity pending to be sent for approval.";
					$('#submitReviewMessage2').html(msg);
					$('#submitReviewMessage').hide();
					$('#submitReviewMessage2').show();
					$('#checkSubmitModal').modal('show');
				}
			}
		});
	} else{
		alert("Please select reporting period");
	}
}

function showReviewerFeedback() {
	$('#reviewerFeedbackDiv').show();
	$('#reviewerFeedback').focus();
}

function hideReviewerFeedback() {
	$('#reviewerFeedback').val('');
	$('#reviewerFeedbackDiv').hide();
}
