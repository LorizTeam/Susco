<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="com.dtac.inventory.form.MaterialForm" %>
<%

String text_1 ="",text_2="",text_3="",text_4 = "",text_5="",text_6="",text_7 = "",text_8="",text_9="";
String text_10 ="",text_11="",text_12="",text_13 = "",text_14="",text_15="",text_16 = "",text_17="",text_18="";
String text_19 ="",text_20="",text_21="";
 


	

	if(request.getAttribute("error")!=null)
	{
		//page_id=request.getAttribute("error").toString();
		out.print("<SCRIPT LANGUAGE=\"JavaScript\">alert('โปรดกรอกข้อมูลให้ครบทุกช่อง');</script>");
	}
	if(request.getAttribute("barForm")!=null)
	{
		MaterialForm barForm = new MaterialForm();
		//List barcodeList = new ArrayList();
		//barcodeList = (List) request.getAttribute("barcodeList");
		barForm = (MaterialForm) request.getAttribute("barForm");

			text_1 = barForm.getText_1(); 
			text_2 = barForm.getText_2(); 
			text_3 = barForm.getText_3(); 
	        text_4 = barForm.getText_4(); 
			text_5 = barForm.getText_5(); 
			text_6 = barForm.getText_6();
	        text_7 = barForm.getText_7(); 
			text_8 = barForm.getText_8(); 
			text_9 = barForm.getText_9(); 
	        text_10 = barForm.getText_10(); 
			text_11 = barForm.getText_11(); 
			text_12 = barForm.getText_12();
			text_13 = barForm.getText_13(); 
			text_14 = barForm.getText_14(); 
			text_15 = barForm.getText_15(); 
	        text_16 = barForm.getText_16(); 
			text_17 = barForm.getText_17(); 
			text_18 = barForm.getText_18();
	        text_19 = barForm.getText_19(); 
			text_20 = barForm.getText_20(); 
			text_21 = barForm.getText_21(); 
	     
	}
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <html:base />
    
    <title>Barcode Generater</title>
	<link href="css/stylesheet.css" rel="stylesheet" type="text/css" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

    <style type="text/css">
