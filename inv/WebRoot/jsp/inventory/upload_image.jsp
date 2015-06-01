<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <html:base />
    
<title></title>
<link href="css/stylesheet.css" rel="stylesheet" type="text/css" />
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><style type="text/css">

</style></head>

  <body>
  <form action="file_upload.jsp" method="POST" enctype="multipart/form-data" name="uploadForm">
  <table width="400" height="200" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="23" height="17"><img src="bg_table/mm/mcorner_tl.gif" alt="" width="23" height="17"></td>
      
      <td width="303" background="bg_table/mm/mmid_t.gif"><img src="bg_table/mm/mmid_t.gif" alt="" width="8" height="17"></td>
      <td width="23" height="17"><img src="bg_table/mm/mcorner_tr.gif" alt="" width="23" height="17"></td>
    </tr>
    <tr>
      <td width="23" background="bg_table/mm/mmid_l.gif">&nbsp;</td>
      
      <td bgcolor="#C2DEFB"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td><img src="image/spacer.gif" alt="" width="10" height="10"></td>
        </tr>
      </table>
        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td align="center" valign="middle" class="tahoma12_bold_black"><strong>&nbsp;Upload รูปภาพ</strong><span class="tahoma12_bold_red"><strong> </strong><br></span></td>
        </tr>
      </table>
        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td><img src="image/spacer.gif" alt="" width="10" height="10"></td>
          </tr>
        </table>
        <table width="340" height="49" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#999999" class="box">
          
      
      
          <tr>
            <td valign="middle" bgcolor="#FFFFFF" class="tahoma11_bold_black">Attach Document</td>
            <td height="15" valign="middle" bgcolor="#FFFFFF" class="txtorder"><input name="file" type="file" class="tahoma12_black"></td>
          </tr>
        </table>
        <table width="100%" border="0" align="center" cellpadding="5" cellspacing="0">
          <tr>
            <td align="center" valign="middle"><input name="submit" type="submit" class="tahoma12_bold_black" id="submit" value="   OK   "></td>
          </tr>
        </table></td>
      <td width="23" background="bg_table/mm/mmid_r.gif">&nbsp;</td>
    </tr>
    <tr>
      <td width="23" height="17"><img src="bg_table/mm/mcorner_dl.gif" alt="" width="23" height="17"></td>
      
      <td height="17" background="bg_table/mm/mmid_d.gif"><img src="bg_table/mm/mmid_d.gif" alt="" width="8" height="17"></td>
      <td width="23" height="17"><img src="bg_table/mm/mcorner_dr.gif" alt="" width="23" height="17"></td>
    </tr>
  </table>
  </form>
  </body>
</html:html>
