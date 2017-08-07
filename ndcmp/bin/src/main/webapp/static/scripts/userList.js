
	$(document).ready(function(){
		
		$('#tabs > li').removeClass('active');
		$('#userTab').attr('class', 'active');
		
		$('#user-list').dataTable({
		       "pagingType": "full_numbers",
		       "aoColumnDefs" : [ {
		            'bSortable' : false,
		            'aTargets' : [ 7 ]
		       }],
		});	
	
	
		$('#userActiveDialog').on('show.bs.modal',function(event) {
				var getIdFromRow = $(event.relatedTarget).find('td:nth-child(3)').text();
				$(this).find('#userActive').html(
				$('<b> Are you sure to Activate User ? '+ getIdFromRow + '</b>'));
		});
		$('#userActiveDialog').on('show.bs.modal',function(e) {
				$(this).find('.btn-ok').attr('href',
				$(e.relatedTarget).data('href'));
		});
		
		/* device dis approved modal window */
		$('#userDeactiveDialog').on('show.bs.modal',function(event) {
				var getIdFromRow = $(event.relatedTarget).find('td:nth-child(3)').text();
				$(this).find('#userDeactive').html(
				$('<b> Are you sure to Deactivate User ? '+ getIdFromRow + '</b>'));
		});
		$('#userDeactiveDialog').on('show.bs.modal',function(e) {
				$(this).find('.btn-ok').attr('href',$(e.relatedTarget).data('href'));
		});
		
		$('#myModal').on( 'show.bs.modal',function(event) {
				var getIdFromRow = $(event.relatedTarget).find('td:nth-child(3)').text();
				$(this).find('#process').html(
				$('<b> Are you sure to delete User Information ? '+ getIdFromRow + '</b>'));
		});
		$('#myModal').on('show.bs.modal',function(e) {
				$(this).find('.btn-ok').attr('href',
				$(e.relatedTarget).data('href'));
		});
		$('#cancel').click(function() {
			window.history.back();
		});

	});
	
	
	function encodeImageFileAsURL() {

	    var filesSelected = document.getElementById("multipartFile").files;
	    if (filesSelected.length > 0) {
	      var fileToLoad = filesSelected[0];

	      var fileReader = new FileReader();

	      fileReader.onload = function(fileLoadedEvent) {
	        var srcData = fileLoadedEvent.target.result; // <--- data: base64
	        
	        
	        srcData.replace("data:image/jpeg;base64,", "");
	        console.log(srcData);

	        var newImage = document.createElement('img');
	       // $('#profileimg').val(srcData);
	        $('#imgFile').attr('src',srcData);
	        $('#imgFile').show();
	       /* newImage.src = srcData;

	        document.getElementById("imgFile").innerHTML = newImage.outerHTML;*/
	      };
	      $('#imageId').show();
	      fileReader.readAsDataURL(fileToLoad);
	    }
	  }
	
	
	function submitUserForm() {
		$('#userRegisterForm')
				.formValidation(
						{
							icon : {
								valid : 'glyphicon glyphicon-ok',
								invalid : 'glyphicon glyphicon-remove',
								validating : 'glyphicon glyphicon-refresh'
							},
							fields : {
								firstName : {
									validators : {
										notEmpty : {
											message : 'First Name is Required'
										}
									}
								},
								lastName : {
									validators : {
										notEmpty : {
											message : 'Last Name is Required'
										}
									}
								},
								username : {
									message : 'Username is not valid',
									validators : {
										notEmpty : {
											message : 'User Name is Required'
										},
										stringLength : {
											min : 2,
											max : 30,
											message : 'The username must be more than 2 and less than 30 characters long'
										},
										regexp : {
											regexp : /^[a-zA-Z0-9_\.]+$/,
											message : 'The username can only consist of alphabetical, number, dot and underscore'
										}
									}
								},
								password : {
									validators : {
										notEmpty : {
											message : 'Password is Required'
										}
									}
								},
								userType : {
									validators : {
										notEmpty : {
											message : 'User Type is Required'
										}
									}
								},
								email : {
									validators : {
										notEmpty : {
											message : 'Email is Required'
										},
										emailAddress : {
											message : 'Not a valid Email Address'
										}

									}
								},
								phone : {
									validators : {
										notEmpty : {
											message : 'Phone is Required'
										},
										phone : {
											message : 'Not a valid Phone'
										},
										stringLength : {
											min : 0,
											max : 10,
											message : 'Phone Number must be 10 characters long'
										},
										regexp : {
											regexp : /^[0-9\-\(\)\+]+$/,
											message : 'The Phone Number can only consist of number, Brackets and plus symbole'
										}
									}
								},
								address1 : {
									validators : {
										notEmpty : {
											message : 'Address1 is Required'
										},
										stringLength : {
											min : 3,
											max : 30,
											message : 'Address1 must be more than 3 and less than 30 characters long'
										}
									}
								},
								'country.id' : {
									validators : {
										notEmpty : {
											message : 'Country is Required'
										}
									}
								},
								'state.id' : {
									validators : {
										notEmpty : {
											message : 'State is Required'
										}
									}
								},
								zipcode : {
									validators : {
										notEmpty : {
											message : 'ZipCode is Required'
										},
										stringLength : {
											min : 3,
											max : 7,
											message : 'ZipCode must be more than 3 and less than 7 characters long'
										}
									}
								},
								'userRole.id' : {
									validators : {
										notEmpty : {
											message : 'User Role is Required'
										}
									}
								},
								agencyId : {
									validators : {
										notEmpty : {
											message : 'Agency is Required'
										}
									}
								}
							}
						});

		return true;
	};
window.setTimeout(function() {
	$('#updateMsg').fadeTo(500, 0).slideUp(500, function() {
		$(this).remove();
	});
}, 2000);