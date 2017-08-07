/*
 * Add By Jeeva
 */

/*
 * Load All Quarters based on Year
 */

var selectedSubActivityForDataCapturePA;

var ctx = window.location.pathname.split('/');
ctx = ctx[1];

var currentRole = localStorage.getItem("role");
var subActivityIds=[];
var reportingPeriodIds=[];
var allIds=[];
var checkedIds=[];
var status="All";
var deleteId=[];
var outputDto=[];
$(document).ready(function() {
	$('#reworkJobLevelStatus').fadeOut();
	$('a.toggle-vis').on( 'click', function (e) {
        e.preventDefault();
        // Get the column API object
        var column = table.column(e.currentTarget.dataset.columnindex);
        // Toggle the visibility
       column.visible( ! column.visible() );
       var colVal=e.currentTarget.dataset.columnindex;
       
       if(column.visible()){
    	   if(colVal == 3){
    			$('#a3').css('display','none');
    	    $('#th'+colVal).show(); 
    	   }else if(colVal == 4){
    		   $('#a4').css('display','none');
    	    $('#th'+colVal).show();
    	   }else if(colVal == 5){
    		   $('#a5').css('display','none');
    	    	   $('#th'+colVal).show();
    	   }
    	   
    	   
       }else if(! column.visible() ){
    	   
    	   if(colVal == 3){
    		   	$('#a3').show();
    	    	$('#th'+colVal).hide(); 
    	       }else if(colVal == 4){
    	    	   $('#a4').show();
    	    	   $('#th'+colVal).hide();
    	       }else if(colVal == 5){
    	    	   $('#a5').show();
    	    	   $('#th'+colVal).hide();
    	       }
       }
       
       /*if(colVal == 3){
    	$('#th'+colVal).hide(); 
       }else if(colVal == 4){
    	   $('#th'+colVal).hide();
       }else if(colVal == 5){
    	   $('#th'+colVal).hide();
       }else{
    	   $('#th'+colVal).show();
       }*/
	} );
	
	$('#save-plan').hide();
	$('#myDropdown').hide();
	$('#reset-btn').hide();
	
	$('#reset').hide();
	
	$(".statusId").change(function() {
	    var checked = $(this).is(':checked');
	    $(".statusId").prop('checked',false);
	    if(checked) {
	        $(this).prop('checked',true);
	         status=$(this).val();
	    }
	});
	$('.accordion-toggle').css( 'cursor', 'pointer' );
	
	$('#hide').hide();
	$('#myDropdown .dropdown-menu').on({
		"click":function(e){
	      e.stopPropagation();
	    }
	});
	
	$('#showColorHint').hide();
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
	
	/*$('#submitfile').click(function () {
	 var file = $('#file').prop("files")[0];

     $.ajax({
         url         : "/ndcmp/api/uploadFileValidation",
         data        : file,
         type        : 'POST',
         dataType    : 'json',
         timeout     : json_timeout,
         error       : function(){
             that.notifyGetDataError('error getting:');                  
         },
         success     : function(data){
             that.notifyGetDataSuccess();
         }
     });
	});*/
	
});

function submitfile(){
	var file = $('#file').prop("files")[0];
	var name = file.name;
	var na = name.split('.');
	if (na[1] == "xls"){
		 $.ajax({
		        url         : "/ndcmp/api/uploadFileValidation",
		        data        : file,
		        type        : 'POST',
		        enctype		: 'multipart/form-data',
		        processData: false, 
                contentType:false,
//		        dataType    : 'json',
//		        timeout     : json_timeout,
		        error       : function(){
		            that.notifyGetDataError('error getting:');                  
		        },
		        success     : function(data){
		            that.notifyGetDataSuccess();
		        }
		    });
	}
}

function resetFilterDiv(){
	$('#filterDiv').show();
	$('#reset-btn').hide();
	$('#filter-btn').show();
}

