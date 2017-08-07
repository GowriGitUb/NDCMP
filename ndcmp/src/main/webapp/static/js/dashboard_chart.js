
/*
 * Added By Muthu
 * Loading dashboard charts
 */

function dashbord_adminchart() {
	$('#container').highcharts(
			{

				chart : {
					type : 'column'
				},

				title : {
					text : 'Total fruit consumtion, grouped by gender'
				},

				xAxis : {
					categories : [ 'Apples', 'Oranges', 'Pears', 'Grapes',
							'Bananas' ]
				},

				yAxis : {
					allowDecimals : false,
					min : 0,
					title : {
						text : 'Number of fruits'
					}
				},

				tooltip : {
					formatter : function() {
						return '<b>' + this.x + '</b><br/>' + this.series.name
								+ ': ' + this.y + '<br/>' + 'Total: '
								+ this.point.stackTotal;
					}
				},

				plotOptions : {
					column : {
						stacking : 'normal'
					}
				},

				series : [ {
					name : 'John',
					data : [ 5, 3, 4, 7, 2 ],
					stack : 'male'
				}, {
					name : 'Joe',
					data : [ 3, 4, 4, 2, 5 ],
					stack : 'male'
				}, {
					name : 'Jane',
					data : [ 2, 5, 6, 2, 1 ],
					stack : 'female'
				}, {
					name : 'Janet',
					data : [ 3, 0, 4, 4, 3 ],
					stack : 'female'
				} ]
			});

	$('#sample')
			.highcharts(
					{
						title : {
							text : 'Combination chart'
						},
						xAxis : {
							categories : [ 'Apples', 'Oranges', 'Pears',
									'Bananas', 'Plums' ]
						},
						labels : {
							items : [ {
								html : 'Total fruit consumption',
								style : {
									left : '50px',
									top : '18px',
									color : (Highcharts.theme && Highcharts.theme.textColor)
											|| 'black'
								}
							} ]
						},
						series : [ {
							type : 'column',
							name : 'Jane',
							data : [ 3, 2, 1, 3, 4 ]
						}, {
							type : 'column',
							name : 'John',
							data : [ 2, 3, 5, 7, 6 ]
						}, {
							type : 'column',
							name : 'Joe',
							data : [ 4, 3, 3, 9, 0 ]
						}, {
							type : 'spline',
							name : 'Average',
							data : [ 3, 2.67, 3, 6.33, 3.33 ],
							marker : {
								lineWidth : 2,
								lineColor : Highcharts.getOptions().colors[3],
								fillColor : 'white'
							}
						}, {
							type : 'pie',
							name : 'Total consumption',
							data : [ {
								name : 'Jane',
								y : 13,
								color : Highcharts.getOptions().colors[0]
							// Jane's color
							}, {
								name : 'John',
								y : 23,
								color : Highcharts.getOptions().colors[1]
							// John's color
							}, {
								name : 'Joe',
								y : 19,
								color : Highcharts.getOptions().colors[2]
							// Joe's color
							} ],
							center : [ 100, 80 ],
							size : 100,
							showInLegend : false,
							dataLabels : {
								enabled : false
							}
						} ]
					});
}

