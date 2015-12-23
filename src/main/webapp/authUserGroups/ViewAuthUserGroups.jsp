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
        <title>AuthUserGroups View</title>
    </head>
    <body>
        <f:view>
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            <h1>AuthUserGroups View</h1>
            <h:form>
                <h:panelGrid columns="2">
                                                           <h:outputText value="id:"/>
                                                   <h:outputText value="#{authUserGroups.entity.id}" title="id" />
                                                                                                       <h:outputText value="userId:"/>
                                                   <h:outputText value="#{authUserGroups.entity.userId}" title="userId" />
                                                                                                       <h:outputText value="groupId:"/>
                                                   <h:outputText value="#{authUserGroups.entity.groupId}" title="groupId" />
                                                                            </h:panelGrid>

                <h:commandButton action="editAuthUserGroups" value="Edit" />
                <br>
                <h:commandButton action="authUserGroupsList" value="Show All"/>
                <br>
            </h:form>
        </f:view>
    </body>
</html>