function loadFilterData(){
	allIds=[];
	
	var projectId=1;
	var rpYears = '';
	var checkboxes = document.getElementsByName('year');
	for (var i=0, n=checkboxes.length;i<n;i++) 
	{
	    if (checkboxes[i].checked) 
	    {
	    	rpYears += ","+checkboxes[i].value;
	    }
	}
	
	if($('#strategicPillarId').val() == ""){
		alert("Strategic Pillar is required");
		$('#strategicPillarId').focus();
		return false;
	}else if($('#themeId').val() == ""){
		alert("Theme is required");
		$('#themeId').focus();
		return false;
	}else if($('#outcomeId').val() == ""){
		alert("Outcome is required");
		$('#outcomeId').focus();
		return false;
	}else if($('#outputId').val() == ""){
		alert("Output is required");
		$('#outputId').focus();
		return false;
	}else{
		$('#save-plan').show();
		
		$('#reworkJobLevelStatus').fadeIn();
		$('#filterDiv').hide();
		$('#filter-btn').hide();
		$('#myDropdown').show();
		$('#reset-btn').show();
		$('#submitForReview').show();
		
		var filterHier={};
		filterHier={
				"strategicPillar" : $('#strategicPillarId').val(),
				"theme" : $('#themeId').val(),
				"outcome" : $('#outcomeId').val(),
				"output" : $('#outputId').val(),
				"status" : status,
		};

		$.ajax({
			url : '/ndcmp/api/getFilterHierarchy1',
			data : {
				projectId : projectId,
				filterHier : JSON.stringify(filterHier),
				rpYears : rpYears
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				
				//$('#legend_tooltip').show();
				
				outputDto=data;
				   var quaterArray=[];
				   var dataTable = new Array();
				   if(data.years != null){
				   $.each(data.years,function(yi,yv){
			    		if(data.reportingPeriods != 0){
			    			var reportingPeriods=data.reportingPeriods;
			    			$.each(reportingPeriods,function(ri,rv){
			    				var year=rv.year;
			    				if(year == yv){
			    					quaterArray.push('{"title" : "'+rv.name+'"}');
			    				}
			    			});
			    		}
				    });
				   }
				   var columnJson=eval('[{"COLUMNS":[{ "title": "#"}, { "title": "Key Activity","sWidth": "100px",},{"title" : "Sub Activity"},{"title":"Responsible Entity"},{"title" :"Partnering Agency"},{"title" :"MOV"},'+quaterArray+']}]');
				
				   if(data.outputDTOs.length != 0){
					
					if(data.outputDTOs[0].keyActivities.length == 0){
						 dataTable = new Array();
					    var arr=new Array();
					    arr.push("");
						arr.push("");
						arr.push("");
						arr.push("");
						dataTable.push(arr);
					}else{
						var outputDTOs=data.outputDTOs;
						dataTable = new Array();
						$.each(outputDTOs,function(oi,ov){
							if(ov.keyActivities.length != 0){
								var keyActivities=ov.keyActivities;
								$.each(keyActivities,function(ki,kv){
									if(kv.subActivities != null){
										if(kv.subActivities.length != 0){
											var arr = new Array();
											arr.push(kv.sequenceNumber);
											arr.push(kv.keyActivity);
											arr.push("");
											arr.push("");
											arr.push("");
											arr.push("");
											
											if(data.years != null){
												 $.each(data.years,function(yi,yv){
											    		if(data.reportingPeriods != 0){
											    			var reportingPeriods=data.reportingPeriods;
											    			$.each(reportingPeriods,function(ri,rv){
											    				arr.push("");
											    			});
											    		}
											    });
											}
											
											dataTable.push(arr);
											var subActivities=kv.subActivities;
											if(subActivities.length > 0){
												
												$.each(subActivities,function(si,sv){
													var arr = new Array();
													arr.push(sv.sequenceNumber);
													arr.push("");
													arr.push(sv.subActivity);
													arr.push(sv.leadAgency.agency);
													
													if(sv.leadAgency.partnerAgency.length == 0){
														arr.push("");
													}else{
														var agency="";
														$.each(sv.leadAgency.partnerAgency,function(ai,av){
															agency+=av.name+", ";
														});
														var sub=agency.substring(0, agency.length-1);
														agency.replace(/:sub/,"");
														
														arr.push(sub.substring(0,sub.length-1));
													}
													arr.push(sv.mov);
													 if(data.years != null){
													 $.each(data.years,function(yi,yv){
												    		if(data.reportingPeriods != 0){
												    			var reportingPeriods=data.reportingPeriods;
												    			$.each(reportingPeriods,function(ri,rv){
												    				var txtgetItemCount="";
												    				if(sv.approveORCompleteStatus == false){
												    					
												    					if(sv.completedReportingPeriod != null){
												    						if(rv.name == sv.completedReportingPeriod.name && rv.reportingStatus == "Closed"){
												    							txt="<div class='ck_"+rv.year+"_"+rv.name+"'><div class='checkbox checkbox-success checkbox-circle' id='chId-"+sv.id+"-"+rv.id+"'><input type='checkbox'  class='styled plan' onclick='getPlans(this)' name='plan' id='c"+sv.id+","+rv.id+"' value='"+sv.id+","+rv.id+"' disabled style='right: 7px;'><label style='padding-bottom:17px;left: -3px;opacity:1.65;' for='c"+sv.id+","+rv.id+"'></label></div></div>";
												    						}else{
												    							txt="<div class='ck_"+rv.year+"_"+rv.name+"'><div class='checkbox checkbox-danger' id='chId-"+sv.id+"-"+rv.id+"'><input type='checkbox'  class='styled plan' onclick='getPlans(this)' name='plan' id='c"+sv.id+","+rv.id+"' value='"+sv.id+","+rv.id+"' disabled style='right: 7px;'><label style='padding-bottom:17px;left: -3px;' for='c"+sv.id+","+rv.id+"'></label></div></div>";
												    						}
												    					}else{
												    						txt="<div class='ck_"+rv.year+"_"+rv.name+"'><div class='checkbox checkbox-primary' id='chId-"+sv.id+"-"+rv.id+"'><input type='checkbox'  class='styled plan' onclick='getPlans(this)' name='plan' id='c"+sv.id+","+rv.id+"' value='"+sv.id+","+rv.id+"' ><label style='padding-bottom:17px;left: -3px;' for='c"+sv.id+","+rv.id+"'></label></div></div>";
												    					}
												    					
												    				}else{
												    					if(sv.completedReportingPeriod != null){
												    						if(rv.name == sv.completedReportingPeriod.name && rv.year == sv.completedReportingPeriod.year){
												    							if(rv.reportingStatus == "Closed" || rv.reportingStatus == "Open" ){
												    								txt="<div class='ck_"+rv.year+"_"+rv.name+"'><div class='checkbox checkbox-success checkbox-circle' id='chId-"+sv.id+"-"+rv.id+"'><input type='checkbox'  class='styled plan' onclick='getPlans(this)' name='plan' id='c"+sv.id+","+rv.id+"' value='"+sv.id+","+rv.id+"' disabled style='right: 7px;'><label style='padding-bottom:17px;left: -3px;opacity:1.65;' for='c"+sv.id+","+rv.id+"'></label></div></div>";
												    							}
												    						}else{
												    							txt="<div class='ck_"+rv.year+"_"+rv.name+"'><div class='checkbox checkbox-danger' id='chId-"+sv.id+"-"+rv.id+"'><input type='checkbox'  class='styled plan' onclick='getPlans(this)' name='plan' id='c"+sv.id+","+rv.id+"' value='"+sv.id+","+rv.id+"' disabled style='right: 7px;'><label style='padding-bottom:17px;left: -3px;' for='c"+sv.id+","+rv.id+"'></label></div></div>";
												    						}
												    					}else{
												    						txt="<div class='ck_"+rv.year+"_"+rv.name+"'><div class='checkbox checkbox-danger' id='chId-"+sv.id+"-"+rv.id+"'><input type='checkbox'  class='styled plan' onclick='getPlans(this)' name='plan' id='c"+sv.id+","+rv.id+"' value='"+sv.id+","+rv.id+"' disabled style='right: 7px;'><label style='padding-bottom:17px;left: -3px;' for='c"+sv.id+","+rv.id+"'></label></div></div>";
												    					}
												    				}
												    				arr.push(txt);
												    			});
												    		}
												    	});
													 }
													dataTable.push(arr);
												});
											}
											
										}
										}
								});
							}
						});
					}
				   $('#filterTable thead').empty();
				   $('#filterTable tbody').empty();
				   $('#filterTable tr').empty();
				   $('#filterTable').empty();
				    table = $('#filterTable').DataTable( {
				    	"bDestroy": true,
				    	"bPaginate" : false,
				    	"bFilter" : false,
				    	"bInfo" :false,
				    	"bSort" : false,
				    	"bLengthChange": false,
						"aoColumns" : columnJson[0].COLUMNS
				    } );
				}
	            table.rows.add(dataTable);
	            table.draw();
	            var txt="";
	            var colSpanCount=0;
	            var yearArray=[];
	            txt+="<tr role='row'><th></th><th style='width: 60%;'></th><th style='width: 60%;'></th><th id='th3'></th><th id='th4'></th><th id='th5'></th>";
	            if(data.years != null){
	            	$.each(data.reportingPeriods,function(ri,rv){
	            		yearArray.push(rv.year);
	            	});
	            	var obj = { };
	            	for (var i = 0, j = yearArray.length; i < j; i++) {
	            		   obj[yearArray[i]] = (obj[yearArray[i]] || 0) + 1;
	            	}
	            	
	            	 $.each(data.years,function(yi,yv){
			    		 txt+="<th colspan='"+obj[yv]+"' style='text-align:center'>"+yv+"</th>";
		            });	
	            }
	            txt+="</tr>";
	            $(txt).insertBefore($('#filterTable thead tr:eq(0) '));
	            $('#filterTable thead tr:eq(1) th').css('padding-bottom','26px');
	            $('#reworkJobLevelStatus').fadeOut();
	            
	    		//To remove Closed Quarter
			}
		});
		
		$('#filterTable').css('width','100%');
		
		getAllPlanning();
		
		
		if(outputDto.reportingPeriods != null){
    		if(outputDto.reportingPeriods != 0){
    			var reportingPeriods=outputDto.reportingPeriods;
    			$.each(reportingPeriods,function(ri,rv){
    				if(rv.reportingStatus == "Closed"){
    					var c=$('.ck_'+rv.year+"_"+rv.name);
    					
    					$.each(c,function(ci,cv){
    						/*var cId=$('.'+cv.className).find(':checkbox');
    						$.each(cId,function(chi,chv){
    							$('input[id="'+chv.id+'"]').prop("disabled", true);
    						});*/
    						var cId=$('.'+cv.className).find(':checkbox:not(:disabled)').parent();
    						$.each(cId,function(chi,chv){
    							$('#'+chv.id).remove();
    							//$('input[id="'+chv.id+'"]').prop("disabled", true);
    						});
    					});
    				}
    			});
    		}
		}
		
		var outputDTOs=outputDto.outputDTOs;
		$.each(outputDTOs,function(oi,ov){
			if(ov.keyActivities.length != 0){
				var keyActivities=ov.keyActivities;
				$.each(keyActivities,function(ki,kv){
					var subActivities=kv.subActivities;
					if(subActivities.length > 0){
						
						$.each(subActivities,function(si,sv){
							if(sv.approveORCompleteStatus == true){
								if($('#filterTable tbody tr:contains('+sv.sequenceNumber+')')){
									$('#filterTable td:contains('+sv.sequenceNumber+')').closest('tr').css('background-color','#a4d083');
									var unChecked=$('#filterTable tbody td:contains("'+sv.sequenceNumber+'")').closest('tr').find(':checkbox:not(:checked)').parent();
									$.each(unChecked,function(unc,unv){
										var checkboxElement = $('#'+unv.id);
										if(checkboxElement[0].className == 'checkbox checkbox-danger'){
											$('#'+unv.id).remove();
										}else{
											var completedCheckbox = $('#'+unv.id)[0].children[0];
											var colorDiv = $('#'+unv.id)[0];
											completedCheckbox.setAttribute('checked',true);
											colorDiv.setAttribute('class', 'checkbox checkbox-danger checkbox-circle');
										}
									});
									
									var successCheckbox=$('#filterTable tbody td:contains("'+sv.sequenceNumber+'")').closest('tr').find(':checkbox(:checked)').parent();
									
									var count=0;
									var classOfCheckbox=[];
									var idOfCheckbox=[];
									
									if(successCheckbox != null){
										$.each(successCheckbox,function(ci,cv){
											classOfCheckbox.push(cv.className);
											if(cv.className == "checkbox-success checkbox-circle checkbox checkbox-danger" || cv.className == "checkbox checkbox-danger checkbox-circle"){
												count++;
											}else{
												idOfCheckbox.push(cv.id);
											}
										});
									}
									
									if(count == 1){
										var checkBoxObj=countData(classOfCheckbox);
										var indexValue="";
										
										if(checkBoxObj != null){
											if(checkBoxObj["checkbox-success checkbox-circle checkbox checkbox-danger"] == 1){
												indexValue="checkbox-success checkbox-circle checkbox checkbox-danger";
											}else if(checkBoxObj["checkbox checkbox-danger checkbox-circle"] == 1){
												indexValue="checkbox checkbox-danger checkbox-circle";
											}
										}
										
										var idvSuccess=classOfCheckbox.indexOf(indexValue);
										
										for(var i=idvSuccess;i<idOfCheckbox.length;i++){
											$('#'+idOfCheckbox[i]).html('<img src="/ndcmp/static/img/checkbox.png" height="20px" width="20px">');
											$('#'+idOfCheckbox[i]).css({'left':'-11px','top' : '4px'});
										}
									}
								}
							}
						});
					}
				});
			}
		});
		
		return true;
	}
}

/**
 * @author prem
 * Loading Strategic Pillars in partner filter
 * @param statusId
 */
