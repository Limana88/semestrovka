<%--
  Created by IntelliJ IDEA.
  User: milanamahsotova
  Date: 06.11.2021
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mainLayout title="Choose Hotel">
<c:forEach items="${hotelsForJSP}" var="hotel">
<div class="center-container hotel">
    <div class="hotel">
        <img src=" <c:url value="${hotel.getImagePath()}"/>">
    <h2>${hotel.getName()}</h2>
        <h4>Address: ${hotel.getLocality()}, ${hotel.getStreet()} ${hotel.getBuildingNumber()}</h4>
        <h4>Number free rooms: ${hotel.getNumberFreeRooms()}</h4>
    </div>
        <form method="POST" action="<c:url value="chosenHotel"/>">
            <input type="submit" name="button" class="my-button" value="Choose">
            <input type="hidden" name="idHotel" value="${hotel.getId()}">
        </form>
</div>
</c:forEach>
</t:mainLayout>
