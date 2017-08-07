/**
 * Added By Thomas
 * 
 * List all the table with content
 * 
 */

/**
 * Load all the user details
 */

function loadAllUser(){
	
	$('#userList').DataTable({
		"iDisplayLength": 10,
		 "processing": true,
		columns: [
		            { title: "#" },
		            { title: "Name" },
		            { title: "Username" },
		            { title: "Email" },
		            { title: "Role" },
		            { title: "Country" },
		            { title: "State" },
		            { 
		            	title: 'Action <a href="createUser"><img src="/ndcmp/static/images/add.png" alt="Add" title="Add User"></a>', 
		            	sorting: false,
		            }
		           
		        ]
	});
}

/**
 * Load all the project details
 */

function loadAllTheme(){
	
	$('#themelist').DataTable({
		"iDisplayLength": 10,
		 "processing": true,
		columns: [
		            { title: "ThemeName" },
		           /* { title: "Description" },*/	
		            { title: "StrategicPillar"},
		            { 
		            	title: 'Action <a href="editTheme?themeId=0"><img src="/ndcmp/static/images/add.png" alt="Add" title="Add Theme"></a>', 
		            	sorting: false,
		            }
		        ]
	});
}


function loadAllOutput(){
	
	$('#outputlist').DataTable({
		"iDisplayLength": 10,
		 "processing": true,
		columns: [
		            { title: "Sequence Number" },
		            { title: "Name" },
		            { title: "Description" },
		            { title: "Outcome" },
		            { 
		            	title: 'Action <a href="editOutput?outputId=0"><img src="/ndcmp/static/images/add.png" alt="Add" title="Add Output"></a>', 
		            	sorting: false,
		            }
		           
		        ]
	});
}


function loadAllProject(projectCount){
	var action = '';
	if(projectCount == 0){
		action = 'Action <a href="createProject?projectId=0"><img src="/ndcmp/static/images/add.png" alt="Add" title="Add Project"></a>';
	} else {
		action = 'Action';
	}
	$('#projectsList').DataTable({
		"iDisplayLength": 10,
		 "processing": true,
		columns: [
		          	{title: "#"},
		            { title: "ProjectName" },
		            { title: "Admin" },
		            { title: "Country" },
		            { title: "States" },
		            { title: "Region" },
		            { title: "Description" },
		            { 
		            	title: action, 
		            	sorting: false,
		            }
		           
		        ]
	});
}

/**
 * Load all the StrategicPillar details
 */

function loadAllStrategicPillar(){
	
	$('#strategicPillarList').DataTable({
		"iDisplayLength": 10,
		 "processing": true,
		columns: [
		            { title: "Name" },
		          /*  { title: "Description" },*/
		            { title: "Status" },
		            { title: "Project Name" },
		            { 
		            	title: 'Action <a href="getStrategicpillar"><img src="/ndcmp/static/images/add.png" alt="Add" title="Add StrategicPillar"></a>', 
		            	sorting: false,
		            }
		           
		        ]
	});
}

/**
 * Load all the StrategicPillar details
 */

function loadAllOutcome(){
	
	$('#outcomeList').DataTable({
		"iDisplayLength": 10,
		 "processing": true,
		columns: [
		            { title: "Sequence Number" },
		            { title: "Name" },
		            { title: "Description" },
		            { title: "Status" },
		            { title: "Theme Name" },
		            { 
		            	title: 'Action <a href="getOutcome"><img src="/ndcmp/static/images/add.png" alt="Add" title="Add Outcome"></a>', 
		            	sorting: false,
		            }
		           
		        ]
	});
}

function loadAllReportingPeriod(){
	
	$('#reportingList').DataTable({
		"iDisplayLength": 10,
		 "processing": true,
		columns: [
		            { title: "Name" },
		            { title: "Year" },
		            { title: "StartDate" },
		            { title: "EndDate" },
		            { 
		            	title: 'Action <a href="createReportingPeriod?reportingPeriodId=0"><img src="/ndcmp/static/images/add.png" alt="Add" title="Add Reporting Period"></a>', 
		            	sorting: false,
		            }
		           
		        ]
	});
}



