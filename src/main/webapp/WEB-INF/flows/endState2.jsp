

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>2</h1>
        
        <form>
            <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}" />
            
            <input type="submit" name="_eventId_go2" value="start"/>
        </form>
            
        <a href="${flowExecutionUrl}&_eventId=start">start</a>    
        
        <br />
        
        <a href="${flowExecutionUrl}&_eventId=go1">1</a>  
        
        <br />
        
        <a href="/">back</a>         
        
    </body>
</html>
