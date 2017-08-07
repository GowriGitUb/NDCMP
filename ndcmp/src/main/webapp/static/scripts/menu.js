/**
 * Added by Jeeva
 */


$(document).ready(function() {
	modules = getModules();
	cRole = getCurrentRole();
	allRoles = getAllRoles();
	var rId = $('#roleId').val();
	localStorage.setItem("role",cRole.name);
	if(cRole.name == 'SUPER_ADMIN'){
		for(var i = 0; i < allRoles.length; i++){
			var modalBody = '<ul class="nav nav-pills nav-stacked">'
				+ '<li id="dashboardTab"><a href="home"><i class="fa fa-dashboard"></i> <span>Dashboard</span>'
				+ '</a></li>';
			for (var k = 0; k < modules.length; k++) {
				if(modules[k].module == 'Project'){
					modalBody += '<li id="projectTab"><a href="viewProject?projectId=1&action=view"> <i class="fa fa-book"></i> <span>'+ "View Project" +'</span></a></li>';
				}
				if(modules[k].module == 'User'){
					modalBody += '<li id="userTab"><a href="userList"> <i class="fa fa-user"></i> <span>'+ modules[k].module +'</span></a></li>';
				}
				if(modules[k].module == 'Access Rights'){
					modalBody += '<li id="accesRightsTab"><a href="accessrightsList"> <i class="fa fa-lock"></i> <span>'+ modules[k].module +'</span></a></li>';
				}
			}
			modalBody += '<li id="configurationTab"><a href="#" class="dropdown-toggle">'
				+ '<i class="fa fa-wrench"></i><span>Configuration</span> <i class="fa fa-angle-right drop-icon"></i></a>'
				+'<ul class="submenu">';
			for (var j = 0; j < modules.length; j++) {
				if(modules[j].module == 'User Role'){
					modalBody += '<li id="roleTab"><a href="roleList">'+ modules[j].module +' </a></li>';
				}
				if(modules[j].module == 'Country'){
					modalBody += '<li id="countryTab"><a href="countryList">'+ modules[j].module +' </a></li>';
				}
				if(modules[j].module == 'State'){
					modalBody += '<li id="stateTab"><a href="stateslist">'+ modules[j].module +' </a></li>';
				}
				if(modules[j].module == 'Region'){
					modalBody += '<li id="regionTab"><a href="regionList">'+ modules[j].module +' </a></li>';
				}
				if(modules[j].module == 'Quarter'){
					modalBody += '<li id="quaterTab"><a href="quarterList">'+ modules[j].module +' </a></li>';
				}
				if(modules[j].module == 'Reporting Period'){
					modalBody += '<li id="reportingTab"><a href="reportingList">'+ modules[j].module +' </a></li>';
				}
				if(modules[j].module == 'Agency'){
					modalBody += '<li id="agencyTab"><a href="agencyList">'+ modules[j].module +' </a></li>';
				}
				if(modules[j].module == 'Status'){
					modalBody += '<li id="statusTab"><a href="statusList">'+ modules[j].module +' </a></li>';
				}
				if(modules[j].module == 'Allowed Device'){
					modalBody += '<li id="allowdDeviceTab"><a href="allowdDeviceList"> Mobile / TAB Registration </a></li>';
				}
			}
			modalBody += '</ul></li>';
			for (var j = 0; j < modules.length; j++) {
				if(modules[j].module == 'Reports'){
					modalBody += '<li id="reportsTab"><a href="#" class="dropdown-toggle"> <i class="fa fa-file"></i> <span>'+ modules[j].module +'</span> <i class="fa fa-angle-right drop-icon"></i></a>'
							+'<ul class="submenu"><li id="activityStatusTab"><a href="approverActivityStatus"> Activity Status </a></li>'+
												'<li id="loginStatusReport"><a onclick="usageReportModel()" style="cursor:pointer;" > Usage Report </a></li>'+
							'</ul></li>';
							/*+'<li id="planActualTab"><a href="planVsStatus"> Plan Vs Actual </a></li></ul></li>';*/
				}
			}
			modalBody += '<li id="logoutTab"><a href="logout"><i class="fa fa-power-off"></i><span>Logout</span></a></li></ul>';
			$('#modules').html(modalBody);
		}
		
	}else if(cRole.name == 'PROJECT_ADMIN'){
		var features = [];
			var modalBody = '<ul class="nav nav-pills nav-stacked">'
				+ '<li id="dashboardTab"><a href="home"><i class="fa fa-dashboard"></i> <span>Dashboard</span>'
				+ '</a></li>';
			for (var k = 0; k < modules.length; k++) {
				if(modules[k].module == 'Project'){
					features = getFeaturesByRoleidAndmoduleId(rId,modules[k].id);
					if(features.length > 0){
						for (var i = 0; i < features.length; i++) {
							if(features[i].feature == 'View Project'){
								modalBody += '<li id="viewTab"><a href="viewProject?projectId=1&action=view"> <i class="fa fa-book"></i> <span>'+ features[i].feature +'</span></a></li>';
							}
							if(features[i].feature == 'Configure Project'){
								modalBody += '<li id="projectTab"><a href="getConfigProject?projectId=1&action=getConfig"> <i class="fa fa-pied-piper-square"></i> <span>'+ features[i].feature +'</span></a></li>';
							}
						}
					}
				}
				if(modules[k].module == 'Quarter'){
					//modalBody += '<li id="quaterTab"><a href="quarterList">'+ modules[k].module +' </a></li>';
					modalBody += '<li id="quaterTab"><a href="quarterList"><i class="glyphicon glyphicon-dashboard" style="margin-top: 13px;"></i><span>'+ modules[k].module +'</span> </a></li>';
				}
				if(modules[k].module == 'Reporting Period'){
					modalBody += '<li id="reportingTab"><a href="reportingList"><i class="fa fa-table"></i><span>'+ modules[k].module +'</span> </a></li>';
				}
			}
			for (var j = 0; j < modules.length; j++) {
				if(modules[j].module == 'Reports'){
					modalBody += '<li id="reportsTab"><a href="#" class="dropdown-toggle"> <i class="fa fa-file"></i> <span>'+ modules[j].module +'</span> <i class="fa fa-angle-right drop-icon"></i></a>'
					+'<ul class="submenu"><li id="activityStatusTab"><a href="approverActivityStatus"> Activity Status </a></li></ul></li>';
					/*+'<li id="planActualTab"><a href="planVsStatus"> Plan Vs Actual </a></li></ul></li>';*/
				}
			}
			modalBody += '<li id="logoutTab"><a href="logout"><i class="fa fa-power-off"></i><span>Logout</span></a></li></ul>';
			$('#modules').html(modalBody);
	}else if(cRole.name == 'PROJECT_PLANNER'){
			var features = [];
			var modalBody = '<ul class="nav nav-pills nav-stacked">'
				+ '<li id="dashboardTab"><a href="home"><i class="fa fa-dashboard"></i> <span>Dashboard</span>'
				+ '</a></li>';
			for (var k = 0; k < modules.length; k++) {
				if(modules[k].module == 'Project'){
					features = getFeaturesByRoleidAndmoduleId(rId,modules[k].id);
					if(features.length > 0){
						for (var i = 0; i < features.length; i++) {
							if(features[i].feature == 'View Project'){
								modalBody += '<li id="viewTab"><a href="viewProject?projectId=1&action=view"> <i class="fa fa-book"></i> <span>'+ features[i].feature +'</span></a></li>';
							}
							if(features[i].feature == 'Plan Project'){
								modalBody += '<li id="planTab"><a href="filterHierarchy?action=plan"> <i class="fa fa-calendar"></i> <span>'+ features[i].feature +'</span></a></li>';
							}
							
						}
					}
				}
				if(modules[k].module == 'Quarter'){
					//modalBody += '<li id="quaterTab"><a href="quarterList">'+ modules[k].module +' </a></li>';
					modalBody += '<li id="quaterTab"><a href="quarterList"><i class="glyphicon glyphicon-dashboard" style="margin-top: 13px;"></i><span>'+ modules[k].module +'</span> </a></li>';
				}
				if(modules[k].module == 'Reporting Period'){
					modalBody += '<li id="reportingTab"><a href="reportingList"><i class="fa fa-table"></i><span>'+ modules[k].module +'</span> </a></li>';
				}
			}
			for (var j = 0; j < modules.length; j++) {
				if(modules[j].module == 'Reports'){
					modalBody += '<li id="reportsTab"><a href="#" class="dropdown-toggle"> <i class="fa fa-file"></i> <span>'+ modules[j].module +'</span> <i class="fa fa-angle-right drop-icon"></i></a>'
					+'<ul class="submenu"><li id="activityStatusTab"><a href="approverActivityStatus"> Activity Status </a></li></ul></li>';
					/*+'<li id="planActualTab"><a href="planVsStatus"> Plan Vs Actual </a></li></ul></li>';*/
				}
			}
			modalBody += '<li id="logoutTab"><a href="logout"><i class="fa fa-power-off"></i><span>Logout</span></a></li></ul>';
			$('#modules').html(modalBody);
	}else if(cRole.name == 'STATUS_REVIEWER'){
		var features = [];
			var modalBody = '<ul class="nav nav-pills nav-stacked">'
				+ '<li id="dashboardTab"><a href="home"><i class="fa fa-dashboard"></i> <span>Dashboard</span>'
				+ '</a></li>';
			for (var k = 0; k < modules.length; k++) {
				if(modules[k].module == 'Project'){
					features = getFeaturesByRoleidAndmoduleId(rId,modules[k].id);
					if(features.length > 0){
						for (var i = 0; i < features.length; i++) {
							if(features[i].feature == 'Review Status'){
								modalBody += '<li id="reviewTab"><a href="listReviewers"> <i class="fa fa-check-square"></i> <span>Review Reporting Status</span></a></li>';
							}
						}
					}
				}
			}
			modalBody += '<li id="submitForReworkTab"><a href="submitForRework"> <i class="fa fa-reply-all"></i> <span>Send for Rework</span></a></li>';
			modalBody += '<li id="submitApprovalTab"><a href="submitApprovalFilter"> <i class="fa fa-paper-plane"></i> <span>Submit for Approval</span></a></li>';
			for (var j = 0; j < modules.length; j++) {
				if(modules[j].module == 'Reports'){
					modalBody += '<li id="reportsTab"><a href="#" class="dropdown-toggle"> <i class="fa fa-file"></i> <span>'+ modules[j].module +'</span> <i class="fa fa-angle-right drop-icon"></i></a>'
					+'<ul class="submenu"><li id="activityStatusTab"><a href="statusReviewerReport"> Activity Status </a></li></ul></li>';
					/*+'<li id="planActualTab"><a href="planVsStatus"> Plan Vs Actual </a></li></ul></li>';*/
				}
			}
			modalBody += '<li id="logoutTab"><a href="logout"><i class="fa fa-power-off"></i><span>Logout</span></a></li></ul>';
			$('#modules').html(modalBody);
	}else if(cRole.name == 'STATUS_UPDATER'){
		var features = [];
			var modalBody = '<ul class="nav nav-pills nav-stacked">'
				+ '<li id="dashboardTab"><a href="home"><i class="fa fa-dashboard"></i> <span>Dashboard</span>'
				+ '</a></li>';
			for (var k = 0; k < modules.length; k++) {
				if(modules[k].module == 'Project'){
					features = getFeaturesByRoleidAndmoduleId(rId,modules[k].id);
					if(features.length > 0){
						for (var i = 0; i < features.length; i++) {
							if(features[i].feature == 'Update Status'){
								modalBody += '<li id="partnerTab"><a href="partnerFilter"> <i class="fa fa-signal"></i> <span>Update Reporting Status</span></a></li>';
							}
						}
					}
				}
			}
			modalBody += '<li id="submitReviewTab" ><a href="submitFilter"> <i class="fa fa-paper-plane"></i> <span>Submit for Review</span></a></li>';
			for (var j = 0; j < modules.length; j++) {
				if(modules[j].module == 'Reports'){
					modalBody += '<li id="reportsTab"><a href="#" class="dropdown-toggle"> <i class="fa fa-file"></i> <span>'+ modules[j].module +'</span> <i class="fa fa-angle-right drop-icon"></i></a>'
					+'<ul class="submenu"><li id="activityStatusTab"><a href="statusUpdaterReport"> Activity Status </a></li></ul></li>';
					/*+'<li id="planActualTab"><a href="planVsStatus"> Plan Vs Actual </a></li></ul></li>';*/
				}
			}
			modalBody += '<li id="logoutTab"><a href="logout"><i class="fa fa-power-off"></i><span>Logout</span></a></li></ul>';
			$('#modules').html(modalBody);
	
		
	} else if(cRole.name == 'STATUS_APPROVER'){
			var features = [];
			var modalBody = '<ul class="nav nav-pills nav-stacked">'
				+ '<li id="dashboardTab"><a href="home"><i class="fa fa-dashboard"></i> <span>Dashboard</span>'
				+ '</a></li>';
			for (var k = 0; k < modules.length; k++) {
				if(modules[k].module == 'Project'){
					features = getFeaturesByRoleidAndmoduleId(rId,modules[k].id);
					if(features.length > 0){
						for(var i = 0 ; i < features.length ; i++){
							if(features[i].feature == 'View Project'){
								modalBody += '<li id="projectTab"><a href="viewProject?projectId=1&action=view"> <i class="fa fa-book"></i> <span>'+ "View Project" +'</span></a></li>';
							}
							if(features[i].feature == 'Approve Status'){
								modalBody += '<li id="approveTab"><a href="approverFilter"> <i class="fa fa-thumbs-up"></i> <span>Review & Approve Reporting Status</span></a></li>';
							}
						}
					}
				}
			}
			modalBody += '<li id="submitForReworkTab"><a href="submitForRework"> <i class="fa fa-reply-all"></i> <span>Submit for Rework</span></a></li>';
			for (var j = 0; j < modules.length; j++) {
				if(modules[j].module == 'Reports'){
					modalBody += '<li id="reportsTab"><a href="#" class="dropdown-toggle"> <i class="fa fa-file"></i> <span>'+ modules[j].module +'</span> <i class="fa fa-angle-right drop-icon"></i></a>'
					+'<ul class="submenu"><li id="approverReportTab"><a href="approverActivityStatus"> Activity Status </a></li></ul></li>';
					/*+'<li id="planActualTab"><a href="planVsStatus"> Plan Vs Actual </a></li></ul></li>';*/
				}
			}
			modalBody += '<li id="logoutTab"><a href="logout"><i class="fa fa-power-off"></i><span>Logout</span></a></li></ul>';
			$('#modules').html(modalBody);
	}
	
	//To scroll top
	$(window).scroll(function(){ 
        if ($(this).scrollTop() > 100) { 
            $('#scroll').fadeIn(); 
        } else { 
            $('#scroll').fadeOut(); 
        } 
    }); 
    $('#scroll').click(function(){ 
        $("html, body").animate({ scrollTop: 0 }, 300); 
        return false; 
    }); 
});

