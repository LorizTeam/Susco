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
	['Inventory Menu', null,
    ['1.ข้อมูลพื้นฐาน', 0,
	  	['- หน่วยสินค้า',      			'/inv/masterTableListStart.do?grpCode=unit&applType=mm'],  	   
	  	['- แบรนด์สินค้า',      			'/inv/masterTableListStart.do?grpCode=bran&applType=mm'],
	    ['- Product',    			'/inv/productListStart.do'], 
    	['- Customer',    			'/inv/customerListStart.do'], 
	  	['- Vender',    			'/inv/venderListStart.do'],
	  	['- POS',  					'/inv/memberSearchstart.do'],
	 // 	['- POS',  					'http://localhost:8080/rpc/mindex.jsp'],   	
	],
    ['2.รับเข้าสินค้า', 0,
		 
		 
	  	['2.1 คลังสินค้า', 				'/inv/warehouseListStart.do'],
		['2.2 รับเข้าสินค้า ',     			'/inv/materialListStart.do'],
	    ['2.3 ใบเปลี่ยนสินค้า',				'/inv/materialChangeListStart.do'],
	   	['2.4 ใบคืนสินค้า',				'/inv/materialRTNListStart.do'],
		['2.5 upload รูปสินค้า ',     			'upload_image.jsp'],
	 
	],
 	  	
	// comment
	['3.เบิกจ่าย สินค้า', 0,
	 
	   	['3.1  เบิกสินค้า',			'/inv/materialTakeListStart.do'],
	   	['3.2  เบิกยืมสินค้า',		    '/inv/materialBRWListStart.do'], 
	   	['3.3  จองสินค้า',           '/inv/materialLOCKSetup.do'], 
	 	 
	  
	],
    ['4.รายงานต่าง ๆ', 0,
        ['4.0 Barcode ',			'input_number.jsp'], 
    	['4.1 การรับสินค้า',			'/inv/actsrcrepreceive.do'], 
	   	['4.2 การส่งสินค้า (จำนวนชิ้น/จำนวนเงิน)', ' /inv/actsrcrepstocktran42.do'],
	   	['4.3 การเปลี่ยนสินค้า',		' /inv/actsrcrepstocktran43.do'],
	   	['4.4 การคืนสินค้า ',		'/inv/actsrcrepstocktran44.do '],
	   	['4.5 การยืมสินค้า',			' /inv/actsrcrepstocktran45.do'],	 
	   	['4.6 Stock on hand',		'/inv/actsrcrepstocktran46.do'],
   	  	['4.7 การจองสินค้า',			' /inv/actsrcrepstocktran47.do'],  
		
	  
 
	],
    ['ออกจากระบบ', 0,
	   	['ออกจากระบบ',  				'/inv/signout.do'], 
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