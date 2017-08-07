$(document).ready(function(){
		$('#state-list').dataTable({
			 "aoColumnDefs" : [ {
		            'bSortable' : false,
		            'aTargets' : [ 5 ]
		       }],
		       "pagingType": "full_numbers"
		});	
		
		
			$('#tabs > li').removeClass('active');
			$('#configTab').attr('class', 'active');
			$('#stateTab').attr('class', 'active');
		
		
		$('#userActiveDialog').on('show.bs.modal',function(event) {
				var getIdFromRow = $(event.relatedTarget).find('td:nth-child(3)').text();
				$(this).find('#userActive').html($('<b> Are you sure to Activate State ? '+ getIdFromRow + '</b>'));
		});
		$('#userActiveDialog').on('show.bs.modal',function(e) {
				$(this).find('.btn-ok').attr('href',
				$(e.relatedTarget).data('href'));
		});
		
		/* device dis approved modal window */
		$('#userDeactiveDialog').on('show.bs.modal',function(event) {
				var getIdFromRow = $(event.relatedTarget).find('td:nth-child(3)').text();
				$(this).find('#userDeactive').html($('<b> Are you sure to Deactivate State ? '+ getIdFromRow + '</b>'));
		});
		$('#userDeactiveDialog').on('show.bs.modal',function(e) {
				$(this).find('.btn-ok').attr('href',
				$(e.relatedTarget).data('href'));
		});
		
		$('#myModal').on('show.bs.modal',function(event) {
				var getIdFromRow = $(event.relatedTarget).find('td:nth-child(3)').text();
				$(this).find('#process').html($('<b> Are you sure to delete State Information ? '+ getIdFromRow + '</b>'));
		});

		$('#myModal').on('show.bs.modal',function(e) {
				$(this).find('.btn-ok').attr('href',
				$(e.relatedTarget).data('href'));
		});
});

$('#cancel').click(function() {
	window.history.back();
});

function submitStateForm() {
	$('#stateAddform')
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
										message : 'State Name is required'
									},
									stringLength : {
										min : 3,
										max : 25,
										message : 'State Name must be more than 3 and less than 25 characters long'
									}
								}
							},
							code : {
								validators : {
									notEmpty : {
										message : 'State Code is required'
									},
									stringLength : {
										min : 2,
										max : 25,
										message : 'State Code must be more than 2 and less than 25 characters long'
									}
								}
							},
							'country.id' : {
								validators : {
									notEmpty : {
										message : 'Country is required'
									}
								}
							}
						}

					});

	return true;
};

function getStateInformation(id){
	var stateInfo = '';
	$.ajax({
		url : '/ndcmp/api/getStateInformation',
		data : {
			stateId : id
		},
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data) {
			if (data != null) {
				stateInfo = data;
				$('#name').html(stateInfo.name);
				$('#code').html(stateInfo.code);
				$('#country').html(stateInfo.country.name);
				$('#status').html(stateInfo.status);
				$('#stateInformation').modal('show');
				
			}
		}
	});
}