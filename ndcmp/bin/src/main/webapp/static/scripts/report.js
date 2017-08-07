function statusUpdaterActualReportDownload(){
	var year = $('#reportingPeriodId').val();
	var yearId = $('#quaterId').val();
	var spId = $('#strategicPillarId').val();
	var themeId = $('#themeId').val();
	var outcomeId = $('#outcomeId').val();
	var outputId = $('#outputId').val();
	/*var partnerAgencyId = $('#partnerAgencyId').val();*/
	
	/*if(yearId == ''){
		alert('Reporting Period is Required');
		document.getElementById("yearId").focus();
		return false;
	}*/
	
	 $.ajax({
			url : '/ndcmp/api/statusUpdaterActualReportDownload',
			data :{
				year : year.toString(),
				yearId : yearId.toString(),
				spId : spId.toString(),
				themeId : themeId.toString(),
				outcomeId : outcomeId.toString(),
				outputId : outputId.toString()
				/*partnerAgencyId : partnerAgencyId*/
			},
	 		type : 'GET',
	 		async : false,
	 		success : function(data) {
	 			if (data != null) {
	 				window.open(data);
	 			}
	 		}
	 	});
}



/**
 * @author Prem Kumar
 * Loading PartnerAgency By ReportingPeriod
 * @param 
 */
function loadReportPartner(statusId){
	var reportingPeriodId = $('#yearId').val();
	$.ajax({
		url : '/ndcmp/api/getReportPartnerAgency',
		data : {
			reportingPeriodId : reportingPeriodId
		},
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data) {
			
			if (data != null) {
				var agency = '<option value="-1">Not Include</option><option value="0">---All---</option>';
				var agencys = data;
				for (var i = 0; i < agencys.length; i++) {
					agency += '<option value="'
							+ agencys[i].id + '">'
							+ agencys[i].name
							+ '</option>';
				}
				$('#partnerAgencyId').html(agency);
			}
		}
	});
}

function loadReviewerQuarter(){
	var reportingPeriodId = $('#reportingPeriodId').val();
	 $.ajax({
			url : '/ndcmp/api/loadQuater',
			data :{
				reportingPeriodId : reportingPeriodId.toString()
			},
	 		type : 'GET',
	 		async : false,
	 		success : function(data) {
	 			if (data != null) {
	 				var QuaterByReportingPeriod = '<option selected="selected" value="0">---All---</option>';
					var Quaters = data;
					for (var i = 0; i < Quaters.length; i++) {
						QuaterByReportingPeriod += '<option  value="'
								+ Quaters[i].id + '">'
								+ Quaters[i].year+"-"+ Quaters[i].name;
						QuaterByReportingPeriod	+= '</option>';
					}
	 				$('#yearId').html(QuaterByReportingPeriod);
	 				$("select#yearId").prop('selectedIndex', 1);
					 
//					 var quater={
//							 value :Quaters[0].id
//					 }
					// getOutcomesByTheme(pillar);
	 				loadReportPillars($('#yearId').val());
	 				//$('#themeId').html('<option value="0">---All---</option>');
//	 				$('#outcomeId').html('<option value="0">---All---</option>');
//	 				$('#outputId').html('<option value="0">---All---</option>');
//	 				$('#partnerAgencyId').html('<option value="0">----All---</option><option value="-1">Not Include</option>');
	 			}else {
	 				$('#strategicPillarId').html('<option value="0">---All---</option>');
	 				$('#themeId').html('<option value="0">---All---</option>');
	 				$('#outcomeId').html('<option value="0">---All---</option>');
	 				$('#outputId').html('<option value="0">---All---</option>');
				}
	 		}
	 	});
}

/**
 * @author PremKumar
 * @param reportId
 */
function loadPartnerReportPillars(reportId){
	var reportingPeriodId = $('#quaterId').val();
	$.ajax({
		url : '/ndcmp/api/getPartnerReportStrategicPillar',
		data : {
			reportingPeriodId : reportingPeriodId.toString()
		},
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data) {
			if (data.length > 0) {
				var pillarsbasedonreportingperiod = '<option value="0">---All---</option>';
				var pillars = data;
				for (var i = 0; i < pillars.length; i++) {
					pillarsbasedonreportingperiod += '<option value="'
							+ pillars[i].id + '">'
							+ pillars[i].sequenceNumber+" . "+ pillars[i].name
							+ '</option>';
				}
				
				$('#strategicPillarId').html(pillarsbasedonreportingperiod);
				$('select#strategicPillarId').prop('selectedIndex',0);
				 var pillar={
						 value :pillars[0].id
				 };
				 loadPartnerReportTheme(pillar);
//				$('#themeId').html('<option value="0">---All---</option>');
//				$('#outcomeId').html('<option value="0">---All---</option>');
//				$('#outputId').html('<option value="0">---All---</option>');
//				$('#partnerAgencyId').html('<option value="0">---All---</option><option value="-1">Not Include</option>');
			}else {
				$('#strategicPillarId').html('<option value="0">---All---</option>');
				$('#themeId').html('<option value="0">---All---</option>');
				$('#outcomeId').html('<option value="0">---All---</option>');
				$('#outputId').html('<option value="0">---All---</option>');
				$('#partnerAgencyId').html('<option value="0">---All---</option>');
			}
		}
	});
}

/**
 * @author prem
 * Loading Strategic Pillars in Reviewer filter
 * @param statusId
 */
