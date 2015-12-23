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
        <title>DjangoMigrations View</title>
    </head>
    <body>
        <f:view>
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            <h1>DjangoMigrations View</h1>
            <h:form>
                <h:panelGrid columns="2">
                                                           <h:outputText value="id:"/>
                                                   <h:outputText value="#{djangoMigrations.entity.id}" title="id" />
                                                                                                       <h:outputText value="app:"/>
                                                   <h:outputText value="#{djangoMigrations.entity.app}" title="app" />
                                                                                                       <h:outputText value="name:"/>
                                                   <h:outputText value="#{djangoMigrations.entity.name}" title="name" />
                                                                                                       <h:outputText value="applied:"/>
                                                   <h:outputText value="#{djangoMigrations.entity.applied}" title="applied" />
                                                                            </h:panelGrid>

                <h:commandButton action="editDjangoMigrations" value="Edit" />
                <br>
                <h:commandButton action="djangoMigrationsList" value="Show All"/>
                <br>
            </h:form>
        </f:view>
    </body>
</html>
