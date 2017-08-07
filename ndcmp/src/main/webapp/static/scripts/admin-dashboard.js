/**
 * Added 3ways of chart by Muthu
 */

function loadChart(agencyCode) {
	if(agencyCode == undefined){
		agencyCode = "";
	}
	$('#reviewerTab a:first').tab('show');
	showNotification();
	showReportingStatusSummary();
//	floatChart();
	loadDataCapturingYear();
	statusUpdaterDashboard(agencyCode);
	statusReviewedDashboard(agencyCode);
	statusApproverDashboard(agencyCode);
	//floatChartDisplay();
	//yearWiReport();
	//yearPlanVsActual();
	//viewSubCharts();
}
function loadDataCapturingYear(){
	$.ajax({
		url : '/ndcmp/api/getAllReportingYears',
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data) {
			if(data != null && data.length > 0){
				var year = '';
				for(var i = 0 ; i < data.length ; i++){
					if(i == 0){
						year += '<option selected="selected" value="'+data[i]+'">'+data[i]+'</option>';
					}else{
						year += '<option value="'+data[i]+'">'+data[i]+'</option>';
					}
					
				}
				 $('#datacapturingYear').html(year);
				 $('#dataReviewedYear').html(year);
				 $('#ApproverDashboardYear').html(year);
			}
		}
	});
}

function statusUpdaterDashboard(agency){
	var agencyName = agency;
	var selectedYear = $('#datacapturingYear').val();
	$.ajax({
		url : '/ndcmp/api/getDataCapturingDashboardGraphCounts',
		data : {
			year : selectedYear
		},
		type : 'GET',
		dataType : 'json',
		success : function(data) {
			var titles = [];
			var updated = [];
			var notUpdated = [];
			var outstandingUpdated = [];
			var outstandingNotUpdated = [];
			if (data != null) {
				for (var i = 0; i < data.length; i++) {
					titles.push(data[i].reportingPeriod);
					updated.push(data[i].updated);
					notUpdated.push(data[i].notUpdated);
					outstandingUpdated.push(data[i].outstandingUpdated);
					outstandingNotUpdated.push(data[i].outstandingNotUpdated);
				}
			}
			 $('#statusUpdaterDashboardDiv').highcharts({

			        chart: {
			            type: 'column'
			        },
			        
			        title : {
			        	text : 'Data Capturing Status ',
			        	align: 'left',
			        	y: 5
			        		
			        },
			        
			        subtitle : {
			        	text : agencyName,
			        	align: 'left',
			        	y: 23
			        },

			        xAxis: {
			            categories : titles,
			            crosshair: true
			        },

			        yAxis: {
			            allowDecimals: false,
			            min: 0,
			            title: {
			                text: 'Number of Activities'
			            }
			        },

			       /* tooltip: {
			            formatter: function () {
			                return '<b>' + this.x + '</b><br/>' +
			                    this.series.name + ': ' + this.y + '<br/>' +
			                    'Total: ' + this.point.stackTotal;
			            }
			        },*/
			        tooltip: {
			            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
			            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
			                '<td style="padding:0"><b>{point.y}</b></td></tr>',
			            footerFormat: '</table>',
			            shared: true,
			            useHTML: true
			        },

			        plotOptions: {
			            column: {
			                stacking: 'normal'
			            }
			        },

			        series: [{
			        	color: '#41c572',
			            name: 'Updated',
			            data: updated,
			            stack: 'Current'
			        }, {
			        	color: '#26a2ed',
			            name: 'Not Updated',
			            data: notUpdated,
			            stack: 'Current'
			        }, {
			        	color: '#3c763d',
			            name: 'Updated (Carry forwarded)',
			            data: outstandingUpdated,
			            stack: 'Outstanding'
			        }, {
			        	color: '#f05050',
			            name: 'Not Updated (Carry forwarded)',
			            data: outstandingNotUpdated,
			            stack: 'Outstanding'
			        }]
			    });
		}
	});
}