function loadReportPillars(statusId){
	var reportingPeriodId = $('#yearId').val();
	if(reportingPeriodId == "" || reportingPeriodId == '' || reportingPeriodId == null){
		/*$('#strategicPillarId').html('<option value="0">---All---</option>');
		$('#themeId').html('<option value="0">---All---</option>');
		$('#outcomeId').html('<option value="0">---All---</option>');
		$('#outputId').html('<option value="0">---All---</option>');
		$('#partnerAgencyId').html('<option value="-1">Not Include</option><option value="0">---All---</option>');*/
		$('#strategicPillarId').html('<option value="0">---All---</option>');
		$('#themeId').html('<option value="0">---All---</option>');
		$('#outcomeId').html('<option value="0">---All---</option>');
		$('#outputId').html('<option value="0">---All---</option>');
		$('#partnerAgencyId').html('<option value="0">---All---</option>');
	}else{
		$.ajax({
			url : '/ndcmp/api/getReportStrategicPillar',
			data : {
				reportingPeriodId : reportingPeriodId.toString()
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				
				if (data.length > 0) {
					var pillarsbasedonreportingperiod = '<option value="0">---All---</option>';
					var pillars = data;
					for (var i = 0; i < pillars.length; i++) {
						pillarsbasedonreportingperiod += '<option value="'
								+ pillars[i].id + '">'
								+ pillars[i].sequenceNumber+" . "+ pillars[i].name
								+ '</option>';
					}
					
					$('#strategicPillarId').html(pillarsbasedonreportingperiod);
					$('select#strategicPillarId').prop('selectedIndex',0);
					 var pillar={
							 value :pillars[0].id
					 }
					getThemesByStrategicReport(pillar);
//					$('#themeId').html('<option value="0">---All---</option>');
//					$('#outcomeId').html('<option value="0">---All---</option>');
//					$('#outputId').html('<option value="0">---All---</option>');
//					$('#partnerAgencyId').html('<option value="0">---All---</option><option value="-1">Not Include</option>');
				}else{
					/*$('#strategicPillarId').html('<option value="0">---All---</option>');
					$('#themeId').html('<option value="0">---All---</option>');
					$('#outcomeId').html('<option value="0">---All---</option>');
					$('#outputId').html('<option value="0">---All---</option>');
					$('#partnerAgencyId').html('<option value="-1">Not Include</option><option value="0">---All---</option>');*/
					$('#strategicPillarId').html('<option value="0">---All---</option>');
					$('#themeId').html('<option value="0">---All---</option>');
					$('#outcomeId').html('<option value="0">---All---</option>');
					$('#outputId').html('<option value="0">---All---</option>');
					$('#partnerAgencyId').html('<option value="0">---All---</option>');
				}
			}
		});
	}
}


function getThemesByStrategicReport(strategicId){
	var reportingPeriodId = $('#yearId').val();
//	var strategicIdVal = strategicId.value;
	var strategicIdVal = $('#strategicPillarId').val();
	if(strategicIdVal == "" || strategicIdVal == '' || strategicIdVal == null){
		$('#themeId').html('<option value="0">---All---</option>');
		$('#outcomeId').html('<option value="0">---All---</option>');
		$('#outputId').html('<option value="0">---All---</option>');
		$('#partnerAgencyId').html('<option value="0">---All---</option>');
	}else{
		$.ajax({
			url : '/ndcmp/api/getThemesByStrategicReport',
			data : {
				reportingPeriodId : reportingPeriodId.toString(),
				strategicId : strategicIdVal.toString()
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				
				if (data.length > 0) {
					var themesbasedonstrategic = '<option value="0">---All---</option>';
					var themes = data;
					for (var i = 0; i < themes.length; i++) {
						themesbasedonstrategic += '<option value="'
								+ themes[i].id + '">'
								+ themes[i].name
								+ '</option>';
					}
					$('#themeId').html(themesbasedonstrategic);
					$('select#themeId').prop('selectedIndex',0);
					 var theme={
							 value :themes[0].id
					 }
					getOutcomesByTheme(theme);
//					$('#outcomeId').html('<option value="0">---All---</option>');
//					$('#outputId').html('<option value="0">---All---</option>');
//					$('#partnerAgencyId').html('<option value="0">---All---</option><option value="-1">Not Include</option>');
				}else{
					$('#themeId').html('<option value="0">---All---</option>');
					$('#outcomeId').html('<option value="0">---All---</option>');
					$('#outputId').html('<option value="0">---All---</option>');
					$('#partnerAgencyId').html('<option value="0">---All---</option>');
				}
			}
		});
	}
	
}

//Get outcomes based on the selected theme --> Planner
function getOutcomesByTheme(slectedTheme){
//	var themeId = slectedTheme.value;
	var reportingPeriodId = $('#yearId').val();
	var themeId = $('#themeId').val();
	var strategic = $('#strategicPillarId').val();
	if(themeId == null || themeId == "" || themeId == ''){
		$('#outcomeId').html('<option value="0">---All---</option>');
		$('#outputId').html('<option value="0">---All---</option>');
		$('#partnerAgencyId').html('<option value="0">---All---</option>');
	}else{
		$.ajax({
			url : '/ndcmp/api/getOutcomesByThemeReport',
			data : {
				reportingPeriodId : reportingPeriodId.toString(),
				themeId : themeId.toString(),
				strategic : strategic.toString()
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				if (data != null) {
					var outcomebasedontheme = '<option value="0">---All---</option>';
					var outcomes = data;
					for (var i = 0; i < outcomes.length; i++) {
						outcomebasedontheme += '<option value="'
								+ outcomes[i].id + '">'
								+ outcomes[i].sequenceNumber + '. ' 
								+ outcomes[i].name
								+ '</option>';
					}
					$('#outcomeId').html(outcomebasedontheme);
					$('select#outcomeId').prop('selectedIndex',0);
					var outcome={
							 value :outcomes[0].id
					 }
					getOutputsByoutcome(outcome);
//					$('#outputId').html('<option value="0">---All---</option>');
//					$('#partnerAgencyId').html('<option value="0">---All---</option><option value="-1">Not Include</option>');
				}else{
					$('#outcomeId').html('<option value="0">---All---</option>');
					$('#outputId').html('<option value="0">---All---</option>');
					$('#partnerAgencyId').html('<option value="0">---All---</option>');
				}
			}
		});
	}
}

