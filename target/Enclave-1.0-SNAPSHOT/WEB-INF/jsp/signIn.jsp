<%--
  Created by IntelliJ IDEA.
  User: milanamahsotova
  Date: 31.10.2021
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mainLayout title="signIn">

<div class="center-container">
<form method="POST"  class="signIn-form" action="<c:url value="signIn"/>">
    <label for="email" >Enter your email:</label>
    <br>
    <input id="email" type="email" name="email" placeholder="Email" class="form-field">
    <br>
    <label for="password" >Enter your password:</label>
    <br>
    <input id="password" type="password" name="password" placeholder="Password" class="form-field">
    <br>
    <p class="message">${message}</p>
    <br>
    <input type="submit" class="my-button" value="Sign In ">
</form>
</div>
</t:mainLayout>
