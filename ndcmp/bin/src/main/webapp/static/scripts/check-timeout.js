function alertSessionTimeout() 
 {	
	
	var currentMillisecond=new Date().getTime();
	var expiredMilliseconds=currentMillisecond+((warningTimeout*60)*1000);
	
	var sessionReset=confirm("You're session is going to timeout in "+warningTimeout+" minute. Press OK if you want to maintain your session");
   
	if (sessionReset==true){						
		var checkExpiredMilliseconds=new Date().getTime();
		if(checkExpiredMilliseconds>=expiredMilliseconds){
			clearTimeout(warning);
 			clearTimeout(timedout);
 			alert("Sorry Your Session Already Expired");
 			window.location.href="/ndcmp/login";
 			}
 		
		else{
			$.post('/ndcmp/resetSession');
 			clearTimeout(warning);
 			clearTimeout(timedout);
	        resetSessionTimeout();  
	        resetWarningTimeout();
 			}  
 	}
   
	else{    
		var checkExpiredMilliseconds=new Date().getTime();
		if(checkExpiredMilliseconds>=expiredMilliseconds){
			//alert("Session Expired");
			window.location.href="/ndcmp/login";
			}        	  
        }        		  
 }

 function resetSessionTimeout()
 {
	  timedout=window.setTimeout(sessionExpired,timeout*1000);
 }
 
 function resetWarningTimeout()
 {
	 warning=window.setTimeout(alertSessionTimeout,(timeout-(warningTimeout*60))*1000);
 }
 
 function sessionExpired()
 {
	  alert("Session Expired");
	  document.location.href="/ndcmp/login";
 }