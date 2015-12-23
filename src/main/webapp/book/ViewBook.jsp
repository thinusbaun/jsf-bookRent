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
<%@taglib prefix="ui" uri="http://java.sun.com/jsf/facelets" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Book View</title>
    </head>
    <body>
        <f:view>
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            <h1>Book View</h1>
            <h:form>
                <h:panelGrid columns="2">
                                                           <h:outputText value="id:"/>
                                                   <h:outputText value="#{book.entity.id}" title="id" />
                                                                                                       <h:outputText value="title:"/>
                                                   <h:outputText value="#{book.entity.title}" title="title" />
                                                                                                       <h:outputText value="isbn:"/>
                                                   <h:outputText value="#{book.entity.isbn}" title="isbn" />
                                                                                                       <h:outputText value="addedDate:"/>
                                                   <h:outputText value="#{book.entity.addedDate}" title="addedDate" />
                    <h:outputText value="#{book.entity.authors}" title="authors"/>
                    <ui:repe
                                                                                
                                                        
                                                        
                                                    </h:panelGrid>

                <h:commandButton action="editBook" value="Edit" />
                <br>
                <h:commandButton action="bookList" value="Show All"/>
                <br>
            </h:form>
        </f:view>
    </body>
</html>
