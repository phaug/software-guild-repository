<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Super Sightings</title>

        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>The Details</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}">
                            Home
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displaySuperPersonsPage">
                            Super People!
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayOrganizationsPage">
                            Super Organizations!
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayLocationsPage">
                            Super Locations!
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displaySightingsPage">
                            Super Sightings!
                        </a>
                    </li> 
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayPowersPage">
                            Super Powers!
                        </a>
                    </li>
                </ul>    
            </div>
            <p>
                Super Name: <c:out value="${superPerson.superName}"/>
            </p>
            <p>
                Description: <c:out value="${superPerson.superDescription}"/>
            </p>
            <p>
                Power: <c:out value="${superPerson.power.powerName}"/>
            </p>
            <p>

                <c:choose>
                    <c:when test="${superPerson.organization!=null}">Organization(s): 
                        <c:forEach var="org" items="${superPerson.organization}">
                            <c:out value="${org.orgName}"/>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        Organization(s): Not associated with an organization.
                    </c:otherwise>
                </c:choose>

            </p>
            <p>
                <c:choose>
                    <c:when test="${superPerson.side==-1}">
                        Good or Evil?: Evil
                    </c:when>
                </c:choose>
                <c:choose>
                    <c:when test="${superPerson.side==0}">   
                        Good or Evil?: Neutral
                    </c:when> 
                </c:choose>
                <c:choose>
                    <c:when test="${superPerson.side==1}">
                        Good or Evil?: Good
                    </c:when>
                </c:choose>
            </p>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>