// Get outputs by selected outcome ---> planner
function getOutputsByoutcome(slectedOutcome){
//	var outcomeId = slectedOutcome.value;
	var reportingPeriodId = $('#yearId').val();
	var themeId = $('#themeId').val();
	var strategic = $('#strategicPillarId').val();
	var outcomeId = $('#outcomeId').val();
	if(outcomeId == null || outcomeId == ""){
		$('#outputId').html('<option value="0">---All---</option>');
		$('#partnerAgencyId').html('<option value="0">---All---</option>');
	}else{
		$.ajax({
			url : '/ndcmp/api/getOutputsByoutcomeReport',
			data : {
				reportingPeriodId : reportingPeriodId.toString(),
				strategicId : strategic.toString(),
				themeId : themeId.toString(),
				outcomeId : outcomeId.toString()
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				if (data != null) {
					var outputbasedonoutcome = '<option value="0">---All---</option>';
					var outputs = data;
					for (var i = 0; i < outputs.length; i++) {
						outputbasedonoutcome += '<option value="'
								+ outputs[i].id + '">'
								+ outputs[i].sequenceNumber + '. '
								+ outputs[i].output 
								+ '</option>';
					}
					$('#outputId').html(outputbasedonoutcome);
					$('#outputId').prop('selectedIndex',0);
					var output={
							 value :outputs[0].id
					 }
					getPartnerAgencyByoutput(output);
//					$('#partnerAgencyId').html('<option value="0">---All---</option><option value="-1">Not Include</option>');
				} else {
					$('#outputId').html('<option value="0">---All---</option>');
					$('#partnerAgencyId').html('<option value="0">---All---</option>');
				}
			}
		});
	}
}

function getPartnerAgencyByoutput(selected){
//	var outputId = selected.value;
	var reportingPeriodId = $('#yearId').val();
	var outputId = $('#outputId').val();
	if(outputId == null || outputId == "" || outputId == ''){
		$('#partnerAgencyId').html('<option value="0">---All---</option>');
	}else{
		$.ajax({
			url : '/ndcmp/api/getPartnerAgencyByoutput',
			data : {
				reportingPeriodId : reportingPeriodId.toString(),
				outputId : outputId.toString()
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				if (data != null) {
					var agency = '<option value="0">---All---</option>';
					var agencys = data;
					for (var i = 0; i < agencys.length; i++) {
						agency += '<option value="'
								+ agencys[i].id + '">'
								+ agencys[i].name
								+ '</option>';
					}
					$('#partnerAgencyId').html(agency);
					$("select#partnerAgencyId").prop('selectedIndex', 0);
				}else {
					$('#partnerAgencyId').html('<option value="0">---All---</option>');
				}
			}
		});
	}
}

/*
 * for statusReviewerActualReportDownload using reportingPeriod and PartnerAgency
 */
/*function statusReviewerActualReportDownload(){
	var reportingId = $('#yearId').val();
	var partnerAgencyId = $('#partnerAgencyId').val();
	if(reportingId == ''){
		alert('Reporting Period is Required');
		document.getElementById("yearId").focus();
		return false;
	}
	$.ajax({
		url : '/ndcmp/api/statusReviewerActualReportDownload',
		data :{
			reportingId : reportingId,
			partnerAgencyId : partnerAgencyId
		},
 		type : 'GET',
 		async : false,
 		success : function(data) {
 			if (data != null) {
 				window.open(data);
 			}
 		}
 	});
}*/

//Reviewer Report Details

function statusReviewerActualReportDownload(){
	var year = $('#reportingPeriodId').val();
	var yearId = $('#yearId').val();
	var spId = $('#strategicPillarId').val();
	var themeId = $('#themeId').val();
	var outcomeId = $('#outcomeId').val();
	var outputId = $('#outputId').val();
	var partnerAgencyId = $('#partnerAgencyId').val();

	 var checked = $('#required').is(':checked');
	 if(checked){
		 partnerAgencyId = "-1";
	 }
	 
	 $.ajax({
			url : '/ndcmp/api/statusReviewerActualReportDownload',
			data :{
				year : year.toString(),
				yearId : yearId.toString(),
				spId : spId.toString(),
				themeId : themeId.toString(),
				outcomeId : outcomeId.toString(),
				outputId : outputId.toString(),
				partnerAgencyId : partnerAgencyId.toString()
			},
	 		type : 'GET',
	 		async : false,
	 		success : function(data) {
	 			if (data != null) {
	 				window.open(data);
	 			}
	 		}
	 	});
}


function getLeadAgencyByoutput(selected){
	var outputId = selected.value;
	if(outputId == null || outputId == ""){
		$('#partnerAgencyId').html('<option value="">---Select---</option>');
	}else{
		$.ajax({
			url : '/ndcmp/api/getLeadAgencyByoutput',
			data : {
				outputId : outputId
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				if (data != null) {
					var agency = '<option value="">---Select---</option>';
					var agencys = data;
					for (var i = 0; i < agencys.length; i++) {
						agency += '<option value="'
								+ agencys[i].id + '">'
								+ agencys[i].name
								+ '</option>';
					}
					$('#partnerAgencyId').html(agency);
				}
			}
		});
	}
}


