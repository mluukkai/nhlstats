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

    <body>
        <h1>Spittles of ${spitter}</h1>

        <ul>
            <c:forEach var="spittle" items="${spittleList}">
                <li><c:out value="${spittle.content}"/> </li>
            </c:forEach>
        </ul>
        
        <p><a href="/">back</a></p>        
    </body>
</html>
