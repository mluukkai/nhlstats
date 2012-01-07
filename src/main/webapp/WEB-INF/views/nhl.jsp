
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nhl stats</title>
    </head>
    
    <body>
        <h1>Nhl statistics</h1>
        
                <ul>
            <c:forEach var="player" items="${players}">

                <li>              
                    <c:out value="${player}"/> 
                </li>
            </c:forEach>
        </ul>
        
    </body>
</html>
