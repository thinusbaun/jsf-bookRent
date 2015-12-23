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
        <title>Edit AuthGroup</title>
    </head>
    <body>
        <f:view>
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            <h1>Edit AuthGroup</h1>
            <h:form>
                <h:panelGrid columns="2">
                                    <h:outputText value="id:"/>
                                                                        <h:inputText value="#{authGroup.entity.id}" title="id" />
                                                                                <h:outputText value="name:"/>
                                                                        <h:inputText value="#{authGroup.entity.name}" title="name" />
                                                                            </h:panelGrid>

                <h:commandButton action="#{authGroup.save}" value="Save"/>
                <h:commandButton action="authGroupList" value="Cancel"/>
                <br>
            </h:form>
        </f:view>
    </body>
</html>