function loadPillars(){
	var reportingPeriodId = $('#yearId').val();
	var status = $('#statusId').val();
	
	if (status == undefined || status == '') {
		$('#themeId').html('<option value="">---Select Theme---</option>');
		$('#outcomeId').html('<option value="">---Select Outcome---</option>');
		$('#outputId').html('<option value="">---Select Output---</option>');
		$('#strategicPillarId').html('<option value="">---Select StrategicPillar---</option>');
		
	} else if (reportingPeriodId != undefined && reportingPeriodId != '' && status != undefined &&status != '') {
		$.ajax({
			url : '/ndcmp/api/getPartnerStrategicPillar',
			data : {
				reportingPeriodId : reportingPeriodId,
				status : status
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				if (data.length > 0) {
					var themesbasedonstrategic = '<option value="">---Select StrategicPillar---</option>';
					var strategicpillers = data;
					for (var i = 0; i < strategicpillers.length; i++) {
						if (i == 0) {
							themesbasedonstrategic += '<option value="'
								+ strategicpillers[i].id + '">'
								+ strategicpillers[i].sequenceNumber + '. '
								+ strategicpillers[i].name
								+ '</option>';
						} else {
							themesbasedonstrategic += '<option value="'
								+ strategicpillers[i].id + '">'
								+ strategicpillers[i].sequenceNumber + '. '
								+ strategicpillers[i].name
								+ '</option>';	
						}
						
					}
					$('#strategicPillarId').html(themesbasedonstrategic);
					$("select#strategicPillarId").prop('selectedIndex', 1);
					 
					getThemesByStrategicStatusReport(strategicpillers[0].id);
					
				}else{
					var themesbasedonstrategic = '<option value="">---Select StrategicPillar---</option>';
					$('#strategicPillarId').html(themesbasedonstrategic);
					$('#themeId').html('<option value="">---Select Theme---</option>');
					$('#outcomeId').html('<option value="">---Select Outcome---</option>');
					$('#outputId').html('<option value="">---Select Output---</option>');
				}
			}
		});	
	}
}

function loadStatusForStatusUpdator(){
	var reportingPeriodId = $('#yearId').val();
	if (reportingPeriodId != undefined && reportingPeriodId != '') {
		$.ajax({
			url : '/ndcmp/api/loadStatusForStatusUpdator',
			data : {
				reportingPeriodId : reportingPeriodId
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				var statusValue = '<option value="">---Select Status---</option>';
				if (data > 0) {
					if (data == 1) {
						statusValue += '<option value="all" selected="selected">All</option>' +
										'<option value="readyForReview">Ready for Review</option>' +
										'<option value="notReadyForReview">Not Ready for Review</option>' ;
					}else if (data == 2) {
						statusValue += '<option value="all" selected="selected">All</option>' + 
										'<option value="sentForReview" selected="selected">Sent for Review</option>';
						
					}else{
						statusValue += '<option value="all" selected="selected">All</option>' +
									'<option value="sentForReview">Sent for Review</option>' +
									'<option value="needMoreInfo">Need More Information</option>' +
									'<option value="readyForReview">Ready for Review</option>';
					}	
				}
				$('#statusId').html(statusValue);
				loadPillars();
			}
		});	
	} else {
		$('#statusId').html('<option value="">---Select Status---</option>');
		$('#themeId').html('<option value="">---Select Theme---</option>');
		$('#outcomeId').html('<option value="">---Select Outcome---</option>');
		$('#outputId').html('<option value="">---Select Output---</option>');
		$('#strategicPillarId').html('<option value="">---Select StrategicPillar---</option>');
	}
	
}

/**
 * @author prem
 * Loading Strategic Pillars in partner filter
 * @param statusId
 */
function loadReportPillars(statusId){
	var reportingPeriodId = $('#yearId').val();
	$.ajax({
		url : '/ndcmp/api/getReportStrategicPillar',
		data : {
			reportingPeriodId : reportingPeriodId
		},
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data) {
			
			if (data.length > 0) {
				var themesbasedonstrategic = '<option value="">---Select Strategic Pillar---</option>';
				var themes = data;
				for (var i = 0; i < themes.length; i++) {
					themesbasedonstrategic += '<option value="'
							+ themes[i].id + '">'
							+ themes[i].name
							+ '</option>';
				}
				
				$('#strategicPillarId').html(themesbasedonstrategic);
			}else{
				var themesbasedonstrategic = '<option value="">---Select StrategicPillar---</option>';
				$('#strategicPillarId').html(themesbasedonstrategic);
			}
		}
	});
}

/*
 * Load All Themes based on Strategic
 */
function getThemesByStrategic(slectedStrategicPiller){
	var strategicId = slectedStrategicPiller.value;
	if(strategicId == "" || strategicId == null){
		$('#themeId').html('<option value="">---Select Theme ---</option>');
		$('#outcomeId').html('<option value="">---Select Outcome ---</option>');
		$('#outputId').html('<option value="">---Select Output ---</option>');
		$('#leadAgencyId').html('<option value="">---Select---</option>');
	}else{
		$.ajax({
			url : '/ndcmp/api/getThemesByStrategic',
			data : {
				strategicId : strategicId
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				if (data.length > 0) {
					var themesbasedonstrategic = '<option value="">---Select Theme---</option>';
					var themes = data;
					for (var i = 0; i < themes.length; i++) {
						themesbasedonstrategic += '<option value="'
								+ themes[i].id + '">'
								+ themes[i].name
								+ '</option>';
					}
					$('#themeId').html(themesbasedonstrategic);
					 $("select#themeId").prop('selectedIndex', 1);
					 
					 var th={
							 value :themes[0].id
					 }
					 getOutcomesByTheme(th);
					
					//$('#outcomeId').html('<option value="">---Select Outcome---</option>');
					//$('#outputId').html('<option value="">---Select Output---</option>');
					$('#leadAgencyId').html('<option value="">---Select---</option>');
				}else{
					$('#themeId').html('<option value="">---Select Theme---</option>');
					$('#outcomeId').html('<option value="">---Select Outcome---</option>');
					$('#outputId').html('<option value="">---Select Output---</option>');
					$('#leadAgencyId').html('<option value="">---Select---</option>');
				}
			}
		});
	}
}

/*function getStatusByYear(){
	$.ajax({
		url : '/ndcmp/api/getStatusByYear',
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data) {
			if (data.length > 0) {
				var themesbasedonstrategic = '<option value="">---Select Status ---</option>';
				themesbasedonstrategic += '<option value="">---All ---</option>';
				var themes = data;
				for (var i = 0; i < themes.length; i++) {
					themesbasedonstrategic += '<option value="'
							+ themes[i].id + '">'
							+ themes[i].name
							+ '</option>';
				}
				$('#statusId').html(themesbasedonstrategic);
			}
		}
	});
}*/

function getThemesByStrategicStatusReport(strategicId){
	var reportingPeriodId = $('#yearId').val();
	var strategicIdVal = strategicId;
	var status = $('#statusId').val();
	if (strategicId== undefined || strategicId== '') {
		$('#themeId').html('<option value="">---Select Theme---</option>');
		$('#outcomeId').html('<option value="">---Select Outcome---</option>');
		$('#outputId').html('<option value="">---Select Output---</option>');
	}
	else if (reportingPeriodId != undefined && reportingPeriodId != '' &&
			strategicIdVal != undefined && strategicIdVal != '' &&
			status != undefined && status != '') {
		$.ajax({
			url : '/ndcmp/api/getThemesByStrategicStatusReport',
			data : {
				reportingPeriodId : reportingPeriodId,
				strategicId : strategicIdVal,
				status : status
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				if (data.length > 0) {
					var themesbasedonstrategic = '<option value="">---Select Theme---</option>';
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
					getStatusUpdatorOutcomesByTheme(themes[0].id);
				} else {
					$('#outcomeId').html('<option value="">---Select Outcome---</option>');
					$('#outputId').html('<option value="">---Select Output---</option>');
				}
			}
		});	
	}
}

function getThemesByStrategicReport(strategicId){
	var reportingPeriodId = $('#yearId').val();
	var strategicIdVal = strategicId.value;
	$.ajax({
		url : '/ndcmp/api/getThemesByStrategicReport',
		data : {
			reportingPeriodId : reportingPeriodId,
			strategicId : strategicIdVal
		},
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data) {
			
			if (data.length > 0) {
				var themesbasedonstrategic = '<option value="">---Select Theme---</option>';
				var themes = data;
				for (var i = 0; i < themes.length; i++) {
					themesbasedonstrategic += '<option value="'
							+ themes[i].id + '">'
							+ themes[i].name
							+ '</option>';
				}
				$('#themeId').html(themesbasedonstrategic);
			}
		}
	});
}


function getOutcomeByStrategicStatusReportTheme(selectedThemeId){
	var reportingPeriodId = $('#yearId').val();
	var themeId = selectedThemeId.value;
	$.ajax({
		url : '/ndcmp/api/getOutcomeByStrategicStatusReportTheme',
		data : {
			reportingPeriodId : reportingPeriodId,
			themeId : themeId
		},
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data) {
			
			if (data.length > 0) {
				var themesbasedonstrategic = '<option value="">---Select Outcome---</option>';
				var themes = data;
				for (var i = 0; i < themes.length; i++) {
					themesbasedonstrategic += '<option value="'
							+ themes[i].id + '">'
							+ themes[i].name
							+ '</option>';
				}
			
				$('#outcomeId').html(themesbasedonstrategic);
			}
		}
	});
}

