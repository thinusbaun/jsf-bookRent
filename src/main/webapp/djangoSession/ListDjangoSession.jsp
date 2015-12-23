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
        <title>DjangoSession List</title>
    </head>
    <body>
        <f:view>
            <h1>DjangoSession List</h1>
            <h:form>
              <h:commandButton action="#{djangoSession.startCreate}" value="Create"/>

              <h:dataTable value='#{djangoSession.allEntities}' var='item' border="1" cellpadding="2" cellspacing="0">
                                                       <h:column>
                      <f:facet name="header">
                      <h:outputText value="sessionKey"/>
                      </f:facet>
                      <h:outputText value="#{item.sessionKey}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="sessionData"/>
                      </f:facet>
                      <h:outputText value="#{item.sessionData}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="expireDate"/>
                      </f:facet>
                      <h:outputText value="#{item.expireDate}"/>
                  </h:column>
                                                   <h:column>
                      <h:commandButton value="View" action="#{djangoSession.startView}">
                          <f:param name="id" value="#{item.sessionKey}"/>
                      </h:commandButton>&nbsp;
                      <h:commandButton value="Edit" action="#{djangoSession.startEdit}">
                          <f:param name="id" value="#{item.sessionKey}"/>
                      </h:commandButton>&nbsp;
                      <h:commandButton value="Delete" action="#{djangoSession.delete}">
                          <f:param name="id" value="#{item.sessionKey}"/>
                      </h:commandButton>
                  </h:column>
              </h:dataTable>
            </h:form>
        </f:view>
    </body>
</html>
