
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Spitter home</title>
        <META HTTP-EQUIV="REFRESH" CONTENT="3">
    </head>

    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
    <%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

    <body>
        <h1>Welcome to Spitter </h1>

        <ul>          
            <li>
                <a href="/tuote">tuote</a>
            </li>                
            
            <li>
                <a href="/nhl">nhl</a>
            </li> 
            
        </ul>
    </body>
</html>
