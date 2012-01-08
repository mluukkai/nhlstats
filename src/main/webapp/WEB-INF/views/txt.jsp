
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/plain; charset=UTF-8">
        <title>Nhl stats</title>
    </head>

    <body>

        <c:forEach var="player" items="${players}">
            ${player.asText} <br/>
        </c:forEach>

    </body>
</html>
