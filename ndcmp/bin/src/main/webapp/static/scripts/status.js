$(document).ready(function(){
	
	$('.hideHint').hide();
	$('.showHint').css('cursor', 'pointer');
	$('.hideHint').css('cursor', 'pointer');
	
$('#showColorHint').hide();
	$('.ui-slider-handle').css('cursor','pointer');
	
		$('#status-list').dataTable({
			 "aoColumnDefs" : [ {
		            'bSortable' : false,
		            'aTargets' : [ 7 ]
		       }],
		       "pagingType": "full_numbers"
		});	
		
		var colorPercent=$('#colorPercent').val();
		
		$('#percent').val(colorPercent+"%");
		
		$('.project').each(function() {
			var $projectBar = $(this).find('.bar');
			var $projectPercent = $(this).find('.percent');
			var $projectRange = $(this).find('.ui-slider-range');
			$projectBar.slider({
				range : "min",
				animate : true,
				value : colorPercent,
				min : 0,
				max : 100,
				step : 1,
				slide : function(event, ui) {
					$projectPercent.val(ui.value + "%");
				},
				change : function(event, ui) {
					var $projectRange = $(this).find('.ui-slider-range');
					var percent = ui.value;
					if (percent <= 10) {
						$('#percent').val('#DDA6A1');
						$('#colorPercent').val(percent);
						$('#colorPer').val(percent+"%");
						$projectPercent.css({
							'color' : '#DDA6A1'
						});
						$projectRange.css({
							'background' : '#DDA6A1'
						});
					} else if (percent > 11 && percent < 20) {
						$('#percent').val('#E77B70');
						$('#colorPercent').val(percent);
						$('#colorPer').val(percent+"%");
						$projectPercent.css({
							'color' : '#E77B70'
						});
						$projectRange.css({
							'background' : '#E77B70'
						});
					} else if (percent > 21 && percent < 30) {
						$('#percent').val('#F14D41');
						$('#colorPercent').val(percent);
						$('#colorPer').val(percent+"%");
						$projectPercent.css({
							'color' : '#F14D41'
						});
						$projectRange.css({
							'background' : '#F14D41'
						});
					} else if (percent > 31 && percent < 40) {
						$('#percent').val('#F66A37');
						$('#colorPercent').val(percent);
						$('#colorPer').val(percent+"%");
						$projectPercent.css({
							'color' : '#F66A37'
						});
						$projectRange.css({
							'background' : '#F66A37'
						});
					} else if (percent > 41 && percent < 50) {
						$('#percent').val('#F89A3A');
						$('#colorPercent').val(percent);
						$('#colorPer').val(percent+"%");
						$projectPercent.css({
							'color' : '#F89A3A'
						});
						$projectRange.css({
							'background' : '#F89A3A'
						});
					}else if (percent > 51 && percent < 60) {
						$('#percent').val('#FECB3A');
						$('#colorPercent').val(percent);
						$('#colorPer').val(percent+"%");
						$projectPercent.css({
							'color' : '#FECB3A'
						});
						$projectRange.css({
							'background' : '#FECB3A'
						});
					}else if (percent > 61 && percent < 70) {
						$('#percent').val('#F3E83B');
						$('#colorPercent').val(percent);
						$('#colorPer').val(percent+"%");
						$projectPercent.css({
							'color' : '#F3E83B'
						});
						$projectRange.css({
							'background' : '#F3E83B'
						});
					}else if (percent > 71 && percent < 80) {
						$('#percent').val('#CDDA42');
						$('#colorPercent').val(percent);
						$('#colorPer').val(percent+"%");
						$projectPercent.css({
							'color' : '#CDDA42'
						});
						$projectRange.css({
							'background' : '#CDDA42'
						});
					}else if (percent > 81 && percent < 90) {
						$('#percent').val('#ABD045');
						$('#colorPercent').val(percent);
						$('#colorPer').val(percent+"%");
						$projectPercent.css({
							'color' : '#ABD045'
						});
						$projectRange.css({
							'background' : '#ABD045'
						});
					}else if (percent > 91) {
						$('#percent').val('#9FCA47');
						$('#colorPercent').val(percent);
						$('#colorPer').val(percent+"%");
						$projectPercent.css({
							'color' : '#9FCA47'
						});
						$projectRange.css({
							'background' : '#9FCA47'
						});
					}
				}
			});
		});
		
		
		
		$("input.onchange-example").ColorPickerSliders({
			previewontriggerelement : true,
			flat : false,
			color : '#cf966f',
			customswatches : false,
			swatches : [ '#DDA6A1', '#E77B70', '#F14D41', '#F66A37','#F89A3A','#FECB3A','#F3E83B','#CDDA42','#ABD045','#9FCA47' ],
			order : {
				hsl : 1
			}
		});
		
			$('#myModal').on('show.bs.modal',function(event) {
					var getIdFromRow = $(event.relatedTarget).find('td:nth-child(3)').text();
					$(this).find('#process').html($('<b> Are you sure to delete Status Information ? '+ getIdFromRow + '</b>'));
			});

			$('#myModal').on('show.bs.modal',function(e) {
					$(this).find('.btn-ok').attr('href',
					$(e.relatedTarget).data('href'));
			});
							
			$('#userActiveDialog').on('show.bs.modal',function(event) {
					var getIdFromRow = $(event.relatedTarget).find('td:nth-child(3)').text();
					$(this).find('#userActive').html($('<b> Are you sure to Activate Status ? '+ getIdFromRow + '</b>'));
			});
			$('#userActiveDialog').on(
					'show.bs.modal',
					function(e) {
						$(this).find('.btn-ok').attr('href',
								$(e.relatedTarget).data('href'));
					});
			
			/* device dis approved modal window */
			$('#userDeactiveDialog').on('show.bs.modal',function(event) {
					var getIdFromRow = $(event.relatedTarget).find('td:nth-child(3)').text();
						$(this).find('#userDeactive').html($('<b> Are you sure to Deactivate Status ? '+ getIdFromRow + '</b>'));
			});
			
			$('#userDeactiveDialog').on('show.bs.modal',function(e) {
						$(this).find('.btn-ok').attr('href',$(e.relatedTarget).data('href'));
			});
			
			$('#myModal').on('show.bs.modal',function(event) {
						var getIdFromRow = $(event.relatedTarget).find('td:nth-child(3)').text();
						$(this).find('#process').html($('<b> Are you sure to delete User Information ? '+ getIdFromRow + '</b>'));
			});
			
			$('#myModal').on('show.bs.modal',function(e) {
						$(this).find('.btn-ok').attr('href',$(e.relatedTarget).data('href'));
			});
			
			$('#cancel').click(function() {
				window.history.back();
			});
			
			/**
			 * Purpose : To set the color based on the input color range
			 */
			 $("#colorPer").keyup(function(){
		        var colorPercentage = $("#colorPer").val();
		        var percent = colorPercentage.replace(/[&\/\\#,+()$~%.'":*?<>{}]/g, '');
		       	
		        $('.project').each(function() {
					var $projectBar = $(this).find('.bar');
					var $projectPercent = $(this).find('.percent');
					var $projectRange = $(this).find('.ui-slider-range');
					$projectBar.slider({
						range : "min",
						animate : true,
						value : percent,
						min : 0,
						max : 100,
						step : 1,
						slide : function(event, ui) {
							$projectPercent.val(ui.value + "%");
						},
						change : function(event, ui) {
							var $projectRange = $(this).find('.ui-slider-range');
							var percent = ui.value;
							if (percent <= 10) {
								$('#percent').val('#DDA6A1');
								$('#colorPercent').val(percent);
								$('#colorPer').val(percent+"%");
								$projectPercent.css({
									'color' : '#DDA6A1'
								});
								$projectRange.css({
									'background' : '#DDA6A1'
								});
							} else if (percent > 11 && percent < 20) {
								$('#percent').val('#E77B70');
								$('#colorPercent').val(percent);
								$('#colorPer').val(percent+"%");
								$projectPercent.css({
									'color' : '#E77B70'
								});
								$projectRange.css({
									'background' : '#E77B70'
								});
							} else if (percent > 21 && percent < 30) {
								$('#percent').val('#F14D41');
								$('#colorPercent').val(percent);
								$('#colorPer').val(percent+"%");
								$projectPercent.css({
									'color' : '#F14D41'
								});
								$projectRange.css({
									'background' : '#F14D41'
								});
							} else if (percent > 31 && percent < 40) {
								$('#percent').val('#F66A37');
								$('#colorPercent').val(percent);
								$('#colorPer').val(percent+"%");
								$projectPercent.css({
									'color' : '#F66A37'
								});
								$projectRange.css({
									'background' : '#F66A37'
								});
							} else if (percent > 41 && percent < 50) {
								$('#percent').val('#F89A3A');
								$('#colorPercent').val(percent);
								$('#colorPer').val(percent+"%");
								$projectPercent.css({
									'color' : '#F89A3A'
								});
								$projectRange.css({
									'background' : '#F89A3A'
								});
							}else if (percent > 51 && percent < 60) {
								$('#percent').val('#FECB3A');
								$('#colorPercent').val(percent);
								$('#colorPer').val(percent+"%");
								$projectPercent.css({
									'color' : '#FECB3A'
								});
								$projectRange.css({
									'background' : '#FECB3A'
								});
							}else if (percent > 61 && percent < 70) {
								$('#percent').val('#F3E83B');
								$('#colorPercent').val(percent);
								$('#colorPer').val(percent+"%");
								$projectPercent.css({
									'color' : '#F3E83B'
								});
								$projectRange.css({
									'background' : '#F3E83B'
								});
							}else if (percent > 71 && percent < 80) {
								$('#percent').val('#CDDA42');
								$('#colorPercent').val(percent);
								$('#colorPer').val(percent+"%");
								$projectPercent.css({
									'color' : '#CDDA42'
								});
								$projectRange.css({
									'background' : '#CDDA42'
								});
							}else if (percent > 81 && percent < 90) {
								$('#percent').val('#ABD045');
								$('#colorPercent').val(percent);
								$('#colorPer').val(percent+"%");
								$projectPercent.css({
									'color' : '#ABD045'
								});
								$projectRange.css({
									'background' : '#ABD045'
								});
							}else if (percent > 91) {
								$('#percent').val('#9FCA47');
								$('#colorPercent').val(percent);
								$('#colorPer').val(percent+"%");
								$projectPercent.css({
									'color' : '#9FCA47'
								});
								$projectRange.css({
									'background' : '#9FCA47'
								});
							}
						}
					});
				});
			 });
});
function submitStatusForm() {
	$('#statusAddform')
			.formValidation(
					{
						icon : {
							valid : 'glyphicon glyphicon-ok',
							invalid : 'glyphicon glyphicon-remove',
							validating : 'glyphicon glyphicon-refresh'
						},
						fields : {
							name : {
								validators : {
									notEmpty : {
										message : 'Status Name is required'
									},
									stringLength : {
										min : 3,
										max : 25,
										message : 'Status Name must be more than 3 and less than 25 characters long'
									}
								}
							},
							description : {
								validators : {
									notEmpty : {
										message : 'Status Description is required'
									},
									stringLength : {
										min : 3,
										max : 25,
										message : 'Status Description must be more than 3 and less than 25 characters long'
									}
								}
							},
							startRange : {
								validators : {
									notEmpty : {
										message : 'Start Range is required'
									}
								}
							},
							endRange : {
								validators : {
									notEmpty : {
										message : 'End Range is required'
									}
								}
							}/*,
							statusColor : {
								validators : {
									notEmpty : {
										message : 'Status Colour is required'
									},
									stringLength : {
										min : 3,
										max : 25,
										message : 'Status Colour must be more than 3 and less than 25 characters long'
									}
								}
							}*/
						}

					});

	return true;
};

function getStatusInformation(id){
	var stateInfo = '';
	$.ajax({
		url : '/ndcmp/api/getStatusInformation',
		data : {
			statusId : id
		},
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data) {
			if (data != null) {
				statusInfo = data;
				$('#statusName').html(statusInfo.name);
				$('#statusDesc').html(statusInfo.description);
				//$('#statusColor').html(statusInfo.statusColor);
				$('#color').css('background-color',statusInfo.statusColor);
				$('#statusStartRange').html(statusInfo.startRange);
				$('#statusEndRange').html(statusInfo.endRange);
				$('#colorStatus').html(statusInfo.status);
				$('#statusInformation').modal('show');
				
			}
		}
	});
}