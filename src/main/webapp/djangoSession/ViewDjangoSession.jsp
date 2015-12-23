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
        <title>DjangoSession View</title>
    </head>
    <body>
        <f:view>
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            <h1>DjangoSession View</h1>
            <h:form>
                <h:panelGrid columns="2">
                                                           <h:outputText value="sessionKey:"/>
                                                   <h:outputText value="#{djangoSession.entity.sessionKey}" title="sessionKey" />
                                                                                                       <h:outputText value="sessionData:"/>
                                                   <h:outputText value="#{djangoSession.entity.sessionData}" title="sessionData" />
                                                                                                       <h:outputText value="expireDate:"/>
                                                   <h:outputText value="#{djangoSession.entity.expireDate}" title="expireDate" />
                                                                            </h:panelGrid>

                <h:commandButton action="editDjangoSession" value="Edit" />
                <br>
                <h:commandButton action="djangoSessionList" value="Show All"/>
                <br>
            </h:form>
        </f:view>
    </body>
</html>
