<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<html>
<head>
    <title>BuyMe: Home</title>

    <script type="text/javascript" src="/static/js/lib/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="/static/js/lib/datatables-1.12.1.min.js"></script>
    <script type="text/javascript" src="/static/js/src/tables.js"></script>

    <link rel="stylesheet" type="text/css" href="/static/css/styles.css">
    <link rel="stylesheet" type="text/css" href="/static/css/datatables-1.12.1.min.css">
</head>
<body>
    <div id="nav-bar">
        <div id="nav-bar-inner">
            <a href="/home" id="nav-bar-logo">BuyMe</a>
            <div id="nav-bar-buttons">
                <a href="/auction/create" class="nav-bar-button">Create Auction</a>
                <c:choose>
                    <c:when test="${not empty user}">
                        <a href="/user/${user.username}" class="nav-bar-button">${user.username}</a>
                        <a href="/login/sign-out" class="nav-bar-button">Sign Out</a>
                    </c:when>
                    <c:otherwise>
                        <a href="/login" class="nav-bar-button">Login</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    <div id="body-div">
<%--        TODO: add filter options here--%>
        <h2>Auctions</h2>
        <table id="auction-table" class="display">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Condition</th>
                    <th>Type</th>
                    <th>Tags</th>
                    <th>Price</th>
                    <th>Time Left</th>
                    <th>Expiration</th>
                    <th>Seller</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="auction" items="${auctions}">
                    <tr>
                        <td><a href="/auction/${auction.auctionId}">${auction.name}</a></td>
                        <td>${auction.condition}</td>
                        <td>${auction.type}</td>
                        <td>${auction.tags}</td>
                        <td>${auction.current}</td>
                        <td>${auction.timeLeft}</td>
                        <td>${auction.expiration}</td>
                        <td><a href="/user/${auction.sellerId}">${auction.sellerId}</a></td>
                    </tr>
                </c:forEach>
            </tbody>
            <tfoot>
                <tr>
                    <th>Title</th>
                    <th>Condition</th>
                    <th>Type</th>
                    <th>Tags</th> <%-- hidden --%>
                    <th>Price</th>
                    <th>Time Left</th>
                    <th>Expiration</th> <%-- hidden --%>
                    <th>Seller</th>
                </tr>
            </tfoot>
        </table>
    </div>
</body>
</html>
