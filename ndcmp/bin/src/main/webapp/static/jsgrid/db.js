(function() {

//	Start Strategic Piller 1 - Output 1
	var db = {
		loadData : function(filter) {
			return $.grep(this.clients, function(client) {
				return (!filter.SNo || client.SNo.indexOf(filter.SNo) > -1) 
						&& (!filter.KeyActivities || client.KeyActivities.indexOf(filter.KeyActivities) > -1) 
						&& (!filter.SubActivities || client.SubActivities.indexOf(filter.SubActivities) > -1) 
						&& (!filter.ResponsibleEntity || client.ResponsibleEntity === filter.ResponsibleEntity) 
						&& (!filter.PartneringAgency || client.PartneringAgency === filter.PartneringAgency)
						&& (!filter.MoV || client.MoV.indexOf(filter.MoV) > -1) ;
			});
		},

		insertItem : function(insertingClient) {
			this.clients.push(insertingClient);
		},

		updateItem : function(updatingClient) {
		},

		deleteItem : function(deletingClient) {
			var clientIndex = $.inArray(deletingClient, this.clients);
			this.clients.splice(clientIndex, 1);
		}

	};
	window.db = db;
// End Strategic Piller 1 - Output 1
	
//	Strategic Piller 1 - Start Output 2
	var db2 = {
			loadData : function(filter) {
				return $.grep(this.output2, function(client) {
					return (!filter.SNo || client.SNo.indexOf(filter.SNo) > -1) 
							&& (!filter.KeyActivities || client.KeyActivities.indexOf(filter.KeyActivities) > -1) 
							&& (!filter.SubActivities || client.SubActivities.indexOf(filter.SubActivities) > -1) 
							&& (!filter.ResponsibleEntity || client.ResponsibleEntity === filter.ResponsibleEntity) 
							&& (!filter.PartneringAgency || client.PartneringAgency === filter.PartneringAgency)
							&& (!filter.MoV || client.MoV.indexOf(filter.MoV) > -1) ;
				});
			},

			insertItem : function(insertingClient) {
				this.output2.push(insertingClient);
			},

			updateItem : function(updatingClient) {
			},

			deleteItem : function(deletingClient) {
				var clientIndex = $.inArray(deletingClient, this.output2);
				this.output2.splice(clientIndex, 1);
			}

		};

	window.db2 = db2;
//	Strategic Piller 1 - End Output 2
	
//	Start Strategic Piller 2 - Output 1
	var db3 = {
			loadData : function(filter) {
				return $.grep(this.output3, function(client) {
					return (!filter.SNo || client.SNo.indexOf(filter.SNo) > -1) 
							&& (!filter.KeyActivities || client.KeyActivities.indexOf(filter.KeyActivities) > -1) 
							&& (!filter.SubActivities || client.SubActivities.indexOf(filter.SubActivities) > -1) 
							&& (!filter.ResponsibleEntity || client.ResponsibleEntity === filter.ResponsibleEntity) 
							&& (!filter.PartneringAgency || client.PartneringAgency === filter.PartneringAgency)
							&& (!filter.MoV || client.MoV.indexOf(filter.MoV) > -1) ;
				});
			},

			insertItem : function(insertingClient) {
				this.output3.push(insertingClient);
			},

			updateItem : function(updatingClient) {
			},

			deleteItem : function(deletingClient) {
				var clientIndex = $.inArray(deletingClient, this.output3);
				this.output3.splice(clientIndex, 1);
			}

		};

	window.db3 = db3;
// End Strategic Piller 2 - Output 1
	
//	Start Strategic Piller 2 - Output 2
	var db4 = {
			loadData : function(filter) {
				return $.grep(this.output4, function(client) {
					return (!filter.SNo || client.SNo.indexOf(filter.SNo) > -1) 
							&& (!filter.KeyActivities || client.KeyActivities.indexOf(filter.KeyActivities) > -1) 
							&& (!filter.SubActivities || client.SubActivities.indexOf(filter.SubActivities) > -1) 
							&& (!filter.ResponsibleEntity || client.ResponsibleEntity === filter.ResponsibleEntity) 
							&& (!filter.PartneringAgency || client.PartneringAgency === filter.PartneringAgency)
							&& (!filter.MoV || client.MoV.indexOf(filter.MoV) > -1) ;
				});
			},

			insertItem : function(insertingClient) {
				this.output4.push(insertingClient);
			},

			updateItem : function(updatingClient) {
			},

			deleteItem : function(deletingClient) {
				var clientIndex = $.inArray(deletingClient, this.output4);
				this.output4.splice(clientIndex, 1);
			}

		};

	window.db4 = db4;
// End Strategic Piller 2 - Output 2
	
//	Start Strategic Piller 3 - Output 1
	var db5 = {
			loadData : function(filter) {
				return $.grep(this.output5, function(client) {
					return (!filter.SNo || client.SNo.indexOf(filter.SNo) > -1) 
							&& (!filter.KeyActivities || client.KeyActivities.indexOf(filter.KeyActivities) > -1) 
							&& (!filter.SubActivities || client.SubActivities.indexOf(filter.SubActivities) > -1) 
							&& (!filter.ResponsibleEntity || client.ResponsibleEntity === filter.ResponsibleEntity) 
							&& (!filter.PartneringAgency || client.PartneringAgency === filter.PartneringAgency)
							&& (!filter.MoV || client.MoV.indexOf(filter.MoV) > -1) ;
				});
			},

			insertItem : function(insertingClient) {
				this.output5.push(insertingClient);
			},

			updateItem : function(updatingClient) {
			},

			deleteItem : function(deletingClient) {
				var clientIndex = $.inArray(deletingClient, this.output5);
				this.output5.splice(clientIndex, 1);
			}
		};

	window.db5 = db5;
// End Strategic Piller 3 - Output 1
	
//	Start Strategic Piller 3 - Output 2
	var db6 = {
			loadData : function(filter) {
				return $.grep(this.output6, function(client) {
					return (!filter.SNo || client.SNo.indexOf(filter.SNo) > -1) 
							&& (!filter.KeyActivities || client.KeyActivities.indexOf(filter.KeyActivities) > -1) 
							&& (!filter.SubActivities || client.SubActivities.indexOf(filter.SubActivities) > -1) 
							&& (!filter.ResponsibleEntity || client.ResponsibleEntity === filter.ResponsibleEntity) 
							&& (!filter.PartneringAgency || client.PartneringAgency === filter.PartneringAgency)
							&& (!filter.MoV || client.MoV.indexOf(filter.MoV) > -1) ;
				});
			},

			insertItem : function(insertingClient) {
				this.output6.push(insertingClient);
			},

			updateItem : function(updatingClient) {
			},

			deleteItem : function(deletingClient) {
				var clientIndex = $.inArray(deletingClient, this.output6);
				this.output6.splice(clientIndex, 1);
			}
		};

	window.db6 = db6;
// End Strategic Piller 3 - Output 2
	
//	Start Strategic Piller 4 - Output 1
	var db7 = {
			loadData : function(filter) {
				return $.grep(this.output7, function(client) {
					return (!filter.SNo || client.SNo.indexOf(filter.SNo) > -1) 
							&& (!filter.KeyActivities || client.KeyActivities.indexOf(filter.KeyActivities) > -1) 
							&& (!filter.SubActivities || client.SubActivities.indexOf(filter.SubActivities) > -1) 
							&& (!filter.ResponsibleEntity || client.ResponsibleEntity === filter.ResponsibleEntity) 
							&& (!filter.PartneringAgency || client.PartneringAgency === filter.PartneringAgency)
							&& (!filter.MoV || client.MoV.indexOf(filter.MoV) > -1) ;
				});
			},

			insertItem : function(insertingClient) {
				this.output7.push(insertingClient);
			},

			updateItem : function(updatingClient) {
			},

			deleteItem : function(deletingClient) {
				var clientIndex = $.inArray(deletingClient, this.output7);
				this.output7.splice(clientIndex, 1);
			}
		};

	window.db7 = db7;
// End Strategic Piller 4 - Output 1
	
//	Start Strategic Piller 4 - Output 2
	var db8 = {
			loadData : function(filter) {
				return $.grep(this.output8, function(client) {
					return (!filter.SNo || client.SNo.indexOf(filter.SNo) > -1) 
							&& (!filter.KeyActivities || client.KeyActivities.indexOf(filter.KeyActivities) > -1) 
							&& (!filter.SubActivities || client.SubActivities.indexOf(filter.SubActivities) > -1) 
							&& (!filter.ResponsibleEntity || client.ResponsibleEntity === filter.ResponsibleEntity) 
							&& (!filter.PartneringAgency || client.PartneringAgency === filter.PartneringAgency)
							&& (!filter.MoV || client.MoV.indexOf(filter.MoV) > -1) ;
				});
			},

			insertItem : function(insertingClient) {
				this.output8.push(insertingClient);
			},

			updateItem : function(updatingClient) {
			},

			deleteItem : function(deletingClient) {
				var clientIndex = $.inArray(deletingClient, this.output8);
				this.output8.splice(clientIndex, 1);
			}
		};

	window.db8 = db8;
// End Strategic Piller 4 - Output 2

	db.countries = [ {
		Name : "",
		Id : 0
	}, {
		Name : "United States",
		Id : 1
	}, {
		Name : "Canada",
		Id : 2
	}, {
		Name : "United Kingdom",
		Id : 3
	}, {
		Name : "France",
		Id : 4
	}, {
		Name : "Brazil",
		Id : 5
	}, {
		Name : "China",
		Id : 6
	}, {
		Name : "Russia",
		Id : 7
	} ];

	db.agency = [ {
		Name : "",
		Id : 0
	}, {
		Name : "NDLEA",
		Id : 1
	}, {
		Name : "MOJ",
		Id : 2
	}, {
		Name : "NJC",
		Id : 3
	}, {
		Name : "NBA",
		Id : 4
	}, {
		Name : "NJI",
		Id : 5
	} ];

	db.clients = [
			{
				"SNo" : "1.1.1",
				"KeyActivities" : "Review gaps in current legislation against international drug and crime conventions.",
				"SubActivities" : "",
				"ResponsibleEntity" : 0,
				"PartneringAgency" : 0,
				"MoV" : "",
			},
			{
				"SNo" : "1.1.1.a",
				"KeyActivities" : "",
				"SubActivities" : "Set up a Review Committee",
				"ResponsibleEntity" : 1,
				"PartneringAgency" : 2,
				"MoV" : "Minutes of Meeting",
			},
			{
				"SNo" : "1.1.1.b",
				"KeyActivities" : "",
				"SubActivities" : "TOR for Review Committee",
				"ResponsibleEntity" : 1,
				"PartneringAgency" : 2,
				"MoV" : "TOR",
			},
			{
				"SNo" : "1.1.1.c",
				"KeyActivities" : "",
				"SubActivities" : "Carry out the Review of current legislation against international drug and crime conventions",
				"ResponsibleEntity" : 1,
				"PartneringAgency" : 2,
				"MoV" : "Review Report",
			},
			{
				"SNo" : "1.1.2",
				"KeyActivities" : "Recommend proposed amendments to current drug and crime legislation.",
				"SubActivities" : "",
				"ResponsibleEntity" : 1,
				"PartneringAgency" : 2,
				"MoV" : "Minutes of Meeting",
			}, {
				"SNo" : "1.1.2.a",
				"KeyActivities" : "",
				"SubActivities" : "Prepare Report of the Recommendation",
				"ResponsibleEntity" : 1,
				"PartneringAgency" : 2,
				"MoV" : "Report Prepared",
			}, {
				"SNo" : "1.1.2.b",
				"KeyActivities" : "",
				"SubActivities" : "Submit the report to MOJ",
				"ResponsibleEntity" : 1,
				"PartneringAgency" : 2,
				"MoV" : "Report Submitted",
			}, {
				"SNo" : "1.1.3",
				"KeyActivities" : "Advocacy to relevant MDAs and National Assembly to support enactment of laws.",
				"SubActivities" : "",
				"ResponsibleEntity" : 0,
				"PartneringAgency" : 0,
				"MoV" : "",
			}, {
				"SNo" : "1.1.3.a",
				"KeyActivities" : "",
				"SubActivities" : "Form the Team for High Level Advocacy",
				"ResponsibleEntity" : 1,
				"PartneringAgency" : 2,
				"MoV" : "Minutes of Meeting",
			}, {
				"SNo" : "1.1.3.b",
				"KeyActivities" : "",
				"SubActivities" : "High Level Advocacy with MDAs and National Assembly",
				"ResponsibleEntity" : 1,
				"PartneringAgency" : 2,
				"MoV" : "Minutes of Meeting",
			}
	
	];
	
//	sTART oUTPUT 2
	db2.output2 = [
	  			{
	  				"SNo" : "1.2.1",
	  				"KeyActivities" : "Form working group to develop sentencing guidelines for drug offences.",
	  				"SubActivities" : "",
	  				"ResponsibleEntity" : 0,
	  				"PartneringAgency" : 0,
	  				"MoV" : "",
	  			},
	  			{
	  				"SNo" : "1.2.1.a",
	  				"KeyActivities" : "",
	  				"SubActivities" : "Terms of Reference for the Working Group",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "TOR",
	  			},
	  			{
	  				"SNo" : "1.2.1.b",
	  				"KeyActivities" : "",
	  				"SubActivities" : "Form working group to develop sentencing guidelines for drug offences",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "Minutes of Meeting",
	  			},
	  			{
	  				"SNo" : "1.2.2",
	  				"KeyActivities" : "Draft sentencing guidelines for drug offences.",
	  				"SubActivities" : "",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "",
	  			}, {
	  				"SNo" : "1.2.2.a",
	  				"KeyActivities" : "",
	  				"SubActivities" : "Consultation Workshop",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "Report of the Workshop",
	  			}, {
	  				"SNo" : "1.2.2.b",
	  				"KeyActivities" : "",
	  				"SubActivities" : "Draft Sentencing Guidelines",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "Draft Guidelines",
	  			}, {
	  				"SNo" : "1.2.3",
	  				"KeyActivities" : "Advocacy to relevant MDAs and judiciary to support sentencing guidelines for drug offences.",
	  				"SubActivities" : "",
	  				"ResponsibleEntity" : 0,
	  				"PartneringAgency" : 0,
	  				"MoV" : "",
	  			}, {
	  				"SNo" : "1.2.3.a",
	  				"KeyActivities" : "",
	  				"SubActivities" : "Form the Team for High Level Advocacy",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "Minutes of Meeting",
	  			}, {
	  				"SNo" : "1.2.3.b",
	  				"KeyActivities" : "",
	  				"SubActivities" : "High Level Advocacy with MDAs, Judiciary and Other Stakeholders (CSOs, Bar Association, Human Rights Activists)",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "Reports of Advocacy",
	  			}
	  	
	  	];
//  eND oUTPUT 2
	
	
//	Start Strategic Piller 2 - Output 1
	db3.output3 = [
	  			{
	  				"SNo" : "7.1.1",
	  				"KeyActivities" : "Review existing guidelines and strategies for drug sensitization and prevention.",
	  				"SubActivities" : "",
	  				"ResponsibleEntity" : 0,
	  				"PartneringAgency" : 0,
	  				"MoV" : "",
	  			},
	  			{
	  				"SNo" : "7.1.1.a",
	  				"KeyActivities" : "",
	  				"SubActivities" : "Identify/map existing guidelines ",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "Report on Mapping",
	  			},
	  			{
	  				"SNo" : "7.1.1.b",
	  				"KeyActivities" : "",
	  				"SubActivities" : "Engage Resource Person to review guidelines and strategies and conduct appraisal of current practices",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "TOR and contract signed",
	  			},
	  			{
	  				"SNo" : "7.1.2",
	  				"KeyActivities" : "Consult on best practices for guidelines and manuals for drug sensitization and prevention.",
	  				"SubActivities" : "",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "",
	  			}, {
	  				"SNo" : "7.1.2.a",
	  				"KeyActivities" : "",
	  				"SubActivities" : "Circulate report recommendations to all agencies",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "Feedbcak Received from Agencies",
	  			}, {
	  				"SNo" : "7.1.2.b",
	  				"KeyActivities" : "",
	  				"SubActivities" : "TWG to provide feedback",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "Feedback Report",
	  			}, {
	  				"SNo" : "7.1.3",
	  				"KeyActivities" : "Develop, produce and disseminate guidelines and manuals for drug sensitization and prevention.",
	  				"SubActivities" : "",
	  				"ResponsibleEntity" : 0,
	  				"PartneringAgency" : 0,
	  				"MoV" : "",
	  			}, {
	  				"SNo" : "7.1.3.a",
	  				"KeyActivities" : "",
	  				"SubActivities" : "Establish a technical working group on sensitization and prevention to guide development of guidelines",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "Minutes of Meeting",
	  			}, {
	  				"SNo" : "7.1.3.b",
	  				"KeyActivities" : "",
	  				"SubActivities" : "Engage Resource Person to work with TWG to develop guidelines and print",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "Guideline",
	  			}, {
	  				"SNo" : "7.1.3.c",
	  				"KeyActivities" : "",
	  				"SubActivities" : "National Dissemination workshop for dissemination and to extract commitments from partners",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "Report on Consultation",
	  			}, {
	  				"SNo" : "7.1.4",
	  				"KeyActivities" : "Periodic review to assess user-friendliness and functionality of the different guidelines and manuals for drug sensitization and prevention.",
	  				"SubActivities" : "",
	  				"ResponsibleEntity" : 0,
	  				"PartneringAgency" : 0,
	  				"MoV" : "",
	  			}, {
	  				"SNo" : "7.1.4.a",
	  				"KeyActivities" : "Technical Working Group to develop instrument and gather information for self-assessment.",
	  				"SubActivities" : "",
	  				"ResponsibleEntity" : 0,
	  				"PartneringAgency" : 0,
	  				"MoV" : "Instrument",
	  			}, {
	  				"SNo" : "7.1.4.b",
	  				"KeyActivities" : "Technical Working Group to review findings and update guidelines .",
	  				"SubActivities" : "",
	  				"ResponsibleEntity" : 0,
	  				"PartneringAgency" : 0,
	  				"MoV" : "Revised guideline",
	  			}
	  	
	  	];
//  End Strategic Piller 2 - Output 1
	
//	Start Strategic Piller 2 - Output 2
	db4.output4 = [
	  			{
	  				"SNo" : "7.2.1",
	  				"KeyActivities" : "Develop training curriculum and calendar for drug prevention and sensitization programmes.",
	  				"SubActivities" : "",
	  				"ResponsibleEntity" : 0,
	  				"PartneringAgency" : 0,
	  				"MoV" : "",
	  			},
	  			{
	  				"SNo" : "7.2.1.a",
	  				"KeyActivities" : "",
	  				"SubActivities" : "Identify training needs (what and who) and resource persons (trainers) ",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "Training needs assessment report",
	  			},
	  			{
	  				"SNo" : "7.2.1.b",
	  				"KeyActivities" : "",
	  				"SubActivities" : "Engage Resource Person to develop training curriculum and calendar (sensitisation and prevention)",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "Training curriculum, materials and calendar",
	  			},
	  			{
	  				"SNo" : "7.2.2",
	  				"KeyActivities" : "Conduct training on drug prevention and sensitization programmes.",
	  				"SubActivities" : "",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "",
	  			}, {
	  				"SNo" : "7.2.2.a",
	  				"KeyActivities" : "",
	  				"SubActivities" : "Identify service providers to be trained",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : " List of service providers identified",
	  			}, {
	  				"SNo" : "7.2.2.b",
	  				"KeyActivities" : "",
	  				"SubActivities" : "Train National Master Trainers on curriculum and guidelines",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "Training report",
	  			}, {
	  				"SNo" : "7.2.2.c",
	  				"KeyActivities" : "",
	  				"SubActivities" : "Implement zonal level training programmes",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "Training report",
	  			}, {
	  				"SNo" : "7.2.3",
	  				"KeyActivities" : "Evaluate training on drug prevention and sensitization programmes.",
	  				"SubActivities" : "",
	  				"ResponsibleEntity" : 0,
	  				"PartneringAgency" : 0,
	  				"MoV" : "",
	  			}, {
	  				"SNo" : "7.2.3.a",
	  				"KeyActivities" : "",
	  				"SubActivities" : "Develop pre-post training instrument to be implemented by trainers ",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "Minutes of Meeting",
	  			}, {
	  				"SNo" : "7.2.3.b",
	  				"KeyActivities" : "",
	  				"SubActivities" : "Pre-post training instrument",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "Guideline",
	  			}, {
	  				"SNo" : "7.2.3.c",
	  				"KeyActivities" : "",
	  				"SubActivities" : "Periodic training reports by each agency / CSO to be submitted to responsible entity ",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "Training Report",
	  			}
	  	];
//  End Strategic Piller 2 - Output 2
	
//	Start Strategic Piller 3 - Output 1
	db5.output5 = [
	  			{
	  				"SNo" : "16.1.1",
	  				"KeyActivities" : "Engage consultant/s to develop national guidelines for estimation of psychotropic substances and precursors.",
	  				"SubActivities" : "",
	  				"ResponsibleEntity" : 0,
	  				"PartneringAgency" : 0,
	  				"MoV" : "",
	  			},
	  			{
	  				"SNo" : "16.1.1.a",
	  				"KeyActivities" : "",
	  				"SubActivities" : "Develop ToR for the Resource Person",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "TOR",
	  			},
	  			{
	  				"SNo" : "16.1.1.b",
	  				"KeyActivities" : "",
	  				"SubActivities" : "Engage Resource Person to Support TWG to develop the draft Guidelines",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "Contract",
	  			},
	  			{
	  				"SNo" : "'16.1.2.",
	  				"KeyActivities" : "Develop, review and finalise national estimation guidelines for psychotropic substances and precursors.",
	  				"SubActivities" : "",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "",
	  			}, {
	  				"SNo" : "16.1.2.a",
	  				"KeyActivities" : "",
	  				"SubActivities" : "Circulate the draft guideline to the relevant stakeholder for comments and feedback",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : " List of service providers identified",
	  			}, {
	  				"SNo" : "16.1.2.b",
	  				"KeyActivities" : "",
	  				"SubActivities" : "TWG meeting to adopt and finalize the guideline",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "Training report",
	  			}, {
	  				"SNo" : "'16.1.3.",
	  				"KeyActivities" : " Launch of the national estimation guidelines for psychotropic substances and precursors by the key stakeholders.",
	  				"SubActivities" : "",
	  				"ResponsibleEntity" : 0,
	  				"PartneringAgency" : 0,
	  				"MoV" : "",
	  			}, {
	  				"SNo" : "16.1.3.a",
	  				"KeyActivities" : "",
	  				"SubActivities" : "Print the Guideline",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "Printed Guidelines",
	  			}, {
	  				"SNo" : "16.1.3.b",
	  				"KeyActivities" : "",
	  				"SubActivities" : "Convene a launching event to disseminate the guideline",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "Guideline",
	  			}
	  	];
//  End Strategic Piller 3 - Output 1
	
//	Start Strategic Piller 3 - Output 2
	db6.output6 = [
	  			{
	  				"SNo" : "16.2.1",
	  				"KeyActivities" : "Engage consultant/s to facilitate the development of data collection forms and field testing of national estimation guidelines for psychotropic substances and precursors.",
	  				"SubActivities" : "",
	  				"ResponsibleEntity" : 0,
	  				"PartneringAgency" : 0,
	  				"MoV" : "",
	  			},
	  			{
	  				"SNo" : "16.2.1.a",
	  				"KeyActivities" : "",
	  				"SubActivities" : "Engage Resource Person to develop data collection forms",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "Contract",
	  			},
	  			{
	  				"SNo" : "16.2.1.b",
	  				"KeyActivities" : "",
	  				"SubActivities" : "Circulate the draft forms to key stakeholder for inputs",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "Draft Forms",
	  			},
	  			{
	  				"SNo" : "16.2.1.c",
	  				"KeyActivities" : "",
	  				"SubActivities" : "Conduct field testing in selected facilities and finalize the data forms",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "Finalized data forms",
	  			},
	  			{
	  				"SNo" : "'16.2.2.",
	  				"KeyActivities" : "Design the survey to collect data estimation of needs for psychotropic substances and precursors.",
	  				"SubActivities" : "",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "",
	  			}, {
	  				"SNo" : "16.2.2.a",
	  				"KeyActivities" : "",
	  				"SubActivities" : "Develop ToR for the resource person",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : " TOR",
	  			}, {
	  				"SNo" : "16.2.2.b",
	  				"KeyActivities" : "",
	  				"SubActivities" : "Engage resource person for the design of the survey",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "Training report",
	  			}, {
	  				"SNo" : "16.2.2.c",
	  				"KeyActivities" : "",
	  				"SubActivities" : "Technical Clearance of the Survey design by the Planning Research and Statistics Department of NAFDAC",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "Letter of Technical Clearance",
	  			}, {
	  				"SNo" : "'16.2.3.",
	  				"KeyActivities" : "Conduct pilot study to collect data for estimation of needs for psychotropic substances and precursors.",
	  				"SubActivities" : "",
	  				"ResponsibleEntity" : 0,
	  				"PartneringAgency" : 0,
	  				"MoV" : "",
	  			}, {
	  				"SNo" : "16.2.3.a",
	  				"KeyActivities" : "",
	  				"SubActivities" : "Conduct Pilot Study to improve the design",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "Study Report",
	  			}, {
	  				"SNo" : "'16.2.4.",
	  				"KeyActivities" : "Conduct of survey to collect data for estimation of needs for psychotropic substances and precursors.",
	  				"SubActivities" : "",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "",
	  			}, {
	  				"SNo" : "16.2.4.a",
	  				"KeyActivities" : "",
	  				"SubActivities" : "To Conduct TOT on Survey methods and data collection",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "Training Report",
	  			}, {
	  				"SNo" : "16.2.4.b",
	  				"KeyActivities" : "",
	  				"SubActivities" : "Training of Data Collectors by the TOT",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "Training Report",
	  			}, {
	  				"SNo" : "16.2.4.c",
	  				"KeyActivities" : "",
	  				"SubActivities" : "Collection, analysis and report  of estimation of needs for psychotropic substances and precursors.",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "Study Report",
	  			}, {
	  				"SNo" : "'16.2.5.",
	  				"KeyActivities" : "Presentation of survey findings of needs for psychotropic substances and precursors.",
	  				"SubActivities" : "",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "",
	  			}, {
	  				"SNo" : "16.2.5.a",
	  				"KeyActivities" : "",
	  				"SubActivities" : "Convene stakeholder meeting for presentation of survey findings",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "Meeting Report",
	  			}, {
	  				"SNo" : "'16.2.6.",
	  				"KeyActivities" : "Periodic review of established baseline data for estimation of psychotropic substances and precursors.",
	  				"SubActivities" : "",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "",
	  			}, {
	  				"SNo" : "16.2.6.a",
	  				"KeyActivities" : "",
	  				"SubActivities" : "Conduct Survey every two years to review the established baseline data",
	  				"ResponsibleEntity" : 1,
	  				"PartneringAgency" : 2,
	  				"MoV" : "Survey Report",
	  			}
	  	];
//  End Strategic Piller 3 - Output 2
	
// Start Strategic Piller 4 - Output 1
	db7.output7 = [
		  			{
		  				"SNo" : "21.1.1",
		  				"KeyActivities" : "Develop Terms of Reference of SDCC.",
		  				"SubActivities" : "",
		  				"ResponsibleEntity" : 0,
		  				"PartneringAgency" : 0,
		  				"MoV" : "",
		  			},
		  			{
		  				"SNo" : "21.1.1.a",
		  				"KeyActivities" : "",
		  				"SubActivities" : "Review and update the ToR",
		  				"ResponsibleEntity" : 1,
		  				"PartneringAgency" : 2,
		  				"MoV" : "Draft TOR",
		  			},
		  			{
		  				"SNo" : "21.1.1.b",
		  				"KeyActivities" : "",
		  				"SubActivities" : "Circulate/Sensitize (workshop) the Draft ToR to the executives of SDCC for review and inputs",
		  				"ResponsibleEntity" : 1,
		  				"PartneringAgency" : 2,
		  				"MoV" : "Workshop Report",
		  			},
		  			{
		  				"SNo" : "21.1.1.c",
		  				"KeyActivities" : "",
		  				"SubActivities" : "Finalization and endorsement of the ToR by the SDCC",
		  				"ResponsibleEntity" : 1,
		  				"PartneringAgency" : 2,
		  				"MoV" : "Final ToR",
		  			},
		  			{
		  				"SNo" : "21.1.2",
		  				"KeyActivities" : "Establish SDCC in each state.",
		  				"SubActivities" : "",
		  				"ResponsibleEntity" : 1,
		  				"PartneringAgency" : 2,
		  				"MoV" : "",
		  			}, {
		  				"SNo" : "21.1.2.a",
		  				"KeyActivities" : "",
		  				"SubActivities" : "NCU to facilitate the administrative process of establishing the SDCC in each state",
		  				"ResponsibleEntity" : 1,
		  				"PartneringAgency" : 2,
		  				"MoV" : " Correspondence",
		  			}, {
		  				"SNo" : "21.1.3.",
		  				"KeyActivities" : "Through state commanders support holding of SDCC meetings quarterly in each state.",
		  				"SubActivities" : "",
		  				"ResponsibleEntity" : 0,
		  				"PartneringAgency" : 0,
		  				"MoV" : "",
		  			}, {
		  				"SNo" : "21.1.3.a",
		  				"KeyActivities" : "",
		  				"SubActivities" : "Inauguration of the first SDCC meeting",
		  				"ResponsibleEntity" : 1,
		  				"PartneringAgency" : 2,
		  				"MoV" : "First meeting minutes",
		  			}, {
		  				"SNo" : "21.1.3.b",
		  				"KeyActivities" : "",
		  				"SubActivities" : "Establishing State Drug Control Committee Secretariat within the NDLEA Commander's Office",
		  				"ResponsibleEntity" : 1,
		  				"PartneringAgency" : 2,
		  				"MoV" : "Secretariat Report",
		  			}, {
		  				"SNo" : "21.1.3.c",
		  				"KeyActivities" : "",
		  				"SubActivities" : "Holding regular quarterly SDCC meetings and disseminating the minutes and the annual work plan",
		  				"ResponsibleEntity" : 1,
		  				"PartneringAgency" : 2,
		  				"MoV" : "Meeting Minutes",
		  			}, {
		  				"SNo" : "21.1.4",
		  				"KeyActivities" : "Review and Revise Terms of Reference of IMC.",
		  				"SubActivities" : "",
		  				"ResponsibleEntity" : 1,
		  				"PartneringAgency" : 2,
		  				"MoV" : "",
		  			}, {
		  				"SNo" : "21.1.4.a",
		  				"KeyActivities" : "",
		  				"SubActivities" : "Review and update the ToR",
		  				"ResponsibleEntity" : 1,
		  				"PartneringAgency" : 2,
		  				"MoV" : "Draft TOR",
		  			}, {
		  				"SNo" : "21.1.4.b",
		  				"KeyActivities" : "",
		  				"SubActivities" : "Finalization and endorsement of the ToR by the IMC",
		  				"ResponsibleEntity" : 1,
		  				"PartneringAgency" : 2,
		  				"MoV" : "Final ToR",
		  			}, {
		  				"SNo" : "21.1.5.",
		  				"KeyActivities" : "Convene and facilitate two meetings of the IMC per year",
		  				"SubActivities" : "",
		  				"ResponsibleEntity" : 1,
		  				"PartneringAgency" : 2,
		  				"MoV" : "",
		  			}, {
		  				"SNo" : "21.1.5.a",
		  				"KeyActivities" : "",
		  				"SubActivities" : "NCU to facilitate and coordinate the meetings of the IMC",
		  				"ResponsibleEntity" : 1,
		  				"PartneringAgency" : 2,
		  				"MoV" : "Invitation and Agenda",
		  			}, {
		  				"SNo" : "21.1.5.b",
		  				"KeyActivities" : "",
		  				"SubActivities" : "Support and conduct the meetings",
		  				"ResponsibleEntity" : 1,
		  				"PartneringAgency" : 2,
		  				"MoV" : "Minutes of the Meeting",
		  			}, {
		  				"SNo" : "21.1.5.c",
		  				"KeyActivities" : "",
		  				"SubActivities" : "Finalize and circulate the minutes",
		  				"ResponsibleEntity" : 1,
		  				"PartneringAgency" : 2,
		  				"MoV" : "Minutes of the Meeting",
		  			}
		  	];
// End Strategic Piller 4 - Output 1

	// Start Strategic Piller 4 - Output 2
	db8.output8 = [
		  			{
		  				"SNo" : "21.2.1",
		  				"KeyActivities" : "Develop monitoring and evaluation plan for implementation of NDCMP 2015-2019",
		  				"SubActivities" : "",
		  				"ResponsibleEntity" : 0,
		  				"PartneringAgency" : 0,
		  				"MoV" : "",
		  			},
		  			{
		  				"SNo" : "21.2.1.a",
		  				"KeyActivities" : "",
		  				"SubActivities" : "Develop TOR and Hire Resource Persons",
		  				"ResponsibleEntity" : 1,
		  				"PartneringAgency" : 2,
		  				"MoV" : "TOR",
		  			},
		  			{
		  				"SNo" : "21.2.1.b",
		  				"KeyActivities" : "",
		  				"SubActivities" : "Review and  approve by the IMC",
		  				"ResponsibleEntity" : 1,
		  				"PartneringAgency" : 2,
		  				"MoV" : "Final M&E Plan",
		  			},
		  			{
		  				"SNo" : "21.2.1.c",
		  				"KeyActivities" : "",
		  				"SubActivities" : "Develop a draft M&E Plan",
		  				"ResponsibleEntity" : 1,
		  				"PartneringAgency" : 2,
		  				"MoV" : "Final M&E Plan",
		  			},
		  			{
		  				"SNo" : "21.2.2",
		  				"KeyActivities" : "Facilitate mid-term evaluation of NDCMP 2015-2019 by independent evaluator",
		  				"SubActivities" : "",
		  				"ResponsibleEntity" : 1,
		  				"PartneringAgency" : 2,
		  				"MoV" : "",
		  			}, {
		  				"SNo" : "21.2.2.a",
		  				"KeyActivities" : "",
		  				"SubActivities" : "Develop ToR for hiring National Independent Evaluator",
		  				"ResponsibleEntity" : 1,
		  				"PartneringAgency" : 2,
		  				"MoV" : "TOR",
		  			}, {
		  				"SNo" : "21.2.2.b",
		  				"KeyActivities" : "",
		  				"SubActivities" : "Administrative process for Evaluation ",
		  				"ResponsibleEntity" : 1,
		  				"PartneringAgency" : 2,
		  				"MoV" : " Correspondence",
		  			}, {
		  				"SNo" : "21.2.2.c",
		  				"KeyActivities" : "",
		  				"SubActivities" : "Implementation of the Evaluation and submission of the report to IMC and NCU ",
		  				"ResponsibleEntity" : 1,
		  				"PartneringAgency" : 2,
		  				"MoV" : " Evaluation Report",
		  			}, {
		  				"SNo" : "21.2.3.",
		  				"KeyActivities" : "Facilitate final evaluation of NDCMP 2015-2019 by independent evaluator.",
		  				"SubActivities" : "",
		  				"ResponsibleEntity" : 0,
		  				"PartneringAgency" : 0,
		  				"MoV" : "",
		  			}, {
		  				"SNo" : "21.2.3.a",
		  				"KeyActivities" : "",
		  				"SubActivities" : "Develop ToR for hiring National Independent Evaluator",
		  				"ResponsibleEntity" : 1,
		  				"PartneringAgency" : 2,
		  				"MoV" : "TOR",
		  			}, {
		  				"SNo" : "21.2.3.b",
		  				"KeyActivities" : "",
		  				"SubActivities" : "Administrative process for Evaluation ",
		  				"ResponsibleEntity" : 1,
		  				"PartneringAgency" : 2,
		  				"MoV" : "Correspondence",
		  			}, {
		  				"SNo" : "21.2.3.c",
		  				"KeyActivities" : "",
		  				"SubActivities" : "Implementation of the Evaluation and submission of the report to IMC and NCU ",
		  				"ResponsibleEntity" : 1,
		  				"PartneringAgency" : 2,
		  				"MoV" : "Evaluation Report",
		  			}
		  	];
// End Strategic Piller 4 - Output 2
	 

}());