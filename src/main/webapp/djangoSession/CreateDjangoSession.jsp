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
        <title>Create DjangoSession</title>
    </head>
    <body>
        <f:view>
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            <h1>Create DjangoSession</h1>
            <h:form>
                <h:panelGrid columns="2">
                                                            <h:outputText value="sessionKey:"/>
                                                    <h:inputText value="#{djangoSession.entity.sessionKey}" title="sessionKey" />
                                                                                                        <h:outputText value="sessionData:"/>
                                                    <h:inputText value="#{djangoSession.entity.sessionData}" title="sessionData" />
                                                                                                        <h:outputText value="expireDate:"/>
                                                    <h:inputText value="#{djangoSession.entity.expireDate}" title="expireDate" />
                                                                            </h:panelGrid>

                <h:commandButton action="#{djangoSession.create}" value="Save" />
                <h:commandButton action="djangoSessionList" value="Cancel"/>
                <br>
            </h:form>
        </f:view>
    </body>
</html>
