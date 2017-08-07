
var selectedLASubActivityForReview;

$(document).ready(function(){
	$('#reset').hide();
	$('#approverDetails').hide();
	$('#filter-btn-show').hide();
	
	$('.hideHint').hide();
	$('.showHint').css('cursor', 'pointer');
	$('.hideHint').css('cursor', 'pointer');
	
	$('.accordion').collapse();
	
	$('#tabs > li').removeClass('active');
	$('#approveTab').attr('class','active');
	$('#showColorHint').hide();
	
	$(".showUserHint").click(function(){
		$('#showColorHint').show();
		$('.showHint').hide();
		$('.hideHint').show();
	});

	$(".hideHint").click(function(){
		$('#showColorHint').hide();
		$('.showHint').show();
		$('.hideHint').hide();
	});
});

// Start Color Div
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
	swatches : [ '#DAAEA9', '#E1948E', '#DDA59E', '#E5847D','#EE5B4F','#F34537','#F57836','#FCEA3A','#DADF3D','#91C648' ],
	order : {
		hsl : 1
	}
});
// End Color Div

// Start Accordin
//Register accordion click event
$('.accordion a').on('click', function() {
	var _this = $(this);
	var _expanded = $(_this).attr('aria-expanded');
	if (_expanded == 'false') {
		$('.btn .edit .delete').show();
	} else if (_expanded == 'true') {
		$('.btn .edit .delete').hide();
	}
});
// End Accordian

