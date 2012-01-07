<%-- 
    Document   : home
    Created on : 1.1.2012, 17:01:04
    Author     : mluukkai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

    <body>
        <h1>All Spitters</h1>

        <ul>
            <c:forEach var="spitter" items="${spitterList}">
                <s:url value="/spitters/{spitterName}" var="spitter_url"> 
                    <s:param name="spitterName" value="${spitter.name}"/>
                </s:url>

                <li> <a href="${spitter_url}"> <c:out value="${spitter.name}"/> </a></li>
            </c:forEach>
        </ul>              

        <p><a href="/">back</a></p>
    </body>
</html>
