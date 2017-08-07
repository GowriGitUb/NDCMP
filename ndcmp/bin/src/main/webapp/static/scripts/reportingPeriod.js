$(document).ready(function() {
			$('#tabs > li').removeClass('active');
			$('#configTab').attr('class', 'active');
			$('#reportingTab	').attr('class', 'active');
			/* $('#logouttab').show(); */
			
			var start=$('#sDate').val();
			var end=$('#eDate').val();
			
			$('#startdate').val(start);
			$('#enddate').val(end);
			/*if(start != "" && end != ""){
				$('#startdate').val(moment(start).format('DD-MM-YYYY'));
				$('#enddate').val(moment(end).format('DD-MM-YYYY'));
			}*/
			
			$('.datePicker').datepicker({
				format : 'dd-mm-yyyy',
				yearRange : '2017' + '2017',
				startDate : new Date(),
				todayHighlight : 1,
				autoclose : true
			}).on('changeDate', function(e) {
	            // Revalidate the date field
				 (e.viewMode=='days') ? $(this).datepicker('hide') : '';
	            $('#reportingPeriodAddForm').formValidation('revalidateField', 'sDate');
	            $('#reportingPeriodAddForm').formValidation('revalidateField', 'eDate');
	        });
			
			/*$('#reportingPeriod-list').dataTable({
			 "aoColumnDefs" : [ {
		            'bSortable' : false,
		            'aTargets' : [ 6 ]
		       }],
		       "pagingType": "full_numbers"
			});*/
			
			$('#reportingActiveDialog').on(
					'show.bs.modal',
					function(event) {
						var getIdFromRow = $(event.relatedTarget).find(
								'td:nth-child(3)').text();
						$(this).find('#ReportingActive').html(
								$('<b> Are you sure to Active Reporting Period ? '
										+ getIdFromRow + '</b>'));
					});
			$('#reportingActiveDialog').on('show.bs.modal',function(e) {
					$(this).find('.btn-ok').attr('href',
					$(e.relatedTarget).data('href'));
			});
			
			/* device dis approved modal window */
			$('#reportingDeactiveDialog').on('show.bs.modal',function(event) {
					var getIdFromRow = $(event.relatedTarget).find('td:nth-child(3)').text();
					$(this).find('#ReportingDeactive').html(
					$('<b> Are you sure to Deactive Reporting Period ? '+ getIdFromRow + '</b>'));
			});
			$('#reportingDeactiveDialog').on('show.bs.modal',function(e) {
					$(this).find('.btn-ok').attr('href',
					$(e.relatedTarget).data('href'));
			});
});

$('#cancel').click(function() {
	window.history.back();
});
/*function submitReportingPeriodForm() {
	$('#reportingPeriodAddForm')
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
										message : 'Quarter is Required'
									},
									stringLength : {
										min : 2,
										max : 10,
										message : 'Quarter must be more than 2 and less than 10 characters long'
									}
								}
							},
							year : {
								validators : {
									notEmpty : {
										message : 'Year is Required'
									},
									stringLength : {
										min : 3,
										max : 30,
										message : 'Year must be more than 3 and less than 30 characters long'
									}
								}
							},

							sDate : {
								validators : {
									notEmpty : {
										message : 'Start Date is Required'
									},
									date :{
										format: 'DD-MM-YYYY'
									}
								}
							},
							eDate : {
								validators : {
									notEmpty : {
										message : 'End Date is Required'
									}
								}
							},
							reportingStatus : {
								validators : {
									notEmpty : {
										message : 'Reporting Period Status is Required'
									}
								}
							}
						}
					});
	$('#sDate')
    .on('dp.change dp.show', function(e) {
        // Validate the date when user change it
        $('#reportingPeriodAddForm').data('formValidation').revalidateField('datetimePicker');
        // You also can call it as following:
        // $('#defaultForm').formValidation('revalidateField', 'datetimePicker');
    });
	return true;
}
*/
$(function() {
	$('#reportingPeriodAddForm')
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
								message : 'Quarter is Required'
							}/*,
							stringLength : {
								min : 2,
								max : 10,
								message : 'Quarter must be more than 2 and less than 10 characters long'
							}*/
						}
					},
					year : {
						validators : {
							notEmpty : {
								message : 'Year is Required'
							},
							stringLength : {
								min : 3,
								max : 30,
								message : 'Year must be more than 3 and less than 30 characters long'
							}
						}
					},

					sDate : {
						validators : {
							notEmpty : {
								message : 'Start Date is Required'
							},
							date :{
								format: 'DD-MM-YYYY'
							}
						}
					},
					eDate : {
						validators : {
							notEmpty : {
								message : 'End Date is Required'
							}
						}
					},
					reportingStatus : {
						validators : {
							notEmpty : {
								message : 'Reporting Period Status is Required'
							}
						}
					}
				}
			});
$('#sDate')
.on('dp.change dp.show', function(e) {
// Validate the date when user change it
$('#reportingPeriodAddForm').data('formValidation').revalidateField('datetimePicker');
// You also can call it as following:
// $('#defaultForm').formValidation('revalidateField', 'datetimePicker');
});

$(function() {
	$('#quaterAddForm').formValidation(
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
							message : 'Quarter Name is Required'
					}
				}
			}
			}
		});
    });
});

//to show the view data modal window
function getReportPeriodInfo(id){
	var stateInfo = '';
	$.ajax({
		url : '/ndcmp/api/getReportPeriodInfo',
		data : {
			reportId : id
		},
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data) {
			if (data != null) {
				reportInfo = data;
				$('#reportName').html(reportInfo.name);
				$('#reportYear').html(reportInfo.year);
				//sDate = reportInfo.startdate;
				//var date1 = moment(sDate).format('MM/DD/YYYY');
				$('#reportStartDate').html(moment(reportInfo.startdate).format('DD-MM-YYYY'));
				$('#reportEndDate').html(moment(reportInfo.enddate).format('DD-MM-YYYY'));
				$('#periodStatus').html(reportInfo.reportingStatus);
				$('#reportPeriodInformation').modal('show');
				
			}
		}
	});
}


//to show confirmation dialog
function closeMessage(selected){
	var selectedValue = selected.value;
	if(selectedValue == "Closed"){
		$('#closeModal').modal('show');
		return false;
	}
}

//when click "No" in modal window it will set below values in reporting status
function setReportingPeriodStatus(){
	$('#closeModal').modal('hide');
	var reportStatusValue = '';
	reportStatusValue += '<option value="Opened" selected="selected">Opened</option>';
	reportStatusValue += '<option value="Closed">Closed</option>';
	$('#reportingStatus').html(reportStatusValue);
}

//to save the value
function saveReportingPeriod(){
	$('#closeModal').modal('hide');
	var id = $('#id').val();
	$.ajax({
		url : '/ndcmp/api/saveReportingPeriod',
		data : {
			reportId : id
		},
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data) {
			if (data != null) {
				window.location.href = "/ndcmp/api/reportingList";
			}
		}
	});
}

var reportID = '';
function showCloseReportingConfirmation(year,id){
	reportID = id;
	$('#ReportingClose').html("Are you sure to Close Reporting Period "+year + "?");
	$('#reportingCloseDialog').modal('show');
}

function colseStatus(){
	$.ajax({
		url : '/ndcmp/api/closeReporting',
		data : {
			reportId : reportID
		},
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data) {
			if (data != null) {
				window.location.href = "/ndcmp/api/reportingList";
			}
		}
	});
}