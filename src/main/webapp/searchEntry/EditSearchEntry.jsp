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
        <title>Edit SearchEntry</title>
    </head>
    <body>
        <f:view>
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            <h1>Edit SearchEntry</h1>
            <h:form>
                <h:panelGrid columns="2">
                                    <h:outputText value="id:"/>
                                                                        <h:inputText value="#{searchEntry.entity.id}" title="id" />
                                                                                <h:outputText value="text:"/>
                                                                        <h:inputText value="#{searchEntry.entity.text}" title="text" />
                                                                                <h:outputText value="user:"/>
                                                                        <h:selectOneMenu value="#{searchEntry.entity.user}"  title="user">
                                <f:selectItems  value="#{authUser.allEntitiesAsSelectedItems}"/>
                            </h:selectOneMenu>
                                                                            </h:panelGrid>

                <h:commandButton action="#{searchEntry.save}" value="Save"/>
                <h:commandButton action="searchEntryList" value="Cancel"/>
                <br>
            </h:form>
        </f:view>
    </body>
</html>
