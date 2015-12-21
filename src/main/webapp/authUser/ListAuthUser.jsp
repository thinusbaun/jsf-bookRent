<!--
  Created by IntelliJ IDEA.
  User: michal
  Date: 15.12.15
  Time: 08:43
  To change this template use File | Settings | File Templates.
-->
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>AuthUser List</title>
    </head>
    <body>
        <f:view>
            <h1>AuthUser List</h1>
            <h:form>
              <h:commandButton action="#{authUser.startCreate}" value="Create"/>

              <h:dataTable value='#{authUser.allEntities}' var='item' border="1" cellpadding="2" cellspacing="0">
                                                       <h:column>
                      <f:facet name="header">
                      <h:outputText value="id"/>
                      </f:facet>
                      <h:outputText value="#{item.id}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="password"/>
                      </f:facet>
                      <h:outputText value="#{item.password}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="isSuperuser"/>
                      </f:facet>
                      <h:outputText value="#{item.isSuperuser}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="username"/>
                      </f:facet>
                      <h:outputText value="#{item.username}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="firstName"/>
                      </f:facet>
                      <h:outputText value="#{item.firstName}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="lastName"/>
                      </f:facet>
                      <h:outputText value="#{item.lastName}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="email"/>
                      </f:facet>
                      <h:outputText value="#{item.email}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="isStaff"/>
                      </f:facet>
                      <h:outputText value="#{item.isStaff}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="isActive"/>
                      </f:facet>
                      <h:outputText value="#{item.isActive}"/>
                  </h:column>
                                                   <h:column>
                      <h:commandButton value="View" action="#{authUser.startView}">
                          <f:param name="id" value="#{item.id}"/>
                      </h:commandButton>&nbsp;
                      <h:commandButton value="Edit" action="#{authUser.startEdit}">
                          <f:param name="id" value="#{item.id}"/>
                      </h:commandButton>&nbsp;
                      <h:commandButton value="Delete" action="#{authUser.delete}">
                          <f:param name="id" value="#{item.id}"/>
                      </h:commandButton>
                  </h:column>
              </h:dataTable>
            </h:form>
        </f:view>
    </body>
</html>
