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
        <title>Create AuthUser</title>
    </head>
    <body>
        <f:view>
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            <h1>Create AuthUser</h1>
            <h:form>
                <h:panelGrid columns="2">
                                                            <h:outputText value="id:"/>
                                                    <h:inputText value="#{authUser.entity.id}" title="id" />
                                                                                                        <h:outputText value="password:"/>
                                                    <h:inputText value="#{authUser.entity.password}" title="password" />
                                                                                                        <h:outputText value="lastLogin:"/>
                                                    <h:inputText value="#{authUser.entity.lastLogin}" title="lastLogin" />
                                                                                                        <h:outputText value="isSuperuser:"/>
                                                    <h:inputText value="#{authUser.entity.isSuperuser}" title="isSuperuser" />
                                                                                                        <h:outputText value="username:"/>
                                                    <h:inputText value="#{authUser.entity.username}" title="username" />
                                                                                                        <h:outputText value="firstName:"/>
                                                    <h:inputText value="#{authUser.entity.firstName}" title="firstName" />
                                                                                                        <h:outputText value="lastName:"/>
                                                    <h:inputText value="#{authUser.entity.lastName}" title="lastName" />
                                                                                                        <h:outputText value="email:"/>
                                                    <h:inputText value="#{authUser.entity.email}" title="email" />
                                                                                                        <h:outputText value="isStaff:"/>
                                                    <h:inputText value="#{authUser.entity.isStaff}" title="isStaff" />
                                                                                                        <h:outputText value="isActive:"/>
                                                    <h:inputText value="#{authUser.entity.isActive}" title="isActive" />
                                                                                                        <h:outputText value="dateJoined:"/>
                                                    <h:inputText value="#{authUser.entity.dateJoined}" title="dateJoined" />
                                                                                                                                                    </h:panelGrid>

                <h:commandButton action="#{authUser.create}" value="Save" />
                <h:commandButton action="authUserList" value="Cancel"/>
                <br>
            </h:form>
        </f:view>
    </body>
</html>