//to download the approver actual report
function downloadApproverReport(){
	var reportingPeriodId = $('#reportingPeriodId').val();
	if(reportingPeriodId == ''){
		alert('Reporting Period is Required');
		document.getElementById("reportingPeriodId").focus();
		return false;
	}
	
	 $.ajax({
			url : '/ndcmp/api/approverActualReport',
			data :{
				reportingPeriodId : reportingPeriodId
			},
	 		type : 'GET',
	 		async : false,
	 		success : function(data) {
	 			if (data != null) {
	 				var Pillars = '<option value="0">---All---</option>';
					var Pillars = data;
					for (var i = 0; i < Pillars.length; i++) {
						Pillars += '<option value="'
								+ Pillars[i].id + '">'
								+ Pillars[i].name;
						Pillars	+= '</option>';
					}
	 				('#strategicPillarId').html(Pillars);
	 			}
	 		}
	 	});
}


function loadPartnerQuater(selected){
	var reportingPeriodId = $('#reportingPeriodId').val();
	 $.ajax({
			url : '/ndcmp/api/loadQuater',
			data :{
				reportingPeriodId : reportingPeriodId.toString()
			},
	 		type : 'GET',
	 		async : false,
	 		success : function(data) {
	 			if (data != null) {
	 				var QuaterByReportingPeriod = '<option selected="selected" value="0">---All---</option>';
					var Quaters = data;
					for (var i = 0; i < Quaters.length; i++) {
						QuaterByReportingPeriod += '<option  value="'
								+ Quaters[i].id + '">'
								+ Quaters[i].year+"-"+ Quaters[i].name;
						QuaterByReportingPeriod	+= '</option>';
					}
	 				$('#quaterId').html(QuaterByReportingPeriod);
	 				$("select#quaterId").prop('selectedIndex', 1);
					 
//					 var quater={
//							 value :Quaters[0].id
//					 }
					// getOutcomesByTheme(pillar);
					 loadPartnerReportPillars($('#quaterId').val());
	 				//$('#themeId').html('<option value="0">---All---</option>');
//	 				$('#outcomeId').html('<option value="0">---All---</option>');
//	 				$('#outputId').html('<option value="0">---All---</option>');
//	 				$('#partnerAgencyId').html('<option value="0">----All---</option><option value="-1">Not Include</option>');
	 			}else {
	 				$('#strategicPillarId').html('<option value="0">---All---</option>');
	 				$('#themeId').html('<option value="0">---All---</option>');
	 				$('#outcomeId').html('<option value="0">---All---</option>');
	 				$('#outputId').html('<option value="0">---All---</option>');
				}
	 		}
	 	});
	/*else {
			$('#quaterId').html('<option selected="selected" value="0">---All---</option>');
			$('#strategicPillarId').html('<option value="0">---All---</option>');
			$('#themeId').html('<option value="0">---All---</option>');
			$('#outcomeId').html('<option value="0">---All---</option>');
			$('#outputId').html('<option value="0">---All---</option>');
	}*/
}


function loadApproverQuater(selected){
	var reportingPeriodId = $('#reportingPeriodId').val();
	 $.ajax({
			url : '/ndcmp/api/loadQuater',
			data :{
				reportingPeriodId : reportingPeriodId.toString()
			},
	 		type : 'GET',
	 		async : false,
	 		success : function(data) {
	 			if (data != null) {
	 				var QuaterByReportingPeriod = '<option selected="selected" value="0">---All---</option>';
					var Quaters = data;
					for (var i = 0; i < Quaters.length; i++) {
						QuaterByReportingPeriod += '<option  value="'
								+ Quaters[i].id + '">'
								+ Quaters[i].year+"-"+ Quaters[i].name;
						QuaterByReportingPeriod	+= '</option>';
					}
	 				$('#quaterId').html(QuaterByReportingPeriod);
	 				$("select#quaterId").prop('selectedIndex', 1);
					 
//					 var quater={
//							 value :Quaters[0].id
//					 }
					// getOutcomesByTheme(pillar);
					 loadApproverReportPillars($('#quaterId').val());
	 				//$('#themeId').html('<option value="0">---All---</option>');
//	 				$('#outcomeId').html('<option value="0">---All---</option>');
//	 				$('#outputId').html('<option value="0">---All---</option>');
//	 				$('#partnerAgencyId').html('<option value="0">----All---</option><option value="-1">Not Include</option>');
	 			}else {
	 				$('#strategicPillarId').html('<option value="0">---All---</option>');
	 				$('#themeId').html('<option value="0">---All---</option>');
	 				$('#outcomeId').html('<option value="0">---All---</option>');
	 				$('#outputId').html('<option value="0">---All---</option>');
				}
	 		}
	 	});
	/*else {
			$('#quaterId').html('<option selected="selected" value="0">---All---</option>');
			$('#strategicPillarId').html('<option value="0">---All---</option>');
			$('#themeId').html('<option value="0">---All---</option>');
			$('#outcomeId').html('<option value="0">---All---</option>');
			$('#outputId').html('<option value="0">---All---</option>');
	}*/
}


