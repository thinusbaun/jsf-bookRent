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
        <title>DjangoAdminLog List</title>
    </head>
    <body>
        <f:view>
            <h1>DjangoAdminLog List</h1>
            <h:form>
              <h:commandButton action="#{djangoAdminLog.startCreate}" value="Create"/>

              <h:dataTable value='#{djangoAdminLog.allEntities}' var='item' border="1" cellpadding="2" cellspacing="0">
                                                       <h:column>
                      <f:facet name="header">
                      <h:outputText value="id"/>
                      </f:facet>
                      <h:outputText value="#{item.id}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="actionTime"/>
                      </f:facet>
                      <h:outputText value="#{item.actionTime}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="objectId"/>
                      </f:facet>
                      <h:outputText value="#{item.objectId}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="objectRepr"/>
                      </f:facet>
                      <h:outputText value="#{item.objectRepr}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="actionFlag"/>
                      </f:facet>
                      <h:outputText value="#{item.actionFlag}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="changeMessage"/>
                      </f:facet>
                      <h:outputText value="#{item.changeMessage}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="contentTypeId"/>
                      </f:facet>
                      <h:outputText value="#{item.contentTypeId}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="userId"/>
                      </f:facet>
                      <h:outputText value="#{item.userId}"/>
                  </h:column>
                                                   <h:column>
                      <h:commandButton value="View" action="#{djangoAdminLog.startView}">
                          <f:param name="id" value="#{item.id}"/>
                      </h:commandButton>&nbsp;
                      <h:commandButton value="Edit" action="#{djangoAdminLog.startEdit}">
                          <f:param name="id" value="#{item.id}"/>
                      </h:commandButton>&nbsp;
                      <h:commandButton value="Delete" action="#{djangoAdminLog.delete}">
                          <f:param name="id" value="#{item.id}"/>
                      </h:commandButton>
                  </h:column>
              </h:dataTable>
            </h:form>
        </f:view>
    </body>
</html>
