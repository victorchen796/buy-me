<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>BuyMe: Create Auction</title>

    <script type="text/javascript" src="/static/js/lib/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="/static/js/src/create-auction.js"></script>

    <link rel="stylesheet" type="text/css" href="/static/css/styles.css">
    <link rel="stylesheet" type="text/css" href="/static/css/create-auction.css">
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
        <form id="form" action="/auction/create/attempt" method="POST" novalidate>
            <div class="section">
                <h1>Create an Auction</h1>
            </div>
            <div class="section">
                <div>
                    <h2 class="section-heading">Title</h2>
                    <p class="subscript">What is the name of your auction? (250 characters max.)</p>
                    <input type="text" name="title" id="title" maxlength="250">
                    <p id="title-error" class="form-error"></p>
                </div>
            </div>
            <div class="section">
                <h2 class="section-heading">Item Information</h2>
                <div>
                    <h3 class="field-heading">Description</h3>
                    <p class="subscript">Describe your item and/or why you are selling it. (1000 characters max.)</p>
                    <textarea rows="10" name="description" id="description" maxlength="1000"></textarea>
                    <p id="description-error" class="form-error"></p>
                </div>
                <div>
                    <h3 class="field-heading">Condition</h3>
                    <p class="subscript">What is the condition of your item?</p>
                    <select name="condition" id="condition">
                        <option value="New">New</option>
                        <option value="Open-box">Open-box</option>
                        <option value="Refurbished">Refurbished</option>
                        <option value="Used">Used</option>
                        <option value="For parts or not working">For parts or not working</option>
                    </select>
                    <h3 class="field-heading">Type</h3>
                    <p class="subscript">What type of item are you selling? (50 characters max.)</p>
                    <input type="text" name="type" id="type" maxlength="50">
                    <p id="type-error" class="form-error"></p>
                </div>
            </div>
            <div class="section">
                <div>
                    <h2 class="section-heading">Additional Item Information</h2>
                    <h3 class="field-heading">Details</h3>
                    <p class="subscript">Add any relevant specifications or information. (Optional)</p>
                    <table>
                        <thead>
                            <tr>
                                <td></td>
                                <td><h4 class="field-heading">Field</h4><p class="subscript">50 characters max.</p></td>
                                <td><h4 class="field-heading">Value</h4><p class="subscript">100 characters max.</p></td>
                                <td></td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach begin="1" end="10" varStatus="loop">
                                <tr id="detail-row${loop.count}">
                                    <td>${loop.count}</td>
                                    <td>
                                        <input type="text" id="field${loop.count}" name="field${loop.count}" maxlength="50">
                                        <p id="field${loop.count}-error" class="form-error"></p>
                                    </td>
                                    <td>
                                        <input type="text" id="value${loop.count}" name="value${loop.count}" maxlength="100">
                                        <p id="value${loop.count}-error" class="form-error"></p>
                                    </td>
                                    <td><button type="button" onclick="removeDetail(${loop.count})" class="row-remove-button">&#10006;</button></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="4"><button type="button" onclick="addDetail()" class="add-button">Add Detail</button></td>
                            </tr>
                        </tfoot>
                    </table>
                </div>
                <div>
                    <h3 class="field-heading">Tags</h3>
                    <p class="subscript">Add descriptive keywords that may help buyers find your item. (Optional)</p>
                    <table>
                        <thead>
                            <tr>
                                <td></td>
                                <td><h4 class="field-heading">Keyword</h4><p class="subscript">50 characters max.</p></td>
                                <td></td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach begin="1" end="20" varStatus="loop">
                                <tr id="tag-row${loop.count}">
                                    <td>${loop.count}</td>
                                    <td>
                                        <input type="text" id="tag${loop.count}" name="tag${loop.count}" maxlength="50">
                                    </td>
                                    <td><button type="button" onclick="removeTag(${loop.count})" class="row-remove-button">&#10006;</button></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="3"><button type="button" onclick="addTag()" class="add-button">Add Tag</button></td>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
            <div class="section">
                <h2 class="section-heading">Pricing</h2>
                <div>
                    <h3 class="field-heading">Initial Price</h3>
                    <p class="subscript">What is the starting price of your item?</p>
                    <input type="number" id="price" name="price" value="0.01" step=".01" min=".01">
                </div>
                <div>
                    <h3 class="field-heading">Price Increment</h3>
                    <p class="subscript">What would you like to set the minimum bid increment to?</p>
                    <input type="number" id="increment" name="increment" value="0.01" step=".01" min=".01">
                </div>
                <div>
                    <h3>Auction Expiration</h3>
                    <input type="datetime-local" name="expiration" id="expiration">
                    <p id="expiration-error" class="form-error"></p>
                </div>
            </div>
            <div class="section">
                <div>
                    <button type="submit" class="submit-button"><b>Create Auction</b></button>
                </div>
            </div>
            <textarea id="data" name="data" style="display: none"></textarea>
        </form>
    </div>
</body>
</html>