//======================== Begin XML HTTP ========================
function getHTTPObject() { 
	var xmlhttp; 

		if(window.XMLHttpRequest){ 
		xmlhttp = new XMLHttpRequest(); 
	
	}else if (window.ActiveXObject){ 
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP"); 
		if (!xmlhttp){ 
			xmlhttp = new ActiveXObject("Msxml2.XMLHTTP"); 
		} 

	} 
	return xmlhttp; 
} 

var http = getHTTPObject(); // We create the HTTP Object 
//======================== End XML HTTP ========================



function init(){
//	document.getElementById('memberid').focus();
//	document.getElementById('MainTable').style.height = '1000px';
	document.getElementById('tabEmployee').style.visibility = 'visible';
	document.getElementById('tabProcess').style.visibility = 'hidden';
 	document.getElementById('tabChange').style.visibility = 'hidden';
	document.getElementById('tabOther').style.visibility = 'hidden';
}



function tab(tabName){

	
	document.getElementById('tabEmployee').style.visibility = 'hidden';
	document.getElementById('tabProcess').style.visibility = 'hidden';
 	document.getElementById('tabChange').style.visibility = 'hidden';
	document.getElementById('tabOther').style.visibility = 'hidden';
 
	document.getElementById(tabName).style.visibility = 'visible';
	
	document.getElementById('aEmployee').innerHTML = '<font color=#008000>พนักงาน</font>';
	document.getElementById('aProcess').innerHTML = '<font color=#008000>การดำเนินงาน</font>';
    document.getElementById('aChange').innerHTML = '<font color=#008000>การเปลี่ยนแปลง</font>';
	document.getElementById('aOther').innerHTML = '<font color=#008000>ทั่วไป</font>';
 


	//Set MainTable Height
	if(tabName == 'tabEducation'){
		document.getElementById('aEducation').innerHTML = '<font color=orange>พนักงาน</font>';
	 		
	}else if(tabName == 'tabProcess'){
		document.getElementById('aProcess').innerHTML = '<font color=orange>การดำเนินงาน</font>'; 
		
	} else if(tabName == 'tabChange'){
		document.getElementById('aChange').innerHTML = '<font color=orange>การเปลี่ยนแปลง</font>'; 
		
	} else if(tabName == 'tabOther'){
		document.getElementById('aOther').innerHTML = '<font color=orange>ทั่วไป</font>'; 
		
	} 
	
}








