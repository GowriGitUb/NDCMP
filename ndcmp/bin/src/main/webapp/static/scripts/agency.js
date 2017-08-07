/*get state by country*/
$(document).ready(function(){
		var agencyId=$('#agencyId').val();
		if(agencyId == 0){
			$('#stateId').html("");
			$('#regionId').html("");
		}
	
			$('#tabs > li').removeClass('active');
			$('#configTab').attr('class', 'active');
			$('#agencyTab').attr('class', 'active');
	
			$('#agency-list').dataTable({
				 "aoColumnDefs" : [ {
			            'bSortable' : false,
			            'aTargets' : [ 4 ]
			       }],
			       "pagingType": "full_numbers"
			});	
	
			$('#cancel').click(function() {
				window.history.back();
			});

			
			$('#myModal').on('show.bs.modal',function(event) {
					var getIdFromRow = $(event.relatedTarget).find('td:nth-child(3)').text();
					$(this).find('#process').html(
					$('<b> Are you sure to delete Agency Information ? '+ getIdFromRow + '</b>'));
			});

			$('#myModal').on('show.bs.modal',function(e) {
					$(this).find('.btn-ok').attr('href',
					$(e.relatedTarget).data('href'));
			});

			$('#agencyActiveDialog').on('show.bs.modal',function(event) {
					var getIdFromRow = $(event.relatedTarget).find('td:nth-child(3)').text();
					$(this).find('#agecnyActive').html(
					$('<b> Are you sure to Activate Agency ? '+ getIdFromRow + '</b>'));
			});
			$('#agencyActiveDialog').on('show.bs.modal',function(e) {
					$(this).find('.btn-ok').attr('href',
					$(e.relatedTarget).data('href'));
			});

/* device dis approved modal window */
			$('#agencyDeactiveDialog').on('show.bs.modal',function(event) {
					var getIdFromRow = $(event.relatedTarget).find('td:nth-child(3)').text();
					$(this).find('#agencyDeactive').html($('<b> Are you sure to Deactivate Agency ? '+ getIdFromRow + '</b>'));
			});
			$('#agencyDeactiveDialog').on('show.bs.modal',function(e) {
					$(this).find('.btn-ok').attr('href',$(e.relatedTarget).data('href'));
			});
				
				
});

function getStateByCountry() {
	var countryId = document.getElementById("countryId").value;
	if(countryId == "" || countryId == ''){
		var state = '<option value="">---Select State---</option>';
		var region = '<option value="">---Select Region---</option>';
		$('#stateId').html(state);
		$('#regionId').html(region);
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
				if (data.length !=0) {
					var state = '<option value="">---Select State---</option>';
					for ( var i = 0; i < data.length; i++) {
						state += '<option value="' + data[i].id + '">'+ data[i].name + '</option>';
					}
					$('#stateId').html(state);
				}else{
					$('#stateId').html("");
				}
			}
		});
	}
}

/*get region by state*/

function getRegionByState() {
	var stateId = document.getElementById("stateId").value;
	if(stateId == "" || stateId == ''){
		var region = '<option value="">---Select Region---</option>';
		$('#regionId').html(region);
	}else{
		$.ajax({
			url : '/ndcmp/api/getRegionByState',
			data : {
				stateId : stateId
			},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				if (data && data.trim != '' && data.length > 0) {
					var region = '<option value="">---Select Region---</option>';
					for ( var i = 0; i < data.length; i++) {
						region += '<option value="' + data[i].id + '">'+ data[i].name + '</option>';
					}
					$('#regionId').html(region);
				}else{
					var region = '<option value="">---Select Region---</option>';
					$('#regionId').html(region);
				}
			}
		});
	}
	
}


function submitAgencyForm() {
	$('#agencyAddForm').formValidation({
		icon : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			code : {
				validators : {
					notEmpty : {
						message : 'Agency Code is required'
					}
				}
			},name : {
				validators : {
					notEmpty : {
						message : 'Agency Name is required'
					}
				}
			},
			/*agencyType : {
				validators : {
					notEmpty : {
						message : 'Agency Type is required'
					}
				}
			},*/
			/*roleIds : {
				validators : {
					notEmpty : {
						message : 'Authority is Required'
					}
				}
			},*/
			'country.id' : {
				validators : {
					notEmpty : {
						message : 'Country is required'
					}
				}
			},
			'state.id' : {
				validators : {
					notEmpty : {
						message : 'State is required'
					}
				}
			},
			'region.id' : {
				validators : {
					notEmpty : {
						message : 'Region is required'
					}
				}
			}
		}
	});
	return true;
};

/*//to get and set users
function getAuthorityUser(selected){
	var userRoleId = "";
	if(selected.value != null) {
		userRoleId = selected.value;
	} else {
		userRoleId = selected;
	}
	$.ajax({
		url  : "/ndcmp/api/getUsers",
		data : {
			userRoleId : userRoleId
		}, 
		
		type : 'GET',
		async:false,
		dataType : 'json',
		success : function(data){
			if(data != null){
				var agencyAuthority = '<option value="">---Select Agency Authority---</option>';
				for (var i = 0; i < data.length; i++) {
					agencyAuthority += '<option value="' + data[i].id + '">' + data[i].firstName + ' '+data[i].lastName + '</option>';
				}
				$('#agencyAuthorityId').html(agencyAuthority);
			}
		}
	});
}*/

