function loginValidation() {

	//var username = $('#username').val();
	var username = $('#username').find('option:selected').val();
	var password = $('#password').val();

	if (username == null || username == "") {
		/* alert("Username should not be empty !"); */
		$('#usererror').html("Username should not be empty !");
		$('#username').focus();
	} else if (password == null || password == "") {
		/* alert("Password should not be empty !"); */
		$('#passworderror').html("Password should not be empty !");
		$('#password').focus();
	} else {
		if (username == "admin" && password == "admin") {
			window.location.href = 'pages/admin/dashboard.html';
		} else if (username == "projectadmin" && password == "projectadmin") {
			window.location.href = 'pages/prjectadmin/dashboard.html';
		} else if (username == "reviewer" && password == "reviewer") {
			window.location.href = 'pages/reviewer/dashboard.html';
		} else if (username == "planner" && password == "planner") {
			window.location.href = 'pages/planner/dashboard.html';
		} else if (username == "statusupdater" && password == "statusupdater") {
			window.location.href = 'pages/status/dashboard.html';
		} else {
			alert("Username and password are not valid !!");
			window.location.href = "index.html";
		}
	}
}

function setPassword(){
	var username = $('#username').find('option:selected').val();
	$('#password').val(username);
}

function advancedTable() {
	var table = $('#table-example')
			.dataTable(
					{
						'info' : false,
						'bSort' : true,
						'bPaginate' : true,
						'sPaginationType' : "full_numbers",
						'sDom' : 'lTfr<"clearfix">tip',
						"oTableTools" : {
							"aButtons" : [],
							"sSwfPath" : "https://datatables.net/release-datatables/extras/TableTools/media/swf/copy_csv_xls_pdf.swf"
						}
					/*
					 * 'sDom': 'lTfr<"clearfix">tip', 'oTableTools': {
					 * 'aButtons': [ { 'aButtons': [ 'csv', 'xls', 'pdf',
					 * 'copy', 'print' ], } ] }
					 */
					});

	var tt = new $.fn.dataTable.TableTools(table);
	$(tt.fnContainer()).insertBefore('div.dataTables_wrapper');

}

function loadProject() {
	// -----------------------------------------------------------------------------------------
	// Shows the year 2005 only
	Grids.OnUpdated = function(G) {
		G.FilterDateRange('I', '1/1/2005~12/31/2005', 'Year', 0);
	}

	// -----------------------------------------------------------------------------------------
	// Custom event for searching
	// Searches in orders with specified items
	Grids.OnRowSearch = function(G, row, col, found, F, type) {
		if (type || G.SearchDefs != "")
			return found; // Only for "in orders with items" and default call
		if (row.Def.Name == "Data")
			return -1; // Only for orders
		for ( var r = row.firstChild; r; r = r.nextSibling) {
			var found = F(r, col, 1); // calls F(r,col,type=1)
			if (!(found <= 0))
				return found;
		}
		return 0;
	}
	// -----------------------------------------------------------------------------------------
	// Creates popup right click menu to manipulate the row
	Grids.OnGetMenu = function(G, row, col) {
		var I = [], M = {
			Items : I
		};
		I[I.length] = {
			Name : "Del",
			Text : row.Deleted ? "Undelete row" : "Delete row"
		};
		I[I.length] = {
			Name : "Sel",
			Text : row.Selected ? "Deselect row" : "Select row"
		};
		I[I.length] = {
			Name : "Copy",
			Text : "Copy row"
		};
		if (row.firstChild)
			I[I.length] = {
				Name : "CopyTree",
				Text : "Copy row tree"
			};
		I[I.length] = {
			Name : "Add",
			Text : "Add new row"
		};
		if (row.firstChild)
			I[I.length] = {
				Name : "Expand",
				Text : row.Expanded ? "Collapse children" : "Expand children"
			};
		if (row.firstChild)
			I[I.length] = {
				Name : "Check",
				Text : Get(row, 'X') ? "Uncheck Used" : "Check Used"
			};
		return M;
	}
	// -----------------------------------------------------------------------------------------
	// Called after selecting item in the popup right click menu
	Grids.OnContextMenu = function(G, row, col, N) {
		switch (N) {
		case "Del":
			G.DeleteRow(row);
			break;
		case "Sel":
			G.SelectRow(row);
			break;
		case "Copy":
			G.CopyRow(row, null, row);
			break;
		case "CopyTree":
			G.CopyRows([ row ], null, row, 1);
			break;
		case "Add":
			G.AddRow(null, row, 1);
			break;
		case "Expand":
			if (row.Expanded)
				G.Collapse(row);
			else
				G.Expand(row);
			break;
		case "Check":
			G.SetValue(row, "X", !Get(row, 'X'), 1);
			break;
		}
	}
	// -----------------------------------------------------------------------------------------
}