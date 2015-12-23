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
        <title>Edit BookAuthors</title>
    </head>
    <body>
        <f:view>
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            <h1>Edit BookAuthors</h1>
            <h:form>
                <h:panelGrid columns="2">
                                    <h:outputText value="id:"/>
                                                                        <h:inputText value="#{bookAuthors.entity.id}" title="id" />
                                                                                <h:outputText value="bookId:"/>
                                                                        <h:inputText value="#{bookAuthors.entity.bookId}" title="bookId" />
                                                                                <h:outputText value="authorId:"/>
                                                                        <h:inputText value="#{bookAuthors.entity.authorId}" title="authorId" />
                                                                            </h:panelGrid>

                <h:commandButton action="#{bookAuthors.save}" value="Save"/>
                <h:commandButton action="bookAuthorsList" value="Cancel"/>
                <br>
            </h:form>
        </f:view>
    </body>
</html>
