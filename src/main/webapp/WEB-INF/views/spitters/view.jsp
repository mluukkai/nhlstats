<%-- 
    Document   : view
    Created on : 2.1.2012, 1:28:03
    Author     : mluukkai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <meta http-equiv="Expires" content="Fri, Jun 12 1981 08:20:00 GMT">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Cache-Control" content="no-cache">
    </head>

    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
    <%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

    <body>
        <c:choose>
            <c:when test="${me eq true}">
                <h1>Welcome ${spitter}</h1>
            </c:when>
            <c:otherwise>
                <h1>Profile of ${spitter}</h1>
            </c:otherwise>
        </c:choose>

                            
        <IMG src="https://s3.amazonaws.com/springtestbucket/${spitter.id}.jpg" alt="no image">                

        <c:if test="${me eq true}">            
            <h3>new spittle</h3>

            <sf:form method="PUT" modelAttribute="spittle" action="/spittles">
                <fieldset>
                    <table>
                        <tbody>                 
                            <tr>
                                <td><sf:input path="content" id="content" size="80"/></td>
                            </tr>                                                                                                            

                            <tr>
                                <td><input name="commit" type="submit" value="submit spittle" /></td>
                            </tr>
                        </tbody>
                    </table>

                </fieldset>            
            </sf:form>

        </c:if>

        <c:if test="${me eq true}">
            <h3>my spittles</h3>
        </c:if>        
        <c:if test="${me eq false}">
            <h3>spittles of ${spitter}</h3>
        </c:if>               

        <ul>
            <c:forEach var="spittle" items="${spittleList}">
                <li><c:out value="${spittle.content}"/> </li>
            </c:forEach>
        </ul>

        <h3>actions</h3>
        <ul>
            <c:if test="${me eq true}">
                <li>
                    <a href="/spittles/pic">upload picture</a>
                </li>  
                <li>
                    <a href="/spitters?logout">logout</a>
                </li>  
            </c:if>              
            <li><a href="/">back</a></li>

        </ul>
    </body>
</html>
