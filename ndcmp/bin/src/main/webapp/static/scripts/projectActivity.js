
var ctx = window.location.pathname.split('/');
ctx = ctx[1];

var subActivityIds=[];
var reportingPeriodIds=[];
var allIds=[];
var checkedIds=[];
var status="";
var act_deactId="";
var act_deactAction="";
var output_id;
var role="";
var deleteId=[];
$(document).ready(function() {
	
	
	
	role=localStorage.getItem("role");
	var action=$('#action').val();
	if(role=="PROJECT_ADMIN" && action != "view"){
		$('#projectHead').html("Configure Project");
		projectAdminHierarchy();	
	}else{
		$('#projectHead').html("View Project");
	}
	$('#reworkJobLevelStatus').fadeOut();
	$('#accordion').show();
	//$("#accordion").accordion({collapsible : true, active : false});
	$(".toggle-vis").click(function (e) {
       
}); 
	
	$(".toggle-vis").css( 'cursor', 'pointer' );
	$(".accordion-toggle").collapse();
	$(".accordion-toggle").attr("aria-expanded","false");

	$('.collapse').collapse();
	
//	$("#accordion").accordion({  event: "mouseover" }).activate(2);
	
	$(".accordion").bind("click", function(event) {
		 status=$('#'+event.currentTarget.id).find('a').hasClass('accordion-toggle collapse in');
		if(status === "false"){
			//console.log(status);
		}else{
			
		}
		
	});

	getAllPlanning();
	
	$(':checkbox').bind('change', function () {

		   if ($(this).is(':checked')){
			   var checkBoxValue=$(this).val();
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
			   var checkBoxValue=$(this).val();
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
		});
	
	
	
	$('a.toggle-vis').on( 'click', function (e) {
		console.log($(this).attr('id'));
		var tableValue=$(this).attr('id').split('-');
		var t='#planner-table'+tableValue[1];
		
		if ( $.fn.dataTable.isDataTable( t ) ) {
		    table = $(t).DataTable();
		}
		else {
		    table = $(t).DataTable( {
		        "paging": false,
		        "scrollX": true,
		        "ordering": false,
		        "searching" : false,
		    } );
		}
		
		
		        e.preventDefault();
		        var column = table.column( $(this).attr('data-columnindex') );
		        column.visible( ! column.visible() );
	 } );
	
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
		
		$('#hierarchy-table').dataTable({
			"bscrollX": true,
			 "sScrollXInner": "100%",
		        "bScrollCollapse": true
		    	
		});



		function setReportingIdSubActivitiesId(){
			$.each($("input[name='plan']:checked"), function(){         
				var idsString=$(this).val().split(',');
				subActivityIds.push(idsString[0]);
				reportingPeriodIds.push(idsString[1]);
			});
		}
		
		$(".btn-add-panel").on("click", function() {
			$('#strategicPillarNameError').html("");
			$('#pillarSNError').html("");
			$('#themeError').html("");
			$('#outcomeSNError').html("");
			$('#outcomeError').html("");
			$('#outputSNError').html("");
			$('#outputError').html("");
			$('#indicatorError').html("");
			$('#targetError').html("");
			$('#addStrategicPillarModal').val();
			$.ajax({
				url : '/ndcmp/api/getAddStrategicpillar',
				data : {
				},
				type : 'GET',
				async : false,
				dataType : 'json',
				success : function(data) {
					if (data && data.trim != '') {
						var ele = data.project;
						var name = data.strategicPillar;
						document.getElementById("strategicPillarName").value = name;
						document.getElementById("projectName").value = ele;
					}
				}
			});
		});
		
		$(".btn-add-panel2").on("click", function() {
			var parent = $(this).closest('.theme');
			var id = $(parent).attr('id');
			var themeId = id.split('_')[1];
			//var strategicPillarId = $('#strategicId').val();
			$.ajax({
				url : '/ndcmp/api/getAddOutcome',
				data : {
					themeId : themeId
				},
				type : 'GET',
				async : false,
				dataType : 'json',
				success : function(data) {
					if (data && data.trim != '') {
						var ele = data.theme;
						var name = data.outcome;
						$('#thmId').val(themeId);
						$('#outcomeName').val(name);
						$('#outcomeThemeName').val(ele);
					}
				}
			});
		});
		
		 var rules = {
		         txtName: {
		             required: true
		         }
		     };
		     var messages = {
		         txtName: {
		             required: "Please enter name"
		         }
		     };
		
		$(".btn-add-panel1").on("click",function() {
			var parent = $(this).closest('.strategic-pillar');
			var id = $(parent).attr('id');
			var strategicPillarId = id.split('_')[1];
			//var strategicPillarId = $('#strategicId').val();
			$.ajax({
				url : '/ndcmp/api/getAddTheme',
				data : {
						strategicPillarId : strategicPillarId
				},
				type : 'GET',
				async : false,
				dataType : 'json',
				success : function(data) {
					if (data && data.trim != '') {
						var ele = data.strategicPillar;
						var name = data.theme;
						document.getElementById("themeName").value = name;
						document.getElementById("themeStrategicPillarName").value = ele;
					}
				}
			});
		});
		
		//to get output id
		$(".btn-add-panel4").on("click", function() {
			var parent = $(this).closest('.output');
			var id = $(parent).attr('id');
			var outputId = id.split('_')[1];
			//var strategicPillarId = $('#strategicId').val();
			$.ajax({
				url : '/ndcmp/api/getHierarchyKeyActivity',
				data : {
					outputId : outputId
				},
				type : 'GET',
				async : false,
				dataType : 'json',
				success : function(data) {
					if (data && data.trim != '') {
						var ele = data.output.output;
						var name = data.name;
						document.getElementById("keyActivityName").value = name;
						$('#outputKeyActivityId').val(data.output.id);
						document.getElementById("keyActivityOutputName").value = ele;
						$('#add_Key_Activity').show();
						$('#update_Key_Activity').hide();
					}
				}
			});
		});
		
		$(".btn-add-panel3").on("click", function() {
			var parent = $(this).closest('.outcome');
			var id = $(parent).attr('id');
			var outcomeId = id.split('_')[1];
			//var strategicPillarId = $('#strategicId').val();
			$.ajax({
				url : '/ndcmp/api/getAddHierarchyOutput',
				data : {
					outcomeId : outcomeId
				},
				type : 'GET',
				async : false,
				dataType : 'json',
				success : function(data) {
					if (data && data.trim != '') {
						var ele = data.outcome;
						var name = data.output;
						$('#outputOutcomeName').val(ele);
						$('#OUTCOMEId').val(data.outcomeId);
						/*document.getElementById("outputName").value = name;
						document.getElementById("outputOutcomeName").value = ele;*/
					}
				}
			});
		});
		
		//to get target id
		$(".btn-add-panel6").on("click", function() {
			var parent = $(this).closest('.output');
			var id = $(parent).attr('id');
			var outputId = id.split('_')[1];
			//var strategicPillarId = $('#strategicId').val();
			$.ajax({
				url : '/ndcmp/api/getHierarchyKeyActivity',
				data : {
					outputId : outputId
				},
				type : 'GET',
				async : false,
				dataType : 'json',
				success : function(data) {
					if (data && data.trim != '') {
						var ele = data.output.output;
						/* $('#projectName').html(ele); */
						document.getElementById("outName").value = ele;
					}
				}
			});
		});
		
		/*$(".pillarDIV").on("click", function() {
			alert();
		});*/
		
	$('#subActivityActiveDialog').on('show.bs.modal',function(event) {
			var getIdFromRow = $(event.relatedTarget).find('td:nth-child(3)').text();
			$(this).find('#subActivityActive').html($('<b> Are you sure to Activate Sub Activity ? '+ getIdFromRow + '</b>'));
	});
	$('#subActivityActiveDialog').on('show.bs.modal',function(e) {
			$(this).find('.btn-ok').attr('href',
			$(e.relatedTarget).data('href'));
	});
	
	/* device dis approved modal window */
	$('#subActivityDeactiveDialog').on('show.bs.modal',function(event) {
			var getIdFromRow = $(event.relatedTarget).find('td:nth-child(3)').text();
			$(this).find('#subActivityDeactive').html($('<b> Are you sure to Deactivate Sub Activity  ? '+ getIdFromRow + '</b>'));
	});
	$('#subActivityDeactiveDialog').on('show.bs.modal',function(e) {
			$(this).find('.btn-ok').attr('href',
			$(e.relatedTarget).data('href'));
	});
		
});

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
				
				//setReportingIdSubActivitiesId();
				  
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
					 
					  	window.location.href = '/' + ctx + '/api/configProject?projectId=1';
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
					});
				}
			});
		}
		function editStrategicPillar(strategicPillarId) {
				$('#strategicNameError').html("");
			$.ajax({
				url : '/ndcmp/api/editStrategicPillar',
				data : {
					strategicPillarId : strategicPillarId
				},
				type : 'GET',
				async : false,
				dataType : 'json',
				success : function(data) {
					if (data && data.trim != '') {
						var ele = data.project.name;
						var name = data.name;
						document.getElementById("editStrategicPillarName").value = name;
						document.getElementById("editProjectName").value = ele;
						$('#editStrategicPillarId').val(data.id);
						$('#add_Strategic_Pillar').hide();
						$('#update_Strategic_Pillar').show();
						$('#editStrategicPillarModal').modal('show');
					}
				}
			});
		}
		
	
		
		function editTheme(themeId) {
			$('#editThemeNameError').html("");
			$.ajax({
				url : '/ndcmp/api/editTheme',
				data : {
					themeId : themeId
				},
				type : 'GET',
				async : false,
				dataType : 'json',
				success : function(data) {
					
					if (data && data.trim != '') {
						$('#editThemeNameError').html("");
						var ele = data.strategicPillar.name;
						var name = data.name;
						$('#editThemeName').val(name);
						$('#editThemeStrategicPillarName').val(ele);
						$('#pillarid').val( data.strategicPillar.id);
						$('#themeid').val(data.id);
						$('#editThemeModal').modal('show');
					}
				}
			});
		}

		function updateTheme(){
			var pillarid=$('#pillarid').val();
			var themeid=$('#themeid').val();
			var editThemeName=$('#editThemeName').val();
			
			if(editThemeName == ""){
				$('#editThemeNameError').html("Theme Name is required");
			}else{
				$('#editThemeNameError').html("");
				
				var themeJson={
					"pillarid" : pillarid,
					"themeid" : themeid,
					"editThemeName" : editThemeName
				};
				
				$.ajax({
					url : '/ndcmp/api/updateTheme',
					data : {
						"themeJson" : JSON.stringify(themeJson)
					},
					type : 'GET',
					async : false,
					dataType : 'json',
					success : function(data) {
						if(data.themeNameError != null){
							$('#editThemeNameError').html(data.themeNameError);
						}else{
							$('#editThemeNameError').html("");
							$('#thm_'+data.theme.id).text(data.theme.name).css('font-weight', 'bold');
							$('#editThemeModal').modal('hide');
						}
					}
				});
			}
		}
		
		function editOutcome(outcomeId) {
			$.ajax({
				url : '/ndcmp/api/editOutcome',
				data : {
					outcomeId : outcomeId
				},
				type : 'GET',
				async : false,
				dataType : 'json',
				success : function(data) {
					if (data && data.trim != '') {
						var ele = data.theme.name;
						var name = data.name;
						$('#outNAME').val(name);
						$('#editOutcomeThemeName').val(ele);
						$('#themId').val(data.theme.id);
						$('#outCOMEId').val(data.id);
						$('#editOutcomeModal').modal('show');
					}
				}
			});
		}
		function updateOutcome(){
			
			var outcomeName=$('#outNAME').val();
			var themeId=$('#themId').val();
			var outcomeId=$('#outCOMEId').val();
			
			if (outcomeName == "") {
				$('#outNAMEError').html(
						"Outcome Name is required");
			} else {
				$('#outNAMEError').html("");
				
				var outcomeJson={
					"outcomeName" : outcomeName,
					"themeId" : themeId,
					"outcomeId" : outcomeId
				};
				
				$.ajax({
					url : '/ndcmp/api/updateOutcome',
					data : {
						outcomeJson : JSON.stringify(outcomeJson)
					},
					type : 'GET',
					async : false,
					dataType : 'json',
					success : function(data) {
						
						if(data.outcomeNameError != null){
							$('#outNAMEError').html(data.outcomeNameError);
						}else{
							$('#outNAMEError').html("");
							$('#ocome_'+data.outcome.id).text(data.outcome.sequenceNumber +' . '+ data.outcome.name).css('font-weight', 'bold');
							$('#editOutcomeModal').modal('hide');	
						}
						
						//var txt='<a class="accordion-toggle collapsed collapse in" data-toggle="collapse" id="themeAdd_'+data.theme.id+'" data-parent="#accordionOne_'+data.theme.id+'" href="#collapseOutcome'+data.id+'" aria-expanded="false"><span><b>'+data.name+'</b></span></a>';
						
					}
				});
			}
			
		}
		
		function showOutcome(){
			$('#outcoSenError').html("");
			$('#outSenError').html("");
			$('#outputnError').html("");
			$('#indicatornameError').html("");
			$('#targetnameError').html("");
			$('#outcmenError').html("");
			
			$('#addOutcomeModal').modal('show');
		}
		
		function saveOutCome(){
			var thmId=$('#thmId').val();
			var outcomeId=$('#outcomeId').val();
			var outcoSen =$('#outcoSen').val();
			var outcmen=$('#outcmen').val();
			var outSen=$('#outSen').val();
			var outputn=$('#outputn').val();
			var indicatorname=$('#indicatorname').val();
			var targetname=$('#targetname').val();
			var validation="";
			
			if(outcomeId == ""){
				outcomeId=0;
			}
			
			if(outcoSen != "" && outcmen != "" && outSen != "" && outputn != "" && indicatorname != "" && targetname!= ""){
				validation = "true";
			}else{
				
				if (outcoSen == "") {
					$('#outcoSenError').html(
							"Sequence Number of Outcome is required");
				} else {
					$('#outcoSenError').html("");
				}
				
				if (outSen == "") {
					$('#outSenError').html(
							"Sequence Number of Output is required");
				} else {
					$('#outSenError').html("");
				}
				if (outputn == "") {
					$('#outputnError').html(
							"Output Name is required");
				} else {
					$('#outputnError').html("");
				}
				if (indicatorname == "") {
					$('#indicatornameError').html(
							"Indicator is required");
				} else {
					$('#indicatornameError').html("");
				}
				if (targetname == "") {
					$('#targetnameError').html(
							"Target is required");
				} else {
					$('#targetnameError').html("");
				}
				if (outcmen == "") {
					$('#outcmenError').html(
							"Outcome is required");
				} else {
					$('#outcmenError').html("");
				}
				validation == "false";
			}
			if(validation == "true"){
				$('#outcoSenError').html("");
				$('#outSenError').html("");
				$('#outputnError').html("");
				$('#indicatornameError').html("");
				$('#targetnameError').html("");
				$('#outcmenError').html("");
				
				var outcomeJson={
					"thmId" : thmId,
					"outcomeId" : outcomeId,
					"outcoSen" : outcoSen,
					"outSen" : outSen,
					"outputn" : outputn,
					"indicatorname" : indicatorname,
					"targetname" : targetname,
					"outcmen":outcmen
				};
				
				$.ajax({
					url : '/ndcmp/api/saveOutcome',
					data : {
						"outcomeJson" : JSON.stringify(outcomeJson)
					},
					type : 'GET',
					async : false,
					dataType : 'json',
					success : function(data) {
						if(data.outcomeNameError != null){
							$('#outcmenError').html(data.outcomeNameError);
						}else{
							$('#outcmenError').html("");
							createOutcomeAccordion(data);
							$('#addOutcomeModal').modal('hide');
						}
					}
				});
				
			}else{
				return false;
			}
			
			
		}
		
		function editOutput(outputId) {
			$('#editOutcError').html("");
			$.ajax({
				url : '/ndcmp/api/editOutput',
				data : {
					outputId : outputId
				},
				type : 'GET',
				async : false,
				dataType : 'json',
				success : function(data) {
					if (data && data.trim != '') {
						var ele = data.outcome.name;
						var name = data.output;
						//for adding
						$('#editOutputName').val(name);
						$('#outputOutcomeName').val(ele);
						$('#OUTCOMEId').val(data.outcome.id);
						$('#OUTPUTId').val(data.output.id);
						
						//for editing
						$('#editOutputOutcomeId').val(data.outcome.id);
						$('#outpId').val(data.id);
						
						
						$('#editOutputOutcomeName').val(data.outcome.name);
						$('#editOutputname').val(data.output);
						
						$('#editOutputModal').modal('show');
					}
				}
			});
		}
		function updateOutput(){
			var editOutputOutcomeId=$('#editOutputOutcomeId').val();
			var outpId=$('#outpId').val();
			var editOutputname=$('#editOutputname').val();
			
			if(editOutputname == ""){
				$('#outputSequenceNumberError').html(
				"Sequence Number of Output is required");
				return false;
			}else{
				$('#outputSequenceNumberError').html("");
			}
			
			var outputJson={
				"editOutputOutcomeId" : editOutputOutcomeId,
				"outpId" :outpId,
				"editOutputname" : editOutputname
			};
			
			$.ajax({
				url : '/ndcmp/api/updateOutput',
				data : {
					outputJson : JSON.stringify(outputJson)
				},
				type : 'GET',
				async : false,
				dataType : 'json',
				success : function(data) {
					if(data.outputNameError != null){
						$('#editOutcError').html(data.outputNameError);
					}else{
						$('#editOutcError').html("");
						$('#output_'+data.output.id).text(data.output.sequenceNumber +' . '+ data.output.output).css('font-weight', 'bold');
						$('#editOutputModal').modal('hide');
					}
				}
			});
			
		}
		function addOutput(){
			
			var id=$('#OUTPUTId').val();
			var OUTCOMEId=$('#OUTCOMEId').val();

			var outputSequenceNumber=$('#outputSequenceNumber').val();
			var OUTPUTNAME=$('#OUTPUTNAME').val();
			var INDICATOR=$('#INDICATOR').val();
			var TARGET=$('#TARGET').val();
			var validation="";
			
			if(id == ""){
				id= 0;
			}
			
			if(outputSequenceNumber != "" && OUTPUTNAME != "" && INDICATOR != "" && TARGET != ""){
				validation = "true";
			}else{
				
				if (outputSequenceNumber == "") {
					$('#outputSequenceNumberError').html(
							"Sequence Number of Output is required");
				} else {
					$('#outputSequenceNumberError').html("");
				}
				
				if (OUTPUTNAME == "") {
					$('#OUTPUTNAMEError').html(
							"Output Name is required");
				} else {
					$('#OUTPUTNAMEError').html("");
				}
				if (INDICATOR == "") {
					$('#INDICATORError').html(
							"Indicator is required");
				} else {
					$('#INDICATORError').html("");
				}
				if (TARGET == "") {
					$('#TARGETError').html(
							"Target is required");
				} else {
					$('#TARGETError').html("");
				}
			}
			
			if(validation == "true"){
				$('#outputSequenceNumberError').html("");
				$('#OUTPUTNAMEError').html("");
				$('#INDICATORError').html("");
				$('#TARGETError').html("");
				
				var outputJson={
						"id" : id,
						"OUTCOMEId" :OUTCOMEId,
						"outputSequenceNumber" : outputSequenceNumber,
						"OUTPUTNAME" : OUTPUTNAME,
						"INDICATOR" : INDICATOR,
						"TARGET" :TARGET
				};
				
				$.ajax({
					url : '/ndcmp/api/saveOutput',
					data : {
						outputJson : JSON.stringify(outputJson)
					},
					type : 'GET',
					async : false,
					dataType : 'json',
					success : function(data) {
						if(data.outputNameError != null){
							$('#OUTPUTNAMEError').html("Output Name is already exist");
						}else{
							$('#OUTPUTNAMEError').html("");
							createOutputAccordion(data);
							$('#addOutputModal').modal('hide');
						}
					}
				});
			}else{
				return false;
			}
			
		}
		
		
		
		function editKeyActivity(keyActivityId) {
			
			$.ajax({
				url : '/ndcmp/api/editKeyActivity',
				data : {
					keyActivityId : keyActivityId
				},
				type : 'GET',
				async : false,
				dataType : 'json',
				success : function(data) {
					output_id=data.output.id;
					if (data && data.trim != '') {
						var ele = data.output.output;
						var name = data.name;
						document.getElementById("keyActivityName").value = name;
						document.getElementById("keyActivityOutputName").value = ele;
						
						
						
						$('#outputKeyActivityId').val(data.output.id);
						$('#keyActivityId').val(data.id);
						
						$('#add_Key_Activity').hide();
						$('#update_Key_Activity').show();
						$('#keyActivityModal').modal('show');
					}
				}
			});
		}
		
		function newKeyActivity(id){
			$('#keyActNameError').html("");
			$('#keyActivityId').val(0);
			output_id=id;
			
			$.ajax({
				url : '/ndcmp/api/getHierarchyKeyActivity',
				data : {
					outputId : id
				},
				type : 'GET',
				async : false,
				dataType : 'json',
				success : function(data) {
					if (data && data.trim != '') {
						var ele = data.output.output;
						var name = data.name;
						document.getElementById("keyActivityName").value = name;
						$('#outputKeyActivityId').val(data.output.id);
						document.getElementById("keyActivityOutputName").value = ele;
						$('#add_Key_Activity').show();
						$('#update_Key_Activity').hide();
					}
				}
			});
			$('#keyActivityModal').modal('show');
		}
		
		
		function openKeyActivtyModal(outputId) {
			$.ajax({
				url : '/ndcmp/api/getHierarchyKeyActivity',
				data : {
					outputId : outputId
				},
				type : 'GET',
				async : false,
				dataType : 'json',
				success : function(data) {
					
					if (data && data.trim != '') {
						var ele = data.output.output;
						/* $('#projectName').html(ele); */
						document.getElementById("outputName").value = ele;
						$('#outputKeyActivityId').val(data.output.id);
						$('#add_Key_Activity').show();
						$('#update_Key_Activity').hide();
						$('#keyActivityModal').modal('show');
					}
				}
			});
		}
		
		function openSubActivtyModal(keyActvityId) {
			$('#subActNameError').html("");
			$('#leadAgencyError').html("");
			$('#partnerAgencyError').html("");
			$('#movError').html("");
			
			$.ajax({
				url : '/ndcmp/api/getHierarchySubActivity',
				data : {
					keyActvityId : keyActvityId
				},
				type : 'GET',
				async : false,
				dataType : 'json',
				success : function(data) {
					if (data && data.trim != '') {
						
						$('#subActivityId').val("");
						$('#subActivityName').val("");
						$('#mov').val("");
						$('#key').val(data.keyActivity.id);
						$('#subKeyActivityName').val(data.keyActivity.name);
						
						var leadAgency = '<option value="">--- Select Responsible Entity ---</option>';
						var leadAgencys = data.leadAgency;
						for (var i = 0; i < leadAgencys.length; i++) {
							leadAgency += '<option value="'	+ leadAgencys[i].id + '">' + leadAgencys[i].code + '</option>';
						}
						$('#leadAgencyId').html(leadAgency);
						
						var partnerAgency = '<option value="">--- Select Partner Agency ---</option>';
						var partnerAgencys = data.partnerAgency;
						for (var i = 0; i < partnerAgencys.length; i++) {
							partnerAgency += '<option value="'	+ partnerAgencys[i].id + '">' + partnerAgencys[i].code + '</option>';
						}
						$('#partnerAgencyId').html(partnerAgency);
						$('#add_Sub_Activity').show();
						$('#update_Sub_Activity').hide();
						$('#subActivityModal').modal('show')
					}
				}
			});
		}
		
		function editSubActivity(subActvityId) {
		
			$('#subActNameError').html("");
			$('#leadAgencyError').html("");
			$('#partnerAgencyError').html("");
			$('#movError').html("");
			
			$.ajax({
				url : '/ndcmp/api/editSubActivity',
				data : {
					subActvityId : subActvityId
				},
				type : 'GET',
				async : false,
				dataType : 'json',
				success : function(data) {
					output_id=data.keyActivity.output.id;
					if (data && data.trim != '') {
						
						$('#subActivityName').val(data.name);
						$('#key').val(data.keyActivity.id);
						$('#subKeyActivityName').val(data.keyActivity.name);
						
						$('#mov').val(data.mov);
						$('#subActivityId').val(data.subActivity.id);
						var leadAgency = '<option value="">--- Select Responsible Entity ---</option>';
						var leadAgencys = data.leadAgency;
//						leadAgency += '<option selected=selected value="'	+ leadAgencys.id + '">' + leadAgencys.name + '</option>';
						for (var i = 0; i < leadAgencys.length; i++) {
							leadAgency += '<option id="lead'+leadAgencys[i].id+'" value="'	+ leadAgencys[i].id + '">' + leadAgencys[i].code + '</option>';
						}
						$('#leadAgencyId').html(leadAgency);	
						
						var partnerAgency = '<option value="">--- Select Partner Agency ---</option>';
						var partnerAgencys = data.partnerAgency;
						for (var i = 0; i < partnerAgencys.length; i++) {
							partnerAgency += '<option value="'	+ partnerAgencys[i].id + '">' + partnerAgencys[i].code + '</option>';
						}
						$('#partnerAgencyId').html(partnerAgency);
						var partnerAgency=data.subActivity.partnerAgency;
						$.each(partnerAgency,function(pi,pv){
							$('#partnerAgencyId option[value='+pv.id+']').attr('selected','selected');	
						});
						
						var leadAgency=data.subActivity.leadAgency.id;
						$('#lead'+leadAgency).attr('selected',true);
						
						$('#key').val(data.keyActivity.id);
						
						
						$('#add_Sub_Activity').hide();
						$('#update_Sub_Activity').show();
						$('#subActivityModal').modal('show');
					}
				}
			});
		}
		
		
		// Add Indicator modal window
		function indicatorModal(outputID){
			$('#msgError').html("");
			$('#indicatorName').val("");
			$.ajax({
				url : '/ndcmp/api/getIndicator',
				data : {
					outputID : outputID
				},
				type : 'GET',
				async : false,
				dataType : 'json',
				success : function(data){
					if(data != null){
						var outputName = data.output.output;
						$('#IndicatorOutputName').val(outputName);
						$('#outputIndicatorId').val(data.output.id);
						$('#IndicatorId').val(0);
						$('#add_Indicator').show();
						$('#update_Indicator').hide();
						$('#indicatorModal').modal('show');
					}
				}
			});
		}
		
		function saveIndicator(){
			var message=$('#indicatorName').val();
			var outputIndicatorId=$('#outputIndicatorId').val();
			var id =$('#IndicatorId').val();
			
			if (message == "") {
				$('#msgError').html(
						"Message is required");
				return false;
			}else{
				$('#msgError').html("");
				
				var indicatorJson={
						"message" : message,
						"outputIndicatorId" : outputIndicatorId,
						"id" : id
					};
					
				
				$.ajax({
					url : '/ndcmp/api/saveIndicator',
					data : {
						"indicatorJson" : JSON.stringify(indicatorJson)
					},
					type : 'GET',
					async : false,
					dataType : 'json',
					success : function(data){
						if(data.error != null){
							$('#msgError').html(data.error);
						}else{
							$('#msgError').html("");
							
							if(id == 0){
								var parentId="#"+data.output.id+"_parentIndDiv";
								var txt='<div id="'+data.id+'_indicator" class="col-md-12"><div class="col-md-10">'+data.message+'</div><div class="col-md-2"><a class="table-link" title="Edit Indicator" onclick="editIndicator('+data.id+');" style="margin: 0px -188px -2px 150px;" <span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i></span> </a></div></div><br /><br />';
								$(parentId).append(txt);
							}else{
								var divId="#"+data.id+"_indicator";
								//$(divId).empty();
								
								var txt='<div id="'+data.id+'_indicator" class="col-md-12"><div class="col-md-10">'+data.message+'</div><div class="col-md-2" style="height:33px;"><a class="table-link" title="Edit Indicator" onclick="editIndicator('+data.id+');" style="margin: 0px -188px -2px 150px;" <span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i></span> </a></div></div>';
								$(divId).replaceWith(txt);
							}
							
							
							$('#indicatorModal').modal('hide');
						}
					}
				});
			}
		}
		
		// edit Indicator modal window
		function editIndicator(indicatorId){
			//$('#msgError').html("");
			$.ajax({
				url : '/ndcmp/api/editIndicator',
				data : {
					indicatorId : indicatorId
				},
				type : 'GET',
				async : false,
				dataType : 'json',
				success : function(data){
					if(data != null){
						var outputName = data.output.output;
						var name = data.message;
						$('#indicatorName').val(name);
						$('#IndicatorId').val(data.id);
						$('#outputIndicatorId').val(data.output.id);
						$('#IndicatorOutputName').val(outputName);
						$('#add_Indicator').hide();
						$('#update_Indicator').show();
						$('#indicatorModal').modal('show');
					}
				}
			});
		}
		
		// Add Indicator modal window
		function targetModal(outputId){
			$('#targetId').val(0);
			$('#targetName').val("");
			$.ajax({
				url : '/ndcmp/api/getTarget',
				data : {
					outputId : outputId
				},
				type : 'GET',
				async : false,
				dataType : 'json',
				success : function(data){
					if(data != null){
						var outputName = data.output.output;
						/* var name = data.message; */
						/* $('#targetName').val(name); */
						$('#targetOutputName').val(outputName);
						$('#outputTargetId').val(data.output.id);
						$('#add_Target').show();
						$('#update_Target').hide();
						$('#TargetModal').modal('show');
					}
				}
			});
		}
		
		// edit Indicator modal window
		function editTarget(targetId){
			
			$.ajax({
				url : '/ndcmp/api/editTarget',
				data : {
					targetId : targetId
				},
				type : 'GET',
				async : false,
				dataType : 'json',
				success : function(data){
					if(data != null){
						$('#targetError').html("");
						var outputName = data.output.output;
						var name = data.message;
						$('#outputTargetId').val(data.output.id);
						$('#targetId').val(data.id);
						$('#targetName').val(name);
						$('#targetOutputName').val(outputName);
						$('#add_Target').hide();
						$('#update_Target').show();
						$('#TargetModal').modal('show');
					}
				}
			});
		}
		function save_Target(){
			var target=$('#targetName').val();
			var outputTargetId=$('#outputTargetId').val();
			var id=$('#targetId').val();
			
			
			if (target == "") {
				$('#targetError').html(
						"Target is required");
				return false;
			}else{
				$('#targetError').html("");
				
				var targetJson={
					"id" : id,
					"target" : target,
					"outputId" : outputTargetId
				};
				
				$.ajax({
					url : '/ndcmp/api/saveTarget',
					data : {
						"targetJson" : JSON.stringify(targetJson)
					},
					type : 'GET',
					async : false,
					dataType : 'json',
					success : function(data){
						if(data.error != null){
							$('#targetError').html(data.error);
						}else{
							$('#targetError').html("");
							
							if(id == 0){
								var parentId="#"+data.output.id+"_parentTarDiv";
								var txt='<div id="'+data.id+'_target" class="col-md-12"><div class="col-md-10">'+data.message+'</div><div class="col-md-2"><a href="#" class="table-link" title="Edit Indicator" onclick="editTarget('+data.id+');" style="margin: 0px -188px -2px 150px;" <span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i></span> </a></div></div><br /><br />';
								$(parentId).append(txt);
							}else{
								var divId="#"+data.id+"_target";
								//$(divId).empty();
								
								var txt='<div id="'+data.id+'_target" class="col-md-12"><div class="col-md-10">'+data.message+'</div><div class="col-md-2"><a href="#" class="table-link" title="Edit Indicator" onclick="editTarget('+data.id+');" style="margin: 0px -188px -2px 150px;" <span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i></span> </a></div></div>';
								$(divId).replaceWith(txt);
							}
							
							
							$('#TargetModal').modal('hide');
						}
					}
				});
			}
		}
		$('#cancel').click(function() {
			window.history.back();
		});

		function submitStrategicPillarForm() {

			$('#addStrategicPillarForm').formValidation({
				icon : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				fields : {
					name : {
						validators : {
							notEmpty : {
								message : 'StrategicPillar Name is Required'
							}
						}
					},
					description : {
						validators : {
							notEmpty : {
								message : 'Description is Required'
							}
						}
					},
					'project.id' : {
						validators : {
							notEmpty : {
								message : 'Project is Required'
							}
						}
					},
				}
			});

			return true;
		};

		function submitThemeForm() {
			$('#themeAddform')
					.formValidation(
							{
								icon : {
									valid : 'glyphicon glyphicon-ok',
									invalid : 'glyphicon glyphicon-remove',
									validating : 'glyphicon glyphicon-refresh'
								},
								fields : {
									name : {
										validators : {
											notEmpty : {
												message : 'Theme Name is Required'
											},
											stringLength : {
												min : 3,
												max : 250,
												message : 'Theme Name must be more than 3 and less than 25 characters long'
											}
										}
									},
									'strategicPillar.name' : {
										validators : {
											notEmpty : {
												message : 'Description is Required'
											},
										}
									}
								}
							});
			return true;
		};
		

		function submitOutcomeForm() {
			$('#outcomeAddform')
					.formValidation(
							{
								icon : {
									valid : 'glyphicon glyphicon-ok',
									invalid : 'glyphicon glyphicon-remove',
									validating : 'glyphicon glyphicon-refresh'
								},
								fields : {
									name : {
										validators : {
											notEmpty : {
												message : 'Outcome Name is Required'
											},
											stringLength : {
												min : 3,
												max : 250,
												message : 'Outcome Name must be more than 3 and less than 25 characters long'
											}
										}
									},
									'theme.name' : {
										validators : {
											notEmpty : {
												message : 'Theme Name is Required'
											},
										}
									}
								}
							});
			return true;
		};

		function submitOutputForm() {
			$('#outputAddform')
					.formValidation(
							{
								icon : {
									valid : 'glyphicon glyphicon-ok',
									invalid : 'glyphicon glyphicon-remove',
									validating : 'glyphicon glyphicon-refresh'
								},
								fields : {
									name : {
										validators : {
											notEmpty : {
												message : 'Output Name is Required'
											},
											stringLength : {
												min : 3,
												max : 250,
												message : 'Output Name must be more than 3 and less than 25 characters long'
											}
										}
									},
									'outcome.name' : {
										validators : {
											notEmpty : {
												message : 'Outcome Name is Required'
											},
										}
									}
								}
							});
			return true;
		};
		
		function submitKeyActivityForm() {
			$('#keyActivityAddform')
					.formValidation(
							{
								icon : {
									valid : 'glyphicon glyphicon-ok',
									invalid : 'glyphicon glyphicon-remove',
									validating : 'glyphicon glyphicon-refresh'
								},
								fields : {
									name : {
										validators : {
											notEmpty : {
												message : 'Key Activity is Required'
											},
											stringLength : {
												min : 3,
												max : 250,
												message : 'Key Activity must be more than 3 and less than 25 characters long'
											}
										}
									},
									'output.output' : {
										validators : {
											notEmpty : {
												message : 'Output Name is Required'
											},
										}
									}
								}
							});
			return true;
		};
		
		
		function submitKeyActivityForm() {
			$('#subActivityAddform')
					.formValidation(
							{
								icon : {
									valid : 'glyphicon glyphicon-ok',
									invalid : 'glyphicon glyphicon-remove',
									validating : 'glyphicon glyphicon-refresh'
								},
								fields : {
									name : {
										validators : {
											notEmpty : {
												message : 'Sub Activity is Required'
											},
											stringLength : {
												min : 3,
												max : 250,
												message : 'Sub Activity must be more than 3 and less than 25 characters long'
											}
										}
									},
									'leadAgency.name' : {
										validators : {
											notEmpty : {
												message : 'Responsible Entity is Required'
											},
										}
									},
									'partnerAgency.name' : {
										validators : {
											notEmpty : {
												message : 'Partner Agency is Required'
											},
										}
									},
									mov : {
										validators : {
											notEmpty : {
												message : 'MOV is Required'
											},
										}
									}
								}
							});
			return true;
		};
		
		/* Indicator message validation */
		function submitIndicatorAddform() {
			$('#indicatorAddform').formValidation(
				{
					icon : {
						valid : 'glyphicon glyphicon-ok',
						invalid : 'glyphicon glyphicon-remove',
						validating : 'glyphicon glyphicon-refresh'
					},
					fields : {
						message : {
							validators : {
								notEmpty : {
									message : 'Message is Required'
								}
							}
						},
						'output.output' : {
							validators : {
								notEmpty : {
									message : 'Output Name is Required'
								},
							}
						}
					}
				});
			return true;
		};

		function projectAdminHierarchy() {
			var projectId = 1;
			var action = 'config';
			$.ajax({
				url : '/ndcmp/api/configProject',
				data : {
					projectId : projectId,
					action : action
				},
				type : 'GET',
				async : false,
				dataType : 'json',
				contentType: "application/json",
				success : function(data){
					if(data.length != 0){
						var modalBody = '<div class="panel-group accordion" id="accordion">'
						+ '<div align="right" style="margin-bottom: 0px;">'
									+ '<button type="button" id="strategic-1" title="Add Strategic Pillar" style="width: 140px; margin: 0px -155px -34px 0px" class="btn btn-primary btn-add-panel" data-toggle="modal" data-target="#addStrategicPillarModal">New Pillar</button>'
									+ '</div>';
						for(var i = 0;i < data.length; i++){
									
							modalBody += '<div class="panel panel-default">'
										+ '<div class="panel-heading" style="background-color: #2C3E50;">'
											+ '<h4 class="panel-title">'
											+ '<a class="accordion-toggle" data-toggle="collapse" id="strategic_'+ data[i].id +'" data-parent="#accordion" href="#collapseStrategic_'+ data[i].id +'">'
											+ '<span id="str_'+data[i].id+'"><b>'+data[i].sequenceNumber+". "+data[i].name +' </b></span></a></h4></div>'
											  	
										+ '<div id="collapseStrategic_'+ data[i].id +'" class="panel-collapse collapse in strategic-pillar">'
											+ '<div class="panel-body">'
												+ '<div class="panel-group accordion" id="accordionOne_'+ data[i].id +'">'
													+ '<div id="themeAdd-'+data[i].id+'">'
														+ '<div align="right" style="margin-top: -42px;">'
																+ '<a title="Edit Strategic Pillar" onclick="editStrategicPillar('+data[i].id+');" style="cursor:pointer;margin: 0px -40px 3px 150px;" <span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i></span> </a>'
														+ '</div>'
														+ '<div align="right" style="margin-bottom: -22px">'
															+ '<button type="button" id="strategic-1" title="Add Theme" style="margin: 12px -155px -13px 0px;width: 140px;" class="btn btn-primary btn-add-panel1" onClick="addThemeModal('+data[i].id+')">New Theme</button>'
														+ '</div>'
													+ '</div>';
								themes = data[i].themes;
								if(theme != null) {
								for(var j =0;j < themes.length; j++){
									modalBody += '<div class="panel panel-default template1">'
										+ '<div class="panel-heading" style="background-color: #4D5B69;">'
											+ '<h4 class="panel-title">'
											+ '<a class="accordion-toggle" data-toggle="collapse" id="themeAdd_'+data[i].id+'" data-parent="#accordionOne_'+ data[i].id +'" href="#collapseTheme_'+ themes[j].id +'">'
											+ '<span id="thm_'+themes[j].id+'"><b>'+ themes[j].name +'</b></span></a></h4></div>'
											+ '<div id="collapseTheme_'+ themes[j].id +'" class="panel-collapse collapse in theme">'
												+ '<div class="panel-body">'
													+ '<div class="panel-group accordion" id="accordionOutput_'+ themes[j].id +'">'
														+ '<div id="outcomeAdd-'+themes[j].id+'">'
															+ '<div align="right" style="margin-top: -42px;">'
																+ '<a title="Edit Theme" onclick="editTheme('+themes[j].id+');" style="cursor:pointer;margin: 0px -40px 3px 150px" <span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i></span> </a>'
															+ '</div>'
															+ '<div align="right" style="margin-bottom: -22px">'
																+ '<button type="button" id="outcomeAdd" title="Add Outcome" style="margin: 13px -155px -14px 0px;width: 140px;" class="btn btn-primary btn-add-panel2" onclick="showOutcome()">New Outcome</button>'
															+ '</div>'
														+ '</div>';
									outcomes = themes[j].outcomes;
									if(outcomes != null){
										for(var k =0;k < outcomes.length; k++) {
											modalBody += '<div class="panel panel-default template2">'
													+ '<div class="panel-heading" style="background-color: #77818C;">'
													+ '<h4 class="panel-title">'
													+ '<a class="accordion-toggle" data-toggle="collapse" id="outcomeAdd_'+themes[j].id+'" data-parent="#accordionOutput_'+ themes[j].id +'" href="#collapseOutcome_'+ outcomes[k].id +'">'
													+ '<span id="ocome_'+outcomes[k].id+'"><b>'+outcomes[k].sequenceNumber+" . "+ outcomes[k].name +'</b></span></a></h4></div>'
													+ '<div id="collapseOutcome_'+ outcomes[k].id +'" class="panel-collapse collapse in outcome">'
													+ '<div class="panel-body">'
													+ '<div class="panel-group accordion" id="collapseOne_'+ outcomes[k].id +'">'
													+ '<div id="outputAdd-'+outcomes[k].id+'">'
													+ '<div align="right" style="margin-top: -42px;">'
													+ '<a title="Edit Outcome" onclick="editOutcome('+outcomes[k].id+');" style="cursor:pointer ; margin: 0px -40px 3px 150px" <span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i></span> </a>'
													+ '</div>'
													+ '<div align="right" style="margin-bottom: -22px">'
													+ '<button type="button" id="outputAdd" title="Add Output" style="margin: 16px -154px -16px 0px;width: 140px;" class="btn btn-primary btn-add-panel3" data-toggle="modal" data-target="#addOutputModal">New Output</button>'
													+ '</div>'
													+ '</div>';
											outputs = outcomes[k].outputs;
											if(outputs != null) {
												for(var m =0;m < outputs.length; m++){
													modalBody += '<div class="panel panel-default template3">'
															+ '<div class="panel-heading" style="background-color: #9C9EA0;">'
															+ '<h4 class="panel-title">'
															+ '<a class="accordion-toggle" id="outputAdd_'+outcomes[k].id+'" data-toggle="collapse" data-parent="#collapseOne_'+ outcomes[k].id +'" href="#collapseOutput_'+ outputs[m].id +'" onclick="loadKeyActivityByOutput('+outputs[m].id+')">'
															+ '<span id="output_'+outputs[m].id+'"><b>'+outputs[m].sequenceNumber+" . "+ outputs[m].output +'</b></span></a></h4></div>'
															+ '<div id="collapseOutput_'+ outputs[m].id +'" class="panel-collapse collapse in output">'
															+ '<div class="panel-body">'
															+ '<div class="panel-group accordion" id="collapseOne_'+ outcomes[k].id +'">'
															+ '<div id="keyActivityAdd">'
															+ '<div align="right" style="margin-top: -30px;">'
															+ '<a title="Edit Output" onclick="editOutput('+outputs[m].id+');" style="cursor:pointer ; margin: 0px -47px 3px 150px" <span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i></span> </a>'
															+ '</div>'
															+ '<div align="right" style="margin-bottom: -36px">'
															+ '<button type="button" id="outputAdd" title="Add KeyActivity" style="margin: 10px -161px -9px 0px; width: 140px;" class="btn btn-primary btn-add-panel4" onClick="newKeyActivity('+outputs[m].id+')">New Key Activity</button>'
															+ '</div>'
															+ '</div>';
													modalBody+='<div id="configDiv_'+outputs[m].id+'"></div>';
												/*keyActivities = outputs[m].keyActivities;
												if(keyActivities != null) {
													modalBody += '<div class="table-responsive dataTables-wrapper" style="width: 100%">'
														+ '<table id="planner-table'+ outputs[m].id +'" style="margin-left: 0px;" class="table table-hover table-bordered CSSTableGenerator">'
														+ '<thead><tr>'
														+ '<th>Sequence Number</th>'
														+ '<th>Key Activity</th>'
														+ '<th>Sub Activity</th>'
														+ '<th>Responsible Entity</th>'
														+ '<th>Partnering Agency</th>'
														+ '<th>MOV</th>'
														+ '<th width="71px">Actions</th>'
														+ '</tr></thead>'
													for(var n =0;n < keyActivities.length; n++){
														modalBody += '<tbody><tr>'
																+ '<td>'+keyActivities[n].sequenceNumber+'</td>'
																+ '<td>'+keyActivities[n].name+'</td>'
																+ '<td></td>'
																+ '<td></td>'
																+ '<td></td>'
																+ '<td></td>'
																+ '<td><a style="cursor:pointer" title="Add SubActivity" onclick="openSubActivtyModal('+keyActivities[n].id+');" style="color: red; text-decoration: none;" <img style="height: 24px; margin-top: -2px;" src="../static/img/add.png"></a>'
																+ '<a style="cursor:pointer" title="Edit KeyActivity" onclick="editKeyActivity('+keyActivities[n].id+')" <span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i></span> </a>'+
																'<a style="cursor:pointer" title="Add SubActivity" style="color: red; text-decoration: none;" onclick="openSubActivtyModal('+keyActivities[n].id+');"><img style="height: 24px; margin-top: -2px;" src="../static/img/add.png"></a></td>'
																+ '</tr>'
																subActivities = keyActivities[n].subActivities;
																if(subActivities != null) {
																	for(var o =0;o < subActivities.length; o++) {
																		modalBody += '<tr>'
																				+ '<td>'+subActivities[o].sequenceNumber+'</td>'
																				+ '<td></td>'
																				+ '<td>'+subActivities[o].name+'</td>'
																				+ '<td>'+subActivities[o].leadAgency.code+'</td>'
																				partnerAgency = subActivities[o].partnerAgency;
																				if(partnerAgency != null) {
																					var partnerAgencies=[];
																					for(var partner = 0; partner < partnerAgency.length; partner++) {
																						var pa = partnerAgency[partner].code;
																						partnerAgencies.push(pa);
																					}
																					modalBody += '<td>'+partnerAgencies+'</td>'
																				}
																				modalBody += '<td>'+subActivities[o].mov+'</td>'
																				+ '<td><div >'
																				+ '<a style="cursor:pointer" title="Edit SubActivity" onclick="editSubActivity('+subActivities[o].id+')" <span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i></span> </a>';
																				if(subActivities[o].status == 'ACTIVE') {
																					modalBody += '<a style="cursor:pointer" class="table-link danger" id="deAct_'+subActivities[o].id+'_'+outputs[m].id+'" title="Deactivate Sub Activity" onclick="actSubAct(this.id)" ><span class="fa-stack"><i class="fa fa-square fa-stack-2x"></i><i class="fa fa-times fa-stack-1x fa-inverse"></i></span></a>';
																				} else {
																					modalBody += '<a style="cursor:pointer" class="table-link" id="act_'+subActivities[o].id+'_'+outputs[m].id+'" title="Activate Sub Activity"  onclick="actSubAct(this.id)"><span class="fa-stack" ><i class="fa fa-square fa-stack-2x"></i><i class="fa fa-check fa-stack-1x fa-inverse"></i></span></a>';
																				}
																				modalBody += '</div>';
																	}
																}
													}
													modalBody += '</tbody></table></div>'
												}*/
												/*indicators = outputs[m].indicators;
												if(indicators != null) {
													modalBody += '<div align="right" style="margin-bottom: 0px">'
															+ '<button type="button" id="indicatorAdd" style="margin: 0px -158px 0px 0px; width: 140px;" class="btn btn-primary" onclick="indicatorModal('+outputs[m].id+');">New Indicator</button>'
															+ '</div>'
													+ '<div id="'+outputs[m].id+'_parentIndDiv"><div><b>Indicators : </b></div>';
													for(var p=0; p<indicators.length; p++) {
														modalBody +=  '<div align="right" style="margin-top: -30px">'
														+ '<a href="#" class="table-link" title="Edit Indicator" onclick="editIndicator('+indicators[p].id+');" style="margin: 0px -188px -2px 150px;" <span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i></span> </a>'
														+ '</div>'
														+ '<tr><td>'+indicators[p].message+'</td></tr></table>'
														modalBody +='<div id="'+indicators[p].id+'_indicator" class="col-md-12"><div class="col-md-10">'+indicators[p].message+'</div><div class="col-md-2"><a href="#" class="table-link" title="Edit Indicator" onclick="editIndicator('+indicators[p].id+');" style="margin: 0px -188px -2px 150px;" <span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i></span> </a></div></div><br /><br />';
													}
													modalBody+='</div>';
												}
												targets = outputs[m].targets;
												if(targets != null) {
													modalBody += '<div align="right" style="margin-bottom: 0px">'
														+ '<button type="button" id="targetAdd" style="margin: 0px -158px 0px 0px; width: 140px;" class="btn btn-primary" onclick="targetModal('+outputs[m].id+');">New Target</button>'
														+ '</div>'
														+ '<div id="'+outputs[m].id+'_parentTarDiv"><div><b>Target : </b></div>';
													for(var q=0; q<targets.length; q++) {
														modalBody +=  '<div align="right" style="margin-top: -30px">'
															+ '<a href="#" class="table-link" title="Edit Target" onclick="editTarget('+targets[q].id+');" style="margin: 0px -188px -2px 150px;" <span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i></span> </a>'
															+ '</div>'
														+ '<tr><td>'+targets[q].message+'</td></tr></table>'
														modalBody +='<div id="'+targets[q].id+'_target" class="col-md-12"><div class="col-md-10">'+targets[q].message+'</div><div class="col-md-2"><a style="cursor:pointer" class="table-link" title="Edit Target" onclick="editTarget('+targets[q].id+');" style="margin: 0px -188px -2px 150px;" <span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i></span> </a></div></div><br /><br />';
													}
													modalBody+='</div>'
												}*/
												modalBody += '</div></div></div></div>'
												}
											}
										modalBody += '</div></div></div></div>';
									}
								}
								modalBody += '</div></div></div></div>';
								}
								}
								modalBody += '</div></div></div></div>';		  
							}
							modalBody += '</div>';
							$('#projectAdminDiv').html(modalBody);
						}else{
							var t='<div align="right" style="margin-bottom: 0px;">'
									+ '<button type="button" id="strategic-1" style="width: 140px; margin: 0px -155px -34px 0px" class="btn btn-primary btn-add-panel" data-toggle="modal" data-target="#addStrategicPillarModal">New Pillar</button>'
									+ '</div>';
							$('#projectAdminDiv').html(t);
//							$('#strategic-1').show();
						}
				}
			});
		}
		
