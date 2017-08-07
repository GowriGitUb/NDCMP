$(document).ready(function() {
	$('#tabs > li').removeClass('active');
	$('#accessrightstab').attr('class', 'active');
	
});

function moveAll(from, to) {
	$('#' + from + ' option').each(function(i, obj) {
		$(this).remove().appendTo('#' + to).prop('selected', 'selected');
	});
}

function moveSelected(from, to) {
	$('#' + from + ' option:selected').each(function(i, obj) {
		$(this).remove().appendTo('#' + to).prop('selected', 'selected');
	});
}

function save() {
	$("#assigned  > option").each(function(i, obj) {
		$(this).prop("selected", 'selected');
	});
	$("#sb_form").submit();

}

function getfeatures(){

	var userRoleId = $("#userRoleId").val();
	available = $('#available');
	assigned = $('#assigned');

	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "http://localhost:8080/ndcmp/api/getFeatures",
		data : {
			"userRoleId" : userRoleId
		},
		dataType : 'json',
		success : function(response) {

			console.log(response.availableFeatures);
			console.log(response.assignedFeatures);
			
			available.html('');
			assigned.html('');
			
			$.each(response.availableFeatures, function(key, val) {
				available.append('<option value="'+val.featureId+'">'+val.featurename+'</option>');
			});
			
			$.each(response.assignedFeatures, function(key, val) {
				assigned.append('<option value="'+val.featureId+'">'+val.featurename+'</option>');
			});
			
			moveAll(available, assigned);
			moveSelected(available, assigned);
			save();

			/*console.log("SUCCESS: ", response.availableFeatures);
			console.log("SUCCESS: ", response.assignedFeatures[0].featureId);
			console.log("SUCCESS: ", response.assignedFeatures[0].featurecode);
			console.log("SUCCESS: ", response.assignedFeatures[0].featurename);*/
		},
		error : function(e) {
			console.log("ERROR: ", e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
	
}
