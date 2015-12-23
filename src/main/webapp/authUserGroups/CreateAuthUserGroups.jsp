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
        <title>Create AuthUserGroups</title>
    </head>
    <body>
        <f:view>
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            <h1>Create AuthUserGroups</h1>
            <h:form>
                <h:panelGrid columns="2">
                                                            <h:outputText value="id:"/>
                                                    <h:inputText value="#{authUserGroups.entity.id}" title="id" />
                                                                                                        <h:outputText value="userId:"/>
                                                    <h:inputText value="#{authUserGroups.entity.userId}" title="userId" />
                                                                                                        <h:outputText value="groupId:"/>
                                                    <h:inputText value="#{authUserGroups.entity.groupId}" title="groupId" />
                                                                            </h:panelGrid>

                <h:commandButton action="#{authUserGroups.create}" value="Save" />
                <h:commandButton action="authUserGroupsList" value="Cancel"/>
                <br>
            </h:form>
        </f:view>
    </body>
</html>
