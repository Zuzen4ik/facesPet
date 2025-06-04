<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>Charm Profiles</title>
</head>
<body>
<%@ include file="header.jsp" %>
<div>
    <h3 style="color: red">Email-warning</h3>
    <form method="post" action="/email">
        <input type="hidden" name="_method" value="put"/>
        <input type="hidden" name="id" value="${requestScope.profile.id}">
        <table>
            <tr>
                <td><h3>Email</h3></td>
                <td><input type="email" name="email" value="${requestScope.profile.email}"></td>
            </tr>
            <tr>
                <td><h3>Repeat Email</h3></td>
                <td><input type="email" name="repeatEmail" placeholder="repeat email"></td>
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