//Load Strategic Pillar for Approver
function loadApproverReportPillars(selected){
	var reportingPeriodId = $('#reportingPeriodId').val();
	var quaterId = $('#quaterId').val();
	
	if (quaterId.length > 0 ) {
	 $.ajax({
			url : '/ndcmp/api/loadApproverStrategicPillarByReportingPeriod',
			data :{
				reportingPeriodId : quaterId.toString(),
			},
	 		type : 'GET',
	 		async : false,
	 		success : function(data) {
	 			if (data != "") {
	 				var PillarsByReportingPeriod = '<option value="0">---All---</option>';
					var Pillars = data;
					for (var i = 0; i < Pillars.length; i++) {
						PillarsByReportingPeriod += '<option value="'
								+ Pillars[i].id + '">'
								+Pillars[i].sequenceNumber +"." + Pillars[i].name;
						PillarsByReportingPeriod	+= '</option>';
					}
	 				$('#strategicPillarId').html(PillarsByReportingPeriod);
	 				$("select#strategicPillarId").prop('selectedIndex', 0);
					 
					 var pillar={
							 value :Pillars[0].id
					 }
					// getOutcomesByTheme(pillar);
					 loadApproverReportTheme(pillar);
	 				//$('#themeId').html('<option value="0">---All---</option>');
//	 				$('#outcomeId').html('<option value="0">---All---</option>');
//	 				$('#outputId').html('<option value="0">---All---</option>');
//	 				$('#partnerAgencyId').html('<option value="0">----All---</option><option value="-1">Not Include</option>');
	 			}else {
	 				$('#strategicPillarId').html('<option value="0">---All---</option>');
	 				$('#themeId').html('<option value="0">---All---</option>');
	 				$('#outcomeId').html('<option value="0">---All---</option>');
	 				$('#outputId').html('<option value="0">---All---</option>');
	 				$('#partnerAgencyId').html('<option value="0">----All---</option><option value="-1">Not Include</option>');
				}
	 		}
	 	});
	}else{
			$('#strategicPillarId').html('<option value="0">---All---</option>');
			$('#themeId').html('<option value="0">---All---</option>');
			$('#outcomeId').html('<option value="0">---All---</option>');
			$('#outputId').html('<option value="0">---All---</option>');
	}
}



//Load Strategic Pillar for SuperAdmin
function loadSuperAdminReportPillars(selected){
	var reportingPeriodId = $('#reportingPeriodId').val();
	if(reportingPeriodId == 0){
		$('#themeId').html('<option value="0">---All---</option>');
		$('#outcomeId').html('<option value="0">---All---</option>');
		$('#outputId').html('<option value="0">---All---</option>');
		$('#strategicPillarId').html('<option value="0">---All---</option>');
		$('#partnerAgencyId').html('<option value="0">----All---</option><option value="-1">Not Include</option>');
	}else if (reportingPeriodId != 0) {
	 $.ajax({
			url : '/ndcmp/api/loadStrategicPillarByReportingPeriod',
			data :{
				reportingPeriodId : reportingPeriodId
			},
	 		type : 'GET',
	 		async : false,
	 		success : function(data) {
	 			if (data != null) {
	 				var PillarsByReportingPeriod = '<option value="0">---All---</option>';
					var Pillars = data;
					for (var i = 0; i < Pillars.length; i++) {
						PillarsByReportingPeriod += '<option value="'
								+ Pillars[i].id + '">'
								+Pillars[i].sequenceNumber +"." + Pillars[i].name;
						PillarsByReportingPeriod	+= '</option>';
					}
	 				$('#strategicPillarId').html(PillarsByReportingPeriod);
	 				$("select#strategicPillarId").prop('selectedIndex', 1);
					 
					 var pillar={
							 value :Pillars[0].id
					 }
					// getOutcomesByTheme(pillar);
	 				loadSuperAdminReportTheme(pillar);
	 				//$('#themeId').html('<option value="0">---All---</option>');
//	 				$('#outcomeId').html('<option value="0">---All---</option>');
//	 				$('#outputId').html('<option value="0">---All---</option>');
//	 				$('#partnerAgencyId').html('<option value="0">----All---</option><option value="-1">Not Include</option>');
	 			}else {
	 				$('#themeId').html('<option value="0">---All---</option>');
	 				$('#outcomeId').html('<option value="0">---All---</option>');
	 				$('#outputId').html('<option value="0">---All---</option>');
	 				$('#partnerAgencyId').html('<option value="0">----All---</option><option value="-1">Not Include</option>');
				}
	 		}
	 	});
	}
}

/**
 * @author PremKumar
 * DataCollector loading theme
 * @param selected
 */
function loadPartnerReportTheme(selected){
	var reportingPeriodId = $('#quaterId').val();
	var strategicPillarId = $('#strategicPillarId').val();
	 $.ajax({
			url : '/ndcmp/api/loadPartnerThemeByStrategicPillarAndReportingPeriod',
			data :{
				reportingPeriodId : reportingPeriodId.toString(),
				strategicId : strategicPillarId.toString()
			},
	 		type : 'GET',
	 		async : false,
	 		success : function(data) {
	 			if (data.length > 0) {
	 				var ThemeByStrategicPillarAndReportiongPeriod = '<option value="0">---All---</option>';
					var Theme = data;
					for (var i = 0; i < Theme.length; i++) {
						ThemeByStrategicPillarAndReportiongPeriod += '<option value="'
								+ Theme[i].id + '">'
								+ Theme[i].name
								+ '</option>';
					}
	 				$('#themeId').html(ThemeByStrategicPillarAndReportiongPeriod);
	 				$("select#themeId").prop('selectedIndex', 0);
					 
					 var theme={
							 value :Theme[0].id
					 }
					 loadPartnerReportOutcome(theme);
//	 				$('#outcomeId').html('<option value="0">---All---</option>');
//	 				$('#outputId').html('<option value="0">---All---</option>');
//	 				$('#partnerAgencyId').html('<option value="0">----All---</option><option value="-1">Not Include</option>');
	 			}
	 			else {
	 				$('#themeId').html('<option value="0">---All---</option>');
	 				$('#outcomeId').html('<option value="0">---All---</option>');
	 				$('#outputId').html('<option value="0">---All---</option>');
	 				$('#partnerAgencyId').html('<option value="0">----All---</option><option value="-1">Not Include</option>');
				}
	 		}
	 	});
}

