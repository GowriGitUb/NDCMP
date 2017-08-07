$(document).ready(function(){
		$('#region-list').dataTable({
			 "aoColumnDefs" : [ {
		            'bSortable' : false,
		            'aTargets' : [ 6 ]
		       }],
		       "pagingType": "full_numbers"
		});	
		
		
		$('#tabs > li').removeClass('active');
		$('#configTab').attr('class', 'active');
		$('#regionTab').attr('class', 'active');
		

		$('#userActiveDialog').on('show.bs.modal',function(event) {
				var getIdFromRow = $(event.relatedTarget).find('td:nth-child(3)').text();
				$(this).find('#userActive').html($('<b> Are you sure to Activate Region ? '+ getIdFromRow + '</b>'));
		});
		$('#userActiveDialog').on('show.bs.modal',function(e) {
				$(this).find('.btn-ok').attr('href',
				$(e.relatedTarget).data('href'));
		});
		
		/* device dis approved modal window */
		$('#userDeactiveDialog').on('show.bs.modal',function(event) {
				var getIdFromRow = $(event.relatedTarget).find('td:nth-child(3)').text();
				$(this).find('#userDeactive').html($('<b> Are you sure to Deactivate Region ? '+ getIdFromRow + '</b>'));
		});
		$('#userDeactiveDialog').on('show.bs.modal',function(e) {
				$(this).find('.btn-ok').attr('href',
				$(e.relatedTarget).data('href'));
		});
		
		$('#myModal').on('show.bs.modal',function(event) {
				var getIdFromRow = $(event.relatedTarget).find('td:nth-child(3)').text();
				$(this).find('#process').html($('<b> Are you sure to delete Region Information ? '+ getIdFromRow + '</b>'));
		});

		$('#myModal').on('show.bs.modal',function(e) {
				$(this).find('.btn-ok').attr('href',
				$(e.relatedTarget).data('href'));
		});
});


$('#cancel').click(function() {
	window.history.back();
});
function submitRegionForm(){
	$('#regionAddForm').formValidation({
		icon : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			code: {
                validators : {
					notEmpty : {
						message : 'Region Code is Required'
					},
					stringLength : {
						min : 2,
						max : 10,
						message : 'Region code must be more than 2 and less than 10 characters long'
					}
				}
            },
            name: {
                validators : {
					notEmpty : {
						message : 'Region Name is Required'
					},
					stringLength : {
						min : 3,
						max : 30,
						message : 'Region code must be more than 3 and less than 30 characters long'
					}
				}
            },
            
            'country.id': {
                validators : {
					notEmpty : {
						message : 'Country is Required'
					}
				}
            },
            'states.id': {
                validators : {
					notEmpty : {
						message : 'State is Required'
					}
				}
            },
		}
	});
	return true;
}

function getRegionInformation(id){
	var regionInfo = '';
	$.ajax({
		url : '/ndcmp/api/getRegionInformation',
		data : {
			regionId : id
		},
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data) {
			if (data != null) {
				regionInfo = data;
				$('#name').html(regionInfo.name);
				$('#code').html(regionInfo.code);
				$('#country').html(regionInfo.country.name);
				$('#states').html(regionInfo.states.name);
				$('#status').html(regionInfo.status);
				$('#regionInformation').modal('show');
				
			}
		}
	});
}