<table class="TblOut2" border="0" cellpadding="0" cellspacing="1" width="100%">
    <tbody><tr>
        <td width="8%"><b>User:</b></td>
        <td width="24%">${portletSessionScope.UserInfo.username}</td>
        <td width="12%"><b>Login Date:</b></td>
        <td width="25%">${portletSessionScope.UserInfo.loginDate}</td>
        <td width="9%"><b>Project</b></td>
        <td align="right" width="22%">
        <%-- 
        <select name="cboProjectList" class="SmallCombo" onchange="javascript:doChangeProject('DM','SearchDefect','ViewAllOpenDefects');"><option selected="selected" value="118385">OOPMS</option>
        </select>
        --%>
        
         <form:select id="cboProjectList" path="selProject" multiple="false" size="1" items="${portletSessionScope.CommonInfo.projectMap}">
         </form:select>

        </td>
    </tr>
    <tr>
        <td width="8%"><b>Group:</b></td>
        <td width="24%">${portletSessionScope.UserInfo.group}</td>
        <td width="12%"><b>Position:</b></td>
        <td width="25%">${portletSessionScope.UserInfo.position}</td>
        <td width="9%"><b>Status</b></td>
        <td align="right" width="22%"><select name="cboProjectStatus" class="SmallCombo" onchange="javascript:doChangeProject('DM','SearchDefect','ViewAllOpenDefects');"><option selected="selected" value="0">On-going</option>
        </select></td>

    </tr>
</tbody></table>