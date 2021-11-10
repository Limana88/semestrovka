<%--
  Created by IntelliJ IDEA.
  User: milanamahsotova
  Date: 03.11.2021
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mainLayout title="Profile">
<div class="center-container">
<div class="profile">
    <h1>Your profile</h1>
    <h4>Name: </h4>
    <h3>${user.getFirstName()}</h3>
    <h4>Second Name: </h4>
    <h3>${user.getSecondName()}</h3>
    <h4>Email: </h4>
    <h3>${user.getEmail()}</h3>
</div>
         <form method="POST" action="<c:url value="hotelList"/>">
            <input type="submit" class="my-button" value="Choose hotel">
        </form>
        <form method="POST" action="<c:url value="historyOrders"/>">
            <input type="submit" class="my-button " value="History of orders">
        </form>
    <a href="<c:url value="/logout"/>" class="my-logout-button" id="logout">Logout</a>
</div>
</t:mainLayout>
