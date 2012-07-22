<%@include file="/WEB-INF/jsp/include.jsp" %>  <!-- ************************************  Form Body  ************************************ -->
    <form method="post" action="DMSServlet" name="frmProjectStageList">
      <!-- ************************************  Standard Screen Identification  ************************************ -->
        <input name="hidActionDetail" value="" type="hidden">
        <input name="hidAction" value="" type="hidden">

      <!-- ************************************  Form variables  ************************************ -->
            <table class="TblOut2" border="0" width="100%">
                <tbody><tr>
                  <td width="9%"><b>User:</b> </td>
                  <td width="25%">${portletSessionScope.UserInfo.username}</td>

                  <td width="10%"><b>Group:</b></td>
                  <td width="28%">${portletSessionScope.UserInfo.group}</td>

                  <td width="12%"><b>Login Date:</b> </td>
                  <td width="17%">${portletSessionScope.UserInfo.loginDate}</td>
                </tr>
             </tbody></table>
          <p></p>
          <table border="0" cellpadding="0" cellspacing="0" width="100%">
            <tbody><tr>
              <td width="3%"><b>ID</b></td>
              <td width="17%"><input name="txtID" size="20" style="width: 121px; height: 20px;" onkeypress="javascript:numberAllowed()" maxlength="5" type="text"></td>
              <td width="5%"><b> Name</b></td>
              <td width="34%"><input name="txtName" size="40" maxlength="30" type="text"></td>
              <td align="left" width="18%">
                <input value="AddNew" name="AddNewProjectStage" class="button" onclick="javascript:doAddNew()" type="button"></td>
            </tr>
          </tbody></table>

<!-- Preparing to update -->
            <input name="hidID" value="" type="hidden">
            <input name="hidName" value="" type="hidden">
          <p></p>
          <table bgcolor="#000000" border="0" cellpadding="0" cellspacing="1" width="100%">
              <tbody><tr class="Row0">
                <td height="19" width="3%"><input name="allbox" value="CheckAll" onclick="JavaScript: CheckAll();" type="checkbox"></td>
                <td align="center" height="19" width="15%">ID</td>
                <td align="center" height="19" width="82%">Name</td>
              </tr>

            
                            <tr class="Row2">
                            <td width="3%"><input name="checkBox" value="1" type="checkbox"></td>
                            <td width="15%">&nbsp;1</td>
                            <td width="82%">&nbsp;<a href="javascript:doUpdate('1',%20'1-Initiation-Thi%C3%A1%C2%BA%C2%BFt%20l%C3%A1%C2%BA%C2%ADp')"><font color="#0000A0" face="Verdana">1-Initiation-Thiết lập</font></a></td>
                            </tr>
                      
                            <tr class="Row1">
                            <td width="3%"><input name="checkBox" value="2" type="checkbox"></td>
                            <td width="15%">&nbsp;2</td>
                            <td width="82%">&nbsp;<a href="javascript:doUpdate('2',%20'2-Definition')"><font color="#0000A0" face="Verdana">2-Definition</font></a></td>
                            </tr>
        </tbody></table>

      <!-- ************************************  Form Code Here  ************************************ -->
        <p><input name="DeleteProjectStage" class="button" onclick="javascript:doDelete()" value="Delete" type="button"></p>

</form>