//Load Theme By StrategicPillar for Approver
function loadApproverReportTheme(selected){
	var reportingPeriodId = $('#quaterId').val();
	var strategicPillarId = $('#strategicPillarId').val();
	 $.ajax({
			url : '/ndcmp/api/loadApproverThemeByStrategicPillarAndReportingPeriod',
			data :{
				reportingPeriodId : reportingPeriodId.toString(),
				strategicPillarId : strategicPillarId.toString()
			},
	 		type : 'GET',
	 		async : false,
	 		success : function(data) {
	 			if (data != null) {
	 				var ThemeByStrategicPillarAndReportiongPeriod = '<option value="0">---All---</option>';
					var Theme = data;
					for (var i = 0; i < Theme.length; i++) {
						ThemeByStrategicPillarAndReportiongPeriod += '<option value="'
								+ Theme[i].id + '">'
								+ Theme[i].name
								+ '</option>';
					}
	 				$('#themeId').html(ThemeByStrategicPillarAndReportiongPeriod);
	 				$("select#themeId").prop('selectedIndex', 0);
					 
					 var theme={
							 value :Theme[0].id
					 }
					 loadApproverReportOutcome(theme);
//	 				$('#outcomeId').html('<option value="0">---All---</option>');
//	 				$('#outputId').html('<option value="0">---All---</option>');
//	 				$('#partnerAgencyId').html('<option value="0">----All---</option><option value="-1">Not Include</option>');
	 			}
	 			else {
	 				$('#outcomeId').html('<option value="0">---All---</option>');
	 				$('#outputId').html('<option value="0">---All---</option>');
	 				$('#partnerAgencyId').html('<option value="0">----All---</option><option value="-1">Not Include</option>');
				}
	 		}
	 	});
}
		 	

//Load Theme By StrategicPillar
function loadSuperAdminReportTheme(selected){
	var reportingPeriodId = $('#reportingPeriodId').val();
	var strategicPillarId = $('#strategicPillarId').val();
	if(strategicPillarId == 0){
		$('#themeId').html('<option value="0">---All---</option>');
		$('#outcomeId').html('<option value="0">---All---</option>');
		$('#outputId').html('<option value="0">----All---</option>');
		$('#partnerAgencyId').html('<option value="0">----All---</option><option value="-1">Not Include</option>');
	}else if (strategicPillarId != 0) {
	 $.ajax({
			url : '/ndcmp/api/loadThemeByStrategicPillarAndReportingPeriod',
			data :{
				reportingPeriodId : reportingPeriodId,
				strategicPillarId : strategicPillarId
			},
	 		type : 'GET',
	 		async : false,
	 		success : function(data) {
	 			if (data != null) {
	 				var ThemeByStrategicPillarAndReportiongPeriod = '<option value="0">---All---</option>';
					var Theme = data;
					for (var i = 0; i < Theme.length; i++) {
						ThemeByStrategicPillarAndReportiongPeriod += '<option value="'
								+ Theme[i].id + '">'
								+ Theme[i].name
								+ '</option>';
					}
	 				$('#themeId').html(ThemeByStrategicPillarAndReportiongPeriod);
	 				$("select#themeId").prop('selectedIndex', 1);
					 
					 var theme={
							 value :Theme[0].id
					 }
					 loadSuperAdminReportOutcome(theme);
//	 				$('#outcomeId').html('<option value="0">---All---</option>');
//	 				$('#outputId').html('<option value="0">---All---</option>');
//	 				$('#partnerAgencyId').html('<option value="0">----All---</option><option value="-1">Not Include</option>');
	 			}
	 			else {
		 				$('#outcomeId').html('<option value="0">---All---</option>');
		 				$('#outputId').html('<option value="0">---All---</option>');
		 				$('#partnerAgencyId').html('<option value="0">----All---</option><option value="-1">Not Include</option>');
					}
		 		}
		 	});
		}
	}

/**
 * @author PremKumar
 * 
 * @param selected
 */

function loadPartnerReportOutcome(selected){
	var reportingPeriodId = $('#quaterId').val();
	var strategicPillarId = $('#strategicPillarId').val();
	var themeId = $('#themeId').val();
	
	 $.ajax({
			url : '/ndcmp/api/loadPartnerOutcomeByThemeAndReportingPeriod',
			data :{
				reportingPeriodId : reportingPeriodId.toString(),
				strategicPillarId : strategicPillarId.toString(),
				themeId : themeId.toString()
			},
	 		type : 'GET',
	 		async : false,
	 		success : function(data) {
	 			if (data != null) {
	 				var OutcomeByThemeAndReportingPeriod = '<option value="0">---All---</option>';
					var Outcome = data;
					for (var i = 0; i < Outcome.length; i++) {
						OutcomeByThemeAndReportingPeriod += '<option value="'
								+ Outcome[i].id + '">'
								+Outcome[i].sequenceNumber +"." + Outcome[i].name
								+ '</option>';
					}
	 				$('#outcomeId').html(OutcomeByThemeAndReportingPeriod);
	 				$("select#outcomeId").prop('selectedIndex', 0);
					 
					 var outcome={
							 value :Outcome[0].id
					 }
					 loadPartnerReportOutput(outcome);
//	 				$('#outputId').html('<option value="0">---All---</option>');
//	 				$('#partnerAgencyId').html('<option value="0">----All---</option><option value="-1">Not Include</option>');
	 			}else {
	 				$('#outputId').html('<option value="0">---All---</option>');
	 				$('#partnerAgencyId').html('<option value="0">----All---</option><option value="-1">Not Include</option>');
				}
	 		}
	 	});
	}

