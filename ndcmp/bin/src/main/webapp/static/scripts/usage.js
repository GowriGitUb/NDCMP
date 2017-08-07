/**
 * 
 */

$(document).ready(function(){
	/*$('.datePicker').datepicker({
		autoclose : true,
		"setDate": new Date(),
		format : 'dd-mm-yyyy',
		endDate: new Date(),
		todayHighlight : 1,
	});
	*/
});
function downloadLoginStatusReport(){
	var currentDate=new Date();
	var validate="";
	currentDate=moment(new Date()).format('YYYY-MM-DD');
	$('#noUsage').hide();
	var from=$('#fromDate').val();
	var to=$('#toDate').val();
	
	var fromDate="";
	var toDate="";
	
	if(from != ""){
		$('#fromDateValid').hide();
		var fromDate=moment(from,'DD-MM-YYYY').format('YYYY-MM-DD');
	}else{
		$('#fromDateValid').show();
		validate="false";
	}
	
	if(to != ""){
		$('#toDateValid').hide();
		var toDate=moment(to,'DD-MM-YYYY').format('YYYY-MM-DD');
	}else{
		$('#toDateValid').show();
		validate="false";
	}
	
	if(from != "" && to != ""){
		if(fromDate > currentDate | toDate > currentDate){
			$('#futureDateValid').show();
			validate="false";
		}else{
			$('#futureDateValid').hide();
		}
		if(fromDate>toDate  ){
			$('#fromDateBig').show();
			validate="false";
		}else{
			$('#fromDateBig').hide();
		}
		
		if(validate == "false"){
			$('#errorAlert').show();
			return false;
		}else{
			$('#futureDateValid').hide();
			$('#fromDateBig').hide();
			$.ajax({
				url : '/ndcmp/api/superAdminLoginActualReportDownload',
				data : {
					"fromDate" : fromDate,
					"toDate" : toDate
				},
				type : 'GET',
				success : function(data) {
					if(data == "Empty"){
						$('#noUsage').show();
					}else{
						$('#noUsage').hide();
						$('#usageModal').modal('hide');
						window.open(data);
					}
				},error : function(){
				}
			});
		}
	}else{
		$('#errorAlert').show();
		return false;
	}
	
	/*var fromDate=$('#fromDate').val();
	var toDate=$('#toDate').val();
	if(fromDate == ""){
		alert("From date is required");
		return false;
	}else if(toDate == ""){
		alert("To date is required");
		return false;
	}else if(fromDate > currentDate | toDate > currentDate){
		if(fromDate > currentDate){
			alert("Future Date '"+fromDate+"' is not allowed");
		}
		if(toDate > currentDate){
			alert("Future Date '"+toDate+"' is not allowed");
		}
		
		return false;
	}else if(toDate<fromDate){
		alert("From Date Cannot be greater than To date");
		return false;
	}else{
		
		$.ajax({
			url : '/ndcmp/api/superAdminLoginActualReportDownload',
			data : {
				"fromDate" : moment(fromDate,'DD-MM-YYYY').format('YYYY-MM-DD'),
				"toDate" : moment(toDate,'DD-MM-YYYY').format('YYYY-MM-DD')
			},
			type : 'GET',
			success : function(data) {
				if(data == "Empty"){
					alert("No Login History Found")
				}else{
					window.open(data);
				}
			},error : function(){
				alert();
			}
		});
	}
	*/
	/*if($('#toDate').val() > currentDate){
		alert("Future Date is not allowed");
		return false;
	}*/
	
}