<%--
  Created by IntelliJ IDEA.
  User: milanamahsotova
  Date: 06.11.2021
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mainLayout title="Profile">

<div class="center-container">
    <h3 class="hotel">Welcome to the website for hotel reservations!</h3>
</div>
<div style="text-align: center" >
    <div class="button-wrapper-inner"><button type="button" class="btn btn-outline-primary "><a href="<c:url value="/signIn"/>"> Sign In</a></button></div>
    <div class="button-wrapper-inner"><button type="button" class="btn btn-outline-primary" ><a href="<c:url value="/signUp"/>"> Sign Up</a></button></div>
</div>
</div>

</t:mainLayout>
