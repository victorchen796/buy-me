<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>BuyMe: Auction</title>

    <script type="text/javascript" src="/static/js/lib/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="/static/js/lib/datatables-1.12.1.min.js"></script>
    <script type="text/javascript" src="/static/js/src/tables.js"></script>

    <link rel="stylesheet" type="text/css" href="/static/css/styles.css">
    <link rel="stylesheet" type="text/css" href="/static/css/auction.css">
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
        <div class="page-section">
            <h2>${auction.name}</h2>
        </div>
        <div class="page-section">
            <h3>Description</h3>
            <p>${auction.description}</p>
        </div>
        <div class="page-section">
            <h3>Current Bid</h3>
            <p>${auction.currentString} (${auction.timeLeft})</p>
            <form action="/auction/${auction.id}/bid" method="post">
                <c:choose>
                    <c:when test="${disabled}">
                        <input type="number" name="bid" step=".01" min="${auction.current + auction.increment}"
                               placeholder="Minimum Bid: ${auction.incrementString}" required disabled>
                        <input type="checkbox" id="anonymous-checkbox" name="anonymous" disabled>
                        <label for="anonymous-checkbox">Bid Anonymously</label>
                        <button type="submit" disabled>Submit Bid</button>
                    </c:when>
                    <c:otherwise>
                        <input type="number" name="bid" step=".01" min="${auction.current + auction.increment}"
                               placeholder="Minimum Bid: ${auction.incrementString}" required>
                        <input type="checkbox" id="anonymous-checkbox" name="anonymous">
                        <label for="anonymous-checkbox">Bid Anonymously</label>
                        <button type="submit">Submit Bid</button>
                    </c:otherwise>
                </c:choose>
            </form>
        </div>
        <div class="page-section">
            <h3>Details</h3>
            <table id="details-table">
                <tbody>
                    <tr>
                        <td>Condition</td>
                        <td>${auction.condition}</td>
                    </tr>
                    <tr>
                        <td>Type</td>
                        <td>${auction.type}</td>
                    </tr>
                    <c:forEach var="detail" items="${auction.details}">
                        <tr>
                            <td>${detail.get("field")}</td>
                            <td>${detail.get("value")}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td>Tags</td>
                        <td>
                            <c:forEach var="tag" items="${auction.tags}" varStatus="status">
                                ${tag}<c:if test="${not (status.count eq auction.tags.size())}">,</c:if>
                            </c:forEach>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="page-section">
            <h3>Posted</h3>
            <p>${auction.timestamp}</p>
        </div>
        <div class="page-section">
            <h2>Bid History</h2>
            <table id="bid-table" class="display">
                <thead>
                    <tr>
                        <th>Bidder</th>
                        <th>Amount</th>
                        <th>Timestamp</th>
                        <th>Date</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="bid" items="${bids}">
                        <tr>
                            <c:choose>
                                <c:when test="${bid.bidderId eq 'anonymous'}">
                                    <td>anonymous</td>
                                </c:when>
                                <c:otherwise>
                                    <td><a href="/user/${bid.bidderId}">${bid.bidderId}</a></td>
                                </c:otherwise>
                            </c:choose>
                            <td>${bid.price}</td>
                            <td>${bid.timestamp}</td>
                            <td>${bid.timestampDate}</td>
                        </tr>
                    </c:forEach>
                </tbody>
                <tfoot>
                    <tr>
                        <th>Bidder</th>
                        <th>Amount</th>
                        <th>Timestamp</th>
                        <th>Date</th> <%-- hidden --%>
                    </tr>
                </tfoot>
            </table>
        </div>
    </div>
</body>