<%--
  Created by IntelliJ IDEA.
  User: milanamahsotova
  Date: 08.11.2021
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mainLayout title="Hotel">

    <div class="center-container">
        <div class="hotel">
            <img src=" <c:url value="${hotel.getImagePath()}"/>">
            <h1>${hotel.getName()}</h1>
            <h4>Address: ${hotel.getLocality()}, ${hotel.getStreet()} ${hotel.getBuildingNumber()}</h4>
            <h4>Number free rooms: ${hotel.getNumberFreeRooms()}</h4>
        </div>
<div>
    <form method="POST" action="<c:url value="/hotel"/>" class="hotel">
    <h4>Please, select the date of your arrival and departure in the calendar</h4>
    <input id="checkInDate" type="date" name="checkInDate" lang="en">
        <input id="checkOutDate" type="date" name="checkOutDate">
        <input type="submit" value="Save">
    </form>
</div>
    </div>
</t:mainLayout>