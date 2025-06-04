<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <title>Faces Registration</title>
</head>
<body>
<%@ include file="header.jsp" %>
<div>
    <form method="post" action="/registration">
        <table>
            <tr>
                <td><h3>Email</h3></td>
                <td><input type="email" name="email" placeholder="user@faces.com"></td>
            </tr>
            <tr>
                <td><h3>Password</h3></td>
                <td><input type="password" name="password" placeholder="password"></td>
            </tr>
            <tr>
                <td><h3> Repeat Password</h3></td>
                <td><input type="password" name="repeatPassword" placeholder="repeat password"></td>
            </tr>
        </table>
        <button type="submit">${requestScope.wordBundle.get("save")}</button>
    </form>
    <c:if test="${not empty errorMessage}">
        <div style="color:red">${errorMessage}</div>
    </c:if>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>