/**
 * Load all the role details
 */

function loadAllRoles(){
	$('#roleList').DataTable({
		"iDisplayLength": 10,
		 "processing": true,
		columns: [
		          	{title: "#"},
		            { title: "Name" },
		            { title: "Description" },
		            { title: "Status" },
		            { 
		            	title: 'Action <a href="getUserrole?id=0"><img src="/ndcmp/static/images/add.png" alt="Add" title="Add Role"></a>', 
		            	sorting: false,
		            }
		           
		        ]
	});
}

/**
 * Load all the state details
 */

function loadAllStats(){
	$('#stateslist').DataTable({
		"iDisplayLength": 10,
		 "processing": true,
		columns: [
		            { title: "State" },
		            { title: "State Code" },
		            { title: "Country" },
		            { 
		            	title: 'Action <a href="getStates?stateId=0"><img src="/ndcmp/static/images/add.png" alt="Add" title="Add Role"></a>', 
		            	sorting: false,
		            }
		           
		        ]
	});
}

/**
 * Load all the region details
 */

function loadAllRegions(){
	$('#regionlist').DataTable({
		"iDisplayLength": 10,
		 "processing": true,
		columns: [
		          	{title: "#"},
		            { title: "Region Code" },
		            { title: "Name" },
		            { title: "State" },
		            { title: "Country" },
		            { 
		            	title: 'Action <a href="getRegion?regionId=0"><img src="/ndcmp/static/images/add.png" alt="Add" title="Add Role"></a>', 
		            	sorting: false,
		            }
		           
		        ]
	});
}

/**
 * Load All the country details
 */
function loadAllCountries(){
	$('#countrylist').DataTable({
		"iDisplayLength": 10,
		 "processing": true,
		columns: [
		          	{title: "#"},
		            { title: "Country Code" },
		            { title: "Name" },
		            { 
		            	title: 'Action <a href="getCountry?countryId=0"><img src="/ndcmp/static/images/add.png" alt="Add" title="Add Country"></a>', 
		            	sorting: false,
		            }
		           
		        ]
	});
}

/**
 * Load All the status details
 */
function loadAllStatus(){
	$('#statusList').DataTable({
		"iDisplayLength": 10,
		 "processing": true,
		columns: [
		          	{ title: "#"},
		            { title: "Status Name" },
		            { title: "Description" },
		            { title: "Status Color" },
		            { 
		            	title: 'Action <a href="getStatus?id=0"><img src="/ndcmp/static/images/add.png" alt="Add" title="Add Status"></a>', 
		            	sorting: false,
		            }
		           
		        ]
	});
}


/**
 * Load All the Access Rights Details
 */
