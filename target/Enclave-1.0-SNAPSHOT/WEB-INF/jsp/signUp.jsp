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

<t:mainLayout title="signUp">
<div class="center-container profile">
<form method="POST" class="signIn-form" action="<c:url value="signUp"/>">
    <label for="firstName" >Enter your first name:</label>
    <br>
    <input id="firstName"  name="firstName" placeholder="First Name" class="form-field">
    <br>
    <label for="lastName" >Enter your last name:</label>
    <br>
    <input id="lastName"  name="lastName" placeholder="Last Name" class="form-field">
    <br>
    <label for="email" >Enter your email:</label>
    <br>
    <input id="email" type="email" name="email" placeholder="Email" class="form-field">
    <br>
    <label for="password" >Enter your password:</label>
    <br>
    <input id="password" type="password" name="password" placeholder="Password" class="form-field">
    <br>
    <label for="repeat-password">Please, repeat the password you entered</label>
    <br>
    <input id="repeat-password" type="password" name="repeatPassword" placeholder="Repeat password" class="form-field">
    <br>
    <p class="message">${message}</p>
    <br>
    <input type="submit" value="Sign Up" class="my-button">
</form>
</div>
</t:mainLayout>