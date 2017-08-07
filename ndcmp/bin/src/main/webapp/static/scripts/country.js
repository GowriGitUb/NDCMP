$(document).ready(function(){
			$('#country-list').dataTable({
				 "aoColumnDefs" : [ {
			            'bSortable' : false,
			            'aTargets' : [ 4 ]
			       } ],
			       "pagingType": "full_numbers"
			});
			
			$('#tabs > li').removeClass('active');
			$('#usertab').attr('class', 'active');
			
			$('#userActiveDialog').on('show.bs.modal',function(event) {
					var getIdFromRow = $(event.relatedTarget).find('td:nth-child(3)').text();
					$(this).find('#userActive').html(
					$('<b> Are you sure to Activate Country ? '+ getIdFromRow + '</b>'));
			});
			
			$('#userActiveDialog').on('show.bs.modal',function(e) {
					$(this).find('.btn-ok').attr('href',$(e.relatedTarget).data('href'));
			});
			
			/* device dis approved modal window */
			$('#userDeactiveDialog').on('show.bs.modal',function(event) {
					var getIdFromRow = $(event.relatedTarget).find('td:nth-child(3)').text();
					$(this).find('#userDeactive').html($('<b> Are you sure to Deactivate Country ? '+ getIdFromRow + '</b>'));
			});
			$('#userDeactiveDialog').on('show.bs.modal',function(e) {
					$(this).find('.btn-ok').attr('href',
					$(e.relatedTarget).data('href'));
			});
			
			
			$('#myModal').on('show.bs.modal',function(event) {
					//var getIdFromRow = $(event.relatedTarget).attr('data-id');
					// if you wnat to take the text of the first cell 
					var getIdFromRow = $(event.relatedTarget).find('td:nth-child(3)').text();
					$(this).find('#process').html($('<b> Are you sure to delete Country Information ? '+ getIdFromRow + '</b>'));
			});

			$('#myModal').on('show.bs.modal',function(e) {$(this).find('.btn-ok').attr('href',
					$(e.relatedTarget).data('href'));
			});
});

$('#cancel').click(function() {
	window.history.back();
});

function submitCountryForm() {
	$('#countyAddform')
			.formValidation(
					{
						icon : {
							valid : 'glyphicon glyphicon-ok',
							invalid : 'glyphicon glyphicon-remove',
							validating : 'glyphicon glyphicon-refresh'
						},
						fields : {
							code : {
								validators : {
									notEmpty : {
										message : 'Country Code is Required'
									},
									stringLength : {
										min : 3,
										max : 25,
										message : 'Country Code must be more than 3 and less than 25 characters long'
									}
								}
							},
							name : {
								validators : {
									notEmpty : {
										message : 'Country Name is Required'
									},
									stringLength : {
										min : 5,
										max : 25,
										message : 'Country Name must be more than 5 and less than 25 characters long'
									}
								}
							}
						}
					});
	return true;
};

function getCountryInformation(id){
	var stateInfo = '';
	$.ajax({
		url : '/ndcmp/api/getCountryInformation',
		data : {
			countryId : id
		},
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data) {
			if (data != null) {
				countryInfo = data;
				$('#countryName').html(countryInfo.name);
				$('#countryCode').html(countryInfo.code);
				$('#countryStatus').html(countryInfo.status);
				$('#countryInformation').modal('show');
				
			}
		}
	});
}