function actSubAct(di){
	var asa=di.split('_');
	
	
	act_deactId=asa[1];
	output_id=asa[2];
	if(asa[0] === "deAct"){
		act_deactAction="deActive";
		$('#subActivityDeactiveDialog').modal('show');	
	}else{
		act_deactAction="Active";
		$('#subActivityActiveDialog').modal('show');	
		
	}
	
	
}

function actDeactSubAct(){
	
	$.ajax({
		url : '/ndcmp/api/actDeActSubAct',
		data : {
			"act_deactAction" : act_deactAction,
			"sub_act_id" : act_deactId
		},
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data){
			/*$.ajax({
				url : '/ndcmp/api/getKeyActivitiesAndSubActivities',
				data : {
					"output_id" : output_id
				},
				type : 'GET',
				async : false,
				dataType : 'json',
				contentType: "application/json",
				success : function(data){
					var txt="";
					if(data.length != 0){
						var table=$('#planner-table'+data[0].id);
						
						txt+="<tbody>";
						if(data[0].keyActivities.length != null){
							if(data[0].keyActivities.length != 0){
								$.each(data[0].keyActivities,function(ki,kv){
									txt+="<tr>";
									txt+="<td >"+kv.sequenceNumber+"</td>";
									txt+="<td >"+kv.keyActivity+"</td>";
									txt+="<td ></td>";
									txt+="<td ></td>";
									txt+="<td ></td>";
									txt+="<td ></td>";
									txt+='<td ><a href="#" onclick="openSubActivtyModal('+kv.id+');" style="color: red; text-decoration: none;" <img style="height: 24px; margin-top: -2px;" src="../static/img/add.png"></a>'
									+ '<a style="cursor:pointer" title="Edit KeyActivity" onclick="editKeyActivity('+kv.id+');" <span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i></span> </a></td>';
									txt+="</tr>";
									if(kv.subActivities.length != null){
										if(kv.subActivities.length != 0){
											$.each(kv.subActivities,function(si,sv){
												txt+="<tr>";
												txt+="<td>"+sv.sequenceNumber+"</td>";
												txt+="<td></td>";
												txt+="<td>"+sv.subActivity+"</td>";
												txt+="<td>"+sv.leadAgency.agency+"</td>";
												txt+="<td></td>";
												txt+="<td>"+sv.mov+"</td>";
												txt+= '<td><div ><a style="cursor:pointer" title="Edit SubActivity" onclick="editSubActivity('+sv.id+');" <span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i></span> </a>';
												if(sv.status == 'ACTIVE') {
													txt += '<a href="#" class="table-link danger" id="deAct_'+sv.id+'_'+output_id+'" title="Deactivate Sub Activity" onclick="actSubAct(this.id)" ><span class="fa-stack"><i class="fa fa-square fa-stack-2x"></i><i class="fa fa-times fa-stack-1x fa-inverse"></i></span></a></td>';
												} else {
													txt += '<a href="#" class="table-link" id="act_'+sv.id+'_'+output_id+'" title="Activate Sub Activity"  onclick="actSubAct(this.id)"><span class="fa-stack" ><i class="fa fa-square fa-stack-2x"></i><i class="fa fa-check fa-stack-1x fa-inverse"></i></span></a></td>';
												}
												
												txt+="<td></td>";
												txt+="<tr>";
												
											});
										}
									}
								});
							}
						}
						
						txt+="</tbody>";
						
						$(table).find('tbody').remove();
						$(table).append(txt);
					}
				}
			});
			*/
			
			
			/*$(".accordion-toggle").collapse();
			$(".accordion-toggle").attr("aria-expanded","false");
			
			
			$('.accordion a').on('click', function() {
				var _this = $(this);
				var btnId="";
				var str=$(this).context.id.split('_');
				if(str[0] == "strategic"){
					btnId="#strategic-1";
				}else{
				btnId="#"+str[0]+'-'+str[1];
				}
				var _expanded = $(_this).attr('aria-expanded');
				if(_expanded == 'false') {
					//$('.btn .edit .delete').show();
					$(btnId).hide();
				} else if(_expanded == 'true') {
					//$('.btn .edit .delete').hide();
					$(btnId).show();
				}
			});*/
			if(role == "SUPER_ADMIN"){
				loadKeyActivityByOutput(data.keyActivity.output.id);
			}else{
				loadTable();	
			}
			
			if(act_deactAction === "deActive"){
				$('#subActivityDeactiveDialog').modal('hide');	
			}else{
				$('#subActivityActiveDialog').modal('hide');	
			}
		}
	});
}
function saveSubActivity(){
	//var subActivity=$('#subActivityAddform').serialize();
	//console.log(subActivity);
	var validation="";
	var name=$('#subActivityName').val();
	var leadAgencyId=$('#leadAgencyId').val();
	var partnerAgencyId=$('#partnerAgencyId').val();
	var mov=$('#mov').val();
	
	if(partnerAgencyId == null){
		partnerAgencyId=0;
	}
	var key=$('#key').val();
	

	if(leadAgencyId != "" && mov != "" && name != ""){
		validation = "true";
	}else{
		if (name == "") {
			$('#subActNameError').html(
					"Sub Activity is required");
		} else {
			$('#subActNameError').html("");
		}
		if (leadAgencyId == "") {
			$('#leadAgencyError').html(
					"Lead Agency is required");
		} else {
			$('#leadAgencyError').html("");
		}
		
		/*if (partnerAgencyId == null) {
			$('#partnerAgencyError').html(
					"Partner Agency is required");
		} else {ocome_
			$('#partnerAgencyError').html("");
		}*/
		/*if (mov == "") {
			$('#movError').html(
					"MOV is required");
		} else {
			$('#movError').html("");
		}*/
	}
	
	if(validation == "true"){
		$('#subActNameError').html("");
		$('#leadAgencyError').html("");
		$('#partnerAgencyError').html("");
		$('#movError').html("");
		
		
		var subActivity={
				"name" : name,
				"id" : $('#subActivityId').val(),
				"leadAgency" : leadAgencyId,
				"mov" : mov,
				"keyActivity" : key
				};
						
		$.ajax({
			url : '/ndcmp/api/saveSubActivity1',
			data : {
				"subActivityJson" : JSON.stringify(subActivity),
				"partner" :JSON.stringify( partnerAgencyId),
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data){
				if(data.error != null){
					$('#subActNameError').html(data.error);
				}else{
					output_id=data.keyActivity.output.id;
					loadTable();
					$('#subActivityModal').modal('hide');	
				}
				
			}
		});
	}else{
		return false;
	}
	
	
}