function statusReviewedDashboard(agency){
	var selectedYear = $('#dataReviewedYear').val();
	$.ajax({
		url : '/ndcmp/api/getDataReviewedDashboardGraphCounts',
		data : {
			year : selectedYear
		},
		type : 'GET',
		dataType : 'json',
		success : function(data) {
			var titles = [];
			var expected = [];
			var updated = [];
			var notUpdated = [];
			var outstandingExpected = [];
			var outstandingUpdated = [];
			var outstandingNotUpdated = [];
			
			if (data != null) {
				for (var i = 0; i < data.length; i++) {
					titles.push(data[i].reportingPeriod);
					expected.push(data[i].expected);
					updated.push(data[i].updated);
					notUpdated.push(data[i].notUpdated);
					outstandingExpected.push(data[i].outstandingExpected);
					outstandingUpdated.push(data[i].outstandingUpdated);
					outstandingNotUpdated.push(data[i].outstandingNotUpdated);
				}
			}
			 $('#statusReviewerDashboardDiv').highcharts({

			        chart: {
			            type: 'column'
			        },
			        
			        title : {
			        	text : 'Data Reviewed Status',
			        	align: 'left',
			        	y: 5
			        		
			        },
			        
			        subtitle : {
			        	text : agency,
			        	align: 'left',
			        	y: 23
			        },
			        
			        xAxis: {
			            categories : titles,
			            crosshair: true
			        },

			        yAxis: {
			            allowDecimals : false,
			            min: 0,
			            title: {
			                text: 'Number of Lines'
			            }
			        },

			        /*tooltip: {
			            formatter: function () {
			                return '<b>' + this.x + '</b><br/>' +
			                    this.series.name + ': ' + this.y + '<br/>' +
			                    'Total: ' + this.point.stackTotal;
			            }
			        },*/
			        tooltip: {
			            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
			            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
			                '<td style="padding:0"><b>{point.y}</b></td></tr>',
			            footerFormat: '</table>',
			            shared: true,
			            useHTML: true
			        },

			        plotOptions: {
			            column: {
			                stacking: 'normal'
			            }
			        },

			        series: [{
			        	color: '#f842cd',
			            name: 'Expected',
			            data: expected,
			            stack: 'Expected'
			        },{
			        	color: '#58b',
			            name: 'Reviewed',
			            data: updated,
			            stack: 'Current'
			        }, {
			        	color: '#fec138',
			            name: 'Not Reviewed',
			            data: notUpdated,
			            stack: 'Current'
			        }, {
			        	color: '#906dff',
			            name: 'Expected (Carry forwarded)',
			            data: outstandingExpected,
			            stack: 'OutstandingExpected'
			        }, {
			        	color: '#446',
			            name: 'Reviewed (Carry forwarded)',
			            data: outstandingUpdated,
			            stack: 'Outstanding'
			        }, {
			        	color: '#f2520d',
			            name: 'Not Reviewed (Carry forwarded)',
			            data: outstandingNotUpdated,
			            stack: 'Outstanding'
			        }]
			    });
		}
	});
}

function showApproverChartLevel1(){
	$('#statusApproverDashboardDiv').show();
	$('#statusApproverDashboardLevel2Div').hide();
	$('#approverBackButton').hide();
	$('#approverGoButton').show();
	$('#ApproverDashboardYear').show();
}

