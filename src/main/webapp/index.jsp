<!--
  Created by IntelliJ IDEA.
  User: michal
  Date: 15.12.15
  Time: 08:43
-->
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<html>
<head><title>Generated Entities</title></head>
<body>

<h1>Generated Entities</h1>
<br/>
<f:view>
    <h:form>
                <h:commandLink action="authUserList" value="AuthUser"/>
        <br/>
                <h:commandLink action="adminMessageList" value="AdminMessage"/>
        <br/>
                <h:commandLink action="authorList" value="Author"/>
        <br/>
            </h:form>
</f:view>

</body>
</html>