function saveKeyActivity(){
	
	var name=$('#keyActivityName').val();
	
	if(name == ""){
		$('#keyActNameError').html("Key Activity Name is required");
		return false;
	}else{
		var keyActivityJson={
				"id" : $('#keyActivityId').val(),
				"outputId" :$('#outputKeyActivityId').val(),
				"name" : name
		};
		$.ajax({
			url : '/ndcmp/api/saveKeyActivity',
			data : {
				"keyActivityJson" : JSON.stringify(keyActivityJson)
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			contentType: "application/json",
			success : function(data){
				if(data.error != null){
					$('#keyActNameError').html(data.error);
				}else{
					loadTable();
					$('#keyActivityModal').modal('hide');
				}
				
			}
		});
	}
	
}

function loadTable(){
	$.ajax({
		url : '/ndcmp/api/getKeyActivitiesAndSubActivities',
		data : {
			"output_id" : output_id
		},
		type : 'GET',
		async : false,
		dataType : 'json',
		contentType: "application/json",
		success : function(data){
			var d=data;
			var txt="";
			 var dataTable = new Array();
			if(data.length != 0){
				var table=$('#planner-table'+data.id);
				
				if(table.length == 0){
					var t='<div class="table-responsive dataTables-wrapper" style="width: 100%"><table id="planner-table'+data.id+'" style="margin-left: 0px;" class="table table-hover table-bordered CSSTableGenerator"></table></div>';
					$(t).insertBefore('#'+output_id+'_parentIndDiv');
						
				}
				
					if(data.keyActivities.length != 0){
						var keyActivities=data.keyActivities;
						$.each(keyActivities,function(ki,kv){
							var arr = new Array();
										arr = new Array();
										arr.push(kv.sequenceNumber);
										arr.push(kv.name);
										arr.push("");
										arr.push("");
										arr.push("");
										arr.push("");
										arr.push('<a style="cursor:pointer" title="Edit KeyActivity"  onclick="openSubActivtyModal('+kv.id+');" style="color: red; text-decoration: none;" <img style="height: 24px; margin-top: 1px;" src="../static/img/add.png"></a>'+
												'<a style="cursor:pointer" title="Edit KeyActivity" onclick="editKeyActivity('+kv.id+');" <span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i></span> </a>'+
												'<a style="cursor:pointer"  title="Add SubActivity" style="color: red; text-decoration: none;" onclick="openSubActivtyModal('+kv.id+');"><img style="height: 24px; margin-top: 1px;" src="../static/img/add.png"></a></td>');
										//dataTable.push(arr);
							dataTable.push(arr);
							
							if(kv.subActivities != null){
								if(kv.subActivities.length != 0){
									/*var arr = new Array();
									arr.push(kv.sequenceNumber);
									arr.push(kv.name);
									arr.push("");
									arr.push("");
									arr.push("");
									arr.push("");
									arr.push('<a style="cursor:pointer" title="Edit KeyActivity"  onclick="openSubActivtyModal('+kv.id+');" style="color: red; text-decoration: none;" <img style="height: 24px; margin-top: 1px;" src="../static/img/add.png"></a>'+
											'<a style="cursor:pointer" title="Edit KeyActivity" onclick="editKeyActivity('+kv.id+');" <span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i></span> </a>'+
											'<a style="cursor:pointer"  title="Add SubActivity" style="color: red; text-decoration: none;" onclick="openSubActivtyModal('+kv.id+');"><img style="height: 24px; margin-top: 1px;" src="../static/img/add.png"></a></td>');
									dataTable.push(arr);*/
									
									if(kv.subActivities != null){
										if(kv.subActivities.length != 0){
											$.each(kv.subActivities,function(si,sv){
												var arr=new Array();
												arr.push(sv.sequenceNumber);
												arr.push("");
												arr.push(sv.name);
												arr.push(sv.leadAgency.code);
												
												var agency="";
												$.each(sv.partnerAgency,function(ai,av){
													agency+=av.code+", ";
												});
												var sub=agency.substring(0, agency.length-1);
												agency.replace(/:sub/,"");
												
												arr.push(sub);
												arr.push(sv.mov);
												
												var txt="";
												
												if(sv.approveORCompleteStatus == false){
													txt+= '<a style="cursor:pointer" title="Edit SubActivity" onclick="editSubActivity('+sv.id+');" <span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i></span> </a>';
													if(sv.status == 'ACTIVE') {
														txt += '<a style="cursor:pointer" class="table-link danger" id="deAct_'+sv.id+'_'+output_id+'" title="Deactivate Sub Activity" onclick="actSubAct(this.id)" ><span class="fa-stack"><i class="fa fa-square fa-stack-2x"></i><i class="fa fa-times fa-stack-1x fa-inverse"></i></span></a>';
													} else {
														txt += '<a style="cursor:pointer" class="table-link" id="act_'+sv.id+'_'+output_id+'" title="Activate Sub Activity"  onclick="actSubAct(this.id)"><span class="fa-stack" ><i class="fa fa-square fa-stack-2x"></i><i class="fa fa-check fa-stack-1x fa-inverse"></i></span></a>';
													}
												}else{
													txt+="";
												}
												arr.push(txt);
												
												dataTable.push(arr);
											});
										}
									}
								}
							}
						});
					}
				//table=$('#planner-table'+data.id);
				
				/*txt+="<tbody>";
				if(data.keyActivities.length != null){
					if(data.keyActivities.length != 0){
						$.each(data.keyActivities,function(ki,kv){

							txt+="<tr>";
							txt+="<td >"+kv.sequenceNumber+"</td>";
							txt+="<td >"+kv.name+"</td>";
							txt+="<td ></td>";
							txt+="<td ></td>";
							txt+="<td ></td>";
							txt+="<td ></td>";
							txt+='<td ><a style="cursor:pointer" title="Edit KeyActivity"  onclick="openSubActivtyModal('+kv.id+');" style="color: red; text-decoration: none;" <img style="height: 24px; margin-top: -2px;" src="../static/img/add.png"></a>';
							txt+='<a style="cursor:pointer" title="Edit KeyActivity" onclick="editKeyActivity('+kv.id+');" <span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i></span> </a>';
							txt+='<a style="cursor:pointer"  title="Add SubActivity" style="color: red; text-decoration: none;" onclick="openSubActivtyModal('+kv.id+');"><img style="height: 24px; margin-top: -2px;" src="../static/img/add.png"></a></td>';
							txt+="</tr>";
							if(kv.subActivities != null){
								if(kv.subActivities.length != 0){
									$.each(kv.subActivities,function(si,sv){
										var style="";
										
										if(sv.approveORCompleteStatus == true){
											style="background-color:#a4d083;";
										}else{
											style="";
										}
										
										
										txt+="<tr style='"+style+"'>";
										txt+="<td>"+sv.sequenceNumber+"</td>";
										txt+="<td></td>";
										txt+="<td>"+sv.name+"</td>";
										txt+="<td>"+sv.leadAgency.code+"</td>";
										
										var agency="";
										$.each(sv.partnerAgency,function(ai,av){
											agency+=av.code+",";
										});
										var sub=agency.substring(0, agency.length-1);
										agency.replace(/:sub/,"");
										
										txt+="<td>"+sub+"</td>";
										txt+="<td>"+sv.mov+"</td>";
										if(sv.approveORCompleteStatus == false){
											txt+= '<td><div ><a style="cursor:pointer" title="Edit SubActivity" onclick="editSubActivity('+sv.id+');" <span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i></span> </a>';
											if(sv.status == 'ACTIVE') {
												txt += '<a style="cursor:pointer" class="table-link danger" id="deAct_'+sv.id+'_'+output_id+'" title="Deactivate Sub Activity" onclick="actSubAct(this.id)" ><span class="fa-stack"><i class="fa fa-square fa-stack-2x"></i><i class="fa fa-times fa-stack-1x fa-inverse"></i></span></a></td>';
											} else {
												txt += '<a style="cursor:pointer" class="table-link" id="act_'+sv.id+'_'+output_id+'" title="Activate Sub Activity"  onclick="actSubAct(this.id)"><span class="fa-stack" ><i class="fa fa-square fa-stack-2x"></i><i class="fa fa-check fa-stack-1x fa-inverse"></i></span></a></td>';
											}
										}else{
											txt+="<td></td>";
										}
										
										txt+="<td></td>";
										txt+="<tr>";
										
									});
								}
							}
						});
					}
				}*/
				
				/*txt+="</tbody>";
				
				$(table).find('tbody').remove();
				$(table).append(txt);*/
				t= 'planner-table'+data.id;
				 $('#'+t+' thead').empty();
				 $('#'+t+'  tbody').empty();
				 $('#'+t+'  tr').empty();
				   $('#'+t).empty();
				
				 var columnJson=eval('[{"COLUMNS":[{"title" : "Sequence Number","sClass" : "text-center"},{ "title": "Key Activity","sWidth": "100px","sClass" : "text-center"},{"title" : "Sub Activity","sClass" : "text-center"},{"title":"Responsible Entity","sClass" : "text-center"},{"title" :"Partnering Agency","sClass" : "text-center"},{"title" :"MOV","sClass" : "text-center"},{"title":"Actions","sClass" : "text-center"}]}]');
				 
				  table = $('#planner-table'+data.id).DataTable( {
				    	"bDestroy": true,
				    	"bPaginate" : false,
				    	"bFilter" : false,
				    	"bInfo" :false,
				    	"bSort" : false,
				    	"bLengthChange": false,
						"aoColumns" : columnJson[0].COLUMNS
				    } );
				 table.rows.add(dataTable);
		            table.draw();
		            $('#'+t+' th').css('text-align','center');
		            $('#'+t+' td').css('text-align','left');
			
		            if(d.keyActivities.length != 0){
						var keyActivities=d.keyActivities;
						$.each(keyActivities,function(ki,kv){
							var subActivities=kv.subActivities;
							if(subActivities != null){
								if(subActivities.length > 0){
									
									$.each(subActivities,function(si,sv){
										if(sv.approveORCompleteStatus == true){
											if($('#'+t+' tbody tr:contains('+sv.sequenceNumber+')')){
											//	$('#filterTable td:contains('+sv.sequenceNumber+')').closest('tr').css('background-color','#a4d083');
												var unChecked=$('#'+t+' tbody td:contains("'+sv.sequenceNumber+'")').closest('tr').css('background-color','#a4d083');
											}
										}
									});
								}
							}
						});
					}   
		            
			}
		}
	});
}

function addStrategicPillar(){
	var strategicPillarName=$('#strategicPillarName').val();
	var pillarSN=$('#pillarSN').val();
	var theme=$('#theme').val();
	var outcomeSN=$('#outcomeSN').val();
	var outcome=$('#outcome').val();
	var outputSN=$('#outputSN').val();
	var output=$('#output').val();
	var indicator=$('#indicator').val();
	var target=$('#target').val();
	var validation="";
	$('#strategicPillarId').val(0);
	var id=$('#strategicPillarId').val();
	
	
	if(strategicPillarName != "" && pillarSN != "" && theme != "" && outcomeSN != "" && outcome != "" && output != "" && indicator != "" && target != "" && outputSN != ""){
		validation = "true";
	}else{
		if (strategicPillarName == "") {
			$('#strategicPillarNameError').html(
					"Strategic Pillar Name is required");
		} else {
			$('#strategicPillarNameError').html("");
		}
		if (pillarSN == "") {
			$('#pillarSNError').html(
					"Sequence Number of Strategic Pillar is required");
		} else {
			$('#pillarSNError').html("");
		}
		
		if (theme == "") {
			$('#themeError').html(
					"Theme is required");
		} else {
			$('#themeError').html("");
		}
		if (outcomeSN == "") {
			$('#outcomeSNError').html(
					"Sequence Number of Outcome is required");
		} else {
			$('#outcomeSNError').html("");
		}
		if (outcome == "") {
			$('#outcomeError').html(
					"Outcome is required");
		} else {
			$('#outcomeError').html("");
		}
		if (outputSN == "") {
			$('#outputSNError').html(
					"Sequence Number of Output is required");
		} else {
			$('#outputSNError').html("");
		}
		if (output == "") {
			$('#outputError').html(
					"Output Number of Output is required");
		} else {
			$('#outputError').html("");
		}
		if (indicator == "") {
			$('#indicatorError').html(
					"Indicator Number of Output is required");
		} else {
			$('#indicatorError').html("");
		}
		if (target == "") {
			$('#targetError').html(
					"Target Number of Output is required");
		} else {
			$('#targetError').html("");
		}
	}
	
	if(validation == "true"){
		$('#strategicPillarNameError').html("");
		$('#pillarSNError').html("");
		$('#themeError').html("");
		$('#outcomeSNError').html("");
		$('#outcomeError').html("");
		$('#outputSNError').html("");
		$('#outputError').html("");
		$('#indicatorError').html("");
		$('#targetError').html("");
		
		var strategicPillarJson={
				"id" : id,
				"strategicPillarName" : strategicPillarName,
				"pillarSN" : pillarSN,
				"theme" : theme,
				"outcomeSN" : outcomeSN,
				"outcome" : outcome,
				"outputSN" : outputSN,
				"output" : output,
				"indicator" : indicator,
				"target" : target
				
		};
		
		$.ajax({
			url : '/ndcmp/api/saveStrategicPillar',
			data : {
				"strategicPillarJson" : JSON.stringify(strategicPillarJson)
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			contentType: "application/json",
			success : function(data){
				
				if(data.strategicPillarNameError != null || data.pillarSNError != null){
					if(data.strategicPillarNameError != null){
						$('#strategicPillarNameError').html(data.strategicPillarNameError);
					}
					if(data.pillarSNError != null){
						$('#pillarSNError').html(data.pillarSNError);
					}
				}else{
					$('#strategicPillarNameError').html("");
					$('#pillarSNError').html("");
					$('#themeError').html("");
					$('#outcomeSNError').html("");
					$('#outcomeError').html("");
					$('#outputSNError').html("");
					$('#outputError').html("");
					$('#indicatorError').html("");
					$('#targetError').html("");
					
					createAccordion(data);
					$('#addStrategicPillarModal').modal('hide');
				}
				
			
				/*if(data.error != null){
					
				}else{
					$('#strategicPillarNameError').html("");
					$('#pillarSNError').html("");
					$('#themeError').html("");
					$('#outcomeSNError').html("");
					$('#outcomeError').html("");
					$('#outputSNError').html("");
					$('#outputError').html("");
					$('#indicatorError').html("");
					$('#targetError').html("");
				}*/
			}
		});
	}
	
}

function addThemeModal(pillarId){
	$('#themeNameError').html("");
	$('#outcomeSeqNError').html("");
	$('#outputSeqNError').html("");
	$('#outptError').html("");
	$('#indError').html("");
	$('#trgError').html("");
	$('#outcmError').html("");
	$('#pillarId').val(pillarId);
	$('#addThemeModal').modal('show');
}

function saveTheme(){
	var id=$('#themeId').val();
	var pillarId=$('#pillarId').val();
	var themeName=$('#themeName').val();
	var outcomeSeqN=$('#outcomeSeqN').val();
	var theme_outcome=$('#theme_outcome').val();
	var outputSeqN=$('#outputSeqN').val();
	var theme_output=$('#theme_output').val();
	var theme_indicator=$('#theme_indicator').val();
	var theme_target=$('#theme_target').val();
	var validation="";
	
	
	if(themeName != "" && outcomeSeqN != "" && theme_outcome != "" && outputSeqN != "" && theme_output != "" && theme_indicator != "" && theme_target != ""){
		validation = "true";
	}else{
		if (themeName == "") {
			$('#themeNameError').html(
					"Theme Name is required");
		} else {
			$('#themeNameError').html("");
		}
		if (outcomeSeqN == "") {
			$('#outcomeSeqNError').html(
					"Sequence Number of Outcome is required");
		} else {
			$('#outcomeSeqNError').html("");
		}
		
		if (theme_outcome == "") {
			$('#outcmError').html(
					"Outcome is required");
		} else {
			$('#outcmError').html("");
		}
		if (outputSeqN == "") {
			$('#outputSeqNError').html(
					"Sequence Number of Output is required");
		} else {
			$('#outputSeqNError').html("");
		}
		if (theme_output == "") {
			$('#outptError').html(
					"Output is required");
		} else {
			$('#outptError').html("");
		}
		if (theme_indicator == "") {
			$('#indError').html(
					"Indicator is required");
		} else {
			$('#indError').html("");
		}
		if (theme_target == "") {
			$('#trgError').html(
					"Target is required");
		} else {
			$('#trgError').html("");
		}
	}
	
	if(validation = "true"){
		if(id == ""){
			id=0;
		}
		var themeJson={
				"id" : id,
				"pillarId" : pillarId,
				"themeName" : themeName,
				"outcomeSeqN" : outcomeSeqN,
				"theme_outcome" : theme_outcome,
				"outputSeqN" : outputSeqN,
				"theme_output" : theme_output,
				"theme_indicator" : theme_indicator,
				"theme_target" : theme_target
		};
		
		$.ajax({
			url : '/ndcmp/api/saveTheme',
			data : {
				"themeJson" : JSON.stringify(themeJson)
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			contentType: "application/json",
			success : function(data){
				
				if(data.themeNameError != null){
					$('#themeNameError').html(data.themeNameError);
				}else{
					createThemeAccordion(data);
					$('#addThemeModal').modal('hide');	
				}
				
				
			}
		});
	}
}

function updateStrategicPillar(){
	var id=$('#editStrategicPillarId').val();
	var strategicPillarName=$('#editStrategicPillarName').val();
	var strategicPillarJson={
		"id" : id,
		"strategicPillarName" : strategicPillarName
	};
	$.ajax({
		url : '/ndcmp/api/updateStrategicPillar',
		data : {
			"strategicPillarJson" : JSON.stringify(strategicPillarJson)
		},
		type : 'GET',
		async : false,
		dataType : 'json',
		contentType: "application/json",
		success : function(data){
			if(data.strategicPillarNameError != null){
				$('#strategicNameError').html(data.strategicPillarNameError);
			}else if(data != null){
				$('#strategicNameError').html("");
				$('#editStrategicPillarModal').modal('hide');
				//var txt='<a class="accordion-toggle collapsed collapse in" data-toggle="collapse" id="strategic_'+data.id+'" data-parent="#accordion" href="#collapseStrategic_'+data.id+'" aria-expanded="false"><span><b>'+data.sequenceNumber+' . '+data.name+' </b></span></a>';
				$('#str_'+data.strategicPillar.id).text(data.strategicPillar.sequenceNumber+' . '+data.strategicPillar.name).css('font-weight', 'bold');
			}
		}
	});
}
function createAccordion(data){
	var txt='<div class="panel panel-default">'+
	'<div class="panel-heading" style="background-color: #2C3E50;">'+
		'<h4 class="panel-title">'+
			'<a class="accordion-toggle collapse in collapsed" data-toggle="collapse" id="strategic_'+data.strategicPillar.id+'" data-parent="#accordion" href="#collapseStrategic_'+data.strategicPillar.id+'" aria-expanded="false">'+
				'<span id="str_'+data.strategicPillar.id+'"><b>'+data.strategicPillar.id+' . '+data.strategicPillar.name+' </b></span>'+
			'</a>'+
		'</h4>'+
	'</div>'+
'<div id="collapseStrategic_'+data.strategicPillar.id+'" class="panel-collapse strategic-pillar collapse" aria-expanded="false">'+

'<div class="panel-body">'+
	'<div class="panel-group accordion" id="accordionOne_'+data.strategicPillar.id+'">'+
		'<div id="themeAdd-'+data.strategicPillar.id+'" style="display: block;">'+
			'<div align="right" style="margin-top: -42px;">'+
				'<a title="Edit Strategic Pillar" onclick="editStrategicPillar('+data.strategicPillar.id+');" style="cursor:pointer;margin: 0px -40px 3px 150px;" <span="" class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i></a>'+
			'</div>'+
			'<div align="right" style="margin-bottom: 0px">'+
				'<button type="button" id="strategic-1" style="margin: 0px -155px -2px 0px; width: 140px;" class="btn btn-primary btn-add-panel1" onclick="addThemeModal('+data.strategicPillar.id+')">New Theme</button>'+
			'</div>'+
		'</div>'+
	'<div class="panel panel-default template1">'+
		'<div class="panel-heading" style="background-color: #4D5B69;">'+
			'<h4 class="panel-title">'+
				'<a class="accordion-toggle collapse in" data-toggle="collapse" id="themeAdd_'+data.theme.id+'" data-parent="#accordionOne_'+data.strategicPillar.id+'" href="#collapseTheme_'+data.theme.id+'" aria-expanded="true"><span id="thm_'+data.theme.id+'"><b>'+data.theme.name+'</b></span></a>'+
			'</h4>'+
		'</div>'+
		'<div id="collapseTheme_'+data.theme.id+'" class="panel-collapse theme collapse in" aria-expanded="true">'+
			'<div class="panel-body">'+
				'<div class="panel-group accordion" id="accordionOutput_'+data.theme.name+'">'+
					'<div id="outcomeAdd-'+data.theme.id+'" style="display: none;">'+
						'<div align="right" style="margin-top: -42px;">'+
							'<a title="Edit Theme" onclick="editTheme('+data.theme.id+');" style="margin: 0px -40px 3px 150px" <span="" class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i> </a>'+
						'</div>'+
						'<div align="right" style="margin-bottom: 0px">'+
							'<button type="button" id="outcomeAdd" style="margin: 0px -155px 0px 0px; width: 140px;" class="btn btn-primary btn-add-panel2" data-toggle="modal" data-target="#addOutcomeModal">New Outcome</button>'+
						'</div>'+
					'</div>'+
	'<div class="panel panel-default template2">'+
		'<div class="panel-heading" style="background-color: #77818C;">'+
			'<h4 class="panel-title"><a class="accordion-toggle collapse in" data-toggle="collapse" id="outcomeAdd_'+data.theme.id+'" data-parent="#accordionOutput_'+data.theme.id+'" href="#collapseOutcome_'+data.outcome.id+'" aria-expanded="true"><span id="ocome_'+data.outcome.id+'"><b>'+data.outcome.sequenceNumber+' . '+data.outcome.name+'</b></span></a></h4>'+
		'</div>'+
		'<div id="collapseOutcome_'+data.outcome.id+'" class="panel-collapse outcome collapse in" aria-expanded="true">'+
			'<div class="panel-body"><div class="panel-group accordion" id="collapseOne_'+data.outcome.id+'">'+
				'<div id="outputAdd-'+data.outcome.id+'" style="display: none;">'+
					'<div align="right" style="margin-top: -42px;">'+
						'<a title="Edit Outcome" onclick="editOutcome('+data.outcome.id+');" style="margin: 0px -40px 3px 150px" <span="" class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i> </a>'+
					'</div>'+
					'<div align="right" style="margin-bottom: 0px">'+
						'<button type="button" id="outputAdd" style="margin: 4px -157px -3px -3px; width: 140px;" class="btn btn-primary btn-add-panel3" data-toggle="modal" data-target="#addOutputModal">New Output</button>'+
					'</div>'+
				'</div>'+
	'<div class="panel panel-default template3">'+
		'<div class="panel-heading" style="background-color: #9C9EA0;">'+
			'<h4 class="panel-title">'+
				'<a class="accordion-toggle collapse in" id="outputAdd_'+data.outcome.id+'" data-toggle="collapse" data-parent="#collapseOne_'+data.outcome.id+'" href="#collapseOutput_'+data.output.id+'" aria-expanded="true"><span id=output_'+data.output.id+'><b>'+data.output.sequenceNumber+' . '+data.output.output+'</b></span></a></h4>'+
		'</div>'+
		'<div id="collapseOutput_'+data.output.id+'" class="panel-collapse output collapse in" aria-expanded="true">'+
			'<div class="panel-body">'+
				'<div class="panel-group accordion" id="collapseOne_'+data.outcome.id+'">'+
					'<div id="keyActivityAdd"><div align="right" style="margin-top: -30px;">'+
						'<a href="#" id="outputAdd_'+data.outcome.id+'" title="Edit Output" onclick="editOutput('+data.output.id+');" style="margin: 0px -40px 3px 150px" <span="" class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i> </a>'+
					'</div>'+
					'<div align="right" style="margin-bottom: 0px">'+
						'<button type="button" id="outputAdd" style="margin: 0px -158px 0px 0px; width: 140px;" class="btn btn-primary btn-add-panel4" onclick="newKeyActivity('+data.output.id+')">New Key Activity</button>'+
					'</div>'+
				'</div>'+
				'<div align="right" style="margin-bottom: 0px">'+
					'<button type="button" id="indicatorAdd" style="margin: 0px -158px 0px 0px; width: 140px;" class="btn btn-primary" onclick="indicatorModal('+data.output.id+');">New Indicator</button>'+
				'</div>'+
				'<div id="'+data.output.id+'_parentIndDiv">'+
					'<div><b>Indicators : </b></div>'+
						'<div id="'+data.indicator.id+'_indicator" class="col-md-12">'+
							'<div class="col-md-10">'+data.indicator.message+'</div>'+
								'<div class="col-md-2" style="height:12px;">'+
									'<a class="table-link" title="Edit Indicator" onclick="editIndicator('+data.indicator.id+');" style="margin: 0px -188px -2px 150px;" <span=""> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i> </a>'+
								'</div>'+
							'</div><br><br>'+
						'</div>'+
					'<div align="right" style="margin-bottom: 0px">'+
						/*'<button type="button" id="targetAdd" style="margin: 0px -158px 0px 0px; width: 140px;" class="btn btn-primary" onclick="targetModal('+data.output.id+');">New Target</button>'+*/
					'</div>'+
				'<div id="'+data.output.id+'_parentTarDiv">'+
					'<div><b>Target : </b><button type="button" id="targetAdd" style="margin: 0px -158px 0px 0px; width: 140px;" class="btn btn-primary pull-right" onclick="targetModal('+data.output.id+');">New Target</button></div>'+
						'<div id="'+data.target.id+'_target" class="col-md-12">'+
							'<div class="col-md-10">'+data.target.message+'</div>'+
								'<div class="col-md-2">'+
									'<a style="cursor:pointer" class="table-link" title="Edit Target" onclick="editTarget('+data.target.id+' );" <span=""> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i> </a>'+
										'</div>'+
								'</div><br><br>'+
							'</div>'+
						'</div>'+
					'</div>'+
				'</div>'+
			'</div>'+
		'</div>'+
	'</div></div></div></div></div></div></div></div></div></div></div>';
	
		$('#accordion').append(txt);
		$('.accordion a').on('click', function() {
		var _this = $(this);
		var btnId="";
		var str=$(this).context.id.split('_');
		if(str[0] == "strategic"){
		btnId="#strategic-1";
		}else{
		btnId="#"+str[0]+'-'+str[1];
		}
		var _expanded = $(_this).attr('aria-expanded');
		if(_expanded == 'false') {
		//$('.btn .edit .delete').show();
		$(btnId).hide();
		} else if(_expanded == 'true') {
		//$('.btn .edit .delete').hide();
		$(btnId).show();
		}
		});
}

function createThemeAccordion(data){
	var txt='<div class="panel panel-default template1">'+
		'<div class="panel-heading" style="background-color: #4D5B69;">'+
			'<h4 class="panel-title">'+
				'<a class="accordion-toggle collapsed collapse in" data-toggle="collapse" id="themeAdd_'+data.theme.id+'" data-parent="#accordionOne_'+data.strategicPillar.id+'" href="#collapseTheme_'+data.theme.id+'" aria-expanded="false"><span id="thm_'+data.theme.id+'"><b>'+data.theme.name+'</b></span></a>'+
			'</h4>'+
		'</div>'+
		'<div id="collapseTheme_'+data.theme.id+'" class="panel-collapse theme collapse" aria-expanded="false" style="height: 2px;">'+
			'<div class="panel-body">'+
				'<div class="panel-group accordion" id="accordionOutput_'+data.theme.id+'">'+
					'<div id="outcomeAdd-'+data.theme.id+'">'+
						'<div align="right" style="margin-top: -42px;">'+
							'<a title="Edit Theme" onclick="editTheme('+data.theme.id+');" style="margin: 0px -40px 3px 150px" <span="" class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i> </a>'+
						'</div>'+
					'<div align="right" style="margin-bottom: 0px">'+
						'<button type="button" id="outcomeAdd" style="margin: 0px -155px 0px 0px; width: 140px;" class="btn btn-primary btn-add-panel2" data-toggle="modal" data-target="#addOutcomeModal">New Outcome</button>'+
					'</div>'+
				'</div>'+
				'<div class="panel panel-default template2">'+
					'<div class="panel-heading" style="background-color: #77818C;">'+
						'<h4 class="panel-title">'+
							'<a class="accordion-toggle collapsed collapse in" data-toggle="collapse" id="outcomeAdd_'+data.theme.id+'" data-parent="#accordionOutput_'+data.theme.id+'" href="#collapseOutcome_'+data.outcome.id+'"  aria-expanded="false"><span id="ocome_'+data.outcome.id+'"><b>'+data.outcome.sequenceNumber+' . '+data.outcome.name+'</b></span></a>'+
						'</h4>'+
					'</div>'+
				'<div  id="collapseOutcome_'+data.outcome.id+'" class="panel-collapse outcome collapse" aria-expanded="false" style="height: 2px;">'+
					'<div class="panel-body">'+
						'<div class="panel-group accordion" id="collapseOne_'+data.outcome.id+'">'+
							'<div id="outputAdd-'+data.outcome.id+'">'+
								'<div align="right" style="margin-top: -42px;">'+
									'<a title="Edit Outcome" onclick="editOutcome('+data.outcome.id+');" style="margin: 0px -40px 3px 150px" <span="" class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i> </a>'+
								'</div>'+
								'<div align="right" style="margin-bottom: 0px">'+
									'<button type="button" id="outputAdd" style="margin: 4px -157px -3px -3px; width: 140px;" class="btn btn-primary btn-add-panel3" data-toggle="modal" data-target="#addOutputModal">New Output</button>'+
								'</div>'+
							'</div>'+
							'<div class="panel panel-default template3">'+
								'<div class="panel-heading" style="background-color: #9C9EA0;">'+
									'<h4 class="panel-title">'+
										'<a class="accordion-toggle collapsed collapse in" id="outputAdd_'+data.outcome.id+'" data-toggle="collapse" data-parent="#collapseOne_'+data.outcome.id+'" href="#collapseOutput_'+data.output.id+'" aria-expanded="false"><span id="output_'+data.output.id+'"><b>'+data.output.sequenceNumber+' . '+data.output.output+'</b></span></a>'+
									'</h4>'+
								'</div>'+
								'<div id="collapseOutput_'+data.output.id+'" class="panel-collapse output collapse" aria-expanded="false" style="height: 2px;">'+
									'<div class="panel-body">'+
										'<div class="panel-group accordion" id="collapseOne_'+data.outcome.id+'">'+
											'<div id="keyActivityAdd"><div align="right" style="margin-top: -30px;">'+
												'<a href="#" id="outputAdd_'+data.outcome.id+'" title="Edit Output" onclick="editOutput('+data.output.id+');" style="margin: 0px -40px 3px 150px" <span="" class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i> </a>'+
											'</div>'+
											
											'<div align="right" style="margin-bottom: 0px">'+
												'<button type="button" id="outputAdd" style="margin: 0px -158px 0px 0px; width: 140px;" class="btn btn-primary btn-add-panel4" onclick="newKeyActivity('+data.output.id+')">New Key Activity</button>'+
											'</div>'+
										'</div>'+
										'<div align="right" style="margin-bottom: 0px">'+
											'<button type="button" id="indicatorAdd" style="margin: 0px -158px 0px 0px; width: 140px;" class="btn btn-primary" onclick="indicatorModal('+data.output.id+');">New Indicator</button>'+
										'</div>'+
										'<div id="'+data.output.id+'_parentIndDiv">'+
											'<div><b>Indicators : </b></div>'+
											'<div id="'+data.indicator.id+'_indicator" class="col-md-12">'+
												'<div class="col-md-10">'+data.indicator.message+'</div>'+
													'<div class="col-md-2">'+
														'<a class="table-link" title="Edit Indicator" onclick="editIndicator('+data.indicator.id+');" style="margin: 0px -188px -2px 150px;" <span=""> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i> </a>'+
													'</div>'+
												'</div><br><br>'+
											'</div>'+
											'<div align="right" style="margin-bottom: 0px">'+
												/*'<button type="button" id="targetAdd" style="margin: 0px -158px 0px 0px; width: 140px;" class="btn btn-primary" onclick="targetModal('+data.output.id+');">New Target</button></div>'+*/
												'<div id="'+data.output.id+'_parentTarDiv"><div><b>Target : </b><button type="button" id="targetAdd" style="margin: 0px -158px 0px 0px; width: 140px;" class="btn btn-primary pull-right" onclick="targetModal('+data.output.id+');">New Target</button></div>'+
												'<div id="'+data.target.id+'_target" class="col-md-12"><div class="col-md-10">'+data.target.message+'</div>'+
												'<div class="col-md-2"><a style="cursor:pointer" class="table-link" title="Edit Target" onclick="editTarget('+data.target.id+');" <span=""> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i> </a></div>'+
											'</div><br><br>'+
										'</div>'+
									'</div>'+
								'</div>'+
							'</div></div></div></div></div></div></div></div></div></div>';
	$('#accordionOne_'+data.strategicPillar.id).append(txt);
	//$('#accordion').append(txt);
	$('.accordion a').on('click', function() {
	var _this = $(this);
	var btnId="";
	var str=$(this).context.id.split('_');
	if(str[0] == "strategic"){
	btnId="#strategic-1";
	}else{
	btnId="#"+str[0]+'-'+str[1];
	}
	var _expanded = $(_this).attr('aria-expanded');
	if(_expanded == 'false') {
	//$('.btn .edit .delete').show();
	$(btnId).hide();
	} else if(_expanded == 'true') {
	//$('.btn .edit .delete').hide();
	$(btnId).show();
	}
	});
}

function createOutcomeAccordion(data){
	var txt='<div class="panel panel-default template2">'+
				'<div class="panel-heading" style="background-color: #77818C;">'+
					'<h4 class="panel-title">'+
						'<a class="accordion-toggle collapsed collapse in" data-toggle="collapse" id="outcomeAdd_'+data.theme.id+'" data-parent="#accordionOutput_'+data.theme.id+'" href="#collapseOutcome_'+data.outcome.id+'" aria-expanded="false"><span id="ocome_'+data.outcome.id+'"><b>'+data.outcome.sequenceNumber+' . '+data.outcome.name+'</b></span></a>'+
					'</h4>'+
				'</div>'+
				'<div id="collapseOutcome_'+data.outcome.id+'" class="panel-collapse outcome collapse" aria-expanded="false" style="height: 2px;">'+
					'<div class="panel-body">'+
						'<div class="panel-group accordion" id="collapseOne_'+data.outcome.id+'">'+
							'<div id="outputAdd-'+data.outcome.id+'">'+
								'<div align="right" style="margin-top: -42px;">'+
									'<a title="Edit Outcome" onclick="editOutcome('+data.outcome.id+');" style="margin: 0px -40px 3px 150px" <span="" class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i> </a>'+
								'</div>'+
								'<div align="right" style="margin-bottom: 0px">'+
									'<button type="button" id="outputAdd" style="margin: 4px -157px -3px -3px; width: 140px;" class="btn btn-primary btn-add-panel3" data-toggle="modal" data-target="#addOutputModal">New Output</button>'+
								'</div>'+
							'</div>'+
						'<div class="panel panel-default template3">'+
							'<div class="panel-heading" style="background-color: #9C9EA0;">'+
								'<h4 class="panel-title">'+
									'<a class="accordion-toggle collapsed collapse in" id="outputAdd_'+data.outcome.id+'" data-toggle="collapse" data-parent="#collapseOne_'+data.outcome.id+'" href="#collapseOutput_'+data.output.id+'" aria-expanded="false"><span id="output_'+data.output.id+'"><b>'+data.output.sequenceNumber+' . '+data.output.output+'</b></span></a>'+
								'</h4>'+
							'</div>'+
							'<div id="collapseOutput_'+data.output.id+'" class="panel-collapse output collapse" aria-expanded="false" style="height: 2px;">'+
								'<div class="panel-body">'+
									'<div class="panel-group accordion" id="collapseOne_'+data.outcome.id+'">'+
										'<div id="keyActivityAdd">'+
											'<div align="right" style="margin-top: -30px;">'+
												'<a href="#" id="outputAdd_'+data.outcome.id+'" title="Edit Output" onclick="editOutput('+data.output.id+');" style="margin: 0px -40px 3px 150px" <span="" class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i> </a>'+
											'</div>'+
											'<div align="right" style="margin-bottom: -35px">'+
												'<button type="button" id="outputAdd" style="margin: 0px -158px 0px 0px; width: 140px;" class="btn btn-primary btn-add-panel4" onclick="newKeyActivity('+data.output.id+')">New Key Activity</button>'+
											'</div>'+
										'</div>'+
								'<div align="right" style="margin-bottom: 0px">'+
									'<button type="button" id="indicatorAdd" style="margin: 0px -158px 0px 0px; width: 140px;" class="btn btn-primary" onclick="indicatorModal('+data.output.id+');">New Indicator</button>'+
								'</div>'+
								'<div id="'+data.output.id+'_parentIndDiv">'+
									'<div><b>Indicators : </b></div>'+
									'<div id="'+data.indicator.id+'_indicator" class="col-md-12"><div class="col-md-10">'+data.indicator.message+'</div>'+
									'<div class="col-md-2">'+
										'<a class="table-link" title="Edit Indicator" onclick="editIndicator('+data.indicator.id+');" style="margin: 0px -188px -2px 150px;" <span=""> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i> </a>'+
									'</div>'+
								'</div><br><br>'+
							'</div>'+
							'<div align="right" style="margin-bottom: 0px">'+
								/*'<button type="button" id="targetAdd" style="margin: 0px -158px 0px 0px; width: 140px;" class="btn btn-primary" onclick="targetModal('+data.output.id+');">New Target</button>'+*/
							'</div>'+
							'<div id="'+data.output.id+'_parentTarDiv">'+
								'<div><b>Target : </b><button type="button" id="targetAdd" style="margin: 0px -158px 0px 0px; width: 140px;" class="btn btn-primary pull-right" onclick="targetModal('+data.output.id+');">New Target</button></div>'+
								'<div id="'+data.target.id+'_target" class="col-md-12"><div class="col-md-10">'+data.target.message+'</div>'+
								'<div class="col-md-2"><a style="cursor:pointer" class="table-link" title="Edit Target" onclick="editTarget('+data.target.id+');" <span=""> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i> </a></div>'+
							'</div><br><br></div></div></div></div></div></div></div></div></div>';
	$('#collapseTheme_'+data.theme.id).append(txt);
	
}

function createOutputAccordion(data){
	var txt='<div class="panel panel-default template3">'+
				'<div class="panel-heading" style="background-color: #9C9EA0;">'+
					'<h4 class="panel-title">'+
						'<a class="accordion-toggle collapse in collapsed" id="outputAdd_'+data.outcome.id+'" data-toggle="collapse" data-parent="#collapseOne_'+data.outcome.id+'" href="#collapseOutput_'+data.output.id+'" aria-expanded="false"><span id="output_'+data.output.id+'"><b>'+data.output.sequenceNumber+' . '+data.output.output+'</b></span></a>'+
					'</h4>'+
				'</div>'+
				'<div id="collapseOutput_'+data.output.id+'" class="panel-collapse output collapse" aria-expanded="false" style="height: 2px;">'+
					'<div class="panel-body">'+
						'<div class="panel-group accordion" id="collapseOne_'+data.outcome.id+'">'+
							'<div id="keyActivityAdd">'+
								'<div align="right" style="margin-top: -30px;">'+
									'<a href="#" id="outputAdd_'+data.outcome.id+'" title="Edit Output" onclick="editOutput('+data.output.id+');" style="margin: 0px -40px 3px 150px" <span="" class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i> </a>'+
								'</div>'+
								'<div align="right" style="margin-bottom: 0px">'+
									'<button type="button" id="outputAdd" style="margin: 0px -158px 0px 0px; width: 140px;" class="btn btn-primary btn-add-panel4" onclick="newKeyActivity('+data.output.id+')">New Key Activity</button>'+
								'</div>'+
							'</div>'+
							'<div align="right" style="margin-bottom: 0px">'+
								'<button type="button" id="indicatorAdd" style="margin: 0px -158px 0px 0px; width: 140px;" class="btn btn-primary" onclick="indicatorModal('+data.output.id+');">New Indicator</button>'+
							'</div>'+
							'<div id="'+data.output.id+'_parentIndDiv"><div><b>Indicators : </b></div>'+
							'<div id="'+data.indicator.id+'_indicator" class="col-md-12">'+
								'<div class="col-md-10">'+data.indicator.message+'</div>'+
								'<div class="col-md-2">'+
									'<a class="table-link" title="Edit Indicator" onclick="editIndicator('+data.indicator.id+');" style="margin: 0px -188px -2px 150px;" <span=""> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i> </a>'+
								'</div>'+
							'</div><br><br>'+
						'</div>'+
						'<div align="right" style="margin-bottom: 0px">'+
							/*'<button type="button" id="targetAdd" style="margin: 0px -158px 0px 0px; width: 140px;" class="btn btn-primary" onclick="targetModal('+data.output.id+');">New Target</button>'+*/
						'</div>'+
						'<div id="'+data.output.id+'_parentTarDiv">'+
							'<div><b>Target : </b><button type="button" id="targetAdd" style="margin: 0px -158px 0px 0px; width: 140px;" class="btn btn-primary pull-right" onclick="targetModal('+data.output.id+');">New Target</button></div>'+
							'<div id="'+data.target.id+'_target" class="col-md-12">'+
								'<div class="col-md-10">'+data.target.message+'</div>'+
								'<div class="col-md-2">'+
									'<a style="cursor:pointer" class="table-link" title="Edit Target" onclick="editTarget('+data.target.id+');" <span=""> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i> </a>'+
								'</div>'+
							'</div><br><br></div></div></div></div></div>';
	
		$('#collapseOne_'+data.outcome.id).append(txt);
	
}

function loadKeyActivityByOutput(outputId){
	$.ajax({
		url : '/ndcmp/api/loadKeyActivityByOutput',
		data : {
			"outputId" : outputId
		},
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data){
			$('#accordion4').show();
			var txt="";
			var table="";
			if(role == "PROJECT_ADMIN" && $('#action').val() == "config"){
				table="planner-table"+outputId;
			}else{
				table="output-data"+outputId;
			}
			txt+='<div class="table-responsive"><table id="'+table+'" class="table table-hover table-bordered CSSTableGenerator dataTable no-footer">';
			txt+='<thead><tr>'
			+ '<th style="text-align: center;">Sequence Number</th>'
			+ '<th style="text-align: center;">Key Activity</th>'
			+ '<th style="text-align: center;">Sub Activity</th>'
			+ '<th style="text-align: center;">Responsible Entity</th>'
			+ '<th style="text-align: center;">Partnering Agency</th>'
			+ '<th style="text-align: center;">MOV</th>';
			if(role == "PROJECT_ADMIN" && $('#action').val() == "config"){
			txt+= '<th width="71px" style="text-align: center;">Actions</th>';
			}else if (role == "SUPER_ADMIN") {
				txt+= '<th width="71px" style="text-align: center;">Action</th>';
			}
			txt+= '</tr></thead>';
			for(var n =0;n < data.keyActivities.length; n++){
				txt += '<tbody><tr>'
						+ '<td>'+data.keyActivities[n].sequenceNumber+'</td>'
						+ '<td>'+data.keyActivities[n].name+'</td>'
						+ '<td></td>'
						+ '<td></td>'
						+ '<td></td>';
						if(role == "PROJECT_ADMIN" && $('#action').val() == "config"){
							txt+='<td></td><td><a style="cursor:pointer" title="Add SubActivity" onclick="openSubActivtyModal('+data.keyActivities[n].id+');" style="color: red; text-decoration: none;" <img style="height: 24px; margin-top: -1px;" src="../static/img/add.png"></a>'
							+ '<a style="cursor:pointer; height: 24px; margin-top: -2px;" title="Edit KeyActivity" onclick="editKeyActivity('+data.keyActivities[n].id+')" <span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i></span> </a>'+
							'<a style="cursor:pointer" title="Add SubActivity" style="color: red; text-decoration: none;" onclick="openSubActivtyModal('+data.keyActivities[n].id+');"><img style="height: 24px; margin-top: -1px;" src="../static/img/add.png"></a></td>';
						}else{
							if(role == "PROJECT_PLANNER" || role == "PROJECT_ADMIN" || role == "STATUS_APPROVER"){
								txt+= '<td></td>';
							}else{
								txt+= '<td></td><td></td>' ;
							}
						}
						txt+= '</tr>';
						var subActivities = data.keyActivities[n].subActivities;
						if(subActivities != null) {
							for(var o =0;o < subActivities.length; o++) {
								var style="";
								
								if(subActivities[o].approveORCompleteStatus == true){
									style="background-color:#a4d083;";
								}else{
									style="";
								}
								
								txt += '<tr style="'+style+'">'
										+ '<td>'+subActivities[o].sequenceNumber+'</td>'
										+ '<td></td>'
										+ '<td>'+subActivities[o].name+'</td>'
										+ '<td>'+subActivities[o].leadAgency.code+'</td>'
										partnerAgency = subActivities[o].partnerAgency;
										if(partnerAgency != null) {
											var partnerAgencies=[];
											for(var partner = 0; partner < partnerAgency.length; partner++) {
												var pa = partnerAgency[partner].code;
												partnerAgencies.push(" "+pa);
											}
											txt += '<td>'+partnerAgencies+'</td>'
										}
										txt += '<td>'+subActivities[o].mov+'</td>'
										
										if(role == "SUPER_ADMIN"){
											if(subActivities[o].approveORCompleteStatus == false){
												txt+= '<td><div class="row" style="text-align:center">'
												if(subActivities[o].status == 'ACTIVE') {
													txt += '<a style="cursor:pointer;font-size: 1.125em;" class="table-link danger" id="deAct_'+subActivities[o].id+'_'+data.id+'" onclick="actSubAct(this.id)" title="Deactivate Sub Activity" ><span class="fa-stack"><i class="fa fa-square fa-stack-2x"></i><i class="fa fa-times fa-stack-1x fa-inverse"></i></span></a>';
												} else {
													txt += '<a style="cursor:pointer" class="table-link" id="act_'+subActivities[o].id+'_'+data.id+'" onclick="actSubAct(this.id)" title="Activate Sub Activity"><span class="fa-stack"><i class="fa fa-square fa-stack-2x"></i><i class="fa fa-check fa-stack-1x fa-inverse"></i></span></a>';
												}
											}else{
												txt+="<td></td>";
											}
										}
										
										if(role == "PROJECT_ADMIN" && $('#action').val() == "config"){
											if(subActivities[o].approveORCompleteStatus == false){
												txt+= '<td><div class="row" style="text-align:center">'
												+ '<a style="cursor:pointer;font-size: 1.125em;" title="Edit SubActivity" onclick="editSubActivity('+subActivities[o].id+')"> <span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i></span> </a>';
												if(subActivities[o].status == 'ACTIVE') {
													txt += '<a style="cursor:pointer;font-size: 1.125em;" class="table-link danger" id="deAct_'+subActivities[o].id+'_'+outputId+'" title="Deactivate Sub Activity" onclick="actSubAct(this.id)" ><span class="fa-stack"  style="left: -6px;"><i class="fa fa-square fa-stack-2x"></i><i class="fa fa-times fa-stack-1x fa-inverse"></i></span></a>';
												} else {
													txt += '<a style="cursor:pointer" class="table-link" id="act_'+subActivities[o].id+'_'+outputId+'" title="Activate Sub Activity"  onclick="actSubAct(this.id)"><span class="fa-stack"  style="left: -6px;"><i class="fa fa-square fa-stack-2x"></i><i class="fa fa-check fa-stack-1x fa-inverse"></i></span></a>';
												}
											}else{
												txt+="<td></td>";
											}
										}
										
										txt += '</div></td></tr>';
							}
						}
			}
			txt += '</tbody></table></div>'	;
			
			if($('#action').val() == "view"){
				var indicators = data.indicators;
				if(indicators != null) {
					txt += '<div id="'+data.id+'_parentIndDiv"><div><b>Indicators : </b></div>';
					for(var p=0; p<indicators.length; p++) {
						/*modalBody +=  '<div align="right" style="margin-top: -30px">'
						+ '<a href="#" class="table-link" title="Edit Indicator" onclick="editIndicator('+indicators[p].id+');" style="margin: 0px -188px -2px 150px;" <span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i></span> </a>'
						+ '</div>'
						+ '<tr><td>'+indicators[p].message+'</td></tr></table>'*/
						txt +='<div id="'+indicators[p].id+'_indicator" class="col-md-12"><div class="col-md-10">'+indicators[p].message+'</div></div><br />';
					}
					txt+='</div>';
				}
				var targets = data.targets;
				if(targets != null) {
					txt += '<div id="'+data.id+'_parentTarDiv"><div><b>Target : </b></div>';
					for(var q=0; q<targets.length; q++) {
						/*modalBody +=  '<div align="right" style="margin-top: -30px">'
							+ '<a href="#" class="table-link" title="Edit Target" onclick="editTarget('+targets[q].id+');" style="margin: 0px -188px -2px 150px;" <span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i></span> </a>'
							+ '</div>'
						+ '<tr><td>'+targets[q].message+'</td></tr></table>'*/
						txt +='<div id="'+targets[q].id+'_target" class="col-md-12"><div class="col-md-10">'+targets[q].message+'</div></div><br />';
					}
					txt+='</div>';
				}
			}
			
			if(role == "PROJECT_ADMIN" && $('#action').val() == "config"){
				var indicators = data.indicators;
				if(indicators != null) {
					txt += '<div align="right" style="margin-bottom: -25px">'
							+ '<button type="button" id="indicatorAdd" title="Add Indicator" style="margin: 0px -158px 0px 0px; width: 140px;" class="btn btn-primary" onclick="indicatorModal('+outputId+');">New Indicator</button>'
							+ '</div>'
					+ '<div id="'+outputId+'_parentIndDiv"><div><b>Indicators : </b></div>';
					for(var p=0; p<indicators.length; p++) {
						
						txt +='<div id="'+indicators[p].id+'_indicator" title="Add Indicator" class="col-md-12"><div class="col-md-10">'+indicators[p].message+'</div><div class="col-md-2" style="height:27px;"><a style="cursor:pointer" class="table-link" title="Edit Indicator" onclick="editIndicator('+indicators[p].id+');" style="margin: 0px -188px -2px 150px;" <span class="fa-stack"> <i class="fa fa-square fa-stack-2x" style="margin-top: -3px;"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse" style="margin-top: 0px;"></i></span> </a></div></div><br />';
					}
					txt+='</div>';
				}
				var targets = data.targets;
				if(targets != null) {
					txt += '<div align="right" style="margin-bottom: 0px">'
					/*	+ '<button type="button" id="targetAdd" style="margin: 22px -159px -32px 0px;width: 140px;" class="btn btn-primary" onclick="targetModal('+outputId+');">New Target</button>'*/
						+ '</div>'
						+ '<div id="'+outputId+'_parentTarDiv"><div><b>Target : </b><button type="button" id="targetAdd" title="Add Target" style="margin: 22px -159px -32px 0px;width: 140px;" class="btn btn-primary pull-right" onclick="targetModal('+outputId+');">New Target</button></div>';
					for(var q=0; q<targets.length; q++) {
						txt +='<div id="'+targets[q].id+'_target" class="col-md-12"><div class="col-md-10">'+targets[q].message+'</div><div class="col-md-2"><a style="cursor:pointer" class="table-link" title="Edit Target" onclick="editTarget('+targets[q].id+');" style="margin: 0px -188px -2px 150px;" <span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-pencil fa-stack-1x fa-inverse"></i></span> </a></div></div><br /><br />';
					}
					txt+='</div>'
				}
			}
			
			if(role == "PROJECT_ADMIN" && $('#action').val() == "config" ){
				$('#configDiv_'+outputId).html("");
				$('#configDiv_'+outputId).html(txt);
			}else{
				$('#keyactivityDatas_'+outputId).html("");
				$('#keyactivityDatas_'+outputId).html(txt);
			}
			
		}
	});
	
}