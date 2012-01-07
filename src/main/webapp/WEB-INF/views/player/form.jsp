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
        <title>Player addition</title>
    </head>

    <%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

    <body>                
        <h1>Add new player</h1>

        <sf:form method="POST" modelAttribute="player" action="/add" >
            <fieldset>
                <table >
                    <tbody>
                        <tr>
                            <td><label for="name">Name </label></td>
                            <td><sf:input path="name" id="name" size="30"/>
                                <sf:errors path="name" cssClass="error"/>  
                            </td>
                        </tr>
                        <tr>
                            <td><label for="team">Team </label></td>
                            <td><sf:input path="team" id="team" size="3"/>
                            </td>
                        </tr>                        
                        <tr>
                            <td><label for="games">Games </label></td>
                            <td><sf:input path="games" id="games" size="3"/>
                            </td>
                        </tr>        
                        <tr>
                            <td><label for="goals">Goals </label></td>
                            <td><sf:input path="goals" id="goals" size="3"/>
                            </td>
                        </tr>  
                        <tr>
                            <td><label for="assists">Assists </label></td>
                            <td><sf:input path="assists" id="assists" size="3"/>
                            </td>
                        </tr>  
                        <tr>
                            <td><label for="penalties">Penalties </label></td>
                            <td><sf:input path="penalties" id="penalties" size="3"/>
                            </td>
                        </tr>    
                        <tr>
                            <td><label for="playerId">Id </label></td>
                            <td><sf:input path="playerId" id="playerId" size="3"/>
                            </td>
                        </tr>                          
                       
                        <tr>
                            <td></td>
                            <td><input name="commit" type="submit" value="add" /></td>
                        </tr>
                    </tbody>
                </table>

            </fieldset>            
        </sf:form>
        
        <p><a href="/">back</a></p>
    </body>
</html>
