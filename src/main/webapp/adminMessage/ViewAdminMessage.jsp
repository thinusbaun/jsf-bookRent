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
        <title>AdminMessage View</title>
    </head>
    <body>
        <f:view>
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            <h1>AdminMessage View</h1>
            <h:form>
                <h:panelGrid columns="2">
                                                           <h:outputText value="id:"/>
                                                   <h:outputText value="#{adminMessage.entity.id}" title="id" />
                                                                                                       <h:outputText value="content:"/>
                                                   <h:outputText value="#{adminMessage.entity.content}" title="content" />
                                                                                                       <h:outputText value="date:"/>
                                                   <h:outputText value="#{adminMessage.entity.date}" title="date" />
                                                                            </h:panelGrid>

                <h:commandButton action="editAdminMessage" value="Edit" />
                <br>
                <h:commandButton action="adminMessageList" value="Show All"/>
                <br>
            </h:form>
        </f:view>
    </body>
</html>
