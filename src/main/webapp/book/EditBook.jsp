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
        <title>Edit Book</title>
    </head>
    <body>
        <f:view>
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            <h1>Edit Book</h1>
            <h:form>
                <h:panelGrid columns="2">
                                    <h:outputText value="id:"/>
                                                                        <h:inputText value="#{book.entity.id}" title="id" />
                                                                                <h:outputText value="title:"/>
                                                                        <h:inputText value="#{book.entity.title}" title="title" />
                                                                                <h:outputText value="isbn:"/>
                                                                        <h:inputText value="#{book.entity.isbn}" title="isbn" />
                                                                                <h:outputText value="addedDate:"/>
                                                                        <h:inputText value="#{book.entity.addedDate}" title="addedDate" />
                                                                                <h:outputText value="authors:"/>
                    
                                                        <h:outputText value="tags:"/>
                    
                                                        <h:outputText value="copies:"/>
                    
                                                    </h:panelGrid>

                <h:commandButton action="#{book.save}" value="Save"/>
                <h:commandButton action="bookList" value="Cancel"/>
                <br>
            </h:form>
        </f:view>
    </body>
</html>
