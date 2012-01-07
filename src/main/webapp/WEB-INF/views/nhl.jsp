
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

        <table>
            <thead>
                <tr>
                    <th WIDTH="200">name</th>
                    <th WIDTH="100">team</th>
                    <th WIDTH="70">games</th>
                    <th WIDTH="70">goals</th>
                    <th WIDTH="70">assists</th>
                    <th WIDTH="70">points</th>
                    <th WIDTH="70">penalties</th>
                </tr>
            </thead>
            <tbody>

                <c:forEach var="player" items="${players}">
                    <tr>
                        <td> <a href="http://www.nhl.com/ice/player.htm?id=${player.playerId}">
                                <c:out value="${player.name}"/> </a> </td>
                        <td ALIGN=CENTER> ${player.team} </td>
                        <td ALIGN=CENTER> ${player.games} </td>
                        <td ALIGN=CENTER>  ${player.goals} </td>
                        <td ALIGN=CENTER>  ${player.assists} </td>
                        <td ALIGN=CENTER>  ${player.penalties} </td>
                        <td ALIGN=CENTER>  ${player.penalties} </td>
                    </tr>

                </c:forEach>
                </ul

            </tbody>
        </table>

        <a href="/add">add</a>

        <a href="/download">download</a>
    </body>
</html>
