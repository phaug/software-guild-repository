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
            <h1>Sighting Details</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/index.jsp">
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
                Sighting Date: <c:out value="${sighting.date}"/>
            </p>
            <p>
                Location: <c:out value="${sighting.location.locationName}"/>
            </p>
            <p>

                <c:choose>
                    <c:when test="${sighting.superPerson!=null}">Super People: 
                        <c:forEach var="superPerson" items="${sighting.superPerson}">
                            <c:out value="${superPerson.superName}"/>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        Super People: Not associated with a sighting.
                    </c:otherwise>
                </c:choose>

            </p>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>