function getOutputByStrategicStatusReportThemeOutcome(selectedOutcomeId){
	var reportingPeriodId = $('#yearId').val();
	var outcomeId = selectedOutcomeId.value;
	$.ajax({
		url : '/ndcmp/api/getOutputByStrategicStatusReportThemeOutcome',
		data : {
			reportingPeriodId : reportingPeriodId,
			outcomeId : outcomeId
		},
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data) {
			
			if (data.length > 0) {
				var themesbasedonstrategic = '<option value="">---Select Theme---</option>';
				var themes = data;
				for (var i = 0; i < themes.length; i++) {
					themesbasedonstrategic += '<option value="'
							+ themes[i].id + '">'
							+ themes[i].name
							+ '</option>';
				}
			
				$('#outputId').html(themesbasedonstrategic);
			}
		}
	});
}

/*
 * Load All outcomes based on theme and Reporting period --> Status Updator
 */
function getStatusUpdatorOutcomesByTheme(slectedTheme){
	var themeId = slectedTheme;
	var reportingPeriodId = $('#yearId').val();
	var status = $('#statusId').val();
	
	if(themeId == undefined || themeId == ""){
		$('#outcomeId').html('<option value="">---Select Outcome---</option>');
		$('#outputId').html('<option value="">---Select Output---</option>');
		$('#leadAgencyId').html('<option value="">---Select---</option>');
	}else{
		$.ajax({
			url : '/ndcmp/api/getOutcomeByStrategicStatusReportTheme',
			data : {
				reportingPeriodId : reportingPeriodId,
				themeId : themeId,
				status : status
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				if (data != null) {
					if (data.length > 0) {
						var outcomebasedontheme = '<option value="">---Select Outcome---</option>';
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
						getStatusUpdatorOutputsByoutcome(outcomes[0].id);
					} else {
						$('#outcomeId').html('<option value="">---Select Outcome---</option>');
						$('#outputId').html('<option value="">---Select Output---</option>');	
					}
					
					
				}
			}
		});
	}
	
}

function showFilter(){
	$('.filterHide').show();
	$('#hide').hide();
}
function resetFuncion(){
	$('#partnerDiv').show();
	$('#reset').hide();
	$('#getFilterHierarchy').show();
	loadPillars();
}


function loadKeyActivity(){
	
	var reportingPeriodId = $('#yearId').val();
	var status = $('#statusId').val();
	var strategicIdVal = $('#strategicPillarId').val();
	var themeId = $('#themeId').val();
	var outcomeId = $('#outcomeId').val();
	var outputId = $('#outputId').val();
	if (reportingPeriodId != undefined && reportingPeriodId != '' &&
			strategicIdVal != undefined && strategicIdVal != '' &&
			status != undefined && status != '' &&
			themeId != undefined && themeId != '' &&
			outcomeId != undefined && outcomeId != '' &&
			outputId != undefined && outputId != '') {
		$('#partnerDiv').hide();
		$('#getFilterHierarchy').hide();
		$('#reset').show();
		$('#submitForReview').show();
		$('#queues-accordion').empty();	
		var serialized = $("#filteringForm").serialize();
		$.ajax({
			url : '/ndcmp/api/getFilterPartner',
			data: serialized,
			type : 'GET',
			async : false,
			dataType : 'json',
			contentType: "application/json",
			success : function(data){
				if(data != null && data.length != 0){
					//for (var i = 0; i < data.length; i++) {
						var modalBody = '<div class="panel-group accordion" id="collapseOne">';
						for(var i = 0;i < data.length; i++){
							/*$('.filterHide').hide();
							$('#hide').show();*/
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
									modalBody += '<div onclick="statusUpdater('+ subActivities[j].id +', this)" style="width:100%;height:35px;border: 1px solid #e4d6d6;background-color: #CE7173;cursor: pointer;padding: 5px;"><span>'
									+ subActivities[j].sequenceNumber+' '+ subActivities[j].subActivity + '</span>' + 
									'<div class="pull-right" style="width:22px; height:22px; border: 1px solid; margin-right:12px; border-radius: 5px; background-color: '+ subActivities[j].colorCode +'"></div>' +
									'<span class="pull-right" style="width:22px;height: 22px;margin-right:5px;text-align: center;"><i style="margin-top:5px;" class="'+ subActivities[j].statusIcon +'"></i></span>';
								}else{
									modalBody += '<div onclick="statusUpdater('+ subActivities[j].id +', this)" style="width:100%;height:35px;border: 1px solid #e4d6d6;background-color: #f3bcb7;cursor: pointer;padding: 5px;"><span>'
									+ subActivities[j].sequenceNumber+' '+ subActivities[j].subActivity + '</span>' + 
									'<div class="pull-right" style="width:22px; height:22px; border: 1px solid; margin-right:12px; border-radius: 5px; background-color: '+ subActivities[j].colorCode +'"></div>' +
									'<span class="pull-right" style="width:22px;height: 22px;margin-right:5px;text-align: center;"><i style="margin-top:5px;" class="'+ subActivities[j].statusIcon +'"></i></span>';
								}
								
								modalBody += '</div>';
							}
							modalBody += '</div></div></div></div>';		  
						}
						modalBody += '</div>';
						
						$('#queues-accordion').html(modalBody);
						$('#queues-accordion').show();
					//}
				}else{
					alert("Record not found.."); 
					$('#partnerDiv').show();
					$('#reset').hide();
					$('#getFilterHierarchy').show();
					
				}
			},
		});
	}else{
		if(reportingPeriodId == ""){
			$('#reportingValidationErrorMsg').show();
			$('#statusUpdaterFilerValidationErrorModelWindow').modal('show');
			return false;
		}else{
			$('#reportingValidationErrorMsg').hide();
		}
		
		if (status == "") {
			$('#statusValidationErrorMsg').show();
			$('#statusUpdaterFilerValidationErrorModelWindow').modal('show');
			return false;
		}else{
			$('#statusValidationErrorMsg').hide();
		}
		
		if (strategicIdVal == "") {
			$('#strategicValidationErrorMsg').show();
			$('#statusUpdaterFilerValidationErrorModelWindow').modal('show');
			return false;
		}else{
			$('#strategicValidationErrorMsg').hide();
		}
		
		if (themeId == "") {
			$('#themeValidationErrorMsg').show();
			$('#statusUpdaterFilerValidationErrorModelWindow').modal('show');
			return false;
		}else{
			$('#themeValidationErrorMsg').hide();
		}
		
		if (outcomeId == "") {
			$('#outcomeValidationErrorMsg').show();
			$('#statusUpdaterFilerValidationErrorModelWindow').modal('show');
			return false;
		}else{
			$('#outcomeValidationErrorMsg').hide();
		}
		
		if (outputId == "") {
			$('#outputValidationErrorMsg').show();
			$('#statusUpdaterFilerValidationErrorModelWindow').modal('show');
			return false;
		}else{
			$('#outputValidationErrorMsg').hide();
		}
	}
}

/*
 * Load All outputs based on outcome
 */
function getStatusUpdatorOutputsByoutcome(slectedOutcome){
	var outcomeId = slectedOutcome;
	var reportingPeriodId = $('#yearId').val();
	var status = $('#statusId').val();
	if(outcomeId == undefined || outcomeId == ""){
		$('#outputId').html('<option value="">---Select Output ---</option>');
		$('#leadAgencyId').html('<option value="">---Select---</option>');
	}else{
		$.ajax({
			url : '/ndcmp/api/getOutputByStrategicStatusReportThemeOutcome',
			data : {
				reportingPeriodId : reportingPeriodId,
				outcomeId : outcomeId,
				status : status
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				if (data != null) {
					var outputbasedonoutcome = '<option value="">---Select Output---</option>';
					var outputs = data;
					for (var i = 0; i < outputs.length; i++) {
						if (i==0) {
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
				} else {
					var outputbasedonoutcome = '<option value="">---Select Output---</option>';
					$('#outputId').html(outputbasedonoutcome);
				}
			}
		});
	}
}

/*
 * Get lead agency based on the output
 */
function getLeadAgencyByoutput(selected){
	var outputId = selected.value;
	if(outputId == null || outputId == ""){
		$('#leadAgencyId').html('<option value="">---Select---</option>');
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
					$('#leadAgencyId').html(agency);
				}
			}
		});
	}
}

function getPartnerAgencyByoutput(selected){
	var outputId = selected.value;
	if(outputId == null || outputId == ""){
		$('#partnerAgencyId').html('<option value="">---Select---</option>');
	}else{
		$.ajax({
			url : '/ndcmp/api/getPartnerAgencyByoutput',
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
					$('#leadAgencyId').html(agency);
				}
			}
		});
	}
}

function getStrategicPillarByQuarter(selectedQuarter){
	var quarterId = selectedQuarter.value;
	var yearId = $("#yearId").val();
	$.ajax({
		url : '/ndcmp/api/getStrategicPillarByQuarter',
		data : {
			quarterId : quarterId,
			yearId : yearId
		},
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data) {
			if (data != null) {
				var pillarsBasedOnQuarters = '<option value="All">All</option>';
				var pillars = data;
				for (var i = 0; i < pillars.length; i++) {
					pillarsBasedOnQuarters += '<option value="'
							+ pillars[i].id + '">'
							+ pillars[i].name
							+ '</option>';
				}
				$('#strategicPillarId').html(pillarsBasedOnQuarters);
			}
		}
	});
}


