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
        <h1>Upload picture</h1>

        <sf:form method="POST" modelAttribute="pic" action="/spittles/pic" enctype="multipart/form-data">
            <fieldset>
                <table >
                    <tbody>                      
                        <tr>
                            <td><label for="image">image</label></td>
                            <td>
                                <input name="image" type="file">
                                <sf:errors path="*" cssClass="error"/>  
                            </td>
                        </tr>                       
                        <tr>
                            <td></td>
                            <td><input name="commit" type="submit" value="confirm" /></td>
                        </tr>
                    </tbody>
                </table>

            </fieldset>            
        </sf:form>

        <p><a href="/">back</a></p>
    </body>
</html>
