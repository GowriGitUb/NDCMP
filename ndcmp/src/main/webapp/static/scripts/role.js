$(document).ready(function(){
	$('#tabs > li').removeClass('active');
	$('#configTab').attr('class', 'active');
	$('#roleTab').attr('class', 'active');
	
		$('#roles-table').dataTable({
			 "aoColumnDefs" : [ {
		            'bSortable' : false,
		            'aTargets' : [ 4 ]
		       } ],
		       "pagingType": "full_numbers"
		});	
		


		$('#userRoleActiveDialog').on('show.bs.modal',function(event) {
				var getIdFromRow = $(event.relatedTarget).find('td:nth-child(3)').text();
				$(this).find('#userRoleActive').html(
				$('<b> Are you sure to Activate User Role ? '+ getIdFromRow + '</b>'));
		});
		$('#userRoleActiveDialog').on('show.bs.modal',function(e) {
				$(this).find('.btn-ok').attr('href',
				$(e.relatedTarget).data('href'));
		});

		/* device dis approved modal window */
		$('#userRoleDeactiveDialog').on('show.bs.modal',function(event) {
				var getIdFromRow = $(event.relatedTarget).find('td:nth-child(3)').text();
				$(this).find('#userRoleDeactive').html(
				$('<b> Are you sure to Deactivate User Role ? '+ getIdFromRow + '</b>'));
		});
		
		$('#userRoleDeactiveDialog').on('show.bs.modal',function(e) {
				$(this).find('.btn-ok').attr('href',
				$(e.relatedTarget).data('href'));
		});

		$('#myModal').on('show.bs.modal',function(event) {
				//var getIdFromRow = $(event.relatedTarget).attr('data-id');
				// if you wnat to take the text of the first cell 
				var getIdFromRow = $(event.relatedTarget).find('td:nth-child(3)').text();
				$(this).find('#process').html($('<b> Are you sure to delete Role Information ? '+ getIdFromRow + '</b>'));
		});

		$('#myModal').on('show.bs.modal',function(e) {
				$(this).find('.btn-ok').attr('href',
				$(e.relatedTarget).data('href'));
		});
		
	});

$('#cancel').click(function() {
	window.history.back();
});

function submitRoleForm() {
	$('#userroleAddForm')
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
										message : 'Role Name is required'
									},
									stringLength : {
										min : 3,
										max : 30,
										message : 'Role Name must be more than 6 and less than 30 characters long'
									}
								}
							},
							description : {
								validators : {
									notEmpty : {
										message : 'Description is required'
									},
									stringLength : {
										max : 250,
										message : 'Description must be less than 300 characters long'
									}
								}
							}
						}
					});
	return true;
};


function getRoleInformation(id){
	var roleInfo = '';
	$.ajax({
		url : '/ndcmp/api/getRoleInformation',
		data : {
			roleId : id
		},
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data) {
			if (data != null) {
				roleInfo = data;
				$('#name').html(roleInfo.name);
				$('#description').html(roleInfo.description);
				$('#status').html(roleInfo.status);
				$('#roleInformation').modal('show');
				
			}
		}
	});
}