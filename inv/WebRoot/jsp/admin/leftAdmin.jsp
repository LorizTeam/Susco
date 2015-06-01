<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ include file="/jsp/admin/css_blue_style.css" %>
<html>
  <head>
	<%@ page contentType="text/html; charset=utf-8" %>
	<link rel="stylesheet" href="includes/site.css">
  </head>
 
<body bottommargin="0" topmargin="0" leftmargin="0" rightmargin="0" >

<script language="JavaScript" src="tree.js"></script>
<script language="JavaScript" src="tree_tpl.js"></script>

<script language="JavaScript">
var TREE_ITEMS = [
	['Admin Menu', null,
    ['1.การทำงาน', 0,
		['1.1 กำหนดสิทธิ์การใช้งาน',  		'/inv/memberListStart.do'],
	  	['1.2 รายงานสิทธิ์การใช้งานระบบ',	'/inv/authReport1Start.do'],	  	   
	  	['1.3 รายงานการใช้งานระบบ',		'/inv/loginReport1Start.do'],
	],
    ['2.ข้อมูลต่าง ๆ', 0,
		
		['- สาขาบริษัท',      		'/inv/masterTableListStart.do?grpCode=offi&applType=ad'],
    	
	],
	
    ['ออกจากระบบ', 0,
    	['เปลี่ยนรหัสผ่าน',      	'/inv/changePasswordSetup.do'],
	   	['ออกจากระบบ',  		'/inv/signout.do'], 
	   		
	   
	],
	]
];
</script>
    <table cellpadding="0" cellspacing="0" border="0" width="100%" height="100%">
    <tr valign="top"><td bgcolor="#DAC987">
		 <table cellpadding="0" cellspacing="1" border="0" width="100%" height="100%">
         <tr valign="top"><td bgcolor="#DAC987"> <br/><br/>
			 <table>
				 	 <tr valign="top">
					 	<td class="title">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 		<a href="mainMenu.jsp" target="_parent">
					 			<img src="../image/Susco.png" width="100" height="90" >
					 		</a> 
					 	</td>
					 </tr>
				
				   <tr>
						<td><script language="JavaScript">
								<!--//
									new tree (TREE_ITEMS, TREE_TPL);
								//-->
								</script>
						</td>
  				 </tr>
			</table>					        
			</td>
		</tr>
        </table>
        </td>
    </tr>
    </table>
</body>
</html>