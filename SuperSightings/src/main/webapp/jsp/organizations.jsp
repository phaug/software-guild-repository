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
                        <a href="${pageContext.request.contextPath}/index.jsp">
                            Home
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displaySuperPersonsPage">
                            Super People!
                        </a>
                    </li>
                    <li role="presentation"
                        class="active">
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

            <div class="row">
                <!-- 
                    organization table 
                -->
                <div class="col-md-6">
                    <h2>Organizations</h2>
                    <table id="contactTable" class="table table-hover">
                        <tr>
                            <th width="25%">Name</th>
                            <th width="25%">Description</th>
                            <th width="25%">Location</th>
                            <th width="25%">Phone</th>
                        </tr>
                        <c:forEach var="currentOrganization" items="${orgList}">
                            <tr>
                                <td>
                                    <c:out value="${currentOrganization.organizationName}"/> 
                                </td>
                                <td>
                                    <c:out value="${currentOrganization.description}"/>
                                </td>
                                <td>
                                    <c:out value="${currentOrganization.location}"/>
                                </td>
                                <td>
                                    <c:out value="${currentOrganization.Phone}"/>
                                </td>
                                <td>
                                    Edit
                                </td>
                                <td>
                                    Delete
                                </td>
                            </tr>
                        </c:forEach>
                    </table>                    
                </div> 
                <!-- 
                    new organization form 
                -->
                <div class="col-md-6">
                    <h2>Add New Organization</h2>
                    <form class="form-horizontal" 
                          role="form" method="POST" 
                          action="createContact">
                        <div class="form-group">
                            <label for="add-organization-name" class="col-md-4 control-label">Organization Name:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="organizationName" placeholder="Organization Name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-description" class="col-md-4 control-label">Description:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="description" placeholder="Description"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-location" class="col-md-4 control-label">Location:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="location" placeholder="Location"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-phone" class="col-md-4 control-label">Phone:</label>
                            <div class="col-md-8">
                                <input type="tel" class="form-control" name="phone" placeholder="Phone"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Create Organization"/>
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