function statusApproverDashboard(agency){
	var selectedYear = $('#ApproverDashboardYear').val();
	showApproverChartLevel1();
	$.ajax({
		url : '/ndcmp/api/getApproverDashboardGraphCounts',
		data : {
			year : selectedYear
		},
		type : 'GET',
		dataType : 'json',
		success : function(data) {
			var titles = [];
			var currentTotal = [];
			var toBeCompleted = [];
			var completed = [];
			var notCompleted = [];
			var outstandingTotal = [];
			var outstandingCompleted = [];
			var outstandingNotCompleted = [];
			if (data != null) {
				for (var i = 0; i < data.length; i++) {
					titles.push(data[i].reportingPeriod);
					currentTotal.push(data[i].currentTotal);
					toBeCompleted.push(data[i].toBeCompleted);
					completed.push(data[i].completed);
					notCompleted.push(data[i].notCompleted);
					outstandingTotal.push(data[i].outstandingTotal);
					outstandingCompleted.push(data[i].outstandingCompleted);
					outstandingNotCompleted.push(data[i].outstandingNotCompleted);
				}
			}
			Highcharts.chart('statusApproverDashboardDiv', {
			        chart: {
			        	events: {
			                load: function () {
			                    var theSeries = this.series;
			                    $.each(theSeries, function () {
			                        if (this.index > 3) {
			                            this.setVisible(false);
			                        }
			                    });
			                }
			            },
			            type: 'column'
			        },
			        title: {
			            text: 'Activity Status',
			            align : 'left'
			        },
			        subtitle : {
			        	text : agency,
			        	align: 'left',
			        	y: 30
			        },
			        xAxis: {
			            categories: titles,
			            crosshair: true
			        },
			        yAxis: {
			        	allowDecimals : false,
			            min: 0,
			            title: {
			                text: 'Number of Activities'
			            }
			        },
			        /*tooltip: {
			            formatter: function () {
			                return '<b>' + this.x + '</b><br/>' +
			                    this.series.name + ': ' + this.y;
			            }
			        },*/
			        tooltip: {
			            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
			            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
			                '<td style="padding:0"><b>{point.y}</b></td></tr>',
			            footerFormat: '</table>',
			            shared: true,
			            useHTML: true
			        },
			        plotOptions: {
			            column: {
			                pointPadding: 0.2,
			                borderWidth: 0
			            },
			            series: {
			                 cursor: 'pointer',
			                 point: {
			                     events: {
			                         click: function () { 
			                        	 //statusApproverDashboardLevel2(this.category);
			                        	 statusApproverDashboardLevel2(this.category, this.series.name, agency); 
			                         }
			                     }
			                 }
			             }
			        },
			        series: [{
			        	color: '#4078ab',
			            name: 'Open Activities',
			            data: currentTotal

			        }, {
			        	color: '#fe5f01',
			            name: 'To Be Completed',
			            data: toBeCompleted

			        }, {
			        	color: '#00bf7d',
			            name: 'Completed',
			            data: completed

			        }, {
			        	color: '#e42908',
			            name: 'Not Completed',
			            data: notCompleted

			        }, {
			        	color: '#8064a1',
			            name: 'Carry Forwarded Total',
			            data: outstandingTotal

			        }, {
			        	color: '#1d500d',
			            name: 'Completed (Carry forwarded)',
			            data: outstandingCompleted

			        }, {
			        	color: '#c9443f',
			            name: 'Not Completed (Carry forwarded)',
			            data: outstandingNotCompleted

			        }]
			    });
		}
	});
}

/*function statusApproverDashboardLevel2(reportingPeriod) {
	alert (reportingPeriod);
}*/

function showApproverChartLevel2(){
	$('#statusApproverDashboardDiv').hide();
	$('#statusApproverDashboardLevel2Div').show();
	$('#statusApproverDashboardLevel2Div').html('<img alt="" src="../static/img/dashboardloading.gif" style="margin-top:100px ">');
	$('#approverBackButton').show();
	$('#approverGoButton').hide();
	$('#ApproverDashboardYear').hide();
}

