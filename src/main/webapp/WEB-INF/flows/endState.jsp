

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>1</h1>
        
        <form>
            <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}" />
            
            <input type="submit" name="_eventId_start" value="start!"/>
        </form>
            
        <a href="${flowExecutionUrl}&_eventId=start">start</a>    
        
        <br />
        
        <a href="${flowExecutionUrl}&_eventId=go2">2</a>  
    </body>
</html>
