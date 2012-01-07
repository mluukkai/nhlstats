

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>start</h1>
        
        <form>
            <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}" />
            
            <input type="submit" name="_eventId_go1" value="Go on!"/>
        </form>
            
        <a href="${flowExecutionUrl}&_eventId=go1">1</a>    
        
        <br />
        
        <a href="${flowExecutionUrl}&_eventId=go2">2</a>  
    </body>
</html>