// Start -> Strategic Piller Loading based on reporting period
function getApproverStatusByReportingPeriod(selectedReportingValue){
	var reporting =  $('#reportingId').val();
	$('#statusId').val('All');
	
	if (reporting == undefined || reporting == '') {
		$('#statusId').val('');
		$('#strategicPillarId').html('<option value="">---Select Starategic Pillar ---</option>');
		$('#themeId').html('<option value="">---Select Theme ---</option>');
		$('#outcomeId').html('<option value="">---Select Outcome ---</option>');
		$('#outputId').html('<option value="">---Select Output ---</option>');
	} else if (reporting != undefined && reporting != '') {
		$.ajax({
			url : '/ndcmp/api/getApproverStrategicPillerByReportingPeriod',
			data : {
				reportingPeriodId : reporting,
				status : 'All'
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				
				if(reporting != null && reporting != ""){
					$('#approver_observation-btn').show();
				}else{
					$('#approver_observation-btn').hide();
				}
				
				if (data.length > 0 || data != null) {
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
					getApproverThemesByStrategicPiller(pillars[0].id);
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
// End Strategic Piller

function getApproverStrategicPillerByStatus() {
	var reporting = $('#reportingId').val();
	var status = $('#statusId').val();
	
	if (status == undefined || status == '') {
		$('#strategicPillarId').html('<option value="">---Select Starategic Pillar ---</option>');
		$('#themeId').html('<option value="">---Select Theme ---</option>');
		$('#outcomeId').html('<option value="">---Select Outcome ---</option>');
		$('#outputId').html('<option value="">---Select Output ---</option>');
	} else if (status != undefined && status != '') {
		$.ajax({
			url : '/ndcmp/api/getApproverStrategicPillerByReportingPeriod',
			data : {
				reportingPeriodId : reporting,
				status : status
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				
				if(reporting != null && reporting != ""){
					$('#approver_observation-btn').show();
				}else{
					$('#approver_observation-btn').hide();
				}
				
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
					getApproverThemesByStrategicPiller(pillars[0].id);
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
// Start --> Theme Loading based on strategic piller
function getApproverThemesByStrategicPiller(slectedStrategicPiller){
	var strategicId = slectedStrategicPiller;
	var status = $('#statusId').val();
	var reporting = $('#reportingId').val();
	if (strategicId == undefined || strategicId == '') {
		$('#themeId').html('<option value="">---Select Theme ---</option>');
		$('#outcomeId').html('<option value="">---Select Outcome ---</option>');
		$('#outputId').html('<option value="">---Select Output ---</option>');
	} else {
		$.ajax({
			url : '/ndcmp/api/getApproverThemesByStrategicPiller',
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
					getApproverOutcomesByThemes(themes[0].id);
				}else{
					$('#themeId').html('<option value="">---Select Theme ---</option>');
					$('#outcomeId').html('<option value="">---Select Outcome ---</option>');
					$('#outputId').html('<option value="">---Select Output ---</option>');
				}
			}
		});
	}
	
}
// End Theme Loading

// Start --> Outcomes loading based on the theme selected
function getApproverOutcomesByThemes(selectedThemeId){
	var themeId = selectedThemeId;
	var status = $('#statusId').val();
	if (themeId == undefined || themeId == '') {
		$('#outcomeId').html('<option value="">---Select Outcome ---</option>');
		$('#outputId').html('<option value="">---Select Output ---</option>');
	} else {
		$.ajax({
			url : '/ndcmp/api/getApproverOutcomesByThemes',
			data : {
				reportingPeriodId : $('#reportingId').val(),
				themeId : themeId,
				status : status
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				if (data != null && data.length > 0) {
					var outcomebasedontheme = '<option value="">---Select Outcome ---</option>';
					var outcomes = data;
					for (var i = 0; i < outcomes.length; i++) {
						if (i == 0) {
							outcomebasedontheme += '<option value="'
								+ outcomes[i].id + '" selected="selected">'
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
					getApproverOutputsByOutcome(outcomes[0].id);
				}else{
					$('#outcomeId').html('<option value="">---Select Outcome ---</option>');
					$('#outputId').html('<option value="">---Select Output ---</option>');
				}
			}
		});
	}
	
}
// End theme loading

// Start output loading based on the outcome selected
function getApproverOutputsByOutcome(selectedOutcomeId){
	var outcomeId = selectedOutcomeId;
	var status = $('#statusId').val();
	if (outcomeId == undefined || outcomeId == '') {
		$('#outputId').html('<option value="">---Select Output ---</option>');
	} else {
		$.ajax({
			url : '/ndcmp/api/getApproverOutputsByOutcome',
			data : {
				reportingPeriodId : $('#reportingId').val(),
				outcomeId : outcomeId,
				status: status
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
// End output loading

// Start -- > Load approver filter data
function loadApproverFilterData(){
	if($('#reportingId').val() != "" && $('#strategicPillarId').val() != "" && $('#themeId').val() != "" && $('#outcomeId').val() != "" && $('#outputId').val() != "")
	{
		$('#approverDiv').hide();
		$('#filter-btn').hide();
		$('#reset').show();
		$('#filter-btn-show').show();
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
			url : '/ndcmp/api/loadApproverData',
			data : {
				projectId : projectId,
				reviewerFilter : JSON.stringify(filterHierForReviewer),
				statusId : statusId
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
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
//						if(subActivities[j].approveORCompleteStatus == true){
//							modalBody += '<div style="height:35px;border: 1px solid #e4d6d6;background-color: #f3bcb7;padding: 5px;">'
//								+ '<span style="color:#ce1115;">'+subActivities[j].sequenceNumber+' '+ subActivities[j].subActivity +'</span><br></div>';
//							if(subActivities[j].carryOverStatus == true){
//								
//							}
//								
//						}else if(subActivities[j].carryOverStatus == true){
//							modalBody += '<div style="height:35px;border: 1px solid #e4d6d6;background-color: #CE7173;padding: 5px;">'
//								+ '<span>'+subActivities[j].sequenceNumber+' '+ subActivities[j].subActivity +'</span><br></div>';
//						}else{
//							modalBody += '<div style="height:35px;border: 1px solid #e4d6d6;background-color: #f3bcb7;padding: 5px;">'
//								+ '<span >'+subActivities[j].sequenceNumber+' '+ subActivities[j].subActivity +'</span><br></div>';
//						}
//						
						if(subActivities[j].approveORCompleteStatus == true){
							if(subActivities[j].carryOverStatus == true){
								modalBody += '<div style="height:35px;border: 1px solid #e4d6d6;background-color: #CE7173;padding: 5px;">'
										  + '<span style="color:#801e7f;">'+subActivities[j].sequenceNumber+' '+ subActivities[j].subActivity +'</span><br></div>';
							}else{
								modalBody += '<div style="height:35px;border: 1px solid #e4d6d6;background-color: #f3bcb7;padding: 5px;">'
									  + '<span style="color:#801e7f;">'+subActivities[j].sequenceNumber+' '+ subActivities[j].subActivity +'</span><br></div>';
							}
						}else{
							if(subActivities[j].carryOverStatus == true){
								modalBody += '<div style="height:35px;border: 1px solid #e4d6d6;background-color: #CE7173;padding: 5px;">'
										  + '<span>'+subActivities[j].sequenceNumber+' '+ subActivities[j].subActivity +'</span><br></div>';
							}else{
								modalBody += '<div style="height:35px;border: 1px solid #e4d6d6;background-color: #f3bcb7;padding: 5px;">'
									  + '<span>'+subActivities[j].sequenceNumber+' '+ subActivities[j].subActivity +'</span><br></div>';
							}
						}
						
						var partnerAgencies = subActivities[j].agencyDTOs;
						for(var k =0;k < partnerAgencies.length; k++){
								modalBody += '<div title="Click to view" onclick="getStatusReviewer('+ partnerAgencies[k].userId +','+ subActivities[j].id +', this, \''+ partnerAgencies[k].agency +'\', \''+partnerAgencies[k].userName+'\')" style="height:35px;border: 1px solid #e4d6d6;background-color: #FBDEDB;cursor: pointer;padding: 5px;">'
								+ '<span style="margin-left: 20px;">'+"by"+' '+partnerAgencies[k].agency+' ('+partnerAgencies[k].userName+')<div class="pull-right" style="width:20px; height:20px;border-radius: 5px; border: 1px solid; margin-right:15px; background-color: '+ partnerAgencies[k].colorCode +'"></div>'
								+ '<span class="pull-right" style="width:20px;height: 20px;margin-right:5px;text-align: center;"><i style="margin-top:5px;" class="'+ partnerAgencies[k].statusIcon +'"></i></span>' 
								+ '</span><br></div>';
						}
					}
					modalBody += '</div></div></div></div>';		  
				}
				modalBody += '</div>';
				$('#approverDetails').html(modalBody);
				$('#approverDetails').show();
				$('#approver_all-btn').show();
			}
		});
		return true;
	}else{
		var reportID = $('#reportingId').val();
		var statusID = $('#statusId').val();
		var stragicID = $('#strategicPillarId').val();
		var themeID = $('#themeId').val();
		var outcomeID = $('#outcomeId').val();
		var outputID = $('#outputId').val();
		
		if(reportID == ""){
			$('#reportingValidationErrorMsg').show();
			$('#validationErrorModelWindow').modal('show');
			return false;
		}else{
			$('#reportingValidationErrorMsg').hide();
		}
		
		if (statusID == "") {
			$('#statusValidationErrorMsg').show();
			$('#validationErrorModelWindow').modal('show');
			return false;
		}else{
			$('#statusValidationErrorMsg').hide();
		}
		
		if (stragicID == "") {
			$('#strategicValidationErrorMsg').show();
			$('#validationErrorModelWindow').modal('show');
			return false;
		}else{
			$('#strategicValidationErrorMsg').hide();
		}
		
		if (themeID == "") {
			$('#themeValidationErrorMsg').show();
			$('#validationErrorModelWindow').modal('show');
			return false;
		}else{
			$('#themeValidationErrorMsg').hide();
		}
		
		if (outcomeID == "") {
			$('#outcomeValidationErrorMsg').show();
			$('#validationErrorModelWindow').modal('show');
			return false;
		}else{
			$('#outcomeValidationErrorMsg').hide();
		}
		
		if (outputID == "") {
			$('#outputValidationErrorMsg').show();
			$('#validationErrorModelWindow').modal('show');
			return false;
		}else{
			$('#outputValidationErrorMsg').hide();
		}
		//$('#validationErrorModelWindow').modal('show');
	}
}
// End approver filter data loading


/*start Approve all*/
function showApproveAllConfirmation() {
	$('#confirmApproveAllModal').modal('show');
}
function approveAll(){
	$('#confirmApproveAllModal').modal('hide');
	if($('#reportingId').val() != "" && $('#strategicPillarId').val() != "" && $('#themeId').val() != "" && $('#outcomeId').val() != "" && $('#outputId').val() != "")
	{
		$('#approverDiv').hide();
		$('#filter-btn').hide();
		$('#reset').show();
		$('#filter-btn-show').show();
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
			url : '/ndcmp/api/approveAll',
			data : {
				projectId : projectId,
				reviewerFilter : JSON.stringify(filterHierForReviewer),
				statusId : statusId
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				if(data == true){
					var approverList = $('#approverDetails');
					
					if (approverList.get(0) != undefined && approverList.get(0).children[0] != undefined)
					for (var i = 0; i < approverList.get(0).children[0].children.length; i++) {
						var actvitiyDiv = approverList.get(0).children[0].children[i];
						if (actvitiyDiv != undefined){
							var list = actvitiyDiv.children[1].children[0].children[0].children;
							for (var j = 0 ; j < list.length; j++){
								var activity = list[j].children[0].children[1];
								if(activity != undefined && activity.localName == "span"){
									var statusIconDiv = activity.children[0];
									if(statusIconDiv.className == "null"){
										statusIconDiv.setAttribute('class', 'fa fa-check-circle-o');
									}
								}
							}
						}
					}
				} else {
					
				}
			}
		});
		return true;
	}
}
/*End approve all*/

function resetFuncion(){
	$('#approverDiv').show();
	$('#filter-btn').show();
	$('#reset').hide();
	$('#filter-btn-show').hide();
	$('#approver_all-btn').hide();
	getApproverStrategicPillerByStatus();
}
function getStatusReviewerShow(){
	$('#confirmApproveAndCompleteAllModal').modal('hide');
	getStatusReviewer(uId,sId,element1,agencyId,uname);
}
var uId = '';
var sId = '';
var agencyId = '';
var uname = '';
var element1 = '';
// Start ---> View Reviewer status update data
function getStatusReviewer(userId,subId, element, agency, userName){
	uId = userId;
	sId = subId;
	element1 = element;
	agencyId = agency;
	uname = userName;
	
	selectedLASubActivityForReview = element;
	$.ajax({
		url : '/ndcmp/api/getApproverStatusTrackingDetails',
		data : {
			userId : userId,
			subId : subId,
			reportingPeriodId : $('#reportingId').val()
		},
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data) {
			$('#saveOptionButton').show();
			if (data.id != null) {
				$('#id').val(data.id);
				$('#userIdReadonly').val(data.user.id);
				$('#subActivityIdReadonly').val(data.subActivity.id);
				$('#subActivityId').val(data.subActivity.id);
				$('#percentReadonly').val(data.actualStatusColor);
				$('#colorPercentReadonly').val(data.actualStatusPercentage);
				$('#keyProgressReadonly').val(data.keyProgress);
				$('#reasonForGapReadonly').val(data.reasonForGap);
				$('#rectifyTheGapReadonly').val(data.rectifyTheGap);
				$('#approverFeedback').val(data.reviewerFeedback);
				$('#approverFeedback').attr("disabled", false);
				$('#reviewerName').html(agency + ' (' + userName + ')');
				$('.bar').css('pointer-events','none');
				var reviewerStatus = data.reviewStatus;
				if(reviewerStatus == -1){
					$('#needToWorkValueReadonly').prop("checked", true);
					$('#approveId1').show();
					$('#approveId2').hide();
				}else if(reviewerStatus == 1){
					$('#completeValueReadonly1').prop("checked", true);
					$('#approveId1').hide();
					$('#approveId2').show();
					$('#approverFeedback').attr("disabled", true);
					$('#saveOptionButton').hide();
				}else{
					$('#completeValueReadonly').attr('checked',false);
					$('#needToWorkValueReadonly').attr('checked',false);
					$('#approveId1').show();
					$('#approveId2').hide();
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
				$('#subActivityNameReadonly').html(data.subActivity.name);
				if(data.reportingPeriod.reportingStatus == "Closed"){
					$('#approverInformation').modal('show');
					$('#approveORcomplete').hide();
				}else{
					if(data.reviewStatus == 0){
						$('#approveORcomplete').show();
					}else if(data.reviewStatus == 1){
						if(data.subActivity.approveORCompleteStatus == true){
							$('#approveORcomplete').hide();
						}else{
							$('#approveORcomplete').show();
						}
					}else if(data.reviewStatus = -1){
						$('#approveORcomplete').hide();
					}
					$('#approverInformation').modal('show');
					var approverStatus = data.reviewStatus;
					if(approverStatus == 1){
						$('#approveORcomplete').html("Complete")
					}else if(approverStatus == 0){
						$('#approveORcomplete').html("Approve and Complete")
					}else{
						$('#approveORcomplete').hide();
					}
				}
				
			}else{
				alert("Failed to load status tracking");
				$('#approverInformation').modal('hide');
			}
		}
	});
}
// End reviewer status update data loading


//to show the showApproveOrCompleteButton
function showApproveOrCompleteButton(){
	var reviewStatus=$("input[name='review']:checked").val();
	if(reviewStatus == "completeValue"){
		$('#approveORcomplete').show();
	}else if(reviewStatus == "needToWorkValue"){
		$('#approveORcomplete').hide();
	}
}


// Start ---> Saving approver status tracking info
function saveApproverStatusTracker(){
	var reviewStatus=$("input[name='review']:checked").val();
	var approvalStatus = 0;
	if(reviewStatus == "completeValue"){
		approvalStatus = 1;
	}else if(reviewStatus == "needToWorkValue"){
		approvalStatus = -1;
	}
	$.ajax({
		url : '/ndcmp/api/saveApproverStatusTrackerDetails',
		data :{
			"subActivityId" : $('#subActivityIdReadonly').val(),
			"userId" : $('#userIdReadonly').val(),
			"reportingPeriodId" : $('#reportingId').val(),
			"reviewStatus" : approvalStatus, 
			"approverFeedback" : $('#approverFeedback').val()
			
		},
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data) {
			
			if (selectedLASubActivityForReview != null && selectedLASubActivityForReview.childElementCount == 2) {
				var statusIconDiv = selectedLASubActivityForReview.children[0].children[1].children[0];
				if (approvalStatus == 1) {
					statusIconDiv.setAttribute('class', 'fa fa-check-circle-o');
				} else if (approvalStatus == -1) {
					statusIconDiv.setAttribute('class', 'fa fa-info-circle');
				}
			}
			
			$('#approverInformation').modal('hide');
		}
	});
}
// End saving approver status tracking info

//to set approverOrComplete status
function saveApproverStatusTrackerCompleted(selectRange){
	if(selectRange == 0){
		selectRange = 0;
	}else{
		selectRange = 1;
	}
	var approvalStatus = 1;
	$.ajax({
		url : '/ndcmp/api/saveApproverCompletedStatusTrackerDetails',
		data :{
			"subActivityId" : $('#subActivityIdReadonly').val(),
			"userId" : $('#userIdReadonly').val(),
			"reportingPeriodId" : $('#reportingId').val(),
			"reviewStatus" : approvalStatus, 
			"approverFeedback" : $('#approverFeedback').val(),
			selectRange : selectRange
			
		},
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data) {
			if(data.errorStatus == true){
				$('#approverInformation').modal('hide');
				$('#confirmApproveAndCompleteAllModal').modal('show');
			}else{
				$('#confirmApproveAndCompleteAllModal').modal('hide');
				if (data != null && data.id != null) {
					if (selectedLASubActivityForReview != null 	&& selectedLASubActivityForReview.childElementCount == 2) {
						var statusIconDiv = selectedLASubActivityForReview.children[0].children[1].children[0];	statusIconDiv.setAttribute('class',	'fa fa-check-circle-o');
					}
				}
			}
			$('#approverInformation').modal('hide');
		}
	});
}



// Start Approver Observation
function showApproverObservation(){
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
				$("#approverObservationTitle").text("Lead Agency's Observation : " + data[0].reportingPeriod.year + " - " + data[0].reportingPeriod.name);
				$('#approverObservationViewAccordion').html('');
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
											'<a class="'+collapse+'" data-toggle="collapse" data-parent="#approverObservationViewAccordion" href="#obsBody'+i+'">' +
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
					$('#approverObservationViewAccordion').append(toggle);
				}
				$('#approverObservationModelWindow').modal('show');
			} else {
				$('#errorMsg').show();
				$('#observationErrorModelWindow').modal('show');
				//alert ("There is no observation found for this reporting period");
			}
		}
	});
}
// End Approver Observation