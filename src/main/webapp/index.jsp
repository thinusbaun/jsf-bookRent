<!--
  Created by IntelliJ IDEA.
  User: michal
  Date: 23.12.15
  Time: 16:51
-->
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<html>
<head><title>Generated Entities</title></head>
<body>

<h1>Generated Entities</h1>
<br/>
<f:view>
    <h:form>
                <h:commandLink action="tagList" value="Tag"/>
        <br/>
                <h:commandLink action="rentalList" value="Rental"/>
        <br/>
                <h:commandLink action="djangoSessionList" value="DjangoSession"/>
        <br/>
                <h:commandLink action="djangoMigrationsList" value="DjangoMigrations"/>
        <br/>
                <h:commandLink action="djangoAdminLogList" value="DjangoAdminLog"/>
        <br/>
                <h:commandLink action="bookCopyList" value="BookCopy"/>
        <br/>
                <h:commandLink action="authorList" value="Author"/>
        <br/>
                <h:commandLink action="searchEntryList" value="SearchEntry"/>
        <br/>
                <h:commandLink action="djangoContentTypeList" value="DjangoContentType"/>
        <br/>
                <h:commandLink action="bookTagsList" value="BookTags"/>
        <br/>
                <h:commandLink action="bookAuthorsList" value="BookAuthors"/>
        <br/>
                <h:commandLink action="authUserUserPermissionsList" value="AuthUserUserPermissions"/>
        <br/>
                <h:commandLink action="authUserGroupsList" value="AuthUserGroups"/>
        <br/>
                <h:commandLink action="authPermissionList" value="AuthPermission"/>
        <br/>
                <h:commandLink action="authGroupPermissionsList" value="AuthGroupPermissions"/>
        <br/>
                <h:commandLink action="authGroupList" value="AuthGroup"/>
        <br/>
                <h:commandLink action="adminMessageList" value="AdminMessage"/>
        <br/>
                <h:commandLink action="bookList" value="Book"/>
        <br/>
                <h:commandLink action="authUserList" value="AuthUser"/>
        <br/>
            </h:form>
</f:view>

</body>
</html>