function statusApproverDashboardLevel2(reportingPeriod, type, agency) {
	var reporting = reportingPeriod.split(' - ');
	if(reporting.length == 2){
		var selectedYear = reporting[0];
		var selectedQuarter = reporting[1];
		showApproverChartLevel2();
		$.ajax({
			url : '/ndcmp/api/getApproverDashboardGraphCountsByReportingPeriod',
			data : {
				year : selectedYear,
				quarter : selectedQuarter
			},
			type : 'GET',
			dataType : 'json',
			success : function(data) {
				var titles = [];
				var currentTotal = [];
				var toBeCompleted = [];
				var completed = [];
				var notCompleted = [];
				var outstandingTotal = [];
				var outstandingCompleted = [];
				var outstandingNotCompleted = [];
				if (data != null) {
					for (var i = 0; i < data.length; i++) {
						titles.push(data[i].reportingPeriod);
						currentTotal.push(data[i].currentTotal);
						toBeCompleted.push(data[i].toBeCompleted);
						completed.push(data[i].completed);
						notCompleted.push(data[i].notCompleted);
						outstandingTotal.push(data[i].outstandingTotal);
						outstandingCompleted.push(data[i].outstandingCompleted);
						outstandingNotCompleted.push(data[i].outstandingNotCompleted);
					}
				}
				Highcharts.chart('statusApproverDashboardLevel2Div', {
				        chart: {
				        	events: {
				                load: function () {
				                    var theSeries = this.series;
				                    $.each(theSeries, function () {
				                        if (this.index > 3) {
				                            this.setVisible(false);
				                        }
				                    });
				                }
				            },
				            type: 'column'
				        },
				        title: {
				            text: 'Activity Status by Theme - ' + reportingPeriod,
				            align : 'left'
				        },
				        subtitle : {
				        	text : agency,
				        	align: 'left',
				        	y: 30
				        },
				        xAxis: {
				            categories: titles,
				            crosshair: true
				        },
				        yAxis: {
				        	allowDecimals : false,
				            min: 0,
				            title: {
				                text: 'Number of Activities'
				            }
				        },
				        /*tooltip: {
				            formatter: function () {
				                return '<b>' + this.x + '</b><br/>' +
				                    this.series.name + ': ' + this.y;
				            }
				        },*/
				        tooltip: {
				            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
				            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
				                '<td style="padding:0"><b>{point.y}</b></td></tr>',
				            footerFormat: '</table>',
				            shared: true,
				            useHTML: true
				        },
				        plotOptions: {
				            column: {
				                pointPadding: 0.2,
				                borderWidth: 0
				            }/*,
				            series: {
				                 cursor: 'pointer',
				                 point: {
				                     events: {
				                         click: function () { 
				                        	 //statusApproverDashboardLevel2(this.category);
				                        	 statusApproverDashboardLevel2(this.category, this.series.name); 
				                         }
				                     }
				                 }
				             }*/
				        },
				        series: [{
				        	color: '#4078ab',
				            name: 'Open Activities',
				            data: currentTotal

				        }, {
				        	color: '#fe5f01',
				            name: 'To Be Completed',
				            data: toBeCompleted

				        }, {
				        	color: '#00bf7d',
				            name: 'Completed',
				            data: completed

				        }, {
				        	color: '#e42908',
				            name: 'Not Completed',
				            data: notCompleted

				        }, {
				        	color: '#8064a1',
				            name: 'Carry Forwarded Total',
				            data: outstandingTotal

				        }, {
				        	color: '#1d500d',
				            name: 'Completed (Carry forwarded)',
				            data: outstandingCompleted

				        }, {
				        	color: '#c9443f',
				            name: 'Not Completed (Carry forwarded)',
				            data: outstandingNotCompleted

				        }]
				    });
			}
		});
	}
	
	
}

