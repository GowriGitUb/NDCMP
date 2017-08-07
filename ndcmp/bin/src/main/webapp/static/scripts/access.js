/*
 * Access Rights
 * Created by Jeeva
 */

var currenModuleLength = 0;
var tempArray = new Array();
var tempArray1 = new Array();
var newArray;
roleFeatures = getFeaturesByRoleId();
currentRole = getCurrentRole1();


//currentModules = getAdminModules();

//Super Admin

function loadSuperAdminModules(){
	var rId = $('#roleId').val();
	moduleFeatures = getModuleFeatures(rId);
	userRoleName = getUserRoleName(rId);
	for(var c = 0; c < currentRole.length; c++){
		if(currentRole[c].name == 'SUPER_ADMIN'){
			if(userRoleName.name == 'SUPER_ADMIN'){
				currenModuleLength = moduleFeatures.length;
				var temp = Math.round(currenModuleLength/3);
				var modalBody = '<div class="form-horizontal"><fieldset>';
				for (var j = 0; j < temp; j++) {
			
					modalBody += '<div class="control-group">'
							+ '<input class="'
							+ j
							+ '_check" type="checkbox" id="'
							+ j
							+ '_selectall" onchange="selectDeselectAll(this)"/><label for="mapingWorkorderchecklistName" style="text-align: left; font-weight: bold;">&nbsp;&nbsp;'
							+ moduleFeatures[j].module.module
							+ '</label>'
							+ '<div class="controls" id="checkboxes">'
							+ '<ul class="chk-container" style="list-style-type: none;">';
					var features1 = [];
					features1 = moduleFeatures[j].features;
					for (var i = 0; i < features1.length; i++) {
						modalBody += '<li><input class="'
								+ j
								+ '_checkbox" type="checkbox" checked="checked" name="check[]" value="'
								+ features1[i].id
								+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
								+ features1[i].featureName + '</li>';
					}
					modalBody += '</ul></div>';
				}
				
				$('#general1').html(modalBody);
				modalBody += '</fieldset></div>';
				// This is for second column
				var temp1 = Math.round((currenModuleLength-temp)/2);
				var tt = temp + temp1;
				modalBody = '<div class="form-horizontal"><fieldset>';
				for (var j = temp; j < tt; j++) {
						modalBody += '<div class="control-group">'
							+ '<input class="'
							+ j
							+ '_check" type="checkbox" id="'
							+ j
							+ '_selectall" onchange="selectDeselectAll(this)"/><label for="mapingWorkorderchecklistName" style="text-align: left; font-weight: bold;">&nbsp;&nbsp;'
							+ moduleFeatures[j].module.module
							+ '</label>'
							+ '<div class="controls" id="checkboxes">'
							+ '<ul class="chk-container" style="list-style-type: none;">';
						//modalBody += loadFeatures(roleId,j,tempArray[j].module.id,tempArray[j].features);
						var features1 = [];
						features1 = moduleFeatures[j].features;
						for (var i = 0; i < features1.length; i++) {
							modalBody += '<li><input class="'
									+ j
									+ '_checkbox" type="checkbox" checked="checked" name="check[]" value="'
									+ features1[i].id
									+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
									+ features1[i].featureName + '</li>';
						}
						modalBody += '</ul></div>';
				}
				
				$('#general2').html(modalBody);
				modalBody += '</fieldset></div>';
				
				// Third Column
				var temp3 = (temp+temp1);
				modalBody = '<div class="form-horizontal"><fieldset>';
				for (var j = temp3; j < currenModuleLength; j++) {
							modalBody += '<div class="control-group">'
								+ '<input class="'
								+ j
								+ '_check" type="checkbox" id="'
								+ j
								+ '_selectall" onchange="selectDeselectAll(this)"/><label for="mapingWorkorderchecklistName" style="text-align: left; font-weight: bold;">&nbsp;&nbsp;'
								+ moduleFeatures[j].module.module
								+ '</label>'
								+ '<div class="controls" id="checkboxes">'
								+ '<ul class="chk-container" style="list-style-type: none;">';
							var features1 = [];
							features1 = moduleFeatures[j].features;
							for (var i = 0; i < features1.length; i++) {
								modalBody += '<li><input class="'
										+ j
										+ '_checkbox" type="checkbox" checked="checked" name="check[]" value="'
										+ features1[i].id
										+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
										+ features1[i].featureName + '</li>';
							}
							modalBody += '</ul></div>';
				}
				
				$('#general3').html(modalBody);
				modalBody += '</fieldset></div>';
				checkSelectAll();
			}else if(userRoleName.name == 'PROJECT_ADMIN'){
				currenModuleLength = moduleFeatures.length;
				var temp = Math.round(currenModuleLength/3);
				var modalBody = '<div class="form-horizontal"><fieldset>';
				for (var j = 0; j < temp; j++) {
					if(moduleFeatures[j].module.module == 'Project' || moduleFeatures[j].module.module == 'Dashboard'){
						modalBody += '<div class="control-group">'
							+ '<input class="'
							+ j
							+ '_check" type="checkbox" id="'
							+ j
							+ '_selectall" onchange="selectDeselectAll(this)"/><label for="mapingWorkorderchecklistName" style="text-align: left; font-weight: bold;">&nbsp;&nbsp;'
							+ moduleFeatures[j].module.module
							+ '</label>'
							+ '<div class="controls" id="checkboxes">'
							+ '<ul class="chk-container" style="list-style-type: none;">';
						var firstLoopAllFeatures = [];
						var getfirstLoopFeaturesByRoleIdAndModuleId = [];
						var copiedFeatures = [];
						getfirstLoopFeaturesByRoleIdAndModuleId = getFeaturesByRoleidAndmoduleId(userRoleName.id,moduleFeatures[j].module.id);
						for(var i =0;i<getfirstLoopFeaturesByRoleIdAndModuleId.length;i++) {
							copiedFeatures.push(getfirstLoopFeaturesByRoleIdAndModuleId[i]);
						}
						var copyFeaturesFromExistingArray = [];
						copyFeaturesFromExistingArray = getfirstLoopFeaturesByRoleIdAndModuleId;
						firstLoopAllFeatures = moduleFeatures[j].features;
						featuresLength = getfirstLoopFeaturesByRoleIdAndModuleId.length;
						var firstLoopIteration = 0;
						for (var i = 0; i < firstLoopAllFeatures.length; i++) {
							if(getfirstLoopFeaturesByRoleIdAndModuleId.length > 0){
								if(firstLoopAllFeatures[i].featureName == copyFeaturesFromExistingArray[firstLoopIteration].feature){
									modalBody += '<li><input class="'
										+ j
										+ '_checkbox" type="checkbox" checked="checked" name="check[]" value="'
										+ firstLoopAllFeatures[i].id
										+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
										+ firstLoopAllFeatures[i].featureName + '</li>';
									firstLoopIteration++;
									if(featuresLength ==1){
										var g = 0;
										if(featuresLength <= firstLoopIteration){
											copyFeaturesFromExistingArray[firstLoopIteration] = copiedFeatures[g];
										}
									}else if(featuresLength == 2){
										var kk = i;
										if(i == 0){
											kk++;
											copyFeaturesFromExistingArray[firstLoopIteration] = copiedFeatures[kk];
										}else if(i == 1){
											copyFeaturesFromExistingArray[firstLoopIteration] = copiedFeatures[kk];
										}else{
											kk--;
											copyFeaturesFromExistingArray[firstLoopIteration] = copiedFeatures[kk];
										}
									}else if(featuresLength == 3) {
										var kk = i;
										if(i >= 2){
											kk--;
											copyFeaturesFromExistingArray[firstLoopIteration] = copiedFeatures[i];
										}else{
											copyFeaturesFromExistingArray[firstLoopIteration] = copiedFeatures[firstLoopIteration];
										}
									}else {
										if(featuresLength <= firstLoopIteration){
											copyFeaturesFromExistingArray[firstLoopIteration] = copiedFeatures[i];
										}
									}
								}else{
									if(firstLoopAllFeatures[i].featureName == 'View Project' || firstLoopAllFeatures[i].featureName == 'Configure Project'||
											firstLoopAllFeatures[i].featureName == 'View Dashboard'){
										modalBody += '<li><input class="'
										+ j
										+ '_checkbox" type="checkbox" name="check[]" value="'
										+ firstLoopAllFeatures[i].id
										+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
										+ firstLoopAllFeatures[i].featureName + '</li>';
									}
									
									firstLoopIteration++;
								if(featuresLength ==1){
									var g = 0;
									if(featuresLength <= firstLoopIteration){
										copyFeaturesFromExistingArray[firstLoopIteration] = copiedFeatures[g];
									}
								}else if(featuresLength == 2){
									var kk = i;
									if(i >= 1){
										if(firstLoopIteration > 2){
											if(i == 1){
												copyFeaturesFromExistingArray[firstLoopIteration] = copiedFeatures[kk];
											}else if(i == 2){
												kk = i;
												kk--;
												copyFeaturesFromExistingArray[firstLoopIteration] = copiedFeatures[kk];
											}else{
												kk = featuresLength;
												kk--;
												copyFeaturesFromExistingArray[firstLoopIteration] = copiedFeatures[kk];
											}
											
										}else{
											if(firstLoopIteration == 2){
												copyFeaturesFromExistingArray[firstLoopIteration] = copiedFeatures[kk];
											}else{
												kk--;
												copyFeaturesFromExistingArray[firstLoopIteration] = copiedFeatures[kk];
											}
										}
									}else{
										copyFeaturesFromExistingArray[firstLoopIteration] = copiedFeatures[kk];
									}
								}else if(featuresLength == 3) {
									if(i ==0){
										firstLoopIteration--;
									copyFeaturesFromExistingArray[firstLoopIteration] = copiedFeatures[i];
								}else{
									copyFeaturesFromExistingArray[firstLoopIteration] = copiedFeatures[i];
								}
									
								}else {
									if(featuresLength <= k){
										copyFeaturesFromExistingArray[firstLoopIteration] = copiedFeatures[i];
									}
								}
								}
							}else{
								if(firstLoopAllFeatures[i].featureName == 'View Project' || 
										firstLoopAllFeatures[i].featureName == 'Configure Project' ||
										firstLoopAllFeatures[i].featureName == 'View Dashboard'){
									modalBody += '<li><input class="'
									+ j
									+ '_checkbox" type="checkbox" name="check[]" value="'
									+ firstLoopAllFeatures[i].id
									+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
									+ firstLoopAllFeatures[i].featureName + '</li>';
								}
							}
						}
					modalBody += '</ul></div>';
					}else{
						modalBody += '<div class="control-group">'
							+ '<input class="'
							+ j
							+ '_check" type="checkbox" id="'
							+ j
							+ '_selectall" onchange="selectDeselectAll(this)"/><label for="mapingWorkorderchecklistName" style="text-align: left; font-weight: bold;">&nbsp;&nbsp;'
							+ moduleFeatures[j].module.module
							+ '</label>'
							+ '<div class="controls" id="checkboxes">'
							+ '<ul class="chk-container" style="list-style-type: none;">';
							var features1 = [];
							features1 = moduleFeatures[j].features;
							for (var i = 0; i < features1.length; i++) {
								modalBody += '<li><input class="'
										+ j
										+ '_checkbox" type="checkbox" name="check[]" value="'
										+ features1[i].id
										+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
										+ features1[i].featureName + '</li>';
							}
					modalBody += '</ul></div>';
					}
					
					/*modalBody += '<div class="control-group">'
						+ '<input class="'
						+ j
						+ '_check" type="checkbox" id="'
						+ j
						+ '_selectall" onchange="selectDeselectAll(this)"/><label for="mapingWorkorderchecklistName" style="text-align: left; font-weight: bold;">&nbsp;&nbsp;'
						+ moduleFeatures[j].module.module
						+ '</label>'
						+ '<div class="controls" id="checkboxes">'
						+ '<ul class="chk-container" style="list-style-type: none;">';
					//modalBody += loadFeatures(roleId,j,tempArray[j].module.id,tempArray[j].features);
					var firstLoopAllFeatures = [];
					var getFirstLoopFeaturesByRoleIdAndModuleId = [];
					var firstLoopcopiedFeatures = [];
					getFirstLoopFeaturesByRoleIdAndModuleId = getFeaturesByRoleidAndmoduleId(userRoleName.id,moduleFeatures[j].module.id);
					for(var i =0;i<getFirstLoopFeaturesByRoleIdAndModuleId.length;i++) {
						firstLoopcopiedFeatures.push(getFirstLoopFeaturesByRoleIdAndModuleId[i]);
					}
					var firstLoopcopyFeaturesFromExistingArray = [];
					firstLoopcopyFeaturesFromExistingArray = getFirstLoopFeaturesByRoleIdAndModuleId;
					firstLoopAllFeatures = moduleFeatures[j].features;
					firstFeaturesLength = getFirstLoopFeaturesByRoleIdAndModuleId.length;
					var firstLoopIteration = 0;
					for (var i = 0; i < firstLoopAllFeatures.length; i++) {
						if(getFirstLoopFeaturesByRoleIdAndModuleId.length > 0){
							if(firstLoopAllFeatures[i].featureName == firstLoopcopyFeaturesFromExistingArray[firstLoopIteration].feature){
								modalBody += '<li><input class="'
									+ j
									+ '_checkbox" type="checkbox" checked="checked" name="check[]" value="'
									+ firstLoopAllFeatures[i].id
									+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
									+ firstLoopAllFeatures[i].featureName + '</li>';
								firstLoopIteration++;
								if(firstFeaturesLength ==1){
									var g = 0;
									if(firstFeaturesLength <= firstLoopIteration){
										firstLoopcopyFeaturesFromExistingArray[firstLoopIteration] = firstLoopcopiedFeatures[g];
									}
								}else{
									if(firstFeaturesLength <= secondLoopIteration){
										firstLoopcopyFeaturesFromExistingArray[firstLoopIteration] = firstLoopcopiedFeatures[i];
									}
								}
							}else{
									modalBody += '<li><input class="'
									+ j
									+ '_checkbox" type="checkbox" name="check[]" value="'
									+ firstLoopAllFeatures[i].id
									+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
									+ firstLoopAllFeatures[i].featureName + '</li>';
								
									firstLoopIteration++;
									if(firstFeaturesLength ==1){
										var g = 0;
										if(featuresLength <= secondLoopIteration){
											firstLoopcopyFeaturesFromExistingArray[firstLoopIteration] = firstLoopcopiedFeatures[g];
										}
									}else{
										if(firstFeaturesLength <= secondLoopIteration){
											firstLoopcopyFeaturesFromExistingArray[firstLoopIteration] = firstLoopcopiedFeatures[i];
										}
									}
							}
						}else{
							modalBody += '<li><input class="'
								+ j
								+ '_checkbox" type="checkbox" name="check[]" value="'
								+ firstLoopAllFeatures[i].id
								+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
								+ firstLoopAllFeatures[i].featureName + '</li>';
						}
					}
					modalBody += '</ul></div>';*/
				}
				
				$('#general1').html(modalBody);
				modalBody += '</fieldset></div>';
				// This is for second column
				var temp1 = Math.round((currenModuleLength-temp)/2);
				var tt = temp + temp1;
				modalBody = '<div class="form-horizontal"><fieldset>';
				for (var j = temp; j < tt; j++) {
						modalBody += '<div class="control-group">'
							+ '<input class="'
							+ j
							+ '_check" type="checkbox" id="'
							+ j
							+ '_selectall" onchange="selectDeselectAll(this)"/><label for="mapingWorkorderchecklistName" style="text-align: left; font-weight: bold;">&nbsp;&nbsp;'
							+ moduleFeatures[j].module.module
							+ '</label>'
							+ '<div class="controls" id="checkboxes">'
							+ '<ul class="chk-container" style="list-style-type: none;">';
						var secondLoopAllFeatures = [];
						var getsecondLoopFeaturesByRoleIdAndModuleId = [];
						var copiedFeatures = [];
						getsecondLoopFeaturesByRoleIdAndModuleId = getFeaturesByRoleidAndmoduleId(userRoleName.id,moduleFeatures[j].module.id);
						for(var i =0;i<getsecondLoopFeaturesByRoleIdAndModuleId.length;i++) {
							copiedFeatures.push(getsecondLoopFeaturesByRoleIdAndModuleId[i]);
						}
						var copyFeaturesFromExistingArray = [];
						copyFeaturesFromExistingArray = getsecondLoopFeaturesByRoleIdAndModuleId;
						secondLoopAllFeatures = moduleFeatures[j].features;
						featuresLength = getsecondLoopFeaturesByRoleIdAndModuleId.length;
						var secondLoopIteration = 0;
						for (var i = 0; i < secondLoopAllFeatures.length; i++) {
							if(getsecondLoopFeaturesByRoleIdAndModuleId.length > 0){
								if(secondLoopAllFeatures[i].featureName == copyFeaturesFromExistingArray[secondLoopIteration].feature){
									modalBody += '<li><input class="'
										+ j
										+ '_checkbox" type="checkbox" checked="checked" name="check[]" value="'
										+ secondLoopAllFeatures[i].id
										+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
										+ secondLoopAllFeatures[i].featureName + '</li>';
									secondLoopIteration++;
									
									if(featuresLength ==1){
										var g = 0;
										if(featuresLength <= secondLoopIteration){
											copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[g];
										}
									}else if(featuresLength == 2){
										var kk = i;
										if(i == 0){
											kk++;
											copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
										}else if(i == 1){
											copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
										}else{
											kk--;
											copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
										}
									}else if(featuresLength == 3) {
										var kk = i;
										if(i >= 2){
											kk--;
											copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[i];
										}else{
											copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[secondLoopIteration];
										}
									}else {
										if(featuresLength <= secondLoopIteration){
											copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[i];
										}
									}
								}else{
									modalBody += '<li><input class="'
									+ j
									+ '_checkbox" type="checkbox" name="check[]" value="'
									+ secondLoopAllFeatures[i].id
									+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
									+ secondLoopAllFeatures[i].featureName + '</li>';
									
									secondLoopIteration++;
									
								if(featuresLength ==1){
									var g = 0;
									if(featuresLength <= secondLoopIteration){
										copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[g];
									}
								}else if(featuresLength == 2){
									var kk = i;
									if(i >= 1){
										if(secondLoopIteration > 2){
											if(i == 1){
												copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
											}else if(i == 2){
												kk = i;
												kk--;
												copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
											}else{
												kk = featuresLength;
												kk--;
												copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
											}
											
										}else{
											if(secondLoopIteration == 2){
												copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
											}else{
												kk--;
												copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
											}
										}
									}else{
										copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
									}
								}else if(featuresLength == 3) {
									if(i ==0){
									secondLoopIteration--;
									copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[i];
								}else{
									copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[i];
								}
									
								}else {
									if(featuresLength <= k){
										copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[i];
									}
								}
								}
							}else{
								modalBody += '<li><input class="'
									+ j
									+ '_checkbox" type="checkbox" name="check[]" value="'
									+ secondLoopAllFeatures[i].id
									+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
									+ secondLoopAllFeatures[i].featureName + '</li>';
							}
						}
					modalBody += '</ul></div>';
				}
				
				$('#general2').html(modalBody);
				modalBody += '</fieldset></div>';
				
				// Third Column
				var temp3 = (temp+temp1);
				modalBody = '<div class="form-horizontal"><fieldset>';
				for (var j = temp3; j < currenModuleLength; j++) {
							modalBody += '<div class="control-group">'
								+ '<input class="'
								+ j
								+ '_check" type="checkbox" id="'
								+ j
								+ '_selectall" onchange="selectDeselectAll(this)"/><label for="mapingWorkorderchecklistName" style="text-align: left; font-weight: bold;">&nbsp;&nbsp;'
								+ moduleFeatures[j].module.module
								+ '</label>'
								+ '<div class="controls" id="checkboxes">'
								+ '<ul class="chk-container" style="list-style-type: none;">';
							var thirdLoopAllFeatures = [];
							var getThirdLoopFeaturesByRoleIdAndModuleId = [];
							var thirdLoopcopiedFeatures = [];
							getThirdLoopFeaturesByRoleIdAndModuleId = getFeaturesByRoleidAndmoduleId(userRoleName.id,moduleFeatures[j].module.id);
							for(var i =0;i<getThirdLoopFeaturesByRoleIdAndModuleId.length;i++) {
								thirdLoopcopiedFeatures.push(getThirdLoopFeaturesByRoleIdAndModuleId[i]);
							}
							var thirdLoopcopyFeaturesFromExistingArray = [];
							thirdLoopcopyFeaturesFromExistingArray = getThirdLoopFeaturesByRoleIdAndModuleId;
							thirdLoopAllFeatures = moduleFeatures[j].features;
							thirdFeaturesLength = getThirdLoopFeaturesByRoleIdAndModuleId.length;
							var thirdLoopIteration = 0;
							for (var i = 0; i < thirdLoopAllFeatures.length; i++) {
								if(getThirdLoopFeaturesByRoleIdAndModuleId.length > 0){
									if(thirdLoopAllFeatures[i].featureName == thirdLoopcopyFeaturesFromExistingArray[thirdLoopIteration].feature){
										modalBody += '<li><input class="'
											+ j
											+ '_checkbox" type="checkbox" checked="checked" name="check[]" value="'
											+ thirdLoopAllFeatures[i].id
											+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
											+ thirdLoopAllFeatures[i].featureName + '</li>';
										thirdLoopIteration++;
										if(thirdFeaturesLength ==1){
											var g = 0;
											if(thirdFeaturesLength <= firstLoopIteration){
												thirdLoopcopyFeaturesFromExistingArray[thirdLoopIteration] = thirdLoopcopiedFeatures[g];
											}
										}else{
											if(thirdFeaturesLength <= secondLoopIteration){
												thirdLoopcopyFeaturesFromExistingArray[thirdLoopIteration] = thirdLoopcopiedFeatures[i];
											}
										}
									}else{
											modalBody += '<li><input class="'
											+ j
											+ '_checkbox" type="checkbox" name="check[]" value="'
											+ thirdLoopAllFeatures[i].id
											+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
											+ thirdLoopAllFeatures[i].featureName + '</li>';
										
											thirdLoopIteration++;
											if(thirdFeaturesLength ==1){
												var g = 0;
												if(thirdFeaturesLength <= secondLoopIteration){
													thirdLoopcopyFeaturesFromExistingArray[thirdLoopIteration] = thirdLoopcopiedFeatures[g];
												}
											}else{
												if(thirdFeaturesLength <= secondLoopIteration){
													thirdLoopcopyFeaturesFromExistingArray[thirdLoopIteration] = thirdLoopcopiedFeatures[i];
												}
											}
									}
								}else{
									modalBody += '<li><input class="'
										+ j
										+ '_checkbox" type="checkbox" name="check[]" value="'
										+ thirdLoopAllFeatures[i].id
										+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
										+ thirdLoopAllFeatures[i].featureName + '</li>';
								}
							}
							modalBody += '</ul></div>';
				}
				
				$('#general3').html(modalBody);
				modalBody += '</fieldset></div>';
				checkSelectAll();
			}else if(userRoleName.name == 'PROJECT_PLANNER'){
				currenModuleLength = moduleFeatures.length;
				var temp = Math.round(currenModuleLength/3);
				var modalBody = '<div class="form-horizontal"><fieldset>';
				for (var j = 0; j < temp; j++) {
					if(moduleFeatures[j].module.module == 'Project' || moduleFeatures[j].module.module == 'Dashboard'){
						modalBody += '<div class="control-group">'
							+ '<input class="'
							+ j
							+ '_check" type="checkbox" id="'
							+ j
							+ '_selectall" onchange="selectDeselectAll(this)"/><label for="mapingWorkorderchecklistName" style="text-align: left; font-weight: bold;">&nbsp;&nbsp;'
							+ moduleFeatures[j].module.module
							+ '</label>'
							+ '<div class="controls" id="checkboxes">'
							+ '<ul class="chk-container" style="list-style-type: none;">';
						var firstLoopAllFeatures = [];
						var getfirstLoopFeaturesByRoleIdAndModuleId = [];
						var copiedFeatures = [];
						getfirstLoopFeaturesByRoleIdAndModuleId = getFeaturesByRoleidAndmoduleId(userRoleName.id,moduleFeatures[j].module.id);
						for(var i =0;i<getfirstLoopFeaturesByRoleIdAndModuleId.length;i++) {
							copiedFeatures.push(getfirstLoopFeaturesByRoleIdAndModuleId[i]);
						}
						var copyFeaturesFromExistingArray = [];
						copyFeaturesFromExistingArray = getfirstLoopFeaturesByRoleIdAndModuleId;
						firstLoopAllFeatures = moduleFeatures[j].features;
						featuresLength = getfirstLoopFeaturesByRoleIdAndModuleId.length;
						var firstLoopIteration = 0;
						for (var i = 0; i < firstLoopAllFeatures.length; i++) {
							if(getfirstLoopFeaturesByRoleIdAndModuleId.length > 0){
								if(firstLoopAllFeatures[i].featureName == copyFeaturesFromExistingArray[firstLoopIteration].feature){
									modalBody += '<li><input class="'
										+ j
										+ '_checkbox" type="checkbox" checked="checked" name="check[]" value="'
										+ firstLoopAllFeatures[i].id
										+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
										+ firstLoopAllFeatures[i].featureName + '</li>';
									firstLoopIteration++;
									if(featuresLength ==1){
										var g = 0;
										if(featuresLength <= firstLoopIteration){
											copyFeaturesFromExistingArray[firstLoopIteration] = copiedFeatures[g];
										}
									}else if(featuresLength == 2){
										var kk = i;
										if(i == 0){
											kk++;
											copyFeaturesFromExistingArray[firstLoopIteration] = copiedFeatures[kk];
										}else if(i == 1){
											copyFeaturesFromExistingArray[firstLoopIteration] = copiedFeatures[kk];
										}else{
											kk--;
											copyFeaturesFromExistingArray[firstLoopIteration] = copiedFeatures[kk];
										}
									}else if(featuresLength == 3) {
										var kk = i;
										if(i >= 2){
											kk--;
											copyFeaturesFromExistingArray[firstLoopIteration] = copiedFeatures[i];
										}else{
											copyFeaturesFromExistingArray[firstLoopIteration] = copiedFeatures[firstLoopIteration];
										}
									}else {
										if(featuresLength <= firstLoopIteration){
											copyFeaturesFromExistingArray[firstLoopIteration] = copiedFeatures[i];
										}
									}
								}else{
									if(firstLoopAllFeatures[i].featureName == 'View Project' || firstLoopAllFeatures[i].featureName == 'Plan Project'||
											firstLoopAllFeatures[i].featureName == 'View Dashboard'){
										modalBody += '<li><input class="'
										+ j
										+ '_checkbox" type="checkbox" name="check[]" value="'
										+ firstLoopAllFeatures[i].id
										+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
										+ firstLoopAllFeatures[i].featureName + '</li>';
									}
									
									firstLoopIteration++;
								if(featuresLength ==1){
									var g = 0;
									if(featuresLength <= firstLoopIteration){
										copyFeaturesFromExistingArray[firstLoopIteration] = copiedFeatures[g];
									}
								}else if(featuresLength == 2){
									var kk = i;
									if(i >= 1){
										if(firstLoopIteration > 2){
											if(i == 1){
												copyFeaturesFromExistingArray[firstLoopIteration] = copiedFeatures[kk];
											}else if(i == 2){
												kk = i;
												kk--;
												copyFeaturesFromExistingArray[firstLoopIteration] = copiedFeatures[kk];
											}else{
												kk = featuresLength;
												kk--;
												copyFeaturesFromExistingArray[firstLoopIteration] = copiedFeatures[kk];
											}
											
										}else{
											if(firstLoopIteration == 2){
												copyFeaturesFromExistingArray[firstLoopIteration] = copiedFeatures[kk];
											}else{
												kk--;
												copyFeaturesFromExistingArray[firstLoopIteration] = copiedFeatures[kk];
											}
										}
									}else{
										copyFeaturesFromExistingArray[firstLoopIteration] = copiedFeatures[kk];
									}
								}else if(featuresLength == 3) {
									if(i ==0){
										firstLoopIteration--;
									copyFeaturesFromExistingArray[firstLoopIteration] = copiedFeatures[i];
								}else{
									copyFeaturesFromExistingArray[firstLoopIteration] = copiedFeatures[i];
								}
									
								}else {
									if(featuresLength <= k){
										copyFeaturesFromExistingArray[firstLoopIteration] = copiedFeatures[i];
									}
								}
								}
							}else{
								if(firstLoopAllFeatures[i].featureName == 'View Project' || 
										firstLoopAllFeatures[i].featureName == 'Plan Project' ||
										firstLoopAllFeatures[i].featureName == 'View Dashboard'){
									modalBody += '<li><input class="'
									+ j
									+ '_checkbox" type="checkbox" name="check[]" value="'
									+ firstLoopAllFeatures[i].id
									+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
									+ firstLoopAllFeatures[i].featureName + '</li>';
								}
							}
						}
					modalBody += '</ul></div>';
					}else{
						modalBody += '<div class="control-group">'
							+ '<input class="'
							+ j
							+ '_check" type="checkbox" id="'
							+ j
							+ '_selectall" onchange="selectDeselectAll(this)"/><label for="mapingWorkorderchecklistName" style="text-align: left; font-weight: bold;">&nbsp;&nbsp;'
							+ moduleFeatures[j].module.module
							+ '</label>'
							+ '<div class="controls" id="checkboxes">'
							+ '<ul class="chk-container" style="list-style-type: none;">';
							var features1 = [];
							features1 = moduleFeatures[j].features;
							for (var i = 0; i < features1.length; i++) {
								modalBody += '<li><input class="'
										+ j
										+ '_checkbox" type="checkbox" name="check[]" value="'
										+ features1[i].id
										+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
										+ features1[i].featureName + '</li>';
							}
					modalBody += '</ul></div>';
					}
					/*modalBody += '<div class="control-group">'
						+ '<input class="'
						+ j
						+ '_check" type="checkbox" id="'
						+ j
						+ '_selectall" onchange="selectDeselectAll(this)"/><label for="mapingWorkorderchecklistName" style="text-align: left; font-weight: bold;">&nbsp;&nbsp;'
						+ moduleFeatures[j].module.module
						+ '</label>'
						+ '<div class="controls" id="checkboxes">'
						+ '<ul class="chk-container" style="list-style-type: none;">';
					//modalBody += loadFeatures(roleId,j,tempArray[j].module.id,tempArray[j].features);
					var features1 = [];
					features1 = moduleFeatures[j].features;
					var firstLoopAllFeatures = [];
					var getFirstLoopFeaturesByRoleIdAndModuleId = [];
					var firstLoopcopiedFeatures = [];
					getFirstLoopFeaturesByRoleIdAndModuleId = getFeaturesByRoleidAndmoduleId(userRoleName.id,moduleFeatures[j].module.id);
					for(var i =0;i<getFirstLoopFeaturesByRoleIdAndModuleId.length;i++) {
						firstLoopcopiedFeatures.push(getFirstLoopFeaturesByRoleIdAndModuleId[i]);
					}
					var firstLoopcopyFeaturesFromExistingArray = [];
					firstLoopcopyFeaturesFromExistingArray = getFirstLoopFeaturesByRoleIdAndModuleId;
					firstLoopAllFeatures = moduleFeatures[j].features;
					firstFeaturesLength = getFirstLoopFeaturesByRoleIdAndModuleId.length;
					var firstLoopIteration = 0;
					for (var i = 0; i < firstLoopAllFeatures.length; i++) {
						if(getFirstLoopFeaturesByRoleIdAndModuleId.length > 0){
							if(firstLoopAllFeatures[i].featureName == firstLoopcopyFeaturesFromExistingArray[firstLoopIteration].feature){
								modalBody += '<li><input class="'
									+ j
									+ '_checkbox" type="checkbox" checked="checked" name="check[]" value="'
									+ firstLoopAllFeatures[i].id
									+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
									+ firstLoopAllFeatures[i].featureName + '</li>';
								firstLoopIteration++;
								if(firstFeaturesLength ==1){
									var g = 0;
									if(firstFeaturesLength <= firstLoopIteration){
										firstLoopcopyFeaturesFromExistingArray[firstLoopIteration] = firstLoopcopiedFeatures[g];
									}
								}else{
									if(firstFeaturesLength <= secondLoopIteration){
										firstLoopcopyFeaturesFromExistingArray[firstLoopIteration] = firstLoopcopiedFeatures[i];
									}
								}
							}else{
									modalBody += '<li><input class="'
									+ j
									+ '_checkbox" type="checkbox" name="check[]" value="'
									+ firstLoopAllFeatures[i].id
									+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
									+ firstLoopAllFeatures[i].featureName + '</li>';
								
									firstLoopIteration++;
									if(firstFeaturesLength ==1){
										var g = 0;
										if(featuresLength <= secondLoopIteration){
											firstLoopcopyFeaturesFromExistingArray[firstLoopIteration] = firstLoopcopiedFeatures[g];
										}
									}else{
										if(firstFeaturesLength <= secondLoopIteration){
											firstLoopcopyFeaturesFromExistingArray[firstLoopIteration] = firstLoopcopiedFeatures[i];
										}
									}
							}
						}else{
							modalBody += '<li><input class="'
								+ j
								+ '_checkbox" type="checkbox" name="check[]" value="'
								+ firstLoopAllFeatures[i].id
								+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
								+ firstLoopAllFeatures[i].featureName + '</li>';
						}
					}*/
					//modalBody += '</ul></div>';
				}
				
				$('#general1').html(modalBody);
				modalBody += '</fieldset></div>';
				// This is for second column
				var temp1 = Math.round((currenModuleLength-temp)/2);
				var tt = temp + temp1;
				modalBody = '<div class="form-horizontal"><fieldset>';
				for (var j = temp; j < tt; j++) {
					modalBody += '<div class="control-group">'
						+ '<input class="'
						+ j
						+ '_check" type="checkbox" id="'
						+ j
						+ '_selectall" onchange="selectDeselectAll(this)"/><label for="mapingWorkorderchecklistName" style="text-align: left; font-weight: bold;">&nbsp;&nbsp;'
						+ moduleFeatures[j].module.module
						+ '</label>'
						+ '<div class="controls" id="checkboxes">'
						+ '<ul class="chk-container" style="list-style-type: none;">';
					var secondLoopAllFeatures = [];
					var getsecondLoopFeaturesByRoleIdAndModuleId = [];
					var copiedFeatures = [];
					getsecondLoopFeaturesByRoleIdAndModuleId = getFeaturesByRoleidAndmoduleId(userRoleName.id,moduleFeatures[j].module.id);
					for(var i =0;i<getsecondLoopFeaturesByRoleIdAndModuleId.length;i++) {
						copiedFeatures.push(getsecondLoopFeaturesByRoleIdAndModuleId[i]);
					}
					var copyFeaturesFromExistingArray = [];
					copyFeaturesFromExistingArray = getsecondLoopFeaturesByRoleIdAndModuleId;
					secondLoopAllFeatures = moduleFeatures[j].features;
					featuresLength = getsecondLoopFeaturesByRoleIdAndModuleId.length;
					var secondLoopIteration = 0;
					for (var i = 0; i < secondLoopAllFeatures.length; i++) {
						if(getsecondLoopFeaturesByRoleIdAndModuleId.length > 0){
							if(secondLoopAllFeatures[i].featureName == copyFeaturesFromExistingArray[secondLoopIteration].feature){
								modalBody += '<li><input class="'
									+ j
									+ '_checkbox" type="checkbox" checked="checked" name="check[]" value="'
									+ secondLoopAllFeatures[i].id
									+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
									+ secondLoopAllFeatures[i].featureName + '</li>';
								secondLoopIteration++;
								
								if(featuresLength ==1){
									var g = 0;
									if(featuresLength <= secondLoopIteration){
										copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[g];
									}
								}else if(featuresLength == 2){
									var kk = i;
									if(i == 0){
										kk++;
										copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
									}else if(i == 1){
										copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
									}else{
										kk--;
										copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
									}
								}else if(featuresLength == 3) {
									var kk = i;
									if(i >= 2){
										kk--;
										copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[i];
									}else{
										copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[secondLoopIteration];
									}
								}else {
									if(featuresLength <= secondLoopIteration){
										copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[i];
									}
								}
							}else{
								modalBody += '<li><input class="'
								+ j
								+ '_checkbox" type="checkbox" name="check[]" value="'
								+ secondLoopAllFeatures[i].id
								+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
								+ secondLoopAllFeatures[i].featureName + '</li>';
								
								secondLoopIteration++;
								
							if(featuresLength ==1){
								var g = 0;
								if(featuresLength <= secondLoopIteration){
									copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[g];
								}
							}else if(featuresLength == 2){
								var kk = i;
								if(i >= 1){
									if(secondLoopIteration > 2){
										if(i == 1){
											copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
										}else if(i == 2){
											kk = i;
											kk--;
											copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
										}else{
											kk = featuresLength;
											kk--;
											copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
										}
										
									}else{
										if(secondLoopIteration == 2){
											copyFeaturesFromExistingArray[secondLoopIteration] = getsecondLoopFeaturesByRoleIdAndModuleId[kk];
										}else{
											kk--;
											copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
										}
									}
								}else{
									copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
								}
							}else if(featuresLength == 3) {
								if(i ==0){
								secondLoopIteration--;
								copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[i];
							}else{
								copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[i];
							}
								
							}else {
								if(featuresLength <= k){
									copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[i];
								}
							}
							}
						}else{
							modalBody += '<li><input class="'
								+ j
								+ '_checkbox" type="checkbox" name="check[]" value="'
								+ secondLoopAllFeatures[i].id
								+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
								+ secondLoopAllFeatures[i].featureName + '</li>';
						}
					}
				modalBody += '</ul></div>';
					/*if(moduleFeatures[j].module.module == 'Project' || moduleFeatures[j].module.module == 'Reporting Period'){
						modalBody += '<div class="control-group">'
							+ '<input class="'
							+ j
							+ '_check" type="checkbox" id="'
							+ j
							+ '_selectall" onchange="selectDeselectAll(this)"/><label for="mapingWorkorderchecklistName" style="text-align: left; font-weight: bold;">&nbsp;&nbsp;'
							+ moduleFeatures[j].module.module
							+ '</label>'
							+ '<div class="controls" id="checkboxes">'
							+ '<ul class="chk-container" style="list-style-type: none;">';
					var secondLoopAllFeatures = [];
					var getsecondLoopFeaturesByRoleIdAndModuleId = [];
					var copiedFeatures = [];
					getsecondLoopFeaturesByRoleIdAndModuleId = getFeaturesByRoleidAndmoduleId(userRoleName.id,moduleFeatures[j].module.id);
					for(var i =0;i<getsecondLoopFeaturesByRoleIdAndModuleId.length;i++) {
						copiedFeatures.push(getsecondLoopFeaturesByRoleIdAndModuleId[i]);
					}
					var copyFeaturesFromExistingArray = [];
					copyFeaturesFromExistingArray = getsecondLoopFeaturesByRoleIdAndModuleId;
					secondLoopAllFeatures = moduleFeatures[j].features;
					featuresLength = getsecondLoopFeaturesByRoleIdAndModuleId.length;
					var secondLoopIteration = 0;
					for (var i = 0; i < secondLoopAllFeatures.length; i++) {
						if(getsecondLoopFeaturesByRoleIdAndModuleId.length > 0){
							if(secondLoopAllFeatures[i].featureName == copyFeaturesFromExistingArray[secondLoopIteration].feature){
								modalBody += '<li><input class="'
									+ j
									+ '_checkbox" type="checkbox" checked="checked" name="check[]" value="'
									+ secondLoopAllFeatures[i].id
									+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
									+ secondLoopAllFeatures[i].featureName + '</li>';
								secondLoopIteration++;
								if(featuresLength ==1){
									var g = 0;
									if(featuresLength <= secondLoopIteration){
										copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[g];
									}
								}else{
									var kk = i;
									if(i == 0){
										kk++;
										copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
									}else if(i == 1){
										copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
									}else{
										kk--;
										copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
									}
								}
								if(featuresLength ==1){
									var g = 0;
									if(featuresLength <= secondLoopIteration){
										copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[g];
									}
								}else if(featuresLength == 2){
									var kk = i;
									if(i == 0){
										kk++;
										copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
									}else if(i == 1){
										copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
									}else{
										kk--;
										copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
									}
								}else if(featuresLength == 3) {
									var kk = i;
									if(i >= 2){
										kk--;
										copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[i];
									}else{
										copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[secondLoopIteration];
									}
								}else {
									if(featuresLength <= secondLoopIteration){
										copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[i];
									}
								}
							}else{
								if(secondLoopAllFeatures[i].featureName == 'Plan Project' || 
									secondLoopAllFeatures[i].featureName == 'View Project' ||
									secondLoopAllFeatures[i].featureName == 'Add Reporting Period' || 
									secondLoopAllFeatures[i].featureName == 'Edit Reporting Period' || 
									secondLoopAllFeatures[i].featureName == 'Activate or Deactivate Reporting Period' || 
									secondLoopAllFeatures[i].featureName == 'View Reporting Period'){
									modalBody += '<li><input class="'
									+ j
									+ '_checkbox" type="checkbox" name="check[]" value="'
									+ secondLoopAllFeatures[i].id
									+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
									+ secondLoopAllFeatures[i].featureName + '</li>';
								}
								
								secondLoopIteration++;
									if(featuresLength ==1){
										var g = 0;
										if(featuresLength <= secondLoopIteration){
											copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[g];
										}
									}else{
										var kk = i;
										if(i >= 1){
											if(secondLoopIteration > 2){
												if(i == 1){
													copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
												}else if(i == 2){
													kk = i;
													kk--;
													copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
												}else{
													copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[featuresLength-1];
												}
												
											}else{
												if(secondLoopIteration == 2){
													copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
												}else{
													kk--;
													copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
												}
											}
										}else{
											copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
										}
									}
								if(featuresLength ==1){
									var g = 0;
									if(featuresLength <= secondLoopIteration){
										copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[g];
									}
								}else if(featuresLength == 2){
									var kk = i;
									if(i >= 1){
										if(secondLoopIteration > 2){
											if(i == 1){
												copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
											}else if(i == 2){
												kk = i;
												kk--;
												copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
											}else{
												kk = featuresLength;
												kk--;
												copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
											}
											
										}else{
											if(secondLoopIteration == 2){
												copyFeaturesFromExistingArray[secondLoopIteration] = getsecondLoopFeaturesByRoleIdAndModuleId[kk];
											}else{
												kk--;
												copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
											}
										}
									}else{
										copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
									}
								}else if(featuresLength == 3) {
									if(i ==0){
									secondLoopIteration--;
									copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[i];
								}else{
									copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[i];
								}
									
								}else {
									if(featuresLength <= k){
										copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[i];
									}
								}
							}
						}else{
							if(secondLoopAllFeatures[i].featureName == 'Plan Project'|| 
								secondLoopAllFeatures[i].featureName == 'View Project' ||
								secondLoopAllFeatures[i].featureName == 'Add Reporting Period' || 
								secondLoopAllFeatures[i].featureName == 'Edit Reporting Period' || 
								secondLoopAllFeatures[i].featureName == 'Activate or Deactivate Reporting Period' || 
								secondLoopAllFeatures[i].featureName == 'View Reporting Period'){
								modalBody += '<li><input class="'
								+ j
								+ '_checkbox" type="checkbox" name="check[]" value="'
								+ secondLoopAllFeatures[i].id
								+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
								+ secondLoopAllFeatures[i].featureName + '</li>';
							}
						}
					}
					modalBody += '</ul></div>';
					}else{
						modalBody += '<div class="control-group">'
							+ '<input class="'
							+ j
							+ '_check" type="checkbox" id="'
							+ j
							+ '_selectall" onchange="selectDeselectAll(this)"/><label for="mapingWorkorderchecklistName" style="text-align: left; font-weight: bold;">&nbsp;&nbsp;'
							+ moduleFeatures[j].module.module
							+ '</label>'
							+ '<div class="controls" id="checkboxes">'
							+ '<ul class="chk-container" style="list-style-type: none;">';
					var features1 = [];
					features1 = moduleFeatures[j].features;
					for (var i = 0; i < features1.length; i++) {
						modalBody += '<li><input class="'
								+ j
								+ '_checkbox" type="checkbox" name="check[]" value="'
								+ features1[i].id
								+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
								+ features1[i].featureName + '</li>';
					}
					modalBody += '</ul></div>';
					}*/
				}
				
				$('#general2').html(modalBody);
				modalBody += '</fieldset></div>';
				
				// Third Column
				var temp3 = (temp+temp1);
				modalBody = '<div class="form-horizontal"><fieldset>';
				for (var j = temp3; j < currenModuleLength; j++) {
							modalBody += '<div class="control-group">'
								+ '<input class="'
								+ j
								+ '_check" type="checkbox" id="'
								+ j
								+ '_selectall" onchange="selectDeselectAll(this)"/><label for="mapingWorkorderchecklistName" style="text-align: left; font-weight: bold;">&nbsp;&nbsp;'
								+ moduleFeatures[j].module.module
								+ '</label>'
								+ '<div class="controls" id="checkboxes">'
								+ '<ul class="chk-container" style="list-style-type: none;">';
							var thirdLoopAllFeatures = [];
							var getThirdLoopFeaturesByRoleIdAndModuleId = [];
							var thirdLoopcopiedFeatures = [];
							getThirdLoopFeaturesByRoleIdAndModuleId = getFeaturesByRoleidAndmoduleId(userRoleName.id,moduleFeatures[j].module.id);
							for(var i =0;i<getThirdLoopFeaturesByRoleIdAndModuleId.length;i++) {
								thirdLoopcopiedFeatures.push(getThirdLoopFeaturesByRoleIdAndModuleId[i]);
							}
							var thirdLoopcopyFeaturesFromExistingArray = [];
							thirdLoopcopyFeaturesFromExistingArray = getThirdLoopFeaturesByRoleIdAndModuleId;
							thirdLoopAllFeatures = moduleFeatures[j].features;
							thirdFeaturesLength = getThirdLoopFeaturesByRoleIdAndModuleId.length;
							var thirdLoopIteration = 0;
							for (var i = 0; i < thirdLoopAllFeatures.length; i++) {
								if(getThirdLoopFeaturesByRoleIdAndModuleId.length > 0){
									if(thirdLoopAllFeatures[i].featureName == thirdLoopcopyFeaturesFromExistingArray[thirdLoopIteration].feature){
										modalBody += '<li><input class="'
											+ j
											+ '_checkbox" type="checkbox" checked="checked" name="check[]" value="'
											+ thirdLoopAllFeatures[i].id
											+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
											+ thirdLoopAllFeatures[i].featureName + '</li>';
										thirdLoopIteration++;
										if(thirdFeaturesLength ==1){
											var g = 0;
											if(thirdFeaturesLength <= firstLoopIteration){
												thirdLoopcopyFeaturesFromExistingArray[thirdLoopIteration] = thirdLoopcopiedFeatures[g];
											}
										}else{
											if(thirdFeaturesLength <= secondLoopIteration){
												thirdLoopcopyFeaturesFromExistingArray[thirdLoopIteration] = thirdLoopcopiedFeatures[i];
											}
										}
									}else{
											modalBody += '<li><input class="'
											+ j
											+ '_checkbox" type="checkbox" name="check[]" value="'
											+ thirdLoopAllFeatures[i].id
											+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
											+ thirdLoopAllFeatures[i].featureName + '</li>';
										
											thirdLoopIteration++;
											if(thirdFeaturesLength ==1){
												var g = 0;
												if(thirdFeaturesLength <= secondLoopIteration){
													thirdLoopcopyFeaturesFromExistingArray[thirdLoopIteration] = thirdLoopcopiedFeatures[g];
												}
											}else{
												if(thirdFeaturesLength <= secondLoopIteration){
													thirdLoopcopyFeaturesFromExistingArray[thirdLoopIteration] = thirdLoopcopiedFeatures[i];
												}
											}
									}
								}else{
									modalBody += '<li><input class="'
										+ j
										+ '_checkbox" type="checkbox" name="check[]" value="'
										+ thirdLoopAllFeatures[i].id
										+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
										+ thirdLoopAllFeatures[i].featureName + '</li>';
								}
							}
							modalBody += '</ul></div>';
				}
				
				$('#general3').html(modalBody);
				modalBody += '</fieldset></div>';
				checkSelectAll();
			}else if(userRoleName.name == 'STATUS_REVIEWER'){
				currenModuleLength = moduleFeatures.length;
				var temp = Math.round(currenModuleLength/3);
				var modalBody = '<div class="form-horizontal"><fieldset>';
				for (var j = 0; j < temp; j++) {
					modalBody += '<div class="control-group">'
						+ '<input class="'
						+ j
						+ '_check" type="checkbox" id="'
						+ j
						+ '_selectall" onchange="selectDeselectAll(this)"/><label for="mapingWorkorderchecklistName" style="text-align: left; font-weight: bold;">&nbsp;&nbsp;'
						+ moduleFeatures[j].module.module
						+ '</label>'
						+ '<div class="controls" id="checkboxes">'
						+ '<ul class="chk-container" style="list-style-type: none;">';
					//modalBody += loadFeatures(roleId,j,tempArray[j].module.id,tempArray[j].features);
					var firstLoopAllFeatures = [];
					var getFirstLoopFeaturesByRoleIdAndModuleId = [];
					var firstLoopcopiedFeatures = [];
					getFirstLoopFeaturesByRoleIdAndModuleId = getFeaturesByRoleidAndmoduleId(userRoleName.id,moduleFeatures[j].module.id);
					for(var i =0;i<getFirstLoopFeaturesByRoleIdAndModuleId.length;i++) {
						firstLoopcopiedFeatures.push(getFirstLoopFeaturesByRoleIdAndModuleId[i]);
					}
					var firstLoopcopyFeaturesFromExistingArray = [];
					firstLoopcopyFeaturesFromExistingArray = getFirstLoopFeaturesByRoleIdAndModuleId;
					firstLoopAllFeatures = moduleFeatures[j].features;
					firstFeaturesLength = getFirstLoopFeaturesByRoleIdAndModuleId.length;
					var firstLoopIteration = 0;
					for (var i = 0; i < firstLoopAllFeatures.length; i++) {
						if(getFirstLoopFeaturesByRoleIdAndModuleId.length > 0){
							if(firstLoopAllFeatures[i].featureName == firstLoopcopyFeaturesFromExistingArray[firstLoopIteration].feature){
								modalBody += '<li><input class="'
									+ j
									+ '_checkbox" type="checkbox" checked="checked" name="check[]" value="'
									+ firstLoopAllFeatures[i].id
									+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
									+ firstLoopAllFeatures[i].featureName + '</li>';
								firstLoopIteration++;
								if(firstFeaturesLength ==1){
									var g = 0;
									if(firstFeaturesLength <= firstLoopIteration){
										firstLoopcopyFeaturesFromExistingArray[firstLoopIteration] = firstLoopcopiedFeatures[g];
									}
								}else{
									if(firstFeaturesLength <= secondLoopIteration){
										firstLoopcopyFeaturesFromExistingArray[firstLoopIteration] = firstLoopcopiedFeatures[i];
									}
								}
							}else{
									modalBody += '<li><input class="'
									+ j
									+ '_checkbox" type="checkbox" name="check[]" value="'
									+ firstLoopAllFeatures[i].id
									+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
									+ firstLoopAllFeatures[i].featureName + '</li>';
								
									firstLoopIteration++;
									if(firstFeaturesLength ==1){
										var g = 0;
										if(featuresLength <= secondLoopIteration){
											firstLoopcopyFeaturesFromExistingArray[firstLoopIteration] = firstLoopcopiedFeatures[g];
										}
									}else{
										if(firstFeaturesLength <= secondLoopIteration){
											firstLoopcopyFeaturesFromExistingArray[firstLoopIteration] = firstLoopcopiedFeatures[i];
										}
									}
							}
						}else{
							modalBody += '<li><input class="'
								+ j
								+ '_checkbox" type="checkbox" name="check[]" value="'
								+ firstLoopAllFeatures[i].id
								+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
								+ firstLoopAllFeatures[i].featureName + '</li>';
						}
					}
					modalBody += '</ul></div>';
				}
				
				$('#general1').html(modalBody);
				modalBody += '</fieldset></div>';
				// This is for second column
				var temp1 = Math.round((currenModuleLength-temp)/2);
				var tt = temp + temp1;
				modalBody = '<div class="form-horizontal"><fieldset>';
				for (var j = temp; j < tt; j++) {
					if(moduleFeatures[j].module.module == 'Project'){
						modalBody += '<div class="control-group">'
							+ '<input class="'
							+ j
							+ '_check" type="checkbox" id="'
							+ j
							+ '_selectall" onchange="selectDeselectAll(this)"/><label for="mapingWorkorderchecklistName" style="text-align: left; font-weight: bold;">&nbsp;&nbsp;'
							+ moduleFeatures[j].module.module
							+ '</label>'
							+ '<div class="controls" id="checkboxes">'
							+ '<ul class="chk-container" style="list-style-type: none;">';
						var secondLoopAllFeatures = [];
						var getsecondLoopFeaturesByRoleIdAndModuleId = [];
						var copiedFeatures = [];
						getsecondLoopFeaturesByRoleIdAndModuleId = getFeaturesByRoleidAndmoduleId(userRoleName.id,moduleFeatures[j].module.id);
						for(var i =0;i<getsecondLoopFeaturesByRoleIdAndModuleId.length;i++) {
							copiedFeatures.push(getsecondLoopFeaturesByRoleIdAndModuleId[i]);
						}
						var copyFeaturesFromExistingArray = [];
						copyFeaturesFromExistingArray = getsecondLoopFeaturesByRoleIdAndModuleId;
						secondLoopAllFeatures = moduleFeatures[j].features;
						featuresLength = getsecondLoopFeaturesByRoleIdAndModuleId.length;
						var secondLoopIteration = 0;
						for (var i = 0; i < secondLoopAllFeatures.length; i++) {
							if(getsecondLoopFeaturesByRoleIdAndModuleId.length > 0){
								if(secondLoopAllFeatures[i].featureName == copyFeaturesFromExistingArray[secondLoopIteration].feature){
									modalBody += '<li><input class="'
										+ j
										+ '_checkbox" type="checkbox" checked="checked" name="check[]" value="'
										+ secondLoopAllFeatures[i].id
										+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
										+ secondLoopAllFeatures[i].featureName + '</li>';
									secondLoopIteration++;
									if(featuresLength ==1){
										var g = 0;
										if(featuresLength <= secondLoopIteration){
											copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[g];
										}
									}else{
										if(featuresLength <= secondLoopIteration){
											copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[i];
										}
									}
								}else{
									if(secondLoopAllFeatures[i].featureName == 'Review Status'){
										modalBody += '<li><input class="'
										+ j
										+ '_checkbox" type="checkbox" name="check[]" value="'
										+ secondLoopAllFeatures[i].id
										+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
										+ secondLoopAllFeatures[i].featureName + '</li>';
									}
									
									secondLoopIteration++;
										if(featuresLength ==1){
											var g = 0;
											if(featuresLength <= secondLoopIteration){
												copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[g];
											}
										}else{
											if(featuresLength <= secondLoopIteration){
												copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[i];
											}
										}
								}
							}else{
								if(secondLoopAllFeatures[i].featureName == 'Review Status'){
									modalBody += '<li><input class="'
									+ j
									+ '_checkbox" type="checkbox" name="check[]" value="'
									+ secondLoopAllFeatures[i].id
									+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
									+ secondLoopAllFeatures[i].featureName + '</li>';
								}
							}
						}
					modalBody += '</ul></div>';
					}else{
						modalBody += '<div class="control-group">'
							+ '<input class="'
							+ j
							+ '_check" type="checkbox" id="'
							+ j
							+ '_selectall" onchange="selectDeselectAll(this)"/><label for="mapingWorkorderchecklistName" style="text-align: left; font-weight: bold;">&nbsp;&nbsp;'
							+ moduleFeatures[j].module.module
							+ '</label>'
							+ '<div class="controls" id="checkboxes">'
							+ '<ul class="chk-container" style="list-style-type: none;">';
					var features1 = [];
					features1 = moduleFeatures[j].features;
					for (var i = 0; i < features1.length; i++) {
						modalBody += '<li><input class="'
								+ j
								+ '_checkbox" type="checkbox" name="check[]" value="'
								+ features1[i].id
								+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
								+ features1[i].featureName + '</li>';
					}
					modalBody += '</ul></div>';
					}
				}
				
				$('#general2').html(modalBody);
				modalBody += '</fieldset></div>';
				
				// Third Column
				var temp3 = (temp+temp1);
				modalBody = '<div class="form-horizontal"><fieldset>';
				for (var j = temp3; j < currenModuleLength; j++) {
							modalBody += '<div class="control-group">'
								+ '<input class="'
								+ j
								+ '_check" type="checkbox" id="'
								+ j
								+ '_selectall" onchange="selectDeselectAll(this)"/><label for="mapingWorkorderchecklistName" style="text-align: left; font-weight: bold;">&nbsp;&nbsp;'
								+ moduleFeatures[j].module.module
								+ '</label>'
								+ '<div class="controls" id="checkboxes">'
								+ '<ul class="chk-container" style="list-style-type: none;">';
							var thirdLoopAllFeatures = [];
							var getThirdLoopFeaturesByRoleIdAndModuleId = [];
							var thirdLoopcopiedFeatures = [];
							getThirdLoopFeaturesByRoleIdAndModuleId = getFeaturesByRoleidAndmoduleId(userRoleName.id,moduleFeatures[j].module.id);
							for(var i =0;i<getThirdLoopFeaturesByRoleIdAndModuleId.length;i++) {
								thirdLoopcopiedFeatures.push(getThirdLoopFeaturesByRoleIdAndModuleId[i]);
							}
							var thirdLoopcopyFeaturesFromExistingArray = [];
							thirdLoopcopyFeaturesFromExistingArray = getThirdLoopFeaturesByRoleIdAndModuleId;
							thirdLoopAllFeatures = moduleFeatures[j].features;
							thirdFeaturesLength = getThirdLoopFeaturesByRoleIdAndModuleId.length;
							var thirdLoopIteration = 0;
							for (var i = 0; i < thirdLoopAllFeatures.length; i++) {
								if(getThirdLoopFeaturesByRoleIdAndModuleId.length > 0){
									if(thirdLoopAllFeatures[i].featureName == thirdLoopcopyFeaturesFromExistingArray[thirdLoopIteration].feature){
										modalBody += '<li><input class="'
											+ j
											+ '_checkbox" type="checkbox" checked="checked" name="check[]" value="'
											+ thirdLoopAllFeatures[i].id
											+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
											+ thirdLoopAllFeatures[i].featureName + '</li>';
										thirdLoopIteration++;
										if(thirdFeaturesLength ==1){
											var g = 0;
											if(thirdFeaturesLength <= firstLoopIteration){
												thirdLoopcopyFeaturesFromExistingArray[thirdLoopIteration] = thirdLoopcopiedFeatures[g];
											}
										}else{
											if(thirdFeaturesLength <= secondLoopIteration){
												thirdLoopcopyFeaturesFromExistingArray[thirdLoopIteration] = thirdLoopcopiedFeatures[i];
											}
										}
									}else{
											modalBody += '<li><input class="'
											+ j
											+ '_checkbox" type="checkbox" name="check[]" value="'
											+ thirdLoopAllFeatures[i].id
											+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
											+ thirdLoopAllFeatures[i].featureName + '</li>';
										
											thirdLoopIteration++;
											if(thirdFeaturesLength ==1){
												var g = 0;
												if(thirdFeaturesLength <= secondLoopIteration){
													thirdLoopcopyFeaturesFromExistingArray[thirdLoopIteration] = thirdLoopcopiedFeatures[g];
												}
											}else{
												if(thirdFeaturesLength <= secondLoopIteration){
													thirdLoopcopyFeaturesFromExistingArray[thirdLoopIteration] = thirdLoopcopiedFeatures[i];
												}
											}
									}
								}else{
									modalBody += '<li><input class="'
										+ j
										+ '_checkbox" type="checkbox" name="check[]" value="'
										+ thirdLoopAllFeatures[i].id
										+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
										+ thirdLoopAllFeatures[i].featureName + '</li>';
								}
							}
							modalBody += '</ul></div>';
				}
				
				$('#general3').html(modalBody);
				modalBody += '</fieldset></div>';
				checkSelectAll();
			}else if(userRoleName.name == 'STATUS_UPDATER'){
				currenModuleLength = moduleFeatures.length;
				var temp = Math.round(currenModuleLength/3);
				var modalBody = '<div class="form-horizontal"><fieldset>';
				for (var j = 0; j < temp; j++) {
					modalBody += '<div class="control-group">'
						+ '<input class="'
						+ j
						+ '_check" type="checkbox" id="'
						+ j
						+ '_selectall" onchange="selectDeselectAll(this)"/><label for="mapingWorkorderchecklistName" style="text-align: left; font-weight: bold;">&nbsp;&nbsp;'
						+ moduleFeatures[j].module.module
						+ '</label>'
						+ '<div class="controls" id="checkboxes">'
						+ '<ul class="chk-container" style="list-style-type: none;">';
					//modalBody += loadFeatures(roleId,j,tempArray[j].module.id,tempArray[j].features);
					var firstLoopAllFeatures = [];
					var getFirstLoopFeaturesByRoleIdAndModuleId = [];
					var firstLoopcopiedFeatures = [];
					getFirstLoopFeaturesByRoleIdAndModuleId = getFeaturesByRoleidAndmoduleId(userRoleName.id,moduleFeatures[j].module.id);
					for(var i =0;i<getFirstLoopFeaturesByRoleIdAndModuleId.length;i++) {
						firstLoopcopiedFeatures.push(getFirstLoopFeaturesByRoleIdAndModuleId[i]);
					}
					var firstLoopcopyFeaturesFromExistingArray = [];
					firstLoopcopyFeaturesFromExistingArray = getFirstLoopFeaturesByRoleIdAndModuleId;
					firstLoopAllFeatures = moduleFeatures[j].features;
					firstFeaturesLength = getFirstLoopFeaturesByRoleIdAndModuleId.length;
					var firstLoopIteration = 0;
					for (var i = 0; i < firstLoopAllFeatures.length; i++) {
						if(getFirstLoopFeaturesByRoleIdAndModuleId.length > 0){
							if(firstLoopAllFeatures[i].featureName == firstLoopcopyFeaturesFromExistingArray[firstLoopIteration].feature){
								modalBody += '<li><input class="'
									+ j
									+ '_checkbox" type="checkbox" checked="checked" name="check[]" value="'
									+ firstLoopAllFeatures[i].id
									+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
									+ firstLoopAllFeatures[i].featureName + '</li>';
								firstLoopIteration++;
								if(firstFeaturesLength ==1){
									var g = 0;
									if(firstFeaturesLength <= firstLoopIteration){
										firstLoopcopyFeaturesFromExistingArray[firstLoopIteration] = firstLoopcopiedFeatures[g];
									}
								}else{
									if(firstFeaturesLength <= secondLoopIteration){
										firstLoopcopyFeaturesFromExistingArray[firstLoopIteration] = firstLoopcopiedFeatures[i];
									}
								}
							}else{
									modalBody += '<li><input class="'
									+ j
									+ '_checkbox" type="checkbox" name="check[]" value="'
									+ firstLoopAllFeatures[i].id
									+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
									+ firstLoopAllFeatures[i].featureName + '</li>';
								
									firstLoopIteration++;
									if(firstFeaturesLength ==1){
										var g = 0;
										if(featuresLength <= secondLoopIteration){
											firstLoopcopyFeaturesFromExistingArray[firstLoopIteration] = firstLoopcopiedFeatures[g];
										}
									}else{
										if(firstFeaturesLength <= secondLoopIteration){
											firstLoopcopyFeaturesFromExistingArray[firstLoopIteration] = firstLoopcopiedFeatures[i];
										}
									}
							}
						}else{
							modalBody += '<li><input class="'
								+ j
								+ '_checkbox" type="checkbox" name="check[]" value="'
								+ firstLoopAllFeatures[i].id
								+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
								+ firstLoopAllFeatures[i].featureName + '</li>';
						}
					}
					modalBody += '</ul></div>';
				}
				
				$('#general1').html(modalBody);
				modalBody += '</fieldset></div>';
				// This is for second column
				var temp1 = Math.round((currenModuleLength-temp)/2);
				var tt = temp + temp1;
				modalBody = '<div class="form-horizontal"><fieldset>';
				for (var j = temp; j < tt; j++) {
					if(moduleFeatures[j].module.module == 'Project'){
						modalBody += '<div class="control-group">'
							+ '<input class="'
							+ j
							+ '_check" type="checkbox" id="'
							+ j
							+ '_selectall" onchange="selectDeselectAll(this)"/><label for="mapingWorkorderchecklistName" style="text-align: left; font-weight: bold;">&nbsp;&nbsp;'
							+ moduleFeatures[j].module.module
							+ '</label>'
							+ '<div class="controls" id="checkboxes">'
							+ '<ul class="chk-container" style="list-style-type: none;">';
						var secondLoopAllFeatures = [];
						var getsecondLoopFeaturesByRoleIdAndModuleId = [];
						var copiedFeatures = [];
						getsecondLoopFeaturesByRoleIdAndModuleId = getFeaturesByRoleidAndmoduleId(userRoleName.id,moduleFeatures[j].module.id);
						for(var i =0;i<getsecondLoopFeaturesByRoleIdAndModuleId.length;i++) {
							copiedFeatures.push(getsecondLoopFeaturesByRoleIdAndModuleId[i]);
						}
						var copyFeaturesFromExistingArray = [];
						copyFeaturesFromExistingArray = getsecondLoopFeaturesByRoleIdAndModuleId;
						secondLoopAllFeatures = moduleFeatures[j].features;
						featuresLength = getsecondLoopFeaturesByRoleIdAndModuleId.length;
						var secondLoopIteration = 0;
						for (var i = 0; i < secondLoopAllFeatures.length; i++) {
							if(getsecondLoopFeaturesByRoleIdAndModuleId.length > 0){
								if(secondLoopAllFeatures[i].featureName == copyFeaturesFromExistingArray[secondLoopIteration].feature){
									modalBody += '<li><input class="'
										+ j
										+ '_checkbox" type="checkbox" checked="checked" name="check[]" value="'
										+ secondLoopAllFeatures[i].id
										+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
										+ secondLoopAllFeatures[i].featureName + '</li>';
									secondLoopIteration++;
									if(featuresLength ==1){
										var g = 0;
										if(featuresLength <= secondLoopIteration){
											copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[g];
										}
									}else{
										if(featuresLength <= secondLoopIteration){
											copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[i];
										}
									}
								}else{
									if(secondLoopAllFeatures[i].featureName == 'Update Status'){
										modalBody += '<li><input class="'
										+ j
										+ '_checkbox" type="checkbox" name="check[]" value="'
										+ secondLoopAllFeatures[i].id
										+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
										+ secondLoopAllFeatures[i].featureName + '</li>';
									}
									
									secondLoopIteration++;
										if(featuresLength ==1){
											var g = 0;
											if(featuresLength <= secondLoopIteration){
												copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[g];
											}
										}else{
											if(featuresLength <= secondLoopIteration){
												copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[i];
											}
										}
								}
							}else{
								if(secondLoopAllFeatures[i].featureName == 'Update Status'){
									modalBody += '<li><input class="'
									+ j
									+ '_checkbox" type="checkbox" name="check[]" value="'
									+ secondLoopAllFeatures[i].id
									+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
									+ secondLoopAllFeatures[i].featureName + '</li>';
								}
							}
						}
					modalBody += '</ul></div>';
					}else{
						modalBody += '<div class="control-group">'
							+ '<input class="'
							+ j
							+ '_check" type="checkbox" id="'
							+ j
							+ '_selectall" onchange="selectDeselectAll(this)"/><label for="mapingWorkorderchecklistName" style="text-align: left; font-weight: bold;">&nbsp;&nbsp;'
							+ moduleFeatures[j].module.module
							+ '</label>'
							+ '<div class="controls" id="checkboxes">'
							+ '<ul class="chk-container" style="list-style-type: none;">';
					var features1 = [];
					features1 = moduleFeatures[j].features;
					for (var i = 0; i < features1.length; i++) {
						modalBody += '<li><input class="'
								+ j
								+ '_checkbox" type="checkbox" name="check[]" value="'
								+ features1[i].id
								+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
								+ features1[i].featureName + '</li>';
					}
					modalBody += '</ul></div>';
					}
				}
				
				$('#general2').html(modalBody);
				modalBody += '</fieldset></div>';
				
				// Third Column
				var temp3 = (temp+temp1);
				modalBody = '<div class="form-horizontal"><fieldset>';
				for (var j = temp3; j < currenModuleLength; j++) {
							modalBody += '<div class="control-group">'
								+ '<input class="'
								+ j
								+ '_check" type="checkbox" id="'
								+ j
								+ '_selectall" onchange="selectDeselectAll(this)"/><label for="mapingWorkorderchecklistName" style="text-align: left; font-weight: bold;">&nbsp;&nbsp;'
								+ moduleFeatures[j].module.module
								+ '</label>'
								+ '<div class="controls" id="checkboxes">'
								+ '<ul class="chk-container" style="list-style-type: none;">';
							var thirdLoopAllFeatures = [];
							var getThirdLoopFeaturesByRoleIdAndModuleId = [];
							var thirdLoopcopiedFeatures = [];
							getThirdLoopFeaturesByRoleIdAndModuleId = getFeaturesByRoleidAndmoduleId(userRoleName.id,moduleFeatures[j].module.id);
							for(var i =0;i<getThirdLoopFeaturesByRoleIdAndModuleId.length;i++) {
								thirdLoopcopiedFeatures.push(getThirdLoopFeaturesByRoleIdAndModuleId[i]);
							}
							var thirdLoopcopyFeaturesFromExistingArray = [];
							thirdLoopcopyFeaturesFromExistingArray = getThirdLoopFeaturesByRoleIdAndModuleId;
							thirdLoopAllFeatures = moduleFeatures[j].features;
							thirdFeaturesLength = getThirdLoopFeaturesByRoleIdAndModuleId.length;
							var thirdLoopIteration = 0;
							for (var i = 0; i < thirdLoopAllFeatures.length; i++) {
								if(getThirdLoopFeaturesByRoleIdAndModuleId.length > 0){
									if(thirdLoopAllFeatures[i].featureName == thirdLoopcopyFeaturesFromExistingArray[thirdLoopIteration].feature){
										modalBody += '<li><input class="'
											+ j
											+ '_checkbox" type="checkbox" checked="checked" name="check[]" value="'
											+ thirdLoopAllFeatures[i].id
											+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
											+ thirdLoopAllFeatures[i].featureName + '</li>';
										thirdLoopIteration++;
										if(thirdFeaturesLength ==1){
											var g = 0;
											if(thirdFeaturesLength <= firstLoopIteration){
												thirdLoopcopyFeaturesFromExistingArray[thirdLoopIteration] = thirdLoopcopiedFeatures[g];
											}
										}else{
											if(thirdFeaturesLength <= secondLoopIteration){
												thirdLoopcopyFeaturesFromExistingArray[thirdLoopIteration] = thirdLoopcopiedFeatures[i];
											}
										}
									}else{
											modalBody += '<li><input class="'
											+ j
											+ '_checkbox" type="checkbox" name="check[]" value="'
											+ thirdLoopAllFeatures[i].id
											+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
											+ thirdLoopAllFeatures[i].featureName + '</li>';
										
											thirdLoopIteration++;
											if(thirdFeaturesLength ==1){
												var g = 0;
												if(thirdFeaturesLength <= secondLoopIteration){
													thirdLoopcopyFeaturesFromExistingArray[thirdLoopIteration] = thirdLoopcopiedFeatures[g];
												}
											}else{
												if(thirdFeaturesLength <= secondLoopIteration){
													thirdLoopcopyFeaturesFromExistingArray[thirdLoopIteration] = thirdLoopcopiedFeatures[i];
												}
											}
									}
								}else{
									modalBody += '<li><input class="'
										+ j
										+ '_checkbox" type="checkbox" name="check[]" value="'
										+ thirdLoopAllFeatures[i].id
										+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
										+ thirdLoopAllFeatures[i].featureName + '</li>';
								}
							}
							modalBody += '</ul></div>';
				}
				
				$('#general3').html(modalBody);
				modalBody += '</fieldset></div>';
				checkSelectAll();
			}else if(userRoleName.name == 'STATUS_APPROVER'){
				currenModuleLength = moduleFeatures.length;
				var temp = Math.round(currenModuleLength/3);
				var modalBody = '<div class="form-horizontal"><fieldset>';
				for (var j = 0; j < temp; j++) {
					modalBody += '<div class="control-group">'
						+ '<input class="'
						+ j
						+ '_check" type="checkbox" id="'
						+ j
						+ '_selectall" onchange="selectDeselectAll(this)"/><label for="mapingWorkorderchecklistName" style="text-align: left; font-weight: bold;">&nbsp;&nbsp;'
						+ moduleFeatures[j].module.module
						+ '</label>'
						+ '<div class="controls" id="checkboxes">'
						+ '<ul class="chk-container" style="list-style-type: none;">';
					//modalBody += loadFeatures(roleId,j,tempArray[j].module.id,tempArray[j].features);
					var firstLoopAllFeatures = [];
					var getFirstLoopFeaturesByRoleIdAndModuleId = [];
					var firstLoopcopiedFeatures = [];
					getFirstLoopFeaturesByRoleIdAndModuleId = getFeaturesByRoleidAndmoduleId(userRoleName.id,moduleFeatures[j].module.id);
					for(var i =0;i<getFirstLoopFeaturesByRoleIdAndModuleId.length;i++) {
						firstLoopcopiedFeatures.push(getFirstLoopFeaturesByRoleIdAndModuleId[i]);
					}
					var firstLoopcopyFeaturesFromExistingArray = [];
					firstLoopcopyFeaturesFromExistingArray = getFirstLoopFeaturesByRoleIdAndModuleId;
					firstLoopAllFeatures = moduleFeatures[j].features;
					firstFeaturesLength = getFirstLoopFeaturesByRoleIdAndModuleId.length;
					var firstLoopIteration = 0;
					for (var i = 0; i < firstLoopAllFeatures.length; i++) {
						if(getFirstLoopFeaturesByRoleIdAndModuleId.length > 0){
							if(firstLoopAllFeatures[i].featureName == firstLoopcopyFeaturesFromExistingArray[firstLoopIteration].feature){
								modalBody += '<li><input class="'
									+ j
									+ '_checkbox" type="checkbox" checked="checked" name="check[]" value="'
									+ firstLoopAllFeatures[i].id
									+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
									+ firstLoopAllFeatures[i].featureName + '</li>';
								firstLoopIteration++;
								if(firstFeaturesLength ==1){
									var g = 0;
									if(firstFeaturesLength <= firstLoopIteration){
										firstLoopcopyFeaturesFromExistingArray[firstLoopIteration] = firstLoopcopiedFeatures[g];
									}
								}else{
									if(firstFeaturesLength <= secondLoopIteration){
										firstLoopcopyFeaturesFromExistingArray[firstLoopIteration] = firstLoopcopiedFeatures[i];
									}
								}
							}else{
									modalBody += '<li><input class="'
									+ j
									+ '_checkbox" type="checkbox" name="check[]" value="'
									+ firstLoopAllFeatures[i].id
									+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
									+ firstLoopAllFeatures[i].featureName + '</li>';
								
									firstLoopIteration++;
									if(firstFeaturesLength ==1){
										var g = 0;
										if(featuresLength <= secondLoopIteration){
											firstLoopcopyFeaturesFromExistingArray[firstLoopIteration] = firstLoopcopiedFeatures[g];
										}
									}else{
										if(firstFeaturesLength <= secondLoopIteration){
											firstLoopcopyFeaturesFromExistingArray[firstLoopIteration] = firstLoopcopiedFeatures[i];
										}
									}
							}
						}else{
							modalBody += '<li><input class="'
								+ j
								+ '_checkbox" type="checkbox" name="check[]" value="'
								+ firstLoopAllFeatures[i].id
								+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
								+ firstLoopAllFeatures[i].featureName + '</li>';
						}
					}
					modalBody += '</ul></div>';
				}
				
				$('#general1').html(modalBody);
				modalBody += '</fieldset></div>';
				// This is for second column
				var temp1 = Math.round((currenModuleLength-temp)/2);
				var tt = temp + temp1;
				modalBody = '<div class="form-horizontal"><fieldset>';
				for (var j = temp; j < tt; j++) {
					if(moduleFeatures[j].module.module == 'Project'){
						modalBody += '<div class="control-group">'
							+ '<input class="'
							+ j
							+ '_check" type="checkbox" id="'
							+ j
							+ '_selectall" onchange="selectDeselectAll(this)"/><label for="mapingWorkorderchecklistName" style="text-align: left; font-weight: bold;">&nbsp;&nbsp;'
							+ moduleFeatures[j].module.module
							+ '</label>'
							+ '<div class="controls" id="checkboxes">'
							+ '<ul class="chk-container" style="list-style-type: none;">';
						var secondLoopAllFeatures = [];
						var getsecondLoopFeaturesByRoleIdAndModuleId = [];
						var copiedFeatures = [];
						getsecondLoopFeaturesByRoleIdAndModuleId = getFeaturesByRoleidAndmoduleId(userRoleName.id,moduleFeatures[j].module.id);
						for(var i =0;i<getsecondLoopFeaturesByRoleIdAndModuleId.length;i++) {
							copiedFeatures.push(getsecondLoopFeaturesByRoleIdAndModuleId[i]);
						}
						var copyFeaturesFromExistingArray = [];
						copyFeaturesFromExistingArray = getsecondLoopFeaturesByRoleIdAndModuleId;
						secondLoopAllFeatures = moduleFeatures[j].features;
						featuresLength = getsecondLoopFeaturesByRoleIdAndModuleId.length;
						var secondLoopIteration = 0;
						for (var i = 0; i < secondLoopAllFeatures.length; i++) {
							if(getsecondLoopFeaturesByRoleIdAndModuleId.length > 0){
								if(secondLoopAllFeatures[i].featureName == copyFeaturesFromExistingArray[secondLoopIteration].feature){
									modalBody += '<li><input class="'
										+ j
										+ '_checkbox" type="checkbox" checked="checked" name="check[]" value="'
										+ secondLoopAllFeatures[i].id
										+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
										+ secondLoopAllFeatures[i].featureName + '</li>';
									secondLoopIteration++;
									if(featuresLength ==1){
										var g = 0;
										if(featuresLength <= secondLoopIteration){
											copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[g];
										}
									}else{
										var kk = i;
										if(i == 0){
											kk++;
											copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
										}else if(i == 1){
											copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
										}else{
											kk--;
											copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
										}
									}
								}else{
									if(secondLoopAllFeatures[i].featureName == 'View Project' || secondLoopAllFeatures[i].featureName == 'Approve Status'){
										modalBody += '<li><input class="'
										+ j
										+ '_checkbox" type="checkbox" name="check[]" value="'
										+ secondLoopAllFeatures[i].id
										+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
										+ secondLoopAllFeatures[i].featureName + '</li>';
									}
									
									secondLoopIteration++;
										if(featuresLength ==1){
											var g = 0;
											if(featuresLength <= secondLoopIteration){
												copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[g];
											}
										}else{
											var kk = i;
											if(i >= 1){
												if(secondLoopIteration > 2){
													if(i == 1){
														copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
													}else if(i == 2){
														kk = i;
														kk--;
														copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
													}else{
														copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[featuresLength-1];
													}
													
												}else{
													if(secondLoopIteration == 2){
														copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
													}else{
														kk--;
														copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
													}
												}
											}else{
												copyFeaturesFromExistingArray[secondLoopIteration] = copiedFeatures[kk];
											}
										}
								}
							}else{
								if(secondLoopAllFeatures[i].featureName == 'View Project' || secondLoopAllFeatures[i].featureName == 'Approve Status'){
									modalBody += '<li><input class="'
									+ j
									+ '_checkbox" type="checkbox" name="check[]" value="'
									+ secondLoopAllFeatures[i].id
									+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
									+ secondLoopAllFeatures[i].featureName + '</li>';
								}
							}
						}
					modalBody += '</ul></div>';
					}else{
						modalBody += '<div class="control-group">'
							+ '<input class="'
							+ j
							+ '_check" type="checkbox" id="'
							+ j
							+ '_selectall" onchange="selectDeselectAll(this)"/><label for="mapingWorkorderchecklistName" style="text-align: left; font-weight: bold;">&nbsp;&nbsp;'
							+ moduleFeatures[j].module.module
							+ '</label>'
							+ '<div class="controls" id="checkboxes">'
							+ '<ul class="chk-container" style="list-style-type: none;">';
					var features1 = [];
					features1 = moduleFeatures[j].features;
					for (var i = 0; i < features1.length; i++) {
						modalBody += '<li><input class="'
								+ j
								+ '_checkbox" type="checkbox" name="check[]" value="'
								+ features1[i].id
								+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
								+ features1[i].featureName + '</li>';
					}
					modalBody += '</ul></div>';
					}
				}
				
				$('#general2').html(modalBody);
				modalBody += '</fieldset></div>';
				
				// Third Column
				var temp3 = (temp+temp1);
				modalBody = '<div class="form-horizontal"><fieldset>';
				for (var j = temp3; j < currenModuleLength; j++) {
							modalBody += '<div class="control-group">'
								+ '<input class="'
								+ j
								+ '_check" type="checkbox" id="'
								+ j
								+ '_selectall" onchange="selectDeselectAll(this)"/><label for="mapingWorkorderchecklistName" style="text-align: left; font-weight: bold;">&nbsp;&nbsp;'
								+ moduleFeatures[j].module.module
								+ '</label>'
								+ '<div class="controls" id="checkboxes">'
								+ '<ul class="chk-container" style="list-style-type: none;">';
							var thirdLoopAllFeatures = [];
							var getThirdLoopFeaturesByRoleIdAndModuleId = [];
							var thirdLoopcopiedFeatures = [];
							getThirdLoopFeaturesByRoleIdAndModuleId = getFeaturesByRoleidAndmoduleId(userRoleName.id,moduleFeatures[j].module.id);
							for(var i =0;i<getThirdLoopFeaturesByRoleIdAndModuleId.length;i++) {
								thirdLoopcopiedFeatures.push(getThirdLoopFeaturesByRoleIdAndModuleId[i]);
							}
							var thirdLoopcopyFeaturesFromExistingArray = [];
							thirdLoopcopyFeaturesFromExistingArray = getThirdLoopFeaturesByRoleIdAndModuleId;
							thirdLoopAllFeatures = moduleFeatures[j].features;
							thirdFeaturesLength = getThirdLoopFeaturesByRoleIdAndModuleId.length;
							var thirdLoopIteration = 0;
							for (var i = 0; i < thirdLoopAllFeatures.length; i++) {
								if(getThirdLoopFeaturesByRoleIdAndModuleId.length > 0){
									if(thirdLoopAllFeatures[i].featureName == thirdLoopcopyFeaturesFromExistingArray[thirdLoopIteration].feature){
										modalBody += '<li><input class="'
											+ j
											+ '_checkbox" type="checkbox" checked="checked" name="check[]" value="'
											+ thirdLoopAllFeatures[i].id
											+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
											+ thirdLoopAllFeatures[i].featureName + '</li>';
										thirdLoopIteration++;
										if(thirdFeaturesLength ==1){
											var g = 0;
											if(thirdFeaturesLength <= firstLoopIteration){
												thirdLoopcopyFeaturesFromExistingArray[thirdLoopIteration] = thirdLoopcopiedFeatures[g];
											}
										}else{
											if(thirdFeaturesLength <= secondLoopIteration){
												thirdLoopcopyFeaturesFromExistingArray[thirdLoopIteration] = thirdLoopcopiedFeatures[i];
											}
										}
									}else{
											modalBody += '<li><input class="'
											+ j
											+ '_checkbox" type="checkbox" name="check[]" value="'
											+ thirdLoopAllFeatures[i].id
											+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
											+ thirdLoopAllFeatures[i].featureName + '</li>';
										
											thirdLoopIteration++;
											if(thirdFeaturesLength ==1){
												var g = 0;
												if(thirdFeaturesLength <= secondLoopIteration){
													thirdLoopcopyFeaturesFromExistingArray[thirdLoopIteration] = thirdLoopcopiedFeatures[g];
												}
											}else{
												if(thirdFeaturesLength <= secondLoopIteration){
													thirdLoopcopyFeaturesFromExistingArray[thirdLoopIteration] = thirdLoopcopiedFeatures[i];
												}
											}
									}
								}else{
									modalBody += '<li><input class="'
										+ j
										+ '_checkbox" type="checkbox" name="check[]" value="'
										+ thirdLoopAllFeatures[i].id
										+ '" onclick="checkoruncheck(this)">&nbsp;&nbsp;'
										+ thirdLoopAllFeatures[i].featureName + '</li>';
								}
							}
							modalBody += '</ul></div>';
				}
				
				$('#general3').html(modalBody);
				modalBody += '</fieldset></div>';
				checkSelectAll();
			}
		}
	}
}