function getResponsibleEntityByOutput(output){
	var outputId = output.value;
	$.ajax({
		url : '/ndcmp/api/getResponsibleEntityByOutput',
		data : {
			outputId : outputId
		},
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data){
			if (data != null) {
				var responsibleEntitybasedonoutput = '<option value="">---Select Responsible Entity---</option>';
				var responsibleEntitys = data;
				for (var i = 0; i < responsibleEntitys.length; i++) {
					responsibleEntitybasedonoutput += '<option value="'
							+ responsibleEntitys[i].id + '">'
							+ responsibleEntitys[i].name
							+ '</option>';
				}
				$('#responseEntityId').html(responsibleEntitybasedonoutput);
			} else {
				var responsibleEntitybasedonoutput = '<option value="">---Select Responsible Entity---</option>';
				$('#responseEntityId').html(responsibleEntitybasedonoutput);
			}
		}
	});
}

function saveAndReadyForReview() {
	var complete=true;
	var colorParcent = $('#colorPercent').val();
	var percent = $('#percent').val();
	if (colorParcent == 0) {
		percent = "#FFFFFF";
	}
	var statusTracking={
			"id" : $('#id').val(),
			"subActivity.id" : $('#subActivityId').val(),
			"actualStatusColor" : percent,
			"actualStatusPercentage" : colorParcent,
			"keyProgress" : $('#keyProgress').val(),
			"reasonForGap" : $('#reasonForGap').val(),
			"rectifyTheGap" : $('#rectifyTheGap').val(),
			"reportingPeriodId" : $('#yearId').val(),
			"complete" :complete,
	};
	$.ajax({
		url : '/ndcmp/api/saveSubActivityStatus',
		data :{
			"statusTracking" : JSON.stringify(statusTracking),
			"subActivityId" : $('#subActivityId').val()
		},
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data) {
			if (data != null) {
				
				if (selectedSubActivityForDataCapturePA != null && selectedSubActivityForDataCapturePA.childElementCount == 3) {
					var colorDiv = selectedSubActivityForDataCapturePA.children[1];
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
					var statusIconDiv = selectedSubActivityForDataCapturePA.children[2].children[0];
					statusIconDiv.setAttribute('class', statusIcon);
				}
			}
			$('#statusModal').modal('hide');
			
		}
	});
}

function saveStatus(){
	var complete=false;
	var colorParcent = $('#colorPercent').val();
	var percent = $('#percent').val();
	if (colorParcent == 0) {
		percent = "#FFFFFF";
	}
	var statusTracking={
			"id" : $('#id').val(),
			"subActivity.id" : $('#subActivityId').val(),
			"actualStatusColor" : percent,
			"actualStatusPercentage" : colorParcent,
			"keyProgress" : $('#keyProgress').val(),
			"reasonForGap" : $('#reasonForGap').val(),
			"rectifyTheGap" : $('#rectifyTheGap').val(),
			"reportingPeriodId" : $('#yearId').val(),
			"complete" :complete,
	};
	$.ajax({
		url : '/ndcmp/api/saveSubActivityStatus',
		data :{
			"statusTracking" : JSON.stringify(statusTracking),
			"subActivityId" : $('#subActivityId').val()
		},
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data) {
			if (data != null) {
				var statusIcon = "";
				
				if (data.reworkRequired && !data.complete) {
						statusIcon = "fa fa-info-circle";
				} else if (data.complete && data.reviewStatus == 1){
						statusIcon = "fa fa-check-square-o";
				} else if (data.complete) {
						statusIcon = "fa fa-external-link-square";
				}
				if (selectedSubActivityForDataCapturePA != null && selectedSubActivityForDataCapturePA.childElementCount == 3) {
					var colorDiv = selectedSubActivityForDataCapturePA.children[1];
					var colorDivStyle = colorDiv.style;
					
					colorDivStyle.setProperty('background-color', percent, 'important'); 
					
					var statusIconDiv = selectedSubActivityForDataCapturePA.children[2].children[0];
					statusIconDiv.setAttribute('class', statusIcon);
			}
			}
			$('#statusModal').modal('hide');
			
		}
	});
}

function saveSubmitForReview(){
	var reportingPeriodId = $("#yearId").val();
	 $.ajax({
			url : '/ndcmp/api/createSubmitForReview',
			data :{
				"reportingPeriodId" : reportingPeriodId
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				
			}
	 });
}


function statusUpdater(id, element){
	selectedSubActivityForDataCapturePA = element;
	var reportingPeriodId = $("#yearId").val();
	 $.ajax({
			url : '/ndcmp/api/getSubactivityStatusById',
			data :{
				"subActId" : id,
				"reportingPeriodId" : reportingPeriodId
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				if(data.id != null){
					$('#id').val(data.id);
					$('#subActivityId').val(data.subActivity.id);
					$('#percent').val(data.actualStatusColor);
					$('#colorPercent').val(data.actualStatusPercentage);
					$('#keyProgress').val(data.keyProgress);
					$('#reasonForGap').val(data.reasonForGap);
					$('#rectifyTheGap').val(data.rectifyTheGap);
					$('#subName').html(data.subActivity.sequenceNumber + ". " + data.subActivity.name);
					$('#carriedFromPreviousRPDiv').hide();
					if (data.user != null) {
						$('#lastUpdatedBy').show();
						$('#partnerName').html(data.user.firstName + " " + data.user.lastName);
					} else {
						$('#lastUpdatedBy').hide();
					}
					
					if(data.complete == true){
						$('#complete').val(1);
						$('#complete').attr('checked',true);
						$('#complete').attr("disabled",true);
						$("#statusTrackingform").find("textarea,input").attr("disabled", "disabled");
						$('#colorPercent').removeAttr('disabled');
						$('.showHint').removeAttr('disabled');
						$('.bar').css('pointer-events','none');
						$('#save-statusTracking').hide();
						$('#readyforreview-statusTracking').hide();
					} else  {
						$('#complete').attr('checked',false);
						$("#statusTrackingform").find("textarea,input").removeAttr('disabled');
						$('#save-statusTracking').show();
						$('#readyforreview-statusTracking').show();
						$('.bar').css('pointer-events','auto');
					}
					
					if (data.reworkRequired == true) {
						$('#reviewedComment').show();
						$('#reviewerFeedback').html(data.reviewerFeedback);
					} else {
						$('#reviewedComment').hide();
					}
					
				}else{
					$('#id').val(0);
					$('#subActivityId').val(data.subActivity.id);
					$('#subName').html(data.subActivity.sequenceNumber + ". " + data.subActivity.name);
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
						$('#lastUpdatedBy').show();
						$('#partnerName').html(data.user.firstName + " " + data.user.lastName);
					} else {
						$('#lastUpdatedBy').hide();
					}
					
					if (data.carriedFrom != null && data.carriedFrom != '') {
						$('#carriedFromPreviousRPDiv').show();
						$('#carriedFromPreviousRP').html(data.carriedFrom);
					} else {
						$('#carriedFromPreviousRPDiv').hide();
					}
					
					$('#save-statusTracking').show();
					$('#readyforreview-statusTracking').show();
					$('#complete').attr('checked',false);
					$("#statusTrackingform").find("textarea,input").removeAttr('disabled');
					$('#save-statusTracking').removeAttr("disabled");
					$('.bar').css('pointer-events','auto');
					$('#reviewedComment').hide();
				}
				if(data.statuss != null){
					$('#colorPercent').val(data.statuss.colorPercent);
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
				
				$("input.onchange-example").ColorPickerSliders({
					previewontriggerelement : true,
					flat : false,
					color : '#cf966f',
					customswatches : false,
					swatches : ['#DDA6A1', '#E77B70', '#F14D41', '#F66A37','#F89A3A','#FECB3A','#F3E83B','#CDDA42','#ABD045','#9FCA47' ],
					order : {
						hsl : 1
					}
				});
				
				$('#statusModal').modal('show');
			}
		});
	
}

function proceedSubmitForReview(){
	$('#proceedCheckSubmitModal').modal('show');
}

