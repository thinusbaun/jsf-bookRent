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
        <title>Create DjangoAdminLog</title>
    </head>
    <body>
        <f:view>
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            <h1>Create DjangoAdminLog</h1>
            <h:form>
                <h:panelGrid columns="2">
                                                            <h:outputText value="id:"/>
                                                    <h:inputText value="#{djangoAdminLog.entity.id}" title="id" />
                                                                                                        <h:outputText value="actionTime:"/>
                                                    <h:inputText value="#{djangoAdminLog.entity.actionTime}" title="actionTime" />
                                                                                                        <h:outputText value="objectId:"/>
                                                    <h:inputText value="#{djangoAdminLog.entity.objectId}" title="objectId" />
                                                                                                        <h:outputText value="objectRepr:"/>
                                                    <h:inputText value="#{djangoAdminLog.entity.objectRepr}" title="objectRepr" />
                                                                                                        <h:outputText value="actionFlag:"/>
                                                    <h:inputText value="#{djangoAdminLog.entity.actionFlag}" title="actionFlag" />
                                                                                                        <h:outputText value="changeMessage:"/>
                                                    <h:inputText value="#{djangoAdminLog.entity.changeMessage}" title="changeMessage" />
                                                                                                        <h:outputText value="contentTypeId:"/>
                                                    <h:inputText value="#{djangoAdminLog.entity.contentTypeId}" title="contentTypeId" />
                                                                                                        <h:outputText value="userId:"/>
                                                    <h:inputText value="#{djangoAdminLog.entity.userId}" title="userId" />
                                                                            </h:panelGrid>

                <h:commandButton action="#{djangoAdminLog.create}" value="Save" />
                <h:commandButton action="djangoAdminLogList" value="Cancel"/>
                <br>
            </h:form>
        </f:view>
    </body>
</html>