function selectDeselectAll(ele) {
	if(ele.checked) {
		var id = ele.id;// check select status
		var moduleIndex = id.split('_')[0];
		var cssClass = '.' + moduleIndex + '_checkbox';
        $(cssClass).each(function() { //loop through each checkbox
            this.checked = true;  //select all checkboxes with class "checkbox1"              
        });
    } else if (!ele.checked) {
    	var id = ele.id;// check select status
		var moduleIndex = id.split('_')[0];
		var cssClass = '.' + moduleIndex + '_checkbox';
        $(cssClass).each(function() { //loop through each checkbox
            this.checked = false;  //select all checkboxes with class "checkbox1"              
        });
    }
}

function checkSelectAll() {
	for (var i = 0; i < currenModuleLength; i++) {
		var flag = true;
		var cssClass = '.' + i + '_checkbox';//$('.' + cssClass + ':checked')
		$(cssClass).each(function() {
			if (!($(this).is(':checked'))) {
				flag = false;
			}
		});
		if (flag) {
			$('#' + i + '_selectall').attr('checked', true);
		} else {
			$('#' + i + '_selectall').attr('checked', false);
		}
	}
}

function checkoruncheck(ele) {
	var id = ele.className;// check select status
	var moduleIndex = id.split('_')[0];
	var cssClass = '.' + moduleIndex + '_checkbox';
	var flag = true;
	
	if ($(ele).is(':checked')) {
		$(cssClass).each(function() {
			if (!($(this).is(':checked'))) {
				flag = false;
			}
		});
		if (flag) {
			$('#' + moduleIndex + '_selectall').attr('checked', true);
		} else {
			$('#' + moduleIndex + '_selectall').prop("checked", true);
		}
	} else {
		$('#' + moduleIndex + '_selectall').attr('checked', false);
	}
}