//Edit Agency
function getAgency(agencyId){
	localStorage.setItem("agencyId", agencyId);
	window.location.href = "/ndcmp/api/addAgency?id="+agencyId;
}
function setAgencyValues(){
	var agencyInfo = "";
	var agencyId=localStorage.getItem("agencyId");
	$.ajax({
		url  : "/ndcmp/api/getAgency",
		data : {
			id : agencyId
		}, 
		type : 'GET',
		async:false,
		dataType : 'json',
		success : function(data){
			if(data != null){
				agencyInfo = data;
				console.log(data);
				/*var userRoleList = agencyInfo.userRoleNames;
				var agencyType = '<option value="">---Select Agency Type---</option>';
				for (var i = 0; i < userRoleList.length; i++) {
					agencyType += '<option id='+userRoleList[i].name +' value="' + userRoleList[i].id + '">' + userRoleList[i].name + '</option>';
				}
				$('#agencyTypeId').html(agencyType);
				var userRole=agencyInfo.agency2.agencyType;
				$('#agencyTypeId option[id='+userRole+']').attr('selected','selected');	*/
				
				/*var userList = agencyInfo.userNames;
				var agencyAuthority = '<option value="">---Select Agency Authority---</option>';
				for (var i = 0; i < userList.length; i++) {
					agencyAuthority += '<option value="' + userList[i].id + '">' + userList[i].firstName + ' '+userList[i].lastName + '</option>';
				}
				$('#agencyAuthorityId').html(agencyAuthority);*/
				
				var userList = agencyInfo.userNames;
				var agencyAuthority = '<option value="">---Select Agency Authority---</option>';
				for (var i = 0; i < userList.length; i++) {
					agencyAuthority += '<option value="' + userList[i].id + '">' + userList[i].firstName + ' '+userList[i].lastName + '</option>';
				}
				$('#agencyAuthorityId').html(agencyAuthority);
				
				var countryList = agencyInfo.countrieNames;
				var countries = '<option value="">---Select Country---</option>';
				for (var i = 0; i < countryList.length; i++) {
					countries += '<option value="' + countryList[i].id + '">' + countryList[i].name + '</option>';
				}
				$('#countryId').html(countries);
				/*var country=agencyInfo.agency2.country;
					$('#countryId option[value='+country.id+']').attr('selected','selected');	*/
				
				if(agencyInfo.agency2 == null){
					var states = '<option value="">---Select State---</option>';
					var regions = '<option value="">---Select Region---</option>';
					$('#stateId').html(states);
					$('#regionId').html(regions);
				}else{
					var statesList = agencyInfo.stateNames;
					var states = '<option value="">---Select State---</option>';
					for (var i = 0; i < statesList.length; i++) {
						states += '<option value="' + statesList[i].id + '">' + statesList[i].name + '</option>';
					}
					$('#stateId').html(states);
					/*var state=agencyInfo.agency2.state;
						$('#stateId option[value='+state.id+']').attr('selected','selected');	*/
					
					var regionList = agencyInfo.regionNamess;
					var regions = '<option value="">---Select Region---</option>';
					for (var i = 0; i < regionList.length; i++) {
						regions += '<option value="' + regionList[i].id + '">' + regionList[i].name + '</option>';
					}
					$('#regionId').html(regions);
				}
				
				if(agencyInfo.agency2 != null) {
					$('#code').val(agencyInfo.agency2.code);
					$('#name').val(agencyInfo.agency2.name);
					
					var users=agencyInfo.agency2.agencyAuthority;
					$.each(users,function(pi,pv){
						$('#agencyAuthorityId option[value='+pv.id+']').attr('selected','selected');	
					});
					
					var country=agencyInfo.agency2.country;
					$('#countryId option[value='+country.id+']').attr('selected','selected');	
					
					var state=agencyInfo.agency2.state;
					$('#stateId option[value='+state.id+']').attr('selected','selected');	
					
					var region=agencyInfo.agency2.region;
					$('#regionId option[value='+region.id+']').attr('selected','selected');	
					
				}else{
					$('#code').val("");
					$('#name').val("");
				}
				/*var region=agencyInfo.agency2.region;
					$('#regionId option[value='+region.id+']').attr('selected','selected');	*/
			}
		}
	});
}

function getAgencyInformation(id){
	var stateInfo = '';
	$.ajax({
		url : '/ndcmp/api/getAgencyInformation',
		data : {
			agencyId : id
		},
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data) {
			if (data != null) {
				agencyInfo = data;
				$('#agencyCode').html(agencyInfo.code);
				$('#agencyName').html(agencyInfo.name);
				var users = agencyInfo.agencyAuthority;
				var name = '';
				for(var i = 0 ; i < users.length ; i++){
					name += ',' + users[i].firstName + ' '+  users[i].lastName;
				}
				
				if(name && name.length > 2){
					name = name.substring(1, name.length);
				}

				$('#agencyAuthority').html(name);
				$('#agencyCountry').html(agencyInfo.country.name);
				$('#agencyRegion').html(agencyInfo.region.name);
				$('#agencyState').html(agencyInfo.state.name);
				$('#agencyStatus').html(agencyInfo.status);
				$('#agencyInformation').modal('show');
				
			}
		}
	});
}