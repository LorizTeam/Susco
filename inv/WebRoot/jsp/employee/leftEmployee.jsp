<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ include file="/jsp/admin/css_blue_style.css" %>
<html>
  <head>
	<%@ page contentType="text/html; charset=utf-8" %>
	<link rel="stylesheet" href="includes/site.css">
  </head>
 
<body bottommargin="0" topmargin="0" leftmargin="0" rightmargin="0" >
<script language="JavaScript" src="../admin/tree.js"></script>
<script language="JavaScript" src="../admin/tree_tpl.js"></script>

<script language="JavaScript">
var TREE_ITEMS = [
	['Personal Menu', null,
    ['1.ข้อมูลพนักงาน', 0,
		['1.1 รายชื่อพนักงาน',  		'/inv/employeeListStart.do'],
	  	['1.2 ประเภทพนักงาน',      	'/inv/memberTypeListStart.do'],	  	   
	  	['- ข้อมูลฝ่าย แผนก',      		'/inv/masterTableListStart.do?grpCode=dept&applType=hr'],
	  	
	],

	
   

    ['รายงานต่าง ๆ', 0,
	   	['1 บุคลากร',				'/inv/employeeReport1Start.do'],
	
	],
    ['ออกจากระบบ', 0,
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
					 		<a href="../admin/mainMenu.jsp" target="_parent">
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