function getModules(){
	var modules = '';
	$.ajax({
		url : '/ndcmp/api/getModules',
		type : 'GET',
		async : false,
		cache : false,
		success : function(result) {
			if (result != undefined && result != null && result != '') {
				modules = result;
			}
		}
	});
	return modules;
}

function getCurrentRole(){
	var currentRole = '';
	$.ajax({
		url : '/ndcmp/api/getRoles',
		type : 'GET',
		async : false,
		cache : false,
		success : function(result) {
			if (result != undefined && result != null && result != '') {
				currentRole = result;
			}
		}
	});
	return currentRole;
}

function getAllRoles(){
	var allRoles = '';
	$.ajax({
		url : '/ndcmp/api/getAllRoles',
		type : 'GET',
		async : false,
		cache : false,
		success : function(result) {
			if (result != undefined && result != null && result != '') {
				allRoles = result;
			}
		}
	});
	return allRoles;
}


function getProperAccess(value){
	var access = '';
	$.ajax({
		url : '/ndcmp/api/getCurrentLoginUrlModuleName',
		data : {
			moduleName : value
		},
		type : 'GET',
		async : false,
		cache : false,
		success : function(result) {
			if (result != undefined && result != null && result != '') {
				access = result;
			}
		}
	});
	return access;
}

function getFeaturesByRoleidAndmoduleId(roleId,moduleId){
	var features = [];
	$.ajax({
		url : '/ndcmp/api/getfeaturesByRoleidAndmoduleId',
		data : {
			id : roleId,
			mid : moduleId
		},
		type : 'GET',
		async : false,
		cache : false,
		success : function(result) {
			if (result != undefined && result != null && result != '') {
				features = result;
			}
		}
	});
	return features;
}
function usageReportModel(){
	var date=moment(new Date()).format('DD-M-YYYY');
	$('.datePicker').val(date);
	$('#usageModal').modal('show');
}