//To Load Outcome Based On StrategicPillar Theme ReportingPeriod for Approver
function loadApproverReportOutcome(selected){
	var reportingPeriodId = $('#quaterId').val();
	var strategicPillarId = $('#strategicPillarId').val();
	var themeId = $('#themeId').val();
	
	 $.ajax({
			url : '/ndcmp/api/loadApproverOutcomeByThemeAndReportingPeriod',
			data :{
				reportingPeriodId : reportingPeriodId.toString(),
				strategicPillarId : strategicPillarId.toString(),
				themeId : themeId.toString()
			},
	 		type : 'GET',
	 		async : false,
	 		success : function(data) {
	 			if (data != null) {
	 				var OutcomeByThemeAndReportingPeriod = '<option value="0">---All---</option>';
					var Outcome = data;
					for (var i = 0; i < Outcome.length; i++) {
						OutcomeByThemeAndReportingPeriod += '<option value="'
								+ Outcome[i].id + '">'
								+Outcome[i].sequenceNumber +"." + Outcome[i].name
								+ '</option>';
					}
	 				$('#outcomeId').html(OutcomeByThemeAndReportingPeriod);
	 				$("select#outcomeId").prop('selectedIndex', 0);
					 
					 var outcome={
							 value :Outcome[0].id
					 }
					 loadApproverReportOutput(outcome);
//	 				$('#outputId').html('<option value="0">---All---</option>');
//	 				$('#partnerAgencyId').html('<option value="0">----All---</option><option value="-1">Not Include</option>');
	 			}else {
	 				$('#outputId').html('<option value="0">---All---</option>');
	 				$('#partnerAgencyId').html('<option value="0">----All---</option><option value="-1">Not Include</option>');
				}
	 		}
	 	});
	}



//To Load Outcome Based On StrategicPillar Theme ReportingPeriod
function loadSuperAdminReportOutcome(selected){
	var reportingPeriodId = $('#reportingPeriodId').val();
	var strategicPillarId = $('#strategicPillarId').val();
	var themeId = $('#themeId').val();
	if(themeId == 0){
		$('#outcomeId').html('<option value="0">---All---</option>');
		$('#outputId').html('<option value="0">---All---</option>');
		$('#partnerAgencyId').html('<option value="0">----All---</option><option value="-1">Not Include</option>');
	}else if (themeId != 0) {
	 $.ajax({
			url : '/ndcmp/api/loadOutcomeByThemeAndReportingPeriod',
			data :{
				reportingPeriodId : reportingPeriodId,
				strategicPillarId : strategicPillarId,
				themeId : themeId
			},
	 		type : 'GET',
	 		async : false,
	 		success : function(data) {
	 			if (data != null) {
	 				var OutcomeByThemeAndReportingPeriod = '<option value="0">---All---</option>';
					var Outcome = data;
					for (var i = 0; i < Outcome.length; i++) {
						OutcomeByThemeAndReportingPeriod += '<option value="'
								+ Outcome[i].id + '">'
								+Outcome[i].sequenceNumber +"." + Outcome[i].name
								+ '</option>';
					}
	 				$('#outcomeId').html(OutcomeByThemeAndReportingPeriod);
	 				$("select#outcomeId").prop('selectedIndex', 1);
					 
					 var outcome={
							 value :Outcome[0].id
					 }
					 loadSuperAdminReportOutput(outcome);
//	 				$('#outputId').html('<option value="0">---All---</option>');
//	 				$('#partnerAgencyId').html('<option value="0">----All---</option><option value="-1">Not Include</option>');
	 			}else {
	 				$('#outputId').html('<option value="0">---All---</option>');
	 				$('#partnerAgencyId').html('<option value="0">----All---</option><option value="-1">Not Include</option>');
				}
	 		}
	 	});
	}
}

function loadPartnerReportOutput(selected){
	var reportingPeriodId = $('#quaterId').val();
	var outcomeId = $('#outcomeId').val();
	var themeId = $('#themeId').val();
	var strategicId = $('#strategicPillarId').val();
	 $.ajax({
			url : '/ndcmp/api/loadPartnerOutputByOutcomeAndReportingPeriod',
			data :{
				reportingPeriodId : reportingPeriodId.toString(),
				outcomeId : outcomeId.toString(),
				themeId : themeId.toString(),
				strategicId : strategicId.toString()
			},
	 		type : 'GET',
	 		async : false,
	 		success : function(data) {
	 			if (data != null) {
	 				var OutputByOutcomeAndReportingPeriod = '<option value="0">---All---</option>';
					var Output = data;
					for (var i = 0; i < Output.length; i++) {
						OutputByOutcomeAndReportingPeriod += '<option value="'
								+ Output[i].id + '">'
								+Output[i].sequenceNumber +"." + Output[i].output
								+ '</option>';
					}
	 				$('#outputId').html(OutputByOutcomeAndReportingPeriod);
	 				$("select#outputId").prop('selectedIndex', 0);
 				}
	 		}
	 	});
	}