function submitForReview(){
	var reportingPeriodId = $('#yearId').val();
	if(reportingPeriodId != null && reportingPeriodId != ''){
		$.ajax({
			url : '/ndcmp/api/checkSubmit',
			data :{
				"reportingPeriodId" : reportingPeriodId
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				
				if(data != null && data.remaining != null){
					var string = data;
					if(string.remaining > 0){
						var msg = string.completed + " out of " + string.total + " activities have been completed.";
						if (string.total == 1) {
							msg = string.completed + " out of " + string.total + " activity have been completed.";
						}
						if (string.remaining == 1){
							msg += " Please complete pending " +  string.remaining + " activity to be sent for next level review.";
						} else {
							msg += " Please complete pending " +  string.remaining + " activities to be sent for next level review.";
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
					var msg = "There is no activity pending to be sent for review...";
					$('#submitReviewMessage2').html(msg);
					$('#submitReviewMessage').hide();
					$('#submitReviewMessage2').show();
					$('#checkSubmitModal').modal('show');
				}
			}
		});
	} else{
		$('#statusUpdaterReportingValidationErrorMsg').show();
		$('#statusUpdatervalidationErrorModelWindow').modal('show');
		return false;
	}
	
}

function submitFilterForm() {
	$('#filteringForm')
			.formValidation(
					{
						icon : {
							valid : 'glyphicon glyphicon-ok',
							invalid : 'glyphicon glyphicon-remove',
							validating : 'glyphicon glyphicon-refresh'
						},
						fields : {
							status : {
								validators : {
									notEmpty : {
										message : 'Status is Required'
									}
								}
							},
							strategicPillar : {
								validators : {
									notEmpty : {
										message : 'StrategicPillar is Required'
									}
								}
							},
							outcome : {
								validators : {
									notEmpty : {
										message : 'Outcome is Required'
									}
								}
							},
							theme : {
								validators : {
									notEmpty : {
										message : 'Theme is Required'
									}
								}
							},
							output : {
								validators : {
									notEmpty : {
										message : 'Output is Required'
									}
								}
							}
						}
					});

	return true;
};
function getPlans(t){
	
	if (t.checked == true){
		   var checkBoxValue=t.value;
		   var temp=0;
		  
		   if(checkedIds.length != 0){
			   for(var i=0;i<checkedIds.length;i++){
				   if(checkBoxValue == checkedIds[i]){
					   allIds.remove(checkBoxValue);
					   temp=0;
					   break;
				   }else{
					   temp=1;
				   }
			   }
			   if(temp == 1){
				   allIds.push(checkBoxValue);
			   }
		   }else{
			   allIds.push(checkBoxValue);
		   }
		}
	   else{
		   var checkBoxValue=t.value;
		   allIds.remove(checkBoxValue);
		   if(checkedIds.length != 0){
			   for(var i=0;i<checkedIds.length;i++){
				   if(checkBoxValue == checkedIds[i]){
					   deleteId.push(checkBoxValue);
					   temp=0;
					   break;
				   }
			   }
		   }
	   }
}

function savePlan(){
	
	allIds.push.apply(allIds, deleteId);
	$.each(allIds,function(ai,av){
		var splitId=av.split(',');
		subActivityIds.push(splitId[0]);
		reportingPeriodIds.push(splitId[1]);
	});
	var dataJson={
			"subActivityIds" : subActivityIds,
			"reportingPeriodIds" : reportingPeriodIds
	};
	if(subActivityIds.length != 0){
	  $.ajax({
			url : '/ndcmp/api/addPlan',
			data :{
				"idValues" : JSON.stringify(dataJson)
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
			}
		});
	 
	  	loadFilterData();
	}else{
		alert("Choose any planning");
	}
}

function getAllPlanning(){
	$.ajax({
		url : '/ndcmp/api/getAllPlanning',
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data) {
			var checkBoxData=data;
			checkedIds=data;
			$.each(checkBoxData,function(cbi,cbv){
				$('input[value="'+cbv+'"]').prop("checked", true);
				$('input[value="'+cbv+'"]').prop("disabled", true);
			});
			
			$('.plan').each(function(){
				if(this.checked){
					var id="#"+this.parentNode.id;
					$(id).removeClass('checkbox checkbox-primary');
					$(id).addClass('checkbox checkbox-danger');
				}
			});
		}
	});
}

Array.prototype.remove = function() {
    var what, a = arguments, L = a.length, ax;
    while (L && this.length) {
        what = a[--L];
        while ((ax = this.indexOf(what)) !== -1) {
            this.splice(ax, 1);
        }
    }
    return this;
};


//to key activity and sub activity based on the agency
function actualActivityLeadAgencyReport(){
	var outputId = $('#outputId').val();
	var leadAgencyId = $('#leadAgencyId').val();
	if(leadAgencyId == null || leadAgencyId == ""){
		$('#reportTable').hide();
	}else{
		$.ajax({
			url : '/ndcmp/api/getActivitysByLeadAgency',
			data : {
				outputId : outputId,
				leadAgencyId : leadAgencyId
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				if (data != null) {
					var keyActivities = data.keyActivities;
					var statusTrackings = data.statusTrackings;
					
					var dataTable = new Array();
					for (var i = 0; i < keyActivities.length; i++) {
						var row = new Array();
						if(keyActivities[i].subActivities != null){
							row.push(keyActivities[i].sequenceNumber);
							row.push(keyActivities[i].name);
							row.push("");
							row.push("");
							row.push("");
							row.push("");
							row.push("");
							dataTable.push(row);
						}
						
						if(keyActivities[i].subActivities != null){
							var subActivities = keyActivities[i].subActivities;
							for(var j = 0 ; j < subActivities.length; j++){
								row = new Array();
								row.push(subActivities[j].sequenceNumber);
								row.push("");
								row.push(subActivities[j].name);
								var temp = 0;
									for(var k = 0 ; k < statusTrackings.length; k++){
										var subId = statusTrackings[k].subActivity; 
										if(subId != null){
											if(subId.id == subActivities[j].id){
												if(currentRole == 'SUPER_ADMIN' || currentRole == 'PROJECT_ADMIN' || currentRole == 'PROJECT_PLANNER' || currentRole == 'STATUS_APPROVER') {
													if(statusTrackings[k].userLevel == 1){
														row.push('<div style=" width: 30px ; height: 30px; background: '+ statusTrackings[k].actualStatusColor +';" data-toggle="modal" data-target="#myModal"></div>');
														row.push(statusTrackings[k].keyProgress);
														row.push(statusTrackings[k].reasonForGap);
														row.push(statusTrackings[k].rectifyTheGap);
														dataTable.push(row);
														temp = 1;
													}
												}
												if(currentRole == 'STATUS_UPDATER' || currentRole == 'STATUS_REVIEWER') {
													if(statusTrackings[k].userLevel == 2){
														row.push('<div style=" width: 30px ; height: 30px; background: '+ statusTrackings[k].actualStatusColor +';" data-toggle="modal" data-target="#myModal"></div>');
														row.push(statusTrackings[k].keyProgress);
														row.push(statusTrackings[k].reasonForGap);
														row.push(statusTrackings[k].rectifyTheGap);
														dataTable.push(row);
														temp = 1;
													}
												}
											}
									}
								}
								if(temp == 0){
									row.push("");
									row.push("");
									row.push("");
									row.push("");
									dataTable.push(row);
								}
							}
						}
					}
					$('#actualReport-table').DataTable({
						data : dataTable,
						"bDestroy": true,
						"bPaginate" : true,
				    	"bFilter" : true,
				    	"bInfo" :false,
				    	"bSort" : false,
						"bLengthChange": false,
						columns : [ {
							title : "#",
							sortable : false,
						}, {
							title : "Key Activities",
							sortable : false,
						}, {
							title : "Sub Activities",
							sortable : false,
						}, {
							title : "Status",
							sortable : false,
						},{
							title : "Key Progress",
							sortable : false,
						},{
							title : "Reasons for gap if any",
							sortable : false,
						},{
							title : 'Plan of Action to rectify the gap',
							sortable : false,
							align : 'center',
							width : 100
						} ],
						"oLanguage" : {
							"sEmptyTable" : "No data found"
						}
					});
				}
			}
		});
	}
	
}

function actualActivityPartnerAgencyReport(){
	var outputId = $('#outputId').val();
	var leadAgencyId = $('#leadAgencyId').val();
	if(leadAgencyId == null || leadAgencyId == ""){
		$('#reportTable').hide();
	}else{
		$.ajax({
			url : '/ndcmp/api/getActivitysByPartnerAgency',
			data : {
				outputId : outputId,
				leadAgencyId : leadAgencyId
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				if (data != null) {
					var keyActivities = data.keyActivities;
					var statusTrackings = data.statusTrackings;
					
					var dataTable = new Array();
					for (var i = 0; i < keyActivities.length; i++) {
						var row = new Array();
						if(keyActivities[i].subActivities != null){
							row.push(keyActivities[i].sequenceNumber);
							row.push(keyActivities[i].name);
							row.push("");
							row.push("");
							row.push("");
							row.push("");
							row.push("");
							dataTable.push(row);
						}
						
						if(keyActivities[i].subActivities != null){
							var subActivities = keyActivities[i].subActivities;
							for(var j = 0 ; j < subActivities.length; j++){
								row = new Array();
								row.push(subActivities[j].sequenceNumber);
								row.push("");
								row.push(subActivities[j].name);
								var temp = 0;
									for(var k = 0 ; k < statusTrackings.length; k++){
										var subId = statusTrackings[k].subActivity; 
										if(subId != null){
											if(subId.id == subActivities[j].id){
												if(currentRole == 'SUPER_ADMIN' || currentRole == 'PROJECT_ADMIN' || currentRole == 'PROJECT_PLANNER' || currentRole == 'STATUS_APPROVER') {
													if(statusTrackings[k].userLevel == 1){
														row.push('<div style=" width: 30px ; height: 30px; background: '+ statusTrackings[k].actualStatusColor +';" data-toggle="modal" data-target="#myModal"></div>');
														row.push(statusTrackings[k].keyProgress);
														row.push(statusTrackings[k].reasonForGap);
														row.push(statusTrackings[k].rectifyTheGap);
														dataTable.push(row);
														temp = 1;
													}
												}
												if(currentRole == 'STATUS_UPDATER' || currentRole == 'STATUS_REVIEWER') {
													if(statusTrackings[k].userLevel == 2){
														row.push('<div style=" width: 30px ; height: 30px; background: '+ statusTrackings[k].actualStatusColor +';" data-toggle="modal" data-target="#myModal"></div>');
														row.push(statusTrackings[k].keyProgress);
														row.push(statusTrackings[k].reasonForGap);
														row.push(statusTrackings[k].rectifyTheGap);
														dataTable.push(row);
														temp = 1;
													}
												}
											}
									}
								}
								if(temp == 0){
									row.push("");
									row.push("");
									row.push("");
									row.push("");
									dataTable.push(row);
								}
							}
						}
					}
					$('#actualReport-table').DataTable({
						data : dataTable,
						"bDestroy": true,
						"bPaginate" : true,
				    	"bFilter" : true,
				    	"bInfo" :false,
				    	"bSort" : false,
						"bLengthChange": false,
						columns : [ {
							title : "#",
							sortable : false,
						}, {
							title : "Key Activities",
							sortable : false,
						}, {
							title : "Sub Activities",
							sortable : false,
						}, {
							title : "Status",
							sortable : false,
						},{
							title : "Key Progress",
							sortable : false,
						},{
							title : "Reasons for gap if any",
							sortable : false,
						},{
							title : 'Plan of Action to rectify the gap',
							sortable : false,
							align : 'center',
							width : 100
						} ],
						"oLanguage" : {
							"sEmptyTable" : "No data found"
						}
					});
				}
			}
		});
	}
	
}

function actualReportDownload(){
	var spId = $('#strategicPillarId').val();
	var themeId = $('#themeId').val();
	var outcomeId = $('#outcomeId').val();
	var outputId = $('#outputId').val();
	var leadAgencyId = $('#leadAgencyId').val();
	
	if(spId == ''){
		alert('Strategic Pillar is Required');
		document.getElementById("strategicPillarId").focus();
		return false;
	}
	
	if(themeId == ''){
		alert('Theme is Required');
		document.getElementById("themeId").focus();
		return false;
	}
	
	if(outcomeId == ''){
		alert('Outcome is Required');
		document.getElementById("outcomeId").focus();
		return false;
	}
	
	if(outputId == ''){
		alert('Output is Required');
		document.getElementById("outputId").focus();
		return false;
	}
	
	if(leadAgencyId == ''){
		alert('Responsible Entity is Required');
		document.getElementById("leadAgencyId").focus();
		return false;
	}
	 $.ajax({
			url : '/ndcmp/api//actualReportDownload',
			data :{
				outputId : outputId,
				leadAgencyId : leadAgencyId
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



//to show the plan values
//to key activity and sub activity based on the agency
function planVsActualReport(){
	
	var spId = $('#strategicPillarId').val();
	var themeId = $('#themeId').val();
	var outcomeId = $('#outcomeId').val();
	var outputId = $('#outputId').val();
	var leadAgencyId = $('#leadAgencyId').val();
	var yearId=$('#yearId').val();
	
	if(yearId == ''){
		alert('Reporting Period is Required');
		$("#yearId").focus();
		return false;
	}
	
	if(spId == ''){
		alert('Strategic Pillar is Required');
		$("#strategicPillarId").focus();
		return false;
	}
	
	if(themeId == ''){
		alert('Theme is Required');
		$("#themeId").focus();
		return false;
	}
	
	if(outcomeId == ''){
		alert('Outcome is Required');
		$("#outcomeId").focus();
		return false;
	}
	
	if(outputId == ''){
		alert('Output is Required');
		$("#outputId").focus();
		return false;
	}
	
	if(leadAgencyId == ''){
		alert('Responsible Entity is Required');
		$("#leadAgencyId").focus();
		return false;
	}
	
	var outputId = $('#outputId').val();
	var leadAgencyId = $('#leadAgencyId').val();
	if(leadAgencyId == null || leadAgencyId == ""){
		$('#planReport-table').hide();
	}else{
		$.ajax({
			url : '/ndcmp/api/getPlanVsActual',
			data : {
				outputId : outputId,
				leadAgencyId : leadAgencyId
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
			   var quaterArray=[];
			   var dataTable = new Array();
			   var keyActivities = data.keyActivities;
			   var statusTrackings = data.statusTrackings;
			   if(data.years != null){
				   $.each(data.years,function(yi,yv){
			    		if(data.reportingPeriods != 0){
			    			var reportingPeriods=data.reportingPeriods;
			    			
			    			
			    			$.each(reportingPeriods,function(ri,rv){
			    				var year=rv.year;
			    				if(year == yv){
			    					quaterArray.push('{"title" : "'+rv.name+'"}');
			    				}
			    			});
			    			
			    		}
				    	 
				    });
				   }
				   var columnJson=eval('[{"COLUMNS":[{ "title": "#"}, { "title": "Key Activity","sWidth": "100px"},{"title" : "Sub Activity"},{"title" :"MOV"},'+quaterArray+']}]');
					   
				   for (var i = 0; i < keyActivities.length; i++) {
						var row = new Array();
						if(keyActivities[i].subActivities != null){
							row.push(keyActivities[i].sequenceNumber);
							row.push(keyActivities[i].name);
							if(data.years != null){
								 $.each(data.years,function(yi,yv){
							    		if(data.reportingPeriods != 0){
							    			var reportingPeriods=data.reportingPeriods;
							    			
							    			
							    			$.each(reportingPeriods,function(ri,rv){
							    				row.push("");
							    			});
							    		}
							    });
							}
							dataTable.push(row);
						}

						if(keyActivities[i].subActivities != null){
							var subActvities = keyActivities[i].subActivities;
							for(var j = 0 ; j < subActvities.length; j++){
								row = new Array();
								row.push(subActvities[j].sequenceNumber);
								row.push("");
								row.push(subActvities[j].name);
								row.push(subActvities[j].mov);
								if(data.years != null){
									 $.each(data.years,function(yi,yv){
								    		if(data.reportingPeriods != 0){
								    			var reportingPeriods=data.reportingPeriods;
								    			
								    			
								    			$.each(reportingPeriods,function(ri,rv){
								    				row.push("<label id='"+subActvities[j].id+"_"+rv.year+"_"+rv.id+"'></label>");
								    			});
								    		}
								    		});
									 }

								dataTable.push(row);
							}
						}
				   }
				   $('#planReport-table thead').empty();
				   $('#planReport-table tbody').empty();
				   $('#planReport-table tr').empty();
				   $('#planReport-table').empty();
					  var table = $('#planReport-table').DataTable( {
					    	"bDestroy": true,
					    	"bPaginate" : false,
					    	"bFilter" : false,
					    	"bInfo" :false,
					    	"bSort" : false,
					    	"bLengthChange": false,
							"aoColumns" :columnJson[0].COLUMNS
					    } );
				  
		            table.rows.add(dataTable);
		            table.draw();
		            var txt="";
		            txt+="<tr><th></th><th style='width: 100px;'></th><th></th><th></th>";
			    	 $.each(data.years,function(yi,yv){
			    		 txt+="<th colspan='4' style='text-align: center;'>"+yv+"</th>";
		            });
		            txt+="</tr>";
		            
		            
		            $(txt).insertBefore($('#planReport-table thead tr:eq(0) '));
		            for (var i = 0; i < keyActivities.length; i++) {
		        		if(keyActivities[i].subActivities != null){
		        			if(keyActivities[i].subActivities != null){
		        				var subActvities = keyActivities[i].subActivities;
		        				for(var j = 0 ; j < subActvities.length; j++){
		        					if(data.years != null){
											 if(data.years != null){
													$.each(data.years,function(yi,yv){
														$.each(subActvities[j].plannings,function(si,sv){
															var reportingPeriod=sv.reportingPeriod;
															if(yv ==reportingPeriod.year){
																for(var k=1;k<=4;k++){
																	if(("Q"+k) == reportingPeriod.name){
																		if(sv.statusOfProgress == true){
																			var id="#"+subActvities[j].id+"_"+reportingPeriod.year+"_"+reportingPeriod.id;
								    										var subId=subActvities[j].id;
								    										
								    										$.each(statusTrackings,function(sti,stv){
								    											if(stv.subActivity != null){
								    												var subActId=stv.subActivity.id;
									    											if(subActId == subId){
									    												if(stv.reportingPeriod.name == ("Q"+k)){
									    													if(stv.userLevel == 1){
									    														$(id).html("X("+stv.actualStatusPercentage+"%)");	
									    													}
									    												}
									    											}
								    											}
								    											
								    										});
								    										
																		}
																	}
																}
															}
														});
													});
												}
		        					}
		        				}
		        		}
		        	}
		           }
			}
		});
	}
}

function planVsActualPartnerReport(){
	var outputId = $('#outputId').val();
	var leadAgencyId = $('#leadAgencyId').val();
	if(leadAgencyId == null || leadAgencyId == ""){
		$('#planReport-table').hide();
	}else{
		$.ajax({
			url : '/ndcmp/api/getPartnerPlanVsActual',
			data : {
				outputId : outputId,
				leadAgencyId : leadAgencyId
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
			   var quaterArray=[];
			   var dataTable = new Array();
			   var keyActivities = data.keyActivities;
			   var statusTrackings = data.statusTrackings;
			   if(data.years != null){
				   $.each(data.years,function(yi,yv){
			    		if(data.reportingPeriods != 0){
			    			var reportingPeriods=data.reportingPeriods;
			    			
			    			
			    			$.each(reportingPeriods,function(ri,rv){
			    				var year=rv.year;
			    				if(year == yv){
			    					quaterArray.push('{"title" : "'+rv.name+'"}');
			    				}
			    			});
			    			
			    		}
				    	 
				    });
				   }
				   var columnJson=eval('[{"COLUMNS":[{ "title": "#"}, { "title": "Key Activity","sWidth": "100px"},{"title" : "Sub Activity"},{"title" :"MOV"},'+quaterArray+']}]');
					   
				   for (var i = 0; i < keyActivities.length; i++) {
						var row = new Array();
						if(keyActivities[i].subActivities != null){
							row.push(keyActivities[i].sequenceNumber);
							row.push(keyActivities[i].name);
							if(data.years != null){
								 $.each(data.years,function(yi,yv){
							    		if(data.reportingPeriods != 0){
							    			var reportingPeriods=data.reportingPeriods;
							    			
							    			
							    			$.each(reportingPeriods,function(ri,rv){
							    				row.push("");
							    			});
							    		}
							    });
							}
							dataTable.push(row);
						}

						if(keyActivities[i].subActivities != null){
							var subActvities = keyActivities[i].subActivities;
							for(var j = 0 ; j < subActvities.length; j++){
								row = new Array();
								row.push(subActvities[j].sequenceNumber);
								row.push("");
								row.push(subActvities[j].name);
								row.push(subActvities[j].mov);
								if(data.years != null){
									 $.each(data.years,function(yi,yv){
								    		if(data.reportingPeriods != 0){
								    			var reportingPeriods=data.reportingPeriods;
								    			
								    			
								    			$.each(reportingPeriods,function(ri,rv){
								    				row.push("<label id='"+subActvities[j].id+"_"+rv.year+"_"+rv.id+"'></label>");
								    			});
								    		}
								    		});
									 }
								dataTable.push(row);
							}
						}
				   }
				   $('#planReport-table thead').empty();
				   $('#planReport-table tbody').empty();
				   $('#planReport-table tr').empty();
				   $('#planReport-table').empty();
					  var table = $('#planReport-table').DataTable( {
					    	"bDestroy": true,
					    	"bPaginate" : false,
					    	"bFilter" : false,
					    	"bInfo" :false,
					    	"bSort" : false,
					    	"bLengthChange": false,
							"aoColumns" :columnJson[0].COLUMNS
					    } );
				  
		            table.rows.add(dataTable);
		            table.draw();
		            var txt="";
		            txt+="<tr><th></th><th style='width: 100px;'></th><th></th><th></th>";
			    	 $.each(data.years,function(yi,yv){
			    		 txt+="<th colspan='4' style='text-align: center;'>"+yv+"</th>";
		            });
		            txt+="</tr>";
		            
		            
		            $(txt).insertBefore($('#planReport-table thead tr:eq(0) '));
		            for (var i = 0; i < keyActivities.length; i++) {
		        		if(keyActivities[i].subActivities != null){
		        			if(keyActivities[i].subActivities != null){
		        				var subActvities = keyActivities[i].subActivities;
		        				for(var j = 0 ; j < subActvities.length; j++){
		        					if(data.years != null){
											 if(data.years != null){
													$.each(data.years,function(yi,yv){
														$.each(subActvities[j].plannings,function(si,sv){
															var reportingPeriod=sv.reportingPeriod;
															if(yv ==reportingPeriod.year){
																for(var k=1;k<=4;k++){
																	if(("Q"+k) == reportingPeriod.name){
																		if(sv.statusOfProgress == true){
																			var id="#"+subActvities[j].id+"_"+reportingPeriod.year+"_"+reportingPeriod.id;
								    										var subId=subActvities[j].id;
								    										
								    										$.each(statusTrackings,function(sti,stv){
								    											if(stv.subActivity != null){
								    												var subActId=stv.subActivity.id;
									    											if(subActId == subId){
									    												if(stv.reportingPeriod.name == ("Q"+k)){
									    													if(stv.userLevel == 2){
									    														$(id).html("X("+stv.actualStatusPercentage+"%)");	
									    													}
									    												}
									    											}
								    											}
								    											
								    										});
								    										
																		}
																	}
																}
															}
														});
													});
												}
		        					}
		        				}
		        		}
		        	}
		           }
			}
		});
	}
}

//To download the Plan vs actual report
function planvsActualReportDownload(){
	var spId = $('#strategicPillarId').val();
	var themeId = $('#themeId').val();
	var outcomeId = $('#outcomeId').val();
	var outputId = $('#outputId').val();
	var leadAgencyId = $('#leadAgencyId').val();
	
	if(spId == ''){
		alert('Strategic Pillar is Required');
		document.getElementById("strategicPillarId").focus();
		return false;
	}
	
	if(themeId == ''){
		alert('Theme is Required');
		document.getElementById("themeId").focus();
		return false;
	}
	
	if(outcomeId == ''){
		alert('Outcome is Required');
		document.getElementById("outcomeId").focus();
		return false;
	}
	
	if(outputId == ''){
		alert('Output is Required');
		document.getElementById("outputId").focus();
		return false;
	}
	
	if(leadAgencyId == ''){
		alert('Responsible Entity is Required');
		document.getElementById("leadAgencyId").focus();
		return false;
	}
	 $.ajax({
			url : '/ndcmp/api/planReportDownload',
			data :{
				outputId : outputId,
				leadAgencyId : leadAgencyId
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

// Get outcomes based on the selected theme --> Planner
function getOutcomesByTheme(slectedTheme){
	var themeId = slectedTheme.value;
	if(themeId == null || themeId == ""){
		$('#outcomeId').html('<option value="">---Select Outcome ---</option>');
		$('#outputId').html('<option value="">---Select Output ---</option>');
		$('#leadAgencyId').html('<option value="">---Select---</option>');
	}else{
		$.ajax({
			url : '/ndcmp/api/getOutcomesByTheme',
			data : {
				themeId : themeId
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				if (data != null) {
					var outcomebasedontheme = '<option value="">---Select Outcome ---</option>';
					var outcomes = data;
					for (var i = 0; i < outcomes.length; i++) {
						outcomebasedontheme += '<option value="'
								+ outcomes[i].id + '">'
								+ outcomes[i].sequenceNumber + '. ' 
								+ outcomes[i].name
								+ '</option>';
					}
					$('#outcomeId').html(outcomebasedontheme);
					 $("select#outcomeId").prop('selectedIndex', 1);
					 var out={
							 value : outcomes[0].id
					 }
					 getOutputsByoutcome(out);
					//$('#outputId').html('<option value="">---Select Output ---</option>');
				}
			}
		});
	}
}

// Get outputs by selected outcome ---> planner
function getOutputsByoutcome(slectedOutcome){
	var outcomeId = slectedOutcome.value;
	if(outcomeId == null || outcomeId == ""){
		$('#outputId').html('<option value="">---Select Output ---</option>');
		$('#leadAgencyId').html('<option value="">---Select---</option>');
	}else{
		$.ajax({
			url : '/ndcmp/api/getOutputsByoutcome',
			data : {
				outcomeId : outcomeId
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				if (data != null) {
					var outputbasedonoutcome = '<option value="">---Select Output---</option>';
					var outputs = data;
					for (var i = 0; i < outputs.length; i++) {
						outputbasedonoutcome += '<option value="'
								+ outputs[i].id + '">'
								+ outputs[i].sequenceNumber + '. '
								+ outputs[i].output 
								+ '</option>';
					}
					$('#outputId').html(outputbasedonoutcome);
					 $("select#outputId").prop('selectedIndex', 1);
				} else {
					var outputbasedonoutcome = '<option value="">---Select Output---</option>';
					$('#outputId').html(outputbasedonoutcome);
				}
			}
		});
	}
}

function downloadPlanningTemplate(){
	var message="";
	if($(strPillarId).val() == null){
		message +='<div class="alert alert-danger">'+
			'<strong>Strategic pillar</strong> is required</div>';
	}
	if($('#repPeriodId').val() == null){
		message +='<div class="alert alert-danger">'+
			'<strong>Theme</strong> is required</div>';
	}
	if(message != ""){
		$('#validationMsg').html(message);
	}else{
		$('#validationMsg').html("");
		
		 $.ajax({
				url : '/ndcmp/api/downloadPlanningTemplate',
				data :{
					"strPillarIds" : $('#strPillarId').val().toString(),
					"repPeriodIds" : $('#repPeriodId').val().toString()
				},
		 		type : 'GET',
		 		async : false,
		 		success : function(data) {
		 			if (data != null) {
		 				window.open(data);
		 				$('#templateModal').modal('hide');
		 			}
		 		}
		 	});
		
	}
	
}

/*function checkIfAllSelected(){
	var strPillar=$(strPillarId).val();
	if(strPillar != null && strPillar.length > 1){
		if(jQuery.inArray("0", strPillar) != -1) {
			alert();
		}else{
			alert();
		}
	}
}*/

function countData(arr) {
	  return arr.reduce((prev, curr) => (prev[curr] = ++prev[curr] || 1, prev), {})
}