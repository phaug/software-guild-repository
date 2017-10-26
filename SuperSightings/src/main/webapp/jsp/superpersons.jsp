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
                    <li role="presentation"
                        class="active">
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

            <div class="row">
                <!-- 
                    superperson summary table
                -->
                <div class="col-md-6">
                    <h2>Super People</h2>
                    <table id="contactTable" class="table table-hover">
                        <tr>
                            <th width="20%">Super Name</th>
                            <th width="20%">Super Description</th>
                            <th width="20%">Super Power</th>
                            <th width="20%">Organization(s)</th>
                            <th width="20%">Good or Evil?</th>
                        </tr>
                        <c:forEach var="currentSuper" items="${personsList}">
                            <tr>
                                <td>
                                    <a href="displaySuperPersonDetails?superPersonsId=${currentSuperPerson.superPersonId}">
                                        <c:out value="${currentSuperPerson.superName}"/>
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${currentSuperPerson.description}"/>
                                </td>
                                <td>
                                    <c:out value="${currentSuperPerson.power}"/>
                                </td>
                                <td>
                                    <c:out value="${currentSuperPerson.organization}"/>
                                </td>
                                <td>
                                    <c:out value="${currentSuperPerson.side}"/>
                                </td>
                                <td>
                                    Edit
                                </td>
                                <td>
                                    <a href="deleteSuperPerson?superPersonId=${currentSuperPerson.superPersonId}">
                                        Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>                    
                </div> 
                <!-- 
                    New SuperPerson form
                -->
                <div class="col-md-6">
                    <h2>Add Person</h2>
                    <form class="form-horizontal" 
                          role="form" method="POST" 
                          action="addPerson">
                        <div class="form-group">
                            <label for="add-person-name" class="col-md-4 control-label">Super Name:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="superName" placeholder="Super Name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-description" class="col-md-4 control-label">Description:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="description" placeholder="Description"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-power" class="col-md-4 control-label">Power:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="power" placeholder="Power"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-organization" class="col-md-4 control-label">Organization:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="organization" placeholder="Organization"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-side" class="col-md-4 control-label">Good or Evil?:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="side" placeholder="Side"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Add Person"/>
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

