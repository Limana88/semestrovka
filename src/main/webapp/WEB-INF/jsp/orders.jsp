<%--
  Created by IntelliJ IDEA.
  User: milanamahsotova
  Date: 09.11.2021
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mainLayout title="Orders">
    <script src="/js/liveSearch.js"></script>
    <div class="center-container">
    <h3 class="hotel">Here's your history order, you can cancel your booking
        simply by clicking on the cancel button or you can renew it by
        clicking on the renew button</h3>
    </div>

    <div>
        <form method="GET" action="<c:url value="profile.jsp"/>" >
            <input type="button" class="btn btn-light" value="Profile">
        </form>
    </div>
    <c:forEach items="${orders}" var="order">
    <div class="center-container">
        <div class="hotel">
            <img src=" <c:url value="${order.getHotel().getImagePath()}"/>">
            <h1 class="elastic">${order.getHotel().getName()}</h1>
            <h4>Address: ${order.getHotel().getLocality()}, ${order.getHotel().getStreet()} ${order.getHotel().getBuildingNumber()}</h4>
            <h4>Number free rooms: ${order.getHotel().getNumberFreeRooms()}</h4>
            <h4>Check-in date: ${order.getCheckInDate()} </h4>
            <h4>Check-out date: ${order.getCheckOutDate()}</h4>
        </div>
<form method="POST" action="<c:url value="cancelReservation"/>" >
    <c:choose>
    <c:when test="${order.getReservationStatus().equals('active')}">
        <input type="submit" name="button" value="Cancel reservation" class="my-button">
        <input type="hidden" name="idOrder" value="${order.getId()}">
        </c:when>
        <c:when test="${order.getReservationStatus().equals('inactive')}">
        <input type="submit" name="button" value="Renew reservation" class="my-button">
        <input type="hidden" name="idOrder" value="${order.getId()}">
        </c:when>
        </c:choose>
</form>
    </div>
    </c:forEach>


</t:mainLayout>