function loadAccRights(){
	$('#accrightsList').DataTable({
		"iDisplayLength": 10,
		 "processing": true,
		columns: [
		            { title: "User Role" },
		            { title: "Module Feature" },
		            { title: "Insert" },
		            { title: "View" },
		            { title: "Update" },
		            { title: "Delete" },
		            { 
		            	title: 'Action <a href="getAccRightsList?accessrightsId=0"><img src="/ndcmp/static/images/add.png" alt="Add" title="Add Access Rights"></a>', 
		            	sorting: false,
		            }
		           
		        ]
	});
}
	
	
	/**
	 * Load All the Access Rights Details
	 */
	function loadAccessRights(){
		$('#accessrightsList').DataTable({
			/*"iDisplayLength": 10,
			 "processing": true,
			 "pagingType" : "full_numbers",
			columns: [
			          { title: "Id",
			        	visible : false,
			        	sorting : true},
			            { 
			        		title: "User Role",
			        		"bSortable" : true
			        	},
			            { title: "Module Feature" },
			            { 
			            	title: 'Action', <a href="getAccessRightsList?accessrightsId=0"><img src="/ndcmp/static/images/add.png" alt="Add" title="Add Access Rights"></a>
			            	"bSortable": false
			            }
			        ],
			        "oLanguage" :{
			        	"sEmptyTable" : "No data found"
			        }*/
			"aoColumnDefs" : [ {
	            'bSortable' : false,
	            'aTargets' : [ 1 ]
	       } ],
	       "pagingType": "full_numbers"
		});
}

	
	/**
	 * Load all the agency details
	 */

	function loadAllAgency(){
		$('#agencyList').DataTable({
			"iDisplayLength": 10,
			 "processing": true,
			columns: [
			          	{title: "#"},
			            { title: "Agency Name" },
			            { title: "Agency Type" },
			            { title: "Status" },
			            { 
			            	title: 'Action <a href="getAgency?id=0"><img src="/ndcmp/static/images/add.png" alt="Add" title="Add Agency"></a>', 
			            	sorting: false,
			            }
			           
			        ]
		});
	}
	
	/**
	 * Load All the allowed device details
	 */
	function loadAllAllowedDevice(){
		$('#allowedDeviceList').DataTable({
			"iDisplayLength": 10,
			 "processing": true,
			columns: [
			            { title: "Mobile Name" },
			            {title : "Os Version"},
			            {title : "SDK Version"},
			            {title : "Status"},
			            { 
			            	title: 'Action', 
			            	sorting: false,
			            }
			           
			        ]
		});
	}
	
	/**
	 * Load all the key details
	 */

	function loadAllKeyActivity(){
		$('#keyActivityList').DataTable({
			"iDisplayLength": 10,
			 "processing": true,
			columns: [
			            { title: "Sequence Number" },
			            { title: "Name" },
			            { title: "Output" },
			            { 
			            	title: 'Action <a href="getActivity?id=0"><img src="/ndcmp/static/images/add.png" alt="Add" title="Add Key Activity"></a>', 
			            	sorting: false,
			            }
			           
			        ]
		});
	}
	
	/**
	 * Load all the sub activity details
	 */

	function loadAllSubActivity(){
		$('#subActivityList').DataTable({
			"iDisplayLength": 10,
			 "processing": true,
			columns: [
			            { title: "Sequence Number" },
			            { title: "Name"},
			            { title: "Key Activity"},
			            { title: "Lead Agency"},
			            { title: "Partner Agency"},
			            { title: "MOV"},
			            { 
			            	title: 'Action <a href="getSubActivity?id=0"><img src="/ndcmp/static/images/add.png" alt="Add" title="Add Sub Activity"></a>', 
			            	sorting: false,
			            }
			           
			        ]
		});
	}
	
	/**
	 * Load all the Larget details
	 */

	function loadAllTargets(){
		$('#targetList').DataTable({
			"iDisplayLength": 10,
			 "processing": true,
			columns: [
			            { title: "Message" },
			            { title: "Output"},
			            { 
			            	title: 'Action <a href="getTarget?id=0"><img src="/ndcmp/static/images/add.png" alt="Add" title="Add Target"></a>', 
			            	sorting: false,
			            }
			           
			        ]
		});
	}
	
	/**
	 * Load all the Larget details
	 */

	function loadAllIndicators(){
		$('#indicatorList').DataTable({
			"iDisplayLength": 10,
			 "processing": true,
			columns: [
			            { title: "Message" },
			            { title: "Output"},
			            { 
			            	title: 'Action <a href="getTarget?id=0"><img src="/ndcmp/static/images/add.png" alt="Add" title="Add Indicator"></a>', 
			            	sorting: false,
			            }
			           
			        ]
		});
	}

	/**
	 * Load all the output details
	 */

	function loadAllOutputs(){
		$('#outputList').DataTable({
			"iDisplayLength": 10,
			 "processing": true,
			 
			columns: [
			          	{ title: "#"},
			            { title: "Key Activity" },
			            { title: "Sub Actitity"},
			            { title: "Responsible Entity"},
			            { title: "Partner Agency"},
			            { title: "MOV"},
			            { 
			            	title: 'Action', 
			            	sorting: false,
			            }
			           
			        ]
		});
	}