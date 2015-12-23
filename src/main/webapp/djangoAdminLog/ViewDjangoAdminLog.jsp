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
        <title>DjangoAdminLog View</title>
    </head>
    <body>
        <f:view>
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            <h1>DjangoAdminLog View</h1>
            <h:form>
                <h:panelGrid columns="2">
                                                           <h:outputText value="id:"/>
                                                   <h:outputText value="#{djangoAdminLog.entity.id}" title="id" />
                                                                                                       <h:outputText value="actionTime:"/>
                                                   <h:outputText value="#{djangoAdminLog.entity.actionTime}" title="actionTime" />
                                                                                                       <h:outputText value="objectId:"/>
                                                   <h:outputText value="#{djangoAdminLog.entity.objectId}" title="objectId" />
                                                                                                       <h:outputText value="objectRepr:"/>
                                                   <h:outputText value="#{djangoAdminLog.entity.objectRepr}" title="objectRepr" />
                                                                                                       <h:outputText value="actionFlag:"/>
                                                   <h:outputText value="#{djangoAdminLog.entity.actionFlag}" title="actionFlag" />
                                                                                                       <h:outputText value="changeMessage:"/>
                                                   <h:outputText value="#{djangoAdminLog.entity.changeMessage}" title="changeMessage" />
                                                                                                       <h:outputText value="contentTypeId:"/>
                                                   <h:outputText value="#{djangoAdminLog.entity.contentTypeId}" title="contentTypeId" />
                                                                                                       <h:outputText value="userId:"/>
                                                   <h:outputText value="#{djangoAdminLog.entity.userId}" title="userId" />
                                                                            </h:panelGrid>

                <h:commandButton action="editDjangoAdminLog" value="Edit" />
                <br>
                <h:commandButton action="djangoAdminLogList" value="Show All"/>
                <br>
            </h:form>
        </f:view>
    </body>
</html>