//To Load Output Based On StrategicPillar Theme Outcome ReportingPeriod for Approver
function loadApproverReportOutput(selected){
	var reportingPeriodId = $('#quaterId').val();
	var outcomeId = $('#outcomeId').val();
	var themeId = $('#themeId').val();
	var strategicId = $('#strategicPillarId').val();
	 $.ajax({
			url : '/ndcmp/api/loadApproverOutputByOutcomeAndReportingPeriod',
			data :{
				reportingPeriodId : reportingPeriodId.toString(),
				outcomeId : outcomeId.toString(),
				themeId : themeId.toString(),
				strategicId : strategicId.toString()
			},
	 		type : 'GET',
	 		async : false,
	 		success : function(data) {
	 			if (data != null) {
	 				var OutputByOutcomeAndReportingPeriod = '<option value="0">---All---</option>';
					var Output = data;
					for (var i = 0; i < Output.length; i++) {
						OutputByOutcomeAndReportingPeriod += '<option value="'
								+ Output[i].id + '">'
								+Output[i].sequenceNumber +"." + Output[i].output
								+ '</option>';
					}
	 				$('#outputId').html(OutputByOutcomeAndReportingPeriod);
	 				$("select#outputId").prop('selectedIndex', 0);
 				}
	 		}
	 	});
	}


//To Load Output Based On StrategicPillar Theme Outcome ReportingPeriod
function loadSuperAdminReportOutput(selected){
	var reportingPeriodId = $('#reportingPeriodId').val();
	var outcomeId = $('#outcomeId').val();
	if(outcomeId == 0){
		$('#outputId').html('<option value="0">---All---</option>');
		$('#partnerAgencyId').html('<option value="0">----All---</option><option value="-1">Not Include</option>');
	}else if (outcomeId != 0) {
	 $.ajax({
			url : '/ndcmp/api/loadOutputByOutcomeAndReportingPeriod',
			data :{
				reportingPeriodId : reportingPeriodId,
				outcomeId : outcomeId
			},
	 		type : 'GET',
	 		async : false,
	 		success : function(data) {
	 			if (data != null) {
	 				var OutputByOutcomeAndReportingPeriod = '<option value="0">---All---</option>';
					var Output = data;
					for (var i = 0; i < Output.length; i++) {
						OutputByOutcomeAndReportingPeriod += '<option value="'
								+ Output[i].id + '">'
								+Output[i].sequenceNumber +"." + Output[i].output
								+ '</option>';
					}
	 				$('#outputId').html(OutputByOutcomeAndReportingPeriod);
	 				$("select#outputId").prop('selectedIndex', 1);
					 
					 var output={
							 value :Output[0].id
					 }
					 getPartnerAgencyByoutput(output);
//	 				$('#partnerAgencyId').html('<option value="0">----All---</option><option value="-1">Not Include</option>');
	 			}else{
	 				$('#partnerAgencyId').html('<option value="0">----All---</option><option value="-1">Not Include</option>');
 				}
	 		}
	 	});
	}
}

//to load LeadAgency List
function LoadLeadAgencyList(selected){

	var reportingPeriodId = $('#reportingPeriodId').val();
	var outputId = $('#outputId').val();
	if(reportingPeriodId == ''){
		alert('Reporting Period is Required');
		document.getElementById("reportingPeriodId").focus();
		return false;
	}
	 $.ajax({
			url : '/ndcmp/api/loadLeadAgency',
			data :{
				reportingPeriodId : reportingPeriodId,
				outputId : outputId
			},
	 		type : 'GET',
	 		async : false,
	 		success : function(data) {
	 			if (data != null) {
	 				var AgencyByOutputAndReportingPeriod = '<option value="0">---All---</option><option value="-1">Not Include</option>';
					var Agency = data;
					for (var i = 0; i < Agency.length; i++) {
						AgencyByOutputAndReportingPeriod += '<option value="'
								+ Agency[i].id + '">'
								+ Agency[i].name
								+ '</option>';
					}
	 				$('#leadAgencyId').html(AgencyByOutputAndReportingPeriod);
	 			}
	 		}
	 	});
}


function approverActualReportDownload(){
	var yearId = $('#reportingPeriodId').val();
	var reportingPeriodId = $('#quaterId').val();
	var spId = $('#strategicPillarId').val();
	var themeId = $('#themeId').val();
	var outcomeId = $('#outcomeId').val();
	var outputId = $('#outputId').val();
	
	if(reportingPeriodId == ''){
		alert('Reporting Period is Required');
		document.getElementById("quaterId").focus();
		return false;
	}
	
	 $.ajax({
			url : '/ndcmp/api/approverActualReportDownload',
			data :{
				year : yearId.toString(),
				reportingPeriodId : reportingPeriodId.toString(),
				spId : spId.toString(),
				themeId : themeId.toString(),
				outcomeId : outcomeId.toString(),
				outputId : outputId.toString(),
			},
	 		type : 'GET',
	 		async : false,
	 		success : function(data) {
	 			if (data != null) {
	 				window.open(data);
	 			}else{
	 				window.location.href="login";
	 			}
	 		}
	 	});
}


function superAdminActualReportDownload(){
	var reportingPeriodId = $('#quaterId').val();
	var spId = $('#strategicPillarId').val();
	var themeId = $('#themeId').val();
	var outcomeId = $('#outcomeId').val();
	var outputId = $('#outputId').val();
	var partnerAgencyId = $('#partnerAgencyId').val();
	
	 $.ajax({
			url : '/ndcmp/api/superAdminActualReportDownload',
			data :{
				reportingPeriodId : reportingPeriodId.toString(),
				spId : spId.toString(),
				themeId : themeId.toString(),
				outcomeId : outcomeId.toString(),
				outputId : outputId.toString(),
				partnerAgencyId : partnerAgencyId.toString()
			},
	 		type : 'GET',
	 		async : false,
	 		success : function(data) {
	 			if (data != null) {
	 				window.open(data);
	 			}
	 		}
	 	});
}

function changePartnerEntityMode(checkedValue){
	 var checked = $(checkedValue).is(':checked');
	 if(checked){
		 $('#partnerAgencyId').css('pointer-events', 'none');
	 }else{
		 $('#partnerAgencyId').css('pointer-events', 'auto');
	 }
}