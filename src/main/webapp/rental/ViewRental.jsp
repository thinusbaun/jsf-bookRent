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
        <title>Rental View</title>
    </head>
    <body>
        <f:view>
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            <h1>Rental View</h1>
            <h:form>
                <h:panelGrid columns="2">
                                                           <h:outputText value="id:"/>
                                                   <h:outputText value="#{rental.entity.id}" title="id" />
                                                                                                       <h:outputText value="rentalDate:"/>
                                                   <h:outputText value="#{rental.entity.rentalDate}" title="rentalDate" />
                                                                                                       <h:outputText value="returnDate:"/>
                                                   <h:outputText value="#{rental.entity.returnDate}" title="returnDate" />
                                                                                                       <h:outputText value="bookCopy:"/>
                                                   <h:selectOneMenu value="#{rental.entity.bookCopy}"  title="bookCopy">
                                <f:selectItems  value="#{bookCopy.allEntitiesAsSelectedItems}"/>
                            </h:selectOneMenu>
                                                                                                       <h:outputText value="user:"/>
                                                   <h:selectOneMenu value="#{rental.entity.user}"  title="user">
                                <f:selectItems  value="#{authUser.allEntitiesAsSelectedItems}"/>
                            </h:selectOneMenu>
                                                                            </h:panelGrid>

                <h:commandButton action="editRental" value="Edit" />
                <br>
                <h:commandButton action="rentalList" value="Show All"/>
                <br>
            </h:form>
        </f:view>
    </body>
</html>
