<!--
  Created by IntelliJ IDEA.
  User: michal
  Date: 23.12.15
  Time: 16:51
  To change this template use File | Settings | File Templates.
-->
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Edit AuthGroupPermissions</title>
    </head>
    <body>
        <f:view>
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            <h1>Edit AuthGroupPermissions</h1>
            <h:form>
                <h:panelGrid columns="2">
                                    <h:outputText value="id:"/>
                                                                        <h:inputText value="#{authGroupPermissions.entity.id}" title="id" />
                                                                                <h:outputText value="groupId:"/>
                                                                        <h:inputText value="#{authGroupPermissions.entity.groupId}" title="groupId" />
                                                                                <h:outputText value="permissionId:"/>
                                                                        <h:inputText value="#{authGroupPermissions.entity.permissionId}" title="permissionId" />
                                                                            </h:panelGrid>

                <h:commandButton action="#{authGroupPermissions.save}" value="Save"/>
                <h:commandButton action="authGroupPermissionsList" value="Cancel"/>
                <br>
            </h:form>
        </f:view>
    </body>
</html>
