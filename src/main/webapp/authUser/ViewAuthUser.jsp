<!--
  Created by IntelliJ IDEA.
  User: michal
  Date: 15.12.15
  Time: 08:43
  To change this template use File | Settings | File Templates.
-->
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>AuthUser View</title>
    </head>
    <body>
        <f:view>
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            <h1>AuthUser View</h1>
            <h:form>
                <h:panelGrid columns="2">
                                                           <h:outputText value="id:"/>
                                                   <h:outputText value="#{authUser.entity.id}" title="id" />
                                                                                                       <h:outputText value="password:"/>
                                                   <h:outputText value="#{authUser.entity.password}" title="password" />
                                                                                                       <h:outputText value="isSuperuser:"/>
                                                   <h:outputText value="#{authUser.entity.isSuperuser}" title="isSuperuser" />
                                                                                                       <h:outputText value="username:"/>
                                                   <h:outputText value="#{authUser.entity.username}" title="username" />
                                                                                                       <h:outputText value="firstName:"/>
                                                   <h:outputText value="#{authUser.entity.firstName}" title="firstName" />
                                                                                                       <h:outputText value="lastName:"/>
                                                   <h:outputText value="#{authUser.entity.lastName}" title="lastName" />
                                                                                                       <h:outputText value="email:"/>
                                                   <h:outputText value="#{authUser.entity.email}" title="email" />
                                                                                                       <h:outputText value="isStaff:"/>
                                                   <h:outputText value="#{authUser.entity.isStaff}" title="isStaff" />
                                                                                                       <h:outputText value="isActive:"/>
                                                   <h:outputText value="#{authUser.entity.isActive}" title="isActive" />
                                                                            </h:panelGrid>

                <h:commandButton action="editAuthUser" value="Edit" />
                <br>
                <h:commandButton action="authUserList" value="Show All"/>
                <br>
            </h:form>
        </f:view>
    </body>
</html>