function getFeaturesByRoleId() {
	var features = [];
	$.ajax({
		url : '/ndcmp/api/assignRoles',
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

function getCurrentRole1() {
	var listCurrentUserRoles = [];
	$.ajax({
		url : '/ndcmp/api/getCurrentUserRoles',
		type : 'GET',
		async : false,
		cache : false,
		success : function(result) {
			if (result != undefined && result != null && result != '') {
				listCurrentUserRoles = result;
			}
		}
	});
	return listCurrentUserRoles;
}

function getModuleFeatures(roleid) {
	var moduleFeatures = [];
	$.ajax({
		url : '/ndcmp/api/getmodulefeatures',
		type : 'GET',
		data : {
			roleId : roleid
		},
		async : false,
		cache : false,
		success : function(result) {
			if (result != undefined && result != null && result != '') {
				moduleFeatures = result;
			}
		}
	});
	return moduleFeatures;
}

function getUserRoleName(roleid){
	var roleName = '';
	$.ajax({
		url : '/ndcmp/api/getUserRoleName',
		type : 'GET',
		data : {
			roleId : roleid
		},
		async : false,
		cache : false,
		success : function(result) {
			if (result != undefined && result != null && result != '') {
				roleName = result;
			}
		}
	});
	return roleName;
}

function getSelectedFeatures() {
	var featureslist = new Array();
		var arr = new Array();
		var featureObj = new Object();
		$('#checkboxes input:checked').each(function() {
			var features = {
				'id' : this.value,
				'feature' : ''
			};
			arr.push(features);
		});
		featureObj.features = arr;
		featureslist.push(featureObj);
	return featureslist;
}

function getRoles(){
	var roles = '';
	$.ajax({
		url : '/ndcmp/api/getRoles',
		type : 'GET',
		async : false,
		cache : false,
		success : function(result) {
			if (result != undefined && result != null && result != '') {
				roles = result;
			}
		}
	});
	return roles;
}

function updateFeature(rid) {
	var featureslist = getSelectedFeatures();
	var obj = new Object();
	roles = getRoles();
	if (featureslist.length > 0) {
		obj.featureList = featureslist;
		obj.roleId = rid;
		var jsonText = JSON.stringify(obj);
		$.ajax({
			url : '/ndcmp/api/updaterolefeatures',
			data : jsonText,
			type : 'POST',
			contentType : 'application/json',
			async : false,
			success : function(result) {
				if (result > 0) {
					window.location.href = "/ndcmp/api/assign?id="+rid;
				} else {
					alert('Failed to update features');
					window.location.href = "/ndcmp/api/listAccess";
				}
			}
		});
	}
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