<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1 style="text-align: center;">Vending Machine</h1>
            <hr/>
            <ul class="list-group" id="errorMessages"></ul>

            <div class="col-md-9" id="itemRows">
                <form class="form-horizontal" method="POST" action="displayItems" role="form" id="displayItems">
                    <c:forEach var="currentItem" items="${items}">
                        <button class="col-md-4" name="itemSelection" value = "${currentItem.itemId}" 
                                type="submit" id="productButton">
                            <p> <c:out value ="${currentItem.itemId}"/> </p>
                            <p> <c:out value ="${currentItem.itemName}"/> </p>
                            <p> <c:out value ="${currentItem.itemCost}"/> </p>
                            <p> <c:out value ="${currentItem.itemInventory}"/> </p>
                        </button>
                    </c:forEach>
                </form>
            </div>


            <div class="col-md-3">
                <h2 style="text-align: center;">Total $ In</h2>
                <form class="form-horizontal" method="POST" action="addMoney" role="form" id="moneyInForm">
                    <div class="form-group">
                        <div class="col-md-12">
                            <input class="form-control" type="text" readonly id="moneyIn" style="border-align: center" 
                                   value="${depositMoney}"></input>
                        </div>
                        <div class="col-md-6">
                            <button type="submit"
                                    name="moneyAdded"
                                    id="addDollar"
                                    value="1.00"
                                    class="btn btn-default">
                                Add Dollar
                            </button>
                        </div>
                        <div class="col-md-6">
                            <button type="submit"
                                    name="moneyAdded"
                                    id="addQuarter"
                                    value="0.25"
                                    class="btn btn-default">
                                Add Quarter
                            </button>
                        </div>
                        <div class="col-md-6">
                            <button type="submit"
                                    name="moneyAdded"
                                    id="addDime"
                                    value="0.10"
                                    class="btn btn-default">
                                Add Dime
                            </button>
                        </div>
                        <div class="col-md-6">
                            <button type="submit"
                                    name="moneyAdded"
                                    id="addNickel"
                                    value="0.05"
                                    class="btn btn-default">
                                Add Nickel
                            </button>
                        </div>
                    </div>
                </form>
                <hr>
                <!-- <div class="make-purchase"> -->
                <h2 style="text-align: center;">Messages</h2>
                <form class="form-horizontal" method="POST" action="purchase" role="form" id="messages">
                    <div class="form-group">
                        <div class="col-md-12">
                            <span id="make-purchase"></span>
                        </div>
                        <div class="col-md-12">
                            <input class="form-control" value="${messages}" type="text" readonly id="displayMessages" 
                                   style="border-align: center"></input>
                        </div>
                        <div class="col-md-3">
                            Index:
                        </div>
                        <div class="col-md-9">
                            <input name="purchase" class="form-control" type="text" readonly id="selectedItemIndex" style="border"
                                   value="${itemId}"></input>
                        </div>
                        <div class="col-md-6">
                            <button type="submit"
                                    name="purchase"
                                    id="makePurchase"
                                    class="btn btn-default">
                                Make Purchase
                            </button>
                        </div>
                    </div>
                </form>
                <hr>
                <!-- <div class="dispense-change"> -->
                <h2 style="text-align: center;">Change</h2>
                <form class="form-horizontal" method="POST" action="returnChange" role="form" id="messages">
                    <div class="form-group">
                        <div class="col-md-12">
                            <span id="make-purchase"></span>
                        </div>
                        <div class="col-md-12">
                            <input name="returnChange" value="${returnChange}" class="form-control" type="text" readonly id="change" style="border-align: center"></input>
                        </div>
                        <div class="col-md-6">
                            <button type="submit"
                                    id="returnChange"
                                    class="btn btn-default">
                                Change Return
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

