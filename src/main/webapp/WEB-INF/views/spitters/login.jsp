<%-- 
    Document   : edit
    Created on : 2.1.2012, 0:37:41
    Author     : mluukkai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

    <body>                
        <h1>Login</h1>

        <sf:form method="POST" modelAttribute="spitter" action="/spitters/login" >
            <fieldset>
                <table >
                    <tbody>
                        <tr>
                            <td><label for="name">Name </label></td>
                            <td><sf:input path="name" id="name" size="15"/>
                                <sf:errors path="name" cssClass="error"/>  
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input name="commit" type="submit" value="login" /></td>
                        </tr>
                    </tbody>
                </table>

            </fieldset>            
        </sf:form>
        
        <p><a href="/">back</a></p>
    </body>
</html>
