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
            <h1>Super Sightings!</h1>
            <h2>Have you seen them?</h2>
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
                    <li role="presentation"
                        class="active">
                        <a href="${pageContext.request.contextPath}/displayPowersPage">
                            Super Powers!
                        </a>
                    </li>
                </ul>    
            </div>

            <div class="row">
                <!-- 
                    List of powers
                -->
                <div class="col-md-6">
                    <h2>Powers</h2>
                    <table id="powerTable" class="table table-hover">
                        <tr>
                            <th width="70%">Power</th>
                            <th width="30%"></th>

                        </tr>
                        <c:forEach var="currentPower" items="${powersList}">
                            <tr>
                                <td>
                                    <c:out value="${currentPower.powerName}"/>
                                </td>
                            </tr>
                        </c:forEach> 
                    </table>                    
                </div> 
                <!-- 
                    new sighting form 
                -->
                <div class="col-md-6">
                    <h2>Add New Power</h2>
                    <form class="form-horizontal" 
                          role="form" method="POST" 
                          action="addPower">
                        <div class="form-group">
                            <label for="addPower" class="col-md-4 control-label">Power:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="power" placeholder="Power"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Add Power"/>
                            </div>
                        </div>
                    </form>

                </div> 

            </div>
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
