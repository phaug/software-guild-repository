<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Company Contacts</title>
        
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>The Details</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
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
                </ul>    
            </div>
            <p>
                Super Name: <c:out value="${person.SuperName}"/>
            </p>
            <p>
                Description: <c:out value="${person.Description}"/>
            </p>
            <p>
                Power: <c:out value="${person.Power}"/>
            </p>
            <p>
                Organization(s): <c:out value="${person.Organization}"/>
            </p>
            <p>
                Good or Evil?: <c:out value="${person.Side}"/>
            </p>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>