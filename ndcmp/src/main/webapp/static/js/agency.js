/*get state by country*/

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