function viewSubCharts() {
	$('#container')
			.highcharts(
					{
						chart : {
							type : 'column'
						},
						title : {
							text : 'Stacked Report for Activity'
						},
						xAxis : {
							categories : [
									'LEGAL AND POLICY FRAMEWORK',
									'TARGETING CRIMINAL WEALTH',
									'INTELLIGENCE-LED POLICING AND COLLABORATION',
									' PROFESSIONALIZATION AND OPERATIONAL CAPABILITY OF NDLEA',
									' SENSITIZATION AND PREVENTION',
									'ADVOCACY',
									'TREATEMENT AND CONTINUING CARE',
									'DRUG USE AND HIV AND AIDS',
									'NATIONAL DRUG MONITORING SYSTEM',
									'ESTIMATION AND QUANTIFICATION',
									'DISTRIBUTION', 'CONTROL AND ACCESS',
									'COORDINATION MANAGEMENT', ],
							title : {
								text : 'Theme Based Report on Activity',
								style : {
									fontWeight : 'bold',
									color : (Highcharts.theme && Highcharts.theme.textColor)
											|| 'black'
								}
							},

						},
						yAxis : {
							min : 0,
							title : {
								text : 'Activities',
								style : {
									fontWeight : 'bold',
									color : (Highcharts.theme && Highcharts.theme.textColor)
											|| 'black'
								}
							},
							stackLabels : {
								enabled : true,
								style : {
									fontWeight : 'bold',
									color : (Highcharts.theme && Highcharts.theme.textColor)
											|| 'gray'
								}
							}
						},

						legend : {
							align : 'left',
							x : -10,
							/* verticalAlign : 'top', */
							y : 15,
							floating : true,
							backgroundColor : (Highcharts.theme && Highcharts.theme.background2)
									|| 'white',
							borderColor : '#CCC',
							borderWidth : 1,
							shadow : false
						},

						tooltip : {
							headerFormat : '<b>{point.x}</b><br/>',
							pointFormat : '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
						},
						plotOptions : {
							column : {
								stacking : 'normal',
								dataLabels : {
									enabled : true,
									color : (Highcharts.theme && Highcharts.theme.dataLabelsColor)
											|| 'white',
									style : {
										textShadow : '0 0 3px black'
									}
								}
							}
						},
						series : [
								{
									name : 'Completed',
									data : [ 15, 10, 9, 25, 13, 9, 20, 12, 10,
											40, 6, 30, 7 ],
									bars : {
										show : true,
										barWidth : 12 * 24 * 60 * 60 * 400,
										fill : true,
										lineWidth : 1,
										order : 1,
										fillColor : "#8bc34a"
									},
									color : "#8bc34a"
								},
								{
									name : 'In Progress',
									data : [ 5, 15, 5, 14, 10, 4, 20, 10, 16,
											2, 18, 12, 1 ],
									bars : {
										show : true,
										barWidth : 12 * 24 * 60 * 60 * 400,
										fill : true,
										lineWidth : 1,
										order : 2,
										fillColor : "#FFEB3B"
									},
									color : "#FFEB3B"
								},
								{
									name : 'Due and not started',
									data : [ 7, 5, 15, 20, 10, 4, 11, 20, 20,
											12, 30, 50, 2 ],
									bars : {
										show : true,
										barWidth : 12 * 24 * 60 * 60 * 400,
										fill : true,
										lineWidth : 1,
										order : 3,
										fillColor : "#F44336"
									},
									color : "#F44336"
								} ]
					});
}

function yearWiReport() {
	var year = $('#yearActivity').val();
	$('#yearAct').html(year);
}

function yearPlanVsActual() {
	var selectedYear = $('#planVsActual').val();
	$('#year').html(selectedYear);
}

function showNotification(){
	$.ajax({
		url : '/ndcmp/api/showNotification',
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data) {
			if (data != null && data.type != null) {
				if (data.type == 'alert-danger') {
					$('#reworkNotificationTitle').html(data.title);
					$('#reworkNotificationMessage').html(data.message);
					$('#reworkNotificationDiv').show();
				} else if (data.type == 'alert-info') {
					$('#infoNotificationTitle').html(data.title);
					$('#infoNotificationMessage').html(data.message);
					$('#infoNotificationDiv').show();
				} else if (data.type == 'alert-success') {
					$('#successNotificationTitle').html(data.title);
					$('#successNotificationMessage').html(data.message);
					$('#successNotificationDiv').show();
				}
			}
		}
	});
}

function showReportingStatusSummary(){
	$.ajax({
		url : '/ndcmp/api/showReportingStatusSummary',
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data) {
			if (data != null) {
				if (data.title == 'reviewer') {
					$('#reviewerReportingStatusSummaryDiv').show();
					$('#reviewerReportingStatusSummmary').html(data.message);
					$('#reviewerPartnerReportingStatusSummmary').html(data.type);
				} else if (data.title == 'partner') {
					$('#partnerReportingStatusSummaryDiv').show();
					$('#partnerReportingStatusSummmary').html(data.message);	
				} else if (data.title == 'approver') {
					$('#approverReportingStatusSummaryDiv').show();
					$('#approverReportingStatusSummmary').html(data.message);	
				}
			} else {
				$('#approverReportingStatusSummaryDiv').show();
				$('#reviewerReportingStatusSummaryDiv').hide();
				$('#partnerReportingStatusSummaryDiv').hide();
			}
		}
	});
}