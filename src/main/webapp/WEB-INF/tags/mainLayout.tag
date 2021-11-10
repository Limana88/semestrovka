<%--<%@tag description="Default Layout Tag" pageEncoding="UTF-8" %>--%>
<%@attribute name="title" required="true" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>${title}</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value = "/css/style.css"/>">
    <link rel="stylesheet" href="<c:url value = "/css/bootstrap.css"/>">
    <script src="https://kit.fontawesome.com/508f4cc3aa.js" crossorigin="anonymous"></script>
</head>
<body class="body">
<t:header/>
<jsp:doBody/>
</body>
</html>