<!--
.style2 {color: #000000}
.style4 {color: #FFFF00}
-->
    </style>
<SCRIPT LANGUAGE="JavaScript">
function check(form) {
	missinginfo = "";
	if (form.text_1.value == "")
	{
		missinginfo += " Barcode No. 1";
	}
	
	if (form.text_2.value == "")
	{
		missinginfo += "\n Barcode No. 2";
	}
	
	if (form.text_3.value == "")
	{
		missinginfo += "\n Barcode No. 3";
	
	
	if (missinginfo != "") 
	{
		missinginfo = "โปรดตรวจสอบข้อมูลใน Field :\n" +
		"------------------------------------\n" +
		missinginfo + "\n------------------------------------";
		alert(missinginfo);
		return false;
	}
	else 
		return true;
}

</script>
  </head>
  
  <body bgcolor="#95b2c3">

  <html:form action="/barcode" method="post">
 
          
         
    <table width="650" border="0"  height="34">
                    <tr>  <td>
                       <input type="submit" value="พิมพ์รายงาน" name="print"> 
                         </td>
                    </tr>
    </table> 
           
                   
    <table width="650" border="0"   class="box_dot">
           <tr>
                      <td align="center" valign="middle" class="tahoma12_bold_black"> 
                      
                          
                            <td   align="center" valign="middle" class="tahoma11_bold_blue">Product1</td>
                            <td align="center" valign="middle"><input name="text_1" size="25"  type="text"   id="text_1" value="<%=text_1%>"></td>
                      
                            <td   align="center" valign="middle" class="tahoma11_bold_blue">Product2</td>
                            <td align="center" valign="middle"><input name="text_2" size="25"  type="text"   id="text_2" value="<%=text_2%>"></td>
                    
                            <td    align="center" valign="middle" class="tahoma11_bold_blue">Product3</td>
                            <td align="center" valign="middle"><input name="text_3" size="25"  type="text"   id="text_3" value="<%=text_3%>"></td>
                         
                     </td> 
            </tr>
            <tr>
            </tr>
             <tr>
            </tr>
             <tr>
            </tr>
             <tr>
            </tr>
                <tr>
                      <td align="center" valign="middle" class="tahoma12_bold_black">     
                            <td   align="center" valign="middle" class="tahoma11_bold_blue">Product4</td>
                            <td align="center" valign="middle"><input name="text_4" size="25"  type="text"   id="text_4" value="<%=text_4%>"></td>
                            <td   align="center" valign="middle" class="tahoma11_bold_blue">Product5</td>
                            <td align="center" valign="middle"><input name="text_5" size="25"  type="text"   id="text_5" value="<%=text_5%>"></td>
                            <td    align="center" valign="middle" class="tahoma11_bold_blue">Product6</td>
                            <td align="center" valign="middle"><input name="text_6" size="25"  type="text"   id="text_6" value="<%=text_6%>"></td>
                      </td> 
              </tr>
               <tr>
            </tr>
             <tr>
            </tr>
             <tr>
            </tr>
             <tr>
            </tr>    
              <tr>
                      <td align="center" valign="middle" class="tahoma12_bold_black">     
                            <td   align="center" valign="middle" class="tahoma11_bold_blue">Product7</td>
                            <td align="center" valign="middle"><input name="text_7" size="25"  type="text"   id="text_7" value="<%=text_7%>"></td>
                            <td   align="center" valign="middle" class="tahoma11_bold_blue">Product8</td>
                            <td align="center" valign="middle"><input name="text_8" size="25"  type="text"   id="text_8" value="<%=text_8%>"></td>
                            <td    align="center" valign="middle" class="tahoma11_bold_blue">Product9</td>
                            <td align="center" valign="middle"><input name="text_9" size="25"  type="text"   id="text_9" value="<%=text_9%>"></td>
                      </td> 
              </tr> 
               <tr>
            </tr>
             <tr>
            </tr>
             <tr>
            </tr>
             <tr>
            </tr>
                              <tr>
                      <td align="center" valign="middle" class="tahoma12_bold_black">     
                            <td   align="center" valign="middle" class="tahoma11_bold_blue">Product10</td>
                            <td align="center" valign="middle"><input name="text_10" size="25"  type="text"   id="text_10" value="<%=text_10%>"></td>
                            <td   align="center" valign="middle" class="tahoma11_bold_blue">Product11</td>
                            <td align="center" valign="middle"><input name="text_11" size="25"  type="text"   id="text_11" value="<%=text_11%>"></td>
                            <td    align="center" valign="middle" class="tahoma11_bold_blue">Product12</td>
                            <td align="center" valign="middle"><input name="text_12" size="25"  type="text"   id="text_12" value="<%=text_12%>"></td>
                      </td> 
              </tr> 
               <tr>
            </tr>
             <tr>
            </tr>
             <tr>
            </tr>
             <tr>
            </tr>
                              <tr>
                      <td align="center" valign="middle" class="tahoma12_bold_black">     
                            <td   align="center" valign="middle" class="tahoma11_bold_blue">Product13</td>
                            <td align="center" valign="middle"><input name="text_13" size="25"  type="text"   id="text_13" value="<%=text_13%>"></td>
                            <td   align="center" valign="middle" class="tahoma11_bold_blue">Product14</td>
                            <td align="center" valign="middle"><input name="text_14" size="25"  type="text"   id="text_14" value="<%=text_14%>"></td>
                            <td    align="center" valign="middle" class="tahoma11_bold_blue">Product15</td>
                            <td align="center" valign="middle"><input name="text_15" size="25"  type="text"   id="text_15" value="<%=text_15%>"></td>
                      </td> 
              </tr> 
               <tr>
            </tr>
             <tr>
            </tr>
             <tr>
            </tr>
             <tr>
            </tr>
                              <tr>
                      <td align="center" valign="middle" class="tahoma12_bold_black">     
                            <td   align="center" valign="middle" class="tahoma11_bold_blue">Product16</td>
                            <td align="center" valign="middle"><input name="text_16" size="25"  type="text"   id="text_16" value="<%=text_16%>"></td>
                            <td   align="center" valign="middle" class="tahoma11_bold_blue">Product17</td>
                            <td align="center" valign="middle"><input name="text_17" size="25"  type="text"   id="text_17" value="<%=text_17%>"></td>
                            <td    align="center" valign="middle" class="tahoma11_bold_blue">Product18</td>
                            <td align="center" valign="middle"><input name="text_18" size="25"  type="text"   id="text_18" value="<%=text_18%>"></td>
                      </td> 
              </tr> 
               <tr>
            </tr>
             <tr>
            </tr>
             <tr>
            </tr>
             <tr>
            </tr>
                              <tr>
                      <td align="center" valign="middle" class="tahoma12_bold_black">     
                            <td   align="center" valign="middle" class="tahoma11_bold_blue">Product19</td>
                            <td align="center" valign="middle"><input name="text_19" size="25"  type="text"   id="text_19" value="<%=text_19%>"></td>
                            <td   align="center" valign="middle" class="tahoma11_bold_blue">Product20</td>
                            <td align="center" valign="middle"><input name="text_20" size="25"  type="text"   id="text_20" value="<%=text_20%>"></td>
                            <td    align="center" valign="middle" class="tahoma11_bold_blue">Product21</td>
                            <td align="center" valign="middle"><input name="text_21" size="25"  type="text"   id="text_21" value="<%=text_21%>"></td>
                      </td> 
              </tr>   
  </table>
  
  
  </html:form>
  </body>
</html:html>
