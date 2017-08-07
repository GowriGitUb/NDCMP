
$(document).ready(function() {
	$("#agencyDiv").hide();
	
});

function getStateByCountry() {
	//var countryId = document.getElementById("countryId").value;
	var countryId = $('#countryId').val();
	if(countryId == ""){
		var ele = '<option value="">---Select State---</option>';
		$('#state').html(ele);
	}else{
		$.ajax({
			url : '/ndcmp/api/getstatebycountry',
			data : {
				countryId : countryId
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				if (data && data.trim != '' && data.length > 0) {
					var ele = '<option value="">---Select State---</option>';
					for ( var i = 0; i < data.length; i++) {
						ele += '<option value="' + data[i].id + '">'
								+ data[i].name + '</option>';
					}
					$('#state').html(ele);
//					 $('#state').selectpicker('refresh');
				}
			}
		});
	}
	
}


//to load state after shows the error message
function loadStates(countryID,stateID,agencyID,roleName) {
	$.ajax({
		url : '/ndcmp/api/loadStates',
		data : {
			countryID : countryID,
			stateID : stateID,
			agencyID : agencyID
		},
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data) {
			var states = data.listStates;
			var state = data.states;
			var agency = data.agency;
			var agencies = data.agencies;
			if (states && states.trim != '' && states.length > 0) {
				var ele = '<option value="">---Select State---</option>';
				for ( var i = 0; i < states.length; i++) {
					if(state.id == states[i].id){
						ele += '<option value="' + states[i].id + '" selected="selected">'
						+ states[i].name + '</option>';
					}else{
						ele += '<option value="' + states[i].id + '">'
						+ states[i].name + '</option>';
					}
					
				}
				$('#state').html(ele);
//				 $('#state').selectpicker('refresh');
			}
			
			var agencyValue = '<option value="" label="---Select Agency---"></option>';
			if(roleName == "STATUS_UPDATER" || roleName == "STATUS_REVIEWER"){
				for ( var i = 0; i < agencies.length; i++) {
					if(agency.id == agencies[i].id){
						agencyValue += '<option value="' + agencies[i].id + '" selected="selected">'
						+ agencies[i].name + '</option>';
					}else{
						agencyValue += '<option value="' + agencies[i].id + '">'
						+ agencies[i].name + '</option>';
					}
					
				}
				$('#agencyId').html(agencyValue);
				$('#agencyDiv').show();
			}
		}
	});
}


function loadAgencies(selected) {
	var userRoleId = selected.value;
	$.ajax({
		url : '/ndcmp/api/getAgencyList',
		data : {
			userRoleId : userRoleId
		},
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data) {
			$("#agencyDiv").hide();
			$("#agencyId").prop('selectedIndex',0);
			if (data && data.trim != '' && data.length > 0) {
				$("#agencyDiv").show();
				var ele = '<option value="">---Select Agency---</option>';
				//var ele = '';
				for ( var i = 0; i < data.length; i++) {
					if(data[i].userAgencyId == null) {
						ele +='<option value="' + data[i].id + '">'
								+ data[i].code + '</option>';
					}
				}
				for ( var i = 0; i < data.length; i++) {
					if(data[i].userAgencyId == null) {
						$('#agencyId').html(ele);
					}
				}
				for ( var i = 0; i < data.length; i++) {
						if(data[i].userAgencyId != null) {
							$('#agencyId option[value='+data[i].userAgencyId+']').attr('selected','selected');
						}
						
					}
			}
			
			
			else {
				$('#agencyId').html(ele);
			}
		}
	});
}
function isNumber(evt) {
	evt = (evt) ? evt : window.event;
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode > 31 && (charCode < 48 || charCode > 57)) {
		return false;
	}
	return true;
}

function getUserInformation(id){
	var userInfo = '';
	$.ajax({
		url : '/ndcmp/api/getUserDetails',
		data : {
			userId : id
		},
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data) {
			if (data != null) {
				userInfo = data;
				$('#name').html(userInfo.firstName+" "+userInfo.lastName);
				$('#username').html(userInfo.username);
				$('#emailAddress').html(userInfo.email);
				$('#phone').html(userInfo.phone);
				$('#address1').html(userInfo.address1);
				$('#address2').html(userInfo.address2);
				$('#address3').html(userInfo.address3);
				$('#userType').html(userInfo.userType);
				$('#country').html(userInfo.country.name);
				$('#state').html(userInfo.state.name);
				$('#zipcode').html(userInfo.zipcode);
				$('#status').html(userInfo.status);
				$('#agencyName').html(userInfo.agencyName);
				userRole = userInfo.userRoles;
				if(userRole.length > 0){
					for ( var i = 0; i < userRole.length; i++) {
						$('#userRoles').html(userRole[i].description);
					}
				}
				$('#userInformation').modal('show');
				
			}
		}
	});
}