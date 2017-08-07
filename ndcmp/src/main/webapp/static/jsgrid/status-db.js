(function() {

	var db = {
		loadData : function(filter) {
			return $
					.grep(this.clients,
							function(client) {
								return (!filter.Id || client.Id
										.indexOf(filter.Id) > -1)
										&& (!filter.Key || client.Key
												.indexOf(filter.Key) > -1)
										&& (!filter.Sub || client.Sub
												.indexOf(filter.Sub) > -1)
										&& (!filter.Status || client.Status
												.indexOf(filter.Status) > -1)
										&& (!filter.Progress || client.Progress
												.indexOf(filter.Progress) > -1)
										&& (!filter.Reason || client.Reason
												.indexOf(filter.Reason) > -1)
										&& (!filter.Plan || client.Plan
												.indexOf(filter.Plan) > -1);
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
	
// Start SP1 - Output 2
	var db2 = {
			loadData : function(filter) {
				return $
						.grep(this.output2,
								function(client) {
									return (!filter.Id || client.Id
											.indexOf(filter.Id) > -1)
											&& (!filter.Key || client.Key
													.indexOf(filter.Key) > -1)
											&& (!filter.Sub || client.Sub
													.indexOf(filter.Sub) > -1)
											&& (!filter.Status || client.Status
													.indexOf(filter.Status) > -1)
											&& (!filter.Progress || client.Progress
													.indexOf(filter.Progress) > -1)
											&& (!filter.Reason || client.Reason
													.indexOf(filter.Reason) > -1)
											&& (!filter.Plan || client.Plan
													.indexOf(filter.Plan) > -1);
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
// End SP1 - Output 2

// Start SP2 - Output 1
		var db3 = {
				loadData : function(filter) {
					return $
							.grep(this.output3,
									function(client) {
										return (!filter.Id || client.Id
												.indexOf(filter.Id) > -1)
												&& (!filter.Key || client.Key
														.indexOf(filter.Key) > -1)
												&& (!filter.Sub || client.Sub
														.indexOf(filter.Sub) > -1)
												&& (!filter.Status || client.Status
														.indexOf(filter.Status) > -1)
												&& (!filter.Progress || client.Progress
														.indexOf(filter.Progress) > -1)
												&& (!filter.Reason || client.Reason
														.indexOf(filter.Reason) > -1)
												&& (!filter.Plan || client.Plan
														.indexOf(filter.Plan) > -1);
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
	// End SP2 - Output 1
			
	// Start SP2 - Output 2
			var db4 = {
					loadData : function(filter) {
						return $
								.grep(this.output4,
										function(client) {
											return (!filter.Id || client.Id
													.indexOf(filter.Id) > -1)
													&& (!filter.Key || client.Key
															.indexOf(filter.Key) > -1)
													&& (!filter.Sub || client.Sub
															.indexOf(filter.Sub) > -1)
													&& (!filter.Status || client.Status
															.indexOf(filter.Status) > -1)
													&& (!filter.Progress || client.Progress
															.indexOf(filter.Progress) > -1)
													&& (!filter.Reason || client.Reason
															.indexOf(filter.Reason) > -1)
													&& (!filter.Plan || client.Plan
															.indexOf(filter.Plan) > -1);
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
		// End SP2 - Output 2
				
		// Start SP2 - Output 2
		var db5 = {
				loadData : function(filter) {
					return $
							.grep(this.output5,
									function(client) {
										return (!filter.Id || client.Id
												.indexOf(filter.Id) > -1)
												&& (!filter.Key || client.Key
														.indexOf(filter.Key) > -1)
												&& (!filter.Sub || client.Sub
														.indexOf(filter.Sub) > -1)
												&& (!filter.Status || client.Status
														.indexOf(filter.Status) > -1)
												&& (!filter.Progress || client.Progress
														.indexOf(filter.Progress) > -1)
												&& (!filter.Reason || client.Reason
														.indexOf(filter.Reason) > -1)
												&& (!filter.Plan || client.Plan
														.indexOf(filter.Plan) > -1);
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
	// End SP3 - Output 1
			
			// Start SP2 - Output 2
			var db6 = {
					loadData : function(filter) {
						return $
								.grep(this.output6,
										function(client) {
											return (!filter.Id || client.Id
													.indexOf(filter.Id) > -1)
													&& (!filter.Key || client.Key
															.indexOf(filter.Key) > -1)
													&& (!filter.Sub || client.Sub
															.indexOf(filter.Sub) > -1)
													&& (!filter.Status || client.Status
															.indexOf(filter.Status) > -1)
													&& (!filter.Progress || client.Progress
															.indexOf(filter.Progress) > -1)
													&& (!filter.Reason || client.Reason
															.indexOf(filter.Reason) > -1)
													&& (!filter.Plan || client.Plan
															.indexOf(filter.Plan) > -1);
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
		// End SP3 - Output 1
				
				// Start SP2 - Output 2
				var db7 = {
						loadData : function(filter) {
							return $
									.grep(this.output7,
											function(client) {
												return (!filter.Id || client.Id
														.indexOf(filter.Id) > -1)
														&& (!filter.Key || client.Key
																.indexOf(filter.Key) > -1)
														&& (!filter.Sub || client.Sub
																.indexOf(filter.Sub) > -1)
														&& (!filter.Status || client.Status
																.indexOf(filter.Status) > -1)
														&& (!filter.Progress || client.Progress
																.indexOf(filter.Progress) > -1)
														&& (!filter.Reason || client.Reason
																.indexOf(filter.Reason) > -1)
														&& (!filter.Plan || client.Plan
																.indexOf(filter.Plan) > -1);
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
			// End SP3 - Output 1

					// Start SP2 - Output 2
					var db8 = {
							loadData : function(filter) {
								return $
										.grep(this.output8,
												function(client) {
													return (!filter.Id || client.Id
															.indexOf(filter.Id) > -1)
															&& (!filter.Key || client.Key
																	.indexOf(filter.Key) > -1)
															&& (!filter.Sub || client.Sub
																	.indexOf(filter.Sub) > -1)
															&& (!filter.Status || client.Status
																	.indexOf(filter.Status) > -1)
															&& (!filter.Progress || client.Progress
																	.indexOf(filter.Progress) > -1)
															&& (!filter.Reason || client.Reason
																	.indexOf(filter.Reason) > -1)
															&& (!filter.Plan || client.Plan
																	.indexOf(filter.Plan) > -1);
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
				// End SP3 - Output 1

	db.clients = [
			{
				"Id" : "17.2.1",
				"Key" : "Identify the illegal narcotics and psychotropic distribution outlets",
				"Sub" : "",
				"Status" : "",
				"Progress" : "",
				"Reason" : "",
				"Plan" : "",
			},
			{
				"Id" : "17.2.1.a",
				"Key" : "",
				"Sub" : "Conduct Nation wide surveillance activities",
				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
				"Progress" : "",
				"Reason" : "The absence of an M&E plan before to guide data collation of activities carried out.",
				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
			},
			{
				"Id" : "17.2.1.b",
				"Key" : "",
				"Sub" : "Inspection of retail and distribution outlets to identify illegal outlets",
				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
				"Progress" : "",
				"Reason" : "The absence of an M&E plan before now to guide data collation of activities carried out.",
				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
				"Feed" : "",
			},
			{
				"Id" : "17.2.2",
				"Key" : "Take enforcement action against the identified illegal outlets",
				"Sub" : "",
				"Status" : "",
				"Progress" : "",
				"Reason" : "",
				"Plan" : "",
			},
			{
				"Id" : "17.2.2",
				"Key" : "Take enforcement action against the identified illegal outlets",
				"Sub" : "",
				"Status" : "",
				"Progress" : "",
				"Reason" : "",
				"Plan" : "",
			},
			{
				"Id" : "17.2.2.a",
				"Key" : "",
				"Sub" : "seal and bar the illegal outlets",
				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
				"Progress" : "",
				"Reason" : "The absence of an M&E plan before now to guide data collation of activities carried out.",
				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
			},
			{
				"Id" : "17.2.2.b",
				"Key" : "",
				"Sub" : "Seize and evacuate the drugs from the illegal outlets for destruction",
				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
				"Progress" : "",
				"Reason" : "The absence of an M&E plan before now to guide data collation of activities carried out.",
				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
			},
			{
				"Id" : "17.2.2.c",
				"Key" : "",
				"Sub" : "closure of illegal outlets",
				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
				"Progress" : "",
				"Reason" : "The absence of an M&E plan before now to guide data collation of activities carried out.",
				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
			}

	];
	
	db2.output2 = [
	  			{
	  				"Id" : "17.2.1",
	  				"Key" : "Identify the illegal narcotics and psychotropic distribution outlets",
	  				"Sub" : "",
	  				"Status" : "",
	  				"Progress" : "",
	  				"Reason" : "",
	  				"Plan" : "",
	  			},
	  			{
	  				"Id" : "17.2.1.a",
	  				"Key" : "",
	  				"Sub" : "Conduct Nation wide surveillance activities",
	  				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
	  				"Progress" : "",
	  				"Reason" : "The absence of an M&E plan before to guide data collation of activities carried out.",
	  				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
	  			},
	  			{
	  				"Id" : "17.2.1.b",
	  				"Key" : "",
	  				"Sub" : "Inspection of retail and distribution outlets to identify illegal outlets",
	  				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
	  				"Progress" : "",
	  				"Reason" : "The absence of an M&E plan before now to guide data collation of activities carried out.",
	  				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
	  				"Feed" : "",
	  			},
	  			{
	  				"Id" : "17.2.2",
	  				"Key" : "Take enforcement action against the identified illegal outlets",
	  				"Sub" : "",
	  				"Status" : "",
	  				"Progress" : "",
	  				"Reason" : "",
	  				"Plan" : "",
	  			},
	  			{
	  				"Id" : "17.2.2",
	  				"Key" : "Take enforcement action against the identified illegal outlets",
	  				"Sub" : "",
	  				"Status" : "",
	  				"Progress" : "",
	  				"Reason" : "",
	  				"Plan" : "",
	  			},
	  			{
	  				"Id" : "17.2.2.a",
	  				"Key" : "",
	  				"Sub" : "seal and bar the illegal outlets",
	  				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
	  				"Progress" : "",
	  				"Reason" : "The absence of an M&E plan before now to guide data collation of activities carried out.",
	  				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
	  			},
	  			{
	  				"Id" : "17.2.2.b",
	  				"Key" : "",
	  				"Sub" : "Seize and evacuate the drugs from the illegal outlets for destruction",
	  				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
	  				"Progress" : "",
	  				"Reason" : "The absence of an M&E plan before now to guide data collation of activities carried out.",
	  				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
	  			},
	  			{
	  				"Id" : "17.2.2.c",
	  				"Key" : "",
	  				"Sub" : "closure of illegal outlets",
	  				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
	  				"Progress" : "",
	  				"Reason" : "The absence of an M&E plan before now to guide data collation of activities carried out.",
	  				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
	  			}

	  	];
	
	db3.output3 = [
		  			{
		  				"Id" : "17.2.1",
		  				"Key" : "Identify the illegal narcotics and psychotropic distribution outlets",
		  				"Sub" : "",
		  				"Status" : "",
		  				"Progress" : "",
		  				"Reason" : "",
		  				"Plan" : "",
		  			},
		  			{
		  				"Id" : "17.2.1.a",
		  				"Key" : "",
		  				"Sub" : "Conduct Nation wide surveillance activities",
		  				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
		  				"Progress" : "",
		  				"Reason" : "The absence of an M&E plan before to guide data collation of activities carried out.",
		  				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
		  			},
		  			{
		  				"Id" : "17.2.1b",
		  				"Key" : "",
		  				"Sub" : "Inspection of retail and distribution outlets to identify illegal outlets",
		  				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
		  				"Progress" : "",
		  				"Reason" : "The absence of an M&E plan before now to guide data collation of activities carried out.",
		  				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
		  				"Feed" : "",
		  			},
		  			{
		  				"Id" : "17.2.2",
		  				"Key" : "Take enforcement action against the identified illegal outlets",
		  				"Sub" : "",
		  				"Status" : "",
		  				"Progress" : "",
		  				"Reason" : "",
		  				"Plan" : "",
		  			},
		  			{
		  				"Id" : "17.2.2",
		  				"Key" : "Take enforcement action against the identified illegal outlets",
		  				"Sub" : "",
		  				"Status" : "",
		  				"Progress" : "",
		  				"Reason" : "",
		  				"Plan" : "",
		  			},
		  			{
		  				"Id" : "17.2.2.a",
		  				"Key" : "",
		  				"Sub" : "seal and bar the illegal outlets",
		  				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
		  				"Progress" : "",
		  				"Reason" : "The absence of an M&E plan before now to guide data collation of activities carried out.",
		  				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
		  			},
		  			{
		  				"Id" : "17.2.2.b",
		  				"Key" : "",
		  				"Sub" : "Seize and evacuate the drugs from the illegal outlets for destruction",
		  				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
		  				"Progress" : "",
		  				"Reason" : "The absence of an M&E plan before now to guide data collation of activities carried out.",
		  				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
		  			},
		  			{
		  				"Id" : "17.2.2.c",
		  				"Key" : "",
		  				"Sub" : "closure of illegal outlets",
		  				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
		  				"Progress" : "",
		  				"Reason" : "The absence of an M&E plan before now to guide data collation of activities carried out.",
		  				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
		  			}

		  	];
	
	db4.output4 = [
		  			{
		  				"Id" : "17.2.1",
		  				"Key" : "Identify the illegal narcotics and psychotropic distribution outlets",
		  				"Sub" : "",
		  				"Status" : "",
		  				"Progress" : "",
		  				"Reason" : "",
		  				"Plan" : "",
		  			},
		  			{
		  				"Id" : "17.2.1.a",
		  				"Key" : "",
		  				"Sub" : "Conduct Nation wide surveillance activities",
		  				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
		  				"Progress" : "",
		  				"Reason" : "The absence of an M&E plan before to guide data collation of activities carried out.",
		  				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
		  			},
		  			{
		  				"Id" : "17.2.1.b",
		  				"Key" : "",
		  				"Sub" : "Inspection of retail and distribution outlets to identify illegal outlets",
		  				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
		  				"Progress" : "",
		  				"Reason" : "The absence of an M&E plan before now to guide data collation of activities carried out.",
		  				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
		  				"Feed" : "",
		  			},
		  			{
		  				"Id" : "17.2.2",
		  				"Key" : "Take enforcement action against the identified illegal outlets",
		  				"Sub" : "",
		  				"Status" : "",
		  				"Progress" : "",
		  				"Reason" : "",
		  				"Plan" : "",
		  			},
		  			{
		  				"Id" : "17.2.2",
		  				"Key" : "Take enforcement action against the identified illegal outlets",
		  				"Sub" : "",
		  				"Status" : "",
		  				"Progress" : "",
		  				"Reason" : "",
		  				"Plan" : "",
		  			},
		  			{
		  				"Id" : "17.2.2.a",
		  				"Key" : "",
		  				"Sub" : "seal and bar the illegal outlets",
		  				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
		  				"Progress" : "",
		  				"Reason" : "The absence of an M&E plan before now to guide data collation of activities carried out.",
		  				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
		  			},
		  			{
		  				"Id" : "17.2.2.b",
		  				"Key" : "",
		  				"Sub" : "Seize and evacuate the drugs from the illegal outlets for destruction",
		  				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
		  				"Progress" : "",
		  				"Reason" : "The absence of an M&E plan before now to guide data collation of activities carried out.",
		  				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
		  			},
		  			{
		  				"Id" : "17.2.2.c",
		  				"Key" : "",
		  				"Sub" : "closure of illegal outlets",
		  				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
		  				"Progress" : "",
		  				"Reason" : "The absence of an M&E plan before now to guide data collation of activities carried out.",
		  				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
		  			}

		  	];
	
	db5.output5 = [
		  			{
		  				"Id" : "17.2.1",
		  				"Key" : "Identify the illegal narcotics and psychotropic distribution outlets",
		  				"Sub" : "",
		  				"Status" : "",
		  				"Progress" : "",
		  				"Reason" : "",
		  				"Plan" : "",
		  			},
		  			{
		  				"Id" : "17.2.1.a",
		  				"Key" : "",
		  				"Sub" : "Conduct Nation wide surveillance activities",
		  				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
		  				"Progress" : "",
		  				"Reason" : "The absence of an M&E plan before to guide data collation of activities carried out.",
		  				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
		  			},
		  			{
		  				"Id" : "17.2.1.b",
		  				"Key" : "",
		  				"Sub" : "Inspection of retail and distribution outlets to identify illegal outlets",
		  				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
		  				"Progress" : "",
		  				"Reason" : "The absence of an M&E plan before now to guide data collation of activities carried out.",
		  				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
		  				"Feed" : "",
		  			},
		  			{
		  				"Id" : "17.2.2",
		  				"Key" : "Take enforcement action against the identified illegal outlets",
		  				"Sub" : "",
		  				"Status" : "",
		  				"Progress" : "",
		  				"Reason" : "",
		  				"Plan" : "",
		  			},
		  			{
		  				"Id" : "17.2.2",
		  				"Key" : "Take enforcement action against the identified illegal outlets",
		  				"Sub" : "",
		  				"Status" : "",
		  				"Progress" : "",
		  				"Reason" : "",
		  				"Plan" : "",
		  			},
		  			{
		  				"Id" : "17.2.2.a",
		  				"Key" : "",
		  				"Sub" : "seal and bar the illegal outlets",
		  				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
		  				"Progress" : "",
		  				"Reason" : "The absence of an M&E plan before now to guide data collation of activities carried out.",
		  				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
		  			},
		  			{
		  				"Id" : "17.2.2.b",
		  				"Key" : "",
		  				"Sub" : "Seize and evacuate the drugs from the illegal outlets for destruction",
		  				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
		  				"Progress" : "",
		  				"Reason" : "The absence of an M&E plan before now to guide data collation of activities carried out.",
		  				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
		  			},
		  			{
		  				"Id" : "17.2.2.c",
		  				"Key" : "",
		  				"Sub" : "closure of illegal outlets",
		  				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
		  				"Progress" : "",
		  				"Reason" : "The absence of an M&E plan before now to guide data collation of activities carried out.",
		  				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
		  			}

		  	];
	
	db6.output6 = [
		  			{
		  				"Id" : "17.2.1",
		  				"Key" : "Identify the illegal narcotics and psychotropic distribution outlets",
		  				"Sub" : "",
		  				"Status" : "",
		  				"Progress" : "",
		  				"Reason" : "",
		  				"Plan" : "",
		  			},
		  			{
		  				"Id" : "17.2.1.a",
		  				"Key" : "",
		  				"Sub" : "Conduct Nation wide surveillance activities",
		  				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
		  				"Progress" : "",
		  				"Reason" : "The absence of an M&E plan before to guide data collation of activities carried out.",
		  				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
		  			},
		  			{
		  				"Id" : "17.2.1.b",
		  				"Key" : "",
		  				"Sub" : "Inspection of retail and distribution outlets to identify illegal outlets",
		  				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
		  				"Progress" : "",
		  				"Reason" : "The absence of an M&E plan before now to guide data collation of activities carried out.",
		  				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
		  				"Feed" : "",
		  			},
		  			{
		  				"Id" : "17.2.2",
		  				"Key" : "Take enforcement action against the identified illegal outlets",
		  				"Sub" : "",
		  				"Status" : "",
		  				"Progress" : "",
		  				"Reason" : "",
		  				"Plan" : "",
		  			},
		  			{
		  				"Id" : "17.2.2",
		  				"Key" : "Take enforcement action against the identified illegal outlets",
		  				"Sub" : "",
		  				"Status" : "",
		  				"Progress" : "",
		  				"Reason" : "",
		  				"Plan" : "",
		  			},
		  			{
		  				"Id" : "17.2.2.a",
		  				"Key" : "",
		  				"Sub" : "seal and bar the illegal outlets",
		  				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
		  				"Progress" : "",
		  				"Reason" : "The absence of an M&E plan before now to guide data collation of activities carried out.",
		  				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
		  			},
		  			{
		  				"Id" : "17.2.2.b",
		  				"Key" : "",
		  				"Sub" : "Seize and evacuate the drugs from the illegal outlets for destruction",
		  				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
		  				"Progress" : "",
		  				"Reason" : "The absence of an M&E plan before now to guide data collation of activities carried out.",
		  				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
		  			},
		  			{
		  				"Id" : "17.2.2.c",
		  				"Key" : "",
		  				"Sub" : "closure of illegal outlets",
		  				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
		  				"Progress" : "",
		  				"Reason" : "The absence of an M&E plan before now to guide data collation of activities carried out.",
		  				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
		  			}

		  	];
	
	db7.output7 = [
		  			{
		  				"Id" : "17.2.1",
		  				"Key" : "Identify the illegal narcotics and psychotropic distribution outlets",
		  				"Sub" : "",
		  				"Status" : "",
		  				"Progress" : "",
		  				"Reason" : "",
		  				"Plan" : "",
		  			},
		  			{
		  				"Id" : "17.2.1.a",
		  				"Key" : "",
		  				"Sub" : "Conduct Nation wide surveillance activities",
		  				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
		  				"Progress" : "",
		  				"Reason" : "The absence of an M&E plan before to guide data collation of activities carried out.",
		  				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
		  			},
		  			{
		  				"Id" : "17.2.1.b",
		  				"Key" : "",
		  				"Sub" : "Inspection of retail and distribution outlets to identify illegal outlets",
		  				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
		  				"Progress" : "",
		  				"Reason" : "The absence of an M&E plan before now to guide data collation of activities carried out.",
		  				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
		  				"Feed" : "",
		  			},
		  			{
		  				"Id" : "17.2.2",
		  				"Key" : "Take enforcement action against the identified illegal outlets",
		  				"Sub" : "",
		  				"Status" : "",
		  				"Progress" : "",
		  				"Reason" : "",
		  				"Plan" : "",
		  			},
		  			{
		  				"Id" : "17.2.2",
		  				"Key" : "Take enforcement action against the identified illegal outlets",
		  				"Sub" : "",
		  				"Status" : "",
		  				"Progress" : "",
		  				"Reason" : "",
		  				"Plan" : "",
		  			},
		  			{
		  				"Id" : "17.2.2.a",
		  				"Key" : "",
		  				"Sub" : "seal and bar the illegal outlets",
		  				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
		  				"Progress" : "",
		  				"Reason" : "The absence of an M&E plan before now to guide data collation of activities carried out.",
		  				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
		  			},
		  			{
		  				"Id" : "17.2.2.b",
		  				"Key" : "",
		  				"Sub" : "Seize and evacuate the drugs from the illegal outlets for destruction",
		  				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
		  				"Progress" : "",
		  				"Reason" : "The absence of an M&E plan before now to guide data collation of activities carried out.",
		  				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
		  			},
		  			{
		  				"Id" : "17.2.2.c",
		  				"Key" : "",
		  				"Sub" : "closure of illegal outlets",
		  				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
		  				"Progress" : "",
		  				"Reason" : "The absence of an M&E plan before now to guide data collation of activities carried out.",
		  				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
		  			}

		  	];
	
	db8.output8 = [
		  			{
		  				"Id" : "17.2.1",
		  				"Key" : "Identify the illegal narcotics and psychotropic distribution outlets",
		  				"Sub" : "",
		  				"Status" : "",
		  				"Progress" : "",
		  				"Reason" : "",
		  				"Plan" : "",
		  			},
		  			{
		  				"Id" : "17.2.1.a",
		  				"Key" : "",
		  				"Sub" : "Conduct Nation wide surveillance activities",
		  				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
		  				"Progress" : "",
		  				"Reason" : "The absence of an M&E plan before to guide data collation of activities carried out.",
		  				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
		  			},
		  			{
		  				"Id" : "17.2.1.b",
		  				"Key" : "",
		  				"Sub" : "Inspection of retail and distribution outlets to identify illegal outlets",
		  				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
		  				"Progress" : "",
		  				"Reason" : "The absence of an M&E plan before now to guide data collation of activities carried out.",
		  				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
		  				"Feed" : "",
		  			},
		  			{
		  				"Id" : "17.2.2",
		  				"Key" : "Take enforcement action against the identified illegal outlets",
		  				"Sub" : "",
		  				"Status" : "",
		  				"Progress" : "",
		  				"Reason" : "",
		  				"Plan" : "",
		  			},
		  			{
		  				"Id" : "17.2.2",
		  				"Key" : "Take enforcement action against the identified illegal outlets",
		  				"Sub" : "",
		  				"Status" : "",
		  				"Progress" : "",
		  				"Reason" : "",
		  				"Plan" : "",
		  			},
		  			{
		  				"Id" : "17.2.2.a",
		  				"Key" : "",
		  				"Sub" : "seal and bar the illegal outlets",
		  				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
		  				"Progress" : "",
		  				"Reason" : "The absence of an M&E plan before now to guide data collation of activities carried out.",
		  				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
		  			},
		  			{
		  				"Id" : "17.2.2.b",
		  				"Key" : "",
		  				"Sub" : "Seize and evacuate the drugs from the illegal outlets for destruction",
		  				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
		  				"Progress" : "",
		  				"Reason" : "The absence of an M&E plan before now to guide data collation of activities carried out.",
		  				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
		  			},
		  			{
		  				"Id" : "17.2.2.c",
		  				"Key" : "",
		  				"Sub" : "closure of illegal outlets",
		  				"Status" : '<div style=" width: 50px ; height: 50px; background: #ebe711;" data-toggle="modal" data-target="#myModal"></div>',
		  				"Progress" : "",
		  				"Reason" : "The absence of an M&E plan before now to guide data collation of activities carried out.",
		  				"Plan" : "Relevant sections of the Agency- NAFDAC and other Stakeholder to be contacted for report of activities carried out in line with the NDCMP 2015-2019 operational plan",
		  			}

		  	];

}());