
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


        <h3>Latest spittles:</h3>

        <ul>
            <c:forEach var="spittle" items="${spittles}">

                <s:url value="/spitters/spittles?spitter={spitterName}" var="spitter_url"> 
                    <s:param name="spitterName" value="${spittle.spitter.name}"/>
                </s:url>

                <li>
                    <a href="${spitter_url}"> <c:out value="${spittle.spitter.name}"/></a>                
                    <c:out value="${spittle.content}"/> 
                </li>
            </c:forEach>
        </ul>

        <h3>Actions</h3>



        <ul>
            <c:if test="${session eq true}">
                <li>
                    <a href="/spitters?my">mypage</a>
                </li>  
                <li>
                    <a href="/spitters?logout">logout</a>
                </li>   

            </c:if>
            <c:if test="${session eq false}">
                <li>
                    <a href="/spitters?login">login</a>
                </li>           
                <li>
                    <a href="/spitters?new">register</a>
                </li>
            </c:if>   
            <li>
                <a href="/spitters">spitters</a>
            </li> 
            
            <li>
                <a href="/reset">reset</a>
            </li>             

            <li>
                <a href="/tuote">tuote</a>
            </li>                
            
        </ul>
    </body>
</html>
